/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50549
Source Host           : localhost:3307
Source Database       : equipment

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-09-07 09:53:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) DEFAULT '' COMMENT '课程名',
  `course_no` varchar(50) DEFAULT NULL COMMENT '课程编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for `course_matrial`
-- ----------------------------
DROP TABLE IF EXISTS `course_matrial`;
CREATE TABLE `course_matrial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `per` int(11) DEFAULT NULL COMMENT '份/人',
  `need_time` date DEFAULT NULL COMMENT '需用时间',
  PRIMARY KEY (`id`),
  KEY `material_id` (`material_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_matrial_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_matrial_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_matrial
-- ----------------------------

-- ----------------------------
-- Table structure for `course_plan`
-- ----------------------------
DROP TABLE IF EXISTS `course_plan`;
CREATE TABLE `course_plan` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '人数',
  `school_time` date DEFAULT NULL COMMENT '上课时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_plan_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `in_storage`
-- ----------------------------
DROP TABLE IF EXISTS `in_storage`;
CREATE TABLE `in_storage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `in_time` date DEFAULT NULL COMMENT '入库时间',
  `material_id` bigint(4) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `in_storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of in_storage
-- ----------------------------

-- ----------------------------
-- Table structure for `material`
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `specification` varchar(100) DEFAULT NULL COMMENT '规格',
  `price` float DEFAULT NULL,
  `rate` float DEFAULT NULL COMMENT '损耗率',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for `out_storage`
-- ----------------------------
DROP TABLE IF EXISTS `out_storage`;
CREATE TABLE `out_storage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_time` date DEFAULT NULL COMMENT '出库时间',
  `material_id` bigint(4) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `out_storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of out_storage
-- ----------------------------

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `storage`
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_id` bigint(20) DEFAULT NULL,
  `num` tinyint(4) DEFAULT NULL COMMENT '数量',
  `place` varchar(50) DEFAULT NULL COMMENT '存放地点',
  PRIMARY KEY (`id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
