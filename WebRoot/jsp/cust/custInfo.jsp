<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="margin-top: 10px;">
	<s:property value="#session.CUSTOMER_SESSION_KEY.custName" />
</div>
<div id="tabs-container" class="easyui-tabs" fit="true"
	style="margin-top: 10px; width: 800px; height: 593px; overflow: hidden;">
	<div title="客户资料" style="margin-left: 5px;"
		href="showCustInfoDtl.action">
	</div>
	<div title="联系人" style="margin-left: 5px;" href="showContList.action">
	</div>
	<div title="拜访" style="margin-left: 5px;" cache="false"
		href="showMarketEventList.action?eventTypeId=1">
	</div>
	<div title="培训" style="margin-left: 5px;" cache="false"
		href="showMarketEventList.action?eventTypeId=3">
	</div>
	<div title="活动" style="margin-left: 5px;" cache="false"
		href="showMarketEventList.action?eventTypeId=2">
	</div>
	<div title="其他" style="margin-left: 5px;" cache="false"
		href="showMarketEventList.action?eventTypeId=4">
	</div>
</div>
