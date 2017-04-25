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
import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressDetailsGtlfFileHelper;
import com.oracle.stagerun.entity.RegressStatus;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class StageRunDaemon extends AbstractStageRun {

    public static boolean upload = false;
    private static StageRunDaemon sr = null;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StageRunnerPUMain");

    private StageRunDaemon() {
        super.init("stagerundemon.log");
    }

    public static StageRunDaemon getInstance() {
        if (sr == null) {
            sr = new StageRunDaemon();
        }
        return sr;
    }

    private static final int interval = 10;

    public static void main(String args[]) {
        if (args.length == 0) {
            StageRunDaemon sr = StageRunDaemon.getInstance();
            if (sr.lockInstance()) {
                sr.runForEver();
            } else {
                System.out.println("Process already started");
            }
        } else {
            JSonStageTraker stageTraker = new JSonStageTraker(sr);
            stageTraker.run();
        }
    }

    public List<RegressDetails> getRunningRegressList() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createNamedQuery("RegressDetails.findByStatus", RegressDetails.class);
            query.setParameter("notstartedstatus", RegressStatus.notstarted);
            query.setParameter("runningstatus", RegressStatus.running);
            return query.getResultList();
        } catch (Exception e) {
            print("Exception :" + e);
        } finally {
            em.close();
        }
        return null;
    }

    private boolean lockInstance() {
        String lockFile = ROOT_FOLDER + "/sr.lck";
        try {
            System.out.println("Inlocking");
            final File file = new File(lockFile);
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            final FileLock fileLock = randomAccessFile.getChannel().tryLock();
            if (fileLock != null) {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        try {
                            fileLock.release();
                            randomAccessFile.close();
                            file.delete();
                        } catch (Exception e) {
                            System.out.println("Unable to remove lock file: " + lockFile + e.getMessage());
                        }
                    }
                });
                return true;
            }
        } catch (Exception e) {
            print("Exception :" + e);
            System.out.println("Process already started: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void runForEver() {
        LocalDateTime startTime = LocalDateTime.now();
        while (true) {
            try {
                runFarmJobAnalyzer();
                LocalDateTime endTime = LocalDateTime.now();
                Duration timeElapsed = Duration.between(startTime, endTime);
                print("TimeElapsed:" + timeElapsed.toMillis());

                if (timeElapsed.toMinutes() < interval) {
                    print("Sleeping for:" + ((interval * 60 * 1000) - timeElapsed.toMillis()));
                    Thread.sleep((interval * 60 * 1000) - timeElapsed.toMillis());
                }
                startTime = LocalDateTime.now();
            } catch (Exception e) {
                print("Exception runForEver:", e);
            }
        }
    }
    private String dummySync = "";

    @Override
    public void merge(RegressDetails rdetails) {
        synchronized (dummySync) {
            print("In StageRun merge");
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(rdetails);
                em.getTransaction().commit();
                //em.flush();
            } catch (Exception e) {
                print("Exception :", e);
            } finally {
                em.close();
            }
            print("Record saved", rdetails);

        }
    }

    @Override
    public void merge(RegressDetails rdetails, RegressDetailsGtlfFileHelper gtlfHelper) {
        synchronized (dummySync) {
            print("In StageRun merge");
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(rdetails);
                em.merge(gtlfHelper);
                em.getTransaction().commit();
                //  em.flush();
            } catch (Exception e) {
                print("Exception :", e);
            } finally {
                em.close();
            }
            print("Record saved", rdetails);
        }
    }
}
