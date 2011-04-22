<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">
		$(function(){			
			$('body').layout();			
		});
	    //////////
		var index = 1;
		function addTab(thisObj,url){		
		    var str=thisObj.innerHTML.replace(/^\s+|\s+$/g,"");		   
		    if($('#tabs-container').tabs('exists',str)){
		    	$('#tabs-container').tabs('select',str);
		    }else{
		    	index++;
				var _tab={
					title: str,
					href: url,
					cache: false,					
					closable: true
				}			
				$('#tabs-container').tabs('add',_tab);
		    }
		}		
	</script>

	<body>
		<div id="region-north" region="north" split="false">
		</div>
		<div region="south" split="false"
			style="height: 30px; padding: 5px; overflow: hidden;">
			&nbsp;
		</div>
		<div region="west" split="false" title="导航菜单"
			style="width: 200px; padding1: 0px; overflow: ">
			<div class="accordion-container" fit="true">
				<div title="客户管理">
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">券商</a>
					</div>
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">銀行</a>
					</div>
				</div>
				<div title="市场工作">
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">日历</a>
					</div>
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">统计</a>
					</div>
				</div>
				<div title="市场监控">
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">发布监控</a>
					</div>
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">行业监控</a>
					</div>
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">新闻专访</a>
					</div>
				</div>
				<div title="开户工作">
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">开户统计</a>
					</div>
				</div>
				<div title="系统设定" selected="true">
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'showUserList.action');">用户设定</a>
					</div>
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">行业设定</a>
					</div>
					<div style="margin: 10px;">
						<a href="#" onclick="addTab(this,'');">发布监控</a>
					</div>
				</div>
			</div>
		</div>
		<div region="center" style="padding1: 5px; background: #aaa;">
			<div id="tabs-container" class="tabs-container" fit="true"
				style="overflow: hidden;">
				<div title="默认信息" style="padding: 0px; display: none;">
					<!--div class="tabs-container" plain="true" style="padding: 0px;
					overflow: hidden;">
					<div title="子标题1" href="#">
						子内容1
					</div>
					<div title="子标题2">
						子内容2
					</div>
					<div title="子标题3">
						子内容3
					</div>
					</div-->
				</div>
			</div>
		</div>
	</body>
</html>