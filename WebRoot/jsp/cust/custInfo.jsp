<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="margin-top: 10px;">
	<s:property value="#session.CUSTOMER_SESSION_KEY.custName" />
</div>
<div id="tabs-container" class="easyui-tabs" border="false" fit="true"
	style="margin-top: 5px; overflow: hidden; width: 400px; height: 500px;">
	<div title="客户资料" style="margin-left: 5px;"
		href="showCustInfoDtl.action">
	</div>
	<div title="联系人" cache="false" href="showContList.action">
	</div>
	<div title="市场工作" cache="false" href="showMktEvtList.action">
	</div>
</div>
