SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_collect
-- ----------------------------
DROP TABLE IF EXISTS `micromall_collect`;
CREATE TABLE `micromall_collect` (
  `collect_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `value_id` int(11) NOT NULL DEFAULT '0' COMMENT '如果type=0,则是商品ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '收藏类型:如果type=0，则是商品ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户收藏表';
