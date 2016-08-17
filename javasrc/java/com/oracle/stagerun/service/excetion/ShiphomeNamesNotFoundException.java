/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.service.excetion;

/**
 *
 * @author mseelam
 */
public class ShiphomeNamesNotFoundException extends Exception{
    public ShiphomeNamesNotFoundException(String s){
        super("Shiphome names not forund for corresponding release, product and platform" + s);
    }
}
