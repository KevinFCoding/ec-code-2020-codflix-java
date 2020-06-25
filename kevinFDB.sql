-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: codflix
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(2,'Horreur'),(3,'Science-Fiction'),(4,'Thriller'),(5,'Fantaisy');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `media_id` int NOT NULL,
  `start_date` datetime NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `watch_duration` int NOT NULL DEFAULT '0' COMMENT 'in seconds',
  PRIMARY KEY (`id`),
  KEY `history_user_id_fk_media_id` (`user_id`),
  KEY `history_media_id_fk_media_id` (`media_id`),
  CONSTRAINT `history_media_id_fk_media_id` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_user_id_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,1,3,'2020-06-22 00:00:00',NULL,3600),(2,2,3,'2020-01-01 00:00:00','2020-01-01 00:00:00',3600),(3,3,1,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(4,1,1,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(5,4,1,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(6,1,4,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(7,1,1,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(8,1,1,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(9,1,3,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(10,1,4,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(11,1,3,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(12,1,3,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(13,1,4,'2020-06-25 00:00:00','2020-06-25 00:00:00',0),(14,1,4,'2020-06-25 00:00:00','2020-06-25 00:00:00',0);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `genre_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `release_date` date NOT NULL,
  `time_media` int NOT NULL,
  `summary` longtext NOT NULL,
  `trailer_url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `media_genre_id_fk_genre_id` (`genre_id`) USING BTREE,
  CONSTRAINT `media_genre_id_b1257088_fk_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,3,'Star Wars : A New Hope','movie','released','1977-10-19',127,'A young boy discover the way of the Force to destroy a great Evil','https://www.youtube.com/embed/1g3_CFmnU7k'),(2,3,'Star Wars : Empire Strike Back','movie','released','1980-08-20',127,'The Empire win this time','https://www.youtube.com/embed/JNwNXF9Y6kY'),(3,3,'Star Wars : Return of the Jedi','movie','released','1983-10-19',127,'After the defeat of Luke, an operation is set in place to save Han Solo, two droids go back to a desert planet','https://www.youtube.com/embed/5UfA_aKBGMc'),(4,3,'Star Wars : The Clone Wars','serie','released','2008-10-03',0,'Adventure take places during the Clone Wars, follow Anakin Skywalker and his friend for an epic adventure','https://www.youtube.com/embed/ZLW2jkd6E7g'),(5,5,'Game of Thrones','serie','finished','2011-04-17',0,'Winter is Coming','https://www.youtube.com/embed/rlR4PJn8b8I');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_episodes`
--

DROP TABLE IF EXISTS `media_episodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media_episodes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_media_serie` int NOT NULL,
  `episode_title` varchar(100) DEFAULT NULL,
  `summary` longtext NOT NULL,
  `release_date` datetime DEFAULT NULL,
  `time_episode` int NOT NULL,
  `episode_number` int NOT NULL,
  `season_number` int NOT NULL,
  `episode_url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_season_fk_media_id` (`id_media_serie`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_episodes`
--

LOCK TABLES `media_episodes` WRITE;
/*!40000 ALTER TABLE `media_episodes` DISABLE KEYS */;
INSERT INTO `media_episodes` VALUES (1,4,'Ambush','Pilot of the Clone Wars - series','2008-10-03 00:00:00',0,1,1,'https://www.youtube.com/embed/Mr9optdDQow'),(2,4,'Rising Malevolance','2nd episode of the Clone Wars - series','2008-10-07 00:00:00',0,2,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(3,5,'Winter is Coming','1st episode of GOT - series','2011-04-17 00:00:00',0,1,1,'https://www.youtube.com/embed/522l0YE9hRQ'),(4,5,'King\'s Road','2nd episode of GOT - series','2011-04-24 00:00:00',0,2,1,'https://www.youtube.com/embed/gcTkNV5Vg1E'),(5,5,'End of Innocence','Worst episode of GoT - series','2013-04-24 00:00:00',0,9,3,'https://www.youtube.com/embed/vU8eL2CjzHw'),(6,4,' E 3 CW Season 1','Some war and stuff','2008-10-10 00:00:00',22,3,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(7,4,' E 4 CW Season 1','Some war and stuff','2008-10-12 00:00:00',22,4,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(8,4,' E 5 CW Season 1','Some war and stuff','2008-10-14 00:00:00',22,5,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(9,4,' E 6 CW Season 1','Some war and stuff','2008-10-16 00:00:00',22,6,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(10,4,' E 7 CW Season 1','Some war and stuff','2008-10-17 00:00:00',22,7,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(11,4,' E 8 CW Season 1','Some war and stuff','2008-10-20 00:00:00',22,8,1,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(12,4,' E 1 CW Season 2','Some war and stuff','2008-10-22 00:00:00',22,1,2,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(13,4,' E 2 CW Season 2','Some war and stuff','2008-10-23 00:00:00',22,2,2,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(14,4,' E 3 CW Season 2','Some war and stuff','2008-10-24 00:00:00',22,3,2,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(15,4,' E 4 CW Season 2','Some war and stuff','2008-10-25 00:00:00',22,4,2,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(16,4,' E 5 CW Season 2','Some war and stuff','2008-11-10 00:00:00',22,5,2,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(17,4,' E 1 CW Season 3','Some war and stuff','2008-12-10 00:00:00',22,1,3,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(18,4,' E 2 CW Season 3','Some war and stuff','2009-02-10 00:00:00',22,2,3,'https://www.youtube.com/embed/Oh3Uex8jF4k'),(19,4,' E 3 CW Season 3','Some war and stuff','2009-10-10 00:00:00',22,3,3,'https://www.youtube.com/embed/Oh3Uex8jF4k');
/*!40000 ALTER TABLE `media_episodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(254) NOT NULL,
  `password` varchar(80) NOT NULL,
  `verified` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'coding@gmail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1),(2,'kvnfagot@gmail.com','password',1),(3,'test@gmail.com','password',0),(4,'bordel@gmail.com','qsd',0),(5,'testosdosdo@gmail.com','azezae',0),(6,'echos@gmail.com','db9831b53a8574d33f3d7ce6820598c67224687dbe57cbbc10b6070e5aa57744',0),(7,'azer@gmail.com','db9831b53a8574d33f3d7ce6820598c67224687dbe57cbbc10b6070e5aa57744',0),(8,'azeaze@gmail.com','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-25 20:20:47
