<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#cal-year-tab {
	border-right: 1px solid #A4BED4;
	border-left: 1px solid #A4BED4;
	border-bottom: 1px solid #A4BED4;
	overflow: hidden;
}

#cal-year-tab tr {
	height: 120px;
}

#cal-year-tab td {
	width: 33%;
	border-top: 1px solid #A4BED4;
	overflow: hidden;
	vertical-align: top;
	border-top: 1px solid #A4BED4;
}

#cal-year-tab td div {
	margin: 5px;
}

#cal-year-tab td div hr {
	border: solid #A4BED4;
	border-width: 1px;
	width: 80%;
	text-align: left;
	width: 80%;
}

#cal-year-tab td div span {
	color: gray;
}

#cal_year_head {
	border-top: 1px solid #A4BED4;
	border-right: 1px solid #A4BED4;
	border-left: 1px solid #A4BED4;
}

#cal_year_head img {
	cursor: hand;
}

.mid {
	border-left: 1px solid #A4BED4;
	border-right: 1px solid #A4BED4;
}
-->
</style>

<script type="text/javascript">
	var months = ['一月', '二月', '三月', '四月', '五月', '六月', '七月',
			'八月', '九月', '十月', '十一月', '十二月'];
	//
	function printTab(){
		var _tabHTML='<table id="cal-year-tab" cellpadding="0" cellspacing="0" width="100%" height="100%" >';	
		for(var i=0;i<months.length;){			
			_tabHTML+='<tr>'			
			//
			for(var j=0;j<3;j++,i++){
				if(j==1){
					_tabHTML+='<td class="mid">';
				}else{
					_tabHTML+='<td>';
				}
				_tabHTML+='<div><b>'+ months[i]+'</b><hr></div>';
				_tabHTML+='<div id="div_mon_'+(i+1)+'"></div>';				
				_tabHTML+='</td>';	
			}
			//
			_tabHTML+='</tr>';			
		}		
		_tabHTML+='</table>';		
		document.write(_tabHTML);		
	}
	
	function getCalYearData(inc){
		var labTxt=$('#_labYear').text();
		if(!labTxt){
			labTxt=new Date().getFullYear();
		}		
		var year=parseInt(labTxt)+inc;
		var month=new Date().getMonth()+1;
	    $('#_labYear').text(year);			
		$('#calExtDto_occurDate').val(year*10000+month*100+1);
		
		//
		var options = {
			url : 'getMktEvtCal.action',
			dataType : 'json',
			type : 'post',
			success : function(data){				
				cleanCalYearData();				
				if (data.total>0) {
					parserCalYearData(data);	
				}
			}, 
			beforeSend: function(XMLHttpRequest) {
				var wrap=$("#cal-year-tab");							
				$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍待。。。")
				.appendTo(wrap).css({display:"block",left:(wrap.width())/2,top:(wrap.height())/2});
			},
			complete: function(XMLHttpRequest, textStatus) {				
				$("#cal-year-tab").children("div.datagrid-mask-msg").remove();
			}			
		};		
		$('#mktevtCalForm').ajaxSubmit(options);
	}
	
	function cleanCalYearData(){
		for(var i=1;i<=months.length;i++){
			$('#div_mon_'+i).html('');
		}
	}
		
	function parserCalYearData(data){	
	    var _map=new JHashMap();
	    $.each(data.rows, function(i, ele) {    
            var mon=parseInt((ele.occurDate%10000)/100);           
            if(!_map.containsKey(mon)){
            	_map.put(mon,new JHashMap());            	
            }
            //
            var _subMap=_map.get(mon); 
            var key=ele.mktevtSuperiorName 
            var value=_subMap.get(key)==null? '':_subMap.get(key);
            var flag=false;
            if(ele.status=='N'){
            	flag=true;
            }                         
            if(_subMap.containsKey(key)){              
            	value+=';'     	
            }
            value+= flag?('<span>'+ele.compCustName+'</span>'):ele.compCustName;          
            _subMap.put(key,value);       
                    
		});
		
		//		
		$.each(_map.keySet(), function(i, ele) {		  
		    var _subMap=_map.get(ele); 
		    var _txt='';
		    $.each(_subMap.keySet(), function(j, ele2) {
		    	_txt+=ele2+'：';
				_txt+=_subMap.get(ele2)+'<br>';				
			});		
			$('#div_mon_'+ele).html(_txt);
		});				
	}
	
	//	
	function getCalMonthData(){
		var frozenColumns = [[{
					field : 'occurDateStr',
					title : '日期',
					width : 100,
					sortable : true,			
					formatter : function(value, rec) {
						var str=value+"-" +rec.mktevtSuperiorName;
						return "<a href='#' onclick=viewMktEvt("+ rec.id +",'"+ str +"');>" + value + "</a>";
					}
				}]];
		var columns = [[{
			field : 'mktevtSuperiorName',
			title : '类型',
			width : 100
		},{
			field : 'compCustName',
			title : '参与机构(所属公司/客户名称/主题)',
			width : 400,
			formatter : function(value, rec) {				
				return cutstr(value,50);
			}
		}]];			
	
		//		
		showDatagridNoPagination('grid-datalist','',frozenColumns,columns);
	}
	
	function viewMktEvt(id,str){		
		$('#div-view-dtl').window({
			title:str,
			href:'showMktEvtCalDtl.action?mktEvt.id='+id		
		});		
		//		
		$('#div-view-dtl').window('open');		
	}
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div style="margin-top: 0px;">
	<form id="mktevtCalForm" name="mktevtCalForm">
		<input type="hidden" id="calExtDto_year" name="calExtDto.year">
		<input type="hidden" id="calExtDto_occurDate"
			name="calExtDto.occurDate">
		<table cellpadding="0" cellspacing="0" border="0" width="800">
			<tr>
				<td width="10%" nowrap="nowrap">
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_year">年</a>
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_month">月</a>
				</td>
				<td class="label-title" width="10%">
					所属公司：
				</td>
				<td width="30%">
					<input id="calExtDto_compId" class="easyui-combobox"
						name="calExtDto.compId" url="getSysComp.action" valueField="id"
						textField="companyName" multiple="true" editable="false"
						panelHeight="auto" style="width: 250px;">
				</td>
				<td width="40%">
					<div id="div-cal-search-month" style="display: none;">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="label-title" width="40%">
									类型：
								</td>
								<td>
									<input id="calExtDto_mktevtSuperiorId"
										name="calExtDto.mktevtSuperiorId" class="easyui-combobox"
										url="getMktEvtType.action?eventTypeId=0" valueField="id"
										textField="name" multiple="false" editable="false"
										panelHeight="auto" style="width: 160px;">
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
			</tr>
			<tr height="5px">
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="div_cal-year" style="margin-top: 0px; display: inline;">
	<table cellpadding="0" cellspacing="0" border="0" width="99%">
		<tr height="30px">
			<td id="cal_year_head" align="center">
				<img src="images/calendar_prevyear.gif" onclick="getCalYearData(-1)">
				&nbsp;
				<label id="_labYear"></label>
				&nbsp;
				<img src="images/calendar_nextyear.gif" onclick="getCalYearData(1)">
			</td>
		</tr>
		<tr valign="top" height="480px">
			<td>
				<script type="text/javascript">
				printTab();
				</script>
			</td>
		</tr>
	</table>
