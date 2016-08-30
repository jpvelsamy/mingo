drop database mingo_sor;
create database mingo_sor;
use mingo_sor;

drop table if exists mingo_sor.reservation;
create table mingo_sor.reservation
(
	`reservation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`user_id` int(10) unsigned DEFAULT NULL, 
	`created_date` timestamp DEFAULT current_timestamp,
  	`updated_date` datetime DEFAULT '0000-00-00 00:00:00', 
	 PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists mingo_sor.reservation_lineitem;
create table mingo_sor.reservation_lineitem
(
	`reservation_lineitem_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`ticket_pool_id` int(10) unsigned,
	`seat_number` int(5) DEFAULT NULL,
	`row_number` mediumint(3) DEFAULT NULL,
	`is_window` tinyint(1) DEFAULT NULL,
	`class` varchar(15) DEFAULT NULL,
	`seat_side` enum('left','right') DEFAULT NULL,
	`reservation_status` varchar(25) DEFAULT NULL,	
	`first_name` varchar(100),  
	`second_name` varchar(100),  
	`email_id` varchar(100),  
	`age` mediumint(2) DEFAULT NULL,  
	`gender` enum('trans','female','male'),  	
	`created_date` timestamp DEFAULT current_timestamp,
  	`updated_date` datetime DEFAULT '0000-00-00 00:00:00',
  	`reservation_id` int(10) unsigned NOT NULL,
  	PRIMARY KEY (`reservation_lineitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists mingo_sor.transaction_audit;
create table mingo_sor.transaction_audit
(
transaction_audit_id int(10) unsigned NOT NULL AUTO_INCREMENT,
transaction_use_case varchar(100) default null,
transaction_entity varchar(50),
transaction_entity_identifier varchar(50),
transaction_error_information text,
created_date timestamp DEFAULT current_timestamp,
updated_date datetime DEFAULT '0000-00-00 00:00:00',
PRIMARY KEY (`transaction_audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists mingo_sor.registered_user;
create table mingo_sor.registered_user
(
user_id int(10) unsigned NOT NULL AUTO_INCREMENT,
email_id varchar(150) not null,
passwd varchar(50) default null,
authtype enum('open','captive'),
created_date timestamp DEFAULT current_timestamp,
updated_date datetime DEFAULT '0000-00-00 00:00:00',
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists mingo_sor.user_session;
create table mingo_sor.user_session
(
session_id int(10) unsigned NOT NULL AUTO_INCREMENT,
user_id int(10) unsigned NOT NULL,
login_start_timestamp timestamp DEFAULT current_timestamp,
PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;