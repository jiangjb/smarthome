<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String WEBPATH3 = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+WEBPATH3+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePathl%>"><!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1" name="viewport" />
<%-- <link rel="stylesheet" href="<%=WEBPATH3 %>/static/js/lc_switch.css"> --%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
		
<div class="container-fluid" id="main-container">
<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
		<p style="color:green;font-size:16px;">首页</p>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<!-- <a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a> -->
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH3 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH3 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH3 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH3 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/jquery.tips.js"></script><!--提示框-->
	</body>
</html>

