<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="right-panel">
	<%@include file="../include/plugin/search.jsp" %>
	<%@include file="../include/plugin/tools.jsp" %>
	<%@include file="../include/plugin/intro.jsp" %>
	<%@include file="../include/plugin/act.jsp" %>
	<%@include file="../include/plugin/ad.jsp" %>
	<%-- <%@include file="../include/plugin/hot.jsp" %> --%>
	<%@include file="../include/plugin/statistics.jsp" %>
	<div class="right-totop"></div>
	<script type="text/javascript">
	$(document).ready(function() {
		$(window).scroll(function() {
			topBtnDisplay();
		});
		$(".right-totop").click(function() {
			$('html,body').animate({scrollTop: '0px'}, 500);
		});
	});
	function topBtnDisplay() {
		if($(window).scrollTop() > 800) {
			$(".right-totop").show();
		} else {
			$(".right-totop").hide();
		}
	}
	</script>
</div>