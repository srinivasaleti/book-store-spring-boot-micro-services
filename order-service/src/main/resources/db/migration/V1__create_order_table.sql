-- Create sequences for order and order item IDs
CREATE SEQUENCE order_id_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE order_item_id_seq START WITH 1 INCREMENT BY 50;

-- Create orders table
CREATE TABLE orders (
    id BIGINT DEFAULT nextval('order_id_seq') NOT NULL,
    order_number TEXT NOT NULL,
    username TEXT NOT NULL,
    customer_name TEXT NOT NULL,
    customer_email TEXT NOT NULL,
    customer_phone TEXT NOT NULL,
    delivery_address_line1 TEXT NOT NULL,
    delivery_address_line2 TEXT,
    delivery_address_city TEXT NOT NULL,
    delivery_address_state TEXT NOT NULL,
    delivery_address_zip_code TEXT NOT NULL,
    delivery_address_country TEXT NOT NULL,
    status TEXT NOT NULL,
    comments TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);
