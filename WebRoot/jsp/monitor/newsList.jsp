<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#infoForm table {
	margin-top: 10px;
	border: 1px solid #A4BED4;
}

#infoForm table th {
	padding-left: 10px;
	border-bottom: 1px solid #A4BED4;
	text-align: left;
}
-->
</style>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
		<s:hidden name="actionFlag" value="" />
		<table cellpadding="0" cellspacing="0" width="900">
			<tr height="30px">
				<th width="50%" colspan="2" style="border-right: 1px solid #A4BED4;">
					采访情况
				</th>
				<th width="50%" colspan="2">
					发布情况
				</th>
			</tr>
			<tr height="30px">
				<td class="label-title" width="10%">
					采访日期：
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" id="newsExtDto_interviewDateStr"
						name="newsExtDto.interviewDateStr" class="easyui-datebox"
						required="true">
				</td>
				<td class="label-title" width="10%">
					发布日期：
				</td>
				<td width="40%">
					<input type="text" id="newsExtDto_publishDateStr"
						name="newsExtDto.publishDateStr" class="easyui-datebox"
						required="true">
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title" width="10%">
					媒体：
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" name="newsExtDto.media" maxlength="200"
						style="width: 350px;" />
				</td>
				<td class="label-title" width="10%">
					标题：
				</td>
				<td width="40%">
					<input type="text" name="newsExtDto.subject" maxlength="200"
						style="width: 350px;" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title" width="10%">
					记者：
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" name="newsExtDto.reporter" maxlength="200"
						style="width: 350px;" />
				</td>
				<td class="label-title" width="10%">
					截图URI：
				</td>
				<td width="40%">
					<input type="text" name="newsExtDto.picture" maxlength="200"
						style="width: 350px;" class="easyui-validatebox"
						validType="exturl" />
				</td>
			</tr>
			<tr height="30px">
				<td class="label-title" width="10%">
					受访人：
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" name="newsExtDto.participant" maxlength="200"
						style="width: 350px;" />
				</td>
				<td class="label-title" width="10%">
					网址：
				</td>
				<td width="40%">
					<input type="text" name="newsExtDto.url" maxlength="200"
						style="width: 350px;" class="easyui-validatebox" validType="url" />
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title" width="10%">
					采访问题：
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<textarea name="newsExtDto.content" rows="5" style="width: 350px;"
						class="easyui-validatebox" validType="length[0,6000]"></textarea>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td class="label-title" width="10%">
					其它：
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<textarea name="newsExtDto.remark" rows="5" style="width: 350px;"
						class="easyui-validatebox" validType="length[0,2000]"></textarea>
				</td>
				<td>
					&nbsp;
				</td>
				<td nowrap="nowrap" align="right" valign="bottom">
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
						<input type="text" id="newsExtDto_publishDateStrBegin"
							name="newsExtDto.publishDateStrBegin" class="easyui-datebox">
					</td>
					<td class="label-title" width="10%">
						至：
					</td>
					<td width="20%">
						<input type="text" id="newsExtDto_publishDateStrEnd"
							name="newsExtDto.publishDateStrEnd" class="easyui-datebox">
					</td>
					<td colspan="2" width="30%">
						&nbsp;
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title" width="10%">
						采访日期：
					</td>
					<td width="20%">
						<input type="text" id="newsExtDto_interviewDateStrBegin"
							name="newsExtDto.interviewDateStrBegin" class="easyui-datebox">
					</td>
					<td class="label-title" width="10%">
						至：
					</td>
					<td width="20%">
						<input type="text" id="newsExtDto_interviewDateStrEnd"
							name="newsExtDto.interviewDateStrEnd" class="easyui-datebox">
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr height="30px">
					<td class="label-title" width="10%">
						标题：
					</td>
					<td colspan="4">
						<input type="text" name="newsExtDto.subject" maxlength="200"
							style="width: 428px;" id="newsExtDto_subject" />
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
	<div style="height: 10px;">
		&nbsp;
	</div>
	<div align="left">
		<table id="grid-datalist"></table>
	</div>
</div>

<script type="text/javascript">
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
			'saveNewsInfo',
			true,
			function(){
				$("#_back").click();
				$("#_search").click();
			}
		);
	});
	
	$("#_reset").click(function() {
		$("#newsExtDto_publishDateStr").datebox('clear');
		$("#newsExtDto_interviewDateStr").datebox('clear');
		resetForm('infoForm');
	});	
	
	//for delete begin
	$("#_delete").click(function() {
		deleteRecord('grid-datalist','deleteNews');
	});
	//fore delte end
	
	//for search begin	
	$("#_reset_search").click(function() {
		$("#newsExtDto_publishDateStrBegin").datebox('clear');
		$("#newsExtDto_publishDateStrEnd").datebox('clear');
		$("#newsExtDto_interviewDateStrBegin").datebox('clear');
		$("#newsExtDto_interviewDateStrEnd").datebox('clear');
		resetForm('searchForm');
	});	
	
	$("#_search").click(function() {
		$('#grid-datalist').datagrid('options').queryParams={
			'newsExtDto.subject':$('#newsExtDto_subject').val(),
			'newsExtDto.publishDateStrBegin':$("#newsExtDto_publishDateStrBegin").datebox('getValue'),
			'newsExtDto.publishDateStrEnd':$("#newsExtDto_publishDateStrEnd").datebox('getValue'),
			'newsExtDto.interviewDateStrBegin':$("#newsExtDto_interviewDateStrBegin").datebox('getValue'),
			'newsExtDto.interviewDateStrEnd':$("#newsExtDto_interviewDateStrEnd").datebox('getValue')
		};
		reloadDatagrid('grid-datalist');
	});	
	
	function editNews(id){		
		window.location.href='showNewsInfo?newsExtDto.id='+id;
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
						return "<a href='#' onclick='editNews(" + rec.id+ ");'>" + value + "</a>";
					}
				}]];
		var columns = [[{
		    field : 'interviewDateStr',
			title : '采访日期',
			width : 100
		},{
			field : 'media',
			title : '媒体',
			width : 150,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		},{
			field : 'participant',
			title : '受访人',
			width : 150,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		}, {
			field : 'reporter',
			title : '记者',
			width : 150,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
		}, {
			field : 'subject',
			title : '标题',
			width : 250,
			formatter : function(value, rec) {				
				return cutstr(value,50);
			}
		}]];
			
		//		
		showDatagrid('grid-datalist','getNewsList',frozenColumns,columns);						
	});	
</script>
