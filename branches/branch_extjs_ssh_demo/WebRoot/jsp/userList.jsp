<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<script type="text/javascript">
	Ext.onReady(function(){
		//定义数据集对象
		var sysUserStore = new Ext.data.Store({
			autoLoad :{params:{start:0,limit:10}},
			reader: new Ext.data.JsonReader({
				totalProperty: "results",
				root:'List'
				//record: "Book",
				//id: "id"
			},
			Ext.data.Record.create([
				{name: 'id'},
				{name: 'username'},
				{name: 'password'},
				{name: 'email'}
			])
			),
			proxy : new Ext.data.HttpProxy({
				url : 'getUserList.action'
			})
		})
		//创建工具栏组件
		var toolbar = new Ext.Toolbar([
			{text : '新增',iconCls:'add',handler:showAddUser},
			{text : '修改',iconCls:'option',handler:showModifyBook},
			{text : '删除',iconCls:'remove',handler:showDeleteBooks}
		]);
		
		//创建分页组件
		var bbar = new Ext.PagingToolbar({
		    pageSize: 10,
		    store:sysUserStore,
		    displayInfo:true,
		    //displayMsg:'第{0}条到{1}条，共{2}条',
		    emptyMsg:'没有记录' 
		});
		
		//创建Grid表格组件
		var cb = new Ext.grid.CheckboxSelectionModel()
		var bookGrid = new Ext.grid.GridPanel({		    
			applyTo : 'grid-div-sysuser',
			frame:true,
			tbar : toolbar,
			bbar : bbar,
			store: sysUserStore,
			stripeRows : true,
			autoScroll : true,
			autoHeight : true,
			viewConfig : {
				autoFill : true
			},
			sm : cb,
			columns: [//配置表格列
				new Ext.grid.RowNumberer({
					header : '行号',
					width : 40
				}),//表格行号组件
				cb,
				{header: "用户编号", width: 80, dataIndex: 'id', sortable: true},
				{header: "用户名", width: 80, dataIndex: 'username', sortable: true},
				{header: "密  码", width: 80, dataIndex: 'password', sortable: true},
				{header: "邮件地址", width: 80, dataIndex: 'email', sortable: true}			
			]
		})
		//创建新增或修改书籍信息的form表单
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';//统一指定错误信息提示方式
		var sysUserForm = new Ext.FormPanel({
			labelSeparator : "：",
			frame:true,
			border:false,
			items : [
				{
					xtype:'textfield',
					width : 200,
					allowBlank : false,
					blankText : '用户名不能为空',
					name : 'username',
					fieldLabel:'用户名'
				},{
					xtype:'textfield',
					width : 200,
					allowBlank : false,
					blankText : '密码不能为空',
					name : 'password',
					fieldLabel:'密  码'
				},{
					xtype:'textfield',
					width : 200,
					name : 'email',
					fieldLabel:'邮件地址'
				},{
					xtype:'hidden',
					name : 'id'
				}
			],
			buttons:[
				{
					text : '关闭',
					handler : function(){
						win.hide();
					}
				},{
					text : '提交',
					handler : submitForm
				}
			]
		});
		//创建弹出窗口
		var win = new Ext.Window({
			layout:'fit',
		    width:380,
		    closeAction:'hide',
		    height:200,
			resizable : false,
			shadow : true,
			modal :true,
		    closable:true,
		    bodyStyle:'padding:5 5 5 5',
		    animCollapse:true,
			items:[sysUserForm]
		});
		//显示新建书籍窗口
		function showAddUser(){
			sysUserForm.form.reset();
			sysUserForm.isAdd = true;
			win.setTitle("新增用户信息");
			win.show();
		}
		//显示修改书籍窗口
		function showModifyBook(){
			var bookList = getBookIdList();
			var num = bookList.length;
			if(num > 1){
				Ext.MessageBox.alert("提示","每次只能修改一条书籍信息。")
			}else if(num == 1){
				sysUserForm.form.reset();
				sysUserForm.isAdd = false;
				win.setTitle("修改书籍信息");
				win.show();
				var bookId = bookList[0];
				loadForm(bookId);
			}
		}
		//显示删除书籍对话框
		function showDeleteBooks(){
			var bookList = getBookIdList();
			var num = bookList.length;
			if(num == 0){
				return;
			}
			Ext.MessageBox.confirm("提示","您确定要删除所选书籍吗？",function(btnId){
				if(btnId == 'yes'){
					deleteBooks(bookList);
				}
			})
		}
		//删除书籍
		function deleteBooks(bookList){
			var bookIds = bookList.join('-');
			var msgTip = Ext.MessageBox.show({
				title:'提示',
				width : 250,
				msg:'正在删除书籍信息，请稍后......'
			});
			Ext.Ajax.request({
				url : 'deleteBooks.action',
				params : {bookIds : bookIds},
				method : 'POST',
				success : function(response,options){
					msgTip.hide();
					var result = Ext.util.JSON.decode(response.responseText);
					if(result.success){
						//服务器端数据成功删除后，同步删除客户端列表中的数据
						for(var i = 0 ; i < bookList.length ; i++){
							var index = sysUserStore.find('id',bookList[i]);
							if(index != -1){
								var rec = sysUserStore.getAt(index)
								sysUserStore.remove(rec);
							}
						}
						Ext.Msg.alert('提示','删除书籍信息成功。');bbar.updateInfo();
					}else{
						Ext.Msg.alert('提示','删除书籍信息失败！');
					}
				},
				failure : function(response,options){
					msgTip.hide();
					Ext.Msg.alert('提示','删除书籍信息请求失败！');
				}
			});
		}
		//加载表单数据
		function loadForm(id){
			sysUserForm.form.load({
				waitMsg : '正在加载数据请稍后',//提示信息
				waitTitle : '提示',//标题
				url : 'getBookById.action',//请求的url地址
				params : {id:id},
				method:'GET',//请求方式
				success:function(form,action){//加载成功的处理函数
					//Ext.Msg.alert('提示','数据加载成功');
					alert(sysUserForm.getForm().findField("typeId").getValue());
				},
				failure:function(form,action){//加载失败的处理函数
					Ext.Msg.alert('提示','数据加载失败');
				}
			});
		}
		//提交表单数据
		function submitForm(){
			//判断当前执行的提交操作，isAdd为true表示执行书籍新增操作，false表示执行书籍修改操作
			if(sysUserForm.isAdd){
				//新增书籍信息
				sysUserForm.form.submit({
					clientValidation:true,//进行客户端验证
					waitMsg : '正在提交数据请稍后',//提示信息
					waitTitle : '提示',//标题
					url : 'addSysUser.action',//请求的url地址
					method:'POST',//请求方式
					success:function(form,action){//加载成功的处理函数
						win.hide();
						updateSysUserList(action.result.id);
						Ext.Msg.alert('提示','新增用户成功');
					},
					failure:function(form,action){//加载失败的处理函数
						Ext.Msg.alert('提示','新增用户失败');
					}
				});
			}else{
				//修改书籍信息
				sysUserForm.form.submit({
					clientValidation:true,//进行客户端验证
					waitMsg : '正在提交数据请稍后',//提示信息
					waitTitle : '提示',//标题
					url : 'modifyBook.action',//请求的url地址
					method:'POST',//请求方式
					success:function(form,action){//加载成功的处理函数
						win.hide();
						updateSysUserList(action.result.id);
						Ext.Msg.alert('提示','修改书籍成功');
					},
					failure:function(form,action){//加载失败的处理函数
						Ext.Msg.alert('提示','修改书籍失败');
					}
				});
			}
		}
		//明细数据修改后，同步更新书籍列表信息
		function updateSysUserList(id){
			var fields = getFormFieldsObj(id);
			var index = sysUserStore.find('id',fields.id);
			if(index != -1){
				var item = sysUserStore.getAt(index);
				for(var fieldName in fields){
					item.set(fieldName,fields[fieldName]);
				}
				sysUserStore.commitChanges();
			}else{
				var rec = new Ext.data.Record(fields);
				sysUserStore.add(rec);
			}
		}
		//取得表单数据
		function getFormFieldsObj(id){
			var fields = sysUserForm.items;
			var obj = {};
			for(var i = 0 ; i < fields.length ; i++){
				var item = 	fields.itemAt(i);
				var value = item.getValue();
				obj[item.name] = value;
			}
			if(Ext.isEmpty(obj['id'])){
				obj['id'] = id;
			}
			return obj;
		}
		//取得所选书籍
		function getUserIdList(){
			var recs = bookGrid.getSelectionModel().getSelections();
			var list = [];
			if(recs.length == 0){
				Ext.MessageBox.alert('提示','请选择要进行操作的书籍！');
			}else{
				for(var i = 0 ; i < recs.length ; i++){
					var rec = recs[i];
					list.push(rec.get('id'))
				}
			}
			return list;
		}
	});
</script>
	<body>
		<div id='grid-div-sysuser' style="width: 100%; height: 100%;" />
	</body>
</html>