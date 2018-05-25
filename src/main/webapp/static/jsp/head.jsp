<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String WEBPATH2 = request.getContextPath();
    String UserID=request.getParameter("UserID");
%>
<head>			
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body >
	<div class="navbar navbar-inverse">
		  <div class="navbar-inner">
		   <div class="container-fluid">
			  <a class="brand"><small><i class="icon-leaf"></i> 深圳-智能屋</small> </a>
			  
			  <ul class="nav ace-nav pull-right">
					<li class="grey">
					</li>
					<li class="green">
					</li>
					<li class="light-blue user-profile">
						<a class="user-menu dropdown-toggle" href="javascript:;" data-toggle="dropdown">
							<img alt="FH" src="<%=WEBPATH2 %>/static/images/avatars/user.jpg" class="nav-user-photo" />
							<span id="user_info">
								
							</span>
							<i class="icon-caret-down"></i>
						</a>
						<ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
							<li><a href="<%=WEBPATH2 %>/modifyPwd-in.jsp?User_ID=<%=UserID %>" style="cursor:pointer;"><i class="icon-user"></i> 修改密码</a></li>
							<li><a href="<%=WEBPATH2 %>/editInfo.jsp?User_ID=<%=UserID %>" style="cursor:pointer;"><i class="icon-user"></i> 编辑资料</a></li>
							<li><a href="<%=WEBPATH2 %>/auth.jsp?User_ID=<%=UserID %>" style="cursor:pointer;"><i class="icon-user"></i> 授权</a></li>
							<li class="divider"></li>
							<li><a href="logout.do"><i class="icon-off"></i> 退出</a></li>
						</ul>
					</li>
			  </ul><!--/.ace-nav-->
		   </div><!--/.container-fluid-->
		  </div><!--/.navbar-inner-->
		</div>
</body>
<!--/.navbar-->
<!--引入属于此页面的js -->
<script type="text/javascript" src="<%=WEBPATH2 %>/static/js/myjs/head.js"></script>
<script type="text/javascript">
    /* $(document).ready(function(){ */
    	$(function(){
 //   		alert("left子页面的元素定位："+$("#fhindex a").text())子页面之间也可以定位
//    		var loginName=request.getParameter("loginName");这里也不能捕获URL中的参数
//    		var loginPwd=request.getParameter("loginPwd");
    		<%-- alert("loginName:"+"<%=loginName %>"); --%>
    		$.ajax({
    			url: "getSysUser.do",
    	    	data: {"UserID":"<%=UserID %>"},
    			type: "POST",
    			dataType:"json",
    			//beforeSend: validateData,
    			cache: false,
    			success: function(data){
   					 $("#user_info").html('<small>Welcome</small> '+data+'');
    			}
    		});
    	});
    //修改个人资料(不包括密码)
	function editUserH(){
////	 alert(locat+'/logout.do')   // http://localhost:8080/smarthomeMavenWebProject/logout.do
	 	jzts();
	 	var diag = new top.Dialog();
	 	diag.Drag=true;
	 	diag.Title ="个人资料";
	 	diag.URL ="<%=WEBPATH2 %>/modifyUserInfo.do?USER_ID=<%=UserID %>";
	 	diag.Width = 225;
	 	diag.Height = 389;
	 	diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}
  //显示加载进度
    function jzts(){
    	$("#jzts").show();
    }
</script>
