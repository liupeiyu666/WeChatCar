/*
Navicat MySQL Data Transfer

Source Server         : CarChange
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : carchange

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-14 14:02:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car_buy
-- ----------------------------
DROP TABLE IF EXISTS `car_buy`;
CREATE TABLE `car_buy` (
  `WxOpenId` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Number` int(11) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  UNIQUE KEY `WxOpenId` (`WxOpenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_buy
-- ----------------------------

-- ----------------------------
-- Table structure for car_sell
-- ----------------------------
DROP TABLE IF EXISTS `car_sell`;
CREATE TABLE `car_sell` (
  `WxOpenId` varchar(255) DEFAULT NULL,
  `SellId` int(11) DEFAULT NULL COMMENT '商品的id，通过wxopenid和sellid来确定唯一性',
  `Title` varchar(255) DEFAULT NULL,
  `GoodsName` varchar(255) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Inventory` int(255) DEFAULT NULL COMMENT '数量',
  `SourceUrl` text,
  `Location` mediumtext,
  `Date` varchar(255) DEFAULT NULL,
  `Longitude` double(255,5) DEFAULT NULL COMMENT '经度',
  `Latitude` double(255,5) DEFAULT NULL COMMENT '纬度',
  `NickName` varchar(255) DEFAULT NULL,
  `AvatarUrl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_sell
-- ----------------------------

-- ----------------------------
-- Table structure for car_users
-- ----------------------------
DROP TABLE IF EXISTS `car_users`;
CREATE TABLE `car_users` (
  `WxOpenId` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `PersonalNote` varchar(255) DEFAULT NULL,
  `VipLevel` int(255) NOT NULL DEFAULT '0',
  UNIQUE KEY `WxOpenId` (`WxOpenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_users
-- ----------------------------
INSERT INTO `car_users` VALUES ('wefwefw33', '1120055803', 'weewfwf', '0');
INSERT INTO `car_users` VALUES ('o18XI5WCYontAjTArEykMhk0Iu8o', null, null, '0');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'eee', '1234');
INSERT INTO `users` VALUES ('2', 'ggg', '565');
INSERT INTO `users` VALUES ('3', 'wwwe', '45443');
