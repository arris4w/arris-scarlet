drop table if exists sys_operator;

/*==============================================================*/
/* Table: sys_operator                                          */
/*==============================================================*/
create table sys_operator
(
   sGuid                VARCHAR2(32) not null comment '主键',
   sOperatorNo          VARCHAR2(50) not null comment '管理员编号',
   sUserName            VARCHAR2(50) not null comment '用户名',
   sPassword            VARCHAR2(33) comment '密码',
   sDepartmentID        VARCHAR2(32) comment '部门ID',
   sRealName            VARCHAR2(50) comment '真实姓名',
   sMobile              VARCHAR2(20) comment '手机',
   sPhone               VARCHAR2(20) comment '联系电话',
   sMail                VARCHAR2(100) comment '邮箱',
   bIsFreeze            INT comment '是否禁用:0：禁用
            1：启用',
   bIsAdmin             INT comment '是否管理员:0 no
            1 yes',
   bIsDelete            INT not null comment '是否删除',
   dLastLoginTime       Date comment '上次登陆时间',
   dEndLoginTime        Date comment '最后登陆时间',
   dAddDate             Date comment '添加日期',
   sAddOperator         VARCHAR2(50) comment '添加人',
   dModifyDate          Date comment '修改日期',
   sModifyOperator      VARCHAR2(50) comment '修改人',
   sJpName              VARCHAR2(50) comment '简拼名',
   sPyName              VARCHAR2(100) comment '全拼名',
   iSex                 INT comment '性别',
   iPwdStatus           INT comment '密码状态
            0：原始密码，1：已修改密
            ',
   iLoginFailedTimes    INT comment '连续登录失败次数',
   iIsLocked            INT comment '是否锁定
            0：否
            1：是
            ',
   primary key (sGuid)
);

alter table sys_operator comment '管理员';
