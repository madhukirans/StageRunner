/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service;

import com.oracle.stagerun.entity.Component;
import com.oracle.stagerun.entity.Platform;
import com.oracle.stagerun.entity.Product;
import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.entity.RegressStatus;
import com.oracle.stagerun.entity.Releases;
import com.oracle.stagerun.entity.Stage;
import com.oracle.stagerun.entity.StageUpperstackShiphomes;
import com.oracle.stagerun.entity.Testunit;
import com.oracle.stagerun.tool.RunJobs;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mseelam
 */
@Stateless
@Path("regressdetails")
public class RegressDetailsFacadeREST extends AbstractFacade<RegressDetails> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public RegressDetailsFacadeREST() {
        super(RegressDetails.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void runRegress1(PostRegressHelper post) {
        System.out.println("getStage:1" + post.getStage());
        System.out.println("getProduct:1" + post.getProduct());
        System.out.println("getComponent:1" + post.getComponent());
        System.out.println("getTestunit:1" + post.getTestunit());

        Stage stage = post.getStage();
        Releases release = stage.getRelease();
        Product product = post.getProduct();
        Component component = post.getComponent();
        Testunit testunit = post.getTestunit();

        List<StageUpperstackShiphomes> allShiphomeList = null;
        List<StageUpperstackShiphomes> shiphomeList = null;

        //Get shiphomes based on postdata
        TypedQuery<StageUpperstackShiphomes> shiphomeQery;
        if (product.getId() == null) {
            shiphomeQery = em.createNamedQuery("StageUpperstackShiphomes.findByStage", StageUpperstackShiphomes.class);
            shiphomeQery.setParameter("stage", stage.getId());
            shiphomeList = shiphomeQery.getResultList();
            allShiphomeList = shiphomeList;
        } else {
            shiphomeQery = em.createNamedQuery("StageUpperstackShiphomes.findByStageProduct", StageUpperstackShiphomes.class);
            shiphomeQery.setParameter("product", product.getId());
            shiphomeQery.setParameter("stage", stage.getId());
            shiphomeList = shiphomeQery.getResultList();

            TypedQuery<StageUpperstackShiphomes> allShiphomeQery = em.createNamedQuery("StageUpperstackShiphomes.findByStage", StageUpperstackShiphomes.class);
            allShiphomeQery.setParameter("stage", stage.getId());
            allShiphomeList = allShiphomeQery.getResultList();
        }

        System.out.println("shiphomeList:" + shiphomeList);

        List<RegressDetails> prepareRegressList = new ArrayList<>();

        for (StageUpperstackShiphomes shiphome : shiphomeList) {

            //Get all testunits based on release, product and platform if user not selected form UI
            Product tempProduct = shiphome.getProduct();
            Platform tempPlatform = shiphome.getPlatform();

            List<Testunit> testUnitList = new ArrayList<>();
            if (testunit.getId() == null) {
                TypedQuery<Testunit> testunitQuery = null;
                if (component.getId() != null) {
                    testunitQuery = em.createNamedQuery("Testunit.findByReleaseProductComponentPlatform", Testunit.class);
                    testunitQuery.setParameter("component", component.getId());
                } else {
                    testunitQuery = em.createNamedQuery("Testunit.findByReleaseProductPlatform", Testunit.class);
                }
                testunitQuery.setParameter("release", release.getId());
                testunitQuery.setParameter("product", tempProduct.getId());                
                testunitQuery.setParameter("platform", tempPlatform.getId());
                testUnitList = testunitQuery.getResultList();
            } else {
                testUnitList.add(testunit);
            }

            //prepare regressDetails to trigger for all testunits.
            for (Testunit testUnit : testUnitList) {
                RegressDetails rDetails = new RegressDetails();
                rDetails.setStarttime(Calendar.getInstance().getTime());
                rDetails.setStage(stage);
                rDetails.setStatus(RegressStatus.notstarted);
                rDetails.setProduct(testUnit.getProduct());
                rDetails.setComponent(testUnit.getComponent());
                rDetails.setTestunit(testUnit);
                em.persist(rDetails);
                prepareRegressList.add(rDetails);
            };
            //}
        };
        System.out.println("Regress List: " + prepareRegressList);

        RunJobs jobs = new RunJobs(release, stage, prepareRegressList, shiphomeList, allShiphomeList, em);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, RegressDetails entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("stage/{stageId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageId(@PathParam("stageId") Integer stageId) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStage", RegressDetails.class);
        query.setParameter("stage", stageId);
        return query.getResultList();
    }

    @GET
    @Path("stage/{stage}/product/{product}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageIdAndProduct(@PathParam("stage") Integer stageId, @PathParam("product") Integer productid) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStageProduct", RegressDetails.class);
        query.setParameter("stage", stageId);
        query.setParameter("product", productid);
        return query.getResultList();
    }

    @GET
    @Path("stage/{stage}/product/{product}/testunit/{testunit}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageIdAndProductAndTestUnit(@PathParam("stage") Integer stageId,
            @PathParam("product") Integer productid, @PathParam("testunit") Integer testunitId) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStageProductTestUnit", RegressDetails.class);
        query.setParameter("stage", stageId);
        query.setParameter("product", productid);
        query.setParameter("testunit", testunitId);
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stage}/product/{product}/component/{component}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageProductComponent(@PathParam("stage") Integer stageId,
            @PathParam("product") Integer productid, @PathParam("component") Integer component) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStageProductComponent", RegressDetails.class);
        query.setParameter("stage", stageId);
        query.setParameter("product", productid);
        query.setParameter("component", component);        
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stage}/product/{product}/component/{component}/testunit/{testunit}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageProductComponentTestUnit(@PathParam("stage") Integer stageId,
            @PathParam("product") Integer productid, @PathParam("component") Integer component, @PathParam("testunit") Integer testunitId) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStageProductComponentTestUnit", RegressDetails.class);
        query.setParameter("stage", stageId);
        query.setParameter("product", productid);
        query.setParameter("component", component);
        query.setParameter("testunit", testunitId);
        return query.getResultList();
    }

//    @GET
//    @Path("stage/{stageid}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    public List<RegressDetails> getREsultsByStage(@PathParam("stageid") Integer stageid) {
//        TypedQuery<RegressDetails> query1 = em.createNamedQuery("RegressDetails.findByStage", RegressDetails.class);
//        query1.setParameter("stageId", stageid);
//        List<RegressDetails> regressList = query1.getResultList();
//        System.out.println("regressListSize:" + regressList.size() + ": regressList:" + regressList);
//        return regressList;
//    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public RegressDetails find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RegressDetails> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RegressDetails> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
