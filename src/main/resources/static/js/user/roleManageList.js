var basepath;

function basePath(){
    //获取当前网址，如： http://localhost:8080/manage/login.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /manage/login.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/manage
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    //获取项目的basePath   http://localhost:8080/manage/
    var basePath=localhostPath+projectName+"/";
    return projectName;
};
basepath=basePath();


function defineLoad() {
	// insert表单校验

	$("#insertForm").validate({
		rules : {
			insert_name : {
				required : true,
				maxlength : 8,
			},
			insert_remark : {
				maxlength : 20,
			}
		},
		messages : {
			insert_name : {
				required : "请输入角色",
				maxlength : "超过8个字符"
			},
			insert_remark : {
				maxlength : "超过20个字符",
			}
		},
		submitHandler : function(form) {
			doSubmit();
		}
	});
	$("#updateForm").validate({
		rules : {
			update_remark : {
				maxlength : 14,
			}
		},
		messages : {
			update_remark : {
				maxlength : "字符过长",
			}
		},
		submitHandler : function(form) {
			doUpdata();
		}
	});
}
// 添加提交成功后调用的函数
function insertSubmit() {
	$("#insertForm").submit();
}

function updateSubmit() {
	$("#updateForm").submit();
}

/* ########################################################### */
$(function() {
	defineLoad();
	pageInit();
	viewMenu();
});
function pageInit() {
	$("#gridTableRole").jqGrid({
		url : basepath+"/role/getRole",
		// datatype: "local",
		datatype : "json",
		styleUI : 'Bootstrap',
		width : $(".middle").width() * 0.99,
		multiselect : true,
		rownumbers : true,
		mtype : "POST",
		colNames : [ '角色ID', '角色名', '备注' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			sortable : false,
			width : "50px",
			hidden : true
		}, {
			name : 'name',
			index : 'name',
			sortable : false,
			width : "50px"
		}, {
			name : 'remark',
			index : 'remarks',
			sortable : false,
			width : "50px"
		},

		],
		sortname : 'id',
		sortorder : 'desc',
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#gridPagerRole",
		/* caption: "用户信息表", */
		jsonReader : {
			root : "list",
			page : 'pageNum', // 页码
			total : 'pages', // 总页数
			records : 'total', // 总记录数
			repeatitems : false,
			cell : "row",
			id : "id",
			userdata : "userdata",
			subgrid : {
				root : "rows",
				repeatitems : true,
				cell : "cell"
			},

		}
	}).navGrid('#pager2', {
		edit : false,
		add : false,
		del : false
	});
}

/**
 * 搜索
 */
function doSearch() {
	$("#gridTableRole").jqGrid('setGridParam', {
		page : 1,
		postData : {
			'id' : $("#roleId").val(),
			'name' : $("#roleName").val(),
		}
	}).trigger('reloadGrid');
}


function viewMenu() {
	$("#roleselect").combotree({
		url : basepath+'/permission/getPermession',
		multiple : true, // 定义是否级联检查。
		onlyLeafCheck : false, // 定义是否只在叶节点前显示复选框。
		animate : true,
		onSelect : function(node) {
			init();
		}
	});
	$("#updateroleselect").combotree({
		url : basepath+'/permission/getPermession',
		multiple : true, // 定义是否级联检查。
		onSelect : function(node) {

		}
	});
}
function init() {

}

function insertView() {
	$('#addUser').modal('show');
}

function showInsertValidate(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop)) {
			var inputError = "insertSpan_" + prop;
			document.getElementById(inputError).innerHTML = obj[prop];
		}
	}
}

