$(function(){
	$("#jobType1").initSelect({
		codeType : "tsk_group_split_task",
		codeName : "job_type"
	});
				$("#gridTable").jqGrid({
					url : '/manage/task/taskData',
					datatype : "json",
					styleUI : 'Bootstrap',
					multiselect : true,
					rownumbers: true,
					rownumWidth:'50',
					width: $(".middle").width()*0.99,
					
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
	                        {name:'taskStatus',index:'taskStatus',sortable:false,width:"65px",formatter:function(cellvalue, options, rowObject){
	            				var taskStatus = "";
	            				cellvalue=$.trim(cellvalue);
	            				taskStatus = "<a href=\""+basepath+"/qualityInspection/historyDetail?procInstId="+rowObject.procInstId+"\" target=\"_blank\">"+cellvalue+"</a>";
	            				return taskStatus;
	            			}},
	                        {name:'jobType',index:'jobType',sortable:false,width:"60px"},
	                        {name:'projectStatus', index:'projectStatus',resizable:true,sortable:false,width:"60px"},
	                        {name:'isQualified',index:'isQualified',sortable:false,width:"60px"},
	                        {name:'remark',index:'remark',sortable:false,width:"60px"}
	                ],
	                sortname:'id',
	                sortorder:'desc',
	                viewrecords:true,
	                rowNum:10,
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

				$("#assign").on("click",function(){
						$(".right").css({
							width: "64vh",
							right: "0"
						});
						$("#realName").val("");
						//查询下拉数据字典
						$("#taskCategory").initSelect({
							codeType : "tsk_project_info",
							codeName : "tsk_category"
						});
						$("#gridTable1").jqGrid('setGridParam',{
							postData:{"realName":""},
							}).trigger('reloadGrid'); 
						$("#right_switch").css({
							backgroundPosition: "center"
						});
						$(".right-turn").css({
							width: "30px",
							right: "-30px"	
						});
						$("#right_transfer").css({
							backgroundPosition: "5px center"
						});
				});
				$("#right_switch").on("click",function(){
					$(".right").css({
						width: "30px",
						right: "-30px"	
					});
					$("#right_switch").css({
						backgroundPosition: "5px center"
					});
				});
				
				$("#revise").on("click",function(){
					$(".right-turn").css({
						width: "64vh",
						right: "0"
					});
					$("#realName1").val("");
					$("#gridTable2").jqGrid('setGridParam',{
						postData:{"realName":""},
						}).trigger('reloadGrid'); 
					$("#right_transfer").css({
						backgroundPosition: "center"
					});
					$("#right_transfer").css({
						backgroundPosition: "center"
					});
					$(".right").css({
						width: "30px",
						right: "-30px"	
					});
					$("#right_switch").css({
						backgroundPosition: "5px center"
					});
			});
			$("#right_transfer").on("click",function(){
				$(".right-turn").css({
					width: "30px",
					right: "-30px"	
				});
				$("#right_transfer").css({
					backgroundPosition: "5px center"
				});
			});
			
			$("#checkDel").click(function() {
				if($('#checkDel').is(':checked')) {
					$("#deleteReason").removeAttr("disabled");
				}else{
					$("#deleteReason").attr("disabled","true");
				}
			});
		})
			
			
			$(function(){
				$("#gridTable1").jqGrid({
					url : '/manage/task/selectListJober',
					datatype : "json",
					styleUI : 'Bootstrap',
					multiselect: true,  
					multiselectWidth:50,
					multiboxonly:true,  
					beforeSelectRow: beforeSelectRow,//js方法
					rownumbers: true,
					rownumWidth:'50',
					width: $(".middle").width()*0.33,
					height:$(".right").height()*0.33,
	                colNames:['主键','用户名', '真实姓名', '已有任务数'],
	                colModel:[
	                        {name:'userId',index:'userId',sortable:false,hidden : true},
	                        {name:'name',index:'name',sortable:false},
	                        {name:'realName',index:'realName',sortable:false},
	                        {name:'taskNum',index:'taskNum',sortable:false},
	                ],
	                sortname:'id',
	                sortorder:'desc',
	                viewrecords:true,
	                rowNum:10,
	                rowList:[10,20,30],
	                pager:"#gridPager1",
	                caption: "",
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
	        }).navGrid('#pager2',{edit:false,add:false,del:false});
				jQuery("#gridTable1").jqGrid('setLabel',0, '序号', 'labelstyle');
				jQuery("#gridTable1").jqGrid('setLabel',1, '选择', 'labelstyle');
				
		var msg = $("#msg").val();
		if(msg != ''){
			if(msg == 1){
				messagePrompt('报表上传成功！');
			}else if(msg == 0){
				messagePrompt('文件读取失败！');
			}else if(msg == 2){
				messagePrompt('上传文件为空！');
			}else if(msg == 3){
				messagePrompt('上传路径不存在！');
			}else if(msg == 4){
				messagePrompt('报表上传失败！');
			}
		}
	});

$(function(){
	$("#gridTable2").jqGrid({
		url : '/manage/task/selectListJober',
		datatype : "json",
		styleUI : 'Bootstrap',
		multiselect: true, 
		multiselectWidth:50,
		multiboxonly:true,  
		rownumWidth:'50',
		beforeSelectRow: beforeSelectRow,//js方法
		rownumbers: true,
		width: $(".middle").width()*0.33,
		height:$(".right").height()*0.33,
        colNames:['主键','用户名', '真实姓名', '已有任务数'],
        colModel:[
                {name:'userId',index:'userId',sortable:false,width:"20px",hidden : true},
                {name:'name',index:'name',sortable:false,width:"20px"},
                {name:'realName',index:'realName',sortable:false,width:"20px"},
                {name:'taskNum',index:'taskNum',sortable:false,width:"30px"},
        ],
        sortname:'id',
        sortorder:'desc',
        viewrecords:true,
        rowNum:10,
        rowList:[10,20,30],
        pager:"#gridPager2",
        caption: "",
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
	}).navGrid('#pager2',{edit:false,add:false,del:false});
	jQuery("#gridTable2").jqGrid('setLabel',0, '序号', 'labelstyle');
	jQuery("#gridTable2").jqGrid('setLabel',1, '选择', 'labelstyle');
});

function beforeSelectRow()  
{  
    $("#gridTable1").jqGrid('resetSelection');  
    return(true);  
} 

/**
* 搜索
*/
function search(){
$("#gridTable").jqGrid('setGridParam',{
page:1,postData:{"taskName":$("#taskName").val(),"taskStatus":$("#status").val(),"createST":$("#createST").val(),"createET":$("#createET").val(),"operator":$("#operator").val(),"jobType1":$("#jobType1").val()},
}).trigger('reloadGrid'); 
}
/**
 * 重置搜索条件
 */
function doReset() {
	$("#projectName").val("");
	$("#fetchStartTime").val("");
	$("#fetchEndTime").val("");
	$("#city").val("");
	$("#carNo").val("");
	$("#status").val("");
}


/**
 * 删除任务
 */
function deleteJob() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var deleteReason = $('#deleteReason').val();
	if($('#checkDel').is(':checked')) {
		if(deleteReason.length > 60){
			$("#delCause-error").html("最多输入60个字符");
		}else{$.ajax({
			type : 'post',
			url : '/manage/task/deleteJob',
			data : {
				procInstIds : rowIds,
				deleteReason:deleteReason
			},
			traditional:true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('操作成功!');
					$('#gridTable').trigger('reloadGrid');
					$("#deleteReason").attr("disabled","true");
					$('#myModal3').modal('hide');
				} else {
					messagePrompt(str.errorMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		
		})
		}
	} else {
		messagePrompt('请确认删除！');
	}
}


/**
 * 设置作业类型
 */
function node_set_type() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var jobType = $('#jobType').val();
	if (rowIds == '') {
		messagePrompt('你还没有选择任何内容!');
	}else if(jobType==""){
		messagePrompt('请选择作业类型！');
	} else {
		$.ajax({
			type : 'post',
			url : '/manage/task/node_set_type',
			data : {
				procInstIds : rowIds,
				jobType : jobType,
			},
			traditional:true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('操作成功！');
					$('#gridTable').trigger('reloadGrid');
					$('#myModal4').modal('hide');
				} else {
					messagePrompt(str.errorMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		})
	}
}

/**
 * 资料导出
 * @returns
 */
function downExcel(){
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var ids = "";
    if(rowIds.length){
        for(var i in rowIds){
        	var id= jQuery('#gridTable').jqGrid('getCell',rowIds[i],'procInstId');
        	ids += id + ",";
        }
        $("#id").val(ids);
        $("#downExcelForm").attr("action", "downExcel").submit();
    }else{
    	messagePrompt('请至少选择一个任务！');
    }
}

/**
 * 搜索作业员
 */
function searchOpertor() {
	var realName=$("#realName").val();
	$("#gridTable1").jqGrid('setGridParam',{
		page:1,postData:{"realName":realName},
	}).trigger('reloadGrid'); 
}

/**
 * 搜索作业员
 */
function searchOpertor1() {
	var realName=$("#realName1").val();
	$("#gridTable2").jqGrid('setGridParam',{
		page:1,postData:{"realName":realName},
	}).trigger('reloadGrid'); 
}

/**
 * 任务分配
 */
function node_jobtask_claim_assign() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var id=$('#gridTable1').jqGrid('getGridParam','selrow');
	if (rowIds == '') {
		messagePrompt('你还没有选择需要分配的任务！');
	} else if(id==""){
		messagePrompt('请选择作业员！');
	}else {
		$.ajax({
			type : 'post',
			url : '/manage/task/node_jobtask_claim_assign',
			data : {
				procInstIds : rowIds,
				userId:id
			},
			traditional:true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('操作成功！');
					$(".right").css({
						width: "30px",
						right: "-30px"	
					});
					$("#right_switch").css({
						backgroundPosition: "5px center"
					});
					$('#gridTable').trigger('reloadGrid');
					
				} else {
					messagePrompt(str.errorMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		})
	}
}

/**
 * 任务移交
 */
function node_job_transfer() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var id=$('#gridTable2').jqGrid('getGridParam','selrow');
	if (rowIds == '') {
		messagePrompt('你还没有选择需要分配的任务！');
	} else {
		$.ajax({
			type : 'post',
			url : '/manage/task/node_job_transfer',
			data : {
				procInstIds : rowIds,
				userId:id
			},
			traditional:true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('操作成功！');
					$(".right-turn").css({
						width: "30px",
						right: "-30px"	
					});
					$("#right_transfer").css({
						backgroundPosition: "5px center"
					});
					$('#gridTable').trigger('reloadGrid');
				} else {
					messagePrompt(str.errorMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		})
	}
}

/**
 * 任务审批
 */
function node_job_transfer_audit() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var isAgree = $('input[name="isAgree"]:checked').val();
	if (rowIds == '') {
		messagePrompt('你还没有选择需要分配的任务！');
	} else if (isAgree==''){
		messagePrompt('请选择是否同意移交！');
	}else {
		$.ajax({
			type : 'post',
			url : '/manage/task/node_job_transfer_audit',
			data : {
				procInstIds : rowIds,
				isAgree:isAgree
			},
			traditional:true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('操作成功！');
					$('#gridTable').trigger('reloadGrid');
					$('#myModal2').modal('hide');
				} else {
					$('#myModal2').modal('hide');
					messagePrompt(str.errorMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		})
	}
}

/**
 * 任务提交
 */
function node_result_commit() {
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	if (rowIds == '') {
		messagePrompt('你还没有选择需要提交的任务！');
	} else {
		$.ajax({
			type : 'post',
			url : '/manage/task/node_result_commit',
			data : {
				procInstIds : rowIds,
			},
			traditional:true,
			async : false,
			dataType : 'text',
			success : function(data) {
				var str = eval('(' + data + ')');
				if (str.msg == 1) {
					messagePrompt('任务提交成功！');
					$('#gridTable').trigger('reloadGrid');
				} else {
					messagePrompt(str.errorMessage);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				messagePrompt('操作异常！');
				window.location.href=basePath()+"/500.jsp";
			}
		})
	}
}

function openModel(id){
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	if (rowIds == '' && id !="myModal5") {
		messagePrompt('请选择需要操作的任务！');
	} else {
		$('#'+id).modal('show');
	}
}
