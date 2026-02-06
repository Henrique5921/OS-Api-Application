CREATE DATABASE hdcea_osapi;
USE hdcea_osapi;

CREATE TABLE cliente (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(60),
 email VARCHAR(255) UNIQUE,
 telefone VARCHAR(20)
 );

INSERT INTO cliente (nome, email, telefone) VALUES 
("KGe", "kge@email.com", "+551199999-9999"),
("Rodrigo", "rodrigo@email.com", "+551188888-8888"),
("Americo", "americo@email.com", "+551177777-7777"),
("maiara", "maiara@email.com", "+551166666-6666");

select * from cliente; 