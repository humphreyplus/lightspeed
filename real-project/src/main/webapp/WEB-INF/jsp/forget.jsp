<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>忘记密码 - ${appcfg.websiteName}</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/form.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/forget.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<div class="form-header">
		忘记密码
		</div>
		
		<form action="<%=request.getContextPath() %>/setPwdEmail" method="post">
			<p><span class="input-header">注册邮箱：</span><input type="text" name="email"><span class="error-msg"></span></p>
			<p><span class="input-header"></span><input type="submit" value="提交"></p>
		</form>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>