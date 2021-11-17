<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 用户信息</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/user.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
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
				<div class="username">
					<c:out value="${user.username}" />
					<c:if test="${user.id != sessionScope.loginUser.id}">
						<a class="public-btn" href="<%=request.getContextPath() %>/my/send?to=${user.username}" style="font-size: 12px;font-weight: normal;margin-left: 20px;">私信</a>
					</c:if>
					<c:if test="${user.id == sessionScope.loginUser.id}">
						<a class="public-btn" href="<%=request.getContextPath() %>/my/avatar" style="font-size: 12px;font-weight: normal;margin-left: 20px;">设置</a>
					</c:if>
				</div>
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
		
		<div class="user-gw2">
			<div class="title">SCer资料</div>
			<div class="user-info">
				游戏昵称：
				<c:if test="${user.publicStatus == 1}">
					<c:out value="${user.gameNickname}" />
				</c:if>
			</div>
			<div class="user-info">
				角色名：
				<c:if test="${user.publicStatus == 1}">
					<c:out value="${user.roleName}" />
				</c:if>
			</div>
			<div class="user-info">
				服务器：
				<c:if test="${user.publicStatus == 1}">
					<c:out value="${user.areaName}" />
					<c:out value="${user.homelandName}" />
				</c:if>
			</div>
			<div class="user-info">
				公会：
				<c:if test="${user.publicStatus == 1}">
					<c:out value="${user.organization}" />
				</c:if>
			</div>
		</div>
		
		<div class="list">
			<div class="title">最新主题</div>
			<ul>
				<c:forEach var="topicItem" items="${topicList}">
					<li>
					${topicItem.formatCreateTime}
					发表主题
					<a href="<%=request.getContextPath() %>/topic/${topicItem.id}" target="_blank">
						<c:out value="${topicItem.title}" />
					</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		
		<div class="list">
			<div class="title">最新回复</div>
			<ul>
				<c:forEach var="replyItem" items="${replyList}">
					<li>
					${replyItem.formatReplyTime}
					回复主题
					<a href="<%=request.getContextPath() %>/gotoReply?id=${replyItem.id}" target="_blank">
						<c:out value="${replyItem.topicTitle}" />
					</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>