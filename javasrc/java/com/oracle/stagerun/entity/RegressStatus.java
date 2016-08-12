/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.entity;

/**
 *
 * @author mseelam
 */
public enum RegressStatus {
    notstarted,
    running,
    completed, 
    aborted,
    failed;
//    
//    notstarted("notstarted"),
//    running ("running"),
//    completed("completed"),
//    aborted("aborted");
//    
//    private String name;
//    private RegressStatus(String name) {
//        this.name = name;
//    }
//    
//    @Override
//    public String toString(){
//        return this.name;
//    }
}
