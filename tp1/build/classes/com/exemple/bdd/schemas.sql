
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
-- Reconstruction de la base
--

DROP DATABASE IF EXISTS Cours_JEE_exemple2;
CREATE DATABASE Cours_JEE_exemple2;
USE Cours_JEE_exemple2;

--
-- Creation de la table Utilisateur
--

-- DROP TABLE IF EXISTS Utilisateur;

CREATE TABLE  Utilisateur (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 email VARCHAR( 60 ) NOT NULL ,
 mot_de_passe VARCHAR( 32 ) NOT NULL ,
 nom VARCHAR( 20 ) NOT NULL ,
 date_inscription DATETIME NOT NULL ,
 PRIMARY KEY ( id ),
 UNIQUE ( email )
) ENGINE = INNODB;

INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('user1@mail.acme', 'pass1', 'user1', NOW());
INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('user2@mail.acme', 'pass2', 'user2', NOW());

SELECT * FROM Utilisateur;