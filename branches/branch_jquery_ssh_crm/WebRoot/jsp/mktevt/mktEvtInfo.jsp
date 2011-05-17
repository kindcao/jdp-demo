<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:hidden id="mktEvtId" name="mktEvt.id" />
<s:set name="_mktEvt" value="#session.MARKET_EVENT_SESSION_KEY" />
<s:set name="_mktEvtView" value="#session.MARKET_EVENT_VIEW_SESSION_KEY" />
<div id="div_info_read-only_mktevt"
	style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				日期:
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.occurDateStr" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				开始时间:
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.beginTimeStr" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				结束时间:
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.endTimeStr" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				大类:
			</td>
			<td>
				<s:property value="#_mktEvtView.mktevtSuperiorName" />
			</td>
			<td nowrap="nowrap" align="center">
				小类:
			</td>
			<td>
				<s:property value="#_mktEvtView.mktevtName" />
			</td>
			<td nowrap="nowrap" align="center">
				我方人员
			</td>
			<td>
				<s:property value="#_mktEvtView.sysCompUserName" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				联系人:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvtView.contName" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				主题:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.subject" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				规模:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.trainScale" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				情况描述:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.situation" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				物料:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.goods" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				备注:
			</td>
			<td colspan="3">
				<s:property value="#_mktEvt.remark" />
			</td>
			<td colspan="2" align="center" valign="bottom">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-edit" id="_edit_mktevt">修改</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-back" id="_back_mktevt">返回</a>
			</td>
		</tr>
	</table>
</div>
<div id="div_info_mktevt" style="margin-top: 10px; display: none;">
	<form id="infoFormMktEvtEdit" name="infoFormMktEvtEdit">
		<input type="hidden" name="mktEvt.customerIds"
			value='<s:property value="#session.CUSTOMER_SESSION_KEY.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					日期:
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_occurDateStr_edit"
						name="mktEvt.occurDateStr" class="easyui-datebox" required="true"
						value='<s:property value="#_mktEvtView.occurDateStr" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					开始时间:
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_beginTimeStr_edit" required="true"
						name="mktEvt.beginTimeStr" class="easyui-timespinner"
						value='<s:property value="#_mktEvtView.beginTimeStr" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					结束时间:
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_endTimeStr_edit"
						name="mktEvt.endTimeStr" class="easyui-timespinner"
						required="true"
						value='<s:property value="#_mktEvtView.endTimeStr" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					大类:
				</td>
				<td>
					<input id="_marketEventTypeId_edit" name="_marketEventTypeId"
						class="easyui-combobox" required="true" url="" valueField="id"
						textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 134px;"
						value='<s:property value="#_mktEvtView.mktevtSuperiorName" />'>
				</td>
				<td nowrap="nowrap" align="center">
					小类:
				</td>
				<td>
					<input id="mktEvt_marketEventTypeId_edit"
						name="mktEvt.marketEventTypeId" class="easyui-combobox"
						required="true" url="" valueField="id" textField="name"
						multiple="false" editable="false" panelHeight="auto"
						style="width: 134px;"
						value='<s:property value="#_mktEvtView.mktevtName" />'>
				</td>
				<td nowrap="nowrap" align="center">
					我方人员
				</td>
				<td>
					<input id="mktEvt_sysCompUseIds_edit" name="mktEvt.sysCompUseIds"
						url='getSysCompUserByCompIds.action?sysCompIds=<s:property value="#session._sysUser.sysCompanyId" />'
						class="easyui-combobox" required="true" valueField="id"
						textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 134px;"
						value='<s:property value="#_mktEvtView.sysCompUserName" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					联系人:
				</td>
				<td colspan="5">
					<input id="mktEvt_contIds_edit" name="mktEvt.contIds"
						url='getContByCustIds.action?customerId=<s:property value="#session.CUSTOMER_SESSION_KEY.id" />'
						class="easyui-combobox" required="true" valueField="id"
						textField="name" multiple="true" editable="false"
						panelHeight="auto" style="width: 401px;"
						value='<s:property value="#_mktEvtView.contName" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					主题:
				</td>
				<td colspan="5">
					<input type="text" name="mktEvt.subject" maxlength="200"
						style="width: 404px;" class="easyui-validatebox"
						value='<s:property value="#_mktEvt.subject" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					规模:
				</td>
				<td colspan="5">
					<input type="text" name="mktEvt.trainScale" maxlength="200"
						style="width: 404px;" class="easyui-validatebox"
						value='<s:property value="#_mktEvt.trainScale" />'>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					情况描述:
				</td>
				<td colspan="5">
					<textarea id="mktEvt_situation" name="mktEvt.situation" rows="5"
						style="width: 404px;" class="easyui-validatebox"
						validType="length[0,6000]"></textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					物料:
				</td>
				<td colspan="5">
					<textarea id="mktEvt_goods" name="mktEvt.goods" rows="5"
						style="width: 404px;" class="easyui-validatebox"
						validType="length[0,1000]"></textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea id="mktEvt_remark" name="mktEvt.remark" rows="5"
						style="width: 404px;" class="easyui-validatebox"
						validType="length[0,2000]"></textarea>
				</td>
				<td colspan="2" align="center" valign="bottom">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save_edit_mktevt">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset_edit_mktevt">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_edit_mktevt">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" defer="defer">
