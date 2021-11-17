<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 重置密码</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/form.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/resetpwd.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<div class="form-header">
		重置密码
		</div>
		<form action="<%=request.getContextPath() %>/doresetpwd" method="post" onSubmit="return registerSubmitCheck();">
			<p><span class="input-header">新密码</span><input type="password" id="input-pwd" name="password"><span class="error-msg" id="pwd-error-msg"></span></p>
			<div class="input-tips"><span class="input-header"></span>请使用字母、数字，长度6~20</div>
			<p><span class="input-header">确认密码</span><input type="password" id="input-pwd2"><span class="error-msg" id="pwd2-error-msg"></span></p>
			<input type="hidden" name="key" value="${param.key}">
			<p><span class="input-header"></span><input type="submit" value="提交"></p>
		</form>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>