CREATE DATABASE  IF NOT EXISTS `results` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `results`;
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
-- Table structure for table `shiphome_names`
--

DROP TABLE IF EXISTS `shiphome_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shiphome_names` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users` int(11) DEFAULT NULL,
  `releaseid` int(11) DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  `component` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shiphome_names_product_fk_idx` (`product`),
  KEY `shiphome_names_platform_fk_idx` (`platform`),
  KEY `shiphome_names_release_fk_idx` (`releaseid`),
  KEY `fk_shiphome_names_component_name_idx` (`component`),
  KEY `shiphome_names_user_fk` (`users`),
  CONSTRAINT `fk_shiphome_names_component_name` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shiphome_names_platform_fk` FOREIGN KEY (`platform`) REFERENCES `platform` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shiphome_names_product_fk` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shiphome_names_release_fk` FOREIGN KEY (`releaseid`) REFERENCES `releases` (`releaseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shiphome_names_user_fk` FOREIGN KEY (`users`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shiphome_names`
--

LOCK TABLES `shiphome_names` WRITE;
/*!40000 ALTER TABLE `shiphome_names` DISABLE KEYS */;
INSERT INTO `shiphome_names` VALUES (4,NULL,1,1,4,1,'fmw_12.2.1.2.0_otd_linux64.bin'),(5,NULL,1,1,5,1,'fmw_12.2.1.2.0_ohs_linux64.bin'),(6,NULL,1,1,6,3,'fmw_12.2.1.2.0_wls_generic.jar'),(7,NULL,1,1,10,3,'fmw_12.2.1.2.0_wls_quick_generic.jar'),(8,NULL,1,1,11,3,'fmw_12.2.1.2.0_wls_supplemental_quick_generic.jar'),(9,NULL,1,1,7,3,'fmw_12.2.1.2.0_infrastructure_generic.jar'),(10,NULL,1,2,4,1,'setup_fmw_12.2.1.2.0_otd_win64.exe'),(11,NULL,1,2,5,1,'setup_fmw_12.2.1.2.0_ohs_win64.exe'),(12,NULL,1,2,6,3,'fmw_12.2.1.2.0_wls_generic.jar'),(13,NULL,1,2,10,3,'fmw_12.2.1.2.0_wls_quick_generic.jar'),(14,NULL,1,2,11,3,'fmw_12.2.1.2.0_wls_supplemental_quick_generic.jar'),(15,NULL,1,2,7,3,'fmw_12.2.1.2.0_infrastructure_generic.jar'),(16,NULL,2,1,4,1,'fmw_12.2.1.3.0_otd_linux64.bin'),(17,NULL,2,1,5,1,'fmw_12.2.1.3.0_ohs_linux64.bin'),(18,NULL,2,1,6,3,'fmw_12.2.1.3.0_wls_generic.jar'),(19,NULL,2,1,10,3,'fmw_12.2.1.3.0_wls_quick_generic.jar'),(20,NULL,2,1,11,3,'fmw_12.2.1.3.0_wls_supplemental_quick_generic.jar'),(21,NULL,2,1,7,3,'fmw_12.2.1.3.0_infrastructure_generic.jar'),(22,NULL,2,2,4,1,'setup_fmw_12.2.1.3.0_otd_win64.exe'),(23,NULL,2,2,5,1,'setup_fmw_12.2.1.3.0_ohs_win64.exe'),(24,NULL,2,2,6,3,'fmw_12.2.1.3.0_wls_generic.jar'),(25,NULL,2,2,10,3,'fmw_12.2.1.3.0_wls_quick_generic.jar'),(26,NULL,2,2,11,3,'fmw_12.2.1.3.0_wls_supplemental_quick_generic.jar'),(27,NULL,2,2,7,3,'fmw_12.2.1.3.0_infrastructure_generic.jar');
/*!40000 ALTER TABLE `shiphome_names` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-09 11:09:02
