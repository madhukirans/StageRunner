-- MySQL dump 10.13  Distrib 5.7.12, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: results
-- ------------------------------------------------------
-- Server version	5.7.13

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
-- Table structure for table `testunit`
--

DROP TABLE IF EXISTS `testunit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testunit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users` int(11) DEFAULT NULL,
  `releaseid` int(11) DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  `product` int(11) NOT NULL,
  `component` int(11) DEFAULT NULL,
  `testunit_name` varchar(40) NOT NULL,
  `isDte` varchar(4) DEFAULT NULL,
  `JobreqAgentCommand` longtext,
  `emails` varchar(200) DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product.id_testunit_id_idx` (`product`),
  KEY `testunit_release_fk_idx` (`releaseid`),
  KEY `testunit_platform_fk_idx` (`platform`),
  KEY `fk_testunit_component_name_idx` (`component`),
  KEY `testunit_user_fk` (`users`),
  CONSTRAINT `fk_testunit_component_name` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_fk` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testunit_platform_fk` FOREIGN KEY (`platform`) REFERENCES `platform` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testunit_release_fk` FOREIGN KEY (`releaseid`) REFERENCES `releases` (`releaseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testunit_user_fk` FOREIGN KEY (`users`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='test units for each product';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testunit`
--

LOCK TABLES `testunit` WRITE;
/*!40000 ALTER TABLE `testunit` DISABLE KEYS */;
INSERT INTO `testunit` VALUES (3,NULL,1,1,1,4,'OTD_RJRF_FUNCTIONAL_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108014 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','madhu.seelam@oracle.com','OTD functional regress'),(4,NULL,1,1,1,4,'OTD_HA_REGRESS','DTE','#!/bin/bash\necho \"this is otd HA regress\"','madhu.seelam@oracle.com','OTD HA regress');
/*!40000 ALTER TABLE `testunit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-12  6:03:49
