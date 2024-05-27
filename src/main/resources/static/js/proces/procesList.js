

function isDelete(id) {
	delPromptById('delProces',id);
}

function delProces(id) {
	var rowId = $('#gridTable').jqGrid('getGridParam', 'selrow');
	var cellval = $('#gridTable').jqGrid('getCell', rowId, 'deploymentId');
	$.ajax({
		type : 'post',
		url : '/manage/activiti/process/newDelete.do?deploymentId=' + id,
		traditional : true,
		async : false,
		dataType : 'text',
		success : function(data) {
			messagePrompt('操作成功!');
			$('#gridTable').trigger('reloadGrid');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			messagePrompt('操作异常!');
			window.location.href = basePath() + "/500.jsp";
		}
	})
}





