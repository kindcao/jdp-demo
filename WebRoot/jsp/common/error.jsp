<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div>
	<h1>
		系统出现异常，请联系管理员或重新
		<a href="login.action">登录</a>
	</h1>
	<h5>
		详细错误信息：
	</h5>
	<hr>
	<s:set value="#request.exception" name="e" />
	<s:property value="#e.printStackTrace()" />
</div>
