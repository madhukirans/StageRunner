/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.stagerun.beans;

import com.oracle.stagerun.entity.RegressDetails;
import com.oracle.stagerun.tool.AbstractStgeRun;
import com.oracle.stagerun.tool.StageRun;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author mseelam
 */
@Singleton
@Startup
public class StageRunWeb extends AbstractStgeRun {
    
    
}
