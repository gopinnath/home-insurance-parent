CREATE TABLE `quote` (
  `quote_id` int(11) NOT NULL AUTO_INCREMENT,
  `premium` varchar(45) DEFAULT NULL,
  `coverage` varchar(45) DEFAULT NULL,
  `detached_structure` varchar(45) DEFAULT NULL,
  `pp` varchar(45) DEFAULT NULL,
  `adle` varchar(45) DEFAULT NULL,
  `me` varchar(45) DEFAULT NULL,
  `ded` varchar(45) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`quote_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `property_id_UNIQUE` (`property_id`)
);
