<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		resetForm('mktevtCalForm');
	});
		
		

	
	
	$(document).ready(function() {
		$('#_add').linkbutton('disable');	
		$('#_delete').linkbutton('disable');	
		$('#_cal_tab').click();
	});
//-->
</script>