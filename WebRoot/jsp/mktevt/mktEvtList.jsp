<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.*,java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
		<s:hidden name="actionFlag" value="" />
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
					所属公司:
				</td>
				<td colspan="3">
					<input id="mktEvt_sysCompIds" class="easyui-combobox"
						name="mktEvt.sysCompIds" url="getSysComp.action" valueField="id"
						textField="companyName" multiple="true" editable="false"
						panelHeight="auto" style="width: 401px;">
				</td>
				<td nowrap="nowrap" align="center">
					实施状态:
				</td>
				<td>
					<select id="mktEvt_status" name="mktEvt.status"
						class="easyui-combobox" required="true" panelHeight="auto"
						editable="false" style="width: 133px;">
						<option value="N" selected="selected">
							未实施
						</option>
						<option value="Y">
							已实施
						</option>
					</select>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					客户名称:
				</td>
				<td colspan="5">
					<input id="mktEvt_customerIds" class="easyui-combobox"
						name="mktEvt.customerIds" url="getCustNameList.action"
						valueField="id" textField="custName" multiple="true"
						editable="false" panelHeight="100" style="width: 401px;">
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
						iconCls="icon-save" id="_save">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="div_search" style="display: inline;">
	<form id="searchForm" name="searchForm">
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
						<input type="text" id="occurDateStrBegin" name="occurDateStrBegin"
							class="easyui-datebox">
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						至
					</td>
					<td width="20%">
						<input type="text" id="occurDateStrEnd" name="occurDateStrEnd"
							class="easyui-datebox">
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						大类:
					</td>
					<td width="20%">
						<input id="mktevtSuperiorId" name="mktevtSuperiorId"
							class="easyui-combobox" url="" valueField="id" textField="name"
							multiple="false" editable="false" panelHeight="auto"
							style="width: 134px;">
					</td>
				</tr>
				<tr height="30px">
					<td nowrap="nowrap" align="center" width="10%">
						开始时间:
					</td>
					<td>
						<input type="text" id="beginTimeStr" name="beginTimeStr"
							class="easyui-timespinner">
					</td>
					<td nowrap="nowrap" align="center">
						结束时间:
					</td>
					<td>
						<input type="text" id="endTimeStr" name="endTimeStr"
							class="easyui-timespinner">
					</td>
					<td nowrap="nowrap" align="center">
						实施状态:
					</td>
					<td>
						<select id="status" name="status" class="easyui-combobox"
							panelHeight="auto" editable="false" style="width: 133px;">
							<option value="">
							</option>
							<option value="N">
								未实施
							</option>
							<option value="Y">
								已实施
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" align="center">
						主题:
					</td>
					<td>
						<input type="text" id="subject" name="subject" maxlength="200"
							class="easyui-validatebox">
					</td>
					<td nowrap="nowrap" align="center">
						我方人员：
					</td>
					<td>
						<input id="sysCompUseIds" name="sysCompUseIds"
							url='getSysCompUserByCompIds.action?sysCompIds=<s:property value="#session._sysUser.sysCompanyId" />'
							class="easyui-combobox" valueField="id" textField="name"
							multiple="false" editable="false" panelHeight="auto"
							style="width: 134px;">
					</td>
					<td nowrap="nowrap" align="center">
						参与机构:
					</td>
					<td>
						<input type="text" id="compCustName" name="compCustName"
							maxlength="200" class="easyui-validatebox">
					</td>
				</tr>
				<tr height="30px">
					<td colspan="5">
						&nbsp;
					</td>
					<td align="center">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" id="_search">查询</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-remove" id="_reset_search">重置</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	<div style="height: 30px;">
		&nbsp;
	</div>
	<div align="left">
		<table id="grid-datalist"></table>
	</div>
</div>
<script type="text/javascript" defer="defer">
<!--
	//for add begin			
	$("#_add").click(function() {
		document.getElementById('div_info').style.display='inline';
		document.getElementById('div_search').style.display='none';
		$('#_delete').linkbutton('disable');	
		$("#_reset").click();
	});
	
	$("#_back").click(function() {
		document.getElementById('div_info').style.display='none';
		document.getElementById('div_search').style.display='inline';
		$('#_delete').linkbutton('enable');
		$("#_reset").click();
	});	
	
	$("#_save").click(function() {
		var isValid = $('#infoForm').form('validate');		
		if (isValid && validateForm()) {
			var options = {
				url : 'saveMktEvtInfo.action',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {						
						$("#_back").click();
						$("#_search").click();
					}
				}
			};
			$('#infoForm').ajaxSubmit(options);
		}
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
	//for add end
	
	//for delete begin
	$("#_delete").click(function() {
		deleteRecord('grid-datalist','deleteMktEvt.action');
	});
	//fore delte end
	
	
	//for search begin	
	$('#_reset_search').click(function(){
		$("#occurDateStrBegin").datebox('clear');
		$("#occurDateStrEnd").datebox('clear');
		$("#beginTimeStr").timespinner('clear');
		$("#endTimeStr").timespinner('clear');
		$('#mktevtSuperiorId').combobox('clear');
		$('#status').combobox('clear');	
		$('#sysCompUseIds').combobox('clear');		
		resetForm('searchForm');		
	});	
	
	$("#_search").click(function() {
		var queryParams = $('#grid-datalist').datagrid('options').queryParams;	
		queryParams.occurDateStrBegin = $("#occurDateStrBegin").datebox('getValue');
		queryParams.occurDateStrEnd = $("#occurDateStrEnd").datebox('getValue');
		queryParams.beginTimeStr = $("#beginTimeStr").timespinner('getValue');
		queryParams.endTimeStr = $("#endTimeStr").timespinner('getValue');		
		queryParams.mktevtSuperiorId = $("#mktevtSuperiorId").combobox('getValue');
		queryParams.status = $("#status").combobox('getValue');
		queryParams.subject = $("#subject").val();
		queryParams.sysCompUseIds = $("#sysCompUseIds").combobox('getValue');
		queryParams.compCustName = $("#compCustName").val();
		reloadDatagrid('grid-datalist');		
	});	
	
	$('#mktevtSuperiorId').combobox({
		url:'getMarketEventType.action?eventTypeId=0'
	});
	
	function editMktEvt(id){	
		window.location.href='showMktEvtInfo.action?mktEvt.id='+id;			
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
			field : 'status', 
			title : '实施状态',
			width : 60,
			formatter : function(value, rec) {
				if('Y'==value){
					return '已实施';
				}
				return '未实施';
			}
		},{
			field : 'subject',
			title : '主题',
			width : 150,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		},{
			field : 'sysCompUserName',
			title : '我方人员',
			width : 150,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		},{
			field : 'compCustName',
			title : '参与机构(所属公司/客户名称)',
			width : 250,
			formatter : function(value, rec) {				
				return cutstr(value,30);
			}
		}]];			
	
		//		
		showDatagrid('grid-datalist','getMktEvtList.action',frozenColumns,columns);
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