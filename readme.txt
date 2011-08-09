220.248.104.218
administrator/Quattro!
------------------------
运行环境说明
------------------------
	a.数据库使用的是mysql，建议版本为5.x以上
	b.web服务器使用tomcat，建议版本为6.x以上
	c.jre建议版本为1.6x以上
	d.操作系统window或linux都可以
	e.建议所有按照环境都采用默认配置例如数据库端口、web服务器端口等。
	
###################
*以下步骤依赖于运行环境的正确配置
###################

------------------------
1、建立数据库（mysql）
------------------------
1.1、创建数据库及新增用户并授权。
	a.使用root权限执行scripts/db/create_db.sql
1.2、建表、视图等
	a.使用user（密码也是user）用户执行scripts/db/create_table.sql
	（需要执行多次，因为视图之间有依赖关系，确保所有视图都已经建好即可）
	b.使用user（密码也是user）用户执行scripts/db/init_data.sql（一次即可）	
	
------------------------
2、配置web服务器，目前以tomcat6.x为例
------------------------
2.1、修改tomcat的配置文档（server.xml）
	a.需要在server.xml中增加类似配置信息，使其支持gzip压缩功能
	<Connector port="8080" protocol="HTTP/1.1" 
               connectionTimeout="20000" 
               redirectPort="8443" 
               compression="on" 
               compressionMinSize="2048"
               noCompressionUserAgents="gozilla, traviata"
               compressableMimeType="text/html,text/xml,text/javascript,text/css,text/plain,text/json"/>
2.2、将crm.war复制到tomcat的webapps目录下

------------------------
3、配置访问数据库的域名
------------------------
	3.1在tomcat所在机器或者是所在网络的dns中增加jdp-demo.dbserver指向数据库所在机器
------------------------

4、测试
------------------------
	4.1启动web服务器
	4.2使用浏览器打开http://127.0.0.1:8080/crm类似地址即可看到系统登录版面
	4.3使用内定admin账号登录即可进入系统
	4.4如果点击某些连接如果没有反应说明相应功能为实现
	