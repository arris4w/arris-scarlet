/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/4/30 19:30:09                           */
/*==============================================================*/


drop table if exists t_admin;

drop table if exists t_admin_role;

drop table if exists t_basic_user;

drop table if exists t_journal_log;

drop table if exists t_logistics;

drop table if exists t_menu;

drop table if exists t_notice;

drop table if exists t_order;

drop table if exists t_order_goods;

drop table if exists t_order_pay_type;

drop table if exists t_order_payment;

drop table if exists t_order_track;

drop table if exists t_ref_admin_role;

drop table if exists t_ref_jifen_charge;

drop table if exists t_ref_role_menu;

drop table if exists t_source;

drop table if exists t_target;

drop table if exists t_target_content;

drop table if exists t_user_expires_jifen;

drop table if exists t_user_jifen;

drop table if exists t_user_jifen_journal;

drop table if exists t_user_ship_address;

drop table if exists t_user_source_jifen;

drop table if exists t_user_source_jifen_journal;

drop table if exists t_user_vipcard_bound;

/*==============================================================*/
/* Table: t_admin                                               */
/*==============================================================*/
create table t_admin
(
   admin_id             varchar(20) not null comment '管理员ID',
   account              varchar(64) not null comment '管理员账号',
   password             varchar(128) not null comment '账号密码',
   salt                 varchar(8) not null comment '密码盐值',
   name                 varchar(256) not null comment '姓名',
   mobile               varchar(16) not null comment '手机号码',
   email                varchar(64) comment '电子邮箱',
   reg_time             datetime not null comment '注册时间',
   remark               varchar(512) comment '描述',
   state                int(2) not null default 1 comment '管理员状态：1、启用|2、禁用',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (admin_id)
);

alter table t_admin comment '管管理员信息表';

/*==============================================================*/
/* Table: t_admin_role                                          */
/*==============================================================*/
create table t_admin_role
(
   role_id              int(11) not null auto_increment comment '角色标识',
   role_name            varchar(256) not null comment '角色名称',
   remark               varchar(512) not null comment '描述',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (role_id)
);

alter table t_admin_role comment '管理员角色信息表';

/*==============================================================*/
/* Table: t_basic_user                                          */
/*==============================================================*/
create table t_basic_user
(
   user_id              varchar(20) not null comment '用户ID',
   mobile               varchar(16) not null comment '手机号',
   password             varchar(128) not null comment '密码',
   salt                 varchar(12) not null comment '盐值',
   nick_name            varchar(256) comment '昵称',
   user_name            varchar(256) comment '姓名',
   sex                  int(2) default 3 comment '性别：1、男|2、女|3、保密',
   birthday             date comment '生日',
   identity             varchar(18) comment '身份证号',
   reg_time             datetime not null comment '注册时间',
   source               int(4) comment '注册来源',
   area_id              varchar(16) comment '地区',
   address              varchar(1024) comment '详细地址',
   email                varchar(256) comment '邮箱（预留）',
   last_modified_time   datetime comment '最后修改时间',
   user_status          int(2) not null default 1 comment '用户状态：1、正常|2、锁定',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (user_id)
);

alter table t_basic_user comment '用户基本信息表';

/*==============================================================*/
/* Table: t_journal_log                                         */
/*==============================================================*/
create table t_journal_log
(
   log_id               bigint(20) not null auto_increment comment '日志标识',
   admin_id             varchar(20) not null comment '管理员ID',
   admin_name           varchar(64) not null comment '管理员姓名',
   admin_ip             varchar(64) not null comment '操作IP',
   journal_type         char(1) not null comment '操作类型(1:新增操作;2:更新操作;3:删除操作)',
   journal_time         datetime not null comment '记录时间',
   description          varchar(512) not null comment '描述信息',
   primary key (log_id)
);

alter table t_journal_log comment '管理员操作日志表';

/*==============================================================*/
/* Table: t_logistics                                           */
/*==============================================================*/
create table t_logistics
(
   logistics_id         varchar(20) not null comment '物流公司标识',
   logistics_name       varchar(256) not null comment '物流公司名称',
   create_time          datetime not null comment '创建时间',
   remark               varchar(512) comment '物流公司描述',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (logistics_id)
);

