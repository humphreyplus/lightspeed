$(document).ready(function() {
	
	$("#select-gw2homeland" + $("#select-gw2area").val()).show();
	$("#select-gw2homeland" + $("#select-gw2area").val()).attr("name", "gw2HomelandId");
	
	$("#select-gw2area").change(function() {
		$(".select-gw2homeland").hide();
		$(".select-gw2homeland").removeAttr("name");
		$("#select-gw2homeland" + $(this).val()).show();
		$("#select-gw2homeland" + $(this).val()).attr("name", "gw2HomelandId");
	});
	
	$("form").submit(function() {
		var gameNickname = $("input[name=gameNickname]").val();
		if(gameNickname != null && gameNickname != '') {
			if(!gameNickname.match(/^.*\.[0-9]{4}$/)) {
				alert("游戏昵称必须以四位数字结尾，例如：犀利哥.9876");
				return false;
			}
		}
	});
});