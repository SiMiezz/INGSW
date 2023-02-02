CREATE DATABASE  IF NOT EXISTS `ratatouille` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ratatouille`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ratatouille
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergen`
--

DROP TABLE IF EXISTS `allergen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergen` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergen`
--

LOCK TABLES `allergen` WRITE;
/*!40000 ALTER TABLE `allergen` DISABLE KEYS */;
INSERT INTO `allergen` VALUES ('clams'),('egg'),('fish'),('fruit'),('milk'),('peanuts'),('shellfish'),('wheat');
/*!40000 ALTER TABLE `allergen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `aliment` enum('food','drink') NOT NULL,
  `posizione` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`id_category`),
  KEY `menu_fk_idx` (`menu_id`),
  CONSTRAINT `menu_fk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id_menu`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'primi','food',2,1),(2,'secondi','food',3,1),(3,'contorni','food',0,1),(15,'alcolici','drink',0,1),(16,'analcolici','drink',3,1),(17,'nervine','drink',1,1),(18,'antipasti','food',1,1),(24,'drinks','drink',2,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `category_BEFORE_INSERT` BEFORE INSERT ON `category` FOR EACH ROW BEGIN

SET NEW.posizione = (SELECT AUTO_INCREMENT 
            FROM information_schema.TABLES 
            WHERE TABLE_SCHEMA = DATABASE() 
            AND TABLE_NAME = 'category');

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `table_id` int NOT NULL,
  KEY `table_fk2_idx` (`table_id`),
  CONSTRAINT `table_fk2` FOREIGN KEY (`table_id`) REFERENCES `tablerestaurant` (`id_table`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composed`
--

DROP TABLE IF EXISTS `composed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composed` (
  `order_id` int NOT NULL,
  `element_id` int NOT NULL,
  KEY `order_fk_idx` (`order_id`),
  KEY `element_fk1_idx` (`element_id`),
  CONSTRAINT `element_fk1` FOREIGN KEY (`element_id`) REFERENCES `element` (`id_element`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_fk` FOREIGN KEY (`order_id`) REFERENCES `ordine` (`id_order`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composed`
--

LOCK TABLES `composed` WRITE;
/*!40000 ALTER TABLE `composed` DISABLE KEYS */;
/*!40000 ALTER TABLE `composed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contains`
--

DROP TABLE IF EXISTS `contains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contains` (
  `element_id` int NOT NULL,
  `allergen_name` varchar(50) NOT NULL,
  KEY `allergen_fk_idx` (`allergen_name`),
  KEY `element_fk_idx` (`element_id`),
  CONSTRAINT `allergen_fk` FOREIGN KEY (`allergen_name`) REFERENCES `allergen` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `element_fk` FOREIGN KEY (`element_id`) REFERENCES `element` (`id_element`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contains`
--

LOCK TABLES `contains` WRITE;
/*!40000 ALTER TABLE `contains` DISABLE KEYS */;
INSERT INTO `contains` VALUES (20,'clams'),(20,'egg'),(20,'peanuts');
/*!40000 ALTER TABLE `contains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `element`
--

DROP TABLE IF EXISTS `element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `element` (
  `id_element` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `translatename` varchar(50) DEFAULT NULL,
  `price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `translatedescription` varchar(255) DEFAULT NULL,
  `prepackaged` tinyint NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id_element`),
  KEY `category_fk_idx` (`category_id`),
  CONSTRAINT `category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id_category`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element`
--

LOCK TABLES `element` WRITE;
/*!40000 ALTER TABLE `element` DISABLE KEYS */;
INSERT INTO `element` VALUES (1,'pasta',NULL,10,'asciutta',NULL,0,1),(2,'carne',NULL,20,'scottona',NULL,0,2),(3,'pesce',NULL,30,'spada',NULL,0,2),(4,'mozzarella',NULL,10,'bufala',NULL,1,18),(5,'prosciutto',NULL,5,'parma',NULL,1,18),(6,'vino',NULL,15,'bianco',NULL,1,15),(7,'birra',NULL,3,'peroni',NULL,1,15),(8,'fanta',NULL,2,'lemon',NULL,1,16),(9,'zucchine',NULL,3,'grigliate',NULL,0,3),(14,'anaconda','',50,'salmonata','',0,2),(15,'riso','',8,'cantonese','',0,1),(16,'melanzane','',5,'fritte','',0,3),(20,'aa','',12,'bb','',0,1);
/*!40000 ALTER TABLE `element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id_menu` int NOT NULL AUTO_INCREMENT,
  `qrcode` varchar(255) DEFAULT NULL,
  `restaurant_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_menu`),
  KEY `restaurant_fk3_idx` (`restaurant_name`),
  CONSTRAINT `restaurant_fk3` FOREIGN KEY (`restaurant_name`) REFERENCES `restaurant` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'123','rest'),(2,NULL,'risto');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `datecreate` date NOT NULL,
  `price` double NOT NULL,
  `table_id` int NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `table_fk1_idx` (`table_id`),
  CONSTRAINT `table_fk1` FOREIGN KEY (`table_id`) REFERENCES `tablerestaurant` (`id_table`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (7,'2023-01-01',2,1);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `locality` varchar(50) NOT NULL,
  `touristic` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES ('rest','descrizione ristorante rest','italia',0),('risto','pizzeria','napoli',1);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablerestaurant`
--

DROP TABLE IF EXISTS `tablerestaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tablerestaurant` (
  `id_table` int NOT NULL AUTO_INCREMENT,
  `seats` int NOT NULL,
  `free` tinyint NOT NULL DEFAULT '1',
  `restaurant_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_table`),
  KEY `restaurant_fk2_idx` (`restaurant_name`),
  CONSTRAINT `restaurant_fk2` FOREIGN KEY (`restaurant_name`) REFERENCES `restaurant` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablerestaurant`
--

LOCK TABLES `tablerestaurant` WRITE;
/*!40000 ALTER TABLE `tablerestaurant` DISABLE KEYS */;
INSERT INTO `tablerestaurant` VALUES (1,2,1,'rest'),(2,6,1,'rest'),(3,4,0,'rest'),(4,8,0,'rest'),(5,10,1,'rest'),(6,4,1,'rest'),(7,2,0,'rest'),(8,6,1,'rest');
/*!40000 ALTER TABLE `tablerestaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `job` enum('admin','waiter','supervisor','chef') NOT NULL,
  `restaurant_name` varchar(50) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `restaurant_fk1_idx` (`restaurant_name`),
  CONSTRAINT `restaurant_fk1` FOREIGN KEY (`restaurant_name`) REFERENCES `restaurant` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','ok','simi','giordi','admin','rest'),('admino','ok','dario','francese','admin','risto'),('cinque','ok','cinque','cognome','waiter','rest'),('due','ok','due','cognome','supervisor','rest'),('mario','mario','mario','francese','waiter','risto'),('quattro','ok','quattro','cognome','waiter','rest'),('renato','ok','renato','antuneri','chef','risto'),('sei','ok','sei','cognome','chef','rest'),('sette','ok','sette','cognome','chef','rest'),('tre','ok','tre','cognome','supervisor','rest'),('uno','ok','uno','cognome','admin','rest'),('wait','ok','luis','star','chef','rest'),('waiter','ok','kekko','cotugn','waiter','rest');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ratatouille'
--

--
-- Dumping routines for database 'ratatouille'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-02 11:42:58
