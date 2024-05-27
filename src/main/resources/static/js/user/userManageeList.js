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

basepath = basePath();
var start = {
	elem: '#start',
	format: 'YYYY-MM-DD',
	min: laydate.now(), //设定最小日期为当前日期
	max: '2099-06-16 23:59:59', //最大日期
	istime: true,
	istoday: false,
	choose: function(datas){
	end.min = datas; //开始日选好后，重置结束日的最小日期
	end.start = datas //将结束日的初始值设定为开始日
	}
	};

$(function() {
	pageInit();
});
function pageInit(){
	$("#gridTable5").jqGrid({
    url:"searchUserList.do",
	//datatype: "local",
	datatype: "json",
    styleUI : 'Bootstrap',
	width: $(".middle").width()*0.99,
	multiselect: true,
	rownumbers: true,
	mtype:"POST",
    colNames:['工号', '用户名', '真实姓名', '作业组','邮箱','入职时间','人员性质','所属单位'],
    colModel:[
        	{name:'code',index:'code',sortable:false,width:"50px"},      
        	{name:'name',index:'name',sortable:false,width:"50px"},
            {name:'realName',index:'realName',sortable:false,width:"50px"},
            {name:'groupName',index:'groupName',sortable:false,width:"55px"},       
            {name:'email',index:'email',sortable:false,width:"55px"},
            {name:'strEntryTime',index:'strEntryTime',sortable:false,width:"55px"},
            {name:'userProperty',index:'userProperty',sortable:false,width:"50px"},
            {name:'userDept',index:'userDept',sortable:false,width:"50px"},
    ],
    sortname:'id',
    sortorder:'desc',
    viewrecords:true,
    rowNum:10,
    rowList:[10,20,30],
    pager:"#gridPager5",
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
}).navGrid('#pager2',{edit:false,add:false,del:false});
 jQuery("#gridTable5").jqGrid('setLabel',0, '序号', 'labelstyle'); 
   }
	
	/**
 * 搜索
*/
function doSearch() {
	$("#endTime").val();
$("#gridTable5").jqGrid('setGridParam', {
	page:1,
	postData : {
		'name' : $("#name").val(),
		'code' : $("#code").val(),
		'realName' : $("#realName").val(),
		'userProperty' : $("#userProperty").val(),
		'userDept' : $("#userDept").val(),
		'groupId' : $("#groupId").val(),
		'strStartTime':$("#startTime").val(),
		'strEndTime':$("#endTime").val()
	}
}).trigger('reloadGrid');
}
//重置搜索
function doReset(){
	$("#name").val("");
$("#code").val("");
$("#realName").val("");
$("#userProperty").val("");
$("#userDept").val("");
$("#groupId").val("");
$("#startTime").val("");
$("#endTime").val("");
}
//修改用户
function modUser(){
	var rowIds = $("#gridTable5").jqGrid('getGridParam', 'selarrrow');
var len = rowIds.length;
if(len!=1){
	messagePrompt('请选择一条操作记录');
	return false;
}

if(len==1){
	var id = rowIds[0];
	window.location.href=basepath+"/user/modUser.do?id="+id;
		
	}
}

//删除用户显示
function deleteUserShow(){
	var rowIds = $("#gridTable5").jqGrid('getGridParam', 'selarrrow').join(",");
var len = rowIds.length;
if(len<1){
	messagePrompt('至少选择一个需要删除的用户');
    return false;
}
delPrompt('isDelete');
}
//删除用户
function isDelete(){
	var rowIds = $("#gridTable5").jqGrid('getGridParam', 'selarrrow').join(",");
$.ajax({
  url : "../user/deleteUsers.do",
  type:"post",
  data:{
	  "ids":rowIds
  },
  dataType : "json",
  success : function(data) {
	  if(data.success=="success"){
		messagePrompt('操作成功');
		doSearch();
	  }
	  if(data.success=="error"){
		messagePrompt('操作失败');
    	  }
      },
      error:function(data){
    	
      }
    }
 );
}
//用户上传文件类型校验
function checkUpload(){
	var fileName = $('#files').val();
if(fileName=="" || fileName == undefined){
	$("#fileError").html('文件不能为空');
	return false;
}else{
	$("#fileError").html("");
}
 var fileext=fileName.substring(fileName.lastIndexOf("."),fileName.length)  
 fileext=fileext.toLowerCase();
 var type =".xls.xlsx.xlsb.xlsm.xlst";
 if(type.indexOf(fileext)==-1){
	 $("#fileError").html('导入数据格式必须是EXCEL格式文件');
	 return false;
 }else{
	 $("#fileError").html("");
 }
 var pwd =  $("#defPassword").val();
 if(pwd.length<6 || pwd.length>16){
	 $("#defPasswordError").html('密码应为6--16个字符！');
	 return false;
 }else{
     var pattern = /[a-zA-Z]([a-zA-Z]+[0-9]+[a-zA-Z0-9]*|[0-9]+[a-zA-Z0-9]*)/;
     var flag = pattern.test(pwd);
     console.log("pattern.test(pwd)",pattern.test(pwd));
     if(!flag){
    	 $("#defPasswordError").html('密码必须以字母开头，至少由字母数字组成');
         return false;
     }else{
    	 $("#defPasswordError").html("");
         }
     } 
	 return true;
}
//用户上传初始化模态框
function clearData(){
	$('#files').val("");
$("#defPassword").val("");
}
//用户上传
function doUpload(){
	if(!checkUpload()){
		return false;
	}
	$.ajaxFileUpload
    (
        {
            url: 'doUploadUser.do',       //用于文件上传的服务器端请求地址
        secureuri: false,          //是否需要安全协议，一般设置为false
        data: { password: $("#defPassword").val()},
        fileElementId: 'files',    //文件上传域的ID
        dataType: 'json',          //返回值类型 一般设置为json
        success: function (data, status)  //服务器成功响应处理函数
        {
            
        	if(data.success=="success"){
	    		 messagePrompt(data.info);
	    		$('#leadUser').modal('hide');
	    		doSearch();
	    	  }
	    	  if(data.success=="error"){
	    		 messagePrompt(data.info);
	    		$('#leadUser').modal('hide');
	    	  }  
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
             messagePrompt("未知异常，请联系管理员");
             $('#leadUser').modal('hide');
            }
        }
    )
}

//导出用户信息
function downUser(){
	var rowIds = $("#gridTable5").jqGrid('getGridParam', 'selarrrow');
var len = rowIds.length;
var ids;
if(len<1){
	//alert("没有选择要下载的用户，默认下载全部用户");
	$("#exportUser").modal('show');
	//ids = "";
}else{
	ids = rowIds.join(",");
	window.location.href=basepath+"/user/doDownUser.do?ids="+ids;
	}
}
function doExportUser(){
	var ids;
	ids = "";
window.location.href=basepath+"/user/doDownUser.do?ids="+ids;
}