/**
 * 模糊查询
 */
function projectInfoGridReload(){
	jQuery("#gridTable").jqGrid('setGridParam', {
		page:1,
		postData : {
			"projectName" : $("#projectName").val(),
			"resolveFlag" : $("#resolveFlag").val(),
			"startMileage" : $("#startMileage").val(),
			"endMileage" : $("#endMileage").val(),
			"city" : $("#city").val(),
			"version" : $("#version").val(),
			"carNo" : $("#carNo").val(),
			"fstartTime" : $("#fetchTime").val(),
			"fendTime" : $("#fetchEndTime").val(),
			"rstartTime" : $("#returnTime").val(),
			"rendTime" : $("#returnEndTime").val()
	   },
	}).trigger("reloadGrid");
}

/**
 * 工程资料详情页面
 * @param id 工程资料id
 */
function viewProjectDateil(id){
	location.href = "viewProjectDateil?id=" + id;
}

/**
 * 工程资料编辑页面
 * @param id 工程资料id
 */
function viewProjectAdd(){
	location.href = "viewProjectAdd";
}

/**
 * 工程资料编辑页面
 * @param id 工程资料id
 */
function viewProjectEdit(){
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var url = "";
	//修改
    if(rowIds.length == 1){
		var id= jQuery('#gridTable').jqGrid('getCell',rowIds[0],'id');
		url = "viewProjectEdit?id=" + id;
    }else{
    	messagePrompt("请选择一个工程！");
    	return;
    }
	location.href = url;
}

/**
 * 资料导出
 */
function downExcel(type){
	if(type == 'All'){
		$("#excelForm").attr("action", "downExcel?type=All").submit();
	}else{
		var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
		var ids = "";
		if(rowIds.length){
			for(var i in rowIds){
				var id= jQuery('#gridTable').jqGrid('getCell',rowIds[i],'id');
				ids += id + ",";
			}
			$("#id").val(ids);
			$("#excelForm").attr("action", "downExcel").submit();
		}else{
			messagePrompt("请至少选择一个工程！");
		}
	}
}

/**
 * 解算完成
 */
function resolveFinish(){
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var ids = "";
    if(rowIds.length){
    	for(var i in rowIds){
        	var id= jQuery('#gridTable').jqGrid('getCell',rowIds[i],'id');
        	ids += id + ",";
        }
		$.ajax({
			url:"resolveFinish",
			data:{"id": ids},
			type: "post",
			dataType : 'json',
			success:function(data){
				if(data == '1'){
					messagePrompt("解算完成！！！");
					jQuery("#gridTable").trigger("reloadGrid");
				}else{
					messagePrompt("解算失败！！！");
				}
			}
		});
    }else{
    	messagePrompt("请至少选择一个工程！");
    }
}

/**
 * 打开资料删除模态窗口
 */
function delProjectModel(){
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var ids = "";
    if(rowIds.length){
    	for(var i in rowIds){
        	var id= jQuery('#gridTable').jqGrid('getCell',rowIds[i],'id');
        	ids += id + ",";
        }
    	$("#ids").val(ids);
    	$("#delCause-error").html("");
    	$('#delModel').modal('show');
    }else{
    	messagePrompt("请至少选择一个工程！");
    }
}

/**
 * 资料删除
 */
function delProject(){
	if($('#checkDel').is(':checked')) {
		var ids = $("#ids").val();
		var delCause = $("#delCause").val();
		if(delCause.length > 60){
			$("#delCause-error").html("最多输入60个字符");
		}else{
			$.ajax({
				url:"delProject",
				data:{"id": ids,
					  "delCause": delCause
					 },
				type: "post",
				dataType : 'json',
				success:function(data){
					if(data == '1'){
				    	messagePrompt("删除成功！！！");
						$('#delModel').modal('hide');
						jQuery("#gridTable").trigger("reloadGrid");
					}else{
				    	messagePrompt("删除失败！！！");
					}
				}
			});
		}
	}else{
		$("#delCause-error").html("请确定删除");
	}
}

/**
 * 资料切分
 */
function projectSplit(){
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow'); 
	var ids = "";
    if(rowIds.length){
    	for(var i in rowIds){
        	var id= jQuery('#gridTable').jqGrid('getCell',rowIds[i],'id');
        	ids += id + ",";
        }
    	$.ajax({
			url:"projectSplit",
			data:{"id": ids},
			type: "post",
			dataType : 'json',
			success:function(data){
				if(data == "1"){
					messagePrompt("切分成功！！！");
				}else{
					messagePrompt("切分失败！！！");
				}
			},
			error:function(errorMsg){
				console.log("errorMsg:" + errorMsg);
				window.location.href = basePath() + "/500.jsp";
			}
		});
    }else{
    	messagePrompt("请至少选择一个工程！");
    }
}

