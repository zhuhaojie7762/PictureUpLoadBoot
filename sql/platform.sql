DROP TABLE IF EXISTS pictures;
CREATE TABLE pictures(
  id varchar(35) NOT NULL comment '主键',
  pid varchar(35)DEFAULT NULL COMMENT '父id',
  title varchar(25) DEFAULT NULL COMMENT '标题',
  name varchar(35) DEFAULT NULL COMMENT '文件名',
  url varchar(255) DEFAULT NULL COMMENT '路径',
  createtime datetime DEFAULT NULL COMMENT '创建时间',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
  id varchar(35) NOT NULL comment '主键',
  name varchar(35) DEFAULT NULL comment '用户名',
  password varchar(255) DEFAULT NULL comment '密码',
  real_name varchar(35) DEFAULT NULL comment '真实姓名',
  rights varchar(35) DEFAULT NULL comment '权限',
  role_id varchar(35) DEFAULT NULL comment '角色id',
  last_login varchar(20) DEFAULT NULL comment '最后登录时间',
  ip varchar(20) DEFAULT NULL comment 'ip地址',
  status tinyint DEFAULT NULL comment '状态  -128---127',
  skin varchar(10) DEFAULT NULL comment '皮肤颜色',
  email varchar(32) DEFAULT NULL comment '电子邮件',
  number int(3) NOT NULL comment '编号',
  phone varchar(12) DEFAULT NULL comment '电话',
  remark varchar(255) DEFAULT NULL comment '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理者表';



DROP TABLE IF EXISTS menu;
CREATE TABLE menu(
  id varchar(35) NOT NULL comment '主键',
  name varchar(35) DEFAULT NULL comment '名称',
  url varchar(255) DEFAULT NULL comment '菜单请求路径',
  pid varchar(35) DEFAULT NULL comment '父菜单id',
  sorts int(2) DEFAULT NULL comment '菜单排序',
  icon varchar(30) DEFAULT NULL comment '菜单图标',
  type tinyint DEFAULT NULL comment '菜单类型',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';


DROP TABLE IF EXISTS weixin_command;
CREATE TABLE weixin_command(
  id varchar(35) NOT NULL comment '主键',
  keywork varchar(40) DEFAULT NULL COMMENT '关键词',
  path varchar(255) DEFAULT NULL COMMENT '应用路径',
  createtime varchar(20) DEFAULT NULL COMMENT '创建时间',
  status tinyint NOT NULL COMMENT '状态',
  remark varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信命令表';


DROP TABLE IF EXISTS sys_app_user;
CREATE TABLE sys_app_user(
  id varchar(35) primary key NOT NULL comment '主键',
  users_name varchar(35) default null comment '用户名',
  pwd varchar(255) DEFAULT NULL comment '密码',
  real_name varchar(35) DEFAULT NULL comment '真实姓名',
  role_id varchar(35) DEFAULT NULL comment '角色id',
  last_login varchar(20) DEFAULT NULL comment '最后登录时间',
  ip_address varchar(20) DEFAULT NULL comment 'ip地址',
  state int(3)  not null comment '状态',
  remark varchar(255) DEFAULT NULL comment '备注',
  phone varchar(12) DEFAULT NULL comment '电话',
  start_time varchar(20) DEFAULT NULL comment '访问开始时间',
  end_time varchar(20) DEFAULT NULL comment '访问结束时间',
  years int(10)   comment '',
  number varchar(10) DEFAULT NULL,
  email varchar(32) DEFAULT NULL comment '电子邮箱'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='使用当前系统的用户表';


DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role(
  id varchar(100) NOT NULL comment '主键',
  name varchar(100) DEFAULT NULL comment '角色名称',
  rights varchar(255) DEFAULT NULL comment '权限',
  pid varchar(100) DEFAULT NULL comment '父id',
  add_qx varchar(255) DEFAULT NULL comment '添加权限',
  del_qx varchar(255) DEFAULT NULL comment '删除权限',
  edit_qx varchar(255) DEFAULT NULL comment '修改权限',
  cha_qx varchar(255) DEFAULT NULL comment '查询权限',
  qx_id varchar(100) DEFAULT NULL comment '权限id',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='管理员角色表';



CREATE TABLE sys_log(
   id varchar(32) NOT NULL COMMENT '主键',
   ip varchar(128) NOT NULL COMMENT 'IP地址',
   session_id varchar(32) NOT NULL COMMENT 'SessionId信息',
   brower_name varchar(50) NOT NULL COMMENT '浏览器名称',
   brower_message varchar(200) NOT NULL COMMENT '浏览器信息',
   request_url varchar(200) NOT NULL COMMENT '请求url',
   request_param text COMMENT '请求参数',
   exception_name text comment '异常信息',
   user_id varchar(32) not null comment '登录用户id',
   user_name varchar(35) NOT NULL COMMENT '用户名',
   status varchar(1) NOT NULL COMMENT '操作状态 0:初始状态 1:成功 2:失败',
   create_time datetime NOT NULL COMMENT '生成时间',
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';





DROP TABLE IF EXISTS new_product_base;
CREATE TABLE new_product_base(
  id int(35) AUTO_INCREMENT  primary key NOT NULL comment '主键',
  user_id varchar(35) DEFAULT NULL comment '用户id',
  item_no varchar(255) DEFAULT NULL comment '货号',
  main_code varchar(50) DEFAULT NULL comment '主货号',
  other varchar(35) DEFAULT NULL comment '其他',
  putaway_type varchar(35) DEFAULT NULL comment '存放方式',
  picture varchar(200) DEFAULT NULL comment '图片',
  classify varchar(20) DEFAULT NULL comment '类型',
  color varchar(3) DEFAULT NULL comment '颜色',
  shop varchar(10) DEFAULT NULL comment '店铺',
  model varchar(32) DEFAULT NULL comment '模型',
  sell_price decimal(6,2) default 0 comment '售价',
  product_size varchar(4) DEFAULT NULL comment '产品尺寸',
  barcode_one varchar(25) DEFAULT NULL comment '图标1',
  barcode_two varchar(25) DEFAULT NULL comment '图标2',
  barcode_three varchar(25) DEFAULT NULL comment '图标3',
  barcode_four varchar(25) DEFAULT NULL comment '图标4',
  barcode_five varchar(25) DEFAULT NULL comment '图标5',
  link varchar(255) DEFAULT NULL comment '链接地址',
  remark varchar(255) DEFAULT NULL comment '备注',
  same_link varchar(255) DEFAULT NULL comment '相同链接地址',
  creater varchar(255) DEFAULT NULL comment '创建者',
  updater varchar(255) DEFAULT NULL comment '更新者',
  update_time TIMESTAMP DEFAULT NULL comment '更新时间',
  create_time TIMESTAMP DEFAULT NULL comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新产品表';