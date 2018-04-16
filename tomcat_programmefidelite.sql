-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 02, 2018 at 03:11 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tomcat_programmefidelite`
--
CREATE DATABASE IF NOT EXISTS `tomcat_programmefidelite` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tomcat_programmefidelite`;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `numero` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`numero`) VALUES
('64684DGDY'),
('ASF9846'),
('CT12345'),
('D5846DG'),
('GDS56'),
('MC123');

-- --------------------------------------------------------

--
-- Table structure for table `client_succursalemembre`
--

DROP TABLE IF EXISTS `client_succursalemembre`;
CREATE TABLE `client_succursalemembre` (
  `id` int(11) NOT NULL,
  `numero_client` varchar(255) CHARACTER SET utf8 NOT NULL,
  `id_succursale` int(11) NOT NULL,
  `soldeArgent_client` double NOT NULL,
  `soldePoints_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_succursalemembre`
--

INSERT INTO `client_succursalemembre` (`id`, `numero_client`, `id_succursale`, `soldeArgent_client`, `soldePoints_client`) VALUES
(1, 'CT12345', 5, 4.5, 450),
(2, 'MC123', 6, 0, 0),
(3, 'ASF9846', 7, 0, 0),
(4, '64684DGDY', 8, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `succursalemembre`
--

DROP TABLE IF EXISTS `succursalemembre`;
CREATE TABLE `succursalemembre` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `tauxRemise` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `succursalemembre`
--

INSERT INTO `succursalemembre` (`id`, `nom`, `tauxRemise`) VALUES
(5, 'Canadian Tire', 0.01),
(6, 'Walmart', 0.02),
(7, 'Rona', 0.01),
(8, 'Simons', 0.015);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`numero`);

--
-- Indexes for table `client_succursalemembre`
--
ALTER TABLE `client_succursalemembre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_client` (`numero_client`),
  ADD UNIQUE KEY `id_succursale` (`id_succursale`);

--
-- Indexes for table `succursalemembre`
--
ALTER TABLE `succursalemembre`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client_succursalemembre`
--
ALTER TABLE `client_succursalemembre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `succursalemembre`
--
ALTER TABLE `succursalemembre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `client_succursalemembre`
--
ALTER TABLE `client_succursalemembre`
  ADD CONSTRAINT `client_FK` FOREIGN KEY (`numero_client`) REFERENCES `client` (`numero`),
  ADD CONSTRAINT `succursale_FK` FOREIGN KEY (`id_succursale`) REFERENCES `succursalemembre` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE USER 'programmeFideliteUser'@'%' IDENTIFIED BY 'programmeFidelitePassword';
grant all privileges on 'tomcat\_%'.* to 'programmeFideliteUser'@'%';
