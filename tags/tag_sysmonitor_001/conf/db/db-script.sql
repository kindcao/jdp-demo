/*
MySQL Data Transfer
Source Host: 127.0.0.1
Source Database: test
Target Host: 127.0.0.1
Target Database: test
Date: 2010-12-5 10:09:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_pass` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin@finet.com');
INSERT INTO `user` VALUES ('2', 'user01', 'user01', 'user01@finet.cn');
INSERT INTO `user` VALUES ('3', 'test02', 'test02', 'test03');
INSERT INTO `user` VALUES ('4', 'test03', 'test03', 'test03');
INSERT INTO `user` VALUES ('5', 'test04', 'test04', 'test04');
INSERT INTO `user` VALUES ('6', 'test05', 'test05', 'test05');
INSERT INTO `user` VALUES ('7', 'test06', 'test06', 'test06');
