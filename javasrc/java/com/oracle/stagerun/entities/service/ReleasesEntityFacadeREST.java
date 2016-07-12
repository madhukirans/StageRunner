/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.ReleasesEntity;
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
@Path("releases")
public class ReleasesEntityFacadeREST extends AbstractFacade<ReleasesEntity> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public ReleasesEntityFacadeREST() {
        super(ReleasesEntity.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(ReleasesEntity entity) {
        ReleasesEntity e = em.find(ReleasesEntity.class, entity.getReleaseName());
        System.out.println("Find e:" + e);

        if (e == null) {
            return super.create(entity);             
        } else {
            System.out.println("Find e -1:" + e);
            //ErrorReporting report = new ErrorReporting(Response.Status.CONFLICT);
            //report.setResponse();
            Response r = Response.status(Response.Status.CONFLICT).tag("Madhutag").entity("Exception: Record already exists [" + e + "]").build();
            return r;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, ReleasesEntity entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ReleasesEntity find(@PathParam("id") String id) {
        return super.find(id);
    }
  
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<ReleasesEntity> findAll() {
        TypedQuery<ReleasesEntity> query = em.createNamedQuery("ReleasesEntity.findAll", ReleasesEntity.class);
        System.out.println("query.getResultList() " + query.getResultList());                
        return query.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ReleasesEntity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
