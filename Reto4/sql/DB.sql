-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: reto4bd
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

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
-- Table structure for table `abilities`
--

DROP TABLE IF EXISTS `abilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abilities` (
  `id` int(11) NOT NULL,
  `champion_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `champion_id` (`champion_id`),
  CONSTRAINT `abilities_ibfk_1` FOREIGN KEY (`champion_id`) REFERENCES `champions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abilities`
--

LOCK TABLES `abilities` WRITE;
/*!40000 ALTER TABLE `abilities` DISABLE KEYS */;
INSERT INTO `abilities` VALUES (1,1,'Flecha de hielo','Ashe dispara una flecha que inflige daño y ralentiza al enemigo alcanzado.'),(2,1,'Descarga de escarcha','Ashe lanza una onda de frío que daña a los enemigos cercanos y ralentiza su velocidad de ataque.'),(3,1,'Enfoque cristalino','Ashe aumenta su daño crítico y su velocidad de ataque durante un corto período de tiempo.'),(4,1,'Flecha de cristal encantada','Ashe dispara una flecha que atraviesa a los enemigos y los ralentiza.'),(5,2,'Estrella ninja','Zed lanza una estrella ninja que inflige daño a los enemigos alcanzados.'),(6,2,'Sombra viviente','Zed se desplaza hacia una sombra que ha creado previamente, infligiendo daño a los enemigos cercanos.'),(7,2,'Cuchillada sombría','Zed ataca con sus cuchillas sombrías, infligiendo daño a los enemigos alcanzados.'),(8,2,'Marca de la muerte','Zed marca a un enemigo, infligiendo daño adicional cuando lo ataca de nuevo después de un corto período de tiempo.'),(9,4,'Golpe abrumador','Mordekaiser inflige daño físico a los enemigos cercanos y reduce su velocidad de movimiento.'),(10,4,'Tormenta de metal','Mordekaiser inflige daño mágico a los enemigos cercanos y cura una cantidad de su vida. Además, crea un escudo que absorbe daño.'),(11,4,'Reino de la muerte','Mordekaiser arrastra a un enemigo al reino de la muerte, lo que le permite luchar contra él en un duelo uno contra uno. Si Mordekaiser mata al enemigo, obtiene su alma y puede invocar a su espíritu para que luche a su lado durante un breve período de tiempo.'),(12,4,'Poder de las sombras','Si Mordekaiser golpea a un enemigo con sus habilidades, obtiene un escudo que absorbe daño y aumenta su poder.');
/*!40000 ALTER TABLE `abilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `champions`
--

DROP TABLE IF EXISTS `champions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `champions` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL,
  `attack_damage` int(11) DEFAULT NULL,
  `ability_power` int(11) DEFAULT NULL,
  `life` int(11) DEFAULT NULL,
  `mana` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `champions`
--

LOCK TABLES `champions` WRITE;
/*!40000 ALTER TABLE `champions` DISABLE KEYS */;
INSERT INTO `champions` VALUES (1,'Ashe','tirador',1,59,0,539,280),(2,'Zed','asesino',3,63,0,584,200),(3,'Ezreal','tirador',3,60,90,400,250),(4,'Mordekaiser','luchador',2,75,40,650,200);
/*!40000 ALTER TABLE `champions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matches` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `result` varchar(10) NOT NULL,
  `champion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `champion` (`champion`),
  CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`champion`) REFERENCES `champions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password_hash` varchar(100) NOT NULL,
  `registration_date` date NOT NULL,
  `level` int(11) NOT NULL,
  `rank` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'johndoe','password123','2022-01-01',10,'Silver'),(2,'janesmith','secret456','2022-01-02',15,'Gold'),(3,'bobjohnson','qwerty789','2022-01-03',20,'Platinum'),(4,'Faker','fakerpass','2022-01-04',25,'Diamond');
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-19 12:54:52
