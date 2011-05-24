<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
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
						时段:
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
						所属公司:
					</td>
					<td width="30%">
						<input id="countExtDto_sysCompIds" class="easyui-combobox"
							name="countExtDto.sysCompIds" url="getSysComp.action"
							valueField="id" textField="companyName" multiple="true"
							editable="false" panelHeight="auto" style="width: 250px;">
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
	$(document).ready(function() {
		$('#_add').linkbutton('disable');
		$('#_delete').linkbutton('disable');		
	});
//-->
</script>