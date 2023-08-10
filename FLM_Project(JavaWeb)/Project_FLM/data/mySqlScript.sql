-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: G5_FLM
-- ------------------------------------------------------
-- Server version	8.0.32
DROP DATABASE IF EXISTS G5_FLM;
CREATE DATABASE G5_FLM;
USE G5_FLM;


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
  `file_description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
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
  PRIMARY KEY (`clo_id`),
  KEY `syllabus_id` (`syllabus_id`),
  KEY `parent_lo_id` (`parent_lo_id`),
  CONSTRAINT `clo_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`),
  CONSTRAINT `clo_ibfk_2` FOREIGN KEY (`parent_lo_id`) REFERENCES `clo` (`clo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clo`
--

LOCK TABLES `clo` WRITE;
/*!40000 ALTER TABLE `clo` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (1,'SE','Kỹ thuật phần mềm','Có các chương trình đào tạo về Lập trình, An toàn thông tin, Mạng máy tính và Truyền thông, Khoa học Dữ liệu, Trí tuệ nhân tạo,...',1,12,2,1),
(2,'HS','Kinh tế','Có các chương trình đào tạo về Quản trị kinh doanh, Marketing, Quản lý nhân sự, Tài chính kế toán,...',1,12,2,1),
(3,'HQ','Tiếng Hàn','Có các chương trình đào tạo về ngôn ngữ Hàn Quốc',1,12,2,1),
(4,'NB','Tiếng Nhật','Có các chương trình đào tạo về ngôn ngữ Nhật Bản',1,12,2,1),
(5,'TA','Tiếng Anh','Có các chương trình đào tạo về ngôn ngữ Anh',1,12,2,1);
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
INSERT INTO `curriculum_subject` VALUES (1,1,1,2),(1,5,1,3),(1,6,1,3),(1,7,1,3),(1,8,1,3),(1,9,1,3),(1,10,1,3),(1,11,1,3),(1,12,1,3);
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
  `is_active` tinyint(1) DEFAULT 1,
  `name` nvarchar(50) DEFAULT NULL,
  PRIMARY KEY (`decision_id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `decision_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decision`
--

LOCK TABLES `decision` WRITE;
/*!40000 ALTER TABLE `decision` DISABLE KEYS */;
INSERT INTO `decision` VALUES (1,'ok','2023-02-02',1,"okname",1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
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
  `access_all` tinyint(1) DEFAULT 0,
  `can_read` tinyint(1) DEFAULT 0,
  `can_add` tinyint(1) DEFAULT 0,
  `can_edit` tinyint(1) DEFAULT 0,
  `can_delete` tinyint(1) DEFAULT 0,
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
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` text,
  `curriculum_id` int DEFAULT NULL,
   `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`plo_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `plo_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plo`
--

LOCK TABLES `plo` WRITE;
/*!40000 ALTER TABLE `plo` DISABLE KEYS */;
INSERT INTO `plo` VALUES (1,'PLO1','Demonstrate basic knowledge of social sciences, politics and law, national security and defense, contributing to the formation of worldview and scientific methodology.',1,0),(2,'PLO2','Demonstrate an entrepreneurial, creative, critical, and problem-solving mindset.',1,0),(3,'PLO3','Communicating and working in groups effectively in academic and practical environments.',1,1),(4,'PLO4','Use English proficiently in communication and learning and be able to communicate simply in Japanese.\r\n',1,1),(5,'PL01','aaaaaaaaaaaaaaaaaaaaa',1,1),(6,'PLO1','NBBBBBBBB',1,1);
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
  PRIMARY KEY (`session_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
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
  `display_order` int, 
  `details` text,
  `status` text,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'PRJ302','Java',2,3,'None'),(2,'SWP391','project mini',3,4,''),(3,'PRJ31','java web2',2,2,'PRJ30'),(4,'SWP490','do an',2,2,'SWP391'),(5,'JPD123','Nhat',2,2,'None'),(6,'ENW292','Eng-Jas',2,3,'2'),(7,'SWP491','do an',2,2,'PRJ302'),(8,'SWP392','do an',2,2,'Non'),(9,'HCM101','Ho Chi Minh Ideology (HCM201)',9,4,'None'),(10,'MAD123','Toan',2,2,'None'),(11,'PRN292','C',2,2,'None'),(12,'PRN293','CCCCCC',2,2,'None');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_group`
--

LOCK TABLES `subject_group` WRITE;
/*!40000 ALTER TABLE `subject_group` DISABLE KEYS */;
INSERT INTO `subject_group` VALUES (1,1,'SWP','.NET'),(2,2,'MKT','Kinh tế'),(3,3,'SPD','tiếng nhật'),(4,2,'PRJ','JAVA');
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
  `name` nvarchar(100) DEFAULT NULL,
  `description` text,
  `status` varchar(50) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syllabus`
