package com.oracle.stagerun.tool;

import com.oracle.stagerun.entities.RegressDetails;
import com.oracle.stagerun.entities.RegressStatus;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

//import org.testlogic.toolkit.gtlf.converters.testng.Main;
public class SapphireUploader implements Callable<Boolean> {

    private String resultDir;

    private String toptestFile;
    private String runKey;
    private String stageId;
    private String runId;
    private String gtlfFileName;
    private String release;
    private String platform;
    private String product;
    private String testUnit;
    private String email;
    private RegressDetails regressDetails;

    public SapphireUploader(RegressDetails regressDetails) {
        this.regressDetails = regressDetails;
        this.resultDir = regressDetails.getWorkLoc();
        this.stageId = regressDetails.getStageId().getStageName();
        this.testUnit = regressDetails.getTestunitId().getTestUnitName();
        this.release = regressDetails.getStageId().getReleaseEntity().getReleaseName();
        product = regressDetails.getTestunitId().getProductName().getProductName();
        platform = regressDetails.getTestunitId().getPlatform().getName();
        //toptestFile = "otd.tsc";
        email = regressDetails.getTestunitId().getEmails();

        //runKey = platform + "_" + product + "_" + testUnit + "_" + job.getFarmId();
        runId = platform + "_" + product + release + "_stage" + stageId + "_" + testUnit + "_" + regressDetails.getFarmrunId();

        runKey = runId;

        gtlfFileName = StageRun.getStageDirectory(regressDetails) + "/" + runId + ".xml";
    }

    public Boolean call() {      
      copyCoverageDump();
      generateGTLF();
      uploadGTLF();
      regressDetails.setSapphireUploadStatus("uploaded");
      return true;
    }

    private void copyCoverageDump() {
        try {
            if (Files.exists(FileSystems.getDefault().getPath(resultDir, "coverage.dump"))) {
                StageRun.print("Copying coverage dump from " + resultDir + "/coverage.dimp" + " to " + StageRun.getStageDirectory(regressDetails) + "/" + runId + ".coverage.dump");
                Files.copy(FileSystems.getDefault().getPath(resultDir, "coverage.dump"),
                        FileSystems.getDefault().getPath(StageRun.getStageDirectory(regressDetails), runId + ".coverage.dump"));
            }
        } catch (Exception e) {
            StageRun.print("Copy coverage dump exception " + e.getMessage());
        }
    }

    public void generateGTLF() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Dgtlf.toptestfile=" + toptestFile);
        list.add("-Dgtlf.string=4");
        list.add("-Dgtlf.env.NativeIO=true");
        list.add("-Dgtlf.env.Primary_Config=LinuxOVM");
        list.add("-Dgtlf.branch=main");
        list.add("-Dgtlf.product=" + product);
        list.add("-Dgtlf.release=" + release);
        list.add("-Dgtlf.load=" + stageId);
        list.add("-Dgtlf.runid=" + runId);
        //list.add("-Dgtlf.testruntype=" + "OTD_Tests");
        //list.add("-Dgtlf.env.JVM_Version=" + "180_40");
        //list.add("-Dgtlf.env.JVM_Name=" + "sun");

        list.add("-Dgtlf.env.RunKey=" + runKey);
        list.add("-Dgtlf.env.OS=" + platform);
        list.add("-Dgtlf.analyzer=" + "mseelam");
        list.add("-testunit");
        list.add(testUnit);
        list.add("-srcdir");
        list.add(resultDir);
        list.add("-destdir");
        list.add(StageRun.getStageDirectory(regressDetails));
        list.add("-filename");
        list.add(gtlfFileName);
        list.add("-verbose");
        StageRun.print("Generating gtlf. Gtlf command:" + list.toString());
        org.testlogic.toolkit.gtlf.converters.file.Main.main(list.toArray(new String[list.size()]));
        StageRun.print("Completed generating gtlf for: ");
    }

    public void uploadGTLF() {
        StageRun.print("Upload begins.");
        try {
            System.setProperty("testmgr.validate", "false");
            System.setProperty("notify", email);
            weblogic.coconutx.WLCustomGTLFUploader.uploadGTLF(gtlfFileName);
            StageRun.print("Upload Successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void sendMail() {
        // Send mail
        Mail mail = new Mail(regressDetails);
        mail.sendMail();
    }
}
