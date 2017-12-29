<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String WEBPATH3 = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>


<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>

</head>
<body>

	
	
	
	<h1>首页 </h1>
	
	<!-- basic scripts -->
	<script src="<%=WEBPATH3 %>/static/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	$(top.hangge());
		
	</script>

</body>
</html>
