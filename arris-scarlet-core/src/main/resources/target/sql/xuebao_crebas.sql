/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/8/26 10:18:29                           */
/*==============================================================*/


drop table if exists t_admin;

drop table if exists t_admin_role;

drop table if exists t_baby_hobbies;

drop table if exists t_base_area;

drop table if exists t_brand_service;

drop table if exists t_brand_type;

drop table if exists t_column;

drop table if exists t_curriculum_type;

drop table if exists t_edu_brand;

drop table if exists t_edu_brand_intro;

drop table if exists t_edu_curriculum;

drop table if exists t_edu_inst;

drop table if exists t_edu_inst_picture;

drop table if exists t_edu_inst_picture_type;

drop table if exists t_edu_inst_teacher;

drop table if exists t_eval;

drop table if exists t_info;

drop table if exists t_journal_log;

drop table if exists t_member;

drop table if exists t_member_baby;

drop table if exists t_menu;

drop table if exists t_peserve;

drop table if exists t_ref_admin_role;

drop table if exists t_ref_baby_hobbies;

drop table if exists t_ref_brand_type;

drop table if exists t_ref_eval_no_help;

drop table if exists t_ref_eval_yes_help;

drop table if exists t_ref_inst_type;

drop table if exists t_ref_role_menu;

drop table if exists t_target;

drop table if exists t_target_content;

/*==============================================================*/
/* Table: t_admin                                               */
/*==============================================================*/
create table t_admin
(
   admin_id             int(9) not null auto_increment comment '管理员ID',
   account              varchar(64) not null comment '管理员账号',
   password             varchar(128) not null comment '账号密码',
   salt                 varchar(8) not null comment '密码盐值',
   name                 varchar(64) not null comment '姓名',
   mobile               varchar(16) not null comment '手机号码',
   email                varchar(64) comment '电子邮箱',
   reg_time             datetime not null comment '注册时间',
   remark               varchar(512) comment '描述',
   state                int(2) not null default 1 comment '管理员状态：1、启用|2、禁用',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (admin_id)
);

alter table t_admin comment '后台用户表';

/*==============================================================*/
/* Table: t_admin_role                                          */
/*==============================================================*/
create table t_admin_role
(
   role_id              int(9) not null auto_increment comment '角色标识',
   role_name            varchar(64) not null comment '角色名称',
   remark               varchar(512) not null comment '描述',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (role_id)
);

alter table t_admin_role comment '管理员角色信息表';

/*==============================================================*/
/* Table: t_baby_hobbies                                        */
/*==============================================================*/
create table t_baby_hobbies
(
   hobbies_id           int(4) not null auto_increment comment '兴趣爱好标识',
   name                 varchar(64) not null comment '兴趣爱好名称',
   primary key (hobbies_id)
);

alter table t_baby_hobbies comment '婴幼儿兴趣爱好项表';

/*==============================================================*/
/* Table: t_base_area                                           */
/*==============================================================*/
create table t_base_area
(
   area_id              varchar(8) not null comment '省区编号',
   name                 varchar(20) not null comment '省区名称',
   parent_area_id       varchar(8) comment '父级编号',
   type                 int(2) not null comment '省区类型：1、大区|2、省|3、市|4、区',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   is_content_support   int(2) not null default 0 comment '内容支持状态：0、未支持|1、已支持',
   primary key (area_id)
);

alter table t_base_area comment '区域基础信息表';

/*==============================================================*/
/* Table: t_brand_service                                       */
/*==============================================================*/
create table t_brand_service
(
   service_id           int(4) not null auto_increment comment '设施标识',
   name                 varchar(64) not null comment '设施名称',
   icon_code            varchar(64) not null comment '图标代码',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (service_id)
);

alter table t_brand_service comment '服务设施项目表';

/*==============================================================*/
/* Table: t_brand_type                                          */
/*==============================================================*/
create table t_brand_type
(
   type_id              int(9) not null auto_increment comment '类型标识',
   name                 varchar(64) not null comment '类型名称',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (type_id)
);

alter table t_brand_type comment '品牌机构类型';

