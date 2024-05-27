
/**
 * 初始化加载
 */
$(function() {
	
	initMenu();
	$("#firstpane h5.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane h5.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	laydate.skin('danlan');//切换皮肤，请查看skins下面皮肤库
	
	
});

/**
 * 初始化菜单
 * @returns
 */
function initMenu(){
	$.ajax({
		url: basepath + "/login/initMenu",
		type: "post",
		async: false,
		dataType : 'json',
		success:function(data){
			var _html = '<h5 class="menu_head first-login"><i class="glyphicon glyphicon-home"></i><a href="#">首页</a></h5>';
			$.each(data,function (index,menu){
				if (menu.pid == 0){
					_html += '<h5 class="menu_head"><i class="'+ menu.icon +'"></i>' + menu.name + '</h5>';
					_html += '<div style="display:none" class="menu_body">';
					$.each(data,function (index,menu2){
						if (menu2.pid==menu.id && menu2.pid !='0'){
							_html += '<a href="'+ basepath + menu2.url + '">' + menu2.name + '</a>';
						}
					});
					_html += '</div>';
				}
			});
			$("#firstpane").html(_html);
		}
	});
	
}

