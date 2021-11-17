<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="plugin-panel plugin-statistics">
	<div class="p-header">
	社区状态
	</div>
	<div class="p-content">
		<p>注册用户：<span>${statisticalData.userCount}</span></p>
		<p>主题数量：<span>${statisticalData.topicCount}</span></p>
		<p>回复数量：<span>${statisticalData.replyCount}</span></p>
	</div>
</div>