CREATE DATABASE lightspeed_db CHARACTER SET utf8 COLLATE utf8_general_ci; 
USE lightspeed_db;

DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` longtext NOT NULL COMMENT '内容',
  `reply_time` datetime NOT NULL COMMENT '回复时间',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `topic_id` int(11) NOT NULL COMMENT '主题ID',
  `del_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态 0未删除 1删除',
  `floor` int(11) NOT NULL COMMENT '楼层',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_section`;
CREATE TABLE `t_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `introduce` varchar(255) DEFAULT NULL COMMENT '介绍',
  `order_index` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_section` VALUES ('1', '灌水乐园', '', 1);
INSERT INTO `t_section` VALUES ('2', '自然科学', '', 2);
INSERT INTO `t_section` VALUES ('3', '财经股票', '', 3);
INSERT INTO `t_section` VALUES ('4', '游戏世界', '', 4);

DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `image` varchar(255) DEFAULT NULL COMMENT '预览图',
  `section_id` int(11) NOT NULL COMMENT '版块ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `last_reply_time` datetime NOT NULL COMMENT '最后回复时间',
  `del_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除 1删除',
  `top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0普通 1置顶',
  `last_reply_user_id` int(11) NOT NULL COMMENT '最后回复用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `reply_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `topic_type_id` int(11) NOT NULL DEFAULT '0' COMMENT '类型ID',
  `high_quality` int(11) NOT NULL DEFAULT '0' COMMENT '0普通 1精品',
  `section_top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0普通 1板块置顶',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_topic_type`;
CREATE TABLE `t_topic_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_topic_type` VALUES ('1', '财经');
INSERT INTO `t_topic_type` VALUES ('2', '电脑');
INSERT INTO `t_topic_type` VALUES ('3', '体育');
INSERT INTO `t_topic_type` VALUES ('4', '音乐');
INSERT INTO `t_topic_type` VALUES ('5', '人过三十');
INSERT INTO `t_topic_type` VALUES ('6', '宠物');
INSERT INTO `t_topic_type` VALUES ('7', '新闻');
INSERT INTO `t_topic_type` VALUES ('8', '生活艰难');
INSERT INTO `t_topic_type` VALUES ('9', '药不能停');
INSERT INTO `t_topic_type` VALUES ('10', '新生活运动');
INSERT INTO `t_topic_type` VALUES ('11', '西行记');

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) NOT NULL UNIQUE COMMENT '用户名称',
  `email` varchar(255) NOT NULL UNIQUE COMMENT 'E-mail',
  `password` varchar(255) NOT NULL COMMENT '加密的密码',
  `topic_count` int(11) NOT NULL DEFAULT '0' COMMENT '发表主题数量',
  `reply_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_gw2_area`;
CREATE TABLE `t_gw2_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '大区名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_gw2_area` VALUES ('1', '南朝');
INSERT INTO `t_gw2_area` VALUES ('2', '北朝');

DROP TABLE IF EXISTS `t_gw2_homeland`;
CREATE TABLE `t_gw2_homeland` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '服务器名称',
  `gw2_area_id` int(11) NOT NULL COMMENT '大区ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_gw2_homeland` VALUES ('1', '宋', '1');
INSERT INTO `t_gw2_homeland` VALUES ('2', '齐', '1');
INSERT INTO `t_gw2_homeland` VALUES ('3', '梁', '1');
INSERT INTO `t_gw2_homeland` VALUES ('4', '陈', '1');
INSERT INTO `t_gw2_homeland` VALUES ('5', '北魏', '2');
INSERT INTO `t_gw2_homeland` VALUES ('6', '东魏', '2');
INSERT INTO `t_gw2_homeland` VALUES ('7', '西魏', '2');
INSERT INTO `t_gw2_homeland` VALUES ('8', '北齐', '2');
INSERT INTO `t_gw2_homeland` VALUES ('9', '北周', '2');

DROP TABLE IF EXISTS `t_gw2_user_info`;
CREATE TABLE `t_gw2_user_info` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `game_nickname` varchar(255) DEFAULT NULL COMMENT '游戏昵称',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `gw2_homeland_id` int(11) DEFAULT NULL COMMENT '家园ID',
  `organization` varchar(255) DEFAULT NULL COMMENT '公会',
  `public_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0不公开 1公开',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_reset_pwd`;
CREATE TABLE `t_reset_pwd` (
  `uuid` varchar(255) NOT NULL COMMENT 'UUID',
  `email` varchar(255) NOT NULL COMMENT '电子邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0初始 1邮件已发送 2密码已重置（失效）',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `content` text DEFAULT NULL COMMENT '消息内容',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户ID、at用户ID，为空则是系统消息',
  `reply_id` int(11) DEFAULT NULL COMMENT 'type为2时对应的楼层',
  `type` tinyint(4) NOT NULL COMMENT '0系统消息 1用户消息 2用户at消息',
  `read_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未读 1已读',
  `del_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_donate`;
CREATE TABLE `t_donate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `money` double(11,2) NOT NULL COMMENT '金额',
  `donate_time` datetime NOT NULL COMMENT '捐款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
