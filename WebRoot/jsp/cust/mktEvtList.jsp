<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.*,java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<jsp:include page="../common/_toolbar_mktevt.jsp"></jsp:include>
<div id="div_info_mktevt" style="margin-top: 10px; display: none;">
	<form id="infoFormMktEvt" name="infoFormMktEvt">
		<input type="hidden" id="mktEvt_customerIds" name="mktEvt.customerIds"
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
					<input type="text" id="mktEvt_beginTimeStr" required="true"
						name="mktEvt.beginTimeStr" class="easyui-timespinner"
						value='<%=new SimpleDateFormat("HH:mm").format(new Date())%>'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					结束时间:
				</td>
				<td width="20%">
					<input type="text" id="mktEvt_endTimeStr" name="mktEvt.endTimeStr"
						class="easyui-timespinner" required="true"
						value='<%=new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis() + 30 * 60 * 1000))%>'>
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
						class="easyui-validatebox" validType="length[0,6000]"></textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					物料:
				</td>
				<td colspan="5">
					<textarea name="mktEvt.goods" rows="5" style="width: 404px;"
						class="easyui-validatebox" validType="length[0,1000]"></textarea>
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea name="mktEvt.remark" rows="5" style="width: 404px;"
						class="easyui-validatebox" validType="length[0,2000]"></textarea>
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
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td nowrap="nowrap" align="center" width="10%">
						日期:
					</td>
					<td width="20%">
						<input type="text" id="occurDateStr" name="occurDateStr"
							class="easyui-datebox">
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						开始时间:
					</td>
					<td width="20%">
						<input type="text" id="beginTimeStr" name="beginTimeStr"
							class="easyui-timespinner">
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						结束时间:
					</td>
					<td width="20%">
						<input type="text" id="endTimeStr" name="endTimeStr"
							class="easyui-timespinner">
					</td>
				</tr>
				<tr height="30px">
					<td nowrap="nowrap" align="center">
						大类:
					</td>
					<td>
						<input id="mktevtSuperiorId" name="mktevtSuperiorId"
							class="easyui-combobox" url="" valueField="id" textField="name"
							multiple="false" editable="false" panelHeight="auto"
							style="width: 134px;">
					</td>
					<td nowrap="nowrap" align="center">
						主题:
					</td>
					<td colspan="3">
						<input type="text" id="subject" name="subject" maxlength="200"
							style="width: 404px;" class="easyui-validatebox">
					</td>
				</tr>
				<tr height="30px">
					<td colspan="5">
						&nbsp;
					</td>
					<td align="center">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" id="_search_mktevt">查询</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-remove" id="_reset_search_mktevt">重置</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	<div style="height: 30px;">
		&nbsp;
	</div>
	<div align="left">
		<table id="grid-datalist-mktevt"></table>
	</div>
</div>
<script type="text/javascript" defer="defer">
<!--
	//for add begin			
	$("#_add_mktevt").click(function() {
		document.getElementById('div_info_mktevt').style.display='inline';
		document.getElementById('div_search_mktevt').style.display='none';
		$('#_delete_mktevt').linkbutton('disable');	
		//$("#_reset_mktevt").click();
	});
	
	$("#_back_mktevt").click(function() {
		document.getElementById('div_info_mktevt').style.display='none';
		document.getElementById('div_search_mktevt').style.display='inline';
		$('#_delete_mktevt').linkbutton('enable');
		//$("#_reset_mktevt").click();
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
						reloadDatagrid('grid-datalist-mktevt');
					}
				}
			};
			$('#infoFormMktEvt').ajaxSubmit(options);
		}
	});
	
	$("#_reset_mktevt").click(function() {	  
	    $("#mktEvt_occurDateStr").datebox('clear');
		$("#mktEvt_beginTimeStr").timespinner('clear');
		$("#mktEvt_endTimeStr").timespinner('clear');
		$('#_marketEventTypeId').combobox('clear');
		$('#mktEvt_marketEventTypeId').combobox('clear');
		$('#mktEvt_sysCompUseIds').combobox('clear');
		$('#mktEvt_contIds').combobox('clear');		
		resetForm('infoFormMktEvt');
	});	
	//for add end
	
	//for delete begin
	$("#_delete_mktevt").click(function() {
		deleteRecord('grid-datalist-mktevt','deleteMktEvt.action');
	});
	//fore delte end
	
	
	//for search begin	
	$('#_reset_search_mktevt').click(function(){
		$("#occurDateStr").datebox('clear');
		$("#beginTimeStr").timespinner('clear');
		$("#endTimeStr").timespinner('clear');
		$('#mktevtSuperiorId').combobox('clear');		
		resetForm('searchFormMktEvt');		
	});	
	
	$("#_search_mktevt").click(function() {
		var queryParams = $('#grid-datalist-mktevt').datagrid('options').queryParams;	
		queryParams.occurDateStr = $("#occurDateStr").datebox('getValue');
		queryParams.beginTimeStr = $("#beginTimeStr").timespinner('getValue');
		queryParams.endTimeStr = $("#endTimeStr").timespinner('getValue');
		queryParams.subject = $("#subject").val();	
		queryParams.mktevtSuperiorId = $("#mktevtSuperiorId").combobox('getValue');			
		reloadDatagrid('grid-datalist-mktevt');		
	});	
	
	$('#mktevtSuperiorId').combobox({
		url:'getMarketEventType.action?eventTypeId=0'
	});
	
	function editMktEvt(id){	
		var tab = $('#tabs-container').tabs('getSelected');	
		$('#tabs-container').tabs('update', {
			tab: tab,
			options:{
				href:'showMktEvtInfo.action?mktEvt.id='+id			
			}
		});			
	}
	
	//	
	$(document).ready(function() {
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'occurDateStr',
					title : '日期',
					width : 100,
					sortable : false,			
					formatter : function(value, rec) {
						return "<a href='#' onclick='editMktEvt(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
		    field : 'beginTimeStr',
			title : '开始时间',
			width : 60
		},{
			field : 'endTimeStr',
			title : '结束时间',
			width : 60
		},{
			field : 'mktevtName',
			title : '类型',
			width : 100
		},{
			field : 'subject',
			title : '主题',
			width : 200
		},{
			field : 'contName',
			title : '联系人',
			width : 150
		},{
			field : 'sysCompUserName',
			title : '我方人员',
			width : 150
		}]];			
	
		//		
		showDatagrid('grid-datalist-mktevt',
			'getMktEvtList.action?customerIds='+ $('#mktEvt_customerIds').val(),
			frozenColumns,columns);
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