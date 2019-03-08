USE cibress;

DELETE FROM day_entry_dish;
DELETE FROM day_entry;
DELETE FROM dish_ingredient;
DELETE FROM dish;
DELETE FROM anonymous_comment;
DELETE FROM ingredient;
DELETE FROM message;
DELETE FROM user;

INSERT INTO user VALUES (1, "root", "{noop}root", "root@email.de", "ROLE_ADMIN");
INSERT INTO user VALUES (2, "staxx6", "{noop}staxx6", "staxx@hotmail.de", "ROLE_USER");

INSERT INTO ingredient VALUES (1, "Apfel", "0");
INSERT INTO ingredient VALUES (2, "Birne", "0");
INSERT INTO ingredient VALUES (3, "Kirsche", "0");

INSERT INTO anonymous_comment VALUES (1, "Das ist absolut nocht meine lieblings Pizza");

INSERT INTO dish VALUES (1, "Pizza", 0, 1);

INSERT INTO dish_ingredient VALUES (1, 1, 1, 50, "Kilogramm");
INSERT INTO dish_ingredient VALUES (2, 1, 2, 500, "Gramm");

INSERT INTO day_entry VALUES (1, 1, CURDATE(), 1);
INSERT INTO day_entry VALUES (2, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY ), 1);

INSERT INTO day_entry_dish VALUES (1, 1, 1, CURTIME(), 1, "Portion");
INSERT INTO day_entry_dish VALUES (2, 2, 1, CURTIME(), 1, "Portion");

# Playground stuff
INSERT INTO message VALUES (1, 1, "Das ist ist super msg", "titel 1");
INSERT INTO message VALUES (2, 1, "Das ist ist super msg2", "titel 2");
INSERT INTO message VALUES (3, 2, "Das ist ist super msg3", "titel 3");
INSERT INTO message VALUES (4, 2, "Das ist ist super msg4", "titel 4");