DROP TABLE IF EXISTS orders_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(250) NULL,
    email      VARCHAR(250) NOT NULL,
    password   VARCHAR(250) NOT NULL,
    is_deleted BOOLEAN      NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE KEY email_UNIQUE (email)
);

CREATE TABLE roles
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(450) NULL,
    category_id BIGINT NOT NULL,
    description VARCHAR(450) NULL,
    price       DOUBLE NULL,
    image       VARCHAR(450) NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    order_id       BIGINT NOT NULL AUTO_INCREMENT,
    product_id     BIGINT NOT NULL,
    user_id        BIGINT NOT NULL,
    order_quantity INT NULL,
    order_date     VARCHAR(450) NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE users_roles
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE orders_details
(
    order_detail_id BIGINT NOT NULL AUTO_INCREMENT,
    order_id        BIGINT NOT NULL,
    product_id      BIGINT NOT NULL,
    price_per_unit  DOUBLE NULL,
    quantity        INT NULL,
    total_price     DOUBLE NULL,
    PRIMARY KEY (order_detail_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
