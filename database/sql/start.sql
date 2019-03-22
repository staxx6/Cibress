DROP DATABASE IF EXISTS `cibress`;
CREATE DATABASE `cibress`;
USE `cibress`;

--
-- Users / Security
--

# DROP TABLE IF EXISTS `users`;
# CREATE TABLE `users` (
#   `username` varchar(50) NOT NULL,
#   `password` char(68) NOT NULL,
#   `enabled` tinyint(1) NOT NULL,
#   PRIMARY KEY (`username`)
# ) ENGINE=InnoDB;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`username`)
) ENGINE=InnoDB;

# DROP TABLE IF EXISTS `authorities`;
# CREATE TABLE `authorities` (
#   `username` varchar(50) NOT NULL,
#   `authority` varchar(50) NOT NULL,
#   UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
#   CONSTRAINT `authorities_ibfk_1`
#     FOREIGN KEY (`username`) REFERENCES `users` (`username`)
# ) ENGINE=InnoDB;

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
  `public_view` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `short_name` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `public_view` TINYINT(1) NOT NULL,
  `id_anonymous_comment` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_anonymous_comment_dish`
    FOREIGN KEY (`id_anonymous_comment`) REFERENCES `anonymous_comment` (`id`)
      ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `dish_ingredient`;
CREATE TABLE `dish_ingredient` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_dish` INT(11) UNSIGNED NOT NULL,
  `id_ingredient` INT(11) UNSIGNED NOT NULL,
  `quantity_ingredient` INT(5) UNSIGNED,
  `id_unit` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_dish_ingredient`
    FOREIGN KEY (`id_dish`) REFERENCES `dish` (`id`)
      ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`)
      ON DELETE NO ACTION ON UPDATE RESTRICT,
    FOREIGN KEY (`id_ingredient`) REFERENCES `ingredient` (`id`)
      ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `day_entry`;
CREATE TABLE `day_entry` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_user` INT(11) UNSIGNED NOT NULL,
  `entry_record` DATE NOT NULL,
  `id_anonymous_comment` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_anonymous_comment_day`
    FOREIGN KEY (`id_anonymous_comment`) REFERENCES `anonymous_comment` (`id`)
      ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
      ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `day_entry_dish`;
CREATE TABLE `day_entry_dish` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_day_entry` INT(11) UNSIGNED NOT NULL,
  `id_dish` INT(11) UNSIGNED, # NULL -> standard entry
  `time_recorded` TIME NOT NULL,
  `quantity_ingredient` INT(5) UNSIGNED,
  `id_unit` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_day_entry_dish`
    FOREIGN KEY (`id_day_entry`) REFERENCES `day_entry` (`id`)
      ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`)
      ON DELETE NO ACTION ON UPDATE RESTRICT,
    FOREIGN KEY (`id_dish`) REFERENCES `dish` (`id`)
      ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `symptom_name`;
CREATE TABLE `symptom_name` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30),
  `public_view` TINYINT(1) NOT NULL,
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

--
-- Playground
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_user` INT(11) UNSIGNED NOT NULL,
  `text` VARCHAR(5000) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_message_user`
    FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
      ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;