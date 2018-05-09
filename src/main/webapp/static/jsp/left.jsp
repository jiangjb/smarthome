<%
	String WEBPATH3 = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+WEBPATH3+"/";
%>
		<!-- 本页面涉及的js函数，都在head.jsp页面中     -->
		<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >  
</head>
		<!-- 本页面涉及的js函数，都在head.jsp页面中     -->
		<div id="sidebar" class="menu-min">

				<div id="sidebar-shortcuts">

					<div id="sidebar-shortcuts-large">

						<button class="btn btn-small btn-success" onclick="changeMenu();" title="切换菜单"><i class="icon-pencil"></i></button>

						<button class="btn btn-small btn-info" title="UI实例" onclick="window.open('<%=WEBPATH3%>/static/UI_new');"><i class="icon-eye-open"></i></button>

						<button class="btn btn-small btn-warning" title="数据字典" id="adminzidian" onclick="zidian();"><i class="icon-book"></i></button>
						
						<button class="btn btn-small btn-danger" title="菜单管理" id="adminmenu" onclick="menu();"><i class="icon-folder-open"></i></button>
						
					</div>

					<div id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>

				</div><!-- #sidebar-shortcuts -->


				<ul class="nav nav-list">

					<li id="fhindex"  class="active">
					  <a onclick="toIndex()"><i class="icon-dashboard"></i><span>后台首页</span></a>
					</li>																																																												
					<li id="lm24">
					  <a style="cursor:pointer;" class="dropdown-toggle">
						<i class="icon-list"></i>
						<span>用户管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
					  	<li id="z25">
							<!-- <a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z25','lm24','全部用户','/showUsers.do')"> -->
							<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z25','lm24','全部用户','/showUsers.do');">
								<i class="icon-double-angle-right">
								</i>全部用户</a></li>
								<li id="z29">
									<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z29','lm24','登陆验证码','/showTelValidations.do')">
										<i class="icon-double-angle-right"></i>登陆验证码</a></li>	
				  	  			</ul>
				   		</li>
				   
				   <li id="lm22">
					  <a style="cursor:pointer;" class="dropdown-toggle">
						<i class="icon-list"></i>
						<span>主机管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
							<li id="z23">
								<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z23','lm22','全部主机','/showHostDevices.do')">
									<i class="icon-double-angle-right"></i>全部主机</a></li>
									<li id="z30">
										<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z30','lm22','用户绑定主机解绑','/showUserDevices.do')">
											<i class="icon-double-angle-right"></i>主机解绑</a></li>	<!-- 用户绑定主机解绑		 -->
				  	  </ul>
				  </li>
				
				  <li id="lm26">
					  <a style="cursor:pointer;" class="dropdown-toggle">
						<i class="icon-list"></i>
						<span>设备管理</span>
						<b class="arrow icon-angle-down"></b>
					  </a>
					  <ul class="submenu">
					  	<li id="z28">
							<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z28','lm26','全部设备','/showDevices.do')">
								<i class="icon-double-angle-right"></i>全部设备</a></li>
								<li id="z27">
									<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z27','lm26','红外转发器参数','/showDevicesRed.do')">
										<i class="icon-double-angle-right"></i>红外转发器参数</a></li>						
				  	  </ul>
				  </li>
				</ul> <!--/.nav-list-->
				<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>
			</div><!--/#sidebar-->
			
			
		
