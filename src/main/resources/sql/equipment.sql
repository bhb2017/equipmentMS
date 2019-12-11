/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50549
Source Host           : localhost:3307
Source Database       : equipment

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-12-07 17:35:21
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '课程1', '1.0');
INSERT INTO `course` VALUES ('2', '课程1', '1.0');
INSERT INTO `course` VALUES ('3', '课程1', '1.0');
INSERT INTO `course` VALUES ('4', '课程1', '1.0');
INSERT INTO `course` VALUES ('5', '课程1', '1.0');
INSERT INTO `course` VALUES ('6', '课程1', '1.0');
INSERT INTO `course` VALUES ('7', '课程1', '1.0');
INSERT INTO `course` VALUES ('8', '课程1', '1.0');
INSERT INTO `course` VALUES ('9', '课程1', '1.0');
INSERT INTO `course` VALUES ('10', '课程1', '1.0');
INSERT INTO `course` VALUES ('11', '课程1', '1.0');
INSERT INTO `course` VALUES ('12', '课程1', '1.0');

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
  `school_time` varchar(100) DEFAULT NULL COMMENT '上课时间',
  `mark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_plan_ibfk_1` (`course_id`),
  CONSTRAINT `course_plan_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_plan
-- ----------------------------
INSERT INTO `course_plan` VALUES ('5', '5', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('6', '6', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('7', '7', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('8', '8', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('9', '9', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('10', '10', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('11', '11', '10', '19下', null);
INSERT INTO `course_plan` VALUES ('12', '12', '10', '19下', null);

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
  CONSTRAINT `in_storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of in_storage
-- ----------------------------
INSERT INTO `in_storage` VALUES ('12', '2019-01-09', '44', '100', null);
INSERT INTO `in_storage` VALUES ('13', '2019-01-14', '44', '100', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('38', '电阻', '12欧姆', '0.1', '0.1', '');
INSERT INTO `material` VALUES ('39', '电阻', '19欧姆', '0.1', '1.999', '');
INSERT INTO `material` VALUES ('40', '电阻', '121欧姆', '1', '0.111', '');
INSERT INTO `material` VALUES ('42', '电阻', '121欧姆', '1', '0.1', '');
INSERT INTO `material` VALUES ('44', '电阻', '122欧姆', '1', '0.1', '1');
INSERT INTO `material` VALUES ('45', '三极管', '543534', '0.1', '0.1', '');
INSERT INTO `material` VALUES ('46', '三极管', '543543', '1', '11', '');
INSERT INTO `material` VALUES ('55', '电阻', '13欧姆', '0.1', '0.1', null);
INSERT INTO `material` VALUES ('56', '电阻', '14欧姆', '0.1', '1.999', null);
INSERT INTO `material` VALUES ('57', '电阻', '123欧姆', '1', '0.111', null);
INSERT INTO `material` VALUES ('58', '电阻', '125欧姆', '1', '0.1', null);
INSERT INTO `material` VALUES ('59', '电阻', '126欧姆', '1', '0.1', '1.0');
INSERT INTO `material` VALUES ('60', '三极管', '543434.0', '0.1', '0.1', null);
INSERT INTO `material` VALUES ('61', '三极管', '544543.0', '1', '11', null);
INSERT INTO `material` VALUES ('62', '电阻', '13欧姆', '0.1', '0.1', null);
INSERT INTO `material` VALUES ('63', '电阻', '14欧姆', '0.1', '1.999', null);
INSERT INTO `material` VALUES ('64', '电阻', '123欧姆', '1', '0.111', null);
INSERT INTO `material` VALUES ('65', '电阻', '125欧姆', '1', '0.1', null);
INSERT INTO `material` VALUES ('66', '电阻', '126欧姆', '1', '0.1', '1.0');
INSERT INTO `material` VALUES ('67', '三极管', '543434.0', '0.1', '0.1', null);
INSERT INTO `material` VALUES ('68', '三极管', '544543.0', '1', '11', null);

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
  CONSTRAINT `out_storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `description` varchar(60) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '/material/*', '器材', '1');
INSERT INTO `permission` VALUES ('2', '/user/*', '用户修改', '1');
INSERT INTO `permission` VALUES ('3', '/calculate/*', '预测', '1');
INSERT INTO `permission` VALUES ('4', '/coursematerial/*', '添加课程所需器材明细\r\n添加课程所需器材明细\r\n添加课程所需器材明细\r\n添加课程所需器材明细\r\n', '1');
INSERT INTO `permission` VALUES ('5', '/storage/*', '库存管理', '1');
INSERT INTO `permission` VALUES ('6', '/course/*', '课程管理', '1');
INSERT INTO `permission` VALUES ('7', '/storagerecord/*', '日志', '1');
INSERT INTO `permission` VALUES ('8', '/storage/add', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '2', '1');
INSERT INTO `role_permission` VALUES ('5', '1', '3');
INSERT INTO `role_permission` VALUES ('6', '1', '4');
INSERT INTO `role_permission` VALUES ('7', '1', '5');
INSERT INTO `role_permission` VALUES ('8', '1', '6');
INSERT INTO `role_permission` VALUES ('9', '1', '7');
INSERT INTO `role_permission` VALUES ('10', '1', '8');

-- ----------------------------
-- Table structure for `storage`
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_id` bigint(20) DEFAULT NULL,
  `num` int(4) DEFAULT NULL COMMENT '数量',
  `place` varchar(50) DEFAULT NULL COMMENT '存放地点',
  PRIMARY KEY (`id`),
  KEY `storage_ibfk_1` (`material_id`),
  CONSTRAINT `storage_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('19', '44', '100', '10-306');
INSERT INTO `storage` VALUES ('20', '44', '100', '1-11');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'cxq', '202CB962AC59075B964B07152D234B70', '13068111311', '1');
INSERT INTO `user` VALUES ('18', 'test1', '202CB962AC59075B964B07152D234B70', '13068111311', '1');
INSERT INTO `user` VALUES ('22', 'admin', '21232F297A57A5A743894A0E4A801FC3', '13068111311', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('33', '16', '2');
INSERT INTO `user_role` VALUES ('34', '16', '1');
INSERT INTO `user_role` VALUES ('50', '18', '2');
INSERT INTO `user_role` VALUES ('51', '22', '1');
