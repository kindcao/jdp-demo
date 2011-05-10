<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
<!--
function login(formIdStr) {
	var isValid = $('#' + formIdStr).form('validate');	
	if (isValid) {
		var options = {
			url : 'login.action',
			dataType : 'json',
			success : function(data){
				if (!data.success) {
					$.messager.alert('提示信息', data.errors, 'error');
				} else {
					window.location.href="welcome.action";
				}
			}
		};
		$('#'+formIdStr).ajaxSubmit(options);
	}
}
//-->
</script>
<div align="center">
	<s:form id="loginForm">
		<table cellspacing="0" cellpadding="0" width="400" height="150"
			style="border: 1px solid #A4BED4;" align="center">
			<tr height="20px">
				<th width="30%" align="left" bgcolor="#A4BED4" colspan="2">
					<div style="margin: 5px;">
						用户登录
					</div>
				</th>
			</tr>
			<tr height="40px">
				<td width="30%" align="center">
					用户名称 :
				</td>
				<td>
					<input name="sysCompUser.loginId" class="easyui-validatebox"
						required="true" validType="length[1,20]" value="sunny" />
				</td>
			</tr>
			<tr height="30px">
				<td width="30%" align="center">
					登录密码 :
				</td>
				<td>
					<input name="sysCompUser.passwd" type="text"
						class="easyui-validatebox" required="true"
						validType="length[1,20]" value="123456" />
				</td>
			</tr>
			<tr>
				<td width="30%" align="center">
					<label>
						&nbsp;
					</label>
				</td>
				<td align="center">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						plain="true" onclick="login('loginForm');">登录</a>
				</td>
			</tr>
		</table>
	</s:form>
</div>
