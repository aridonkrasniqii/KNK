create database DBHotel;

use DBHotel;

drop table if exists registerGuests;
CREATE TABLE `registerGuests` (
  `id` int(11) NOT NULL AUTO_INCREMENT primary key,
  `first_name` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(100),
  `password` varchar(100) NOT NULL,
   `birthdate` varchar(100),
  `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` varchar(10) DEFAULT NULL,
   `location` varchar(100)
);

drop table if exists admin;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT primary key,
  `first_name` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(100),
  `password` varchar(100) NOT NULL,
   `birthdate` varchar(100),
  `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` varchar(10) DEFAULT NULL,
   `location` varchar(100)
);

insert into admin() values(1, 'admin','admin','admin@gmail.com','admin','2002-07-10',now(),'Male','Kosovo');

alter table admin
rename column registred_date to registered_date;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
	 id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	 name VARCHAR(50) NOT NULL,
	 email VARCHAR(50) NOT NULL UNIQUE,
	 username varchar(50) NOT NULL UNIQUE,
	 password VARCHAR(255) NOT NULL,
	 salt VARCHAR(255) NOT NULL,
	 role CHAR(1) NOT NULL,
	 isActive BIT NOT NULL DEFAULT 1,
	 createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
 );

select * from users;

-- admin user
insert into users() values(1,'admin','admin@hotmail.com','admin','7c63395911f085cf3a8144538e526e5e77de19b16e671c8bbac31e62f77be4be','79492994','A',1,now(),now());

CREATE TABLE `guests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `personal_number` bigint(20) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;


drop table if exists staff;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `personal_number` int(11) DEFAULT NULL,
  `position` varchar(100) NOT NULL,
  `birthdate` date NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `salary` double DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(200) default null,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `staff_chk_1` CHECK ((`salary` > 169))
) ;




drop table if exists payments;
CREATE TABLE `payments` (
  `id` integer NOT NULL AUTO_INCREMENT,
  `guest_id` integer NOT NULL,
  `staff_id` integer NOT NULL,
  `price` double NOT NULL,
  `payment_method` varchar(30) NOT NULL,
  `is_payed` tinyint(1) NOT NULL DEFAULT '0',
  `pay_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT payments_ibfk_2 FOREIGN KEY(staff_id) REFERENCES staff (id) ,
  CONSTRAINT `payments_chk_1` CHECK ((`price` > 0))
);


CREATE TABLE `perdoruesit` (
  `username` int(11) NOT NULL,
  `hashedPassword` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`username`)
);


drop table if exists `rooms`;
CREATE TABLE `rooms` (
  `room_number` int(11) NOT NULL AUTO_INCREMENT,
  `floor_number` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `bed_number` int(11) NOT NULL,
  `room_type` varchar(20) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`room_number`),
  CONSTRAINT `rooms_chk_1` CHECK ((`floor_number` > 0)),
  CONSTRAINT `rooms_chk_2` CHECK ((`capacity` > 0)),
  CONSTRAINT `rooms_chk_3` CHECK ((`bed_number` > 0)),
  CONSTRAINT `rooms_chk_4` CHECK ((`price` > 0))
) ;

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `reservation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `adults` int(11) DEFAULT '1',
  `children` int(11) DEFAULT '1',
  `payment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  KEY `room_id` (`room_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_number`),
  CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
);

CREATE TABLE `services_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(40) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `services_type_chk_1` CHECK ((`price` > 0))
);


CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  KEY `service_id` (`service_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `services_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT `services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services_type` (`id`),
  CONSTRAINT `services_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
  CONSTRAINT `services_chk_1` CHECK ((`quantity` > 0))
) ;

create view paymentmodal(payment_id , firstname, lastname, date, price, ispayed) as
	select p.id as 'payment_id', r.first_name, r.username ,p.pay_date as 'pay_date' , p.price  ,p.is_payed from registerGuests r
	inner join payments p on p.guest_id = r.id;


CREATE TABLE `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `organizer` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `price` double DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,

  PRIMARY KEY (`id`)
) ;

INSERT INTO events VALUES (1,"event", "org", "cat", 100.2, '2020-11-22', '2020-12-20');
