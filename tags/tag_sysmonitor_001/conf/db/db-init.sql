/*==============================================================*/
/* Table: nc_host                                               */
/*==============================================================*/
INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `status`, `remarks`)
VALUES
   (1, '127.0.0.1', 'localhost', 'N', '');

INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `status`, `remarks`)
VALUES
   (2, 'www.baidu.com', 'baidu', '1', '');

INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `status`, `remarks`)
VALUES
   (3, '129.0.0.1', 'xyz', '1', '');
   
INSERT INTO nc_host
   (`id`, `host_address`, `host_name`, `status`, `remarks`)
VALUES
   (4, 'www.sohu.com', 'sohu', '0', '');


/*==============================================================*/
/* Table: nf_host                                               */
/*==============================================================*/
INSERT INTO nf_host
   (`id`, `host_address`, `host_name`, `protocol`, `port`, `community`, `in_octets`, `out_octets`, `status`, `remarks`)
VALUES
   (1, '127.0.0.1', 'localhost', 'udp', 161, 'public', 1024, 1024, '1', '');
   
/*==============================================================*/
/* Table: nd_oid                                               */
/*==============================================================*/
INSERT INTO nd_oid
   (`id`, `oid`, `status`, `remarks`)
VALUES
   (1, '1.3.6.1.2.1.2.2.1.10.1', '1', 'ifInOctets#1');

INSERT INTO nd_oid
   (`id`, `oid`, `status`, `remarks`)
VALUES
   (2, '1.3.6.1.2.1.2.2.1.16.1', '1', 'ifOutOctets#1');

/*==============================================================*/
/* Table: nf_host_oid_ref                                       */
/*==============================================================*/
INSERT INTO nf_host_oid_ref
   (`id`, `host_id`, `oid_id`)
VALUES
   (1, 1, 1);

INSERT INTO nf_host_oid_ref
   (`id`, `host_id`, `oid_id`)
VALUES
   (2, 1, 2);

   