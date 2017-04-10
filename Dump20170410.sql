-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: twitter_eav
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `attribute_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`attribute_id`),
  UNIQUE KEY `attribute_id_UNIQUE` (`attribute_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (4,'email'),(5,'enabled'),(9,'expire_date'),(1,'id'),(7,'name'),(3,'password'),(10,'p_token'),(6,'token_expired'),(2,'username'),(8,'v_token');
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute_value`
--

DROP TABLE IF EXISTS `attribute_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute_value` (
  `value_id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`value_id`),
  UNIQUE KEY `value_id_UNIQUE` (`value_id`),
  KEY `attribute_id_idx` (`attribute_id`),
  KEY `entity_id_idx` (`entity_id`),
  CONSTRAINT `attribute_id` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`attribute_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `entity_id` FOREIGN KEY (`entity_id`) REFERENCES `object` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_value`
--

LOCK TABLES `attribute_value` WRITE;
/*!40000 ALTER TABLE `attribute_value` DISABLE KEYS */;
INSERT INTO `attribute_value` VALUES (1,3,18,'234'),(2,6,18,'false'),(3,4,18,'sdfsd@dsfd'),(4,5,18,'false'),(5,2,18,'Yla'),(6,3,19,'234'),(7,6,19,'false'),(8,4,19,'sdfsd@dsfd'),(9,5,19,'false'),(10,2,19,'Yla'),(11,3,20,'234'),(12,6,20,'false'),(13,4,20,'sdfsd@dsfd'),(14,5,20,'false'),(15,2,20,'Yla'),(16,3,21,'234'),(17,6,21,'false'),(18,4,21,'sdfsd@dsfd'),(19,5,21,'false'),(20,2,21,'Yla'),(21,3,22,'234'),(22,6,22,'false'),(23,4,22,'sdfsd@dsfd'),(24,5,22,'false'),(25,2,22,'Yla'),(26,3,23,'234'),(27,6,23,'false'),(28,4,23,'sdfsd@dsfd'),(29,5,23,'false'),(30,2,23,'Yla'),(31,7,34,'USER'),(32,7,35,'ADMIN'),(33,7,36,'GUEST'),(37,7,40,'READ_PRIVILEGE'),(38,7,41,'WRITE_PRIVILEGE'),(43,7,46,'ROLE_ADMIN'),(44,7,47,'ROLE_ADMIN'),(45,7,48,'ROLE_ADMIN'),(46,7,49,'ROLE_ADMIN'),(47,7,50,'ROLE_ADMIN'),(48,7,51,'ROLE_ADMIN'),(49,7,52,'ROLE_ADMIN'),(50,7,53,'ROLE_ADMIN'),(51,7,54,'ROLE_ADMIN'),(52,7,55,'ROLE_USER'),(53,7,56,'ROLE_ADMIN'),(54,7,57,'ROLE_USER'),(55,7,58,'ROLE_ADMIN'),(56,7,59,'ROLE_USER'),(57,7,60,'ROLE_ADMIN'),(58,7,61,'ROLE_USER'),(59,7,62,'ROLE_ADMIN'),(60,7,63,'ROLE_USER'),(61,7,64,'ROLE_ADMIN'),(62,7,65,'ROLE_USER'),(63,7,66,'ROLE_ADMIN'),(64,7,67,'ROLE_USER'),(65,7,68,'ROLE_ADMIN'),(66,7,69,'ROLE_USER'),(67,7,70,'ROLE_ADMIN'),(68,7,71,'ROLE_USER'),(69,7,72,'ROLE_ADMIN'),(70,7,73,'ROLE_USER'),(71,7,74,'ROLE_ADMIN'),(72,7,75,'ROLE_USER'),(73,7,76,'sdfdsfdsfdsfsdfjdsfjsdfjk'),(74,9,76,'04/10/2017 17:39:15'),(75,7,77,'sdfdsfdsfdsfsdfjdsfjsdfjk'),(76,9,77,'04/10/2017 17:39:27'),(77,7,78,'sdfdsfdsfdsfsdfjdsfjsdfjk'),(78,9,78,'04/10/2017 17:39:41'),(79,8,79,'sdfdsfdsfdsfsdfjdsfjsdfjk'),(80,9,79,'04/10/2017 18:21:13'),(81,10,80,'qwerttyikjdfbfjhlxascbjh,p;'),(82,9,80,'04/10/2017 20:57:36');
/*!40000 ALTER TABLE `attribute_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `object` (
  `entity_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`entity_id`),
  KEY `type_id_idx` (`type_id`),
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `object_type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` VALUES (18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(25,1),(26,1),(27,2),(28,2),(29,2),(31,2),(32,2),(33,2),(34,2),(35,2),(36,2),(46,2),(47,2),(48,2),(49,2),(50,2),(51,2),(52,2),(53,2),(54,2),(55,2),(56,2),(57,2),(58,2),(59,2),(60,2),(61,2),(62,2),(63,2),(64,2),(65,2),(66,2),(67,2),(68,2),(69,2),(70,2),(71,2),(72,2),(73,2),(74,2),(75,2),(30,3),(40,3),(41,3),(76,4),(77,4),(78,4),(79,4),(80,5);
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `object_type`
--

DROP TABLE IF EXISTS `object_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `object_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id_UNIQUE` (`type_id`),
  UNIQUE KEY `type_name_UNIQUE` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object_type`
--

LOCK TABLES `object_type` WRITE;
/*!40000 ALTER TABLE `object_type` DISABLE KEYS */;
INSERT INTO `object_type` VALUES (5,'password_reset_token'),(3,'privilege'),(2,'role'),(1,'user'),(4,'verification_token');
/*!40000 ALTER TABLE `object_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference`
--

DROP TABLE IF EXISTS `reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference` (
  `parent_id` int(11) NOT NULL,
  `child_id` int(11) NOT NULL,
  `ref_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ref_id`),
  KEY `parent_id_idx` (`parent_id`),
  KEY `child_id_idx` (`child_id`),
  CONSTRAINT `child_id` FOREIGN KEY (`child_id`) REFERENCES `object` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `object` (`entity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (18,27,1),(18,28,2),(19,28,3),(20,29,4),(18,30,5),(18,34,6),(54,41,7),(55,41,8),(56,41,9),(57,41,10),(58,41,11),(59,41,12),(60,41,13),(61,41,14),(62,41,15),(63,41,16),(64,40,17),(65,40,18),(66,41,19),(67,41,20),(66,40,21),(67,41,22),(68,41,23),(69,41,24),(70,41,25),(71,41,26),(72,41,27),(73,41,28),(74,40,29),(74,41,30),(75,40,31),(75,41,32),(21,74,33),(76,21,34),(77,21,35),(78,21,36),(79,21,37),(80,21,38);
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-10 18:33:35
