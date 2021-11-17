<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title><c:out value="${topic.title}"/> - ${appcfg.websiteName}</title>
	<meta name="keywords" content="光速论坛">
	<meta name="description" content="天诛八尺">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/topic.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<script type="text/javascript">
	window.UEDITOR_HOME_URL = "<%=request.getContextPath() %>/static/js/ueditor/";
	window.UEDITOR_SERVER_URL = "<%=request.getContextPath() %>/ueditorService";
	</script>
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/URI.js/1.11.2/URI.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/topic.js"></script>
</head>
<body>

<%@include file="../include/header.jsp" %>

<div id="bg">
<div id="main">

	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
	
		<a href="<%=request.getContextPath() %>/new?section=${topic.sectionId}" class="btn-new-topic">创建主题</a>
		
		<ul class="topic-sections">
			<li><a href="<%=request.getContextPath() %>/">全部</a></li>
			<c:forEach var="sectionItem" items="${sectionList}">
				<c:if test="${topic.sectionId != sectionItem.id}">
					<li title="${sectionItem.introduce}"><a href="<%=request.getContextPath() %>/section/${sectionItem.id}">${sectionItem.name}</a></li>
				</c:if>
				<c:if test="${topic.sectionId == sectionItem.id}">
					<li title="${sectionItem.introduce}" class="section-selected"><a href="<%=request.getContextPath() %>/section/${sectionItem.id}">${sectionItem.name}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		
		<div class="topic-title">
			<span class="topic-reply-count" title="回复数"><c:out value="${topic.replyCount}"/></span>
			<div>
				<c:if test="${not empty topic.importance}">
					<span class="topic-importance" style="background-color:${topic.importanceColor}">${topic.importance}</span>
					&nbsp;
				</c:if>
				<h1><c:out value="${topic.title}"/></h1>
				<c:if test="${topic.userId == sessionScope.loginUser.id}">
					<a href="javascript:void(0)" class="edit-topic-title-btn" data-id="${topic.id}" data-baseurl="<%=request.getContextPath() %>">
   						<img src="${appcfg.staticFileUrl}/static/img/icon-edit.png" title="编辑标题">
   					</a>
   					<a href="javascript:void(0)" class="edit-topic-btn" data-id="${topic.id}" data-baseurl="<%=request.getContextPath() %>">
   						<img src="${appcfg.staticFileUrl}/static/img/icon-delete.png" title="删除主题">
   					</a>
				</c:if>
			</div>
		</div>
	
		<c:forEach var="replyItem" items="${replyList}">
			<div class="reply-floor" id="gotoReply${replyItem.id}">
				<div class="reply-user-info <c:if test="${replyItem.userId == topic.userId}">reply-louzhu</c:if>">
					<a href="<%=request.getContextPath() %>/user/${replyItem.userId}" target="_blank" class="reply-user-avatar">
						<img alt="" src="<c:out value="${replyItem.avatar}" />">
					</a>
					<div class="reply-user-name"><a href="<%=request.getContextPath() %>/user/${replyItem.userId}" target="_blank"><c:out value="${replyItem.username}" /></a></div>
				</div>
				<div class="reply-main">
					<div class="reply-content">${replyItem.content}</div>
					<c:if test="${replyItem.formatUpdateTime != null}">
						<div class="reply-update-time">
							<span>最后编辑于${replyItem.formatUpdateTime}</span>
						</div>
					</c:if>
					<c:if test="${replyItem.userId == sessionScope.loginUser.id}">
						<div class="reply-edit">
							<a href="<%=request.getContextPath() %>/my/editReply?id=${replyItem.id}">
		   						<img src="${appcfg.staticFileUrl}/static/img/icon-edit.png" title="编辑回复">
		   					</a>
		   					<a href="<%=request.getContextPath() %>/my/deletereply" class="reply-delete-btn" data-id="${replyItem.id}">
		   						<img src="${appcfg.staticFileUrl}/static/img/icon-delete.png" title="删除回复">
		   					</a>
						</div>
					</c:if>
					<div class="reply-bottom">
						<span>#${replyItem.floor}</span>
						&nbsp;
						<c:out value="${replyItem.formatReplyTime}" />
						&nbsp;
						<a href="#replytopic" data-username="<c:out value="${replyItem.username}" />" class="reply-replyuser">回复</a>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<div class="pager">
			<c:if test="${p == 1}">
				<span>&lt;</span>
			</c:if>
			<c:if test="${p != 1}">
				<a href="<%=request.getContextPath() %>/topic/${topic.id}?p=${p - 1}">&lt;</a>
			</c:if>
			<c:forEach var="pageItem" items="${pageList}">
				<c:if test="${pageItem == p}">
					<span class="pager-selected">${pageItem}</span>
				</c:if>
				<c:if test="${pageItem != p}">
					<a href="<%=request.getContextPath() %>/topic/${topic.id}?p=${pageItem}">${pageItem}</a>
				</c:if>
			</c:forEach>
			<c:if test="${p == pageCount}">
				<span>&gt;</span>
			</c:if>
			<c:if test="${p != pageCount}">
				<a href="<%=request.getContextPath() %>/topic/${topic.id}?p=${p + 1}">&gt;</a>
			</c:if>
		</div>
		
		<div class="reply-textarea" id="replytopic">
			<c:if test="${not empty sessionScope.loginUser.username}">
			    <script id="container" type="text/plain"></script>
			    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ueditor/ueditor.config.js"></script>
			    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ueditor/ueditor.all.js"></script>
			    <script type="text/javascript">
			        var ue = UE.getEditor('container');
			    </script>
		    </c:if>
		    <c:if test="${empty sessionScope.loginUser.username}">
			    <div class="reply-tologin">
			 		   请<a href="<%=request.getContextPath() %>/login">登录</a>后再进行回复操作
			    </div>
		    </c:if>
			<div class="reply-btn reply-btn-enable" data-topicid="${topic.id}" data-submiturl="<%=request.getContextPath() %>/ajaxreply">回 复</div>
		</div>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>
	
</div>
</div>
</body>
</html>