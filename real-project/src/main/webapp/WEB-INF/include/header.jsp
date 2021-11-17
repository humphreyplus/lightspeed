<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<div class="header-center">
    	<div id="header-right">
    	
    		<c:if test="${not empty sessionScope.loginUser.username}">
    			<a href="<%=request.getContextPath() %>/my/avatar"><img alt="" src="<c:out value="${sessionScope.loginUser.avatar}"/>"></a>
    			<a href="<%=request.getContextPath() %>/my/gw2info"><c:out value="${sessionScope.loginUser.username}"/></a>
    			<a href="<%=request.getContextPath() %>/my/message">消息<span class="header-new-msg"></span></a>
    			<a href="<%=request.getContextPath() %>/logout">注销</a>
    		</c:if>
    		<c:if test="${empty sessionScope.loginUser.username}">
    			<a href="<%=request.getContextPath() %>/login">登录</a>
    			<a href="<%=request.getContextPath() %>/register">注册</a>
    		</c:if>
    		
    	</div>
    	<a href="<%=request.getContextPath() %>/"><img alt="" src="${appcfg.staticFileUrl}/static/img/header-logo.png"></a>
    </div>
</div>
<script>
$(document).ready(function() {
	
	$.ajax({
		url: "<%=request.getContextPath() %>/my/getMsgCount",
		type: "GET",
		cache: false,
		dataType: "json",
		success: function(data) {
			if(data.status) {
				$(".header-new-msg").html(data.message);
			}
		}
	});
	
});
</script>