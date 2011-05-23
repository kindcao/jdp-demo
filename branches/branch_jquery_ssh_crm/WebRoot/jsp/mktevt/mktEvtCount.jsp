<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#count-tab {
	border-left: 1px solid #A4BED4;
	overflow: hidden;
}

#count-tab td {
	border-top: 1px solid #A4BED4;
	border-right: 1px solid #A4BED4;
}

.title-left {
	font-weight: bold;
	text-align: center;
}
-->
</style>

<script type="text/javascript">
<!--	
	function getCountTabData(inc){
		var options = {
			url : 'getMktEvtCountTab.action',
			dataType : 'json',
			success : function(data){				
				//cleanCalYearData();
				//
				if (data.total>0) {
					parserCountTabData(data);	
				}
			}
		};		
		$('#mktevtCountForm').ajaxSubmit(options);
	}
	
	function parserCountTabData(data){
		var _tabHTML='<table id="count-tab" cellpadding="0"  cellspacing="0" width="100%" height="100%" >';
		_tabHTML+='<tr>';
		_tabHTML+='<td class="title-left">&nbsp;</td>';
		_tabHTML+='<td class="title-top">客户名称</td>';
		_tabHTML+='<td class="title-top">拜访</td>';
		_tabHTML+='<td class="title-top">培训</td>';
		_tabHTML+='<td class="title-top">活动</td>';
		_tabHTML+='<td>其他</td>';
		_tabHTML+='</tr>';
		$.each(data.rows, function(i, ele) {
			//
			_tabHTML+='<tr>';
			_tabHTML+='<td rowspan='+ele.itemNum +'>'+ele.induName+'</td>';
			$.each(ele.items, function(i, ele2) {
				_tabHTML+='<td>'+ele2.custName +'</td>';
				_tabHTML+='<td>'+ele2.visitNum +'</td>';
				_tabHTML+='<td>'+ele2.trainingNum +'</td>';
				_tabHTML+='<td>'+ele2.activityNum +'</td>';
				_tabHTML+='<td>'+ele2.othersNum +'</td>';
			});
			_tabHTML+='</tr>';			
			//
			_tabHTML+='<tr>';			
			_tabHTML+='<td class="sum">小计</td>';
			_tabHTML+='<td>'+ele.sum.visitNum +'</td>';
			_tabHTML+='<td>'+ele.sum.trainingNum +'</td>';
			_tabHTML+='<td>'+ele.sum.activityNum +'</td>';
			_tabHTML+='<td>'+ele.sum.othersNum +'</td>';
			_tabHTML+='</tr>';
		});
		_tabHTML+='</table>';	
		$('#div-count-tab').html(_tabHTML);		
	}	
	
//-->
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div style="margin-top: 0px;">
	<form id="mktevtCountForm" name="mktevtCountForm">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="10%" nowrap="nowrap">
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_def">默认统计</a>
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_tab">统计表</a>
				</td>
				<td width="5%" nowrap="nowrap" align="center">
					日期：
				</td>
				<td width="10%">
					<input>
				</td>
				<td width="5%" nowrap="nowrap" align="center">
					至
				</td>
				<td width="10%">
					<input>
				</td>
				<td width="30%">
					<div id="div-cont-search-tab" style="display: none;">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td nowrap="nowrap" align="center" width="30%">
									所属公司:
								</td>
								<td>
									<input id="calExtDto_compId" class="easyui-combobox"
										name="calExtDto.compId" url="getSysComp.action"
										valueField="id" textField="companyName" multiple="true"
										editable="false" panelHeight="auto" style="width: 200px;">
								</td>
							</tr>
						</table>
					</div>
					<div id="div-cont-search-def" style="display: none;">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td nowrap="nowrap" align="center" width="30%">
									类型:
								</td>
								<td>
									<input id="calExtDto_mktevtSuperiorId"
										name="calExtDto.mktevtSuperiorId" class="easyui-combobox"
										url="getMarketEventType.action?eventTypeId=0" valueField="id"
										textField="name" multiple="false" editable="false"
										panelHeight="auto" style="width: 200px;">
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td nowrap="nowrap" width="10%">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-search" id="_search">查询</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset">重置</a>
				</td>
				<td width="20%">
					&nbsp;
				</td>
			</tr>
			<tr height="5px">
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="div-count-tab">
</div>

<script type="text/javascript" defer="defer">
<!--	
	$('#_cal_def').click(function(){		
		$('#_cal_def').linkbutton('disable');
		$('#_cal_tab').linkbutton('enable');
		document.getElementById('div-cont-search-def').style.display='inline';
		document.getElementById('div-cont-search-tab').style.display='none';	
		//$('#_reset').click();
		//$('#_search').click();			
	});
	
	$('#_cal_tab').click(function(){		
		$('#_cal_def').linkbutton('enable');
		$('#_cal_tab').linkbutton('disable');
		document.getElementById('div-cont-search-def').style.display='none';
		document.getElementById('div-cont-search-tab').style.display='inline';
		
		//$('#_reset').click();
		//$('#_search').click();
	});
	
	$('#_search').click(function(){		
		if($('#calExtDto_year').val()){			
			getCalYearData(0);
		}else{
			getCalMonthData();
			var _url='getMktEvtCal.action?calExtDto.year=false';
			_url+='&calExtDto.occurDate='+$('#calExtDto_occurDate').val();
			_url+='&calExtDto.compId='+$("#calExtDto_compId").combobox('getValues');
			_url+='&calExtDto.mktevtSuperiorId='+$("#calExtDto_mktevtSuperiorId").combobox('getValue');
			$('#grid-datalist').datagrid('options').url=_url;			
			$('#grid-datalist').datagrid('reload');					
		}
	});
	
	$('#_reset').click(function(){
		$('#calExtDto_compId').combobox('clear');		
		if(!$('#calExtDto_year').val()){
			$('#calExtDto_mktevtSuperiorId').combobox('clear');
		}
		resetForm('mktevtCountForm');
	});   
	
	$(document).ready(function() {
		$('#_add').linkbutton('disable');	
		$('#_delete').linkbutton('disable');	
		$('#_cal_tab').click();
		
		//
		getCountTabData();
	});
//-->
</script>