<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
		
////////////////////
		$(document).ready(function() {
			$('#test').datagrid({							
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'getUserList.action',
				queryParams:{},			
				sortName: 'code',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'code',				
				fitColumns: true,				
				rownumbers:false,
				pagination:true,
				pageNumber : 1,
				pageSize : 3,
				pageList : [3,10],									
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {field:'id',title:'ID',width:80,sortable:true}
				]],
				columns:[[
					{field:"username",title:"User Name",width:120},
					{field:'password',title:'Password',width:120,rowspan:2,sortable:true,
						sorter:function(a,b){						     
							return (a>b?1:-1);
						}
					},
					{field:"email",title:"email",width:120}										
				]],
				
				toolbar:[{
					id:'btnsearch',
					text:'查询',
					iconCls:'icon-search',
					handler:function(){					 
						document.getElementById('_userSearch').style.display='inline';						
					}
				},'-',{
					id:'btnadd',
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						//$('#btnsave').linkbutton('enable');
						alert('add')
					}
				},'-',{
					id:'btncut',
					text:'删除',
					iconCls:'icon-cut',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						alert('删除')
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
			if (p){
				$(p).pagination({
					onBeforeRefresh:function(){
						alert('before refresh');
					}
				});
			}
		});
		
		function getSelected(){
			var selected = $('#test').datagrid('getSelected');
			if (selected){
				alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
			}
		}
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			$('#test').datagrid('clearSelections');
		}
		function selectRow(){
			$('#test').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#test').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#test').datagrid('unselectRow',2);
		}
		/**		
		function resize(){
			$('#test').datagrid('resize', {
				width:700,
				height:400
			});
		}
		
		function mergeCells(){
			$('#test').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}**/
			
		
	
			/**
			$("#searchFrom").validate({
				rules: {			
					username: {
						required: true				
					}
				},
				messages: {			
					username: {
						required: '<s:text name="msg.comm.null"><s:param><s:text name="msg.login.username" /></s:param></s:text>'			
					}		
				},
				submitHandler: function() {
					      		
				},
				success: function(label) {				
					// set &nbsp; as text for IE
					label.html("&nbsp;").addClass("checked");
				}
			});		
			//
			//$("#searchFrom").submit() **/
			
		$("#_searchAction").click(function() {
			var queryParams = $('#test').datagrid('options').queryParams;		   
		    queryParams.username = $("#username").val();
		    queryParams.password = $("#password").val();		    
		    $('#test').datagrid('options').queryParams=queryParams;	
		    $('#test').datagrid('options').pageNumber=1;
		    $('#test').datagrid('getPager').pagination({pageNumber: 1});		    
		    $("#test").datagrid("reload"); 		
		});
		
		$("#_cancelAction").click(function() {
		    $('#searchFrom').each(function(index){
				$('#searchFrom')[index].reset();
			});			
		});
		
		$("#_backAction").click(function() {		    
			document.getElementById('_userSearch').style.display='none';
		});
		
	</script>

<body>
	<div id="_userSearch" style="display: none;">
		<form id="searchFrom" name="searchFrom"
			style="margin: 5px; text-align: left">
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table cellspacing="0" cellpadding="0" width="400"
					style="margin: 10px; border: 1px solid #A4BED4;">
					<tr height="30px">
						<td width="30%" align="center">
							<label id="lusername" for="username">
								<s:text name="msg.login.username" />
								:
							</label>
						</td>
						<td>
							<input id="username" name="username" />
						</td>
					</tr>
					<tr height="30px">
						<td width="30%" align="center">
							<label id="lpassword" for="password">
								<s:text name="msg.login.passwd" />
								:
							</label>
						</td>
						<td>
							<input id="password" name="password" type="password" />
						</td>
					</tr>
					<tr>
						<td width="30%" align="center">
							<label>
								&nbsp;
							</label>
						</td>
						<td align="right">
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-search" id="_searchAction">查询</a>
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-cancel" id="_cancelAction">重置</a>
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-back" id="_backAction">返回</a>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<div style="margin: 5px;">
		<table id="test"></table>
	</div>

</body>
