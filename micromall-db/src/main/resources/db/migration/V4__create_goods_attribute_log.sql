SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for micromall_goods
-- ----------------------------
DROP TABLE IF EXISTS `micromall_goods`;
CREATE TABLE `micromall_goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_sn` varchar(63) NOT NULL DEFAULT '' COMMENT '商品编号',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '商品名称',
  `brief` varchar(255) DEFAULT '' COMMENT '商品简介',
  `detail` text COMMENT '商品详细介绍，是富文本格式',
  `pic_url` varchar(1024) DEFAULT NULL COMMENT '商品页面商品图片',
  `category_id` bigint(20) DEFAULT '0' COMMENT '商品所属类目ID',
  `brand_id` bigint(20) DEFAULT '0' COMMENT '供应商id',
  `is_on_sale` tinyint(1) DEFAULT '1' COMMENT '是否上架',
  `unit` varchar(31) DEFAULT '’件‘' COMMENT '商品单位，例如件、盒',
  `counter_price` decimal(10,2) DEFAULT '0.00' COMMENT '专柜价格',
  `retail_price` decimal(10,2) DEFAULT '100000.00' COMMENT '零售价格',
  `sold` int(11) DEFAULT '0' COMMENT '可修改销量',
  `sale` int(11) DEFAULT '0' COMMENT '商品真销量',
  `sort_order` tinyint(3) DEFAULT '100' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for micromall_goods_attribute
-- ----------------------------
DROP TABLE IF EXISTS `micromall_goods_attribute`;
CREATE TABLE `micromall_goods_attribute` (
  `attribute_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `attribute` varchar(255) NOT NULL COMMENT '商品参数名称',
  `value` varchar(255) NOT NULL COMMENT '商品参数值',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='商品属性参数';

-- ----------------------------
-- Table structure for micromall_log
-- ----------------------------
DROP TABLE IF EXISTS `micromall_log`;
CREATE TABLE `micromall_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `request_url` varchar(64) DEFAULT NULL COMMENT '请求路径',
  `param` varchar(1024) DEFAULT NULL COMMENT '请求参数',
  `name` varchar(255) DEFAULT NULL COMMENT '操作接口名称',
  `ip` varchar(30) DEFAULT NULL COMMENT '操作用户的ip',
  `id` bigint(20) DEFAULT NULL COMMENT '请求用户id',
  `ua` varchar(30) DEFAULT NULL COMMENT '操作用户的user_agent',
  `os` varchar(30) DEFAULT NULL COMMENT '浏览器类型',
  `referer` varchar(100) DEFAULT NULL COMMENT '请求来源地址',
  `type` enum('SYSTEM','VISIT','ERROR') DEFAULT NULL COMMENT '日志类型（系统操作日志，用户访问日志，异常记录日志）',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='日志记录';
