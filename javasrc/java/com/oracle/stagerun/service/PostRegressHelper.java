/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service;

import com.oracle.stagerun.entity.Component;
import com.oracle.stagerun.entity.Product;
import com.oracle.stagerun.entity.Stage;
import com.oracle.stagerun.entity.Testunit;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@XmlRootElement (name = "postdata")
public class PostRegressHelper {
    private Stage stage;
    private Product product;
    private Component component;
    private Testunit testunit;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Testunit getTestunit() {
        return testunit;
    }

    public void setTestunit(Testunit testunit) {
        this.testunit = testunit;
    }
    
    
    
}
