<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String WEBPATH22 = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+WEBPATH22+"/";
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
			<form action="" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="DEVICE_CODE" value="" placeholder="这里输入主机序列号" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					
					<!-- <td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td> -->
					<td style="vertical-align:top;"><input type="button" style="border:none;height:28px;" value="搜索"  onclick="search(1);"  title="检索"></input></td>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">序号</th>
						<th class="center">USER_PHONE</th>
						<th class="center">DEVICE_CODE</th>
						<th class="center">NICK_NAME</th>
						<th class="center">操作</th>
						<%-- <shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> --%>
					</tr>
				</thead>
										
				<tbody id="userDevicelist">
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.USER_DEVICE_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.USER_PHONE}</td>
										<td>${var.DEVICE_CODE}</td>
										<td>${var.NICK_NAME}</td>
										
								<td style="width: 30px;" class="center">
									<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<span onclick="del('${var.USER_DEVICE_ID}','${var.DEVICE_ID}','${var.USER_ID}');" style="color: blue;">解绑</span>
								</td>
							</tr>
						
						</c:forEach>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<%-- <c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise> --%>
				</c:choose>
					
				
				</tbody>
			</table>
			
		<div class="page-header position-relative" >
			<td style="vertical-align:top;">
					<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="userdeviceList01">
						
					</div>
				</td>
		
		</div>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH22 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH22 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH22 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH22 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH22 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH22 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH22 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH22 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		$(function(){
			var role='<%= session.getAttribute("role")%>';
			var tel='<%= session.getAttribute("userPhone")%>';
			if(tel!="null"){
				$.ajax({
	    			url: "findHostDByPhone.do",
	    	    	data: {"userPhone":tel },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				if(data[0].id == null){
	    					$("#userdeviceList01").empty();
	    					$("#userDevicelist").empty();
	    					$("#userDevicelist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					$.each(data,function(i,item){
	    						if(item.id == null){
	        						var currentPage=item.currentPage;
	        						var totalPages=item.totalPages;
	        						
	        						/* alert(totalPages)  */
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
	       								/* alert("2"); */
	       								table='<ul>'+
	                								'<li><a><font color="#808080">首页</font></a></li>'+
	            									'<li><a><font color="#808080">上页</font></a></li>'+
	            									'<li><a><font color="#808080">1</font></a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">尾页</a></li>'+
	               								'</ul>';
	       							}else if(totalPages ==3){
	       								table='<ul>'+
	               								'<li><a><font color="#808080">首页</font></a></li>'+
	           									'<li><a><font color="#808080">上页</font></a></li>'+
	           									'<li><a><font color="#808080">1</font></a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(3);">尾页</a></li>'+
	               								'</ul>';
	       							}else if(totalPages ==4){
	       								table='<ul>'+
	                								'<li><a><font color="#808080">首页</font></a></li>'+
	            									'<li><a><font color="#808080">上页</font></a></li>'+
	            									'<li><a><font color="#808080">1</font></a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(4);">4</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(4);">尾页</a></li>'+
	               								'</ul>';
	       							}else{//totalPages >= 5 时
	       								table='<ul>'+
	                								'<li><a><font color="#808080">首页</font></a></li>'+
	            									'<li><a><font color="#808080">上页</font></a></li>'+
	            									'<li><a><font color="#808080">1</font></a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(4);">4</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(5);">5</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>'+
	               								'</ul>';
	       							}
	        						
	        						$("#userdeviceList01").append(table);
	        					}else{
        							$("#userDevicelist").append('<tr>'+
	            	    					'<td class="center" style="width: 30px;">'+
	            	    					'<label>'+
	            	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	            	    					'<span class="lbl">'+
	            	    					'</span>'+
	            	    					'</label>'+
	            	    					'</td>'+
	            	    					'<td class="center">'+(i+1)+'</td>'+
	            	    					'<td class="center">'+item.userPhone+'</td>'+
	            	    					'<td class="center">'+item.deviceCode+'</td>'+
	            	    					'<td class="center">'+item.nick_name+'</td>'+
	            	    					'<shiro:hasRole name="buyer">'+
	            	    					'<td style="width: 30px;" class="center">'+
	            	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
	            	    					'</td>'+
	            	    					'</shiro:hasRole>'+
	            	    					'</tr>');
	        						
	        					}
	    					})
	    					
	    				}
	    				
	    			}
	    		});
			}else{
				//显示所有数据
				$.ajax({
	    			url: "showUserDevices.do",
	    	    	data: { },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				if(data == ""){
	    					$("#userDevicelist").empty();
	    					$("#userDevicelist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					$.each(data,function(i,item){
	    						if(item.id == null){
	        						var currentPage=item.currentPage;
	        						var totalPages=item.totalPages;
	        						
	        						/* alert(totalPages)  */
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
	       								/* alert("2"); */
	       								table='<ul>'+
	                								'<li><a><font color="#808080">首页</font></a></li>'+
	            									'<li><a><font color="#808080">上页</font></a></li>'+
	            									'<li><a><font color="#808080">1</font></a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">尾页</a></li>'+
	               								'</ul>';
	       							}else if(totalPages ==3){
	       								table='<ul>'+
	               								'<li><a><font color="#808080">首页</font></a></li>'+
	           									'<li><a><font color="#808080">上页</font></a></li>'+
	           									'<li><a><font color="#808080">1</font></a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	           									'<li style="cursor:pointer;"><a onclick="find(3);">尾页</a></li>'+
	               								'</ul>';
	       							}else if(totalPages ==4){
	       								table='<ul>'+
	                								'<li><a><font color="#808080">首页</font></a></li>'+
	            									'<li><a><font color="#808080">上页</font></a></li>'+
	            									'<li><a><font color="#808080">1</font></a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(4);">4</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(4);">尾页</a></li>'+
	               								'</ul>';
	       							}else{//totalPages >= 5 时
	       								table='<ul>'+
	                								'<li><a><font color="#808080">首页</font></a></li>'+
	            									'<li><a><font color="#808080">上页</font></a></li>'+
	            									'<li><a><font color="#808080">1</font></a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(4);">4</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(5);">5</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
	            									'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>'+
	               								'</ul>';
	       							}
	        						
	        						$("#userdeviceList01").append(table);
	        					}else{
	        						if(role=="admin"){
	        							$("#userDevicelist").append('<tr>'+
		            	    					'<td class="center" style="width: 30px;">'+
		            	    					'<label>'+
		            	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		            	    					'<span class="lbl">'+
		            	    					'</span>'+
		            	    					'</label>'+
		            	    					'</td>'+
		            	    					'<td class="center">'+(i+1)+'</td>'+
		            	    					'<td class="center">'+item.userPhone+'</td>'+
		            	    					'<td class="center">'+item.deviceCode+'</td>'+
		            	    					'<td class="center">'+item.nick_name+'</td>'+
		            	    					'<shiro:hasRole name="admin">'+
		            	    					'<td style="width: 30px;" class="center">'+
		            	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
		            	    					'</td>'+
		            	    					'</shiro:hasRole>'+
		            	    					'</tr>');
	        						}else{
	        							$("#userDevicelist").append('<tr>'+
		            	    					'<td class="center" style="width: 30px;">'+
		            	    					'<label>'+
		            	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		            	    					'<span class="lbl">'+
		            	    					'</span>'+
		            	    					'</label>'+
		            	    					'</td>'+
		            	    					'<td class="center">'+(i+1)+'</td>'+
		            	    					'<td class="center">'+item.userPhone+'</td>'+
		            	    					'<td class="center">'+item.deviceCode+'</td>'+
		            	    					'<td class="center">'+item.nick_name+'</td>'+
		            	    					'<td style="width: 30px;" class="center">'+
		            	    					'</td>'+
		            	    					'</tr>');
	        						}
	        						
	        					}
	    					})
	    					
	    				}
	    				
	    			}
	    		});
			}
		});
		
		
		//分页 find(index)
		function find(index){
			/* alert(index); */
			var role= '<%= session.getAttribute("role")%>'; 
			var deviceCode=$("#nav-search-input").val();
			/* alert("deviceCode:"+deviceCode); */
			if(deviceCode == ""){
				$.ajax({
					url:"findudeviceByIndex.do",
			    	data: {"index":index }, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){  //list里的实体类都在这里面遍历
						/* alert("success:"+data) */
						//清空标签内 [子标签及内容]，再添加
						$("#userDevicelist").empty();
						$.each(data,function(i,item){//i是key,item是value
							var currentPage=item.currentPage;
							var totalPages=item.totalPages;
							if(item.id == null){
								//如果是page实体类  处理一
								$("#userdeviceList01").empty();
								
								
								//根据 totalPages 和 currentPage 分成 5 种情况
								if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
									var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
								}else{//首页时 上页不能再选择
									var fore='<li><a><font color="#808080">上页</font></a></li>';
								}
								
								//先处理最后一页的分页信息
								if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
									if(currentPage == totalPages && currentPage !=1){
										$("#userdeviceList01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else{
										$("#userdeviceList01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}	
								}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
									if(currentPage == totalPages){
										$("#userdeviceList01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										if(currentPage == 1){
											$("#userdeviceList01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userdeviceList01").append('<ul>'+
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
										$("#userdeviceList01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#userdeviceList01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										if(currentPage == 1){
											$("#userdeviceList01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userdeviceList01").append('<ul>'+
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
										$("#userdeviceList01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#userdeviceList01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										$("#userdeviceList01").append('<ul>'+
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
											$("#userdeviceList01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userdeviceList01").append('<ul>'+
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
											$("#userdeviceList01").append('<ul>'+
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
											$("#userdeviceList01").append('<ul>'+
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
											$("#userdeviceList01").append('<ul>'+
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
											$("#userdeviceList01").append('<ul>'+
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
										$("#userdeviceList01").append('<ul>'+
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
										$("#userdeviceList01").append('<ul>'+
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
										$("#userdeviceList01").append('<ul>'+
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
								if(role == "admin"){
									$("#userDevicelist").append('<tr>'+
											'<td class="center" style="width: 30px;">'+
		        	    					'<label>'+
		        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    					'<span class="lbl">'+
		        	    					'</span>'+
		        	    					'</label>'+
		        	    					'</td>'+
		        	    					'<td class="center">'+(i+1)+'</td>'+
		        	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    					'<td class="center">'+item.nick_name+'</td>'+
		        	    					'<shiro:hasRole name="admin">'+
		        	    					'<td style="width: 30px;" class="center">'+
		        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
		        	    					'</td>'+
		        	    					'</shiro:hasRole>'+
		        	    					'</tr>');
								}else if(role == "buyer"){
									$("#userDevicelist").append('<tr>'+
											'<td class="center" style="width: 30px;">'+
		        	    					'<label>'+
		        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    					'<span class="lbl">'+
		        	    					'</span>'+
		        	    					'</label>'+
		        	    					'</td>'+
		        	    					'<td class="center">'+(i+1)+'</td>'+
		        	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    					'<td class="center">'+item.nick_name+'</td>'+
		        	    					'<shiro:hasRole name="buyer">'+
		        	    					'<td style="width: 30px;" class="center">'+
		        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
		        	    					'</td>'+
		        	    					'</shiro:hasRole>'+
		        	    					'</tr>');
								}else{
									$("#userDevicelist").append('<tr>'+
											'<td class="center" style="width: 30px;">'+
		        	    					'<label>'+
		        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    					'<span class="lbl">'+
		        	    					'</span>'+
		        	    					'</label>'+
		        	    					'</td>'+
		        	    					'<td class="center">'+(i+1)+'</td>'+
		        	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    					'<td class="center">'+item.nick_name+'</td>'+
		        	    					'<td style="width: 30px;" class="center">'+
		        	    					'</td>'+
		        	    					'</tr>');
								}
		    				}
						})								    					
				}, 
				error: function(e){
	           	    /* alert(e) */
				}
			});
			}else{
				$.ajax({
					url:"findUDsByDevicecode.do",
			    	data: {"deviceCode":deviceCode ,"index":index}, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
							/* alert("success:"+data) */
							//清空标签内 [子标签及内容]，再添加
							$("#userDevicelist").empty();
							$("#userdeviceList01").empty();
							if(data == ""){
								$("#userDevicelist").append('<tr class="main_info">'+
				    					'<td colspan="100" class="center" >没有相关数据</td>'+
		    					'</tr>');	
							}else{
								$.each(data,function(i,item){//i是key,item是value
									var currentPage=item.currentPage;
									var totalPages=item.totalPages;
									if(item.id == null){
										//如果是page实体类  处理一
										
										//根据 totalPages 和 currentPage 分成 5 种情况
										if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
											var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>';
										}else{//首页时 上页不能再选择
											var fore='<li><a><font color="#808080">上页</font></a></li>';
										}
										
										//先处理最后一页的分页信息
										if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
											if(currentPage == totalPages && currentPage !=1){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														fore+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li><a><font color="#808080">下页</font></a></li>'+
														'<li><a><font color="#808080">尾页</font></a></li>');
											}else{
												$("#userdeviceList01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
														fore+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li><a><font color="#808080">下页</font></a></li>'+
														'<li><a><font color="#808080">尾页</font></a></li>');
											}	
										}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
											if(currentPage == totalPages){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														fore+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li><a><font color="#808080">下页</font></a></li>'+
														'<li><a><font color="#808080">尾页</font></a></li>');
											}else if(currentPage == totalPages - 1){
												if(currentPage == 1){
													$("#userdeviceList01").append('<ul>'+
															'<li><a><font color="#808080">首页</font></a></li>'+
				        									'<li><a><font color="#808080">上页</font></a></li>'+
				        									'<li><a><font color="#808080">1</font></a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}else{
													$("#userdeviceList01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');	
												}
											}
										}else if( (totalPages%5==3) && ((currentPage==totalPages) || (currentPage==totalPages-1)|| (currentPage==totalPages-2)) ){
											if(currentPage == totalPages){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														fore+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li><a><font color="#808080">下页</font></a></li>'+
														'<li><a><font color="#808080">尾页</font></a></li>');
											}else if(currentPage == totalPages - 1){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else if(currentPage == totalPages - 2){
												if(currentPage == 1){
													$("#userdeviceList01").append('<ul>'+
															'<li><a><font color="#808080">首页</font></a></li>'+
															'<li><a><font color="#808080">上页</font></a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}else{
													$("#userdeviceList01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}
											}
										}else if( (totalPages%5==4) && ((currentPage==totalPages) || (currentPage==totalPages-1)|| (currentPage==totalPages-2)|| (currentPage==totalPages-3)) ){
											if(currentPage == totalPages){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														fore+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li><a><font color="#808080">下页</font></a></li>'+
														'<li><a><font color="#808080">尾页</font></a></li>');
											}else if(currentPage == totalPages - 1){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else if(currentPage == totalPages - 2){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else if(currentPage == totalPages - 3){
												if(currentPage == 1){
													$("#userdeviceList01").append('<ul>'+
															'<li><a><font color="#808080">首页</font></a></li>'+
															'<li><a><font color="#808080">上页</font></a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}else{
													$("#userdeviceList01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}
											}
										}else{//取整 totalPages % 5 = 0   (总页数：5,10,15...)
											if(currentPage % 5 == 0){
												if(currentPage == totalPages){
													$("#userdeviceList01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
															fore+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-4)+');">'+(currentPage-4)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li><a><font color="#808080">下页</font></a></li>'+
															'<li><a><font color="#808080">尾页</font></a></li>');
												}else{
													$("#userdeviceList01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
															fore+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-4)+');">'+(currentPage-4)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');	
												}
											}else if(currentPage % 5 == 1){
												if(currentPage == 1){
													$("#userdeviceList01").append('<ul>'+
															'<li><a><font color="#808080">首页</font></a></li>'+
				        									'<li><a><font color="#808080">上页</font></a></li>'+
				        									'<li><a><font color="#808080">1</font></a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(4);">4</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(5);">5</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
				        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}else{	
													$("#userdeviceList01").append('<ul>'+
															'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
															'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+4)+');">'+(currentPage+4)+'</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
															'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
												}
											}else if(currentPage % 5 ==  2){
												/* alert("2") */
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else if(currentPage % 5 == 3){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else if(currentPage % 5 == 4){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}
										}
										
									}else{
										if(role == "admin"){
											$("#userDevicelist").append('<tr>'+
													'<td class="center" style="width: 30px;">'+
				        	    					'<label>'+
				        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
				        	    					'<span class="lbl">'+
				        	    					'</span>'+
				        	    					'</label>'+
				        	    					'</td>'+
				        	    					'<td class="center">'+(i+1)+'</td>'+
				        	    					'<td class="center">'+item.userPhone+'</td>'+
				        	    					'<td class="center">'+item.deviceCode+'</td>'+
				        	    					'<td class="center">'+item.nick_name+'</td>'+
				        	    					'<shiro:hasRole name="admin">'+
				        	    					'<td style="width: 30px;" class="center">'+
				        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
				        	    					'</td>'+
				        	    					'</shiro:hasRole>'+
				        	    					'</tr>');
										}else if(role == "buyer"){
											$("#userDevicelist").append('<tr>'+
													'<td class="center" style="width: 30px;">'+
				        	    					'<label>'+
				        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
				        	    					'<span class="lbl">'+
				        	    					'</span>'+
				        	    					'</label>'+
				        	    					'</td>'+
				        	    					'<td class="center">'+(i+1)+'</td>'+
				        	    					'<td class="center">'+item.userPhone+'</td>'+
				        	    					'<td class="center">'+item.deviceCode+'</td>'+
				        	    					'<td class="center">'+item.nick_name+'</td>'+
				        	    					'<shiro:hasRole name="buyer">'+
				        	    					'<td style="width: 30px;" class="center">'+
				        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
				        	    					'</td>'+
				        	    					'</shiro:hasRole>'+
				        	    					'</tr>');
										}else{
											$("#userDevicelist").append('<tr>'+
													'<td class="center" style="width: 30px;">'+
				        	    					'<label>'+
				        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
				        	    					'<span class="lbl">'+
				        	    					'</span>'+
				        	    					'</label>'+
				        	    					'</td>'+
				        	    					'<td class="center">'+(i+1)+'</td>'+
				        	    					'<td class="center">'+item.userPhone+'</td>'+
				        	    					'<td class="center">'+item.deviceCode+'</td>'+
				        	    					'<td class="center">'+item.nick_name+'</td>'+
				        	    					'<td style="width: 30px;" class="center">'+
				        	    					'</td>'+
				        	    					'</tr>');
										}
				    				}
								})								    					
							}
					}, 
					error: function(e){
		           	    /* alert(e) */
					}
				});
			}
		}
		
		//检索
		function search(index){
			var role= '<%= session.getAttribute("role")%>'; 
			/* alert("search"); */
			/* top.jzts();
			$("#Form").submit(); */
			var deviceCode=$("#nav-search-input").val();
			$.ajax({
				url:"findUDsByDevicecode.do",
		    	data: {"deviceCode":deviceCode ,"index":index}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
						/* alert("success:"+data) */
						//清空标签内 [子标签及内容]，再添加
						$("#userDevicelist").empty();
						$("#userdeviceList01").empty();
						if(data == ""){
							$("#userDevicelist").append('<tr class="main_info">'+
			    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');	
						}else{
							$.each(data,function(i,item){//i是key,item是value
								var currentPage=item.currentPage;
								var totalPages=item.totalPages;
								if(item.id == null){
									//如果是page实体类  处理一
									$("#userdeviceList01").empty();
									
									
									//根据 totalPages 和 currentPage 分成 5 种情况
									if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
										var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>';
									}else{//首页时 上页不能再选择
										var fore='<li><a><font color="#808080">上页</font></a></li>';
									}
									
									//先处理最后一页的分页信息
									if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
										if(currentPage == totalPages && currentPage !=1){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													fore+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else{
											$("#userdeviceList01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													fore+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}	
									}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
										if(currentPage == totalPages){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else if(currentPage == totalPages - 1){
											if(currentPage == 1){
												$("#userdeviceList01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
			        									'<li><a><font color="#808080">上页</font></a></li>'+
			        									'<li><a><font color="#808080">1</font></a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else{
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');	
											}
										}
									}else if( (totalPages%5==3) && ((currentPage==totalPages) || (currentPage==totalPages-1)|| (currentPage==totalPages-2)) ){
										if(currentPage == totalPages){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else if(currentPage == totalPages - 1){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
										}else if(currentPage == totalPages - 2){
											if(currentPage == 1){
												$("#userdeviceList01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
														'<li><a><font color="#808080">上页</font></a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else{
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}
										}
									}else if( (totalPages%5==4) && ((currentPage==totalPages) || (currentPage==totalPages-1)|| (currentPage==totalPages-2)|| (currentPage==totalPages-3)) ){
										if(currentPage == totalPages){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else if(currentPage == totalPages - 1){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
										}else if(currentPage == totalPages - 2){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
										}else if(currentPage == totalPages - 3){
											if(currentPage == 1){
												$("#userdeviceList01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
														'<li><a><font color="#808080">上页</font></a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else{
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}
										}
									}else{//取整 totalPages % 5 = 0   (总页数：5,10,15...)
										if(currentPage % 5 == 0){
											if(currentPage == totalPages){
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														fore+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-4)+');">'+(currentPage-4)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li><a><font color="#808080">下页</font></a></li>'+
														'<li><a><font color="#808080">尾页</font></a></li>');
											}else{
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														fore+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-4)+');">'+(currentPage-4)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');	
											}
										}else if(currentPage % 5 == 1){
											if(currentPage == 1){
												$("#userdeviceList01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
			        									'<li><a><font color="#808080">上页</font></a></li>'+
			        									'<li><a><font color="#808080">1</font></a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(2);">2</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(3);">3</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(4);">4</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(5);">5</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(2);">下页</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}else{	
												$("#userdeviceList01").append('<ul>'+
														'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+4)+');">'+(currentPage+4)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
											}
										}else if(currentPage % 5 ==  2){
											/* alert("2") */
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+');">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
										}else if(currentPage % 5 == 3){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+');">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
										}else if(currentPage % 5 == 4){
											$("#userdeviceList01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1);">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+');">'+(currentPage-3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+');">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+');">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+');">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+');">尾页</a></li>');
										}
									}
									
								}else{
									if(role=="admin"){
										$("#userDevicelist").append('<tr>'+
												'<td class="center" style="width: 30px;">'+
			        	    					'<label>'+
			        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
			        	    					'<span class="lbl">'+
			        	    					'</span>'+
			        	    					'</label>'+
			        	    					'</td>'+
			        	    					'<td class="center">'+(i+1)+'</td>'+
			        	    					'<td class="center">'+item.userPhone+'</td>'+
			        	    					'<td class="center">'+item.deviceCode+'</td>'+
			        	    					'<td class="center">'+item.nick_name+'</td>'+
			        	    					'<shiro:hasRole name="admin">'+
			        	    					'<td style="width: 30px;" class="center">'+
			        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
			        	    					'</td>'+
			        	    					'</shiro:hasRole>'+
			        	    					'</tr>');
									}else if(role=="buyer"){
										$("#userDevicelist").append('<tr>'+
												'<td class="center" style="width: 30px;">'+
			        	    					'<label>'+
			        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
			        	    					'<span class="lbl">'+
			        	    					'</span>'+
			        	    					'</label>'+
			        	    					'</td>'+
			        	    					'<td class="center">'+(i+1)+'</td>'+
			        	    					'<td class="center">'+item.userPhone+'</td>'+
			        	    					'<td class="center">'+item.deviceCode+'</td>'+
			        	    					'<td class="center">'+item.nick_name+'</td>'+
			        	    					'<shiro:hasRole name="buyer">'+
			        	    					'<td style="width: 30px;" class="center">'+
			        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
			        	    					'</td>'+
			        	    					'</shiro:hasRole>'+
			        	    					'</tr>');
									}else{
										$("#userDevicelist").append('<tr>'+
												'<td class="center" style="width: 30px;">'+
			        	    					'<label>'+
			        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
			        	    					'<span class="lbl">'+
			        	    					'</span>'+
			        	    					'</label>'+
			        	    					'</td>'+
			        	    					'<td class="center">'+(i+1)+'</td>'+
			        	    					'<td class="center">'+item.userPhone+'</td>'+
			        	    					'<td class="center">'+item.deviceCode+'</td>'+
			        	    					'<td class="center">'+item.nick_name+'</td>'+
			        	    					'<td style="width: 30px;" class="center">'+
			        	    					'</td>'+
			        	    					'</tr>');
									}
									
			    				}
							})								    					
						}
				}, 
				error: function(e){
	           	    /* alert(e) */
				}
			});
		}
		
		
		//删除
		function del(Id,DEVICE_ID,USER_ID){
			/* alert("id:"+Id+",deviceId:"+DEVICE_ID+",userId:"+USER_ID); */
			$.ajax({
    			url: "delHost.do",
    	    	data: {"id":Id,"DEVICE_ID":DEVICE_ID,"USER_ID":USER_ID},
    			type: "POST",
    			dataType:"json",
    			success: function(data){
    				alert("解绑成功");
    				//跳回原来的页面或移除该行  （未完成）
    				window.location.reload();
				}
			})
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
			
		});
		
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>user_devices/excel.do';
		}
		</script>
		
	</body>
</html>

