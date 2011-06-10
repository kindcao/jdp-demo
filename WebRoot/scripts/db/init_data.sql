delete from customer_industry;
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(1,'券商',null,1,null,1);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(2,'银行',null,1,null,2);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(3,'投资',null,1,null,3);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(4,'媒体',null,1,null,4);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(5,'资讯',null,1,null,5);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(6,'上市',null,1,null,6);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(7,'其他',null,1,null,7);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(8,'中资券商',1,2,null,8);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(9,'海外券商',1,2,null,9);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(10,'中资银行',2,2,null,10);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(11,'海外银行',2,2,null,11);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(12,'公募基金',3,2,null,12);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(13,'私募基金',3,2,null,13);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(14,'投资公司',3,2,null,14);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(15,'财务公司',3,2,null,15);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(16,'财经网站',4,2,null,16);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(17,'报刊杂志',4,2,null,17);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(18,'电视台',4,2,null,18);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(19,'电台',4,2,null,19);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(20,'资讯公司',5,2,null,20);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(21,'上市公司',6,2,null,21);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(22,'政府机构',7,2,null,22);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(23,'高校',7,2,null,23);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(24,'研究单位',7,2,null,24);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(25,'其他',7,2,null,25);

delete from market_event_type;
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(1,'拜访',null,1,null,1);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(2,'培训',null,1,null,2);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(3,'活动',null,1,null,3);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(4,'其他',null,1,null,4);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(5,'拜访',1,2,null,5);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(6,'内部培训',2,2,null,6);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(7,'投资者培训',2,2,null,7);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(8,'线上活动',3,2,null,8);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(9,'线下活动',3,2,null,9);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(10,'其他',4,2,null,10);

delete from industry_news_type;
insert into industry_news_type(id,name,descript,disp_order) values(1,'政策',null,1);
insert into industry_news_type(id,name,descript,disp_order) values(2,'行业新闻',null,2);
insert into industry_news_type(id,name,descript,disp_order) values(3,'公司新闻',null,3);


delete from sys_company;
insert into sys_company VALUES ('1', '融聚', null, 'A', 'default.jpg', 'R');
insert into sys_company VALUES ('2', '联通', null, 'A', 'default.jpg', 'O');

delete from sys_company_user;
insert into `sys_company_user` VALUES ('1', '1', 'admin', 'admin', 'admin123', null, null, 'A', 'N');

delete from sys_company_user_role;
insert into sys_company_user_role(sys_company_user_id, sys_company_role_id) values(1, 1);
insert into sys_company_user_role(sys_company_user_id, sys_company_role_id) values(2, 2); 