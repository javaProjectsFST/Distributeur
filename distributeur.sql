-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2016 at 10:35 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `distributeur`
--

-- --------------------------------------------------------

--
-- Table structure for table `boisson`
--

CREATE TABLE `boisson` (
  `ID` int(11) NOT NULL,
  `boissonName` text NOT NULL,
  `price` int(11) NOT NULL,
  `imgPath` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `boisson`
--

INSERT INTO `boisson` (`ID`, `boissonName`, `price`, `imgPath`) VALUES
(1, 'coke', 1500, 'resources/boissons/coke.png'),
(2, 'water', 1000, 'resources/boissons/water.png'),
(3, 'fanta', 1450, 'resources/boissons/fanta.png'),
(4, 'cidre', 1600, 'resources/boissons/cidre.png'),
(5, 'petillante', 1000, 'resources/boissons/petillante.png'),
(6, 'boga', 1500, 'resources/boissons/boga.png');

-- --------------------------------------------------------

--
-- Table structure for table `earned`
--

CREATE TABLE `earned` (
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `earned`
--

INSERT INTO `earned` (`value`) VALUES
(25400);

-- --------------------------------------------------------

--
-- Table structure for table `money`
--

CREATE TABLE `money` (
  `value` double NOT NULL,
  `imgPath` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `money`
--

INSERT INTO `money` (`value`, `imgPath`) VALUES
(50, 'resources/pieces/50.png'),
(100, 'resources/pieces/100.png'),
(200, 'resources/pieces/200.png'),
(500, 'resources/pieces/500.png'),
(1000, 'resources/pieces/1000.png');

-- --------------------------------------------------------

--
-- Table structure for table `sandwich`
--

CREATE TABLE `sandwich` (
  `ID` int(11) NOT NULL,
  `sandwichName` text NOT NULL,
  `price` int(11) NOT NULL,
  `imgPath` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sandwich`
--

INSERT INTO `sandwich` (`ID`, `sandwichName`, `price`, `imgPath`) VALUES
(1, 'sandwichBoeuf', 4500, 'resources/sandwichs/Boeuf.png'),
(2, 'paniniOmelette', 2700, 'resources/sandwichs/PaniniOmelette.png'),
(3, 'PaniniBoeuf', 3650, 'resources/sandwichs/PaniniBoeuf.png'),
(4, 'PaniniSalami', 2800, 'resources/sandwichs/PaniniSalami.png'),
(5, 'SandwichOmelette', 3000, 'resources/sandwichs/SandwichOmelette.png'),
(6, 'SandwichSalade', 1850, 'resources/sandwichs/SandwichSalade.png');

-- --------------------------------------------------------

--
-- Table structure for table `stockboisson`
--

CREATE TABLE `stockboisson` (
  `ID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockboisson`
--

INSERT INTO `stockboisson` (`ID`, `quantity`) VALUES
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3),
(6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `stocksandwich`
--

CREATE TABLE `stocksandwich` (
  `ID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stocksandwich`
--

INSERT INTO `stocksandwich` (`ID`, `quantity`) VALUES
(1, 1),
(2, 0),
(3, 2),
(4, 2),
(5, 3),
(6, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `boisson`
--
ALTER TABLE `boisson`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `earned`
--
ALTER TABLE `earned`
  ADD PRIMARY KEY (`value`);

--
-- Indexes for table `money`
--
ALTER TABLE `money`
  ADD PRIMARY KEY (`value`);

--
-- Indexes for table `sandwich`
--
ALTER TABLE `sandwich`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `stockboisson`
--
ALTER TABLE `stockboisson`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `stocksandwich`
--
ALTER TABLE `stocksandwich`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `boisson`
--
ALTER TABLE `boisson`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `sandwich`
--
ALTER TABLE `sandwich`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `stockboisson`
--
ALTER TABLE `stockboisson`
  ADD CONSTRAINT `stockboisson_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `boisson` (`ID`);

--
-- Constraints for table `stocksandwich`
--
ALTER TABLE `stocksandwich`
  ADD CONSTRAINT `stocksandwich_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `sandwich` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
