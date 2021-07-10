DROP SCHEMA IF EXISTS Currency_Exchange_db;

CREATE SCHEMA Currency_Exchange_db;
USE Currency_Exchange_db;

CREATE TABLE Customer (
	customer_id INT AUTO_INCREMENT,
	customer_email VARCHAR(50) NOT NULL,
	customer_password VARCHAR(50) NOT NULL,
	customer_name VARCHAR(50) NOT NULL,
	CONSTRAINT customer_id_pk PRIMARY KEY (customer_id)
);
INSERT INTO Customer(customer_id,customer_email, customer_password, customer_name) VALUES (100,'internsteam@infosys.com', 'interns123','Interns');

SELECT * FROM Customer;