-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: shop_db
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Phones'),(2,'Notebooks'),(3,'Tablets'),(4,'eReaders');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `id_option` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(12) NOT NULL,
  PRIMARY KEY (`id_option`),
  UNIQUE KEY `options_id_option_uindex` (`id_option`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'popular');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `id_product` int(11) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` varchar(45) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_order`),
  KEY `id_product_idx` (`id_product`),
  KEY `id_user` (`login`),
  CONSTRAINT `id_product` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  CONSTRAINT `id_user` FOREIGN KEY (`login`) REFERENCES `users` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (299,'user',3,'27.10.2017',1,'1700',2),(306,'user',24,'28.10.2017',1,'1700',2),(307,'user',25,'29.10.2017',1,'120',2),(325,'admin',17,'01.11.2017',1,'1700',2),(326,'admin',15,'01.11.2017',4,'199',2),(327,'admin',19,'05.11.2017',5,'459',2),(328,'admin',1,'05.11.2017',2,'100',2),(329,'admin',2,'05.11.2017',2,'120',2);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(500) NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `brand` varchar(100) NOT NULL,
  `screen` varchar(25) NOT NULL,
  `os` varchar(25) NOT NULL,
  `storage` varchar(25) NOT NULL,
  `rating` int(11) DEFAULT '0',
  `p_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_product`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Galaxy s 10','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/p8.png',1,47,'Samsung','5','Android','32',3,1),(2,'Galaxy S8','Android, displaysdf sdfsdf sdf sdf','/shop/img/p1.png',1,48,'Samsung','5','Android','34',4,2),(3,'Redmi 4 Pro','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p2.png',1,52,'Xiaomi','5.5','Flyme','63',3,5),(4,'Iphone 8','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p3.png',1,55,'Apple','5.5','iOS','120',4,6),(5,'Redmi 5','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/p4.png',1,54,'Samsung','6','Android','32',2,3),(6,'Iphone 6','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p5.png',1,55,'Apple','4','iOS','34',5,10),(7,'Xperia L','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p6.png',1,55,'Sony','4.5','Android','12',2,5),(9,'Note8','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p7.png',1,55,'Samsung','4.2','Android','12',3,11),(10,'Galaxy S8','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/p9.png',1,55,'Samsung','4','Android','12',3,4),(11,'Iphone V3','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p10.png',1,55,'Apple','5','iOS','12',2,12),(12,'Iphone X','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p11.png',1,49,'Apple','4.5','iOS','12',4,5),(13,'aPhone','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/p12.png',1,55,'Apple','5.5','iOS','32',2,5),(14,'Idea Pad','Android, display 5.8 AMOLED (1440x2960), RAM 4 GB, Flash 64 Gb, camera 12 Mp','/shop/img/tablets/t7.png',3,40,'Lenovo','8','Android','45',3,7),(15,'Galaxy Tab E','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/tablets/t8.png',3,14,'Samsung','9.6','Android','16',2,5),(16,'Fire 7','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/tablets/t9.png',3,20,'Amazon','7','Linux','16',5,8),(17,'iPad','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/tablets/t10.png',3,51,'Apple','7','iOS','12',2,3),(18,'Surface Pro','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/tablets/t11.png',3,49,'Microsoft','8','Windows 10','12',1,5),(19,'Inspiron 545','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/notebooks/n1.jpg',2,30,'Dell','15.6','Windows 10','500',1,9),(20,'MacBook Air','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/notebooks/n2.jpg',2,43,'Apple','14.0','Mac OS','1000',1,13),(21,'Chromebook','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/notebooks/n7.jpg',2,44,'HP','15.6','Linux','2000',5,5),(22,'Touch-Screen Laptop','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/notebooks/n4.jpg',2,51,'Asus','15.6','Linux','1000',3,6),(23,'Fire 7','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/readers/r1.jpg',4,47,'Amazon','7','Fire OS','32',3,1),(24,'Kindle','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/readers/r2.jpg',4,120,'Amazon','8','Android','34',5,7),(25,'Glo HD','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/readers/r3.jpg',4,52,'Kobo','7','iOS','63',0,2),(27,'Note S5','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/p13.png',1,54,'Samsung','5','Android','32',1,3),(28,'Redmi 4','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/p14.png',1,54,'Xiaomi','4.5','Android','34',3,8),(29,'Iphone 12','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/p17.png',1,54,'Apple','5.5','iOS','23',1,4),(32,'Iphone 1','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/p15.png',1,54,'Apple','5','iOS','12',1,10),(33,'Galaxy S10','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/p2.png',1,52,'Samsung','5','Android','50',1,9),(40,'Canvas 2','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/p16.png',1,55,'Micromax','4.2','Android','12',0,11),(76,'TM-5005','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/p7.png',1,12,'TeXet','4','Android','35',0,12),(77,'iPhone 8','All-new dual 12MP cameras. The brightest, most colorful iPhone display ever. The fastest performance and best battery life in an iPhone. Water and splash resistant.* And stereo speakers.','/shop/img/p4.png',1,12,'Apple','4','iOS','4',0,12),(78,'Flex 5','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/p1.png',1,12,'Samsung','5','Android','4',0,13),(79,'MX-5','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/p9.png',1,60,'Meizu','5.5','Flyme 5.6','2',2,12),(81,'8','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/p10.png',1,134,'Nokia','5','Windows OS','32',5,7),(82,'iPad 2017','8MP camera, 1.2MP FaceTime HD camera, 1080p HD video, 2048Ã1536 resolution','/shop/img/tablets/t9.png',3,25,'Apple','9.5','iOS 10','32',0,23),(83,'Fire 7','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/tablets/t8.png',4,25,'Amazon','8','Alexa','16',0,24),(84,'Pixel','Meet Pixel The power of Google, at your fingertips. Every touch, every interaction, every moment â made easy. Comes with a 5-inch FHD AMOLED display, 2770 mAh battery. The Google Pixel comes in 32.','/shop/img/p5.png',1,14,'Google','5','Android Nougat 7.1','32',4,65),(85,'MadDesire','Beautiful 7inch IPS display with higher contrast and sharper text, a 1.3 GHz quad-core processor, and up to 8 hours of battery life. 8 or 16 GB of internal storage and a microSD slot for up to 256 GB of expandable storage.','/shop/img/Iphone8Plus256gb.png',1,12,'Prestigio','5.5','Windows OS','6',0,12),(86,'M 83','NiceOne smartphone. IKBdkjasnd ddnkjef wef wdqwedqexd wefwe e','/shop/img/Nokia8.png',1,13,'Nokia ','5','Android','32',0,12),(87,'Aura H2O','7 High-Resolution Display (300 ppi), Waterproof, Built-In Audible, 32GB, Wi-Fi   Free Cellular','/shop/img/Kobo.jpg',4,14,'Kobo','6.8','Kindle','8',0,12);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT '1',
  `dob` varchar(45) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin','admin@mail.com','2','24.07.1994'),('bobel','bobel','bobel@mail.ru','1','123123'),('bread','bread','bread@mail.ru','1','12.12.1202'),('user','user','user@mail.ru','1','24.09.1997'),('Vasya','vasya','vasya@mail.ru','1','24.12.2001');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-23 10:19:03
