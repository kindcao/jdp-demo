<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	    String root = request.getContextPath();
	%>

	<link rel="stylesheet" type="text/css" href="<%=root%>/css/chili.css" />
	<link rel="stylesheet" type="text/css" href="<%=root%>/css/style.css" />
	<script type="text/javascript" src="<%=root%>/scripts/jquery/jquery.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.validate.js"></script>
	<script type="text/javascript">
	 <!--	   
	   	window.onbeforeunload = function() {
		var n = window.event.screenX - window.screenLeft;
		var b = n > document.documentElement.scrollWidth - 20;
		if (b && window.event.clientY < 0 || window.event.altKey) {
			document.location = "logout.action";
			// window.event.returnValue = ""; //这里可以放置你想做的操作代码
		}
	}
	//-->
	</script>
</head>