/**
 * 初始化加载
 */
$(function() {
	$("#sourceUrl").blur(function(){
		getProjectName(this.value);
	});
	
	//初始化数据
	initData();
	
	$("#projectEditForm").validate({
		rules:{
			sourceUrl:{
				required:true
			},
			projectName:{
				required:true
			},
			city:{
				maxlength:10
			},
			type:{
				maxlength:10
			},
			resolveFlag:{
				maxlength:20
			},
			outMileage:{
				number:true,
				decimals:true
			},
			factMileage:{
				number:true,
				decimals:true
			},
			recordNum:{
				digits:true
			},
			resolveUserName:{
				maxlength:20
			},
			solutionMachine:{
				maxlength:250
			},
			resolveMethod:{
				maxlength:64
			},
			qcUserName:{
				maxlength:20
			},
			remark:{
				maxlength:255
			}
		},
		submitHandler: function(form) {
			$(form).ajaxSubmit({
		        success: function (data) {
		        	var str = eval('(' + data + ')');
		        	var msg = str.msg;
					if(msg == 0){
						messagePrompt("添加错误，请重新添加！！！");
					}else if(msg == 1){
						messagePrompt("资料编辑成功！！！");
						setInterval("location.href = 'viewProjectList'",1000)
					}else if(msg == 2){
						messagePrompt("上传文件为空！！！");
					}else if(msg == 3){
						messagePrompt("excel导入失败，请检查格式！！！");
					}else if(msg == 4){
						messagePrompt("工程名格式错误！！！");
					}else if(msg == 5){
						messagePrompt("rpp解析错误！！！");
					}else if(msg == 6){
						messagePrompt("轨迹抽稀解析错误！！！");
					}
		        }
			});
	    }
	});
	jQuery.validator.addMethod("decimals", function(value, element) {         
        return this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);         
    }, "精确到两位小数点");
});

/**
 * 资料编辑
 * @returns
 */
function projectEdit(){
	$.ajax({
		url:"projectEdit",
		data:$('#projectEditForm').serialize(),
		type: "post",
		dataType : 'json',
		success:function(data){
			var msg = data.msg;
			if(msg == 0){
				messagePrompt("添加错误，请重新添加！！！");
			}else if(msg == 1){
				messagePrompt("资料编辑成功！！！");
				location.href = "viewProjectList";
			}else if(msg == 2){
				messagePrompt("上传文件为空！！！");
			}else if(msg == 3){
				messagePrompt("excel导入失败，请检查格式！！！");
			}else if(msg == 4){
				messagePrompt("工程名：" + data.error + "  有误！！！");
			}else if(msg == 5){
				messagePrompt(data.error + "：rpp解析错误！！！");
			}else if(msg == 6){
				messagePrompt(data.error + "：轨迹抽稀解析错误！！！");
			}
		}
	});
}

/**
 * 获取工程名
 * @returns
 */
function getProjectName(url){
	url = $.trim(url);
	if(url != ""){
		$.ajax({
			url:"getProjectName",
			data:{"sourceUrl": url},
			type: "post",
			dataType : 'json',
			success:function(data){
				$("#projectName").val(data.projectName);
				$("#city").val(data.city);
			}
		});
	}
}

/**
 * 初始化数据
 */
function initData(){
	$("#returnTime").val(fomart($("#returnTime_hid").val()));
	$("#trailEndTime").val(fomart($("#trailEndTime_hid").val()));
	$("#pointCloudStartTime").val(fomart($("#pointCloudStartTime_hid").val()));
	$("#pointCloudEndTime").val(fomart($("#pointCloudEndTime_hid").val()));
	$("#qcStartTime").val(fomart($("#qcStartTime_hid").val()));
	$("#qcEndTime").val(fomart($("#qcEndTime_hid").val()));
	$("#indoorworkCommitTime").val(fomart($("#indoorworkCommitTime_hid").val()));
}

/**
 * 时间格式化(YYYY-MM-DD hh:mm:ss)
 */
function fomart(val){
	if(val == null || val == ""){
		return "";
	}else{
		var date = new Date(val);
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		if(month < 10){
			month = "0" + month;
		}
		var d = date.getDate();
		if(d < 10){
			d = "0" + d;
		}
		var h = date.getHours();
		if(h < 10){
			h = "0" + h;
		}
		var m = date.getMinutes();
		if(m < 10){
			m = "0" + m;
		}
		var s = date.getSeconds();
		if(s < 10){
			s = "0" + s;
		}
		return year + "-" + month + "-" + d + " " + h + ":" + m + ":" + s;
	}
}
