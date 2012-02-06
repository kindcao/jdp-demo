/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012-2-3 12:08:10                            */
/*==============================================================*/

drop table if exists nc_log;
drop table if exists nc_host;

drop table if exists nf_log;
drop table if exists nf_switch;
drop table if exists nf_host;

drop table if exists sr_log;
drop table if exists sr_resource;
drop table if exists sr_host;

/*==============================================================*/
/* Table: nc_host                                               */
/*==============================================================*/
create table nc_host
(
   id                   int not null auto_increment,
   host_address         varchar(30),
   host_name            varchar(50),
   times                int,
   status               char,
   remarks              varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: nc_log                                                */
/*==============================================================*/
create table nc_log
(
   id                   int not null auto_increment,
   host_id              int not null,
   occurrence_time      timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: nf_host                                               */
/*==============================================================*/
create table nf_host
(
   id                   int not null auto_increment,
   host_address         varchar(30),
   host_name            varchar(50),
   protocol             varchar(20),
   port                 int,
   community            varchar(50),
   status               char,
   remarks              varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: nf_log                                                */
/*==============================================================*/
create table nf_log
(
   id                   int not null auto_increment,
   switch_id            int,
   in_octets            bigint,
   out_octets           bigint,
   occurrence_time      timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: nf_switch                                             */
/*==============================================================*/
create table nf_switch
(
   id                   int not null auto_increment,
   host_id              int,
   if_index             varchar(30),
   alarm_in_octets      bigint,
   alarm_out_octets     bigint,
   status               char,
   remarks              varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: sr_host                                               */
/*==============================================================*/
create table sr_host
(
   id                   int not null auto_increment,
   host_address         varchar(30),
   host_name            varchar(50),
   protocol             varchar(20),
   port                 int,
   community            varchar(50),
   os_type              varchar(20),
   status               char,
   remarks              varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: sr_log                                                */
/*==============================================================*/
create table sr_log
(
   id                   int not null auto_increment,
   resource_id          int,
   actual_value         numeric(20),
   occurrence_time      timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: sr_resource                                           */
/*==============================================================*/
create table sr_resource
(
   id                   int not null auto_increment,
   host_id              int,
   resource_index       varchar(30),
   alarm_value          numeric(20),
   status               char,
   remarks              varchar(50),
   primary key (id)
);

alter table nc_log add constraint fk_nc_log_ref_host foreign key (host_id)
      references nc_host (id) on delete restrict on update restrict;

alter table nf_log add constraint fk_nf_log_ref_switch foreign key (switch_id)
      references nf_switch (id) on delete restrict on update restrict;

alter table nf_switch add constraint fk_nf_switch_ref_host foreign key (host_id)
      references nf_host (id) on delete restrict on update restrict;

alter table sr_log add constraint fk_sr_log_ref_resource foreign key (resource_id)
      references sr_resource (id) on delete restrict on update restrict;

alter table sr_resource add constraint fk_sr_resource_ref_host foreign key (host_id)
      references sr_host (id) on delete restrict on update restrict;