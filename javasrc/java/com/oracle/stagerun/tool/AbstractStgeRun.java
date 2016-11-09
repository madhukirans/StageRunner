/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.tool;

import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressStatus;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author mseelam
 */
abstract public class AbstractStgeRun {

    private Logger LOGGER;
    private static final String rootFolder = "/scratch/sr/work";

    abstract public void merge(RegressDetails rdetails);
    //StageRun sr;
    public AbstractStgeRun() {
        //sr= StageRun.getInstance();
    }

    public void print() {
        System.out.println("Madhu In print");
    }

    public Logger getLogger() {
        return LOGGER;
    }

    
    public void init(String filename) {
        System.out.println("In Init AbstractStgeRun");
        LOGGER = Logger.getLogger("stageruner_log");
        Handler consoleHandler = null;
        Handler fileHandler = null;
        try {
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler = new FileHandler(rootFolder + "/" + filename);

            //Assigning handlers to LOGGER object
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.setLevel(Level.ALL);
            LOGGER.config("Configuration done.");
            //Console handler removed
            //LOGGER.removeHandler(consoleHandler);
           // LOGGER.log(Level.FINE, "Finer logged");
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
    }

    public void print(String message, RegressDetails rdetails) {
        String str = "[" + rdetails.getStage().getStageName() + " " + rdetails.getProduct().getName()
                + " " + rdetails.getTestunit().getTestunitName() + " " + rdetails.getFarmrunId() + "]";
        print(str + message);
    }

    public void print(String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (LOGGER != null) {
            LOGGER.log(Level.ALL, message);
        }

        //System.out.println(time + " " + message);
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public String getStageDirectory(RegressDetails rDetails) {
        String loc = rootFolder + "/" + rDetails.getStage().getRelease().getName()
                + "/" + rDetails.getStage().getStageName() + "/" + rDetails.getProduct().getName() + "/"
                + rDetails.getComponent().getName() + "/" + rDetails.getTestunit().getTestunitName();
        File f = new File(loc);
        if (!f.exists()) {
            f.mkdirs();
        }

        return loc;
    }

    private List<RegressDetails> getRunningRegressList(EntityManager em) {
        TypedQuery query = em.createNamedQuery("RegressDetails.findByStatus", RegressDetails.class);
        query.setParameter("notstartedstatus", RegressStatus.notstarted);
        query.setParameter("runningstatus", RegressStatus.running);
        return query.getResultList();
    }

    public void runFarmJobAnalyzer(EntityManager em) {
        List<RegressDetails> regressList = getRunningRegressList(em);

        print("List: " + regressList);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<Boolean>> futureList = new ArrayList<>();

        List<Callable<Boolean>> farmJobList = new ArrayList<>();

        for (RegressDetails regress : regressList) {
            farmJobList.add(new FarmJobAnalyzer(regress, this));
        }
//        regressList.forEach(regress -> {
//            farmJobList.add(new FarmJobAnalyzer(regress));
//        });

        try {
            futureList = executor.invokeAll(farmJobList);
        } catch (InterruptedException e) {
            print("Exception : runFarmJobAnalyzer:" + e);
        }

        for (Future<Boolean> fut : futureList) {
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                print("Thread ended" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                print("Exception in Future thread. runFarmJobAnalyzer:" + e);
            }
        }
        //shut down the executor service now
        executor.shutdown();
    }
}
