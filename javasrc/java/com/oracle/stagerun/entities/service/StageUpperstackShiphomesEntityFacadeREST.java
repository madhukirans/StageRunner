/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.ProductsEntity;
import com.oracle.stagerun.entities.StageEntity;
import com.oracle.stagerun.entities.StageUpperstackShiphomesEntity;
import java.io.File;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("shiphomes")
public class StageUpperstackShiphomesEntityFacadeREST extends AbstractFacade<StageUpperstackShiphomesEntity> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public StageUpperstackShiphomesEntityFacadeREST() {
        super(StageUpperstackShiphomesEntity.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(StageUpperstackShiphomesEntity entity) {
        return super.create(entity);
    }

    @GET
    @Path("stage/{stageid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomesEntity> findByStageId(@PathParam("stageid") Integer stageid) {
        TypedQuery<StageUpperstackShiphomesEntity> query = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageId", StageUpperstackShiphomesEntity.class);
        query.setParameter("stageid", stageid);
        return query.getResultList();
    }
    
    @GET
    @Path("release/{release}/stage/{stagename}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomesEntity> findByStageId(@PathParam("release") String release, @PathParam("stagename") String stagename) {
        TypedQuery<StageUpperstackShiphomesEntity> query = em.createNamedQuery("StageUpperstackShiphomesEntity.findByReleaseANDStage", StageUpperstackShiphomesEntity.class);
        query.setParameter("release", release);
        query.setParameter("stagename", stagename);
        return query.getResultList();
    }
    
    @GET
    @Path("stage/{stageid}/product/{productName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomesEntity> findByStageIdAndProduct(@PathParam("stageid") Integer stageid, @PathParam("productName") String productName) {
        TypedQuery<StageUpperstackShiphomesEntity> query = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageAndProduct", StageUpperstackShiphomesEntity.class);
        query.setParameter("stageid", stageid);
        query.setParameter("productname", productName);
        return query.getResultList();
    }

    @GET
    @Path("platform/{platform}/shiphomeLoc/{shiphomeLoc}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response checkShiphomeExistence(@PathParam("platform") String platform, @PathParam("shiphomeLoc") String shiphomeLoc) {
        File f = new File(shiphomeLoc);
        if (f.exists()) {
            return Response.ok().build();
        }

        System.out.println(platform + ":" + shiphomeLoc);

        if (platform.equals("WINDOWS.X64")) {
            //\\adcavere03-cifs.us.oracle.com\gd17_fmw\ASKERNEL_12.2.1.2.0_GENERIC.rdd\160715.1137.251.S\askernel\shiphome
            try {
                String str[] = shiphomeLoc.split("\\\\");
                String hostName = str[0];
                String shareName = str[1];
                System.out.println(":" + str);
            } catch (Exception e) {
                System.out.println("Exception SR:" + e);
            }
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }

    @GET
    @Path("stage/{stageId}/products")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductsEntity> findProductsByStage(@PathParam("stageId") Integer stageId) {
        TypedQuery query = em.createNamedQuery("StageUpperstackShiphomesEntity.findProductsStageId", ProductsEntity.class);
        StageEntity sEntity = new StageEntity();
        sEntity.setId(stageId);
        query.setParameter("stageid", sEntity);
        System.out.println(sEntity);
        return query.getResultList();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, StageUpperstackShiphomesEntity entity) {

        super.edit(entity);
        System.out.println("Edit Successful" + id + entity.toString());
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public StageUpperstackShiphomesEntity find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomesEntity> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<StageUpperstackShiphomesEntity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
