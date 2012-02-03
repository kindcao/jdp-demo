/*==============================================================*/
/* Delete:                                                */
/*==============================================================*/
DELETE FROM nc_log;
DELETE FROM nc_host;
/*==============================================================*/
DELETE FROM nf_log;
DELETE FROM nf_switch;
DELETE FROM nf_host;
/*==============================================================*/
DELETE FROM sr_log;
DELETE FROM sr_resource;
DELETE FROM sr_host;
/*==============================================================*/


/*==============================================================*/
/* Table: nc_host                                               */
/*==============================================================*/
INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `times`, `status`, `remarks`)
VALUES
   (1, '127.0.0.1', 'localhost', 3, '1', '');

INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `times`, `status`, `remarks`)
VALUES
   (2, 'www.baidu.com', 'baidu', 3, '1', '');

INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `times`, `status`, `remarks`)
VALUES
   (3, '129.0.0.1', 'xyz', 3, '1', '');

INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `times`, `status`, `remarks`)
VALUES
   (4, 'www.sohu.com', 'sohu', 3, '0', '');

/*==============================================================*/
/* Table: nf_host                                               */
/*==============================================================*/
INSERT INTO nf_host
   (`id`, `host_address`, `host_name`, `protocol`, `port`, `community`, `status`, `remarks`)
VALUES
   (1, '127.0.0.1', 'localhost', 'udp', 161, 'public', '1', '');

/*==============================================================*/
/* Table: nf_switch                                             */
/*==============================================================*/  
INSERT INTO nf_switch
   (`id`, `host_id`, `if_index`, `alarm_in_octets`, `alarm_out_octets`, `status`, `remarks`)
VALUES
   (1, 1, '1', 100000, 100000, '1', 'interfaces#1');

INSERT INTO nf_switch
   (`id`, `host_id`, `if_index`, `alarm_in_octets`, `alarm_out_octets`, `status`, `remarks`)
VALUES
   (2, 1, '2', 100000, 100000, '1', '');

/*==============================================================*/
/* Table: sr_host                                             */
/*==============================================================*/ 
INSERT INTO sr_host
   (`id`, `host_address`, `host_name`, `protocol`, `port`, `community`, `os_type`, `status`, `remarks`)
VALUES
   (1, '127.0.0.1', 'localhost', 'udp', 161, 'public', 'windows', '1', '');

/*==============================================================*/
/* Table: sr_resource                                           */
/*==============================================================*/
INSERT INTO sr_resource
   (`id`, `host_id`, `resource_index`, `alarm_value`, `status`, `remarks`)
VALUES
   (1, 1, '2', 10000000000, '1', 'C:\\');

INSERT INTO sr_resource
   (`id`, `host_id`, `resource_index`, `alarm_value`, `status`, `remarks`)
VALUES
   (2, 1, '12', 50000000, '1', 'Physical Memory');
