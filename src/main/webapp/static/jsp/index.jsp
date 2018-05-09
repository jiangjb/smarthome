<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String WEBPATH = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ WEBPATH + "/";
%>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >  
<head>
<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
	<style type="text/css">
	.commitopacity{position:absolute; width:100%; height:100px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.8; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
	</style>
	
	<!-- 即时通讯 -->
	<!-- <script type="text/javascript">var wimadress="${pd.WIMIP}:${pd.WIMPORT}";</script>
	<script type="text/javascript">var oladress="${pd.OLIP}:${pd.OLPORT}";</script>
	<link rel="stylesheet" type="text/css" href="<%=WEBPATH %>/static/plugins/websocketInstantMsg/ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="<%=WEBPATH %>/static/plugins/websocketInstantMsg/css/websocket.css" />
	<script type="text/javascript" src="<%=WEBPATH %>/static/plugins/websocketInstantMsg/ext4/ext-all-debug.js"></script>
	<script type="text/javascript" src="<%=WEBPATH %>/static/plugins/websocketInstantMsg/websocket.js"></script> -->
	<!-- 即时通讯 -->
	
</head>
<body>

	<!-- 页面顶部¨ -->
	<%@ include file="head.jsp"%>
	<div id="websocket_button"></div>
	<div class="container-fluid" id="main-container">
		<a href="#" id="menu-toggler"></a>
		<!-- menu toggler -->

		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<div id="main-content" class="clearfix">

			<div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
			<div class="commitopacity" id="bkbgjz"></div>
			<div style="padding-left: 70%;padding-top: 1px;">
				<div style="float: left;margin-top: 3px;"><img src="<%=WEBPATH %>/static/images/loadingi.gif" /> </div>
				<div style="margin-top: 5px;"><h4 class="lighter block red">&nbsp;加载中 ...</h4></div>
			</div>
			</div>

			<div>
				<!-- <iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe> -->
				<%-- <iframe name="mainFrame" id="mainFrame" frameborder="0"  src="<%=WEBPATH %>/static/jsp/FisrtPage.jsp" style="margin:0 auto;width:100%;height:100%;"></iframe> --%>
				<iframe name="mainFrame" id="mainFrame" frameborder="0"  style="margin:0 auto;width:100%;height:100%;"></iframe>
			</div>

			<!-- 换肤 -->
			<div id="ace-settings-container">
				<div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
					<i class="icon-cog"></i>
				</div>
				<div id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hidden">
								<option data-class="default" value="#438EB9"
									<c:if test="${user.SKIN =='default' }">selected</c:if>>#438EB9</option>
								<option data-class="skin-1" value="#222A2D"
									<c:if test="${user.SKIN =='skin-1' }">selected</c:if>>#222A2D</option>
								<option data-class="skin-2" value="#C6487E"
									<c:if test="${user.SKIN =='skin-2' }">selected</c:if>>#C6487E</option>
								<option data-class="skin-3" value="#D0D0D0"
									<c:if test="${user.SKIN =='skin-3' }">selected</c:if>>#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>
					<div>
						<label><input type='checkbox' name='menusf' id="menusf"
							onclick="menusf();" /><span class="lbl" style="padding-top: 5px;">菜单缩放</span></label>
					</div>
				</div>
			</div>
			<!--/#ace-settings-container-->

		</div>
		<!-- #main-content -->
		<!-- <div id="page-content" class="clearfix">
			<h2>首页</h2>
		</div> -->
		
		
	</div>
	
	<!--/.fluid-container#main-container-->
	<!-- basic scripts -->
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH %>/static/js/ace.min.js"></script>
		<!-- 引入 -->
		
		<script type="text/javascript" src="<%=WEBPATH %>/static/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=WEBPATH %>/static/js/myjs/menusf.js"></script>
		
		<!--引入属于此页面的js -->
		<script type="text/javascript" src="<%=WEBPATH %>/static/js/myjs/index.js"></script>
		<script type="text/javascript">
		//决定了框架中id="mainFrame"标签的显示
			$(function(){	
				//alert($("#fhindex a").text().replace(/\s+/g,""));//去掉所有空格
				var id=$("#fhindex a").text();
				if(id == "后台首页"){
					$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/FisrtPage.jsp");
				}
			})
		</script>
		<script type="text/javascript">
				$(function(){
					//登录验证码页面和红外转发器参数页面没有有用，暂且隐藏
					/* $("#z29").hide();
					$("#z27").hide(); */
					<%-- var role= '<%= session.getAttribute("role")%>';
					if(role == "buyer"){
						$("#z29").hide();
						$("#z27").hide();
					}  --%>
				})
				function toIndex(){
					/* alert($("#mainFrame").text()); */
					$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/FisrtPage.jsp");
				}
				//菜单状态切换(用户管理、主机管理和设备管理)
				var fmid = "fhindex";
				var mid = "fhindex";
				 
				function siMenu(id,fid,MENU_NAME,MENU_URL){//四个参数缺一不可，id是子标签的，fid是父标签的 两个都要设置class属性
					var session= '<%= session.getAttribute("role")%>';
					/* alert("session:"+session); */
					if(MENU_NAME == "全部用户"){
						<%-- window.location.href ="<%=WEBPATH%>/showUsers.do"; --%>
						$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/users/users_list.jsp")
					}else if(MENU_NAME =="登陆验证码"){
						$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/users_validation/users_validation_list.jsp")
					}else if(MENU_NAME =="全部主机"){
						$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/device/device_list.jsp")
					}else if(MENU_NAME =="用户绑定主机解绑"){
						$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/user_devices/user_devices_list.jsp")
					}else if(MENU_NAME =="全部设备"){
						$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/host_device/host_device_list.jsp")
					}else if(MENU_NAME =="红外转发器参数"){
						$("#mainFrame").attr("src","<%=WEBPATH %>/static/jsp/system/infrared_part/infrared_part_list.jsp")
					}else{
						alert("error");
					}
					if(id != mid){
						$("#"+mid).removeClass();
						mid = id;
					}
					if(fid != fmid){
						$("#"+fmid).removeClass();
						fmid = fid;
					}
					$("#"+fid).attr("class","active open");
					$("#"+id).attr("class","active");
				}
			</script>
</body>
</html>
