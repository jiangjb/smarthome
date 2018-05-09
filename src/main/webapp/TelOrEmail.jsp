<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>找回密码</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<%=WEBPATH %>/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=WEBPATH %>/static/css/camera.css" />
<link rel="stylesheet" href="<%=WEBPATH %>/static/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=WEBPATH %>/static/css/matrix-login.css" />
<link href="<%=WEBPATH %>/static/css/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="<%=WEBPATH %>/static/js/jquery-1.9.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >  
</head>
<style type="text/css">      
     body{      
        background-image: url(<%=WEBPATH %>/static/images/banner_slide_01.jpg);      
        background-size:cover;    
     }      
 </style>  
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<!-- <img src="" alt="选择找回密码的方式" /> -->选择找回密码的方式
					</h>
				
				<div>
					<span>
						<div style="float: left;margin-top:3px;margin-right:20px;">
							<a href="javascript:history.back(1);">返回</a>
						</div>
					</span>
					
					<span style="margin-right:100px;">
						<div style="float: left;margin-top:3px;margin-right:20px;">
							<font color="white"><a href="<%=WEBPATH %>/findPwdByEmail.jsp" style="color:white;font-size:13px;margin-right:20px;" onmouseover="this.style.cssText='color:green;font-size:18px; text-decoration:none;'" onmouseout="this.style.cssText='color:white;font-size:13px;text-decoration:none'">邮箱找回</a></font>
						</div>
					</span>
					<span>
						<div style="float: left;margin-top:3px;margin-right:20px;">
							<font color="white"><a href="<%=WEBPATH %>/findPwdByTel.jsp" style="color:white;font-size:13px;margin-right:10px;" onmouseover="this.style.cssText='color:green;font-size:18px; text-decoration:none;'" onmouseout="this.style.cssText='color:white;font-size:13px;text-decoration:none'">手机号找回</a></font>
						</div>
					</span>
					
				</div>
			</form>
	</div>
	<script type="text/javascript">
	
		
	
		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});
		
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}


	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>

	<script src="<%=WEBPATH %>/static/js/bootstrap.min.js"></script>
	<script src="<%=WEBPATH %>/static/js/jquery.easing.1.3.js"></script>
	<script src="<%=WEBPATH %>/static/js/jquery.mobile.customized.min.js"></script>
	<script src="<%=WEBPATH %>/static/js/camera.min.js"></script>
	<script src="<%=WEBPATH %>/static/js/templatemo_script.js"></script>
	<script type="text/javascript" src="<%=WEBPATH %>/static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="<%=WEBPATH %>/static/js/jquery.cookie.js"></script>
</body>

</html>