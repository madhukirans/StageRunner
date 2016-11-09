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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage_upperstack_shiphomes`
--

LOCK TABLES `stage_upperstack_shiphomes` WRITE;
/*!40000 ALTER TABLE `stage_upperstack_shiphomes` DISABLE KEYS */;
INSERT INTO `stage_upperstack_shiphomes` VALUES (3,NULL,5,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160807.1830.346.S/ascore/shiphome','manifest.integration.release:ascore-linux_x64-manifest:12.2.1.2.0-160807.1830.346',''),(4,NULL,5,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160808.0808.247.S/ascore/shiphome','manifest.integration.release:ascore-windows_x64-manifest:12.2.1.2.0-160808.0808.247',''),(5,NULL,5,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160807.0317.302.S/askernel/shiphome',NULL,''),(6,NULL,5,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160807.0317.302.S\\askernel\\shiphome',NULL,''),(7,NULL,6,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160815.1511.361.S/ascore/shiphome',NULL,''),(8,NULL,6,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160815.1203.257.S/ascore/shiphome',NULL,''),(9,NULL,6,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160814.1256.314.S/askernel/shiphome',NULL,''),(10,NULL,6,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160814.1256.314.S\\askernel\\shiphome',NULL,''),(11,NULL,7,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160821.1725.371.S/ascore/shiphome',NULL,''),(12,NULL,7,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160820.1939.326.S/askernel/shiphome',NULL,''),(13,NULL,7,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160820.1939.326.S\\askernel\\shiphome',NULL,''),(14,NULL,7,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160821.1628.267.S/ascore/shiphome',NULL,''),(15,NULL,8,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160829.0802.280.S/ascore/shiphome',NULL,''),(16,NULL,8,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160829.0629.386.S/ascore/shiphome',NULL,''),(17,NULL,8,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160828.2022.380.S/askernel/shiphome',NULL,''),(18,NULL,8,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160828.2022.380.S\\askernel\\shiphome',NULL,''),(19,NULL,9,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160904.1732.397.S\\askernel\\shiphome',NULL,''),(20,NULL,9,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160904.1732.397.S/askernel/shiphome',NULL,''),(21,NULL,9,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160905.1332.294.S/ascore/shiphome',NULL,''),(22,NULL,9,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160905.1155.398.S/ascore/shiphome',NULL,''),(23,NULL,10,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160911.1110.408.S/ascore/shiphome',NULL,''),(24,NULL,10,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160909.2209.413.S/askernel/shiphome',NULL,''),(25,NULL,10,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160910.1359.304.S/ascore/shiphome',NULL,''),(26,NULL,10,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160909.2209.413.S\\askernel\\shiphome',NULL,''),(27,NULL,11,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160918.1809.319.S/ascore/shiphome',NULL,''),(28,NULL,11,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160918.1727.422.S/ascore/shiphome',NULL,''),(29,NULL,11,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160918.0545.432.S/askernel/shiphome',NULL,''),(30,NULL,11,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160918.0545.432.S\\askernel\\shiphome',NULL,''),(31,NULL,12,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/160925.1729.433.S/ascore/shiphome',NULL,''),(32,NULL,12,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/160926.1428.335.S/ascore/shiphome',NULL,''),(33,NULL,12,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/160924.1452.446.S/askernel/shiphome',NULL,''),(34,NULL,12,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\160924.1452.446.S\\askernel\\shiphome',NULL,''),(35,NULL,13,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/161002.0310.445.S/ascore/shiphome',NULL,''),(37,NULL,13,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/161001.1401.461.S/askernel/shiphome',NULL,''),(38,NULL,13,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\161001.1401.461.S\\askernel\\shiphome',NULL,''),(39,NULL,14,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.2.0_LINUX.X64.rdd/161010.1635.461.S/ascore/shiphome',NULL,''),(40,NULL,14,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.2.0_GENERIC.rdd/161009.1204.477.S/askernel/shiphome',NULL,''),(41,NULL,14,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.2.0_WINDOWS.X64.rdd/161010.1402.354.S/ascore/shiphome',NULL,''),(42,NULL,14,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.2.0_GENERIC.rdd\\161009.1204.477.S\\askernel\\shiphome',NULL,''),(43,NULL,15,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161023.2244.112.S/ascore/shiphome',NULL,''),(44,NULL,15,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161026.0813.113.S/ascore/shiphome',NULL,''),(45,NULL,15,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161019.0706.130.S\\askernel\\shiphome',NULL,''),(46,NULL,15,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161019.0706.130.S/askernel/shiphome',NULL,''),(47,NULL,16,1,1,NULL,'','/ade_autofs/gd12_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161030.0449.119.S/ascore/shiphome',NULL,''),(48,NULL,16,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161030.0516.119.S/ascore/shiphome',NULL,''),(49,NULL,16,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161019.0706.130.S/askernel/shiphome',NULL,''),(50,NULL,16,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161019.0706.130.S\\askernel\\shiphome',NULL,''),(51,NULL,17,3,1,NULL,'','/ade_autofs/gd17_fmw/ASKERNEL_12.2.1.3.0_GENERIC.rdd/161103.1353.166.S/askernel/shiphome',NULL,''),(52,NULL,17,3,2,NULL,'','\\\\adcavere03-cifs.us.oracle.com\\gd17_fmw\\ASKERNEL_12.2.1.3.0_GENERIC.rdd\\161103.1353.166.S\\askernel\\shiphome',NULL,''),(53,NULL,17,1,1,NULL,'','/ade_autofs/gd59_fmw/ASCORE_12.2.1.3.0_LINUX.X64.rdd/161107.0741.127.S/ascore/shiphome',NULL,''),(54,NULL,17,1,2,NULL,'','/ade_autofs/wd55_fmw/ASCORE_12.2.1.3.0_WINDOWS.X64.rdd/161107.1303.127.S/ascore/shiphome',NULL,'');
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

-- Dump completed on 2016-11-09 11:09:02
