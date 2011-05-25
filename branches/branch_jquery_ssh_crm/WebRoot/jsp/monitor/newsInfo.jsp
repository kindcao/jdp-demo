<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#div_info_read-only table {
	margin-top: 10px;
	border: 1px solid #A4BED4;
}

#div_info_read-only table th {
	padding-left: 10px;
	border-bottom: 1px solid #A4BED4;
	text-align: left;
}

#infoForm table {
	margin-top: 10px;
	border: 1px solid #A4BED4;
}

#infoForm table th {
	padding-left: 10px;
	border-bottom: 1px solid #A4BED4;
	text-align: left;
}
-->
</style>

<s:set name="_news" value="#session.MONITOR_NEWS_VIEW_SESSION_KEY" />
<div id="div_info_read-only" style="margin-top: 10px; display: inline;">
	<table cellpadding="0" cellspacing="0" width="900" border="0">
		<tr height="30px">
			<th width="50%" colspan="2" style="border-right: 1px solid #A4BED4;">
				采访情况
			</th>
			<th width="50%" colspan="2">
				发布情况
			</th>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				采访日期:
			</td>
			<td width="40%" style="border-right: 1px solid #A4BED4;">
				<s:property value="#_news.interviewDateStr" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				发布日期:
			</td>
			<td width="40%">
				<s:property value="#_news.publishDateStr" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				媒体:
			</td>
			<td width="40%" style="border-right: 1px solid #A4BED4;">
				<s:property value="#_news.media" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				标题:
			</td>
			<td width="40%">
				<s:property value="#_news.subject" />
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				记者:
			</td>
			<td width="40%" style="border-right: 1px solid #A4BED4;">
				<s:property value="#_news.reporter" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				截图URI:
			</td>
			<td width="40%">
				<s:if test="%{#_news.picture.trim().length()>0}">
					<a href="#" onclick=window.open('<s:property value="#_news.picture" />')><s:property
							value="#_news.picture" /> </a>
				</s:if>
			</td>
		</tr>
		<tr height="30px">
			<td nowrap="nowrap" align="center" width="10%">
				受访人:
			</td>
			<td width="40%" style="border-right: 1px solid #A4BED4;">
				<s:property value="#_news.participant" />
			</td>
			<td nowrap="nowrap" align="center" width="10%">
				网址:
			</td>
			<td width="40%">
				<s:if test="%{#_news.url.trim().length()>0}">
					<a href="#" onclick=window.open('<s:property value="#_news.url" />')><s:property
							value="#_news.url" /> </a>
				</s:if>
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center" width="10%">
				采访问题:
			</td>
			<td width="40%" style="border-right: 1px solid #A4BED4;">
				<s:property value="#_news.content" />
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td nowrap="nowrap" align="center" width="10%">
				其它:
			</td>
			<td width="40%" style="border-right: 1px solid #A4BED4;">
				<s:property value="#_news.remark" />
			</td>
			<td>
				&nbsp;
			</td>
			<td nowrap="nowrap" align="right" valign="bottom">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-edit" id="_edit">修改</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-back" id="_back">返回</a>
			</td>
		</tr>
	</table>
