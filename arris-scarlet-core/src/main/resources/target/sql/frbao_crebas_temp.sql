create table pz_CapitalRate
(
   sGuid                varchar(32) not null comment '主键',
   fLever               numeric(4,1),
   fBeginAmount         numeric(18,2),
   fEndAmount           numeric(18,2),
   fMonthRate           numeric(5,4),
   sEntryMan            varchar(50),
   dEntryDate           datetime,
   sModifyMan           varchar(50),
   dModifyDate          datetime,
   primary key (sGuid)
);