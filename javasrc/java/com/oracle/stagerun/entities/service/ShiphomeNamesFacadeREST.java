/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import com.oracle.stagerun.entities.ReleasesEntity;
import com.oracle.stagerun.entities.ShiphomeNamesEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mseelam
 */
@Stateless
@Path("shiphomenames")
public class ShiphomeNamesFacadeREST {
    @PersistenceContext(unitName = "StageRunnerPU")
    private EntityManager em;
    
    
    public ShiphomeNamesFacadeREST() {
        
    }
            
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ShiphomeNamesEntity> findAll(){
        TypedQuery<ShiphomeNamesEntity> query = em.createNamedQuery("ShiphomeNamesEntity.findAll", ShiphomeNamesEntity.class );
        return query.getResultList();
    }
    
    
//    @GET
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<ReleasesEntity> findAll(){
//        TypedQuery<ReleasesEntity> query = em.createNamedQuery("ReleasesEntity.findAll", ReleasesEntity.class );           
//                
//        return  query.getResultList();
//    }
            
}
