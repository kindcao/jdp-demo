<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" defer="defer">    
	$(document).ready(function() {
		var selectTitle='${sessionScope.menutitle}';
		$('#menu').accordion('select',selectTitle);					
	});
	
	function clickMenu(href){
		window.location.href=href;	
	}
</script>

<div id="menu" class="easyui-accordion" align="center"
	style="overflow: hidden;" fit="true">
	<div title="系统默认">
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('welcome');">欢迎页</a>
		</div>
	</div>
	<div title="客户管理">
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=1');">券商</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=2');">银行</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=3');">投资</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=4');">媒体</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=5');">资讯</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=6');">上市</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showCustList?induId=7');">其他</a>
		</div>
	</div>
	<div title="市场工作">
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showMktEvtList');">市场工作</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showMktEvtCal');">日历</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showMktEvtCount');">统计</a>
		</div>
	</div>
	<div title="市场监控">
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showPublishList');">发布监控</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showNewsList');">新闻专访</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showIndustryList');">行业监控</a>
		</div>
	</div>
	<div title="开户工作">
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('#');">开户统计</a>
		</div>
	</div>
	<div title="系统设定">
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showSysCompList');">所属公司</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showSysCompUserList');">系统用户</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('#');">行业设定</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('#');">发布监控</a>
		</div>
		<div style="margin: 10px;">
			<a href="#" onclick="clickMenu('showFileList');">上传文件</a>
		</div>
	</div>
</div>