--

LOCK TABLES `syllabus` WRITE;
/*!40000 ALTER TABLE `syllabus` DISABLE KEYS */;
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
  `full_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` tinyint DEFAULT '1',
  `title` varchar(10) DEFAULT NULL,
  `company` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `avatar_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `modified_by` (`modified_by`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


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
INSERT INTO `user_role` VALUES (1,1,1),(1,2,1),(1,2,1),(2,4,1),(3,5,1),(4,6,1),(5,7,1),(6,8,1),(7,9,1),(6,10,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
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
  `syllabus_id` int ,
  KEY `subject_id` (`subject_id`),
  KEY `pre_requisite_id` (`pre_requisite_id`),
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
/*!40000 ALTER TABLE `pre_requisite` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




INSERT INTO user(user_id,full_name,user_name,email,mobile,password,status,title,company,create_at,update_at,modified_by) VALUES
(1,"Admin","admin12","thuongvvhs160715@fpt.edu.vn","0888160699","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mrs","fpt software",CURDATE(),null,null),
(2,"truc ha","ha12","hantthe170330@fpt.edu.vn","093909032","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mr","NTF solution",CURDATE(),null,null),
(3,"thuong","thuong12","hoangthuongx748@gmail.com","096333219","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"ms","fpt software",CURDATE(),null,null),
(4,"Wakamo Kurosagi","wakamo","foxOfCalamity@fpt.edu","0223443219","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"ms","foxyCrafe INC",CURDATE(),null,null),
(5,"Admin ARIS","admin123","thuongvv1606@gmail.com","0888160699","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mr","fpt software",CURDATE(),null,null),
(6,"Miyako","rabbitfire","phucchfx01851@funix.edu.vn","0223443219","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mrs","fpt software",CURDATE(),null,null),
(7,"End Of All","thuong12","thuongvv1606@gmail.com","09032343455","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mr","fpt software",CURDATE(),null,null),
(8,"Miyako","trucha12","phucchfx01851@funix.edu.vn","0223443219","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mrs","fpt software",CURDATE(),null,null),
(9,"End Of All","thuong123","thuongvv1606@gmail.com","09032343455","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mr","fpt software",CURDATE(),null,null),
(10,"Miyako","admin123","phucchfx01851@funix.edu.vn","0223443219","$2a$10$rBbHM187S9d2/pbh5zuu5eMhHvOFnDxTg/C5YMgWjFgsk4Coxu03i",1,"mrs","fpt software",CURDATE(),null,null);


INSERT INTO role(role_id,name,description) VALUES
(1,"System Admin","Manage the system , able to manage users and roles"),
(2,"CRDD Head","Assign, approve or disapprove curricula, subject, syllabi"),
(3,"CRDD Staff","Maintain and manage curricula, subject, syllabi"),
(4,"Sysllabus designer","Staff from outside school helping in sysllabus designing"),
(5,"Sysllabus reviewer","Staff from outside school helping in sysllabus reviewing"),
(6,"Teacher","Using for view and downloading teaching materrial"),
(7,"Student","View learning materrial");

INSERT INTO `G5_FLM`.`setting` (`setting_id`, `type`, `title`, `value`, `details`, `status`,`display_order`) VALUES 
('1', 'User Role', 'Teacher', 'of', 'system role', 1,1),
('2',"User Role","Student","of","role for system",1,2),
('3',"User Role","CRDD Head","of","role for system",1,3),
('4',"User Role","CRDD Staff","3","role for system",1,4),
('5',"User Role","Sysllabus designer","3","role for system",1,5),
('6',"User Role","Sysllabus reviewer","3","role for system",1,6),
('7',"User Role","System Admin","3","role for system",1,7),
('8',"Email Domain","Email Domain 1","fpt.edu.vn","email for system",1,1),
('9',"Email Domain","Email Domain 2","funix.edu.vn","email for system",2,2),
('10',"System page","User list","/userlist","system user",1,1),
('11',"System page","SettingList","/settingList","system user",1,2);

INSERT INTO subject (subject_id,code,name,semester,no_credit,pre_condition) VALUES 
(13,"PRJ321","Phát triển ứng dụng Web bằng java",8,5,''),
(14,"WEB101x","Xây dựng website đầu tiên",2,3,''),
(15,"PRO192x","Lập trình hướng đối tượng (bằng Java)",6,4,''),
(16,"DBA201x","Cơ sở dữ liệu và giải thuật",7,5,''),
(17,"FUN100x","Tổng quan về IT",1,2,''),
(18,"PRJ311x","Phát triển phần mềm bằng Java",7,5,''),
(19,"ITE302","Đạo đức trong CNTT",9,2,''),
(20,"JPN101","Tiếng nhật N1",8,5,''),
(21,"JPN102","Tiếng Nhật N2",8,5,'');

INSERT INTO pre_requisite (subject_id,pre_requisite_id) VALUES
(14,17),
(15,14),
(16,15),
(13,16),
(13,19),
(21,20);


INSERT INTO `page`( `page_id`,`name`,`url`) VALUES
(1,"User List","/userList"),
(2,"Setting list","/settingList");

INSERT INTO `permission` (`role_id`,`page_id`,`access_all`,`can_read`,`can_add`,`can_edit`,`can_delete`) VALUES 
(1,1,0,0,0,0,0),
(2,1,0,1,0,0,0),
(3,1,0,0,0,0,0),
(4,1,0,0,0,0,0),
(5,1,0,0,1,0,0),
(6,1,0,0,0,0,0),
(7,1,0,0,0,0,0),
(1,2,0,0,0,0,0),
(2,2,0,0,0,1,0),
(3,2,0,0,0,0,0),
(4,2,0,0,0,0,0),
(5,2,0,0,0,0,0),
(6,2,0,1,0,0,0),
(7,2,0,0,0,0,0);


CREATE TABLE elective (
 elective_id int auto_increment primary key, 
`name` nvarchar(100),
`description` nvarchar(200),
`is_active` tinyint(1) DEFAULT 1, 
 curriculum_id int , 
 CONSTRAINT `elective_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
);
INSERT INTO elective(elective_id,name,description,is_active,curriculum_id) VALUES 
(1,"elective 1","somthing ouf of niothing",1,1),
(2,"elective 2","somthing ouf of niothing",1,1);



CREATE TABLE elective_subject (
elective_id int NOT NULL,
subject_id int NOT NULL
);

INSERT INTO elective_subject(elective_id,subject_id)  VALUES
(1,2),
(1,5),
(1,6),
(2,3),
(2,8),
(2,2)
;

INSERT INTO decision (`decision_id`,`decision_no` ,`decision_date` ,`creator_id` ,name,is_active) VALUES 
(2,"QD 123",CURDATE(),1,"decision1",1),
(3,"312/QD-fpt",CURDATE(),1,"decision2",1);

INSERT INTO `syllabus` (`syllabus_id`,`name`,`description`,`status`,`decision_id`,`designer_id`,`reviewer_id`,`subject_id`,`degree_level`)
VALUES 
('1',"Phát triển ứng dụng Web bằng java","This course focuses on designing, developing, and integrating the basic Web","status",2,null,null,13,null),
('2',"Phát triển ứng dụng Web bằng java1","This course focuses on designing, developing, and integrating the basic Web","status",2,null,null,13,null),
('3',"Xây dựng website đầu","This course focuses on designing, developing, and integrating the basic Web","status",2,null,null,14,null);