/*==============================================================*/
/* Table: t_column                                              */
/*==============================================================*/
create table t_column
(
   col_id               int(9) not null auto_increment comment '栏目标识',
   col_code             varchar(32) not null comment '栏目编号',
   col_name             varchar(64) not null comment '栏目名称',
   col_desc             varchar(512) not null comment '栏目描述',
   parent_col_id        int(9) not null comment '父栏目ID',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (col_id)
);

alter table t_column comment '资讯栏目表';

/*==============================================================*/
/* Table: t_curriculum_type                                     */
/*==============================================================*/
create table t_curriculum_type
(
   type_id              int(9) not null auto_increment comment '类型标识',
   name                 varchar(64) not null comment '类型名称',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (type_id)
);

alter table t_curriculum_type comment '开发课程类型表';

/*==============================================================*/
/* Table: t_edu_brand                                           */
/*==============================================================*/
create table t_edu_brand
(
   brand_id             int(9) not null auto_increment comment '品牌ID',
   name                 varchar(64) not null comment '品牌名称',
   initial              char(1) not null comment '名称首字母（大写）',
   suit_age_min         int(2) comment '适合年龄(min)',
   suit_age_max         int(2) comment '适合年龄(max)',
   city_id              varchar(8) not null comment '所属城市',
   area_id              varchar(8) not null comment '所属区域',
   address              varchar(1024) comment '详细地址',
   brand_site           varchar(64) comment '网址',
   buss_license         varchar(512) not null comment '营业执照',
   legal_person         varchar(64) not null comment '法人代表',
   service_tel          varchar(16) not null comment '客服电话',
   establishing_time    datetime not null comment '创立时间',
   brand_logo           varchar(512) comment 'LOGO',
   servic_items         varchar(512) comment '服务设施',
   is_hot               int(2) not null default 0 comment '是否热门：0、普通|1、正常',
   publish_state        int(2) not null default 1 comment '发布状态：1、待发布|2、发布|3、取消发布',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (brand_id)
);

alter table t_edu_brand comment '教育品牌表';

/*==============================================================*/
/* Table: t_edu_brand_intro                                     */
/*==============================================================*/
create table t_edu_brand_intro
(
   brand_Id             int(9) not null comment '品牌标识',
   intro                text comment '品牌简介',
   static_address       varchar(512) comment '静态地址',
   is_static            int(2) default 0 comment '是否静态化：0、否|1、是',
   static_time          date comment '静态化时间',
   primary key (brand_Id)
);

alter table t_edu_brand_intro comment '教育品牌介绍表';

/*==============================================================*/
/* Table: t_edu_curriculum                                      */
/*==============================================================*/
create table t_edu_curriculum
(
   curriculum_id        int(9) not null auto_increment comment '课程标识',
   inst_id              int(9) not null comment '机构标识',
   type_id              int(9) not null comment '所属类型',
   curriculum_name      varchar(256) not null comment '课程名称',
   title_pic            varchar(512) not null comment '标题图片',
   suit_age_min         int(2) comment '适合年龄(min)',
   suit_age_max         int(2) comment '适合年龄(max)',
   class_hour           numeric(3,1) not null comment '课时',
   curriculum_intro     text not null comment '课程介绍',
   is_audition          int(2) not null comment '是否试听：0、否|1、是',
   create_time          datetime comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (curriculum_id)
);

alter table t_edu_curriculum comment '开发课程表';

/*==============================================================*/
/* Table: t_edu_inst                                            */
/*==============================================================*/
create table t_edu_inst
(
   inst_id              int(9) not null auto_increment comment '机构标识',
   brand_id             int(9) not null comment '品牌标识',
   name                 varchar(64) not null comment '机构名称',
   suit_age_min         int(2) comment '适合年龄(min)',
   suit_age_max         int(2) comment '适合年龄(max)',
   city_id              varchar(8) not null comment '所属城市',
   area_id              varchar(8) not null comment '所属区域',
   address              varchar(300) not null comment '详细地址',
   opening_time         varchar(512) not null comment '营业时间',
   synthesize_score     numeric(5,4) comment '综合评分',
   gps_longitude        char(30) comment 'GPS经度',
   gps_latitude         char(30) comment 'GPS纬度',
   gps_altitude         char(30) comment 'GPS海拔',
   inst_head            varchar(64) comment '负责人',
   inst_head_tel        varchar(16) not null comment '负责人电话',
   service_tel          varchar(16) not null comment '服务电话',
   service_items        varchar(512) comment '服务设施',
   pic_url              varchar(512) comment '形象照片（相册封面照）',
   foreign_state        int(2) default 1 comment '外教状态：1、有外教|2、无外教',
   audition_state       int(2) not null default 1 comment '试听状态：1、支持试听|2、不支持试听',
   publish_state        int(2) not null default 1 comment '发布状态：1、待发布|2、发布|3、取发布',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (inst_id)
);

