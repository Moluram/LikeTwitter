CREATE DATABASE  IF NOT EXISTS `twitter_eav` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `twitter_eav`;
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (17,'date'),(4,'email'),(5,'enabled'),(9,'expire_date'),(11,'first_name'),(1,'id'),(12,'last_name'),(15,'links'),(19,'mini_photo'),(7,'name'),(20,'owner_tweet_id'),(18,'owner_username'),(3,'password'),(13,'photo_url'),(10,'p_token'),(14,'status'),(16,'text'),(6,'token_expired'),(2,'username'),(8,'v_token');
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
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_value`
--

LOCK TABLES `attribute_value` WRITE;
/*!40000 ALTER TABLE `attribute_value` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object_type`
--

LOCK TABLES `object_type` WRITE;
/*!40000 ALTER TABLE `object_type` DISABLE KEYS */;
INSERT INTO `object_type` VALUES (8,'comment'),(5,'password_reset_token'),(3,'privilege'),(2,'role'),(7,'tweet'),(1,'user'),(6,'user_profile'),(4,'verification_token');
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
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
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

-- Dump completed on 2017-04-27 23:45:52
