<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 用户设置</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/user.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/jquery-jcrop/0.9.12/css/jquery.Jcrop.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/webuploader/0.1.1/webuploader.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/jquery-jcrop/0.9.12/js/jquery.Jcrop.min.js"></script>
	<script src="http://cdn.bootcss.com/webuploader/0.1.1/webuploader.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/myavatar.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<div class="user-basic">
			<div class="avatar">
				<img src="<c:out value="${user.avatar}" />">
			</div>
			<div class="basic-info">
				<div class="username"><c:out value="${user.username}" /></div>
				<div class="user-info">
					光速论坛 第<c:out value="${user.id}" />位会员
				</div>
				<div class="user-info">
					注册于<c:out value="${user.formatRegisterTime}" />
				</div>
				<div class="user-info">
					主题数 ${user.topicCount}
					&nbsp;
					回复数 ${user.replyCount}
				</div>
			</div>
		</div>
		
		<div class="mynav">
			<a href="<%=request.getContextPath() %>/my/avatar" class="mynav-selected">头像设置</a>
			<a href="<%=request.getContextPath() %>/my/gw2info">SCer 资料</a>
		</div>
		
		<div class="set-avatar">
			<div class="title">头像设置</div>
			<div>
				<div class="avatar-uploader" data-upload-url="<%=request.getContextPath() %>/my/uploadAvatar">选择图片</div>
			</div>
			<div class="avatar-setter">
			</div>
			<div>
			头像预览效果：
			</div>
			<div class="avatar-preview">
			</div>
			<div class="avatar-submit" data-submit-url="<%=request.getContextPath() %>/my/doSubmitAvatarInfo">
				提交
			</div>
		</div>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>