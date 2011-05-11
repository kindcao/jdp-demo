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
						return "<a href='javascript:void(0);' onclick='editComp(" + rec.id+ ");'>" + value + "</a>";
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
			width : 250,
			formatter : function(value, rec) {					
				return "<a href='#' onclick=viewLogo('"+value+"')>" + value + "</a>";							
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
		
		$('#div-log-img').dialog({
			title:'Logo图片预览',
			modal:true,			
			buttons:[{
				text:'Ok',
				iconCls:'icon-ok',
				handler:function(){
					$('#div-log-img').dialog('close');
				}
			}]
		});
		$('#div-log-img').dialog('close');
		$('#_delete').linkbutton('disable');
		//	
		
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
					url : 'saveSysCompInfo.action',
					dataType : 'json',
					type: 'post',
					//contentType:'application/x-www-form-urlencoded; charset=utf-8',
					success : function(data){
						if (!data.success) {
							$.messager.alert('提示信息', data.errors, 'error');
						} else {						
							$("#_back").click();
							reloadDatagrid('grid-datalist');
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
	
	
	function viewLogo(logoUrl){
		if(logoUrl){
			document.getElementById('logoImg').src=logoUrl;
			$('#div-log-img').dialog('open');
		}		
	}
	
	function editComp(id){
		alert(id);
	}
	
//-->
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
		<table cellpadding="0" cellspacing="0" width="800" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					公司名称:
				</td>
				<td width="20%">
					<input type="text" name="sysComp.companyName"
						class="easyui-validatebox" required="true"
						validType="length[1,50]">
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					公司类型:
				</td>
				<td width="20%">
					<select id="sysComp.type" name="sysComp.type"
						class="easyui-combobox" panelHeight="auto" required="true"
						editable="false">
						<option value="R">
							融聚公司
						</option>
						<option value="O">
							其它公司
						</option>
					</select>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					公司状态:
				</td>
				<td width="20%">
					<select id="sysComp.status" name="sysComp.status"
						class="easyui-combobox" panelHeight="auto" required="true"
						editable="false">
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
				<td nowrap="nowrap" align="center">
					Logo图URI:
				</td>
				<td colspan="3">
					<input type="text" name="sysComp.logo" style="width: 402px;"
						maxlength="100" class="easyui-validatebox" required="true"
						validType="url" />
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
					<textarea name="sysComp.descript" rows="5" style="width: 402px;"
						class="easyui-validatebox" validType="length[0,100]"> 
					</textarea>
				</td>
				<td colspan="2" align="center">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td colspan="4" align="center">
					&nbsp;
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
	<form id="searchFrom" name="searchFrom">
		<fieldset>
			<legend style="margin-top: 10px;">
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td nowrap="nowrap" align="center" width="10%">
						公司名称:
					</td>
					<td width="20%">
						<input id="companyName" name="companyName" type="text"
							maxlength="50" />
					</td>
					<td nowrap="nowrap" align="center" width="10%">
						公司状态:
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
					<td colspan="2" align="center">
						&nbsp;
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
	<div id="div-log-img" icon="icon-search"
		style="padding: 5px; width: 300px; height: 200px;">
		<img id="logoImg" width="200" height="100"
			style="border: 1px #987cb9 dotted">
	</div>
	<div style="height: 30px;">
		&nbsp;
	</div>
	<div align="left">
		<table id="grid-datalist"></table>
	</div>
</div>
