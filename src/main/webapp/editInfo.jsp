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
<title>修改个人信息</title>
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
						<img src="" alt="修改个人信息" />
					</h>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="loginName" id="loginName"  value="" />
							</span>
							<span>
								<input type="hidden" name="loginName1" id="loginName0"  value="" />
							</span>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="userName" id="userName"  value="" />
							</span>
							<span>
								<input type="hidden" name="userName1" id="userName0"  value="" />
							</span>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="userPhone" id="userPhone"  value="" />
							</span>
							<span>
								<input type="hidden" name="userPhone1" id="userPhone0"  value="" />
							</span>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="email" id="email"  value="" />
							</span>
							<span>
								<input type="hidden" name="email1" id="email0"  value="" />
							</span>
						</div>
					</div>
				</div>
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="modifyUserInfo();" class="flip-link btn btn-info" id="to-recover"> 提 交 </a>
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
	 	$(document).ready(function(){
			/* changeCode();
			$("#codeImg").bind("click", changeCode); */
			/* $("#email").val("1768101847@qq.com"); */
//	    		var loginName=request.getParameter("loginName");这里也不能捕获URL中的参数
//	    		var loginPwd=request.getParameter("loginPwd");
	    		$.ajax({
	    			url:"findUserInfo.do",
	    	    	data:{"USER_ID":"<%=USER_ID %>"},
	    			type: "POST",
	    			dataType:"json",
	    			cache: false,
	    			async:false,
	    			success: function(data){
	    				/* alert("success"); */
	    				$.each(data,function(i,item){//i是key,item是value
	    					if("用户名" == i){
	    						$("#loginName").val(item);
	    						$("#loginName0").val(item);
	    					}else if("别名" == i){
	    						$("#userName").val(item);
	    						$("#userName0").val(item);
	    					}else if("手机" == i){
	    						$("#userPhone").val(item);
	    						$("#userPhone0").val(item);
	    					}else if("邮箱" == i){
	    						$("#email").val(item);
	    						$("#email0").val(item);
	    					}else{
	    						alert("error")
	    					}
	    						
	    					/* alert(i+"="+item); */
	    				})
	    			}
	    		});
		});
		
		//客户端校验
		function check(){
			//邮箱验证
			var pattern=/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
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
			
			//用户名验证
			pattern=/^[a-zA-Z0-9_-]{3,16}$/;
			if ($("#loginName").val() == "") {

				$("#loginName").tips({
					side : 2,
					msg : '用户名不能为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginName").focus();
				return false;
			} else if(!pattern.test($("#loginName").val())){
				//这里匹配正则
				$("#loginName").tips({
					side : 2,
					msg : '用户名只支持3-16位由字母、数字、下划线、减号和英文句号组成的字符串！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#loginName").val(jQuery.trim($('#loginName').val()));
			}
			
			//别名验证
			pattern= /^[\u4E00-\u9FA5A-Za-z]+$/;
			if ($("#userName").val() == "") {

				$("#userName").tips({
					side : 2,
					msg : '别名不能为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#userName").focus();
				return false;
			} else if(!pattern.test($("#userName").val())){
				//这里匹配正则
				$("#userName").tips({
					side : 2,
					msg : '只能输入中文和英文！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#userName").val(jQuery.trim($('#userName').val()));
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
		function modifyUserInfo(){
			if(check()){
			/* debugger */
				/* alert("被点击。。。") */
				var loginName=$("#loginName").val();
				var userName=$("#userName").val();
				var userPhone=$("#userPhone").val();
				var email = $("#email").val();
				//初始的值
				var loginName0=$("#loginName0").val();
				var userName0=$("#userName0").val();
				var userPhone0=$("#userPhone0").val();
				var email0 = $("#email0").val();
				if(loginName0 !=loginName || userName0 != userName || userPhone0 != userPhone || email0 !=email){//任意一个改动 ，才进入ajax
					$.ajax({
						url:"modifyUserInfo.do",
				    	data: {"USER_ID":"<%=USER_ID %>","loginName":loginName,"userName":userName,"userPhone":userPhone,"email":email },
						type: "POST",
						dataType:"json",
						async: false,	
						/* cache: false, */
						success: function(data){
							/* alert(data) */
							if("success" == data){
								/* saveCookie(); */
								alert("修改成功...")
								<%-- setTimeout(function(){ 
									window.location.href="<%=WEBPATH %>/static/jsp/head.jsp";
										},1000);  --%>
								window.location.href="<%=WEBPATH %>/static/jsp/head.jsp";//js,css会被拦截
								
							}else{
								alert("修改失败！");
								//回到初始值
								$("#loginName").val(userPhone0);
								$("#userName").val(userName0);
								$("#userPhone").val(userPhone0);
								$("#email").val(email0);
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