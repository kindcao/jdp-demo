<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="_sysComp" value="#session.SYS_COMP_SESSION_KEY" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				公司名称：
			</td>
			<td width="20%">
				<s:property value="#_sysComp.companyName" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				公司类型：
			</td>
			<td width="20%">
				<s:if test='%{"R"==#_sysComp.type}'>
				融聚公司
				</s:if>
				<s:else>
				其它公司
				</s:else>
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				公司状态：
			</td>
			<td width="20%">
				<s:if test='%{"A"==#_sysComp.status}'>
				正常
				</s:if>
				<s:else>
				禁用
				</s:else>
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				Logo图URI：
			</td>
			<td colspan="3">
				<s:if test="%{#_sysComp.logo.trim().length()>0}">
					<a href="#" onclick=window.open('<s:property value="#_sysComp.logo" />')><s:property
							value="#_sysComp.logo" /> </a>
				</s:if>
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				备注：
			</td>
			<td colspan="3">
				<s:property value="#_sysComp.descript" />
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
		<input type="hidden" name="sysComp.id"
			value='<s:property value="#_sysComp.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					公司名称：
				</td>
				<td width="20%">
					<input type="text" name="sysComp.companyName"
						class="easyui-validatebox" required="true"
						validType="length[1,50]"
						value='<s:property value="#_sysComp.companyName" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					公司类型：
				</td>
				<td width="20%">
					<select id="sysComp_type" name="sysComp.type"
						class="easyui-combobox" panelHeight="auto" required="true"
						editable="false">
						<option value="R">
							融聚公司
						</option>
						<option value="O">
							其它公司
						</option>
					</select>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					公司状态：
				</td>
				<td width="20%">
					<select id="sysComp_status" name="sysComp.status"
						class="easyui-combobox" panelHeight="auto" required="true"
						editable="false">
						<option value="A">
							正常
						</option>
						<option value="D">
							禁用
						</option>
					</select>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					Logo图URI：
				</td>
				<td colspan="3">
					<input type="text" name="sysComp.logo" style="width: 402px;"
						maxlength="100" class="easyui-validatebox" required="true"
						validType="url" value='<s:property value="#_sysComp.logo" />' />
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注：
				</td>
				<td colspan="3">
					<textarea id="sysComp_descript" name="sysComp.descript" rows="5"
						style="width: 402px;" class="easyui-validatebox"
						validType="length[0,100]"></textarea>
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
	$("#_back").click(function() {
		window.location.href='showSysCompList.action';			
	});	
	
	$("#_back_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='inline';
		document.getElementById('div_info').style.display='none';
	});
	
	$("#_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='none';
		document.getElementById('div_info').style.display='inline';
		//	
		$('#sysComp_type').combobox('setValue','<s:property value="#_sysComp.type" />');
		$('#sysComp_status').combobox('setValue','<s:property value="#_sysComp.status" />');
		document.getElementById('_sysComp_descript').value='<s:property value="#_sysComp.descript" escape="false" />';
		
	});
		
	$("#_reset").click(function() {
		$("#sysComp_type").datebox('clear');
		$('#sysComp_status').combobox('clear');
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		$('#infoForm').submitForm(
			'saveSysCompInfo.action',
			true,
			function(){
				window.location.href='showSysCompInfo.action?sysComp.id=<s:property value="#_sysComp.id" />';
			}
		);		
	});	
//-->
</script>