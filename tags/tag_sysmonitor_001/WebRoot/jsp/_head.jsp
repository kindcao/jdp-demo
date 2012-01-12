<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title><s:text name="msg.sys.title" />
	</title>
	<%
	    String root = request.getContextPath();
	%>

	<link rel="stylesheet" type="text/css" href="<%=root%>/css/style.css" />
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery-1.6.4.min.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.form.min.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=root%>/scripts/jquery/common.js"></script>
	<script type="text/javascript">
	 <!--	   
	 
	 $.ajaxSetup ({
		cache: false //关闭AJAX相应的缓存
	 });
	 
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