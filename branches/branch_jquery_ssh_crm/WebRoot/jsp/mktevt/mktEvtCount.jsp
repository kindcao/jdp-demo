<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#count-tab {
	border-left: 1px solid #A4BED4;
	border-bottom: 1px solid #A4BED4;
	font-size: 10px;
}

#count-tab tr {
	height: 20px;
}

#count-tab td {
	border-top: 1px solid #A4BED4;
	border-right: 1px solid #A4BED4;
	padding-left: 5px;
}

#tab-head {
	font-weight: bold;
	color: gray;
	height: 20px;
	vertical-align: middle;
}

#sum {
	color: blue;
}

.title-left {
	font-weight: bold;
	text-align: center;
	width: 10%;
	color: gray;
	white-space: nowrap;
}
-->
</style>

<script type="text/javascript">
<!--	
	function getCountTabData(){		
		var options = {
			url : 'getMktEvtCountTab.action',
			dataType : 'json',
			type : 'post',
			success : function(data){
				if (data.total>0) {
					parserCountTabData(data);	
				}
			}, 
			beforeSend: function(XMLHttpRequest) {
				var wrap=$("#count-tab");								
				$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍待。。。")
				.appendTo(wrap).css({display:"block",left:(wrap.width())/2,top:(wrap.height())/2});				
			},
			complete: function(XMLHttpRequest, textStatus) {				
				$("#count-tab").children("div.datagrid-mask-msg").remove();
			}
		};		
		$('#mktevtCountForm').ajaxSubmit(options);
	}
	
	function parserCountTabData(data){
		var _tabHTML='<table id="count-tab" cellpadding="0"  cellspacing="0" width="99%" height="80%" >';
		_tabHTML+='<tr id="tab-head">';
		_tabHTML+='<td>&nbsp;</td>';
		_tabHTML+='<td nowrap="nowrap">客户名称</td>';
		_tabHTML+='<td>拜访</td>';
		_tabHTML+='<td>培训</td>';
		_tabHTML+='<td>活动</td>';
		_tabHTML+='<td>其他</td>';
		_tabHTML+='</tr>';
		$.each(data.rows, function(i, ele) {		
			_tabHTML+='<tr>';
			_tabHTML+='<td class="title-left" rowspan='+ele.itemNum +'>'+ele.induName+'</td>';
			//
			$.each(ele.items, function(i, ele2) {
				if(i>0){
					_tabHTML+='<tr>';					
				}
				_tabHTML+='<td width="30%" nowrap="nowrap">'+ele2.custName +'</td>';
				_tabHTML+='<td width="15%">'+(ele2.visitNum==0?"&nbsp;":ele2.visitNum)+'</td>';
				_tabHTML+='<td width="15%">'+(ele2.trainingNum==0?"&nbsp;":ele2.trainingNum)+'</td>';
				_tabHTML+='<td width="15%">'+(ele2.activityNum==0?"&nbsp;":ele2.activityNum)+'</td>';
				_tabHTML+='<td width="15%">'+(ele2.othersNum==0?"&nbsp;":ele2.othersNum)+'</td>';
				if(i>0){
					_tabHTML+='</tr>';
				}
			});
			//
			if(ele.items.length>0){
				_tabHTML+='<tr id="sum">';
				_tabHTML+='<td >小计</td>';
				_tabHTML+='<td>'+(ele.sum.visitNum==0?"&nbsp;":ele.sum.visitNum) +'</td>';
				_tabHTML+='<td>'+(ele.sum.trainingNum==0?"&nbsp;":ele.sum.trainingNum) +'</td>';
				_tabHTML+='<td>'+(ele.sum.activityNum==0?"&nbsp;":ele.sum.activityNum) +'</td>';
				_tabHTML+='<td>'+(ele.sum.othersNum==0?"&nbsp;":ele.sum.othersNum) +'</td>';
				_tabHTML+='</tr>';
			}else{
				_tabHTML+='<td>&nbsp;</td>';
				_tabHTML+='<td>&nbsp;</td>';
				_tabHTML+='<td>&nbsp;</td>';
				_tabHTML+='<td>&nbsp;</td>';
				_tabHTML+='<td>&nbsp;</td>';
			}			
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
				<td width="10%" nowrap="nowrap" style="display: none;">
					<!--
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_def">默认统计</a>					
					-->
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_tab">统计表</a>
				</td>
				<td width="5%" class="label-title">
					日期：
				</td>
				<td width="10%">
					<input type="text" id="countExtDt_occurDateStart"
						name="countExtDto.occurDateStart" class="easyui-datebox">
				</td>
				<td width="10%" class="label-title">
					至：
				</td>
				<td width="10%">
					<input type="text" id="countExtDt_occurDateEnd"
						name="countExtDto.occurDateEnd" class="easyui-datebox">
				</td>
				<td width="30%">
					<div id="div-cont-search-tab" style="display: none;">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="label-title" width="30%">
									所属公司：
								</td>
								<td>
									<input id="countExtDto_sysCompIds" class="easyui-combobox"
										name="countExtDto.sysCompIds" url="getSysComp.action"
										valueField="id" textField="companyName" multiple="true"
										editable="false" panelHeight="auto" style="width: 200px;">
								</td>
							</tr>
						</table>
					</div>
					<div id="div-cont-search-def" style="display: none;">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="label-title" width="30%">
									类型:
								</td>
								<td>
									<input id="calExtDto_mktevtSuperiorId"
										name="calExtDto.mktevtSuperiorId" class="easyui-combobox"
										url="getMktEvtType.action?eventTypeId=0" valueField="id"
										textField="name" multiple="false" editable="false"
										panelHeight="auto" style="width: 200px;">
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td nowrap="nowrap" style="padding-left: 10px;">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-search" id="_search">查询</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset">重置</a>
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
<div id="div-count-tab" style="margin-top: 10px;">
</div>

<script type="text/javascript" defer="defer">
<!--	
	$('#_cal_def').click(function(){		
		$('#_cal_def').linkbutton('disable');
		$('#_cal_tab').linkbutton('enable');
		document.getElementById('div-cont-search-def').style.display='inline';
		document.getElementById('div-cont-search-tab').style.display='none';	
		$('#_reset').click();
		$('#_search').click();			
	});
	
	$('#_cal_tab').click(function(){		
		//$('#_cal_def').linkbutton('enable');
		$('#_cal_tab').linkbutton('disable');
		document.getElementById('div-cont-search-def').style.display='none';
		document.getElementById('div-cont-search-tab').style.display='inline';		
		$('#_reset').click();
		$('#_search').click();
	});
	
	$('#_search').click(function(){
		if(document.getElementById('div-cont-search-tab').style.display=='inline'){
			getCountTabData();
		}	
	});
	
	$('#_reset').click(function(){
		if(document.getElementById('div-cont-search-tab').style.display=='inline'){
			$('#countExtDto_sysCompIds').combobox('clear');	
		}
		$("#countExtDt_occurDateStart").datebox('clear');
		$("#countExtDt_occurDateEnd").datebox('clear');
		resetForm('mktevtCountForm');
	});   
	
	$(document).ready(function() {
		$('#_add').linkbutton('disable');	
		$('#_delete').linkbutton('disable');	
		$('#_cal_tab').click();		
	});
//-->
</script>