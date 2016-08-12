/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service;

import com.oracle.stagerun.entity.Product;
import com.oracle.stagerun.entity.ShiphomeNames;
import com.oracle.stagerun.entity.Stage;
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
@Path("shiphomenames")
public class ShiphomeNamesFacadeREST extends AbstractFacade<ShiphomeNames> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public ShiphomeNamesFacadeREST() {
        super(ShiphomeNames.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ShiphomeNames entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ShiphomeNames entity) {
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
    public ShiphomeNames find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ShiphomeNames> findAll() {
        return super.findAll();
    }
    
    
    
    @GET   
    @Path("release/{releaseid}/product/{productid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ShiphomeNames> findByRelease(@PathParam ("releaseid") Integer releaseId, @PathParam ("productid") Integer productId) {
        TypedQuery query = em.createNamedQuery("ShiphomeNames.findByReleaseAndProduct", ShiphomeNames.class);
        query.setParameter("releaseid", releaseId);
        query.setParameter("productid", productId);
        return query.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ShiphomeNames> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
