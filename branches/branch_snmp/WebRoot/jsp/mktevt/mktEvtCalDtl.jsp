<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="_mktEvt" value="#session.MARKET_EVENT_SESSION_KEY" />
<s:set name="_mktEvtView" value="#session.MARKET_EVENT_VIEW_SESSION_KEY" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="800" border="0"
		style="margin: 10px;">
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				日期:
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.occurDateStr" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				开始时间:
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.beginTimeStr" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				结束时间:
			</td>
			<td width="20%">
				<s:property value="#_mktEvtView.endTimeStr" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				大类:
			</td>
			<td>
				<s:property value="#_mktEvtView.mktevtSuperiorName" />
			</td>
			<td nowrap="nowrap" align="center">
				小类:
			</td>
			<td>
				<s:property value="#_mktEvtView.mktevtName" />
			</td>
			<td nowrap="nowrap" align="center">
				我方人员
			</td>
			<td>
				<s:property value="#_mktEvtView.sysCompUserName" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				所属公司:
			</td>
			<td colspan="3">
				<s:property value="#_mktEvtView.compName" />
			</td>
			<td nowrap="nowrap" align="center">
				实施状态:
			</td>
			<td>
				<s:if test='%{"Y"==#_mktEvt.status}'>
				已实施
				</s:if>
				<s:else>
				未实施
				</s:else>
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				客户名称:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvtView.custName" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				主题:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.subject" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center">
				规模:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.trainScale" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				情况描述:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.situation" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				物料:
			</td>
			<td colspan="5">
				<s:property value="#_mktEvt.goods" />
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center">
				备注:
			</td>
			<td colspan="3">
				<s:property value="#_mktEvt.remark" />
			</td>
			<td colspan="2" align="center" valign="bottom">
				&nbsp;
			</td>
		</tr>
	</table>
</div>