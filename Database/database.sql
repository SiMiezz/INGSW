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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'primi','food',2,1),(2,'secondi','food',3,1),(3,'contorni','food',1,1),(4,'vino','drink',2,1),(5,'analcolici','drink',1,1),(6,'birra','drink',0,1),(7,'antipasti','food',0,1),(8,'cocktails','drink',3,1),(9,'pizze','food',34,1),(10,'dessert','food',34,1);
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

SET NEW.posizione = (
        SELECT `AUTO_INCREMENT`
		FROM  INFORMATION_SCHEMA.TABLES
		WHERE TABLE_SCHEMA = 'ratatouille'
		AND   TABLE_NAME   = 'category'
  );

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `contains` VALUES (1,'milk'),(2,'milk'),(4,'milk'),(5,'milk'),(14,'milk'),(15,'milk'),(20,'milk'),(24,'milk'),(26,'milk'),(27,'milk'),(30,'clams'),(1,'egg'),(3,'egg'),(14,'egg'),(26,'fruit'),(1,'wheat'),(20,'wheat'),(30,'wheat'),(24,'wheat'),(25,'wheat');
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `element`
--

LOCK TABLES `element` WRITE;
/*!40000 ALTER TABLE `element` DISABLE KEYS */;
INSERT INTO `element` VALUES (1,'spaghetti alla carbonara','spaghetti carbonara',9.99,'uova, guanciale, pecorino','eggs, bacon, pecorino cheese',0,1),(2,'filetto di manzo','beef',20.99,'manzo, rucola, pomodoro, parmigiano','beef, rucola, tomato, parmesan',0,2),(3,'pollo fritto','fried chicken',14.99,'pollo, patatine fritte','chicken, fries',0,2),(4,'mozzarella','mozzarella',9.99,'mozzarella di bufala','bufala mozzarella',0,7),(5,'tagliere di salumi e formaggi','salami and cheese',14.99,'prosciutto cotto, prosciutto crudo, salame, parmigiano, formaggio stagionato','baked ham, ham, salami, parmesan,  aged cheese ',0,7),(6,'chianti','chianti',19.99,'vino rosso','red wine',1,4),(7,'prosecco','prosecco',17.99,'vino bianco frizzante','white sparkling wine',1,4),(8,'coca cola','coca cola',2.49,'bibita gassata','sparkling drink',1,5),(9,'verdure grigliate','grilled vegetables',4.99,'melanzane, peperoni, zucchine, funghi','eggplant, peppers, zucchini,, mushrooms',0,3),(14,'parmigiana di melanzane','eggplant parmigiana',15.99,'melanzana, mozzarella, pomodoro, parmigiano','eggplant, mozzarella, tomato, parmesan',0,2),(15,'risotto allo zafferano','saffron risotto',12.99,'zafferano, parmigiano, cipolla','saffron, parmesan, onion',0,1),(16,'patate al forno','baked potatoes',5.99,'patate, rosmarino','potatoes, rosemary',0,3),(20,'pennette alla norma','pennette norma',11.99,'melanzane, pomodoro, ricotta salata','eggplants, tomato, ricotta',0,1),(21,'sprite','sprite',2.49,'bibita gassata','sparkling drink',1,5),(22,'peroni','peroni',2.99,'birra chiara','blonde beer',1,6),(23,'franziskaner','franziskaner',3.49,'birra scura','dark beer',1,6),(24,'margherita','margherita',5.99,'mozzarella, pomodoro, basilico','mozzarella, tomato, basil',0,9),(25,'marinara','marinara',5.49,'pomodoro, aglio, origano','tomato, garlic, origan',0,9),(26,'cheesecake','cheesecake',6.99,'formaggio, biscotti, marmellata di frutta','cheese, biscuits, fruit jam',0,10),(27,'tiramisu','tiramisu',6.99,'formaggio, caffè, biscotti','cheese, coffee, biscuits',0,10),(28,'aperol spritz','aperol spritz',7.99,'aperol, prosecco, acqua tonica','aperol, prosecco, sparkling water',0,8),(29,'negroni','negroni',8.99,'gin, campari, vermouth','gin, campari, vermouth',0,8),(30,'spaghetti ai frutti di mare','spaghetti with clams',15.99,'frutti di mare, prezzemolo, aglio','clams, parsley, garlic',0,1);
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
  `restaurant_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_menu`),
  KEY `restaurant_fk3_idx` (`restaurant_name`),
  CONSTRAINT `restaurant_fk3` FOREIGN KEY (`restaurant_name`) REFERENCES `restaurant` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'rest'),(2,'risto');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `menu_AFTER_INSERT` AFTER INSERT ON `menu` FOR EACH ROW BEGIN

INSERT INTO category(name,aliment,menu_id) VALUES ('antipasti','food',NEW.id_menu);
INSERT INTO category(name,aliment,menu_id) VALUES ('primi','food',NEW.id_menu);
INSERT INTO category(name,aliment,menu_id) VALUES ('secondi','food',NEW.id_menu);

INSERT INTO category(name,aliment,menu_id) VALUES ('analcolici','drink',NEW.id_menu);
INSERT INTO category(name,aliment,menu_id) VALUES ('alcolici','drink',NEW.id_menu);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (20,'2018-08-09',123,2);
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
INSERT INTO `restaurant` VALUES ('rest','descrizione ristorante rest','italia',1),('risto','pizzeria','napoli',1);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `restaurant_AFTER_INSERT` AFTER INSERT ON `restaurant` FOR EACH ROW BEGIN

INSERT INTO menu(restaurant_name) VALUES(NEW.name);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  CONSTRAINT `restaurant_fk2` FOREIGN KEY (`restaurant_name`) REFERENCES `restaurant` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tablerestaurant_chk_1` CHECK (((`seats` = 2) or (`seats` = 4) or (`seats` = 6) or (`seats` = 8) or (`seats` = 10)))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablerestaurant`
--

LOCK TABLES `tablerestaurant` WRITE;
/*!40000 ALTER TABLE `tablerestaurant` DISABLE KEYS */;
INSERT INTO `tablerestaurant` VALUES (1,2,1,'rest'),(2,6,0,'rest'),(3,4,1,'rest'),(4,8,0,'rest'),(5,10,1,'rest'),(6,4,1,'rest'),(7,2,0,'rest'),(8,6,1,'rest');
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
INSERT INTO `user` VALUES ('admin','ok','admin','admin','admin','rest'),('chef','ok','chef','chef','chef','rest'),('gianmarcoaddati','ok','gian marco','addati','admin','rest'),('simonegiordano','ok','simone','giordano','admin','rest'),('supervisor','ok','supervisor','supervisor','supervisor','rest'),('waiter','ok','waiter','waiter','waiter','rest');
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

-- Dump completed on 2023-02-12 12:57:14
