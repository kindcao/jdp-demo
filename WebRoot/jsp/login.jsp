<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">
$(document).ready(function() {
	// validate signup form on keyup and submit
	var validator = $("#signupform").validate({
		rules: {			
			username: {
				required: true
			},
			password: {
				required: true				
			}	
		},
		messages: {			
			username: {
				required: '<s:text name="msg.comm.null"><s:param><s:text name="msg.login.username" /></s:param></s:text>'				
			},
			password: {
				required: '<s:text name="msg.comm.null"><s:param><s:text name="msg.login.passwd" /></s:param></s:text>'
		    }
		},
		// the errorPlacement has to take the table layout into account
		errorPlacement: function(error, element) {
			if ( element.is(":radio") )
				error.appendTo( element.parent().next().next() );
			else if ( element.is(":checkbox") )
				error.appendTo ( element.next() );
			else
				error.appendTo( element.parent().next() );
		},
		// specifying a submitHandler prevents the default submit, good for the demo
		submitHandler: function() {		  
			$.getJSON("login.action", {
				username: $("#username").val(), 
				password: $("#password").val()
				},
				function(data){				    					 
				 	if(data.success){		
				 	 	window.location.href="forward.action?forward=main";
			     	}else{			     	
			     		$("#errormsg").html(data.errors);			     		
			     	}
				}
			);			
		},
		// set this class to error-labels to indicate valid fields
		success: function(label) {
			// set &nbsp; as text for IE
			label.html("&nbsp;").addClass("checked");
		}
	});	
});
</script>

	<body>
		<div id="main">
			<div id="content">
				<div id="header">
					<div id="headerlogo" />
					</div>
					<div style="clear: both;">
						<div></div>
					</div>
					<div class="content">
						<div id="signuptab">
							<ul>
								<li id="signupcurrent">
									<a href="#">Signup</a>
								</li>
							</ul>
						</div>
						<div id="signupwrap">
							<form id="signupform" name="signupform" autocomplete="off"
								method="post">
								<table>
									<tr>
										<td class="label">
											<label id="lusername" for="username">
												<s:text name="msg.login.username" />
												:
											</label>
										</td>
										<td class="field">
											<input id="username" name="username" type="text"
												value="admin" maxlength="20" />
										</td>
										<td class="status"></td>
									</tr>
									<tr>
										<td class="label">
											<label id="lpassword" for="password">
												<s:text name="msg.login.passwd" />
												:
											</label>
										</td>
										<td class="field">
											<input id="password" name="password" type="password"
												maxlength="50" value="admin" />
										</td>
										<td class="status"></td>
									</tr>
									<tr>
										<td class="label">
											<label>
												&nbsp;
											</label>
										</td>
										<td class="field" colspan="2"
											style="vertical-align: top; padding-top: 2px;">
											<table>
												<tbody>
													<tr>
														<td style="padding-right: 5px;">
															<input id="dateformat_eu" name="dateformat"
																type="checkbox" value="0" />
															<label id="ldateformat_eu" for="dateformat_eu">
																save pssward
															</label>
														</td>
														<td style="padding-left: 5px;">
															<input id="dateformat_am" name="dateformat"
																type="checkbox" value="1" />
															<label id="ldateformat_am" for="dateformat_am">
																change pssward
															</label>
														</td>
														<td>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td class="label">
											&nbsp;
										</td>
										<td class="field" colspan="2" id="errormsg">
										</td>
									</tr>
									<tr>
										<td class="label">
											&nbsp;
										</td>
										<td class="field" colspan="2">
											<input id="signupsubmit" name="signup" type="submit"
												value='<s:text name="msg.button.login"/>' />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
	</body>
</html>