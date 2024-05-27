/**
 * 质检任务列表
 */
$(function() {
	var _height = $("body").height();
	$('list').css('height', _height + 'px');// 调整列表的宽高
	$("#gridTable")
			.jqGrid(
					{
						datatype : "json",
						url : 'viewQI',
						styleUI : 'Bootstrap',
						multiselect : true,
						rownumbers : true,
						rownumWidth : '50',
						width : $(".middle").width() * 0.99,
						colNames : [ 'taskStatus', 'Id', 'procInstId', '日期',
								'任务名称', '任务编号', '质检组', '质检人',
								'资料路径', '任务状态', '作业类型', '是否合格', '备注' ],
						colModel : [
								{
									name : 'taskStatus',
									index : 'taskStatus',
									hidden : true
								},
								{
									name : 'id',
									index : 'id',
									hidden : true
								},
								{
									name : 'procInstId',
									index : 'procInstId',
									align : 'center',
									hidden : true
								},
								{
									name : 'createTime',
									index : 'createTime',
									align : 'center',
									formatter : function(cellval, opts) {
										return dateFomart(cellval);
									},
									width : "50px"
								},
								{
									name : 'taskName',
									index : 'taskName',
									sortable : false,
									width : "55px"
								},
								{
									name : 'procInstId',
									index : 'procInstId',
									sortable : false,
									width : "55px"
								},
								{
									name : 'qualityGroup',
									index : 'qualityGroup',
									sortable : false,
									width : "50px"
								},
								{
									name : 'qualityOperator',
									index : 'qualityOperator',
									sortable : false,
									width : "50px"
								},
								{
									name : 'path',
									index : 'path',
									sortable : false,
									width : "65px"
								},
								{
									name : 'taskStatus',
									index : 'taskStatus',
									sortable : false,
									width : "65px",
									formatter : function(cellvalue, options,
											rowObject) {
										var taskStatus = "";
										cellvalue=$.trim(cellvalue);
										taskStatus = "<a href=\"historyDetail?procInstId="
												+ rowObject.procInstId
												+ "\" target=\"_blank\">"
												+ cellvalue + "</a>";
										return taskStatus;
									}
								}, {
									name : 'jobType',
									index : 'jobType',
									sortable : false,
									width : "60px"
								}, {
									name : 'isQualified',
									index : 'isQualified',
									sortable : false,
									width : "60px"
								}, {
									name : 'remark',
									index : 'remark',
									sortable : false,
									width : "60px"
								} ],
						sortname : 'id',
						sortorder : 'desc',
						viewrecords : true,
						rowNum : 10,
						rowList : [ 10, 20, 30 ],
						pager : "#gridPager",
						caption : "",
						jsonReader : {
							root : "list",
							page : 'pageNum', // 页码
							total : 'pages', // 总页数
							records : 'total', // 总记录数
							repeatitems : false,
							cell : "row",
							id : "procInstId",
							userdata : "userdata",
							subgrid : {
								root : "rows",
								repeatitems : true,
								cell : "cell"
							},

						},
						// 异常处理回调函数
						loadError : function(data) {
							window.location.href = basePath() + "/500.jsp";// 跳转到异常页面
						}
					}).navGrid('#pager2', {
				edit : false,
				add : false,
				del : false
			});
	jQuery("#gridTable").jqGrid('setLabel', 0, '序号', 'labelstyle');

});

/**
 * 重置搜索条件
 */
function doReset() {
	$("#taskName").val("");
	$("#taskStatus").val("");
}

/**
 * 分配页面质检员列表
 */
