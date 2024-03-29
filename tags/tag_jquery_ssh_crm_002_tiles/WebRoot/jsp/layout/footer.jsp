<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#tab-footer td {
	padding-left: 10px;
	padding-right: 10px;
	white-space: nowrap;
}

.split-line {
	border-right: 1px solid white;
	width: 1px;
}
-->
</style>

<div style="height: 30px; overflow: hidden;">
	<table cellpadding="0" cellspacing="0" height="30px" width="100%"
		style="background-color: #A4BED4;" align="left">
		<tr valign="middle">
			<td style="width: 199px; border: 1px solid white;" nowrap="nowrap">
				<span id="labtime" style="margin: 0 10px 0 10px;"></span>
			</td>
			<td style="border: 1px solid white;" nowrap="nowrap">
				<table id="tab-footer" cellpadding="0" cellspacing="0" border="0">
					<tr valign="middle" align="left">
						<td nowrap="nowrap" width="100px">
							<label>
								在线用户数：
							</label>
							<span id="labOnlineUserNum"></span>
						</td>
						<td width="1px" class="split-line">
							&nbsp;
						</td>
						<td nowrap="nowrap" width="120px">
							<label>
								当前用户：
							</label>
							<s:property value="#session._sysUser.loginId" />
						</td>
						<td class="split-line">
							&nbsp;
						</td>
						<td nowrap="nowrap" width="150px">
							<label>
								所属公司：
							</label>
							<s:property value="#session._sysUserComp.companyName" />
						</td>
						<td class="split-line">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
			<td style="width: 70px; border: 1px solid white;" nowrap="nowrap"
				align="right">
				<div style="margin-right: 5px;">
					<a id="logoutAction" href="#" class="easyui-linkbutton"
						plain="true" iconCls="icon-back"
						onclick='document.location = "logout"'>注销</a>
				</div>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript" defer="defer">	
	function clockon() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth();
		var date = now.getDate();
		var day = now.getDay();
		var hour = now.getHours();
		var minu = now.getMinutes();
		var sec = now.getSeconds();
		var week;
		month = month + 1;
		if (month < 10)
			month = "0" + month;
		if (date < 10)
			date = "0" + date;
		if (hour < 10)
			hour = "0" + hour;
		if (minu < 10)
			minu = "0" + minu;
		if (sec < 10)
			sec = "0" + sec;
		var arr_week = new Array("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
		week = arr_week[day];
		var time = year + "-" + month + "-" + date + "&nbsp;" + hour + ":" + minu
			 + "&nbsp;&nbsp;" + week;
		document.getElementById('labtime').innerHTML = time;
	}
	
	function showErrorMsg(){
		$.messager.show({
			title:'提示信息-系统错误',
			msg:'原因：服务异常或Session超时或重复登录，15秒后系统自动转向登录页面！',
			timeout:15*1000,
			showType:'slide'
		});	
		setTimeout("window.location.href='showLogin'",15*1000);		
	}
	
	function sysStatus() {	
		var options = {
			url : 'getSysStatus',
			dataType : 'json',
			//timeout : 30*1000,
			success : function(data,textStatus){			
				if (null!=data && data.statusCode==0) {
					document.getElementById('labOnlineUserNum').innerHTML = data.onlineUserNum;					
				} else {
					showErrorMsg();	
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				if('error'==textStatus){
					showErrorMsg();
				}				
			}
		}
		$.ajax(options);
	}
	
	function innerTask(){
		clockon();
		sysStatus();
	}
	
	//
	$(document).ready(function(){
		innerTask();
		setInterval("innerTask();", 60*1000);
	});
</script>
