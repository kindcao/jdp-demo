<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<s:hidden id="eventTypeId" name="eventTypeId" />
<div id="div_info_market_event"
	style="margin-top: 10px; display: inline;">
	<form id="infoFormMarketEvent" name="infoFormMarketEvent">
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					日期:
				</td>
				<td width="20%">
					<input type="text" id="marketEvent_occurDate"
						name="marketEvent.occurDate" class="easyui-datebox"
						required="true">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					开始时间:
				</td>
				<td width="20%">
					<input type="text" name="marketEvent.beginTime"
						class="easyui-timespinner" value="09:00" required="true">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					结束时间:
				</td>
				<td width="20%">
					<input type="text" name="marketEvent.endTime"
						class="easyui-timespinner" value="18:00" required="true">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					类型：
				</td>
				<td>
					<input id="marketEvent_marketEventTypeId" class="easyui-combobox"
						name="marketEvent.marketEventTypeId" required="true" url=""
						valueField="id" textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 135px;">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					主题:
				</td>
				<td colspan="5">
					<input type="text" name="cont.email" maxlength="50"
						style="width: 402px;" class="easyui-validatebox" validType="email">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					规模:
				</td>
				<td colspan="5">
					<input type="text" name="cont.email" maxlength="50"
						style="width: 402px;" class="easyui-validatebox" validType="email">
				</td>

			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					物料:
				</td>
				<td colspan="5">
					<textarea name="cont.remark" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,500]"> 				
					</textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					情况描述:
				</td>
				<td colspan="5">
					<textarea name="cont.remark" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,500]"> 				
					</textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea name="cont.remark" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,500]"> 				
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
	$('#marketEvent_marketEventTypeId').combobox({
		url:'getMarketEventType.action?eventTypeId='+$('#eventTypeId').val()		
	});	
});
//-->
</script>