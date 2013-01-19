DROP TABLE IF EXISTS `passport_ticket_key`;
CREATE TABLE `passport_ticket_key`(
  `ticket_key` VARCHAR(64) NOT NULL DEFAULT '',
  `type` INT(10) NOT NULL DEFAULT 0, 
  `create_time` TIMESTAMP NOT NULL DEFAULT 0,
  PRIMARY KEY  (`ticket_key`),
  UNIQUE KEY `distinct` (`ticket_key`, `type`, `create_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;