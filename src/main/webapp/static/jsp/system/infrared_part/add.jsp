<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH = request.getContextPath();
   /*  String loginName=request.getParameter("loginName");
    String loginPwd=request.getParameter("loginPwd"); */
    String USER_ID=request.getParameter("User_ID");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>添加红外参数</title>
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
						<img src="" alt="添加红外参数" />
					</h>
				
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="deviceAddress" id="deviceAddress"  value="" />
							</span>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="validationCode" id="validationCode"  value="" />
							</span>
						</div>
					</div>
				</div>
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="save();" class="flip-link btn btn-info" id="to-recover"> 提 交 </a>
						 </span>
						 <span
							class="pull-right"><a onclick="excel();" class="flip-link btn btn-info" id="to-recover"> 取消 </a>
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
	    //取消
	    function excel(){
	    	$('#deviceAddress').val("");
	    	$('#validationCode').val("");
	    	$("#deviceAddress").focus();
	    }
		//客户端校验
		function check(){
			//deviceAddress验证 （未完成）
			/* var pattern=/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0,5-9])|(18[0,5-9]))\d{8}$/; */
			if ($("#deviceAddress").val() == "") {

				$("#deviceAddress").tips({
					side : 2,
					msg : '红外地址码不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#deviceAddress").focus();
				return false;
			} else {
				$("#deviceAddress").val(jQuery.trim($('#deviceAddress').val()));
			}
			
			if($("#validationCode").val() == ""){
				$("#validationCode").tips({
					side : 2,
					msg : '验证码不能未空！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#validationCode").val(jQuery.trim($('#validationCode').val()));
			}			
			$("#loginbox").tips({
				side : 1,
				msg : '正在修改用户信息 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}
		
		//服务器校验
		function save(){
			if(check()){
			/* debugger */
				/* alert("被点击。。。") */
				var deviceAddress=$("#deviceAddress").val();
				var validationCode=$("#validationCode").val();
				
				$.ajax({
					url:"${pageContext.request.contextPath}/addDeviceRed.do",
			    	data: {"deviceAddress":deviceAddress,"validationCode":validationCode },
					type: "POST",
					dataType:"json",
					async: false,	
					/* cache: false, */
					success: function(data){
						/* alert(data) */
						if(data != null){
							alert("保存成功...")
							//调到手机验证码页面  (未完成)
							window.history.back();
						}else{
							alert("修改失败！");
							//回到初始值
							$("#deviceAddress").val("");
							$("#validationCode").val("");
						} 
					},
					error: function(e){  	 
	                	/* alert(XMLHttpRequest.status);//200
	                	alert(XMLHttpRequest.readyState);//4       － （完成）响应内容解析完成，可以在客户端调用了
	               	    alert(textStatus);//parsererror */
	               	    /* debugger */
	               	    /* alert(e) */
					}
				});
				}
				
			}
		/* }; */
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