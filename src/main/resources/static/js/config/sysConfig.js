/**
 * 初始化加载
 */
$(function() {
	//加载初始化数据
	initData();
	
	$("#warnFlg").click(function() {
		if($('#warnFlg').is(':checked')) {
			$("#warnTime").removeAttr("disabled");
		}else{
			$("#warnTime").attr("disabled","true");
		}
	});
	
	$("#warnTime").blur(function(){
		var warnTime = $("#warnTime").val();
		var numFlg = validate_num(warnTime);
		if(numFlg){
			$("#warnTime_error").html("请输入合法时间");
		}else{
			$("#warnTime_error").html("");
		}
	});
	
	$("#submitConfig").click(function() {
		submitConfig();
	});
	
});

/**
 * 加载初始化数据
 */
function initData(){
	$.ajax({
		url:"initData",
		type: "post",
		dataType : 'json',
		success:function(data){
			var warnFlg = data.warnFlg;
			//是否自动提交
			if(data.commitFlg){
				$("#commitFlg").attr("checked",'true');
			}
			//是否预警
			if(warnFlg){
				$("#warnFlg").attr("checked",'true');
				$("#warnTime").removeAttr("disabled");
				$("#warnTime").val(data.warnTime);
			}else{
				$("#warnTime").val("");
				$("#warnTime").attr("disabled","true");
			}
		}
	});
}

/**
 * 提交系统设置
 */
function submitConfig(){
	var commitFlg = $('#commitFlg').is(':checked');
	var warnFlg = $('#warnFlg').is(':checked');
	var warnTime = "";
	var flg = false;
	if(warnFlg){
		warnTime = $("#warnTime").val();
		var numFlg = validate_num(warnTime);
		if(numFlg){
			$("#warnTime_error").html("请输入合法时间");
			return;
		}
	}
	$.ajax({
		url:"submitConfig",
		data:{
			"commitFlg":commitFlg,
			"warnFlg":warnFlg,
			"warnTime":warnTime
			},
		type: "post",
		dataType : 'json',
		success:function(data){
			if(data.msg == 1){
				initData();
				messagePrompt("系统配置成功！！！");
			}else{
				messagePrompt("系统配置失败。。。" + data.error);
			}
		}
	});
}

/**
 * 校验数字
 */
function validate_num(val){
	val = $.trim(val);
	if(!/^[1-9]*[1-9][0-9]*$/.test(val)){  
        return true;
    }
	return false;
}
