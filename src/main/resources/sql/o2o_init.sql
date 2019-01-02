CREATE DATABASE `o2o`;

USE `o2o`;
DROP TABLE if exists `tb_area`;
CREATE TABLE `tb_area`(
  `area_id` INT(2) NOT NULL AUTO_INCREMENT,
  `area_name` VARCHAR(200) NOT NULL,
  `priority` INT(2) default '0',
  `create_time` DATETIME default NULL,
  `update_time` DATETIME default NULL ,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA`(`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE if exists `tb_person`;
CREATE TABLE `tb_person`(
  `user_id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(32) DEFAULT NULL,
  `profile_img` VARCHAR(1024) DEFAULT NULL,
  `email` VARCHAR(1024) DEFAULT NULL,
  `gender` VARCHAR(2) DEFAULT NULL,
  `enable_status` INT(2) NOT NULL DEFAULT '0' COMMENT '0：禁止登陆本系统；1：允许登陆本系统',
  `role_type` int(2) NOT NULL DEFAULT '1' COMMENT '1:顾客；2：商家；3：管理员',
  `create_time` DATETIME default NULL,
  `update_time` DATETIME default NULL ,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth`(
  `wechat_auth_id` INT(10) NOT NULL AUTO_INCREMENT,
  `open_id` VARCHAR(1024) NOT NULL ,
  `create_time` DATETIME DEFAULT NULL ,
  `user_id` INT(10) NOT NULL ,
  PRIMARY KEY (`wechat_auth_id`),
  CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE  tb_wechat_auth ADD UNIQUE INDEX (`open_id`);

USE `o2o`;
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth`(
  `local_auth_id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL ,
  `user_name` VARCHAR(128) NOT NULL ,
  `password` VARCHAR(128) NOT NULL ,
  `create_time` DATETIME DEFAULT NULL ,
  `update_time` DATETIME DEFAULT NULL ,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile`(`user_name`),
  CONSTRAINT `fk_localauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line`(
  `line_id` INT(100) NOT NULL AUTO_INCREMENT,
  `line_name` VARCHAR(1024) DEFAULT NULL,
  `line_link` VARCHAR(1024) DEFAULT NULL ,
  `line_img` VARCHAR(1024) DEFAULT NULL ,
  `priority` INT(2) DEFAULT NULL ,
  `enable_status` INT(2) NOT NULL DEFAULT '0' COMMENT '0：禁止；1：允许',
  `create_time` DATETIME default NULL,
  `update_time` DATETIME default NULL ,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category`(
  `shop_category_id` INT(10) NOT NULL AUTO_INCREMENT,
  `shop_category_name` VARCHAR(1024) NOT NULL DEFAULT '',
  `shop_category_desc` VARCHAR(1024) DEFAULT '',
  `shop_category_img` VARCHAR(1024) DEFAULT NULL ,
  `priority` INT(2) NOT NULL DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL ,
  `update_time` DATETIME DEFAULT NULL ,
  `parent_id` INT(10) DEFAULT NULL ,
  PRIMARY KEY (`shop_category_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category`(`shop_category_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop`(
  `shop_id` INT(10) NOT NULL AUTO_INCREMENT,
  `shop_desc` VARCHAR(1024) DEFAULT NULL ,
  `shop_img` VARCHAR(1024) DEFAULT NULL ,
  `shop_name` VARCHAR(1024) DEFAULT NULL ,
  `phone` VARCHAR(128) DEFAULT NULL ,
  `shop_addr` VARCHAR(1024) DEFAULT NULL ,
  `area_id` INT(2) DEFAULT NULL ,
  `owner_id` INT(10) NOT NULL ,
  `shop_category_id` INT(10)DEFAULT NULL ,
  `priority` INT(3) DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL ,
  `update_time` DATETIME DEFAULT NULL ,
  `enable_status` INT(2) NOT NULL DEFAULT '0',
  `advice` VARCHAR(1024),
  PRIMARY KEY (`shop_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area`(`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person`(`user_id`),
  CONSTRAINT `fk_shop_shopcategory` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category`(`shop_category_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category`(
  `product_category_id` INT(10) NOT NULL AUTO_INCREMENT,
  `product_category_name` VARCHAR(1024) NOT NULL ,
  `priority` INT(2) DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL ,
  `shop_id` INT(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  CONSTRAINT `fk_product_category_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop`(`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`(
  `product_id` INT(10) NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(128) NOT NULL ,
  `product_desc` VARCHAR(2000) DEFAULT NULL ,
  `image_addr` VARCHAR(128) DEFAULT '',
  `normal_price` VARCHAR(100) DEFAULT NULL ,
  `promotion_price` VARCHAR(100) DEFAULT NULL ,
  `prioroty` INT(2) NOT NULL DEFAULT '0',
  `create_time` DATETIME DEFAULT NULL ,
  `update_time` DATETIME DEFAULT NULL ,
  `enable_status` INT(2) NOT NULL DEFAULT '0',
  `product_category_id` INT(10) DEFAULT NULL ,
  `shop_id` INT(10) NOT NULL DEFAULT '0',
  PRIMARY KEY(`product_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category`(`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop`(`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

