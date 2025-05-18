DROP TABLE IF EXISTS my_ponies;

CREATE TABLE my_ponies(
                     ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                     NAME VARCHAR(255),
                     AGE INT,
                     SIZE INT
);