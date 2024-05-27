//获取当月最后一天
function getLastDay(date){
	//debugger;
	 date.setMonth(date.getMonth()+1);
	   date.setDate(0);
	   var year=date.getFullYear();
	   var month=date.getMonth()+1;
	   var day=date.getDate();
	   var result="";
	   if(month>10){
		   result=year+"-"+month+"-"+day;
	   }else{
		   result=year+"-0"+month+"-"+day;
	   }
	    return result;
 }  



/**
 * 获取当前日期的前一天日期
 */
function getYestoday(date){       
    
	var yesterday_milliseconds=date.getTime(); 
    var yesterday = new Date();        
        yesterday.setTime(yesterday_milliseconds);        
         
    var strYear = yesterday.getFullYear();     
    var strDay = yesterday.getDate();     
    var strMonth = yesterday.getMonth()+1;   
    if(strMonth<10)     
    {     
        strMonth="0"+strMonth;     
    }
    if(strDay<10){
    	strDay="0"+strDay;
    	
    }
    datastr = strYear+"-"+strMonth+"-"+strDay;   
    return datastr;   
  } 

//判断是否是数组
function isArray(params){
	   if(params instanceof Array){
		   return true;
	   }else{
		   return false;
	   }
}

/**
 * 获取年月日的数组
 */
function getNewDate(dateStr){
	var DateArry=dateStr.split("-");
    var year=DateArry[0];
        year=parseInt(year);
     var Month=DateArry[1];
       Month=parseInt(Month);
   var Day=DateArry[2];
        Day=parseInt(Day);
        var result={
        		"year":year,
        		"month":Month,
        		"day":Day
        };
        return result;
}
var myChart="";
var checkType="";//日期查询方式
var shapeType="";//展示的图片类型
/**
 * 改变查询方式
 * @returns
 */
function changeStyle(){
    checkType=$("#checkType").val();
	if(typeof(checkType)=="undefined"){
		//alert("id为checkType的节点不存在");
//		$('#oper .modal-body').html("id为checkType的节点不存在");
//		$('#oper').modal('show');
		messagePrompt("id为checkType的节点不存在");
       return false;
	}
     
	checkType = $.trim(checkType);
	if(checkType!="byDay"&&checkType!="byMonth"&&checkType!="byWeek"){
		//alert("被选中查询方式不合法");
//		$('#oper .modal-body').html("被选中查询方式不合法");
//		$('#oper').modal('show');
		messagePrompt("被选中查询方式不合法");
		return false;
	}
	
	if(checkType=="byMonth"){
		$("#beginDay").hide();
		$("#endDay").hide();
		$("#begin").show();
		$("#end").show();
	}
	if(checkType=="byDay"){
		$("#beginDay").show();
		$("#endDay").show();
		$("#begin").hide();
		$("#end").hide();
	}
	return checkType;
};

/**
 * 改变图形类型
 */
function changeType(){
        shapeType=$("#types").val();
		if(typeof(shapeType)=="undefined"){
            messagePrompt("id为types的节点不存在");
			return false;
		}
		shapeType = $.trim(shapeType);
		if(shapeType!="line"&&shapeType!="bar"&&shapeType!="pie"){
		   messagePrompt("被选中查询方式不合法");
            return false;
		}
        beginCheck();
}


