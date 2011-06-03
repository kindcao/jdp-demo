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
					发布日期：
				</td>
				<td width="20%">
					<input type="text" id="industryExtDto_publishDateStr"
						name="industryExtDto.publishDateStr" class="easyui-datebox"
						required="true">
				</td>
				<td class="label-title" width="10%">
					属性：
				</td>
				<td width="20%">
					<input id="industryExtDto_industryNewsTypeId"
						name="industryExtDto.industryNewsTypeId" class="easyui-combobox"
						required="true" url="getIndustryNewsType.action" valueField="id"
						textField="name" editable="false" panelHeight="auto"
						style="width: 157px;">
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title" width="10%">
					相关机构：
				</td>
				<td colspan="3">
					<input id="industryExtDto_customerId"
						name="industryExtDto.customerId" class="easyui-combobox"
						required="true" url="getCustNameList.action" valueField="id"
						textField="custName" editable="false" panelHeight="100"
						style="width: 398px;">
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					网址：
				</td>
				<td colspan="3">
					<input type="text" name="industryExtDto.url"
						class="easyui-validatebox" validType="url" maxlength="200"
						style="width: 400px;">
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title">
					标题：
				</td>
				<td colspan="3">
					<input type="text" name="industryExtDto.subject" maxlength="400"
						class="easyui-validatebox" required="true" style="width: 400px;" />
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title">
					相关资料：
				</td>
				<td colspan="3">
					<textarea name="industryExtDto.content" rows="5"
						style="width: 400px;" class="easyui-validatebox"
						validType="length[0,6000]"></textarea>
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
		<fieldset>
			<legend>
				查询条件
			</legend>
			<table cellpadding="0" cellspacing="0" width="800" border="0"
				style="margin: 10px;">
				<tr height="30px">
					<td class="label-title" width="10%">
						发布日期：
					</td>
					<td width="20%">
						<input type="text" id="industryExtDto_publishDateStrBegin"
							name="industryExtDto.publishDateStrBegin" class="easyui-datebox">
					</td>
					<td class="label-title" width="10%">
						至：
					</td>
					<td width="20%">
						<input type="text" id="industryExtDto_publishDateStrEnd"
							name="industryExtDto.publishDateStrEnd" class="easyui-datebox">
					</td>
					<td class="label-title" width="10%">
						属性：
					</td>
					<td width="20%">
						<input id="industryExtDto_industryNewsTypeId_search"
							name="industryExtDto.industryNewsTypeId" class="easyui-combobox"
							url="getIndustryNewsType.action" valueField="id" textField="name"
							editable="false" panelHeight="auto" style="width: 160px;">
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title">
						相关机构：
					</td>
					<td colspan="3">
						<input id="industryExtDto_customerId_search"
							name="industryExtDto.customerId" class="easyui-combobox"
							url="getCustNameList.action" valueField="id" textField="custName"
							editable="false" panelHeight="100" style="width: 427px;">
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title">
						标题：
					</td>
					<td colspan="3">
						<input type="text" id="industryExtDto_subject"
							name="industryExtDto.subject" maxlength="400"
							style="width: 429px;" />
					</td>
					<td>
						&nbsp;
					</td>
					<td align="center">
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
			'saveIndustryInfo.action',
			true,
			function(){
				$("#_back").click();
				$("#_search").click();
			}
		);		
	});
	
	$("#_reset").click(function() {
		$("#industryExtDto_publishDateStr").datebox('clear');
		$('#industryExtDto_industryNewsTypeId').combobox('clear');
		$('#industryExtDto_customerId').combobox('clear');
		resetForm('infoForm');
	});	
	//for add end
	
	//for delete begin
	$("#_delete").click(function() {
		deleteRecord('grid-datalist','deleteIndustry.action');
	});
	//fore delte end
	
	//for search begin	
	$("#_reset_search").click(function() {		
		$("#industryExtDto_publishDateStrBegin").datebox('clear');
		$('#industryExtDto_publishDateStrEnd').datebox('clear');	
		$('#industryExtDto_industryNewsTypeId_search').combobox('clear');		
		$('#industryExtDto_customerId_search').combobox('clear');
		resetForm('searchForm');
	});	
	
	$("#_search").click(function() {		
		var queryParams = $('#grid-datalist').datagrid('options').queryParams={			
			'industryExtDto.publishDateStrBegin':$("#industryExtDto_publishDateStrBegin").datebox('getValue'),
			'industryExtDto.publishDateStrEnd':$("#industryExtDto_publishDateStrEnd").datebox('getValue'),
			'industryExtDto.industryNewsTypeId':$("#industryExtDto_industryNewsTypeId_search").combobox('getValue'),
			'industryExtDto.customerId':$("#industryExtDto_customerId_search").combobox('getValue'),
			'industryExtDto.subject':$('#industryExtDto_subject').val()
		};	
		reloadDatagrid('grid-datalist');
	});	
	
	function editIndustry(id){	
		window.location.href='showIndustryInfo.action?industryExtDto.id='+id;
	}
		
	//	
	$(document).ready(function() {		
		var frozenColumns = [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'publishDateStr',
					title : '发布日期',
					width : 100,
					sortable : false,			
					formatter : function(value, rec) {
						return "<a href='#' onclick='editIndustry(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
		    field : 'induNewsTypeName',
			title : '属性',
			width : 100
		},{
			field : 'custName',
			title : '相关机构',
			width : 150
		},{
			field : 'url',
			title : '网址',
			width : 200,
			formatter : function(value, rec) {				
				if(value){	
					return "<a href='#' onclick=window.open('" + value+ "');>" + cutstr(value,30) + "</a>";
				}
			}
		},{
			field : 'subject',
			title : '标题',
			width : 300,
			formatter : function(value, rec) {				
				return cutstr(value,50);
			}
		}]];
			
		//		
		showDatagrid('grid-datalist','getIndustryList.action',frozenColumns,columns);					
	});	
	//for search end  
//-->
</script>