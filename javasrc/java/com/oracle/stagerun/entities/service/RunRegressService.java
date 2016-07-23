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
import com.oracle.stagerun.entities.service.helper.PostRegressHelper;
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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

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
    public List<RegressDetails> findAll(@Context HttpServletRequest requestContext,@Context SecurityContext context) {
        String yourIP = requestContext.getRemoteAddr();
        
        TypedQuery<StageEntity> query = em.createNamedQuery("StageEntity.getRecent", StageEntity.class);        
        List<StageEntity> recentStage = query.getResultList();
        System.out.println(":" + recentStage + "Remote IP:" + yourIP);
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
    public void runRegress1(PostRegressHelper post)//, List<ProductsEntityFacadeREST> products1, List<TestUnitsEntity> testunits1) //@PathParam("stageId") Integer stageId) 
    //@PathParam("testunitId") Integer testunitId) 
    {
        System.out.println("getStage:1" + post.getStage());
        System.out.println("getProduct:1" + post.getProduct());
        System.out.println("getTestunit:1" + post.getTestunit());

        StageEntity stage = post.getStage();
        ProductsEntity product = post.getProduct();
        TestUnitsEntity testunit = post.getTestunit();
        
        List<StageUpperstackShiphomesEntity> allShiphomeList = null;
        List<StageUpperstackShiphomesEntity> shiphomeList = null;

        //Get shiphomes based on postdata
        TypedQuery<StageUpperstackShiphomesEntity> shiphomeQery;
        if (product.getProductName() == null) {
            shiphomeQery = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageId", StageUpperstackShiphomesEntity.class);
            shiphomeQery.setParameter("stageid", stage.getId());
            shiphomeList = shiphomeQery.getResultList();
            allShiphomeList=shiphomeList;
        } else {
            shiphomeQery = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageAndProduct", StageUpperstackShiphomesEntity.class);
            shiphomeQery.setParameter("productname", product.getProductName());
            shiphomeQery.setParameter("stageid", stage.getId());
            shiphomeList = shiphomeQery.getResultList();
            
            TypedQuery<StageUpperstackShiphomesEntity> allShiphomeQery = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageId", StageUpperstackShiphomesEntity.class);
            allShiphomeQery.setParameter("stageid", stage.getId());
            allShiphomeList = allShiphomeQery.getResultList();
        }

        System.out.println("shiphomeList:" + shiphomeList);

        // StageEntity stage1 = em.find(StageEntity.class, stage.getId());
        ReleasesEntity release = stage.getReleaseEntity();
        List<RegressDetails> regressList = new ArrayList<>();

        for (StageUpperstackShiphomesEntity shiphome : shiphomeList) {
           
            //Get all testunits based on release, product and platform if user not selected form UI
            ProductsEntity tempProduct = shiphome.getProduct();
            PlatformEntity tempPlatform = shiphome.getPlatform();            
           
            List<TestUnitsEntity> testUnitList = new ArrayList<>();
            if (testunit.getId() == null) {
                TypedQuery<TestUnitsEntity> testunitQuery = em.createNamedQuery("TestUnitsEntity.findByReleaseAndProductsAndPlatform", TestUnitsEntity.class);
                testunitQuery.setParameter("release", release.getReleaseName());
                testunitQuery.setParameter("pname", tempProduct.getProductName());
                testunitQuery.setParameter("platform", tempPlatform.getName());
                testUnitList = testunitQuery.getResultList();
            } else {
                testUnitList.add(testunit);
            }
            
            //prepare regressDetails to trigger for all testunits.
            for (TestUnitsEntity testUnit : testUnitList) {
                RegressDetails rDetails = new RegressDetails();
                rDetails.setStarttime(Calendar.getInstance());
                rDetails.setStageId(stage);               
                rDetails.setStatus(RegressStatus.notstarted);
                rDetails.setProduct(testUnit.getProductName());
                rDetails.setTestunitId(testUnit);               
                em.persist(rDetails);
                regressList.add(rDetails);
            }; 
            //}
        };
        System.out.println("List: " + regressList);
       
        RunJobs jobs = new RunJobs(regressList, shiphomeList, allShiphomeList, em);
    }

    //@Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
