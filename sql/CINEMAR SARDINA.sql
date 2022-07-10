-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cinemar2
-- -----------------------------------------------------

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
  numeroCelular VARCHAR(10) not null,
  `email` VARCHAR(35) NULL DEFAULT NULL,
  `rol` ENUM('Administrador', 'Cliente') NOT NULL,
  PRIMARY KEY (`numUSUARIO`));
  
CREATE UNIQUE INDEX `numUSUARIO_UNIQUE` ON `cinemar2`.`usuario` (`numUSUARIO` ASC) VISIBLE;

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
  CONSTRAINT `fk_administrador_usuario1`
    FOREIGN KEY (`usuario_numUSUARIO`)
    REFERENCES `cinemar2`.`usuario` (`numUSUARIO`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idADMINISTRADOR_UNIQUE` ON `cinemar2`.`administrador` (`idADMINISTRADOR` ASC) VISIBLE;

CREATE INDEX `fk_administrador_usuario1_idx` ON `cinemar2`.`administrador` (`usuario_numUSUARIO` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cinemar2`.`sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`sala` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`sala` (
  `idSALA` INT(11) NOT NULL,
  `tipo` ENUM('2D', '3D') NOT NULL,
  `capacidad` INT(200) NOT NULL,
  `administrador_idADMINISTRADOR` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idSALA`),
  CONSTRAINT `fk_sala_administrador1`
    FOREIGN KEY (`administrador_idADMINISTRADOR`)
    REFERENCES `cinemar2`.`administrador` (`idADMINISTRADOR`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idSALA_UNIQUE` ON `cinemar2`.`sala` (`idSALA` ASC) VISIBLE;

CREATE INDEX `fk_sala_administrador1_idx` ON `cinemar2`.`sala` (`administrador_idADMINISTRADOR` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cinemar2`.`butaca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`butaca` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`butaca` (
  `idBUTACA` VARCHAR(10) NOT NULL,
  `fila` CHAR(2) NOT NULL,
  `numero` INT(20) NOT NULL,
  `sala_idSALA` INT(11) NOT NULL,
  `reservada` ENUM('si', 'no') NOT NULL,
  PRIMARY KEY (`idBUTACA`),
  CONSTRAINT `fk_butaca_sala1`
    FOREIGN KEY (`sala_idSALA`)
    REFERENCES `cinemar2`.`sala` (`idSALA`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idBUTACA_UNIQUE` ON `cinemar2`.`butaca` (`idBUTACA` ASC) VISIBLE;

CREATE INDEX `fk_butaca_sala1_idx` ON `cinemar2`.`butaca` (`sala_idSALA` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cinemar2`.`clasificación`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`clasificación` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`clasificación` (
  `idCLASIFICACIÓN` INT(11) NOT NULL,
  `descripcion` LONGTEXT NOT NULL,
  `tipo` ENUM('ATP', '+13', '+16', '+18') NOT NULL,
  PRIMARY KEY (`idCLASIFICACIÓN`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idCLASIFICACIÓN_UNIQUE` ON `cinemar2`.`clasificación` (`idCLASIFICACIÓN` ASC) VISIBLE;


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
  CONSTRAINT `fk_cliente_usuario1`
    FOREIGN KEY (`usuario_numUSUARIO`)
    REFERENCES `cinemar2`.`usuario` (`numUSUARIO`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idCLIENTE_UNIQUE` ON `cinemar2`.`cliente` (`idCLIENTE` ASC) VISIBLE;

CREATE INDEX `fk_cliente_usuario1_idx` ON `cinemar2`.`cliente` (`usuario_numUSUARIO` ASC) VISIBLE;


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
  CONSTRAINT `fk_película_clasificación1`
    FOREIGN KEY (`clasificación_idCLASIFICACIÓN`)
    REFERENCES `cinemar2`.`clasificación` (`idCLASIFICACIÓN`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idPELÍCULA_UNIQUE` ON `cinemar2`.`película` (`idPELÍCULA` ASC) VISIBLE;

CREATE INDEX `fk_película_clasificación1_idx` ON `cinemar2`.`película` (`clasificación_idCLASIFICACIÓN` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cinemar2`.`sesión`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`sesión` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`sesión` (
  `idSESIÓN` INT(11) NOT NULL,
  `hora` TIME NOT NULL,
  `precio` DOUBLE NOT NULL,
  `día` VARCHAR(10) NOT NULL,
  `sala_idSALA` INT(11) NOT NULL,
  `película_idPELÍCULA` INT(11) NOT NULL,
  `descuento_idDESCUENTO` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idSESIÓN`),
  CONSTRAINT `fk_sesión_sala1`
    FOREIGN KEY (`sala_idSALA`)
    REFERENCES `cinemar2`.`sala` (`idSALA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sesión_película1`
    FOREIGN KEY (`película_idPELÍCULA`)
    REFERENCES `cinemar2`.`película` (`idPELÍCULA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sesión_descuento1`
    FOREIGN KEY (`descuento_idDESCUENTO`)
    REFERENCES `cinemar2`.`descuento` (`idDESCUENTO`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idSESIÓN_UNIQUE` ON `cinemar2`.`sesión` (`idSESIÓN` ASC) VISIBLE;

CREATE INDEX `fk_sesión_sala1_idx` ON `cinemar2`.`sesión` (`sala_idSALA` ASC) VISIBLE;

CREATE INDEX `fk_sesión_película1_idx` ON `cinemar2`.`sesión` (`película_idPELÍCULA` ASC) VISIBLE;

CREATE INDEX `fk_sesión_descuento1_idx` ON `cinemar2`.`sesión` (`descuento_idDESCUENTO` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cinemar2`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`reserva` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`reserva` (
  `numRESERVA` INT(11) NOT NULL,
  `idRESERVA` VARCHAR(20) NOT NULL,
  `sesión_idSESIÓN` INT(11) NOT NULL,
  `cliente_idCLIENTE` VARCHAR(15) NOT NULL,
  `butaca_idBUTACA` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idRESERVA`),
  CONSTRAINT `fk_reserva_sesión1`
    FOREIGN KEY (`sesión_idSESIÓN`)
    REFERENCES `cinemar2`.`sesión` (`idSESIÓN`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_cliente1`
    FOREIGN KEY (`cliente_idCLIENTE`)
    REFERENCES `cinemar2`.`cliente` (`idCLIENTE`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_butaca1`
    FOREIGN KEY (`butaca_idBUTACA`)
    REFERENCES `cinemar2`.`butaca` (`idBUTACA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_reserva_sesión1_idx` ON `cinemar2`.`reserva` (`sesión_idSESIÓN` ASC) VISIBLE;

CREATE INDEX `fk_reserva_cliente1_idx` ON `cinemar2`.`reserva` (`cliente_idCLIENTE` ASC) VISIBLE;

CREATE INDEX `fk_reserva_butaca1_idx` ON `cinemar2`.`reserva` (`butaca_idBUTACA` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `cinemar2`.`tarjeta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemar2`.`tarjeta` ;

CREATE TABLE IF NOT EXISTS `cinemar2`.`tarjeta` (
  `idTARJETA` INT(11) NOT NULL,
  `fechaVencimiento` DATE NOT NULL,
  `cliente_idCLIENTE` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idTARJETA`),
  CONSTRAINT `fk_tarjeta_cliente1`
    FOREIGN KEY (`cliente_idCLIENTE`)
    REFERENCES `cinemar2`.`cliente` (`idCLIENTE`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `idTARJETA_UNIQUE` ON `cinemar2`.`tarjeta` (`idTARJETA` ASC) VISIBLE;

CREATE INDEX `fk_tarjeta_cliente1_idx` ON `cinemar2`.`tarjeta` (`cliente_idCLIENTE` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
