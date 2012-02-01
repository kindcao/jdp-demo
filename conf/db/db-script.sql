/*
MySQL Data Transfer
Source Host: 127.0.0.1
Source Database: test
Target Host: 127.0.0.1
Target Database: test
Date: 2010-12-5 10:09:28
*/

SET FOREIGN_KEY_CHECKS=0;

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012-1-27 14:48:48                           */
/*==============================================================*/


drop table if exists nc_log;

drop table if exists nc_host;

/*==============================================================*/
drop table if exists nf_log;

drop table if exists nf_switch;

drop table if exists nf_host;


/*==============================================================*/
/* Table: nc_host                                               */
/*==============================================================*/
create table nc_host
(
   id                   int not null auto_increment,
   host_address         varchar(30),
   host_name            varchar(50),
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
   protocol             varbinary(20),
   port                 int,
   community            varchar(50),
   in_octets            numeric,
   out_octets           numeric,
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
   in_octets            numeric,
   out_octets           numeric,
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
   status               char,
   remarks              varchar(50),
   primary key (id)
);


/*==============================================================*/
/* references:                                                  */
/*==============================================================*/

alter table nc_log add constraint fk_nc_log_ref_host foreign key (host_id)
      references nc_host (id) on delete restrict on update restrict;

alter table nf_log add constraint fk_nf_log_ref_switch foreign key (switch_id)
      references nf_switch (id) on delete restrict on update restrict;

alter table nf_switch add constraint fk_nf_switch_ref_host foreign key (host_id)
      references nf_host (id) on delete restrict on update restrict;
   
   