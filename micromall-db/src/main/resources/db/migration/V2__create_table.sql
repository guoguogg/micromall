SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_admin
-- ----------------------------
DROP TABLE IF EXISTS `micromall_admin`;
CREATE TABLE `micromall_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) NOT NULL DEFAULT '' COMMENT '账号',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) DEFAULT '''' COMMENT '头像图片',
  `role_ids` varchar(127) DEFAULT '[]' COMMENT '角色列表',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of micromall_admin
-- ----------------------------
INSERT INTO `micromall_admin` VALUES ('1', 'admin123', '张三', '$2a$10$.rEfyBb/GURD9P2p0fRg/OAJGloGNDkJS4lY0cQHeqDgsbdTylBpu', '192.168.3.80', '2021-06-30 10:56:06', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '[1,2]', '2021-01-19 16:58:46', '2021-01-19 16:59:15', '0');

-- ----------------------------
-- Table structure for micromall_config
-- ----------------------------
DROP TABLE IF EXISTS `micromall_config`;
CREATE TABLE `micromall_config` (
  `sys_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `key_name` varchar(100) DEFAULT NULL COMMENT '配置key',
  `key_value` varchar(500) DEFAULT NULL COMMENT '配置值',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- ----------------------------
-- Records of micromall_config
-- ----------------------------
INSERT INTO `micromall_config` VALUES ('52', 'micromall_mall_name', '微商城', '2021-06-29 22:17:25', null, '0');
INSERT INTO `micromall_config` VALUES ('53', 'micromall_file_thumb_width', '300', '2021-06-29 22:17:25', null, '0');
INSERT INTO `micromall_config` VALUES ('54', 'micromall_file_storage_location', 'LOCAL', '2021-06-29 22:17:25', null, '0');
INSERT INTO `micromall_config` VALUES ('55', 'micromall_file_thumb_height', '300', '2021-06-29 22:17:25', null, '0');

-- ----------------------------
-- Table structure for micromall_permission
-- ----------------------------
DROP TABLE IF EXISTS `micromall_permission`;
CREATE TABLE `micromall_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `permission` varchar(63) DEFAULT NULL COMMENT '权限',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of micromall_permission
-- ----------------------------
INSERT INTO `micromall_permission` VALUES ('1', '1', '*', '2021-01-19 16:26:23', null, '0');

-- ----------------------------
-- Table structure for micromall_role
-- ----------------------------
DROP TABLE IF EXISTS `micromall_role`;
CREATE TABLE `micromall_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL COMMENT '角色名称',
  `desc` varchar(1023) DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of micromall_role
-- ----------------------------
INSERT INTO `micromall_role` VALUES ('1', '超级管理员', '所有模块的权限', '1', '2021-01-19 16:26:18', null, '0');

-- ----------------------------
-- Table structure for micromall_storage
-- ----------------------------
DROP TABLE IF EXISTS `micromall_storage`;
CREATE TABLE `micromall_storage` (
  `storage_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件自增id',
  `key` varchar(255) DEFAULT NULL,
  `file_name` varchar(1000) DEFAULT NULL COMMENT '附件名称',
  `file_path` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `thumb_url` varchar(255) DEFAULT NULL COMMENT '略缩图url',
  `media_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `suffix` varchar(50) DEFAULT NULL COMMENT '文件后缀',
  `size` varchar(50) DEFAULT NULL COMMENT '文件大小',
  `type` varchar(10) DEFAULT 'LOCAL' COMMENT '存储位置',
  `md5` varchar(255) DEFAULT NULL COMMENT '文件md5值',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`storage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件存储表';

-- ----------------------------
-- Records of micromall_storage
-- ----------------------------

-- ----------------------------
-- Table structure for shiro_session
-- ----------------------------
DROP TABLE IF EXISTS `shiro_session`;
CREATE TABLE `shiro_session` (
  `session_id` varchar(128) NOT NULL,
  `session_data` blob,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台用户登录session';

-- ----------------------------
-- Records of shiro_session
-- ----------------------------
