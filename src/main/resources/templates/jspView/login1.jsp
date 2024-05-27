<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jspView/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<meta charset="UTF-8" />
<title>${title}</title>
<link rel="stylesheet" href="${ctxStatic }/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="${ctxStatic }/css/index.css" />
<script type="text/javascript"
	src="${ctxStatic }/bootstrap/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic }/bootstrap/js/bootstrap.js"></script>
<%
//初始数据
String userName="";
String pwd = "";
String remember = "";
String autoLogin = "";
Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
if(cookies!=null && cookies.length>0){
	for(Cookie cookie : cookies){
		if("username".equals(cookie.getName())){
			userName = cookie.getValue();
		}else if("pwd".equals(cookie.getName())){
			pwd = cookie.getValue();
		}else if("remember".equals(cookie.getName())){
			remember = cookie.getValue();
	    }else if("autoLogin".equals(cookie.getName())){
	    	autoLogin = cookie.getValue();
	    }
	}
}
%>

<body>
	<div class="main-index img-index">
		<div class="head-index">
			<img src="${ctxStatic }/images/logo.png">
			<div class="manage-index">
				<h4>${title}</h4>
			</div>
		</div>
		<div class="body-index">
			<span class="body-img"><img
				src="${ctxStatic }/images/sucai.png" /></span> <span class="xian-index"><img
				src="${ctxStatic}/images/xian.png"></span> <span class="form-right">
				
				<form action="login/login.do" method="get" id="myform">
					<div class="row row-index">
						<div class="col-sm-4 user-in">
							<label class="user-index">用户名：</label>
						</div>
						<div class="col-sm-8">
							<input id="name" type="text" class="form-control form-index"
								name="name" value="<%=userName%>" /> <br />
							<span id="errorname" style="color: red"></span>
						</div>
					</div>
					<br />
					<div class="row row-index">
						<div class="col-sm-4 user-in">
							<label class="user-index">密码：</label>
						</div>
						
						
						<div class="col-sm-8">
							<input id="password" type="password" class="form-control form-index" name="password" value="<%=pwd%>"/><br />
							<span id="errorpassword" style="color: red"></span>
						</div>
					</div>
					<div class="row row-index">
						<div class="col-sm-3 user-in"></div>
						<div class="col-sm-9 rem-index">
							<input type="checkbox" name="remember" id="remember" value="auto" <c:if test='<%="auto".equals(remember)%>'>checked</c:if>/>记住用户名 <input
								id="autoLogin_input" type="checkbox" name="autoLogin" value="auto" <c:if test='<%="auto".equals(autoLogin)%>'>checked</c:if>/>下次自动登录
						</div>
					</div>
					<div class="login-index">
						<a id="loginButton"><img src="${ctxStatic }/images/login.png" /></a>
					</div>
				</form>
			</span>
		</div>
	</div>
	
	
	<div class="modal fade" id="kickoutDialog" tabindex="-1" role="dialog"
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
    
    
</body>
<script type="text/javascript">
	var info={};
	info = getCookie(info);
	console.log("info",info);
	var autoLogin = info.autoLogin;
	if(typeof(autoLogin) != 'undefined' && autoLogin == "auto"){
		$.ajax({
			type : "POST",
			url : "/manage/login/login.do",
			data : {
				name:info.username,
				password:info.pwd,
				autoLogin:info.autoLogin,
				remember:info.remember
			},
			dataType : "json",
			success : function(data) {
				var list=data.success;
				if(list=="success"){
					window.location.href="${ctx }/login/index.do"; 
				}else{
					document.cookie="autoLogin="+"";
				}
			}
		})
	}
    function getCookie(info){ 
    	var strCookie=document.cookie; 
    	var arrCookie=strCookie.split(";"); 
    	for(var i=0;i<arrCookie.length;i++){ 
	    	var arr=arrCookie[i].split("="); 
	    	info[$.trim(arr[0])] =  arr[1];
    	} 
    	return info; 
    } 
    function deleteCookie(name){ 
    	var date=new Date(); 
    	date.setTime(date.getTime()-10000); 
    	document.cookie=name+"=v; expires="+date.toGMTString(); 
    } 
</script>
<script type="text/javascript">   
    
   	
    $(function(){
    	var _href = window.location.href+"";
    	if(_href && _href.indexOf('?kickout')!=-1){
    		$("#kickoutDialog .modal-body").html("您的账号在另外地方已登录！");
    		$("#kickoutDialog").modal('show');
    	}
    	$("#loginButton img").bind("click",function(){
    		doSubmit();
    	});
    });
    function doSubmit() {
		var name = $("#name").val();
		var pwd = $("#password").val();
		var remember = $("#remember").is(':checked');
		var autoLogin =$("#autoLogin_input").is(':checked');
		var autoLoginVal ="";
		var rememberVal ="";
		if(remember){
			rememberVal=$("#remember").val();
		}
		if(autoLogin){
			autoLoginVal=$("#autoLogin_input").val();
		}
		//判断是否记住密码
		/* if(remember){
			setCookie(name,pwd,remember,autoLogin);
		}else{
			delCookie("userinfo");
		} */
		
		$.ajax({
			type : "POST",
			url : "/manage/login/login.do",
			data : {
				name:$("#name").val(),
				password:$("#password").val(),
				autoLogin:autoLoginVal,
				remember:rememberVal
			},
			dataType : "json",
			success : function(data) {
				var list=data.success;
				if(list=="1"){
					document.getElementById("errorname").innerHTML="请检查用户名";
				}else if(list=="2"){
					document.getElementById("errorpassword").innerHTML="请检查密码";
				}else if(list=="3"){
					document.getElementById("errorpassword").innerHTML="请检查密码";
					document.getElementById("errorname").innerHTML="请检查用户名";
				}else if(list=="success"){
					window.location.href="${ctx }/login/index.do"; 
				}	
			}
		})
	};
	
	
	document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
        	var b = true;
        	if($("#name").val()==""){
        		document.getElementById("errorname").innerHTML="用户名不能为空";
        		b =  false;
        	}else{
        		document.getElementById("errorname").innerHTML="";
        	}
        	if($("#password").val()==""){
        		document.getElementById("errorpassword").innerHTML="密码不能为空";
        		b =  false;
        	}else{
        		document.getElementById("errorpassword").innerHTML="";
        	}
        	if(!b){
        		return false;
        	}
        	doSubmit();
        }
    };

</script>
</html>