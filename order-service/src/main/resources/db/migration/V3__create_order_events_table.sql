-- Create a sequence for generating unique IDs
CREATE SEQUENCE order_event_id_seq START WITH 1 INCREMENT BY 50;

-- Create the outbox table
CREATE TABLE order_events (
    id            BIGINT DEFAULT nextval('order_event_id_seq') NOT NULL,
    order_number  TEXT NOT NULL,
    event_id      TEXT NOT NULL UNIQUE,
    event_type    TEXT NOT NULL,
    payload       TEXT NOT NULL,
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);
