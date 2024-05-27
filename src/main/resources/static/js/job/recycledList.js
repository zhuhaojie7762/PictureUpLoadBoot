$(function(){
				$("#gridTable").jqGrid({
					url : '/manage/task/recycledTaskData',
					datatype : "json",
					styleUI : 'Bootstrap',
					rownumbers: true,
					rownumWidth:'50',
					
					width: $(".middle").width()*0.99,
					height: window.innerHeight-300,
	                colNames:['Id','procInstId','日期', '服务器', '工程名称', '任务名称','任务编号','作业组','作业人','资料路径','任务状态','作业类型','项目状态','是否合格','备注'],
	                colModel:[
	                        {name:'id',index:'id',hidden : true},
	                        {name:'procInstId',index:'procInstId',hidden : true},
	                        {name:'createTime',index:'createTime', formatter: function (cellval, opts) {
	                        	return dateFomart(cellval);
	                        	},width:"50px"},
	                        {name:'resultURL',index:'resultURL',sortable:false,width:"50px"},
	                        {name:'projectName',index:'projectName',sortable:false,width:"50px"},
	                        {name:'taskName',index:'taskName',sortable:false,width:"55px"},
	                        {name:'procInstId',index:'procInstId',sortable:false,width:"55px"},
	                        {name:'group',index:'group',sortable:false,width:"50px"},
	                        {name:'operator',index:'operator',sortable:false,width:"50px"},
	                        {name:'path',index:'path',sortable:false,width:"65px"},
	                        {name:'taskStatus',index:'taskStatus',sortable:false,width:"65px"},
	                        {name:'jobType',index:'jobType',sortable:false,width:"60px"},
	                        {name:'projectStatus', index:'projectStatus',resizable:true, align:'center',sortable:false,width:"60px"},
	                        {name:'isQualified',index:'isQualified',sortable:false,width:"60px"},
	                        {name:'remark',index:'remark',sortable:false,width:"60px"}
	                ],
	                sortname:'id',
	                sortorder:'desc',
	                viewrecords:true,
	                rowNum:11,
	                rowList:[10,20,30],
	                pager:"#gridPager",
	                caption: "",
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

					}
	        }).navGrid('#pager2',{edit:false,add:false,del:false});
				jQuery("#gridTable").jqGrid('setLabel',0, '序号', 'labelstyle');
});

/**
* 搜索
*/
function search(){
$("#gridTable").jqGrid('setGridParam',{
	page:1,postData:{"projectName":$("#projectName").val(),"stUpdateTime":$("#stUpdateTime").val(),"edUpdateTime":$("#edUpdateTime").val()},
}).trigger('reloadGrid'); 
}