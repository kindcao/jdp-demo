<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
		<s:hidden name="actionFlag" value="" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td class="label-title" width="10%">
					客户名称：
				</td>
				<td width="20%">
					<input type="text" name="cust.custName" class="easyui-validatebox"
						required="true" validType="length[1,50]">
				</td>
				<td class="label-title" width="10%">
					简称：
				</td>
				<td width="20%">
					<input type="text" name="cust.shortName" class="easyui-validatebox"
						required="true" validType="length[1,50]">
				</td>
				<td class="label-title" width="10%">
					客户编码：
				</td>
				<td width="20%">
					<input type="text" name="cust.custCode" maxlength="20">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					所属公司：
				</td>
				<td colspan="3">
					<input id="cust_sys_comp" class="easyui-combobox"
						name="custSysCompIds" required="true" url="getSysComp.action"
						valueField="id" textField="companyName" multiple="true"
						editable="false" panelHeight="auto" style="width: 425px;">
				</td>
				<td class="label-title">
					行业：
				</td>
				<td>
					<input id="cust_indu" class="easyui-combobox"
						name="cust.industryId" required="true" url="getCustIndu.action"
						valueField="id" textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 159px;">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					电话：
				</td>
				<td>
					<input type="text" name="cust.phone" class="easyui-validatebox"
						required="true" validType="phone" maxlength="40">
				</td>
				<td class="label-title">
					传真：
				</td>
				<td>
					<input type="text" name="cust.fax" maxlength="40">
				</td>
				<td class="label-title">
					邮箱：
				</td>
				<td>
					<input type="text" name="cust.email" maxlength="40"
						class="easyui-validatebox" validType="email">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					国家：
				</td>
				<td>
					<input type="text" name="cust.country" maxlength="20">
				</td>
				<td class="label-title">
					省份：
				</td>
				<td>
					<input type="text" name="cust.province" maxlength="20">
				</td>
				<td class="label-title">
					城市：
				</td>
				<td>
					<input type="text" name="cust.city" maxlength="20">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					地址：
				</td>
				<td colspan="3">
					<input type="text" name="cust.address" maxlength="200"
						style="width: 427px;" />
				</td>
				<td class="label-title">
					邮编：
				</td>
				<td>
					<input type="text" name="cust.postcode" class="easyui-validatebox"
						validType="postcode" maxlength="10">
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					简介：
				</td>
				<td colspan="3">
					<textarea name="cust.descript" rows="5" style="width: 427px;"
						class="easyui-validatebox" validType="length[0,500]"></textarea>
				</td>
				<td class="label-title">
					网站：
				</td>
				<td>
					<input type="text" name="cust.website" class="easyui-validatebox"
						validType="url" maxlength="50">
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					备注：
				</td>
				<td colspan="3">
					<textarea name="cust.remark" rows="5" style="width: 427px;"
						class="easyui-validatebox" validType="length[0,500]"> 				
					</textarea>
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
		<fieldset style="margin-top: 10px;">
			<legend>
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td class="label-title" width="10%">
						客户名称：
					</td>
					<td width="20%">
						<input type="text" id="custName" name="custName"
							class="easyui-validatebox" validType="length[1,50]">
					</td>
					<td class="label-title" width="10%">
						客户编码：
					</td>
					<td width="20%">
						<input type="text" id="custCode" name="custCode" maxlength="20">
					</td>
					<td class="label-title" width="10%">
						行业：
					</td>
					<td width="20%">
						<input id="cust_indu_search" class="easyui-combobox"
							name="industryId" url="getCustIndu.action" valueField="id"
							textField="name" multiple="false" editable="false"
							panelHeight="auto" style="width: 159px;">
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title">
						所属公司：
					</td>
					<td colspan="3">
						<input id="cust_sys_comp_search" class="easyui-combobox"
							name="custSysCompIds" url="getSysComp.action" valueField="id"
							textField="companyName" multiple="true" editable="false"
							panelHeight="auto" style="width: 424px;">
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title">
						地址：
					</td>
					<td colspan="3">
						<input type="text" id="address" name="address" maxlength="200"
							style="width: 426px;" />
					</td>
					<td colspan="2" align="center" valign="middle">
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
		$('#infoForm').submitForm(
			'saveCustInfo.action',
			true,
			function(){
				$("#_back").click();
				$("#_search").click();
			}
		);		
	});
	
	$("#_reset").click(function() {
		$('#cust_indu').combobox('clear');		
		$('#cust_sys_comp').combobox('clear');
		//$('#cust_sys_user').combobox('clear');
		//$('#cust_sys_prim_user').combobox('clear');
		resetForm('infoForm');
	});		
	
	/**
	$('#cust_sys_comp').combobox({
		url:'getSysComp.action',		
		onChange:function(rec){
		 	var _sysUserIds=$(this).combobox("getValues");		 	
		 	$('#cust_sys_user').combobox({
		 		url:'getSysCompUserByCompIds?sysCompIds='+_sysUserIds
		 	}).combobox('clear');
		}
	});	
	
	$('#cust_sys_user').combobox({
	    onChange:function(rec){
	    	var _sysUserIds=$(this).combobox("getValues");		 	
		 	$('#cust_sys_prim_user').combobox({
		 		url:'getSysCompUserByUserIds?sysUserIds='+_sysUserIds
		 	}).combobox('clear');
	    }
	});	**/
	//for add end
	
	//for delete begin
	$("#_delete").click(function() {
		deleteRecord('grid-datalist','deleteCust.action');
	});
	//fore delte end
	
	//for search begin	
	/**
	$('#cust_sys_comp_search').combobox({
		url:'getSysComp.action',	
		onChange:function(rec){
		 	var _sysUserIds=$(this).combobox("getValues");		 	
		 	$('#cust_sys_user_search').combobox({
		 		url:'getSysCompUserByCompIds?sysCompIds='+_sysUserIds
		 	}).combobox('clear');
		}
	});	**/
	
	$("#_reset_search").click(function() {
		$('#cust_indu_search').combobox('clear');		
		$('#cust_sys_comp_search').combobox('clear');		
		//$('#cust_sys_user_search').combobox('clear');
		resetForm('searchForm');
	});	
	
	$("#_search").click(function() {		
		var queryParams = $('#grid-datalist').datagrid('options').queryParams;	
		queryParams.custName = $("#custName").val();
		queryParams.custCode = $("#custCode").val();
		queryParams.address = $("#address").val();
		queryParams.industryId = $("#cust_indu_search").combobox('getValue');
		queryParams.custSysCompIds = $('#cust_sys_comp_search').combobox('getValues')+'';		
		reloadDatagrid('grid-datalist');
	});	
	
	function editComp(id){		
		window.location.href='showCustInfo.action?cust.id='+id;
	}	
		
	//	
	$(document).ready(function() {		
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'custName',
					title : '客户名称',
					width : 200,
					sortable : false,			
					formatter : function(value, rec) {
						return "<a href='#' onclick='editComp(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
		    field : 'custCode',
			title : '公司编码',
			width : 100
		},{
			field : 'companyName',
			title : '所属公司',
			width : 100
		},{
			field : 'industryName',
			title : '行业',
			width : 100
		}, {
			field : 'phone',
			title : '电话',
			width : 150
		}, {
			field : 'address',
			title : '联系地址',
			width : 200,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		}, {
			field : 'contactName',
			title : '联系人',
			width : 150,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		}]];
			
		//		
		showDatagrid('grid-datalist','getCustList.action',frozenColumns,columns);		
	});	
	//for search end  
//-->
</script>
