CREATE TABLE `address_info` (
  `address` varchar(45) DEFAULT NULL,
  `address_line_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `property_id` bigint(20) NOT NULL,
  `resdience_type` varchar(45) DEFAULT NULL,
  `resdience_use` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`property_id`)
);


CREATE TABLE `home_info` (
  `idhome_info` int(11) NOT NULL AUTO_INCREMENT,
  `dwelling_style` varchar(255) DEFAULT NULL,
  `number_of_full_baths` varchar(255) DEFAULT NULL,
  `number_of_half_baths` varchar(255) DEFAULT NULL,
  `pool` varchar(255) DEFAULT NULL,
  `roof_type` varchar(255) DEFAULT NULL,
  `square_footage` varchar(255) DEFAULT NULL,
  `type_garage` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `value_of_home` varchar(255) DEFAULT NULL,
  `year_was_built` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idhome_info`),
  UNIQUE KEY `UK_dnw1nk5ebh6tqbpwjw08eo5qh` (`user_id`)
);
