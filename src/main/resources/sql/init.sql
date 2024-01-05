CREATE TABLE IF NOT EXISTS products
(
    id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) DEFAULT NULL,
    cost DOUBLE       DEFAULT NULL
);

INSERT INTO products (name, cost)
VALUES ('product 1', 11.11),
       ('product 2', 22.22),
       ('product 3', 33.33),
       ('product 4', 44.44),
       ('product 5', 55.55),
       ('product 6', 66.66),
       ('product 7', 77.77),
       ('product 8', 88.88),
       ('product 9', 99.99),
       ('product 10', 100.10);

CREATE TABLE IF NOT EXISTS orders
(
    id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date DATE   DEFAULT NULL,
    cost DOUBLE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS order_product
(
    order_id   BIGINT NOT NULL REFERENCES orders (id),
    product_id BIGINT NOT NULL REFERENCES products (id),
    PRIMARY KEY (order_id, product_id)
);
