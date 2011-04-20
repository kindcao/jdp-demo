<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
        Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<%
	    String root = request.getContextPath();
	%>
	<title><s:text name="msg.sys.title" /></title>
	<!--  sj:head locale="zh_CN" jqueryui="true" jquerytheme="black-tie" /-->
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=root%>/css/style.css" />

	<script type="text/javascript" src="<%=root%>/scripts/jquery/jquery.js"></script>

	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.validate.js"></script>


	<link rel="stylesheet" type="text/css"
		href="<%=root%>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="<%=root%>/css/themes/icon.css">
	
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.easyui.min.js"></script>

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