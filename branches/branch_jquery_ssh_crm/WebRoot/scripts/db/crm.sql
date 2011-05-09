-- ----------------------------
-- View structure for customer_view
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view` AS select `t`.`customer_id` AS `id`,`t`.`customer_id` AS `customer_id`,`t`.`cust_name` AS `cust_name`,`t`.`cust_code` AS `cust_code`,`t`.`phone` AS `phone`,`t`.`address` AS `address`,`t`.`superior_industry_id` AS `superior_industry_id`,`t`.`industry_id` AS `industry_id`,`t`.`industry_name` AS `industry_name`,`t`.`sys_company_id` AS `sys_company_id`,`t`.`company_name` AS `company_name`,group_concat(cast(`cc`.`id` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `contact_id`,group_concat(cast(`cc`.`name` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `contact_name`,group_concat(cast(`cc`.`is_primary` as char charset utf8) order by `cc`.`id` ASC separator ',') AS `is_primary_contact` from (`customer_view_a` `t` left join `customer_contact` `cc` on((`t`.`customer_id` = `cc`.`customer_id`))) group by `t`.`customer_id` order by `t`.`customer_id` desc;

-- ----------------------------
-- View structure for customer_view_a
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`user`@`%` SQL SECURITY DEFINER VIEW `customer_view_a` AS select `cust`.`id` AS `customer_id`,`cust`.`cust_name` AS `cust_name`,`cust`.`cust_code` AS `cust_code`,`cust`.`phone` AS `phone`,concat(concat(`cust`.`country`,`cust`.`province`),concat(`cust`.`city`,`cust`.`address`)) AS `address`,`ci`.`superior_id` AS `superior_industry_id`,`cust`.`industry_id` AS `industry_id`,`ci`.`name` AS `industry_name`,group_concat(cast(`sc`.`id` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `sys_company_id`,group_concat(cast(`sc`.`company_name` as char charset utf8) order by `sc`.`id` ASC separator ',') AS `company_name` from (((`customer` `cust` join `customer_industry` `ci`) join `customer_sys_company_rel` `cscr`) join `sys_company` `sc`) where ((`cust`.`delete_flag` = _utf8'N') and (`cust`.`industry_id` = `ci`.`id`) and (`cust`.`id` = `cscr`.`customer_id`) and (`cscr`.`sys_company_id` = `sc`.`id`)) group by `cust`.`id`;
