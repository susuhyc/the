CREATE TABLE `s_sys_acl` (
  `acl_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `url` varchar(20) DEFAULT NULL COMMENT '访问地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '-1删除0停用1启用',
  `type` tinyint(4) DEFAULT NULL COMMENT '1目录2菜单3按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `order` tinyint(4) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`acl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `s_sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `role_key` varchar(50) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0' COMMENT '-1删除，0停用，1启用',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `s_sys_role_acl` (
  `role_id` varchar(60) DEFAULT NULL,
  `acl_id` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `s_sys_user` (
  `user_id` varchar(200) NOT NULL DEFAULT '',
  `user_name` varchar(20) DEFAULT '',
  `user_age` int(3) DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `s_sys_user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
