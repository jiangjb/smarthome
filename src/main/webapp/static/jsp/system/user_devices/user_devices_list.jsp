<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<td style="vertical-align:top;"><input type="button" value="搜索"  onclick="search();"  title="检索"></input></td>
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
			//显示所有数据
			$.ajax({
    			url: "showHostD.do",
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
        						/* alert(totalPages) */
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
            	    					'<td style="width: 30px;" class="center">'+
            	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
            	    					'</td>'+
            	    					'</tr>');
        					}
    					})
    					
    				}
    				
    			}
    		});
		})
		
		//分页 find(index)
		function find(index){
			/* alert(index); */
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
							if((currentPage >= 1) &&  (currentPage <= 5) && (totalPages >= 6)){
								if(currentPage ==1){
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
										'</ul>');
								}else{
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
										'</ul>');
								}else{
									$("#userdeviceList01").append('<ul>'+
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
									$("#userdeviceList01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>'+
										'</ul>');
								}else{
									$("#userdeviceList01").append('<ul>'+
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
        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
        	    					'</td>'+
        	    					'</tr>'); 
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
			/* top.jzts();
			$("#Form").submit(); */
			var deviceCode=$("#nav-search-input").val();
			$.ajax({
				url:"findHostByDevicecode.do",
		    	data: {"deviceCode":deviceCode }, 
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
    					/* alert("success") */	
    					$.each(data,function(i,item){//i是key,item是value
    						$("#userDevicelist").empty();
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
    	    					'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
	    						'</td>'+
    	    					'</tr>');  
        				}) 
        				
    				}
				}, 
				error: function(e){
               	    /* alert(e) */
				}
			});
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>user_devices/goAdd.do';
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
		function del(Id,DEVICE_ID,USER_ID){
			<%-- bootbox.confirm("确定解绑吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>user_devices/delete.do?USER_DEVICE_ID="+Id+"&DEVICE_ID="+DEVICE_ID+"&USER_ID="+USER_ID;
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			}); --%>
			$.ajax({
    			url: "delHost.do",
    	    	data: {"id":Id,"DEVICE_ID":DEVICE_ID,"USER_ID":USER_ID},
    			type: "POST",
    			dataType:"json",
    			success: function(data){
    				alert("解绑成功");
    				//跳回原来的页面或移除该行  （未完成）
    				window.history.back();
				}
			})
		}
		
		//修改
		function edit(Id){
			 /* alert(Id); */
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>user_devices/goEdit.do?USER_DEVICE_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
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

