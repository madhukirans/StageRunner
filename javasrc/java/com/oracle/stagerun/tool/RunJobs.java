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

public class RunJobs {

    Map<String, String> shiphomesEnv = new HashMap<>();
    List<File> jobList = new ArrayList<>();
    String rootFolder = "/tmp/sr/work";

    public RunJobs(List<RegressDetails> jobsList, List<StageUpperstackShiphomesEntity> shiphomeList) {
        if (shiphomeList != null && shiphomeList.size() > 0) {
            shiphomesEnv.put("RELEASE", shiphomeList.get(0).getStageid().getReleaseEntity().getReleaseName());
            shiphomesEnv.put("STAGE", shiphomeList.get(0).getStageid().getStageName());
        }
        
        shiphomeList.stream().forEach((shiphome) -> {
            shiphomesEnv.put(shiphome.getPlatform().getName() + "_" + shiphome.getProduct().getProductName() + "_SHIPHOME",
                    shiphome.getShiphomeloc() + "/" + shiphome.getShiphomenames());
        });

        for (RegressDetails regress : jobsList) {
            String farmJobCommand = regress.getTestunitId().getJobreqAgentCommand();
            String stageName = regress.getStageId().getStageName();
            String releaseName = regress.getStageId().getReleaseEntity().getReleaseName();
            String testUnitName = regress.getTestunitId().getTestUnitName();

            String filePath = rootFolder + "/" + releaseName + "/" + stageName;
            String jobFileName = filePath + "/" + regress.getProduct().getProductName()
                    + "_" + testUnitName + "_" + LocalDateTime.now().toString().replace(":", ".") + ".sh";
            File jobFile = new File(jobFileName);

            try {
                new File(filePath).mkdirs();
                try (PrintWriter out = new PrintWriter(new FileWriter(jobFile))) {
                    out.println(farmJobCommand);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                jobList.add(jobFile);
            } catch (Exception e) {

            }
            runFarmCommand(regress);
        };

//        for (FarmJob job : jobs) {
//            if (job.isEnabled()) {
//                StageRun.sapphireProperties.setProperty(job + ".farmid", job.getFarmId() + "");
//            }
//        }
        //  StageRun.updatePropertyFile();
    }

    private void runFarmCommand(RegressDetails regress) {
        try {

            for (File job : jobList) {
                StageRun.print("Farm Submit Command:" + job.getCanonicalPath(), Color.BLUE);
                shiphomesEnv.put("PLATFORM", regress.getTestunitId().getPlatform().getName());
                shiphomesEnv.put("PRODUCT", regress.getProduct().getProductName());
                
                ProcessBuilder processBuilder = new ProcessBuilder("bash", job.getCanonicalPath());
                Map<String, String> env = processBuilder.environment();
                env.putAll(shiphomesEnv);
                env.put("PLATFORM", regress.getTestunitId().getPlatform().getName());

                Process process = processBuilder.start();
                process.waitFor(30, TimeUnit.MINUTES);

                int exitStatus = process.exitValue();
                StageRun.print("Fram job submit status:" + exitStatus, Color.GREEN);
                if (exitStatus == 0) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                    String errString;
                    while ((errString = err.readLine()) != null) {
                        StageRun.print("Command Error:" + errString + "\n", Color.RED);
                        regress.setStatus(RegressStatus.failed);
                    }

                    String currLine = null;
                    int farmId = 0;
                    while ((currLine = in.readLine()) != null) {
                        StageRun.print("Output: " + currLine + "\n", Color.BLUE);
                        if (currLine.contains("farm showjobs -d -j")) {
                            String str = currLine.substring(currLine.indexOf("(") + 1, currLine.indexOf(")")).trim();
                            str = str.substring(str.lastIndexOf(" ")).trim();
                            try {
                                farmId = Integer.parseInt(str);
                                regress.setFarmrunId(farmId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (farmId == 0) {
                        regress.setStatus(RegressStatus.failed);
                    }

                    StageRun.print("Farm id: " + regress.getFarmrunId());
                } else {
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String errString;
                    StageRun.print("Error: Command failed " + regress, Color.RED);
                    while ((errString = err.readLine()) != null) {
                        StageRun.print("Error:" + errString, Color.RED);
                    }
                    regress.setStatus(RegressStatus.failed);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
