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
  `status` enum('notstarted','running','completed','aborted') DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regress_details`
--

LOCK TABLES `regress_details` WRITE;
/*!40000 ALTER TABLE `regress_details` DISABLE KEYS */;
INSERT INTO `regress_details` VALUES (2,8,'OTD',2,123,'completed',NULL,NULL,'/A/WOK',2,3,'YES','/AS/AS/S/'),(20,8,'OTD',6,NULL,'notstarted','2016-07-08 15:53:47',NULL,NULL,100,30,NULL,NULL),(21,8,'OTD',1,NULL,'notstarted','2016-07-08 15:53:47',NULL,NULL,45,9,NULL,NULL),(22,8,'OTD',2,NULL,'notstarted','2016-07-08 15:53:47',NULL,NULL,45,9,NULL,NULL),(23,8,'WLS',9,NULL,'notstarted','2016-07-08 15:53:47',NULL,NULL,454,9,NULL,NULL),(24,8,'OTD',6,NULL,'notstarted','2016-07-08 15:54:10',NULL,NULL,656,9,NULL,NULL),(25,8,'OTD',2,NULL,'notstarted','2016-07-08 15:54:10',NULL,NULL,674,9,NULL,NULL),(26,8,'OTD',1,NULL,'notstarted','2016-07-08 15:54:10',NULL,NULL,678,9,NULL,NULL),(27,8,'WLS',9,NULL,'notstarted','2016-07-08 15:54:11',NULL,NULL,6766,9,NULL,NULL),(28,8,'OTD',6,NULL,'notstarted','2016-07-08 16:00:38',NULL,NULL,678,6,NULL,NULL),(29,8,'OTD',2,NULL,'notstarted','2016-07-08 16:00:38',NULL,NULL,23,9,NULL,NULL),(30,8,'OTD',1,NULL,'notstarted','2016-07-08 16:00:38',NULL,NULL,451,9,NULL,NULL),(31,8,'WLS',9,NULL,'notstarted','2016-07-08 16:00:38',NULL,NULL,545,9,NULL,NULL),(32,8,'OTD',6,NULL,'notstarted','2016-07-08 16:02:26',NULL,NULL,567,9,NULL,NULL),(33,8,'OTD',1,NULL,'notstarted','2016-07-08 16:02:26',NULL,NULL,56,9,NULL,NULL),(34,8,'OTD',2,NULL,'notstarted','2016-07-08 16:02:26',NULL,NULL,8,99,NULL,NULL),(35,8,'WLS',9,NULL,'notstarted','2016-07-08 16:02:26',NULL,NULL,45,97,NULL,NULL),(36,9,'OTD',1,NULL,'notstarted','2016-07-08 17:49:17',NULL,NULL,97,67,NULL,NULL),(37,9,'OTD',2,NULL,'notstarted','2016-07-08 17:49:17',NULL,NULL,5,67,NULL,NULL),(38,9,'JRF',5,NULL,'notstarted','2016-07-08 17:49:17',NULL,NULL,56,89,NULL,NULL),(39,8,'OTD',6,NULL,'notstarted','2016-07-10 20:40:56',NULL,NULL,423,89,NULL,NULL),(40,8,'OHS',11,NULL,'notstarted','2016-07-10 20:40:56',NULL,NULL,45,4,NULL,NULL),(41,8,'OHS',10,NULL,'notstarted','2016-07-10 20:40:56',NULL,NULL,676,898,NULL,NULL),(42,8,'OTD',1,NULL,'notstarted','2016-07-10 20:40:56',NULL,NULL,86,8,NULL,NULL),(43,8,'OTD',2,NULL,'notstarted','2016-07-10 20:40:56',NULL,NULL,534,4,NULL,NULL),(44,8,'WLS',9,NULL,'notstarted','2016-07-10 20:40:56',NULL,NULL,123,4,NULL,NULL),(45,8,'OTD',6,NULL,'notstarted','2016-07-10 20:44:59',NULL,NULL,67,4,NULL,NULL),(46,8,'OHS',11,NULL,'notstarted','2016-07-10 20:44:59',NULL,NULL,45,4,NULL,NULL),(47,8,'OHS',10,NULL,'notstarted','2016-07-10 20:44:59',NULL,NULL,456,7,NULL,NULL),(48,8,'OTD',1,NULL,'notstarted','2016-07-10 20:44:59',NULL,NULL,568,7,NULL,NULL),(49,8,'OTD',2,NULL,'notstarted','2016-07-10 20:44:59',NULL,NULL,456,7,NULL,NULL),(50,8,'WLS',9,NULL,'notstarted','2016-07-10 20:44:59',NULL,NULL,456,7,NULL,NULL),(51,8,'OTD',6,NULL,'notstarted','2016-07-10 21:39:31',NULL,NULL,45,7,NULL,NULL),(52,8,'OHS',10,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,444,45,NULL,NULL),(53,8,'OHS',11,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,5,454,NULL,NULL),(54,8,'OTD',2,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,65,45,NULL,NULL),(55,8,'OTD',1,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,6,45,NULL,NULL),(56,8,'WLS',9,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,89,45,NULL,NULL),(57,8,'BI',12,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,89,45,NULL,NULL),(58,8,'IDM',17,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,89,454,NULL,NULL),(59,8,'EDQ',13,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,8,4,NULL,NULL),(60,8,'BI',12,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,89,4,NULL,NULL),(61,8,'SOA',18,NULL,'notstarted','2016-07-10 21:39:32',NULL,NULL,89,45,NULL,NULL),(62,14,'OTD',7,NULL,'notstarted','2016-07-11 00:57:13',NULL,NULL,678,6,NULL,NULL),(63,14,'OTD',8,NULL,'notstarted','2016-07-11 00:57:13',NULL,NULL,67,6,NULL,NULL),(64,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,44,7,NULL,NULL);
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

-- Dump completed on 2016-07-11  1:11:13
