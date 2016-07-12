/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mseelam
 */
@Singleton
@LocalBean
@Startup
public class Test {

    @PersistenceContext(unitName = "StageRunnerPU")
    EntityManager em;

    @PostConstruct
    public void main() {
//        
//        StageEntity stage = em.find(StageEntity.class, 8);
//        System.out.println("shiphomeList:1" + stage);
//        TypedQuery<StageUpperstackShiphomesEntity> query = em.createNamedQuery("StageUpperstackShiphomesEntity.findByStageId", StageUpperstackShiphomesEntity.class);
//        query.setParameter("stageid", stage.getId());
//        List<StageUpperstackShiphomesEntity> shiphomeList = query.getResultList();
//        System.out.println("shiphomeList:" + shiphomeList);
//
//        // StageEntity stage1 = em.find(StageEntity.class, stage.getId());
//        ReleasesEntity release = stage.getReleaseEntity();
//
//        List<RegressDetails> rList = new ArrayList<>();
//
//        for (StageUpperstackShiphomesEntity shiphome : shiphomeList) {
//            ProductsEntity product = shiphome.getProduct();
//            PlatformEntity platform = shiphome.getPlatform();
//
//            TypedQuery<TestUnitsEntity> query1 = em.createNamedQuery("TestUnitsEntity.findByReleaseAndProductsAndPlatform", TestUnitsEntity.class);
//            query1.setParameter("release", release.getReleaseName());
//            query1.setParameter("pname", product.getProductName());
//            query1.setParameter("platform", platform.getName());
//
//            List<TestUnitsEntity> testUnitList = query1.getResultList();
//
//            for (TestUnitsEntity testUnit : testUnitList) {
//                RegressDetails rDetails = new RegressDetails();
//                rDetails.setStarttime(Calendar.getInstance());
//                rDetails.setStageId(stage);
//                rDetails.setProduct(testUnit.getProductName());
//                rDetails.setTestunitId(testUnit);
//                rList.add(rDetails);
//                em.persist(rDetails); 
//            };
//        };
//        System.out.println("List: " + rList);

       
    }
}
