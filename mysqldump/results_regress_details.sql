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
  `users` int(11) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  `component` int(11) DEFAULT NULL,
  `testunit` int(11) DEFAULT NULL,
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
  KEY `results_details_stageid_fk_idx` (`stage`),
  KEY `results_details_testunit_fk_idx` (`testunit`),
  KEY `results_details_product_fk_idx` (`product`),
  KEY `fk_regress_details_component_name_idx` (`component`),
  KEY `regress_details_user_fk` (`users`),
  CONSTRAINT `fk_regress_details_component_name` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `regress_details_product_fk` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `regress_details_stageid_fk` FOREIGN KEY (`stage`) REFERENCES `stage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `regress_details_testunit_fk` FOREIGN KEY (`testunit`) REFERENCES `testunit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `regress_details_user_fk` FOREIGN KEY (`users`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regress_details`
--

LOCK TABLES `regress_details` WRITE;
/*!40000 ALTER TABLE `regress_details` DISABLE KEYS */;
INSERT INTO `regress_details` VALUES (1,NULL,5,1,4,4,NULL,'failed','2016-08-11 23:53:53',NULL,NULL,1,2,NULL,NULL),(2,NULL,5,1,4,3,NULL,'failed','2016-08-11 23:53:53',NULL,NULL,23,2,NULL,NULL),(3,NULL,5,1,4,4,NULL,'failed','2016-08-12 00:01:37',NULL,NULL,34,0,NULL,NULL),(4,NULL,5,1,4,3,18076259,'completed','2016-08-12 00:01:37','2016-08-12 12:31:40','/net/adcnas470/export/farm_txn_results/OTDQA_MAIN_GENERIC_T18076259',NULL,NULL,NULL,NULL);
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

-- Dump completed on 2016-08-12  6:03:49
