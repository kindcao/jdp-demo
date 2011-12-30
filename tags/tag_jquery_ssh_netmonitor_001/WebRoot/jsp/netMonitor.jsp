<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="margin: 0 0 0 5px;">
	<h6>
		&nbsp;
	</h6>
	<div style="border: 1px solid #A4BED4;"></div>
	<div id="div_search" style="display: inline;">
		<form id="infoForm" name="infoForm">
			<fieldset>
				<legend>
					参数信息
				</legend>
				<table cellpadding="0" cellspacing="0" width="850" border="0"
					style="margin: 10px;">
					<tr height="30px">
						<td class="label-title" width="15%">
							主机地址：
						</td>
						<td width="20%">
							<input id="sr_address" name="sr.address" type="text"
								class="easyui-validatebox" required="true"
								value="udp:127.0.0.1/161" />
						</td>
						<td class="label-title" width="15%">
							读取参数：
						</td>
						<td width="20%">
							<input name="sr.community" type="text" class="easyui-validatebox"
								validType="length[1,50]">
						</td>
						<td class="label-title" width="15%">
							超时时间（秒）：
						</td>
						<td width="20%">
							<input name="sr.timeout" type="text" class="easyui-validatebox"
								validType="number">
						</td>
					</tr>
					<tr height="30px">
						<td class="label-title" width="15%">
							重试次数：
						</td>
						<td width="20%">
							<input name="sr.retries" type="text" class="easyui-validatebox"
								validType="number">
						</td>
						<td class="label-title" width="15%">
							检测周期（秒）：
						</td>
						<td width="20%">
							<input name="period" type="text" class="easyui-validatebox"
								validType="number">
						</td>
						<td colspan="2" align="center" valign="bottom">
							<a href="javascript:void(0);" class="easyui-linkbutton"
								plain="true" iconCls="icon-add" id="_input">输入</a>
							<a href="javascript:void(0);" class="easyui-linkbutton"
								plain="true" iconCls="icon-remove" id="_reset_input">重置</a>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
		<div style="height: 10px;">
			&nbsp;
		</div>
		<div style="height: 10px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" plain="true"
				iconCls="icon-go" id="_start">启动</a>
		</div>
		<div align="left">
			<table id="grid-datalist-netmonitor"></table>
		</div>
	</div>
</div>

<script type="text/javascript">
<!--

$("#_start").click(function() {	  
	//reloadDatagrid('grid-datalist-netmonitor');
});

$("#_input").click(function() {
	var isValid=$('#infoForm').form('validate');	
	if(isValid){							   
		var options = {
			url:'inputSnmpPara',
			dataType:'json',
			success:function(data){
				//if(!data.success){
		   			//$.messager.alert('提示信息',data.errors,'error');
				//}else{
					//resetForm(formIdStr);
				    // goback('_userUpdate');					        
				    //reloadDatagrid('test');
				//}
			}
		};
	}			
	$('#infoForm').ajaxSubmit(options);
	//
	$('#_input').linkbutton('disable');
	$('#_reset_input').linkbutton('enable');
});

$("#_reset_input").click(function() {
	$('#_input').linkbutton('enable');
	$('#_reset_input').linkbutton('disable');
	resetForm('infoForm');
});

$(document).ready(function() {
	var frozenColumns = [[{
			field : 'ck',
			checkbox : true
		}, {
			field : 'ifDescr',
			title : '名称',
			width : 200,
			sortable : true,			
			formatter : function(value, rec) {
				return  value;
			}
		}]];
	var columns = [[{
	    	field : 'ifIndex',
			title : '图表',
			width : 400,
			formatter : function(value, rec) {				
				return value;			
			}
		},	{
			field : 'totalIfInOctets',
			title : '总入口流量（字节）',
			width : 150,
			formatter : function(value, rec) {				
				return value;
			}
		}, {
			field : 'totalIfOutOctets',
			title : '总出口流量（字节）',
			width : 150,
			formatter : function(value, rec) {				
				return value;
			}
		}, {
			field : 'totalTime',
			title : '监控时长（秒）',
			width : 150,
			formatter : function(value, rec) {				
				return value;
			}
		}]];
	//		
	showDatagrid('grid-datalist-netmonitor','getDataList',frozenColumns,columns);						
});	
//-->
</script>
