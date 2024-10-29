-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2024 at 04:26 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_tbl`
--

CREATE TABLE `admin_tbl` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `course_tbl`
--

CREATE TABLE `course_tbl` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `department_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_tbl`
--

INSERT INTO `course_tbl` (`course_id`, `course_name`, `department_name`) VALUES
(1, 'Computer Science', 'ICTE'),
(2, 'Information Technology', 'ICTE');

-- --------------------------------------------------------

--
-- Table structure for table `schedule_tbl`
--

CREATE TABLE `schedule_tbl` (
  `schedule_id` int(11) NOT NULL,
  `day` varchar(50) NOT NULL,
  `section_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schedule_tbl`
--

INSERT INTO `schedule_tbl` (`schedule_id`, `day`, `section_id`, `subject_id`, `start_time`, `end_time`) VALUES
(1, 'Thursday', 1, 3, '13:00:00', '16:30:00'),
(2, 'Thursday', 2, 1, '13:00:00', '16:30:00'),
(3, 'Friday', 1, 1, '13:00:00', '16:30:00'),
(4, 'Friday', 2, 3, '13:00:00', '16:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `section_tbl`
--

CREATE TABLE `section_tbl` (
  `section_id` int(11) NOT NULL,
  `section_name` varchar(50) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `section_tbl`
--

INSERT INTO `section_tbl` (`section_id`, `section_name`, `course_id`) VALUES
(1, 'CS101', 1),
(2, 'CS102', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_subject_tbl`
--

CREATE TABLE `student_subject_tbl` (
  `student_subject_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `archived` tinyint(1) DEFAULT 0,
  `section_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_subject_tbl`
--

INSERT INTO `student_subject_tbl` (`student_subject_id`, `student_id`, `subject_id`, `archived`, `section_id`) VALUES
(1, 1, 1, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `student_tbl`
--

CREATE TABLE `student_tbl` (
  `student_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `sex` char(1) NOT NULL,
  `year_level` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `section_id` int(11) NOT NULL,
  `archived` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_tbl`
--

INSERT INTO `student_tbl` (`student_id`, `first_name`, `last_name`, `birth_date`, `sex`, `year_level`, `course_id`, `section_id`, `archived`) VALUES
(1, 'Paul Marius', 'Simms', '2003-07-18', 'M', 4, 1, 1, 0),
(2, 'Crystal Kate', 'Aquino', '2002-08-06', 'F', 4, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `subject_tbl`
--

CREATE TABLE `subject_tbl` (
  `subject_id` int(11) NOT NULL,
  `subject_name` varchar(50) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject_tbl`
--

INSERT INTO `subject_tbl` (`subject_id`, `subject_name`, `course_id`) VALUES
(1, 'Network Technology', 1),
(2, 'Software Quality Assurance', 1),
(3, 'Data Privacy ', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_tbl`
--
ALTER TABLE `admin_tbl`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `course_tbl`
--
ALTER TABLE `course_tbl`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `schedule_tbl`
--
ALTER TABLE `schedule_tbl`
  ADD PRIMARY KEY (`schedule_id`),
  ADD KEY `section_id` (`section_id`),
  ADD KEY `fk_subject` (`subject_id`);

--
-- Indexes for table `section_tbl`
--
ALTER TABLE `section_tbl`
  ADD PRIMARY KEY (`section_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `student_subject_tbl`
--
ALTER TABLE `student_subject_tbl`
  ADD PRIMARY KEY (`student_subject_id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `section_id` (`section_id`);

--
-- Indexes for table `student_tbl`
--
ALTER TABLE `student_tbl`
  ADD PRIMARY KEY (`student_id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `section_id` (`section_id`);

--
-- Indexes for table `subject_tbl`
--
ALTER TABLE `subject_tbl`
  ADD PRIMARY KEY (`subject_id`),
  ADD KEY `course_id` (`course_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_tbl`
--
ALTER TABLE `admin_tbl`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course_tbl`
--
ALTER TABLE `course_tbl`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `schedule_tbl`
--
ALTER TABLE `schedule_tbl`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `section_tbl`
--
ALTER TABLE `section_tbl`
  MODIFY `section_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `student_subject_tbl`
--
ALTER TABLE `student_subject_tbl`
  MODIFY `student_subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `student_tbl`
--
ALTER TABLE `student_tbl`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `subject_tbl`
--
ALTER TABLE `subject_tbl`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `schedule_tbl`
--
ALTER TABLE `schedule_tbl`
  ADD CONSTRAINT `fk_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject_tbl` (`subject_id`),
  ADD CONSTRAINT `schedule_tbl_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `section_tbl` (`section_id`);

--
-- Constraints for table `section_tbl`
--
ALTER TABLE `section_tbl`
  ADD CONSTRAINT `section_tbl_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_tbl` (`course_id`);

--
-- Constraints for table `student_subject_tbl`
--
ALTER TABLE `student_subject_tbl`
  ADD CONSTRAINT `fk_section` FOREIGN KEY (`section_id`) REFERENCES `section_tbl` (`section_id`),
  ADD CONSTRAINT `student_subject_tbl_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_tbl` (`student_id`),
  ADD CONSTRAINT `student_subject_tbl_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject_tbl` (`subject_id`);

--
-- Constraints for table `student_tbl`
--
ALTER TABLE `student_tbl`
  ADD CONSTRAINT `student_tbl_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_tbl` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_tbl_ibfk_2` FOREIGN KEY (`section_id`) REFERENCES `section_tbl` (`section_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subject_tbl`
--
ALTER TABLE `subject_tbl`
  ADD CONSTRAINT `subject_tbl_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_tbl` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
