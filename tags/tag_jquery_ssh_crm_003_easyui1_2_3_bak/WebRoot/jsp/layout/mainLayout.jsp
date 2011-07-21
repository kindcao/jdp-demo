<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
	<jsp:include page="../common/_head.jsp" />
	<body>
		<s:set name="_sysUser" value="#session.CURR_SYS_USER_SESSION_KEY"
			scope="session"></s:set>
		<s:set name="_sysUserComp"
			value="#session.CURR_SYS_USER_COMP_SESSION_KEY" scope="session"></s:set>
		<table cellpadding="0" cellspacing="0" width="100%" height="100%"
			style="margin: 0; border: 1px solid #A4BED4;">
			<tr height="100px">
				<td>
					<tiles:insertAttribute name="header" />
				</td>
			</tr>
			<tr valign="top">
				<td style="border: 1px solid #A4BED4;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%"
						height="100%">
						<tr valign="top">
							<td width="200px">
								<s:set name="menutitle" scope="session">
									<tiles:getAsString name="menutitle" />
								</s:set>
								<tiles:insertAttribute name="menu" />
							</td>
							<td>
								<div style="margin: 0 0 0 5px;">
									<h6>
										<tiles:getAsString name="menutitle" />
										－
										<tiles:getAsString name="bodyhead" />
									</h6>
									<div style="border: 1px solid #A4BED4;"></div>
									<tiles:insertAttribute name="body" />
								</div>
							</td>
						</tr>
					</table>
				<td>
			</tr>
			<tr height="30px">
				<td>
					<tiles:insertAttribute name="footer" />
				</td>
			</tr>
		</table>
	</body>
</html>