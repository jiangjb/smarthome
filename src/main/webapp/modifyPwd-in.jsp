<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH = request.getContextPath();
	String USER_ID=request.getParameter("User_ID");
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
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="" alt="修改密码" />
					</h>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="password" name="oldpassword" id="oldpassword" placeholder="请输入旧密码" value="" />
						</div>
						<div class="main_input_box">
							<input type="password" name="reoldpassword" id="reoldpassword" placeholder="请再次输入旧密码" value="" />
						</div>
						<div class="main_input_box">
							<input type="text" name="newpassword" id="newpassword" placeholder="请输入新密码" value="" />
						</div>
					</div>
				</div>
				
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="findPwd();"
							class="flip-link btn btn-info" id="to-recover">确定</a></span>

					</div>
				</div>

			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © 
							2018-2028 ZNHOMES All Rights Reserved.</span></font>
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div data-src="<%=WEBPATH %>/static/images/banner_slide_01.jpg"></div>
			<div data-src="<%=WEBPATH %>/static/images/banner_slide_02.jpg"></div>
			<div data-src="<%=WEBPATH %>/static/images/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>

	<script type="text/javascript">
	
		
	
		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});
		//新旧密码检测> 一致 > 进行数据库操作        
		function findPwd(){
			var oldpassword = $("#oldpassword").val();
			var reoldpassword = $("#reoldpassword").val();
			var newpassword = $("#newpassword").val();
			if(oldpassword==reoldpassword && newpassword!="" && oldpassword!="" && reoldpassword!=""){
				$.ajax({
					url:'${pageContext.request.contextPath}/modifyPwd.do',
					data: {"USER_ID":"<%=USER_ID %>","oldpassword":oldpassword,"newpassword":newpassword}, 
					type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						/* alert(data) */
						if("success" == data){
							alert("修改成功，即将跳入登录页面...")
							window.location.href="<%=WEBPATH %>/login.jsp";
						}else if("error" == data){
							$("#oldpassword").tips({
								side : 1,
								msg : "密码修改出现了异常",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}
					},
					error: function(e){  	 
	               	    alert("error方法执行...")
					}
				});
			}else if(oldpassword!=reoldpassword && newpassword!="" && reoldpassword!=""){
				$("#oldpassword").tips({
					side : 1,
					msg : "前后两次输入的密码不一致，请重试！",
					bg : '#FF5080',
					time : 15
				});
				$("#oldpassword").val("");
				$("#reoldpassword").val("");
				$("#oldpassword").focus();
			}else if(newpassword!="" || oldpassword!="" || reoldpassword!=""){
				if(newpassword==""){
					$("#newpassword").tips({
						side : 1,
						msg : "不能为空！",
						bg : '#FF5080',
						time : 15
					});
				}
				if(oldpassword==""){
					$("#oldpassword").tips({
						side : 1,
						msg : "不能为空！",
						bg : '#FF5080',
						time : 15
					});
				}
				if(reoldpassword==""){
					$("#reoldpassword").tips({
						side : 1,
						msg : "不能为空！",
						bg : '#FF5080',
						time : 15
					});
				}
			}else{
				alert("发生异常！")
			}
			
		};
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