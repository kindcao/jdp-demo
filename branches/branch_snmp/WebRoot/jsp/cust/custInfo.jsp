<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="margin-top: 5px;">
	<s:property value="cust.custName" />
</div>
<div id="tabs-container" class="easyui-tabs" border="false" fit="true"
	style="margin-top: 5px; overflow: hidden; width: 500px; height: 500px;">
	<div title="客户资料" style="margin-left: 5px;"
		href="showCustInfoDtl">
	</div>
	<div title="联系人" style="margin-left: 5px;" cache="false"
		href='showContList?customerId=<s:property value="cust.id" />'>
	</div>
</div>
