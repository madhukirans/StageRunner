/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.ProductsEntity;
import com.oracle.stagerun.entities.TestUnitsEntity;
import com.oracle.stagerun.entities.TestUnitRunTypeEnum;
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
    
    @GET
    @Path("updateall")
    @Produces({MediaType.APPLICATION_JSON})
    public List <TestUnitsEntity> updateAllBashscripts() {
        
        TypedQuery<ProductsEntity> query = em.createNamedQuery("ProductsEntity.findAll", ProductsEntity.class);
        List <ProductsEntity> list = query.getResultList();
        
        String strProduct ="";
        for (ProductsEntity product : list) {
            strProduct = strProduct + "echo $WINDOWS_X64_" + product.getProductName() + "_SHIPHOME_LOC\n"; 
            strProduct = strProduct + "echo $LINUX_X64_" + product.getProductName() + "_SHIPHOME_LOC\n"; 
            strProduct = strProduct + "echo $GENERIC_" + product.getProductName() + "_SHIPHOME_LOC\n"; 
        };
        
        String str = "#!/bin/bash\n"
                + "\n"
                + "export VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\n"
                + "echo VIEW_LABEL: $VIEW_LABEL\n"
                + "echo \" TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\n"
                + "echo PLATFORM $PLATFORM\n"
                + "echo Product: $PRODUCT\n"
                + "echo Release: $RELEASE\n"
                + "echo Stage: $STAGE\n"
                + strProduct 
                + "\n"
                + "/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 90854 " 
                + "-p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME_LOC} -l $VIEW_LABEL "
                + "-preExecuted\\=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 "
                + "-preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPOME_LOC} "
                + "JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump "
                + "-Dcom.oracle.lifecycle.oob=true";
        
        List <TestUnitsEntity> list1 = findAll();
        for (TestUnitsEntity entity : list1) {
            entity.setJobreqAgentCommand(str);
            entity.setIsDte(TestUnitRunTypeEnum.DTE);
            em.persist(entity);
        }
        
        return list1;
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
    @Path("release/{release}/product/{product}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TestUnitsEntity> findByProductAndRelease(@PathParam("release") String release, @PathParam("product") String product) {
        TypedQuery<TestUnitsEntity> query = em.createNamedQuery("TestUnitsEntity.findByReleaseAndProducts", TestUnitsEntity.class);

        query.setParameter("release", release);
        query.setParameter("pname", product);
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
