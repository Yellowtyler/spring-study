BEGIN;
DROP TABLE IF EXISTS users cascade;
CREATE TABLE users (id BIGSERIAL PRIMARY KEY, name VARCHAR(255));
INSERT INTO users(name) VALUES ('Ivan'), ('Egor'), ('Max'), ('Gena'), ('Gosha'), ('Michael'), ('Dima'),
('Bob');

DROP TABLE IF EXISTS lots cascade;
CREATE TABLE lots (id BIGSERIAL PRIMARY KEY, name VARCHAR(255), bet INT,
user_id BIGINT REFERENCES users(id), version BIGINT);
INSERT INTO lots(name, bet, user_id, version) VALUES ('player 1', 0, 1, 1),
('player 2', 0, 2, 1), ('player 3', 0, 3, 1), ('player 4', 0, 4, 1);

COMMIT;