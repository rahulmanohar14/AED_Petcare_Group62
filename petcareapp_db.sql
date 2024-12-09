-- MariaDB dump 10.19  Distrib 10.11.5-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: petcareapp_db
-- ------------------------------------------------------
-- Server version	10.11.5-MariaDB-3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity_logs`
--

DROP TABLE IF EXISTS `activity_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_logs` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_id` int(11) NOT NULL,
  `activity_type` enum('Walk','Playtime','Training','Other') NOT NULL,
  `duration_minutes` int(11) NOT NULL,
  `activity_date` date NOT NULL,
  `notes` text DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_logs`
--

LOCK TABLES `activity_logs` WRITE;
/*!40000 ALTER TABLE `activity_logs` DISABLE KEYS */;
INSERT INTO `activity_logs` VALUES
(1,1,'Walk',30,'2024-11-20','Morning walk in the park.','2024-12-08 17:57:04'),
(2,1,'Playtime',45,'2024-11-21','Playing fetch in the backyard.','2024-12-08 17:57:04'),
(3,2,'Training',60,'2024-11-22','Basic obedience training session.','2024-12-08 17:57:04'),
(4,3,'Walk',40,'2024-11-23','Evening walk around the neighborhood.','2024-12-08 17:57:04'),
(5,4,'Playtime',20,'2024-11-24','Interactive play with new toys.','2024-12-08 17:57:04');
/*!40000 ALTER TABLE `activity_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `service_type` enum('Vet','Grooming','Training') NOT NULL,
  `appointment_date` datetime NOT NULL,
  `status` enum('Scheduled','Completed','Cancelled') DEFAULT 'Scheduled',
  `notes` text DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `pet_id` (`pet_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`) ON DELETE CASCADE,
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES
(2,1,1,'Vet','2024-12-05 10:06:43','Scheduled','testing'),
(3,1,1,'Grooming','2024-12-05 10:06:43','Completed','testing purpose');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_items`
--

DROP TABLE IF EXISTS `buy_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buy_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL CHECK (`quantity` > 0),
  `price` decimal(10,2) NOT NULL CHECK (`price` >= 0),
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `buy_items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_items`
--

