<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title><s:text name="msg.sys.title" />
	</title>
	<%
	    String root = request.getContextPath();
	%>

	<link rel="stylesheet" type="text/css" href="<%=root%>/css/style.css" />
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery-1.6.min.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.form.min.js"></script>
	<script type="text/javascript"
		src="<%=root%>/scripts/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=root%>/scripts/jquery/common.js"></script>
	<script type="text/javascript">
	 <!--	   
	 
	 $.ajaxSetup ({
		cache: false //关闭AJAX相应的缓存
	 });
	 
	 window.onbeforeunload = function() {
		var n = window.event.screenX - window.screenLeft;
		var b = n > document.documentElement.scrollWidth - 20;
		if (b && window.event.clientY < 0 || window.event.altKey) {
			document.location = "logout.action";
			// window.event.returnValue = ""; //这里可以放置你想做的操作代码
		}
	 }
	//-->
	</script>

	<script language="Javascript">
	function document.oncontextmenu(){event.returnValue=false;}//屏蔽鼠标右键    
	function window.onhelp(){return false;} //屏蔽F1帮助    
	function document.onkeydown(){
		////屏蔽 Alt+ 方向键 ←   //屏蔽 Alt+ 方向键 →
		if ((window.event.altKey)&& ((window.event.keyCode==37)||(window.event.keyCode==39))){    
			//alert("不准你使用ALT+方向键前进或后退网页！");    
			event.returnValue=false;    
		}    
		//屏蔽退格删除键(event.keyCode==8) 屏蔽 F5 刷新键  Ctrl + R  
		if ((event.keyCode==116)||(event.ctrlKey && event.keyCode==82)){  
			event.keyCode=0;    
			event.returnValue=false;
		}    
		if (event.keyCode==122){event.keyCode=0;event.returnValue=false;} //屏蔽F11    
		if (event.ctrlKey && event.keyCode==78) event.returnValue=false; //屏蔽 Ctrl+n    
		if (event.shiftKey && event.keyCode==121)event.returnValue=false; //屏蔽 shift+F10    
		if (window.event.srcElement.tagName == "A" && window.event.shiftKey)    
			window.event.returnValue = false; //屏蔽 shift 加鼠标左键新开一网页 
	    //屏蔽Alt+F4 
		if ((window.event.altKey)&&(window.event.keyCode==115)){    
			window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");    
			return false;    
		}    
	}
	//禁止右键弹出菜单         
	function document.oncontextmenu(){return false;}      
</script>

</head>