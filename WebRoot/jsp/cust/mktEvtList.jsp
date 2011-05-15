<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.*,java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<jsp:include page="../common/_toolbar_mktevt.jsp"></jsp:include>
<s:hidden id="eventTypeId" name="eventTypeId" />
<div id="div_info_mktevt" style="margin-top: 10px; display: none;">
	<form id="infoFormMktEvt" name="infoFormMktEvt">
		<input type="hidden" name="mktEvt.customerIds"
			value='<s:property value="#session.CUSTOMER_SESSION_KEY.id" />' />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					日期:
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_occurDateStr"
						name="mktEvt.occurDateStr" class="easyui-datebox" required="true"
						value='<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					开始时间:
				</td>
				<td width="20%">
					<input type="text" name="mktEvt.beginTimeStr"
						class="easyui-timespinner" required="true"
						value='<%=new SimpleDateFormat("HH:mm").format(new Date())%>'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					结束时间:
				</td>
				<td width="20%">
					<input name="mktEvt.endTimeStr" class="easyui-timespinner"
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
					<input id="mktEvt_sysCompUseIds" name="mktEvt.sysCompUseIds"
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
					<input id="mktEvt_contIds" name="mktEvt.contIds"
						url='getContByCustIds.action?customerId=<s:property value="#session.CUSTOMER_SESSION_KEY.id" />'
						class="easyui-combobox" required="true" valueField="id"
						textField="name" multiple="true" editable="false"
						panelHeight="auto" style="width: 401px;">
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
						iconCls="icon-save" id="_save_mktevt">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset_mktevt">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_mktevt">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="div_search_mktevt" style="display: inline;">
	<form id="searchFormMktEvt" name="searchFormMktEvt">
		<fieldset style="margin-top: 5px;">
			<legend>
				查询条件
			</legend>
		</fieldset>
	</form>
</div>
<script type="text/javascript" defer="defer">
<!--
	//for add begin			
	$("#_add_mktevt").click(function() {
		document.getElementById('div_info_mktevt').style.display='inline';
		document.getElementById('div_search_mktevt').style.display='none';
		$('#_delete_mktevt').linkbutton('disable');	
		//$("#_reset_contact").click();	
	});
	
	$("#_back_mktevt").click(function() {
		document.getElementById('div_info_mktevt').style.display='none';
		document.getElementById('div_search_mktevt').style.display='inline';
		$('#_delete_mktevt').linkbutton('enable');
		$("#_reset_mktevt").click();
	});	
	
	$("#_save_mktevt").click(function() {
		var isValid = $('#infoFormMktEvt').form('validate');	
		if (isValid) {
			var options = {
				url : 'saveMktEvtInfo.action',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {						
						$("#_back_mktevt").click();
						//reloadDatagrid('grid-datalist_mktevt');
					}
				}
			};
			$('#infoFormMktEvt').ajaxSubmit(options);
		}
	});
	
	$("#_reset_mktevt").click(function() {
		$('#_marketEventTypeId').combobox('clear');
		$('#mktEvt_marketEventTypeId').combobox('clear');
		$('#mktEvt_sysCompUseIds').combobox('clear');
		$('#mktEvt_contIds').combobox('clear');		
		resetForm('infoFormMktEvt');
	});	
	//for add end
	
	//for delete begin
	$("#_delete_mktevt").click(function() {
		deleteRecord('grid-datalist_mktevt','deleteCont.action');
	});
	//fore delte end
	
	
	//$('#mktEvt_occurDateStr').datebox('setValue','2011-03-05');
	
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