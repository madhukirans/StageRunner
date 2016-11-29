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
-- Table structure for table `stage`
--

DROP TABLE IF EXISTS `stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users` int(11) DEFAULT NULL,
  `stage_name` varchar(45) NOT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `releaseid` int(11) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stage_userid` (`users`),
  KEY `fk_stage_releaseid_idx` (`releaseid`),
  CONSTRAINT `fk_stage_releaseid` FOREIGN KEY (`releaseid`) REFERENCES `releases` (`releaseid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_stage_userid` FOREIGN KEY (`users`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stage`
--

LOCK TABLES `stage` WRITE;
/*!40000 ALTER TABLE `stage` DISABLE KEYS */;
INSERT INTO `stage` VALUES (5,NULL,'1','stage 1',1,'0000-00-00 00:00:00'),(6,NULL,'12','Stage12',1,'0000-00-00 00:00:00'),(7,NULL,'13','Stage13',1,'0000-00-00 00:00:00'),(8,NULL,'14','stage14',1,'0000-00-00 00:00:00'),(9,NULL,'15','stageRc1',1,'0000-00-00 00:00:00'),(10,NULL,'16','stage rc2',1,'0000-00-00 00:00:00'),(11,NULL,'17','Stagerc3',1,'0000-00-00 00:00:00'),(12,NULL,'18','Stagerc4',1,'0000-00-00 00:00:00'),(13,NULL,'19','Stage rc5',1,'0000-00-00 00:00:00'),(14,NULL,'20','Stage rc6',1,'0000-00-00 00:00:00'),(15,NULL,'1','Stage1',2,'0000-00-00 00:00:00'),(16,NULL,'2','stage 2',2,'0000-00-00 00:00:00'),(17,NULL,'3','stage 3',2,'0000-00-00 00:00:00'),(18,NULL,'4','Stage 4',2,'0000-00-00 00:00:00'),(19,NULL,'5','Stage 5',2,'2016-11-21 22:54:14'),(20,NULL,'6','Stage 6',2,'2016-11-28 21:35:11');
/*!40000 ALTER TABLE `stage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29  4:43:26
