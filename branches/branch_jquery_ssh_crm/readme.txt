220.248.104.218
administrator/Quattro!
------------------------
���л���˵��
------------------------
	a.���ݿ�ʹ�õ���mysql������汾Ϊ5.x����
	b.web������ʹ��tomcat������汾Ϊ6.x����
	c.jre����汾Ϊ1.6x����
	d.����ϵͳwindow��linux������
	e.�������а��ջ���������Ĭ�������������ݿ�˿ڡ�web�������˿ڵȡ�
	
###################
*���²������������л�������ȷ����
###################

------------------------
1���������ݿ⣨mysql��
------------------------
1.1���������ݿ⼰�����û�����Ȩ��
	a.ʹ��rootȨ��ִ��scripts/db/create_db.sql
1.2��������ͼ��
	a.ʹ��user������Ҳ��user���û�ִ��scripts/db/create_table.sql
	����Ҫִ�ж�Σ���Ϊ��ͼ֮����������ϵ��ȷ��������ͼ���Ѿ����ü��ɣ�
	b.ʹ��user������Ҳ��user���û�ִ��scripts/db/init_data.sql��һ�μ��ɣ�	
	
------------------------
2������web��������Ŀǰ��tomcat6.xΪ��
------------------------
2.1���޸�tomcat�������ĵ���server.xml��
	a.��Ҫ��server.xml����������������Ϣ��ʹ��֧��gzipѹ������
	<Connector port="8080" protocol="HTTP/1.1" 
               connectionTimeout="20000" 
               redirectPort="8443" 
               compression="on" 
               compressionMinSize="2048"
               noCompressionUserAgents="gozilla, traviata"
               compressableMimeType="text/html,text/xml,text/javascript,text/css,text/plain,text/json"/>
2.2����crm.war���Ƶ�tomcat��webappsĿ¼��

------------------------
3�����÷������ݿ������
------------------------
	3.1��tomcat���ڻ������������������dns������jdp-demo.dbserverָ�����ݿ����ڻ���
------------------------

4������
------------------------
	4.1����web������
	4.2ʹ���������http://127.0.0.1:8080/crm���Ƶ�ַ���ɿ���ϵͳ��¼����
	4.3ʹ���ڶ�admin�˺ŵ�¼���ɽ���ϵͳ
	4.4������ĳЩ�������û�з�Ӧ˵����Ӧ����Ϊʵ��
	