<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 发送消息</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/form.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/send.js"></script>
	<style type="text/css">
	#content input[type=submit] {
		background-color: #5cb85c;
		color: white;
		width: 130px;
		height: 30px;
		font-size: 16px;
		border-radius: 5px;
		border: 0;
		cursor: pointer;
		outline: none;
		font-family: 微软雅黑;
	}
	#content input[type=submit]:hover {
		background-color: #449d44;
	}
	</style>
	
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<div class="form-header">
		发送消息
		</div>
		
		<form action="<%=request.getContextPath() %>/my/sendMessage" method="post">
			<p><span class="input-header">收件人：</span><input type="text" name="username" value="<c:out value="${param.to}" />"><span class="error-msg"></span></p>
			<p><span class="input-header" style="vertical-align: top;">消息内容：</span><textarea name="content" style="width: 300px; height: 150px;resize: none;"></textarea></p>
			<p><span class="input-header"></span><input type="submit" value="发送"></p>
		</form>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>