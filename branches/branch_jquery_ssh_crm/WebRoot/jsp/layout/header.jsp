<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="margin: 5px; overflow: hidden; height: 90px;">
	<s:if test="%{#session._sysUserComp.logo.trim().length()>0}">
		<img style="background-repeat: no-repeat;"
			src='<s:property value="#session._sysUserComp.logo"/>' />
	</s:if>
	<s:else>
		<img style="background-repeat: no-repeat;" src='images/logo.jpg' />
	</s:else>
</div>
