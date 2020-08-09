BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id BIGSERIAL PRIMARY KEY, name VARCHAR(255), age INT);
INSERT INTO users (name, age) VALUES ('Igor', 24), ('Alexey', 37), ('Dmitriy', 19), ('Ivan', 40);


DROP TABLE IF EXISTS items CASCADE;
CREATE TABLE items (id BIGSERIAL PRIMARY KEY, title VARCHAR(255), price INT);
INSERT INTO items (title, price) VALUES ('tomato', 40), ('chicken', 150), ('potato', 20);

COMMIT;