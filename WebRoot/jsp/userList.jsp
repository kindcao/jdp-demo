<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_toolbar.jsp" />

	<script type="text/javascript">
<!--	
		$("#_searchAction").click(function() {			
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
				   $.getJSON("getUserList.action", {
							username: $trim($("#username").val()), 
							password: $trim($("#password").val())
						},
						function(data){								 
						 	if(data.jvrResult.success){				 	    		        	 
					        	alert('succ');
					     	}else{			     	
					     		alert('error');			     		
					     	}
						}
					);	
				},
				success: function(label) {				
					// set &nbsp; as text for IE
					label.html("&nbsp;").addClass("checked");
				}
			});					
		    //
		    $("#searchFrom").submit();
		});		
//-->
</script>


	<style type="text/css">
#searchFrom {
	width: 100%;
	text-align: left;
	vertical-align: middle;
	display: inline;
}

#searchFrom label {
	width: 15%;
	margin-left: 20px;
	display: inline;
}

#searchFrom input {
	width: 180px;
	display: inline;
}

#searchFrom label.error {
	width: auto;
	margin-left: 20px;
	display: inline;
	color: red;
}
</style>
	<body>
		<div id="searchPanel">
			<br>
			<form id="searchFrom" name="searchFrom" method="post"
				autocomplete="off">
				<fieldset style="margin: 0, 0, 0, 5px; border: 1px solid #A4BED4;">
					<legend>
						查询条件
					</legend>
					<p>
						<label id="lusername" for="username">
							<s:text name="msg.login.username" />
							:
						</label>
						<input id="username" name="username" />
					</p>
					<p>
						<label id="lpassword" for="password">
							<s:text name="msg.login.passwd" />
							:
						</label>
						<input id="password" name="password" type="password" />
					</p>
					<p>
						<label>
							&nbsp;
						</label>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-search" id="_searchAction">查询</a>
						<a href="#" class="easyui-linkbutton" plain="true"
							iconCls="icon-cancel">取消</a>
					</p>
				</fieldset>
			</form>
		</div>
		<br />
		<div id="dataGridPanel">
			<table align="left" width="100%" border="0" style="margin: 0px;">
				<tr>
					<td>
						<s:url id="remoteurl" action="getUserList.action" />
						<sjg:grid id="gridtable" caption="Customer Examples"
							dataType="json" href="%{remoteurl}" pager="true"
							gridModel="gridModel" rowList="10,15,20" rowNum="3"
							rownumbers="true" multiselect="true"
							onSelectRowTopics="rowselect">
							<sjg:gridColumn name="id" index="id" title="ID"
								formatter="integer" sortable="false" />
							<sjg:gridColumn name="username" index="username" title="Name"
								sortable="true" />
							<sjg:gridColumn name="password" index="password" title="Password"
								sortable="false" />
						</sjg:grid>
					</td>

				</tr>
			</table>
		</div>
	</body>
</html>