$(function() {
	$("#gridTable1").jqGrid({
		datatype : "json",
		url : "taskUserList",
		styleUI : 'Bootstrap',
		width : $(".middle").width() * 0.33,
		height : $(".right").height() * 0.33,
		multiselect : true,
		multiboxonly : true,
		rownumbers : true,
		beforeSelectRow : beforeSelectRow,
		colNames : [ '编号', '用户名', '真是姓名', '已有任务数' ],
		colModel : [ {
			name : 'userId',
			index : 'userId',
			sortable : false,
			width : "20px",
			hidden : true
		}, {
			name : 'name',
			index : 'name',
			sortable : false,
			width : "20px"
		}, {
			name : 'realName',
			index : 'realName',
			sortable : false,
			width : "20px"
		}, {
			name : 'taskNum',
			index : 'taskNum',
			sortable : false,
			width : "30px"
		}, ],
		sortname : 'id',
		sortorder : 'desc',
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#gridPager1",
		caption : "",
		jsonReader : {
			root : "list",
			page : 'pageNum', // 页码
			total : 'pages', // 总页数
			records : 'total', // 总记录数
			repeatitems : false,
			cell : "row",
			id : "userId",
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
	jQuery("#gridTable1").jqGrid('setLabel', 0, '序号', 'labelstyle');
	jQuery("#gridTable1").jqGrid('setLabel', 1, '选择', 'labelstyle');
});

/**
 * 移交页面质检员列表
 */
$(function() {
	$("#gridTable2").jqGrid({
		datatype : "json",
		url : "taskUserList",
		styleUI : 'Bootstrap',
		width : $(".middle").width() * 0.33,
		height : $(".right").height() * 0.33,
		multiselect : true,
		multiboxonly : true,
		rownumbers : true,
		colNames : [ '编号', '用户名', '真实姓名', '已有任务数' ],
		colModel : [ {
			name : 'userId',
			index : 'userId',
			sortable : false,
			width : "20px",
			hidden : true
		}, {
			name : 'name',
			index : 'name',
			sortable : false,
			width : "20px"
		}, {
			name : 'realName',
			index : 'realName',
			sortable : false,
			width : "20px"
		}, {
			name : 'taskNum',
			index : 'taskNum',
			sortable : false,
			width : "30px"
		}, ],
		sortname : 'id',
		sortorder : 'desc',
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#gridPager2",
		caption : "",
		jsonReader : {
			root : "list",
			page : 'pageNum', // 页码
			total : 'pages', // 总页数
			records : 'total', // 总记录数
			repeatitems : false,
			cell : "row",
			id : "userId",
			userdata : "userdata",
			subgrid : {
				root : "rows",
				repeatitems : true,
				cell : "cell"
			},

		}
	}).navGrid('#pager1', {
		edit : false,
		add : false,
		del : false
	});
	jQuery("#gridTable2").jqGrid('setLabel', 0, '序号', 'labelstyle');
	jQuery("#gridTable2").jqGrid('setLabel', 1, '选择', 'labelstyle');

});

function beforeSelectRow() {
	$("#gridTable1").jqGrid('resetSelection');
	return (true);
}

/**
 * 搜索
 */
function search() {
	$("#gridTable").jqGrid('setGridParam', {
		page : 1,
		postData : {
			'taskName' : $("#taskName").val(),
			'taskStatus' : $("#taskStatus").val(),
		},
	}).trigger('reloadGrid');
}

/**
 * 搜索作业员(任务分配)
 */
function searchOpertorDistribution() {
	var realName = $("#realNameDistribution").val();
	$("#gridTable1").jqGrid('setGridParam', {
		postData : {
			"realName" : realName
		},
	}).trigger('reloadGrid');
}

/**
 * 搜索作业员(任务移交)
 */
function searchOpertorHandOver() {
	var realName = $("#realNameHandOver").val();
	$("#gridTable2").jqGrid('setGridParam', {
		postData : {
			"realName" : realName
		},
	}).trigger('reloadGrid');
}

/**
 * 任务分配
 */

function assign() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	var id = $('#gridTable1').jqGrid('getGridParam', 'selrow');
	var taskStatus = $('#gridTable').jqGrid('getCell', rowIds, 'taskStatus');
	// if(taskStatus != "质检任务已分配") {
	if (rowIds != '' && rowIds != null && typeof (rowIds) != 'undefined') {
		if (id != '' && id != null && typeof (id) != 'undefined') {
			$.ajax({
				type : 'post',
				url : 'taskAllocation',
				data : {
					procInstIds : rowIds,
					userId : id
				},
				traditional : true,
				async : false,
				dataType : 'text',
				success : function(data) {
					var str = eval('(' + data + ')');
					if (str.msg == 1) {
						messagePrompt('操作成功!');
						$(".right").css({
							width : "30px",
							right : "-30px"
						});
						$("#right_switch").css({
							backgroundPosition : "5px center"
						});
						$('#gridTable').trigger('reloadGrid');

					} else {
						messagePrompt(str.errorMessage);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					messagePrompt('操作异常！!');
					window.location.href = basePath() + "/500.jsp";
				}
			})
		} else {
			messagePrompt('请选择一位质检人员!');
		}
	} else {
		messagePrompt('请选择一条操作记录!');
	}
	// }else {
	// messagePrompt('您选择的任务已分配，不可重复分配!');
	// }
}

/**
 * 质检任务审批
 */
function approve() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	var isAgree = $('input[name="isAgree"]:checked').val();
	if (typeof (isAgree) == 'undefined') {
		messagePrompt('请选择移交结果!');
	} else {
		$.ajax({
			type : 'post',
			url : 'qualityApprove',
			data : {
				procInstIds : rowIds,
				isAgree : isAgree
			},
			traditional : true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('操作成功!');
					$('#gridTable').trigger('reloadGrid');
					$('#myModal2').modal('hide');
				} else {
					messagePrompt('操作失败!');
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常!');
				window.location.href = basePath() + "/500.jsp";
			}
		})
	}
}

/**
 * 任务移交
 */
function transfer() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	var id = $('#gridTable2').jqGrid('getGridParam', 'selrow');
	if (rowIds != '' && rowIds != null && typeof (rowIds) != 'undefined') {
		if (id != '' && id != null && typeof (id) != 'undefined') {
			$.ajax({
				type : 'post',
				url : 'qctaskTransfer',
				data : {
					procInstIds : rowIds,
					userId : id
				},
				traditional : true,
				async : false,
				dataType : 'text',
				success : function(data) {
					var str = eval('(' + data + ')');
					if (str.msg == 1) {
						messagePrompt('操作成功！');
						$(".right-turn").css({
							width : "30px",
							right : "-30px"
						});
						$("#right_transfer").css({
							backgroundPosition : "5px center"
						});
						$('#gridTable').trigger('reloadGrid');
					} else {
						messagePrompt(str.errorMessage);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					messagePrompt('操作异常!');
					window.location.href = basePath() + "/500.jsp";
				}
			})
		} else {
			messagePrompt('请选择一位质检人员!');
		}
	} else {
		messagePrompt('请选择一条操作记录!');
	}
}

/**
 * 资料导出
 */
function downExcel() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	var ids = "";
	if (rowIds.length) {
		for ( var i in rowIds) {
			var id = jQuery('#gridTable').jqGrid('getCell', rowIds[i],
					'procInstId');
			ids += id + ",";
		}
		$("#id").val(ids);
		$("#downExcelForm").attr("action", "downExcel").submit();
	} else {
		messagePrompt('请选择一条操作记录!');
	}
}

