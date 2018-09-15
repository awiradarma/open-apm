DROP TABLE IF EXISTS publisher;
CREATE TABLE publisher (
  publisher_id INT NOT NULL AUTO_INCREMENT,
  publisher_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (publisher_id));