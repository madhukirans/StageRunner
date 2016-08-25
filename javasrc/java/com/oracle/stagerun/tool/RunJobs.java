package com.oracle.stagerun.tool;

import com.oracle.stagerun.beans.StageRunWeb;
import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressStatus;
import com.oracle.stagerun.entity.Releases;
import com.oracle.stagerun.entity.ShiphomeNames;
import com.oracle.stagerun.entity.Stage;
import com.oracle.stagerun.entity.StageUpperstackShiphomes;
import com.oracle.stagerun.entity.TestUnitRunTypeEnum;
import com.oracle.stagerun.service.excetion.ShiphomeNamesNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RunJobs {

    Map<String, String> shiphomesEnv = new HashMap<>();
    private Releases release;
    private Stage stage;

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    @Inject
    private StageRunWeb sr;

    public RunJobs() {

    }

    public void execute(Releases release, Stage stage, List<RegressDetails> jobsList)//, List<StageUpperstackShiphomes> shiphomeList,
            //        List<StageUpperstackShiphomes> allShiphomeList)
            throws ShiphomeNamesNotFoundException {
        this.release = release;
        this.stage = stage;

        TypedQuery<StageUpperstackShiphomes> allShiphomeQery = em.createNamedQuery("StageUpperstackShiphomes.findByStage", StageUpperstackShiphomes.class);
        allShiphomeQery.setParameter("stage", stage.getId());
        List<StageUpperstackShiphomes> allShiphomeList = allShiphomeQery.getResultList();

        shiphomesEnv.put("RELEASE", release.getName());
        shiphomesEnv.put("STAGE", stage.getStageName());
        setShiphomeEnvironmentVariables(allShiphomeList);

        //delete root folder @TODO. Remove this in production mode.
        new File(sr.getRootFolder()).delete();

        for (RegressDetails regress : jobsList) {
            String farmJobCommand = regress.getTestunit().getJobreqAgentCommand();
            String stageName = regress.getStage().getStageName();
            String releaseName = regress.getStage().getRelease().getName();
            String testUnitName = regress.getTestunit().getTestunitName();

            String regressDir = sr.getStageDirectory(regress);
            if (regress.getTestunit().getIsDte() == TestUnitRunTypeEnum.DTE) {
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
            } else {
                /*String bashFileName = regressDir + "/" + LocalDateTime.now().toString().replace(":", ".") + ".bash.sh";
                File bashFile = new File(bashFileName);

                try {
                    new File(regressDir).mkdirs();
                    try (PrintWriter out = new PrintWriter(new FileWriter(bashFile))) {
                        out.println(farmJobCommand);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                String jobFileName = regressDir + "/" + LocalDateTime.now().toString().replace(":", ".") + ".sh";
                File jobFile = new File(jobFileName);

                try {                    
                    try (PrintWriter out = new PrintWriter(new FileWriter(jobFile))) {
                        out.println("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                regress.setFileToRun(jobFile);
                 */
            }

            runFarmCommand(regress);
        };
    }

    private void setShiphomeEnvironmentVariables(List<StageUpperstackShiphomes> allShiphomeList) throws ShiphomeNamesNotFoundException {
        //Set SHIPHOME environemnt varibales
        for (StageUpperstackShiphomes shiphome : allShiphomeList) {

            TypedQuery<ShiphomeNames> query = em.createNamedQuery("ShiphomeNames.findByReleaseAndProductPlatform", ShiphomeNames.class);
            query.setParameter("release", release.getId());
            query.setParameter("product", shiphome.getProduct().getId());
            query.setParameter("platform", shiphome.getPlatform().getId());

            List<ShiphomeNames> shiphomeNames = query.getResultList();
            sr.print("shiphomeNames:" + release.getId() + shiphomeNames + shiphome.getProduct().getId() + shiphome.getPlatform().getId());

            if (shiphomeNames == null || shiphomeNames.size() == 0) {
                //throw new ShiphomeNamesNotFoundException("" + query.toString());
                continue;
            }

            for (ShiphomeNames shiphomeName : shiphomeNames) {
                String shipohmeLoc = shiphome.getPlatform().getName().replace(".", "_") + "_" + shiphome.getProduct().getName()
                        + "_" + shiphomeName.getComponent().getName() + "_SHIPHOME";
                String shiphomeLoc = shiphome.getShiphomeloc() + "/" + shiphomeName.getName();

                if (shiphome.getPlatform().getName().startsWith("WINDOWS")) {
                    shiphomeLoc = shiphomeLoc.replaceAll("\\\\", "/");
                }
                shiphomesEnv.put(shipohmeLoc.toUpperCase(), shiphomeLoc);
            };
        };

        sr.print("shiphomesEnv:" + shiphomesEnv);
    }

    private void wiriteEnvFile(Map map, RegressDetails regress) {
        String regressDir = sr.getStageDirectory(regress);

        SortedProperties properties = new SortedProperties();
        properties.putAll(map);
        try {
            FileOutputStream out = new FileOutputStream(regressDir + "/env.prop");
            properties.store(out, null);
        } catch (Exception e) {
            sr.print("Exception : wiriteEnvFile: " + e, regress);
        }
    }

    class SortedProperties extends Properties {
        public Enumeration keys() {
            Enumeration<Object> keysEnum = super.keys();
            Vector keyList = new Vector();
            while (keysEnum.hasMoreElements()) {
                keyList.add((String) keysEnum.nextElement());
            }
            Collections.sort(keyList);
            return keyList.elements();
        }
    }

    private void runFarmCommand(RegressDetails regress) {
        try {
            sr.print("Farm Submit Command:" + regress.getFileToRun().getCanonicalPath(), regress);
            shiphomesEnv.put("PLATFORM", regress.getTestunit().getPlatform().getName());
            shiphomesEnv.put("PRODUCT", regress.getProduct().getName());
            shiphomesEnv.put("COMPONENT", regress.getComponent().getName());

            ProcessBuilder processBuilder = new ProcessBuilder("bash", regress.getFileToRun().getCanonicalPath());
            Map<String, String> env = processBuilder.environment();
            env.putAll(shiphomesEnv);
            env.put("PLATFORM", regress.getTestunit().getPlatform().getName());
            wiriteEnvFile(env, regress);

            Process process = processBuilder.start();
            process.waitFor(10, TimeUnit.MINUTES);

            int exitStatus = process.exitValue();
            sr.print("Fram job submit status:" + exitStatus, regress);
            if (exitStatus == 0) {
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                String errString;
                while ((errString = err.readLine()) != null) {
                    sr.print("Command Error:" + errString + "\n", regress);
                    regress.setStatus(RegressStatus.failed);
                    em.merge(regress);
                }

                String currLine = null;
                int farmId = 0;
                while ((currLine = in.readLine()) != null) {
                    sr.print("Output: " + currLine + "\n", regress);
                    if (currLine.contains("farm showjobs -d -j")) {
                        String str = currLine.substring(currLine.indexOf("(") + 1, currLine.indexOf(")")).trim();
                        str = str.substring(str.lastIndexOf(" ")).trim();
                        try {
                            farmId = Integer.parseInt(str);
                            regress.setFarmrunId(farmId);
                            regress.setStatus(RegressStatus.running);
                            em.merge(regress);
                        } catch (Exception e) {
                            sr.print("Exception : runFarmCommand: Reading command output: " + e, regress);
                        }
                    }
                }

                if (farmId == 0) {
                    regress.setStatus(RegressStatus.failed);
                }

                sr.print("Farm id: ", regress);
            } else {
                BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String errString;
                sr.print("Error: Command failed ", regress);
                while ((errString = err.readLine()) != null) {
                    sr.print("Error:" + errString, regress);
                }
                regress.setStatus(RegressStatus.failed);
                em.merge(regress);
            }

        } catch (Exception e) {
            sr.print("Exception : runFarmCommand:" + e, regress);
        }
    }
}
