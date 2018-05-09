<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH426 = request.getContextPath();
    String userPhone=request.getParameter("userPhone");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="<%=WEBPATH426 %>/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=WEBPATH426 %>/static/css/camera.css" />
<link rel="stylesheet" href="<%=WEBPATH426 %>/static/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=WEBPATH426 %>/static/css/matrix-login.css" />
<link href="<%=WEBPATH426 %>/static/css/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="<%=WEBPATH426 %>/static/js/jquery-1.9.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >  
<title>更换账号</title>
</head>
<body>
	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm"
				id="loginForm">
				<div class="control-group normal_text">
					<h3>
						更换账号
					</h>
				
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="userPhone0" id="userPhone0"  value="<%=userPhone %>" readonly="true"/>
							</span>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="userPhone" id="userPhone"  value="" placeholder="请输入手机号"/>
							</span>
						</div>
					</div>
				</div>
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="submit();" class="flip-link btn btn-info" id="to-recover"> 提 交 </a>
						 </span>
						 <span
							class="pull-right"><a onclick="excel();" class="flip-link btn btn-info" id="to-recover"> 取消 </a>
						 </span>
						 <span 
							class="pull-left"><input type="button" class="flip-link btn btn-info" value="返回" onclick="javascript:history.back(1);">
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
		function submit(){
			if(check()){
				/* alert("通过客户端验证"); */
				var userPhone0=$("#userPhone0").val();
				var userPhone=$("#userPhone").val();
				/* alert("原来的号码："+userPhone0+",现在的号码："+userPhone); */
				if(userPhone !=userPhone0){
					$.ajax({
						type: "POST",
						url:"${pageContext.request.contextPath}/changeAccount.do",
				    	data: {"oldPhone":userPhone0,"newPhone":userPhone },
						dataType:"json",
						async: false,	
						success: function(data){
							/* alert(data) */
							if("success" == data){
								alert("修改成功...")
								//调到手机验证码页面
								window.history.back();
								<%-- $("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/users_validation/users_validation_list.jsp") --%>
							}else{
								alert("修改失败！");
								//回到初始值
								$('#userPhone').val("");
								$("#userPhone").focus();
							} 
						}
					});

				}
			}
		}
		//客户端校验
		function check(){
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
			/* alert($("#userPhone").val()); */
			return true;
		}
		
		function excel(){
	    	$('#userPhone').val("");
	    	$("#userPhone").focus();
		}
	</script>
	<script src="<%=WEBPATH426 %>/static/js/bootstrap.min.js"></script>
	<script src="<%=WEBPATH426 %>/static/js/jquery.easing.1.3.js"></script>
	<script src="<%=WEBPATH426 %>/static/js/jquery.mobile.customized.min.js"></script>
	<script src="<%=WEBPATH426 %>/static/js/camera.min.js"></script>
	<script src="<%=WEBPATH426 %>/static/js/templatemo_script.js"></script>
	<script type="text/javascript" src="<%=WEBPATH426 %>/static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="<%=WEBPATH426 %>/static/js/jquery.cookie.js"></script>
</body>
</html>