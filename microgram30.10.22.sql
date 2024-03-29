CREATE DATABASE  IF NOT EXISTS `microgram` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `microgram`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: microgram
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(100) NOT NULL,
  `authority` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('dd@mail.ru','ROLE_USER'),('f444@mail.ru','ROLE_USER'),('ff@mail.ru','ROLE_USER'),('ratata@ret.com','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comm_id` int NOT NULL AUTO_INCREMENT,
  `pub_id` int NOT NULL,
  `comm_text` varchar(2000) NOT NULL,
  `date_time` datetime DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`comm_id`),
  KEY `pub_id` (`pub_id`),
  KEY `foreign_key_user_fk` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`pub_id`) REFERENCES `publications` (`pub_id`),
  CONSTRAINT `foreign_key_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,'hey','2004-06-18 08:16:00',1),(2,2,'Hello','2004-06-18 08:30:00',2),(3,1,'sdfg','2022-10-29 18:16:35',1),(4,1,'ffff','2022-10-29 18:18:27',1),(5,1,'vb','2022-10-29 18:26:03',1),(6,1,'gfdgdgd','2022-10-29 18:32:13',1),(7,1,'123','2022-10-29 18:33:33',1),(8,1,'422','2022-10-29 18:38:48',1),(9,1,'666','2022-10-29 18:40:41',1),(10,1,'wer','2022-10-29 18:49:34',1),(11,85,'d','2022-10-29 19:07:07',1),(12,85,'d','2022-10-29 19:07:20',1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `pub_id` int NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `user_id` (`user_id`),
  KEY `pub_id` (`pub_id`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`pub_id`) REFERENCES `publications` (`pub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,1,3,'2004-06-18 08:16:00'),(2,1,6,'2004-06-18 08:25:00'),(3,1,1,'2022-10-04 17:43:57');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publications`
--

DROP TABLE IF EXISTS `publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publications` (
  `pub_id` int NOT NULL AUTO_INCREMENT,
  `img_link` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `user_idfk` int NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`pub_id`),
  KEY `user_idfk` (`user_idfk`),
  CONSTRAINT `publications_ibfk_1` FOREIGN KEY (`user_idfk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publications`
--

LOCK TABLES `publications` WRITE;
/*!40000 ALTER TABLE `publications` DISABLE KEYS */;
INSERT INTO `publications` VALUES (1,'images/1.jpg','hey',1,'2000-02-23 14:47:00'),(2,'images/2.jpg','hello',1,'2002-03-13 10:17:00'),(3,'images/3.jpg','like me',2,'2001-02-15 11:16:00'),(4,'images/4.jpg','hey everyone',2,'2001-03-16 12:19:00'),(5,'images/5.jpg','bad',2,'2001-02-15 11:16:00'),(6,'images/6.jpg','good',3,'2005-02-15 11:16:00'),(85,'images/WarRobots_sample.jpg','ddd',1,'2022-10-29 19:07:01');
/*!40000 ALTER TABLE `publications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `sub_id` int NOT NULL AUTO_INCREMENT,
  `who_subscribes_id` int NOT NULL,
  `who_is_subscribed_to_id` int NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`sub_id`),
  KEY `who_subscribes_id` (`who_subscribes_id`),
  KEY `who_is_subscribed_to_id` (`who_is_subscribed_to_id`),
  CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`who_subscribes_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `subscriptions_ibfk_2` FOREIGN KEY (`who_is_subscribed_to_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (1,1,2,'2004-06-18 08:16:00'),(2,1,3,'2004-06-18 08:20:00');
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user1','fff','ff@mail.ru','$2a$10$tQ/IIoPB6a3VKVil/H2ROuuR95nCSmv6IoAHG83bWl7iHZTdeWgWG',1),(2,'user2','ttt','dd@mail.ru','$2a$10$WeHWVmek24qRmadbu3mqcewMhP8KX6x8foapSaPVzFzu4Mq6hQ2KW',1),(3,'user3','err','f444@mail.ru','$2a$10$ypv05FABsMbJ33fqtrKX2uJQDQk3txu17MAFr4DvsZo8UE5dHSEw6',1),(4,'tester','ratata','ratata@ret.com','$2a$10$xMJMSSCYYufu6/rijUybTe.HdYasJSBSbAXI9f8cYHqeEC/2nyihe',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'microgram'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-30 20:52:21
