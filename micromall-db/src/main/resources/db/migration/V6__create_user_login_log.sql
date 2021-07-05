SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_login_log
-- ----------------------------
DROP TABLE IF EXISTS `micromall_login_log`;
CREATE TABLE `micromall_login_log` (
  `login_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL,
  `login_ip` varchar(63) NOT NULL DEFAULT '' COMMENT '登录IP地址',
  `login_address` varchar(255) DEFAULT NULL COMMENT '登录地址',
  `add_time` datetime DEFAULT NULL COMMENT '登录时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`login_log_id`),
  KEY `idx_user_id_login_ip` (`user_id`,`login_ip`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录日志记录';

-- ----------------------------
-- Table structure for micromall_user
-- ----------------------------
DROP TABLE IF EXISTS `micromall_user`;
CREATE TABLE `micromall_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(63) NOT NULL COMMENT '用户名称',
  `pass_word` varchar(63) NOT NULL DEFAULT '' COMMENT '用户密码',
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别：0 未知， 1男， 1 女',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '用户手机号码',
  `weixin_mini_openid` varchar(64) DEFAULT '' COMMENT '微信小程序miniopenid',
  `weixin_openid` varchar(64) DEFAULT '' COMMENT '微信登录openid',
  `weixin_unionid` varchar(64) DEFAULT NULL COMMENT '微信登录unionid',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0 可用, 1 禁用, 2 注销',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
