-- Create table
create table DZ_LLOUTBILLDETAIL
(
  sguid         VARCHAR2(32) not null,
  sfundcouponno VARCHAR2(50) not null,
  smerchorderno VARCHAR2(64),
  sllorderno    VARCHAR2(64),
  ssource       VARCHAR2(100),
  sduebank      VARCHAR2(100),
  saccountno    VARCHAR2(100),
  saccountname  VARCHAR2(100),
  dcreatetime   DATE,
  dtradetime    DATE,
  ftradeamount  NUMBER(18,2) not null,
  itradestatus  INTEGER,
  sremark       VARCHAR2(300),
  sfunduse      VARCHAR2(100),
  saddoperator  DATE,
  idelflag      INTEGER not null,
  supdateman    VARCHAR2(50),
  dupdatedate   DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_LLOUTBILLDETAIL
  is '连连提现账单记录表';
-- Add comments to the columns
comment on column DZ_LLOUTBILLDETAIL.sguid
  is '主键';
comment on column DZ_LLOUTBILLDETAIL.sfundcouponno
  is '序号';
comment on column DZ_LLOUTBILLDETAIL.smerchorderno
  is '商家订单号';
comment on column DZ_LLOUTBILLDETAIL.sllorderno
  is '连连订单号 ';
comment on column DZ_LLOUTBILLDETAIL.ssource
  is '来源';
comment on column DZ_LLOUTBILLDETAIL.sduebank
  is '收款银行';
comment on column DZ_LLOUTBILLDETAIL.saccountno
  is '收款银行卡';
comment on column DZ_LLOUTBILLDETAIL.saccountname
  is '收款方姓名';
comment on column DZ_LLOUTBILLDETAIL.dcreatetime
  is '创建时间';
comment on column DZ_LLOUTBILLDETAIL.dtradetime
  is '交易时间';
comment on column DZ_LLOUTBILLDETAIL.ftradeamount
  is '交易金额';
comment on column DZ_LLOUTBILLDETAIL.itradestatus
  is '交易状态';
comment on column DZ_LLOUTBILLDETAIL.sremark
  is '备注信息';
comment on column DZ_LLOUTBILLDETAIL.sfunduse
  is '资金用途';
comment on column DZ_LLOUTBILLDETAIL.saddoperator
  is '添加人';
comment on column DZ_LLOUTBILLDETAIL.idelflag
  is '记录状态
0：无效
1：有效（默认）
  ';
comment on column DZ_LLOUTBILLDETAIL.supdateman
  is '更新人';
comment on column DZ_LLOUTBILLDETAIL.dupdatedate
  is '更新日期';
-- Create/Recreate indexes
create unique index IDX_SQSFCN on DZ_LLOUTBILLDETAIL (SFUNDCOUPONNO)
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_LLOUTBILLDETAIL
  add constraint PK_DZ_LLOUTBILLDETAIL primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create table
create table DZ_CCOUTBILLDETAIL
(
  sguid           VARCHAR2(32) not null,
  sseqno          VARCHAR2(50) not null,
  dtradetime      DATE,
  smarketorderno  VARCHAR2(50),
  ssettleserialno VARCHAR2(64),
  sorgname        VARCHAR2(100),
  fsettleamount   NUMBER(18,2) not null,
  saccountname    VARCHAR2(100),
  saccountno      VARCHAR2(100),
  iaccounttype    INTEGER,
  itradetype      INTEGER,
  itradestatus    INTEGER,
  sremark         VARCHAR2(300),
  saddoperator    VARCHAR2(50),
  idelflag        INTEGER not null,
  supdateman      VARCHAR2(50),
  dupdatedate     DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_CCOUTBILLDETAIL
  is '中金提现账单记录表';
-- Add comments to the columns
comment on column DZ_CCOUTBILLDETAIL.sguid
  is '主键';
comment on column DZ_CCOUTBILLDETAIL.sseqno
  is '序号';
comment on column DZ_CCOUTBILLDETAIL.dtradetime
  is '交易时间';
comment on column DZ_CCOUTBILLDETAIL.smarketorderno
  is '市场订单号';
comment on column DZ_CCOUTBILLDETAIL.ssettleserialno
  is '结算交易流水号 ';
comment on column DZ_CCOUTBILLDETAIL.sorgname
  is '机构名称';
comment on column DZ_CCOUTBILLDETAIL.fsettleamount
  is '结算金额';
comment on column DZ_CCOUTBILLDETAIL.saccountname
  is '收款账户名称';
comment on column DZ_CCOUTBILLDETAIL.saccountno
  is '收款账户号';
comment on column DZ_CCOUTBILLDETAIL.iaccounttype
  is '账户类型 ';
comment on column DZ_CCOUTBILLDETAIL.itradetype
  is '交易类型';
comment on column DZ_CCOUTBILLDETAIL.itradestatus
  is '交易状态';
comment on column DZ_CCOUTBILLDETAIL.sremark
  is '备注信息';
comment on column DZ_CCOUTBILLDETAIL.saddoperator
  is '添加人';
comment on column DZ_CCOUTBILLDETAIL.idelflag
  is '记录状态
0：无效
1：有效（默认）
';
comment on column DZ_CCOUTBILLDETAIL.supdateman
  is '更新人';
comment on column DZ_CCOUTBILLDETAIL.dupdatedate
  is '更新日期';
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_CCOUTBILLDETAIL
  add constraint PK_DZ_CCOUTBILLDETAIL primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create table
create table DZ_OUTBILL
(
  sguid           VARCHAR2(32) not null,
  srechargebillno VARCHAR2(50) not null,
  dbilldate       DATE,
  itotalnum       INTEGER,
  ftotalamount    NUMBER(18,2) not null,
  ipaychannel     INTEGER,
  ichannelbranch  INTEGER,
  ireadrow        INTEGER,
  ibillstatus     INTEGER,
  saedesc         VARCHAR2(300),
  icheckstatus    INTEGER,
  iexceptnum      INTEGER,
  saddoperator    VARCHAR2(50),
  dadddate        DATE,
  sremark         VARCHAR2(300),
  idelflag        INTEGER,
  supdateman      VARCHAR2(50),
  dupdatedate     DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_OUTBILL
  is '提现账单记录表';
-- Add comments to the columns
comment on column DZ_OUTBILL.sguid
  is '主键';
comment on column DZ_OUTBILL.srechargebillno
  is '账单编号';
comment on column DZ_OUTBILL.dbilldate
  is '账单日';
comment on column DZ_OUTBILL.itotalnum
  is '记录总数';
comment on column DZ_OUTBILL.ftotalamount
  is '总金额';
comment on column DZ_OUTBILL.ipaychannel
  is '支付通道
1:中金支付（默认值）
2：连连支付
';
comment on column DZ_OUTBILL.ichannelbranch
  is '通道分支编号（如快捷支付，网银支付等）
11：中金银行直连
12：中金快捷
21：连连银行直连
22：连连快捷
';
comment on column DZ_OUTBILL.ireadrow
  is '当前读取行';
comment on column DZ_OUTBILL.ibillstatus
  is '账单状态
0：解析失败
1：解析成功
';
comment on column DZ_OUTBILL.saedesc
  is '解析异常描述';
comment on column DZ_OUTBILL.icheckstatus
  is '对账状态
0：未对账
1：核对账目无异常
2：核对账目异常
3：核对异常失败
';
comment on column DZ_OUTBILL.iexceptnum
  is '异常记录数';
comment on column DZ_OUTBILL.saddoperator
  is '添加人';
comment on column DZ_OUTBILL.dadddate
  is '添加日期';
comment on column DZ_OUTBILL.sremark
  is '备注';
comment on column DZ_OUTBILL.idelflag
  is '记录状态
0：无效
1：有效（默认）
';
comment on column DZ_OUTBILL.supdateman
  is '更新人';
comment on column DZ_OUTBILL.dupdatedate
  is '更新日期';
-- Create/Recreate indexes
create unique index IDX_SEQSRCB on DZ_OUTBILL (SRECHARGEBILLNO)
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_OUTBILL
  add constraint PK_DZ_OUTBILL primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create table
create table DZ_LLBILLRECORD
(
  sguid           VARCHAR2(32) not null,
  srechargebillid VARCHAR2(32) not null,
  srechargebillno VARCHAR2(50) not null,
  smerchorderno   VARCHAR2(64),
  sllorderno      VARCHAR2(64),
  dcreatetime     DATE,
  dsuccesstime    DATE,
  ftradeamount    NUMBER(18,2) not null,
  frefundamount   NUMBER(18,2) not null,
  itradestatus    INTEGER,
  scommodityname  VARCHAR2(100),
  sremark         VARCHAR2(300),
  idelflag        INTEGER not null,
  supdateman      VARCHAR2(50),
  dupdatedate     DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_LLBILLRECORD
  is '连连快捷充值账单记录表';
-- Add comments to the columns
comment on column DZ_LLBILLRECORD.sguid
  is '主键';
comment on column DZ_LLBILLRECORD.srechargebillid
  is '账单ID';
comment on column DZ_LLBILLRECORD.srechargebillno
  is '账单编号';
comment on column DZ_LLBILLRECORD.smerchorderno
  is '商家订单号';
comment on column DZ_LLBILLRECORD.sllorderno
  is '银通订单号 ';
comment on column DZ_LLBILLRECORD.dcreatetime
  is '创建时间';
comment on column DZ_LLBILLRECORD.dsuccesstime
  is '成功时间';
comment on column DZ_LLBILLRECORD.ftradeamount
  is '交易金额';
comment on column DZ_LLBILLRECORD.frefundamount
  is '退款金额';
comment on column DZ_LLBILLRECORD.itradestatus
  is '交易状态';
comment on column DZ_LLBILLRECORD.scommodityname
  is '商品名称';
comment on column DZ_LLBILLRECORD.sremark
  is '备注';
comment on column DZ_LLBILLRECORD.idelflag
  is '记录状态
0：无效
1：有效（默认）
';
comment on column DZ_LLBILLRECORD.supdateman
  is '更新人';
comment on column DZ_LLBILLRECORD.dupdatedate
  is '更新日期';
-- Create/Recreate indexes
create unique index IDX_SQSCG on DZ_LLBILLRECORD (SRECHARGEBILLID)
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
create unique index IDX_SQ_SRB on DZ_LLBILLRECORD (SRECHARGEBILLNO)
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_LLBILLRECORD
  add constraint PK_DZ_LLBILLRECORD primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create table
create table DZ_CCQUICKBILLRECORD
(
  sguid           VARCHAR2(32) not null,
  srechargebillid VARCHAR2(32) not null,
  srechargebillno VARCHAR2(50) not null,
  iseq            INTEGER,
  dtradetime      DATE,
  dpaysucctime    DATE,
  smarketorderno  VARCHAR2(50),
  stsserialno     VARCHAR2(64),
  sbindserialno   VARCHAR2(64),
  sorgname        VARCHAR2(100),
  fpayamount      NUMBER(18,2) not null,
  frefundamount   NUMBER(18,2) not null,
  itradestatus    INTEGER,
  itradetype      INTEGER,
  sremark         VARCHAR2(300),
  idelflag        INTEGER not null,
  supdateman      VARCHAR2(50),
  dupdatedate     DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_CCQUICKBILLRECORD
  is '中金快捷充值账单记录表';
-- Add comments to the columns
comment on column DZ_CCQUICKBILLRECORD.sguid
  is '主键';
comment on column DZ_CCQUICKBILLRECORD.srechargebillid
  is '账单ID';
comment on column DZ_CCQUICKBILLRECORD.srechargebillno
  is '账单编号';
comment on column DZ_CCQUICKBILLRECORD.iseq
  is '序号';
comment on column DZ_CCQUICKBILLRECORD.dtradetime
  is '交易时间';
comment on column DZ_CCQUICKBILLRECORD.dpaysucctime
  is '支付成功时间';
comment on column DZ_CCQUICKBILLRECORD.smarketorderno
  is '市场订单号';
comment on column DZ_CCQUICKBILLRECORD.stsserialno
  is '支付交易流水号';
comment on column DZ_CCQUICKBILLRECORD.sbindserialno
  is '绑定流水号';
comment on column DZ_CCQUICKBILLRECORD.sorgname
  is '机构名称 ';
comment on column DZ_CCQUICKBILLRECORD.fpayamount
  is '支付金额';
comment on column DZ_CCQUICKBILLRECORD.frefundamount
  is '可退款余额';
comment on column DZ_CCQUICKBILLRECORD.itradestatus
  is '交易状态';
comment on column DZ_CCQUICKBILLRECORD.itradetype
  is '交易类型';
comment on column DZ_CCQUICKBILLRECORD.sremark
  is '备注 ';
comment on column DZ_CCQUICKBILLRECORD.idelflag
  is '记录状态
0：无效
1：有效（默认）
';
comment on column DZ_CCQUICKBILLRECORD.supdateman
  is '更新人';
comment on column DZ_CCQUICKBILLRECORD.dupdatedate
  is '更新日期';
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_CCQUICKBILLRECORD
  add constraint PK_DZ_CCQUICKBILLRECORD primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create table
create table DZ_CCDIRECTBILLRECORD
(
  sguid           VARCHAR2(32) not null,
  srechargebillid VARCHAR2(32) not null,
  srechargebillno VARCHAR2(50) not null,
  iseq            INTEGER,
  dtradetime      DATE,
  smarketorderno  VARCHAR2(50),
  stsserialno     VARCHAR2(64),
  sorgname        VARCHAR2(100),
  ftradeamount    NUMBER(18,2) not null,
  sfunduse        VARCHAR2(100),
  itradestatus    INTEGER,
  spayerid        VARCHAR2(64),
  spayername      VARCHAR2(100),
  dnotifytime     DATE,
  sremark         VARCHAR2(300),
  ibankordertype  INTEGER,
  icardtype       INTEGER,
  idelflag        INTEGER not null,
  supdateman      VARCHAR2(50),
  dupdatedate     DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_CCDIRECTBILLRECORD
  is '中金直连充值账单记录表';
-- Add comments to the columns
comment on column DZ_CCDIRECTBILLRECORD.sguid
  is '主键';
comment on column DZ_CCDIRECTBILLRECORD.srechargebillid
  is '账单ID';
comment on column DZ_CCDIRECTBILLRECORD.srechargebillno
  is '账单编号';
comment on column DZ_CCDIRECTBILLRECORD.iseq
  is '序号';
comment on column DZ_CCDIRECTBILLRECORD.dtradetime
  is '交易时间';
comment on column DZ_CCDIRECTBILLRECORD.smarketorderno
  is '市场订单号';
comment on column DZ_CCDIRECTBILLRECORD.stsserialno
  is '支付交易流水号';
comment on column DZ_CCDIRECTBILLRECORD.sorgname
  is '机构名称';
comment on column DZ_CCDIRECTBILLRECORD.ftradeamount
  is '支付金额';
comment on column DZ_CCDIRECTBILLRECORD.sfunduse
  is '资金用途';
comment on column DZ_CCDIRECTBILLRECORD.itradestatus
  is '交易状态';
comment on column DZ_CCDIRECTBILLRECORD.spayerid
  is '付款人ID';
comment on column DZ_CCDIRECTBILLRECORD.spayername
  is '付款人名称';
comment on column DZ_CCDIRECTBILLRECORD.dnotifytime
  is '银行通知时间';
comment on column DZ_CCDIRECTBILLRECORD.sremark
  is '备注';
comment on column DZ_CCDIRECTBILLRECORD.ibankordertype
  is '银行订单类型';
comment on column DZ_CCDIRECTBILLRECORD.icardtype
  is '卡类型';
comment on column DZ_CCDIRECTBILLRECORD.idelflag
  is '记录状态
0：无效
1：有效（默认）
';
comment on column DZ_CCDIRECTBILLRECORD.supdateman
  is '更新人';
comment on column DZ_CCDIRECTBILLRECORD.dupdatedate
  is '更新日期';
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_CCDIRECTBILLRECORD
  add constraint PK_DZ_CCDIRECTBILLRECORD primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create table
create table DZ_RECHARGEBILL
(
  sguid           VARCHAR2(32) not null,
  srechargebillno VARCHAR2(50) not null,
  dbilldate       DATE,
  itotalnum       INTEGER,
  ftotalamount    NUMBER(18,2) not null,
  ipaychannel     INTEGER default 1,
  ichannelbranch  INTEGER,
  ireadrow        INTEGER,
  ibillstatus     INTEGER,
  saedesc         VARCHAR2(300),
  icheckstatus    INTEGER,
  iexceptnum      INTEGER,
  saddoperator    VARCHAR2(50),
  dadddate        DATE,
  sremark         VARCHAR2(300),
  idelflag        INTEGER not null,
  supdateman      VARCHAR2(50),
  dupdatedate     DATE
)
tablespace TBS_MAIYAXD
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DZ_RECHARGEBILL
  is '充值账单表';
-- Add comments to the columns
comment on column DZ_RECHARGEBILL.sguid
  is '主键';
comment on column DZ_RECHARGEBILL.srechargebillno
  is '账单编号';
comment on column DZ_RECHARGEBILL.dbilldate
  is '账单日 ';
comment on column DZ_RECHARGEBILL.itotalnum
  is '记录总数';
comment on column DZ_RECHARGEBILL.ftotalamount
  is '总金额';
comment on column DZ_RECHARGEBILL.ipaychannel
  is '支付通道
1:中金支付（默认值）
2：连连支付
';
comment on column DZ_RECHARGEBILL.ichannelbranch
  is '通道分支编号（如快捷支付，网银支付等）
11：中金银行直连
12：中金快捷
21：连连银行直连
22：连连快捷
';
comment on column DZ_RECHARGEBILL.ireadrow
  is '当前读取行';
comment on column DZ_RECHARGEBILL.ibillstatus
  is '账单解析状态
0：解析失败
1：解析成功
';
comment on column DZ_RECHARGEBILL.saedesc
  is '解析异常描述';
comment on column DZ_RECHARGEBILL.icheckstatus
  is '对账状态
0：未对账
1：核对账目无异常
2：核对账目异常
3：核对异常失败
';
comment on column DZ_RECHARGEBILL.iexceptnum
  is '异常记录数';
comment on column DZ_RECHARGEBILL.saddoperator
  is '添加人';
comment on column DZ_RECHARGEBILL.dadddate
  is '添加日期';
comment on column DZ_RECHARGEBILL.sremark
  is '备注';
comment on column DZ_RECHARGEBILL.idelflag
  is '记录状态
0：无效
1：有效（默认）
';
comment on column DZ_RECHARGEBILL.supdateman
  is '更新人';
comment on column DZ_RECHARGEBILL.dupdatedate
  is '更新日期';
-- Create/Recreate indexes
create unique index IDX_SQSRB on DZ_RECHARGEBILL (SRECHARGEBILLNO)
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
-- Create/Recreate primary, unique and foreign key constraints
alter table DZ_RECHARGEBILL
  add constraint PK_DZ_RECHARGEBILL primary key (SGUID)
  using index
  tablespace TBS_MAIYAXD
  pctfree 10
  initrans 2
  maxtrans 255;