LOCK TABLES `buy_items` WRITE;
/*!40000 ALTER TABLE `buy_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `buy_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_records`
--

DROP TABLE IF EXISTS `health_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `health_records` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_id` int(11) NOT NULL,
  `vet_id` int(11) NOT NULL,
  `date_of_visit` date NOT NULL,
  `vaccination_given` varchar(100) DEFAULT NULL,
  `diagnosis` text DEFAULT NULL,
  `treatment` text DEFAULT NULL,
  `follow_up_date` date DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `pet_id` (`pet_id`),
  KEY `vet_id` (`vet_id`),
  CONSTRAINT `health_records_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`) ON DELETE CASCADE,
  CONSTRAINT `health_records_ibfk_2` FOREIGN KEY (`vet_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_records`
--

LOCK TABLES `health_records` WRITE;
/*!40000 ALTER TABLE `health_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_policies`
--

DROP TABLE IF EXISTS `insurance_policies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insurance_policies` (
  `policy_id` int(11) NOT NULL AUTO_INCREMENT,
  `policy_number` varchar(50) NOT NULL,
  `pet_owner_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  `coverage_amount` decimal(10,2) NOT NULL,
  `premium_amount` decimal(10,2) NOT NULL,
  `policy_start_date` date NOT NULL,
  `policy_end_date` date NOT NULL,
  `status` enum('Active','Expired','Canceled') DEFAULT 'Active',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`policy_id`),
  UNIQUE KEY `policy_number` (`policy_number`),
  KEY `pet_owner_id` (`pet_owner_id`),
  KEY `pet_id` (`pet_id`),
  CONSTRAINT `insurance_policies_ibfk_1` FOREIGN KEY (`pet_owner_id`) REFERENCES `pets` (`owner_id`),
  CONSTRAINT `insurance_policies_ibfk_2` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_policies`
--

LOCK TABLES `insurance_policies` WRITE;
/*!40000 ALTER TABLE `insurance_policies` DISABLE KEYS */;
INSERT INTO `insurance_policies` VALUES
(3,'POL12345',1,1,5000.00,50.00,'2024-01-01','2025-01-01','Active','2024-12-08 14:24:57');
/*!40000 ALTER TABLE `insurance_policies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_items`
--

DROP TABLE IF EXISTS `inventory_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_items`
--

LOCK TABLES `inventory_items` WRITE;
/*!40000 ALTER TABLE `inventory_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `item_quantity` int(11) NOT NULL CHECK (`item_quantity` >= 0),
  `item_price` decimal(10,2) NOT NULL CHECK (`item_price` >= 0),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES
(1,'first',4,40.00),
(3,'third',10,300.00),
(4,'trtr',23,45.00);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logistics_operations`
--

DROP TABLE IF EXISTS `logistics_operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logistics_operations` (
  `operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `personnel_name` varchar(100) NOT NULL,
  `personnel_email` varchar(100) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `role` enum('Driver','Coordinator','Dispatcher') NOT NULL,
  `task_type` enum('Supplies Delivery','Pet Transportation') NOT NULL,
  `item_description` text DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `vehicle_details` varchar(100) DEFAULT NULL,
  `request_date` date NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `status` enum('Pending','In Transit','Delivered','Failed') DEFAULT 'Pending',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`operation_id`),
  UNIQUE KEY `personnel_email` (`personnel_email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logistics_operations`
--

LOCK TABLES `logistics_operations` WRITE;
/*!40000 ALTER TABLE `logistics_operations` DISABLE KEYS */;
INSERT INTO `logistics_operations` VALUES
(1,'Alice Johnson','alice.johnson@example.com','1234567890','Driver','Supplies Delivery','Pet food supplies for shelter',101,201,'Van - ABC123','2024-12-08',NULL,'Pending','2024-12-08 15:32:03'),
(2,'Bob Smith','bob.smith@example.com','9876543210','Coordinator','Pet Transportation','Transport of a Labrador to vet clinic',102,202,'Truck - XYZ789','2024-12-09',NULL,'Pending','2024-12-08 15:32:03');
/*!40000 ALTER TABLE `logistics_operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` enum('Error','Info','Warning') NOT NULL,
  `event_message` text NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id` (`order_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `inventory_items` (`item_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `order_date` timestamp NULL DEFAULT current_timestamp(),
  `total_amount` decimal(10,2) NOT NULL,
  `status` enum('Pending','Shipped','Delivered','Cancelled') DEFAULT 'Pending',
  PRIMARY KEY (`order_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_health_records`
--

DROP TABLE IF EXISTS `pet_health_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_health_records` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `vet_id` int(11) DEFAULT NULL,
  `weight` decimal(5,2) NOT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `vaccinations` text DEFAULT NULL,
  `medical_conditions` text DEFAULT NULL,
  `wellness_score` int(11) DEFAULT NULL CHECK (`wellness_score` between 1 and 100),
  `last_checkup_date` date DEFAULT NULL,
  `next_checkup_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_health_records`
--

LOCK TABLES `pet_health_records` WRITE;
/*!40000 ALTER TABLE `pet_health_records` DISABLE KEYS */;
INSERT INTO `pet_health_records` VALUES
(1,1,101,201,18.50,45.00,'Rabies, Distemper, Parvovirus','None',85,'2024-10-15','2025-01-15','2024-12-08 17:56:57'),
(2,2,102,202,7.30,25.00,'Rabies, Parvovirus','Allergy to pollen',78,'2024-09-20','2025-02-20','2024-12-08 17:56:57'),
(3,3,103,203,30.00,60.00,'Distemper, Rabies','Hip dysplasia',70,'2024-08-10','2024-12-10','2024-12-08 17:56:57'),
(4,4,104,204,4.50,20.00,'Parvovirus','None',90,'2024-11-01','2025-05-01','2024-12-08 17:56:57'),
(5,5,105,205,12.00,35.00,'Rabies, Distemper','Obesity',65,'2024-07-15','2025-01-15','2024-12-08 17:56:57');
/*!40000 ALTER TABLE `pet_health_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pets`
--

DROP TABLE IF EXISTS `pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pets` (
  `pet_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `breed` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `gender` enum('Male','Female') DEFAULT NULL,
  `medical_history` text DEFAULT NULL,
  PRIMARY KEY (`pet_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `pets_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
INSERT INTO `pets` VALUES
(1,1,'Bear','jem-dks',5,50.00,'Male','good in health'),
(2,1,'newla','guk',3,30.00,'Female','posi'),
(3,1,'test','test1',12,34.00,'Female','slkdfj'),
(4,1,'try1','try2',23,40.00,'Male','best-n-best'),
(5,1,'aaa','aaa',10,12.00,'Male','sldkfjds'),
(6,1,'zzz','zzz',99,99.00,'Male','sldkjflskdj'),
(7,9,'testing','testing',4,10.00,'Female','weakness');
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_type` enum('Health','Sales','Appointments','Requests') NOT NULL,
  `generated_by` int(11) NOT NULL,
  `generated_at` timestamp NULL DEFAULT current_timestamp(),
  `content` text DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `generated_by` (`generated_by`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`generated_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriptions` (
  `subscription_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `frequency` enum('Weekly','Bi-Weekly','Monthly') NOT NULL,
  `status` enum('Active','Cancelled') DEFAULT 'Active',
  PRIMARY KEY (`subscription_id`),
  KEY `owner_id` (`owner_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `subscriptions_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `inventory_items` (`item_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_programs`
--

DROP TABLE IF EXISTS `training_programs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_programs` (
  `program_id` int(11) NOT NULL AUTO_INCREMENT,
  `trainer_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `duration_in_weeks` int(11) DEFAULT NULL,
  `fee` decimal(10,2) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`program_id`),
  KEY `trainer_id` (`trainer_id`),
  CONSTRAINT `training_programs_ibfk_1` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_programs`
--

LOCK TABLES `training_programs` WRITE;
/*!40000 ALTER TABLE `training_programs` DISABLE KEYS */;
INSERT INTO `training_programs` VALUES
(1,6,'Learn','Learn how to care a pet',20,2000.00,'2024-12-04','2024-12-05'),
(2,6,'new learn','new learning ',10,100.00,'2024-12-05','2024-12-05');
/*!40000 ALTER TABLE `training_programs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('PetOwner','Veterinarian','Groomer','Trainer','StoreManager','PlatformAdministrator','LogisticsPersonnel','InsuranceAgent','AdoptionCoordinator','NGOVolunteer') NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'test','test','PetOwner','test@gmail.com','8978685645','2024-12-04 00:15:17'),
(2,'test2','test2','Groomer','test2@gmail.com','2323232312','2024-12-04 00:22:10'),
(5,'test5','test5','StoreManager','ddr1@gmail.com','2323232323','2024-12-05 04:36:43'),
(6,'lll','lll','Trainer','lll@gmail.com','8987766756','2024-12-05 11:27:47'),
(7,'qqq','qqq','Veterinarian','qqq@gmail.com','2323344323','2024-12-05 14:04:02'),
(8,'ttt','ttt','Groomer','ttt@gmail.com','5655545445','2024-12-06 07:45:22'),
(9,'first','first','PetOwner','first@gmail.com','454545453434','2024-12-06 10:13:32'),
(10,'second','second','Veterinarian','second@gmail.com','4545453434','2024-12-06 10:15:12'),
(11,'admin','admin','PlatformAdministrator','admin!@gmail.com','1231231231','2024-12-08 13:02:42'),
(12,'ins','ins','InsuranceAgent','ins@gmail.com','1231231234','2024-12-08 14:27:16');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_requests`
--

DROP TABLE IF EXISTS `work_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_requests` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `request_type` enum('Organization','Enterprise') NOT NULL,
  `description` text DEFAULT NULL,
  `status` enum('Pending','In Progress','Completed','Rejected') DEFAULT 'Pending',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`request_id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  CONSTRAINT `work_requests_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `work_requests_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_requests`
--

LOCK TABLES `work_requests` WRITE;
/*!40000 ALTER TABLE `work_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_requests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-08 23:54:05
