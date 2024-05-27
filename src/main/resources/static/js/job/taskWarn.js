$(function() {
	$("#gridTableRole").jqGrid(
			{
				url : 'warnData',
				datatype : "json",
				styleUI : 'Bootstrap',
				multiselect : true,
				rownumbers : true,
				rownumWidth : '50',
				width : $(".middle").width() * 0.99,
				height : window.innerHeight - 300,
				colNames : [ '日期', '服务器', '工程名称', '任务名称', '任务编号', '作业组', '作业人',
						'资料路径', '任务状态', '作业类型', '项目状态', '质检结果', '处理结果' ],
				colModel : [ {
					name : 'createTime',
					index : 'createTime',
					formatter: function (cellval, opts) {
						return dateFomart(cellval);
					},
					sortable : false,
					width : "40px"
				}, {
					name : 'serverURL',
					index : 'serverURL',
					sortable : false,
					width : "40px"
				}, {
					name : 'projectName',
					index : 'projectName',
					sortable : false,
					width : "50px"
				}, {
					name : 'taskName',
					index : 'taskName',
					sortable : false,
					width : "40px"
				}, {
					name : 'taskId',
					index : 'taskId',
					sortable : false,
					width : "50px"
				}, {
					name : 'operationsGroup',
					index : 'operationsGroup',
					sortable : false,
					width : "50px"
				}, {
					name : 'operator',
					index : 'operator',
					sortable : false,
					width : "50px"
				}, {
					name : 'path',
					index : 'path',
					sortable : false,
					width : "50px"
				}, {
					name : 'busiStatus',
					index : 'busiStatus',
					sortable : false,
					width : "50px"
				}, {
					name : 'operationsType',
					index : 'operationsType',
					sortable : false,
					width : "50px"
				}, {
					name : 'projectStatus',
					index : 'projectStatus',
					sortable : false,
					width : "50px"
				}, {
					name : 'qcResult',
					index : 'qcResult',
					sortable : false,
					width : "50px"
				}, {
					name : 'dealResult',
					index : 'dealResult',
					sortable : false,
					width : "50px"
				}, ],
				sortname : 'id',
				sortorder : 'desc',
				viewrecords : true,
				rowNum : 11,
				rowList : [ 10, 20, 30 ],
				pager : "#gridPagerRole",
				caption : "",
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
	jQuery("#gridTableRole").jqGrid('setLabel', 0, '序号', 'labelstyle');

});

/**
* 搜索
*/
function search(){
$("#gridTableRole").jqGrid('setGridParam',{
	page:1,postData:{"projectName":$("#projectName").val(),"busiStatus":$("#busiStatus").val()},
}).trigger('reloadGrid'); 
}

/**
 * 预警处理
 */
function warnDeal() {
	var rowIds = jQuery("#gridTableRole").jqGrid('getGridParam', 'selarrrow');
	var dealResult = $('#dealResult').val();
	if (typeof (rowIds) == 'undefined') {
		messagePrompt('你还没有选择任何内容！');
	} else if (dealResult == "") {
		messagePrompt("请填写预警处理结果！");
	} else {
		$.ajax({
			type : 'post',
			url : '/manage/warning/warnDeal',
			data : {
				ids : rowIds,
				dealResult : dealResult,
			},
			traditional : true,
			async : false,
			dataType : 'text',
			success : function(mesg) {
				messagePrompt("处理成功！");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		})
	}
}