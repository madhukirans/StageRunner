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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StageRun extends AbstractStgeRun {

    public static boolean upload = false;
    private EntityManager em;
    private static StageRun sr = null;

    private StageRun() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StageRunnerPUMain");
        em = emf.createEntityManager();
        super.init("stagerundemon.log");
    }

    public static StageRun getInstance() {
        if (sr == null) {
            sr = new StageRun();
        }
        return sr;
    }

    private static final int interval = 10;

    public static void main(String args[]) {

        StageRun sr = StageRun.getInstance();
        sr.runForEver();
    }

    private void runForEver() {
        LocalDateTime startTime = LocalDateTime.now();
        while (true) {
            try {
                runFarmJobAnalyzer(em);
                LocalDateTime endTime = LocalDateTime.now();
                Duration timeElapsed = Duration.between(startTime, endTime);
                print("TimeElapsed:" + timeElapsed.toMillis());

                if (timeElapsed.toMinutes() < interval) {
                    print("Sleeping for:" + ((interval * 60 * 1000) - timeElapsed.toMillis()));
                    Thread.sleep((interval * 60 * 1000) - timeElapsed.toMillis());
                }
                startTime = LocalDateTime.now();
            } catch (Exception e) {
                print("Exception runForEver:" + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public synchronized void merge(RegressDetails rdetails) {
        print("In StageRun merge");
        try {
            em.getTransaction().begin();
            em.merge(rdetails);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception e) {
            print("Exception :" + e);
        }
        print("Record saved", rdetails);
    }
}
