/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.tool;

import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressDetailsGtlfFileHelper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

/**
 *
 * @author mseelam
 */
abstract public class AbstractStageRun {

    private static final Logger LOGGER = Logger.getLogger("stageruner_log");
    ;
    public static final String ROOT_FOLDER = "/scratch/sr/work";

    abstract public void merge(RegressDetails rdetails);

    abstract public void merge(RegressDetails rdetails, RegressDetailsGtlfFileHelper gtlfHelper);

    abstract public List<RegressDetails> getRunningRegressList();

    public AbstractStageRun() {
    }
   
    public Logger getLogger() {
        return LOGGER;
    }

    public void init(String filename) {
        System.out.println("In Init AbstractStgeRun");

        Handler consoleHandler = null;
        Handler fileHandler = null;
        try {
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler = new FileHandler(ROOT_FOLDER + "/" + filename);

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

    public String getStacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public void print(String message, RegressDetails rdetails) {
        log(message, rdetails, null);
    }

    public void print(String message, Exception e, RegressDetails rdetails) {
        log(message, rdetails, e);
    }

    public void print(String message, Exception e) {
        log(message, null, e);
    }

    public void print(String message) {
        log (message, null, null);
    }

    private void log(String message, RegressDetails rdetails, Exception e) {
        String exceptionAsString ="";
        if (e != null) {            
            exceptionAsString = getStacktrace(e);
        }

        String str = "";
        
        if (rdetails !=null){
            str = "[" + rdetails.getStage().getStageName() + "] [" + rdetails.getProduct().getName()
                + "] [" + rdetails.getTestunit().getTestunitName() + "] [" + rdetails.getFarmrunId() + "]";
        }
        
        
        message = str + " [" + message + "]\n" + exceptionAsString;
        
        if (rdetails != null)
            rdetails.getLogger().log(Level.ALL, message);
        else
            LOGGER.log(Level.ALL, message);
    }

    public String getRootFolder() {
        return ROOT_FOLDER;
    }

    public String getStageDirectory(RegressDetails rDetails) {
        String loc = rDetails.getLocation();
        File f = new File(loc);
        if (!f.exists()) {
            f.mkdirs();
        }
        return loc;
    }

    public void runFarmJobAnalyzer() {
        List<RegressDetails> regressList = getRunningRegressList();

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
