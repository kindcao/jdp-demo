-- ---------------------------- 
-- Create database 
-- ----------------------------

create database if not exists test default charset utf8 Collate utf8_unicode_ci;

-- ---------------------------- 
-- Create database 
-- ---------------------------- 
grant all privileges on test.* to user@'%' identified by 'user';