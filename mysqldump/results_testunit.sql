-- MySQL dump 10.13  Distrib 5.7.13, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: results
-- ------------------------------------------------------
-- Server version	5.7.16

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
  `isGtlfGenerated` varchar(5) DEFAULT 'false',
  `version` int(11) DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='test units for each product';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testunit`
--

LOCK TABLES `testunit` WRITE;
/*!40000 ALTER TABLE `testunit` DISABLE KEYS */;
INSERT INTO `testunit` VALUES (3,NULL,1,1,1,4,'OTD_RJRF_FUNCTIONAL_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\necho /ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108014 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108014 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','webtierqa_in@oracle.com','OTD functional regress','false',NULL),(4,NULL,1,1,1,4,'OTD_RJRF_HA_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\necho /ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20088 -toposetMemberID 113400 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20088 -toposetMemberID 108019 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true','webtierqa_in@oracle.com','OTD HA regress','false',NULL),(5,NULL,1,1,1,9,'NLS_OTD','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\necho /ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108014 -p ${PLATFORM} -r  ${RELEASE} -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true -AUTO_HOME=/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE OtdTestParam=\"G11n -Dlocale=ja -Dcoverage=true\" \n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108014 -p ${PLATFORM} -r  ${RELEASE} -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true -AUTO_HOME=/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE OtdTestParam=\"G11n -Dlocale=ja -Dcoverage=true\"','webtierqa_in@oracle.com','Globalization tests','false',NULL),(6,NULL,1,1,1,4,'OTD_RJRF_MT_DUAL_DOMAIN_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\necho /ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108013 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=\"-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\"\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20087 -toposetMemberID 108013 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=\"-javaagent:/ade_autofs/gd12_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\"','webtierqa_in@oracle.com','OTD MT regress','false',NULL),(7,NULL,1,2,1,4,'WINDOWS_OTD_RJRF_FUNCTIONAL_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_12.2.1.2.0_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${WINDOWS_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${WINDOWS_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\necho /ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20089 -toposetMemberID 108027 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME}  JAVA_OPTIONS=-Dcom.oracle.lifecycle.oob=true\n\n/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 20089 -toposetMemberID 108027 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME}  JAVA_OPTIONS=-Dcom.oracle.lifecycle.oob=true','webtierqa_in@oracle.com','Windows OTD Functional regress','false',NULL),(8,NULL,1,2,1,4,'WINDOWS_OTD_RJRF_MT_DUALDOMAIN_REGRESS','DTE','#!/bin/bash\n\nsource env.prop\nexport VIEW_LABEL=`ade showlabels -series OTDQA_12.2.1.2.0_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${WINDOWS_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${WINDOWS_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21104 -toposetMemberID 114198 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME}  JAVA_OPTIONS=-Dcom.oracle.lifecycle.oob=true\"\n\necho $command\n$command\necho \"Execution completed\"','webtierqa_in@oracle.com','Windows OTD MT regress','false',NULL),(9,NULL,1,1,3,6,'WLS_REGRESS','BASH','#!/bin/bash\necho \"wls regress\"','madhu.seelam@oracle.com','asdf','false',NULL),(10,NULL,2,1,1,4,'OTD_RJRF_FUNCTIONAL_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21105 -toposetMemberID 114204 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=\\\"-javaagent:/ade_autofs/gd59_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\\\"\"\n\necho $command\n\n$command\n\necho \"Execution complted\"','webtierqa_in@oracle.com','OTD functional regress','false',NULL),(11,NULL,2,1,1,4,'OTD_RJRF_HA_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21103 -toposetMemberID 114195 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=\\\"-javaagent:/ade_autofs/gd59_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\\\"\"\n\necho $command\n$command\necho \"Execution completed\"','webtierqa_in@oracle.com','OTD HA regress','false',NULL),(12,NULL,2,1,1,9,'NLS_OTD','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21105 -toposetMemberID 114210 -p ${PLATFORM} -r  ${RELEASE} -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=-javaagent:/ade_autofs/gd59_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true -AUTO_HOME=/ade_autofs/ade_base/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE OtdTestParam=\\\"G11n -Dlocale=ja -Dcoverage=true\\\"\"\n\necho $command\n$command\necho \"Execution completed\"','webtierqa_in@oracle.com','Globalization tests','false',NULL),(13,NULL,2,1,1,4,'OTD_RJRF_MT_DUAL_DOMAIN_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_MAIN_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${LINUX_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21105 -toposetMemberID 114212 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME} JAVA_OPTIONS=\\\"-javaagent:/ade_autofs/gd59_fmw/OTDQA_MAIN_GENERIC.rdd/LATEST/otd_test/lib/jacoco/jacocoagent.jar=destfile=%T_WORK%/coverage.dump -Dcom.oracle.lifecycle.oob=true\\\"\"\n\necho $command\n$command\necho \"Execution completed\"','webtierqa_in@oracle.com','OTD MT regress','false',NULL),(14,NULL,2,2,1,4,'WINDOWS_OTD_RJRF_FUNCTIONAL_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_12.2.1.3.0_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${WINDOWS_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${WINDOWS_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21104 -toposetMemberID 114200 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME}  JAVA_OPTIONS=\\\"-Dcom.oracle.lifecycle.oob=true\\\"\"\n\necho $command\n$command\necho \"Execution completed\"','webtierqa_in@oracle.com','Windows OTD Functional regress','false',NULL),(15,NULL,2,2,1,4,'WINDOWS_OTD_RJRF_MT_DUALDOMAIN_REGRESS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series OTDQA_12.2.1.3.0_GENERIC -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\necho \"TARGETLOC=%ADE_VIEW_ROOT%/otd_test\" >  download_export.txt\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOTD_SHIPHOME=${WINDOWS_X64_ASCORE_OTD_SHIPHOME}\nJRF_SHIPHOME=${WINDOWS_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OTD_SHIPHOME: $OTD_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -toposetid 21104 -toposetMemberID 114196 -p $PLATFORM -r $RELEASE -s ${OTD_SHIPHOME} -l ${VIEW_LABEL} -preExecuted=OTD_MT.DOWNLOAD_TEST1,OTD_Setup.DOWNLOAD_TEST1,OTD_HA_Setup.DOWNLOAD_TEST1 -preExportedFile=download_export.txt,download_export.txt,download_export.txt ENV:MY_JRF_SHIPHOME_LOCATION=${JRF_SHIPHOME}  JAVA_OPTIONS=-Dcom.oracle.lifecycle.oob=true\"\n\necho $command\n$command\necho \"Execution completed\"','webtierqa_in@oracle.com','Windows OTD MT regress','false',NULL),(16,NULL,2,1,3,6,'WLS_REGRESS','BASH','#!/bin/bash\necho \"wls regress\"','madhu.seelam@oracle.com','asdf','false',NULL),(17,NULL,2,1,1,5,'OHS_MATS','DTE','#!/bin/bash\n\nexport VIEW_LABEL=`ade showlabels -series ASCORE_${RELEASE}_LINUX.X64 -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\n\nLINUX_ASCOREMANIFEST=`re list \"manifest.integration.release:ascore-linux_x64-manifest:${RELEASE}*\" | grep \" *UNKNOWN*\" | tail -1 | awk -F\'*UNKNOWN*\' \'{print $1}\' | tr -d \' \'`\necho LINUX_ASCOREMANIFEST: ${LINUX_ASCOREMANIFEST}\n\nexport COORDINATES=`re list \"com.oracle.ohs.linux_x64:ohs-mats:${RELEASE}-*\" | grep \" sandbox\" | tail -1 | awk -F\'sandbox\' \'{print $1}\' | tr -d \' \'`\necho COORDINATES: $COORDINATES\n\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOHS_SHIPHOME=${LINUX_X64_ASCORE_OHS_SHIPHOME}\nJRF_SHIPHOME=${LINUX_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OHS_SHIPHOME: $OHS_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 89839 -p $PLATFORM -r $RELEASE -s $OHS_SHIPHOME -l $VIEW_LABEL  ENV:MY_JRF_SHIPHOME_LOCATION=$JRF_SHIPHOME MANIFEST=\\\"${LINUX_ASCOREMANIFEST}:pom\\\" COORDINATES=\\\"$COORDINATES:tar.gz\\\" TESTDEPCOORDINATES=\\\"manifest.publish.label.TESTTOOL_GENERIC:manifest\\\" TARGETLOC=\\\"%ADE_VIEW_ROOT%\\\" TEST_PARAM=\\\"-Dtestlogic.group.includes.working=true -Dtestlogic.group.includes.install=restricted\\\" -noalways=true -emailNotifyOption=none GTLF_STRING=4 GTLF_BRANCH=main GTLF_RELEASE=WebTier${RELEASE} GTLF_STAGE=$STAGE\"\n\necho $command\n$command\necho \"Execution completed\"','WEBTIERQA_IN@oracle.com','','true',NULL),(18,NULL,2,2,1,5,'WINDOWS_OHS_MATS','DTE','#!/bin/bash\n\nexport ADE_INCLUDE_LABEL_SERVERS=\"/ade_autofs/wd55_fmw/ slcavere05-422.us.oracle.com:/export/wd55_fmw\"\n\nexport VIEW_LABEL=`ade showlabels -series ASCORE_${RELEASE}_WINDOWS.X64 -latest | tail -1`\necho VIEW_LABEL: $VIEW_LABEL\n\nWINDOWS_ASCOREMANIFEST=`re list \"manifest.integration.release:ascore-windows_x64-manifest:${RELEASE}*\" | grep \" *UNKNOWN*\" | tail -1 | awk -F\'*UNKNOWN*\' \'{print $1}\' | tr -d \' \'`\necho WINDOWS_ASCOREMANIFEST: ${WINDOWS_ASCOREMANIFEST}\n\nexport COORDINATES=`re list \"com.oracle.ohs.linux_x64:ohs-mats:${RELEASE}-*\" | grep \" sandbox\" | tail -1 | awk -F\'sandbox\' \'{print $1}\' | tr -d \' \'`\necho COORDINATES: $COORDINATES\n\necho PLATFORM $PLATFORM\necho Product: $PRODUCT\necho Release: $RELEASE\necho Stage: $STAGE\n\nOHS_SHIPHOME=${WINDOWS_X64_ASCORE_OHS_SHIPHOME}\nJRF_SHIPHOME=${WINDOWS_X64_ASKERNEL_JRF_SHIPHOME}\n\necho OHS_SHIPHOME: $OHS_SHIPHOME\necho JRF_SHIPHOME: $JRF_SHIPHOME\n#Topoid = 89839\n#-toposetid 20261 -toposetMemberID 108957\ncommand=\"/ade_autofs/ade_infra/AIME_MAIN_LINUX.rdd/LATEST/dte/DTE/bin/jobReqAgent -topoid 89839 -p $PLATFORM -v 2008_zfs -r $RELEASE -s $OHS_SHIPHOME -l $VIEW_LABEL  ENV:MY_JRF_SHIPHOME_LOCATION=$JRF_SHIPHOME MANIFEST=\\\"${WINDOWS_ASCOREMANIFEST}:pom\\\" COORDINATES=\\\"${COORDINATES}:tar.gz\\\" TESTDEPCOORDINATES=manifest.publish.label.TESTTOOL_GENERIC:manifest TARGETLOC=\\\"%ADE_VIEW_ROOT%\\\" TEST_PARAM=\\\"-Dtestlogic.group.includes.working=true -Dtestlogic.group.includes.install=restricted -Dtestlogic.group.includes.winFIPS=false\\\" -intgflag 4 -txn_name=mseelam_ohs_opensslfix -txn_view_name=mseelam_ascore1 -txn_hostname=slc04cgb.us.oracle.com -noalways=true -emailNotifyOption=none GTLF_STRING=4 GTLF_BRANCH=main GTLF_RELEASE=WebTier${RELEASE} GTLF_STAGE=$STAGE\"\n\necho $command\n$command\necho \"Execution completed\"','WEBTIERQA_IN@oracle.com','','true',NULL);
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

-- Dump completed on 2017-03-03  5:25:28
