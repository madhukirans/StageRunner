CREATE DATABASE  IF NOT EXISTS `results` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `results`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: results
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `test_units`
--

DROP TABLE IF EXISTS `test_units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_units` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_unit_name` varchar(40) NOT NULL,
  `product_name` varchar(20) NOT NULL,
  `description` varchar(40) DEFAULT NULL,
  `topoid` int(11) DEFAULT NULL,
  `JobreqAgentCommand` longtext,
  `release_name` varchar(20) DEFAULT NULL,
  `platform_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `products.id_test_units_id_idx` (`product_name`),
  KEY `testunit_release_fk_idx` (`release_name`),
  KEY `testunit_platform_fk_idx` (`platform_name`),
  CONSTRAINT `product_fk` FOREIGN KEY (`product_name`) REFERENCES `products` (`product_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `testunit_platform_fk` FOREIGN KEY (`platform_name`) REFERENCES `platform` (`NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `testunit_release_fk` FOREIGN KEY (`release_name`) REFERENCES `releases` (`release_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='test units for each products';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_units`
--

LOCK TABLES `test_units` WRITE;
/*!40000 ALTER TABLE `test_units` DISABLE KEYS */;
INSERT INTO `test_units` VALUES (1,'LINUX_OTD_FUNC_REGRESS','OTD','otd regress',88620,'#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho \" TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\neval  echo \\$${PLATFORM}_${OTD_SHIPHOME_LOC}\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 88620 -p LINUX.X64 -r 12.2.1.2.0 -s ${OTD_SHIPHOME_LOC} -l $VIEW_LABEL -preExecuted\\=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPOME_LOC} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','12.2.1.1.0','LINUX.X64'),(2,'OTD_HA','OTD','Otd regress',123,'#!/bin/bash\necho \"this is job reqagenmt command\"','12.2.1.1.0','LINUX.X64'),(3,'OHS_FR','OHS','asdf',1234,'echo \"this is job reqagent command form OHS component\"\necho \"This is OHS_SHIPHOME $OHS_SHIPHOME\"','12.2.1.1.0','WINDOWS.X64'),(5,'JRF','JRF','deesc',123456,'echo \"this is JRF jobreqagent command\"','12.2.1.1.0','GENERIC'),(6,'OTD_FUNC_REGRESS','OTD','otd windows regress',88620,'#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho \" TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\neval  echo \\$${PLATFORM}_${OTD_SHIPHOME_LOC}\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 88620 -p LINUX.X64 -r 12.2.1.2.0 -s ${OTD_SHIPHOME_LOC} -l $VIEW_LABEL -preExecuted\\=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPOME_LOC} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','12.2.1.1.0','WINDOWS.X64'),(7,'OTD_FA_RGRESS','OTD','otd regress',88889,'#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho \" TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\neval  echo \\$${PLATFORM}_${OTD_SHIPHOME_LOC}\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 88620 -p LINUX.X64 -r 12.2.1.2.0 -s ${OTD_SHIPHOME_LOC} -l $VIEW_LABEL -preExecuted\\=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPOME_LOC} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','12.2.1.2.0','LINUX.X64'),(8,'OTD_FA_RGRESS','OTD','otd regress',88889,'#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho \" TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\neval  echo \\$${PLATFORM}_${OTD_SHIPHOME_LOC}\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 88620 -p LINUX.X64 -r 12.2.1.2.0 -s ${OTD_SHIPHOME_LOC} -l $VIEW_LABEL -preExecuted\\=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPOME_LOC} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','12.2.1.2.0','WINDOWS.X64'),(9,'WLS_REGRESS','WLS','wls regress',674534,'#!/bin/bash\necho \"This is WLS regress\"','12.2.1.1.0','GENERIC');
/*!40000 ALTER TABLE `test_units` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-08 17:58:52
