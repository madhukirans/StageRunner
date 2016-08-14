/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service;

import com.oracle.stagerun.entity.Component;
import com.oracle.stagerun.entity.TestUnitRunTypeEnum;
import com.oracle.stagerun.entity.Testunit;
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
@Path("testunit")
public class TestunitFacadeREST extends AbstractFacade<Testunit> {

    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;

    public TestunitFacadeREST() {
        super(Testunit.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Testunit entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Testunit entity) {
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
    public Testunit find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Testunit> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("release/{release}/product/{product}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Testunit> findByProductAndRelease(@PathParam("release") Integer release, 
            @PathParam("product") Integer product) {
        TypedQuery<Testunit> query = em.createNamedQuery("Testunit.findByReleaseProduct", Testunit.class);

        query.setParameter("release", release);
        query.setParameter("product", product);        
        return query.getResultList();
    }
    
    @GET
    @Path("release/{release}/product/{product}/component/{component}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Testunit> findByProductAndReleaseComponent(@PathParam("release") Integer release, 
            @PathParam("product") Integer product, @PathParam("component") Integer componentid) {
        TypedQuery<Testunit> query = em.createNamedQuery("Testunit.findByReleaseProductComponent", Testunit.class);

        query.setParameter("release", release);
        query.setParameter("product", product);
        query.setParameter("component", componentid);
        return query.getResultList();
    }
    
    
    
     @GET
    @Path("updateall")
    @Produces({MediaType.APPLICATION_JSON})
    public List <Testunit> updateAllBashscripts() {
        
        TypedQuery<Component> query = em.createNamedQuery("Component.findAll", Component.class);
        List <Component> list = query.getResultList();
        
        String strProduct ="";
        for (Component test : list) {
            strProduct = strProduct + "echo $WINDOWS_X64_" + test.getProduct().getName() + "_SHIPHOME_LOC\n"; 
            strProduct = strProduct + "echo $LINUX_X64_" + test.getProduct().getName() + "_SHIPHOME_LOC\n"; 
            strProduct = strProduct + "echo $GENERIC_" + test.getProduct().getName() + "_SHIPHOME_LOC\n"; 
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
        
        List <Testunit> list1 = findAll();
        for (Testunit entity : list1) {
            entity.setJobreqAgentCommand(str);
            entity.setIsDte(TestUnitRunTypeEnum.DTE);
            em.persist(entity);
        }
        
        return list1;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Testunit> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
