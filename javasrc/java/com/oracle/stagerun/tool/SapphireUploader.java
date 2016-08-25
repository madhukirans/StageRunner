package com.oracle.stagerun.tool;

import com.oracle.stagerun.entity.RegressDetails;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.persistence.EntityManager;

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
    private String component;
    private RegressDetails regressDetails;
    private AbstractStgeRun sr;

    public SapphireUploader(RegressDetails regressDetails, AbstractStgeRun sr) {
        this.sr = sr;
        this.regressDetails = regressDetails;
        this.resultDir = regressDetails.getWorkLoc();
        this.stageId = regressDetails.getStage().getStageName();
        this.testUnit = regressDetails.getTestunit().getTestunitName();
        this.release = regressDetails.getStage().getRelease().getName();
        this.product = regressDetails.getTestunit().getProduct().getName();
        this.component = regressDetails.getTestunit().getComponent().getName();
        this.platform = regressDetails.getTestunit().getPlatform().getName();
        toptestFile = product + "_" + component;
        email = regressDetails.getTestunit().getEmails();

        //runKey = platform + "_" + product + "_" + testUnit + "_" + job.getFarmId();
        runId = platform + "_" + product + component + release + "_stage" + stageId + "_" + testUnit + "_" + regressDetails.getFarmrunId();

        runKey = runId;

        gtlfFileName = runId + ".xml";
    }

    public Boolean call() {
        sr.print("In sapphire upload thread", regressDetails);
        copyCoverageDump();
        generateGTLF();
        uploadGTLF();
        regressDetails.setGtlfFileLoc(sr.getStageDirectory(regressDetails) + "/" + gtlfFileName);
        regressDetails.setSapphireUploadStatus("uploaded");

        sr.merge(regressDetails);
        return true;
    }

    private void copyCoverageDump() {
        try {
            if (Files.exists(FileSystems.getDefault().getPath(resultDir, "coverage.dump"))) {
                sr.print("Copying coverage dump from " + resultDir + "/coverage.dimp" + " to "
                        + sr.getStageDirectory(regressDetails) + "/" + runId + ".coverage.dump", regressDetails);
                Files.copy(FileSystems.getDefault().getPath(resultDir, "coverage.dump"),
                        FileSystems.getDefault().getPath(sr.getStageDirectory(regressDetails), runId + ".coverage.dump"));
            }
        } catch (Exception e) {
            sr.print("Copy coverage dump exception " + e.getMessage(), regressDetails);
        }
    }

    public void generateGTLF() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Dgtlf.toptestfile=" + toptestFile);
        list.add("-Dgtlf.string=4");
        list.add("-Dgtlf.env.NativeIO=true");
        list.add("-Dgtlf.env.Primary_Config=LinuxOVM");
        list.add("-Dgtlf.branch=main");
        list.add("-Dgtlf.product=" + component.toUpperCase());
        list.add("-Dgtlf.release=" + (component + release).toUpperCase());
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
        list.add(sr.getStageDirectory(regressDetails));
        list.add("-filename");
        list.add(gtlfFileName);
        list.add("-verbose");
        sr.print("Generating gtlf. Gtlf command:" + list.toString(), regressDetails);
        try {
        org.testlogic.toolkit.gtlf.converters.file.Main.main(list.toArray(new String[list.size()]));
        } catch (Exception e) {
            sr.print("Exception : " + e, regressDetails);
        }
        sr.print("Completed generating gtlf for: ", regressDetails);
    }

    public void uploadGTLF() {
        sr.print("Upload begins.", regressDetails);
        try {
            System.setProperty("testmgr.validate", "false");
            System.setProperty("notify", email);
            weblogic.coconutx.WLCustomGTLFUploader.uploadGTLF(sr.getStageDirectory(regressDetails) + "/" + gtlfFileName);
            sr.print("Upload Successful.", regressDetails);
            sendMail();
        } catch (Exception e) {
            sr.print("Exception in upload gtlf: " + e, regressDetails);
        }
    }

    private void sendMail() {
        // Send mail
        Mail mail = new Mail(regressDetails);
        mail.sendMail();
    }
}