</div>
<div id="div_cal-month" style="margin-top: 0px; display: none;">
	<table cellpadding="0" cellspacing="0" border="0" width="99%">
		<tr valign="top" height="400px">
			<td width="20%">
				<div id="div-cal-ui" class="easyui-calendar"
					style="width: 200px; height: 200px;"></div>
			</td>
			<td width="1%">
				&nbsp;
			</td>
			<td width="79%">
				<div align="left">
					<table id="grid-datalist"></table>
				</div>
			</td>
		</tr>
	</table>
	<div id="div-view-dtl" class="easyui-window" icon="icon-search"
		closed="true" collapsible="false" resizable="false" cache="false"
		minimizable="false" modal="true" title="详细信息"
		style="width: 800px; height: 500px; overflow: hidden;">
	</div>
</div>

<script type="text/javascript" defer="defer">
<!--	
	$('#_cal_year').click(function(){
		$('#calExtDto_year').val(true);	
		$('#_cal_year').linkbutton('disable');
		$('#_cal_month').linkbutton('enable');
		document.getElementById('div_cal-year').style.display='inline';
		document.getElementById('div_cal-month').style.display='none';
		document.getElementById('div-cal-search-month').style.display='none';		
		$('#_reset').click();
		$('#_search').click();			
	});
	
	$('#_cal_month').click(function(){
		$('#calExtDto_year').val('');
		$('#_cal_year').linkbutton('enable');
		$('#_cal_month').linkbutton('disable');
		document.getElementById('div_cal-year').style.display='none';
		document.getElementById('div_cal-month').style.display='inline';		
		document.getElementById('div-cal-search-month').style.display='inline'; 
		$('#_reset').click();
		$('#_search').click();
	});
	
	$('#_search').click(function(){		
		if($('#calExtDto_year').val()){			
			getCalYearData(0);
		}else{
			getCalMonthData();
			var _url='getMktEvtCal.action?calExtDto.year=false';
			_url+='&calExtDto.occurDate='+$('#calExtDto_occurDate').val();
			_url+='&calExtDto.compId='+$("#calExtDto_compId").combobox('getValues');
			_url+='&calExtDto.mktevtSuperiorId='+$("#calExtDto_mktevtSuperiorId").combobox('getValues');
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
		
		
	$('#div-cal-ui').calendar().find(".calendar-nextmonth").click(function() {
		calClick();	
	});
	
	$('#div-cal-ui').calendar().find(".calendar-prevmonth").click(function() {
		calClick();	
	});
	
	$('#div-cal-ui').calendar().find(".calendar-nextyear").click(function() {
		calClick();	
	});
	
	$('#div-cal-ui').calendar().find(".calendar-prevyear").click(function() {
		calClick();	
	});
	
	function calClick(){
		var _occurDate=$('#calExtDto_occurDate').val();
		var _year=$('#div-cal-ui').calendar('options').year;
		var _month=$('#div-cal-ui').calendar('options').month-1;		
		if(_month==12){
			_year+=1;
			_month=1;
		}
		var now=new Date(_year,_month,1);
		$('#calExtDto_occurDate').val(_year*10000+(_month+1)*100+1);
		$('#_search').click();		
		//$('#div-cal-ui').calendar('moveTo',now);	
	}
	
	
	$(document).ready(function() {
		$('#_add').linkbutton('disable');	
		$('#_delete').linkbutton('disable');	
		$('#_cal_year').click();
	});
//-->
</script>