<!--	
	$("#_back_mktevt").click(function() {
		window.location.href='showMktEvtList.action';			
	});	
	
	$("#_back_edit_mktevt").click(function() {
		document.getElementById('div_info_read-only_mktevt').style.display='inline';
		document.getElementById('div_info_mktevt').style.display='none';
	});
	
	$("#_edit_mktevt").click(function() {
		document.getElementById('div_info_read-only_mktevt').style.display='none';
		document.getElementById('div_info_mktevt').style.display='inline';		
		//
		$('#_marketEventTypeId_edit').combobox('setValue','<s:property value="#_mktEvtView.mktevtSuperiorId" />');
		$('#mktEvt_marketEventTypeId_edit').combobox('setValue','<s:property value="#_mktEvtView.mktevtId" />');		
		$('#mktEvt_sysCompUseIds_edit').combobox('setValue','<s:property value="#_mktEvtView.sysCompUserId" />');
		var _idsStr='<s:property value="#_mktEvtView.contId" />';
		$('#mktEvt_contIds_edit').combobox('setValues',_idsStr.split(','));
		//
		document.getElementById('mktEvt_situation').value='<s:property value="#_mktEvt.situation" escape="false" />';
		document.getElementById('mktEvt_goods').value='<s:property value="#_mktEvt.goods" escape="false" />';	
		document.getElementById('mktEvt_remark').value='<s:property value="#_mktEvt.remark" escape="false" />';
	});
		
	$("#_reset_edit_mktevt").click(function() {
		$("#mktEvt_occurDateStr_edit").datebox('clear');
		$("#mktEvt_beginTimeStr_edit").timespinner('clear');
		$("#mktEvt_endTimeStr_edit").timespinner('clear');
		$('#_marketEventTypeId_edit').combobox('clear');
		$('#mktEvt_marketEventTypeId_edit').combobox('clear');
		$('#mktEvt_sysCompUseIds_edit').combobox('clear');
		$('#mktEvt_contIds_edit').combobox('clear');		
		resetForm('infoFormMktEvtEdit');
	});
	
	$("#_save_edit_mktevt").click(function() {
		var isValid = $('#infoFormMktEvtEdit').form('validate');	
		if (isValid) {			
			var options = {
				url : 'saveMktEvtInfo.action?actionFlag=U',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {
						editMktEvt($('#mktEvtId').val());						
					}
				}
			};
			$('#infoFormMktEvtEdit').ajaxSubmit(options);			
		}
	});	
	
	$('#_marketEventTypeId_edit').combobox({
		url:'getMarketEventType.action?eventTypeId=0',		
		onChange:function(rec){
		 	var _eventTypeId=$(this).combobox("getValue");		 	 	
		 	$('#mktEvt_marketEventTypeId_edit').combobox({
		 		url:'getMarketEventType.action?eventTypeId='+_eventTypeId
		 	}).combobox('clear');
		}
	});	
	
//-->
</script>