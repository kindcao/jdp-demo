<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>

	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">
		$(function(){			
			$('body').layout();			
		});
		
		function clickMenuItem(){
			
		}
	</script>

	<body>
		<div region="north" split="false"
			style="height: 100px; padding: 5px; overflow: hidden;">
			&nbsp;
		</div>
		<div region="south" split="false"
			style="height: 30px; padding: 5px; overflow: hidden;">
			&nbsp;
		</div>
		<div region="west" split="false" title="dsfsf"
			style="width: 200px; padding1: 0px; overflow: hidden;">
			<div class="accordion-container" fit="true">
				<div title="title1" selected="true">
					<a href="#" onclick="clickMenuItem('title1');">content12</a>
				</div>
				<div title="title2">
					content2
				</div>
				<div title="title3">
					content3
				</div>
			</div>
		</div>
		<div region="center" title="center title"
			style="padding1: 5px; background: #aaa;">
			<div id="tabs-container" class="tabs-container" fit="true"
				style="overflow: hidden;">
				<div title="Tab1" closable="true"
					style="padding: 20px; display: none;">
				</div>
				<div title="Tab2" closable="true"
					style="padding: 20px; display: none;">
					&nbsp;
				</div>
			</div>
		</div>
	</body>
</html>