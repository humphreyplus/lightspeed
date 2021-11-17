<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="plugin-panel plugin-hot">
	<div class="p-header">
	热门主题
	</div>
	<div class="p-content">
		<ul>
			<c:forEach var="hotTopicItem" items="${hotTopicList}">
				<li>
					<span title="回复数">${hotTopicItem.replyCount}</span>
					<a href="<%=request.getContextPath() %>/topic/${hotTopicItem.id}" target="_blank"><c:out value="${hotTopicItem.title}" /></a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>