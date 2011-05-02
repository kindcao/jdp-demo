<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="crm.common.Constants"%>

<script type="text/javascript">	
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
				+ ":" + sec + "&nbsp;&nbsp;" + week;
		document.getElementById('labtime').innerHTML = time;
	}
	setInterval("clockon()", 1000); 
</script>

<div style="height: 30px; overflow: hidden;">
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
							<s:property value="#session.CURR_SYS_USER_SESSION_KEY.loginId" />
						</td>
						<td width="1px" style="border-right: 1px solid white">
							&nbsp;
						</td>
						<td nowrap="nowrap" width="120px">
							在线人数:
							<%=Constants.SYS_USER_MAP.size()%>
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
					<a id="logoutAction" href="#"
						onclick='document.location = "logout.action"'>Logout</a>
				</div>
			</td>
		</tr>
	</table>
</div>
