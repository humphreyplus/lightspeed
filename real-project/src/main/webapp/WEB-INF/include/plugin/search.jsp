<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="plugin-panel plugin-search">
	<div class="p-header">
	搜索
	</div>
	<div class="p-content">
		<form action="<%=request.getContextPath() %>/search">
			<input type="text" name="key">
			<input type="submit" value="搜索">
		</form>
	</div>
</div>