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
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regress_details`
--

LOCK TABLES `regress_details` WRITE;
/*!40000 ALTER TABLE `regress_details` DISABLE KEYS */;
INSERT INTO `regress_details` VALUES (2,8,'OTD',2,17914978,'completed',NULL,'2016-07-26 13:18:56','/net/adcnas470/export/farm_txn_results/FMWTOOLS_12.2.1.2.0_GENERIC_T17914978',2,3,'uploaded','/tmp/sr/work/12.2.1.1.0/1/OTD/OTD_HA/LINUX.X64_OTD12.2.1.1.0_stage1_OTD_HA_17914978.xml'),(98,9,'OTD',2,17914972,'completed','2016-07-22 12:08:01','2016-07-26 13:18:56','/net/adcnas470/export/farm_txn_results/OTDQA_MAIN_GENERIC_T17914972',NULL,NULL,'uploaded','/tmp/sr/work/12.2.1.1.0/22/OTD/OTD_HA/LINUX.X64_OTD12.2.1.1.0_stage22_OTD_HA_17914972.xml');
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

-- Dump completed on 2016-07-27  4:29:51
