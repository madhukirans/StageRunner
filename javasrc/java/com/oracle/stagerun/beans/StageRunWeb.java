/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.beans;

import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.tool.AbstractStgeRun;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mseelam
 */
@Singleton
@Startup
public class StageRunWeb extends AbstractStgeRun {

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
        System.out.println("Automatic timeout occured" +  LocalDateTime.now());
        runFarmJobAnalyzer(em);
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
}
