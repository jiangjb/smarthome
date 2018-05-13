<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
  
<%
	String WEBPATH11 = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ WEBPATH11 + "/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
		<%@ include file="/static/jsp/top.jsp"%> 
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1" name="viewport" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	  	<style>
		    body { font-size: 62.5%; }
		    label, input { display:block; }
		    input.text { margin-bottom:12px; width:95%; padding: .4em; } 
		    fieldset { padding:0; border:0; margin-top:25px; }
		    h1 { font-size: 1.2em; margin: .6em 0; }
		    div#users-contain { width: 350px; margin: 20px 0; }
		    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		    .ui-dialog .ui-state-error { padding: .3em; }
		    .validateTips { border: 1px solid transparent; padding: 0.3em; }
	  </style>
	</head>
<body>
<div class="container-fluid" id="main-container">

<!-- jquery ui dialog -->
<div id="dialog-form" title="创建新用户">
  <p class="validateTips">请完善下面的字段信息,<font color="red">*</font>修饰的为必填项</p>
 
  <form action="" method="post">
	  <fieldset>
	    <label for="name">昵称</label><input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" placeholder="这里输入用户昵称">
	    <label for="userPhone"><font color="red">*</font>手机号</label><input type="text" name="userPhone" id="userPhone" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入手机号">
	    <label for="password"><font color="red">*</font>密码</label><input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入密码">
	    <label for="email">邮箱</label><input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入邮箱">
	  </fieldset>
  </form>
</div>

<div id="changeAccount" title="移交账户">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	  	<input type="text" name="userPhone0" id="userPhone0" value="" class="text ui-widget-content ui-corner-all" readonly="true">
	    <input type="text" name="userPhone1" id="userPhone1" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入要移交的手机号">
	  </fieldset>
  </form>
</div>
 

