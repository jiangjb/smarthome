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
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="" alt="输入手机号" />
					</h>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" style="width:220px;" name="userPhone" id="userPhone" placeholder="请输入手机号" value="" />
							</span>
							<span
								class="pull-right"><a onclick="findVerificationCode();" style="width:70px;" class="flip-link btn btn-info" id="to-recover">发送验证码</a>
						 	</span>
						</div>
					</div>
				</div>
				<input type="hidden" name="VCodeFromBack" id="VCodeFromBack" value="这是个隐藏的input" />
				<input type="hidden" name="UserID" id="UserID" value="这是个隐藏的input" />
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="text" name="VerificationCode" id="VerificationCode" placeholder="请输入验证码" value="" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="password" name="password" id="password" placeholder="请输入登录密码" value="" />
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<input type="password" name="repassword" id="repassword" placeholder="请确认登录密码" value="" />
						</div>
					</div>
				</div>		
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="findPwd();" class="flip-link btn btn-info" id="to-recover"> 提 交 </a>
						 </span>
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

	<script type="text/javascript">
	
		
	
		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});
		
		//发送验证码
		function findVerificationCode(){
			var userPhone=$("#userPhone").val();
			var pattern=/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0,5-9])|(18[0,5-9]))\d{8}$/;
			if (userPhone == "") {
				$("#userPhone").tips({
					side : 2,
					msg : '手机号不能为空！',
					bg : '#AE81FF',
					time : 3
				});
				$("#userPhone").focus();
			} else if(!pattern.test(userPhone)){//这里匹配正则	
				$("#userPhone").tips({
					side : 2,
					msg : '手机号格式有误，只支持13、14、15、17和18开头的！',
					bg : '#AE81FF',
					time : 3
				});

			}else {
				$.ajax({
					url:"phoneCode.do",
			    	data: {"userPhone":userPhone },
					type: "POST",
					dataType:"json",
					async: false,					
					/* cache: false, */
					success: function(data){
						/* alert(data) */
						//alert(typeof data)//number
						if(data ==null){
							$("#userPhone").tips({
								side : 1,
								msg : "未找到手机号！",
								bg : '#FF5080',
								time : 15
							});
							$("#userPhone").focus();
						}else{
							//把验证码和UserID分别保存到隐藏的表单域中
							$.each(data,function(i,item){//i是key,item是value
		    					if("UserID" == i){
		    						/* alert(typeof item)//String */
		    						/* alert(item) */
		    						$("#UserID").val(item);
		    					}else if("mobile_code" == i){
		    						alert(item)
		    						$("#VCodeFromBack").val(item);
		    					}else{
		    						alert("error")
		    					}
	    					})

						} 
					}
				})
			}
		}
		
		//客户端校验
		function check(){
			//手机号验证
			var pattern=/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0,5-9])|(18[0,5-9]))\d{8}$/;
			if ($("#userPhone").val() == "") {
				$("#userPhone").tips({
					side : 2,
					msg : '手机号不能为空！',
					bg : '#AE81FF',
					time : 3
				});
				$("#userPhone").focus();
				return false;
			} else if(!pattern.test($("#userPhone").val())){//这里匹配正则	
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
			
			//密码验证
			pattern=/^[a-zA-Z0-9.]{3,16}$/;
			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			} else if(!pattern.test($("#password").val())){
				//这里匹配正则
				$("#password").tips({
					side : 2,
					msg : '密码格式错误，密码只支持字母、数字及英文句号！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#password").val(jQuery.trim($('#password').val()));
			}
			
			//再次输入的密码验证
			if ($("#repassword").val() == "") {

				$("#repassword").tips({
					side : 2,
					msg : '此处密码确认，不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#repassword").focus();
				return false;
			} else if(!pattern.test($("#repassword").val())){
				//这里匹配正则
				$("#repassword").tips({
					side : 2,
					msg : '密码格式错误，密码只支持字母、数字及英文句号！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#repassword").val(jQuery.trim($('#repassword').val()));
			}
			
			//两次输入的密码的验证
			if($("#password").val() != $("#repassword").val()){
				$("#repassword").tips({
					side : 2,
					msg : '两次输入的密码不一致，请重新输入！',
					bg : '#AE81FF',
					time : 3
				});

				$("#repassword").focus();
				return false;
			}
			
			$("#loginbox").tips({
				side : 1,
				msg : '正在发送短信验证码 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});
			return true;
		}
		//服务器校验
		function findPwd(){
			alert("后台传过来的验证码："+$("#VCodeFromBack").val())
			alert($("#VCodeFromBack").val()==$("#VerificationCode").val() );
			if(check()  && ($("#VCodeFromBack").val()==$("#VerificationCode").val()) ){//并且验证码输入相同
			/* debugger */
				/* alert("被点击。。。") */
				var UserID=$("#UserID").val();
				var loginPwd=$("#password").val();
				$.ajax({
					url:"findByTelOrEmail.do",
			    	data: {"UserID":UserID,"loginPwd":loginPwd },
					type: "POST",
					dataType:"json",
					async: false,					
					/* cache: false, */
					success: function(data){
						/* alert(data) */
						if("success" == data){
							setTimeout(function(){//两秒后跳转  
								window.location.href="<%=WEBPATH %>/login.jsp";
                             },2000); 
							<%-- window.location.href="<%=WEBPATH %>/login.jsp"; --%>
						}else{
							$("#userPhone").tips({
								side : 1,
								msg : "未找到手机号！",
								bg : '#FF5080',
								time : 15
							});
							$("#userPhone").focus();
						} 
					},
					error: function(e){  	 
	                	/* alert(XMLHttpRequest.status);//200
	                	alert(XMLHttpRequest.readyState);//4       － （完成）响应内容解析完成，可以在客户端调用了
	               	    alert(textStatus);//parsererror */
	               	    /* debugger */
	               	    alert(e)
					}
				});
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