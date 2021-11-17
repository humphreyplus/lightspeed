$(document).ready(function() {
	
	$("form").submit(function() {
		$(".error-msg").html("");
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
					$("form input[name=email]").val("");
					alert(data.message);
				} else {
					$(".error-msg").html(data.message);
				}
			},
			error: function() {
				alert("邮件发送失败，请重试或联系网站管理员：webmaster@vsgames.cn");
			}
		});
		return false;
	});
	
});