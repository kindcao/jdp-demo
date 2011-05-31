/** ***************************** */
if ($.fn.pagination) {
	$.fn.pagination.defaults.beforePageText = '第';
	$.fn.pagination.defaults.afterPageText = '共{pages}页';
	$.fn.pagination.defaults.displayMsg = '显示{from}到{to},共{total}记录';
}
if ($.fn.datagrid) {
	$.fn.datagrid.defaults.loadMsg = '正在处理，请稍待。。。';
}
if ($.messager) {
	$.messager.defaults.ok = '确定';
	$.messager.defaults.cancel = '取消';
}
if ($.fn.validatebox) {
	$.fn.validatebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.validatebox.defaults.rules.email.message = '请输入有效的电子邮件地址';
	$.fn.validatebox.defaults.rules.url.message = '请输入有效的URL地址';
	$.fn.validatebox.defaults.rules.length.message = '输入内容长度必须介于{0}和{1}之间';
	$.fn.validatebox.defaults.rules.remote.message = '请修正该字段';
}
if ($.fn.numberbox) {
	$.fn.numberbox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combobox) {
	$.fn.combobox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combotree) {
	$.fn.combotree.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combogrid) {
	$.fn.combogrid.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.timespinner) {
	$.fn.timespinner.defaults.missingMessage = '该输入项为必输项';
}

if ($.fn.calendar) {
	$.fn.calendar.defaults.weeks = ['日', '一', '二', '三', '四', '五', '六'];
	$.fn.calendar.defaults.months = ['一月', '二月', '三月', '四月', '五月', '六月', '七月',
			'八月', '九月', '十月', '十一月', '十二月'];
}
if ($.fn.datebox) {
	$.fn.datebox.defaults.currentText = '今天';
	$.fn.datebox.defaults.closeText = '关闭';
	$.fn.datebox.defaults.okText = '确定';
	$.fn.datebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.datebox.defaults.formatter = function(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d);
	};
	$.fn.datebox.defaults.parser = function(s) {
		if (!s)
			return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox) {
	$.extend($.fn.datetimebox.defaults, {
				currentText : $.fn.datebox.defaults.currentText,
				closeText : $.fn.datebox.defaults.closeText,
				okText : $.fn.datebox.defaults.okText,
				missingMessage : $.fn.datebox.defaults.missingMessage
			});
}

// ///////////////
$.extend($.fn.validatebox.defaults.rules, {
	CHS : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '请输入汉字'
	},
	postcode : {
		validator : function(value, param) {
			return /^[1-9]\d{5}$/.test(value);
		},
		message : '邮政编码不存在'
	},
	QQ : {
		validator : function(value, param) {
			return /^[1-9]\d{4,10}$/.test(value);
		},
		message : 'QQ号码不正确'
	},
	mobile : {
		validator : function(value, param) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);
		},
		message : '手机号码不正确'
	},
	loginName : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message : '登录名称只允许汉字、英文字母、数字及下划线。'
	},
	safepass : {
		validator : function(value, param) {
			// return /^\s*[.A-Za-z0-9_-]{6,50}\s*$/.test(value);
			return safePassword(value);
		},
		message : '密码由字母和数字组成，至少6位'
	},
	equalTo : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入的字符不一至'
	},
	number : {
		validator : function(value, param) {
			return /^\d+$/.test(value);
		},
		message : '请输入数字'
	},
	idcard : {
		validator : function(value, param) {
			return idCard(value);
		},
		message : '请输入正确的身份证号码'
	},
	phone : {
		validator : function(value, param) {
			var reg = /(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/;
			return reg.test(value);
		},
		message : '请输入正确的电话号码,区号和号码用\'-\'分割'
	}
});

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
			.test(value));
}

// =================================================================//
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
				columns : columns,
				onClickRow : function(rowIndex, rowData) {
				},
				onSelectAll : function(rows) {
				}
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

$.fn.submitForm = function(url, validFlag, successFn) {
	var isValid = this.form('validate');
	if (isValid && validFlag) {
		var options = {
			url : url,
			dataType : 'json',
			type : 'post',
			success : function(data) {
				if (!data.success) {
					$.messager.alert('提示信息', data.errors, 'error');
				} else {
					successFn();
				}
			}
		};
		this.ajaxSubmit(options);
	}
}

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
// ////////
Date.prototype.Format = function(fmt) {
	// author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
		// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4
						- RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1)
							? (o[k])
							: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

Date.prototype.addDays = function(d) {
	this.setDate(this.getDate() + d);
};

Date.prototype.addWeeks = function(w) {
	this.addDays(w * 7);
};

Date.prototype.addMonths = function(m) {
	var d = this.getDate();
	this.setMonth(this.getMonth() + m);

	if (this.getDate() < d)
		this.setDate(0);
};

Date.prototype.addYears = function(y) {
	var m = this.getMonth();
	this.setFullYear(this.getFullYear() + y);

	if (m < this.getMonth()) {
		this.setDate(0);
	}
};