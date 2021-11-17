var submitNow = false;
$(document).ready(function() {
	
	window.onbeforeunload = function() {
		return "您输入的内容尚未保存，确定离开此页面吗？";
	};
	
	$(".section-intro-" + $("form select").val()).show();
	$("form select").change(function() {
		$(".section-intro").hide();
		$(".section-intro-" + $(this).val()).show();
	});
	
	$("#content form").submit(function() {
		if(!submitNow) {
		
			$("#section-error-msg").html("");
			$("#title-error-msg").html("");
			
			if(!ue.hasContents()) {
				alert("内容不能为空");
				return false;
			}
			
			if($("#input-section").length > 0 || $("#input-title").length > 0) {
			
				if($("#input-section").val() === null || $("#input-section").val() === '') {
					$("#section-error-msg").html("请选择版块！");
					return false;
				}
				
				if($("#input-title").val() === null || $("#input-title").val() === '') {
					$("#title-error-msg").html("标题不能为空！");
					return false;
				}
				
				if($("#input-title").val().length < 4) {
					$("#title-error-msg").html("标题太短！");
					return false;
				}
				
				if($("#input-title").val().length > 100) {
					$("#title-error-msg").html("标题太长！");
					return false;
				}
			}
			
			submitNow = true;
			$("#content form input[type=submit]").val("发送中...");
			
			if($("#input-section").length > 0 || $("#input-title").length > 0) {
				var content = ue.getContent();
				var $content = $(content);
				var $imgs = $content.find("img");
				for(var i = 0; i < $imgs.length; i++) {
					var img = $imgs.eq(i).attr("src");
					if(img.indexOf("http://7xi3wi.com1.z0.glb.clouddn.com/upload/") === 0) {
						$(this).append("<input type='hidden' name='image' value='" + img + "'>");
						break;
					}
				}
			}
			window.onbeforeunload = function() {
			};
		}
		
	});
	
});