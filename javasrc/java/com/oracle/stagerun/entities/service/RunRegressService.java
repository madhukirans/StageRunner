/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.PlatformEntity;
import com.oracle.stagerun.entities.ProductsEntity;
import com.oracle.stagerun.entities.RegressDetails;
import com.oracle.stagerun.entities.RegressStatus;
import com.oracle.stagerun.entities.ReleasesEntity;
import com.oracle.stagerun.entities.StageEntity;
import com.oracle.stagerun.entities.StageUpperstackShiphomesEntity;
import com.oracle.stagerun.entities.TestUnitsEntity;
import com.oracle.stagerun.tool.RunJobs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mseelam
 */
@Stateless
@Path("runregress")
public class RunRegressService {//extends AbstractFacade<RegressDetails> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public RunRegressService() {

    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public List<RegressDetails> findAll() {
        TypedQuery<StageEntity> query = em.createNamedQuery("StageEntity.getRecent", StageEntity.class);
        List<StageEntity> recentStage = query.getResultList();
        System.out.println(":" + recentStage);

        if (recentStage != null && recentStage.size() >= 1) {
            return getREsultsByStage(recentStage.get(0).getId());
        }

        return null;
    }

    @GET
    @Path("stage/{stageid}")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getREsultsByStage(@PathParam("stageid") Integer stageid) {
        TypedQuery<RegressDetails> query1 = em.createNamedQuery("RegressDetails.findByStage", RegressDetails.class);
        query1.setParameter("stageId", stageid);
        List<RegressDetails> regressList = query1.getResultList();
        System.out.println("regressListSize:" + regressList.size() + ": regressList:" + regressList);
        return regressList;
    }

    @POST
    //@Path("stage/{stageId}")///product/{productName}/testunit/{testunitId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void runRegress1(List<StageEntity> stage1) //@PathParam("stageId") Integer stageId) 
    //@PathParam("testunitId") Integer testunitId) 
    {
        StageEntity stage = stage1.get(0);

        System.out.println("shiphomeList:1" + stage);
        TypedQuery<StageUpperstackShiphomesEntity> query = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageId", StageUpperstackShiphomesEntity.class);
        query.setParameter("stageid", stage.getId());
        List<StageUpperstackShiphomesEntity> shiphomeList = query.getResultList();
        System.out.println("shiphomeList:" + shiphomeList);

        // StageEntity stage1 = em.find(StageEntity.class, stage.getId());
        ReleasesEntity release = stage.getReleaseEntity();
        List<RegressDetails> rList = new ArrayList<>();

        for (StageUpperstackShiphomesEntity shiphome : shiphomeList) {
            ProductsEntity product = shiphome.getProduct();
            PlatformEntity platform = shiphome.getPlatform();

            TypedQuery<TestUnitsEntity> query1 = em.createNamedQuery("TestUnitsEntity.findByReleaseAndProductsAndPlatform", TestUnitsEntity.class);
            query1.setParameter("release", release.getReleaseName());
            query1.setParameter("pname", product.getProductName());
            query1.setParameter("platform", platform.getName());

            List<TestUnitsEntity> testUnitList = query1.getResultList();

            for (TestUnitsEntity testUnit : testUnitList) {
                RegressDetails rDetails = new RegressDetails();
                rDetails.setStarttime(Calendar.getInstance());
                rDetails.setStageId(stage);
                rDetails.setStatus(RegressStatus.notstarted.name());
                rDetails.setProduct(testUnit.getProductName());
                rDetails.setTestunitId(testUnit);
                rList.add(rDetails);
                em.persist(rDetails);
            };
        };
        System.out.println("List: " + rList);
        // return rList;
        RunJobs jobs = new RunJobs(rList, shiphomeList);
    }

    //@Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
