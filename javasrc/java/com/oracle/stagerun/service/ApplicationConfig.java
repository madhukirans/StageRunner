/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service;

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
        resources.add(com.oracle.stagerun.service.ComponentFacadeREST.class);
        resources.add(com.oracle.stagerun.service.GenericResource.class);
        resources.add(com.oracle.stagerun.service.PlatformFacadeREST.class);
        resources.add(com.oracle.stagerun.service.ProductFacadeREST.class);
        resources.add(com.oracle.stagerun.service.RegressDetailsFacadeREST.class);
        resources.add(com.oracle.stagerun.service.ReleasesFacadeREST.class);
        resources.add(com.oracle.stagerun.service.ShiphomeNamesFacadeREST.class);
        resources.add(com.oracle.stagerun.service.StageFacadeREST.class);
        resources.add(com.oracle.stagerun.service.StageUpperstackShiphomesFacadeREST.class);
        resources.add(com.oracle.stagerun.service.TestunitFacadeREST.class);
        resources.add(com.oracle.stagerun.service.UsersFacadeREST.class);
        
    }
    
}
