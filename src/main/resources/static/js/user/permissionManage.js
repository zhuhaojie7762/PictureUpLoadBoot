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

basePath=basePath();

function defineLoad(){
	//insert表单校验
	
	$("#insertForm").validate({
		rules:{
			insert_name:{
				required:true,
				maxlength:8,
			},
			insert_url:{
				required:true,
				maxlength:256,
			},
			insert_identification:{
				required: true,
				maxlength:30
			},
			insert_remark:{
				maxlength:50,
			},
			insert_sortNum:{
				required: true,
				number: true,
				digits: true,
				maxlength:32
			}
		},
		messages:{
			insert_name:{
			  	required:"权限名称不能为空",
			  	maxlength:"名称过长"
			},
			insert_url:{
				required:"URL不能为空",
				maxlength:"URL过长",
			},
			insert_identification:{
				required: "唯一标示不能为空",
				maxlength: "唯一标示过长"
			},
			insert_remark:{
				maxlength:"过长",
			},
			insert_sortNum:{
				required: "必填",
				number: "请输入有效的数字",
				digits: "只能输入数字",
				maxlength:"最大不能超过32位"
			}
		},
		submitHandler:function(form) {
			insertP();
		}
	});
	
	$("#updateForm").validate({
		rules:{
			update_name:{
				required:true,
				maxlength:8,
			},
			update_url:{
				required:true,
				maxlength:256,
			},
			update_identification:{
				required: true,
				maxlength:30
			},
			update_remark:{
				maxlength:50,
			},
			update_sortNum:{
				required: true,
				number: true,
				digits: true,
				maxlength:32
			}
		},
		messages:{
			update_name:{
			  	required:"权限名称不能为空",
			  	maxlength:"名称过长"
			},
			update_url:{
				required:"URL不能为空",
				maxlength:"URL过长",
			},
			update_identification:{
				required: "唯一标示不能为空",
				maxlength: "唯一标示过长"
			},
			update_remark:{
				maxlength:"过长",
			},
			update_sortNum:{
				required: "必填",
				number: "请输入有效的数字",
				digits: "只能输入数字",
				maxlength:"最大不能超过32位"
			}
		},
		submitHandler:function(form) {
			updateP();
		}
	});
}		

function insertValida(){
	$("#insertForm").submit();
}

function updateValida(){
	$("#updateForm").submit();
}
/*###########################################################*/
$(function() {
	defineLoad();
});
/*###########################################################*/

/**
 * 搜索
*/
function doSearch(id) {
	$("#gridTableRole").jqGrid('setGridParam', {
		postData : {
			'id' : id,
			'name':$("#permissionName").val()
		}
	}).trigger('reloadGrid');
}	



function viewMenu(){
$("#insertSelect").combotree({
    url: basePath+'/permission/getPermession',
    lines:true,
    cascadeCheck:false,
    onSelect:function(node){
    	 var tree = $(this).tree;  
           //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
         var isLeaf = tree('isLeaf', node.target);  
         if (!isLeaf) {
             /*  alert("请选择子节点!!!"); */
             //清除选中  
             $('#catalogId').combotree('clear');  
         }  
    }
});

}
function init(){
var t = $("#roleselect").combotree('tree'); // 得到树对象  
var n = t.tree('getSelected'); // 得到选择的节点  
alert(n.text);
}


function insertView(){
viewMenu();
$("#addUser").modal("show");
}

function showInsertValidate(obj){
for (var prop in obj) {  
    if (obj.hasOwnProperty(prop)) {
        var inputError="insertSpan_"+prop;
        document.getElementById(inputError).innerHTML=obj[prop];
    }  
	}
}

