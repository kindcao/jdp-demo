<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<s:set name="_cont" value="#session.CUSTOMER_CONTACT_SESSION_KEY" />
<div id="div_info_read-only_contact"
	style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				姓名:
			</td>
			<td width="20%">
				<s:property value="#_cont.name" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				部门:
			</td>
			<td width="20%">
				<s:property value="#_cont.department" />
			</td>
			<td nowrap="nowrap" align="center" valign="top">
				照片URI:
			</td>
			<td rowspan="3" valign="top">
				<s:if test="%{#_cont.picture.trim().length()>0}">
					<img width="100px" height="80px"
						src='<s:property value="#_cont.picture" />' />
				</s:if>
				<s:else>
					<img width="100px" height="80px" src='images/portrait.gif' />
				</s:else>
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				职位:
			</td>
			<td width="20%">
				<s:property value="#_cont.posit" />
			</td>
			<td nowrap="nowrap" align="center">
				座机:
			</td>
			<td>
				<s:property value="#_cont.phone" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				传真:
			</td>
			<td>
				<s:property value="#_cont.fax" />
			</td>
			<td nowrap="nowrap" align="center">
				移动电话:
			</td>
			<td>
				<s:property value="#_cont.mobile" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				邮箱:
			</td>
			<td>
				<s:if test="%{#_cont.email.trim().length()>0}">
					<a href='mailto:<s:property value="#_cont.email" />'> <s:property
							value="#_cont.email" /> </a>
				</s:if>
			</td>
			<td nowrap="nowrap" align="center">
				是否主要:
			</td>
			<td>
				<s:if test='%{"Y"==#_cont.isPrimary}'>
				是
				</s:if>
				<s:else>
				否
				</s:else>
			</td>
			<td nowrap="nowrap" align="center">
				MSN/QQ:
			</td>
			<td>
				<s:property value="#_cont.im" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				地址:
			</td>
			<td colspan="3">
				<s:property value="#_cont.address" />
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
				<s:property value="#_cont.remark" />
			</td>
			<td colspan="2" align="center" valign="bottom">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-edit" id="_edit_contact">修改</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-back" id="_back_contact">返回</a>
			</td>
		</tr>
	</table>
</div>
<div id="div_info_contact" style="margin-top: 10px; display: none;">
	<form id="infoFormContantEdit" name="infoFormContantEdit">
		<input type="hidden" name="cont.id"
			value='<s:property value="#_cont.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					姓名:
				</td>
				<td width="20%">
					<input type="text" name="cont.name" class="easyui-validatebox"
						required="true" validType="length[1,40]"
						value='<s:property value="#_cont.name" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					部门:
				</td>
				<td width="20%">
					<input type="text" name="cont.department" maxlength="40"
						value='<s:property value="#_cont.department" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					职位:
				</td>
				<td width="20%">
					<input type="text" name="cont.posit" maxlength="40"
						value='<s:property value="#_cont.posit" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					座机:
				</td>
				<td>
					<input type="text" name="cont.phone" class="easyui-validatebox"
						validType="phone" maxlength="40"
						value='<s:property value="#_cont.phone" />'>
				</td>
				<td nowrap="nowrap" align="center">
					传真:
				</td>
				<td>
					<input type="text" name="cont.fax" maxlength="40"
						value='<s:property value="#_cont.fax" />'>
				</td>
				<td nowrap="nowrap" align="center">
					移动电话:
				</td>
				<td>
					<input type="text" name="cont.mobile" class="easyui-validatebox"
						validType="mobile" maxlength="40"
						value='<s:property value="#_cont.mobile" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					邮箱:
				</td>
				<td>
					<input type="text" name="cont.email" maxlength="50"
						class="easyui-validatebox" validType="email"
						value='<s:property value="#_cont.email" />'>
				</td>
				<td nowrap="nowrap" align="center">
					是否主要:
				</td>
				<td>
					<input id="_cont_isPrimary" name="cont.isPrimary"
						class="easyui-combobox" url="getStatusYN.action" valueField="id"
						textField="text" panelHeight="auto" editable="false"
						style="width: 133px;">
				</td>
				<td nowrap="nowrap" align="center">
					MSN/QQ:
				</td>
				<td>
					<input type="text" name="cont.im" maxlength="50"
						value='<s:property value="#_cont.im" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					照片URI:
				</td>
				<td colspan="3">
					<input type="text" name="cont.picture" maxlength="100"
						style="width: 402px;" class="easyui-validatebox" validType="url"
						value='<s:property value="#_cont.picture" />' />
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					地址:
				</td>
				<td colspan="3">
					<input type="text" name="cont.address" maxlength="200"
						style="width: 402px;"
						value='<s:property value="#_cont.address" />' />
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
					<textarea id="_cont_remark" name="cont.remark" rows="5"
						style="width: 402px;" class="easyui-validatebox"
						validType="length[0,500]"></textarea>
				</td>
				<td colspan="2" align="center" valign="bottom">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save_edit_contact">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset_edit_contact">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_edit_contact">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" defer="defer">
<!--	
	$("#_back_contact").click(function() {
		var tab = $('#tabs-container').tabs('getSelected');	
		$('#tabs-container').tabs('update', {
			tab: tab,
			options:{
				href:'showContList.action'		
			}
		});	
	});	
	
	$("#_back_edit_contact").click(function() {
		document.getElementById('div_info_read-only_contact').style.display='inline';
		document.getElementById('div_info_contact').style.display='none';
	});
	
	$("#_edit_contact").click(function() {
		document.getElementById('div_info_read-only_contact').style.display='none';
		document.getElementById('div_info_contact').style.display='inline';	
		//		
		document.getElementById('_cont_remark').value='<s:property value="#_cont.remark" escape="false" />';
		$('#_cont_isPrimary').combobox('setValue','<s:property value="#_cont.isPrimary" />');				
	});
		
	$("#_reset_edit_contact").click(function() {
		$('#_cont_isPrimary').combobox('clear');	
		resetForm('infoFormContantEdit');
	});
	
	$("#_save_edit_contact").click(function() {
		var isValid = $('#infoFormContantEdit').form('validate');	
		if (isValid) {			
			var options = {
				url : 'saveContInfo.action',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {
						editCont('<s:property value="#_cont.id" />');						
					}
				}
			};
			$('#infoFormContantEdit').ajaxSubmit(options);			
		}
	});	
//-->
</script>