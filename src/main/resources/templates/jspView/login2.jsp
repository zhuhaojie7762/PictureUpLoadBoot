<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="/jspView/common/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${title}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 引入公共页面 -->
<%@ include file="/jspView/common/common.jsp"%>
</head>
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="${ctxStatic}/static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"> <i><img height="37"
									src="${ctxStatic}/static/login/user.png" /></i>
							</span><input type="text" name="loginname" id="loginname" value=""
								placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"> <i><img height="37"
									src="${ctxStatic}/static/login/suo.png" /></i>
							</span><input type="password" name="password" id="password"
								placeholder="请输入密码" value="" />
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;">
							<i><img src="${ctxStatic}/static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:16px; padding-top:0px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="codeImg" alt="点击更换"
								title="点击更换" src="" /></i>
						</div>

						<span class="pull-right" style="padding-right:3%;"><a
							href="javascript:quxiao();" class="btn btn-success">取消</a></span> <span
							class="pull-right"><a onclick="severCheck();"
							class="flip-link btn btn-info" id="to-recover">登录</a></span>

					</div>
				</div>

			</form>


			<!--  div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © FH2100</span></font>
				</div>
			</div-->
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div data-src="${ctxStatic}/static/login/images/banner_slide_01.jpg"></div>
			<div data-src="${ctxStatic}/static/login/images/banner_slide_02.jpg"></div>
			<div data-src="${ctxStatic}/static/login/images/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>
	<script src="${ctxStatic}/static/login/js/camera.min.js"></script>
	<script src="${ctxStatic}/static/login/js/templatemo_script.js"></script>
	<!-- 引入当前页面js -->
	<script type="text/javascript" src="${ctxStatic}/js/login2.js"></script>
</body>
</html>