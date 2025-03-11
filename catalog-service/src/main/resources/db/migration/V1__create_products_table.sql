-- Create sequence for product ID
CREATE SEQUENCE product_id_sequence START WITH 1 INCREMENT BY 1;

-- Create products table
CREATE TABLE products (
    id BIGINT PRIMARY KEY DEFAULT nextval('product_id_sequence'),
    code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url TEXT,
    price NUMERIC NOT NULL
);