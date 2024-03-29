<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
	<jsp:include page="../common/_head.jsp" />
	<body>
		<table cellpadding="0" cellspacing="0" width="100%" height="100%"
			style="margin: 0; border: 1px solid #A4BED4;">
			<tr height="100px">
				<td colspan="2">
					<tiles:insertAttribute name="header" />
				</td>
			</tr>
			<tr valign="top">
				<td style="border: 1px solid #A4BED4;" width="200px" nowrap="nowrap">
					<tiles:insertAttribute name="menu" />
					<s:set name="menutitle" scope="session">
						<tiles:getAsString name="menutitle" />
					</s:set>
				</td>
				<td style="border: 1px solid #A4BED4;" width="100%">
					<div style="margin: 0 0 0 5px;">
						<h6>
							<tiles:getAsString name="menutitle" />
							－
							<tiles:getAsString name="bodyhead" />
						</h6>
						<div style="border: 1px solid #ccc; margin-top: 1px;"></div>
						<tiles:insertAttribute name="body" />
					</div>
				</td>
			</tr>
			<tr height="30px">
				<td colspan="2" nowrap="nowrap">
					<tiles:insertAttribute name="footer" />
				</td>
			</tr>
		</table>
	</body>
</html>