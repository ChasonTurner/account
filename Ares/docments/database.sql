GRANT ALL ON ares. TO 'ares'@'%' IDENTIFIED BY 'ares';

DROP TABLE IF EXISTS tb_weixin_menu;
CREATE TABLE tb_weixin_menu (
	`id`			int(11)			NOT NULL AUTO_INCREMENT,
	`name`		varchar(50)		DEFAULT NULL 	COMMENT '菜单标题',
	`parentId`	int(11)			DEFAULT NULL 	COMMENT '父节点菜单id',
	`type`		varchar(50)		DEFAULT NULL 	COMMENT '菜单的响应动作类型',
	`key`			varchar(1024)	DEFAULT NULL 	COMMENT '菜单KEY值，用于消息接口推送，不超过128字节',
	`updateTime`	datetime		DEFAULT NULL,
	`status`		int(11)			DEFAULT NULL 	COMMENT '0：正常1：异常',
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_weixin_config;
CREATE TABLE tb_weixin_config (
	name		varchar(50)		DEFAULT NULL 	COMMENT '配置名',
	value		varchar(255)	DEFAULT NULL 	COMMENT '配置值',
	descrption	varchar(255)	DEFAULT NULL 	COMMENT '描述',
	PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_weixin_follow;
CREATE TABLE tb_weixin_follow (
	openid			varchar(50)	NOT NULL		COMMENT '关注者openID',
	subscribable	int(11)		DEFAULT 1		COMMENT '订阅标识：0：取消订阅 1：订阅',
	createtime		datetime	NOT NULL		COMMENT '创建时间',
	updatetime		datetime	DEFAULT NULL	COMMENT '更新时间',
	pushflag		int(11)		DEFAULT 0		COMMENT '已推送标识:0 未推送，1 已推送，-1 推送失败',
	PRIMARY KEY (openid)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_weixin_material;
CREATE TABLE tb_weixin_material (
	id				int(11) 		DEFAULT NULL,
	media_id		varchar(255)	DEFAULT NULL,
	title			varchar(255)	DEFAULT NULL,
	thumb_media_id	varchar(255)	DEFAULT NULL,
	author			varchar(255)	DEFAULT NULL,
	digest			varchar(255)	DEFAULT NULL,
	content			text,
	url				varchar(255)	DEFAULT NULL,
	content_source_url	varchar(255) DEFAULT NULL,
	update_time		varchar(255)	DEFAULT NULL,
	name			varchar(255)	DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_weixin_user;
CREATE TABLE tb_weixin_user (
	openid varchar(50) DEFAULT NULL COMMENT '用户的唯一标识',
	subscribe int(11) DEFAULT NULL COMMENT '是否关注公众号0：未关注，1：关注',
	nickname varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户昵称',
	sex int(11) DEFAULT NULL,
	language varchar(50) DEFAULT NULL,
	city varchar(50) DEFAULT NULL,
	province varchar(50) DEFAULT NULL,
	country varchar(50) DEFAULT NULL,
	headimgurl varchar(255) DEFAULT NULL,
	subscribetime long DEFAULT NULL COMMENT '关注时间',
	unionid varchar(50) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
	updatetime datetime NOT NULL COMMENT '最近更新时间',
	PRIMARY KEY (openid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;