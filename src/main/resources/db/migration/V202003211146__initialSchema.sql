-- -----------------------------------------------------
-- Table `gazapp`.`role_identity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gazapp`.`role_identity` ;

CREATE TABLE IF NOT EXISTS `gazapp`.`role_identity` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45)  NOT NULL ,
  `description` VARCHAR(45)  NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gazapp`.`user_identity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gazapp`.`user_identity` ;

CREATE TABLE IF NOT EXISTS `gazapp`.`user_identity` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT,
  `email_id` VARCHAR(45)  NOT NULL ,
  `mobile_number` bigint(10)  NOT NULL ,
  `hash` BLOB  NOT NULL ,
  `salt` BLOB  NOT NULL ,
  `first_name` VARCHAR(45)  NOT NULL ,
  `last_name` VARCHAR(45)  NOT NULL ,
  `otp_validated` VARCHAR(45) NOT NULL DEFAULT 0,
  `mail_validated` TINYINT  NOT NULL DEFAULT 0,
  `business_type` VARCHAR(45)  NOT NULL ,
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `created_by` VARCHAR(45)  NOT NULL ,
  `updated_by` VARCHAR(45)  NOT NULL ,
  `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `role_id` bigint(20)   NOT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `emailId_UNIQUE` (`email_id` ASC) VISIBLE,
  UNIQUE INDEX `mobile_number_UNIQUE` (`mobile_number` ASC) VISIBLE,
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `gazapp`.`role_identity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gazapp`.`commercial_user_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gazapp`.`commercial_user_address` ;

CREATE TABLE IF NOT EXISTS `gazapp`.`commercial_user_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `business_name` VARCHAR(45)  NOT NULL ,
  `business_address` VARCHAR(255)  NOT NULL ,
  `gst_number` VARCHAR(45)  NOT NULL ,
  `billing_address` VARCHAR(255)  NOT NULL ,
  `user_id` bigint(20)   NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `gazapp`.`user_identity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

