DROP DATABASE IF EXISTS 'cibress';
CREATE DATABASE 'cibress';
USE 'cibress';

CREATE TABLE ingredient (
  'id' INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(30) NOT NULL,
  'public' TINYINT(1) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB;


CREATE TABLE dishe (
  'id' INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(30) NOT NULL,
  'public' TINYINT(1) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB;

CREATE TABLE dish_comment (
   'id' INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   'text' VARCHAR(1500),
   'id_dish' INT(10) UNSIGNED NOT NULL,
   PRIMARY KEY ('id'),
   CONSTRAINT 'fk_dish_comment'
     FOREIGN KEY (id_dish) REFERENCES dishe (id)
     ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE dish_ingredient (
  'id_dish' INT(11) UNSIGNED NOT NULL,
  'id_ingredient' INT(10) UNSIGNED NOT NULL,
  'quantity_ingredient' INT(5) UNSIGNED,
  'unit' VARCHAR(10),
  PRIMARY KEY (id_dish, id_ingredient),
  CONSTRAINT 'fk_dish_ingredient'
    FOREIGN KEY (id_dish) REFERENCES dish (id)
      ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (id_ingredient) REFERENCES ingredien (id)
      ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE symptom_name (
  'id' INT(11) UNSIGNED NOT NULL,
  'name' VARCHAR(30),
  'public' TINYINT(1) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB;

CREATE TABLE symptom (
  'id' INT(11) UNSIGNED NOT NULL,
  'text' VARCHAR(1500),
  'intensity' TINYINT(2) NOT NULL,
  'id_symptom_name' INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_symptom_name'
    FOREIGN KEY (id_symptom_name) REFERENCES symptom_name (id)
      ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE day (
  'id' INT(11) UNSIGNED NOT NULL,
  ''
) ENGINE=InnoDB;