-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: results
-- ------------------------------------------------------
-- Server version	5.7.18

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
  `users` int(11) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `product` int(11) DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  `component` int(11) DEFAULT NULL,
  `shiphomenames` varchar(400) DEFAULT NULL,
  `shiphomeloc` varchar(400) DEFAULT NULL,
  `manifest` varchar(400) DEFAULT NULL,
  `comment` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stage_product_platform_shiphomes_UNIQUE` (`stage`,`product`,`platform`,`shiphomenames`),
  KEY `stage_idx` (`stage`),
  KEY `product_fk_idx` (`product`),
  KEY `platform_fk_idx` (`platform`),
  KEY `product_idx` (`product`),
  KEY `platform_idx` (`platform`),
  KEY `fk_stage_upperstack_shiphomes_component_idx` (`component`),
  KEY `stage_upper_stack_user_fk` (`users`),
  CONSTRAINT `fk_stage_upperstack_shiphomes_component` FOREIGN KEY (`component`) REFERENCES `component` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stage_upper_stack_user_fk` FOREIGN KEY (`users`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stage_upperstack_shiphomes_platform_fk` FOREIGN KEY (`platform`) REFERENCES `platform` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stage_upperstack_shiphomes_product_fk` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stageid_stage_upperstack_fk` FOREIGN KEY (`stage`) REFERENCES `stage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage_upperstack_shiphomes`
--

LOCK TABLES `stage_upperstack_shiphomes` WRITE;
/*!40000 ALTER TABLE `stage_upperstack_shiphomes` DISABLE KEYS */;
INSERT INTO `stage_upperstack_shiphomes` VALUES (3,NULL,5,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160807.1830.346.S/ascore/shiphome','manifest.integration.release:ascore-linux_x64-manifest:12.2.1.2.0-160807.1830.346',''),(4,NULL,5,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160808.0808.247.S/ascore/shiphome','manifest.integration.release:ascore-windows_x64-manifest:12.2.1.2.0-160808.0808.247',''),(5,NULL,5,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160807.0317.302.S/askernel/shiphome',NULL,''),(6,NULL,5,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160807.0317.302.S\\askernel\\shiphome',NULL,''),(7,NULL,6,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160815.1511.361.S/ascore/shiphome',NULL,''),(8,NULL,6,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160815.1203.257.S/ascore/shiphome',NULL,''),(9,NULL,6,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160814.1256.314.S/askernel/shiphome',NULL,''),(10,NULL,6,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160814.1256.314.S\\askernel\\shiphome',NULL,''),(11,NULL,7,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160821.1725.371.S/ascore/shiphome',NULL,''),(12,NULL,7,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160820.1939.326.S/askernel/shiphome',NULL,''),(13,NULL,7,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160820.1939.326.S\\askernel\\shiphome',NULL,''),(14,NULL,7,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160821.1628.267.S/ascore/shiphome',NULL,''),(15,NULL,8,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160829.0802.280.S/ascore/shiphome',NULL,''),(16,NULL,8,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160829.0629.386.S/ascore/shiphome',NULL,''),(17,NULL,8,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160828.2022.380.S/askernel/shiphome',NULL,''),(18,NULL,8,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160828.2022.380.S\\askernel\\shiphome',NULL,''),(19,NULL,9,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160904.1732.397.S\\askernel\\shiphome',NULL,''),(20,NULL,9,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160904.1732.397.S/askernel/shiphome',NULL,''),(21,NULL,9,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160905.1332.294.S/ascore/shiphome',NULL,''),(22,NULL,9,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160905.1155.398.S/ascore/shiphome',NULL,''),(23,NULL,10,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160911.1110.408.S/ascore/shiphome',NULL,''),(24,NULL,10,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160909.2209.413.S/askernel/shiphome',NULL,''),(25,NULL,10,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160910.1359.304.S/ascore/shiphome',NULL,''),(26,NULL,10,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160909.2209.413.S\\askernel\\shiphome',NULL,''),(31,NULL,12,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160925.1729.433.S/ascore/shiphome',NULL,''),(32,NULL,12,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160926.1428.335.S/ascore/shiphome',NULL,''),(33,NULL,12,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160924.1452.446.S/askernel/shiphome',NULL,''),(34,NULL,12,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160924.1452.446.S\\askernel\\shiphome',NULL,''),(35,NULL,13,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/161002.0310.445.S/ascore/shiphome',NULL,''),(37,NULL,13,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/161001.1401.461.S/askernel/shiphome',NULL,''),(38,NULL,13,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\161001.1401.461.S\\askernel\\shiphome',NULL,''),(39,NULL,14,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/161010.1635.461.S/ascore/shiphome',NULL,''),(40,NULL,14,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/161009.1204.477.S/askernel/shiphome',NULL,''),(41,NULL,14,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/161010.1402.354.S/ascore/shiphome',NULL,''),(42,NULL,14,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\161009.1204.477.S\\askernel\\shiphome',NULL,''),(43,NULL,15,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161023.2244.112.S/ascore/shiphome',NULL,''),(44,NULL,15,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161026.0813.113.S/ascore/shiphome',NULL,''),(45,NULL,15,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161019.0706.130.S\\askernel\\shiphome',NULL,''),(46,NULL,15,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161019.0706.130.S/askernel/shiphome',NULL,''),(47,NULL,16,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161030.0449.119.S/ascore/shiphome',NULL,''),(48,NULL,16,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161030.0516.119.S/ascore/shiphome',NULL,''),(49,NULL,16,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161019.0706.130.S/askernel/shiphome',NULL,''),(50,NULL,16,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161019.0706.130.S\\askernel\\shiphome',NULL,''),(51,NULL,17,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161103.1353.166.S/askernel/shiphome',NULL,''),(52,NULL,17,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161103.1353.166.S\\askernel\\shiphome',NULL,''),(53,NULL,17,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161107.0741.127.S/ascore/shiphome',NULL,''),(54,NULL,17,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161107.1303.127.S/ascore/shiphome',NULL,''),(55,NULL,18,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161113.1532.180.S/askernel/shiphome',NULL,''),(56,NULL,18,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161114.0742.138.S/ascore/shiphome',NULL,''),(57,NULL,19,1,1,NULL,'','/net/slc07phi/scratch/mseelam/stage5',NULL,''),(58,NULL,19,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161120.1338.150.S/ascore/shiphome',NULL,''),(59,NULL,19,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161118.0825.190.S/askernel/shiphome',NULL,''),(60,NULL,19,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161118.0825.190.S\\askernel\\shiphome',NULL,''),(61,NULL,20,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161127.1120.157.S/ascore/shiphome',NULL,''),(62,NULL,20,1,2,NULL,'','\\\\slcavere05-422.us.oracle.com\\wd55_fmw\\ASCORE_12.2.1.3.0_WINDOWS.X64.rdd\\161127.1137.159.S\\ascore\\shiphome',NULL,''),(63,NULL,20,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161123.2127.201.S/askernel/shiphome',NULL,''),(64,NULL,20,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161123.2127.201.S\\askernel\\shiphome',NULL,''),(65,NULL,21,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161204.0429.166.S/ascore/shiphome',NULL,''),(66,NULL,21,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161203.1237.224.S/askernel/shiphome',NULL,''),(67,NULL,21,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161203.1237.224.S\\askernel\\shiphome',NULL,''),(68,NULL,21,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161130.0237.162.S/ascore/shiphome',NULL,''),(69,NULL,22,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161211.2202.178.S/ascore/shiphome',NULL,''),(70,NULL,22,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161211.1626.186.S/ascore/shiphome',NULL,''),(71,NULL,22,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161210.2110.249.S/askernel/shiphome',NULL,''),(72,NULL,22,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161210.2110.249.S\\askernel\\shiphome',NULL,''),(73,NULL,23,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161215.2312.266.S/askernel/shiphome',NULL,''),(74,NULL,23,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161215.2312.266.S\\askernel\\shiphome',NULL,''),(75,NULL,23,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161218.1102.188.S/ascore/shiphome',NULL,''),(76,NULL,23,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161218.0227.192.S/ascore/shiphome',NULL,''),(77,NULL,24,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170102.1418.221.S/ascore/shiphome',NULL,''),(78,NULL,24,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170102.1417.225.S/ascore/shiphome',NULL,''),(79,NULL,24,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161231.1534.319.S/askernel/shiphome',NULL,''),(80,NULL,24,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161231.1534.319.S\\askernel\\shiphome',NULL,''),(81,NULL,25,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170109.0111.239.S/ascore/shiphome',NULL,''),(82,NULL,25,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170108.1743.242.S/ascore/shiphome',NULL,''),(83,NULL,25,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170107.1143.340.S\\askernel\\shiphome',NULL,''),(84,NULL,25,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170107.1143.340.S/askernel/shiphome',NULL,''),(85,NULL,26,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170116.0359.252.S/ascore/shiphome',NULL,''),(86,NULL,26,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170116.0351.253.S/ascore/shiphome',NULL,''),(87,NULL,26,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170114.0132.357.S/askernel/shiphome',NULL,''),(88,NULL,26,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170114.0132.357.S\\askernel\\shiphome',NULL,''),(89,NULL,27,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170122.1505.375.S/askernel/shiphome',NULL,''),(90,NULL,27,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170122.1505.375.S\\askernel\\shiphome',NULL,''),(91,NULL,27,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170125.1410.267.S/ascore/shiphome',NULL,''),(92,NULL,27,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170124.0923.266.S/ascore/shiphome',NULL,''),(93,NULL,28,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170130.0122.272.S/ascore/shiphome',NULL,''),(94,NULL,28,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170129.2114.270.S/ascore/shiphome',NULL,''),(95,NULL,28,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170124.1254.378.S/askernel/shiphome',NULL,''),(96,NULL,28,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170124.1254.378.S\\askernel\\shiphome',NULL,''),(97,NULL,29,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170205.2123.283.S/ascore/shiphome',NULL,''),(98,NULL,29,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170205.1310.280.S/ascore/shiphome',NULL,''),(99,NULL,29,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170204.1424.389.S/askernel/shiphome',NULL,''),(100,NULL,29,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170204.1424.389.S\\askernel\\shiphome',NULL,''),(101,NULL,30,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170208.1111/ascore/shiphome',NULL,''),(102,NULL,30,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170212.1015.294.S/ascore/shiphome',NULL,''),(103,NULL,30,3,1,NULL,'','/net/slc07phi/scratch/mseelam',NULL,''),(104,NULL,30,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170209.0931.399.S\\askernel\\shiphome',NULL,''),(108,NULL,39,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170214.1342.408.S\\askernel\\shiphome',NULL,NULL),(109,NULL,39,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170219.2134.297.S/ascore/shiphome',NULL,NULL),(110,NULL,39,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170214.1342.408.S/askernel/shiphome',NULL,NULL),(111,NULL,40,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170225.0941.428.S\\askernel\\shiphome',NULL,''),(112,NULL,40,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170225.0941.428.S/askernel/shiphome',NULL,''),(113,NULL,40,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170226.0932.309.S/ascore/shiphome',NULL,''),(114,NULL,41,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170305.2135.323.S/ascore/shiphome',NULL,NULL),(115,NULL,41,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170303.1136.443.S\\askernel\\shiphome',NULL,NULL),(116,NULL,41,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170306.0807.329.S/ascore/shiphome',NULL,NULL),(117,NULL,41,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170303.1136.443.S/askernel/shiphome',NULL,NULL),(118,NULL,43,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170312.1439.335.S/ascore/shiphome',NULL,NULL),(119,NULL,43,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170312.0154.467.S\\askernel\\shiphome',NULL,NULL),(120,NULL,43,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170312.1642.340.S/ascore/shiphome',NULL,NULL),(121,NULL,43,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170312.0154.467.S/askernel/shiphome',NULL,NULL),(122,NULL,44,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170319.1309.345.S/ascore/shiphome',NULL,NULL),(123,NULL,44,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170318.1648.480.S\\askernel\\shiphome',NULL,NULL),(124,NULL,44,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170317.0728.346.S/ascore/shiphome',NULL,NULL),(125,NULL,44,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170318.1648.480.S/askernel/shiphome',NULL,NULL),(126,NULL,45,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170326.1555.355.S/ascore/shiphome',NULL,NULL),(127,NULL,45,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170319.1940.482.S\\askernel\\shiphome',NULL,NULL),(128,NULL,45,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170326.1412.360.S/ascore/shiphome',NULL,NULL),(129,NULL,45,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170319.1940.482.S/askernel/shiphome',NULL,NULL),(130,NULL,46,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170404.1600.372.S/ascore/shiphome',NULL,NULL),(131,NULL,46,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170403.1010.523.S\\askernel\\shiphome',NULL,NULL),(132,NULL,46,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170404.1611.374.S/ascore/shiphome',NULL,NULL),(133,NULL,46,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170403.1010.523.S/askernel/shiphome',NULL,NULL),(134,NULL,47,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170411.1307.381.S/ascore/shiphome',NULL,NULL),(135,NULL,47,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170410.1022.539.S\\askernel\\shiphome',NULL,NULL),(136,NULL,47,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170411.0942.381.S/ascore/shiphome',NULL,NULL),(137,NULL,47,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170410.1022.539.S/askernel/shiphome',NULL,NULL),(138,NULL,50,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170417.0730.389.S/ascore/shiphome',NULL,NULL),(139,NULL,50,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170413.1906.549.S\\askernel\\shiphome',NULL,NULL),(140,NULL,50,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170417.0751.383.S/ascore/shiphome',NULL,NULL),(141,NULL,50,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170413.1906.549.S/askernel/shiphome',NULL,NULL),(142,NULL,51,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170423.1405.398.S/ascore/shiphome',NULL,NULL),(143,NULL,51,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170420.1212.564.S\\askernel\\shiphome',NULL,NULL),(144,NULL,51,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170424.0844.386.S/ascore/shiphome',NULL,NULL),(145,NULL,51,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170420.1212.564.S/askernel/shiphome',NULL,NULL),(146,NULL,52,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170423.1600.565.S\\askernel\\shiphome',NULL,NULL),(147,NULL,52,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170423.1600.565.S/askernel/shiphome',NULL,NULL),(148,NULL,52,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170424.0936.400.S/ascore/shiphome',NULL,NULL),(149,NULL,52,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170501.0644.389.S/ascore/shiphome',NULL,''),(150,NULL,53,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170506.2134.419.S/ascore/shiphome',NULL,''),(151,NULL,53,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170503.0449.392.S/ascore/shiphome',NULL,''),(152,NULL,53,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170420.1212.564.S/askernel/shiphome',NULL,''),(153,NULL,53,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170423.1600.565.S\\askernel\\shiphome',NULL,''),(154,NULL,54,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170515.0954.612.S\\askernel\\shiphome',NULL,''),(155,NULL,54,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170511.1340.600.S/askernel/shiphome',NULL,''),(156,NULL,54,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170514.1624.406.S/ascore/shiphome',NULL,''),(157,NULL,54,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170515.0954.612.S/ascore/shiphome',NULL,''),(158,NULL,55,1,1,NULL,NULL,'/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/170602.0956.455.S/ascore/shiphome',NULL,NULL),(159,NULL,55,3,2,NULL,NULL,'\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\170601.0756.666.S\\askernel\\shiphome',NULL,NULL),(160,NULL,55,1,2,NULL,NULL,'/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/170602.1046.437.S/ascore/shiphome',NULL,NULL),(161,NULL,55,3,1,NULL,NULL,'/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/170601.0756.666.S/askernel/shiphome',NULL,NULL);
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

-- Dump completed on 2017-06-08 11:50:09
