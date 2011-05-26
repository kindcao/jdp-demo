<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#div-publish-date img {
	cursor: hand;
}
-->
</style>

<script type="text/javascript">
<!--
	function getPublishData(inc){
		var labTxt=$('#_labPublishDate').text();
		var d=new Date();	
		if(labTxt){
			var arr=$('#publishView_publishDateStr').val().split('-');			
			d=new Date(arr[0],parseInt(arr[1],10)-1,1);		
			d.addMonths(inc);				
		}
		var dateStr=d.Format('yyyy-MM-dd');				
		$('#_labPublishDate').text(dateStr.substr(0,7));
		$('#publishView_publishDateStr').val(dateStr);
		
		//		
		var _url='getPublishList.action?publishView.publishDateStr='+$('#publishView_publishDateStr').val();		
		$('#grid-datalist').datagrid('options').url=_url;			
		$('#grid-datalist').datagrid('reload');	
	}
//-->
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div-search" style="margin-top: 5px;">
	<form id="searchForm" name="searchForm">
		<input type="hidden" id="publishView_publishDateStr"
			name="publishView.publishDateStr">
		<table cellpadding="0" cellspacing="0" border="0" width="30%">
			<tr>
				<td nowrap="nowrap" width="25%">
					发布时段：
				</td>
				<td id="div-publish-date" width="75%">
					<img src="images/calendar_prevyear.gif"
						onclick="getPublishData(-1)">
					&nbsp;
					<label id="_labPublishDate"></label>
					&nbsp;
					<img src="images/calendar_nextyear.gif" onclick="getPublishData(1)">
				</td>
			</tr>
		</table>
	</form>
</div>
<div align="left" style="margin-top: 10px;">
	<table id="grid-datalist"></table>
</div>

<script type="text/javascript" defer="defer">
<!--
	$(document).ready(function() {
		$('#_add').linkbutton('disable');
		$('#_delete').linkbutton('disable');
		//
		var frozenColumns = [[{
					field : 'publishDateStr',
					title : '发布日期',
					width : 100,
					sortable : true					
				}]];
		var columns = [[{
			field : 'publishTimeStr',
			title : '发布时间',
			width : 100
		},{
			field : 'website',
			title : '网站',
			width : 200,
			formatter : function(value, rec) {				
				return cutstr(value,20);
			}
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
		showDatagrid('grid-datalist','',frozenColumns,columns);
		getPublishData();
	});
//-->
</script>