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
					value='其他公司';
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
		$('#_delete').linkbutton('disable');
	});	
//-->
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
						公司名称:
					</td>
					<td>
						<input type="text" name="sysComp.companyName" />
					</td>
					<td nowrap="nowrap" align="center">
						公司状态:
					</td>
					<td>
						<input type="text" name="sysComp.status" />
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