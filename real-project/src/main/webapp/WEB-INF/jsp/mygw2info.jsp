<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit">
	<title>${appcfg.websiteName} - 用户设置</title>
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/common.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/plugin.css">
	<link rel="stylesheet" href="${appcfg.staticFileUrl}/static/css/user.css">
	<style type="text/css">
	form .user-info {
		line-height: 40px;
	}
	.user-info select,.user-info input[type=text] {
		height: 25px;
		width: 200px;
		padding: 0;
		margin: 0;
	}
	.user-info input[type=submit] {
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
	.user-info input[type=submit]:hover {
		background-color: #449d44;
	}
	</style>
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="${appcfg.staticFileUrl}/static/js/mygw2info.js"></script>
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
				<div class="username"><c:out value="${user.username}" /></div>
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
		
		<div class="mynav">
			<a href="<%=request.getContextPath() %>/my/avatar">头像设置</a>
			<a href="<%=request.getContextPath() %>/my/gw2info" class="mynav-selected">激战2资料</a>
		</div>
		
		<form method="post" action="<%=request.getContextPath() %>/my/doSubmitGw2Info">
			<div class="user-gw2">
				<div class="title">SCer 资料编辑</div>
				<div class="user-info">
					<span>游戏昵称</span>
					<input type="text" name="gameNickname" value="<c:out value="${user.gameNickname}" />">
					* 以四位数字结尾，例如：犀利哥.9876
				</div>
				<div class="user-info">
					<span>角色名</span>
					<input type="text" name="roleName" value="<c:out value="${user.roleName}" />">
					* 填写一个常用的角色名
				</div>
				<div class="user-info">
					<span>服务器</span>
					<select id="select-gw2area">
						<option></option>
						<c:forEach var="gw2AreaItem" items="${gw2AreaList}">
							<c:if test="${gw2AreaItem.name == user.areaName}">
								<option value="${gw2AreaItem.id}" selected="selected">${gw2AreaItem.name}</option>
							</c:if>
							<c:if test="${gw2AreaItem.name != user.areaName}">
								<option value="${gw2AreaItem.id}">${gw2AreaItem.name}</option>
							</c:if>
						</c:forEach>
					</select>
					<select class="select-gw2homeland" id="select-gw2homeland" style="display: none;">
					</select>
					<c:forEach var="gw2AreaItem" items="${gw2AreaList}">
						<select style="display: none;" class="select-gw2homeland" id="select-gw2homeland${gw2AreaItem.id}">
							<option></option>
							<c:forEach var="gw2HomelandItem" items="${gw2HomelandList}">
								<c:if test="${gw2HomelandItem.gw2AreaId == gw2AreaItem.id}">
									<c:if test="${gw2HomelandItem.name == user.homelandName}">
										<option value="${gw2HomelandItem.id}" selected="selected">${gw2HomelandItem.name}</option>
									</c:if>
									<c:if test="${gw2HomelandItem.name != user.homelandName}">
										<option value="${gw2HomelandItem.id}">${gw2HomelandItem.name}</option>
									</c:if>
								</c:if>
							</c:forEach>
						</select>
					</c:forEach>
				</div>
				<div class="user-info">
					<span>公会</span>
					<input type="text" name="organization" value="<c:out value="${user.organization}" />">
					* 填写一个代表的公会
				</div>
				<div class="user-info">
					<span>是否公开</span>
					<input type="checkbox" name="publicStatus" value="1" <c:if test="${user.publicStatus == 1}">checked="checked"</c:if>>
					* 公开后以上信息可以被其他用户查看
				</div>
				<div class="user-info">
					<span></span>
					<input type="submit" value="提交">
				</div>
			</div>
		</form>
		
	</div>
	
	<%@include file="../include/footer.jsp" %>

</div>
</div>
</body>
</html>