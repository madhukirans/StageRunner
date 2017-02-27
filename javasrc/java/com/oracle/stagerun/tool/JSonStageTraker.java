/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.tool;

import com.oracle.stagerun.entity.Platform;
import com.oracle.stagerun.entity.Product;
import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressStatus;
import com.oracle.stagerun.entity.Stage;
import com.oracle.stagerun.entity.StageUpperstackShiphomes;
import com.oracle.stagerun.entity.Testunit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author mseelam
 */
public class JSonStageTraker implements Runnable {

    private String requestURL = "http://changeiq.oraclecorp.com/fmw-stage/rest/config/store/fmw-stage-info/FMW12.2.1.3.0";
    private JSONObject root;
    private JSONObject intgRoot;

    String rootString = "";
    String intgReleaseRootLabel = "manifest.integration.release";
    String ascoreWindowsLabel = "ascore-windows_x64-manifest";
    String ascoreLinuxlabel = "ascore-linux_x64-manifest";
    String askernelLabel = "askernel-manifest";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StageRunnerPUMain");
    private StageRunDaemon sr;

    //private ArrayList<StageUpperstackShiphomes> shiphomeList;
    public JSonStageTraker(StageRunDaemon sr) {
        //shiphomeList = new ArrayList<>();
        this.sr = sr;
        try {
            URL wikiRequest = new URL(requestURL);
            JSONTokener tokener = new JSONTokener(wikiRequest.openStream());
            root = new JSONObject(tokener);
            intgRoot = root.getJSONObject(intgReleaseRootLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String a[]) {
        //JSonStageTraker j = new JSonStageTraker();

        //j.run();
        //j.getShiphomes("stage16");
    }

    public void run() {
        Stage newStage = getNewStage();

        if (newStage == null) {
            return;
        }

        ArrayList<StageUpperstackShiphomes> shiphomeList = new ArrayList<>();
        final String stagePrefix;

        if (newStage.getStageName().chars().allMatch(Character::isDigit)) {
            stagePrefix = "stage";
        } else {
            stagePrefix = "";
        }

        EntityManager em = emf.createEntityManager();

        List<Platform> platformList = em.createNamedQuery("Platform.findAll", Platform.class).getResultList();
        List<Product> productList = em.createNamedQuery("Product.findAll", Product.class).getResultList();
        try {
            for (Platform platform : platformList) {
                for (Product product : productList) {
                    StageUpperstackShiphomes shiphomes = getShiphomes(stagePrefix + newStage.getStageName(), newStage, product, platform);
                    if (shiphomes != null) {
                        shiphomeList.add(shiphomes);
                    }
                }
            }

            persistStageAndShiphomes(newStage, shiphomeList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        System.out.println("shiphomeList:" + shiphomeList);
    }

    public void runJobs(Stage stage) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Testunit> testunitQuery = em.createNamedQuery("Testunit.findByRelease", Testunit.class);
        testunitQuery.setParameter("release", stage.getRelease().getId());

        List<RegressDetails> prepareRegressList = new ArrayList<>();
        List<Testunit> testUnitList = testunitQuery.getResultList();
        for (Testunit testUnit : testUnitList) {
            RegressDetails rDetails = new RegressDetails();
            rDetails.setStarttime(Calendar.getInstance().getTime());
            rDetails.setStage(stage);
            rDetails.setStatus(RegressStatus.notstarted);
            rDetails.setProduct(testUnit.getProduct());
            rDetails.setComponent(testUnit.getComponent());
            rDetails.setTestunit(testUnit);
            em.persist(rDetails);
            prepareRegressList.add(rDetails);
        }

        //sr.print("Regress List: " + prepareRegressList);
        RunJobs runJobs = new RunJobs(sr);
        try {
            runJobs.execute(stage.getRelease(), stage, prepareRegressList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void persistStageAndShiphomes(Stage newStage, ArrayList<StageUpperstackShiphomes> shiphomeList) {
        EntityManager em = emf.createEntityManager();
        try {
            synchronized (dummySync) {
                em.getTransaction().begin();
                newStage = em.merge(newStage);
                // em.getTransaction().commit();

                //  em.getTransaction().begin();
                for (StageUpperstackShiphomes shiphome : shiphomeList) {
                    shiphome.setStage(newStage);
                    em.persist(shiphome);
                }
                em.getTransaction().commit();
//                em.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    String dummySync = "";

    //this logic fails when stages move form stage to rc
    public Stage getNewStage() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Stage> list = em.createNamedQuery("Stage.getRecent", Stage.class).getResultList();
            Stage oldStage = list.get(0);

            System.out.println("Existing old Stage is:" + oldStage);

            String oldStageString = "";

            if (oldStage.getStageName().chars().allMatch(Character::isDigit)) {
                oldStageString = "stage" + oldStage.getStageName();
            } else {
                oldStageString = oldStage.getStageName();
            }

            String newStageString = "";
            Stage newStage = new Stage();
            //oldStageString = "rc2";
            if (oldStageString.startsWith("rc")) {
                newStageString = oldStageString.split("rc")[1] + 1;
                newStage.setStageName(newStageString);
            } else {
                newStageString = "stage" + (Integer.parseInt(oldStage.getStageName()) + 1);
                newStage.setStageName("" + (Integer.parseInt(oldStage.getStageName()) + 1));
            }

            //@TODO
            //newStage.setStageName("16");
            Set<String> keySet = intgRoot.getJSONObject(askernelLabel).keySet();
            System.out.println("Expected new stage is :" + newStageString);
            System.out.println("All stages in json:" + keySet);

            if (keySet.contains(newStageString)) {
                newStage.setComments(newStageString);
                newStage.setRelease(oldStage.getRelease());
                newStage.setDatecreated(Calendar.getInstance().getTime());
                System.out.println("New Stage Found" + newStage);
                return newStage;
            } else {
                System.out.println("No new Stage Found");
                return null;
            }

        } catch (Exception e) {
            return null;
            //print("Exception :" + e);
        } finally {
            em.close();
        }
        //print("Record saved", rdetails);

    }

    public boolean isStageExists(String newStage) {
        try {
            JSONObject stageAscoreWinObject = intgRoot.getJSONObject(ascoreWindowsLabel).getJSONObject(newStage);
            JSONObject stageAscoreLinObject = intgRoot.getJSONObject(ascoreLinuxlabel).getJSONObject(newStage);
            JSONObject stageAskernelObject = intgRoot.getJSONObject(askernelLabel).getJSONObject(newStage);

            if (stageAscoreLinObject != null && stageAscoreWinObject != null && stageAskernelObject != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public StageUpperstackShiphomes getShiphomes(String newStage, Stage stage, Product prod, Platform platform) {
        try {
            JSONObject stageObject = null;

            System.out.println("Try to find the product shiphome in json object. " + newStage + " " + prod + " " + platform);
            try {
                if (prod.getName().equals("askernel")) {
                    String temp = prod.getName() + "-manifest";
                    System.out.println("Askernal name:" + temp + intgRoot.has(temp));
                    if (intgRoot.has(temp) && intgRoot.getJSONObject(temp).has(newStage)) {
                        stageObject = intgRoot.getJSONObject(temp).getJSONObject(newStage);
                    }
                } else {
                    String temp = prod.getName() + "-" + platform.getName().toLowerCase().replaceAll("\\.", "_") + "-manifest";
                    System.out.println("Ascore name:" + temp + intgRoot.has(temp));
                    if (intgRoot.has(temp) && intgRoot.getJSONObject(temp).has(newStage)) {
                        stageObject = intgRoot.getJSONObject(temp).getJSONObject(newStage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (stageObject != null) {
                String platformName = platform.getName().toLowerCase();
                platformName = platformName.substring(0, platformName.indexOf("."));
                JSONObject shiphomObject = stageObject.getJSONObject("shiphome");

                StageUpperstackShiphomes shiphome = new StageUpperstackShiphomes();
                shiphome.setProduct(prod);
                shiphome.setPlatform(platform);
                shiphome.setShiphomeloc(shiphomObject.getString(platformName));
                shiphome.setStage(stage);

                System.out.println("ShiphomeLoc:" + shiphome);
                return shiphome;
            }

//            JSONObject stage = ascoreWindows.getJSONObject("stage1");
//
//            System.out.println("Object" + stage);
//
//            String askernelWinShiphome, askernelLinShiphome, ascoreWinShiphome, ascoreLinShiphome;
            //askernel-manifest - > stage1 -> shiphome -> linux/windows
            //manifest.integration.release - > ascore-windows_x64-manifest -> stage1 -> shiphome -> windows
            //manifest.integration.release - > ascore-linux_x64-manifest -> stage1 -> shiphome -> windows
//            "manifest.integration.release"
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
