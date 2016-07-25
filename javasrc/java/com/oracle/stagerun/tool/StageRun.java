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
    

    private EntityManager em;
    public StageRun() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StageRunnerDemonPU");
        em = emf.createEntityManager();
    }

    private void init() {        
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

    private static String rootFolder = "/tmp/sr/work";
    
    public static String getRootFolder() {
        return rootFolder;
    }
    
    public static String getStageDirectory(RegressDetails rDetails) {
        return rootFolder + "/" + rDetails.getStageId().getReleaseEntity().getReleaseName() + 
                "/" + rDetails.getStageId().getStageName() + "/" + rDetails.getProduct().getProductName() + "/" + rDetails.getTestunitId().getTestUnitName();
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
        
        regressList.forEach( regress -> { 
            Callable <Boolean> call = new FarmJobAnalyzer(regress, em);
            Future<Boolean> future = executor.submit(call);
            futureList.add(future);
        });
        
        for(Future<Boolean> fut : futureList){
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                StageRun.print("Thread ended" + fut.get());
            } catch (InterruptedException  | ExecutionException e) {
                StageRun.print("" + e);
            }
        }
        //shut down the executor service now
        executor.shutdown();
    }
    
    public static void main(String args[]) {
        StageRun sr = new StageRun();
        sr.runFarmJobAnalyzer();
    }
    
    public static void print(String message, RegressDetails rdetails){
        String str = "[" + rdetails.getStageId().getStageName() + "\t" + rdetails.getProduct().getProductName()
                    + "\t" + rdetails.getTestunitId().getTestUnitName() + rdetails.getFarmrunId()+"]";
        print (str + message);
    }
    
    public static void print(String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (StageRun.LOGGER != null) {
            StageRun.LOGGER.info(message);
        }

        System.out.println(time + " " + message);

    }
}
