<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script>
		$(function(){
			$('#tt2').datagrid({
				title:'my table',
				width:800,
				height:500,
				fit: true,
				border: false,
				url:'datagrid_data.json',
				sortName: 'code',
				sortOrder: 'desc',
				columns:[[
				          {title:'Base Information',colspan:3},
							{field:'opt',title:'操作',width:100,align:'center', rowspan:2,
								formatter:function(value,rec){
									return '<span style="color:red">Edit Delete</span>';
								}
							}
				          
				],[
							{field:'name',title:'名称',width:120},
							{field:'addr',title:'地址',width:120,rowspan:2,sortable:true},
							{field:'col4',title:'Col41',width:150,rowspan:2}
				]],
				pagination:true
				
			});
			$('body').layout();
			$('#tree1').tree({url:'tree_data.json'});
		});
	</script>
	<body>
		<div region="north" split="false"
			style="height: 100px; padding: 10px;">
			<p>
				n1
			</p>
		</div>
		<div region="west" split="false" title="导航菜单"
			style="width: 250px; padding1: 1px;">
			<div class="accordion-container" fit="true" border="false">
				<div title="title1">
					<p>
						content1
					</p>
					<p>
						content1
					</p>
					<p>
						content1
					</p>
				</div>
				<div title="title2" selected="true" style="padding: 10px;">
					content2
				</div>
				<div title="title3">
					content3
				</div>
			</div>
		</div>
		<div region="center" split="false" title="center title"
			style="padding1: 5px; background: #aaa;">
			<div class="tabs-container" fit="true" border="false"
				style="height: 200px;">
				<div title="Tab1" style="padding: 20px; display: none;">
					<div style="margin-top: 20px;">
						<p>
							我们对系统进行简化，旨在说明一般功能的开发方法。
						</p>
					</div>
				</div>
				<div title="Tab2" closable="true"
					style="padding: 20px; display: none;">
					This is Tab2 width close button.
				</div>
				<div title="Tab3" fit="true" icon="icon-reload" closable="true"
					style="overflow: hidden; padding1: 20px;">
					<table id="tt2"></table>
				</div>
				<div title="Tab4 with iframe" fit="true" closable="true"
					style="width: 100%; height: 100%; display: none;">
				</div>
			</div>
		</div>
	</body>
</html>
