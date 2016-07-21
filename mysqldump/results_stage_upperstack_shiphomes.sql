CREATE DATABASE  IF NOT EXISTS `results` /*!40100 DEFAULT CHARACTER SET latin1 */;
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
-- Table structure for table `stage_upperstack_shiphomes`
--

DROP TABLE IF EXISTS `stage_upperstack_shiphomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage_upperstack_shiphomes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageid` int(11) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `shiphomenames` varchar(400) DEFAULT NULL,
  `shiphomeloc` varchar(400) DEFAULT NULL,
  `manifest` varchar(400) DEFAULT NULL,
  `comment` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stage_idx` (`stageid`),
  KEY `product_fk_idx` (`product`),
  KEY `platform_fk_idx` (`platform`),
  KEY `product_idx` (`product`),
  KEY `platform_idx` (`platform`),
  CONSTRAINT `stage_upperstack_shiphomes_platform_fk` FOREIGN KEY (`platform`) REFERENCES `platform` (`NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stage_upperstack_shiphomes_product_fk` FOREIGN KEY (`product`) REFERENCES `products` (`product_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stageid_stage_upperstack_fk` FOREIGN KEY (`stageid`) REFERENCES `stage` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage_upperstack_shiphomes`
--

LOCK TABLES `stage_upperstack_shiphomes` WRITE;
/*!40000 ALTER TABLE `stage_upperstack_shiphomes` DISABLE KEYS */;
INSERT INTO `stage_upperstack_shiphomes` VALUES (1,8,'OTD','WINDOWS.X64','otd.exe','/a/b/c/','a.pom','asdf'),(3,8,'OHS','LINUX.X64','ohs.bin1111','/a/b/c1111','b.pom1111','asdf'),(4,8,'OTD','LINUX.X64','otd.bin1','/a/b/cc1','a.pom1','asdf'),(5,8,'WLS','GENERIC','wls.jar','/a/b/c1','w.pom','sadfsadf'),(6,9,'OTD','LINUX.X64','otd.bin','/a/a/b','otd.pom','asdf'),(7,9,'JRF','GENERIC','asdf','asdf','asdf',''),(8,14,'OTD','LINUX.X64','fmw_12.2.1.2.0_otd_linux64.bin','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160626.1913.185.S/ascore/shiphome/fmw_12.2.1.2.0_otd_linux64.bin','manifest.integration.release:ascore-linux_x64-manifest:12.2.1.2.0-160626.1913.185:pom',''),(9,14,'OTD','WINDOWS.X64','fmw_12.2.1.2.0_otd_linux64.exe','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160627.1151.124.S/ascore/shiphome/setup_fmw_12.2.1.2.0_otd_win64.exe','manifest.integration.release:ascore-windows_x64-manifest:12.2.1.2.0-160627.1151.124:pom',''),(10,14,'OHS','LINUX.X64','sd','asdf1','asdf1',''),(11,8,'BI','GENERIC','wls.zip','/a/b/c','b.pom',''),(12,8,'WebCenter','LINUX.X64','a.zip','/a/b/c','c.pom',''),(13,8,'IDM','LINUX.X64','idm.zip','/a/b/c','c.pom',''),(14,8,'EDQ','GENERIC','wls.zip','/a/b/c','c.pom',''),(15,8,'BI','LINUX.X64','a.xip','/a/b/c',NULL,''),(16,8,'BI','WINDOWS.X64','wls.zip','/a/b/c','b.pom',''),(17,8,'BI','GENERIC','ac','b',NULL,''),(19,NULL,'EDQ','LINUX.X64','a','c','c',''),(20,8,'SOA','GENERIC','soa.zip','/a/b/c','a',''),(21,15,'OTD','LINUX.X64','otd.bin','/net/slc09iyd/scratch/',NULL,''),(22,15,'OTD','WINDOWS.X64','otd.exe','\\\\slc09iyd\\scratch',NULL,''),(23,15,'OHS','LINUX.X64','ohs.bin','/net/slc09iyd/scratch/',NULL,''),(24,15,'OHS','WINDOWS.X64','ohs.exe','\\\\slc09iyd\\scratch',NULL,'');
/*!40000 ALTER TABLE `stage_upperstack_shiphomes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-21  7:41:59
