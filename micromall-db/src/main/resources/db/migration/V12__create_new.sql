/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : micromall

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-07-09 10:44:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_new
-- ----------------------------
DROP TABLE IF EXISTS `micromall_new`;
CREATE TABLE `micromall_new` (
  `new_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新品首发自增ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`new_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='新品首发商品关联';
