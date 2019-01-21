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
  `gender` INT(2) DEFAULT '1' COMMENT '1：男，2：女',
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
  `product_id`          INT(10)      NOT NULL AUTO_INCREMENT,
  `product_name`        VARCHAR(128) NOT NULL,
  `product_desc`        VARCHAR(2000)         DEFAULT NULL,
  `image_addr`          VARCHAR(128)          DEFAULT '',
  `normal_price`        VARCHAR(100)          DEFAULT NULL,
  `promotion_price`     VARCHAR(100)          DEFAULT NULL,
  `priority`            INT(2)       NOT NULL DEFAULT '0',
  `create_time`         DATETIME              DEFAULT NULL,
  `update_time`         DATETIME              DEFAULT NULL,
  `enable_status`       INT(2)       NOT NULL DEFAULT '0',
  `product_category_id` INT(10)               DEFAULT NULL,
  `shop_id`             INT(10)      NOT NULL DEFAULT '0',
  PRIMARY KEY(`product_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category`(`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop`(`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

USE `o2o`;
DROP TABLE IF EXISTS `tb_product_img`;
create table `tb_product_img` (
  `product_img_id` INT(10) NOT NULL AUTO_INCREMENT,
  `img_addr`       VARCHAR(128)     DEFAULT '',
  `img_desc`       VARCHAR(1024)    DEFAULT NULL,
  `priority`       INT(2)  NOT NULL DEFAULT '0',
  `create_time`    DATETIME         DEFAULT NULL,
  `product_id`     INT(10) NOT NULL,
  PRIMARY KEY (`product_img_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product__id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

## 初始化区域数据表数据
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('东区', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('西区', 2, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('豫园', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('沁园', 3, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('天健园', 2, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('南区', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('韵园', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('紫崧', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('百景园', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('北区', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');
INSERT INTO `o2o`.`tb_area` (`area_name`, `priority`, `create_time`, `update_time`) VALUES ('喻园', 1, '2019-01-02 06:22:48', '2019-01-02 06:22:59');

## 初始化用户信息数据表数据
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('wang', ' ', ' ', 1, 1, 3, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('zhu', ' ', ' ', 2, 1, 3, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('lei', ' ', ' ', 1, 1, 3, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('huangmenji', ' ', ' ', 1, 1, 2, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('hualaishi', ' ', ' ', 2, 1, 2, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('huangguan', ' ', ' ', 1, 1, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('kfc', ' ', ' ', 1, 2, 2, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('jianhua', ' ', ' ', 1, 2, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('litao', ' ', ' ', 1, 1, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('wangmeng', ' ', ' ', 1, 2, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('hanliu', ' ', ' ', 1, 2, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('rujia', ' ', ' ', 1, 2, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');
INSERT INTO `o2o`.`tb_person` (`user_name`, `profile_img`, `email`, `gender`, `enable_status`, `role_type`, `create_time`, `update_time`) VALUES ('xindongfang', ' ', ' ', 1, 2, 1, '2019-01-02 06:41:10', '2019-01-02 06:41:14');


## 初始化店铺类别数据表数据
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('美食', '名以食为天', ' ', 9, '2019-01-03 08:31:34', '2019-01-03 08:31:39', NULL);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('娱乐', '像绅士一样的玩', ' ', 8, '2019-01-03 08:31:34', '2019-01-03 08:31:39', NULL);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('酒店', '家的味道', ' ', 7, '2019-01-03 08:31:34', '2019-01-03 08:31:39', NULL);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('学习', '像狗一样的学', ' ', 6, '2019-01-03 08:31:34', '2019-01-03 08:31:39', NULL);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('中餐', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 1);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('西餐', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 1);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('火锅', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 1);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('烧烤', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 1);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('奶茶', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 1);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('KTV', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 2);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('影院', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 2);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('轰趴', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 2);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('快捷酒店', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 3);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('民宿', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 3);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('外语', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 4);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('美术', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 4);
INSERT INTO `o2o`.`tb_shop_category` (`shop_category_name`, `shop_category_desc`, `shop_category_img`, `priority`, `create_time`, `update_time`, `parent_id`) VALUES ('音乐', '', ' ', 5, '2019-01-03 08:31:34', '2019-01-03 08:31:39', 4);
