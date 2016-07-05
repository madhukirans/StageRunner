/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.ProductsEntity;
import com.oracle.stagerun.entities.TestUnitsEntity;
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
@Path("testunits")
public class TestUnitsEntityFacadeREST extends AbstractFacade<TestUnitsEntity> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public TestUnitsEntityFacadeREST() {
        super(TestUnitsEntity.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(TestUnitsEntity entity) {
       // entity.setId(100);
       // entity.setJobreqAgentCommand("commad");
       // entity.setTestUnitName("JRF_1");
       // entity.setProductName(new ProductsEntity("JRF"));
        System.out.println("test unit entity:" + entity);
        
        em.persist(entity);
        Response r = Response.ok(entity).build();
        System.out.println(" StageEntiry Add Successful -" + r.toString());
        return r;
        //return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, TestUnitsEntity entity) {
        System.out.println("test unit entity:" + entity);
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
    public TestUnitsEntity find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<TestUnitsEntity> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("/product/{product}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TestUnitsEntity> findByProduct(@PathParam ("product") String product) {
        TypedQuery<TestUnitsEntity> query = em.createNamedQuery("TestUnitsEntity.findByProducts", TestUnitsEntity.class);
        ProductsEntity productObj = new ProductsEntity();
        productObj.setProductName(product);
        query.setParameter("pname", productObj);
        return query.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TestUnitsEntity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
