<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 我的消息</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/message.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/message.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<div class="message-top-line">
			<a href="<%=request.getContextPath() %>/my/send">写消息</a>
			消息中心
		</div>
		
		<div class="message-list">
			<ul>
				<c:forEach var="messageItem" items="${messageList}">
					<li <c:if test="${messageItem.readStatus == 0}">class="message-unread"</c:if> data-submit-url="<%=request.getContextPath() %>/my/readMessage" data-msgcount-url="<%=request.getContextPath() %>/my/getMsgCount" data-id="${messageItem.id}">
						<span>${messageItem.formatCreateTime}</span><c:out value="${messageItem.sendUsername}" />
						<c:if test="${messageItem.type == 1}">给我私信</c:if>
						<c:if test="${messageItem.type == 2}">@提到了我</c:if>
						<c:if test="${messageItem.type == 0}">系统消息</c:if>
						<div class="message-html">
							<c:if test="${messageItem.type == 0}">
								<font style="color: red">系统消息</font>：
								<c:out value="${messageItem.content}" />
							</c:if>
							<c:if test="${messageItem.type == 1}">
								<a href="<%=request.getContextPath() %>/user/${messageItem.sendUserId}" target="_blank"><c:out value="${messageItem.sendUsername}" /></a>
								：
								<c:out value="${messageItem.content}" />
								<br><br>
								<a class="message-bottom-btn" href="<%=request.getContextPath() %>/my/send?to=${messageItem.sendUsername}" target="_blank">回复</a>
							</c:if>
							<c:if test="${messageItem.type == 2}">
								<a href="<%=request.getContextPath() %>/user/${messageItem.sendUserId}" target="_blank"><c:out value="${messageItem.sendUsername}" /></a>
								在帖子
								<a href="<%=request.getContextPath() %>/gotoReply?id=${messageItem.replyId}" target="_blank"><c:out value="${messageItem.topicTitle}" /></a>
								中@提到了我
								<br><br>
								<a class="message-bottom-btn" href="<%=request.getContextPath() %>/gotoReply?id=${messageItem.replyId}" target="_blank">查看</a>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="message-list-pager">
			<c:if test="${p > 1}">
				<a href="<%=request.getContextPath() %>/my/message?p=${p - 1}">上一页</a>
			</c:if>
			<c:if test="${p == 1}">
				<a href="javascript:void(0)">上一页</a>
			</c:if>
			${p}/${pageCount}
			<c:if test="${p < pageCount}">
				<a href="<%=request.getContextPath() %>/my/message?p=${p + 1}">下一页</a>
			</c:if>
			<c:if test="${p == pageCount}">
				<a href="javascript:void(0)">下一页</a>
			</c:if>
			
			</div>
		</div>
		
		<div class="message-content">
			<div class="message-content-main">
			</div>
		</div>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>