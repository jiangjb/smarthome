<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String WEBPATH = request.getContextPath();
    String USER_ID=request.getParameter("User_ID");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>权限分配</title>
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
					<h4 id="hello">
						
					</h4>
				
				<div class="control-group">
					<div class="controls">
						<div  class="main_input_box" style="margin-left:-126px;">
							<span id="role">要分配的角色:</span>
							<!-- <span>
								<input type="radio" name="role" value="admin" checked style="margin-left:10px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>管理员</font>
							</span>
							<span>
								<input type="radio" name="role" value="buyer"  style="margin-left:20px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>买家</font>
							</span>
							<span>
								<input type="radio" name="role" value="normal"  style="margin-left:20px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>一般用户</font>
							</span> -->
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input  type="text" name="name" id="name" placeholder="请输入要添加的用户的名称"/>
							</span>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input  type="text" name="userPhone" id="userPhone" placeholder="请输入相应的手机号码"/>
							</span>
						</div>
					</div>
				</div>
				
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="submit();" class="flip-link btn btn-info" id="to-recover"> 提 交 </a>
						 </span>
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
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		var role= '<%= session.getAttribute("role")%>';
	 		alert(role); 
	 		/* 根据用户的类型，所能分配的权限也不同 */
	 		if(role == "superadmin"){
	 			$("#hello").html("hello,超级管理员");
	 			$("#role").append(
 					'<span>'+
						'<input type="radio" name="role" value="admin" checked style="margin-left:10px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>管理员</font>'+
					'</span>'+
					'<span>'+
						'<input type="radio" name="role" value="buyer"  style="margin-left:20px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>买家</font>'+
					'</span>'+
					'<span>'+
						'<input type="radio" name="role" value="normal"  style="margin-left:20px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>一般用户</font>'+
					'</span>'
	 			);
	 		}else if(role == "admin"){
	 			$("#hello").html("hello,管理员");
	 			$("#role").append(
 					'<span>'+
						'<input type="radio" name="role" value="normal" checked style="margin-left:20px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>一般用户</font>'+
					'</span>'
	 			);
	 		}else if(role == "buyer"){
	 			$("#hello").html("hello,管理员");
	 			$("#role").append(
 					'<span>'+
						'<input type="radio" name="role" value="normal" checked style="margin-left:20px;opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>一般用户</font>'+
					'</span>'
	 			);
	 		}
		});
		
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
			//用户名
			pattern=/^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/;
			if ($("#name").val() == "") {

				$("#name").tips({
					side : 2,
					msg : '用户名不能为空！',
					bg : '#AE81FF',
					time : 3
				});

				$("#name").focus();
				return false;
			} else if(!pattern.test($("#name").val())){
				//这里匹配正则
				$("#name").tips({
					side : 2,
					msg : '用户名只能是1-16位的英文字母、数字以及汉字组合！',
					bg : '#AE81FF',
					time : 3
				});
				return false;
			}else {
				$("#name").val(jQuery.trim($('#name').val()));
			}
			return true;
		}
		
		//服务器校验
		function submit(){
			var role=$('input:radio:checked').val();
			var name=$('#name').val();
			var userPhone=$('#userPhone').val();
			if(check()){
				$.ajax({
					url:"permissionAssignment.do",
			    	data: {"role":role,"name":name,"userPhone":userPhone },
					type: "POST",
					dataType:"json",
					async: false,	
					/* cache: false, */
					success: function(data){
						/* alert(data) */
						if("success" == data){
							alert("授权成功");
							window.history.back(-1);
						}else if("Exsit" == data){
							alert("该手机号绑定用户已存在，请更换手机号");
							$("#userPhone").val("");
							$("#userPhone").focus();
						}else{
							alert("授权失败！");
							//回到初始值
							$("#name").val("");
							$("#userPhone").val("");
							$("#name").focus();
						} 
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