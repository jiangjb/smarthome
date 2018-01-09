<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
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
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
			<!-- 检索  -->
			<!-- <form action="findByTel.do" method="post" name="Form" id="Form"> -->
			<form action="findByTel.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="userPhone" value="" placeholder="这里输入手机号" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<!-- <td style="vertical-align:top;"><button  class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td> -->
					<td style="vertical-align:top;"><input type="button" style="border:none;" value="搜索"  onclick="search();"  title="检索"></input></td>
					<%-- <c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if> --%>
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
						<!-- <th class="center">操作</th> -->
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
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH11 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
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
							if(totalPages>=1 && totalPages<=5){
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
    							}else if(totalPages ==5){
    								table='<ul>'+
	            								'<li><a><font color="#808080">首页</font></a></li>'+
	        									'<li><a><font color="#808080">上页</font></a></li>'+
	        									'<li><a><font color="#808080">1</font></a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(5)">尾页</a></li>'+
            								'</ul>';
    							}
    						}else{
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
		    					'<td class="center">'+item.city+'</td></tr>'); 
						}
					})	
    			}
    		});
		})
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
		    					'<td class="center">'+item.city+'</td>');  
	    				}) 
			
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
								if((currentPage >= 1) &&  (currentPage <= 5) && (totalPages >= 6)){
									if(currentPage ==1){
										$("#userlist01").append('<ul>'+
															'<li><a><font color="#808080">首页</font></a></li>'+
															'<li><a><font color="#808080">上页</font></a></li>'+
															'<li><a><font color="#808080">1</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
														'</ul>');
									}else if(currentPage ==2){
										$("#userlist01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">上页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">1</a></li>'+
															'<li><a><font color="#808080">2</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(3)">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
														'</ul>');
									}else if(currentPage ==3){
										$("#userlist01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(2)">上页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">1</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
															'<li><a><font color="#808080">3</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(4)">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
														'</ul>');
									}else if(currentPage ==4){
										$("#userlist01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(3)">上页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">1</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
															'<li><a><font color="#808080">4</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(5)">5</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(5)">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
														'</ul>');
									}else{
										$("#userlist01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(4)">上页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(1)">1</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(3)">3</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(4)">4</a></li>'+
															'<li><a><font color="#808080">5</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find(6)">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
														'</ul>');
									}	
								}else if(currentPage == (totalPages-4)){ 
									if(totalPages == 4){
										$("#userlist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+4)+')">'+(currentPage+4)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}else{
										$("#userlist01").append('<ul>'+
												'<li><a onclick="find(1);">首页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}
									
								}else if((currentPage == (totalPages-3))){
									if(totalPages == 4){
										$("#userlist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}else{
										$("#userlist01").append('<ul>'+
												'<li><a onclick="find(1);">首页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}
					
								}else if(currentPage == (totalPages-2)){
									if(totalPages == 3){
										$("#userlist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}else{
										$("#userlist01").append('<ul>'+
												'<li><a onclick="find(1);">首页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}
									
								}else if((currentPage == (totalPages-1))){
									if(totalPages == 2){
										/* alert("totalPages != 2") */
										$("#userlist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}else{
										$("#userlist01").append('<ul>'+
												'<li><a onclick="find(1);">首页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
											'</ul>');
									}
									
								}else if((currentPage == totalPages)){
									if(totalPages == 1){
										$("#userlist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>'+
											'</ul>');
									}else{
										$("#userlist01").append('<ul>'+
												'<li><a onclick="find(1);">首页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>'+
											'</ul>');
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
			    					'<td class="center">'+item.city+'</td>');  
		    				}
						})								    					
				}, 
				error: function(e){
               	    /* alert(e) */
				}
			});
		}
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

    			/* alert("success") */
    			 <%-- window.location.href ="<%=WEBPATH11%>/showUsers.do";/* 不要什么都用ajax，ajax有它的限制 */ --%>
    			 
	    		/* $.ajax({
	    			url: "showUsers.do",
	    	    	data: { },
	    			type: "POST",
	    			dataType:"json",
	    			success: function(data){
	    				alert("success")
	    			}
	    		}); */
		});
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>users/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>users/excel.do';
		}
		</script>
		
	</body>
</html>

