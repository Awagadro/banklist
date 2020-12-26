DROP DATABASE IF EXISTS bank_list;

CREATE DATABASE bank_list DEFAULT CHARACTER SET 'utf8';

USE bank_list;

CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
	`sur_name` varchar(45) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_account` (
	`id` int NOT NULL AUTO_INCREMENT,
	`userAccount` int NOT NULL,
	`user_id` int NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `user_account` ADD CONSTRAINT `user_id_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);