alter table t_logistics comment '物流公司表';

/*==============================================================*/
/* Table: t_menu                                                */
/*==============================================================*/
create table t_menu
(
   menu_id              int(11) not null auto_increment comment '菜单标识',
   menu_name            varchar(256) not null comment '菜单名称',
   parent               int(11) comment '父菜单',
   href                 varchar(1024) comment '连接地址',
   icon_cls             varchar(128) comment '图标样式',
   `order`              int(2) not null comment '显示顺序',
   status               int(2) not null default 1 comment '菜单状态：1、启用|2、禁用',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (menu_id)
);

alter table t_menu comment '菜单信息表';

/*==============================================================*/
/* Table: t_notice                                              */
/*==============================================================*/
create table t_notice
(
   notice_id            int(9) not null auto_increment comment '公告标识',
   notice_context       varchar(2048) not null comment '公告内容',
   create_time          datetime not null comment '创建时间',
   publish_status       int(2) not null comment '发布状态：0、待发布|1、发布|2、取消发布',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (notice_id)
);

alter table t_notice comment '系统公告表';

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
create table t_order
(
   order_id             varchar(20) not null comment '订单标识',
   groupon_order_id     varchar(64) comment '商品系统订单标识',
   user_id              varchar(20) not null comment '用户标识',
   order_type           int(2) not null comment '订单类型：1.商品订单|2.优惠券订单|3.话费充值订单',
   consignee            varchar(256) comment '收货人',
   consignee_address    varchar(1024) comment '收货地址',
   mobile               varchar(16) comment '手机号码',
   money                decimal(8,2) comment '支付金钱',
   jifen                decimal(12,4) not null comment '支付积分',
   total_pieces         int(9) not null comment '订单总件数',
   logistics_id         varchar(20) comment '物流公司',
   logistics_num        varchar(32) comment '物流单号',
   state                int(2) not null comment '订单状态：0、正常',
   remark               varchar(2048) comment '订单描述',
   create_time          datetime not null comment '创建时间',
   id_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (order_id)
);

alter table t_order comment '订单信息表';

/*==============================================================*/
/* Table: t_order_goods                                         */
/*==============================================================*/
create table t_order_goods
(
   order_goods_id       bigint(20) not null auto_increment comment '订单商品标识',
   goods_id             varchar(64) comment '商品标识',
   order_id             varchar(20) not null comment '订单标识',
   user_id              varchar(20) not null comment '用户标识',
   goods_name           varchar(256) not null comment '商品名称',
   goods_number         int(5) not null default 1 comment '商品数量',
   goods_price          decimal(8,2) not null comment '商品单价',
   jifen                decimal(12,4) comment '支付积分',
   pay_price            decimal(8,2) comment '支付价格',
   goods_pic            varchar(512) comment '商品图片',
   create_time          datetime not null comment '创建时间',
   primary key (order_goods_id)
);

alter table t_order_goods comment '订单商品快照表';

/*==============================================================*/
/* Table: t_order_pay_type                                      */
/*==============================================================*/
create table t_order_pay_type
(
   type_id              varchar(20) not null comment '支付类型标识',
   type_name            varchar(256) not null comment '支付类型名称',
   create_time          datetime not null comment '创建时间',
   remark               varchar(512) comment '支付类型描述',
   primary key (type_id)
);

alter table t_order_pay_type comment '订单支付类型';

/*==============================================================*/
/* Table: t_order_payment                                       */
/*==============================================================*/
create table t_order_payment
(
   payment_id           varchar(20) not null comment '支付标识',
   type_id              varchar(20) not null comment '支付类型',
   order_id             varchar(20) not null comment '订单标识',
   pay_num              varchar(32) comment '支付单号',
   money                decimal(8,2) comment '支付金钱',
   jifen                decimal(12,4) comment '支付积分',
   create_time          datetime not null comment '创建时间',
   primary key (payment_id)
);

