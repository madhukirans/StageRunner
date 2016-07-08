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
 * @version $Header: otd_test/src/test/oracle/otd/common/tools/stagerun/StageRun.java /main/6 2016/06/21 02:54:56 mseelam Exp $
 * @author mseelam
 * @since release specific (what release of product did this appear in)
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//import weblogic.qa.frame.util.ant.SapphireLogFileUploaderTask;

public class StageRun
{

   public static String AUTO_HOME = "/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE";
   public static String LABEL_NAME;
   public static String otdShiphome;
   public static String winOtdShiphome;
   public static String wlsShiphome;
   public static String winWlsShiphome;
   public static boolean upload = false;
   public static Logger logger;
   public static String uploadPropertiesFile;
   public static String mailPropertiesFile;
   // public static final int numberOfCommands = 3;

   // private ArrayList<String> workDirList;
   public static final String T_WORK ="";
   public static final String TEST_HOME="";
   public static final String user ="";
   public static String StageDir;

   public static String stage;
   public static Properties versionProperties;
   public static String sapphirePropertiesLoc;
   public static SortedProperties sapphireProperties;
   // public static final String platform = "LINUX.X64";
   public static final String winPlatform = "WINDOWS.X64";
   private static boolean resultsUploadOnly = false;
   public static boolean uploadBadResults = false;

   private static Logger LOGGER;

   // private int farmIds[];
   static
   {
//      Map<String, String> env = System.getenv();
//      T_WORK = env.get("T_WORK");
//      TEST_HOME = env.get("TEST_HOME");
//      user = env.get("USER");
//
//      versionProperties = new Properties();
//      InputStream input = null;
//
//      try
//      {
//         input = new FileInputStream(TEST_HOME + "/config/version.properties");
//         versionProperties.load(input);
//         // StageRun.print("versionProperties:" + StageRun.versionProperties.getProperty("HA_TOPOSET_ID") + versionProperties);
//      }
//      catch (IOException ex)
//      {
//         ex.printStackTrace();
//      }
//      finally
//      {
//         if (input != null)
//         {
//            try
//            {
//               input.close();
//            }
//            catch (IOException e)
//            {
//               e.printStackTrace();
//            }
//         }
//      }
   }

   public StageRun()
   {
//      // Submit jobs if resultsUploadOnly = true. else do not submit farm jobs.
//
//      if (!resultsUploadOnly)
//      {
//         new RunJobs();
//      }
   }

