<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">	
	$(document).ready(function() {
		var selectTitle='${sessionScope.menutitle}';
		$('#menu').accordion('select',selectTitle);
	});	
	
	function clickMenu(href){
		window.location.href=href;		
	}
</script>

<div id="menu" class="easyui-accordion" align="center"
	style="height: 712px;">
	<div title="客户管理">
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">券商</a>
		</div>
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">銀行</a>
		</div>
	</div>
	<div title="市场工作">
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">日历</a>
		</div>
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">统计</a>
		</div>
	</div>
	<div title="市场监控">
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">发布监控</a>
		</div>
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">行业监控</a>
		</div>
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">新闻专访</a>
		</div>
	</div>
	<div title="开户工作">
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">开户统计</a>
		</div>
	</div>
	<div title="系统设定">
		<div style="margin: 10px;">
			<a href="javascript:void(0)"
				onclick="clickMenu('showUserList.action');">用户管理</a>
		</div>
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">行业设定</a>
		</div>
		<div style="margin: 10px;">
			<a href="javascript:void(0)" onclick="clickMenu('#');">发布监控</a>
		</div>
	</div>
</div>