//新增权限AJAX
function insertP() {
$.ajax({
	type : "POST",
	url : basePath+"/permission/insertP",
	data : {
		name : $("#insertName").val(),
		remark : $("#insertRemark").val(),
		url : $("#insertUrl").val(),
		pid : $("#insertSelect").combotree("getValues").join(","),
		identification : $("#insertIdentification").val(),
		type:$("#insertType").val(),
		sortNum:$("#insertSort").val(),
		is_jump_link:$("#insertIsJumpLink").val()
	},
	dataType : "json",
	success : function(data) {
		if (data.success == "success") {
			$("#addUser").modal("hide");
			messagePrompt("添加成功");
			doSearch();
		}
		if(data.success=="error"){
			if(data.msg == 0){
				messagePrompt("操作失败，请重试");
    		}else if(data.msg == 2){
    			//console.log(data.errorinfo);
    			//给添加操作后面加操作值
    			showInsertValidate(data.errorinfo);
    		}else if(data.msg == 1){
    			messagePrompt("操作失败，请联系管理员");
    		}
		}
	}
});
}
//删除确认
function delP(id) {
delPromptById('deletedsP',id);
}
//删除AJAX
function deletedsP(id){
	$.ajax({
		type : "POST",
		url : basePath+"/permission/deletedsP",
		data : {
			ids:id
		},
		dataType : "json",
		success : function(data) {
			if (data.success == "success") {
				messagePrompt("删除成功");
				doSearch();
			}
		}
	});	
}

//修改AJAX--数据显示
function updatePView(id){
console.log(id);
$('#modificationUser').modal('show');
$.ajax({
	type : "POST",
	url : basePath+"/permission/updatePView",
	data : {
		Id:id
	},
	dataType : "json",
	success : function(data) {
		if (data.success == "true") {
			var obj=data.obj;
			$("#updateId").val(obj.id);
			$("#updateName").val(obj.name);
			$("#updateUrl").val(obj.url);
			$("#updateIdentification").val(obj.identification);
			$("#updateRemark").val(obj.remark);
			$("#updateSort").val(obj.sortNum);
			$("#updateType option[value='"+obj.type+"']").attr("selected","selected");
			$("#updateIsJumpLink option[value='"+obj.is_jump_link+"']").attr("selected","selected");
			$('#updateSelect').combotree('setValues',obj.pid)
		}
	}
});
$("#updateSelect").combotree({
    url: basePath+'/permission/getPermessionById?id='+id,
    multiple:true,		 //定义是否级联检查。
    cascadeCheck:false,
    onCheck:function(node,checked){
    	 //console.log("node"+node.id);
    	 //console.log("checked"+checked);
    	 
    	 var t =$('#updateSelect').combotree('tree')
    	 var nodes = t.tree('getChecked');
    	 if(nodes.length>1){ 
    		 $('#updateSelect').combotree('setValues',node.id)
    	 }
    }
});
}

function showUpdateValidate(obj){
for (var prop in obj) {  
    if (obj.hasOwnProperty(prop)) {
        var inputError="updateSpan_"+prop;
        document.getElementById(inputError).innerHTML=obj[prop];
    }  
	}
}
//AJAX--数据修改
function updateP(){
$.ajax({
	type : "POST",
	url : basePath+"/permission/updateP",
	data : {
		id:$("#updateId").val(),
		name:$("#updateName").val(),
		url:$("#updateUrl").val(),
		identification:$("#updateIdentification").val(),
		remark:$("#updateRemark").val(),
		pid:$("#updateSelect").combotree("getValues").join(","),
		type:$("#updateType").val(),
		sortNum:$("#updateSort").val(),
		is_jump_link:$("#updateIsJumpLink").val()
	},
	dataType : "json",
	success : function(data) {
		if (data.success == "success") {
			$('#modificationUser').modal('hide');
			messagePrompt("修改成功");
			var updateId=$("#updateId").val();
			var pid=$("#updateSelect").combotree("getValues").join(",");
			console.log("updateId",updateId);
			console.log("pid",pid);
			if(pid!=null&&pid!=""){
				doSearch(pid);
			}else{
				doSearch(updateId);
			}
		}
		if(data.success=="error"){
			if(data.msg == 0){
				messagePrompt("操作失败，请重试");
    		}else if(data.msg == 2){
    			//console.log(data.errorinfo);
    			//给添加操作后面加操作值
    			showUpdateValidate(data.errorinfo);
    		}else if(data.msg == 1){
    			messagePrompt("操作失败，请联系管理员");
    		}
		}
	}
});
}
