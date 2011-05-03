<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div style="margin: 10px">
	<h3>
		系统出现异常，请联系管理员或重新
		<a href="forward.action?forward=login">登录</a>
	</h3>
	<hr>
	<h5>
		详细错误信息：
	</h5>
	<s:property value="exception.message" />
	<s:property value="exceptionStack" />
</div>
