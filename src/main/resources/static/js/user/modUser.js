   //多选
/*     $('.selectpicker').selectpicker({
    	  style: 'btn-info',
    	  size: 4
    }); */
//初始化参数-------start


//查询角色
function searchRoles(roleIds){
	 $.ajax({
	      url : "../role/interfaceRole",
	      type:"post",
	      dataType : "json",
	      success : function(data) {
	    	  if(data.success=="true"){
		        var obj = data.obj;
		        var len = obj.length;
		        for(var i=0;i<len;i++){
		        	 if(obj[i].id == roleIds){
                         $("#roleIds").append("<option value='"+obj[i].id+"' selected>"+obj[i].name+"</option>");
                     }else{
                    	 $("#roleIds").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
                     }
		        }
		       /*  $("#roleIds").selectpicker('val', roleIds);
		        $('#roleIds').selectpicker('render');
                $('#roleIds').selectpicker('refresh'); */
	    	  }
	      },
	      error:function(data){
	    	
	      }
	    }
	 );
}

function searchGroups(id){
	$.ajax({
	      url : "../group/selectSysGroupAll",
	      type:"post",
	      dataType : "json",
	      success : function(data) {
	    	  if(data.success=="true"){
		        var obj = data.obj;
		        var len = obj.length;
		        for(var i=0;i<len;i++){
		             if(obj[i].id == id){
		            	 $("#groupIds").append("<option value='"+obj[i].id+"' selected>"+obj[i].name+"</option>");
		             }else{
		            	 $("#groupIds").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
		             }
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
	
	var strGroupIds = $("#groupIds").val();
	var strRoleIds = $("#roleIds").val();
	/* if($("#groupIds").val()!='' && $("#groupIds").val()){
		strGroupIds = $("#groupIds").val().join(",");
		
	} */
	/* if($("#roleIds").val()!='' && $("#roleIds").val()){
		strRoleIds = $("#roleIds").val().join(",");
	} */
    var pwd = $("#password").val();
    var pwd_cf = $("#password_cf").val();
    if(pwd != pwd_cf){
        var password_cfError=document.getElementById("password_cfError");
        password_cfError.innerHTML="两次输入的密码不一致！";
        return false;
    }
	 $.ajax({
	      url : "doModUser",
	      type:"post",
	      data : {
	    	    "id":$("#id").val(),
	    	    "code":$("#code").val(),
	    	    "name":$("#userName").val(),
	    	    
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
	    		  $("#isBack .modal-body").html("修改成功，返回操作列表");
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
  
