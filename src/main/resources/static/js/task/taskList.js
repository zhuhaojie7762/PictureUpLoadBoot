
$(function(){
	//使用jqGrid发起请求
	pageInit();
});


//自动执行的查询方法开始
function pageInit(){
	$("#gridTable").jqGrid({
		url : 'getList',//请求的url
		mtype:'GET',//ajax提交方式 GET或者POST，默认GET  
		datatype : "json",    //返回的数据类型
		styleUI : 'Bootstrap',
		multiselect : true, //可多选
		rownumbers: true, //显示行号
		rownumWidth:'50', //行号宽度
		width: $(".middle").width()*0.99, //数据宽度
		//数据列名称
        colNames:['Id','任务编号','TaskID','表名','记录ID','加锁任务编号','加锁时间'],
        /**
         * 常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；
         * width 列宽度；align 对齐方式；
         * sortable 是否可以排序
         * hidden 是否隐藏
         * 居左
         */
        colModel:[
                  {name:'id',index:'id',align:'left',width:"50px",hidden:true},
                {name:'procInstId',index:'procInstId',align:'left',width:"50px"},//任务编号
                {name:'taskId',index:'taskId',align:'left',width:"50px"},
                {name:'tableName',index:'tableName',align:'left',width:"50px"},//表名
                {name:'recordId',index:'recordId',align:'left',sortable:false,width:"50px"},//记录ID
                {name:'lockProcInstId',index:'lockProcInstId',align:'left',sortable:false,width:"50px"},//加锁任务编号
                {name:'createTime',index:'createTime',align:'left', width:"50px",formatter: function (cellval, opts) {
                	return dateFomart(cellval);
                	},width:"50px"}//加锁时间
                 ],
        sortname:'id',//按照哪个字段进行排序
        sortorder:'desc',//排序规则
        viewrecords:true,//显示总记录数
        rowNum:10,//在grid上显示记录条数
        rowList:[10,20,30],//可选的记录调试
        /**
         * 定义翻页用的导航栏，必须是有效的html元素。
         * 翻页工具栏可以放置在html页面任意位置
         */
        pager:"#gridPager",
        caption: "",//表格名称
        jsonReader : {  //描述json 数据格式的数组
			root : "list",
			page : 'pageNum', // 页码
			total : 'pages', // 总页数
			records : 'total', // 总记录数
			recordpos : 'left',//总记录显示的位置
			repeatitems : false,
			cell : "row",
			id : "id",
			userdata : "userdata",
			subgrid : {
				root : "rows",
				repeatitems : true,
				cell : "cell"
			},
		},
		loadError:function(xhr,status,error){//加载失败时的处理函数
			/**
			 * 如果请求服务器失败则调用此方法。x
			 * hr：XMLHttpRequest 对象；status：错误类型，
			 * 字符串类型；error：exception对象
			 */
			console.log("status:"+status);
			console.log("error:"+error);
			window.location.href=basePath()+"/500.jsp";
			
		   }
	     });
	//此方法用来激活配置
	$("#gridTable").navGrid('#pager2',{edit:false,add:false,del:false});
	//设置第一列标题
	$("#gridTable").jqGrid('setLabel',0, '序号', 'labelstyle'); 
}
//自动执行的查询方法结束


/**
 * var rowId = $('#gridTable').jqGrid('getGridParam', 'selrow');
  var cellval = $('#gridTable').jqGrid('getCell', rowId, 'deploymentId');

var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
 * 在选择之前，使之清除掉之前的选项
 */
function beforeSelectRow(){
	
    $("#gridTable").jqGrid('resetSelection');  
    return (true);  
}
/**
 * 搜索操作
 */
function search() {
	var procId=$("#procId").val();//获取输入的(任务编号)流程实例id
	var taskId=$("#taskId").val();//获取任务id
	var tableName=$("#tableName").val();//表名
	var recordId=$("#recordId").val();//记录id
	if(typeof(procId)=="undefined" || procId==null){
		messagePrompt('任务编号非法! procId:'+procId);
		return false;
	}
	if(typeof(taskId)=="undefined" || taskId==null){
		messagePrompt('TaskID非法! taskId:'+taskId);
		return false;
	}
	if(typeof(tableName)=="undefined" || tableName==null){
		messagePrompt('表名非法! tableName:'+tableName);
		return false;
	}
	if(typeof(recordId)=="undefined" || recordId==null){
		messagePrompt('记录ID非法! recordId:'+recordId);
		return false;
	}
	procId=$.trim(procId);
	taskId=$.trim(taskId);
	tableName=$.trim(tableName);
	recordId=$.trim(recordId);
	console.log("任务编号:"+procId+",TaskID:"+taskId+",表名:"+tableName+",记录id:"+recordId);
	//开始重新加载数据
	$("#gridTable").jqGrid('setGridParam',{
		page:1,//搜索后默认显示
		postData:{
           "procInstId":procId,//流程实例id
           "taskId":taskId, //任务id
           "tableName":tableName,//表名
           "recordId":recordId  //记录id
        },
	}).trigger('reloadGrid'); 
}




/**
 * 任务解锁*/
function taskUnlock(){
	//debugger;
	//获取选择的列id
	//var rowId = $('#gridTable').jqGrid('getGridParam', 'selrow');
	//获取全选的rowIds
	var rowIds = jQuery("#gridTable").jqGrid('getGridParam', 'selarrrow');
	if(typeof(rowIds)=="undefined" || rowIds==null){
		messagePrompt('记录ID非法! recordId:'+recordId);
		return false;
	}
	var leng=rowIds.length;
	if(leng==0 ||leng<0){
		messagePrompt('请至少选择一条记录!');
		return false;
	}
	
    console.log("rowIds:"+rowIds);
    //发送ajax请求
    $.ajax({    //使用JQuery内置的Ajax方法
        type : "post",        //post请求方式
        async : true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url:"toUnlock",
        data:{"rowIds":rowIds},
        dataType : "json",        //返回数据形式为json
        traditional:true,//traditional 为true阻止Jquery深度序列化参数对象
        success :function(result){
       
        	if(result==null||typeof(result)==undefined||result==""){
        		messagePrompt("result非法");
        		//setTimeout(window.location.reload(),5000);
        		return false;
        	}
        	var success=result.success;
        	if(success==false){
        		messagePrompt(result.message);
        		//setTimeout(window.location.reload(),5000);
        		return false;
        	}
        	if(success==""){
        		messagePrompt(result.message);
        		//setTimeout(window.location.reload(),5000);
        		return false;
        	}
        	if(typeof(success)=="undefined"){
        		messagePrompt(result.message);
        		//setTimeout(window.location.reload(),5000);
        		return false;
        	}
        	
        	//五秒后刷新页面
        	//setTimeout(window.location.reload(),5000);
        	//刷新表格
        	$('#gridTable').trigger('reloadGrid');
        	messagePrompt(result.message);
        	//window.location.reload();
        	return true;
        },
        error : function(errorMsg) {
       	 console.log("errorMsg:"+errorMsg);
          window.location.href=basePath()+"/500.jsp";
        }
  });
}
