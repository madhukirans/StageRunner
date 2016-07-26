package com.oracle.stagerun.tool;

import com.oracle.stagerun.entities.RegressDetails;
import com.oracle.stagerun.entities.RegressStatus;
import com.oracle.stagerun.entities.StageUpperstackShiphomesEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RunJobs {

    Map<String, String> shiphomesEnv = new HashMap<>();    
    
    private EntityManager em;

    public RunJobs(List<RegressDetails> jobsList, List<StageUpperstackShiphomesEntity> shiphomeList,
            List<StageUpperstackShiphomesEntity> allShiphomeList, EntityManager em) {
        this.em = em;
        if (shiphomeList != null && shiphomeList.size() > 0) {
            shiphomesEnv.put("RELEASE", shiphomeList.get(0).getStageid().getReleaseEntity().getReleaseName());
            shiphomesEnv.put("STAGE", shiphomeList.get(0).getStageid().getStageName());
        }
        
        new File(StageRun.getRootFolder()).delete();
        
        allShiphomeList.stream().forEach((shiphome) -> {
            shiphomesEnv.put(shiphome.getPlatform().getName().replace(".", "_") + "_" + shiphome.getProduct().getProductName() + "_SHIPHOME_LOC",
                    shiphome.getShiphomeloc() + "/" + shiphome.getShiphomenames());
        });

        for (RegressDetails regress : jobsList) {
            String farmJobCommand = regress.getTestunitId().getJobreqAgentCommand();
            String stageName = regress.getStageId().getStageName();
            String releaseName = regress.getStageId().getReleaseEntity().getReleaseName();
            String testUnitName = regress.getTestunitId().getTestUnitName();

            String regressDir = StageRun.getStageDirectory(regress);
            String jobFileName = regressDir + "/" + LocalDateTime.now().toString().replace(":", ".") + ".sh";
            File jobFile = new File(jobFileName);

            try {
                new File(regressDir).mkdirs();
                try (PrintWriter out = new PrintWriter(new FileWriter(jobFile))) {
                    out.println(farmJobCommand);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            regress.setFileToRun(jobFile);            
            runFarmCommand(regress);
        };
    }

    private void runFarmCommand(RegressDetails regress) {
        try {            
                StageRun.print("Farm Submit Command:" + regress.getFileToRun().getCanonicalPath(), regress);
                shiphomesEnv.put("PLATFORM", regress.getTestunitId().getPlatform().getName());
                shiphomesEnv.put("PRODUCT", regress.getProduct().getProductName());                
                StageRun.print("shiphomesEnv:" + shiphomesEnv, regress);
                ProcessBuilder processBuilder = new ProcessBuilder("bash", regress.getFileToRun().getCanonicalPath());
                Map<String, String> env = processBuilder.environment();
                env.putAll(shiphomesEnv);
                env.put("PLATFORM", regress.getTestunitId().getPlatform().getName());

                Process process = processBuilder.start();
                process.waitFor(10, TimeUnit.MINUTES);

                int exitStatus = process.exitValue();
                StageRun.print("Fram job submit status:" + exitStatus, regress);
                if (exitStatus == 0) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                    String errString;
                    while ((errString = err.readLine()) != null) {
                        StageRun.print("Command Error:" + errString + "\n", regress);
                        regress.setStatus(RegressStatus.failed);
                        em.merge(regress);
                    }

                    String currLine = null;
                    int farmId = 0;
                    while ((currLine = in.readLine()) != null) {
                        StageRun.print("Output: " + currLine + "\n", regress);
                        if (currLine.contains("farm showjobs -d -j")) {
                            String str = currLine.substring(currLine.indexOf("(") + 1, currLine.indexOf(")")).trim();
                            str = str.substring(str.lastIndexOf(" ")).trim();
                            try {
                                farmId = Integer.parseInt(str);
                                regress.setFarmrunId(farmId);
                                regress.setStatus(RegressStatus.running);
                                em.merge(regress);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (farmId == 0) {
                        regress.setStatus(RegressStatus.failed);
                    }

                    StageRun.print("Farm id: " , regress);
                } else {
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String errString;
                    StageRun.print("Error: Command failed " , regress);
                    while ((errString = err.readLine()) != null) {
                        StageRun.print("Error:" + errString, regress);
                    }
                    regress.setStatus(RegressStatus.failed);
                    em.merge(regress);
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
