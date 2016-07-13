/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author mseelam
 */
@javax.ws.rs.ApplicationPath("web")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.oracle.stagerun.entities.service.PlatformEntityFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.ProductsEntityFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.RegressDetailsFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.ReleasesEntityFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.RunRegressService.class);
        resources.add(com.oracle.stagerun.entities.service.StageEntityFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.StageInfraDetailsEntityFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.StageUpperstackShiphomesEntityFacadeREST.class);
        resources.add(com.oracle.stagerun.entities.service.TestUnitsEntityFacadeREST.class);
    }
    
}