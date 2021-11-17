$(document).ready(function() {
	
	$(".preview-img img").click(function() {
		if($(this).parent().hasClass("preview-img-small")) {
			$(this).parent().removeClass("preview-img-small");
		} else {
			$(this).parent().addClass("preview-img-small");
		}
	});
	
	$(".pager a").click(function() {
		var page = $(this).attr("data-p");
		var uri = new URI();
		uri.setSearch("p", page).fragment("");;
		location.href = uri.toString();
	});
});