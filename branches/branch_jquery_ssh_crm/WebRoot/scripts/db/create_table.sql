/*
MySQL Data Transfer
Source Host: 192.168.1.111
Source Database: test
Target Host: 192.168.1.111
Target Database: test
Date: 2011-6-10 10:34:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for customer
-- ----------------------------
CREATE TABLE `customer` (
  `id` int(11) NOT NULL auto_increment,
  `cust_name` varchar(50) collate utf8_unicode_ci NOT NULL,
  `short_name` varchar(50) collate utf8_unicode_ci NOT NULL,
  `cust_code` varchar(20) collate utf8_unicode_ci default NULL,
  `industry_id` int(11) NOT NULL,
  `phone` varchar(40) collate utf8_unicode_ci NOT NULL,
  `fax` varchar(40) collate utf8_unicode_ci default NULL,
  `website` varchar(50) collate utf8_unicode_ci default NULL,
  `country` varchar(20) collate utf8_unicode_ci default NULL,
  `province` varchar(20) collate utf8_unicode_ci default NULL,
  `city` varchar(20) collate utf8_unicode_ci default NULL,
  `address` varchar(200) collate utf8_unicode_ci default NULL,
  `postcode` varchar(10) collate utf8_unicode_ci default NULL,
  `descript` varchar(500) collate utf8_unicode_ci default NULL,
  `remark` varchar(500) collate utf8_unicode_ci default NULL,
  `email` varchar(40) collate utf8_unicode_ci default NULL,
  `created_by` int(11) NOT NULL,
  `created_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_updated_by` int(11) default NULL,
  `last_updated_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `delete_flag` char(1) collate utf8_unicode_ci NOT NULL,
  `deleted_by` int(11) default NULL,
  `deleted_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `FK_CUSTOMER_REF_INDUSTRY` (`industry_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for customer_contact
-- ----------------------------
CREATE TABLE `customer_contact` (
  `id` int(11) NOT NULL auto_increment,
  `customer_id` int(11) NOT NULL,
  `name` varchar(40) collate utf8_unicode_ci NOT NULL,
  `department` varchar(40) collate utf8_unicode_ci default NULL,
  `posit` varchar(40) collate utf8_unicode_ci default NULL,
  `phone` varchar(40) collate utf8_unicode_ci default NULL,
  `fax` varchar(40) collate utf8_unicode_ci default NULL,
  `mobile` varchar(40) collate utf8_unicode_ci default NULL,
  `email` varchar(50) collate utf8_unicode_ci default NULL,
  `address` varchar(200) collate utf8_unicode_ci default NULL,
  `is_primary` char(1) collate utf8_unicode_ci default NULL,
  `remark` varchar(500) collate utf8_unicode_ci default NULL,
  `im` varchar(50) collate utf8_unicode_ci default NULL,
  `picture` varchar(100) collate utf8_unicode_ci default NULL,
  `created_by` int(11) NOT NULL,
  `created_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_updated_by` int(11) default NULL,
  `last_updated_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `delete_flag` char(1) collate utf8_unicode_ci NOT NULL,
  `deleted_by` int(11) default NULL,
  `deleted_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `FK_CUSTOMER_CONTACT_REF_CUSTOMER` (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for customer_industry
-- ----------------------------
CREATE TABLE `customer_industry` (
  `id` int(11) NOT NULL,
  `name` varchar(20) collate utf8_unicode_ci NOT NULL,
  `superior_id` int(11) default NULL,
  `level` int(11) NOT NULL,
  `descript` varchar(60) collate utf8_unicode_ci default NULL,
  `disp_order` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for customer_sys_company_rel
-- ----------------------------
CREATE TABLE `customer_sys_company_rel` (
  `customer_id` int(11) NOT NULL,
  `sys_company_id` int(11) NOT NULL,
  PRIMARY KEY  (`customer_id`,`sys_company_id`),
  KEY `FK_CUST_COMP_REL_REF_SYS_COMPANY` (`sys_company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for customer_sys_user_rel
-- ----------------------------
CREATE TABLE `customer_sys_user_rel` (
  `customer_id` int(11) NOT NULL,
  `sys_company_user_id` int(11) NOT NULL,
  `is_primary` char(1) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`customer_id`,`sys_company_user_id`),
  KEY `FK_CUST_USER_REL_REF_SYS_USER` (`sys_company_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for industry_news_type
-- ----------------------------
CREATE TABLE `industry_news_type` (
  `id` int(11) NOT NULL,
  `name` varchar(20) collate utf8_unicode_ci NOT NULL,
  `descript` varchar(60) collate utf8_unicode_ci default NULL,
  `disp_order` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for market_event
-- ----------------------------
CREATE TABLE `market_event` (
  `id` int(11) NOT NULL auto_increment,
  `occur_date` int(11) NOT NULL,
  `begin_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL,
  `market_event_type_id` int(11) NOT NULL,
  `situation` varchar(6000) collate utf8_unicode_ci NOT NULL,
  `goods` varchar(1000) collate utf8_unicode_ci default NULL,
  `remark` varchar(2000) collate utf8_unicode_ci default NULL,
  `train_scale` varchar(200) collate utf8_unicode_ci default NULL,
  `subject` varchar(200) collate utf8_unicode_ci default NULL,
  `status` char(1) collate utf8_unicode_ci NOT NULL default 'N',
  PRIMARY KEY  (`id`),
  KEY `FK_MARKET_EVENT_REF_TYPE` (`market_event_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for market_event_company_rel
-- ----------------------------
CREATE TABLE `market_event_company_rel` (
  `market_event_id` int(11) NOT NULL,
  `sys_company_id` int(11) NOT NULL,
  PRIMARY KEY  (`market_event_id`,`sys_company_id`),
  KEY `FK_MARKET_EVENT_COMP_REL_REF_COMPANY` (`sys_company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for market_event_contact_rel
-- ----------------------------
CREATE TABLE `market_event_contact_rel` (
  `market_event_id` int(11) NOT NULL,
  `customer_contact_id` int(11) NOT NULL,
  PRIMARY KEY  (`market_event_id`,`customer_contact_id`),
  KEY `FK_MARKET_EVENT_CONTA_REL_REF_CONTACT` (`customer_contact_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for market_event_customer_rel
-- ----------------------------
CREATE TABLE `market_event_customer_rel` (
  `market_event_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY  (`market_event_id`,`customer_id`),
  KEY `FK_MARKET_EVENT_CUST_REL_REF_CUSTOMER` (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for market_event_sys_user_rel
-- ----------------------------
CREATE TABLE `market_event_sys_user_rel` (
  `market_event_id` int(11) NOT NULL,
  `sys_company_user_id` int(11) NOT NULL,
  `is_primary` char(1) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`market_event_id`,`sys_company_user_id`),
  KEY `FK_MARKET_EVENT_USER_REL_REF_USER` (`sys_company_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for market_event_type
-- ----------------------------
CREATE TABLE `market_event_type` (
  `id` int(11) NOT NULL,
  `name` varchar(20) collate utf8_unicode_ci NOT NULL,
  `superior_id` int(11) default NULL,
  `level` int(11) NOT NULL,
  `descript` varchar(100) collate utf8_unicode_ci default NULL,
  `disp_order` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for monitor_industry
-- ----------------------------
CREATE TABLE `monitor_industry` (
  `id` int(11) NOT NULL auto_increment,
  `publish_date` int(11) NOT NULL,
  `industry_news_type_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `url` varchar(200) collate utf8_unicode_ci default NULL,
  `subject` varchar(400) collate utf8_unicode_ci NOT NULL,
  `content` varchar(6000) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_MONITOR_INDUSTRY_REF_CUSTOMER` (`customer_id`),
  KEY `FK_MONITOR_INDUSTRY_REF_NEWS_TYPE` (`industry_news_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for monitor_news
-- ----------------------------
CREATE TABLE `monitor_news` (
  `id` int(11) NOT NULL auto_increment,
  `publish_date` int(11) NOT NULL,
  `media` varchar(200) collate utf8_unicode_ci NOT NULL,
  `interview_date` int(11) default NULL,
  `participant` varchar(200) collate utf8_unicode_ci default NULL,
  `reporter` varchar(200) collate utf8_unicode_ci default NULL,
  `url` varchar(200) collate utf8_unicode_ci default NULL,
  `subject` varchar(400) collate utf8_unicode_ci default NULL,
  `content` varchar(6000) collate utf8_unicode_ci default NULL,
  `remark` varchar(2000) collate utf8_unicode_ci default NULL,
  `picture` varchar(100) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for monitor_publish
-- ----------------------------
CREATE TABLE `monitor_publish` (
  `id` int(11) NOT NULL auto_increment,
  `publish_date` int(11) NOT NULL,
  `publish_time` int(11) NOT NULL,
  `subject` varchar(400) collate utf8_unicode_ci NOT NULL,
  `website` varchar(200) collate utf8_unicode_ci NOT NULL,
  `url` varchar(200) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
CREATE TABLE `sys_company` (
  `id` int(11) NOT NULL auto_increment,
  `company_name` varchar(50) collate utf8_unicode_ci NOT NULL,
  `descript` varchar(100) collate utf8_unicode_ci default NULL,
  `status` char(1) collate utf8_unicode_ci NOT NULL,
  `logo` varchar(100) collate utf8_unicode_ci NOT NULL,
  `type` char(1) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sys_company_role
-- ----------------------------
CREATE TABLE `sys_company_role` (
  `id` int(11) NOT NULL auto_increment,
  `sys_company_id` int(11) NOT NULL,
  `role_name` varchar(20) collate utf8_unicode_ci NOT NULL,
  `descript` varchar(100) collate utf8_unicode_ci default NULL,
  `disp_order` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_ROLE_REF_COMPANY` (`sys_company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sys_company_role_permission
-- ----------------------------
CREATE TABLE `sys_company_role_permission` (
  `id` int(11) NOT NULL auto_increment,
  `sys_company_role_id` int(11) NOT NULL,
  `service` varchar(25) collate utf8_unicode_ci NOT NULL,
  `method` varchar(25) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_PERMISSION_REF_COMPANY_ROLE` (`sys_company_role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sys_company_user
-- ----------------------------
CREATE TABLE `sys_company_user` (
  `id` int(11) NOT NULL auto_increment,
  `sys_company_id` int(11) NOT NULL,
  `name` varchar(40) collate utf8_unicode_ci NOT NULL,
  `login_id` varchar(20) collate utf8_unicode_ci NOT NULL,
  `passwd` varchar(50) collate utf8_unicode_ci NOT NULL,
  `superior_id` int(11) default NULL,
  `email` varchar(50) collate utf8_unicode_ci default NULL,
  `status` char(1) collate utf8_unicode_ci NOT NULL,
  `delete_flag` char(1) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_USER_REF_COMPANY` (`sys_company_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sys_company_user_rel
-- ----------------------------
CREATE TABLE `sys_company_user_rel` (
  `parent_user_id` int(11) NOT NULL,
  `child_user_id` int(11) NOT NULL,
  PRIMARY KEY  (`parent_user_id`,`child_user_id`),
  KEY `FK_CHILD_USER_REL_REF_USER` (`child_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for sys_company_user_role
-- ----------------------------
CREATE TABLE `sys_company_user_role` (
  `sys_company_user_id` int(11) NOT NULL,
  `sys_company_role_id` int(11) NOT NULL,
  PRIMARY KEY  (`sys_company_user_id`,`sys_company_role_id`),
  KEY `FK_USER_ROLE_REF_ROLE` (`sys_company_role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- View structure for customer_contact_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_contact_view` AS select `cc`.`id` AS `id`,`cc`.`customer_id` AS `customer_id`,`cc`.`name` AS `name`,`cc`.`department` AS `department`,`cc`.`posit` AS `posit`,`cc`.`phone` AS `phone`,`cc`.`fax` AS `fax`,`cc`.`mobile` AS `mobile`,`cc`.`email` AS `email`,`cc`.`address` AS `address`,`cc`.`is_primary` AS `is_primary`,`cc`.`remark` AS `remark`,`cc`.`im` AS `im`,`cc`.`picture` AS `picture`,`cc`.`created_by` AS `created_by`,`cc`.`created_time` AS `created_time`,`cc`.`last_updated_by` AS `last_updated_by`,`cc`.`last_updated_time` AS `last_updated_time`,`cc`.`delete_flag` AS `delete_flag`,`cc`.`deleted_by` AS `deleted_by`,`cc`.`deleted_time` AS `deleted_time` from `customer_contact` `cc` where (`cc`.`delete_flag` = _utf8'N');

-- ----------------------------
-- View structure for customer_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view` AS select `cvs`.`id` AS `id`,`cvs`.`cust_name` AS `cust_name`,`cvs`.`cust_code` AS `cust_code`,`cvs`.`phone` AS `phone`,`cvs`.`address` AS `address`,`cvs`.`superior_industry_id` AS `superior_industry_id`,`cvs`.`industry_id` AS `industry_id`,`cvs`.`industry_name` AS `industry_name`,`cvs`.`sys_company_id` AS `sys_company_id`,`cvs`.`company_name` AS `company_name`,`cvc`.`customer_id` AS `customer_id`,`cvc`.`contact_id` AS `contact_id`,`cvc`.`contact_name` AS `contact_name`,`cvc`.`is_primary_contact` AS `is_primary_contact` from (`customer_view_sub` `cvs` left join `customer_view_cont` `cvc` on((`cvs`.`id` = `cvc`.`customer_id`))) order by `cvs`.`id` desc;

-- ----------------------------
-- View structure for customer_view_cont
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view_cont` AS select `cc`.`customer_id` AS `customer_id`,group_concat(distinct cast(`cc`.`id` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `contact_id`,group_concat(distinct cast(`cc`.`name` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `contact_name`,group_concat(distinct cast(`cc`.`is_primary` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `is_primary_contact` from (`customer_view_sub` `t` join `customer_contact_view` `cc` on((`t`.`id` = `cc`.`customer_id`))) group by `t`.`id`;

-- ----------------------------
-- View structure for customer_view_sub
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view_sub` AS select `cust`.`id` AS `id`,`cust`.`cust_name` AS `cust_name`,`cust`.`cust_code` AS `cust_code`,`cust`.`phone` AS `phone`,concat_ws(`cust`.`country`,`cust`.`province`,`cust`.`city`,`cust`.`address`) AS `address`,`ci`.`superior_id` AS `superior_industry_id`,`cust`.`industry_id` AS `industry_id`,`ci`.`name` AS `industry_name`,group_concat(distinct cast(`sc`.`id` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `sys_company_id`,group_concat(distinct cast(`sc`.`company_name` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `company_name` from (((`customer` `cust` join `customer_industry` `ci`) join `customer_sys_company_rel` `cscr`) join `sys_company` `sc`) where ((`cust`.`delete_flag` = _utf8'N') and (`cust`.`industry_id` = `ci`.`id`) and (`cust`.`id` = `cscr`.`customer_id`) and (`cscr`.`sys_company_id` = `sc`.`id`)) group by `cust`.`id`;

-- ----------------------------
-- View structure for market_event_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view` AS select `sub`.`id` AS `id`,`sub`.`occur_date` AS `occur_date`,`sub`.`occur_date_str` AS `occur_date_str`,`sub`.`begin_time` AS `begin_time`,`sub`.`begin_time_str` AS `begin_time_str`,`sub`.`end_time` AS `end_time`,`sub`.`end_time_str` AS `end_time_str`,`sub`.`subject` AS `subject`,`sub`.`status` AS `status`,`sub`.`mktevt_superior_id` AS `mktevt_superior_id`,`sub`.`mktevt_superior_name` AS `mktevt_superior_name`,`sub`.`mktevt_id` AS `mktevt_id`,`sub`.`mktevt_name` AS `mktevt_name`,`sysuser`.`sys_comp_user_id` AS `sys_comp_user_id`,`sysuser`.`sys_comp_user_name` AS `sys_comp_user_name`,`comp`.`comp_id` AS `comp_id`,`comp`.`comp_name` AS `comp_name`,`cust`.`cust_id` AS `cust_id`,`cust`.`cust_name` AS `cust_name`,concat_ws(_utf8' / ',`comp`.`comp_name`,`cust`.`cust_name`) AS `comp_cust_name` from (((`market_event_view_sub` `sub` join `market_event_view_sysuser` `sysuser` on((`sub`.`id` = `sysuser`.`id`))) left join `market_event_view_comp` `comp` on((`sub`.`id` = `comp`.`id`))) left join `market_event_view_cust` `cust` on((`sub`.`id` = `cust`.`id`))) order by `sub`.`id` desc;

-- ----------------------------
-- View structure for market_event_view_cal
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_cal` AS select `mev`.`id` AS `id`,`mev`.`occur_date` AS `occur_date`,`mev`.`occur_date_str` AS `occur_date_str`,`mev`.`status` AS `status`,`mev`.`mktevt_superior_id` AS `mktevt_superior_id`,`mev`.`mktevt_superior_name` AS `mktevt_superior_name`,`mev`.`comp_id` AS `comp_id`,`mev`.`cust_id` AS `cust_id`,cast((case when (`mev`.`mktevt_superior_id` = 3) then `mev`.`subject` else `mev`.`comp_cust_name` end) as char charset utf8) AS `comp_cust_name` from `market_event_view` `mev` order by `mev`.`mktevt_superior_id`;

-- ----------------------------
-- View structure for market_event_view_comp
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_comp` AS select `me`.`id` AS `id`,group_concat(distinct cast(`sc`.`id` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `comp_id`,group_concat(distinct cast(`sc`.`company_name` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `comp_name` from ((`market_event` `me` join `market_event_company_rel` `mecr`) join `sys_company` `sc`) where ((`me`.`id` = `mecr`.`market_event_id`) and (`mecr`.`sys_company_id` = `sc`.`id`)) group by `me`.`id`;

-- ----------------------------
-- View structure for market_event_view_count
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_count` AS select sql_no_cache `test`.`rno`() AS `id`,`sub`.`industry_superior_id` AS `industry_superior_id`,`sub`.`cust_id` AS `cust_id`,`sub`.`cust_name` AS `cust_name`,`sub`.`mktevt_superior_id` AS `mktevt_superior_id`,`sub`.`mktevt_superior_name` AS `mktevt_superior_name`,count(0) AS `num`,`sub`.`occur_date` AS `occur_date`,`sub`.`sys_company_id` AS `sys_company_id` from `market_event_view_count_sub` `sub` where (`test`.`rno_reset`() = 1) group by `sub`.`industry_superior_id`,`sub`.`cust_id`,`sub`.`mktevt_superior_id`,`sub`.`occur_date`;

-- ----------------------------
-- View structure for market_event_view_count_sub
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_count_sub` AS select `a`.`id` AS `industry_superior_id`,`a`.`name` AS `industry_superior_name`,`cust`.`id` AS `cust_id`,`cust`.`cust_name` AS `cust_name`,`cscr`.`sys_company_id` AS `sys_company_id`,`mecr`.`market_event_id` AS `market_event_id`,`me`.`occur_date` AS `occur_date`,`n`.`id` AS `mktevt_superior_id`,`n`.`name` AS `mktevt_superior_name` from (((((((`customer_industry` `a` join `customer_industry` `b`) join `customer` `cust`) join `customer_sys_company_rel` `cscr`) join `market_event_customer_rel` `mecr`) join `market_event` `me`) join `market_event_type` `m`) join `market_event_type` `n`) where ((`a`.`id` = `b`.`superior_id`) and (`b`.`id` = `cust`.`industry_id`) and (`cust`.`id` = `cscr`.`customer_id`) and (`cust`.`id` = `mecr`.`customer_id`) and (`mecr`.`market_event_id` = `me`.`id`) and (`me`.`market_event_type_id` = `m`.`id`) and (`m`.`superior_id` = `n`.`id`));

-- ----------------------------
-- View structure for market_event_view_cust
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_cust` AS select `me`.`id` AS `id`,group_concat(distinct cast(`cust`.`id` as char charset utf8) order by `cust`.`id` ASC separator ',') AS `cust_id`,group_concat(distinct cast(`cust`.`short_name` as char charset utf8) order by `cust`.`id` ASC separator ',') AS `cust_name` from ((`market_event` `me` join `market_event_customer_rel` `mesr`) join `customer` `cust`) where ((`me`.`id` = `mesr`.`market_event_id`) and (`mesr`.`customer_id` = `cust`.`id`)) group by `me`.`id`;

-- ----------------------------
-- View structure for market_event_view_sub
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_sub` AS select `me`.`id` AS `id`,`me`.`occur_date` AS `occur_date`,date_format(`me`.`occur_date`,_utf8'%Y-%m-%d') AS `occur_date_str`,`me`.`begin_time` AS `begin_time`,time_format(`me`.`begin_time`,_utf8'%i:%s') AS `begin_time_str`,`me`.`end_time` AS `end_time`,time_format(`me`.`end_time`,_utf8'%i:%s') AS `end_time_str`,`me`.`subject` AS `subject`,`me`.`status` AS `status`,`met`.`superior_id` AS `mktevt_superior_id`,`mets`.`name` AS `mktevt_superior_name`,`met`.`id` AS `mktevt_id`,`met`.`name` AS `mktevt_name` from ((`market_event` `me` join `market_event_type` `met`) join `market_event_type` `mets`) where ((`me`.`market_event_type_id` = `met`.`id`) and (`met`.`superior_id` = `mets`.`id`));

-- ----------------------------
-- View structure for market_event_view_sysuser
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_sysuser` AS select `me`.`id` AS `id`,group_concat(distinct cast(`scu`.`id` as char charset utf8) order by `scu`.`id` ASC separator ',') AS `sys_comp_user_id`,group_concat(distinct cast(`scu`.`name` as char charset utf8) order by `scu`.`id` ASC separator ',') AS `sys_comp_user_name` from ((`market_event` `me` join `market_event_sys_user_rel` `mesur`) join `sys_company_user` `scu`) where ((`me`.`id` = `mesur`.`market_event_id`) and (`mesur`.`sys_company_user_id` = `scu`.`id`)) group by `me`.`id`;

-- ----------------------------
-- View structure for monitor_industry_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `monitor_industry_view` AS select `mi`.`id` AS `id`,`mi`.`publish_date` AS `publish_date`,`mi`.`industry_news_type_id` AS `industry_news_type_id`,`mi`.`customer_id` AS `customer_id`,`mi`.`url` AS `url`,`mi`.`subject` AS `subject`,`mi`.`content` AS `content`,date_format(`mi`.`publish_date`,_utf8'%Y-%m-%d') AS `publish_date_str`,`nt`.`name` AS `indu_news_type_name`,`cust`.`cust_name` AS `cust_name`,group_concat(distinct cast(`sc`.`id` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `sys_comp_id`,group_concat(distinct cast(`sc`.`company_name` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `sys_comp_name` from ((((`monitor_industry` `mi` join `industry_news_type` `nt`) join `customer` `cust`) join `customer_sys_company_rel` `cscr`) join `sys_company` `sc`) where ((`mi`.`industry_news_type_id` = `nt`.`id`) and (`mi`.`customer_id` = `cust`.`id`) and (`cust`.`id` = `cscr`.`customer_id`) and (`cscr`.`sys_company_id` = `sc`.`id`)) group by `mi`.`id`;

-- ----------------------------
-- View structure for monitor_news_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `monitor_news_view` AS select `mn`.`id` AS `id`,`mn`.`publish_date` AS `publish_date`,`mn`.`media` AS `media`,`mn`.`interview_date` AS `interview_date`,`mn`.`participant` AS `participant`,`mn`.`reporter` AS `reporter`,`mn`.`url` AS `url`,`mn`.`subject` AS `subject`,`mn`.`content` AS `content`,`mn`.`remark` AS `remark`,`mn`.`picture` AS `picture`,date_format(`mn`.`publish_date`,_utf8'%Y-%m-%d') AS `publish_date_str`,date_format(`mn`.`interview_date`,_utf8'%Y-%m-%d') AS `interview_date_str` from `monitor_news` `mn`;

-- ----------------------------
-- View structure for monitor_publish_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `monitor_publish_view` AS select `mp`.`id` AS `id`,`mp`.`publish_date` AS `publish_date`,date_format(`mp`.`publish_date`,_utf8'%Y-%m-%d') AS `publish_date_str`,`mp`.`publish_time` AS `publish_time`,time_format(`mp`.`publish_time`,_utf8'%i:%s') AS `publish_time_str`,`mp`.`subject` AS `subject`,`mp`.`website` AS `website`,`mp`.`url` AS `url` from `monitor_publish` `mp`;

-- ----------------------------
-- View structure for sys_company_user_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `sys_company_user_view` AS select `scu`.`id` AS `id`,`scu`.`sys_company_id` AS `sys_company_id`,`scu`.`name` AS `name`,`scu`.`login_id` AS `login_id`,`scu`.`passwd` AS `passwd`,`scu`.`superior_id` AS `superior_id`,`scu`.`email` AS `email`,`scu`.`status` AS `status`,`scu`.`delete_flag` AS `delete_flag`,`sc`.`company_name` AS `company_name`,`scu2`.`name` AS `superior_name` from ((`sys_company_user` `scu` join `sys_company` `sc` on((`scu`.`sys_company_id` = `sc`.`id`))) left join `sys_company_user` `scu2` on((`scu`.`superior_id` = `scu2`.`id`)));

-- ----------------------------
-- Function structure for rno
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`user`@`%` FUNCTION `rno`() RETURNS int(11)
BEGIN 
SET @rno = @rno + 1; 
 RETURN @rno; 
END;;
DELIMITER ;

-- ----------------------------
-- Function structure for rno_reset
-- ----------------------------
DELIMITER ;;
CREATE DEFINER=`user`@`%` FUNCTION `rno_reset`() RETURNS int(11)
BEGIN 
SET @rno = 0; 
RETURN 1; 
END;;
DELIMITER ;

--------------------------------------------------------------------------
