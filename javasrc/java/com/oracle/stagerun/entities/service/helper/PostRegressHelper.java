/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entities.service.helper;

import com.oracle.stagerun.entities.ProductsEntity;
import com.oracle.stagerun.entities.StageEntity;
import com.oracle.stagerun.entities.TestUnitsEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@XmlRootElement (name = "postdata")
public class PostRegressHelper {
    private StageEntity stage;
    private ProductsEntity product;
    private TestUnitsEntity testunit;

    public StageEntity getStage() {
        return stage;
    }

    public void setStage(StageEntity stage) {
        this.stage = stage;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public TestUnitsEntity getTestunit() {
        return testunit;
    }

    public void setTestunit(TestUnitsEntity testunit) {
        this.testunit = testunit;
    }
    
    
    
}
