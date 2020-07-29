BEGIN;

DROP TABLE IF EXISTS products cascade;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price INT);
INSERT INTO products (title, price) VALUES
('tomato',50),
('potato', 30),
('pickle', 60);

DROP TABLE IF EXISTS consumers cascade;
CREATE TABLE consumers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO consumers (name) VALUES
('john'),
('bob'),
('mike');

DROP TABLE IF EXISTS consumers_products CASCADE;
CREATE TABLE consumers_products (consumers_id bigint, products_id bigint, product_price int, FOREIGN KEY (consumers_id) REFERENCES consumers (id), FOREIGN KEY (products_id) REFERENCES products (id));
INSERT INTO consumers_products (consumers_id, products_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(1, 1),
(2, 2),
(3, 2),
(3, 3);

UPDATE consumers_products t2
SET    product_price = t1.price
FROM   products t1
WHERE  t2.products_id = t1.id;


COMMIT;