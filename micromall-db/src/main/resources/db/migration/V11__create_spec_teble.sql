/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : micromall

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-07-08 22:35:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_goods_sku
-- ----------------------------
DROP TABLE IF EXISTS `micromall_goods_sku`;
CREATE TABLE `micromall_goods_sku` (
  `sku_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'sku自增ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SKU表';

-- ----------------------------
-- Table structure for micromall_goods_spec
-- ----------------------------
DROP TABLE IF EXISTS `micromall_goods_spec`;
CREATE TABLE `micromall_goods_spec` (
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `spec_id` bigint(20) NOT NULL COMMENT '规格id',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品与规格关联表';

-- ----------------------------
-- Table structure for micromall_sku_spec_value
-- ----------------------------
DROP TABLE IF EXISTS `micromall_sku_spec_value`;
CREATE TABLE `micromall_sku_spec_value` (
  `sku_id` bigint(20) NOT NULL COMMENT 'skuId',
  `spec_value_id` bigint(20) NOT NULL COMMENT '规格值ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='SKU与规格值关联表';

-- ----------------------------
-- Table structure for micromall_spec
-- ----------------------------
DROP TABLE IF EXISTS `micromall_spec`;
CREATE TABLE `micromall_spec` (
  `spec_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格自增id',
  `spec_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格名称',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`spec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='规格表';

-- ----------------------------
-- Table structure for micromall_spec_value
-- ----------------------------
DROP TABLE IF EXISTS `micromall_spec_value`;
CREATE TABLE `micromall_spec_value` (
  `spec_value_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格值自增ID',
  `spec_value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格值',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`spec_value_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='规格值';
