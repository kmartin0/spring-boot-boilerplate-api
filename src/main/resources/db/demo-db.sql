-- -----------------------------------------------------
-- Schema demo-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `demo-db` DEFAULT CHARACTER SET utf8;
USE `demo-db`;

-- -----------------------------------------------------
-- Table `client_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `client_details`
(
    `clientId`             VARCHAR(255) NOT NULL,
    `clientSecret`         VARCHAR(255) NOT NULL,
    `scope`                VARCHAR(255) NOT NULL,
    `authorizedGrantTypes` VARCHAR(255) NOT NULL,
    `authorities`          VARCHAR(255) NOT NULL,
    `accessTokenValidity`  INT          NOT NULL,
    `refreshTokenValidity` INT          NOT NULL,
    PRIMARY KEY (`clientId`)
);


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `userName`  VARCHAR(255) NOT NULL,
    `firstName` VARCHAR(255) NULL,
    `lastName`  VARCHAR(255) NULL,
    `email`     VARCHAR(255) NOT NULL,
    `password`  VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `userName_UNIQUE` (`userName` ASC)
);


-- -----------------------------------------------------
-- Table `password_tokens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `password_tokens`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `token`      VARCHAR(255) NOT NULL,
    `user`       INT          NOT NULL,
    `expiration` DATETIME     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `token_UNIQUE` (`token` ASC),
    INDEX `fk_password_token_user_idx` (`user` ASC),
    CONSTRAINT `fk_password_token_user`
        FOREIGN KEY (`user`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Insert demo data Passwords are 'secret'
-- -----------------------------------------------------
INSERT INTO `client_details` (`clientId`, `clientSecret`, `scope`, `authorizedGrantTypes`, `authorities`,
                              `accessTokenValidity`, `refreshTokenValidity`)
VALUES ('demo-client', '$2a$12$o3dmbF3ElqPL1ApJ.9R/Qu7cVBMyV8pn80.HPFPdKO/jerqGJiXZe', 'all',
        'password,refresh_token,client_credentials', 'role_CLIENT', '18000', '604800');
INSERT INTO `users` (`id`, `userName`, `firstName`, `lastName`, `email`, `password`)
VALUES ('1', 'Johndoe1', 'John', 'Doe', 'johndoe1@email.com',
        '$2a$12$o3dmbF3ElqPL1ApJ.9R/Qu7cVBMyV8pn80.HPFPdKO/jerqGJiXZe');
INSERT INTO `users` (`id`, `userName`, `firstName`, `lastName`, `email`, `password`)
VALUES ('2', 'Janedoe1', 'Jane', 'Doe', 'janedoe1@email.com',
        '$2a$12$o3dmbF3ElqPL1ApJ.9R/Qu7cVBMyV8pn80.HPFPdKO/jerqGJiXZe');
INSERT INTO `password_tokens` (`token`, `user`, `expiration`)
VALUES ('7f019466-3845-443c-abf6-09780ca64fc2', '1', DATE_ADD(NOW(), INTERVAL 7 DAY));