/**
 * 初始化加载
 */
$(function() {
	$("#queryButton").click(function() {
		projectInfoGridReload();
	});
	$("#gridTable").jqGrid({
		url : 'selectProjectList?delFlag=1',
		datatype : 'json',
		styleUI : 'Bootstrap',
		width: $(".middle").width()*0.99,
		height: window.innerHeight-300,
		rownumbers: true,
		rownumWidth:'50',
		colNames:['编号','城市', '类型', '工程名称', '原始数据路径','导出成果路径','解算进度','外业里程','实际里程','数据回传时间','质检提交时间','Record个数','删除原因','操作'],
		colModel : [ {name : 'id',index : 'id',hidden : true,},  
		             {name : 'city',index : 'city',sortable : false}, 
		             {name : 'type',index : 'type',sortable : false}, 
		             {name : 'projectName',index : 'projectName',sortable : false},  
		             {name : 'sourceUrl',index : 'sourceUrl',sortable : false},  
		             {name : 'resultUrl',index : 'resultUrl',sortable : false}, 
		             {name : 'resolveFlag',index : 'resolveFlag',sortable : false},
		             {name : 'outMileage',index : 'outMileage',sortable : false},
		             {name : 'factMileage',index : 'factMileage',sortable : false},
		             {name : 'returnTime',index : 'returnTime',sortable : false,
		            	 formatter: function (cellval, opts) {
	                        return dateFomart(cellval);
	                     }
		             },
		             {name : 'qcEndTime',index : 'qcEndTime',sortable : false,
		            	 formatter: function (cellval, opts) {
	                        return dateFomart(cellval);
	                     }
		             },
		             {name : 'recordNum',index : 'recordNum',sortable : false},
		             {name : 'delCause',index : 'delCause',sortable : false},
		             {name : 'options',index : 'options',resizable : true,sortable : false}
		           ],
		sortname : 'id',
		sortorder : 'desc',
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 50, 100 ],
		pager : '#gridPager',
		caption : '',
		jsonReader : {
			root : 'list',
			page : 'pageNum', // 页码
			total : 'pages', // 总页数
			records : 'total', // 总记录数
			repeatitems : false,
			cell : 'list',
			id : 'id',
			userdata : 'userdata',
			subgrid : {
				root : 'rows',
				repeatitems : true,
				cell : 'cell'
			}
		},
		gridComplete : function() {
	        var ids = $("#gridTable").jqGrid('getDataIDs');
	        for (var i = 0; i < ids.length; i++) {
	          var rowArray = $("#gridTable").jqGrid('getRowData', ids[i]);
	          var editBtn = '<a href="javascript:void(0)" onclick="viewProjectDateil(\''+rowArray.id+'\')">详情</a>';
	          $("#gridTable").jqGrid('setCell', ids[i], 'options', editBtn);
	        };
		 }
	});
	jQuery("#gridTable").jqGrid('setLabel',0, '序号', 'labelstyle');
});

/**
 * 模糊查询
 */
function projectInfoGridReload(){
	jQuery("#gridTable").jqGrid('setGridParam', {
		page:1,
		postData : {
			"projectName" : $("#projectName").val(),
			"delTime" : $("#delTime").val(),
			"delETime" : $("#delETime").val()
	   },
	}).trigger("reloadGrid");
}

/**
 * 工程资料详情页面
 * @param id 工程资料id
 */
function viewProjectDateil(id){
	location.href = "viewProjectDateil?type=del&id=" + id;
}