</div>
<div id="div_info" style="margin-top: 10px; display: none;">
	<form id="infoForm" name="infoForm">
		<input type="hidden" name="newsExtDto.id"
			value='<s:property value="#session.MONITOR_NEWS_VIEW_SESSION_KEY.id" />' />
		<s:hidden name="actionFlag" value="U" />
		<table cellpadding="0" cellspacing="0" width="900" border="0"
			style="margin: 10px;">
			<tr height="30px">
				<th width="50%" colspan="2" style="border-right: 1px solid #A4BED4;">
					采访情况
				</th>
				<th width="50%" colspan="2">
					发布情况
				</th>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					采访日期:
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" id="newsExtDto_interviewDateStr"
						name="newsExtDto.interviewDateStr" class="easyui-datebox"
						required="true"
						value='<s:property value="#_news.interviewDateStr" />'>
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					发布日期:
				</td>
				<td width="40%">
					<input type="text" id="newsExtDto_publishDateStr"
						name="newsExtDto.publishDateStr" class="easyui-datebox"
						required="true"
						value='<s:property value="#_news.publishDateStr" />'>
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					媒体:
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" name="newsExtDto.media" maxlength="200"
						style="width: 350px;" value='<s:property value="#_news.media" />' />
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					标题:
				</td>
				<td width="40%">
					<input type="text" name="newsExtDto.subject" maxlength="200"
						style="width: 350px;" class="easyui-validatebox" required="true"
						value='<s:property value="#_news.subject" />' />
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					记者:
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" name="newsExtDto.reporter" maxlength="200"
						style="width: 350px;"
						value='<s:property value="#_news.reporter" />' />
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					截图URI:
				</td>
				<td width="40%">
					<input type="text" name="newsExtDto.picture" maxlength="200"
						style="width: 350px;" class="easyui-validatebox" validType="url"
						value='<s:property value="#_news.picture" />' />
				</td>
			</tr>
			<tr height="30px">
				<td nowrap="nowrap" align="center" width="10%">
					受访人:
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<input type="text" name="newsExtDto.participant" maxlength="200"
						style="width: 350px;"
						value='<s:property value="#_news.participant" />' />
				</td>
				<td nowrap="nowrap" align="center" width="10%">
					网址:
				</td>
				<td width="40%">
					<input type="text" name="newsExtDto.url" maxlength="200"
						style="width: 350px;" class="easyui-validatebox" validType="url"
						value='<s:property value="#_news.url" />' />
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center" width="10%">
					采访问题:
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<textarea id="newsExtDto_content" name="newsExtDto.content"
						rows="5" style="width: 350px;" class="easyui-validatebox"
						validType="length[0,6000]"></textarea>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr height="30px" valign="top">
				<td nowrap="nowrap" align="center" width="10%">
					其它:
				</td>
				<td width="40%" style="border-right: 1px solid #A4BED4;">
					<textarea id="newsExtDto_remark" name="newsExtDto.remark" rows="5"
						style="width: 350px;" class="easyui-validatebox"
						validType="length[0,2000]"></textarea>
				</td>
				<td>
					&nbsp;
				</td>
				<td nowrap="nowrap" align="right" valign="bottom">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-save" id="_save">保存</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" id="_reset">重置</a>
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-back" id="_back_edit">返回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" defer="defer">
<!--	
	$("#_back").click(function() {
		window.location.href='showNewsList.action';			
	});	
	
	$("#_back_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='inline';
		document.getElementById('div_info').style.display='none';
	});
	
	$("#_edit").click(function() {
		document.getElementById('div_info_read-only').style.display='none';
		document.getElementById('div_info').style.display='inline';		
		//
		document.getElementById('newsExtDto_content').value='<s:property value="#_news.content" escape="false" />';
		document.getElementById('newsExtDto_remark').value='<s:property value="#_news.remark" escape="false" />';
	});
		
	$("#_reset").click(function() {		
		$("#newsExtDto_interviewDateStr").datebox('clear');
		$("#newsExtDto_publishDateStr").datebox('clear');	
		resetForm('infoForm');
	});
	
	$("#_save").click(function() {
		var isValid = $('#infoForm').form('validate');	
		if (isValid) {			
			var options = {
				url : 'saveNewsInfo.action',
				dataType : 'json',
				type: 'post',
				//contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data){
					if (!data.success) {
						$.messager.alert('提示信息', data.errors, 'error');
					} else {
						window.location.href='showNewsInfo.action?newsExtDto.id=<s:property value="#_news.id" />';
					}
				}
			};
			$('#infoForm').ajaxSubmit(options);			
		}
	});		
//-->
</script>