<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
		
////////////////////
		$(document).ready(function() {
			$('#test').datagrid({
			    //title: '查询结果',	
			    border:true,						
				nowrap: false,
				striped: true,
				collapsible:false,
				url:'getUserList.action',
				queryParams:{},			
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'id',				
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
					{field:'username',title:'User Name',width:120,sortable:true},
					{field:'password',title:'Password',width:120},
					{field:'email',title:'email',width:120}										
				]],
				toolbar:[{
					id:'btnsearch',
					text:'查询',
					iconCls:'icon-search',
					handler:function(){					 
						document.getElementById('_userSearch').style.display='inline';
						$('#btnadd').linkbutton('disable');
						$('#btncut').linkbutton('disable');					
					}
				},'-',{
					id:'btnadd',
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						document.getElementById('_userUpdate').style.display='inline';
						$('#btnsearch').linkbutton('disable');
						$('#btncut').linkbutton('disable');	
					}
				},'-',{
					id:'btncut',
					text:'删除',
					iconCls:'icon-cut',
					handler:function(){						
						getSelections();
					}
				}]
			});			
		});
		
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			if(rows.length>0){			
			    $.messager.confirm('提示信息', '要删除选中的数据吗？', 
			    	function(r){
						if (r){
							for(var i=0;i<rows.length;i++){
								ids.push(rows[i].id);
							}
							//							
							$.ajax({
		                        url:'delUser.action?id='+ids.join(','),
		                        dataType:'json',	                        
		                        success:function(data){		                       
		                        	if(!data.success){
							   		    $.messager.alert('提示信息',data.errors,'error');
							   		}else{
							   			$('#test').datagrid('clearSelections');
								        reloadDatagrid('test');
							   		}
		                        ;}
		                    });
						}
					}
			    );
			}else{
				$.messager.alert('提示信息','请至少选择一条需要删除的数据！','warning');
			}
		}
							
		$("#_searchAction").click(function() {
			var queryParams = $('#test').datagrid('options').queryParams;		   
		    queryParams.username = $("#username").val();
		    queryParams.password = $("#password").val();  
		    reloadDatagrid('test');
		});
		
		function reloadDatagrid(datagridIdStr){			
			$('#'+datagridIdStr).datagrid('options').pageNumber=1;
		    $('#'+datagridIdStr).datagrid('getPager').pagination({pageNumber: 1});		    
		    $('#'+datagridIdStr).datagrid("reload"); 
		}
		
		function resetForm(formIdStr){
			$('#'+formIdStr).each(function(index){
				$('#'+formIdStr)[index].reset();
			});
		}
		
		function goback(sectionIdStr){
			document.getElementById(sectionIdStr).style.display='none';
			$('#btnsearch').linkbutton('enable');	
			$('#btnadd').linkbutton('enable');
			$('#btncut').linkbutton('enable');	
		}
		
		function saveData(formIdStr){
			var isValid=$('#'+formIdStr).form('validate');
			if(isValid){			   
				var options = {
					url:'addUser.action',
					dataType:'json',
					success:function(data){
				   		if(!data.success){
				   		    $.messager.alert('提示信息',data.errors,'error');
				   		}else{
				   			resetForm(formIdStr);
					        goback('_userUpdate');					        
					        reloadDatagrid('test');
				   		}
					}
				 };				
				 $('#'+formIdStr).ajaxSubmit(options);
			}		      	    
		}
		//	
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
								iconCls="icon-cancel" id="_cancelAction"
								onclick="resetForm('searchFrom');">重置</a>
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-back" id="_backAction"
								onclick="goback('_userSearch');">返回</a>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<div id="_userUpdate" style="display: none;">
		<form id="updateFrom" name="updateFrom"
			style="margin: 5px; text-align: left" method="post">
			<input name="actionFlag" id="actionFlag" type="hidden" value="A">
			<input name="id" id="id" type="hidden">
			<fieldset>
				<legend>
					基本数据
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
							<input id="username" name="username" class="easyui-validatebox"
								required="true" validType="length[1,20]" />
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
							<input id="password" name="password" type="text"
								class="easyui-validatebox" required="true"
								validType="length[1,20]" />
						</td>
					</tr>
					<tr height="30px">
						<td width="30%" align="center">
							<label id="lemail" for="email">
								邮件地址:
							</label>
						</td>
						<td>
							<input id="email" name="email" type="text"
								class="easyui-validatebox" validType="email" />
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
								iconCls="icon-save" id="_saveAction"
								onclick="saveData('updateFrom');">保存</a>
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-cancel" id="_cancelAction"
								onclick="resetForm('updateFrom');">重置</a>
							<a href="#" class="easyui-linkbutton" plain="true"
								iconCls="icon-back" id="_backAction"
								onclick="goback('_userUpdate');">返回</a>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<div style="margin: 5px;" align="left">
		<table id="test"></table>
	</div>
</body>
