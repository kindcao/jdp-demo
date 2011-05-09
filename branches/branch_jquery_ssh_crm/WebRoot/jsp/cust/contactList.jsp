<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<s:hidden id="induId" name="induId" />
<s:hidden id="custId" name="cust.id" />
<div id="div_info_contact" style="margin-top: 10px; display: none;">
	<form id="infoFormContact" name="infoFormContact">
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					客户名称:
				</td>
				<td width="20%">
					<input type="text" name="cust.custName" class="easyui-validatebox"
						required="true" validType="length[1,50]">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					客户编码:
				</td>
				<td width="20%">
					<input type="text" name="cust.custCode" maxlength="20">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					行业:
				</td>
				<td width="20%">
					<input id="cust_indu" class="easyui-combobox"
						name="cust.industryId" required="true" url="" valueField="id"
						textField="name" multiple="false" editable="false"
						panelHeight="auto" style="width: 135px;">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					所属公司:
				</td>
				<td colspan="3">
					<input id="cust_sys_comp" class="easyui-combobox"
						name="custSysCompIds" required="true" url="getSysComp.action"
						valueField="id" textField="companyName" multiple="true"
						editable="false" panelHeight="auto" style="width: 400px;">
				</td>
				<td nowrap="nowrap" align="center">
					电话:
				</td>
				<td>
					<input type="text" name="cust.phone" class="easyui-validatebox"
						required="true" validType="phone" maxlength="40">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					邮箱:
				</td>
				<td>
					<input type="text" name="cust.email" maxlength="40"
						class="easyui-validatebox" validType="email">
				</td>
				<td nowrap="nowrap" align="center">
					传真:
				</td>
				<td>
					<input type="text" name="cust.fax" maxlength="40">
				</td>
				<td nowrap="nowrap" align="center">
					网站:
				</td>
				<td>
					<input type="text" name="cust.website" class="easyui-validatebox"
						validType="url" maxlength="50">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					国家:
				</td>
				<td>
					<input type="text" name="cust.country" maxlength="20">
				</td>
				<td nowrap="nowrap" align="center">
					省份:
				</td>
				<td>
					<input type="text" name="cust.province" maxlength="20">
				</td>
				<td nowrap="nowrap" align="center">
					城市:
				</td>
				<td>
					<input type="text" name="cust.city" maxlength="20">
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					地址:
				</td>
				<td colspan="3">
					<input type="text" name="cust.address" maxlength="200"
						style="width: 402px;" />
				</td>
				<td nowrap="nowrap" align="center">
					邮编:
				</td>
				<td>
					<input type="text" name="cust.postcode" class="easyui-validatebox"
						validType="postcode" maxlength="10">
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					简介:
				</td>
				<td colspan="3">
					<textarea name="cust.descript" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,500]"> 
					</textarea>
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea name="cust.remark" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,500]"> 				
					</textarea>
				</td>
				<td colspan="2" align="center" valign="bottom">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_contact">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="div_search_contact" style="display: inline;">
	<form id="searchForm" name="searchForm">
		<fieldset style="margin-top: 5px;">
			<legend>
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td nowrap="nowrap" align="center" width="10%">
						客户名称:
					</td>
					<td width="20%">
						<input type="text" id="custName" name="custName"
							class="easyui-validatebox" validType="length[1,50]">
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						客户编码:
					</td>
					<td width="20%">
						<input type="text" id="custCode" name="custCode" maxlength="20">
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						行业:
					</td>
					<td width="20%">
						<input id="cust_indu_search" class="easyui-combobox"
							name="industryId" url="" valueField="id" textField="name"
							multiple="false" editable="false" panelHeight="auto"
							style="width: 135px;">
					</td>
				</tr>
				<tr height="30px">
					<td nowrap="nowrap" align="center">
						所属公司:
					</td>
					<td colspan="3">
						<input id="cust_sys_comp_search" class="easyui-combobox"
							name="custSysCompIds" url="getSysComp.action" valueField="id"
							textField="companyName" multiple="true" editable="false"
							panelHeight="auto" style="width: 400px;">
					</td>
				</tr>
				<tr height="30px">

					<td nowrap="nowrap" align="center">
						地址:
					</td>
					<td colspan="3">
						<input type="text" id="address" name="address" maxlength="200"
							style="width: 402px;" />
					</td>
				</tr>
				<tr height="30px">
					<td colspan="5">
						&nbsp;
					</td>
					<td align="right">
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
		document.getElementById('div_info_contact').style.display='inline';
		document.getElementById('div_search_contact').style.display='none';
		$('#_delete').linkbutton('disable');	
		$("#_reset").click();	
	});	
	
	$("#_back_contact").click(function() {
		document.getElementById('div_info_contact').style.display='none';
		document.getElementById('div_search_contact').style.display='inline';
		$('#_delete').linkbutton('enable');
		$("#_reset").click();
	});	
	
	$("#_save").click(function() {
		var isValid = $('#infoFormContact').form('validate');	
		if (isValid) {
			var options = {
				url : 'saveCustInfo.action',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {						
						$("#_back_contact").click();
						reloadDatagrid('grid-datalist');
					}
				}
			};
			$('#infoFormContact').ajaxSubmit(options);
		}
	});
	
	$("#_reset").click(function() {
		resetForm('infoFormContact');
	});	
	
	//for add end
	
	//for delete begin
	$("#_delete").click(function() {
		deleteRecord('grid-datalist','deleteCust.action');
	});
	//fore delte end
	
	//for search begin	
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
	
	//for search end	
    function editComp(cId){    	
		window.location.href='showCustInfo.action?induId='+$('#induId').val()+'&cust.id='+cId;
	}	
//-->
</script>
