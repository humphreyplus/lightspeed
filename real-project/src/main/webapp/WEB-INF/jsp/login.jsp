<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>用户登录 - ${appcfg.websiteName}</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/form.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/login.js"></script>
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
		用户登录
		</div>
		
		<form action="<%=request.getContextPath() %>/dologin" method="post" onSubmit="return loginSubmitCheck();">
			<p><span class="input-header">用户</span><input type="text" id="input-user" name="userstr" placeholder="用户名或邮箱"></p>
			<p><span class="input-header">密码</span><input type="password" id="input-pwd" name="password" placeholder="用户登录密码"></p>
			<p><span class="input-header"></span><input type="checkbox" name="saveUser" value="saveUser">下次自动登录</p>
			<p><span class="input-header"></span><input type="submit" value="登录"><span class="error-msg" id="login-error-msg"><c:if test="${param.error == 'true'}">用户名或密码错误！</c:if></span></p>
			<p><span class="input-header"></span><a href="<%=request.getContextPath() %>/forget">忘记密码？</a></p>
		</form>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>