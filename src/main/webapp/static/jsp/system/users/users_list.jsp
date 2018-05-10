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


<div id="page-content" class="clearfix">
						
<div id="dialog-form" title="创建新用户">
  <p class="validateTips">所有的表单字段都是必填的。</p>
  <form>
	  <fieldset>
	      <label for="name">名字</label>
	      <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">
	      <label for="email">邮箱</label>
	      <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all">
	      <label for="password">密码</label>
	      <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all">
	  </fieldset>
  </form>
</div>
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
						<th class="center">城市</th>
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
		<div class="page-header position-relative">
			<span><button id="create-user" class="btn btn-small btn-success">新增</button></span>
			<!-- <span><a class="btn btn-small btn-success"  onclick="add();">新增</a></span> -->
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
		</form>
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
			  						 $("#userslist").append('<tr>'+
				    					'<td class="center" style="width: 30px;">'+
				    					'<label>'+
				    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
				    					'<td class="center">'+item.city+'</td>'+
				    					'<shiro:hasRole name="buyer">'+
		       	    						'<td class="center"><a href="<%=WEBPATH11 %>/static/jsp/system/users/editPhone.jsp?userPhone='+item.userPhone+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
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
		    				    if(role == "admin"){
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
					    					'<td class="center">'+item.city+'</td>'+
					    					'<shiro:hasRole name="admin">'+
			       	    						'<td class="center"><a href="<%=WEBPATH11 %>/static/jsp/system/users/editPhone.jsp?userPhone='+item.userPhone+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
			       	    					'</shiro:hasRole>'+
					    					'</tr>'); 
		    				    }else{
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
					    					'<td class="center">'+item.city+'</td>'+
			       	    					'<td class="center"></td>'+
					    					'</tr>'); 
		    				    }
		  						 
							}
						})	
	    			}
	    		});
			}
		});
		
		//模态表单
		$(function(){
		    var name = $( "#name" ),
		      email = $( "#email" ),
		      password = $( "#password" ),
		      allFields = $( [] ).add( name ).add( email ).add( password ),
		      tips = $( ".validateTips" );
		 
		    function updateTips( t ) {
		      tips
		        .text( t )
		        .addClass( "ui-state-highlight" );
		      setTimeout(function() {
		        tips.removeClass( "ui-state-highlight", 1500 );
		      }, 500 );
		    }
		 
		    function checkLength( o, n, min, max ) {
		      if ( o.val().length > max || o.val().length < min ) {
		        o.addClass( "ui-state-error" );
		        updateTips( "" + n + " 的长度必须在 " +
		          min + " 和 " + max + " 之间。" );
		        return false;
		      } else {
		        return true;
		      }
		    }
		 
		    function checkRegexp( o, regexp, n ) {
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
		     
		              bValid = bValid && checkLength( name, "username", 3, 16 );
		              bValid = bValid && checkLength( email, "email", 6, 80 );
		              bValid = bValid && checkLength( password, "password", 5, 16 );
		     
		              bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "用户名必须由 a-z、0-9、下划线组成，且必须以字母开头。" );
		              // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
		              bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
		              bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "密码字段只允许： a-z 0-9" );
		     
		              if ( bValid ) {
		                $( "#users tbody" ).append( "<tr>" +
		                  "<td>" + name.val() + "</td>" +
		                  "<td>" + email.val() + "</td>" +
		                  "<td>" + password.val() + "</td>" +
		                "</tr>" );
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
		  						 $("#userslist").append('<tr>'+
			    					'<td class="center" style="width: 30px;">'+
			    					'<label>'+
			    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
			    					'<td class="center">'+item.city+'</td>'+
			    					'<shiro:hasRole name="admin">'+
	       	    						'<td class="center"><a href="<%=WEBPATH11 %>/static/jsp/system/users/editPhone.jsp?userPhone='+item.userPhone+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
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
				alert(role);
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
		    				    if(role == "admin"){
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
					    					'<td class="center">'+item.city+'</td>'+
					    					'<shiro:hasRole name="admin">'+
				       	    					'<td class="center"><a href="<%=WEBPATH11 %>/static/jsp/system/users/editPhone.jsp?userPhone='+item.userPhone+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
				       	    				'</shiro:hasRole>'+
					    					'</tr>'); 
		    				    }else if(role == "buyer"){
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
					    					'<td class="center">'+item.city+'</td>'+
					    					'<shiro:hasRole name="buyer">'+
				       	    					'<td class="center"><a href="<%=WEBPATH11 %>/static/jsp/system/users/editPhone.jsp?userPhone='+item.userPhone+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
				       	    				'</shiro:hasRole>'+
					    					'</tr>'); 
		    				    }else{
		    				    	$("#userslist").append('<tr>'+
					    					'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="checkbox" />'+
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
					    					'<td class="center">'+item.city+'</td>'+
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
		function add(){
			alert("add");
		};
		//删除
		function del(Id){
			/* alert(typeof(Id)); String */
			/* int id=2; */
			/* alert(typeof(parseInt(Id))); */
			var id=parseInt(Id);
			/* alert(typeof(id)) */
			/* alert(id); */
			$.ajax({
    			url: "delUser.do",
    	    	data: {"id":id},
    			type: "POST",
    			dataType:"json",
    			success: function(data){
    				alert("删除成功");
    				window.history.back();
    				//跳回原来的页面或移除该行  （未完成）
				}
			})
		};
		//批量操作
		function delSelected(){
			$('input:checkbox:checked').each(function (index, item) {
				//逐个取出id
				/* alert($(this).val()); */
				del($(this).val());
				window.history.back();
			});
		}
		
		</script>
		
	</body>
</html>

