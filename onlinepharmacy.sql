-- MySQL Script generated by MySQL Workbench
-- Thu Aug  5 22:43:16 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema online_pharmacy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema online_pharmacy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `online_pharmacy` DEFAULT CHARACTER SET utf8 ;
USE `online_pharmacy` ;

-- -----------------------------------------------------
-- Table `online_pharmacy`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_pharmacy`.`users` (
    `user_id` BIGINT(19) NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `telephone` VARCHAR(45) NOT NULL,
    `role` ENUM('pharmasist', 'customer', 'guest', 'admin') NOT NULL,
    `status` ENUM('active', 'inactive', 'in_registr') NOT NULL,
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_pharmacy`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_pharmacy`.`products` (
    `product_id` BIGINT(19) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `price` DECIMAL NULL,
    `recipe` TINYINT NOT NULL,
    `picture` BLOB NOT NULL,
    `instruction` LONGTEXT NOT NULL,
    PRIMARY KEY (`product_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_pharmacy`.`pharmacy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_pharmacy`.`pharmacy` (
    `pharmacy_id` BIGINT(8) NOT NULL AUTO_INCREMENT,
    `address` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`pharmacy_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_pharmacy`.`statuses_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_pharmacy`.`statuses_order` (
                                                                  `status_order_id` INT NOT NULL AUTO_INCREMENT,
                                                                  `status` ENUM('processing', 'prepared', 'released', 'deleted') NOT NULL,
    PRIMARY KEY (`status_order_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_pharmacy`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_pharmacy`.`orders` (
    `order_id` BIGINT(19) NOT NULL AUTO_INCREMENT,
    `data_starting` TIMESTAMP(6) NOT NULL,
    `data_ending` TIMESTAMP(6) NOT NULL,
    `status_order` INT NOT NULL,
    `pharmacy_id` BIGINT(8) NOT NULL,
    PRIMARY KEY (`order_id`),
    INDEX `status_idx` (`status_order` ASC) VISIBLE,
    INDEX `pharmacy_id_idx` (`pharmacy_id` ASC) VISIBLE,
    CONSTRAINT `status`
    FOREIGN KEY (`status_order`)
    REFERENCES `online_pharmacy`.`statuses_order` (`status_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `pharmacy_id`
    FOREIGN KEY (`pharmacy_id`)
    REFERENCES `online_pharmacy`.`pharmacy` (`pharmacy_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online_pharmacy`.`basket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `online_pharmacy`.`basket` (
                                                          `basket_id` INT NOT NULL AUTO_INCREMENT,
                                                          `user_id` BIGINT(19) NOT NULL,
    `product_id` BIGINT(19) NOT NULL,
    `order_id` BIGINT(19) NOT NULL,
    `quantity` INT(8) NOT NULL,
    PRIMARY KEY (`basket_id`),
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `product_id_idx` (`product_id` ASC) VISIBLE,
    INDEX `order_id_idx` (`order_id` ASC) VISIBLE,
    CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `online_pharmacy`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `online_pharmacy`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `online_pharmacy`.`orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
