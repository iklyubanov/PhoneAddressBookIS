-- creates user and database

CREATE USER operuser WITH PASSWORD '123';
CREATE DATABASE addressbook;
GRANT ALL ON addressbook.* TO operuser;

--for mysql
--CREATE USER 'addressbook'@'localhost' IDENTIFIED BY 'addressbook';
--CREATE DATABASE addressbook;
--GRANT ALL ON addressbook.* TO 'addressbook'@'localhost';