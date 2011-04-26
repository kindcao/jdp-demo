<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />
	<script type="text/javascript">
		function reLogin(){		
			document.location ='login.action';			
		}
	</script>

	<body>
		<h1>
			系统出现异常，请联系管理员或重新
			<a href="#" onclick="reLogin();">登录</a>
		</h1>
	</body>
</html>