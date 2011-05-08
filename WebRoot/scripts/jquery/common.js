//
function showDatagrid(datagridIdStr, url, frozenColumns, columns) {
	$('#' + datagridIdStr).datagrid({
				title : '查询结果',
				border : true,
				nowrap : false,
				striped : true,
				collapsible : false,
				url : url,
				queryParams : {},
				sortName : 'id',
				sortOrder : 'desc',
				remoteSort : false,
				idField : 'id',
				// fitColumns : true,
				rownumbers : false,
				pagination : true,
				pageNumber : 1,
				pageSize : 3,
				pageList : [3, 10],
				frozenColumns : frozenColumns,
				columns : columns
			});
}

//
function reloadDatagrid(datagridIdStr) {
	$('#' + datagridIdStr).datagrid('options').pageNumber = 1;
	$('#' + datagridIdStr).datagrid('getPager').pagination({
				pageNumber : 1
			});
	$('#' + datagridIdStr).datagrid("reload");
}

//
function deleteRecord(datagridIdStr, url) {
	var ids = [];
	var rows = $('#' + datagridIdStr).datagrid('getSelections');
	if (rows.length > 0) {
		$.messager.confirm('提示信息', '要删除选中的数据吗？', function(r) {
					if (r) {
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						//							
						$.ajax({
									url : url + '?ids=' + ids.join(','),
									dataType : 'json',
									success : function(data) {
										if (!data.success) {
											$.messager.alert('提示信息',
													data.errors, 'error');
										} else {
											$('#' + datagridIdStr)
													.datagrid('clearSelections');
											reloadDatagrid(datagridIdStr);
										};
									}
								});
					}
				});
	} else {
		$.messager.alert('提示信息', '请至少选择一条需要删除的数据！', 'warning');
	}
}

//
function resetForm(formIdStr) {
	// $('#' + formIdStr).each(function(index) {
	// $('#' + formIdStr)[index].reset();
	// });
	$('#' + formIdStr).clearForm();
}

$.fn.clearForm = function() {
	return this.each(function() {
				var type = this.type, tag = this.tagName.toLowerCase();
				if (tag == 'form')
					return $(':input', this).clearForm();
				if (type == 'text' || type == 'password' || tag == 'textarea')
					this.value = '';
				else if (type == 'checkbox' || type == 'radio')
					this.checked = false;
				else if (tag == 'select')
					this.selectedIndex = -1;
			});
};