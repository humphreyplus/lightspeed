<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title><c:if test="${not empty sectionId}">${sectionName} - </c:if>${appcfg.websiteName}</title>
	<meta name="keywords" content="光速论坛">
	<meta name="description" content="天诛八尺">
	<link rel="apple-touch-icon" href="<%=request.getContextPath() %>/logo.png"/>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/list.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/URI.js/1.11.2/URI.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/list.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<a href="<%=request.getContextPath() %>/new?section=${sectionId}" class="btn-new-topic">创建主题</a>
		
		<ul class="topic-sections">
			<c:if test="${empty sectionId}">
				<li class="section-selected">全部</li>
			</c:if>
			<c:if test="${not empty sectionId}">
				<li><a href="<%=request.getContextPath() %>/">全部</a></li>
			</c:if>
			<c:forEach var="sectionItem" items="${sectionList}">
				<c:if test="${sectionId != sectionItem.id}">
					<li title="${sectionItem.introduce}"><a href="<%=request.getContextPath() %>/section/${sectionItem.id}">${sectionItem.name}</a></li>
				</c:if>
				<c:if test="${sectionId == sectionItem.id}">
					<li title="${sectionItem.introduce}" class="section-selected">${sectionItem.name}</li>
				</c:if>
			</c:forEach>
		</ul>
		
		<c:forEach var="topicItem" items="${topicList}">
			<div class="topic">
				<div class="topic-user">
					<a href="<%=request.getContextPath() %>/user/${topicItem.userId}" target="_blank" class="topic-user-avatar">
						<img alt="" src="<c:out value="${topicItem.avatar}" />">
					</a>
				</div>
				<div class="topic-right-content">
					<div class="topic-reply-count" title="回复数">${topicItem.replyCount}</div>
					<div class="topic-title">
						<c:if test="${not empty topicItem.importance}">
							<span class="topic-importance" style="background-color:${topicItem.importanceColor}">${topicItem.importance}</span>
							&nbsp;
						</c:if>
						<a href="<%=request.getContextPath() %>/topic/${topicItem.id}" target="_blank"><c:out value="${topicItem.title}" /></a>
					</div>
					<c:if test="${not empty topicItem.image}">
						<div class="preview-img preview-img-small">
							<img alt="" src="<c:out value="${topicItem.image}" />">
						</div>
					</c:if>
					<div class="topic-bottom">
						<a href="<%=request.getContextPath() %>/section/${topicItem.sectionId}" class="topic-section"><c:out value="${topicItem.sectionName}" /></a>
						•
						<a href="<%=request.getContextPath() %>/user/${topicItem.userId}" target="_blank"><c:out value="${topicItem.username}" /></a>
						•
						最后回复来自<a href="<%=request.getContextPath() %>/user/${topicItem.lastReplyUserId}" target="_blank"><c:out value="${topicItem.lastReplyUsername}" /></a>
						•
						<c:out value="${topicItem.formatLastReplyTime}" />
						•
						<a href="<%=request.getContextPath() %>/topic/${topicItem.id}#replytopic" target="_blank">回复</a>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<div class="pager">
			<c:if test="${p == 1}">
				<span>&lt;</span>
			</c:if>
			<c:if test="${p != 1}">
				<a href="javascript:void(0);" data-p="${p - 1}">&lt;</a>
			</c:if>
			<c:forEach var="pageItem" items="${pageList}">
				<c:if test="${pageItem == p}">
					<span class="pager-selected">${pageItem}</span>
				</c:if>
				<c:if test="${pageItem != p}">
					<a href="javascript:void(0);" data-p="${pageItem}">${pageItem}</a>
				</c:if>
			</c:forEach>
			<c:if test="${p == pageCount}">
				<span>&gt;</span>
			</c:if>
			<c:if test="${p != pageCount}">
				<a href="javascript:void(0);" data-p="${p + 1}">&gt;</a>
			</c:if>
		</div>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>