-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema icu_management_v2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema icu_management_v2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `icu_management_v2` DEFAULT CHARACTER SET utf8 ;
USE `icu_management_v2` ;

-- -----------------------------------------------------
-- Table `icu_management_v2`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`doctor` (
  `id_doctor` CHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `specialize` VARCHAR(100) NULL,
  `street` VARCHAR(100) NULL,
  `zone` VARCHAR(45) NULL,
  PRIMARY KEY (`id_doctor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`icu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`icu` (
  `id_icu` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_cons_doctor` CHAR(14) NOT NULL,
  PRIMARY KEY (`id_icu`),
  INDEX `id_doctor_idx` (`id_cons_doctor` ASC) VISIBLE,
  CONSTRAINT `FK_icu_id_doctor`
    FOREIGN KEY (`id_cons_doctor`)
    REFERENCES `icu_management_v2`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`patient` (
  `id_patient` CHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `birth` DATE NOT NULL,
  `gender` ENUM('Male', 'Female') NOT NULL,
  `weight` DOUBLE NULL,
  `height` DOUBLE NULL,
  `street` VARCHAR(100) NULL,
  `zone` VARCHAR(45) NULL,
  `companion_name` VARCHAR(100) NULL,
  `companion_phone` CHAR(11) NULL,
  `patient_phone` CHAR(11) NULL,
  `id_icu` INT NULL DEFAULT NULL,
  `diagnosis` VARCHAR(45) NULL,
  PRIMARY KEY (`id_patient`),
  INDEX `id_icu_idx` (`id_icu` ASC) VISIBLE,
  CONSTRAINT `FK_patient_id_icu`
    FOREIGN KEY (`id_icu`)
    REFERENCES `icu_management_v2`.`icu` (`id_icu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`unit` (
  `id_unit` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_icu` INT NOT NULL,
  PRIMARY KEY (`id_unit`),
  INDEX `id_icu_idx` (`id_icu` ASC) VISIBLE,
  CONSTRAINT `FK_unit_id_icu`
    FOREIGN KEY (`id_icu`)
    REFERENCES `icu_management_v2`.`icu` (`id_icu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`device` (
  `id_device` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_unit` INT NOT NULL,
  PRIMARY KEY (`id_device`),
  INDEX `id_unit_idx` (`id_unit` ASC) VISIBLE,
  CONSTRAINT `FK_device_id_unit`
    FOREIGN KEY (`id_unit`)
    REFERENCES `icu_management_v2`.`unit` (`id_unit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`coordinate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`coordinate` (
  `id_coordinate` BIGINT NOT NULL AUTO_INCREMENT,
  `x1` DOUBLE NOT NULL,
  `y1` DOUBLE NOT NULL,
  `x2` DOUBLE NOT NULL,
  `y2` DOUBLE NOT NULL,
  PRIMARY KEY (`id_coordinate`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`sign`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`sign` (
  `id_sign` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_sign`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`shift`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`shift` (
  `id_shift` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  PRIMARY KEY (`id_shift`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`medicine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`medicine` (
  `id_medicine` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id_medicine`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`nurse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`nurse` (
  `id_nurse` CHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `street` VARCHAR(100) NULL,
  `zone` VARCHAR(45) NULL,
  PRIMARY KEY (`id_nurse`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`task` (
  `id_task` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(500) NULL,
  `status` ENUM('done', 'not done') NOT NULL,
  `donedate` DATETIME NULL,
  `duedate` DATETIME NULL,
  `id_doctor` CHAR(14) NOT NULL,
  `id_patient` CHAR(14) NOT NULL,
  `id_medicine` INT NULL,
  `id_nurse` CHAR(14) NULL,
  PRIMARY KEY (`id_task`),
  INDEX `id_doctor_idx` (`id_doctor` ASC) VISIBLE,
  INDEX `id_patient_idx` (`id_patient` ASC) VISIBLE,
  INDEX `id_medicine_idx` (`id_medicine` ASC) VISIBLE,
  INDEX `id_nurse_idx` (`id_nurse` ASC) VISIBLE,
  CONSTRAINT `FK_task_id_doctor`
    FOREIGN KEY (`id_doctor`)
    REFERENCES `icu_management_v2`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management_v2`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_id_medicine`
    FOREIGN KEY (`id_medicine`)
    REFERENCES `icu_management_v2`.`medicine` (`id_medicine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_id_nurse`
    FOREIGN KEY (`id_nurse`)
    REFERENCES `icu_management_v2`.`nurse` (`id_nurse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`shift_doctor_unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`shift_doctor_unit` (
  `id_doctor` CHAR(14) NOT NULL,
  `id_unit` INT NOT NULL,
  `id_shift` INT NOT NULL,
  PRIMARY KEY (`id_unit`, `id_shift`, `id_doctor`),
  INDEX `id_unit_idx` (`id_unit` ASC) VISIBLE,
  INDEX `id_shift_idx` (`id_shift` ASC) INVISIBLE,
  CONSTRAINT `FK_shift_doctor_unit_id_doctor`
    FOREIGN KEY (`id_doctor`)
    REFERENCES `icu_management_v2`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_doctor_unit_id_unit`
    FOREIGN KEY (`id_unit`)
    REFERENCES `icu_management_v2`.`unit` (`id_unit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_doctor_unit_id_shift`
    FOREIGN KEY (`id_shift`)
    REFERENCES `icu_management_v2`.`shift` (`id_shift`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`phone_doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`phone_doctor` (
  `id_doctor` CHAR(14) NOT NULL,
  `phone` CHAR(11) NOT NULL,
  PRIMARY KEY (`id_doctor`, `phone`),
  CONSTRAINT `FK_phone_doctor_id_doctor`
    FOREIGN KEY (`id_doctor`)
    REFERENCES `icu_management_v2`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`phone_nurse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`phone_nurse` (
  `id_nurse` CHAR(14) NOT NULL,
  `phone` CHAR(11) NOT NULL,
  PRIMARY KEY (`id_nurse`, `phone`),
  CONSTRAINT `FK_phone_nurse_id_nurse`
    FOREIGN KEY (`id_nurse`)
    REFERENCES `icu_management_v2`.`nurse` (`id_nurse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`shift_nurse_patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`shift_nurse_patient` (
  `id_nurse` CHAR(14) NOT NULL,
  `id_shift` INT NOT NULL,
  `id_patient` CHAR(14) NOT NULL,
  PRIMARY KEY (`id_nurse`, `id_shift`, `id_patient`),
  INDEX `id_patient_idx` (`id_patient` ASC) INVISIBLE,
  INDEX `id_shift_idx` (`id_shift` ASC) VISIBLE,
  CONSTRAINT `FK_shift_nurse_patient_id_nurse`
    FOREIGN KEY (`id_nurse`)
    REFERENCES `icu_management_v2`.`nurse` (`id_nurse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_nurse_patient_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management_v2`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_nurse_patient_id_shift`
    FOREIGN KEY (`id_shift`)
    REFERENCES `icu_management_v2`.`shift` (`id_shift`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`device_sign_coordinate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`device_sign_coordinate` (
  `id_device` INT NOT NULL,
  `id_sign` INT NOT NULL,
  `id_coordinate` BIGINT NOT NULL,
  PRIMARY KEY (`id_device`, `id_sign`, `id_coordinate`),
  INDEX `id_sign_idx` (`id_sign` ASC) VISIBLE,
  INDEX `id_coordinate_idx` (`id_coordinate` ASC) VISIBLE,
  CONSTRAINT `FK_device_sign_coordinate_id_device`
    FOREIGN KEY (`id_device`)
    REFERENCES `icu_management_v2`.`device` (`id_device`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_device_sign_coordinate_id_sign`
    FOREIGN KEY (`id_sign`)
    REFERENCES `icu_management_v2`.`sign` (`id_sign`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_device_sign_coordinate_id_coordinate`
    FOREIGN KEY (`id_coordinate`)
    REFERENCES `icu_management_v2`.`coordinate` (`id_coordinate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`patient_device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`patient_device` (
  `id_patient` CHAR(14) NOT NULL,
  `id_device` INT NOT NULL,
  PRIMARY KEY (`id_patient`, `id_device`),
  INDEX `id_device_idx` (`id_device` ASC) VISIBLE,
  CONSTRAINT `FK_patient_device_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management_v2`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_patient_device_id_device`
    FOREIGN KEY (`id_device`)
    REFERENCES `icu_management_v2`.`device` (`id_device`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`patient_icu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`patient_icu` (
  `id_patient_icu` BIGINT NOT NULL AUTO_INCREMENT,
  `out_reason` ENUM('deceased', 'family_request', 'treated') NULL,
  `date_in` DATE NULL,
  `date_out` DATE NULL,
  `id_icu` INT NOT NULL,
  `id_patient` CHAR(14) NOT NULL,
  PRIMARY KEY (`id_patient_icu`),
  INDEX `id_icu_idx` (`id_icu` ASC) VISIBLE,
  INDEX `FK_patient_icu_id_patient_idx` (`id_patient` ASC) VISIBLE,
  CONSTRAINT `FK_patient_icu_id_icu`
    FOREIGN KEY (`id_icu`)
    REFERENCES `icu_management_v2`.`icu` (`id_icu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_patient_icu_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management_v2`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`user` (
  `id_user` CHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `phone` CHAR(11) NOT NULL,
  `profile_picture` TEXT NULL DEFAULT NULL,
  `role` ENUM('DOCTOR', 'NURSE') NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management_v2`.`otp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management_v2`.`otp` (
  `id_otp` BIGINT NOT NULL AUTO_INCREMENT,
  `otp_code` VARCHAR(10) NOT NULL,
  `expiration_date` DATETIME NOT NULL,
  `id_user` CHAR(14) NOT NULL,
  `used` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_otp`),
  INDEX `FK_otp_id_user_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `FK_otp_id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `icu_management_v2`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