alter table t_edu_inst comment '教育机构表';

/*==============================================================*/
/* Table: t_edu_inst_picture                                    */
/*==============================================================*/
create table t_edu_inst_picture
(
   pic_id               int(9) not null auto_increment comment '照片标识',
   type_id              int(9) not null comment '类型标识',
   inst_id              int(9) not null comment '机构标识',
   file_name            varchar(64) comment '照片名称',
   pic_url_a            varchar(512) comment '照片地址A',
   pic_url_b            varchar(512) comment '照片地址B',
   upload_time          datetime not null comment '上传时间',
   is_cover             int(2) not null default 0 comment '是否封面：0、非封面|1、封面照片',
   primary key (pic_id)
);

alter table t_edu_inst_picture comment '教育机构照片表';

/*==============================================================*/
/* Table: t_edu_inst_picture_type                               */
/*==============================================================*/
create table t_edu_inst_picture_type
(
   type_id              int(9) not null auto_increment comment '类型标识',
   name                 varchar(64) not null comment '类型名称',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (type_id)
);

alter table t_edu_inst_picture_type comment '机构照片类型表';

/*==============================================================*/
/* Table: t_edu_inst_teacher                                    */
/*==============================================================*/
create table t_edu_inst_teacher
(
   teacher_Id           int(9) not null auto_increment comment '教师标识',
   inst_id              int(9) not null comment '机构标识',
   name                 varchar(64) comment '教师姓名',
   age                  int(2) comment '教师年龄',
   sex                  int(2) comment '教师性别：1、男|2、女|3、保密',
   Is_foreign           int(2) comment '是否外教：0、否|1、是',
   nationality          char(4) comment '所属国籍',
   teacher_intro        varchar(512) comment '教师简介',
   teacher_pic          varchar(512) comment '照片',
   work_state           int(2) not null default 1 comment '在职状态：1、在职、2、离职',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (teacher_Id)
);

alter table t_edu_inst_teacher comment '机构教师表';

/*==============================================================*/
/* Table: t_eval                                                */
/*==============================================================*/
create table t_eval
(
   eval_id              bigint not null auto_increment comment '点评标识',
   inst_id              int(9) not null comment '机构标识',
   synthesize_score     numeric(5,4) not null comment '综合评分',
   member_id            int(9) not null comment '评价人',
   eval_item_popu       int(1) not null comment '点评项-知名度',
   eval_item_lot        int(1) not null comment '点评项-地段',
   eval_item_envi       int(1) not null comment '点评项-环境',
   eval_item_hard_faci  int(1) not null comment '点评项-硬件设施',
   eval_item_sec_faci   int(1) not null comment '点评项-安全设施',
   eval_item_qual       int(1) not null comment '点评项-师资',
   eval_item_course     int(1) not null comment '点评项-课程',
   eval_item_price      int(1) not null comment '点评项-价格',
   eval_item_service    int(1) not null comment '点评项-服务',
   eval_item_cheerful   int(1) not null comment '点评项-愉悦度',
   edu_start_date       date not null comment '入学年份',
   eval_content         varchar(512) comment '评价内容',
   count_yes_help       int(3) default 0 comment '有帮助计数',
   count_no_help        int(3) default 0 comment '无帮助计数',
   eval_time            datetime not null comment '评价时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (eval_id)
);

alter table t_eval comment '机构点评主表';

/*==============================================================*/
/* Table: t_info                                                */
/*==============================================================*/
create table t_info
(
   info_id              int(9) not null auto_increment comment '资讯标识',
   col_id               int(9) comment '栏目标识',
   info_title_large     varchar(256) comment '大标题',
   info_title_small     varchar(256) comment '小标题',
   info_from            varchar(32) comment '资讯来源',
   publisher            varchar(32) comment '发布人',
   publisher_time       datetime comment '发布时间',
   info_content         text comment '资讯内容',
   is_static            int comment '是否静态化',
   info_url             varchar(512) comment '网址',
   create_time          datetime comment '创建时间',
   publish_status       int(2) not null default 1 comment '发布状态：1、待发布|2、发布|3、取消发布',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (info_id)
);

