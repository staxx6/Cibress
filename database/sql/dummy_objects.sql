USE cibress;

DELETE FROM symptom;
DELETE FROM symptom_name;
DELETE FROM day_entry_dish;
DELETE FROM day_entry;
DELETE FROM dish_ingredient;
DELETE FROM dish;
DELETE FROM anonymous_comment;
DELETE FROM ingredient;
DELETE FROM unit;
DELETE FROM message;
DELETE FROM user;

INSERT INTO user VALUES (1, "root", "{noop}root", "root@email.de", "ROLE_ADMIN");
INSERT INTO user VALUES (2, "staxx6", "{noop}staxx6", "staxx@hotmail.de", "ROLE_USER");

INSERT INTO unit VALUES (1, "Portion", "1");
INSERT INTO unit VALUES (2, "Kg", "1");
INSERT INTO unit VALUES (3, "L", "1");

INSERT INTO ingredient VALUES (1, "New ingredient", 0);
INSERT INTO ingredient VALUES (2, "Birne", 0);
INSERT INTO ingredient VALUES (3, "Kirsche", 0);

INSERT INTO anonymous_comment VALUES (1, "Enter a comment");

INSERT INTO dish VALUES (1, "New dish", 0, 1);
INSERT INTO dish VALUES (2, "Pizza", 0, 1);

INSERT INTO dish_ingredient VALUES (1, 2, 2, 50, 2);
INSERT INTO dish_ingredient VALUES (2, 2, 3, 500, 3);

INSERT INTO day_entry VALUES (1, 1, CURDATE(), 1);
INSERT INTO day_entry VALUES (2, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY ), 1);

INSERT INTO day_entry_dish VALUES (1, 1, 2, CURTIME(), 1, 1);
INSERT INTO day_entry_dish VALUES (2, 1, 2, CURTIME(), 1, 1);
INSERT INTO day_entry_dish VALUES (3, 1, 2, CURTIME(), 1, 1);
INSERT INTO day_entry_dish VALUES (4, 2, 2, CURTIME(), 1, 1);

INSERT INTO symptom_name VALUES (1, "everyting hurts", 1);

INSERT INTO symptom VALUES (1, CURTIME(), "mostly my brain", 5, 1, 1);
INSERT INTO symptom VALUES (2, CURTIME(), "mostly my brain", 3, 1, 2);

# Playground stuff
INSERT INTO message VALUES (1, 1, "Das ist ist super msg", "titel 1");
INSERT INTO message VALUES (2, 1, "Das ist ist super msg2", "titel 2");
INSERT INTO message VALUES (3, 2, "Das ist ist super msg3", "titel 3");
INSERT INTO message VALUES (4, 2, "Das ist ist super msg4", "titel 4");