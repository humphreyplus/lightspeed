<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>捐助我们 - ${appcfg.websiteName}</title>
	<meta name="keywords" content="光速论坛">
	<meta name="description" content="天诛八尺">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<style type="text/css">
	.donate-table {
		padding: 50px 100px;
	}
	.donate-table span {
		display: inline-block;
		width: 31%;
	}
	.donate-table div {
		height: 35px;
	}
	.donate-table a {
		color: #449d44;
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

		<h4 style="padding-left: 40px;">捐助方式</h4>
		<h4 style="padding-left: 40px;">注：捐助时请备注论坛ID ^_^</h4>
		
		<div class="donate-table">
		<c:forEach var="donateItem" items="${donateList}">
		<div>
			<c:if test="${donateItem.username == null}">
				<span>匿名用户</span>
			</c:if>
			<c:if test="${donateItem.username != null}">
				<span><a href="<%=request.getContextPath() %>/user/${donateItem.userId}" target="_blank"><c:out value="${donateItem.username}" /></a></span>
			</c:if>
			<span>￥${donateItem.money}</span>
			<span>${donateItem.formatDonateTime}</span>
		</div>
		</c:forEach>
		</div>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>
	
</div>
</div>
</body>
</html>