alter table t_info comment '资讯表';

/*==============================================================*/
/* Table: t_journal_log                                         */
/*==============================================================*/
create table t_journal_log
(
   log_id               bigint(20) not null auto_increment comment '日志标识',
   admin_id             int(9) not null comment '用户ID',
   admin_name           varchar(64) not null comment '用户姓名',
   admin_ip             varchar(64) not null comment '操作IP',
   journal_type         char(1) not null comment '操作类型(1:新增操作;2:更新操作;3:删除操作)',
   journal_time         datetime not null comment '记录时间',
   description          varchar(512) not null comment '描述信息',
   primary key (log_id)
);

alter table t_journal_log comment '管理员操作日志表';

/*==============================================================*/
/* Table: t_member                                              */
/*==============================================================*/
create table t_member
(
   member_id            int(9) not null comment '会员ID',
   mobile               varchar(16) comment '手机号',
   password             varchar(128) not null comment '密码',
   salt                 varchar(12) not null comment '盐值',
   nick_name            varchar(64) comment '昵称',
   user_name            varchar(64) comment '姓名',
   sex                  int(1) comment '性别：1、男|2、女|3、保密',
   birthday             date comment '生日',
   head_pic             varchar(512) comment '头像地址',
   identity             varchar(18) comment '身份证号',
   reg_time             datetime not null comment '注册时间',
   source               int(4) comment '注册来源',
   area_id              varchar(16) comment '地区',
   address              varchar(512) comment '详细地址',
   email                varchar(64) not null comment '邮箱',
   user_status          int(2) not null default 1 comment '会员状态：1、正常|2、锁定',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (member_id)
);

alter table t_member comment '用户基本信息表';

/*==============================================================*/
/* Table: t_member_baby                                         */
/*==============================================================*/
create table t_member_baby
(
   archives_id          int(9) not null auto_increment comment '档案标识',
   member_id            int(9) comment '会员标识',
   archives_no          varchar(12) comment '档案代码',
   baby_name            varchar(64) not null comment '宝宝姓名',
   baby_age             int(2) not null comment '宝宝年龄',
   baby_birth           date not null comment '宝宝出生年月',
   baby_sex             int(1) not null comment '宝宝性别',
   parent_name          varchar(64) not null comment '家长姓名',
   parent_tel           varchar(16) not null comment '家长电话',
   parent_address       varchar(512) comment '家庭住址',
   create_time          datetime not null comment '创建时间',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (archives_id)
);

alter table t_member_baby comment '婴幼儿档案表';

/*==============================================================*/
/* Table: t_menu                                                */
/*==============================================================*/
create table t_menu
(
   menu_id              int(9) not null auto_increment comment '菜单标识',
   menu_name            varchar(64) not null comment '菜单名称',
   menu_parent          int(9) comment '父菜单',
   menu_href            varchar(512) comment '连接地址',
   icon_cls             varchar(128) comment '图标样式',
   menu_order           int(2) not null comment '显示顺序',
   status               int(2) not null default 1 comment '菜单状态：1、启用|2、禁用',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (menu_id)
);

alter table t_menu comment '菜单信息表';

/*==============================================================*/
/* Table: t_peserve                                             */
/*==============================================================*/
create table t_peserve
(
   reserve_id           int(9) not null auto_increment comment '预约序号ID',
   curriculum_id        int(9) not null comment '课程编号',
   reserve_time         datetime not null comment '预约时间',
   member_id            int(9) not null comment '预约人',
   baby_name            varchar(64) not null comment '宝宝姓名',
   baby_sex             int(1) not null comment '宝宝性别',
   baby_age             int(2) not null comment '宝宝年龄',
   baby_birthday        date not null comment '宝宝生日',
   parent_name          varchar(64) not null comment '家长姓名',
   parent_tel           varchar(16) not null comment '联系电话',
   parent_call          varchar(64) not null comment '家长称呼',
   create_time          datetime not null comment '创建时间',
   reserve_state        int(2) not null default 1 comment '预约状态：1、未处理|2、已处理|3、已完成',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (reserve_id)
);

