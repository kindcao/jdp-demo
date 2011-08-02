<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery/swfobject.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery/jquery.uploadify.v2.1.4.min.js"></script>

<jsp:include page="../common/_toolbar.jsp"></jsp:include>
<div id="div_info" style="margin-top: 10px;">
	<fieldset>
		<legend>
			上传文件
		</legend>
		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			<tr>
				<td colspan="2" style="padding-left: 5px;">
					<div id="fileQueue"></div>
					<div id="result"></div>
				</td>
			</tr>
			<tr>
				<td width="90%" align="right">
					<input type="file" name="fileData" id="uploadify" />
				</td>
				<td width="10%" nowrap="nowrap" align="center">
					<a href="javascript:uploadFile()">开始</a>&nbsp;|&nbsp;
					<a href="javascript:clearFile()">取消</a>
				</td>
			</tr>
		</table>
	</fieldset>
</div>
<div style="height: 10px;">
	&nbsp;
</div>
<div align="left">
	<table id="grid-datalist"></table>
</div>
<script type="text/javascript" defer="defer">
	$(document).ready(function(){
		$('#_upload').linkbutton('disable');
		//		
        $("#uploadify").uploadify({
        	uploader : 'images/uploadify.swf',
        	cancelImg : 'images/icons/cancel.png',
            script : 'upload',            
            folder : '/uploads/',
       		fileDataName : 'fileData',
       		queueID : 'fileQueue',
       		auto : false,
       		multi: true,       		
       		queueSizeLimit : 3,
       		buttonText : 'BROWSE',
       		fileDesc : '支持格式:(jpg/gif/jpeg/png/bmp)',
       		fileExt : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
       		sizeLimit : 1024*1024,       	
            onAllComplete : function(event,data) {
		      	$("#result").html("success:"+data.filesUploaded + ', errors:'+data.errors
		      	+", allBytesLoaded:"+data.allBytesLoaded+" bytes, speed:"+Math.round(data.speed*100)/100+" KB/s");
		      	setInterval("showResult()",10000);
		      	reloadDatagrid('grid-datalist');
		    }		   		    
        });
        //
        var frozenColumns = [[{
				field : 'fileName',
				title : '文件名称',
				width : 300,
				sortable : true
			}]];
		var columns = [[{
			field : 'fileURI',
			title : '文件URL',
			width : 350,
			formatter : function(value, rec) {
				if(value){
					return "<a href='#' onclick=_openWin('${pageContext.request.contextPath}','" 
					+ value + "');>" + cutstr(value,80) + "</a>";
				}
			}
		},{
			field : 'uploadDate',
			title : '上传日期',
			width : 200
		},{
			field : 'fileSize',
			title : '文件大小(K)',
			width : 100,
			formatter : function(value, rec) {				
				return value/1000;
			}
		}]];
		//		
		showDatagridNoPagination('grid-datalist','getFileList',frozenColumns,columns); 
	});
	//
	function showResult(){
    	$('#result').html('');
    }
    function uploadFile(){
    	$('#uploadify').uploadifyUpload();
    }
    function clearFile(){
    	$('#uploadify').uploadifyClearQueue();
    }
</script>
