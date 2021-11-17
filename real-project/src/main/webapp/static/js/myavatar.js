
var position, boundx, boundy;

function updatePreview(c) {
	position = c;
	if (parseInt(c.w) > 0) {
		var rx = 100 / c.w;
		var ry = 100 / c.h;

		$(".avatar-preview img").css({
			width : Math.round(rx * boundx) + 'px',
			height : Math.round(ry * boundy) + 'px',
			marginLeft : '-' + Math.round(rx * c.x) + 'px',
			marginTop : '-' + Math.round(ry * c.y) + 'px'
		});
	}
}

$(document).ready(function() {
	
	var uploader = WebUploader.create({
		auto: true,
		swf: 'http://cdn.bootcss.com/webuploader/0.1.1/Uploader.swf',
		server: $(".avatar-uploader").attr("data-upload-url"),
		pick: '.avatar-uploader',
		accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	});
	uploader.on('uploadSuccess', function(file, response) {
		if(response.status) {

			$(".avatar-setter").html("<img src='" + response.message + "'>");
			$(".avatar-preview").html("<img src='" + response.message + "'>");
			$(".avatar-setter img").Jcrop({
				onChange : updatePreview,
				onSelect : updatePreview,
				allowSelect: false,
				aspectRatio : 1
			}, function() {
				var bounds = this.getBounds();
				boundx = bounds[0];
				boundy = bounds[1];
				this.setSelect([ 0, 0, 100, 100]);
			});
			
		} else {
			alert(response.messgae);
		}
	});
	uploader.on('uploadError', function(file) {
		alert('上传出错');
	});
	
	

	$(".avatar-submit").click(function() {
		if($(".avatar-preview img").length > 0) {
			var image = $(".avatar-preview img").attr("src");
			var imageFileName = image.substr(image.lastIndexOf("/"));
			var submiturl = $(this).attr("data-submit-url");
			$.ajax({
				url: submiturl,
				type: "POST",
				data: {fileName: imageFileName, x: position.x, y: position.y, h: position.h},
				cache: false,
				dataType: "json",
				success: function(data) {
					if(data.status) {
						location.href = data.message;
					} else {
						alert("提交失败！");
					}
				},
				error: function() {
					alert("提交数据发生异常！");
				}
			});
		} else {
			alert("请选择图片！");
		}
	});
});