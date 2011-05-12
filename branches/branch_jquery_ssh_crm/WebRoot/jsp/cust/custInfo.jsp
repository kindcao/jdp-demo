<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="margin-top: 10px;">
	<s:property value="#session.CUSTOMER_SESSION_KEY.custName" />
</div>
<div id="tabs-container" class="easyui-tabs" border="false" fit="true"
	style="margin-top: 5px; overflow: hidden; width: 400px; height: 600px;">
	<div title="客户资料" style="margin-left: 5px;"
		href="showCustInfoDtl.action">
	</div>
	<div title="联系人" cache="false" href="showContList.action">
	</div>
	<div title="拜访" cache="false"
		href="showMarketEventList.action?eventTypeId=1">
	</div>
	<div title="培训" cache="false"
		href="showMarketEventList.action?eventTypeId=2">
	</div>
	<div title="活动" cache="false"
		href="showMarketEventList.action?eventTypeId=3">
	</div>
	<div title="其他" cache="false"
		href="showMarketEventList.action?eventTypeId=4">
	</div>
</div>
