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
-- Table structure for table `regress_details`
--

DROP TABLE IF EXISTS `regress_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regress_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stage_id` int(11) DEFAULT NULL,
  `product` varchar(45) DEFAULT NULL,
  `testunit_id` int(11) DEFAULT NULL,
  `farmrun_id` int(11) DEFAULT NULL,
  `status` enum('notstarted','running','completed','aborted','failed') DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `work_loc` mediumtext,
  `suc_count` int(11) DEFAULT NULL,
  `dif_count` int(11) DEFAULT NULL,
  `sapphire_upload_status` varchar(10) DEFAULT NULL,
  `gtlf_file_loc` mediumtext,
  PRIMARY KEY (`id`),
  KEY `results_details_stageid_fk_idx` (`stage_id`),
  KEY `results_details_testunits_fk_idx` (`testunit_id`),
  KEY `results_details_products_fk_idx` (`product`),
  CONSTRAINT `results_details_products_fk` FOREIGN KEY (`product`) REFERENCES `products` (`product_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `results_details_stageid_fk` FOREIGN KEY (`stage_id`) REFERENCES `stage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `results_details_testunits_fk` FOREIGN KEY (`testunit_id`) REFERENCES `test_units` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regress_details`
--

LOCK TABLES `regress_details` WRITE;
/*!40000 ALTER TABLE `regress_details` DISABLE KEYS */;
INSERT INTO `regress_details` VALUES (2,8,'OTD',2,17914978,'completed',NULL,'2016-07-26 13:18:56','/net/adcnas470/export/farm_txn_results/FMWTOOLS_12.2.1.2.0_GENERIC_T17914978',2,3,'uploaded','/tmp/sr/work/12.2.1.1.0/1/OTD/OTD_HA/LINUX.X64_OTD12.2.1.1.0_stage1_OTD_HA_17914978.xml'),(98,9,'OTD',2,17914972,'completed','2016-07-22 12:08:01','2016-07-26 13:18:56','/net/adcnas470/export/farm_txn_results/OTDQA_MAIN_GENERIC_T17914972',789,87,'uploaded','/tmp/sr/work/12.2.1.1.0/22/OTD/OTD_HA/LINUX.X64_OTD12.2.1.1.0_stage22_OTD_HA_17914972.xml'),(99,8,'OTD',2,17949410,'running','2016-07-28 00:04:34',NULL,NULL,1234,20,NULL,NULL),(100,8,'OTD',2,17949411,'running','2016-07-28 00:04:34',NULL,NULL,350,6,NULL,NULL),(101,8,'OTD',6,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,8,1,NULL,NULL),(102,8,'OHS',11,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,8,1,NULL,NULL),(103,8,'OHS',10,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,9,1,NULL,NULL),(104,8,'OTD',2,17950916,'running','2016-07-28 03:35:02',NULL,NULL,80,20,NULL,NULL),(105,8,'OTD',1,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,2,1,NULL,NULL),(106,8,'WLS',9,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,3,1,NULL,NULL),(107,8,'BI',12,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,2,1,NULL,NULL),(108,8,'IDM',17,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,5,1,NULL,NULL),(109,8,'EDQ',13,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,2,1,NULL,NULL),(110,8,'BI',12,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,NULL,1,NULL,NULL),(111,8,'SOA',18,NULL,'failed','2016-07-28 03:35:02',NULL,NULL,NULL,1,NULL,NULL),(112,15,'OTD',7,NULL,'failed','2016-07-28 03:40:12',NULL,NULL,NULL,1,NULL,NULL),(113,15,'OTD',20,NULL,'failed','2016-07-28 03:40:12',NULL,NULL,NULL,1,NULL,NULL),(114,15,'OTD',8,NULL,'failed','2016-07-28 03:40:12',NULL,NULL,NULL,1,NULL,NULL),(115,15,'OHS',21,NULL,'failed','2016-07-28 03:40:12',NULL,NULL,20,1,NULL,NULL),(116,15,'OHS',22,NULL,'failed','2016-07-28 03:40:12',NULL,NULL,12,1,NULL,NULL),(119,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,23,4,NULL,NULL),(120,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,3,NULL,NULL),(121,14,'OHS',21,NULL,'failed','2016-07-28 08:04:33',NULL,NULL,NULL,NULL,NULL,NULL),(122,14,'OHS',22,NULL,'failed','2016-07-28 08:40:48',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `regress_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-02 13:49:10
