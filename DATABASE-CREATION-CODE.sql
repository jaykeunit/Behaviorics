-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Behaviorics_Development
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Behaviorics_Development
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Behaviorics_Development` DEFAULT CHARACTER SET latin1 ;
USE `Behaviorics_Development` ;

-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`States`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`States` (
  `stateID` SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Unique state ID',
  `stateName` VARCHAR(32) NOT NULL COMMENT 'State name with first letter capital',
  `stateAbbr` VARCHAR(8) NOT NULL COMMENT 'Optional state abbreviation (US is 2 capital letters)',
  PRIMARY KEY (`stateID`),
  UNIQUE INDEX `stateAbbr_UNIQUE` (`stateAbbr` ASC),
  UNIQUE INDEX `stateName_UNIQUE` (`stateName` ASC),
  UNIQUE INDEX `stateID_UNIQUE` (`stateID` ASC),
  INDEX `abbr` (`stateAbbr` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 53
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`Organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`Organization` (
  `organizationID` INT(11) NOT NULL AUTO_INCREMENT,
  `organizationName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`organizationID`),
  UNIQUE INDEX `organizationID_UNIQUE` (`organizationID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`Entity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`Entity` (
  `entityID` INT(11) NOT NULL AUTO_INCREMENT,
  `organizationID` INT(11) NOT NULL,
  `entityName` VARCHAR(45) NOT NULL,
  `contactNumber` VARCHAR(10) NULL DEFAULT NULL,
  `entityAcronym` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`entityID`),
  UNIQUE INDEX `entityID_UNIQUE` (`entityID` ASC),
  UNIQUE INDEX `entityAcronym_UNIQUE` (`entityAcronym` ASC),
  UNIQUE INDEX `entityName_UNIQUE` (`entityName` ASC),
  INDEX `EntityOrganizationID_idx` (`organizationID` ASC),
  CONSTRAINT `EntityOrganizationID`
    FOREIGN KEY (`organizationID`)
    REFERENCES `Behaviorics_Development`.`Organization` (`organizationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`Building`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`Building` (
  `buildingID` INT(11) NOT NULL AUTO_INCREMENT,
  `buildingName` VARCHAR(45) NOT NULL,
  `entityID` INT(11) NOT NULL,
  `streetCode` INT(11) NOT NULL,
  `streetName` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `zipcode` INT(5) NOT NULL,
  `buildingAcronym` VARCHAR(45) NOT NULL,
  `state` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`buildingID`),
  UNIQUE INDEX `buildingID_UNIQUE` (`buildingID` ASC),
  INDEX `buildingToState_idx` (`state` ASC),
  INDEX `buildingsToEntities_idx` (`entityID` ASC),
  CONSTRAINT `buildingToState`
    FOREIGN KEY (`state`)
    REFERENCES `Behaviorics_Development`.`States` (`stateAbbr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `buildingsToEntities`
    FOREIGN KEY (`entityID`)
    REFERENCES `Behaviorics_Development`.`Entity` (`entityID`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`Floors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`Floors` (
  `floorID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `floorNumber` INT(11) NOT NULL,
  `buildingID` INT(11) NOT NULL,
  `floorType` ENUM('F', 'B', 'R', 'EX') NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`floorID`),
  UNIQUE INDEX `floorID_UNIQUE` (`floorID` ASC),
  UNIQUE INDEX `numberAndBuilding_UNIQUE` (`floorNumber` ASC, `buildingID` ASC),
  INDEX `floorsToBuildings_idx` (`buildingID` ASC),
  CONSTRAINT `floorsToBuildings`
    FOREIGN KEY (`buildingID`)
    REFERENCES `Behaviorics_Development`.`Building` (`buildingID`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 196
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`Cameras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`Cameras` (
  `cameraID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cameraName` VARCHAR(45) NOT NULL,
  `feedIP` VARCHAR(45) NOT NULL,
  `feedCredential` VARCHAR(45) NULL DEFAULT NULL,
  `feedPassword` VARCHAR(45) NULL DEFAULT NULL,
  `locationX` DOUBLE NOT NULL,
  `locationY` DOUBLE NOT NULL,
  `dateInstalled` DATE NOT NULL,
  `floorID` INT(11) UNSIGNED NOT NULL,
  `vendorName` VARCHAR(45) NULL DEFAULT NULL,
  `localMaintenanceName` VARCHAR(45) NULL DEFAULT NULL,
  `localMaintenanceNumber` VARCHAR(10) NULL DEFAULT NULL,
  `vendorPhoneNumber` VARCHAR(10) NULL DEFAULT NULL,
  `vendorEmail` VARCHAR(45) NULL DEFAULT NULL,
  `warrantyExpiration` VARCHAR(45) NULL DEFAULT NULL,
  `cameraStatus` ENUM('Working', 'Down', 'UnderRepair') NOT NULL DEFAULT 'Working',
  `httpPort` INT(11) NULL DEFAULT '88',
  `onvifPort` INT(11) NULL DEFAULT '888',
  `orgID` INT(10) NOT NULL,
  PRIMARY KEY (`cameraID`),
  UNIQUE INDEX `cameraName_UNIQUE` (`cameraName` ASC),
  UNIQUE INDEX `cameraID_UNIQUE` (`cameraID` ASC),
  INDEX `cameraToFloor_idx` (`floorID` ASC),
  CONSTRAINT `cameraToFloor`
    FOREIGN KEY (`floorID`)
    REFERENCES `Behaviorics_Development`.`Floors` (`floorID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`RepairLogs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`RepairLogs` (
  `repairID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cameraID` INT(10) UNSIGNED NOT NULL,
  `dateFailed` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `repairStatus` ENUM('Working', 'Down', 'UnderRepair') NOT NULL,
  `failReason` ENUM('Water Damage', 'Blurred Image', 'Broken Camera', 'Bad Batteries', 'Broken Zoom Lens', 'Sensor Malfunction', 'LCD Cracked', 'Software Issue', 'Bad Image quality', 'Lens Obstructed', 'Night Light Issue', 'Corrupted Image', 'Other') NOT NULL,
  `dateRepaired` DATETIME NULL DEFAULT NULL,
  `repairRequestDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`repairID`),
  UNIQUE INDEX `repairID_UNIQUE` (`repairID` ASC),
  INDEX `repairlogToCameraID_idx` (`cameraID` ASC),
  CONSTRAINT `repairlogToCameraID`
    FOREIGN KEY (`cameraID`)
    REFERENCES `Behaviorics_Development`.`Cameras` (`cameraID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`FailImages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`FailImages` (
  `failImagesID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `image` LONGBLOB NOT NULL,
  `repairID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`failImagesID`),
  UNIQUE INDEX `failImagesID_UNIQUE` (`failImagesID` ASC),
  INDEX `FailImagesToRepairLogs_idx` (`repairID` ASC),
  CONSTRAINT `FailImagesToRepairLogs`
    FOREIGN KEY (`repairID`)
    REFERENCES `Behaviorics_Development`.`RepairLogs` (`repairID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`FloorPlan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`FloorPlan` (
  `floorPlanID` INT(11) NOT NULL AUTO_INCREMENT,
  `image` LONGBLOB NOT NULL,
  `floorID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`floorPlanID`),
  UNIQUE INDEX `FloorPlanID_UNIQUE` (`floorPlanID` ASC),
  UNIQUE INDEX `floorID_UNIQUE` (`floorID` ASC),
  INDEX `planToFloor_idx` (`floorID` ASC),
  CONSTRAINT `planToFloor`
    FOREIGN KEY (`floorID`)
    REFERENCES `Behaviorics_Development`.`Floors` (`floorID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 81
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`Users` (
  `usersID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `hash` BLOB NOT NULL,
  `salt` BLOB NOT NULL,
  `privilege` ENUM('tech', 'admin') NOT NULL,
  `organizationID` INT(11) NOT NULL,
  PRIMARY KEY (`usersID`),
  UNIQUE INDEX `usersID_UNIQUE` (`usersID` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `organizationID_idx` (`organizationID` ASC),
  CONSTRAINT `organizationID`
    FOREIGN KEY (`organizationID`)
    REFERENCES `Behaviorics_Development`.`Organization` (`organizationID`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 91
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `Behaviorics_Development`.`WorkingStateImages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Behaviorics_Development`.`WorkingStateImages` (
  `imageID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `image` LONGBLOB NOT NULL,
  `cameraID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`imageID`),
  UNIQUE INDEX `imageID_UNIQUE` (`imageID` ASC),
  UNIQUE INDEX `cameraID_UNIQUE` (`cameraID` ASC),
  INDEX `WorkingStateToCameras_idx` (`cameraID` ASC),
  CONSTRAINT `WorkingStateToCameras`
    FOREIGN KEY (`cameraID`)
    REFERENCES `Behaviorics_Development`.`Cameras` (`cameraID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Default ORGANIZATION
-- -----------------------------------------------------
INSERT INTO `Behaviorics_Development`.`Organization` (`organizationID`, `organizationName`) VALUES ('1', 'UniversityOfHouston');

-- -----------------------------------------------------
-- Default User
-- -----------------------------------------------------

INSERT INTO `Behaviorics_Development`.`Users` (`username`, `hash`, `salt`, `privilege`, `organizationID`) VALUES ('admin', UNHEX('154ABC0ADC7FFDDF60265B164705B93E1854600259FC4CAF01195619D17AE8C8'), UNHEX('535DC1446CA40D3A'), 'admin', '1');