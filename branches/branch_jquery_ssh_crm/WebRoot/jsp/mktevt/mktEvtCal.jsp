<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
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
	font-weight: bold;
}

#cal-year-tab td div hr {
	border: solid #A4BED4;
	width: 80%;
	text-align: left;
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
				_tabHTML+='<div>'+ months[i]+'<hr></div>';
				_tabHTML+='<div id="div_mon_'+(i+1)+'"></div>';
				_tabHTML+='</td>';	
			}
			//
			_tabHTML+='</tr>';			
		}		
		_tabHTML+='</table>';		
		document.write(_tabHTML);		
	}
</script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div style="margin-top: 5px;">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr height="30px">
			<td align="left">
				市场工作：
			</td>
			<td>
				<a href="#" class="easyui-linkbutton" plain="true" id="_cal_year">年</a>
			</td>
			<td>
				<a href="#" class="easyui-linkbutton" plain="true" id="_cal_month">月</a>
			</td>
			<td width="100px">
				&nbsp;
			</td>
			<td align="left">
				用户：
			</td>
		</tr>
	</table>
</div>
<div id="div_cal-year" style="margin-top: 5px;">
	<table cellpadding="0" cellspacing="0" border="0" width="99%"
		style="border: 1px solid #A4BED4; overflow: hidden;">
		<tr height="30px">
			<td align="center">
				gf
			</td>
		</tr>
		<tr valign="top" height="500px">
			<td>
				<script type="text/javascript">
				printTab();
				</script>
			</td>
		</tr>
	</table>
</div>
<div id="div_cal-month" style="margin-top: 10px;">
	dfffdfd
</div>

<script type="text/javascript" defer="defer">
<!--
	$(document).ready(function() {
		$('#_add').linkbutton('disable');	
		$('#_delete').linkbutton('disable');
	});
//-->
</script>