package com.oracle.stagerun.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FarmJob
{  
//   private String name;
//   private String platform;
//   private String topoid;
//   private String farmCommand;
//   private String otdShiphome;
//   private String wlsShiphome;
//   private String options[];
//
//   private boolean enabled = true;
//   private boolean completed = false;
//   private boolean isUploaded = false;
//   private boolean failed = false;
//   private boolean badresults = false;
//   
//   public boolean isUploaded()
//   {
//      return isUploaded;
//   }
//
//   public void setUploaded(boolean isUploaded)
//   {
//      this.isUploaded = isUploaded;
//   }
//
//   private int farmRunId;
//   private String resultDir;
//   
//   FarmJob(String name, String platform, String toposetId, String toposetMemberId, String otdShiphome, String wlsShiphome, String... options)
//   {
//      this.name = name;
//      this.platform = platform;
//      this.toposetId = toposetId;
//      this.toposetMemberId = toposetMemberId;
//      this.otdShiphome = otdShiphome;
//      this.wlsShiphome = wlsShiphome;
//      this.options = options;
//   }
//   
//   public boolean isCompleted()
//   {
//      return completed;
//   }
//   
//   public void setCompleted(boolean status)
//   {
//      completed = status;
//   }
//   
//   public boolean isEnabled()
//   {
//      return enabled;
//   }
//   
//   public boolean hasBadResults()
//   {
//      return badresults;
//   }
//   
//   public void setBadResults(boolean status)
//   {
//      badresults = status;
//   }
//   
//   public void setEnabled(boolean status)
//   {
//      enabled = status;
//   }
//   
//   public boolean isFailed()
//   {
//      return failed;
//   }
//   
//   public void setFailed(boolean status)
//   {
//      failed = status;
//   }
//   
//   public String getName()
//   {
//      return this.name; 
//   }
//   
//   public String getPlatform()
//   {
//      return this.platform; 
//   }
//   
//   public void setFarmId(int farmRunId)
//   {
//      this.farmRunId = farmRunId; 
//   }
//   
//   public int getFarmId()
//   {
//      return this.farmRunId; 
//   }
//   
//   public void setResultDir(String resultDir)
//   {
//      this.resultDir = resultDir; 
//   }
//   
//   public String getResultDir()
//   {
//      return this.resultDir; 
//   }
//   
//   public List<String> getCommand()
//   {
//      List<String> listArg = new ArrayList<String>();
//      listArg.add(StageRun.AUTO_HOME + "/bin/jobReqAgent");
//      listArg.add("-toposetid");
//      listArg.add(toposetId);
//      listArg.add("-toposetMemberID");
//      listArg.add(toposetMemberId);
//      listArg.add("-p");
//      listArg.add(platform);
//      listArg.add("-r");
//      listArg.add(StageRun.versionProperties.getProperty("RELEASE"));
//      listArg.add("-s");
//      listArg.add(otdShiphome);
//      listArg.add("-l");
//      listArg.add(StageRun.LABEL_NAME);
//      listArg.add("-preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1");
//      listArg.add("-preExportedFile=" + StageRun.T_WORK + "/download_export.txt," + 
//            StageRun.T_WORK + "/download_export.txt," + StageRun.T_WORK + "/download_export.txt");      
//      listArg.add("ENV:MY_JRF_SHIPHOME_LOCATION=" + wlsShiphome);
//      
//      if (platform.equals("LINUX.X64"))
//         listArg.add("JAVA_OPTIONS=" + "-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true");    
//      else
//         listArg.add("JAVA_OPTIONS=" + "-Dcom.oracle.lifecycle.oob=true");
//      
//      if (!StageRun.AUTO_HOME.startsWith("/usr/local/packages"))
//         listArg.add("-AUTO_HOME=" + StageRun.AUTO_HOME);
//        
//	  if (options != null)
//	  {
//		  for (String str : options)
//		  {
//				listArg.add(str);
//		  }
//	  }
//
//      return listArg;
//   }
//   
//   public String toCommandString()
//   {  
//      List <String > listArgs = getCommand();
//      String command ="";
//      for (String str:listArgs)
//         command += str + " ";         
//      return command;
//   }     
   
   /*@Override
   public String toString()
   {          
      return platform + "_" + name;
   }*/
}
