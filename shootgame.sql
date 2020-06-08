/*
Navicat MySQL Data Transfer

Source Server         : CarChange
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : shootgame

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-14 14:02:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for avatar
-- ----------------------------
DROP TABLE IF EXISTS `avatar`;
CREATE TABLE `avatar` (
  `id` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of avatar
-- ----------------------------
INSERT INTO `avatar` VALUES ('1', 'Bullets/jian', null);
INSERT INTO `avatar` VALUES ('2', 'Bullets/luoxuan', null);
INSERT INTO `avatar` VALUES ('3', 'Bullets/yuandan', null);
INSERT INTO `avatar` VALUES ('4', 'Bullets/bossemit', null);
INSERT INTO `avatar` VALUES ('5', 'Bullets/One_Attack_One/begin', null);
INSERT INTO `avatar` VALUES ('6', 'Bullets/One_Attack_One/mid', null);
INSERT INTO `avatar` VALUES ('7', 'Bullets/One_Attack_One/hold', null);
INSERT INTO `avatar` VALUES ('8', 'Bullets/One_Attack_One/hit', null);
INSERT INTO `avatar` VALUES ('1000', 'Sprite/Hero/heroone', null);
INSERT INTO `avatar` VALUES ('2001', 'Sprite/Weapon/weapon_01_01', null);
INSERT INTO `avatar` VALUES ('3001', 'Sprite/Monster/monster', null);

-- ----------------------------
-- Table structure for effect
-- ----------------------------
DROP TABLE IF EXISTS `effect`;
CREATE TABLE `effect` (
  `id` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of effect
-- ----------------------------
INSERT INTO `effect` VALUES ('1', 'Bullets/jian');
INSERT INTO `effect` VALUES ('2', 'Bullets/luoxuan');
INSERT INTO `effect` VALUES ('3', 'Bullets/yuandan');
INSERT INTO `effect` VALUES ('4', 'Bullets/bossemit');
INSERT INTO `effect` VALUES ('5', 'Bullets/One_Attack_One/begin');
INSERT INTO `effect` VALUES ('6', 'Bullets/One_Attack_One/hit');
INSERT INTO `effect` VALUES ('7', 'Bullets/One_Attack_One/hold');
INSERT INTO `effect` VALUES ('8', 'Bullets/One_Attack_One/mid');
INSERT INTO `effect` VALUES ('9', 'Bullets/One_Attack_One/subMit');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  `move_speed` float DEFAULT NULL COMMENT '移动速度',
  `rotate_speed` float DEFAULT NULL COMMENT '转身速度',
  `avatar_id` int(11) DEFAULT NULL COMMENT '模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', null, '5', '3', '1000');
INSERT INTO `role` VALUES ('2', '怪物测试', '5', '3', '3001');

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `effectid` int(11) DEFAULT NULL,
  `skill_type` int(255) NOT NULL DEFAULT '0' COMMENT '0表示非持续性施法,1表示持续性施法',
  `ani_name` varchar(255) DEFAULT NULL,
  `bind_pos_begin` int(255) DEFAULT '0' COMMENT '绑定的位置',
  `bind_pos_mid` int(255) DEFAULT NULL,
  `bind_pos_hold` int(255) DEFAULT NULL,
  `bind_pos_end` int(255) DEFAULT NULL,
  `bind_des` varchar(255) DEFAULT NULL COMMENT '绑定的描述，用于偏移设置',
  `ani_begin_name` varchar(255) DEFAULT NULL,
  `ani_mid_name` varchar(255) DEFAULT NULL,
  `ani_hold_name` varchar(255) DEFAULT NULL COMMENT '施法',
  `ani_end_name` varchar(255) DEFAULT NULL,
  `effect_begin_id` int(11) DEFAULT NULL,
  `effect_mid_id` int(11) DEFAULT NULL,
  `effect_hold_id` int(11) DEFAULT NULL,
  `effect_end_id` int(11) DEFAULT NULL,
  `time_begin` float DEFAULT NULL,
  `time_mid` float DEFAULT NULL,
  `time_hold` float DEFAULT NULL,
  `time_end` float DEFAULT NULL,
  `cd_time` float DEFAULT NULL,
  `interval_time` float DEFAULT NULL COMMENT '间隔时间',
  `effect_hit_id` int(11) DEFAULT NULL COMMENT '碰撞特效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES ('1', '0', '1', '0', '0', '0', '0', '0', null, 'attack_one_begin', 'attack_one_mid', 'attack_one_hold', 'attack_one_end', '5', '8', '9', null, '0.317', '0.46', '3', '0.19', '0.96', '0.2', '6');
INSERT INTO `skill` VALUES ('2', '1', '0', null, '1', null, null, null, '2_0_0', 'attack_one_begin', 'attack_one_mid', 'attack_one_hold', 'attack_one_end', null, null, null, null, '0.317', '0.46', '0.46', '0.19', '0.96', '0.2', '6');
INSERT INTO `skill` VALUES ('3', null, '2', null, '0', '0', '0', '0', null, 'attack_one_begin', null, 'attack_one_hold', 'attack_one_end', '5', null, '9', null, '0.317', '0.46', '0.46', '0.19', null, '0.2', '6');
INSERT INTO `skill` VALUES ('10001', null, '0', null, '0', null, null, null, null, 'attack_one_begin', null, null, null, '9', null, null, null, '3', null, null, null, '0.96', '0.2', '6');

-- ----------------------------
-- Table structure for weapon
-- ----------------------------
DROP TABLE IF EXISTS `weapon`;
CREATE TABLE `weapon` (
  `id` int(11) NOT NULL,
  `vocation_bindinex` int(11) DEFAULT NULL COMMENT '绑定的挂点位置',
  `skill_id` int(11) DEFAULT NULL COMMENT '子弹特效id',
  `avatar_id` int(11) DEFAULT NULL COMMENT '武器外显'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weapon
-- ----------------------------
INSERT INTO `weapon` VALUES ('1', '1001', '3', '2001');
