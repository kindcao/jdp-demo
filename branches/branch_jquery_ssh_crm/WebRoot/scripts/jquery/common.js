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
function showDatagridNoPagination(datagridIdStr, url, frozenColumns, columns) {
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
				pagination : false,
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
//
function cutstr(str, len) {
	var str_length = 0;
	var str_len = 0;
	str_cut = new String();
	str_len = str.length;
	for (var i = 0; i < str_len; i++) {
		a = str.charAt(i);
		str_length++;
		if (escape(a).length > 4) {
			// 中文字符的长度经编码之后大于4
			str_length++;
		}
		str_cut = str_cut.concat(a);
		if (str_length >= len) {
			str_cut = str_cut.concat("...");
			return str_cut;
		}
	}
	// 如果给定字符串小于指定长度，则返回源字符串；
	if (str_length < len) {
		return str;
	}
}

// ///////////////////////////////
/*
 * HashMap version 3.0 HashMap构造函数
 */
function JHashMap() {
	this.length = 0;
	this.prefix = "hashmap_prefix_20040716_";
}
/**
 * 向HashMap中添加键值对
 */
JHashMap.prototype.put = function(key, value) {
	this[this.prefix + key] = value;
	this.length++;
}
/**
 * 从HashMap中获取value值
 */
JHashMap.prototype.get = function(key) {
	return typeof this[this.prefix + key] == "undefined"
			? null
			: this[this.prefix + key];
}
/**
 * 从HashMap中获取所有key的集合，以数组形式返回
 */
JHashMap.prototype.keySet = function() {
	var arrKeySet = new Array();
	var index = 0;
	for (var strKey in this) {
		if (strKey.substring(0, this.prefix.length) == this.prefix)
			arrKeySet[index++] = strKey.substring(this.prefix.length);
	}
	return arrKeySet.length == 0 ? null : arrKeySet;
}
/**
 * 从HashMap中获取value的集合，以数组形式返回
 */
JHashMap.prototype.values = function() {
	var arrValues = new Array();
	var index = 0;
	for (var strKey in this) {
		if (strKey.substring(0, this.prefix.length) == this.prefix)
			arrValues[index++] = this[strKey];
	}
	return arrValues.length == 0 ? null : arrValues;
}
/**
 * 获取HashMap的value值数量
 */
JHashMap.prototype.size = function() {
	return this.length;
}
/**
 * 删除指定的值
 */
JHashMap.prototype.remove = function(key) {
	delete this[this.prefix + key];
	this.length--;
}
/**
 * 清空HashMap
 */
JHashMap.prototype.clear = function() {
	for (var strKey in this) {
		if (strKey.substring(0, this.prefix.length) == this.prefix)
			delete this[strKey];
	}
	this.length = 0;
}
/**
 * 判断HashMap是否为空
 */
JHashMap.prototype.isEmpty = function() {
	return this.length == 0;
}
/**
 * 判断HashMap是否存在某个key
 */
JHashMap.prototype.containsKey = function(key) {
	for (var strKey in this) {
		if (strKey == this.prefix + key)
			return true;
	}
	return false;
}
/**
 * 判断HashMap是否存在某个value
 */
JHashMap.prototype.containsValue = function(value) {
	for (var strKey in this) {
		if (this[strKey] == value)
			return true;
	}
	return false;
}
/**
 * 把一个HashMap的值加入到另一个HashMap中，参数必须是HashMap
 */
JHashMap.prototype.putAll = function(map) {
	if (map == null)
		return;
	if (map.constructor != JHashMap)
		return;
	var arrKey = map.keySet();
	var arrValue = map.values();
	for (var i in arrKey)
		this.put(arrKey[i], arrValue[i]);
}
// toString
JHashMap.prototype.toString = function() {
	var str = "";
	for (var strKey in this) {
		if (strKey.substring(0, this.prefix.length) == this.prefix)
			str += strKey.substring(this.prefix.length) + " : " + this[strKey]
					+ "\r\n";
	}
	return str;
}