function getPieOption(shapeType){
	if(shapeType!="pie"){
		return null;
	}
	var option = {
			//标题
	    title : {
	        text: '里程统计', //正标题
	        x:'center', //标题位置 居中
	        top:'5%' ////距离容器上侧相对距离
	     },
	    tooltip : { //提示框组件
	        trigger: 'item',//触发类型 item:无轴类型图触发
	        // axis是在有轴图触发
	        //提示框内容格式器
	        /**
	         * {a}（系列名称），
	         * {b}（数据项名称），
	         * {c}（数值）, 
	         * {d}（百分比）
	         */
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    toolbox:{ //工具箱 内置有导出图片，数据视图，动态类型切换，数据区域缩放，重置五个工具
	    	show:true,
	    	orient:'horizontal',// 工具栏的布局朝向 'horizontal','vertical'
	    	itemSize:16,//工具栏图标大小
	    	itemGap:15,//工具栏图标间隔
	    	//left:'right',//工具栏离容器左侧距离
	    	right:'20%',//距离容器右侧距离
//	    	right:'center',
	    	feature:{ 
	    		saveAsImage:{ //导出图片
	    			type:'jpg',//导出图片类型
	    			name:'picture',//导出图片名称，默认使用title.text为名称
	    			backgroundColor:'white',//导出后图片背景，默认白色
	    			excludeComponents:['toolbox'],//导出时忽略的组件列表
	    			iconStyle:{ //图片icon样式设置
	    				normal:{
	    					borderColor:'red',//图形描边颜色
	    					borderWidth:1,//描边线宽
	    					borderType:'solid',//描边类型，支持：'solid', 'dashed', 'dotted'
	    				}
	    			},
	    			pixelRatio:2,//保存图片时的分辨率，默认为1
	    		},
	    		dataView:{//数据视图
	        		show:false
	        	},
//	        	 magicType: {//只支持直角坐标系图形切换
//	        	        type: ['line', 'bar', 'stack', 'tiled']
//	        	    }
	    	},
	     },
	    legend: {//图列设置
	        orient: 'vertical',//图例列表的布局朝向 vertical:纵向
	        left: 'left',//左边 图例位置由 left和orient共同决定
	        data:[]
	    },
	    series : [ //系列列表，每个系列通过type决定自己的图表类型
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '55%',//饼图半径
	            /**
	             * 饼图的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标。
	支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
	             */
	            center: ['50%', '60%'],//圆心相对容器的宽度和高度

	            data:[],
	            itemStyle: {//图形样式
	                emphasis: { //图形高亮样式，鼠标悬浮或者图例联动样式
	                	//shadowBlur图形阴影的模糊大小。
	                	//该属性配合 shadowColor 阴影颜色,
	                	//shadowOffsetX, 
	                	//shadowOffsetY 阴影垂直方向上的偏移距离一起设置图形的阴影效果
	                    shadowBlur: 10,
	                    //阴影水平方向上的偏移距离
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	return option;
}


function getOption(shapeType){
	if(shapeType!="line"&&shapeType!="bar"){
		return null;
	}
	var option = {
            title: {
                text: '里程统计',
                	x:'center',
                	top:"5%"//距离容器上侧相对距离
            },
            tooltip : { //提示框组件
                trigger: 'axis',//触发类型 item:无轴类型图触发
                // axis是在有轴图触发
                //提示框内容格式器
                /**
                 * {a}（系列名称），
                 * {b}（数据项名称），
                 * {c}（数值）, 
                 * {d}（百分比）
                 */
                formatter: "{a} <br/>{b} : {c} "
            },
            toolbox:{ //工具箱 内置有导出图片，数据视图，动态类型切换，数据区域缩放，重置五个工具
            	show:true,
            	orient:'horizontal',// 工具栏的布局朝向 'horizontal','vertical'
            	itemSize:16,//工具栏图标大小
            	itemGap:15,//工具栏图标间隔
            	left:'right',//工具栏离容器左侧距离
//            	right:'center',
            	feature:{ 
            		saveAsImage:{ //导出图片
            			type:'jpg',//导出图片类型
            			name:'picture',//导出图片名称，默认使用title.text为名称
            			backgroundColor:'white',//导出后图片背景，默认白色
            			excludeComponents:['toolbox'],//导出时忽略的组件列表
            			iconStyle:{ //图片icon样式设置
            				normal:{
            					borderColor:'red',//图形描边颜色
            					borderWidth:1,//描边线宽
            					borderType:'solid',//描边类型，支持：'solid', 'dashed', 'dotted'
            				}
            			},
            			pixelRatio:2,//保存图片时的分辨率，默认为1
            		},
            		dataView:{//数据视图
                		show:false
                	},
//                	 magicType: { //数据类型切换  stack:堆叠 titled:平滑
//                	        type: ['line', 'bar']
//                	 },
                  },
             },
            legend: {
                
            	data:[]
            },
            xAxis: {
                
            	data:[] //时间
            },
            yAxis: [
                    {
                    name:'数据里程(km)',
                     type : 'value',
                     splitArea : {show : true}
                     }
                    ],
            	
            
            series: [{
                name: '数据里程',
                type: shapeType,
                barWidth : 30,  //设置柱图宽度
                data:[]  //数据
                
            }]
        };
	return option;
   
}



function pieCallBack(result,myChart,shapeType){
	if(myChart==null||typeof(myChart)==undefined||myChart==""){
		return false;
	}
	if(result==null||typeof(result)==undefined||result==""){
		return false;
	}
	if(shapeType!="pie"){
		return false;
	}
	if(!result.success){
		return false;
	}
	myChart.hideLoading();    //隐藏加载动画
    myChart.clear();//清除图形
    myChart.setOption({
    		//标题
 	    title : {
 	        //text: '某站点用户访问来源', //正标题
 	        //subtext: '纯属虚构',//副标题
 	    	text:result.title,
 	        x:'center'  //标题位置 居中
 	    },
 	    tooltip : { //提示框组件
 	        trigger: 'item',//触发类型 item:无轴类型图触发
 	        // axis是在有轴图触发
 	        //提示框内容格式器
 	        /**
 	         * {a}（系列名称），
 	         * {b}（数据项名称），
 	         * {c}（数值）, 
 	         * {d}（百分比）
 	         */
 	        formatter: "{a} <br/>{b} : {c} ({d}%)"
 	    },
 	    toolbox:{ //工具箱 内置有导出图片，数据视图，动态类型切换，数据区域缩放，重置五个工具
 	    	show:true,
 	    	orient:'horizontal',// 工具栏的布局朝向 'horizontal','vertical'
 	    	itemSize:16,//工具栏图标大小
 	    	itemGap:15,//工具栏图标间隔
 	    	left:'right',//工具栏离容器左侧距离
// 	    	right:'center',
 	    	feature:{ 
 	    		saveAsImage:{ //导出图片
 	    			type:'jpg',//导出图片类型
 	    			name:'picture',//导出图片名称，默认使用title.text为名称
 	    			backgroundColor:'white',//导出后图片背景，默认白色
 	    			excludeComponents:['toolbox'],//导出时忽略的组件列表
 	    			iconStyle:{ //图片icon样式设置
 	    				normal:{
 	    					borderColor:'red',//图形描边颜色
 	    					borderWidth:1,//描边线宽
 	    					borderType:'solid',//描边类型，支持：'solid', 'dashed', 'dotted'
 	    				}
 	    			},
 	    			pixelRatio:2,//保存图片时的分辨率，默认为1
 	    		},
 	    		dataView:{//数据视图
 	        		show:false
 	        	},
              },
 	     },
 	    legend: {//图列设置
 	        orient: 'vertical',//图例列表的布局朝向 vertical:纵向
 	        left: 'left',//左边 图例位置由 left和orient共同决定
 	        //图例的数据数组
 	        //data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
 	        data:result.time
 	    },
 	    series : [ //系列列表，每个系列通过type决定自己的图表类型
 	        {
 	            name: '数据里程',
 	            type: 'pie',
 	            radius : '55%',//饼图半径
 	            /**
 	             * 饼图的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标。
 	支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
 	             */
 	            center: ['50%', '60%'],//圆心相对容器的宽度和高度
                data:result.pieResult,
 	            itemStyle: {//图形样式
 	                emphasis: { //图形高亮样式，鼠标悬浮或者图例联动样式
 	                	//shadowBlur图形阴影的模糊大小。
 	                	//该属性配合 shadowColor 阴影颜色,
 	                	//shadowOffsetX, 
 	                	//shadowOffsetY 阴影垂直方向上的偏移距离一起设置图形的阴影效果
 	                    shadowBlur: 10,
 	                    //阴影水平方向上的偏移距离
 	                    shadowOffsetX: 0,
 	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
 	                }
 	            }
 	        }
 	    ]
     });
}



//线状图回调 
function lineCallBack(result,myChart,shapeType){
	if(myChart==null||typeof(myChart)==undefined||myChart==""){
        messagePrompt("图表对象非法");
		return false;
	}
	if(result==null||typeof(result)==undefined||result==""){
		messagePrompt("result非法");
		return false;
	}
	if(shapeType!="line"&&shapeType!="bar"){
		messagePrompt("图形类型指定错误");
		return false;
	}
	if(!result.success){
		messagePrompt(result.message);
	    return false;
	}
	var dataa=result.data;
	if(!isArray(dataa)){
		messagePrompt("返回的数据非法");
		return false;
	}
	if(dataa.length==0){
		messagePrompt(result.message);
		return false;
	}
	myChart.setOption({
		legend: {
           data:result.title
        },
        xAxis: {
           data:result.time
        },
       series: [{
            name: '数据里程',
            type: shapeType,
            data:result.data
            }]
	});
	return true;
}



function getData(myChart,address,Data,shapeType){
	if(myChart==null||typeof(myChart)==undefined||myChart==""){
		return false;
	}
	if(address==null||typeof(address)==undefined||address==""){
		return false;
	}
	if(Data==null||typeof(Data)==undefined||Data==""){
		return false;
	}
    if(shapeType!="line"&&shapeType!="bar"&&shapeType!="pie"){
    	return false;
    }
   //var t=true;
    //myChart.hideLoading(); 
     $.ajax({    //使用JQuery内置的Ajax方法
             type : "post",        //post请求方式
             async : true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
             url:address,
             data:Data,
             dataType : "json",        //返回数据形式为json
             success :function(result){
    	             if(shapeType=="line"|| shapeType=="bar"){
    	                 var t=lineCallBack(result,myChart,shapeType);
    	                  return t;
    	            }else{
    	    	          var t=pieCallBack(result,myChart,shapeType);
    	    	          return t;
    	            }
             },
             error : function(errorMsg) {
            	 console.log("errorMsg:"+errorMsg);
               window.location.href=basePath()+"/500.jsp";
             }
       });
     return true;
}


function checkOther(id){
	//debugger;
	var cityInput=$("#"+id);
	 if(typeof(cityInput)=="undefined"){
        messagePrompt("id为"+id+"的节点不存在");
		return false;
	}
	var city=$(cityInput).val();
	if(typeof(city)=="undefined"||city==null){
		city=" ";
		
	}
	return city;
}

function checkOtherNew(id){
	var projectStatusSelect=$("#"+id);//工程状态
	if(typeof(projectStatusSelect)=="undefined"){

		messagePrompt("id为"+id+"的节点不存在");
		return false;
	}
	var projectStatus=$(projectStatusSelect).find("option:selected").val();
	if(typeof(projectStatus)==undefined || projectStatus==null){
         messagePrompt("下拉框选值错误!");
		return false;
	}else{
		return projectStatus;
	}
}

var nowYear="";
var nowMonth="";
var nowDate="";

//初次进入的方法
$(function(){
   
	//查询下拉数据字典
	$("#resolveFlag").initSelect({
		codeType : "tsk_project_info",
		codeName : "resolve_flag"
	});
	
	
	var div = document.getElementById('main');
	if(div==null){
   		//alert("id为main的图片区域节点不存在");
//   		$('#oper .modal-body').html("id为main的图片区域节点不存在");
//		$('#oper').modal('show');
		messagePrompt("id为main的图片区域节点不存在!");
   		return false;
   	}
	$("#beginDay").hide();
	$("#endDay").hide();
	$("#begin").show();
	$("#end").show();
	//修改查询方式为按月查询
//	 $("#checkType").find("option[text='按月份查询']").attr("selected",true);
	var city="北京";
	$("#city").val(city);

	jieguo=checkOther("projectName");
	var projectName="";
	if(jieguo ||jieguo==""){
		//return false;
		projectName=jieguo;
	}else{
		return false;
	}
	jieguo=checkOtherNew("resolveFlag");
	var projectStatus="";
	if(jieguo ||jieguo==""){
		
		projectStatus=jieguo;
	}else{
		//projectStatus=jieguo;
		return false;
	}
	jieguo=checkOtherNew("taskStatus");
	var taskStatus="";
	if(jieguo ||jieguo==""){
		//return false;
		taskStatus=jieguo;
	}else{
		return false;
	}
	
	//初始化checkType
	checkType="byMonth";//默认按月查询
	shapeType="line";//默认按照折线图显示
    var now=new Date();
     nowYear=now.getFullYear();
    nowMonth=now.getMonth()+1;//月都要加1
    nowDate=now.getDate();
    if(nowMonth>10){
    	beginDate=nowYear+"-"+nowMonth+"-"+"01 00:00:00";
    }else{
    	beginDate=nowYear+"-0"+nowMonth+"-"+"01 00:00:00";
    }
  
    //开始获取当月最后一天
    var dateT=getMonthLastDay(nowYear,nowMonth);
    var monthT=dateT.getMonth()+1;
    var dateTT=dateT.getDate();
    if(monthT<10){
    	monthT="0"+monthT;
    }
    if(dateTT<10){
    	dateTT="0"+dateTT;
    }
    endDate=dateT.getFullYear()+"-"+monthT+"-"+dateTT+" "+"23:59:59";

    var time="";
       if(nowMonth>10){
    	   time=nowYear+"-"+nowMonth;
       }else{
    	   time=nowYear+"-0"+nowMonth;
       }
    var chushibegin=time;
    var chushiend=chushibegin;
    $("#begin").val(chushibegin);
    $("#end").val(chushiend);

      //实例化myChart
        myChart = echarts.init(div);
        myChart.showLoading();
       //日历皮肤指定
       //laydate.skin('molv');
       var option=getOption(shapeType);
       if(option==null){
          //alert("传入的图片类型错误");
//    	   $('#oper .modal-body').html("传入的图片类型错误!");
//   		$('#oper').modal('show');
    	   messagePrompt("传入的图片类型错误!");
		return false;
       }else{
    	   myChart.showLoading();
    	   //debugger;
    	   var result=getData(myChart,"mileData",
        		   {
        	       "city":city,//城市名称
        	       "projectName":projectName,//项目名称
        	       "projectStatus":projectStatus,// 工程状态
        	       "taskStatus":taskStatus,//任务状态
        	       "checkType":checkType,// 查询时间类型
        	        "shapeType":shapeType,// 显示的图形类型
        	        "finalBegin":beginDate,// 查询开始时间
        	        "finalEnd":endDate// 查询结束时间
        		   },
        		   shapeType);
           myChart.hideLoading();
           if(result==false){
        	   //alert("载入图表失败！");
//        	   $('#oper .modal-body').html("载入图表失败!");
//       		$('#oper').modal('show');
        	   messagePrompt("载入图表失败!");
        	  
           }else{
        	   myChart.setOption(option);    //载入图表 
           }
         }
    });

/**
 * 按天查询时，结束时间与今天比较
 * @param endDate
 * @returns {Boolean}
 */
function checkToToday(endDate){
	//debugger;
	//得到今天的
	 var jieZhiDateStr=getYestoday(new Date());
	 var jieZhiDate=jieZhiDateStr.split("-");
	     var jieZhiYear=jieZhiDate[0];
	         jieZhiYear=parseInt(jieZhiYear);
	      var jieZhiMonth=jieZhiDate[1];
        jieZhiMonth=parseInt(jieZhiMonth);
        var jieZhiDay=jieZhiDate[2];
	         jieZhiDay=parseInt(jieZhiDay);
	         var jieZhiRi=new Date(jieZhiYear,jieZhiMonth-1,jieZhiDay,23,59,59);
	    result=endDate.getTime()-jieZhiRi.getTime();
	    if(result>0){
	    	return false;
	    }else{
	    	return true;
	    }
}


/**
 * 得到这个月的最后一天
 */
function getMonthLastDay(year,month){
	//debugger;
	var nextMonthFirstDay=new Date(year,month,1);
    var oneDay=1000*60*60*24;
    var dateT= new Date(nextMonthFirstDay-oneDay);
    return dateT;
}


/**
 * 添加指定天数
 */
function addDate(date,days){ 
	var d=new Date(date); 
	//console.log("d:"+d);
	d.setDate(d.getDate()+days); 
	var month=d.getMonth()+1; 
	var day = d.getDate(); 
	if(month<10){ 
	month = "0"+month; 
	} 
	if(day<10){ 
	day = "0"+day; 
	} 
	var val = d.getFullYear()+"-"+month+"-"+day; 
	return val; 
	}
/**
 * 添加指定月数
 * @param date
 * @param months
 * @returns {String}
 */
	function addMonth(date,months){
		var d=new Date(date);
		d.setMonth(d.getMonth()+months);
		var month=d.getMonth()+1;
		var day=d.getDate();
		if(month<10){ 
	month = "0"+month; 
	} 
	if(day<10){ 
	day = "0"+day; 
	} 
	var val = d.getFullYear()+"-"+month+"-"+day; 
	return val; 
	}
	
	
	
	

/**
 * 检验参数的合法性
 *
 */
function checkParameter(){
	if(checkType!="byDay"&&checkType!="byWeek"&&checkType!="byMonth"){
		messagePrompt("选择的日期类型非法!");
//		$('#oper .modal-body').html("选择的查询类型非法");
//		$('#oper').modal('show');
		//alert("选择的查询类型非法");
        return false;
	}
	if(shapeType!="line"&&shapeType!="bar"&&shapeType!="pie"){
	
		messagePrompt("选择的图形类型非法!");
//		$('#oper .modal-body').html("选择的查询类型非法");
//		$('#oper').modal('show');
		return false;
	}
	var cityInput=$("#city");
	if(typeof(cityInput)=="undefined" ||cityInput==null){
	  //alert("id为"+id+"的节点不存在");
//		$('#oper .modal-body').html("id为"+id+"的节点不存在");
//		$('#oper').modal('show');
		messagePrompt("id为"+id+"的节点不存在");
		return false;
	}
	var city=$(cityInput).val();
	if(typeof(city)=="undefined"||city==null ||city==""){
		//alert("城市为必填项");
		messagePrompt("城市为必填项");
//		$('#oper .modal-body').html("城市为必填项");
//		$('#oper').modal('show');
		return false;
		//return false;
	}
	city=$.trim(city);
	if(city==""){
		//alert("城市为必填项");
//		$('#oper .modal-body').html("城市为必填项");
//		$('#oper').modal('show');
		messagePrompt("城市为必填项");
		return false;
	}

	var begin="";
	var end="";
	if(checkType=="byDay"){//按日期查询
		begin=$("#beginDay").val();
		end=$("#endDay").val();
    }else if(checkType=="byMonth"){//按月查询
		 begin=$("#begin").val();
		 end=$("#end").val();
	}
	var beginDate="";//最终返回的参数
		var endDate="";
	if(begin!=""&&end!=""){//都不是null  1
		   begin=begin.split("-");
		   end=end.split("-");
		   if(!isArray(begin)||begin.length<2){
			   //alert("开始日期非法");
//			   $('#oper .modal-body').html("开始日期非法");
//				$('#oper').modal('show');
			   messagePrompt("开始日期非法");
			   return false;
		   }
		   if(!isArray(end)||end.length<2){
			   //alert("结束日期非法");
//			   $('#oper .modal-body').html("结束日期非法");
//				$('#oper').modal('show');
			   messagePrompt("结束日期非法");
			   return false;
		   }
		    var beginYear=begin[0];
			 beginYear=parseInt(beginYear);
	         var beginMonth=parseInt(begin[1]);
	         beginMonth=parseInt(beginMonth);
	         var beginMMonth=beginMonth;
	         var endYear=parseInt(end[0]);
	         endYear=parseInt(endYear);
	         var endMonth=parseInt(end[1]);
	         endMonth=parseInt(endMonth);
	         var endMMonth=endMonth;
	        if(beginYear>nowYear ||endYear>nowYear ||endYear<beginYear){
                 //alert("开始日期或者结束日期非法");
//	        	  $('#oper .modal-body').html("开始日期或者结束日期非法");
//					$('#oper').modal('show');
	        	messagePrompt("开始日期或者结束日期非法");
		  			return false;
		     }
	         if(checkType=="byDay"){
			   var beginDay=begin[2];
			        beginDay=parseInt(beginDay);
		         var endDay=end[2];
		            endDay=parseInt(endDay);
		        
		         //开始时间
		         beginDate=new Date(beginYear,beginMonth-1,beginDay);
		         //结束时间
		   	     endDate=new Date(endYear,endMonth-1,endDay);
		   	     //判断开始时间是否小于结束时间，及时间间隔
		   	  var result=(endDate.getTime())-(beginDate.getTime());
		   	  var minuteDate=parseInt(result / 1000 / 60 / 60 / 24) ;
		   	 if(minuteDate>31){
		   		 //alert("最长间隔31天");
//		   	  $('#oper .modal-body').html("最长间隔31天");
//				$('#oper').modal('show');
		   		messagePrompt("最长间隔31天");
		   		  return false;
		   	 }
		   	 if(minuteDate<0){
		   		 //alert("开始时间不能大于结束时间");
//		   		$('#oper .modal-body').html("开始时间不能大于结束时间");
//				$('#oper').modal('show');
		   		messagePrompt("开始时间不能大于结束时间");
		   		return false;
		   	 }
		   	  var resultNew=checkToToday(endDate);
		   	  if(resultNew==false){
		   		//alert("最长查询时间为今天");
//		   		$('#oper .modal-body').html("最长查询时间为今天");
//				$('#oper').modal('show');
		   		messagePrompt("查询截止时间最长为今天");
		   		  return false;
		   	  }
		   	 
		   	  if(beginMonth<10){
		   		  beginMonth="0"+beginMonth;
		   	  }
		   	  if(beginDay<10){
		   		beginDay="0"+beginDay; 
		   	  }
		   	 beginDate=beginYear+"-"+beginMonth+"-"+beginDay+" "+"00:00:00";
		   	 if(endMonth<10){
		   		 endMonth="0"+endMonth;
		   	 }
		   	 if(endDay<10){
		   		 endDay="0"+endDay;
		   	 }
		   	endDate=endYear+"-"+endMonth+"-"+endDay+" "+"23:59:59";
            
	       }else if(checkType=="byMonth") {
			   var len = (endYear - beginYear) * 12 + (endMMonth - beginMMonth);
		    	 if(len>12){
		    		//alert("最长间隔12个月");
//		    			$('#oper .modal-body').html("最长间隔12个月");
//						$('#oper').modal('show');
		    		 messagePrompt("最长间隔12个月");
		    		return false;
		    	 }
		    	 if(len<0){
		    	    //alert("开始时间不能大于结束时间");
//		    			$('#oper .modal-body').html("开始时间不能大于结束时间");
//						$('#oper').modal('show');
		    		 messagePrompt("开始时间不能大于结束时间");
		    		return false;
		    	 }
		    	 len=(nowYear-endYear)*12+(nowMonth-endMMonth);
		         if(len<0){
		            //alert("最长查询到本月");
//		        	 $('#oper .modal-body').html("最长查询到本月");
//						$('#oper').modal('show');
		        	 messagePrompt("最长查询到本月");
		        	return false;
		         }
		         if(beginMonth<10){
		        	 beginMonth="0"+beginMonth;
		         }
		         beginDate=beginYear+"-"+beginMonth+"-"+"01"+" "+"00:00:00";
		         var dateT=getMonthLastDay(endYear,endMMonth);
		        var monthT=dateT.getMonth()+1;
		        var dateTT=dateT.getDate();
		        if(monthT<10){
		        	monthT="0"+monthT;
		        }
		        if(dateTT<10){
		        	dateTT="0"+dateTT;
		        }
		        endDate=dateT.getFullYear()+"-"+monthT+"-"+dateTT+" "+"23:59:59";
		         
		     }
	
	   }else if(begin==""&&end==""){//都是空串  2
		  var nowMMonth=nowMonth;
		  var nowDDate=nowDate;
		  if(nowMMonth<10){
			  nowMMonth="0"+nowMMonth;
		  }
		  if(nowDDate<10){
			  nowDDate="0"+nowDDate;
		  }
		   endDate=nowYear+"-"+nowMMonth+"-"+nowDDate+" "+"23:59:59";
		  
		   //当前时间
		   var now=new Date();
		   var beginM="";
		   if(checkType=="byDay"){//向前推30天
			  beginM=addDate(now,-29);
		   }else if(checkType="byMonth"){//向前推12月
			  beginM=addMonth(now,-12);
			  beginM=beginM.split("-");
			   beginM=beginM[0]+"-"+beginM[1]+"-01";
		   }
		   beginDate=beginM+" "+"00:00:00";
	   }else if(begin==""&&end!=""){//开始为空结束不是空  3
		   end=end.split("-");
		   if(!isArray(end)||end.length<2){
			   //alert("结束日期非法");
//			   $('#oper .modal-body').html("结束日期非法");
//				$('#oper').modal('show');
			   messagePrompt("结束日期非法");
			   return false;
		   }
		   var endYear=end[0];
	       endYear=parseInt(endYear);
	         if(endYear>nowYear){
                 //alert("结束日期非法");
//	        	 $('#oper .modal-body').html("结束日期非法");
//					$('#oper').modal('show');
	        	 messagePrompt("结束日期非法");
		  			return false;
		  	  }
	         var endMonth=end[1];
	              endMonth=parseInt(endMonth);
	         if(checkType=="byDay"){//向前推29天
	        	 var endDay=end[2];
	        	 endDay=parseInt(endDay);
	        	 var endDay1=new Date(endYear,endMonth-1,endDay);//结束时间
	        	 var resultNew=checkToToday(endDay1);
	        	 if(resultNew==false){
	        		 //alert("最长查询时间为今天");
//	        		 $('#oper .modal-body').html("最长结束时间为今天");
//						$('#oper').modal('show');
	        		 messagePrompt("最长结束时间为今天");
			   		  return false;
			   	  }
	        	 if(endMonth<10){
	        		 endMonth="0"+endMonth;
	        	 }
	        	 if(endDay<10){
	        		 endDay="0"+endDay;
	        	 }
	        	 endDate=endYear+"-"+endMonth+"-"+endDay+" "+"23:59:59";
                 beginM=addDate(endDay1,-29);//开始时间
	        	 //开始拼接合法的当前时间
	        	 beginDate=beginM+" "+"00:00:00";
	        	 
	         }else if(checkType="byMonth"){//与当前时间进行比较
	        	 len=(nowYear-endYear)*12+(nowMonth-endMonth);
		         if(len<0){
		            //alert("最长查询到本月");
//		        	 $('#oper .modal-body').html("最长查询到本月");
//						$('#oper').modal('show');
		        	 messagePrompt("最长查询到本月");
		        	return false;
		         }
		         endDate=new Date(endYear,endMonth-1,1);//结束时间
		         beginM=addMonth(endDate,-11);
		         beginM=beginM.split("-");
				 beginM=beginM[0]+"-"+beginM[1]+"-01";
		         beginDate=beginM+" "+"00:00:00";//开始
		 		 endDate=getLastDay(endDate)+" "+"23:59:59";
		  }
		   
	   }else {//开始不为空结束为空  4
		   //debugger;
		   begin=begin.split("-");
		   if(!isArray(begin)||begin.length<2){
//			   $('#oper .modal-body').html("开始日期非法");
//				$('#oper').modal('show');
			   messagePrompt("开始日期非法");
			   //alert("开始日期非法");
			   return false;
		   }
		   var beginYear=begin[0];
	       beginYear=parseInt(beginYear);
	         if(beginYear>nowYear){
                 //alert("开始日期非法");
//	        	 $('#oper .modal-body').html("开始日期非法");
//					$('#oper').modal('show');
	        	 messagePrompt("开始日期非法");
		  			return false;
		  	  }
	         //开始时间不能大于当前时间
	         var beginMonth=begin[1];
             beginMonth=parseInt(beginMonth);
             if(checkType=="byDay"){//向后推30天
            	 var beginDay=begin[2];
	        	 beginDay=parseInt(beginDay);
	        	 var beginDay1=new Date(beginYear,beginMonth-1,beginDay);//开始时间
	        	 var resultNew=checkToToday(beginDay1);
	        	 if(resultNew==false){
	        		 //alert("最长查询时间为今天");
//	        		 $('#oper .modal-body').html("最长查询时间为今天");
//						$('#oper').modal('show');
	        		 messagePrompt("最长查询时间为今天");
			   		  return false;
			   	  }
	        	 if(beginMonth<10){
	        		 beginMonth="0"+beginMonth;
	        	 }
	        	 if(beginDay<10){
	        		beginDay="0"+beginDay; 
	        	 }
	        	 beginDate=beginYear+"-"+beginMonth+"-"+beginDay+" "+"00:00:00";
                 //开始时间向前加30天
	        	 endM=addDate(beginDay1,29);//结束时间
	        	 endMArry=endM.split("-");//数组
	        	 var endMArrayYear=endMArry[0];
	        	      endMArrayYear=parseInt(endMArrayYear);
	        	 var endMArrayMonth=endMArry[1];
	        	 endMArrayMonth=parseInt(endMArrayMonth);
	        	 var endMArrayDate=endMArry[2];
	        	 endMArrayDate=parseInt(endMArrayDate);
	        	 //加后的时间
	        	 var endDayNew=new Date(endMArrayYear,endMArrayMonth-1,endMArrayDate);
	        	 var resultNew=checkToToday(endDayNew);
	        	 if(resultNew==false){
                    if(nowMonth<10){
	        			 nowMonth="0"+nowMonth;
	        		 }
	        		 if(nowDate<10){
	        			 nowDate="0"+nowDate;
	        		 }
	        		 endDate=nowYear+"-"+nowMonth+"-"+nowDate+" "+"23:59:59";
			   	  }else{
                      if(endMArrayMonth<10){
			   			  endMArrayMonth="0"+endMArrayMonth;
			   		  }
			   		  if(endMArrayDate<10){
			   			 endMArrayDate="0"+endMArrayDate;
			   		  }
			   		endDate=endMArrayYear+"-"+endMArrayMonth+"-"+endMArrayDate+" "+"23:59:55";
			   	  }
             }else if(checkType="byMonth"){//向后推12个月
            	 //debugger;
            	 len=(nowYear-beginYear)*12+(nowMonth-beginMonth);
		         if(len<0){
		            //alert("最长查询到本月");
//		            $('#oper .modal-body').html("最长查询到本月");
//					$('#oper').modal('show');
		        	 messagePrompt("最长查询到本月");
		        	return false;
		         }
		         if(beginMonth<10){
			        	beginDate=beginYear+"-0"+beginMonth+"-"+"01"+" "+"00:00:00";
			         }else{
			        	beginDate=beginYear+"-"+beginMonth+"-"+"01"+" "+"00:00:00";
			         }
		         var beginDate1=new Date(beginYear,beginMonth-1,1);//开始时间
		         endM=addMonth(beginDate1,11);//结束时间
		         var endMArry=endM.split("-");//结束
		         var endMArryYear=endMArry[0];
		         endMArryYear=parseInt(endMArryYear);
		         var endMArrayMonth=endMArry[1];
		         endMArrayMonth=parseInt(endMArrayMonth);
		         //与当前时间进行比较
		         len=(nowYear-endMArryYear)*12+(nowMonth-endMArrayMonth);
		         if(len<0){
		            //结束时间为当前月最后一天
		        	 var endDD=getMonthLastDay(nowYear,nowMonth);//日期对象
		        	    var endDDYear=endDD.getFullYear();
		        	    var endDDMonth=endDD.getMonth()+1;
		        	    var endDDDate=endDD.getDate();
		        	    if(endDDMonth<10){
		        	    	endDDMonth="0"+endDDMonth;
		        	    }
		        	    if(endDDDate<10){
		        	    	endDDDate="0"+endDDDate;
		        	    }
                        endDate=endDDYear+"-"+endDDMonth+"-"+endDDDate+" "+"23:59:59";
	        	    }else{
		        	 endM=endM.split("-");
		        	 var year=endM[0];
		        	 year=parseInt(year);
		        	 var month=endM[1];
		        	 month=parseInt(month);
		        	 var DateTT=getMonthLastDay(year,month);
		        	 var DateTTMonth=DateTT.getMonth()+1;
		        	 var DateTTDay=DateTT.getDate();
		        	 if(DateTTMonth<10){
		        		 DateTTMonth="0"+DateTTMonth;
		        	 }
		        	 if(DateTTDay<10){
		        		 DateTTDay="0"+DateTTDay;
		        	 }
                     endDate=DateTT.getFullYear()+"-"+DateTTMonth+"-"+DateTT.getDate()+" "+"23:59:59";
		        }
             }
	   }
	//回写beginDate，endDate
	$("#finalEnd").val(endDate);
	$("#finalBegin").val(beginDate);
	var resultP={
			"city":city,
			"checkType":checkType,
			"shapeType":shapeType,
			"finalBegin":beginDate,
			"finalEnd":endDate
	};

	resultNew=checkOther("projectName");//工程名称
	if(resultNew || resultNew==""){
		resultP.projectName=resultNew;
	}else{
		return false;
	}
	resultNew=checkOtherNew("resolveFlag");
	if(resultNew ||resultNew==""){
		resultP.projectStatus=resultNew;
	}else{
		return false;
	}
	resultNew=checkOtherNew("taskStatus");//任务状态

	if(resultNew ||resultNew==""){
		resultP.taskStatus=resultNew;
		
	}else{
		return false;
	}
    return resultP;
}

/**
 * 导出
 */
$("#export").click(function(){
	var result=checkParameter();
	if(result!=false&&typeof(result)!=undefined){
		$("#prodForm").submit();//提交表单
	}
	return result;
});

//开始查询
function beginCheck(){
	
	var parameters=checkParameter();
	
	if(parameters==false){
		return false;
	}
     //开始加载图表
	myChart.showLoading();
     //日历皮肤指定
     //laydate.skin('molv');
	 var option=null;
	 if(shapeType=="line"||shapeType=="bar"){
		  option=getOption(shapeType);
	 }else{
		 option=getPieOption(shapeType);
	 }
	 
	 if(option==null){
		 
		 myChart.hideLoading();
//		 $('#oper .modal-body').html("图表载入失败!");
//			$('#oper').modal('show');
		 messagePrompt("图表载入失败");
		 return false;
	 }else{
		 var result=false;
		 var datas={
				"city":parameters.city,//城市
	      	       "projectName":parameters.projectName,//项目名称
	    	       "projectStatus":parameters.projectStatus,//工程状态
	    	       "taskStatus":parameters.taskStatus,//任务状态
	    	       "checkType":parameters.checkType,//日期查询方式
	    	        "shapeType":parameters.shapeType,//图形类型
	    	        "finalBegin":parameters.finalBegin,//开始时间
	    	        "finalEnd":parameters.finalEnd //结束时间
		};
        result = getData(myChart,"mileData",datas,shapeType);
		 if(result){
			 myChart.hideLoading();
			  myChart.setOption(option);    //载入图表
		  }else{
			  myChart.hideLoading();
			  //alert("图表载入失败");
//			  $('#oper .modal-body').html("图表载入失败");
//				$('#oper').modal('show');
			  messagePrompt("图表载入失败");
		  }
	 }
  }