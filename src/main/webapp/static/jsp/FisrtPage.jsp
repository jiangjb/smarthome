<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String WEBPATH3 = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+WEBPATH3+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePathl%>"><!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1" name="viewport" />
<%-- <link rel="stylesheet" href="<%=WEBPATH3 %>/static/js/lc_switch.css"> --%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
		
<div class="container-fluid" id="main-container">
<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<!-- <div class="row-fluid">
		<p style="color:green;font-size:16px;">首页</p>
	</div> -->
	<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">昵称</th>
						<th class="center">手机号</th>
						<th class="center">主机序列号</th>
						<th class="center">注册时间</th>
						<th class="center">最后登录时间</th>
						<th class="center">状态</th>
						<th class="center">备注</th>
						<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole>
					</tr>
				</thead>
										
				<tbody id="userdeviceslist0">
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<%-- <c:if test="${QX.cha == 1 }"> --%>
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<%-- <td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.userId}" /><span class="lbl"></span></label>
								</td> --%>
								<%-- <td class='center' style="width: 30px;">${vs.index+1}</td> --%>
										<%-- <td>${var.USER_CODE}</td> --%>
										<td>${var.USER_NAME}</td>
										<td>${var.USER_PHONE}</td>
										<td>${var.DEVICE_CODE}</td>
										<td>${var.MNT_CREATOR_DATE}</td>
										<td>${var.MNT_UPDATED_DATE}</td>
										<td>${var.HOST_STATUS}</td>
										<td>${var.SIGNATURE}</td>
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
						<%-- </c:if> 
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>--%>
					</c:when>
					<%-- <c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>--%>
				</c:choose> 
					
				
				</tbody>
			</table>
			<div class="page-header position-relative">
				<table style="width:100%;" >
		    		<tr>
    					<td style="vertical-align:top;">
    						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="userdeviceslist">
    					</td>
		    		</tr>
				</table>
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
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH3 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH3 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH3 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH3 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH3 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(function(){
			//获取首页初始化所需的数据
			$.ajax({
    			url: "homePage.do",
    	    	data: { },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				if(data == null){
    					$("#userdeviceslist0").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');	
    				}else{
        				$.each(data,function(i,item){//i是key,item是value
        					if(item.DEVICE_CODE == null){
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
        						
        						$("#userdeviceslist").append(table);
        					}else{
        						var col="";
								var creator_date="";
								var updated_date="";
	           				     /* alert(item.deviceCode); */
	   							 if(item.HOST_STATUS =="离线"){
	   								 col='<td class="center"><span style="color: black;">'+item.DEVICE_CODE+'</span></td>';
	   							 }else{
	   								 col='<td class="center"><span style="color: blue;">'+item.DEVICE_CODE+'</span></td>';
	   							 }
	           				     if(item.MNT_CREATOR_DATE !=null){
	           				    	creator_date='<td class="center"><span style="color: black;">'+item.MNT_CREATOR_DATE+'</span></td>';
	           				     }else{
	           				    	creator_date='<td></td>';
	           				     }
	           				    if(item.MNT_UPDATED_DATE !=null){
	           				    	updated_date='<td class="center"><span style="color: black;">'+item.MNT_UPDATED_DATE+'</span></td>';
	           				     }else{
	           				    	updated_date='<td></td>';
	           				     }
	           				     
	         					$("#userdeviceslist0").append('<tr>'+
	         							'<td class="center" style="width: 30px;">'+
   	    	    					'<label>'+
   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
   	    	    					'<span class="lbl">'+
   	    	    					'</span>'+
   	    	    					'</label>'+
   	    	    					'</td>'+
	         							'<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>'+
	         							'<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>'+
	       	    						col+
	       	    						creator_date+
	       	    						updated_date+
	         							'<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>'+
	         							'<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>'+
	         							'<shiro:hasRole name="admin">'+
		       	    					'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		       	    					'</shiro:hasRole>'+
	       	    					'</tr>'); 
	        					}
    					})	
    				}
    			}
			})
		});
		</script>
		<script type="text/javascript">
		//分页
		function find(index){
				$.ajax({
					url:"findUserDevicesByIndex.do",
			    	data: {"index":index }, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){  //list里的实体类都在这里面遍历
						/* alert("success:"+data) */
						//清空标签内 [子标签及内容]，再添加
						$("#userdeviceslist0").empty();
						$.each(data,function(i,item){//i是key,item是value
							var currentPage=item.currentPage;
							var totalPages=item.totalPages;
							if(item.DEVICE_CODE == null){
								//如果是page实体类  处理一
								$("#userdeviceslist").empty();
								
								//根据 totalPages 和 currentPage 分成 5 种情况
								if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
									var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
								}else{//首页时 上页不能再选择
									var fore='<li><a><font color="#808080">上页</font></a></li>';
								}
								
								//先处理最后一页的分页信息
								if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
									if(currentPage == totalPages && currentPage !=1){
										$("#userdeviceslist").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else{
										$("#userdeviceslist").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}	
								}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
									if(currentPage == totalPages){
										$("#userdeviceslist").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										if(currentPage == 1){
											$("#userdeviceslist").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userdeviceslist").append('<ul>'+
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
										$("#userdeviceslist").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#userdeviceslist").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										if(currentPage == 1){
											$("#userdeviceslist").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userdeviceslist").append('<ul>'+
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
										$("#userdeviceslist").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#userdeviceslist").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										$("#userdeviceslist").append('<ul>'+
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
											$("#userdeviceslist").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#userdeviceslist").append('<ul>'+
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
											$("#userdeviceslist").append('<ul>'+
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
											$("#userdeviceslist").append('<ul>'+
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
											$("#userdeviceslist").append('<ul>'+
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
											$("#userdeviceslist").append('<ul>'+
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
										$("#userdeviceslist").append('<ul>'+
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
										$("#userdeviceslist").append('<ul>'+
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
										$("#userdeviceslist").append('<ul>'+
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
								var col="";
								var creator_date="";
								var updated_date="";
	           				     /* alert(item.deviceCode); */
	   							 if(item.HOST_STATUS =="离线"){
	   								 col='<td class="center"><span style="color: black;">'+item.DEVICE_CODE+'</span></td>';
	   							 }else{
	   								 col='<td class="center"><span style="color: blue;">'+item.DEVICE_CODE+'</span></td>';
	   							 }
	           				     if(item.MNT_CREATOR_DATE !=null){
	           				    	creator_date='<td class="center"><span style="color: black;">'+item.MNT_CREATOR_DATE+'</span></td>';
	           				     }else{
	           				    	creator_date='<td></td>';
	           				     }
	           				    if(item.MNT_UPDATED_DATE !=null){
	           				    	updated_date='<td class="center"><span style="color: black;">'+item.MNT_UPDATED_DATE+'</span></td>';
	           				     }else{
	           				    	updated_date='<td></td>';
	           				     }
	           				     
	         					$("#userdeviceslist0").append('<tr>'+
	         							'<td class="center" style="width: 30px;">'+
   	    	    					'<label>'+
   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
   	    	    					'<span class="lbl">'+
   	    	    					'</span>'+
   	    	    					'</label>'+
   	    	    					'</td>'+
	         							'<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>'+
	         							'<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>'+
	       	    						col+
	       	    						creator_date+
	       	    						updated_date+
	         							'<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>'+
	         							'<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>'+
	         							'<shiro:hasRole name="admin">'+
		       	    					'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		       	    					'</shiro:hasRole>'+
	       	    					'</tr>'); 
			        		
		    				}
						})								    					
				}, 
				error: function(e){
               	    /* alert(e) */
				}
			});
		}
		</script>
	</body>
</html>

