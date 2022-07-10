-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinemar2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinemar2` ;

-- -----------------------------------------------------
-- Schema cinemar2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinemar2` DEFAULT CHARACTER SET utf8 ;
USE `cinemar2` ;

-- -----------------------------------------------------
-- Table `cinemar2`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`usuario` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`usuario` (
  `numUSUARIO` INT(11) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellido` VARCHAR(20) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `numeroCelular` VARCHAR(10) NOT NULL,
  `email` VARCHAR(35) NULL DEFAULT NULL,
  `rol` ENUM('Administrador', 'Cliente') NOT NULL,
  PRIMARY KEY (`numUSUARIO`),
  UNIQUE INDEX `numUSUARIO_UNIQUE` (`numUSUARIO` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`administrador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`administrador` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`administrador` (
  `numADMINISTRADOR` INT(11) NOT NULL,
  `idADMINISTRADOR` VARCHAR(15) NOT NULL,
  `contraseña` VARCHAR(16) NOT NULL DEFAULT 'CINEMAR!22',
  `antigüedad` INT(11) NOT NULL,
  `usuario_numUSUARIO` INT(11) NOT NULL,
  PRIMARY KEY (`idADMINISTRADOR`),
  UNIQUE INDEX `idADMINISTRADOR_UNIQUE` (`idADMINISTRADOR` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`descuento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`descuento` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`descuento` (
  `num` INT(11) NOT NULL,
  `idDESCUENTO` VARCHAR(15) NOT NULL,
  `descuento` FLOAT NOT NULL,
  PRIMARY KEY (`idDESCUENTO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`clasificación`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`clasificación` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`clasificación` (
  `idCLASIFICACIÓN` INT(11) NOT NULL,
  `descripcion` LONGTEXT NOT NULL,
  `tipo` ENUM('ATP', '+13', '+16', '+18') NOT NULL,
  PRIMARY KEY (`idCLASIFICACIÓN`),
  UNIQUE INDEX `idCLASIFICACIÓN_UNIQUE` (`idCLASIFICACIÓN` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`película`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`película` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`película` (
  `idPELÍCULA` INT(11) NOT NULL,
  `título` VARCHAR(50) NOT NULL,
  `duración(min)` INT(11) NOT NULL,
  `genero` VARCHAR(20) NOT NULL,
  `idioma` VARCHAR(15) NOT NULL,
  `clasificación_idCLASIFICACIÓN` INT(11) NOT NULL,
  PRIMARY KEY (`idPELÍCULA`),
  UNIQUE INDEX `idPELÍCULA_UNIQUE` (`idPELÍCULA` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`sesión`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`sesión` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`sesión` (
  `idSESIÓN` INT(11) NOT NULL,
  `hora` TIME NOT NULL,
  `precio` DOUBLE NOT NULL,
  `día` VARCHAR(10) NOT NULL,
  `película_idPELÍCULA` INT(11) NOT NULL,
  `descuento_idDESCUENTO` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idSESIÓN`),
  UNIQUE INDEX `idSESIÓN_UNIQUE` (`idSESIÓN` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`sala` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`sala` (
  `idSALA` VARCHAR(3) NOT NULL,
  `numSALA` INT NOT NULL,
  `tipo` ENUM('2D', '3D') NOT NULL,
  `capacidad` INT(200) NOT NULL,
  `sesión_idSESIÓN` INT(11) NOT NULL,
  PRIMARY KEY (`idSALA`),
  UNIQUE INDEX `idSALA_UNIQUE` (`idSALA` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`butaca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`butaca` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`butaca` (
  `idBUTACA` VARCHAR(10) NOT NULL,
  `fila` CHAR(2) NOT NULL,
  `numero` INT(20) NOT NULL,
  `sala_idSALA` VARCHAR(3) NOT NULL,
  `reservada` ENUM('si', 'no') NOT NULL,
  PRIMARY KEY (`idBUTACA`),
  UNIQUE INDEX `idBUTACA_UNIQUE` (`idBUTACA` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`cliente` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`cliente` (
  `numCLIENTE` INT(11) NOT NULL,
  `idCLIENTE` VARCHAR(15) NOT NULL,
  `contraseña` VARCHAR(16) NOT NULL,
  `fechaRegistro` DATE NOT NULL,
  `usuario_numUSUARIO` INT(11) NOT NULL,
  PRIMARY KEY (`idCLIENTE`),
  UNIQUE INDEX `idCLIENTE_UNIQUE` (`idCLIENTE` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`reserva` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`reserva` (
  `numRESERVA` INT(11) NOT NULL,
  `idRESERVA` VARCHAR(20) NOT NULL,
  `cliente_idCLIENTE` VARCHAR(15) NOT NULL,
  `butaca_idBUTACA` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idRESERVA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cinemar2`.`tarjeta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`tarjeta` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`tarjeta` (
  `idTARJETA` INT(11) NOT NULL,
  `fechaVencimiento` DATE NOT NULL,
  `cliente_idCLIENTE` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idTARJETA`),
  UNIQUE INDEX `idTARJETA_UNIQUE` (`idTARJETA` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