   private static void init()
   {
      if (StageDir == null)
      {
         StageDir = T_WORK + "/stage" + stage;
         stage = sapphireProperties.getProperty("stage");
      }

      sapphirePropertiesLoc = StageDir + "/stage.properties";
      File dir = new File(StageDir);
      dir.mkdirs();

      LOGGER = Logger.getLogger(StageDir + "/stagerun.log");

      try
      {
         FileHandler fileHandler = new FileHandler(StageDir + "/" + LocalDateTime.now() + "stagerun.log");
         SimpleFormatter simpleFormatter = new SimpleFormatter();

         LOGGER.addHandler(fileHandler);
         fileHandler.setFormatter(simpleFormatter);
         fileHandler.setLevel(Level.ALL);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }

   private void uploadResults(FarmJob job)
   {
//      StageRun.print("In Uploading results");
//      // upload the results in sapphire
//      if (upload)
//      {
//         StageRun.print("In Uploading results1");
//         // Upload only when farm id exists.
//         if (!job.isFailed() && job.getFarmId() > 0)
//         {
//            StageRun.print("In Uploading results2");
//            String dir = sapphireProperties.getProperty(job.toString() + ".dir");
//
//            if (!job.isUploaded())
//            {
//               try
//               {
//                  ResultsAnalyzerUploader uploader = new ResultsAnalyzerUploader(dir, stage, job);
//                  StageRun.print("Starting sapphire upload thread " + job.toString());
//                  Thread t = new Thread(uploader);
//                  t.start();
//               }
//               catch (Exception e)
//               {
//                  StageRun.print("Uploading thread exceptin " + e);
//               }
//            }
//            else
//            {
//               sapphireProperties.setProperty(job.toString() + ".uploadStatus", "false");
//            }
//         }
//         else
//         {
//            sapphireProperties.setProperty(job.toString() + ".uploadStatus", "false");
//         }
//
//         updatePropertyFile();
//         StageRun.print("Upload complete for Job:" + job);
//         // }
//      }
   }

   private void sendMail()
   {
      // Send mail
      Mail mail = new Mail(StageRun.sapphireProperties);
      mail.sendMail();
   }

   /**
    * To update the properties file in work filder.
    * This properties file maintains the status of StageRun.
    */
   public static void updatePropertyFile()
   {
      FileOutputStream fos;
      try
      {
         fos = new FileOutputStream(StageRun.sapphirePropertiesLoc);
         sapphireProperties.store(fos, "Properties for Sapphire. Created on " + LocalDateTime.now());
         fos.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void farmRunAnalyzer()
   {
//      StageRun.print("\nAnalyzing the results.\n");
//      boolean completedStatus = false;
//      while (!completedStatus)
//      {
//         for (FarmJob job : FarmJob.values())
//         {
//            StageRun.print("farmRunAnalyzer: Job:" + job + "\tFarmId:" + job.getFarmId() + "\tEnabled:" + job.isEnabled() +
//                  "\tComplated:" + job.isCompleted() + "\tUploaded:" + job.isUploaded() + "\tfailed:" + job.isFailed());
//
//            if (!job.isEnabled())
//               continue;
//
//            if (job.isUploaded())
//            {
//               continue;
//            }
//
//            if (job.isCompleted())
//            {
//               continue;
//            }
//
//            FarmJobAnalyzer jobAnalyzer = new FarmJobAnalyzer(job);
//            if (jobAnalyzer.getStatus())
//            {
//               job.setResultDir(jobAnalyzer.getWorkLoc().get(0));
//               sapphireProperties.setProperty(job.toString() + ".dir", job.getResultDir());
//               updatePropertyFile();
//               StageRun.print("Directory is :" + job.getResultDir());
//               job.setCompleted(true);
//               uploadResults(job);
//            }
//         } // end for
//
//         // Check for all job completion
//         completedStatus = true;
//         for (FarmJob job : FarmJob.values())
//         {
//            StageRun.print("Checking for job completion:" + job + " FarmId:" + job.getFarmId() + " Enabled?:" + job.isEnabled() +
//                  " Failed?:" + job.isFailed() + " Completed?:" + job.isCompleted());
//
//            if (!job.isEnabled())
//               continue;
//            
//            if(job.isFailed())
//               continue;
//            
//            if (!job.isCompleted())
//            {
//               completedStatus = false;
//            }
//         }
//
//         StageRun.print("All Jobs completion:" + completedStatus);
//
//         // If jobs are not finished then sleep for 10 mins
//         if (!completedStatus)
//         {
//            StageRun.print("Sleep for 5 mins");
//            try
//            {
//               Thread.sleep(1000 * 60 * 5); // 5 mins
//            }
//            catch (Exception e)
//            {
//
//            }
//         }
//      }
//
//      // Verify weather all jobs are uploaded in sapphire.
//      boolean uploadStatus = false;
//      while (!uploadStatus)
//      {
//         for (FarmJob job : FarmJob.values())
//         {
//            StageRun.print("Checking for Upload Status:" + job + " FarmId:" + job.getFarmId() + " Enabled?:" + job.isEnabled() +
//                  " Failed?:" + job.isFailed() + " Completed?:" + job.isCompleted() + " Uploaded?:" + job.isUploaded());
//            
//            if (!job.isEnabled() || job.isUploaded() || job.isFailed() || job.hasBadResults())
//            {
//               StageRun.print("Setting to true", Color.RED);
//               uploadStatus = true;
//            }
//            else
//            {
//               StageRun.print("Setting to false", Color.RED);
//               uploadStatus = false;
//
//               try
//               {
//                  StageRun.print("Sleep for 30 sec. In Upload staus for job:" + job.getFarmId() + " : " + job.getName(), Color.BLUE);
//                  Thread.sleep(1000 * 30);// 30 seconds
//               }
//               catch (Exception e)
//               {
//                  e.printStackTrace();
//               }
//               break;
//            }
//         }
//      }
   }

   private static void usage()
   {

//      StageRun.print("Usage : ", Color.CYAN);
//      StageRun.print("java StageRun -uploadFile <propfile>", Color.BLUE);
//      StageRun.print("\t- Above command is to analyze and upload the results with existing properties file.", Color.BLUE);
//      StageRun.print("java StageRun -mailPropFile <propfile>", Color.BLUE);
//      StageRun.print("\t- Above command is to analyze and upload the results with existing properties file.", Color.BLUE);
//      StageRun.print("java StageRun -stage <stage> -otd <OTDShiphome> -wls <WLSShiphome> " +
//            "-winOtd <windows otd shiphome> -winWls <windows wls shiphome> [-jobs " + Arrays.toString(FarmJob.values()) + " ] [[-auto_home <AUTO_HOME>] [-l <OTDQA_LABEL>] "
//            + "[-upload false/true default false]], Color.BLUE", Color.BLUE);
//      StageRun.print("\t- Ex1 : java StageRun -stage 10 -otd /ade_autofs/gd12_fmw/ASCORE_12.2.2.0.0_LINUX.X64.rdd/LATEST/ascore/shiphome/otd_linux64.bin "
//            + " -wls /ade_autofs/gd17_fmw/ASKERNEL_12.2.2.0.0_GENERIC.rdd/LATEST/askernel/shiphome/wls_jrf_generic.jar", Color.BLUE);
//      StageRun.print("\t- Ex2 : java StageRun -stage 10 -otd /ade_autofs/gd12_fmw/ASCORE_12.2.2.0.0_LINUX.X64.rdd/LATEST/ascore/shiphome/otd_linux64.bin  "
//            + "-wls /ade_autofs/gd17_fmw/ASKERNEL_12.2.2.0.0_GENERIC.rdd/LATEST/askernel/shiphome/wls_jrf_generic.jar "
//            + "-auto_home /ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE", Color.BLUE);
//      StageRun.print("\t- Ex3 : java StageRun -stage 10 -otd /ade_autofs/gd12_fmw/ASCORE_12.2.2.0.0_LINUX.X64.rdd/LATEST/ascore/shiphome/otd_linux64.bin  "
//            + "-wls /ade_autofs/gd17_fmw/ASKERNEL_12.2.2.0.0_GENERIC.rdd/LATEST/askernel/shiphome/wls_jrf_generic.jar "
//            + "-auto_home /ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE -l OTDQA_MAIN_GENERIC_151129.0800", Color.BLUE);
//      StageRun.print("\t User need to run inside the view if -l is not provided", Color.BLUE);
//      System.exit(-1);
   }

   public static void validateLoc(String a)
   {
      File f = new File(a);
      if (!f.exists())
      {
         StageRun.print("Location doesnot exists:[" + a + "]", Color.RED);
         usage();
      }
   }

   /**
    * <p>
    * VIEW_NAME : mseelam_otd1 HOST_NAME : slc09iyd.us.oracle.com VIEW_LOCATION : /scratch/mseelam/view_storage/mseelam_otd1 VIEW_LABEL : OTDQA_MAIN_GENERIC_151129.0800 VIEW_TXN_NAME :
    * mseelam_some_temp_trans VIEW_TXN_LOC : /net/slcnas532.us.oracle.com/export/ade_slc_txn/mseelam/ mseelam_some_temp_trans VIEW_TXN_MERGE_STATE : NOT MERGING VIEW_REFRESH_STATE : OK
    * </p>
    * 
    * @return
    */

   public static String getCurrentLabel()
   {
      try
      {
         List<String> listArg = new ArrayList<String>();
         listArg.add("ade");
         listArg.add("pwv");

         ProcessBuilder processBuilder = new ProcessBuilder(listArg);
         Process process = processBuilder.start();

         // String mesgOutput = readContent (process.getInputStream());
         // String mesgError = readContent (process.getErrorStream());

         process.waitFor(30, TimeUnit.MINUTES);

         int exitStatus = process.exitValue();
         if (exitStatus == 0)
         {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                  process.getInputStream()));
            String currLine = null;
            while ((currLine = in.readLine()) != null
                  && currLine.contains("VIEW_LABEL"))
            {
               StageRun.print(currLine);
               String str[] = currLine.split(":");
               return str[1].trim();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      return null;
   }

   public static SortedProperties loadProperties(String loc)
   {
      SortedProperties properties = null;
      if (new File(loc).exists())
      {
         properties = new SortedProperties();
         try
         {
            properties.load(new FileInputStream(loc));
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
      else
      {
         System.out.println("Properties file does not exists:" + loc);
      }
      return properties;
   }

   private static void initFarmJobs()
   {
//      for (FarmJob job : FarmJob.values())
//      {
//         if (sapphireProperties.getProperty(job.toString() + ".farmid") != null)
//         {
//            StageRun.print("Job:" + job.toString() + " FarmId:" + sapphireProperties.getProperty(job.toString() + ".farmid"), Color.BLUE);
//            if (sapphireProperties.getProperty(job.toString() + ".farmid") != null)
//            {
//               job.setFarmId(Integer.parseInt(sapphireProperties.getProperty(job.toString() + ".farmid")));
//               
//               StageRun.print(sapphireProperties.getProperty(job.toString() + ".uploadStatus"), Color.RED);
//               
//               boolean uploaded = Boolean.parseBoolean(sapphireProperties.getProperty(job.toString() + ".uploadStatus"));
//               if (uploaded == true)
//                  job.setUploaded(true);
//               else
//                  job.setUploaded(false);
//            }
//         }
//         else
//         {
//            job.setEnabled(false);
//         }
//      }
   }

   public static void main(String args[])
   {
      // StageRun.print("Args : " + args.length, Color.RED);
      processArguments(args);

      if (uploadPropertiesFile != null)
      {
         sapphirePropertiesLoc = uploadPropertiesFile;
         sapphireProperties = loadProperties(uploadPropertiesFile);
         File file = new File(uploadPropertiesFile);
         StageDir = file.getParent();

         init();

         StageRun.print("sapphireProperties:" + sapphireProperties);
         resultsUploadOnly = true;
         upload = true;
         stage = sapphireProperties.getProperty("stage");
         // uploadBadResults = true;

         StageRun stagerun = new StageRun();
         initFarmJobs();
         stagerun.farmRunAnalyzer();
         StageRun.print("Sending mail..");
         stagerun.sendMail();
         return;
      }

      if (mailPropertiesFile != null)
      {
         sapphirePropertiesLoc = mailPropertiesFile;
         sapphireProperties = loadProperties(mailPropertiesFile);
         File file = new File(mailPropertiesFile);
         StageDir = file.getParent();
         init();

         resultsUploadOnly = false;
         stage = sapphireProperties.getProperty("stage");
         initFarmJobs();
         Mail mail = new Mail(sapphireProperties);
         mail.sendMail();
         return;
      }

      if (args.length < 3)
      {
         usage();
      }

      // validateLoc(otdShiphome);
      // validateLoc(wlsShiphome);
      StageDir = T_WORK + "/stage" + stage;
      init();
      StageRun.print("Stage :" + stage);
      StageRun.print("otdShiphome :" + otdShiphome);
      StageRun.print("wlsShiphome :" + wlsShiphome);
      StageRun.print("AUTO_HOME :" + AUTO_HOME);
      StageRun.print("LABEL_NAME :" + LABEL_NAME);
      StageRun.print("Upload :" + upload);

      sapphireProperties = new SortedProperties();
      sapphireProperties.setProperty("stage", stage);
      sapphireProperties.setProperty("release", versionProperties.getProperty("RELEASE"));

      StageRun stagerun = new StageRun();
      stagerun.farmRunAnalyzer();
      stagerun.sendMail();
   }

   public static void processArguments(String args[])
   {
//      for (int i = 0; i < args.length; i++)
//      {
//         if (args[i].equalsIgnoreCase("-help"))
//         {
//            usage();
//         }
//         else if (args[i].equalsIgnoreCase("-stage"))
//         {
//            stage = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-otd"))
//         {
//            otdShiphome = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-wls"))
//         {
//            wlsShiphome = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-winOtd"))
//         {
//            winOtdShiphome = args[++i];
//            winOtdShiphome = winOtdShiphome.replace('\\', '/');
//         }
//         else if (args[i].equalsIgnoreCase("-winWls"))
//         {
//            winWlsShiphome = args[++i];
//            winWlsShiphome = winWlsShiphome.replace('\\', '/');
//         }
//         else if (args[i].equalsIgnoreCase("-l") || args[i].equalsIgnoreCase("-label"))
//         {
//            LABEL_NAME = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-auto_home"))
//         {
//            AUTO_HOME = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-upload"))
//         {
//            upload = Boolean.parseBoolean(args[++i]);
//         }
//         else if (args[i].equalsIgnoreCase("-uploadFile"))
//         {
//            uploadPropertiesFile = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-mailPropFile"))
//         {
//            mailPropertiesFile = args[++i];
//         }
//         else if (args[i].equalsIgnoreCase("-jobs"))
//         {
//            String jobs[] = args[++i].trim().split(",");
//
//            // First disble all jobs
//            for (FarmJob fjob : FarmJob.values())
//               fjob.setEnabled(false);
//
//            // Enable only required jobs.
//            for (String job : jobs)
//            {
//               try
//               {
//                  FarmJob fjob = FarmJob.valueOf(job);
//                  fjob.setEnabled(true);
//               }
//               catch (IllegalArgumentException e)
//               {
//                  StageRun.print("Wrong Job type:" + job + "\nPossible values are :" + Arrays.toString(FarmJob.values()));
//                  e.printStackTrace();
//                  System.exit(-1);
//               }
//
//               StageRun.print(job);
//            }
//         }
//         else
//         {
//            usage();
//         }
//      }

   }

   public static void print(String message, Color... option)
   {
      String time = LocalDateTime.now().toString();

      if (StageRun.LOGGER != null)
        StageRun.LOGGER.info(message);

         if (option.length == 0)
         {
            System.out.println(time + " " + message);
         }
         else
         {
            System.out.print(option[0] + time + " " + message);
            System.out.println("\u001B[0m");
         }
   }
}

enum Color
{
   BLACK("\u001B[30m"),
   RED("\u001B[31m"),
   GREEN("\u001B[32m"),
   YELLOW("\u001B[33m"),
   BLUE("\u001B[34m"),
   PURPLE("\u001B[35m"),
   CYAN("\u001B[36m");

   private final String colorCode;

   private Color(String code)
   {
      colorCode = code;
   }

   public String toString()
   {
      return this.colorCode;
   }
};

class SortedProperties extends Properties
{
   public Enumeration keys()
   {
      Enumeration<Object> keysEnum = super.keys();
      Vector keyList = new Vector();
      while (keysEnum.hasMoreElements())
      {
         keyList.add((String) keysEnum.nextElement());
      }
      Collections.sort(keyList);
      return keyList.elements();
   }
}