/**
 * 任务删除
 */
function deleteProcess() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	var deleteReason = $("#deleteReason").val();
	if ($('#checkDel').is(':checked')) {
		if (deleteReason.length > 60) {
			messagePrompt('最多输入60个字符!');
		} else {
			$.ajax({
				type : 'post',
				url : 'deleteProcess',
				data : {
					procInstIds : rowIds,
					deleteReason : deleteReason
				},
				traditional : true,
				async : false,
				dataType : 'text',
				success : function(data) {
					var str = eval('(' + data + ')');
					if (str.msg == 1) {
						messagePrompt('操作成功!');
						$('#gridTable').trigger('reloadGrid');
						$("#deleteReason").attr("disabled", "true");
						$('#myModal3').modal('hide');
					} else {
						messagePrompt('编号为：' + str.procInstId + '的任务操作失败！');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					messagePrompt('操作异常!');
					window.location.href = basePath() + "/500.jsp";
				}
			})
		}
	} else {
		messagePrompt('请确认放入回收站!');
	}
}

/**
 * 清空删除原因框
 */
function clearText() {
	$("#deleteReason").val("");
}

/**
 * 任务删除
 * 
 * @param id
 *            展示model
 */
function openModel(id) {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	if (rowIds == '') {
		messagePrompt('请选择一条操作记录!');
	} else {
		$('#' + id).modal('show');
	}
}
