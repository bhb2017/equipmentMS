/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50549
Source Host           : localhost:3307
Source Database       : equipment

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-09-19 20:16:38
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '课程1', '1001');
INSERT INTO `course` VALUES ('2', '电子技术', '1002');

-- ----------------------------
-- Table structure for `course_matrial`
-- ----------------------------
DROP TABLE IF EXISTS `course_matrial`;
CREATE TABLE `course_matrial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  `per` int(11) DEFAULT NULL COMMENT '份/人',
  PRIMARY KEY (`id`),
  KEY `course_matrial_ibfk_1` (`material_id`),
  KEY `course_matrial_ibfk_2` (`course_id`),
  CONSTRAINT `course_matrial_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_matrial_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_matrial
-- ----------------------------
INSERT INTO `course_matrial` VALUES ('5', '1', '37', '2');
INSERT INTO `course_matrial` VALUES ('6', '1', '36', '2');
INSERT INTO `course_matrial` VALUES ('7', '1', '36', '1');
INSERT INTO `course_matrial` VALUES ('8', '2', '37', '1');
INSERT INTO `course_matrial` VALUES ('9', '2', '36', '1');
INSERT INTO `course_matrial` VALUES ('10', '1', '35', '3');
INSERT INTO `course_matrial` VALUES ('11', '1', '34', '4');
INSERT INTO `course_matrial` VALUES ('12', '1', '36', '5');
INSERT INTO `course_matrial` VALUES ('13', '2', '34', '1');
INSERT INTO `course_matrial` VALUES ('14', '2', '35', '2');
INSERT INTO `course_matrial` VALUES ('17', '2', '37', '3');
INSERT INTO `course_matrial` VALUES ('18', '2', '36', '1');

-- ----------------------------
-- Table structure for `course_plan`
-- ----------------------------
DROP TABLE IF EXISTS `course_plan`;
CREATE TABLE `course_plan` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `course_id` int(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '人数',
  `school_time` varchar(100) DEFAULT NULL COMMENT '上课时间',
  PRIMARY KEY (`id`),
  KEY `course_plan_ibfk_1` (`course_id`),
  CONSTRAINT `course_plan_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_plan
-- ----------------------------
INSERT INTO `course_plan` VALUES ('1', '1', '10', '2019-09-14');

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
  KEY `in_storage_ibfk_1` (`material_id`),
  CONSTRAINT `in_storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE NO ACTION
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('34', '电阻', '12欧姆', '0.01', '0.1', 'hao');
INSERT INTO `material` VALUES ('35', '电阻', '11欧姆', '0.02', '0.1', '好');
INSERT INTO `material` VALUES ('36', '二极管', '9013', '0.1', '0.2', '好');
INSERT INTO `material` VALUES ('37', '电阻', '1k', '0.01', '0.8', '好');

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
  KEY `out_storage_ibfk_1` (`material_id`),
  CONSTRAINT `out_storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE NO ACTION
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '/material/*', '器材', '1');
INSERT INTO `permission` VALUES ('2', '/user/*', '用户修改', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员', '1');
INSERT INTO `role` VALUES ('2', 'stu', '学生', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '2', '1');

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
  KEY `storage_ibfk_1` (`material_id`),
  CONSTRAINT `storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `phone` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'cxq', '202CB962AC59075B964B07152D234B70', '13068111311', '1');
INSERT INTO `user` VALUES ('18', 'test', '202CB962AC59075B964B07152D234B70', '13068111311', '1');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_ibfk_1` (`user_id`),
  KEY `user_role_ibfk_2` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('33', '16', '2');
INSERT INTO `user_role` VALUES ('34', '16', '1');
INSERT INTO `user_role` VALUES ('35', '18', '2');
