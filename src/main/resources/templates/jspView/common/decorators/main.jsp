<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jspView/common/include.jsp"%>
<%@ include file="/jspView/common/modelTip.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<meta charset="utf-8" />
<title>${title}</title>

<meta name="description" content="widgets" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctxStatic }/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="${ctxStatic }/css/common/index.css" rel="stylesheet"/>
<link href="${ctxStatic }/css/login1.css" rel="stylesheet" />
<link href="${ctxStatic }/css/taskManage.css" rel="stylesheet" />
<link href="${ctxStatic }/css/addUser.css" rel="stylesheet" />
<link href="${ctxStatic }/jqgrid_5.1.1/css/ui.jqgrid-bootstrap.css" rel="stylesheet"/>
<script src="${ctxStatic }/bootstrap/js/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic }/bootstrap/js/bootstrap.js"></script>
<script src="${ctxStatic}/jQuery-calendar/js/laydate.js"></script>
<script src="${ctxStatic }/jqgrid_5.1.1/js/i18n/grid.locale-cn.js"></script>
<script src="${ctxStatic }/jqgrid_5.1.1/js/jquery.jqGrid.min.js"></script>
<script src="${ctxStatic }/js/common/select.js?version=${version}"></script>
<script type="text/javascript" src="${ctxStatic }/echarts/echarts3.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-validation-1.15.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery-validation-1.15.0/lib/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui-1.5/themes/icon.css">
<script type="text/javascript" src="${ctxStatic}/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="application/javascript">
	var _height=$("body").height();
	
	$('list').css('height',_height+'px');//调整列表的宽高
</script>
<style>
	.menu_list{
		padding-left: 15px !important;
	}
	.menu_third{
		padding-left: 12px !important;
	}
</style>
<sitemesh:write property='head' />
</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- top -->
	<div class="complete">
		<div class="title">
			<nav class="navbar navbar-color">
			  <div class="container-fluid content-lli">
			    <!-- Brand and toggle get grouped for better mobile display -->
			    <img src="<%=request.getContextPath()%>/resource/images/logo.png">
			    <div class="navbar-header navbar-in">
			      <span class="stroke">
			      	<div class="map-group">
					  <span class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    <a href="#"><h3 class="map-login">${title}</h3></a> 
					  </span>
					  
					  
					  						 <span class="exit">
						 <span>
						 	<a href="#" id="welcome"><i class="glyphicon glyphicon-user"></i>您好，${login_user_manage.name}</a>
						 	<ul class="ul-name" id="ul-na" style="display: none;">
						 		
						 		
						 		<li class=""><span class="us">真实姓名：</span>${login_user_manage.realName}</li>
						 		<li class=""><span class="us">邮箱：</span>${login_user_manage.email}</li>
						 		<li class=""><span class="us">人员性质：</span>${login_user_manage.userProperty}</li>
						 		<li class=""><span class="us">所属单位：</span>${login_user_manage.userDept}</li>
						 		
						 		<li class=""><span class="us">作业组：</span>${login_user_manage.groupIds}</li>
						 		<li class=""><span class="us">角色：</span>${login_user_manage.roleIds}</li>
						 		<li class=""><span class="us">入职时间：</span><fmt:formatDate value="${login_user_manage.entryTime}" type="date"/></li>
						 	</ul>
						 </span>
						 <span class="pas-act"><a href="#" data-toggle="modal" data-target="#myModal" onclick="cleanPwd()">修改密码</a></span>

						  <span class="pas-act"><a href="${ctx}/login/logout.do">退出</a></span>	
						</span> 
					</div>
			      </span>
			    </div>
			  </div>
			</nav>
		</div>
	</div>

	<div class="main_container container">
		<div id="firstpane" class="menu_list">
			<h5 class="menu_head first-login"><i class="glyphicon glyphicon-home"></i><a href="${ctx }/login/index.do">首页</a></h5>
			<c:forEach items="${menuList}" var="menu">
			  <c:if test="${menu.pid=='0'}">
			  	<h5 class="menu_head" style="cursor:pointer;"><i class="${menu.icon}"></i>${menu.name}</h5>
			  	<div style="<c:if test="${fn:contains(depthPath,menu.id)==false}">display:none;</c:if>
			  	<c:if test="${fn:contains(depthPath,menu.id)}">display:block;</c:if>" class="menu_body" >
					 <c:forEach items="${menuList}" var="menu2">
					  	<c:if test="${menu2.pid==menu.id and menu2.pid !='0'}">
					  	        <c:if test='${menu2.url ne "#"}'>
					  	            <a href="${ctx}${menu2.url}">${menu2.name}</a>
					  	        </c:if>
					  	        <c:if test='${menu2.url eq "#"}'>
					  	            <h5  class="menu_head" style="cursor:pointer;"><%-- <i class="${menu2.icon}"></i> --%>${menu2.name}</h5>
					  	            <div style="<c:if test="${title1 ne menu2.name}">display:none;</c:if>
	                                    <c:if test="${title1 eq menu2.name}">display:block;</c:if>" class="menu_body menu_third" >
		                                <c:forEach items="${menuList}" var="menu3">
		                                     <c:if test="${menu3.pid==menu2.id and menu3.pid !='0'}">
		                                          <a href="${ctx}${menu3.url}">${menu3.name}</a>
		                                     </c:if>
		                                </c:forEach>
	                                </div>
					  	        </c:if>
                                
						</c:if>
					 </c:forEach>
			  	</div>
			  </c:if>
			</c:forEach>
		</div>
		<div>
			<!-- 面包屑导航 -->
			<div style="margin-top: 5px;margin-right: 3px;margin-left: 175px;" >
			    <ol id="bread" class="breadcrumb crumb-task">
			      <li>${title}</li>
			     <c:if test="${not empty title1}">
			      	<li>${title1}</li>
			      </c:if>
			      <c:if test="${not empty title2}">
			      	<li>${title2}</li>
			      </c:if>
			    </ol>
		    </div>
			<sitemesh:write property='body' />
		</div>
			<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
            	   <div class="row psd-act">
            	   	<div class="col-sm-4">
            	   		<label class="beg-act">原始密码</label>
            	   	</div>
            	   	<div class="col-sm-8">
            	   		<input type="password" id='password' class="form-control" maxlength="16" placeholder="请输入原始密码" onkeydown="if(event.keyCode==32) return false">
            	   	</div>
            	   </div>
            	   <div class="row psd-act">
            	   	<div class="col-sm-4">
            	   		<label class="beg-act">修改密码</label>
            	   	</div>
            	   	<div class="col-sm-8">
            	   		<input type="password" id='modpassword' class="form-control" maxlength="16" placeholder="请输入新密码" onkeydown="if(event.keyCode==32) return false">
            	   	</div>
            	   </div>
            	   <div class="row psd-act">
            	   	<div class="col-sm-4">
            	   		<label class="beg-act">确认密码</label>
            	   	</div>
            	   	<div class="col-sm-8">
            	   		<input type="password" id='modpassword_cf' class="form-control" maxlength="16" placeholder="请再次确认新密码" onkeydown="if(event.keyCode==32) return false">
            	   	</div>
            	   </div>
            </div>
            <div class="modal-footer">
                
                <button type="button" class="btn btn-primary" onclick="modPassword()">提交更改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
	</div>
	
	<!-- update password for model -->
	 <div class="modal fade" id="update" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content modal-user">
                <div class="modal-header" style="border-bottom: 1px solid #FFFFFF;">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body" style="text-align: center;"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
    
    	<!-- update sessionTimeOut for model -->
	 <div class="modal fade" id="sessionTimeOut" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content modal-user">
                <div class="modal-header" style="border-bottom: 1px solid #FFFFFF;">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body" style="text-align: center;"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary"
                        onclick="doSessionTimeOut()">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
