<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH = request.getContextPath();
   /*  String loginName=request.getParameter("loginName");
    String loginPwd=request.getParameter("loginPwd"); */
    /* int id=Integer.parseInt(request.getParameter("id")); */
    String signature=request.getParameter("signature");
    String id=request.getParameter("id");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>修改备注</title>
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
						<img src="" alt="修改用户备注" />
					</h>
				
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="signature" id="signature"  value="" />
							</span>
							<span>
								<input type="hidden" name="signature0" id="signature0"  value="" />
							</span>
						</div>
					</div>
				</div>
				
				
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						 <span
							class="pull-right"><a onclick="modify();" class="flip-link btn btn-info" id="to-recover"> 提  交 </a>
						 </span>
						 <span
							class="pull-right"><a onclick="excel();" class="flip-link btn btn-info" id="to-recover"> 取 消 </a>
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
	$(function(){
		$('#signature0').val("<%=signature %>");
		$('#signature').val("<%=signature %>");
	})
	//取消
    function excel(){
    	$('#signature').val($('#signature0').val());
    	$("#signature").focus();
    }
	
	//服务器校验
	function modify(){
		<%-- alert(<%=id %>); --%>
		/* debugger */
			/* alert("被点击。。。") */
			var signature=$("#signature").val();
			var signature0=$("#signature0").val();
			//alert(signature !=signature0)//进来后是true
			//alert("${pageContext.request.contextPath}/modifyBZ.do"); 
			if(signature !=signature0){
				$.ajax({
					url:"${pageContext.request.contextPath}/modifyBZ.do",//smarthome.IMCPlatform/static/jsp/modifyBZ.do 不加“${pageContext.request.contextPath}/”的话出现这个路径
			    	data: {"signature":signature,"id":<%=id %> },
					type: "POST",
					dataType:"json",
					async: false,	
					success: function(data){
						if("success" == data){
							alert("修改成功,等待页面跳转...")
							//调到手机验证码页面
							window.history.back();
						}else{
							alert("修改失败！");
							//回到初始值
							$('#signature').val($('#signature0').val());
						} 
					}
				});
			}
			
		
			
		}
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		/* function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		} */


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