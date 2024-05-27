//多选
  /*   $('.selectpicker').selectpicker({
  style: 'btn-info',
  size: 4
}); */ 
//初始化参数-------start
$(function (){
	searchRoles();
	searchGroups();
});
//查询角色
function searchRoles(){
	 $.ajax({
	      url : "../role/interfaceRole",
	      type:"post",
	      dataType : "json",
	      success : function(data) {
	    	  if(data.success=="true"){
		        var obj = data.obj;
		        var len = obj.length;
		        for(var i=0;i<len;i++){
		        	 $("#roleIds").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
		        }
		       /*  $('#roleIds').selectpicker('render');
                $('#roleIds').selectpicker('refresh'); */
	    	  }
	      },
	      error:function(data){
	    	
	      }
	    }
	 );
}

function searchGroups(){
	$.ajax({
	      url : "../group/selectSysGroupAll",
	      type:"post",
	      dataType : "json",
	      success : function(data) {
	    	  if(data.success=="true"){
		        var obj = data.obj;
		        var len = obj.length;
		        for(var i=0;i<len;i++){
		        	 $("#groupIds").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
		        }
		        /* $('#groupIds').selectpicker('render');
                $('#groupIds').selectpicker('refresh'); */
	    	  }
	      },
	      error:function(data){
	    	
	      }
	    }
	 );
}
//初始化参数-------end   
//检查code是否存在
function checkCode(){
	var code = $("#code").val();
	var codeError = document.getElementById("codeError");
	if(code == null || code == undefined || code == ''){
		codeError.innerHTML="工号不能为空，请输入工号";
		return false;
	}
	if(escape(code).indexOf("%u")!=-1){
		codeError.innerHTML="工号不能有中文";
        return false;
	}
	var b = false
	$.ajax({
		url : "checkCode",
		type : "post",
		data : {"code": code},
		dataType : "json",
		async : false,
		success : function(data) {
			 if(data.success=='success'){
				 codeError.innerHTML="";
				 b = true;
	    	  }
	    	  if(data.success=='error'){
	    		  codeError.innerHTML="工号已经存在，请重新输入";
	    		  b = false;
	    	  }
		},
		error : function (data) {
			codeError.innerHTML="未知异常，请联系管理员";
			b = false;
		}
	});
	
	return b;
}
//检查用户名是否存在
function checkName(){
	var name = $("#name").val();
	var userError = document.getElementById("nameError");
	if(name == null || name == undefined || name == ''){
		userError.innerHTML="用户名不能为空，请输入用户名";
		return false;
	}
	if(escape(name).indexOf("%u")!=-1){
		nameError.innerHTML="用户名不能有中文";
        return false;
    }
	var b = false;
	$.ajax({
		url : "checkUserName.do",
		type : "post",
		async : false,
		data : {"userName": name},
		dataType : "json",
		success : function(data) {
			 if(data.success=='success'){
				 userError.innerHTML="";
				 b = true;
	    	  }
	    	  if(data.success=='error'){
	    		  userError.innerHTML="此账号已经存在，请重新输入";
	    		  b = false;
	    	  }
		},
		error : function (data) {
			userError.innerHTML="未知异常，请联系管理员";
			b = false;
		}
	});
	return b;
}
//初始化提示
function initValidate(obj){
	 document.getElementById(obj).innerHTML="";
}
//提交 
function showValidate(obj){
	for (var prop in obj) {  
		  if (obj.hasOwnProperty(prop)) {
			  var inputError=prop+"Error";
			  document.getElementById(inputError).innerHTML=obj[prop];
		  }  
	}
}
function doSubmit(){
	var groupIds =$("#groupIds").val();
	console.log("groupIds",groupIds);
	var strGroupIds="";
	//多选的时候使用。现在为单选
	/* if(groupIds!=null && groupIds != ""){
		strGroupIds = groupIds.join(",");
	} */
	strGroupIds = groupIds;
	var roleIds = $("#roleIds").val();
	var strRoleIds="";
	strRoleIds = roleIds;
	//多选的时候使用。现在为单选
	/* if(roleIds!=null  && roleIds != ""){
		strRoleIds = roleIds.join(",");
	} */
	var pwd = $("#password").val();
	var pwd_cf = $("#password_cf").val();
	if(pwd != pwd_cf){
	    var password_cfError=document.getElementById("password_cfError");
		password_cfError.innerHTML="两次输入的密码不一致！";
		return false;
	}
	 if(!checkName()){
		 console.log(checkName());
		return false;
	}
	if(!checkCode()){
		console.log(checkCode());
        return false;
    } 
	 $.ajax({
	      url : "doAddUser",
	      type:"post",
	      data : {
	    	    "name":$("#name").val(),
	    	    "code":$("#code").val(),
		        "password":$("#password").val(),
		        "realName":$("#realName").val(),
		    	"email":$("#email").val(),
		    	"userProperty":$("#userProperty").val(),
		    	"userDept":$("#userDept").val(),
		    	"groupIds":strGroupIds,
		    	"roleIds":strRoleIds,
		    	"entryTime":$("#entryTime").val()
	      },
	      dataType : "json",
	      success : function(data) {
	    	  if(data.success=='success'){
	    		  $("#isBack .modal-body").html("添加成功，返回操作列表");
                  $('#isBack').modal('show');
	    	  }
	    	  if(data.success=='error'){
	    		  if(data.msg == 0){
	    			  messagePrompt('操作失败，请重试');
	    		   }else if(data.msg == 2){
	    			  showValidate(data.errorinfo);
	    		   }else if(data.msg == 1){
	    			   messagePrompt('操作失败，请联系管理员');
	    		   }
	    	   }
	      },
	      error:function(data){
	    	  messagePrompt('操作失败');
	      }
	    }
	 );
}