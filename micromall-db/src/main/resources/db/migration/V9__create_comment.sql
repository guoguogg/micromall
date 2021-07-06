SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_comment
-- ----------------------------
DROP TABLE IF EXISTS `micromall_comment`;
CREATE TABLE `micromall_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '如果type=0,则是商品评论',
  `order_goods_id` bigint(20) DEFAULT NULL COMMENT '订单商品id',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '评论类型:如果type=0,则是商品评论',
  `content` varchar(1023) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '评论内容',
  `admin_content` varchar(511) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '管理员回复内容',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `has_picture` tinyint(1) DEFAULT '0' COMMENT '是否含有图片',
  `pic_urls` varchar(1023) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图片地址列表: 英文逗号分隔',
  `star` smallint(6) DEFAULT '1' COMMENT '评分， 1-5',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';
