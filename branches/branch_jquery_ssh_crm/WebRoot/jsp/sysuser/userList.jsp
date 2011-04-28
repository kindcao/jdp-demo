<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" defer="defer">	
	$(document).ready(function() {
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'id',
					title : 'ID',
					width : 80,
					sortable : true
				}]];
		var columns = [[{
			field : 'username',
			title : 'User Name',
			width : 120,
			sortable : true,
			formatter : function(value, rec) {
				return "<a href='javascript:void(0);' onclick='editUser(" + rec.id+ ");'>" + value + "</a>";
			}
		}, {
			field : 'password',
			title : 'Password',
			width : 120
		}, {
			field : 'email',
			title : 'email',
			width : 120
		}]];
			
		//
		showDatagrid('grid-datalist','getUserList.action',frozenColumns,columns);
	});				
				
	$("#_delete").click(function() {
		 deleteRecord('grid-datalist', 'delUser.action');
	});	
	
	$("#_search").click(function() {			
		var queryParams = $('#grid-datalist').datagrid('options').queryParams;		   
	    queryParams.username = $("#username").val();
	    queryParams.password = $("#password").val();  
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
			<table cellpadding="0" cellspacing="0" width="60%" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td nowrap="nowrap" align="center">
						用户名称:
					</td>
					<td>
						<input type="text" name="username" />
					</td>
					<td nowrap="nowrap" align="center">
						登录密码:
					</td>
					<td>
						<input type="text" name="password" />
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
</div>
<div style="height: 30px;">
	&nbsp;
</div>
<div align="left">
	<table id="grid-datalist"></table>
</div>
