<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">    
    Ext.onReady(function() {
		Ext.BLANK_IMAGE_URL = '<%=request.getContextPath()%>/scripts/extjs2.0/resources/images/default/s.gif';
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		var loginForm = new Ext.form.FormPanel({
			title : '<s:text name="msg.login"/>',
			labelWidth : 80,
			labelAlign:'right',
			bodyStyle:'padding:5 5 5 5',			 
			width : 300,
			frame : true,
			labelSeparator : ' : ',
			applyTo : 'loginForm',
			defaultType : "textfield",			
			keys : [{ // 键盘回车提交功能
				key : [10, 13],
				fn : login
			}],
			items : [new Ext.form.TextField({
				fieldLabel : '<s:text name="msg.login.username"/>',
				name : 'username',
				width : 160,
				maxLength : 20,				
				allowBlank : false,
				selectOnFocus : true,
				vtypeText : '<s:text name="msg.login.username"/>',			
				blankText : '<s:text name="msg.comm.null"><s:param><s:text name="msg.login.username"/></s:param></s:text>'
			}), new Ext.form.TextField({
				fieldLabel : '<s:text name="msg.login.passwd"/>',
				name : 'password',
				inputType : 'password',
				maxLength : 20,				
				width : 160,
				allowBlank : false,
				blankText : '<s:text name="msg.comm.null"><s:param><s:text name="msg.login.passwd"/></s:param></s:text>'
			})],
			buttons : [new Ext.Button({
								text : '<s:text name="msg.button.login"/>',																
								handler : login
							}), new Ext.Button({
								text : '<s:text name="msg.button.reset"/>',
								handler : reset
							})]
		})
	
		function login() {				
			loginForm.form.submit({
				clientValidation : true,// 进行客户端验证
				waitMsg : '<s:text name="msg.login.waitmsg"/>',// 提示信息
				waitTitle : '<s:text name="msg.comm.title"><s:param><s:text name="msg.login.waittitle"/></s:param></s:text>',// 标题
				url : 'login.action',
				method : 'GET',// 请求方式
				success : function(form, action) {
					// Ext.Msg.alert('提示','系统成功登陆');
					document.location = "forward.action?forward=main";
				},
				failure : function(form, action) {
				   obj = Ext.util.JSON.decode(action.response.responseText);
                   Ext.Msg.alert('<s:text name="msg.comm.title"><s:param><s:text name="msg.login.failtitle"/></s:param></s:text>',
				                 '<s:text name="msg.login.errormsg"><s:param>'+ obj.errors.info + '</s:param></s:text>');
				}
			})
		}	
		
		function reset() {
			loginForm.form.reset();
		}
	});
</script>
	<body>
		<center>
			<div id="loginForm" style="margin-top: 15%"></div>
		</center>
	</body>
</html>
