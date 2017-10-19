/*
Navicat MySQL Data Transfer

Source Server         : ali
Source Server Version : 50552
Source Host           : 119.23.231.239:3306
Source Database       : newb

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2017-10-19 11:22:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `oid` int(11) NOT NULL DEFAULT '0',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1111', '1111');
