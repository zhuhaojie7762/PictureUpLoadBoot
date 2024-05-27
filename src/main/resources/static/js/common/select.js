var basepath;

function basePath(){
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    //获取项目的basePath   http://localhost:8080/ems/
    var basePath=localhostPath+projectName+"/";
    return projectName;
};
/**
 * 查询下拉数据
 * @author jinlei 
 * 创建时间：2016年9月23日 下午5:49:11
 */
// 创建一个闭包
(function($) {
	basepath = basePath();
	$.fn.initSelect = function(options,val) {
		var opts = $.extend({}, $.fn.initSelect.defaults, options); 
		return this.each(function() {    
			var $this = $(this);
			buildSelect($this, opts);
		});
	}
	
	function buildSelect(targetObj, opts){
		targetObj.append("<option value=''>请选择</option>");
		var url = basepath + "/dict/selectKeyValue?codeType=" + opts.codeType + "&codeName=" + opts.codeName
		$.ajax({
			url:url,
			type: "post",
			async: false,
			dataType : 'json',
			success:function(data){
				$.each(data, function(i, dict){
					targetObj.append("<option value='"+ dict.id + "'>" + dict.val + "</option>");
				})
			}
		});
	}
	
	$.fn.initSelect.defaults = {    
		codeType: '',
		codeName: ''	
	};
	
})(jQuery);


