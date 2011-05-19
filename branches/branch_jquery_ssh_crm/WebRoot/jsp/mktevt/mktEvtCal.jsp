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
	height: 125px;
}

#cal-year-tab td {
	width: "33%";
	border-top: 1px solid #A4BED4;
	overflow: hidden;
	vertical-align: top;
}

#cal-year-tab td div {
	margin: 5px;
}

#cal-year-tab td div hr {
	border: solid #A4BED4;
	width: 80%;
	text-align: left;
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
		var year=parseInt($('#_labYear').html())+inc;		
		$('#_labYear').html(year);
		$('#calExtDto_occurDate').val(year+'0101');
		//
		var options = {
			url : 'getMktEvtCalByYear.action',
			dataType : 'json',
			success : function(data){				
				cleanCalYearData();
				//
				if (data.total>0) {
					parserCalYearData(data);	
				}
			}
		};		
		$('#mktevtCalYearForm').ajaxSubmit(options);
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
	 
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div style="margin-top: 5px;">
	<form id="mktevtCalYearForm" name="mktevtCalYearForm">
		<input type="hidden" name="calExtDto.year" value="true">
		<input type="hidden" id="calExtDto_occurDate"
			name="calExtDto.occurDate">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr height="30px">
				<td nowrap="nowrap" width="10%">
					市场工作：
				</td>
				<td width="20%">
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_year">年</a>
					<a href="#" class="easyui-linkbutton" plain="true" id="_cal_month">月</a>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					用户：
				</td>
				<td width="20%">
					&nbsp;
				</td>
				<td width="40%">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td id="cal_year_head" align="center" colspan="6">
					<img src="images/calendar_prevyear.gif"
						onclick="getCalYearData(-1)">
					&nbsp;
					<label id="_labYear"></label>
					&nbsp;
					<img src="images/calendar_nextyear.gif" onclick="getCalYearData(1)">
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="div_cal-year" style="margin-top: 0px; display: inline;">
	<table cellpadding="0" cellspacing="0" border="0" width="99%">
		<tr valign="top" height="500px">
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
		<tr valign="top" height="500px">
			<td>
				ss
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript" defer="defer">
<!--
	
	$('#_cal_year').click(function(){
		document.getElementById('div_cal-year').style.display='inline';
		document.getElementById('div_cal-month').style.display='none';		    		
		getCalYearData(0);						
	});
	
	$('#_cal_month').click(function(){
		document.getElementById('div_cal-year').style.display='none';
		document.getElementById('div_cal-month').style.display='inline';			
	});
		
	$(document).ready(function() {
		$('#_add').linkbutton('disable');	
		$('#_delete').linkbutton('disable');
		
		//
		if(!$('#_labYear').html()){
			$('#_labYear').html(new Date().getFullYear());
		}
		
		//
		$('#_cal_year').click();
		//		
	});
//-->
</script>