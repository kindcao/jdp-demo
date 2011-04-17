<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<title><s:text name="msg.sys.title"></s:text></title>

	<%
	    String root = request.getContextPath();
	%>
	<link rel="stylesheet" type="text/css"
		href="<%=root%>/scripts/extjs2.0/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=root%>/css/style.css" />
	<script type="text/javascript"
		src="<%=root%>/scripts/extjs2.0/adapter/ext/ext-base.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/extjs2.0/ext-all.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/extjs2.0/source/locale/ext-lang-zh_CN.js"></script>
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
    // -->   
	</script>

</head>