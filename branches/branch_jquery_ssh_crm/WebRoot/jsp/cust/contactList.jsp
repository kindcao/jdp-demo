<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info_contact" style="margin-top: 10px; display: none;">
	<form id="infoFormContact" name="infoFormContact">
		<s:hidden name="actionFlag" value="" />
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td class="label-title" width="10%">
					姓名：
				</td>
				<td width="20%">
					<input type="text" name="cont.name" class="easyui-validatebox"
						required="true" validType="length[1,40]">
				</td>
				<td class="label-title" width="10%">
					部门：
				</td>
				<td width="20%">
					<input type="text" name="cont.department" maxlength="40">
				</td>
				<td class="label-title" width="10%">
					职位：
				</td>
				<td width="20%">
					<input type="text" name="cont.posit" maxlength="40">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					座机：
				</td>
				<td>
					<input type="text" name="cont.phone" class="easyui-validatebox"
						validType="phone" maxlength="40">
				</td>
				<td class="label-title">
					传真：
				</td>
				<td>
					<input type="text" name="cont.fax" maxlength="40">
				</td>
				<td class="label-title">
					移动电话：
				</td>
				<td>
					<input type="text" name="cont.mobile" class="easyui-validatebox"
						validType="mobile" maxlength="40">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					邮箱：
				</td>
				<td>
					<input type="text" name="cont.email" maxlength="50"
						class="easyui-validatebox" validType="email">
				</td>
				<td class="label-title">
					是否主要：
				</td>
				<td>
					<input id="cont.isPrimary" name="cont.isPrimary"
						class="easyui-combobox" url="getStatusYN.action" valueField="id"
						textField="text" panelHeight="auto" editable="false"
						style="width: 159px;">
				</td>
				<td class="label-title">
					MSN/QQ：
				</td>
				<td>
					<input type="text" name="cont.im" maxlength="50">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					照片URI：
				</td>
				<td colspan="3">
					<input type="text" name="cont.picture" maxlength="100"
						style="width: 428px;" class="easyui-validatebox" validType="url" />
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					地址：
				</td>
				<td colspan="3">
					<input type="text" name="cont.address" maxlength="200"
						style="width: 428px;" />
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					备注：
				</td>
				<td colspan="3">
					<textarea name="cont.remark" rows="5" style="width: 428px;"
						class="easyui-validatebox" validType="length[0,500]"></textarea>
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
<div id="div_search_contact" style="display: inline;">
	<form id="searchFormContact" name="searchFormContact">
		<fieldset>
			<legend>
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td class="label-title" width="10%">
						姓名：
					</td>
					<td width="20%">
						<input type="text" id="name" name="name" maxlength="40">
					</td>
					<td class="label-title" width="10%">
						职位：
					</td>
					<td width="20%">
						<input type="text" id="posit" name="posit" maxlength="40">
					</td>
					<td class="label-title">
						是否主要：
					</td>
					<td>
						<input id="isPrimary" name="isPrimary" class="easyui-combobox"
							url="getStatusYN.action" valueField="id" textField="text"
							panelHeight="auto" editable="false" style="width: 159px;">
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title">
						地址：
					</td>
					<td colspan="3">
						<input type="text" id="address" name="address" maxlength="200"
							style="width: 400px;" />
					</td>
					<td colspan="2" align="center">
						&nbsp;
					</td>
				</tr>
				<tr height="30px">
					<td colspan="5">
						&nbsp;
					</td>
					<td align="center">
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" id="_search_contact">查询</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-remove" id="_reset_search_contact">重置</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	<div style="height: 30px;">
		&nbsp;
	</div>
	<div align="left">
		<table id="grid-datalist-contact"></table>
	</div>
</div>

<script type="text/javascript" defer="defer">
<!--	
	//for add begin			
	$("#_add").click(function() {
		document.getElementById('div_info_contact').style.display='inline';
		document.getElementById('div_search_contact').style.display='none';
		$('#_delete').linkbutton('disable');	
		$("#_reset_contact").click();	
	});	
	
	$("#_back_contact").click(function() {
		document.getElementById('div_info_contact').style.display='none';
		document.getElementById('div_search_contact').style.display='inline';
		$('#_delete').linkbutton('enable');
		$("#_reset_contact").click();
	});	
	
	$("#_save_contact").click(function() {
		$('#infoFormContact').submitForm(
			'saveContInfo.action',
			true,
			function(){
				$("#_back_contact").click();
				$("#_search_contact").click();
			}
		);		
	});
	
	$("#_reset_contact").click(function() {
		$('#cont.isPrimary').combobox('clear');
		resetForm('infoFormContact');
	});	
	//for add end
	
	//for delete begin
	$("#_delete").click(function() {
		deleteRecord('grid-datalist-contact','deleteCont.action');
	});
	//fore delte end
	
	//for search begin	
	$('#_reset_search_contact').click(function() {
		$('#isPrimary').combobox('clear');
		resetForm('searchFormContact');
	});	
	
	$("#_search_contact").click(function() {		
		var queryParams = $('#grid-datalist-contact').datagrid('options').queryParams;	
		queryParams.name = $("#name").val();
		queryParams.posit = $("#posit").val();	
		queryParams.address = $("#address").val();	
		queryParams.isPrimary = $("#isPrimary").combobox('getValue');
		reloadDatagrid('grid-datalist-contact');
	});	
	
	function editCont(id){	
		var tab = $('#tabs-container').tabs('getSelected');	
		$('#tabs-container').tabs('update', {
			tab: tab,
			options:{
				href:'showContInfo.action?cont.id='+id			
			}
		});			
	}
		
	//	
	$(document).ready(function() {		
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'name',
					title : '姓名',
					width : 120,
					sortable : false,			
					formatter : function(value, rec) {
						return "<a href='#' onclick='editCont(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
		    field : 'department',
			title : '部门',
			width : 100
		},{
			field : 'posit',
			title : '职位',
			width : 100
		},{
			field : 'phone',
			title : '座机',
			width : 120
		}, {
			field : 'mobile',
			title : '移动电话',
			width : 120
		},{
			field : 'email',
			title : '邮箱',
			width : 120
		},{
			field : 'address',
			title : '联系地址',
			width : 200,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		}, {
			field : 'isPrimary',
			title : '是否主要',
			width : 100,
			formatter : function(value, rec) {			
				if(value=='Y'){
					return '是';
				}else{
					return '否';
				}
			}
		}]];
			
		//		
		showDatagrid('grid-datalist-contact','getContList.action',frozenColumns,columns);				
	});	
	//for search end
//-->
</script>
