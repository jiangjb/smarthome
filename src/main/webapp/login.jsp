<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<%=WEBPATH %>/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=WEBPATH %>/static/css/camera.css" />
<link rel="stylesheet" href="<%=WEBPATH %>/static/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=WEBPATH %>/static/css/matrix-login.css" />
<link href="<%=WEBPATH %>/static/css/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="<%=WEBPATH %>/static/js/jquery-1.9.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >  
<!-- <style type="text/css">
	a:hover{ color:green; }
</style> -->
</head>
<body>

	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i><img height="37" src="<%=WEBPATH %>/static/images/user.png" /></i>
							</span><input type="text" name="loginname" id="loginname" value="" placeholder="请输入账号" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i><img height="37" src="<%=WEBPATH %>/static/images/suo.png" /></i>
							</span><input type="password" name="password" id="password" placeholder="请输入密码" value="" />
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<div style="float:left;padding-left:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white"><a href="<%=WEBPATH %>/register.jsp" style="color:white;"  onmouseover="this.style.cssText='color:green;'" onmouseout="this.style.cssText='color:white;'">新用户注册</a></font><!--找到注册页面，注册页面写跳转Ajax  -->
					</div>
					
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white"><a href="<%=WEBPATH %>/TelOrEmail.jsp" style="color:white;" onmouseover="this.style.cssText='color:green; text-decoration:none;'" onmouseout="this.style.cssText='color:white;text-decoration:none'">找回密码</a></font>
					</div>
					
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">


						<span class="pull-right" style="padding-right:3%;"><a
							href="javascript:cancel();" class="btn btn-success">取消</a></span> <span
							class="pull-right"><a onclick="severCheck();"
							class="flip-link btn btn-info" id="to-recover">登录</a></span>

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
			//初始化页面时验证是否记住了密码
			if ($.cookie("rmbUser") == "true") {
			    $("#saveid").attr("checked", true);
				$("#loginname").val($.cookie("loginname"));
				$("#password").val($.cookie("password"));
			}
		});
		//服务器校验
		function severCheck(){
			if(check()){
			/* debugger */
				/* alert("被点击。。。") */
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				/* var code = "qq313596790fh"+loginname+",fh,"+password+"QQ978336446fh"+",fh,"+$("#code").val(); */
				/* alert("loginName="+loginname) */
				$.ajax({
					url:"login.do",
			    	data: {"loginName":loginname,"loginPwd":password }, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						/* alert("success:"+data) */
						if(data !=0){
							/* alert("success") */
							/* saveCookie(); */
							<%-- window.location.href="<%=WEBPATH %>/static/jsp/index.jsp"+"?loginName="+loginname+"&loginPwd="+password; //js中路径 --%>
							window.location.href="<%=WEBPATH %>/static/jsp/index.jsp?UserID="+data;
						}else if(data == 0){
							/* alert("usererror") */
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}else{
							/* alert("other") */
							$("#loginname").tips({
								side : 1,
								msg : "未配置栏目权限",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}  
					},
					/* error: function(XMLHttpRequest, textStatus, errorThrown){  */ 	 
					error: function(e){
	                	/* alert(XMLHttpRequest.status);//200
	                	alert(XMLHttpRequest.readyState);//4       － （完成）响应内容解析完成，可以在客户端调用了
	               	    alert(textStatus);//parsererror  */
	               	    /* debugger */
	               	    /* alert(e) */
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

		//客户端校验
		function check() {
			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		};

		function savePaw() {
			/* 勾选“记住密码”，点击登录，保存cookie，下次登录不需输入；
			不勾“记住密码”，点击登录，不保存cookie，下次登录需输入； */
			if ($("#saveid").attr("checked") == true) {
				        var userName = $("#loginname").val();
				        var passWord = $("#password").val();
				        $.cookie("saveid", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
				        $.cookie("loginname", userName, { expires: 7 }); // 存储一个带7天期限的 cookie
				        $.cookie("password", passWord, { expires: 7 }); // 存储一个带7天期限的 cookie
				    }
				    else {
				        $.cookie("saveid", "false", { expires: -1 });        // 删除 cookie
				        $.cookie("loginname", '', { expires: -1 });
				        $.cookie("password", '', { expires: -1 });
				    }
			
		}

		/* function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		} */
		function cancel() {/* 取消   即将用户名和密码清空 */
			$("#loginname").val('');
			$("#password").val('');
		}
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
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