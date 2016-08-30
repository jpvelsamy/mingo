DROP DATABASE mingo_txn;
CREATE DATABASE mingo_txn;
USE mingo_txn;
DROP TABLE IF EXISTS `mingo_txn`.`flight_plan`;
CREATE TABLE `flight_plan` (
  `flight_plan_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `flight_start_date` datetime DEFAULT NULL,
  `flight_end_date` datetime DEFAULT NULL,
  `is_multi_city_stop` tinyint(1) DEFAULT NULL,
  `first_origin` varchar(150) DEFAULT NULL,
  `last_destination` varchar(150) DEFAULT NULL,
  `seat_count` mediumint(3) DEFAULT NULL,
  `public_seat_count` mediumint(3) DEFAULT NULL,
  `captive_seat_count` mediumint(3) DEFAULT NULL,
  `reserved_seat_count` mediumint(3) DEFAULT NULL,
  `reserved_seat_count_public` mediumint(3) DEFAULT NULL,
  `reserved_seat_count_captive` mediumint(3) DEFAULT NULL,
  PRIMARY KEY (`flight_plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mingo_txn`.`flight_plan_detail`;
CREATE TABLE `flight_plan_detail` (
  `flight_plan_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `origin` varchar(150) DEFAULT NULL,
  `destination` varchar(150) DEFAULT NULL,
  `seat_count` mediumint(3) unsigned DEFAULT NULL,
  `fare` int(11) DEFAULT NULL,
  `flight_plan_id` int(10) unsigned DEFAULT NULL,
  `reserved_seat_count` mediumint(3) DEFAULT NULL,
  `flight_arrival_time` timestamp NULL DEFAULT NULL,
  `flight_departure_time` timestamp NULL DEFAULT NULL,
  `created_date` timestamp DEFAULT current_timestamp,
  `updated_date` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`flight_plan_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `mingo_txn`.`ticket_pool`;
CREATE TABLE `ticket_pool` (
  `ticket_pool_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `seat_number` int(5) DEFAULT NULL,
  `row_number` mediumint(3) DEFAULT NULL,
  `is_window` tinyint(1) DEFAULT NULL,
  `class` varchar(15) DEFAULT NULL,
  `seat_side` enum('left','right') DEFAULT NULL,
  `reservation_status` varchar(25) DEFAULT NULL,
  `flight_plan_id` int(10) unsigned DEFAULT NULL,
  `flight_plan_detail_id` int(10) unsigned DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `full_name` varchar(150) DEFAULT NULL,
  `primary_passenger` enum('yes','no'),
  `email` varchar(75) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT '0000-00-00 00:00:00',  
  PRIMARY KEY (`ticket_pool_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


CREATE TABLE `ticket_pool_archive` (
  `ticket_pool_id` int(10) unsigned NOT NULL ,
  `seat_number` int(5) DEFAULT NULL,
  `row_number` mediumint(3) DEFAULT NULL,
  `is_window` tinyint(1) DEFAULT NULL,
  `class` varchar(15) DEFAULT NULL,
  `seat_side` enum('left','right') DEFAULT NULL,
  `reservation_status` varchar(25) DEFAULT NULL,
  `flight_plan_id` int(10) unsigned DEFAULT NULL,
  `flight_plan_detail_id` int(10) unsigned DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `full_name` varchar(150) DEFAULT NULL,
  `primary_passenger` enum('yes','no'),
  `email` varchar(75) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT '0000-00-00 00:00:00'   
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE `reservation` (
  `reservation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `credit_card_number` varchar(19) DEFAULT NULL,
  `credit_card_holder` varchar(150) DEFAULT NULL,  
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;