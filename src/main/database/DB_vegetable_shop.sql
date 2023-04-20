DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `category`;

CREATE TABLE `users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(250) NOT NULL,
    `email`    VARCHAR(250) NOT NULL,
    `password` VARCHAR(250) NOT NULL,
    `role_id`  INT          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
);

CREATE TABLE `products`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(450) NOT NULL,
    `category_id` INT          NOT NULL,
    `description` VARCHAR(450) NOT NULL,
    `price`       DOUBLE       NOT NULL,
    `image`       VARCHAR(450) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
);

CREATE TABLE `orders`
(
    `order_id`       INT          NOT NULL AUTO_INCREMENT,
    `product_id`     INT          NOT NULL,
    `user_id`        INT          NOT NULL,
    `order_quantity` INT          NOT NULL,
    `order_date`     VARCHAR(450) NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

CREATE TABLE `roles`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `category`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NOT NULL,
    PRIMARY KEY (`id`)
);