alter table t_order_payment comment '订单支付信息表';

/*==============================================================*/
/* Table: t_order_track                                         */
/*==============================================================*/
create table t_order_track
(
   track_id             varchar(20) not null comment 'track标识',
   order_id             varchar(20) not null comment '订单标识',
   create_time          datetime not null comment '创建时间',
   remark               varchar(512) not null comment '描述信息',
   primary key (track_id)
);

alter table t_order_track comment '订单跟踪表';

/*==============================================================*/
/* Table: t_ref_admin_role                                      */
/*==============================================================*/
create table t_ref_admin_role
(
   ref_id               int(11) not null auto_increment comment 'ref_id',
   admin_id             varchar(20) not null comment '管理员标识',
   role_id              int(11) not null comment '角色标识',
   primary key (ref_id)
);

alter table t_ref_admin_role comment '管理员角色关系表';

/*==============================================================*/
/* Table: t_ref_jifen_charge                                    */
/*==============================================================*/
create table t_ref_jifen_charge
(
   number               varchar(20) not null comment '关系标识',
   jifen                decimal(12,4) not null comment '积分',
   charge               decimal(8,2) not null comment '话费',
   remark               varchar(512) comment '描述',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (number)
);

alter table t_ref_jifen_charge comment '积分话费兑换关系表';

/*==============================================================*/
/* Table: t_ref_role_menu                                       */
/*==============================================================*/
create table t_ref_role_menu
(
   ref_id               int(11) not null auto_increment comment 'ref_id',
   role_id              int(11) not null comment '角色标识',
   menu_id              int(11) not null comment '菜单标识',
   primary key (ref_id)
);

alter table t_ref_role_menu comment '角色菜单关联表';

/*==============================================================*/
/* Table: t_source                                              */
/*==============================================================*/
create table t_source
(
   source_id            int(4) not null comment '来源标识',
   source_name          varchar(256) not null comment '来源名称',
   logo                 varchar(1024) comment '来源logo',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (source_id)
);

alter table t_source comment '来源信息表';

/*==============================================================*/
/* Table: t_target                                              */
/*==============================================================*/
create table t_target
(
   target_id            varchar(20) not null comment '位置标识',
   target_num           varchar(32) not null comment '位置编号',
   target_name          varchar(1024) not null comment '位置名称',
   image_height         int(4) not null comment '图片高度',
   image_width          int(4) not null comment '图片宽度',
   remark               varchar(512) comment '位置描述',
   max_limit            int(2) not null default 1 comment '最大内容限制',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除',
   primary key (target_id)
);

alter table t_target comment '位置信息表';

/*==============================================================*/
/* Table: t_target_content                                      */
/*==============================================================*/
create table t_target_content
(
   content_id           varchar(20) not null comment '内容标识',
   target_id            varchar(20) not null comment '位置标识',
   title                varchar(256) not null comment '内容标题',
   image                varchar(1024) not null comment '图片地址',
   href                 varchar(1024) comment '链接地址',
   `order`              int(2) not null comment '内容顺序',
   expiration_time      datetime not null comment '过期时间',
   create_time          datetime not null comment '创建时间',
   publish_status       int(2) not null comment '发布状态：0、待发布|1、发布|2、取消发布',
   remark               varchar(512) comment '备注',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (content_id)
);

alter table t_target_content comment '位置内容表';

/*==============================================================*/
/* Table: t_user_expires_jifen                                  */
/*==============================================================*/
create table t_user_expires_jifen
(
   expires_id           bigint(18) not null auto_increment comment 'expires',
   user_id              varchar(20) not null comment '用户ID',
   prev_expires         decimal(12,4) not null comment '月前过期',
   curr_expires         decimal(12,4) not null comment '月过期',
   real_expires         decimal(12,4) not null default 0 comment '实际过期',
   expires_time         datetime not null comment '过期时间',
   create_time          datetime not null comment '创建时间',
   deduct_time          datetime comment '扣除时间',
   is_deduct            int(2) not null default 0 comment '是否已扣除：0、未扣除 | 1、已扣除',
   primary key (expires_id)
);

