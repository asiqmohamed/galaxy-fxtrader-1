
CREATE TABLE IF NOT EXISTS `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS `pricing_tier` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `last_updated_by` varchar(50) DEFAULT NULL,
  `last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE IF NOT EXISTS `pricing_item` (
  `id` varchar(50) NOT NULL,
  `pricing_tier_id` varchar(50) NOT NULL,
  `tier_type` tinyint DEFAULT NULL,
  `default_tier_id` varchar(50) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `channels` json DEFAULT NULL,
  `is_all_day` bit(1) DEFAULT NULL,
  `from_time` int DEFAULT NULL,
  `to_time` int DEFAULT NULL,
  `no_quote_message` text,
  `created_by` varchar(50) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `last_updated_by` varchar(50) DEFAULT NULL,
  `last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pricing_tier` (`pricing_tier_id`),
  CONSTRAINT `fk_pricing_tier` FOREIGN KEY (`pricing_tier_id`) REFERENCES `pricing_tier` (`id`)
) ;

CREATE TABLE IF NOT EXISTS `pricing_currency_group` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pricing_tier_id` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `last_updated_by` varchar(50) DEFAULT NULL,
  `last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE IF NOT EXISTS `pricing_currency_set` (
  `id` varchar(50) NOT NULL,
  `ccy_group_id` varchar(50) NOT NULL,
  `ccy_pair` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ccy_grp` (`ccy_group_id`),
  CONSTRAINT `fk_ccy_grp` FOREIGN KEY (`ccy_group_id`) REFERENCES `pricing_currency_group` (`id`)
);

CREATE TABLE IF NOT EXISTS `pricing_tenor_range` (
  `id` varchar(50) NOT NULL,
  `ccy_group_id` varchar(50) NOT NULL,
  `range_from` varchar(5) NOT NULL,
  `range_to` varchar(5) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `last_updated_by` varchar(50) DEFAULT NULL,
  `last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pricing_ccy_grp_tenor` (`ccy_group_id`),
  CONSTRAINT `fk_pricing_ccy_grp_tenor` FOREIGN KEY (`ccy_group_id`) REFERENCES `pricing_currency_group` (`id`)
);

CREATE TABLE IF NOT EXISTS `pricing_amount` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `pricing_tenor_range_id` varchar(50) DEFAULT NULL,
  `ccy_type` tinyint DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `last_updated_by` varchar(50) DEFAULT NULL,
  `last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tenor_amount` (`pricing_tenor_range_id`),
  CONSTRAINT `fk_tenor_amount` FOREIGN KEY (`pricing_tenor_range_id`) REFERENCES `pricing_tenor_range` (`id`)
);

CREATE TABLE IF NOT EXISTS `pricing_amount_range` (
  `id` varchar(50) NOT NULL,
  `pricing_amount_id` varchar(50) DEFAULT NULL,
  `amount_from` decimal(18,8) DEFAULT NULL,
  `amount_to` decimal(18,8) DEFAULT NULL,
  `bank_buys` decimal(10,2) DEFAULT NULL,
  `bank_sells` decimal(10,2) DEFAULT NULL,
  `spread_unit` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pricing_amt_tier` (`pricing_amount_id`),
  CONSTRAINT `fk_pricing_amt_tier` FOREIGN KEY (`pricing_amount_id`) REFERENCES `pricing_amount` (`id`)
);