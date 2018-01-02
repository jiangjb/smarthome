<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>新用户注册</title>
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
						<img src="" alt="用户注册" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="text" name="userName" id="userName" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="userPhone" name="userPhone" id="userPhone" placeholder="请输入手机号" value="" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="email" name="email" id="email" placeholder="请输入邮箱" value="" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="password" name="userPwd" id="userPwd" placeholder="请输入密码" value="" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="password" name="reuserPwd" id="reuserPwd" placeholder="请再次输入密码" value="" />
						</div>
					</div>
				</div>
				
				
				
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="register();"
							class="flip-link btn btn-info" id="to-recover">注册</a></span>

					</div>
				</div>

			</form>


			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © 
							2016</span></font>
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
		//服务器校验
		function register(){
			/* debugger */
				/* alert("被点击。。。") */
			if(check()){
				var userName = $("#userName").val();
				var userPhone = $("#userPhone").val();
				var email=$("#email").val();
				var userPwd = $("#userPwd").val();
				var reuserPwd = $("#reuserPwd").val();
				
				if(userPwd == reuserPwd){
				    /* alert("userPwd和reuserPwd一致，可继续操作...");  */
					$.ajax({
						url:"register.do",
						data: {"userName":userName,"userPwd":userPwd,"userPhone":userPhone,"email":email }, 
						type: "POST",
						dataType:"json",
						async: true,
						/* cache: false, */
						success: function(data){
							/* alert(data)  */
							if(data == "success"){
								/* alert("success") */
								window.location.href="<%=WEBPATH %>/login.jsp";
							}else{
								/* alert(data) */
							}
						},
						error: function(e){  	 
		                	/* alert(XMLHttpRequest.status);//200
		                	alert(XMLHttpRequest.readyState);//4       － （完成）响应内容解析完成，可以在客户端调用了
		               	    alert(textStatus);//parsererror */
		               	    /* debugger */
		               	   /*  alert(e) */
						}
					});
				}else{
					$("#reuserPwd").tips({
						side : 1,
						msg : "两次输入的密码不一致",
						bg : '#FF5080',
						time : 15
					});
					$("#userPwd").focus();
				} 
			}
		};
		
		//客户端校验
		function check() {
			var pattern="";
			//用户名验证
			pattern=/^[a-zA-Z0-9_-]{3,16}$/;
			if ($("#userName").val() == "") {

				$("#userName").tips({
					side : 2,
					msg : '用户名不能为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#userName").focus();
				return false;
			} else if(!pattern.test($("#userName").val())){
				//这里匹配正则
				$("#userName").tips({
					side : 2,
					msg : '用户名只支持3-16位由字母、数字、下划线、减号和英文句号组成的字符串！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#userName").val(jQuery.trim($('#userName').val()));
			}
			
			//手机号验证
			pattern=/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0,5-9])|(18[0,5-9]))\d{8}$/;
			if ($("#userPhone").val() == "") {

				$("#userPhone").tips({
					side : 2,
					msg : '手机号不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#userPhone").focus();
				return false;
			} else if(!pattern.test($("#userPhone").val())){
				//这里匹配正则
				$("#userPhone").tips({
					side : 2,
					msg : '手机号格式有误，只支持13、14、15、17和18开头的！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#userPhone").val(jQuery.trim($('#userPhone').val()));
			}
			
			//邮箱验证
			pattern=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if ($("#email").val() == "") {

				$("#email").tips({
					side : 2,
					msg : '邮箱不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#email").focus();
				return false;
			} else if(!pattern.test($("#email").val())){
				//这里匹配正则
				$("#email").tips({
					side : 2,
					msg : '邮箱格式错误！（格式：xxx@xxx.com|cn等）',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#email").val(jQuery.trim($('#email').val()));
			}
			//密码验证
			pattern=/^[a-zA-Z0-9.]{3,16}$/;
			if ($("#userPwd").val() == "") {

				$("#userPwd").tips({
					side : 2,
					msg : '密码不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#userPwd").focus();
				return false;
			} else if(!pattern.test($("#userPwd").val())){
				//这里匹配正则
				$("#userPwd").tips({
					side : 2,
					msg : '密码格式错误，密码只支持字母、数字及英文句号！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#userPwd").val(jQuery.trim($('#userPwd').val()));
			}
			//再次输入的密码验证
			if ($("#reuserPwd").val() == "") {

				$("#reuserPwd").tips({
					side : 2,
					msg : '此处密码确认，不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#reuserPwd").focus();
				return false;
			} else if(!pattern.test($("#reuserPwd").val())){
				//这里匹配正则
				$("#reuserPwd").tips({
					side : 2,
					msg : '密码格式错误，密码只支持字母、数字及英文句号！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#reuserPwd").val(jQuery.trim($('#reuserPwd').val()));
			}
			
			
			$("#loginbox").tips({
				side : 1,
				msg : '正在注册 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
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