alter table t_user_expires_jifen comment '用户每月过期积分表';

/*==============================================================*/
/* Table: t_user_jifen                                          */
/*==============================================================*/
create table t_user_jifen
(
   user_id              varchar(20) not null comment '用户ID',
   jifen                decimal(12,4) not null comment '用户积分',
   total_out            decimal(12,4) not null comment '总支出',
   total_get            decimal(12,4) not null comment '总收入',
   primary key (user_id)
);

alter table t_user_jifen comment '用户积分表';

/*==============================================================*/
/* Table: t_user_jifen_journal                                  */
/*==============================================================*/
create table t_user_jifen_journal
(
   journal_id           varchar(20) not null comment '记录标识',
   user_id              varchar(20) not null comment '用户标识',
   operate              varchar(512) not null comment '操作',
   type                 int(2) not null comment '类型 ：1、获取|2、支出',
   jifen                decimal(12,4) not null comment '用户积分',
   journal_time         datetime not null comment '操作时间',
   expires_time         datetime not null comment '过期时间',
   source               int(4) not null comment '来源',
   remark               varchar(512) comment '描述',
   has_history          int(2) not null comment '是否有历史明细：0、正常|1、有历史明细',
   primary key (journal_id)
);

alter table t_user_jifen_journal comment '用户积分明细';

/*==============================================================*/
/* Table: t_user_ship_address                                   */
/*==============================================================*/
create table t_user_ship_address
(
   address_id           varchar(20) not null default '0' comment '地址标识',
   user_id              varchar(20) not null comment '用户标识',
   consignee            varchar(256) not null comment '收货人',
   mobile               varchar(16) not null comment '手机号码',
   telephone            varchar(20) comment '固定电话',
   area_id              varchar(16) not null comment '区域标识',
   address              varchar(1024) not null comment '详细地址',
   create_time          datetime comment '创建时间',
   is_default           int(2) not null default 0 comment '默认收货地址：0、正常|1、默认',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (address_id)
);

alter table t_user_ship_address comment '用户收货地址';

/*==============================================================*/
/* Table: t_user_source_jifen                                   */
/*==============================================================*/
create table t_user_source_jifen
(
   user_id              varchar(20) not null comment '用户ID',
   jifen                decimal(12,4) not null comment '用户积分',
   total_out            decimal(12,4) not null comment '总支出',
   total_get            decimal(12,4) not null comment '总收入',
   source               int(4) not null comment '来源平台',
   primary key (user_id, source)
);

alter table t_user_source_jifen comment '用户来源模块积分表';

/*==============================================================*/
/* Table: t_user_source_jifen_journal                           */
/*==============================================================*/
create table t_user_source_jifen_journal
(
   source_journal_id    bigint(20) not null auto_increment comment '来源记录标识',
   journal_id           varchar(20) not null comment '记录标识',
   user_id              varchar(20) not null comment '用户标识',
   operate              varchar(512) not null comment '操作',
   type                 int(2) not null comment '类型 ：1、获取|2、支出',
   jifen                decimal(12,4) not null comment '用户积分',
   journal_time         datetime not null comment '操作时间',
   expires_time         datetime not null comment '过期时间',
   source               int(4) not null comment '来源',
   remark               varchar(512) comment '描述',
   primary key (source_journal_id)
);

alter table t_user_source_jifen_journal comment '用户来源模块积分明细';

/*==============================================================*/
/* Table: t_user_vipcard_bound                                  */
/*==============================================================*/
create table t_user_vipcard_bound
(
   user_id              varchar(20) not null comment '用户ID',
   cust_no              varchar(32) not null comment '顾客编号',
   card_num             varchar(32) not null comment '会员卡号',
   jifen                decimal(12,4) not null comment '绑定积分',
   bound_time           datetime not null comment '绑定时间',
   create_time          datetime not null comment '创建时间',
   primary key (user_id)
);

alter table t_user_vipcard_bound comment '用户会员卡绑定表';