alter table t_peserve comment '预约登记表';

/*==============================================================*/
/* Table: t_ref_admin_role                                      */
/*==============================================================*/
create table t_ref_admin_role
(
   ref_id               int(9) not null auto_increment comment 'ref_id',
   admin_id             varchar(20) not null comment '管理员标识',
   role_id              int(9) not null comment '角色标识',
   primary key (ref_id)
);

alter table t_ref_admin_role comment '后台用户角色关系表';

/*==============================================================*/
/* Table: t_ref_baby_hobbies                                    */
/*==============================================================*/
create table t_ref_baby_hobbies
(
   ref_id               bigint not null auto_increment comment '关联标识',
   archives_id          int(9) not null comment '婴幼儿档案标识',
   hobbies_id           int(4) not null comment '兴趣爱好标识',
   primary key (ref_id)
);

alter table t_ref_baby_hobbies comment '婴幼儿兴趣爱好项表';

/*==============================================================*/
/* Table: t_ref_brand_type                                      */
/*==============================================================*/
create table t_ref_brand_type
(
   ref_id               bigint not null auto_increment comment '关联标识',
   type_id              int(9) not null comment '类型标识',
   brand_id             int(9) not null comment '品牌标识',
   primary key (ref_id)
);

alter table t_ref_brand_type comment '品牌品牌机构类型关联表';

/*==============================================================*/
/* Table: t_ref_eval_no_help                                    */
/*==============================================================*/
create table t_ref_eval_no_help
(
   ref_id               bigint not null auto_increment comment '关联标识',
   eval_id              int(9) not null comment '评论标识',
   member_id            int(9) not null comment '会员标识',
   primary key (ref_id)
);

alter table t_ref_eval_no_help comment '评论无帮助表';

/*==============================================================*/
/* Table: t_ref_eval_yes_help                                   */
/*==============================================================*/
create table t_ref_eval_yes_help
(
   ref_id               bigint not null auto_increment comment '关联标识',
   eval_id              int(9) not null comment '评论标识',
   member_id            int(9) not null comment '会员标识',
   primary key (ref_id)
);

alter table t_ref_eval_yes_help comment '评论有帮助表';

/*==============================================================*/
/* Table: t_ref_inst_type                                       */
/*==============================================================*/
create table t_ref_inst_type
(
   ref_id               bigint not null auto_increment comment '关联标识',
   type_id              int(9) not null comment '类型标识',
   inst_id              int(9) not null comment '机构标识',
   primary key (ref_id)
);

alter table t_ref_inst_type comment '机构品牌机构类型关联表';

/*==============================================================*/
/* Table: t_ref_role_menu                                       */
/*==============================================================*/
create table t_ref_role_menu
(
   ref_id               int(9) not null auto_increment comment 'ref_id',
   role_id              int(9) not null comment '角色标识',
   menu_id              int(9) not null comment '菜单标识',
   primary key (ref_id)
);

alter table t_ref_role_menu comment '角色菜单关联表';

/*==============================================================*/
/* Table: t_target                                              */
/*==============================================================*/
create table t_target
(
   target_id            int(9) not null comment '位置标识',
   target_num           varchar(32) not null comment '位置编号',
   target_name          varchar(1024) not null comment '位置名称',
   image_height         int(4) comment '图片高度',
   image_width          int(4) comment '图片宽度',
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
   content_id           int(9) not null comment '内容标识',
   target_id            varchar(20) not null comment '位置标识',
   content_type         int(2) not null comment '内容类型：1、普通内容|2、机构内容3、资讯内容',
   ref_content_id       int(9) comment '关联内容标识',
   title                varchar(256) not null comment '内容标题',
   image                varchar(512) not null comment '图片地址',
   href                 varchar(512) comment '链接地址',
   `order`              int(2) not null comment '内容顺序',
   expiration_time      datetime not null comment '过期时间',
   create_time          datetime not null comment '创建时间',
   remark               varchar(512) comment '备注',
   publish_status       int(2) not null default 1 comment '发布状态：1、待发布|2、发布|3、取消发布',
   is_delete            int(2) not null default 0 comment '是否删除：0、正常|1、已删除',
   primary key (content_id)
);

alter table t_target_content comment '位置内容表';

