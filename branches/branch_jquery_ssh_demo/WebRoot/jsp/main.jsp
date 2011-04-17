<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<!-- include head jsp -->
	<jsp:include page="_head.jsp" />

	<!-- include theme jsp -->
	<jsp:include page="_theme.jsp" />

	<script type="text/javascript">
    
    Ext.onReady(function(){
        Ext.BLANK_IMAGE_URL = '<%=request.getContextPath()%>/scripts/extjs2.0/resources/images/default/s.gif';

	    /////////
 		var root = new Ext.tree.TreeNode();          
        root.appendChild(new Ext.tree.TreeNode({
              text:'书籍类型维护',
              url:'showBookTypeList.action'
        }));                
        root.appendChild(new Ext.tree.TreeNode({
              text:'书籍维护',
              url:'showBookList.action'
        }));
        root.appendChild(new Ext.tree.TreeNode({
              text:'用户管理',
              url:'showUserList.action'
        }));
        
        var menu = new Ext.tree.TreePanel({
              border:false,
              root:root,
              rootVisible:false,
              lines:false,
              listeners:{
                  click:function(node,e){
                      for(var i=0; i<tabPanel.items.length;i++){
                          if(tabPanel.items.items[i].title==node.text){  
                              tabPanel.setActiveTab(tabPanel.items.items[i]);
                              return;
                          }
                      }
                      //if(tabPanel.items.length>2){
                      //    alert("最多只能打开3个活动面板！");
                      //    return;
                      //}   
                      //var index = tabPanel.items.length+1;
                      //var tabPage = 'tabPage'+index;
                      var tabPage = new Ext.Panel({
                            title:node.text,
                            closable:true                           
                      });
                      
		           
		              tabPanel.add(tabPage);
		              tabPanel.setActiveTab(tabPage);
                      //alert(node.attributes.url);
                      tabPage.load({
                          url:node.attributes.url,
                          callback:function(){
                              //alert(node.text);
                              //tabPage.setTitle(node.text);
                          },
                          scripts:true
                      });
                     // tabPage.rederTo(Ext.getBody());   
                  }                  
              }
        });
        
        var tabPanel = new Ext.TabPanel({
		  id:'p4',
	      //height:475,
	      //width:855,
	      border:false,	    
	      activeTab:0,	 
	      animScroll:true,
	      enableTabScroll:true,
	      layoutOnTabChange:true,
	      deferredRender:false,
	      //html:'<img src="<!%=root%>/images/bg.jpg"/>',
	      items:'',
	      listeners:{
	          tabchange : function(tab,panel){
	              //alert(panel.title);
	              //tab.remove(panel);
	              
	          }
	      }
	    });
        
        new Ext.Viewport({
            title:'图书管理系统ExtJS版',
            layout:'border',
            items:[{
                //title:'简易图书管理系统ExtJS版',
                collapsible: true,
                html:'<img height="100" src="<%=request.getContextPath()%>/images/bg.jpg"/>',
                region:'north',
                height:100
            },
            {
                title:'功能菜单',
                collapsible: true,
                split:true,
                region:'west',
                width:170,
                layout:'fit',
                items:[{
                    layout : 'accordion',
                    frame:true,
                    collapseFirst:false,
                    //height:450,
                    autoWidth:true,
                    defaults:{bodyStyle:'background-color:#FFFFFF;padding:5px'},
                    layoutConfig:{
	                    activeOnTop:false,
	                    fill:true,
	                    hideCollapseTool:false,
	                    titleCollapse:true,
	                    animate:true
	                },
                    items:[{
						title:"图书管理系统",
						items:menu
					},{
					    title:"学生管理系统"
					}]    
                }],
                tbar : [
				    '皮肤选择：',
				  {
					xtype : 'themeChange',
					width : 100,
					listWidth : 100
				  },
				  '->'
			    ]
            },
            {
                //title:'系统说明',
                collapsible:false,
                id : 'mainContent',
                region:'center',
                items:tabPanel,   
                html:'<img src="<%=request.getContextPath()%>/images/bg.jpg"/>'
            }]
        });
        menu.expandAll();
        //var mainPanel = Ext.getCmp('mainContent');
	});
</script>
	<body>
	</body>
</html>
