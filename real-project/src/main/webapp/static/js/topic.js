$(document).ready(function() {
	
	$(".reply-delete-btn").click(function() {
		if(confirm("确定删除？")) {
			var submiturl = $(this).attr("href");
			var id = $(this).attr("data-id");
			$.ajax({
				url: submiturl,
				type: "POST",
				data: {id: id},
				cache: false,
				dataType: "json",
				success: function(data) {
					if(data.status) {
						location.reload();
					}
				},
				error: function() {
					alert("提交发生异常，请重试！");
				}
			});
		}
		return false;
	});
	
	$(".edit-topic-title-btn").click(function() {
		var title = prompt("修改标题",$(".topic-title h1").html());
		if (title!=null && title!="") {
			var baseurl = $(this).attr("data-baseurl");
			var id = $(this).attr("data-id");
			$.ajax({
				url: baseurl + "/my/updatetopic",
				type: "POST",
				data: {id: id, title: title},
				cache: false,
				dataType: "json",
				success: function(data) {
					if(data.status) {
						location.reload();
					}
				},
				error: function() {
					alert("提交发生异常，请重试！");
				}
			});
		}
	});
	$(".edit-topic-btn").click(function() {
		if(confirm("确定删除整个主题及所有回复？")) {
			var baseurl = $(this).attr("data-baseurl");
			var id = $(this).attr("data-id");
			$.ajax({
				url: baseurl + "/my/deletetopic",
				type: "POST",
				data: {id: id},
				cache: false,
				dataType: "json",
				success: function(data) {
					if(data.status) {
						alert("删除成功");
						location.href= baseurl + "/";
					}
				},
				error: function() {
					alert("提交发生异常，请重试！");
				}
			});
		}
		return false;
	});
	
	$(".reply-btn-enable").click(function() {
		if(typeof(ue) === "undefined") {
			alert("请先登录！");
		} else if(ue.hasContents()) {
			var $this = $(this);
			if($this.hasClass("reply-btn-enable")) {
				$this.removeClass("reply-btn-enable");
				$this.html("发送中...");
				var topicId = $(this).attr("data-topicid");
				var html = ue.getContent();
				var submiturl = $(this).attr("data-submiturl");
				$.ajax({
					url: submiturl,
					type: "POST",
					data: {topicId: topicId, html: html},
					cache: false,
					dataType: "json",
					success: function(data) {
						if(data.status) {
							var uri = new URI();
							uri.setSearch({"p": data.message, "t": new Date().getTime()}).fragment("replytopic");;
							location.href = uri.toString();
						} else {
							$this.addClass("reply-btn-enable");
							$this.html("回 复");
							alert("提交失败！");
						}
					},
					error: function() {
						$this.addClass("reply-btn-enable");
						$this.html("回 复");
						alert("提交数据发生异常！");
					}
				});
			}
		} else {
			alert("回复内容不能为空！");
		}
	});
	
	if(typeof(ue) !== "undefined") {
		$(".reply-replyuser").click(function() {
			var username = $(this).attr("data-username");
			ue.setContent('@' + username + '<br>', true);
			ue.focus(true);
		});
	}
});