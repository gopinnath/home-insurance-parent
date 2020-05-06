CREATE TABLE `username` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  `user_role` int(11) DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);


CREATE TABLE `home_owner` (
  `home_owner_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `are_you_retired` varchar(45) DEFAULT NULL,
  `social_security_number` varchar(45) DEFAULT NULL,
  `date_of_birth` DATE DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`home_owner_id`),
  UNIQUE KEY `home_owner_UNIQUE` (`username`)
);
