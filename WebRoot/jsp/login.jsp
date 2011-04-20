<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">
	$.validator.setDefaults({
		submitHandler: function() {		
			$.getJSON("login.action", {
				username: $("#username").val(), 
				password: $("#password").val()
				},
				function(data){				 
				 	if(data.success){				 	    		        	 
			        	document.location ="forward.action?forward=main";
			     	}else{			     	
			     		$("#status").html(data.errors.info);			     		
			     	}
				});
		  }
	});

//
	$(document).ready(function(){
		$(function() {		   
			// highlight 
			var elements = $("input[type!='submit'], textarea, select");
			elements.focus(function(){
				$(this).parents('li').addClass('highlight');
			});
			elements.blur(function(){
				$(this).parents('li').removeClass('highlight');
			});
			$("#loginForm").validate();			
		});
	});
	</script>

	<body>
		<div id="page">
			<div id="header">
				<h1>
					<s:text name="msg.sys.title" />
				</h1>
			</div>
			<div id="content">
				<form id="loginForm">
					<p id="status" class="error" />
					<p></p>
					<fieldset>
						<legend>
							<s:text name="msg.login" />
						</legend>
						<ul>
							<li>
								<s:textfield id="username" name="username"
									key="msg.login.username" cssClass="text required"
									required="true" />
								<label for="username" class="error">
									<s:text name="msg.comm.null">
										<s:param>
											<s:text name="msg.login.username" />
										</s:param>
									</s:text>
								</label>
							</li>
							<li>
								<s:password id="password" name="password" key="msg.login.passwd"
									cssClass="text required" required="true" />
								<label for="password" class="error">
									<s:text name="msg.comm.null">
										<s:param>
											<s:text name="msg.login.passwd" />
										</s:param>
									</s:text>
								</label>
							</li>
						</ul>
					</fieldset>
					<fieldset class="submit">
						<input type="submit" value='<s:text name="msg.button.login"/>'>
					</fieldset>
					<div class="clear"></div>
				</form>
			</div>
		</div>
	</body>
</html>