function doSubmit() {
	$('#addUser').modal('show');
	$('.modal-backdrop.in').fadeIn();
	$('.modal-backdrop.fade').fadeIn();
	// 处理节点解析
	var updateCombotreeInsert = $("#roleselect").combotree('tree');
	var nodeInsert = updateCombotreeInsert.tree('getChecked', [ 'checked',
			'indeterminate' ]);// 所有选中和模糊选中的
	var nodeArrayInsert = new Array;
	for (var i = 0; i < nodeInsert.length; i++) {
		if (nodeInsert[i].id == "000000")
			continue; // 默认删除最外面的展示节点
		nodeArrayInsert.push(nodeInsert[i].id);
	}

	$.ajax({
		type : "POST",
		url : basepath+"/role/insertRole",
		data : {
			code : $("#insert_id").val(),
			name : $("#insert_name").val(),
			remark : $("#insert_remark").val(),
			ids : nodeArrayInsert.join(",")
		},
		dataType : "json",
		success : function(data) {
			if (data.success == "success") {

				messagePrompt('添加成功');

				// 清空-start
				$("#insert_name").val("");
				$("#insert_remark").val("");
				$('#roleselect').combotree('clear');
				// 清空-end
				$('#addUser').modal('hide');
				$('.modal-backdrop.in').fadeOut();
				$('.modal-backdrop.fade').fadeOut();
				doSearch();

			}
			if (data.success == "error") {
				if (data.msg == 0) {
					messagePrompt("操作失败，请重试");
				} else if (data.msg == 2) {
					// console.log(data.errorinfo);
					// 给添加操作后面加操作值
					showInsertValidate(data.errorinfo);
				} else if (data.msg == 1) {
					messagePrompt("操作失败，请联系管理员");
				}
			}
		}
	})
};
// 角色修改-显示接口数据回显
function doUpdateView() {
	var rowIds = $("#gridTableRole").jqGrid('getGridParam', 'selarrrow');
	var len = rowIds.length;
	if (len > 1) {
		messagePrompt("选择的角色太多，请选择一位！");
		return false;
	}
	if (len < 1) {
		messagePrompt("选择的角色太多，请选择一位！");
		return false;
	}
	$('#modificationUser').modal('show');
	var rowIdsNew = rowIds.join("");
	$.ajax({
		type : "POST",
		url : basepath+"/role/roleById",
		data : {
			Id : rowIdsNew
		},
		dataType : "json",
		success : function(data) {
			if (data.success == "true") {
				var obj1 = data.obj1;
				// console.log(obj1);
				// console.log(obj1);
				$('#updateroleselect').combotree('setValues', obj1);
				var obj = data.obj;
				$("#updateId").val(obj.id);
				$("#updateName").val(obj.name);
				$("#updateRemark").val(obj.remark);
			}
		}
	})
};

function showUpdateValidate(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop)) {
			var inputError = "updateSpan_" + prop;
			document.getElementById(inputError).innerHTML = obj[prop];
		}
	}
}

// 数据到后台进行修改
function doUpdata() {
	var updateCombotree = $("#updateroleselect").combotree('tree');
	var node = updateCombotree.tree('getChecked',
			[ 'checked', 'indeterminate' ]);// 所有选中和模糊选中的
	var nodeArray = new Array;
	for (var i = 0; i < node.length; i++) {
		if (node[i].id == "000000")
			continue; // 默认删除最外面的展示节点
		nodeArray.push(node[i].id);
	}
	console.log("nodeArray:" + nodeArray);
	$.ajax({
		type : "POST",
		url : basepath+"/role/updateRole",
		data : {
			ids : nodeArray.join(","),
			id : $("#updateId").val(),
			name : $("#updateName").val(),
			remark : $("#updateRemark").val()
		},
		dataType : "json",
		success : function(data) {
			if (data.success == "success") {
				$('#modificationUser').modal('hide');
				messagePrompt("修改成功");
				doSearch();
			}
			if (data.success == "error") {
				if (data.msg == 0) {
					messagePrompt("操作失败，请重试");
				} else if (data.msg == 2) {
					console.log(data.errorinfo);
					// 给添加操作后面加操作值
					showUpdateValidate(data.errorinfo);
				} else if (data.msg == 1) {
					messagePrompt("操作失败，请联系管理员");
				}
			}
		}
	})
}

function deleteView() {
	var rowId = $("#gridTableRole").jqGrid('getGridParam', 'selarrrow');
	if (rowId == null || rowId == "") {
		messagePrompt("先选择至少一个角色");
	} else {
		$("#deleteUser").modal("show");
	}
}
// 角色删除
function deleteRole() {
	var rowIds = $("#gridTableRole").jqGrid('getGridParam', 'selarrrow').join(
			",");
	// console.log(rowIds);
	$.ajax({
		type : "POST",
		url : basepath+"/role/delete",
		data : {
			ids : rowIds
		},
		dataType : "json",
		success : function(data) {
			if (data.success == "success") {
				doSearch();
				if (data.count == "0") {
					messagePrompt("删除成功");
				} else {
					messagePrompt("角色下存在" + data.count + "个用户不能删除");
				}
			}
			if (data.error == "error") {
				messagePrompt("异常原因删除失败");
			}
		}
	})
}
