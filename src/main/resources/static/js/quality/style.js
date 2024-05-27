$(function(){
	$("#assign").on("click",function(){
		$(".right").css({
			width: "64vh",
			right: "0"
		});
		$("#right_switch").css({
			backgroundPosition: "center"
		});
		$(".right-turn").css({
			width: "30px",
			right: "-30px"	
		});
		$("#right_transfer").css({
			backgroundPosition: "center"
		});
});
$("#right_switch").on("click",function(){
	$(".right").css({
		width: "30px",
		right: "-30px"	
	});
	$("#right_switch").css({
		backgroundPosition: "5px center"
	});
});

$("#revise").on("click",function(){
		$(".right-turn").css({
			width: "64vh",
			right: "0"
		});
		$("#right_transfer").css({
			backgroundPosition: "center"
		});
		$(".right").css({
			width: "30px",
			right: "-30px"	
		});
		$("#right_switch").css({
			backgroundPosition: "center"
		});
});
$("#right_transfer").on("click",function(){
	$(".right-turn").css({
		width: "30px",
		right: "-30px"	
	});
	$("#right_transfer").css({
		backgroundPosition: "5px center"
	});
});

$("#checkDel").click(function() {
	if($('#checkDel').is(':checked')) {
		$("#deleteReason").removeAttr("disabled");
	}else{
		$("#deleteReason").attr("disabled","true");
	}
});

});



$("#firstpane h5.menu_head").click(function(){
	$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
	$(this).siblings().removeClass("current");
});
$("#secondpane .menu_body:eq(0)").show();
$("#secondpane h5.menu_head").mouseover(function(){
	$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
	$(this).siblings().removeClass("current");
});










