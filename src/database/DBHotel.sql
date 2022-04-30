-- create database era1;
-- use era1;



CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT primary key,
  `first_name` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(100),
  `password` date NOT NULL,
   `birthdate` date,
  `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` varchar(10) DEFAULT NULL,
   `location` varchar(100)
) ;


-- CREATE TABLE `admin` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `first_name` varchar(30) NOT NULL,
--   `username` varchar(30) NOT NULL,
--   `email` varchar(100),
--   `password` date NOT NULL,
--    `birthdate` date,
--   `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `gender` varchar(10) DEFAULT NULL,
--    `location` varchar(100)
--   PRIMARY KEY (`id`)
-- ) ;

-- CREATE TABLE `guests` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `first_name` varchar(20) NOT NULL,
--   `last_name` varchar(20) NOT NULL,
--   `personal_number` bigint(20) DEFAULT NULL,
--   `birthdate` date NOT NULL,
--   `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `gender` varchar(10) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ;

-- CREATE TABLE `staff` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `first_name` varchar(20) NOT NULL,
--   `last_name` varchar(20) NOT NULL,
--   `personal_number` int(11) DEFAULT NULL,
--   `position` varchar(20) NOT NULL,
--   `birthdate` date NOT NULL,
--   `phone_number` varchar(20) NOT NULL,
--   `salary` double DEFAULT NULL,
--   `password` varchar(200) DEFAULT NULL,
--   `gender` varchar(10) DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   CONSTRAINT `staff_chk_1` CHECK ((`salary` > 169))
-- ) ;



-- CREATE TABLE `payments` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `guest_id` int(11) NOT NULL,
--   staff_id int(11) NOT NULL,
--   `price` double NOT NULL,
--   `payment_method` varchar(30) NOT NULL,
--   `is_payed` tinyint(1) NOT NULL DEFAULT '0',
--   `pay_date` timestamp NULL DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `guest_id` (`guest_id`),
--   CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
--   CONSTRAINT payments_ibfk_2 FOREIGN KEY(staff_id) REFERENCES staff (id) ,
--   CONSTRAINT `payments_chk_1` CHECK ((`price` > 0))
-- );

-- CREATE TABLE `perdoruesit` (
--   `username` int(11) NOT NULL,
--   `hashedPassword` varchar(200) DEFAULT NULL,
--   PRIMARY KEY (`username`)
-- );

-- CREATE TABLE `rooms` (
--   `room_number` int(11) NOT NULL AUTO_INCREMENT,
--   `floor_number` int(11) NOT NULL,
--   `capacity` int(11) NOT NULL,
--   `bed_number` int(11) NOT NULL,
--   `room_type` varchar(20) NOT NULL,
--   `price` double DEFAULT NULL,
--   PRIMARY KEY (`room_number`),
--   CONSTRAINT `rooms_chk_1` CHECK ((`floor_number` > 0)),
--   CONSTRAINT `rooms_chk_2` CHECK ((`capacity` > 0)),
--   CONSTRAINT `rooms_chk_3` CHECK ((`bed_number` > 0)),
--   CONSTRAINT `rooms_chk_4` CHECK ((`price` > 0))
-- ) ;

-- CREATE TABLE `reservations` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `guest_id` int(11) NOT NULL,
--   `room_id` int(11) NOT NULL,
--   `reservation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `checkin_date` date DEFAULT NULL,
--   `checkout_date` date DEFAULT NULL,
--   `adults` int(11) DEFAULT '1',
--   `children` int(11) DEFAULT '1',
--   `payment_id` int(11) DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   KEY `guest_id` (`guest_id`),
--   KEY `room_id` (`room_id`),
--   KEY `payment_id` (`payment_id`),
--   CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
--   CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_number`),
--   CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
-- );

-- CREATE TABLE `services_type` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `service_name` varchar(40) NOT NULL,
--   `price` double DEFAULT NULL,
--   `quantity` int(11) NOT NULL,
--   PRIMARY KEY (`id`),
--   CONSTRAINT `services_type_chk_1` CHECK ((`price` > 0))
-- );


-- CREATE TABLE `services` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `guest_id` int(11) NOT NULL,
--   `service_id` int(11) NOT NULL,
--   `quantity` int(11) NOT NULL,
--   `payment_id` int(11) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `guest_id` (`guest_id`),
--   KEY `service_id` (`service_id`),
--   KEY `payment_id` (`payment_id`),
--   CONSTRAINT `services_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
--   CONSTRAINT `services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services_type` (`id`),
--   CONSTRAINT `services_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
--   CONSTRAINT `services_chk_1` CHECK ((`quantity` > 0))
-- ) ;
