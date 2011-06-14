<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="_mktEvt" value="mktEvt" />
<s:set name="_mktEvtView" value="mktEvtView" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td class="label-title" width="10%">
				日期：
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.occurDateStr" />
			</td>
			<td class="label-title" width="10%">
				开始时间：
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.beginTimeStr" />
			</td>
			<td class="label-title" width="10%">
				结束时间：
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.endTimeStr" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				大类：
			</td>
			<td>
				<s:property value="#_mktEvtView.mktevtSuperiorName" />
			</td>
			<td class="label-title">
				小类：
			</td>
			<td>
				<s:property value="#_mktEvtView.mktevtName" />
			</td>
			<td class="label-title">
				我方人员：
			</td>
			<td>
				<s:property value="#_mktEvtView.sysCompUserName" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				所属公司：
			</td>
			<td colspan="3">
				<s:property value="#_mktEvtView.compName" />
			</td>
			<td class="label-title">
				实施状态：
			</td>
			<td>
				<s:if test='%{"Y"==#_mktEvt.status}'>
				已实施
				</s:if>
				<s:else>
				未实施
				</s:else>
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				客户名称：
			</td>
			<td colspan="5">
				<s:property value="#_mktEvtView.custName" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				主题：
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.subject" />
			</td>
		</tr>
		<tr height="30px">
			<td class="label-title">
				规模：
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.trainScale" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td class="label-title">
				情况描述：
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.situation" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td class="label-title">
				物料：
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.goods" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td class="label-title">
				备注：
			</td>
			<td colspan="3">
				<s:property value="#_mktEvt.remark" />
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
		<input type="hidden" name="mktEvt.id"
			value='<s:property value="#_mktEvt.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td class="label-title" width="10%">
					日期：
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_occurDateStr"
						name="mktEvt.occurDateStr" class="easyui-datebox" required="true"
						value='<s:property value="#_mktEvtView.occurDateStr" />'>
				</td>
				<td class="label-title" width="10%">
					开始时间：
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_beginTimeStr" required="true"
						name="mktEvt.beginTimeStr" class="easyui-timespinner"
						value='<s:property value="#_mktEvtView.beginTimeStr" />'>
				</td>
				<td class="label-title" width="10%">
					结束时间：
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_endTimeStr" name="mktEvt.endTimeStr"
						class="easyui-timespinner" required="true"
						value='<s:property value="#_mktEvtView.endTimeStr" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					大类：
				</td>
				<td>
					<input id="_marketEventTypeId" name="_marketEventTypeId"
						class="easyui-combobox" required="true" url="" valueField="id"
						textField="name" panelHeight="auto" editable="false"
						panelHeight="auto" style="width: 160px;"
						value='<s:property value="#_mktEvtView.mktevtSuperiorName" />'>
				</td>
				<td class="label-title">
					小类：
				</td>
				<td>
					<input id="mktEvt_marketEventTypeId"
						name="mktEvt.marketEventTypeId" class="easyui-combobox"
						required="true" url="" valueField="id" textField="name"
						panelHeight="auto" editable="false" panelHeight="auto"
						style="width: 160px;"
						value='<s:property value="#_mktEvtView.mktevtName" />'>
				</td>
				<td class="label-title">
					我方人员：
				</td>
				<td>
					<input id="mktEvt_sysCompUseIds" name="mktEvt.sysCompUseIds"
						url='getSysCompUserByCompIds' class="easyui-combobox"
						required="true" valueField="id" textField="name"
						panelHeight="auto" editable="false" panelHeight="auto"
						style="width: 160px;"
						value='<s:property value="#_mktEvtView.sysCompUserName" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					所属公司：
				</td>
				<td colspan="3">
					<input id="mktEvt_sysCompIds" class="easyui-combobox"
						name="mktEvt.sysCompIds" url="getSysComp" valueField="id"
						textField="companyName" multiple="true" editable="false"
						panelHeight="auto" style="width: 426px;"
						value='<s:property value="#_mktEvtView.compName" />'>
				</td>
				<td class="label-title">
					实施状态：
				</td>
				<td>
					<input id="mktEvt_status" name="mktEvt.status"
						class="easyui-combobox" url="getStatusYN?statusFlag=2"
						required="true" valueField="id" textField="text"
						panelHeight="auto" editable="false" style="width: 160px;"
						value='<s:property value="#_mktEvt.status" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					客户名称：
				</td>
				<td colspan="5">
					<input id="mktEvt_customerIds" class="easyui-combobox"
						name="mktEvt.customerIds" url="getCustNameList" valueField="id"
						textField="custName" multiple="true" editable="false"
						panelHeight="auto" style="width: 426px;"
						value='<s:property value="#_mktEvtView.custName" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					主题：
				</td>
				<td colspan="5">
					<input type="text" name="mktEvt.subject" maxlength="200"
						style="width: 426px;" class="easyui-validatebox"
						value='<s:property value="#_mktEvt.subject" />'>
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					规模：
				</td>
				<td colspan="5">
					<input type="text" name="mktEvt.trainScale" maxlength="200"
						style="width: 426px;" class="easyui-validatebox"
						value='<s:property value="#_mktEvt.trainScale" />'>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					情况描述：
				</td>
				<td colspan="5">
					<textarea id="mktEvt_situation" name="mktEvt.situation" rows="5"
						style="width: 426px;" class="easyui-validatebox"
						validType="length[0,6000]"></textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					物料：
				</td>
				<td colspan="5">
					<textarea id="mktEvt_goods" name="mktEvt.goods" rows="5"
						style="width: 426px;" class="easyui-validatebox"
						validType="length[0,1000]"></textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					备注：
				</td>
				<td colspan="3">
					<textarea id="mktEvt_remark" name="mktEvt.remark" rows="5"
						style="width: 426px;" class="easyui-validatebox"
						validType="length[0,2000]"></textarea>
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
		window.location.href='showMktEvtList';			
	});	
	
	$("#_back_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='inline';
		document.getElementById('div_info').style.display='none';
	});
	
	$("#_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='none';
		document.getElementById('div_info').style.display='inline';		
		//		
		$('#_marketEventTypeId').combobox('setValue','<s:property value="#_mktEvtView.mktevtSuperiorId" />');
		$('#mktEvt_marketEventTypeId').combobox('setValue','<s:property value="#_mktEvtView.mktevtId" />');		
		$('#mktEvt_sysCompUseIds').combobox('setValue','<s:property value="#_mktEvtView.sysCompUserId" />');
		var _idsStr='<s:property value="#_mktEvtView.compId" />';
		$('#mktEvt_sysCompIds').combobox('setValues',_idsStr.split(','));
		_idsStr='<s:property value="#_mktEvtView.custId" />';
		$('#mktEvt_customerIds').combobox('setValues',_idsStr.split(','));		
		$('#mktEvt_status').combobox('setValue','<s:property value="#_mktEvt.status" />');
		//
		document.getElementById('mktEvt_situation').value='<s:property value="#_mktEvt.situation" escape="false" />';
		document.getElementById('mktEvt_goods').value='<s:property value="#_mktEvt.goods" escape="false" />';	
		document.getElementById('mktEvt_remark').value='<s:property value="#_mktEvt.remark" escape="false" />';
	});
		
	$("#_reset").click(function() {		
		$("#mktEvt_occurDateStr").datebox('clear');
		$("#mktEvt_beginTimeStr").timespinner('clear');
		$("#mktEvt_endTimeStr").timespinner('clear');
		$('#_marketEventTypeId').combobox('clear');
		$('#mktEvt_marketEventTypeId').combobox('clear');
		$('#mktEvt_sysCompUseIds').combobox('clear');
		$('#mktEvt_sysCompIds').combobox('clear');
		$('#mktEvt_customerIds').combobox('clear');	
		$('#mktEvt_status').combobox('clear');	
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		$('#infoForm').submitForm(
			'saveMktEvtInfo',
			validateForm(),
			function(){
				window.location.href='showMktEvtInfo?mktEvt.id=<s:property value="#_mktEvt.id" />';
			}
		);
	});	
	
	$('#_marketEventTypeId').combobox({
		url:'getMktEvtType?eventTypeId=0',		
		onChange:function(rec){
		 	var _eventTypeId=$(this).combobox("getValue");		 	 	
		 	$('#mktEvt_marketEventTypeId').combobox({
		 		url:'getMktEvtType?eventTypeId='+_eventTypeId
		 	}).combobox('clear');
		}
	});	
	
	
	function validateForm(){
		var _errorMsg='';
		var _str=''+$('#mktEvt_sysCompIds').combobox('getValues');
		_str+=''+$('#mktEvt_customerIds').combobox('getValues');
		if(null==_str || _str.replace(/(^\s*)|(\s*$)/g, "").length==0){
			_errorMsg+='所属公司和客户名称不能同时为空!<br>';			
		}
	    var sVal= $("#mktEvt_beginTimeStr").timespinner('getValue');
		var eVal= $("#mktEvt_endTimeStr").timespinner('getValue');
		if(null!=sVal && null!=eVal){
			sVal=sVal.replace(':','');
			eVal=eVal.replace(':','');
			if(sVal>eVal){
				_errorMsg+='开始时间必须小于等于结束时间!<br>';
			}
		}
		//
		if(_errorMsg.length>0){
			$.messager.alert('错误提示',_errorMsg,'error');
			return false;
		}		
		return true;
	}	
//-->
</script>