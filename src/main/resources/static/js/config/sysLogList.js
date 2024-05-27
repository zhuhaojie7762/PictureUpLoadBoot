/**
 * 初始化加载
 */
$(function() {
	$("#queryButton").click(function() {
		logGridReload();
	});
	$("#gridTable").jqGrid({
		url : 'selectSysLogList',
		datatype : 'json',
		styleUI : 'Bootstrap',
		width: $(".middle").width()*0.99,
		height: window.innerHeight-300,
		rownumbers: true,
		rownumWidth:'50',
		colNames:['编号','执行用户', '操作名称', '操作内容', '操作结果', '操作时间'],
		colModel : [ {name : 'id',index : 'id',hidden : true},  
		             {name : 'userName',index : 'userName',sortable : false}, 
		             {name : 'actName',index : 'actName',sortable : false}, 
		             {name : 'actContent',index : 'actContent',sortable : false},  
		             {name : 'result',index : 'result',sortable : false},
		             {name : 'createTime',index : 'createTime',sortable : false,
		            	 	formatter: function (cellval, opts) {
			            		if(cellval == null || cellval == ""){
			            				return "";
			            		}else{
		            				var date = new Date(cellval);
		            				var year = date.getFullYear();
		            				var month = date.getMonth()+1;
		            				var d = date.getDate();
		            				var h = date.getHours();
		            				var m = date.getMinutes();
		            				var s = date.getSeconds();
		            				return year + "-" + month + "-" + d + " " + h + ":" + m + ":" + s;
		            			}
		                     }
		             }
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
		}
	});
	jQuery("#gridTable").jqGrid('setLabel',0, '序号', 'labelstyle');
});

/**
 * 模糊查询
 */
function logGridReload(){
	jQuery("#gridTable").jqGrid('setGridParam', {
		page:1,
		postData : {
			"userName" : $("#userName").val(),
			"startTime" : $("#startTime").val(),
			"endTime" : $("#endTime").val()
	   },
	}).trigger("reloadGrid");
}