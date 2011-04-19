<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<script type="text/javascript">
		
	$(function() {
		// highlight 
		var elements = $("input[type!='submit'], textarea, select");
		elements.focus(function(){
			$(this).parents('li').addClass('highlight');
		});
		elements.blur(function(){
			$(this).parents('li').removeClass('highlight');
		});
		
		$("#forgotpassword").click(function() {
			$("#password").removeClass("required");
			$("#login").submit();
			$("#password").addClass("required");
			return false;
		});
		
		$("#login").validate()
	});
	</script>

	<body>
		<div id="page">

			<div id="header">
				<h1>
					Login
				</h1>
			</div>

			<div id="content">
				<p id="status"></p>
				<form action="" method="get" id="login">
					<fieldset>
						<legend>
							User details
						</legend>
						<ul>
							<li>
								<label for="email">
									<span class="required">Email address</span>
								</label>
								<input id="email" name="email" class="text required email"
									type="text" />
								<label for="email" class="error">
									This must be a valid email address
								</label>
							</li>

							<li>
								<label for="password">
									<span class="required">Password</span>
								</label>
								<input name="password" type="password" class="text required"
									id="password" minlength="4" maxlength="20" />
							</li>

							<li>
								<label class="centered info">
									<a id="forgotpassword" href="#">Email my password...</a>
								</label>
							</li>
						</ul>
					</fieldset>

					<fieldset class="submit">
						<input type="submit" class="button" value="Login..." />
					</fieldset>

					<div class="clear"></div>
				</form>

			</div>
		</div>

	</body>
</html>
