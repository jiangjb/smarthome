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
					<!-- <li id="lm24" class="active open">
						<a style="cursor:pointer;" class="dropdown-toggle">
					    	<i class="icon-list"></i>  该句是icon显示的语句
							<span>用户管理</span>
							<b class="arrow icon-angle-down"></b>
					  </a>
					</li> -->
					<!-- 显示用户管理、主机管理和设备管理 （固定，写死）-->
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
										<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z30','lm22','用户绑定主机解绑','/showHostD.do')">
											<i class="icon-double-angle-right"></i>用户绑定主机解绑</a></li>			
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

				<!-- 显示用户管理、主机管理和设备管理 （灵活）-->
				<%-- <c:forEach items="${menuList}" var="menu">
					<c:if test="${menu.hasMenu}">
					<li id="lm${menu.MENU_ID }">
						  <a style="cursor:pointer;" class="dropdown-toggle" >
							<i class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}"></i>
							<span>${menu.MENU_NAME }</span>
							<b class="arrow icon-angle-down"></b>
						  </a>
						  <ul class="submenu">
								<c:forEach items="${menu.subMenu}" var="sub">
									<c:if test="${sub.hasMenu}">
									<c:choose>
										<c:when test="${not empty sub.MENU_URL}">
										<li id="z${sub.MENU_ID }">
										<a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('z${sub.MENU_ID }','lm${menu.MENU_ID }','${sub.MENU_NAME }','${sub.MENU_URL }')"><i class="icon-double-angle-right"></i>${sub.MENU_NAME }</a></li>
										</c:when>
										<c:otherwise>
										<li><a href="javascript:void(0);"><i class="icon-double-angle-right"></i>${sub.MENU_NAME }</a></li>
										</c:otherwise>
									</c:choose>
									</c:if>
								</c:forEach>
					  		</ul>
					</li>
					</c:if>
				</c:forEach>

				</ul> --%><!--/.nav-list-->

				<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>

			</div><!--/#sidebar-->
			
			
		
