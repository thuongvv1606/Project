-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: g5_flmm
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
-- Table structure for table `assessment`
--

DROP TABLE IF EXISTS `assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment` (
  `assessment_id` int NOT NULL AUTO_INCREMENT,
  `syllabus_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`assessment_id`),
  KEY `syllabus_id` (`syllabus_id`),
  KEY `category_id` (`category_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `assessment_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`),
  CONSTRAINT `assessment_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `assessment_category` (`category_id`),
  CONSTRAINT `assessment_ibfk_3` FOREIGN KEY (`type_id`) REFERENCES `assessment_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment`
--

LOCK TABLES `assessment` WRITE;
/*!40000 ALTER TABLE `assessment` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assessment_category`
--

DROP TABLE IF EXISTS `assessment_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_category`
--

LOCK TABLES `assessment_category` WRITE;
/*!40000 ALTER TABLE `assessment_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assessment_lo`
--

DROP TABLE IF EXISTS `assessment_lo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment_lo` (
  `assessment_id` int DEFAULT NULL,
  `clo_id` int DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  KEY `assessment_id` (`assessment_id`),
  KEY `clo_id` (`clo_id`),
  CONSTRAINT `assessment_lo_ibfk_1` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`assessment_id`),
  CONSTRAINT `assessment_lo_ibfk_2` FOREIGN KEY (`clo_id`) REFERENCES `clo` (`clo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_lo`
--

LOCK TABLES `assessment_lo` WRITE;
/*!40000 ALTER TABLE `assessment_lo` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment_lo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assessment_type`
--

DROP TABLE IF EXISTS `assessment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_type`
--

LOCK TABLES `assessment_type` WRITE;
/*!40000 ALTER TABLE `assessment_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avatar`
--

DROP TABLE IF EXISTS `avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avatar` (
  `avatar_id` int NOT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `file_description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`avatar_id`),
  KEY `fk_avatar_userId` (`user_id`),
  CONSTRAINT `fk_avatar_userId` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avatar`
--

LOCK TABLES `avatar` WRITE;
/*!40000 ALTER TABLE `avatar` DISABLE KEYS */;
/*!40000 ALTER TABLE `avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clo`
--

DROP TABLE IF EXISTS `clo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clo` (
  `clo_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `syllabus_id` int DEFAULT NULL,
  `parent_lo_id` int DEFAULT NULL,
  `description` text,
  `status` tinyint DEFAULT '1',
  PRIMARY KEY (`clo_id`),
  KEY `syllabus_id` (`syllabus_id`),
  KEY `parent_lo_id` (`parent_lo_id`),
  CONSTRAINT `clo_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`),
  CONSTRAINT `clo_ibfk_2` FOREIGN KEY (`parent_lo_id`) REFERENCES `clo` (`clo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clo`
--

LOCK TABLES `clo` WRITE;
/*!40000 ALTER TABLE `clo` DISABLE KEYS */;
INSERT INTO `clo` VALUES (1,'Dung deptrai',1,NULL,'2',1),(2,'abc',1,NULL,'1',1),(3,'dung',1,NULL,'okla',1);
/*!40000 ALTER TABLE `clo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curriculum` (
  `curriculum_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `decision_id` int DEFAULT NULL,
  `total_credit` int DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  `is_active` tinyint DEFAULT '1',
  PRIMARY KEY (`curriculum_id`),
  KEY `decision_id` (`decision_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `curriculum_ibfk_1` FOREIGN KEY (`decision_id`) REFERENCES `decision` (`decision_id`),
  CONSTRAINT `curriculum_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (1,'BIT_GD_K15DK16A','Bachelor Program of Information Technology, Digital Art & Design Major (Chương trình cử nhân ngành C','1. Training Objectives General objective: Training Bachelor of Information Technology, Digital Art & Design specialty with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implem...',1,149,2,1),(2,'BIT_GD_K16B	','Bachelor Program of Information Technology, Digital Art & Design Major (Chương trình cử nhân ngành C','1. Training Objectives General objective: Training Bachelor of Information Technology, Digital Art & Design specialty with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implem...',2,146,2,1),(3,'BIT_GD_K16C','Bachelor Program of Information Technology, Digital Art & Design Major (Chương trình cử nhân ngành C','1. Training Objectives General objective: Training Bachelor of Information Technology, Digital Art & Design specialty with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implem..',3,146,2,1),(4,'BIT_SE_K16D_K17A','Bachelor Program of Information Technology, Software Engineering Major (Chương trình cử nhân ngành C','1. Training Objectives 1.1 General objective: Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organ...',4,145,2,1),(5,'BIT_GD_K18B','Bachelor Program of Information Technology, Digital Art & Design Major (Chương trình cử nhân ngành C','1. Training Objectives General objective: Training Bachelor of Information Technology, Digital Art & Design specialty with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implem...',5,146,2,1),(6,'BIT_GD_K18C','Bachelor Program of Information Technology, Digital Art & Design Major (Chương trình cử nhân ngành C','1. Training Objectives General objective: Training Bachelor of Information Technology, Digital Art & Design specialty with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implem...',1,146,2,1),(7,'BIT_AI_K16D-K17A','Bachelor Program of Information Technology, Artificial Intelligence (CTĐT ngành CNTT, chuyên ngành T','1. Training Objectives 1.1 General objective: Training Information Technology (IT)/Artificial Intelligence (AI) specialty engineers with qualities and capacities to meet the needs of society, mastering professional knowledge and practice, being able to or...',2,146,2,1),(8,'BIT_IA_K16D-K17A','	Bachelor Program of Information Technology, Information Assurance (CTĐT ngành CNTT, chuyên ngành An','1. TRAINING OBJECTIVES 1.1. General objectives: Training Information Technology (IT)/ Information Assurance (IA) engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize,...',3,146,2,1),(9,'BIT_IoT_K16D-17A','Bachelor Program of Information Technology, Internet of Things (CTĐT ngành CNTT, chuyên ngành Intern','1. TRAINING OBJECTIVES 1.1. General objectives: Training Information Technology (IT)/Internet of Things (IoT) engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, im...',4,146,2,1),(10,'BIT_IS_K16C','Bachelor Program of Information Technology, Information System Major (Chương trình cử nhân ngành CNT','1. Training Objectives 1.1 General objective: Training Information Technology (IT)/Information System (IS) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organiz...',5,146,2,1);
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_subject`
--

DROP TABLE IF EXISTS `curriculum_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curriculum_subject` (
  `curriculum_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `subject_group_id` int DEFAULT NULL,
  `learning_order` int DEFAULT NULL,
  KEY `curriculum_id` (`curriculum_id`),
  KEY `subject_id` (`subject_id`),
  KEY `subject_group_id` (`subject_group_id`),
  CONSTRAINT `curriculum_subject_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`),
  CONSTRAINT `curriculum_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  CONSTRAINT `curriculum_subject_ibfk_3` FOREIGN KEY (`subject_group_id`) REFERENCES `subject_group` (`subject_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_subject`
--

LOCK TABLES `curriculum_subject` WRITE;
/*!40000 ALTER TABLE `curriculum_subject` DISABLE KEYS */;
INSERT INTO `curriculum_subject` VALUES (1,1,1,2),(1,2,1,3),(1,3,1,3),(1,4,1,3),(1,5,1,3),(1,6,1,3),(1,7,1,3),(1,8,1,3),(1,9,1,3),(1,10,1,3),(1,11,1,3),(2,1,2,2),(2,2,2,3),(2,3,2,3),(2,4,2,3),(2,5,2,3),(2,6,2,3),(2,7,2,3),(2,8,2,3),(2,9,2,3),(2,10,2,3),(2,11,2,3),(3,1,3,2),(3,2,3,3),(3,3,3,3),(3,4,3,3),(3,5,3,3),(3,6,3,3),(3,7,3,3),(3,8,3,3),(3,9,3,3),(3,10,3,3),(3,11,3,3),(4,1,4,2),(4,2,4,3),(4,3,4,3),(4,4,4,3),(4,5,4,3),(4,6,4,3),(4,7,4,3),(4,8,4,3),(4,9,4,3),(4,10,4,3),(4,11,4,3),(5,1,5,2),(5,2,5,3),(5,3,5,3),(5,4,5,3),(5,5,5,3),(5,6,5,3),(5,7,5,3),(5,8,5,3),(5,9,5,3),(5,10,5,3),(5,11,5,3),(6,1,6,2),(6,2,6,3),(6,3,6,3),(6,4,6,3),(6,5,6,3),(6,6,6,3),(6,7,6,3),(6,8,6,3),(6,9,6,3),(6,10,6,3),(6,11,6,3),(7,1,7,2),(7,2,7,3),(7,3,7,3),(7,4,7,3),(7,5,7,3),(7,6,7,3),(7,7,7,3),(7,8,7,3),(7,9,7,3),(7,10,7,3),(7,11,7,3),(8,1,8,2),(8,2,8,3),(8,3,8,3),(8,4,8,3),(8,5,8,3),(8,6,8,3),(8,7,8,3),(8,8,8,3),(8,9,8,3),(8,10,8,3),(8,11,8,3),(9,1,9,2),(9,2,9,3),(9,3,9,3),(9,4,9,3),(9,5,9,3),(9,6,9,3),(9,7,9,3),(9,8,9,3),(9,9,9,3),(9,10,9,3),(9,11,9,3),(10,1,10,2),(10,2,10,3),(10,3,10,3),(10,4,10,3),(10,5,10,3),(10,6,10,3),(10,7,10,3),(10,8,10,3),(10,9,10,3),(10,10,10,3),(10,11,10,3),(4,44,14,2),(4,13,14,3),(4,17,24,2),(4,18,24,3),(4,19,24,3),(4,20,24,2),(4,21,24,3),(4,22,24,3),(4,23,24,3),(4,24,24,2),(4,25,24,3),(4,26,24,3),(4,27,24,2),(4,28,24,3),(4,29,24,3),(7,17,27,2),(7,18,27,3),(7,19,27,3),(7,20,27,2),(7,21,27,3),(7,22,27,3),(7,23,27,3),(7,24,27,2),(7,25,27,3),(7,26,27,3),(7,27,27,2),(7,28,27,3),(7,29,27,3),(8,17,28,2),(8,18,28,3),(8,19,28,3),(8,20,28,2),(8,21,28,3),(8,22,28,3),(8,23,28,3),(8,24,28,2),(8,25,28,3),(8,26,28,3),(8,27,28,2),(8,28,28,3),(8,29,28,3),(9,17,29,2),(9,18,29,3),(9,19,29,3),(9,20,29,2),(9,21,29,3),(9,22,29,3),(9,23,29,3),(9,24,29,2),(9,25,29,3),(9,26,29,3),(9,27,29,2),(9,28,29,3),(9,29,29,3),(10,17,30,2),(10,18,30,3),(10,19,30,3),(10,20,30,2),(10,21,30,3),(10,22,30,3),(10,23,30,3),(10,24,30,2),(10,25,30,3),(10,26,30,3),(10,27,30,2),(10,28,30,3),(10,29,30,3),(4,34,34,2),(4,35,34,3),(4,36,34,3),(4,37,34,2),(4,38,34,3);
/*!40000 ALTER TABLE `curriculum_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `decision`
--

DROP TABLE IF EXISTS `decision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `decision` (
  `decision_id` int NOT NULL AUTO_INCREMENT,
  `decision_no` varchar(50) DEFAULT NULL,
  `decision_date` date DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`decision_id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `decision_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decision`
--

LOCK TABLES `decision` WRITE;
/*!40000 ALTER TABLE `decision` DISABLE KEYS */;
INSERT INTO `decision` VALUES (1,'495/QĐ-ĐHFPT','2023-05-16',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(2,'496/QĐ-ĐHFPT','2023-05-17',2,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(3,'497/QĐ-ĐHFPT','2023-05-18',3,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(4,'498/QĐ-ĐHFPT','2023-05-19',4,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(5,'499/QĐ-ĐHFPT','2023-05-20',5,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(6,'500/QĐ-ĐHFPT','2023-05-21',6,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(7,'501/QĐ-ĐHFPT','2023-05-22',7,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(8,'502/QĐ-ĐHFPT','2023-05-23',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(9,'503/QĐ-ĐHFPT','2023-05-24',2,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(10,'504/QĐ-ĐHFPT','2023-05-25',3,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(11,'341/QĐ-ĐHFPT','2023-06-01',4,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(12,'342/QĐ-ĐHFPT','2023-06-02',5,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(13,'343/QĐ-ĐHFPT','2023-05-03',6,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(14,'344/QĐ-ĐHFPT','2023-05-04',7,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(15,'345/QĐ-ĐHFPT','2023-05-05',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(16,'346/QĐ-ĐHFPT','2023-06-07',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(17,'347/QĐ-ĐHFPT','2023-06-08',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(18,'348/QĐ-ĐHFPT','2023-06-09',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(19,'349/QĐ-ĐHFPT','2023-06-10',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(20,'350/QĐ-ĐHFPT','2023-06-11',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(21,'351/QĐ-ĐHFPT','2023-06-12',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(22,'352/QĐ-ĐHFPT','2023-06-13',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(23,'353/QĐ-ĐHFPT','2023-06-14',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(24,'354/QĐ-ĐHFPT','2023-06-15',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(25,'355/QĐ-ĐHFPT','2023-06-16',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(26,'356/QĐ-ĐHFPT','2023-05-21',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(27,'358/QĐ-ĐHFPT','2023-05-22',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(28,'359/QĐ-ĐHFPT','2023-05-23',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(29,'360/QĐ-ĐHFPT','2023-05-24',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(30,'361/QĐ-ĐHFPT','2023-05-25',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(31,'362/QĐ-ĐHFPT','2023-06-01',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(32,'363/QĐ-ĐHFPT','2023-06-02',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(33,'364/QĐ-ĐHFPT','2023-05-03',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(34,'365/QĐ-ĐHFPT','2023-05-04',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(35,'366/QĐ-ĐHFPT','2023-05-05',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(36,'367/QĐ-ĐHFPT','2023-06-07',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(37,'368/QĐ-ĐHFPT','2023-06-08',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(38,'369/QĐ-ĐHFPT','2023-05-09',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(39,'370/QĐ-ĐHFPT','2023-05-10',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(40,'371/QĐ-ĐHFPT','2023-05-11',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(41,'372/QĐ-ĐHFPT','2023-06-01',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(42,'373/QĐ-ĐHFPT','2023-06-02',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(43,'374/QĐ-ĐHFPT','2023-05-03',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(44,'375/QĐ-ĐHFPT','2023-05-04',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(45,'376/QĐ-ĐHFPT','2023-05-05',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(46,'377/QĐ-ĐHFPT','2023-06-07',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(47,'378/QĐ-ĐHFPT','2023-06-08',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(48,'379/QĐ-ĐHFPT','2023-05-09',1,1,'Ban hành đề cương chi tiết học kì Summer 2023'),
(49,'379/QĐ-ĐHFPT','2023-05-10',1,1,'Ban hành đề cương chi tiết học kì Summer 2023');
/*!40000 ALTER TABLE `decision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussion`
--

DROP TABLE IF EXISTS `discussion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussion` (
  `discussion_id` int NOT NULL AUTO_INCREMENT,
  `submit_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`discussion_id`),
  KEY `submit_id` (`submit_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `discussion_ibfk_1` FOREIGN KEY (`submit_id`) REFERENCES `submit` (`submit_id`),
  CONSTRAINT `discussion_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `discussion_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `discussion` (`discussion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussion`
--

LOCK TABLES `discussion` WRITE;
/*!40000 ALTER TABLE `discussion` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elective`
--

DROP TABLE IF EXISTS `elective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elective` (
  `elective_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `curriculum_id` int DEFAULT NULL,
  PRIMARY KEY (`elective_id`),
  KEY `elective_ibfk_1` (`curriculum_id`),
  CONSTRAINT `elective_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elective`
--

LOCK TABLES `elective` WRITE;
/*!40000 ALTER TABLE `elective` DISABLE KEYS */;
INSERT INTO `elective` VALUES (1,'BIT_GD_K15D,K16A TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,1),(2,'BIT_GD_K16B TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,2),(3,'EXE_ELE -- Entrepreneurship Group 1_ Nhóm học phần Khởi nghiệp 1','Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1',1,2),(4,'BIT_GD_K16C TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,3),(5,'Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1','Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1',1,3),(6,'BIT_GD_K16D,K17A TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,4),(7,'Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1','Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1',1,4),(8,'BIT_GD_K17C TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,5),(9,'BIT_GD_K17C TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,6),(10,'BIT_AI_K16D,K17A TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,7),(11,'BEN_K15C TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,8),(12,'BIT_IoT_K16C(FNO) TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,9),(13,'Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1','Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1',1,9),(14,'BIT_IS_K16C TMI_ELE','TMI_ELE -- Traditional musical instrument_Nhạc cụ truyền thống',1,10),(15,'Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1','Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1',1,10);
/*!40000 ALTER TABLE `elective` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elective_subject`
--

DROP TABLE IF EXISTS `elective_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elective_subject` (
  `elective_id` int NOT NULL,
  `subject_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elective_subject`
--

LOCK TABLES `elective_subject` WRITE;
/*!40000 ALTER TABLE `elective_subject` DISABLE KEYS */;
INSERT INTO `elective_subject` VALUES (1,47),(1,48),(2,47),(2,48),(3,15),(3,46),(4,47),(4,48),(5,15),(5,46),(6,47),(6,48),(7,15),(7,46),(8,47),(8,48),(9,47),(9,48),(10,47),(10,48),(11,47),(11,48),(12,47),(12,48),(13,15),(13,46),(14,47),(14,48),(15,15),(15,46);
/*!40000 ALTER TABLE `elective_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `syllabus_id` int DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `material_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `page` (
  `page_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'User List','/userList'),(2,'Setting list','/settingList');
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `role_id` int DEFAULT NULL,
  `page_id` int DEFAULT NULL,
  `access_all` tinyint(1) DEFAULT '0',
  `can_read` tinyint(1) DEFAULT '0',
  `can_add` tinyint(1) DEFAULT '0',
  `can_edit` tinyint(1) DEFAULT '0',
  `can_delete` tinyint(1) DEFAULT '0',
  KEY `role_id` (`role_id`),
  KEY `page_id` (`page_id`),
  CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `permission_ibfk_2` FOREIGN KEY (`page_id`) REFERENCES `page` (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,1,0,0,0,0,0),(2,1,0,1,0,0,0),(3,1,0,0,0,0,0),(4,1,0,0,0,0,0),(5,1,0,0,1,0,0),(6,1,0,0,0,0,0),(7,1,0,0,0,0,0),(1,2,0,0,0,0,0),(2,2,0,0,0,1,0),(3,2,0,0,0,0,0),(4,2,0,0,0,0,0),(5,2,0,0,0,0,0),(6,2,0,1,0,0,0),(7,2,0,0,0,0,0);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plo`
--

DROP TABLE IF EXISTS `plo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plo` (
  `plo_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `description` text,
  `curriculum_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`plo_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `plo_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plo`
--

LOCK TABLES `plo` WRITE;
/*!40000 ALTER TABLE `plo` DISABLE KEYS */;
INSERT INTO `plo` VALUES (1,'PLO1','Demonstrate basic knowledge of social sciences, politics and law, national security and defense, contributing to the formation of worldview and scientific methodology.',1,0),(2,'PLO2','Demonstrate an entrepreneurial, creative, critical, and problem-solving mindset.',1,0),(3,'PLO3','Communicating and working in groups effectively in academic and practical environments.',1,1),(4,'PLO4','Use English proficiently in communication and learning (equivalent to level 4 according to the 6-level Foreign Language Proficiency Framework for Vietnam, IELTS 6.0 or TOEFL (paper) 575-600 or TOEFL (iBT) 90 -100); and be able to communicate simply in Japanese.',1,1),(5,'PLO5','Demonstrate professional behaviors, morality, social responsibilities and a sense of dedication to community.',1,1),(6,'PLO6','Be mentally and physically strong, be capable of expressing national identity and integrating confidently into the world.',1,1),(7,'PLO1','Demonstrate basic knowledge of social sciences, politics and law, national security and defense, contributing to the formation of worldview and scientific methodology.',2,0),(8,'PLO2','Demonstrate an entrepreneurial, creative, critical, and problem-solving mindset.',2,0),(9,'PLO3','Communicating and working in groups effectively in academic and practical environments.',2,0),(10,'PLO4','Use English proficiently in communication and learning (equivalent to level 4 according to the 6-level Foreign Language Proficiency Framework for Vietnam, IELTS 6.0 or TOEFL (paper) 575-600 or TOEFL (iBT) 90 -100); and be able to communicate simply in Japanese.',2,0);
/*!40000 ALTER TABLE `plo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plo_clo`
--

DROP TABLE IF EXISTS `plo_clo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plo_clo` (
  `plo_id` int DEFAULT NULL,
  `clo_id` int DEFAULT NULL,
  KEY `plo_id` (`plo_id`),
  KEY `clo_id` (`clo_id`),
  CONSTRAINT `plo_clo_ibfk_1` FOREIGN KEY (`plo_id`) REFERENCES `plo` (`plo_id`),
  CONSTRAINT `plo_clo_ibfk_2` FOREIGN KEY (`clo_id`) REFERENCES `clo` (`clo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plo_clo`
--

LOCK TABLES `plo_clo` WRITE;
/*!40000 ALTER TABLE `plo_clo` DISABLE KEYS */;
/*!40000 ALTER TABLE `plo_clo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plo_subject`
--

DROP TABLE IF EXISTS `plo_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plo_subject` (
  `plo_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  KEY `plo_id` (`plo_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `plo_subject_ibfk_1` FOREIGN KEY (`plo_id`) REFERENCES `plo` (`plo_id`),
  CONSTRAINT `plo_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plo_subject`
--

LOCK TABLES `plo_subject` WRITE;
/*!40000 ALTER TABLE `plo_subject` DISABLE KEYS */;
INSERT INTO `plo_subject` VALUES (7,1),(8,2);
/*!40000 ALTER TABLE `plo_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `po`
--

DROP TABLE IF EXISTS `po`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `po` (
  `po_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `curriculum_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`po_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `po_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `po`
--

LOCK TABLES `po` WRITE;
/*!40000 ALTER TABLE `po` DISABLE KEYS */;
/*!40000 ALTER TABLE `po` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `po_plo`
--

DROP TABLE IF EXISTS `po_plo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `po_plo` (
  `po_id` int DEFAULT NULL,
  `plo_id` int DEFAULT NULL,
  KEY `po_id` (`po_id`),
  KEY `plo_id` (`plo_id`),
  CONSTRAINT `po_plo_ibfk_1` FOREIGN KEY (`po_id`) REFERENCES `po` (`po_id`),
  CONSTRAINT `po_plo_ibfk_2` FOREIGN KEY (`plo_id`) REFERENCES `plo` (`plo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `po_plo`
--

LOCK TABLES `po_plo` WRITE;
/*!40000 ALTER TABLE `po_plo` DISABLE KEYS */;
/*!40000 ALTER TABLE `po_plo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pre_requisite`
--

DROP TABLE IF EXISTS `pre_requisite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pre_requisite` (
  `subject_id` int DEFAULT NULL,
  `pre_requisite_id` int DEFAULT NULL,
  `syllabus_id` int DEFAULT NULL,
  KEY `subject_id` (`subject_id`),
  KEY `pre_requisite_id` (`pre_requisite_id`),
  KEY `pre_requisite_ibfk_3` (`syllabus_id`),
  CONSTRAINT `pre_requisite_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  CONSTRAINT `pre_requisite_ibfk_2` FOREIGN KEY (`pre_requisite_id`) REFERENCES `subject` (`subject_id`),
  CONSTRAINT `pre_requisite_ibfk_3` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pre_requisite`
--

LOCK TABLES `pre_requisite` WRITE;
/*!40000 ALTER TABLE `pre_requisite` DISABLE KEYS */;
INSERT INTO `pre_requisite` VALUES (14,13,5),(19,17,8),(23,22,12),(24,23,13),(28,23,17),(35,27,21),(35,23,21),(36,35,22),(36,37,22),(37,23,23),(38,37,24),(39,37,25),(7,5,32),(7,6,32),(8,5,33),(8,6,33),(9,5,34),(9,6,34),(16,15,36),(32,5,38),(32,6,38),(33,23,39);
/*!40000 ALTER TABLE `pre_requisite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` int NOT NULL,
  `syllabus_id` int DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer`
--

DROP TABLE IF EXISTS `reviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviewer` (
  `syllabus_id` int DEFAULT NULL,
  `reviewer_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  KEY `syllabus_id` (`syllabus_id`),
  KEY `reviewer_id` (`reviewer_id`),
  CONSTRAINT `reviewer_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`),
  CONSTRAINT `reviewer_ibfk_2` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer`
--

LOCK TABLES `reviewer` WRITE;
/*!40000 ALTER TABLE `reviewer` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'System Admin','Manage the system , able to manage users and roles'),(2,'CRDD Head','Assign, approve or disapprove curricula, subject, syllabi'),(3,'CRDD Staff','Maintain and manage curricula, subject, syllabi'),(4,'Sysllabus designer','Staff from outside school helping in sysllabus designing'),(5,'Sysllabus reviewer','Staff from outside school helping in sysllabus reviewing'),(6,'Teacher','Using for view and downloading teaching materrial'),(7,'Student','View learning materrial');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `session_id` int NOT NULL AUTO_INCREMENT,
  `syllabus_id` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `details` text,
  `learning_type` varchar(100) DEFAULT NULL,
  `student_materials` text,
  `student_task` text,
  PRIMARY KEY (`session_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (1,1,'LO1','Introduction to the online course ENW492c','Online,Offline','Offline 1.5 hours','Enroll to the spec on Coursera for FPT University Platform'),(2,1,'LO1, LO2	','MOOC 1:MOOC 1:','Online,Offline','Deadline of Course 1 Completion: End of Friday of the week.','- Watch all videos, and read all materials in the module'),(3,1,'LO4','MOOC 2:','Online','Online','- Watch all videos, and read all materials in the module'),(4,1,'LO1','hoa dan da add','Online','Online','G4. Understand concepts of assemblies to be able to write simple programs. Distinguish the difference between linguistic and high-level languages.'),(5,1,'LO2','G3. Know the basic concepts in computer organization and architecture such as stored programs, script architectures (properties, functions, formats), storage and memory access, representation operands. Know how to use simulation software for program testing and debugging.','Online','Online','Submit projects in order to unlock the next lessons'),(6,NULL,'LO3','Understand the basic concepts of cache like:','Offline','Offline','G1. Know the concepts of information, coding, error correction, and multiplexing. Understand how to represent numbers in binary, hexadecimal, numbers with offset 2 and basic operations.');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_lo`
--

DROP TABLE IF EXISTS `session_lo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session_lo` (
  `session_id` int DEFAULT NULL,
  `clo_id` int DEFAULT NULL,
  KEY `session_id` (`session_id`),
  KEY `clo_id` (`clo_id`),
  CONSTRAINT `session_lo_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `session` (`session_id`),
  CONSTRAINT `session_lo_ibfk_2` FOREIGN KEY (`clo_id`) REFERENCES `clo` (`clo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_lo`
--

LOCK TABLES `session_lo` WRITE;
/*!40000 ALTER TABLE `session_lo` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_lo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `value` text,
  `display_order` int DEFAULT NULL,
  `details` text,
  `status` text,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'User Role','Teacher','of',1,'system role','1'),(2,'User Role','Student','of',2,'role for system','1'),(3,'User Role','CRDD Head','of',3,'role for system','1'),(4,'User Role','CRDD Staff','3',4,'role for system','1'),(5,'User Role','Sysllabus designer','3',5,'role for system','1'),(6,'User Role','Sysllabus reviewer','3',6,'role for system','1'),(7,'User Role','System Admin','3',7,'role for system','1'),(8,'Email Domain','Email Domain 1','fpt.edu.vn',1,'email for system','1'),(9,'Email Domain','Email Domain 2','funix.edu.vn',2,'email for system','2'),(10,'System page','User list','/userlist',1,'system user','1'),(11,'System page','SettingList','/settingList',2,'system user','1');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `no_credit` int DEFAULT NULL,
  `pre_condition` varchar(450) DEFAULT '',
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'OTP101','OTP101 -- Orientation and General Training Program_Định hướng và Rèn luyện tập trung',1,3,''),(2,'PEN','Preparation English_Tiếng Anh chuẩn bị',0,4,''),(3,'PHE_COM','Bachelor Program of Information Technology, Software',0,2,''),(4,'TMI_ELE','Traditional musical instrument_Nhạc cụ truyền thống',0,2,''),(5,'MLN11','Philosophy of Marxism – Leninism_Triết học Mác - Lê-nin',8,2,''),(6,'MLN122','Political economics of Marxism – Leninism_Kinh tế chính trị Mác - Lê-nin',8,3,''),(7,'MLN131','Scientific socialism_Chủ nghĩa xã hội khoa học',9,2,''),(8,'HCM202','HCM Ideology_Tư tưởng Hồ Chí Minh',9,2,''),(9,'VNR202','History of CPV_Lịch sử Đảng Cộng sản Việt Nam',9,4,'None'),(10,'SSG103','Communication and In-Group Working Skills_Kỹ năng giao tiếp và cộng tác',2,2,''),(11,'SSL101c','Academic Skills for University Success_Kỹ năng học tập đại học',1,2,''),(12,'ENW492c','Academic Writing Skills_Kỹ năng viết học thuật',6,2,''),(13,'JPD113','Elementary Japanese 1- A1.1_Tiếng Nhật sơ cấp 1-A1.1',3,3,''),(14,'JPD123','Elementary Japanese 1-A1.2_Tiếng Nhật sơ cấp 1-A1.2',4,4,''),(15,'EXE101','Experiential Entrepreneurship 1_Trải nghiệm khởi nghiệp 1',7,2,''),(16,'EXE201','Experiential Entrepreneurship 2_Trải nghiệm khởi nghiệp 2',8,2,''),(17,'MAE101','Mathematics for Engineering_Toán cho ngành kỹ thuật',1,2,''),(18,'MAD101',' Discrete mathematics_Toán rời rạc',2,3,''),(19,'MAS291','Statistics & Probability_Xác suất thống kê',4,2,''),(20,'CSI104','Introduction to Computer_Nhập môn khoa học máy tính',1,2,''),(21,'CEA201','Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính',1,4,''),(22,'PRF192','Programming Fundamentals_Cơ sở lập trình',1,2,''),(23,'PRO192','Object-Oriented Programming_Lập trình hướng đối tượng',2,2,''),(24,'CSD201','Data Structures and Algorithms_Cấu trúc dữ liệu và giải thuật',3,2,''),(25,'OSG202','Operating Systems_Hệ điều hành',2,3,''),(26,'NWC203c','Computer Networking_Mạng máy tính',2,4,''),(27,'DBI202',' Introduction to Databases_Các hệ cơ sở dữ liệu',3,2,''),(28,'LAB211','OOP with Java Lab_Thực hành OOP với Java',3,2,''),(29,'PMG202c','Project management_Quản trị dự án',8,2,''),(30,'ITE302c','Ethics in IT_Đạo đức trong CNTT',5,3,''),(31,'OJT202','On-The-Job Training_Đào tạo trong môi trường thực tế',6,2,''),(32,'HCM202','HCM Ideology_Tư tưởng Hồ Chí Minh',9,2,''),(33,'PRM392','Mobile Programming_Lập trình di động',7,4,''),(34,'WED201c','Web Design_Thiết kế web',3,2,''),(35,'PRJ301','Java Web Application Development_Phát triển ứng dụng Java web',4,2,''),(36,'SWP391','Software development project_Dự án phát triển phần mềm',5,2,''),(37,'SWE201c','Introduction to Software Engineering_Nhập môn kĩ thuật phần mềm',4,3,''),(38,'SWR302','Software Requirement_Yêu cầu phần mềm',5,4,''),(39,'SWT301','Software Testing_Kiểm thử phần mềm',5,2,''),(40,'WDU203c','UI/UX Design_Thiết kế trải nghiệm người dùng',8,2,''),(41,'SWD392','Software Architecture and Design_Kiến trúc và thiết kế phần mềm',7,2,''),(42,'IOT102','Internet of Things_Internet vạn vật',4,3,''),(43,'SEP490','SE Capstone Project_Đồ án tốt nghiệp KTPM',9,2,''),(44,'PRN211','Basic Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng cơ bản với .NE',5,2,''),(45,'JPD133','Elementary Japanese 1-A1/A2_Tiếng Nhật sơ cấp 1-A1/A2',3,4,''),(46,'SYB302c','Entrepreneurship_Khởi sự doanh nghiệp',7,2,''),(47,'TRG102','Traditional musical instrument_Nhạc cụ truyền thống-Trống dân tộc',0,2,''),(48,'ĐTR102','Traditional musical instrument_Nhạc cụ truyền thống-Đàn Tranh',0,2,'');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_group`
--

DROP TABLE IF EXISTS `subject_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_group` (
  `subject_group_id` int NOT NULL AUTO_INCREMENT,
  `curriculum_id` int DEFAULT NULL,
  `group_type` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`subject_group_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `subject_group_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_group`
--

LOCK TABLES `subject_group` WRITE;
/*!40000 ALTER TABLE `subject_group` DISABLE KEYS */;
INSERT INTO `subject_group` VALUES (1,1,'General','General knowledge and skills_Khối Kiến thức chung'),(2,2,'General','General knowledge and skills_Khối Kiến thức chung'),(3,3,'General','General knowledge and skills_Khối Kiến thức chung'),(4,4,'General','General knowledge and skills_Khối Kiến thức chung'),(5,5,'General','General knowledge and skills_Khối Kiến thức chung'),(6,6,'General','General knowledge and skills_Khối Kiến thức chung'),(7,7,'General','General knowledge and skills_Khối Kiến thức chung'),(8,8,'General','General knowledge and skills_Khối Kiến thức chung'),(9,9,'General','General knowledge and skills_Khối Kiến thức chung'),(10,10,'General','General knowledge and skills_Khối Kiến thức chung'),(11,1,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(12,2,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(13,3,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(14,4,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(15,5,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(16,6,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(17,7,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(18,8,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(19,9,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(20,10,'Elective&Combo','Elective combo knowledge and skills_Khối kiến thức combo lựa chọn'),(21,1,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(22,2,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(23,3,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(24,4,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(25,5,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(26,6,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(27,7,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(28,8,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(29,9,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(30,10,'Major','Major knowledge and skills (including OJT)_Khối kiến thức ngành (bao gồm OJT)'),(31,1,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(32,2,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(33,3,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(34,4,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(35,5,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(36,6,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(37,7,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(38,8,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(39,9,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành'),(40,10,'Specialized','Specialized knowledge and skills_Khối kiến thức chuyên ngành');
/*!40000 ALTER TABLE `subject_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submit`
--

DROP TABLE IF EXISTS `submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submit` (
  `submit_id` int NOT NULL AUTO_INCREMENT,
  `syllabus_id` int DEFAULT NULL,
  `submitter_id` int DEFAULT NULL,
  `submit_file_url` varchar(200) DEFAULT NULL,
  `submit_notes` text,
  PRIMARY KEY (`submit_id`),
  KEY `syllabus_id` (`syllabus_id`),
  KEY `submitter_id` (`submitter_id`),
  CONSTRAINT `submit_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`),
  CONSTRAINT `submit_ibfk_2` FOREIGN KEY (`submitter_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submit`
--

LOCK TABLES `submit` WRITE;
/*!40000 ALTER TABLE `submit` DISABLE KEYS */;
/*!40000 ALTER TABLE `submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syllabus`
--

DROP TABLE IF EXISTS `syllabus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `syllabus` (
  `syllabus_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `description` text,
  `is_active` tinyint(1) DEFAULT '1',
  `decision_id` int DEFAULT NULL,
  `designer_id` int DEFAULT NULL,
  `reviewer_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `degree_level` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`syllabus_id`),
  KEY `decision_id` (`decision_id`),
  KEY `designer_id` (`designer_id`),
  KEY `reviewer_id` (`reviewer_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `syllabus_ibfk_1` FOREIGN KEY (`decision_id`) REFERENCES `decision` (`decision_id`),
  CONSTRAINT `syllabus_ibfk_2` FOREIGN KEY (`designer_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `syllabus_ibfk_3` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `syllabus_ibfk_4` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syllabus`
--

LOCK TABLES `syllabus` WRITE;
/*!40000 ALTER TABLE `syllabus` DISABLE KEYS */;
INSERT INTO `syllabus` VALUES (1,'Định hướng và Rèn luyện tập trung(Orientaiton and General Training Program)','Orientation and general training program includes 4 modules :',1,2,2,NULL,1,NULL),(2,'Communication and In-group working skills','This course will cover both working in groups and communication skills.\n\nAssessment structure:\n* On-going Assessment:\n- Activity: 10%\n- Quiz: 15%\n- Group Assignment : 15%\n- Group Project : 30%\n* Final Exam: 30%\n* Completion Criteria: Every on-going assessment component > 0, Final Exam >=4, Final Result >=5',1,2,1,NULL,10,NULL),(3,'Academic Skills for University Success_Kỹ năng học tập ở Đại học','Upon finishing the course, students can:\n1) Knowledge: Understand\n- Method to develop your Information & Digital Literacy Skills\n- Method to develop your Problem Solving and Creativity Skills\n- How to develop your Critical Thinking Skills\n- How to develop your Communication Skills\n2) Able to (ABET)',1,2,3,NULL,11,NULL),(4,'Elementary Japanese 1- A1.1_Tiếng Nhật sơ cấp 1-A1.1','I. Yêu cầu định hướng triển khai của môn học: Môn học cung cấp kiến thức, kỹ năng cơ bản của tiếng Nhật ở trình độ sơ cấp 1 (tương đương với trình độ A1) cho đối tượng sinh viên học tiếng Nhật là ngoại ngữ 2 tại trường Đại học.\nII. Mục tiêu của môn học:',1,2,3,NULL,13,NULL),(5,'Elementary Japanese 1-A1.2_Tiếng Nhật sơ cấp 1-A1.2','I. Yêu cầu định hướng triển khai của môn học: Môn học cung cấp kiến thức, kỹ năng cơ bản của tiếng Nhật ở trình độ sơ cấp 1 (tương đương với trình độ A1.2) cho đối tượng sinh viên học tiếng Nhật là ngoại ngữ 2 tại trường Đại học. Môn học là học phần tiếp theo môn JPD113.\nII. Mục tiêu của môn học:',1,2,2,NULL,14,NULL),(6,'Mathematics for Engineering_Toán cho ngành kỹ thuật','',1,2,2,NULL,17,NULL),(7,'Toán rời rạc','Upon finishing the course, students must acquire:\n1. the following knowledge: (ABET a.1)\n• Concepts of logical expressions & predicate logic.\n• The method of induction and recursive definition.\n• Concepts of algorithms, recursive algorithms, the complexity.\n• Recurrence relations and divide-and-conquer algorithms.\n• Application of integers and congruence in information technology.\n• Set structure and map, counting principles and combinatorics concepts.\n• The terminologies and properties of graphs & trees & weighted graphs.',1,2,3,NULL,18,NULL),(8,'Statistics & Probability_Xác suất thống kê','Upon finishing the course, students must acquire:\n1. the following knowledge:(ABET a1)\n• The fundamental principles of probability and their applications\n• The frequently used probability distributions.\n• The basics of descriptive statistics\n• The inferences of statistics: parameter estimations, hypothesis testing, regressions & correlations.\n2. the following skills: (ABET a2)',1,2,4,NULL,19,NULL),(9,'Introduction to Computer Science_Nhập môn khoa học máy tính','This course provides an overview of computer Fundamentals. Topics cover all areas of computer science in breadth such as computer organization, network, operating system, data structure , file structure, social and ethical issues.\nMajor Instructional Areas\n- Introduction to Von Neumann Model and computer components\n- Numbering system & data representation\n- Different data types and operations on data\n- The concepts of computer networking and internet\n- Operating system\n- Introduction to basic algorithms and algorithms representation\n- Introduction to Data structure and File structure\n- The concepts of database\n- software engineering\n- Computing Security & Ethics',1,2,5,NULL,20,NULL),(10,'Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính','This course in an introduction to computer architecture and organization. It will cover topics in both the physical design of the computer (organization) and the logical design of the computer (architecture). The main contents include the organization of a simple stored-program computer: CPU, busses and memory; Instruction sets, machine code, and assembly language; Conventions for assembly language generated by compilers; Floating-point number representation; Hardware organization of simple processors; Address translation and virtual memory; Very introductory examples of input/output devices, interrupt handling and multi-tasking systems.',1,2,5,NULL,21,NULL),(11,'Programming Fundamentals_Cơ sở lập trình','Understand basics of information theory, computer system and methods of software development, focus on function-oriented programming design, coding, testing and discipline in programming.\nExplain basic concepts of programming, function-oriented programming design, modularity, understand and coding programs using C',1,2,5,NULL,22,NULL),(12,'Object-Oriented Programming_Lập trình hướng đối tượng','-This subject introduces the student to object-oriented programming. The student learns to build reusable objects, encapsulate data and logic within a class, inherit one class from another and implement polymorphism.\n- Compose technical documentation of a Java program using internal comments\n- Adhere to object-oriented programming principles including encapsulation, polymorphism and inheritance when writing program code\n- Trace the execution of Java program logic to determine what a program does or to validate the correctness of a program',1,2,3,NULL,23,NULL),(13,'Data Structures and Algorithm_Cấu trúc dữ liệu và giải thuật','Upon finishing the course, students can:\n1) Knowledge: Understand (ABET e)\n- the connection between data structures and their algorithms, including an analysis of algorithms\' complexity;\n- data structurre in the context of object-oriented program design;\n- how data structure are implemented in an OO programming language such as Java',1,2,1,NULL,24,NULL),(14,'Operating System_Hệ điều hành','At the end of this course, students will be able to perceive:\n1) Background knowledge: (ABET e)\n-The role of operating system,\n- important OS concepts,\n- the mechanism of operating system, and\n- main problems of Operating system.\n2) Practice skills: (ABET k)',1,2,3,NULL,25,NULL),(15,'Computer Networking_Mạng máy tính','Final Exam is included of Final Theory Exam (TE) & Final Practical Exam (PE): 100%\nStudent gets 0.25 bonus points for each course completed on time. The total bonus point is not greater than 1.\nCompletion Criteria: Final TE Score >=4 & Final PE Score >=4 & ((Final TE Score + Final PE Score)/2 + bonus) >= 5\nIn the case: (5 > Final TE Score >=4) & (5 > Final PE Score >=4) & ((Final TE Score + Final PE Score)/2 + bonus) < 5, the student can choose to take the resit of both TE & PE OR just either TE or PE.',1,2,5,NULL,26,NULL),(16,'Database Systems_Các hệ cơ sở dữ liệu','- Knowledge about database systems has become an essential part of an education in computer science because database management has evolved from a specialized computer application to a central component of a modern computing environment.\n- The content of this course includes aspects of database management basic concepts, database design, database',1,2,4,NULL,27,NULL),(17,'OOP with Java Lab_Thực hành OOP với Java','This course focuses on basic problems related to Java programming skills. Students are required to implement all assignments by him/her self in lab rooms.\nEach assignment must be completed continuosly in the defined time.',1,2,1,NULL,28,NULL),(18,'Ethics in IT_Đạo đức trong CNTT','Organizations and governments are seeking out ethics professionals to minimize risk and guide their decision-making about the design of inclusive, responsible, and trusted technology.',1,2,1,NULL,30,NULL),(19,'On-the-job training_Đào tạo trong môi trường thực tế','Target:\n- Students get acquainted with the real working environment before completing the study program.\n- Students explore and expand their understanding of learned programs from a broader perspective.\n- Students have rich learning experiences in an industrial and globalized environment.',1,2,2,NULL,31,NULL),(20,'Web Design_Thiết kế web','Upon finishing the course, students can:\n1) Knowledge: Understand (ABET e)\n- The concepts of HTML, CSS3, JavaScript, Interactivity with JavaScript, Advanced Styling with Responsive Design\n- Web page structure.\n- Web site structure.\n- Demonstrate the ability to design and implement a responsive site for three platforms.\n- The way a web page is presented in browsers.',1,2,4,NULL,34,NULL),(21,'Java Web Application Development_Phát triển ứng dụng Java web','By the end of this course Students will be able to:\na) Knowledge: (what will students know?)\n• Understand the core technologies of Java web programming:\n- Servlet and JSP\n- Scope of sharing state (session, application, request,page)\n- JSP Tags, JSTL, Customtags\n- Filtering\n• Know how to develop and deploy your own websites using Java\n• Understand and be able to apply MVC architecture for the web',1,2,2,NULL,35,NULL),(22,'Software development project_Dự án phát triển phần mềm','This course focuses on designing, developing, and integrating the basic Web-based system/application using Java Web or .NET technologies (with the system requirements, technical framework & DBMS as asigned/agreed by the teacher)\nStudents are required to build the system with other 3-5 team members as appointed by the teacher.\nAfter the course, students will be able to achieve Java web programing proficiency with the following skills by practicing with other members in the assigned team',1,2,2,NULL,36,NULL),(23,'Introduction to Software Engineering_Nhập môn kỹ thuật phần mềm','SWE201c is for people who are new to software engineering. It\'s also for those who have already developed software, but wish to gain a deeper understanding of the underlying context and theory of software development practices.\n\nAt the end of this course, we expect learners to be able to:',1,2,1,NULL,37,NULL),(24,'Software Requirement_Yêu cầu phần mềm','This course is a model-based introduction to RE, providing the conceptual background and terminology on RE, addressing a variety of techniques for requirements development including Analysis and Requirements Elicitation; Requirements Evaluation; Requirements Specification and Documentation; Requirements Quality Assurance...',1,2,3,NULL,38,NULL),(25,'Software Testing_Kiểm thử phần mềm','• General concepts about software testing\n• Testing techniques aimed at assuring that appropriate',1,2,5,NULL,39,NULL),(26,'Internet of Things_Internet vạn vật','This is a 3-credit course. This course has two parts online and offline.\nThe content includes basic concepts and applications of IoT, practical exercises on the learning KIT.\nStudents are taught how to learn online, and practice some parts at home. Q & A sessions, the Guidance for important issues, as well as performance assessments, will be conducted in the classroom.',1,2,4,NULL,40,NULL),(27,'	PEN -- Preparation English_Tiếng Anh chuẩn bị','Adminstration, Digital Marketing Major (CTĐT ngành QTKD, chuyên ngành Marketing Số)',1,2,4,NULL,2,NULL),(28,'Nhạc cụ truyền thống-Đàn Tranh Traditional musical instrument - Dan Tranh','1. Kiến thức/ Knowledge:\n- Nắm được những nét đăc trựng về lịch sử phát triển và cấu trúc Đàn Tranh\nUnderstand specific characteristics of the historical development and structure of Dan Tranh.',1,2,3,NULL,3,NULL),(29,'BBA_MKT_K16D17A -- Bachelor Program of Business','Adminstration, Digital Marketing Major (CTĐT ngành QTKD, chuyên ngành Marketing Số)',1,2,3,NULL,4,NULL),(30,'Philosophy of Marxism – Leninism - Triết học Mác - Lê-nin','Giới thiệu môn học: Triết học Mác - Lênin nghiên cứu những quan điểm duy vật biện chứng về tự nhiên, xã hội, tư duy và nhờ đó thế giới quan duy vật biện chứng trở thành toàn diện và triệt để. Áp dụng và mở rộng quan điểm duy vật biện chứng vào nghiên cứu xã hội, Mác đã đưa ra được quan niệm duy vật về lịch sử, chỉ ra con đường nghiên cứu những quy luật của sự phát triển xã hội, cũng như sự phát triển của tự nhiên, không phải do ý muốn chủ quan mà do những quy luật khách quan quyết định. Sự ra đời của Triết học Mác - Lênin đã đặt cơ sở cho việc nghiên cứu lịch sử và đời sống xã hội thực sự có tính chất khoa học.',1,2,4,NULL,5,NULL),(31,'MLN121 -- Political economics of Marxism – Leninism - Kinh tế chính trị Mác - Lênin','MLN121 -- Political economics of Marxism – Leninism - Kinh tế chính trị Mác - Lênin',1,2,2,NULL,6,NULL),(32,'Scientific socialism - Chủ nghĩa xã hội khoa học','Giới thiệu môn học: Chủ nghĩa xã hội khoa học là một trong ba bộ phận của chủ nghĩa Marx-Lenin. Chủ nghĩa xã hội khoa học đã dựa trên phương pháp luận triết học duy vật biện chứng và duy vật lịch sử, đồng thời cũng dựa trên những cơ sở lý luận khoa học về các quy luật kinh tế, quan hệ kinh tế để luận giải một cách khoa học về quá trình nảy sinh cách mạng xã hội chủ nghĩa, hình thành và phát triển hình thái kinh tế - xã hội cộng sản chủ nghĩa, gắn liền với sứ mệnh lịch sử của giai cấp công nhân, nhằm giải phóng con người, giải phóng xã hội.',1,2,1,NULL,7,NULL),(33,'HCM Ideology - Tư tưởng Hồ Chí Minh','Giới thiệu môn học: Tư tưởng Hồ Chí Minh là kết tinh của truyền thống hàng nghìn năm dựng nước và giữ nước của dân tộc Việt Nam. Trên nền tảng đó, tư tưởng Hồ Chí Minh đã gạn lọc các hạt giống trí tuệ của phương Đông, phương Tây, vận dụng sáng tạo và phát triển chủ nghĩa Mác - Lênin vào điều kiện cụ thể của nước ta. Dưới góc độ triết học, tư tưởng Hồ Chí Minh là hệ thống các quan điểm toàn diện, sâu sắc về những vấn đề cơ bản của cách mạng Việt Nam. Mục tiêu của tư tưởng Hồ Chí Minh là hướng tới giải phóng giai cấp, giải phóng dân tộc và giải phóng con người. ',1,2,2,NULL,8,NULL),(34,'History of CPV - Lịch sử Đảng Cộng sản Việt Nam','Giới thiệu môn học: Lịch sử Đảng Cộng sản Việt Nam là một chuyên ngành, một bộ phận của khoa học lịch sử. Chủ tịch Hồ Chí Minh đã khẳng định Lịch sử Đảng Cộng sản Việt Nam là cả một pho lịch sử bằng vàng.',1,2,5,NULL,9,NULL),(35,'Experiential Entrepreneurship 1 - Trải nghiệm khởi nghiệp 1','- This course will provide students with essential knowledge and tips on starting a start-up efficiently and effectively.\n- The course covers the most important aspects of modern entrepreneurship.\n- Students will study by watching videos shared by prolific startup founders on various topics. Each week, there will be a face-to-face session with an instructor, typically a faculty lecturer of business',1,2,5,NULL,15,NULL),(36,'Experiential Entrepreneurship 2 - Trải nghiệm khởi nghiệp 2','- In Experimental Experiment 2, groups of students will develop products for their start-up ideas prepared from Experimental Experiment 1 and deploy sales and find real customers for their group\'s products/services.',1,2,4,NULL,16,NULL),(37,'Project Management_Quản trị dự án','Project managers play a key role in leading, planning and implementing critical projects to help their organizations succeed. In this course, students’ll discover foundational project management terminology and gain a deeper understanding of the role and responsibilities of a project manager. They’ll gain an immersive understanding of the practices and skills needed to succeed in an entry-level project management role;',1,2,5,NULL,29,NULL),(38,'HCM Ideology - Tư tưởng Hồ Chí Minh','Giới thiệu môn học: Tư tưởng Hồ Chí Minh là kết tinh của truyền thống hàng nghìn năm dựng nước và giữ nước của dân tộc Việt Nam. Trên nền tảng đó, tư tưởng Hồ Chí Minh đã gạn lọc các hạt giống trí tuệ của phương Đông, phương Tây, vận dụng sáng tạo và phát triển chủ nghĩa Mác - Lênin vào điều kiện cụ thể của nước ta',1,2,2,NULL,32,NULL),(39,'Mobile Programming_Lập trình di động','Upon completion of this course students should:\n1. understand basic knowledge of mobile programming\n2. get some experienced with all common controls of Android\n3. have knowledge about some advanced Android components\n4. have knowledge about data storage in mobile application, can use api to connect data from server from mobile application\n5. understand knowledge of Android programming which help student can self study further more easily\n6. Be able to work in team and present group\'s results',1,2,2,NULL,33,NULL);
/*!40000 ALTER TABLE `syllabus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` tinyint DEFAULT '1',
  `title` varchar(10) DEFAULT NULL,
  `company` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `avatar_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin Admin','admin12','thuongvvhs161@fpt.edu.vn','0888160699','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mr','fpt software','2023-06-23',NULL,NULL,NULL),(2,'Truc Ha','ha123','hantthe17033@fpt.edu.vn','0843442345','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mrs','NTF solution','2023-06-23',NULL,NULL,NULL),(3,'Van Thuong','thuong12','hoangthuongx748@gmail.com','0963332191','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'ms','fpt software','2023-06-23',NULL,NULL,NULL),(4,'Wakamo Kurosagi','admin1234','foxOfCalamity@fpt.edu','0223443219','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'ms','foxyCrafe INC','2023-06-23',NULL,NULL,NULL),(5,'Admin ARIS','admin123','thuongvv1606@gmail.com','0888130699','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mr','fpt software','2023-06-23',NULL,NULL,NULL),(6,'Miyako Rabbit','admin1','phucchfx01851@funix.edu.vn','0223773219','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mrs','fpt software','2023-06-23',NULL,NULL,NULL),(7,'End Of All','thuong123','thuongv160@gmail.com','03032343455','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mr','fpt software','2023-06-23',NULL,NULL,NULL),(8,'Miyako Phuc','trucha12','phucchfx01851@funix.edu.vn','0223446619','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mrs','fpt software','2023-06-23',NULL,NULL,NULL),(9,'End Of All','thuong1234','thuong1606@gmail.com','0901234345','$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i',1,'mr','fpt software','2023-06-23',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(1,2,1),(1,3,1),(2,3,1),(2,4,1),(3,5,1),(4,6,1),(5,7,1),(6,8,1),(7,9,1),(6,10,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-23  1:51:36
