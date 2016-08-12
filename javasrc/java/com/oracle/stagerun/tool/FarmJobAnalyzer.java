package com.oracle.stagerun.tool;



import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressStatus;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FarmJobAnalyzer implements Callable<Boolean> {

    private String rootWorkLoc;
    private boolean completed = false;
    // private int farmJobId;
    
    private RegressDetails regressDetail;
    
    public FarmJobAnalyzer(RegressDetails regressDetail) {
        StageRun.print("In FarmJobAnalyzer.", regressDetail);
        this.regressDetail = regressDetail;    
    }

//    public List<String> getWorkLoc() {
//        List<String> dirs = new ArrayList<String>();
//        File f = new File(rootWorkLoc);
//        File files[] = f.listFiles();
//        for (File file : files) {
//            if (file.isDirectory() && !file.getPath().endsWith("build")) {
//                dirs.add(file.getAbsolutePath());
//            }
//        }
//        return dirs;
//    }
    public Boolean call() {
        try {
            if (regressDetail.getFarmrunId() == null)
                return false;
            
            List<String> listArg = new ArrayList<String>();
            listArg.add("farm");
            listArg.add("showjobs");
            listArg.add("-d");
            listArg.add("-j");
            listArg.add(regressDetail.getFarmrunId().toString());
            
            StageRun.print("command:" + listArg, regressDetail);
            ProcessBuilder processBuilder = new ProcessBuilder(listArg);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor(5, TimeUnit.MINUTES);

            /*
          * Farm command output
         $ farm showjobs -j 16799530 -d
         Farm Command Line - 2.9
         ======== ============ ===== ============================ ============ =========
         16799530 MAR 06 18:20 DTE S                 dte_16799530 finished             1
         ======== ============ ===== ============================ ============ =========
          label             : OTDQA_MAIN_GENERIC_T16799530
          job owner         : mseelam
          view name         : NO_ADE_VIEW_NAME
          description       : DTE Submission LINUX.X64
          txn name          : dte_16799530
          zone / exec zone  : ADC / ADC
          base label        : OTDQA_MAIN_GENERIC_160301.0217.S
          compile           : no
          shiphome          : no
          cross platform    : no
          database create   : no
          DO location       :
          DB location       :
          Short regress     : no
          Start time        : MAR-06-2016 18:20:43 UTC
          Finish time       : MAR-06-2016 19:15:22 UTC
          Metadata location : /net/adcnas469/export/farm_metadata/OTDQA_MAIN_GENERIC_T16799530
          Results location  : /net/adcnas470/export/farm_txn_results/OTDQA_MAIN_GENERIC_T16799530
          Scheduler         : adc00zmb
          LRGs              : total=1, started=1, done=1
          31315213.Failover_JRF : fin : 32 sucs,  0 difs in  .8 hrs [03/06 18:23 - 03/06 19:12 UTC]
             */
            int exitStatus = process.exitValue();
            if (exitStatus == 0) {
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String currLine = null;
                int i = 0;
                while ((currLine = in.readLine()) != null) {
                    StageRun.print("Farm Job Line:" + currLine, regressDetail);
                    i++;

                    if (i == 3 && currLine.contains("finished")) {
                        
                        regressDetail.setStatus(RegressStatus.completed);
                        while ((currLine = in.readLine()) != null) {
                            if (currLine.contains("Results location")) {
                                rootWorkLoc = currLine.split(":")[1].trim();
                                regressDetail.setWorkLoc(rootWorkLoc);
                                regressDetail.setEndtime(Calendar.getInstance().getTime());
                                StageRun.merge(regressDetail);
                                
                                ExecutorService executor = Executors.newCachedThreadPool();
                                Future<Boolean> fut = executor.submit(new SapphireUploader(regressDetail));
                                return fut.get(10, TimeUnit.MINUTES);
                                //return true;
                            }
                        }
                        break;
                    } else if (i == 3 && (currLine.contains("failed") || currLine.contains("aborted"))) {
                        if (currLine.contains("failed")) {
                            regressDetail.setStatus(RegressStatus.failed);
                        } else if (currLine.contains("aborted")) {
                            regressDetail.setStatus(RegressStatus.aborted);
                        }
                        
                        StageRun.merge(regressDetail);
                        return false;
                    }
                }

                /*if (i==2)
            {
               StageRun.print("Jobs output has only tow lines. There is a peobelem in farm submit command or label/shiphome existance. " + job.toString() + " :" + currLine, Color.RED);
               job.setFailed(true);
               job.setCompleted(true);
            }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
