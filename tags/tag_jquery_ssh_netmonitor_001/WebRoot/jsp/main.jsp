<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">
	    //		
		function addTab(thisObj,href){		
		    var str=thisObj.innerHTML.replace(/^\s+|\s+$/g,"");		   
		    if($('#tabs-container').tabs('exists',str)){
		    	$('#tabs-container').tabs('select',str);
		    }else{		    	
				var _tab={
					title: str,
					href: href,					
					cache: false,					
					closable: true
				}			
				$('#tabs-container').tabs('add',_tab);
		    }
		}		
	</script>
	<script type="text/javascript">	
		function clockon(){    
			var now = new Date();    
			var year = now.getFullYear();    
			var month = now.getMonth();    
			var date = now.getDate();    
			var day = now.getDay();    
			var hour = now.getHours();    
			var minu = now.getMinutes();    
			var sec = now.getSeconds();   
			var week;     
			month = month+1;    
			if(month<10)month="0"+month;    
			if(date<10)date="0"+date;    
			if(hour<10)hour="0"+hour;    
			if(minu<10)minu="0"+minu;    
			if(sec<10)sec="0"+sec;    
			var arr_week = new Array("Sun","Mon","Tue","Wed","Thu","Fri","Sat");     
			week = arr_week[day];
			var time =  year+"-"+month+"-"+date+"&nbsp;"+hour+":"+minu+":"+sec +"&nbsp;&nbsp;"+week;
			document.getElementById('labtime').innerHTML=time;			          
		}		
		setInterval("clockon()",1000);  
	</script>

	<body class="easyui-layout">
		<div id="region-north" region="north" title="" split="false">
		</div>

		<div region="south" split="false"
			style="height: 30px; overflow: hidden;">
			<table cellpadding="0" cellspacing="0"
				style="background-color: #A4BED4;" align="left" width="100%"
				height="28px;">
				<tr valign="middle">
					<td style="width: 196px; border: 1px solid white;" nowrap="nowrap">
						<div style="margin: 0 10px 0 10px;" id="labtime"></div>
					</td>
					<td style="border: 1px solid white;" nowrap="nowrap">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr valign="middle" align="center">
								<td nowrap="nowrap" width="120px">
									当前用户:
									<s:property value="#session._CURR_USER.username" />
								</td>
								<td width="1px" style="border-right: 1px solid white">
									&nbsp;
								</td>
								<td nowrap="nowrap" width="120px">
									在线人数:
									<%=com.base.action.BaseAction.DATA_MAP.size()%>
								</td>
								<td width="1px" style="border-right: 1px solid white">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td style="width: 80px; border: 1px solid white;" nowrap="nowrap"
						align="center">
						<div style="margin: 5px;" id="labinfo">
							<a href="javascript:void(0);" class="easyui-linkbutton"
								plain="true" iconCls="icon-back" id="logoutAction"
								onclick='document.location="logout"'>注销</a>
						</div>
					</td>
				</tr>
			</table>
		</div>

		<div region="west" split="false" title="&nbsp;" align="center"
			style="width: 200px; padding1: 0px; overflow: hidden;">
			<div class="easyui-accordion" fit="true" selected="true">
				<div title="网络监控">
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'showNetMonitor');">网络监控</a>
					</div>
				</div>
				<div title="系统设定" fit="true">
					<div style="margin: 10px;">
						<label onclick=""></label>
						<a href="#" onclick="addTab(this,'showUserList');">用户设定</a>
					</div>
				</div>
			</div>
		</div>

		<div region="center" style="padding1: 5px; background: #aaa;">
			<div id="tabs-container" class="easyui-tabs" fit="true"
				style="overflow: hidden;">
				<div title="默认信息" style="padding: 0px; display: none;">
					<!--div class="tabs-container" plain="true" style="padding: 0px;
					overflow: hidden;">
					<div title="子标题1" href="#">
						子内容1
					</div>
					<div title="子标题2">
						子内容2
					</div>
					<div title="子标题3">
						子内容3
					</div>
					</div-->
				</div>
			</div>
		</div>
	</body>
</html>