/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mseelam
 */
@XmlRootElement
public class GtlfFile implements Serializable {
        
        private String gtlfContent;

        public String getGtlfContent() {
            return gtlfContent;
        }

        public void setGtlfContent(String gtlfContent) {
            this.gtlfContent = gtlfContent;
        }        
    }