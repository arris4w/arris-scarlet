/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/6/3 16:26:06                            */
/*==============================================================*/


drop table if exists xbb_base_area;

drop table if exists xbb_base_city;

drop table if exists xbb_base_province;

drop table if exists xbb_biz_eval;

drop table if exists xbb_biz_eval_proj;

drop table if exists xbb_biz_peserve;

drop table if exists xbb_cms_column;

drop table if exists xbb_cms_info;

drop table if exists xbb_cms_position;

drop table if exists xbb_cms_position_info;

drop table if exists xbb_edu_brand;

drop table if exists xbb_edu_brand_intro;

drop table if exists xbb_edu_class;

drop table if exists xbb_edu_curriculum;

drop table if exists xbb_edu_inst;

drop table if exists xbb_edu_inst_picture;

drop table if exists xbb_edu_inst_teacher;

drop table if exists xbb_edu_service;

drop table if exists xbb_mem_baby;

drop table if exists xbb_mem_member;

drop table if exists xbb_sys_user;

/*==============================================================*/
/* Table: xbb_base_area                                         */
/*==============================================================*/
create table xbb_base_area
(
   code                 numeric(6) not null comment '区编码',
   name                 char(20) comment '区名称',
   city_code            numeric(6) comment '城市编码',
   primary key (code)
);

alter table xbb_base_area comment '区域表';

/*==============================================================*/
/* Table: xbb_base_city                                         */
/*==============================================================*/
create table xbb_base_city
(
   code                 numeric(6) not null comment '城市编码',
   name                 char(20) comment '城市名称',
   province_code        numeric(6) comment '省编码',
   primary key (code)
);

alter table xbb_base_city comment '城市表';

/*==============================================================*/
/* Table: xbb_base_province                                     */
/*==============================================================*/
create table xbb_base_province
(
   code                 numeric(6) not null comment '省编码',
   name                 char(20) comment '省名',
   primary key (code)
);

alter table xbb_base_province comment '省市表';

/*==============================================================*/
/* Table: xbb_biz_eval                                          */
/*==============================================================*/
create table xbb_biz_eval
(
   Eval_Id              bigint not null auto_increment comment '点评序号ID',
   Total_Score          numeric(2,1) comment '总评分',
   Eval_Item_Score      varchar(300) comment '评价项和评分',
   Eval_Content         varchar(300) comment '评价内容',
   Eval_Time            datetime comment '评价时间',
   Eval_Person          varchar(100) comment '评价人',
   Eval_Item_Envi       numeric(1) comment '点评项-环境',
   Eval_Item_Qual       numeric(1) comment '点评项-师资',
   Eval_Item_Price      numeric(1) comment '点评项-价格',
   Eval_Item_Service    numeric(1) comment '点评项-服务',
   Eval_Item_Faci       numeric(1) comment '点评项-安全设施',
   Eval_Item_Cheerful   numeric(1) comment '点评项-愉悦度',
   Count_Yes_Help       numeric(3) comment '有帮助计数',
   Count_No_Help        numeric(3) comment '无帮助计数',
   primary key (Eval_Id)
);

alter table xbb_biz_eval comment '机构点评主表';

/*==============================================================*/
/* Table: xbb_biz_eval_proj                                     */
/*==============================================================*/
create table xbb_biz_eval_proj
(
   Proj_Code            numeric(2) not null comment '项目编码',
   Proj_Name            char(20) comment '项目名称',
   Proj_Score           numeric(5) comment '项目分值',
   primary key (Proj_Code)
);

alter table xbb_biz_eval_proj comment '点评项目表';

/*==============================================================*/
/* Table: xbb_biz_peserve                                       */
/*==============================================================*/
create table xbb_biz_peserve
(
   Reserve_Id           int not null auto_increment comment '预约序号ID',
   Reserve_Time         date comment '预约时间',
   Baby_Name            char(20) comment '宝宝姓名',
   Baby_Gender          numeric(1) comment '宝宝性别',
   Baby_Age             numeric(2) comment '宝宝年龄',
   Baby_Birthday        date comment '宝宝生日',
   Parent_Name          char(20) comment '家长姓名',
   Parent_Tel           char(20) comment '联系电话',
   Parent_Call          char(10) comment '家长称呼',
   primary key (Reserve_Id)
);

alter table xbb_biz_peserve comment '预约登记表';

/*==============================================================*/
/* Table: xbb_cms_column                                        */
/*==============================================================*/
create table xbb_cms_column
(
   Col_Id               int not null auto_increment comment '栏目序号',
   Col_Code             numeric(4) comment '栏目编号',
   Col_Name             char(20) comment '栏目名称',
   Col_Desc             varchar(200) comment '栏目描述',
   Parent_Col_Id        int comment '父栏目ID',
   primary key (Col_Id)
);

