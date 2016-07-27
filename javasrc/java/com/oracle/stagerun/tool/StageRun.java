package com.oracle.stagerun.tool;

/* $Header: otd_test/src/test/oracle/otd/common/tools/stagerun/StageRun.java /main/6 2016/06/21 02:54:56 mseelam Exp $ */

 /* Copyright (c) 2015, 2016, Oracle and/or its affiliates. 
All rights reserved.*/

 /*
 DESCRIPTION
 <short description of component this file declares/defines>

 PRIVATE CLASSES
 <list of private classes defined - with one-line descriptions>

 NOTES
 <other useful comments, qualifications, etc.>

 MODIFIED    (MM/DD/YY)
 mseelam     12/08/15 - Creation
 */
/**
 * @version $Header:
 * otd_test/src/test/oracle/otd/common/tools/stagerun/StageRun.java /main/6
 * 2016/06/21 02:54:56 mseelam Exp $
 * @author mseelam
 * @since release specific (what release of product did this appear in)
 */
import com.oracle.stagerun.entities.RegressDetails;
import com.oracle.stagerun.entities.RegressStatus;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//import weblogic.qa.frame.util.ant.SapphireLogFileUploaderTask;
public class StageRun {

    public static boolean upload = false;
    public static Logger logger;
    private static Logger LOGGER;

    private static EntityManager em;

    public StageRun() {

    }

    static {
        init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StageRunnerPU");
        em = emf.createEntityManager();
    }

    private static void init() {
        LOGGER = Logger.getLogger("stageruner.log");
        try {
            FileHandler fileHandler = new FileHandler("stagerun.log");
            SimpleFormatter simpleFormatter = new SimpleFormatter();

            LOGGER.addHandler(fileHandler);
            fileHandler.setFormatter(simpleFormatter);
            fileHandler.setLevel(Level.ALL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String rootFolder = "/tmp/sr/work";

    public static String getRootFolder() {
        return rootFolder;
    }

    public static String getStageDirectory(RegressDetails rDetails) {
        String loc = rootFolder + "/" + rDetails.getStageId().getReleaseEntity().getReleaseName()
                + "/" + rDetails.getStageId().getStageName() + "/" + rDetails.getProduct().getProductName() + "/" + rDetails.getTestunitId().getTestUnitName();
        File f = new File(loc);
        if (!f.exists()) {
            f.mkdirs();
        }

        return loc;
    }

    private List<RegressDetails> getRunningRegressList() {
        TypedQuery query = em.createNamedQuery("RegressDetails.findByStatus", RegressDetails.class);
        query.setParameter("notstartedstatus", RegressStatus.notstarted);
        query.setParameter("runningstatus", RegressStatus.running);
        return query.getResultList();
    }

    private void runFarmJobAnalyzer() {
        List<RegressDetails> regressList = getRunningRegressList();

        StageRun.print("List: " + regressList);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<Boolean>> futureList = new ArrayList<>();

        List<Callable<Boolean>> farmJobList = new ArrayList<>();

        regressList.forEach(regress -> {
            farmJobList.add(new FarmJobAnalyzer(regress));
        });

        try {
            futureList = executor.invokeAll(farmJobList);
        } catch (InterruptedException e) {

        }

        for (Future<Boolean> fut : futureList) {
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                StageRun.print("Thread ended" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                StageRun.print("" + e);
            }
        }
        //shut down the executor service now
        executor.shutdown();
    }

    private static final int interval = 10;

    public static void main(String args[]) {
        LocalDateTime startTime = LocalDateTime.now();
        StageRun sr = new StageRun();

        while (true) {
            sr.runFarmJobAnalyzer();
            LocalDateTime endTime = LocalDateTime.now();
            Duration timeElapsed = Duration.between(startTime, endTime);
            StageRun.print("TimeElapsed:" + timeElapsed.toMillis());

            if (timeElapsed.toMinutes() < interval) {
                try {
                    StageRun.print("Sleeping for:" + ((interval * 60 * 1000) - timeElapsed.toMillis()));
                    Thread.sleep((interval * 60 * 1000) - timeElapsed.toMillis());
                } catch (Exception e) {

                }
            }

            startTime = LocalDateTime.now();
        }
    }

    public static void print(String message, RegressDetails rdetails) {
        String str = "[" + rdetails.getStageId().getStageName() + " " + rdetails.getProduct().getProductName()
                + " " + rdetails.getTestunitId().getTestUnitName() + " " + rdetails.getFarmrunId() + "]";
        print(str + message);
    }

    public synchronized static void merge(RegressDetails rdetails) {
        try {
            em.getTransaction().begin();
            em.merge(rdetails);
            em.flush();
            em.getTransaction().commit();
           
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        print("Record saved", rdetails);
    }

    public static void print(String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (StageRun.LOGGER != null) {
            StageRun.LOGGER.fine(message);
        }

        System.out.println(time + " " + message);
    }
}
