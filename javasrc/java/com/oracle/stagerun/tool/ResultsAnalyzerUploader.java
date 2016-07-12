package com.oracle.stagerun.tool;



import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

//import org.testlogic.toolkit.gtlf.converters.testng.Main;

public class ResultsAnalyzerUploader implements Runnable
{

   private String resultDir;

   private String toptestFile;
   private String runKey;
   private String stageId;
   private String runId;
   private String gtlfFileName;
  // private String prefixStr;
   //private String platform;
   private FarmJob job;
   private String product="OTD";
   private String release;
   private String testUnit="";
   

   public ResultsAnalyzerUploader(String resultDir, String stageId, FarmJob job)
   {
//      this.resultDir = resultDir;
//      this.stageId = stageId;
//      //this.prefixStr = propertyString;
//      //this.platform = platform;
//      this.job = job;
//      toptestFile = "otd.tsc";
//      StageRun.print(job + " resultDir:" + resultDir);
//
//      runKey = this.job.getPlatform() + "_" + resultDir.substring(resultDir.lastIndexOf(".") + 1) + "_" + job.getFarmId();
//
//      runId = job.getPlatform() + "_OTD" + StageRun.versionProperties.getProperty("RELEASE") + "_stage" + stageId + "_" +
//            resultDir.substring(resultDir.lastIndexOf("/") + 1) + "_" + job.getFarmId();
//      
//      testUnit = job.getName();
//      
//      if (this.job.toString().contains("GQA"))
//      {
//         product = "NLS";
//      }
//      
//      release = product + StageRun.versionProperties.getProperty("RELEASE");
//      
//      gtlfFileName = runId + ".xml";        
   }
   
   public void run()
   {     
//      job.setCompleted(true);
//      copyCoverageDump();
//      generateGTLF();
//      
//      if (analyzeResults())
//      {
//         uploadGTLF();
//      }
//      
//      StageRun.sapphireProperties.setProperty(job + ".uploadStatus", "true");    
//      job.setUploaded(true);
//      StageRun.updatePropertyFile();
//      StageRun.print("Exiting Thread: " + job + job.getFarmId());
   }
   
   private void copyCoverageDump()
   {
//      try
//      {         
//         if (Files.exists(FileSystems.getDefault().getPath(resultDir, "coverage.dump")))
//         {
//            StageRun.print(job + " Copying coverage dump from " + resultDir+ "/coverage.dimp" + " to " + StageRun.StageDir +"/" + runId + ".coverage.dump");
//            Files.copy(FileSystems.getDefault().getPath(resultDir, "coverage.dump"), FileSystems.getDefault().getPath(StageRun.StageDir, runId + ".coverage.dump"));
//         }            
//      }
//      catch(Exception e)
//      {
//         StageRun.print(job + " Copying coverage dump excepiont " + e.getMessage() );
//      }
   }

   public void generateGTLF()
   {
     
//      ArrayList<String> list = new ArrayList<String>();
//      list.add("-Dgtlf.toptestfile=" + toptestFile);
//      list.add("-Dgtlf.string=4");
//      list.add("-Dgtlf.env.NativeIO=true");
//      list.add("-Dgtlf.env.Primary_Config=LinuxOVM");
//      list.add("-Dgtlf.branch=main");
//      list.add("-Dgtlf.product=" + product);
//      list.add("-Dgtlf.release=" + release);
//      list.add("-Dgtlf.load=" + stageId);
//      list.add("-Dgtlf.runid=" + runId);
//      //list.add("-Dgtlf.testruntype=" + "OTD_Tests");
//      // list.add("-Dgtlf.env.JVM_Version=" + "180_40");
//      // list.add("-Dgtlf.env.JVM_Name=" + "sun");
//      
//      list.add("-Dgtlf.env.RunKey=" + runKey);
//      list.add("-Dgtlf.env.OS=" + job.getPlatform());
//      list.add("-Dgtlf.analyzer=" + StageRun.user);
//      list.add("-testunit");
//      list.add(testUnit);
//      list.add("-srcdir");
//      list.add(resultDir);
//      list.add("-destdir");
//      list.add(StageRun.StageDir);
//      list.add("-filename");
//      list.add(gtlfFileName);
//      list.add("-verbose");
//      StageRun.print(job + " Generating gtlf for: " + job + ". Gtlf command:" + list.toString(), Color.CYAN);
//      StageRun.sapphireProperties.setProperty(job + ".gtlf", list.toString());
//      org.testlogic.toolkit.gtlf.converters.file.Main.main(list.toArray(new String[list.size()]));
//      StageRun.print(job + " Completed generating gtlf for: " + job + "\n", Color.GREEN);
   }

   public boolean analyzeResults()
   {
      File f = new File(resultDir);
      StageRun.print(job + " loc:" + resultDir, Color.CYAN);

      FilenameFilter sucFilter = new FilenameFilter()
         {
            public boolean accept(File dir, String name)
            {
               return name.toLowerCase().endsWith(".suc");
            }
         };

      FilenameFilter difFilter = new FilenameFilter()
         {
            public boolean accept(File dir, String name)
            {
               return name.toLowerCase().endsWith(".dif");
            }
         };

      File[] sucFiles = f.listFiles(sucFilter);
      File[] difFiles = f.listFiles(difFilter);
      int sucFileCount = sucFiles.length;
      int difFileCount = difFiles.length;

      StageRun.print(job + " sucfile count:" + sucFileCount, Color.PURPLE);
      StageRun.print(job + " diffile count:" + difFileCount, Color.PURPLE);

      int totalResults = sucFileCount + difFileCount;

      StageRun.sapphireProperties.setProperty(job + ".succount", sucFileCount + "");
      StageRun.sapphireProperties.setProperty(job + ".difcount", difFileCount + "");
      StageRun.sapphireProperties.setProperty(job + ".difs", Arrays.toString(difFiles));
      StageRun.updatePropertyFile();
      
      int threshold = 10;
      if (totalResults < 100)
         threshold = 5;
      
      if (totalResults / threshold < difFileCount)
      {
 //        job.setBadResults(true);
         
           
            return false;
      }
      else
      {
         StageRun.print(job + " Results are fine. Generating gtlf.", Color.GREEN);
      }

      return true;
   }

   public void uploadGTLF()
   {
      StageRun.print(job + " Upload begins.", Color.GREEN);
      try
      {
         System.setProperty("testmgr.validate", "false");
         System.setProperty("notify", StageRun.user + "@oracle.com");
        // weblogic.coconutx.WLCustomGTLFUploader.uploadGTLF(StageRun.StageDir + "/" + gtlfFileName);
         StageRun.print(job + " Upload Successful.", Color.GREEN);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      StageRun.print(job + " -------------------------------------------------------.", Color.CYAN);

   }

   public static void main(String a[])
   {
      String stage = a[0];
      String dir = a[1];
      String prefix = a[2];
      String platform = a[3];
      // ArrayList<String> list = new ArrayList<String>();
      // list.add("/net/slcnas551/export/farm_results/ASCORE_12.2.2.0.0_LINUX.X64_T16194202/29818241.Failover_RJRF_MT_Dual_Domain");
      // list.add("/net/slcnas551/export/farm_results/ASCORE_12.2.2.0.0_LINUX.X64_T16194202/29818243.Failover_DB");
      // list.add("/scratch/mseelam/view_storage/mseelam_otd1/oracle/work");

     // ResultsAnalyzerUploader uploader = new ResultsAnalyzerUploader(dir, stage, prefix, platform);
      //if (uploader.analyzeResults())
      //{
      //   uploader.generateGTLF();
      //   uploader.uploadGTLF();
      //}
   }
}
