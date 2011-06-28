<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="_cust" value="cust" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td class="label-title" width="10%">
				客户名称：
			</td>
			<td width="20%">
				<s:property value="#_cust.custName" />
			</td>
			<td class="label-title" width="10%">
				简称：
			</td>
			<td width="20%">
				<s:property value="#_cust.shortName" />
			</td>
			<td class="label-title" width="10%">
				客户编码：
			</td>
			<td width="20%">
				<s:property value="#_cust.custCode" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				所属公司：
			</td>
			<td colspan="3">
				<s:property value="#_cust.custSysCompNames" />
			</td>
			<td class="label-title">
				行业：
			</td>
			<td>
				<s:property value="#_cust.industryName" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				电话：
			</td>
			<td>
				<s:property value="#_cust.phone" />
			</td>
			<td class="label-title">
				传真：
			</td>
			<td>
				<s:property value="#_cust.fax" />
			</td>
			<td class="label-title">
				邮箱：
			</td>
			<td>
				<s:if test="%{#_cust.email!=null && #_cust.email.trim().length()>0}">
					<a href='mailto:<s:property value="#_cust.email" />'><s:property
							value="#_cust.email" /> </a>
				</s:if>
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				国家：
			</td>
			<td>
				<s:property value="#_cust.country" />
			</td>
			<td class="label-title">
				省份：
			</td>
			<td>
				<s:property value="#_cust.province" />
			</td>
			<td class="label-title">
				城市：
			</td>
			<td>
				<s:property value="#_cust.city" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				地址：
			</td>
			<td colspan="3">
				<s:property value="#_cust.address" />
			</td>
			<td class="label-title">
				邮编：
			</td>
			<td>
				<s:property value="#_cust.postcode" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td class="label-title">
				简介：
			</td>
			<td colspan="3">
				<s:property value="#_cust.descript" />
			</td>
			<td class="label-title">
				网站：
			</td>
			<td>
				<s:if
					test="%{#_cust.website!=null && #_cust.website.trim().length()>0}">
					<a href="#" onclick=window.open('<s:property value="#_cust.website" />')><s:property
							value="#_cust.website" /> </a>
				</s:if>
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td class="label-title">
				备注:
			</td>
			<td colspan="3">
				<s:property value="#_cust.remark" />
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
		<input type="hidden" name="cust.id"
			value='<s:property value="#_cust.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td class="label-title" width="10%">
					客户名称：
				</td>
				<td width="20%">
					<input type="text" name="cust.custName" class="easyui-validatebox"
						required="true" validType="length[1,50]"
						value='<s:property value="#_cust.custName" />'>
				</td>
				<td class="label-title" width="10%">
					简称：
				</td>
				<td width="20%">
					<input type="text" name="cust.shortName" class="easyui-validatebox"
						required="true" validType="length[1,50]"
						value='<s:property value="#_cust.shortName" />'>
				</td>
				<td class="label-title" width="10%">
					客户编码：
				</td>
				<td width="20%">
					<input type="text" name="cust.custCode" maxlength="20"
						value='<s:property value="#_cust.custCode" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					所属公司：
				</td>
				<td colspan="3">
					<input id="cust_sys_comp" class="easyui-combobox"
						name="custSysCompIds" required="true" url="getSysComp"
						valueField="id" textField="companyName" multiple="true"
						editable="false" panelHeight="auto" style="width: 425px;"
						value='<s:property value="#_cust.custSysCompNames" />'>

				</td>
				<td class="label-title">
					行业：
				</td>
				<td>
					<input id="cust_indu" class="easyui-combobox"
						name="cust.industryId" required="true" url="getCustIndu"
						valueField="id" textField="name" panelHeight="auto"
						editable="false" panelHeight="auto" style="width: 159px;"
						value='<s:property value="#_cust.industryName" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					电话：
				</td>
				<td>
					<input type="text" name="cust.phone" class="easyui-validatebox"
						required="true" validType="phone" maxlength="40"
						value='<s:property value="#_cust.phone" />'>
				</td>
				<td class="label-title">
					传真：
				</td>
				<td>
					<input type="text" name="cust.fax" maxlength="40"
						value='<s:property value="#_cust.fax" />'>
				</td>
				<td class="label-title">
					邮箱：
				</td>
				<td>
					<input type="text" name="cust.email" maxlength="40"
						class="easyui-validatebox" validType="email"
						value='<s:property value="#_cust.email" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					国家：
				</td>
				<td>
					<input type="text" name="cust.country" maxlength="20"
						value='<s:property value="#_cust.country" />'>
				</td>
				<td class="label-title">
					省份：
				</td>
				<td>
					<input type="text" name="cust.province" maxlength="20"
						value='<s:property value="#_cust.province" />'>
				</td>
				<td class="label-title">
					城市：
				</td>
				<td>
					<input type="text" name="cust.city" maxlength="20"
						value='<s:property value="#_cust.city" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					地址：
				</td>
				<td colspan="3">
					<input type="text" name="cust.address" maxlength="200"
						style="width: 427px;"
						value='<s:property value="#_cust.address" />' />
				</td>
				<td class="label-title">
					邮编：
				</td>
				<td>
					<input type="text" name="cust.postcode" class="easyui-validatebox"
						validType="postcode" maxlength="10"
						value='<s:property value="#_cust.postcode" />'>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					简介：
				</td>
				<td colspan="3">
					<textarea id="cust.descript" name="cust.descript" rows="5"
						style="width: 427px;" class="easyui-validatebox"
						validType="length[0,500]"> 			
					</textarea>
				</td>
				<td class="label-title">
					网站：
				</td>
				<td>
					<input type="text" name="cust.website" class="easyui-validatebox"
						validType="url" maxlength="50"
						value='<s:property value="#_cust.website" />'>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					备注：
				</td>
				<td colspan="3">
					<textarea name="cust.remark" rows="5" style="width: 427px;"
						class="easyui-validatebox" validType="length[0,500]"
						value='<s:property value="#_cust.remark" />'></textarea>
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
		window.location.href='showCustList';
	});	
	
	$("#_back_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='inline';
		document.getElementById('div_info').style.display='none';
	});
	
	$("#_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='none';
		document.getElementById('div_info').style.display='inline';	
		//		
		document.getElementById('cust.descript').value='<s:property value="#_cust.descript" escape="false" />';
		document.getElementById('cust.remark').value='<s:property value="#_cust.remark" escape="false" />';
		$('#cust_indu').combobox('setValue','<s:property value="#_cust.industryId"/>');
		var _idsStr='<s:property value="#_cust.custSysCompIds" />';		
		$('#cust_sys_comp').combobox('setValues',_idsStr.split(','));	
	});
		
	$("#_reset").click(function() {
		$('#cust_indu').combobox('clear');		
		$('#cust_sys_comp').combobox('clear');	
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		$('#infoForm').submitForm(
			'saveCustInfo',
			true,
			function(){
				var _href='showCustInfo?cust.id=<s:property value="#_cust.id" />';					
				window.location.href=_href;
			}
		);
	});
//-->
</script>
