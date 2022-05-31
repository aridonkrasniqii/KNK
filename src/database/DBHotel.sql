create database DBHotel;

use DBHotel;



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


-- admin user
insert into users() values(1,'admin','admin@hotmail.com','admin','7c63395911f085cf3a8144538e526e5e77de19b16e671c8bbac31e62f77be4be','79492994','A',1,now(),now());







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

insert into rooms() values(1 , 1 , 3 , 3 , "Triple" , 200 );
insert into rooms() values(2 , 1 , 2 , 2 , "Double" , 150 );
insert into rooms() values(3 , 2 , 3 , 3 , "Triple" , 140 );
insert into rooms() values(4 , 3 , 3 , 3 , "Triple" , 200 );
insert into rooms() values(5 , 2 , 2 , 2 , "Double" , 250 );
insert into rooms() values(6 , 2 , 4 , 4 , "Quad" , 300 );
insert into rooms() values(7 , 1 , 4 , 4 , "Quad" , 300 );
insert into rooms() values(8 , 3 , 4 , 4 , "Quad" , 300 );
insert into rooms() values(9 , 3 , 5 , 5 , "Suite" , 400 );
insert into rooms() values(10 , 3 , 5 , 5 , "Suite" , 400 );



CREATE TABLE `staff` (
  `id` integer NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `personal_number` integer DEFAULT NULL,
  `position` varchar(20) NOT NULL,
  `birthdate` date NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `salary` double DEFAULT NULL,
  `passwordd` varchar(200) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `staff_chk_1` CHECK ((`salary` > 169))
);



CREATE TABLE `payments` (
  `id` integer NOT NULL AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `staff_id` integer NOT NULL,
  `price` double NOT NULL,
  `payment_method` varchar(30) NOT NULL,
  `is_payed` tinyint(1) NOT NULL DEFAULT '0',
  `pay_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `payments_ibfk_2` FOREIGN KEY(staff_id) REFERENCES staff (id) ,
  CONSTRAINT `payments_chk_1` CHECK ((`price` > 0))
);

CREATE TABLE `reservations` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `user_id` int(11) NOT NULL,
 `room_id` int(11) NOT NULL,
 `reservation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `checkin_date` date DEFAULT NULL,
 `checkout_date` date DEFAULT NULL,
 `adults` int(11) DEFAULT '1',
 `children` int(11) DEFAULT '1',
 `payment_id` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `user_id` (`user_id`),
 KEY `room_id` (`room_id`),
 KEY `payment_id` (`payment_id`),
 CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
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

insert into services_type() values(1 , "Car rental services" , 100 , 100);
insert into services_type() values(2, "Catering services " , 50 , 100);
insert into services_type() values(3 , "Courier services " , 50 , 200);
insert into services_type() values(4, "Doctor on call " , 100, 20);
insert into services_type() values(5 , "Dry cleaning"  , 100 , 100);
insert into services_type() values(6 , "Car rental services" , 100 , 100);
insert into services_type() values(7, "Mail services " , 50 , 100);
insert into services_type() values(8 , "Shoeshine service" , 10 , 200);
insert into services_type() values(9, "Concierge services " , 100, 20);
insert into services_type() values(10 , "Massages" , 200 , 200);


create view paymentmodel(payment_id , name, lastname, date, price, ispayed) as
	select p.id as 'payment_id', u.name, u.username ,p.pay_date as 'pay_date' , p.price  ,p.is_payed from users u
	inner join payments p on p.user_id = u.id;





CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `service_id` (`service_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `services_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services_type` (`id`),
  CONSTRAINT `services_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
  CONSTRAINT `services_chk_1` CHECK ((`quantity` > 0))
) ;


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

insert into events() values(1, "Call of Duty Championship", "Juan Flatroo" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(2, "Counter-Strike: Global Offensive Major", "Juan Flatroo" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(3, "eSports World Convention", "S1MPLE" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(4, "Evolution Championship Series", "GXX-" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(5, "FIFA eWorld Cup", "RAIN" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(6, "eSports World Convention", "ELECTRON" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(7, "Dreamhack", "PERFECTO" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(8, "Intel Extreme Masters", "B1T" ,"Inspiration" , 200, now(), now() + 1 );
insert into events() values(9, "The International.", "Juan Flatro" ,"Inspiration" , 200, now(), now() + 1 );
