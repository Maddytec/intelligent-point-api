-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 10.129.76.12
-- Generation Time: 01-Jan-2019 às 10:04
-- Versão do servidor: 5.6.26-log
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `intelligent_db`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
`id` bigint(20) NOT NULL,
  `count_hours_lunch` float DEFAULT NULL,
  `count_hours_work_day` float DEFAULT NULL,
  `date_create` datetime DEFAULT NULL,
  `date_update` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `number_document_employee` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `value_hour` decimal(19,2) DEFAULT NULL,
  `employer_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-----------------------------------------------------------

--
-- Estrutura da tabela `employer`
--

CREATE TABLE IF NOT EXISTS `employer` (
`id` bigint(20) NOT NULL,
  `date_create` datetime DEFAULT NULL,
  `date_update` datetime DEFAULT NULL,
  `employer_name` varchar(255) DEFAULT NULL,
  `number_document_employer` varchar(255) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-----------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3),
(3),
(3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `register`
--

CREATE TABLE IF NOT EXISTS `register` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `date_create` datetime DEFAULT NULL,
  `date_update` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile` varchar(255) NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
 ADD PRIMARY KEY (`id`), ADD KEY `FKrmknjkdeoo3molal1gxkt9dar` (`employer_id`);

--
-- Indexes for table `employer`
--
ALTER TABLE `employer`
 ADD PRIMARY KEY (`id`);
--
-- Indexes for table `register`
--
ALTER TABLE `register`
 ADD PRIMARY KEY (`id`), ADD KEY `FK27yj934o5cr7royh64xm6pihr` (`employee_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `employer`
--
ALTER TABLE `employer`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

----------------------------------------------------------------------------
--
-- Insert of employer default
INSERT INTO employer (number_document_employer, date_update, date_create, employer_name)
VALUE ('02199568000109', CURRENT_DATE(), CURRENT_DATE(), 'MADDYTEC Tecnologia ao seu alcance');
--
--
-- Insert of employee default 
INSERT INTO employee (number_document_employee, date_update, date_create, email, employee_name,
 profile, count_hours_lunch, count_hours_work_day, password, value_hour, employer_id)
 VALUE ('63085993010', CURRENT_DATE(), CURRENT_DATE(), 'madson.silva@maddytec.com.br', 'MADSON SILVA', 'ROLE_ADMIN', NULL, NULL, 
 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWRkeXRlY0BnbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9BRE1JTiIsImNyZWF0ZWQiOjE1NDYyMTc0MTY3ODYsImV4cCI6MTU0NjgyMjIxNn0.ZWiOzm5dsMGevRFPlizklbsaAM8yvP0AgzljhGp7eIBbjxgEJQcoFrJtOu7H9QqhRZGHNQwtQoJBu2_dRScS2w',
  NULL, (SELECT id FROM employer WHERE number_document_employer = '02199568000109'));

