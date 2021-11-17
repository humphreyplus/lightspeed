$(document).ready(function() {
	
	
	
	readMessage($(".message-list li:first"));
	
	$(".message-list li").click(function() {
		readMessage($(this));
	});
	
	function readMessage($msgEle) {
		var submitUrl = $msgEle.attr("data-submit-url");
		var messageId = $msgEle.attr("data-id");
		var messageCountUrl = $msgEle.attr("data-msgcount-url");
		if($msgEle.hasClass("message-unread")) {
			$msgEle.removeClass("message-unread");
			$.ajax({
				url: submitUrl,
				type: "POST",
				data: {id: messageId},
				cache: false,
				dataType: "json",
				success: function(data) {
					if(data.status) {

							$.ajax({
								url: messageCountUrl,
								type: "GET",
								cache: false,
								dataType: "json",
								success: function(data) {
									if(data.status) {
										$(".header-new-msg").html(data.message);
									}
								}
							});
							
					}
				}
			});
		}
		$(".message-list li").removeClass("message-selected");
		$msgEle.addClass("message-selected");
		$(".message-content-main").html($msgEle.find(".message-html").html());
	}
	
});