</body>
<script>
$(document).ready(function(){
//	$("#firstpane .menu_body:eq(0)").show();
	$("#firstpane h5.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	 $("#secondpane .menu_body:eq(0)").show();
	$("#secondpane h5.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	}); 
	laydate.skin('yahui');//切换皮肤，请查看skins下面皮肤库
});
function dateFomart(val){
	if(val == null || val == ""){
		return "";
	}else{
		var date = new Date(val);
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var d = date.getDate();
		return year + "-" + month + "-" + d;
	}
}
function messagePrompt(msg){
	$("#oper .modal-body").html(msg);
	$("#oper").modal('show');
}

function delPrompt(del){
	$("#isDelete .btn-primary").attr("onclick",del + "()");
	$("#isDelete").modal('show');
}

function delPromptById(del,id){
    $("#isDelete .btn-primary").attr("onclick",del + "("+"'"+id+"'"+")");
    $("#isDelete").modal('show');
}
</script>
<script>
	var wel=document.getElementById("welcome");
	var na=document.getElementById("ul-na");
	wel.onmouseover=function(){
		na.style.display="block";
	};
	wel.onmouseout=function(){
		na.style.display="none";
	};
</script>
<script type="text/javascript">
function isRegisterUserName(s)   
{   
	var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]){5,15}/;   
	if (!patrn.exec(s)) return false 
	return true 
}


function cleanPwd(){
	document.getElementById("password").value="";
	document.getElementById("modpassword").value="";
	document.getElementById("modpassword_cf").value="";
}
function modPassword(){
	var _pwd =document.getElementById("password").value;
	var _modpwd =document.getElementById("modpassword").value;
	var _modpwd_cf =document.getElementById("modpassword_cf").value;
	if(!isRegisterUserName(_modpwd)){
		$("#update .modal-body").html("密码必须以字母开头，至少由字母数字组成的6-16位之间");
		 $("#update").modal('show');
		return false;
	}
	
	if(_modpwd!=_modpwd_cf){
	 	$("#update .modal-body").html("两次输入的密码不一致！");
	 	 $("#update").modal('show');
	    return false;
	}
	$.ajax({
		url : "${ctx}/user/modPassword.do",
		type : "post",
		data : {
			"password": _pwd,
			"modpassword":_modpwd
		},
		dataType : "json",
		success : function(data) {
			 if(data.success=='success'){
				 $("#update .modal-body").html(data.info);
                 $("#update").modal('show');
				 $('#myModal').modal('hide');
	    	  }
	    	  if(data.success=='error'){
	    	       $("#update .modal-body").html(data.info);
	    	       $("#update").modal('show');
	      		  return false;
	    	  }
		},
		error : function (data) {
			$("#update .modal-body").html("未知异常，请联系管理员");
			$("#update").modal('show');
      		return false;
		}
	});
}

$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
          //通过XMLHttpRequest取得响应头，sessionstatus           
          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
          if(sessionstatus=="SessionTimeOut"){
               //这里怎么处理在你，这里跳转的登录页面
        	   $("#sessionTimeOut .modal-body").html("您尚未登录或登录时间过长,请重新登录!");
     		   $("#sessionTimeOut").modal('show');
       	  }
    }
});

function doSessionTimeOut(){
	 $('#sessionTimeOut').modal('hide');
	 window.location.href='/manage/login.jsp';
}
</script>
</html>
