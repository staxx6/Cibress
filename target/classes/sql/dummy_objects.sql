DELETE FROM user;
INSERT INTO user VALUES (1, "root", "{noop}root");
INSERT INTO user VALUES (2, "staxx6", "{noop}staxx6");

DELETE FROM ingredient;
INSERT INTO ingredient VALUES (1, "Apfel", "0");
INSERT INTO ingredient VALUES (2, "Birne", "0");
INSERT INTO ingredient VALUES (3, "Kirsche", "0");

DELETE FROM anonymous_comment;
INSERT INTO anonymous_comment VALUES (1, "Das ist absolut nocht meine lieblings Pizza");

DELETE FROM dish;
INSERT INTO dish VALUES (1, "Pizza", 0, 1);

DELETE FROM dish_ingredient;
INSERT INTO dish_ingredient VALUES (1, 1, 1, 50, "Kilogramm");
INSERT INTO dish_ingredient VALUES (1, 1, 2, 500, "Gramm");