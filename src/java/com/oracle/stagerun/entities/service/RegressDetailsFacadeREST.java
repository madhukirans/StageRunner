/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.ProductsEntity;
import com.oracle.stagerun.entities.RegressDetails;
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
import javax.ws.rs.core.Response;

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
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(RegressDetails entity) {
        return super.create(entity);
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
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RegressDetails find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
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
    @Path("stage/{stageId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageId(@PathParam("stageId") Integer stageId) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStage", RegressDetails.class);
        query.setParameter("stageId", stageId);
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stageId}/product/{productName}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageIdAndProduct(@PathParam("stageId") Integer stageId, @PathParam("productName") String productName) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStageAndProduct", RegressDetails.class);
        query.setParameter("stageId", stageId);        
        
        ProductsEntity product = new ProductsEntity(productName);                
        query.setParameter("product", productName);
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stageId}/product/{productName}/testunit/{testunitId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegressDetails> getDetailsByStageIdAndProductAndTestUnit(@PathParam("stageId") Integer stageId,
            @PathParam("productName") String productName, @PathParam("testunitId") Integer testunitId) {
        TypedQuery<RegressDetails> query = em.createNamedQuery("RegressDetails.findByStageAndProductAndTestUnit", RegressDetails.class);
        query.setParameter("stageId", stageId);        
        
        ProductsEntity product = new ProductsEntity(productName);                
        query.setParameter("product", productName);
        
        query.setParameter("testunitId", testunitId);
        return query.getResultList();
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
