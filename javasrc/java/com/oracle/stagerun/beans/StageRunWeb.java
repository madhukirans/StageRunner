/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.beans;

import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressDetailsGtlfFileHelper;
import com.oracle.stagerun.entity.RegressStatus;
import com.oracle.stagerun.tool.AbstractStageRun;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mseelam
 */
@Singleton
@Startup
public class StageRunWeb extends AbstractStageRun {

    @PersistenceContext(unitName = "StageRunnerPU")
    EntityManager em;

    private static final int interval = 10;

    //@Override
    @PostConstruct
    public void init() {
        System.out.println("StageRunWeb:In Init AbstractStgeRun");
        super.init("stagerunweb.log");
    }

    //@Schedule(minute = "*/10", hour = "*")
    public void automaticTimeout() {
        System.out.println("Automatic timeout occured" + LocalDateTime.now());
        runFarmJobAnalyzer();
    }

    @Override
    public List<RegressDetails> getRunningRegressList() {
        TypedQuery query = em.createNamedQuery("RegressDetails.findByStatus", RegressDetails.class);
        query.setParameter("notstartedstatus", RegressStatus.notstarted);
        query.setParameter("runningstatus", RegressStatus.running);
        return query.getResultList();
    }

    @Override
    public void merge(RegressDetails rdetails) {
        print("In StageRunWeb merge");
        try {
            em.merge(rdetails);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        print("Record saved", rdetails);
    }

    @Override
    public void merge(RegressDetails rdetails, RegressDetailsGtlfFileHelper gtlfHelper) {
        print("In StageRunWeb merge");
        try {
            em.merge(rdetails);
            em.merge(gtlfHelper);
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        print("Record saved", rdetails);
    }
}
