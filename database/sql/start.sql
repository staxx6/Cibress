DROP DATABASE IF EXISTS `cibress`;
CREATE DATABASE `cibress`;
USE `cibress`;

--
-- Users / Security
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB;

--
-- Application itself
--

DROP TABLE IF EXISTS `anonymous_comment`;
CREATE TABLE anonymous_comment (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(1500),
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `public` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `dish`;
CREATE TABLE dish (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `public` TINYINT(1) NOT NULL,
  `id_anonymous_comment` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_anonymous_comment_dish`
    FOREIGN KEY (`id_anonymous_comment`) REFERENCES `anonymous_comment` (`id`)
      ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `dish_ingredient`;
CREATE TABLE `dish_ingredient` (
  `id_dish` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_ingredient` INT(11) UNSIGNED NOT NULL,
  `quantity_ingredient` INT(5) UNSIGNED,
  `unit` VARCHAR(10),
  PRIMARY KEY (`id_dish`, `id_ingredient`),
  CONSTRAINT `fk_dish_ingredient`
    FOREIGN KEY (`id_dish`) REFERENCES `dish` (`id`)
      ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id`)
ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `day_entry`;
CREATE TABLE `day_entry` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `entry_record` DATE NOT NULL,
  `id_anonymous_comment` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_anonymous_comment_day`
    FOREIGN KEY (`id_anonymous_comment`) REFERENCES `anonymous_comment` (`id`)
      ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`username`) REFERENCES `users` (`username`)
      ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `day_entry_dish`;
CREATE TABLE `day_entry_dish` (
   `id_day_entry` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   `id_dish` INT(11) UNSIGNED NOT NULL,
   `time_recorded` TIME,
   `quantity_ingredient` INT(5) UNSIGNED,
   `unit` VARCHAR(10),
   PRIMARY KEY (`id_day_entry`, `id_dish`),
   CONSTRAINT `fk_day_entry_dish`
     FOREIGN KEY (`id_day_entry`) REFERENCES `day_entry` (`id`)
       ON DELETE CASCADE ON UPDATE RESTRICT,
   FOREIGN KEY (`id_dish`) REFERENCES `dish` (`id`)
     ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `symptom_name`;
CREATE TABLE `symptom_name` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30),
  `public` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `symptom`;
CREATE TABLE `symptom` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `recorded` DATETIME NOT NULL,
  `text` VARCHAR(1500),
  `intensity` TINYINT(2) NOT NULL,
  `id_symptom_name` INT(11) UNSIGNED NOT NULL,
  `id_day_entry` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
    CONSTRAINT `fk_symptom_name`
      FOREIGN KEY (`id_symptom_name`) REFERENCES `symptom_name` (`id`)
  ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (`id_day_entry`) REFERENCES `day_entry` (`id`)
      ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

