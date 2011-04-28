<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" defer="defer">
<!--
	$("#_add").click(function() {
		document.getElementById('div_info').style.display='inline';
		document.getElementById('div_search').style.display='none';
		$('#_delete').linkbutton('disable');
	});	
	
	$("#_back").click(function() {
		document.getElementById('div_info').style.display='none';
		document.getElementById('div_search').style.display='inline';
		$('#_delete').linkbutton('enable');
	});	
	
	$("#_save").click(function() {
		var isValid = $('#infoForm').form('validate');	
		if (isValid) {
			var options = {
				url : 'saveCustInfo.action',
				dataType : 'json',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {
						resetForm('infoForm');
						$("#_back").click();
					}
				}
			};
			$('#infoForm').ajaxSubmit(options);
		}
	});
	
//-->
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<s:hidden id="induId" name="induId" />
<div id="div_info" style="margin-top: 10px; display: none;">
	<s:hidden id="induId" name="induId" />
	<form id="infoForm" name="infoForm">
		<table cellpadding="0" cellspacing="0" width="60%" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center">
					客户名称:
				</td>
				<td>
					<input type="text" name="cust.custName" class="easyui-validatebox"
						required="true" validType="length[1,50]">
				</td>
				<td nowrap="nowrap" align="center">
					客户编码:
				</td>
				<td>
					<input type="text" name="cust.custCode" maxlength="20">
				</td>
				<td nowrap="nowrap" align="center">
					行业:
				</td>
				<td>
					<input type="text" name="cust.industryId" />
				</td>
			</tr>

			<tr height="30px">
				<td nowrap="nowrap" align="center">
					电话:
				</td>
				<td>
					<input type="text" name="cust.phone" class="easyui-validatebox"
						required="true" validType="mobile" maxlength="40">
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
						style="width: 415px;" />
				</td>
				<td nowrap="nowrap" align="center">
					邮编:
				</td>
				<td>
					<input type="text" name="cust.postcode" maxlength="10">
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					简介:
				</td>
				<td colspan="3">
					<textarea name="cust.descript" rows="5" style="width: 415px;"
						class="easyui-validatebox" validType="length[0,500]"> 
					</textarea>
				</td>
				<td nowrap="nowrap" align="center">
					邮箱:
				</td>
				<td>
					<input type="text" name="cust.email" maxlength="40"
						class="easyui-validatebox" validType="email">
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center">
					备注:
				</td>
				<td colspan="3">
					<textarea name="cust.remark" rows="5" style="width: 415px;"
						class="easyui-validatebox" validType="length[0,500]"> 				
					</textarea>
				</td>
				<td colspan="2" align="right">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td colspan="6" align="right">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" onclick="resetForm('infoForm');">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back">返回</a>

				</td>
			</tr>
		</table>
	</form>
</div>

<div id="div_search" style="margin-top: 10px; display: inline;">
	<form id="searchForm" name="searchForm">
		sss
	</form>
	<div style="height: 30px;">
		&nbsp;
	</div>
	<div align="left">
		<table id="grid-datalist"></table>
	</div>
</div>

