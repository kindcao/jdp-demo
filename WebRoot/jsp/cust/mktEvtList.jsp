<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<s:hidden id="eventTypeId" name="eventTypeId" />
<div id="div_info_market_event"
	style="margin-top: 10px; display: inline;">
	<form id="infoFormMktEvt" name="infoFormMktEvt">
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					日期:
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_occurDate" name="mktEvt.occurDate"
						class="easyui-datebox" required="true">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					开始时间:
				</td>
				<td width="20%">
					<input type="text" name="mktEvt.beginTime"
						class="easyui-timespinner" value="09:00" required="true">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					结束时间:
				</td>
				<td width="20%">
					<input type="text" name="mktEvt.endTime" class="easyui-timespinner"
						value="18:00" required="true">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					大类:
				</td>
				<td>
					<input id="_marketEventTypeId" name="_marketEventTypeId"
						class="easyui-combobox" required="true" url="" valueField="id"
						textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 134px;">
				</td>
				<td nowrap="nowrap" align="center">
					小类:
				</td>
				<td>
					<input id="mktEvt_marketEventTypeId"
						name="mktEvt.marketEventTypeId" class="easyui-combobox"
						required="true" url="" valueField="id" textField="name"
						multiple="false" editable="false" panelHeight="auto"
						style="width: 134px;">
				</td>
				<td nowrap="nowrap" align="center">
					我方人员
				</td>
				<td>
					<input id="_sysCompUseIds" name="sysCompUseIds"
						url='getSysCompUserByCompIds.action?sysCompIds=<s:property value="#session._sysUser.sysCompanyId" />'
						class="easyui-combobox" required="true" valueField="id"
						textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 134px;">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					联系人:
				</td>
				<td colspan="5">
					<input class="easyui-combobox" id="_contIds" name="contIds"
						url='getContByCustIds.action?customerId=<s:property value="#_cust.id" />'
						required="true" valueField="id" textField="name" multiple="true"
						editable="false" panelHeight="auto" style="width: 401px;">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					主题:
				</td>
				<td colspan="5">
					<input type="text" name="mktEvt.subject" maxlength="200"
						style="width: 404px;" class="easyui-validatebox">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					规模:
				</td>
				<td colspan="5">
					<input type="text" name="mktEvt.trainScale" maxlength="200"
						style="width: 404px;" class="easyui-validatebox">
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					情况描述:
				</td>
				<td colspan="5">
					<textarea name="mktEvt.situation" rows="5" style="width: 404px;"
						class="easyui-validatebox" validType="length[0,6000]"> 				
					</textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					物料:
				</td>
				<td colspan="5">
					<textarea name="mktEvt.goods" rows="5" style="width: 404px;"
						class="easyui-validatebox" validType="length[0,1000]"> 				
					</textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea name="mktEvt.remark" rows="5" style="width: 404px;"
						class="easyui-validatebox" validType="length[0,2000]"> 				
					</textarea>
				</td>
				<td colspan="2" align="center" valign="bottom">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save_contact">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset_contact">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_contact">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript" defer="defer">
<!--
//	
$(document).ready(function() {
	//
	$('#_marketEventTypeId').combobox({
		url:'getMarketEventType.action?eventTypeId=0',		
		onChange:function(rec){
		 	var _eventTypeId=$(this).combobox("getValue");		 	 	
		 	$('#mktEvt_marketEventTypeId').combobox({
		 		url:'getMarketEventType.action?eventTypeId='+_eventTypeId
		 	}).combobox('clear');
		}
	});	
});
//-->
</script>