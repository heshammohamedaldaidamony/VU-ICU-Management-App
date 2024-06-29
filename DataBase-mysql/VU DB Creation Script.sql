-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema icu_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema icu_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `icu_management` DEFAULT CHARACTER SET utf8 ;
USE `icu_management` ;

-- -----------------------------------------------------
-- Table `icu_management`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`doctor` (
  `id_doctor` CHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `specialize` VARCHAR(100) NULL,
  `street` VARCHAR(100) NULL,
  `zone` VARCHAR(45) NULL,
  `picture` BLOB NULL,
  PRIMARY KEY (`id_doctor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`icu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`icu` (
  `id_icu` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_doctor` CHAR(14) NOT NULL,
  PRIMARY KEY (`id_icu`),
  INDEX `id_doctor_idx` (`id_doctor` ASC) VISIBLE,
  CONSTRAINT `FK_icu_id_doctor`
    FOREIGN KEY (`id_doctor`)
    REFERENCES `icu_management`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`patient` (
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
  `id_icu` INT NULL,
  `date_in` DATE NULL,
  `date_out` DATE NULL,
  `diagnosis` VARCHAR(45) NULL,
  PRIMARY KEY (`id_patient`),
  INDEX `id_icu_idx` (`id_icu` ASC) VISIBLE,
  CONSTRAINT `FK_patient_id_icu`
    FOREIGN KEY (`id_icu`)
    REFERENCES `icu_management`.`icu` (`id_icu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`unit` (
  `id_unit` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_icu` INT NOT NULL,
  PRIMARY KEY (`id_unit`),
  INDEX `id_icu_idx` (`id_icu` ASC) VISIBLE,
  CONSTRAINT `FK_unit_id_icu`
    FOREIGN KEY (`id_icu`)
    REFERENCES `icu_management`.`icu` (`id_icu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`device` (
  `id_device` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `id_unit` INT NOT NULL,
  PRIMARY KEY (`id_device`),
  INDEX `id_unit_idx` (`id_unit` ASC) VISIBLE,
  CONSTRAINT `FK_device_id_unit`
    FOREIGN KEY (`id_unit`)
    REFERENCES `icu_management`.`unit` (`id_unit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`coordinate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`coordinate` (
  `id_coordinate` INT NOT NULL AUTO_INCREMENT,
  `x1` DOUBLE NOT NULL,
  `y1` DOUBLE NOT NULL,
  `x2` DOUBLE NOT NULL,
  `y2` DOUBLE NOT NULL,
  PRIMARY KEY (`id_coordinate`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`sign`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`sign` (
  `id_sign` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_sign`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`shift`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`shift` (
  `id_shift` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  PRIMARY KEY (`id_shift`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`medicine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`medicine` (
  `id_medicine` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id_medicine`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`nurse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`nurse` (
  `id_nurse` CHAR(14) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `street` VARCHAR(100) NULL,
  `zone` VARCHAR(45) NULL,
  `picture` BLOB NULL,
  PRIMARY KEY (`id_nurse`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`task` (
  `id_task` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(500) NULL,
  `status` ENUM('done', 'not done') NOT NULL,
  `donedate` TIMESTAMP NULL,
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
    REFERENCES `icu_management`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_id_medicine`
    FOREIGN KEY (`id_medicine`)
    REFERENCES `icu_management`.`medicine` (`id_medicine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_id_nurse`
    FOREIGN KEY (`id_nurse`)
    REFERENCES `icu_management`.`nurse` (`id_nurse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`medical_report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`medical_report` (
  `id_med_report` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NULL,
  `id_patient` CHAR(11) NOT NULL,
  PRIMARY KEY (`id_med_report`),
  INDEX `id_patient_idx` (`id_patient` ASC) VISIBLE,
  CONSTRAINT `FK_medical_report_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`image` (
  `id_image` INT NOT NULL AUTO_INCREMENT,
  `path` BLOB NOT NULL,
  `id_med_report` BIGINT NOT NULL,
  PRIMARY KEY (`id_image`),
  INDEX `id_medical_report_idx` (`id_med_report` ASC) VISIBLE,
  CONSTRAINT `FK_image_id_med_report`
    FOREIGN KEY (`id_med_report`)
    REFERENCES `icu_management`.`medical_report` (`id_med_report`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`shift_doctor_unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`shift_doctor_unit` (
  `id_doctor` CHAR(14) NOT NULL,
  `id_unit` INT NOT NULL,
  `id_shift` INT NOT NULL,
  PRIMARY KEY (`id_unit`, `id_shift`, `id_doctor`),
  INDEX `id_unit_idx` (`id_unit` ASC) VISIBLE,
  INDEX `id_shift_idx` (`id_shift` ASC) INVISIBLE,
  CONSTRAINT `FK_shift_doctor_unit_id_doctor`
    FOREIGN KEY (`id_doctor`)
    REFERENCES `icu_management`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_doctor_unit_id_unit`
    FOREIGN KEY (`id_unit`)
    REFERENCES `icu_management`.`unit` (`id_unit`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_doctor_unit_id_shift`
    FOREIGN KEY (`id_shift`)
    REFERENCES `icu_management`.`shift` (`id_shift`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`phone_doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`phone_doctor` (
  `id_doctor` CHAR(14) NOT NULL,
  `phone` CHAR(11) NOT NULL,
  PRIMARY KEY (`id_doctor`, `phone`),
  CONSTRAINT `FK_phone_doctor_id_doctor`
    FOREIGN KEY (`id_doctor`)
    REFERENCES `icu_management`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`phone_nurse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`phone_nurse` (
  `id_nurse` CHAR(14) NOT NULL,
  `phone` CHAR(11) NOT NULL,
  PRIMARY KEY (`id_nurse`, `phone`),
  CONSTRAINT `FK_phone_nurse_id_nurse`
    FOREIGN KEY (`id_nurse`)
    REFERENCES `icu_management`.`nurse` (`id_nurse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`shift_nurse_patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`shift_nurse_patient` (
  `id_nurse` CHAR(14) NOT NULL,
  `id_shift` INT NOT NULL,
  `id_patient` CHAR(14) NOT NULL,
  PRIMARY KEY (`id_nurse`, `id_shift`, `id_patient`),
  INDEX `id_patient_idx` (`id_patient` ASC) INVISIBLE,
  INDEX `id_shift_idx` (`id_shift` ASC) VISIBLE,
  CONSTRAINT `FK_shift_nurse_patient_id_nurse`
    FOREIGN KEY (`id_nurse`)
    REFERENCES `icu_management`.`nurse` (`id_nurse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_nurse_patient_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_shift_nurse_patient_id_shift`
    FOREIGN KEY (`id_shift`)
    REFERENCES `icu_management`.`shift` (`id_shift`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`device_sign_coordinate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`device_sign_coordinate` (
  `id_device` INT NOT NULL,
  `id_sign` INT NOT NULL,
  `id_coordinate` INT NOT NULL,
  PRIMARY KEY (`id_device`, `id_sign`, `id_coordinate`),
  INDEX `id_sign_idx` (`id_sign` ASC) VISIBLE,
  INDEX `id_coordinate_idx` (`id_coordinate` ASC) VISIBLE,
  CONSTRAINT `FK_device_sign_coordinate_id_device`
    FOREIGN KEY (`id_device`)
    REFERENCES `icu_management`.`device` (`id_device`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_device_sign_coordinate_id_sign`
    FOREIGN KEY (`id_sign`)
    REFERENCES `icu_management`.`sign` (`id_sign`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_device_sign_coordinate_id_coordinate`
    FOREIGN KEY (`id_coordinate`)
    REFERENCES `icu_management`.`coordinate` (`id_coordinate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`vitalsign_report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`vitalsign_report` (
  `id_vs_report` BIGINT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `id_patient` CHAR(14) NOT NULL,
  PRIMARY KEY (`id_vs_report`),
  INDEX `id_patient_idx` (`id_patient` ASC) VISIBLE,
  CONSTRAINT `FK_vitalsign_report_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`measure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`measure` (
  `id_measure` BIGINT NOT NULL AUTO_INCREMENT,
  `value` DOUBLE NOT NULL,
  `time` TIME NOT NULL,
  `id_vs_report` BIGINT NOT NULL,
  `id_sign` INT NOT NULL,
  PRIMARY KEY (`id_measure`),
  INDEX `id_vs_report_idx` (`id_vs_report` ASC) VISIBLE,
  INDEX `id_sign_idx` (`id_sign` ASC) VISIBLE,
  CONSTRAINT `FK_measure_id_vs_report`
    FOREIGN KEY (`id_vs_report`)
    REFERENCES `icu_management`.`vitalsign_report` (`id_vs_report`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_measure_id_sign`
    FOREIGN KEY (`id_sign`)
    REFERENCES `icu_management`.`sign` (`id_sign`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `icu_management`.`patient_device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `icu_management`.`patient_device` (
  `id_patient` CHAR(14) NOT NULL,
  `id_device` INT NOT NULL,
  PRIMARY KEY (`id_patient`, `id_device`),
  INDEX `id_device_idx` (`id_device` ASC) VISIBLE,
  CONSTRAINT `FK_patient_device_id_patient`
    FOREIGN KEY (`id_patient`)
    REFERENCES `icu_management`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_patient_device_id_device`
    FOREIGN KEY (`id_device`)
    REFERENCES `icu_management`.`device` (`id_device`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
