------------------------------
alter table customer add short_name varchar(50) not null;
alter table market_event add status char not null default 'N';

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
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view_cont` AS select `cc`.`customer_id` AS `customer_id`,group_concat(cast(`cc`.`id` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `contact_id`,group_concat(cast(`cc`.`name` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `contact_name`,group_concat(cast(`cc`.`is_primary` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `is_primary_contact` from (`customer_view_sub` `t` join `customer_contact_view` `cc` on((`t`.`id` = `cc`.`customer_id`))) group by `t`.`id`;

-- ----------------------------
-- View structure for customer_view_sub
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view_sub` AS select `cust`.`id` AS `id`,`cust`.`cust_name` AS `cust_name`,`cust`.`cust_code` AS `cust_code`,`cust`.`phone` AS `phone`,concat_ws(`cust`.`country`,`cust`.`province`,`cust`.`city`,`cust`.`address`) AS `address`,`ci`.`superior_id` AS `superior_industry_id`,`cust`.`industry_id` AS `industry_id`,`ci`.`name` AS `industry_name`,group_concat(cast(`sc`.`id` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `sys_company_id`,group_concat(cast(`sc`.`company_name` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `company_name` from (((`customer` `cust` join `customer_industry` `ci`) join `customer_sys_company_rel` `cscr`) join `sys_company` `sc`) where ((`cust`.`delete_flag` = _utf8'N') and (`cust`.`industry_id` = `ci`.`id`) and (`cust`.`id` = `cscr`.`customer_id`) and (`cscr`.`sys_company_id` = `sc`.`id`)) group by `cust`.`id`;

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
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_comp` AS select `me`.`id` AS `id`,group_concat(cast(`sc`.`id` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `comp_id`,group_concat(cast(`sc`.`company_name` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `comp_name` from ((`market_event` `me` join `market_event_company_rel` `mecr`) join `sys_company` `sc`) where ((`me`.`id` = `mecr`.`market_event_id`) and (`mecr`.`sys_company_id` = `sc`.`id`)) group by `me`.`id`;

-- ----------------------------
-- View structure for market_event_view_count
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_count` AS select sql_no_cache `test`.`rno`() AS `id`,`a`.`id` AS `industry_superior_id`,`a`.`name` AS `industry_superior_name`,`cust`.`id` AS `cust_id`,`cust`.`cust_name` AS `cust_name`,`cscr`.`sys_company_id` AS `sys_company_id`,`mecr`.`market_event_id` AS `market_event_id`,`me`.`occur_date` AS `occur_date`,`n`.`id` AS `mktevt_superior_id`,`n`.`name` AS `mktevt_superior_name` from (((((((`customer_industry` `a` join `customer_industry` `b`) join `customer` `cust`) join `customer_sys_company_rel` `cscr`) join `market_event_customer_rel` `mecr`) join `market_event` `me`) join `market_event_type` `m`) join `market_event_type` `n`) where ((`test`.`rno_reset`() = 1) and (`a`.`id` = `b`.`superior_id`) and (`b`.`id` = `cust`.`industry_id`) and (`cust`.`id` = `cscr`.`customer_id`) and (`cust`.`id` = `mecr`.`customer_id`) and (`mecr`.`market_event_id` = `me`.`id`) and (`me`.`market_event_type_id` = `m`.`id`) and (`m`.`superior_id` = `n`.`id`));

-- ----------------------------
-- View structure for market_event_view_cust
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_cust` AS select `me`.`id` AS `id`,group_concat(cast(`cust`.`id` as char charset utf8) order by `cust`.`id` ASC separator ',') AS `cust_id`,group_concat(cast(`cust`.`short_name` as char charset utf8) order by `cust`.`id` ASC separator ',') AS `cust_name` from ((`market_event` `me` join `market_event_customer_rel` `mesr`) join `customer` `cust`) where ((`me`.`id` = `mesr`.`market_event_id`) and (`mesr`.`customer_id` = `cust`.`id`)) group by `me`.`id`;

-- ----------------------------
-- View structure for market_event_view_sub
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_sub` AS select `me`.`id` AS `id`,`me`.`occur_date` AS `occur_date`,date_format(`me`.`occur_date`,_utf8'%Y-%m-%d') AS `occur_date_str`,`me`.`begin_time` AS `begin_time`,time_format(`me`.`begin_time`,_utf8'%i:%s') AS `begin_time_str`,`me`.`end_time` AS `end_time`,time_format(`me`.`end_time`,_utf8'%i:%s') AS `end_time_str`,`me`.`subject` AS `subject`,`me`.`status` AS `status`,`met`.`superior_id` AS `mktevt_superior_id`,`mets`.`name` AS `mktevt_superior_name`,`met`.`id` AS `mktevt_id`,`met`.`name` AS `mktevt_name` from ((`market_event` `me` join `market_event_type` `met`) join `market_event_type` `mets`) where ((`me`.`market_event_type_id` = `met`.`id`) and (`met`.`superior_id` = `mets`.`id`));

-- ----------------------------
-- View structure for market_event_view_sysuser
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `market_event_view_sysuser` AS select `me`.`id` AS `id`,group_concat(cast(`scu`.`id` as char charset utf8) order by `scu`.`id` ASC separator ',') AS `sys_comp_user_id`,group_concat(cast(`scu`.`name` as char charset utf8) order by `scu`.`id` ASC separator ',') AS `sys_comp_user_name` from ((`market_event` `me` join `market_event_sys_user_rel` `mesur`) join `sys_company_user` `scu`) where ((`me`.`id` = `mesur`.`market_event_id`) and (`mesur`.`sys_company_user_id` = `scu`.`id`)) group by `me`.`id`;

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
