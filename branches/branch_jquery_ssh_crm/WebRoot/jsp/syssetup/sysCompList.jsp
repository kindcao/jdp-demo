<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'companyName',
					title : '公司名称',
					width : 200,
					sortable : true,			
					formatter : function(value, rec) {
						return "<a href='javascript:void(0);' onclick='editUser(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
		    field : 'type',
			title : '公司类型',
			width : 100,
			formatter : function(value, rec) {				
				if(value=='R'){
					value='融聚公司';
				}else{
					value='其它公司';
				}
				return value;
			}
		}, {
			field : 'status',
			title : '公司状态',
			width : 100,
			formatter : function(value, rec) {
				if(value=='A'){
					value='正常';
				}else{
					value='禁用';
				}
				return value;
			}
		}, {
			field : 'logo',
			title : '公司Logo图URI',
			width : 200,
			formatter : function(value, rec) {				
				return "<a href='" + value + "'>" + value + "</a>";			
			}
		}, {
			field : 'descript',
			title : '备注',
			width : 250
		}]];
			
		//		
		showDatagrid('grid-datalist','getCompList.action',frozenColumns,columns);
		//
		$("#_search").click(function() {		
			var queryParams = $('#grid-datalist').datagrid('options').queryParams;	
		    queryParams.companyName = $("#companyName").val();
		    queryParams.status = $('#status').combobox('getValue'); 
		    reloadDatagrid('grid-datalist');
		});		
		//
		$('#_delete').linkbutton('disable');
		
		//for add begin
		$("#_add").click(function() {
			document.getElementById('div_info').style.display='inline';
			document.getElementById('div_search').style.display='none';
			//$('#_delete').linkbutton('disable');	
			$("#_reset").click();	
		});	
		
		$("#_back").click(function() {
			document.getElementById('div_info').style.display='none';
			document.getElementById('div_search').style.display='inline';
			//$('#_delete').linkbutton('enable');
			$("#_reset").click();
		});	
		
		$("#_save").click(function() {
			var isValid = $('#infoForm').form('validate');	
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
							$("#_back").click();
						}
					}
				};
				$('#infoForm').ajaxSubmit(options);
			}
		});
		
		$("#_reset").click(function() {	
			resetForm('infoForm');
		});	
		//for add end
		
	});	
//-->
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
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
			<tr height="30px" valign="top">			
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
	<form id="searchFrom" name="searchFrom">
		<fieldset>
			<legend>
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="60%" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td nowrap="nowrap" align="center">
						公司名称:
					</td>
					<td>
						<input id="companyName" name="companyName" type="text"
							maxlength="50" />
					</td>
					<td nowrap="nowrap" align="center">
						公司状态:
					</td>
					<td>
						<select id="status" name="status" class="easyui-combobox"
							panelHeight="auto">
							<option value="">
								---请选择---
							</option>
							<option value="A">
								正常
							</option>
							<option value="D">
								禁用
							</option>
						</select>
					</td>
				</tr>
				<tr height="30px">
					<td colspan="3">
						&nbsp;
					</td>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton"
							plain="true" iconCls="icon-search" id="_search">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
							plain="true" iconCls="icon-remove"
							onclick="resetForm('searchFrom');">重置</a>
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