<div id="page-content" class="clearfix">			
	<div class="row-fluid">
			<!-- 检索  -->
			<form action="" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="userPhone" value="" placeholder="这里输入手机号" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"><input type="button" style="border:none;height:28px;" style="border:none;" value="搜索"  onclick="search();"  title="检索"></input></td>
				</tr>
			</table>
			<!-- 检索  -->
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<!-- <th class="center">序号</th> -->
						<!-- <th class="center">用户表示</th> -->
						<th class="center">昵称</th>
						<th class="center">性别</th>
						<th class="center">手机号</th>
						<th class="center">最后登录手机</th>
						<th class="center">账号使用的软件</th>
						<th class="center">签名</th>
						<th class="center">操作</th>
						<%-- <shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> --%>
					</tr>
				</thead>
										
				<tbody id="userslist">
					
				<!-- 开始循环 -->	
				<%-- <c:choose> --%>

					<%-- <c:when test="${not empty varList}"> --%>
						<%-- <c:if test="${QX.cha == 1 }"> --%>
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<%-- <td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.userId}" /><span class="lbl"></span></label>
								</td> --%>
								<%-- <td class='center' style="width: 30px;">${vs.index+1}</td> --%>
										<%-- <td>${var.USER_CODE}</td> --%>
										<td>${var.userName}</td>
										<td>${var.userSex}</td>
										<td>${var.userPhone}</td>
										<td>
											<c:if test="${var.phoneType==0}">安卓</c:if>
											<c:if test="${var.phoneType==1}">苹果</c:if>
										</td>
										
										<td><%--  --%>
											<c:if test="${var.versionType==1}">易家智联/掌上智家</c:if>
											<c:if test="${var.versionType==2}">爱波瑞</c:if>
										</td>
										<td>${var.signature}</td>
										<td>${var.city}</td>
								 <%-- <td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<c:if test="${QX.edit == 1 }">
											<li><a style="cursor:pointer;" title="编辑" onclick="edit('${var.USER_ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											</c:if>
											<c:if test="${QX.del == 1 }">
											<li><a style="cursor:pointer;" title="删除" onclick="del('${var.USER_ID}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
											</c:if>
										</ul>
										</div>
									</div>
								</td> --%>
							</tr>
						</c:forEach>
						<%-- </c:if> --%>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					<%-- </c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose> --%>
					
				
				</tbody>
			</table>
		</form>
		<div class="page-header position-relative">
			<span><button id="create-user" class="btn btn-small btn-success">新增</button></span>
			<span><a class="btn btn-small btn-danger"  onclick="delSelected();" title="批量删除" ><i class="icon-trash"></i></a></span>
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="userlist01">
							
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<!-- <a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a> -->
		
		<!-- 引入 -->
		<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
		<link rel="stylesheet" href="<%=WEBPATH11 %>/static/css/jquery-ui-1.9.2.custom.css" />
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH11 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH11 %>/static/js/jquery.cookie.js"></script>
		<script src="<%=WEBPATH11 %>/static/js/jquery-ui-1.9.2.custom.min.js"></script>
		<script src="<%=WEBPATH11 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH11 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH11 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH11 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH11 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH11 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH11 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		$(function(){
			var role= '<%= session.getAttribute("role")%>';
			var tel='<%= session.getAttribute("userPhone")%>';
			/* var firstTime=$.cookie('firstTime'); */
			/* alert(firstTime); */
			/* alert(tel); */
			if(tel!="null"){
				//显示房东或经销商
				$.ajax({
	    			url: "findUsersByPhone.do",
	    	    	data: {"userPhone":tel },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				/* alert("success") */
	    				/* alert(data[0].userId==null) */
	    				if(data[0].userId==null){
	    					$("#userlist01").empty();
	    					$("#userslist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');	
	    				}else{
		    				$.each(data,function(i,item){//i是key,item是value
		    					var currentPage=item.currentPage;
								var totalPages=item.totalPages;
								if(item.userId == null){
									//如果是page实体类  处理一
									
		   							var table="";
		   							if(totalPages ==1){
		   								table='<ul>'+
		           									'<li><a><font color="#808080">首页</font></a></li>'+
		           									'<li><a><font color="#808080">上页</font></a></li>'+
		           									'<li><a><font color="#808080">1</font></a></li>'+
		           									'<li><a><font color="#808080">下页</font></a></li>'+
		           									'<li><a><font color="#808080">尾页</font></a></li>'+
		           								'</ul>';
		   							}else if(totalPages ==2){
		   								table='<ul>'+
		            								'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">尾页</a></li>'+
		           								'</ul>';
		   							}else if(totalPages ==3){
		   								table='<ul>'+
		           								'<li><a><font color="#808080">首页</font></a></li>'+
		       									'<li><a><font color="#808080">上页</font></a></li>'+
		       									'<li><a><font color="#808080">1</font></a></li>'+
		       									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		       									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
		       									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		       									'<li style="cursor:pointer;"><a onclick="find(3)">尾页</a></li>'+
		           								'</ul>';
		   							}else if(totalPages ==4){
		   								table='<ul>'+
		            								'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(4)">尾页</a></li>'+
		           								'</ul>';
		   							}else{//totalPages >= 5 时
		   								table='<ul>'+
		            								'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
		           								'</ul>';
		   							}
		    						
									$("#userlist01").append(table);
								}else{
									/* alert(item.userId) */
			    					var phoneType="";
			    				    var versionType="";
			    			       /*  alert(typeof(item.phoneType));//number */			    
			    				    if(item.phoneType == 0){
			    				    	phoneType="安卓";
			    				    }else{
			    				    	phoneType="苹果";
			    				    }
			    				    if(item.versionType == 0){
			    				    	versionType="";
			    				    }else if(item.versionType == 1){
			    				    	versionType="易家智联/掌上智家";
			    				    }else if(item.versionType == 2){
			    				    	versionType="爱波瑞科技";
			    				    }else if(item.versionType == 3){
			    				    	versionType="";
			    				    }else if(item.versionType == 4){
			    				    	versionType="思创智能";
			    				    }else if(item.versionType == 5){
			    				    	versionType="峰庭智能";
			    				    }else if(item.versionType == 6){
			    				    	versionType="智能屋";
			    				    }else if(item.versionType == 7){
			    				    	versionType="乐沃智能";
			    				    }
			    				    var userPhone0=JSON.stringify(item.userPhone).replace(/\"/g,"'");
			  						 $("#userslist").append('<tr>'+
				    					'<td class="center" style="width: 30px;">'+
				    					'<label>'+
				    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
				    					'<span class="lbl">'+
				    					'</span>'+
				    					'</label>'+
				    					'</td>'+
				    					'<td class="center">'+item.userName+'</td>'+
				    					'<td class="center">'+item.userSex+'</td>'+
				    					'<td class="center">'+item.userPhone+'</td>'+
				    					'<td class="center">'+phoneType +'</td>'+
				    					'<td class="center">'+versionType+'</td>'+
				    					'<td class="center">'+item.signature+'</td>'+
				    					'<shiro:hasRole name="buyer">'+
					    					'<td style="width: 30px;" class="center">'+
    											'<div class="inline position-relative">'+
    												'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
    												'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
    													'<li><a onclick="clickOnMe('+userPhone0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
    													'<li><a style="cursor:pointer;" title="删除" onclick="delMe('+item.userId+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
    												'</ul>'+
    											'</div>'+
	    									'</td>'+
		       	    					'</shiro:hasRole>'+
				    					'</tr>'); 
								}
							})	
	    				}
	    			}
	    		});
			}else{
				
				$.ajax({
	    			url: "showUsers.do",
	    	    	data: { },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				/* alert("success") */
	    				$.each(data,function(i,item){//i是key,item是value
	    					var currentPage=item.currentPage;
							var totalPages=item.totalPages;
							if(item.userId == null){
								//如果是page实体类  处理一
								
	   							var table="";
	   							if(totalPages ==1){
	   								table='<ul>'+
	           									'<li><a><font color="#808080">首页</font></a></li>'+
	           									'<li><a><font color="#808080">上页</font></a></li>'+
	           									'<li><a><font color="#808080">1</font></a></li>'+
	           									'<li><a><font color="#808080">下页</font></a></li>'+
	           									'<li><a><font color="#808080">尾页</font></a></li>'+
	           								'</ul>';
	   							}else if(totalPages ==2){
	   								table='<ul>'+
	            								'<li><a><font color="#808080">首页</font></a></li>'+
	        									'<li><a><font color="#808080">上页</font></a></li>'+
	        									'<li><a><font color="#808080">1</font></a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">尾页</a></li>'+
	           								'</ul>';
	   							}else if(totalPages ==3){
	   								table='<ul>'+
	           								'<li><a><font color="#808080">首页</font></a></li>'+
	       									'<li><a><font color="#808080">上页</font></a></li>'+
	       									'<li><a><font color="#808080">1</font></a></li>'+
	       									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	       									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
	       									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	       									'<li style="cursor:pointer;"><a onclick="find(3)">尾页</a></li>'+
	           								'</ul>';
	   							}else if(totalPages ==4){
	   								table='<ul>'+
	            								'<li><a><font color="#808080">首页</font></a></li>'+
	        									'<li><a><font color="#808080">上页</font></a></li>'+
	        									'<li><a><font color="#808080">1</font></a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(4)">尾页</a></li>'+
	           								'</ul>';
	   							}else{//totalPages >= 5 时
	   								table='<ul>'+
	            								'<li><a><font color="#808080">首页</font></a></li>'+
	        									'<li><a><font color="#808080">上页</font></a></li>'+
	        									'<li><a><font color="#808080">1</font></a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
	           								'</ul>';
	   							}
	    						
								$("#userlist01").append(table);
							}else{
								/* alert(item.userId) */
		    					var phoneType="";
		    				    var versionType="";
		    			       /*  alert(typeof(item.phoneType));//number */			    
		    				    if(item.phoneType == 0){
		    				    	phoneType="安卓";
		    				    }else{
		    				    	phoneType="苹果";
		    				    }
		    				    if(item.versionType == 0){
		    				    	versionType="";
		    				    }else if(item.versionType == 1){
		    				    	versionType="易家智联/掌上智家";
		    				    }else if(item.versionType == 2){
		    				    	versionType="爱波瑞科技";
		    				    }else if(item.versionType == 3){
		    				    	versionType="";
		    				    }else if(item.versionType == 4){
		    				    	versionType="思创智能";
		    				    }else if(item.versionType == 5){
		    				    	versionType="峰庭智能";
		    				    }else if(item.versionType == 6){
		    				    	versionType="智能屋";
		    				    }else if(item.versionType == 7){
		    				    	versionType="乐沃智能";
		    				    }
		    				    var userPhone0=JSON.stringify(item.userPhone).replace(/\"/g,"'");
		    				    if(role == "admin"){
		    				    	var userPhone0=JSON.stringify(item.userPhone).replace(/\"/g,"'");
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					'<td class="center">'+item.userName+'</td>'+
					    					'<td class="center">'+item.userSex+'</td>'+
					    					'<td class="center">'+item.userPhone+'</td>'+
					    					'<td class="center">'+phoneType +'</td>'+
					    					'<td class="center">'+versionType+'</td>'+
					    					'<td class="center">'+item.signature+'</td>'+
					    					'<shiro:hasRole name="admin">'+
					    						'<td style="width: 30px;" class="center">'+
   													'<div class="inline position-relative">'+
   														'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
   														'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
   															'<li><a onclick="clickOnMe('+userPhone0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
   															'<li><a onclick="delMe('+item.userId+')" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
   														'</ul>'+
   													'</div>'+
    											'</td>'+
			       	    					'</shiro:hasRole>'+
					    					'</tr>'); 
		    				    }else{
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					'<td class="center">'+item.userName+'</td>'+
					    					'<td class="center">'+item.userSex+'</td>'+
					    					'<td class="center">'+item.userPhone+'</td>'+
					    					'<td class="center">'+phoneType +'</td>'+
					    					'<td class="center">'+versionType+'</td>'+
					    					'<td class="center">'+item.signature+'</td>'+
			       	    					'<td class="center"></td>'+
					    					'</tr>'); 
		    				    }
		  						 
							}
						})	
	    			}
	    		});
			}
			/* $.cookie('firstTime', 'no', { expires: 7 }); */
		});
		
		  $(function() {
			    var name = $( "#name" ),
			      userPhone=$("#userPhone"),
			      userPhone1=$("#userPhone1"),
			      password = $( "#password" ),
			      email = $( "#email" ),
			      allFields = $( [] ).add( name ).add(userPhone).add( password ).add( email ).add(userPhone1),
			      tips = $( ".validateTips" );
			 
			    function updateTips( t ) {
			      tips
			        .text( t )
			        .addClass( "ui-state-highlight" );
			      setTimeout(function() {
			        tips.removeClass( "ui-state-highlight", 1500 );
			      }, 500 );
			    }
			 
			     function checkLength( o, n, min, max ) {//对输入字段信息进行 长度判断
			      if ( o.val().length > max || o.val().length < min ) {
			        o.addClass( "ui-state-error" );
			        updateTips( "" + n + " 的长度必须在 " +
			          min + " 和 " + max + " 之间。" );
			        return false;
			      } else {
			        return true;
			      }
			    } 
			 
			    function checkRegexp( o, regexp, n ) {//对输入字段信息进行 正则判断
			      if ( !( regexp.test( o.val() ) ) ) {
			        o.addClass( "ui-state-error" );
			        updateTips( n );
			        return false;
			      } else {
			        return true;
			      }
			    } 
			 
			    $( "#dialog-form" ).dialog({
			      autoOpen: false,
			      height: 300,
			      width: 350,
			      modal: true,
			      buttons: {
			        "创建一个帐户": function() {
			          var bValid = true;
			          allFields.removeClass( "ui-state-error" );
			 
			          bValid = bValid && checkLength( name, "username", 1, 16 );
			          bValid = bValid && checkLength( password, "password", 5, 16 );
			          bValid = bValid && checkLength( email, "email", 6, 80 );
			 
			          bValid = bValid || checkRegexp( name, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "用户名必须是1-16位。" );// ||标识该字段可为空
			          // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
			          bValid = bValid && checkRegexp( userPhone, /^[1][3,4,5,7,8][0-9]{9}$/, "手机号字段以1为开头；第二位可为3,4,5,7,8,中的任意一位；最后以0-9的9个整数结尾。" );
			          bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "密码字段只允许： a-z 0-9" );
			          bValid = bValid || checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
			 
			          if ( bValid ) {
			            $( "#users tbody" ).append( "<tr>" +
			              "<td>" + name.val() + "</td>" +
			              "<td>" + userPhone.val() + "</td>" +
			              "<td>" + password.val() + "</td>" +
			              "<td>" + email.val() + "</td>" +
			            "</tr>" );
			            /* alert(name.val()); */
			            $.ajax({
			            	url:"<%=WEBPATH11 %>/addUser.do",
					    	data: {"name":name.val(), "email":email.val(),"password":password.val(),"userPhone":userPhone.val()}, 
					    	type: "POST",
							dataType:"json",
							async: true,
							success: function(data){
								if(data == "success"){
									/* console.log("操作成功"); */
									alert("操作成功");
								}else{
									alert("操作失败，请重试！");
								}
							}
			            });
			            
			            $( this ).dialog( "close" );
			          }
			        },
			        Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			      },
			      close: function() {
			        allFields.val( "" ).removeClass( "ui-state-error" );
			      }
			    });
			    
			    $( "#changeAccount" ).dialog({
				      autoOpen: false,
				      height: 300,
				      width: 350,
				      modal: true,
				      buttons: {
				        "移交账户": function() {
				          var bValid = true;
				          allFields.removeClass( "ui-state-error" );
				 
				          bValid = bValid && checkRegexp( userPhone1, /^[1][3,4,5,7,8][0-9]{9}$/, "手机号字段以1为开头；第二位可为3,4,5,7,8,中的任意一位；最后以0-9的9个整数结尾。" );
				          if ( bValid ) {
				            $( "#users tbody" ).append( "<tr>" +
				              "<td>" + userPhone1.val() + "</td>" +
				            "</tr>" );
				            /* alert(name.val()); */
				            var oldPhone=$("#userPhone0").val();
				           /*  alert("userPhone1:"+userPhone1.val()); */
				            $.ajax({
				            	url:"<%=WEBPATH11 %>/changeAccount.do",
						    	data: {"oldPhone":oldPhone, "newPhone":userPhone1.val()}, 
						    	type: "POST",
								dataType:"json",
								async: true,
								success: function(data){
									if(data == "success"){
										/* console.log("操作成功"); */
										alert("操作成功");
									}else{
										alert("操作失败，请重试！");
									}
								}
				            });
				            
				            $( this ).dialog( "close" );
				          }
				        },
				        Cancel: function() {
				          $( this ).dialog( "close" );
				        }
				      },
				      close: function() {
				        allFields.val( "" ).removeClass( "ui-state-error" );
				      }
				    });
			 
			    $( "#create-user" )
			      .button()
			      .click(function() {
			        $( "#dialog-form" ).dialog( "open" );
			      });
			  });
		  function clickOnMe(userPhone0){
				/* var id="create-user"+Id; */
				/* html中     id="create-user'+item.id+'" */
				$("#userPhone0").val(userPhone0);
			    $( "#changeAccount" ).dialog( "open" );
			};
		function delMe(id){
			$.ajax({
            	url:"<%=WEBPATH11 %>/delUser.do",
		    	data: {"id":id}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					if(data == "success"){
						/* console.log("操作成功"); */
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败，请重试！");
					}
				}
            });
		};
		//批量操作
		function delSelected(){
			$('input:checkbox:checked').each(function (index, item) {
				//逐个取出id
				/* alert($(this).val());   */
				delMe($(this).val());//通过input标签value值来传递delMe方法需要的参数
			});
		};
		//检索
		function search(){
			/* top.jzts();
			$("#Form").submit(); */
			var userPhone=$("#nav-search-input").val();
			if(userPhone != ""){
				$.ajax({
					url:"findByTel.do",
			    	data: {"userPhone":userPhone }, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						/* alert("success:"+data) */
						//清空标签内 [子标签及内容]，再添加
						$("#userslist").empty();
						$("#userlist01").empty();
						if(data == ""){
	    					$("#userslist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');
						}else{
							$.each(data,function(i,item){//i是key,item是value
		    					/* alert(item.userId) */
		    					var phoneType="";
		    				    var versionType="";
		    			       /*  alert(typeof(item.phoneType));//number */			    
		    				    if(item.phoneType == 0){
		    				    	phoneType="安卓";
		    				    }else{
		    				    	phoneType="苹果";
		    				    }
		    				    if(item.versionType == 0){
		    				    	versionType="";
		    				    }else if(item.versionType == 1){
		    				    	versionType="易家智联/掌上智家";
		    				    }else if(item.versionType == 2){
		    				    	versionType="爱波瑞科技";
		    				    }else if(item.versionType == 3){
		    				    	versionType="";
		    				    }else if(item.versionType == 4){
		    				    	versionType="思创智能";
		    				    }else if(item.versionType == 5){
		    				    	versionType="峰庭智能";
		    				    }else if(item.versionType == 6){
		    				    	versionType="智能屋";
		    				    }else if(item.versionType == 7){
		    				    	versionType="乐沃智能";
		    				    }
		    				    var userPhone0=JSON.stringify(item.userPhone).replace(/\"/g,"'");
		  						 $("#userslist").append('<tr>'+
			    					'<td class="center" style="width: 30px;">'+
			    					'<label>'+
			    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
			    					'<span class="lbl">'+
			    					'</span>'+
			    					'</label>'+
			    					'</td>'+
			    					'<td class="center">'+item.userName+'</td>'+
			    					'<td class="center">'+item.userSex+'</td>'+
			    					'<td class="center">'+item.userPhone+'</td>'+
			    					'<td class="center">'+phoneType +'</td>'+
			    					'<td class="center">'+versionType+'</td>'+
			    					'<td class="center">'+item.signature+'</td>'+
			    					'<shiro:hasRole name="admin">'+
			    						'<td style="width: 30px;" class="center">'+
											'<div class="inline position-relative">'+
												'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
													'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
														'<li><a onclick="clickOnMe('+userPhone0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
														'<li><a onclick="delMe('+item.userId+')" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
													'</ul>'+
											'</div>'+
										'</td>'+
	       	    					'</shiro:hasRole>'+
			    					'</tr>');  
		    				}) 
						}
			
				}, 
				error: function(e){
               	    /* alert(e) */
				}
			});
			}else{
				alert("请先输入手机号！");
				$("#nav-search-input").focus();
			}
		}
		
		//分页
		function find(index){
				/* alert(index); */
				var role= '<%= session.getAttribute("role")%>';
				/* alert(role); */
				$.ajax({
					url:"findByIndex.do",
			    	data: {"index":index }, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){  //list里的实体类都在这里面遍历
						/* alert("success:"+data) */
						//清空标签内 [子标签及内容]，再添加
						$("#userslist").empty();
						$.each(data,function(i,item){//i是key,item是value
							var currentPage=item.currentPage;
							var totalPages=item.totalPages;
							if(item.userId == null){
								//如果是page实体类  处理一
								$("#userlist01").empty();
								
								//根据 totalPages 和 currentPage 分成 5 种情况
								if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
									var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
								}else{//首页时 上页不能再选择
									var fore='<li><a><font color="#808080">上页</font></a></li>';
								}
								
								//先处理最后一页的分页信息
								if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
									if(currentPage == totalPages && currentPage !=1){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else{
										$("#userlist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}	
								}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
									if(currentPage == totalPages){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										if(currentPage == 1){
											$("#userlist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userlist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');	
										}
									}
								}else if( (totalPages%5==3) && ((currentPage==totalPages) || (currentPage==totalPages-1)|| (currentPage==totalPages-2)) ){
									if(currentPage == totalPages){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										if(currentPage == 1){
											$("#userlist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userlist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}
									}
								}else if( (totalPages%5==4) && ((currentPage==totalPages) || (currentPage==totalPages-1)|| (currentPage==totalPages-2)|| (currentPage==totalPages-3)) ){
									if(currentPage == totalPages){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 3){
										if(currentPage == 1){
											$("#userlist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userlist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}
									}
								}else{//取整 totalPages % 5 = 0   (总页数：5,10,15...)
									if(currentPage % 5 == 0){
										if(currentPage == totalPages){
											$("#userlist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-4)+')">'+(currentPage-4)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else{
											$("#userlist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-4)+')">'+(currentPage-4)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');	
										}
									}else if(currentPage % 5 == 1){
										if(currentPage == 1){
											$("#userlist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{	
											$("#userlist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+4)+')">'+(currentPage+4)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}
									}else if(currentPage % 5 ==  2){
										/* alert("2") */
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage % 5 == 3){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage % 5 == 4){
										$("#userlist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}
								}
								
							}else{
								//如果是BoUsers实体类 处理二
								/* alert(item.userId) */
		    					var phoneType="";
		    				    var versionType="";
		    			       /*  alert(typeof(item.phoneType));//number */			    
		    				    if(item.phoneType == 0){
		    				    	phoneType="安卓";
		    				    }else{
		    				    	phoneType="苹果";
		    				    }
		    				    if(item.versionType == 0){
		    				    	versionType="";
		    				    }else if(item.versionType == 1){
		    				    	versionType="易家智联/掌上智家";
		    				    }else if(item.versionType == 2){
		    				    	versionType="爱波瑞科技";
		    				    }else if(item.versionType == 3){
		    				    	versionType="";
		    				    }else if(item.versionType == 4){
		    				    	versionType="思创智能";
		    				    }else if(item.versionType == 5){
		    				    	versionType="峰庭智能";
		    				    }else if(item.versionType == 6){
		    				    	versionType="智能屋";
		    				    }else if(item.versionType == 7){
		    				    	versionType="乐沃智能";
		    				    }
		    				    var userPhone0=JSON.stringify(item.userPhone).replace(/\"/g,"'");
		    				    if(role == "admin"){
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					'<td class="center">'+item.userName+'</td>'+
					    					'<td class="center">'+item.userSex+'</td>'+
					    					'<td class="center">'+item.userPhone+'</td>'+
					    					'<td class="center">'+phoneType +'</td>'+
					    					'<td class="center">'+versionType+'</td>'+
					    					'<td class="center">'+item.signature+'</td>'+
					    					'<shiro:hasRole name="admin">'+
						    					'<td style="width: 30px;" class="center">'+
													'<div class="inline position-relative">'+
														'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
															'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
																'<li><a onclick="clickOnMe('+userPhone0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
																'<li><a onclick="delMe('+item.userId+')" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
															'</ul>'+
													'</div>'+
												'</td>'+
				       	    				'</shiro:hasRole>'+
					    					'</tr>'); 
		    				    }else if(role == "buyer"){
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					'<td class="center">'+item.userName+'</td>'+
					    					'<td class="center">'+item.userSex+'</td>'+
					    					'<td class="center">'+item.userPhone+'</td>'+
					    					'<td class="center">'+phoneType +'</td>'+
					    					'<td class="center">'+versionType+'</td>'+
					    					'<td class="center">'+item.signature+'</td>'+
					    					'<shiro:hasRole name="buyer">'+
					    						'<td style="width: 30px;" class="center">'+
													'<div class="inline position-relative">'+
														'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
															'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
																'<li><a onclick="clickOnMe('+userPhone0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
																'<li><a onclick="delMe('+item.userId+')" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
															'</ul>'+
													'</div>'+
												'</td>'+
				       	    				'</shiro:hasRole>'+
					    					'</tr>'); 
		    				    }else{
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.userId+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					'<td class="center">'+item.userName+'</td>'+
					    					'<td class="center">'+item.userSex+'</td>'+
					    					'<td class="center">'+item.userPhone+'</td>'+
					    					'<td class="center">'+phoneType +'</td>'+
					    					'<td class="center">'+versionType+'</td>'+
					    					'<td class="center">'+item.signature+'</td>'+
				       	    				'<td class="center"></td>'+
					    					'</tr>'); 
		    				    }
		  						 
		    				}
						})								    					
				}, 
				error: function(e){
               	    /* alert(e) */
				}
			});
		};
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
		});
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>users/excel.do';
		};
		
		
		</script>
		
	</body>
</html>

