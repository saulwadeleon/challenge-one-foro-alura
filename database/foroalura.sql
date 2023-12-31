-- MySQL Script generated by MySQL Workbench
-- Wed Sep 27 22:41:36 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema foroalura
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema foroalura
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `foroalura` DEFAULT CHARACTER SET utf8 ;
USE `foroalura` ;

-- -----------------------------------------------------
-- Table `foroalura`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foroalura`.`categoria` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `dsc_categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dsc_categoria_UNIQUE` (`dsc_categoria` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foroalura`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foroalura`.`curso` (
  `id` BIGINT NOT NULL,
  `nombre_curso` VARCHAR(200) NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_curso_categoria_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_curso_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `foroalura`.`categoria` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foroalura`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foroalura`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre_role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_role_UNIQUE` (`nombre_role` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foroalura`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foroalura`.`usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre_usuario` VARCHAR(100) NOT NULL,
  `apellido_usuario` VARCHAR(150) NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(200),
  `password` VARCHAR(255) NOT NULL,
  `role_id` BIGINT NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_role_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_rol`
    FOREIGN KEY (`role_id`)
    REFERENCES `foroalura`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foroalura`.`topico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foroalura`.`topico` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo_topico` VARCHAR(200) NOT NULL,
  `mensaje_topico` TEXT(600) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `autor_id` BIGINT NOT NULL,
  `curso_id` BIGINT NOT NULL,
  `estatus_topico` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_topico_autor_idx` (`autor_id` ASC) VISIBLE,
  INDEX `fk_topico_curso_idx` (`curso_id` ASC) VISIBLE,
  CONSTRAINT `fk_topico_autor`
    FOREIGN KEY (`autor_id`)
    REFERENCES `foroalura`.`usuario` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_topico_curso`
    FOREIGN KEY (`curso_id`)
    REFERENCES `foroalura`.`curso` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `foroalura`.`respuesta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `foroalura`.`respuesta` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `mensaje_respuesta` TEXT(600) NOT NULL,
  `topico_id` BIGINT NOT NULL,
  `fecha_respuesta` DATETIME NOT NULL,
  `autor_id` BIGINT NOT NULL,
  `solucion` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_respuesta_topico_idx` (`topico_id` ASC) VISIBLE,
  INDEX `fk_respuesta_autor_idx` (`autor_id` ASC) VISIBLE,
  CONSTRAINT `fk_respuesta_topico`
    FOREIGN KEY (`topico_id`)
    REFERENCES `foroalura`.`topico` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_respuesta_autor`
    FOREIGN KEY (`autor_id`)
    REFERENCES `foroalura`.`usuario` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `foroalura`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `foroalura`;
INSERT INTO `foroalura`.`role` (`id`, `nombre_role`) VALUES (1, 'ADMIN');
INSERT INTO `foroalura`.`role` (`id`, `nombre_role`) VALUES (2, 'USER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `foroalura`.`usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE foroalura;
INSERT INTO `foroalura`.`usuario` (`id`, `nombre_usuario`, `apellido_usuario`, `email`, `username`, `password`, `roleId`, `activo`) VALUES (1, 'Saul', 'Wade', 'saul.wade@gmail.com', 'Alura', '$2a$10$ULY.xj2PL9mBK/eEjd4uqe.Wdjxp/QYbYmqoQT1.XJ0oGwaDrGohK', 1, 1);

COMMIT;

