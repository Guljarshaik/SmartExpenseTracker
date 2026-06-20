CREATE DATABASE khatabook;
USE khatabook;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    address VARCHAR(255)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    date DATE,
    description VARCHAR(255),
    amount DOUBLE,
    type ENUM('credit','debit'),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
