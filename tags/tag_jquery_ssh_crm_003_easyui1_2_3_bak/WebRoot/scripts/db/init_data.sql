delete from customer_industry;
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(1,'ȯ��',null,1,null,1);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(2,'����',null,1,null,2);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(3,'Ͷ��',null,1,null,3);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(4,'ý��',null,1,null,4);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(5,'��Ѷ',null,1,null,5);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(6,'����',null,1,null,6);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(7,'����',null,1,null,7);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(8,'����ȯ��',1,2,null,8);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(9,'����ȯ��',1,2,null,9);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(10,'��������',2,2,null,10);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(11,'��������',2,2,null,11);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(12,'��ļ����',3,2,null,12);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(13,'˽ļ����',3,2,null,13);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(14,'Ͷ�ʹ�˾',3,2,null,14);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(15,'����˾',3,2,null,15);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(16,'�ƾ���վ',4,2,null,16);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(17,'������־',4,2,null,17);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(18,'����̨',4,2,null,18);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(19,'��̨',4,2,null,19);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(20,'��Ѷ��˾',5,2,null,20);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(21,'���й�˾',6,2,null,21);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(22,'��������',7,2,null,22);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(23,'��У',7,2,null,23);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(24,'�о���λ',7,2,null,24);
insert into customer_industry(id,name,superior_id,level,descript,disp_order) values(25,'����',7,2,null,25);

delete from market_event_type;
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(1,'�ݷ�',null,1,null,1);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(2,'��ѵ',null,1,null,2);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(3,'�',null,1,null,3);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(4,'����',null,1,null,4);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(5,'�ݷ�',1,2,null,5);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(6,'�ڲ���ѵ',2,2,null,6);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(7,'Ͷ������ѵ',2,2,null,7);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(8,'���ϻ',3,2,null,8);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(9,'���»',3,2,null,9);
insert into market_event_type(id,name,superior_id,level,descript,disp_order) values(10,'����',4,2,null,10);

delete from industry_news_type;
insert into industry_news_type(id,name,descript,disp_order) values(1,'����',null,1);
insert into industry_news_type(id,name,descript,disp_order) values(2,'��ҵ����',null,2);
insert into industry_news_type(id,name,descript,disp_order) values(3,'��˾����',null,3);


delete from sys_company;
insert into sys_company VALUES ('1', '�ھ�', null, 'A', 'default.jpg', 'R');
insert into sys_company VALUES ('2', '��ͨ', null, 'A', 'default.jpg', 'O');

delete from sys_company_user;
insert into `sys_company_user` VALUES ('1', '1', 'admin', 'admin', 'admin123', null, null, 'A', 'N');

delete from sys_company_user_role;
insert into sys_company_user_role(sys_company_user_id, sys_company_role_id) values(1, 1);
insert into sys_company_user_role(sys_company_user_id, sys_company_role_id) values(2, 2); 