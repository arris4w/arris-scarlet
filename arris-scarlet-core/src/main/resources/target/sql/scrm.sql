/*==============================================================*/
/* Table: t_operation_user                                      */
/*==============================================================*/
create table t_operation_user
(
   id                   bigint(20) not null auto_increment comment '用户标识',
   mobile               varchar(20) not null comment '手机号',
   user_name            varchar(64) not null comment '用户姓名',
   dept_id              int(11) not null comment '用户所属的部门id',
   dept_id_chain        varchar(32) not null comment '用户所属的部门id链',
   disabled             tinyint(4) not null default 0 comment '是否禁用：0、启用|1、禁用',
   creator_id           bigint(20) not null comment '创建人标识',
   creator_name         varchar(64) not null comment '创建人名称',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   modifier_id          bigint(20) comment '修改人标识',
   modifier_name        varchar(64) comment '修改人名称',
   modify_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   deleted              bigint(20) not null default 0 comment '是否删除：0、否|Id、是',
   primary key (id)
);

alter table t_operation_user comment '运营后台用户信息表';