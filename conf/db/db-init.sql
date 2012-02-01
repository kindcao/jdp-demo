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
   (1, '127.0.0.1', 'localhost', 'udp', 161, 'public', 100000, 100000, '1', '');

/*==============================================================*/
/* Table: nf_switch                                             */
/*==============================================================*/ 
   
INSERT INTO nf_switch
   (`id`, `host_id`, `if_index`, `status`, `remarks`)
VALUES
   (1, 1, '1', '1', 'interfaces#1');

INSERT INTO nf_switch
   (`id`, `host_id`, `if_index`, `status`, `remarks`)
VALUES
   (2, 1, '2', '1', 'interfaces#2');
   
   
   