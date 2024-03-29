<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="_induView" value="industryView" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td class="label-title" width="10%">
				发布日期：
			</td>
			<td width="20%">
				<s:property value="#_induView.publishDateStr" />
			</td>
			<td class="label-title" width="10%">
				属性：
			</td>
			<td width="20%">
				<s:property value="#_induView.induNewsTypeName" />
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				相关机构：
			</td>
			<td colspan="3">
				<s:property value="#_induView.custName" />
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				网址：
			</td>
			<td colspan="3">
				<s:if
					test="%{#_induView.url!=null && #_induView.url.trim().length()>0}">
					<a href="#" onclick=window.open('<s:property value="#_induView.url" />')><s:property
							value="#_induView.url" /> </a>
				</s:if>
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				标题：
			</td>
			<td colspan="3">
				<s:property value="#_induView.subject" />
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td class="label-title">
				备注：
			</td>
			<td colspan="3">
				<s:property value="#_induView.content" />
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
		<input type="hidden" name="industryExtDto.id"
			value='<s:property value="#_induView.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td class="label-title" width="10%">
					发布日期：
				</td>
				<td width="20%">
					<input type="text" id="industryExtDto_publishDateStr"
						name="industryExtDto.publishDateStr" class="easyui-datebox"
						required="true"
						value='<s:property value="#_induView.publishDateStr" />'>
				</td>
				<td class="label-title" width="10%">
					属性：
				</td>
				<td width="20%">
					<input id="industryExtDto_industryNewsTypeId"
						name="industryExtDto.industryNewsTypeId" class="easyui-combobox"
						required="true" url="getIndustryNewsType" valueField="id"
						textField="name" editable="false" panelHeight="auto"
						style="width: 157px;"
						value='<s:property value="#_induView.industryNewsTypeId" />'>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title" width="10%">
					相关机构：
				</td>
				<td colspan="3">
					<input id="industryExtDto_customerId"
						name="industryExtDto.customerId" class="easyui-combobox"
						required="true" url="getCustNameList" valueField="id"
						textField="custName" editable="false" panelHeight="auto"
						style="width: 397px;"
						value='<s:property value="#_induView.customerId" />'>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					网址：
				</td>
				<td colspan="3">
					<input type="text" name="industryExtDto.url"
						class="easyui-validatebox" validType="url" maxlength="200"
						style="width: 400px;"
						value='<s:property value="#_induView.url" />'>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					标题：
				</td>
				<td colspan="3">
					<input type="text" name="industryExtDto.subject" maxlength="400"
						class="easyui-validatebox" required="true" style="width: 400px;"
						value='<s:property value="#_induView.subject" />' />
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					相关资料：
				</td>
				<td colspan="3">
					<textarea id="industryExtDto_content" name="industryExtDto.content"
						rows="5" style="width: 400px;" class="easyui-validatebox"
						validType="length[0,6000]"></textarea>
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
		window.location.href='showIndustryList';			
	});	
	
	$("#_back_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='inline';
		document.getElementById('div_info').style.display='none';
	});
	
	$("#_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='none';
		document.getElementById('div_info').style.display='inline';
		//
		$('#industryExtDto_industryNewsTypeId').combobox('setValue','<s:property value="#_induView.industryNewsTypeId" />');
		$('#industryExtDto_customerId').combobox('setValue','<s:property value="#_induView.customerId" />');		
		document.getElementById('industryExtDto_content').value='<s:property value="#_induView.content" escape="false" />';
	});
		
	$("#_reset").click(function() {
		$("#industryExtDto_publishDateStr").datebox('clear');
		$('#industryExtDto_industryNewsTypeId').combobox('clear');
		$('#industryExtDto_customerId').combobox('clear');
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		$('#infoForm').submitForm(
			'saveIndustryInfo',
			true,
			function(){
				window.location.href='showIndustryInfo?industryExtDto.id=<s:property value="#_induView.id" />';
			}
		);		
	});	
//-->
</script>