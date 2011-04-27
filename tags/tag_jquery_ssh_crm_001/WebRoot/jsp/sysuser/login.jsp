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
			success : function(data) {			
				if (!data.success) {
					$.messager.alert('提示信息', data.errors, 'error');
				} else {
					window.location.href="forward.action?forward=main";
				}
			}
		};
		$('#'+formIdStr).ajaxSubmit(options);
	}
}
//-->
</script>

<s:form id="loginForm" method="post">
	<table cellspacing="0" cellpadding="0" width="400" height="150"
		style="border: 1px solid #A4BED4;" align="center">
		<tr height="20px">
			<td width="30%" align="left" bgcolor="#A4BED4" colspan="2">
				<label id="lusername" for="username">
					<s:text name="msg.login" />
				</label>
			</td>
		</tr>
		<tr height="30px">
			<td width="30%" align="center">
				<label id="lusername" for="username">
					<s:text name="msg.login.username" />
					:
				</label>
			</td>
			<td>
				<input id="username" name="username" class="easyui-validatebox"
					required="true" validType="length[1,20]" value="admin" />
			</td>
		</tr>
		<tr height="30px">
			<td width="30%" align="center">
				<label id="lpassword" for="password">
					<s:text name="msg.login.passwd" />
					:
				</label>
			</td>
			<td>
				<input id="password" name="password" type="text"
					class="easyui-validatebox" required="true" validType="length[1,20]"
					value="admin" />
			</td>
		</tr>
		<tr>
			<td width="30%" align="center">
				<label>
					&nbsp;
				</label>
			</td>
			<td align="center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true"
					onclick="login('loginForm');">登录</a>
			</td>
		</tr>
	</table>
</s:form>
