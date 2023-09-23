# SQL configs
SET SQL_MODE ='IGNORE_SPACE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

# create database and use it
CREATE DATABASE IF NOT EXISTS startup-portal;
USE startup-portal;





# create the customer table
CREATE TABLE IF NOT EXISTS CUSTOMER(
id       int unique key not null auto_increment primary key,
address  varchar(255) null,
email    varchar(255) null,
password varchar(255) null,
role     varchar(255) null,
username varchar(255) null
);

-- Adding a hashing function to the password column
-- The example below uses SHA-256 for hashing, you can choose a different hashing algorithm if needed.

DELIMITER //

CREATE TRIGGER HashPasswordBeforeInsert BEFORE INSERT ON CUSTOMER
FOR EACH ROW
BEGIN
    SET NEW.password = SHA2(NEW.password, 256);
END;
//

DELIMITER ;


# insert default customers
INSERT INTO CUSTOMER(address, email, password, role, username) VALUES
                                                                   ('123, Albany Street', 'admin@gmail.com', 'admin123', 'ROLE_ADMIN', 'admin'),
                                                                   ('765, 5th Avenue', 'manav@gmail.com', 'manav123', 'ROLE_NORMAL', 'manav');






CREATE INDEX FKt23apo8r9s2hse1dkt95ig0w5
    ON PRODUCT (customer_id);