alter table xbb_cms_column comment '资讯栏目表';

/*==============================================================*/
/* Table: xbb_cms_info                                          */
/*==============================================================*/
create table xbb_cms_info
(
   Info_Id              int not null auto_increment comment '资讯序号ID',
   Col_Id               int comment '栏目序号',
   Info_Title_Large     varchar(50) comment '大标题',
   Info_Title_Small     varchar(20) comment '小标题',
   Info_From            varchar(20) comment '资讯来源',
   Publisher            char(20) comment '发布人',
   Publisher_Time       datetime comment '发布时间',
   Is_Pub               numeric(1) comment '是否发布',
   Info_Content         text comment '资讯内容',
   Is_Static            numeric(1) comment '是否静态化',
   Info_http            char(50) comment '网址',
   Create_Time          datetime comment '创建时间',
   primary key (Info_Id)
);

alter table xbb_cms_info comment '资讯总表';

/*==============================================================*/
/* Table: xbb_cms_position                                      */
/*==============================================================*/
create table xbb_cms_position
(
   Pos_Id               int not null auto_increment comment '位置序号',
   Pos_Code             numeric(4) comment '位置编号',
   Pos_Name             char(20) comment '位置名称',
   Pos_Title            char(100) comment '关键词',
   primary key (Pos_Id)
);

alter table xbb_cms_position comment '位置表';

/*==============================================================*/
/* Table: xbb_cms_position_info                                 */
/*==============================================================*/
create table xbb_cms_position_info
(
   Info_Id              int not null auto_increment comment '内容序号',
   Pos_Id               int comment '位置序号',
   Pic_Http             varchar(100) comment '图片地址',
   Info_Http            varchar(100) comment '内容网址',
   Is_headline          numeric(1) comment '是否头条',
   Info_Title_Large     varchar(50) comment '大标题',
   Info_Title_Small     varchar(20) comment '小标题',
   Create_Time          datetime comment '创建时间',
   primary key (Info_Id)
);

alter table xbb_cms_position_info comment '位置内容表';

/*==============================================================*/
/* Table: xbb_edu_brand                                         */
/*==============================================================*/
create table xbb_edu_brand
(
   brand_Id             int not null auto_increment comment '品牌ID',
   brand_Name           char(30) comment '品牌名称',
   brand_Type           numeric(4) comment '所属类型',
   Suit_Age_Min         numeric(2) comment '适合年龄(min)',
   Suit_Age_Max         numeric(2) comment '适合年龄(max)',
   Suit_Age             char(10) comment '适合年龄',
   Prov_Code            numeric(6) comment '所属省市',
   City_Code            numeric(6) comment '所属城市',
   area_Code            numeric(6) comment '所属区域',
   Address              varchar(300) comment '详细地址',
   brand_www            varchar(100) comment '网址',
   Buss_license         varchar(100) comment '营业执照',
   Legal_Person         char(30) comment '法人代表',
   Serv_Tel             char(30) comment '客服电话',
   create_date          numeric(4) comment '创立时间',
   brand_logo           varchar(100) comment 'LOGO',
   Serv_Items           varchar(300) comment '服务设施',
   primary key (brand_Id)
);

alter table xbb_edu_brand comment '教育品牌表';

/*==============================================================*/
/* Table: xbb_edu_brand_intro                                   */
/*==============================================================*/
create table xbb_edu_brand_intro
(
   brand_Id             int not null comment '品牌ID',
   brand_Intro          text comment '品牌简介',
   static_address       varchar(100) comment '静态地址',
   is_static            numeric(1) comment '是否静态化',
   static_time          datetime comment '静态化时间',
   primary key (brand_Id)
);

alter table xbb_edu_brand_intro comment '教育品牌介绍表';

/*==============================================================*/
/* Table: xbb_edu_class                                         */
/*==============================================================*/
create table xbb_edu_class
(
   Class_Code           numeric(4) not null comment '类型编码',
   Class_Name           char(20) comment '类型名称',
   primary key (Class_Code)
);

alter table xbb_edu_class comment '机构类型表';

/*==============================================================*/
/* Table: xbb_edu_curriculum                                    */
/*==============================================================*/
create table xbb_edu_curriculum
(
   Curriculum_Id        int not null auto_increment comment '序号',
   brand_Id             int comment '品牌ID',
   Curriculum_Name      char(20) comment '课程名称',
   Suit_Age_Min         numeric(2) comment '适合年龄(min)',
   Suit_Age             char(10) comment '适合年龄',
   Suit_Age_Max         numeric(2) comment '适合年龄(max)',
   Class_Hour           numeric(3,1) comment '课时',
   Curriculum_Intro     text comment '课程介绍',
   Is_Audition          numeric(1) comment '是否试听',
   brand_Type           numeric(4) comment '所属类型',
   primary key (Curriculum_Id)
);

