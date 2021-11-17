<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 创建主题</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/form.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
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
	<script type="text/javascript">
	window.UEDITOR_HOME_URL = "<%=request.getContextPath() %>/static/js/ueditor/";
	window.UEDITOR_SERVER_URL = "<%=request.getContextPath() %>/ueditorService";
	</script>
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/new.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>
<div id="bg">
<div id="main">
	
	<%@include file="../include/toplogo.jsp" %>

	<%@include file="../include/right-panel.jsp" %>
	
	<div id="content">
		
		<div class="form-header">
		创建主题
		</div>
		<form action="<%=request.getContextPath() %>/donewtopic" method="post">
			<p style="padding-bottom: 2px;">
				<span class="input-header">板块</span>
				<select id="input-section" name="sectionId">
					<option></option>
					<c:forEach var="sectionItem" items="${sectionList}">
						<c:if test="${param.section == sectionItem.id}">
							<option selected="selected" value="${sectionItem.id}">${sectionItem.name}</option>
						</c:if>
						<c:if test="${param.section != sectionItem.id}">
							<option value="${sectionItem.id}">${sectionItem.name}</option>
						</c:if>
					</c:forEach>
				</select>
				<span class="error-msg" id="section-error-msg"></span>
			</p>
			<div style="font-size: 12px;color: #999;">
				<span class="input-header"></span>
				<c:forEach var="sectionItem" items="${sectionList}">
					<span style="display: none;" class="section-intro section-intro-${sectionItem.id}">${sectionItem.introduce}</span>
				</c:forEach>
			</div>
			<p>
				<span class="input-header">标题</span>
				<input type="text" id="input-title" name="title">
				<span class="error-msg" id="title-error-msg"></span>
			</p>
			<script id="container" name="content" type="text/plain" style="padding-left: 122px;"></script>
		    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ueditor/ueditor.config.js"></script>
		    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ueditor/ueditor.all.js"></script>
		    <script type="text/javascript">
		        var ue = UE.getEditor('container', {
		        	initialFrameWidth:580,
		            initialFrameHeight:150
		        });
		    </script>
		    <p>
				<span class="input-header">类型（可不选）</span>
				<c:forEach var="typeItem" items="${typeList}">
				<input type="radio" name="topicTypeId" value="${typeItem.id}">${typeItem.name}
				</c:forEach>
			</p>
			<p><span class="input-header"></span><input type="submit" value="提交"></p>
		</form>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>