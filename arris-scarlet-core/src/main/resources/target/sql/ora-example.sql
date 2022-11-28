-- Create table
create table CS_CURRENTTRADEDATE
(
  sguid       VARCHAR2(32) not null,
  dtradedate  DATE,
  dadddate    DATE,
  bisworkdate INTEGER
)
tablespace FPAY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table CS_CURRENTTRADEDATE
  is '当前交易日期表';
-- Add comments to the columns
comment on column CS_CURRENTTRADEDATE.sguid
  is '主键';
comment on column CS_CURRENTTRADEDATE.dtradedate
  is '交易日期';
comment on column CS_CURRENTTRADEDATE.dadddate
  is '添加日期';
comment on column CS_CURRENTTRADEDATE.bisworkdate
  is '是否工作日';
-- Create/Recreate primary, unique and foreign key constraints
alter table CS_CURRENTTRADEDATE
  add constraint PK_CS_CURRENTTRADEDATE primary key (SGUID)
  using index
  tablespace FPAY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
