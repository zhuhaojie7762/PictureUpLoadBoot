/**
 * 初始化加载
 */
$(function() {
	$("#queryButton").click(function() {
		projectInfoGridReload();
	});
	$("#gridTable").jqGrid({
		url : 'selectUserLoginList',
		datatype : 'json',
		styleUI : 'Bootstrap',
		width: $(".middle").width()*0.99,
		height: window.innerHeight-300,
		rownumbers: true,
		rownumWidth:'50',
		colNames:['编号','工号','用户名', '真实姓名', '登录方式', 'ip', '登录时间'],
		colModel : [ {name : 'id',index : 'id',hidden : true},  
		             {name : 'code',index : 'code',sortable : false,}, 
		             {name : 'userName',index : 'userName',sortable : false}, 
		             {name : 'realName',index : 'realName',sortable : false}, 
		             {name : 'loginMode',index : 'loginMode',sortable : false,
		            	 formatter: function (cellval, opts) {
		            		 if(cellval == '1'){
		            			 return "网站";
		            		 }else if(cellval == '2'){
		            			 return "客户端";
		            		 }else{
		            			 return cellval;
		            		 }
		            		 
		            	 }
		             },
		             {name : 'ip',index : 'ip',sortable : false},
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
function projectInfoGridReload(){
	jQuery("#gridTable").jqGrid('setGridParam', {
		page:1,
		postData : {
			"code" : $("#code").val(),
			"userName" : $("#userName").val(),
			"realName" : $("#realName").val(),
			"ip" : $("#ip").val()
	   },
	}).trigger("reloadGrid");
}