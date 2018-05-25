<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String WEBPATH31 = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ WEBPATH31 + "/";
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
							<input autocomplete="off" id="ADDRESS_PHONE" type="text" name="" value="" placeholder="这里输入要搜索的内容" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>	
					</td>
					<td style="vertical-align:top;"><input type="button" value="搜索" style="border:none;height:30px;" onclick="search();"  title="检索"></input></td>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">绑定主机序列号</th>
						<th class="center">绑定手机号</th>
						<th class="center">设备名</th>
						<th class="center">操作</th>
						<%-- <shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> --%>
					</tr>
				</thead>
										
				<tbody id="hostDevices">
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.ID}" /><span class="lbl"></span></label>
								</td>
								<td>${var.DEVICE_CODE}</td>
								<td>${var.USER_PHONE}</td>
								<td>${var.DEVICE_ADDRESS}</td>
							</tr>
						
						</c:forEach>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
				</c:choose>
					
				
				</tbody>
			</table>
			
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<shiro:hasRole name="admin">
					<td style="vertical-align:top;">
						<%-- <c:if test="${QX.add == 1 }">
						<a class="btn btn-small btn-success" onclick="add();">新增</a>
						</c:if> --%>
						<%-- <c:if test="${QX.del == 1 }"> --%>
						<a class="btn btn-small btn-danger" onclick="makeAll();" title="批量删除" ><i class="icon-trash"></i></a>
						<%-- </c:if> --%>
					</td>
				</shiro:hasRole>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
				<td style="vertical-align:top;">
					<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="hostdevicelist01">
						
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
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH31 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH31 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH31 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH31 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH31 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH31 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH31 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH31 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		$(function(){
			var tel='<%= session.getAttribute("userPhone")%>';
			var role= '<%= session.getAttribute("role")%>'; 
			/* alert(tel); */
			/* alert(tel!="null"); */
			if(tel!="null"){
				$.ajax({
	    			url: "findHostDByPhone.do",
	    	    	data: {"userPhone":tel },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				if(data[0].id == null){
	    					$("#hostdevicelist01").empty();
	    					$("#hostDevices").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					/* alert("success") */
	        				$.each(data,function(i,item){//i是key,item是value
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
	        						
	        						$("#hostdevicelist01").append(table);
	        					}else{
	        						if(role == "superadmin"){
		        						 $("#hostDevices").append('<tr>'+
		        	    	    					'<td class="center" style="width: 30px;">'+
		        	    	    					'<label>'+
		        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    	    					'<span class="lbl">'+
		        	    	    					'</span>'+
		        	    	    					'</label>'+
		        	    	    					'</td>'+
		        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    	    					'<td class="center">'+item.nick_name+'</td>'+
		        	    	    					'<shiro:hasRole name="superadmin">'+
				       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
				       	    						'</shiro:hasRole>'+
		        	    	    					'</tr>');  
	        						}else if(role == "admin"){
		        						 $("#hostDevices").append('<tr>'+
		        	    	    					'<td class="center" style="width: 30px;">'+
		        	    	    					'<label>'+
		        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    	    					'<span class="lbl">'+
		        	    	    					'</span>'+
		        	    	    					'</label>'+
		        	    	    					'</td>'+
		        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    	    					'<td class="center">'+item.nick_name+'</td>'+
		        	    	    					'<shiro:hasRole name="admin">'+
				       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
				       	    						'</shiro:hasRole>'+
		        	    	    					'</tr>');  
	        						}else if(role == "buyer"){
	        							$("#hostDevices").append('<tr>'+
	        	    	    					'<td class="center" style="width: 30px;">'+
	        	    	    					'<label>'+
	        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	        	    	    					'<span class="lbl">'+
	        	    	    					'</span>'+
	        	    	    					'</label>'+
	        	    	    					'</td>'+
	        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	        	    	    					'<td class="center">'+item.userPhone+'</td>'+
	        	    	    					'<td class="center">'+item.nick_name+'</td>'+
	        	    	    					'<shiro:hasRole name="buyer">'+
			       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
			       	    						'</shiro:hasRole>'+
	        	    	    					'</tr>');  
	        						}else{
	        							$("#hostDevices").append('<tr>'+
	        	    	    					'<td class="center" style="width: 30px;">'+
	        	    	    					'<label>'+
	        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	        	    	    					'<span class="lbl">'+
	        	    	    					'</span>'+
	        	    	    					'</label>'+
	        	    	    					'</td>'+
	        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	        	    	    					'<td class="center">'+item.userPhone+'</td>'+
	        	    	    					'<td class="center">'+item.nick_name+'</td>'+
	        	    	    					'<td>'+
			       	    						'</td>'+
	        	    	    					'</tr>');  
	        						}
	        					}	
	        				}) 
	    				}
	    				
	    			}
	    		});
			}else{
				//显示所有数据
				$.ajax({
	    			url: "showDevices.do",
	    	    	data: { },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				
	    				if(data == ""){
	    					$("#hostDevices").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					/* alert("success") */
	        				$.each(data,function(i,item){//i是key,item是value
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
	        						
	        						$("#hostdevicelist01").append(table);
	        					}else{
	        						if(role == "superadmin"){
		        						 $("#hostDevices").append('<tr>'+
		        	    	    					'<td class="center" style="width: 30px;">'+
		        	    	    					'<label>'+
		        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    	    					'<span class="lbl">'+
		        	    	    					'</span>'+
		        	    	    					'</label>'+
		        	    	    					'</td>'+
		        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    	    					'<td class="center">'+item.nickName+'</td>'+
		        	    	    					'<shiro:hasRole name="superadmin">'+
				       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
				       	    						'</shiro:hasRole>'+
		        	    	    					'</tr>');  
	        						}else if(role == "admin"){
		        						 $("#hostDevices").append('<tr>'+
		        	    	    					'<td class="center" style="width: 30px;">'+
		        	    	    					'<label>'+
		        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    	    					'<span class="lbl">'+
		        	    	    					'</span>'+
		        	    	    					'</label>'+
		        	    	    					'</td>'+
		        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    	    					'<td class="center">'+item.nickName+'</td>'+
		        	    	    					'<shiro:hasRole name="admin">'+
				       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
				       	    						'</shiro:hasRole>'+
		        	    	    					'</tr>');  
	        						}else if(role == "buyer"){
	        							$("#hostDevices").append('<tr>'+
	        	    	    					'<td class="center" style="width: 30px;">'+
	        	    	    					'<label>'+
	        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	        	    	    					'<span class="lbl">'+
	        	    	    					'</span>'+
	        	    	    					'</label>'+
	        	    	    					'</td>'+
	        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	        	    	    					'<td class="center">'+item.userPhone+'</td>'+
	        	    	    					'<td class="center">'+item.nickName+'</td>'+
	        	    	    					'<shiro:hasRole name="buyer">'+
			       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
			       	    						'</shiro:hasRole>'+
	        	    	    					'</tr>');  
	        						}else{
	        							$("#hostDevices").append('<tr>'+
	        	    	    					'<td class="center" style="width: 30px;">'+
	        	    	    					'<label>'+
	        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	        	    	    					'<span class="lbl">'+
	        	    	    					'</span>'+
	        	    	    					'</label>'+
	        	    	    					'</td>'+
	        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	        	    	    					'<td class="center">'+item.userPhone+'</td>'+
	        	    	    					'<td class="center">'+item.nickName+'</td>'+
	        	    	    					'<td>'+
			       	    						'</td>'+
	        	    	    					'</tr>');  
	        						}
	        					}	
	        				}) 
	    				}
	    				
	    			}
	    		});
			}
		})
		
		
		//分页 find（index）
		function find(index){
			var role= '<%= session.getAttribute("role")%>'; 
			var AddrOrTel=$("#ADDRESS_PHONE").val();
			$.ajax({
				url:"findHostByIndex.do",
		    	data: {"index":index ,"addrOtel":AddrOrTel}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){  //list里的实体类都在这里面遍历
					/* alert("success:"+data) */
					//清空标签内 [子标签及内容]，再添加
					$("#hostDevices").empty();
					$.each(data,function(i,item){//i是key,item是value
						var currentPage=item.currentPage;
						var totalPages=item.totalPages;
						if(item.id == null){
							//如果是page实体类  处理一
							$("#hostdevicelist01").empty();
							
							//根据 totalPages 和 currentPage 分成 5 种情况
							if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
								var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
							}else{//首页时 上页不能再选择
								var fore='<li><a><font color="#808080">上页</font></a></li>';
							}
							
							//先处理最后一页的分页信息
							if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
								if(currentPage == totalPages && currentPage !=1){
									$("#hostdevicelist01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else{
									$("#hostdevicelist01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											fore+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}	
							}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
								if(currentPage == totalPages){
									$("#hostdevicelist01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else if(currentPage == totalPages - 1){
									if(currentPage == 1){
										$("#hostdevicelist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
	        									'<li><a><font color="#808080">上页</font></a></li>'+
	        									'<li><a><font color="#808080">1</font></a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else{
										$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else if(currentPage == totalPages - 1){
									$("#hostdevicelist01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
								}else if(currentPage == totalPages - 2){
									if(currentPage == 1){
										$("#hostdevicelist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else{
										$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else if(currentPage == totalPages - 1){
									$("#hostdevicelist01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
								}else if(currentPage == totalPages - 2){
									$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else{
										$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
							if(role == "superadmin"){
	       						 $("#hostDevices").append('<tr>'+
	       	    	    					'<td class="center" style="width: 30px;">'+
	       	    	    					'<label>'+
	       	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	       	    	    					'<span class="lbl">'+
	       	    	    					'</span>'+
	       	    	    					'</label>'+
	       	    	    					'</td>'+
	       	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	       	    	    					'<td class="center">'+item.userPhone+'</td>'+
	       	    	    					'<td class="center">'+item.nickName+'</td>'+
	       	    	    					'<shiro:hasRole name="superadmin">'+
			       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
			       	    						'</shiro:hasRole>'+
	       	    	    					'</tr>');  
		   						}else if(role == "admin"){
       						 $("#hostDevices").append('<tr>'+
       	    	    					'<td class="center" style="width: 30px;">'+
       	    	    					'<label>'+
       	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
       	    	    					'<span class="lbl">'+
       	    	    					'</span>'+
       	    	    					'</label>'+
       	    	    					'</td>'+
       	    	    					'<td class="center">'+item.deviceCode+'</td>'+
       	    	    					'<td class="center">'+item.userPhone+'</td>'+
       	    	    					'<td class="center">'+item.nickName+'</td>'+
       	    	    					'<shiro:hasRole name="admin">'+
		       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
		       	    						'</shiro:hasRole>'+
       	    	    					'</tr>');  
	   						}else if(role == "buyer"){
	   							$("#hostDevices").append('<tr>'+
	   	    	    					'<td class="center" style="width: 30px;">'+
	   	    	    					'<label>'+
	   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	   	    	    					'<span class="lbl">'+
	   	    	    					'</span>'+
	   	    	    					'</label>'+
	   	    	    					'</td>'+
	   	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	   	    	    					'<td class="center">'+item.userPhone+'</td>'+
	   	    	    					'<td class="center">'+item.nickName+'</td>'+
	   	    	    					'<shiro:hasRole name="buyer">'+
		       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
		       	    						'</shiro:hasRole>'+
	   	    	    					'</tr>');  
	   						}else{
	   							$("#hostDevices").append('<tr>'+
	   	    	    					'<td class="center" style="width: 30px;">'+
	   	    	    					'<label>'+
	   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	   	    	    					'<span class="lbl">'+
	   	    	    					'</span>'+
	   	    	    					'</label>'+
	   	    	    					'</td>'+
	   	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	   	    	    					'<td class="center">'+item.userPhone+'</td>'+
	   	    	    					'<td class="center">'+item.nickName+'</td>'+
	   	    	    					'<td>'+
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
		}
		
		//检索
		function search(){
			var role= '<%= session.getAttribute("role")%>'; 
			/* top.jzts();
			$("#Form").submit(); */
			var AddrOrTel=$("#ADDRESS_PHONE").val();
			if(AddrOrTel !=''){
				$.ajax({
	    			url: "findHostByAddrOrTel.do",
	    	    	data: {"AddrOrTel":AddrOrTel },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				$("#hostDevices").empty();
						$.each(data,function(i,item){//i是key,item是value
							var currentPage=item.currentPage;
							var totalPages=item.totalPages;
							if(item.id == null){
								//如果是page实体类  处理一
								$("#hostdevicelist01").empty();
								
								//根据 totalPages 和 currentPage 分成 5 种情况
								if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
									var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
								}else{//首页时 上页不能再选择
									var fore='<li><a><font color="#808080">上页</font></a></li>';
								}
								
								//先处理最后一页的分页信息
								if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
									if(currentPage == totalPages && currentPage !=1){
										$("#hostdevicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else{
										$("#hostdevicelist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}	
								}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
									if(currentPage == totalPages){
										$("#hostdevicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										if(currentPage == 1){
											$("#hostdevicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#hostdevicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										if(currentPage == 1){
											$("#hostdevicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#hostdevicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										$("#hostdevicelist01").append('<ul>'+
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
											$("#hostdevicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#hostdevicelist01").append('<ul>'+
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
											$("#hostdevicelist01").append('<ul>'+
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
											$("#hostdevicelist01").append('<ul>'+
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
											$("#hostdevicelist01").append('<ul>'+
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
											$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
										$("#hostdevicelist01").append('<ul>'+
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
								if(role == "superadmin"){
	        						 $("#hostDevices").append('<tr>'+
	        	    	    					'<td class="center" style="width: 30px;">'+
	        	    	    					'<label>'+
	        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	        	    	    					'<span class="lbl">'+
	        	    	    					'</span>'+
	        	    	    					'</label>'+
	        	    	    					'</td>'+
	        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	        	    	    					'<td class="center">'+item.userPhone+'</td>'+
	        	    	    					'<td class="center">'+item.nickName+'</td>'+
	        	    	    					'<shiro:hasRole name="superadmin">'+
			       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
			       	    						'</shiro:hasRole>'+
	        	    	    					'</tr>');  
	       						}else if(role == "admin"){
	        						 $("#hostDevices").append('<tr>'+
	        	    	    					'<td class="center" style="width: 30px;">'+
	        	    	    					'<label>'+
	        	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	        	    	    					'<span class="lbl">'+
	        	    	    					'</span>'+
	        	    	    					'</label>'+
	        	    	    					'</td>'+
	        	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	        	    	    					'<td class="center">'+item.userPhone+'</td>'+
	        	    	    					'<td class="center">'+item.nickName+'</td>'+
	        	    	    					'<shiro:hasRole name="admin">'+
			       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
			       	    						'</shiro:hasRole>'+
	        	    	    					'</tr>');  
	       						}else if(role == "buyer"){
	       							$("#hostDevices").append('<tr>'+
	       	    	    					'<td class="center" style="width: 30px;">'+
	       	    	    					'<label>'+
	       	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	       	    	    					'<span class="lbl">'+
	       	    	    					'</span>'+
	       	    	    					'</label>'+
	       	    	    					'</td>'+
	       	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	       	    	    					'<td class="center">'+item.userPhone+'</td>'+
	       	    	    					'<td class="center">'+item.nickName+'</td>'+
	       	    	    					'<shiro:hasRole name="buyer">'+
			       	    							'<td class="center"><a onclick="del('+item.id+');" style="cursor:pointer;" title="删除"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></td>'+ 
			       	    						'</shiro:hasRole>'+
	       	    	    					'</tr>');  
	       						}else{
	       							$("#hostDevices").append('<tr>'+
	       	    	    					'<td class="center" style="width: 30px;">'+
	       	    	    					'<label>'+
	       	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	       	    	    					'<span class="lbl">'+
	       	    	    					'</span>'+
	       	    	    					'</label>'+
	       	    	    					'</td>'+
	       	    	    					'<td class="center">'+item.deviceCode+'</td>'+
	       	    	    					'<td class="center">'+item.userPhone+'</td>'+
	       	    	    					'<td class="center">'+item.nickName+'</td>'+
	       	    	    					'<td>'+
			       	    						'</td>'+
	       	    	    					'</tr>');  
	       						}
		    				}
						})								    					
				}
	    		});
			}
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>host_device/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			/* alert(typeof(Id)); String */
			var id=parseInt(Id);
			$.ajax({
    			url: "delDevices.do",
    	    	data: {"id":id},
    			type: "POST",
    			dataType:"json",
    			success: function(data){
    				alert("删除成功");
    				window.location.reload();
    				//跳回原来的页面或移除该行  （未完成）
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
		
		
			//批量操作
			function makeAll(){
				$('input:checkbox:checked').each(function (index, item) {
					//逐个取出id
					/* alert($(this).val()); */
					del($(this).val());
					window.history.back();
				});
			}
		
			//导出excel
			function toExcel(){
				window.location.href='<%=basePath%>host_device/excel.do';
			}
		</script>
		
	</body>
</html>

