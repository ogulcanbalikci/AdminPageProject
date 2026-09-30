CREATE DATABASE IF NOT EXISTS company;
USE company;

CREATE TABLE IF NOT EXISTS users (
    userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    userRole VARCHAR(20) DEFAULT 'normal'
);

INSERT INTO users (name, surname, username, password, userRole) 
VALUES ('Oğulcan', 'Yönetici', 'admin', '1234', 'admin');
INSERT INTO users (name, surname, username, password, userRole) 
VALUES ('Ahmet', 'Yılmaz', 'ahmet', '1234', 'normal');