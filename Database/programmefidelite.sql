-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 19 Février 2018 à 14:35
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `programmefidelite`
--
CREATE DATABASE IF NOT EXISTS `programmefidelite` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `programmefidelite`;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `numero` varchar(255) NOT NULL,
  `soldePoints` int(11) NOT NULL,
  `soldeArgent` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`numero`, `soldePoints`, `soldeArgent`) VALUES
('12345', 1000, 10),
('345677', 2560, 25.6);

-- --------------------------------------------------------

--
-- Structure de la table `succursalemembre`
--

DROP TABLE IF EXISTS `succursalemembre`;
CREATE TABLE `succursalemembre` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `tauxRemise` double NOT NULL DEFAULT '0.01'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `succursalemembre`
--

INSERT INTO `succursalemembre` (`id`, `nom`, `tauxRemise`) VALUES
(1, 'Pizza Hub', 0.01),
(2, 'MAC Donald\'s', 0.05),
(3, 'Burger Ping', 0.02),
(6, 'Float Pizza', 0.01);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`numero`);

--
-- Index pour la table `succursalemembre`
--
ALTER TABLE `succursalemembre`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
