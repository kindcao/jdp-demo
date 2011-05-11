<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" defer="defer">	
	$(document).ready(function() {
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'name',
					title : '用户姓名',
					width : 120,
					sortable : true,
					formatter : function(value, rec) {
						return "<a href='javascript:void(0);' onclick='editUser(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
			field : 'loginId',
			title : '登录账号',
			width : 120			
		}, {
			field : 'passwd',
			title : '登录密码',
			width : 120
		}, {
			field : 'email',
			title : '用户邮箱',
			width : 150
		}, {
			field : 'status',
			title : '用户状态',
			width : 100,
			formatter : function(value, rec) {
				if(value=='A'){
					value='正常';
				}else{
					value='禁用';
				}
				return value;
			}
		},{
			field : 'deleteFlag',
			title : '删除标记',
			width : 100,
			formatter : function(value, rec) {
				if(value=='Y'){
					value='已删除';
				}else{
					value='未删除';
				}
				return value;
			}
		}]];
					
		//
		showDatagrid('grid-datalist','getUserList.action',frozenColumns,columns);
	});				
				
	$("#_delete").click(function() {
		 deleteRecord('grid-datalist', 'delUser.action');
	});	
	
	$("#_search").click(function() {			
		var queryParams = $('#grid-datalist').datagrid('options').queryParams;
		queryParams.name = $("#name").val();   
	    queryParams.loginId = $("#loginId").val();
	   queryParams.status = $('#status').combobox('getValue');
	    reloadDatagrid('grid-datalist');
	});		
		//	
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div style="margin-top: 10px;">
	<form id="searchFrom" name="searchFrom">
		<fieldset>
			<legend>
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td nowrap="nowrap" align="center" width="10%">
						用户名称:
					</td>
					<td width="20%">
						<input type="text" id="name" name="name" />
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						登录账号:
					</td>
					<td width="20%">
						<input type="text" id="loginId" name="loginId" />
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						用户状态:
					</td>
					<td width="20%">
						<select id="status" name="status" class="easyui-combobox"
							panelHeight="auto" editable="false">
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
					<td colspan="5">
						&nbsp;
					</td>
					<td align="right">
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
</div>
<div style="height: 30px;">
	&nbsp;
</div>
<div align="left">
	<table id="grid-datalist"></table>
</div>
