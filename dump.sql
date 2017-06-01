-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных twitter_eav
CREATE DATABASE IF NOT EXISTS `twitter_eav` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `twitter_eav`;

-- Дамп структуры для таблица twitter_eav.attribute
CREATE TABLE IF NOT EXISTS `attribute` (
  `attribute_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`attribute_id`),
  UNIQUE KEY `attribute_id_UNIQUE` (`attribute_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы twitter_eav.attribute: ~24 rows (приблизительно)
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` (`attribute_id`, `name`) VALUES
	(17, 'date'),
	(4, 'email'),
	(5, 'enabled'),
	(9, 'expire_date'),
	(11, 'first_name'),
	(1, 'id'),
	(24, 'is_baned'),
	(12, 'last_name'),
	(15, 'links'),
	(19, 'mini_photo'),
	(7, 'name'),
	(20, 'owner_tweet_id'),
	(18, 'owner_username'),
	(3, 'password'),
	(13, 'photo_url'),
	(10, 'p_token'),
	(14, 'status'),
	(21, 'subscribe'),
	(22, 'subscribes'),
	(16, 'text'),
	(6, 'token_expired'),
	(2, 'username'),
	(23, 'username_who_likes'),
	(8, 'v_token');
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;

-- Дамп структуры для таблица twitter_eav.attribute_value
CREATE TABLE IF NOT EXISTS `attribute_value` (
  `value_id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`value_id`),
  UNIQUE KEY `value_id_UNIQUE` (`value_id`),
  KEY `attribute_id_idx` (`attribute_id`),
  KEY `entity_id_idx` (`entity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы twitter_eav.attribute_value: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `attribute_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `attribute_value` ENABLE KEYS */;

-- Дамп структуры для таблица twitter_eav.object
CREATE TABLE IF NOT EXISTS `object` (
  `entity_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`entity_id`),
  KEY `type_id_idx` (`type_id`),
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `object_type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы twitter_eav.object: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
/*!40000 ALTER TABLE `object` ENABLE KEYS */;

-- Дамп структуры для таблица twitter_eav.object_type
CREATE TABLE IF NOT EXISTS `object_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id_UNIQUE` (`type_id`),
  UNIQUE KEY `type_name_UNIQUE` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы twitter_eav.object_type: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `object_type` DISABLE KEYS */;
INSERT INTO `object_type` (`type_id`, `type_name`) VALUES
	(8, 'comment'),
	(5, 'password_reset_token'),
	(3, 'privilege'),
	(2, 'role'),
	(9, 'subscribe'),
	(7, 'tweet'),
	(1, 'user'),
	(6, 'user_profile'),
	(4, 'verification_token');
/*!40000 ALTER TABLE `object_type` ENABLE KEYS */;

-- Дамп структуры для таблица twitter_eav.reference
CREATE TABLE IF NOT EXISTS `reference` (
  `parent_id` int(11) NOT NULL,
  `child_id` int(11) NOT NULL,
  `ref_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ref_id`),
  KEY `parent_id_idx` (`parent_id`),
  KEY `child_id_idx` (`child_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы twitter_eav.reference: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
