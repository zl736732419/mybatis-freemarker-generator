-- CREATE SCHEMA
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user (
  idAttr INT PRIMARY KEY AUTO_INCREMENT not null ,
  nick_name VARCHAR(50),
  age INT,
  is_delete INT NOT NULL DEFAULT 0);

-- LOAD INIT DATA
INSERT INTO tb_user(nick_name, age, is_delete)
VALUES ('xiaolian', 25, 0);