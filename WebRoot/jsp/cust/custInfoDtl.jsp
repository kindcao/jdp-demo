<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<s:hidden id="induId" name="induId" />
<s:hidden id="custId" name="cust.id" />
<s:set name="_cust" value="#session.CUSTOMER_SESSION_KEY" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				客户名称:
			</td>
			<td width="20%">
				<s:property value="#_cust.custName" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				客户编码:
			</td>
			<td width="20%">
				<s:property value="#_cust.custCode" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				行业:
			</td>
			<td width="20%">
				<s:property value="#_cust.industryName" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				所属公司:
			</td>
			<td colspan="3">
				<s:property value="#_cust.custSysCompNames" />
			</td>
			<td nowrap="nowrap" align="center">
				电话:
			</td>
			<td>
				<s:property value="#_cust.phone" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				邮箱:
			</td>
			<td>
				<s:if test="%{null!=#_cust.email}">
					<a href='mailto:<s:property value="#_cust.email" />'><s:property
							value="#_cust.email" /> </a>
				</s:if>
			</td>
			<td nowrap="nowrap" align="center">
				传真:
			</td>
			<td>
				<s:property value="#_cust.fax" />
			</td>
			<td nowrap="nowrap" align="center">
				网站:
			</td>
			<td>
				<s:if test="%{null!=#_cust.website}">
					<a href='<s:property value="#_cust.website" />'><s:property
							value="#_cust.website" /> </a>
				</s:if>
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				国家:
			</td>
			<td>
				<s:property value="#_cust.country" />
			</td>
			<td nowrap="nowrap" align="center">
				省份:
			</td>
			<td>
				<s:property value="#_cust.province" />
			</td>
			<td nowrap="nowrap" align="center">
				城市:
			</td>
			<td>
				<s:property value="#_cust.city" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				地址:
			</td>
			<td colspan="3">
				<s:property value="#_cust.address" />
			</td>
			<td nowrap="nowrap" align="center">
				邮编:
			</td>
			<td>
				<s:property value="#_cust.postcode" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				简介:
			</td>
			<td colspan="3">
				<s:property value="#_cust.descript" />
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
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
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					客户名称:
				</td>
				<td width="20%">
					<input type="text" name="cust.custName" class="easyui-validatebox"
						required="true" validType="length[1,50]"
						value='<s:property value="#_cust.custName" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					客户编码:
				</td>
				<td width="20%">
					<input type="text" name="cust.custCode" maxlength="20"
						value='<s:property value="#_cust.custCode" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					行业:
				</td>
				<td width="20%">
					<input id="cust_indu" class="easyui-combobox"
						name="cust.industryId" required="true" url="" valueField="id"
						textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 135px;"
						value='<s:property value="#_cust.industryName" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					所属公司:
				</td>
				<td colspan="3">
					<input id="cust_sys_comp" class="easyui-combobox"
						name="custSysCompIds" required="true" url="getSysComp.action"
						valueField="id" textField="companyName" multiple="true"
						editable="false" panelHeight="auto" style="width: 400px;"
						value='<s:property value="#_cust.custSysCompNames" />'>

				</td>
				<td nowrap="nowrap" align="center">
					电话:
				</td>
				<td>
					<input type="text" name="cust.phone" class="easyui-validatebox"
						required="true" validType="phone" maxlength="40"
						value='<s:property value="#_cust.phone" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					邮箱:
				</td>
				<td>
					<input type="text" name="cust.email" maxlength="40"
						class="easyui-validatebox" validType="email"
						value='<s:property value="#_cust.email" />'>
				</td>
				<td nowrap="nowrap" align="center">
					传真:
				</td>
				<td>
					<input type="text" name="cust.fax" maxlength="40"
						value='<s:property value="#_cust.fax" />'>
				</td>
				<td nowrap="nowrap" align="center">
					网站:
				</td>
				<td>
					<input type="text" name="cust.website" class="easyui-validatebox"
						validType="url" maxlength="50"
						value='<s:property value="#_cust.website" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					国家:
				</td>
				<td>
					<input type="text" name="cust.country" maxlength="20"
						value='<s:property value="#_cust.country" />'>
				</td>
				<td nowrap="nowrap" align="center">
					省份:
				</td>
				<td>
					<input type="text" name="cust.province" maxlength="20"
						value='<s:property value="#_cust.province" />'>
				</td>
				<td nowrap="nowrap" align="center">
					城市:
				</td>
				<td>
					<input type="text" name="cust.city" maxlength="20"
						value='<s:property value="#_cust.city" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					地址:
				</td>
				<td colspan="3">
					<input type="text" name="cust.address" maxlength="200"
						style="width: 402px;"
						value='<s:property value="#_cust.address" />' />
				</td>
				<td nowrap="nowrap" align="center">
					邮编:
				</td>
				<td>
					<input type="text" name="cust.postcode" class="easyui-validatebox"
						validType="postcode" maxlength="10"
						value='<s:property value="#_cust.postcode" />'>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					简介:
				</td>
				<td colspan="3">
					<textarea id="cust.descript" name="cust.descript" rows="5"
						style="width: 402px;" class="easyui-validatebox"
						validType="length[0,500]"> 			
					</textarea>
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea name="cust.remark" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,500]"
						value='<s:property value="#_cust.remark" />'> 				
					</textarea>
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

<script type="text/javascript">
<!--
	$('#cust_indu').combobox({
		url:'getCustIndu.action?induId='+$('#induId').val()				
	});	
	
	$("#_back").click(function() {
		window.location.href='showCustList.action?induId='+$('#induId').val();
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
		$('#cust_indu').combobox('setValue','<s:property value="#_cust.industryId" />');
		var _idsStr='<s:property value="#_cust.custSysCompIds" />';		
		$('#cust_sys_comp').combobox('setValues',_idsStr.split(','));	
	});
		
	$("#_reset").click(function() {
		$('#cust_indu').combobox('clear');		
		$('#cust_sys_comp').combobox('clear');	
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		var isValid = $('#infoForm').form('validate');	
		if (isValid) {			
			var options = {
				url : 'saveCustInfo.action?actionFlag=U',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {
						var _href='showCustInfo.action?induId='+$('#induId').val()
						+'&cust.id='+$('#custId').val();						
						window.location.href=_href;
					}
				}
			};
			$('#infoForm').ajaxSubmit(options);			
		}
	});	
//-->
</script>
