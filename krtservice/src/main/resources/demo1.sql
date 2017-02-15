/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : demo1

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-15 17:30:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `is_show` char(1) NOT NULL DEFAULT '0',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '0,', '功能菜单', '/#', '1', '', null, null, null, null, '0');
INSERT INTO `menu` VALUES ('2', '1', '0,1,', '系统管理', '/#', '0', '', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('3', '2', '0,1,2,', '角色管理', '/role', '0', 'sys:role:mgr', null, null, null, null, '2');
INSERT INTO `menu` VALUES ('4', '2', '0,1,2,', '资源管理', '/menu', '0', 'sys:menu:mgr', null, null, null, null, '3');
INSERT INTO `menu` VALUES ('5', '2', '0,1,2,', '用户管理', '/user', '0', 'sys:user:mgr', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('6', '3', '0,1,2,3,', '查看', '', '1', 'sys:role:view', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('7', '3', '0,1,2,3,', '修改', '/#', '1', 'sys:role:edit', null, null, null, null, '2');
INSERT INTO `menu` VALUES ('8', '5', '0,1,2,5,', '查看', '/#', '1', 'sys:user:view', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('9', '5', '0,1,2,5,', '修改', '', '1', 'sys:user:edit', null, null, null, null, '2');
INSERT INTO `menu` VALUES ('11', '4', '0,1,2,4,', '查看', '', '1', 'sys:menu:view', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('12', '5', '0,1,2,5,', '删除', '', '1', 'sys:user:delete', null, null, null, null, '3');
INSERT INTO `menu` VALUES ('13', '3', '0,1,2,3,', '删除', '', '1', 'sys:role:delete', null, null, null, null, '3');
INSERT INTO `menu` VALUES ('14', '4', '0,1,2,4,', '删除', '', '1', 'sys:menu:delete', null, null, null, null, '3');
INSERT INTO `menu` VALUES ('16', '4', '0,1,2,4,', '修改', '', '1', 'sys:menu:edit', null, null, null, null, '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_type` varchar(255) NOT NULL COMMENT '角色类型',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', 'superadmin', null, null, null, null, null);
INSERT INTO `role` VALUES ('2', '管理员', 'admin', null, null, null, null, null);
INSERT INTO `role` VALUES ('3', '公司管理员', 'companyadmin', null, null, null, null, null);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('3', '1');
INSERT INTO `role_menu` VALUES ('3', '2');
INSERT INTO `role_menu` VALUES ('3', '4');
INSERT INTO `role_menu` VALUES ('3', '5');
INSERT INTO `role_menu` VALUES ('3', '8');
INSERT INTO `role_menu` VALUES ('3', '9');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `no` varchar(100) DEFAULT NULL COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`username`),
  KEY `sys_user_update_date` (`update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, 'hefeng', '182ae2dd235d37089dbfe7120c745170', 'c0e91d93-a0c1-4824-b001-b082b4746b00', '何锋', null, null, null, '2017-02-11 11:19:12', null, '2017-02-15 14:55:33', null);
INSERT INTO `user` VALUES ('2', null, 'test', '1', '1', '测试用户', null, null, null, '2017-02-13 09:20:29', null, '2017-02-13 09:20:32', null);
INSERT INTO `user` VALUES ('3', null, 'test2', '16b74f21aa9ac1228640a1ec55b3e6df', '05678210-3dc5-42c2-bd54-adcb6901cfa6', 'ss测试用户2', null, null, null, '2017-02-14 09:51:45', null, '2017-02-15 16:13:09', null);
INSERT INTO `user` VALUES ('4', null, 'test1', 'b4bd5b8c00ee225de546bee5c4649179', '5148fcf2-831a-4264-bd83-f8add50d3984', '测试用户无任何权限', null, null, null, '2017-02-15 15:53:53', null, '2017-02-15 15:54:49', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('3', '3');
SET FOREIGN_KEY_CHECKS=1;
