<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div align="center">
	<form id="loginForm">
		<table cellspacing="0" cellpadding="0" width="400" height="150"
			style="border: 1px solid #A4BED4; margin-top: 150px;" align="center">
			<tr height="20px">
				<th width="30%" align="left" bgcolor="#A4BED4" colspan="2">
					<div style="margin: 5px;">
						<img src="images/lock.png">
						用户登录
					</div>
				</th>
			</tr>
			<tr height="40px">
				<td width="30%" class="label-title">
					用户名称：
				</td>
				<td>
					<input name="sysCompUser.loginId" class="easyui-validatebox"
						required="true" validType="length[1,20]" value="admin" />
				</td>
			</tr>
			<tr height="30px">
				<td width="30%" class="label-title">
					登录密码：
				</td>
				<td>
					<input name="sysCompUser.passwd" type="password"
						class="easyui-validatebox" required="true" validType="safepass"
						maxlength="50" value="admin123" />
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
						plain="true" id="_login">登录</a>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript" defer="defer">
<!--
	$("#_login").click(function() {
		$('#loginForm').submitForm(
			'login',
			true,
			function(){
				window.location.href='welcome';
			}
		);	
	});
//-->
</script>