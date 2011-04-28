<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
	<jsp:include page="../common/_head.jsp" />
	<body>
		<table cellpadding="0" cellspacing="0" width="100%" height="80%"
			border="0">
			<tr height="100px">
				<td>
					<tiles:insertAttribute name="header" />
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insertAttribute name="body" />
				</td>
			</tr>
			<tr height="250px">
				<td>
					<tiles:insertAttribute name="footer" />
				</td>
			</tr>
		</table>

	</body>
</html>