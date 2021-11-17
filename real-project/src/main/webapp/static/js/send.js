$(document).ready(function() {
	
	$("form").submit(function() {
		$(".error-msg").html("");
		if($("textarea").val() == null || $("textarea").val().length == 0) {
			alert("消息不能为空！");
			return false;
		}
		var submitUrl = $(this).attr("action");
		var data = $(this).serialize();
		$.ajax({
			url: submitUrl,
			type: "POST",
			data: data,
			cache: false,
			dataType: "json",
			success: function(data) {
				if(data.status) {
					$("form input[name=username]").val("");
					$("form textarea").val("");
					alert(data.message);
				} else {
					$(".error-msg").html(data.message);
				}
			},
			error: function() {
				alert("消息发送失败，请重试或联系网站管理员：webmaster@vsgames.cn");
			}
		});
		return false;
	});
	
});