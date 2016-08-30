TRUNCATE `mingo_txn`.`flight_plan`;
TRUNCATE `mingo_txn`.`flight_plan_detail`;
TRUNCATE `mingo_txn`.`ticket_pool`;

INSERT INTO `mingo_txn`.`flight_plan` (`flight_plan_id`, `flight_start_date`, `flight_end_date`, `is_multi_city_stop`, `first_origin`, `last_destination`, `seat_count`, `public_seat_count`, `captive_seat_count`) 
VALUES 
('1', '2016-09-20 08:00:00', '2016-09-20 16:30:00', '1', 'new york', 'san francisco', '12', '8', '4');

INSERT INTO `mingo_txn`.`flight_plan_detail` (`flight_plan_detail_id`, `origin`, `destination`, `seat_count`, `fare`, `flight_plan_id`, `flight_arrival_time`, `flight_departure_time`) VALUES 
('1', 'New york', 'Denver', '2', '100', '1', '2016-09-20 08:00:00', '2016-09-20 12:00:00');

INSERT INTO `mingo_txn`.`flight_plan_detail` (`flight_plan_detail_id`, `origin`, `destination`, `seat_count`, `fare`, `flight_plan_id`, `flight_arrival_time`, `flight_departure_time`) VALUES 
('2', 'Denver', 'New York', '2', '50', '1', '2016-09-20 12:15:00', '2016-09-20 16:30:00');

INSERT INTO `mingo_txn`.`flight_plan_detail` (`flight_plan_detail_id`, `origin`, `destination`, `seat_count`, `fare`, `flight_plan_id`, `flight_arrival_time`, `flight_departure_time`) VALUES 
('3', 'New York', 'San Francisco', '8', '140', '1', '2016-09-20 08:00:00', '2016-09-20 16:30:00');

INSERT INTO `mingo_txn`.`ticket_pool`
(`seat_number`,`row_number`,`is_window`,`class`,`seat_side`,`reservation_status`,`created_date`,`updated_date`,`flight_plan_id`,`flight_plan_detail_id`)
VALUES
(1,1,1,'business','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(2,1,1,'business','right','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(3,2,1,'business','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(4,2,1,'business','right','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(5,3,1,'economy','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(6,3,0,'economy','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(7,3,0,'economy','right','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(8,3,1,'economy','right','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(9,4,1,'economy','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(10,4,0,'economy','left','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(11,4,0,'economy','right','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null),
(12,4,1,'economy','right','open','2016-09-20 00:00:00', '2016-09-20 08:00:00', 1, null);