alter table xbb_edu_curriculum comment '开发课程表';

/*==============================================================*/
/* Table: xbb_edu_inst                                          */
/*==============================================================*/
create table xbb_edu_inst
(
   Inst_Id              int not null comment '机构序号ID',
   brand_Id             int comment '品牌ID',
   Inst_Name            char(30) comment '机构名称',
   Prov_Code            numeric(6) comment '所属省市',
   City_Code            numeric(6) comment '所属城市',
   area_Code            numeric(6) comment '所属区域',
   Address              varchar(300) comment '详细地址',
   Gps_Longitude        char(30) comment 'GPS经度',
   Gps_Latitude         char(30) comment 'GPS纬度',
   Gps_Altitude         char(30) comment 'GPS海拔',
   Inst_Head            char(30) comment '负责人',
   Inst_Head_Tel        char(30) comment '负责人电话',
   Serv_Tel             char(30) comment '服务电话',
   Serv_Items           varchar(300) comment '服务设施',
   primary key (Inst_Id)
);

alter table xbb_edu_inst comment '机构表';

/*==============================================================*/
/* Table: xbb_edu_inst_picture                                  */
/*==============================================================*/
create table xbb_edu_inst_picture
(
   Pic_Id               int not null auto_increment comment '序号',
   Inst_Id              int comment '机构序号ID',
   File_Name            char(50) comment '照片名称',
   Upload_Time          datetime comment '上传时间',
   File_Path            varchar(200) comment '照片地址',
   primary key (Pic_Id)
);

alter table xbb_edu_inst_picture comment '机构照片';

/*==============================================================*/
/* Table: xbb_edu_inst_teacher                                  */
/*==============================================================*/
create table xbb_edu_inst_teacher
(
   Teacher_Id           int not null auto_increment comment '序号',
   Inst_Id              int comment '机构序号ID',
   Teacher_Name         char(20) comment '姓名',
   Teacher_Age          numeric(2) comment '年龄',
   Teacher_Sex          numeric(1) comment '性别',
   Is_Foreign           numeric(1) comment '是否外教',
   Nationality          char(4) comment '所属国籍',
   Teacher_Intro        varchar(200) comment '教师简介',
   Teacher_Pic          varchar(50) comment '照片',
   primary key (Teacher_Id)
);

alter table xbb_edu_inst_teacher comment '机构老师';

/*==============================================================*/
/* Table: xbb_edu_service                                       */
/*==============================================================*/
create table xbb_edu_service
(
   Serv_Code            numeric(4) not null comment '设施编码',
   Serv_Name            char(20) comment '设施名称',
   Serv_Type            numeric(2) comment '设施类型',
   icon_code            char(20) comment '图标代码',
   primary key (Serv_Code)
);

alter table xbb_edu_service comment '服务设施项目表';

/*==============================================================*/
/* Table: xbb_mem_baby                                          */
/*==============================================================*/
create table xbb_mem_baby
(
   Archives_Id          int not null auto_increment comment '序号',
   Archives_No          numeric(12) comment '档案代码',
   Baby_Name            char(20) comment '姓名',
   Baby_Age             numeric(2) comment '年龄',
   Baby_birth           date comment '出生年月',
   Baby_Sex             numeric(1) comment '性别',
   Parent_Name          char(20) comment '家长姓名',
   Parent_Tel           varchar(30) comment '家长电话',
   Parent_Address       varchar(200) comment '家庭住址',
   primary key (Archives_Id)
);

alter table xbb_mem_baby comment '婴幼儿档案表';

/*==============================================================*/
/* Table: xbb_mem_member                                        */
/*==============================================================*/
create table xbb_mem_member
(
   Member_Id            int not null auto_increment comment '会员序号',
   Member_Code          numeric(12) comment '会员代码',
   Member_Card_No       numeric(12) comment '会员卡号',
   Member_Name          char(20) comment '会员名称',
   Member_Account       char(30) comment '会员账号',
   Member_Password      char(20) comment '会员密码',
   Create_Time          datetime comment '创建时间',
   Is_Vaild             numeric(1) comment '是否有效',
   primary key (Member_Id)
);

alter table xbb_mem_member comment '会员表';

/*==============================================================*/
/* Table: xbb_sys_user                                          */
/*==============================================================*/
create table xbb_sys_user
(
   User_Id              int not null auto_increment comment '用户序号',
   User_Code            numeric(4) comment '用户代码',
   User_Name            char(20) comment '用户名称',
   User_Password        char(20) comment '用户密码',
   Is_Valid             numeric(1) comment '是否有效',
   Create_Time          datetime comment '创建时间',
   primary key (User_Id)
);

alter table xbb_sys_user comment '后台用户表';


