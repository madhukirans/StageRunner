/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service;

import com.oracle.stagerun.entity.Product;
import com.oracle.stagerun.entity.Stage;
import com.oracle.stagerun.entity.StageUpperstackShiphomes;
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
@Path("shiphomes")
public class StageUpperstackShiphomesFacadeREST extends AbstractFacade<StageUpperstackShiphomes> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public StageUpperstackShiphomesFacadeREST() {
        super(StageUpperstackShiphomes.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(StageUpperstackShiphomes entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, StageUpperstackShiphomes entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public StageUpperstackShiphomes find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET 
    @Path("stage/{stageid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomes> findByStage(@PathParam("stageid") Integer stageid) {
        TypedQuery<StageUpperstackShiphomes> query = em.createNamedQuery("StageUpperstackShiphomes.findByStage", StageUpperstackShiphomes.class);
        query.setParameter("stage", stageid);
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stageId}/products")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Product> findProductsByStage(@PathParam("stageId") Integer stageId) {
        TypedQuery query = em.createNamedQuery("StageUpperstackShiphomes.findProductsStage", Product.class);        
        query.setParameter("stage", stageId);   
        System.out.println("Madhu");
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stageId}/product/{product}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomes> findByStageProduct(@PathParam("stageId") Integer stageId, @PathParam("product") Integer product) {
        TypedQuery query = em.createNamedQuery("StageUpperstackShiphomes.findByStageProduct", StageUpperstackShiphomes.class);        
        query.setParameter("stage", stageId);  
        query.setParameter("product", product);          
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomes> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
