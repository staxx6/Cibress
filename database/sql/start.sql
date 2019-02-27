DROP DATABASE IF EXISTS 'cibress';
CREATE DATABASE 'cibress';
USE 'cibress';

CREATE TABLE ingredients (
  'id' INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(30) NOT NULL,
  'public' TINYINT(1) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB;


CREATE TABLE dishes (
  'id' INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(30) NOT NULL,
  'public' TINYINT(1) NOT NULL,
  PRIMARY KEY ('id')
) ENGINE=InnoDB;

CREATE TABLE dish_comments (
   'id' INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   'text' VARCHAR(1500),
   'id_dish' INT(10) UNSIGNED NOT NULL,
   PRIMARY KEY ('id'),
   CONSTRAINT 'fk_dish_comments'
     FOREIGN KEY (id_dish) REFERENCES dishes (id)
     ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE dishes_ingredients (
  'id_dish' INT(10) UNSIGNED NOT NULL,
  'id_ingredient' INT(10) UNSIGNED NOT NULL,
  'quantity_ingredient' INT(5) UNSIGNED,
  'unit' VARCHAR(10),
  PRIMARY KEY (id_dish, id_ingredient),
  CONSTRAINT 'fk_dishes_ingredients'
    FOREIGN KEY (id_dish) REFERENCES dishes (id)
      ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (id_ingredient) REFERENCES ingredients (id)
      ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB;