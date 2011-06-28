<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="_sysCompUser" value="sysCompUserView" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td class="label-title" width="10%">
				用户姓名：
			</td>
			<td width="20%">
				<s:property value="#_sysCompUser.name" />
			</td>
			<td class="label-title" width="10%">
				属性：
			</td>
			<td width="20%">
				<s:property value="#_sysCompUser.companyName" />
			</td>
			<td class="label-title" width="10%">
				用户领导：
			</td>
			<td width="20%">
				<s:property value="#_sysCompUser.superiorName" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				登录账号：
			</td>
			<td>
				<s:property value="#_sysCompUser.loginId" />
			</td>
			<td class="label-title">
				登录密码：
			</td>
			<td>
				<s:property value="#_sysCompUser.passwd" />
			</td>
			<td class="label-title">
				用户状态：
			</td>
			<td>
				<s:if test='%{"A"==#_sysCompUser.status}'>
				正常
				</s:if>
				<s:else>
				禁用
				</s:else>
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				用户邮箱：
			</td>
			<td>
				<s:if test="%{#_sysCompUser.email.trim().length()>0}">
					<a href='mailto:<s:property value="#_sysCompUser.email" />'><s:property
							value="#_sysCompUser.email" /> </a>
				</s:if>
			</td>
			<td class="label-title">
				删除标记：
			</td>
			<td>
				<s:if test='%{"N"==#_sysCompUser.deleteFlag}'>
				未删除
				</s:if>
				<s:else>
				已删除
				</s:else>
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center" valign="bottom">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-edit" id="_edit">修改</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-back" id="_back">返回</a>
			</td>
		</tr>
	</table>
</div>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
		<input type="hidden" name="sysCompUser.id"
			value='<s:property value="#_sysCompUser.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td class="label-title" width="10%">
					用户姓名：
				</td>
				<td width="20%">
					<input type="text" name="sysCompUser.name"
						class="easyui-validatebox" required="true"
						validType="length[1,40]"
						value='<s:property value="#_sysCompUser.name" />'>
				</td>
				<td class="label-title" width="10%">
					所属公司：
				</td>
				<td>
					<input id="sysCompUser_sysCompanyId" class="easyui-combobox"
						name="sysCompUser.sysCompanyId" url="getSysComp" valueField="id"
						textField="companyName" editable="false" panelHeight="auto"
						required="true" style="width: 158px;"
						value='<s:property value="#_sysCompUser.sysCompanyId" />'>
				</td>
				<td class="label-title" width="10%">
					用户领导：
				</td>
				<td width="20%">
					<input id="sysCompUser_superiorId" name="sysCompUser.superiorId"
						url='' class="easyui-combobox" valueField="id" textField="name"
						panelHeight="auto" editable="true" panelHeight="auto"
						style="width: 158px;"
						value='<s:property value="#_sysCompUser.superiorId" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					登录账号：
				</td>
				<td>
					<input type="text" name="sysCompUser.loginId"
						class="easyui-validatebox" required="true" validType="loginName"
						maxlength="20" readonly="readonly"
						value='<s:property value="#_sysCompUser.loginId" />'>
				</td>
				<td class="label-title">
					登录密码：
				</td>
				<td width="20%">
					<input type="text" name="sysCompUser.passwd"
						class="easyui-validatebox" required="true" validType="safepass"
						maxlength="50" value='<s:property value="#_sysCompUser.passwd" />'>
				</td>
				<td class="label-title">
					用户状态：
				</td>
				<td>
					<input id="sysCompUser_status" name="sysCompUser.status"
						class="easyui-combobox" url="getStatusYN?statusFlag=3"
						valueField="id" textField="text" panelHeight="auto"
						required="true" editable="false" style="width: 158px;">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					用户邮箱：
				</td>
				<td>
					<input type="text" name="sysCompUser.email"
						class="easyui-validatebox" validType="email" maxlength="50"
						value='<s:property value="#_sysCompUser.email" />'>
				</td>
				<td class="label-title">
					删除标记：
				</td>
				<td>
					<input id="sysCompUser_deleteFlag" name="sysCompUser.deleteFlag"
						class="easyui-combobox" url="getStatusYN?statusFlag=1"
						valueField="id" textField="text" panelHeight="auto"
						required="true" editable="false" style="width: 158px;">
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td colspan="4" align="center">
					&nbsp;
				</td>
				<td colspan="2" align="center" valign="bottom">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_edit">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" defer="defer">
<!--
	$('#sysCompUser_sysCompanyId').combobox({		
		onSelect:function(rec){
		 	var _sysUserIds=$(this).combobox("getValues");		 	
		 	$('#sysCompUser_superiorId').combobox({
		 		url:'getSysCompUserByCompIds?sysCompIds='+_sysUserIds
		 	}).combobox('clear');
		}
	});
	
	$('#sysCompUser_superiorId').combobox({
		onSelect:function(rec){
			if(rec.name=='<s:property value="#_sysCompUser.name" />'){
				$(this).combobox("unselect",rec.id);
			}
		}
	});
	
	$("#_back").click(function() {
		window.location.href='showSysCompUserList';			
	});	
	
	$("#_back_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='inline';
		document.getElementById('div_info').style.display='none';
	});
	
	$("#_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='none';
		document.getElementById('div_info').style.display='inline';
		//
		$('#sysCompUser_sysCompanyId').combobox('select','<s:property value="#_sysCompUser.sysCompanyId" />');
		$('#sysCompUser_superiorId').combobox('setValue','<s:property value="#_sysCompUser.superiorId" />');
		$('#sysCompUser_status').combobox('setValue','<s:property value="#_sysCompUser.status" />');
		$('#sysCompUser_deleteFlag').combobox('setValue','<s:property value="#_sysCompUser.deleteFlag" />');
		
	});
		
	$("#_reset").click(function() {
		$("#sysCompUser_sysCompanyId").datebox('clear');
		$('#sysCompUser_superiorId').combobox('clear');
		$('#sysCompUser_status').combobox('clear');
		$('#sysCompUser_deleteFlag').combobox('clear');
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		$('#infoForm').submitForm(
			'saveSysCompUserInfo',
			true,
			function(){
				window.location.href='showSysCompUserInfo?sysCompUser.id=<s:property value="#_sysCompUser.id" />';
			}
		);		
	});	
//-->
</script>