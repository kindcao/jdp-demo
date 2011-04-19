<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	<%
	    String root = request.getContextPath();
	%>
	<title><s:text name="msg.sys.title" />
	</title>
	<!--  sj:head locale="zh_CN" jqueryui="true" jquerytheme="black-tie" /-->
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=root%>/css/style.css" />
	<script type="text/javascript" src="<%=root%>/scripts/jquery/jquery.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.validate.js"></script>
</head>