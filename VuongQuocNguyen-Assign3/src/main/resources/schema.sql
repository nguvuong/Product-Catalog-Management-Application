DROP TABLE IF EXISTS product;
CREATE TABLE product (
id INT AUTO_INCREMENT PRIMARY KEY,
productCode VARCHAR(20),
productBrand VARCHAR(20) NOT NULL,
quantity int,
productPrice numeric(5,2) );
