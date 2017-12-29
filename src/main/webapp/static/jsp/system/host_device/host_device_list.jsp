<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
							<input autocomplete="off" id="DEVICE_ADDRESS" type="text" name="DEVICE_ADDRESS" value="" placeholder="这里输入设备地址码" />
							
							
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
						
						<span class="input-icon">
							<input autocomplete="off" id="USER_PHONE" type="text" name="USER_PHONE" value="" placeholder="这里输入绑定手机号" />
							
							
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
						
					</td>
					<%-- <td><input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期"/></td>
					<td><input class="span10 date-picker" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期"/></td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="field2" id="field2" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
							<option value=""></option>
							<option value="">全部</option>
							<option value="">1</option>
							<option value="">2</option>
					  	</select>
					</td> --%>
					<!-- <td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td> -->
					<td style="vertical-align:top;"><input type="button" value="搜索"  onclick="search();"  title="检索"></input></td>
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
						<th class="center">绑定主机序列号</th>
						<th class="center">绑定手机号</th>
						<th class="center">设备地址码</th><!-- 
						<th class="center">操作</th> -->
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
									
								<%-- <td style="width: 30px;" class="center">
									<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
											<c:if test="${QX.edit == 1 }">
												<a style="cursor:pointer;" title="编辑" onclick="edit('${var.ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a>
											</c:if>
											<c:if test="${QX.del == 1 }">
												<a style="cursor:pointer;" title="删除" onclick="del('${var.ID}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a>
										</c:if>
								</td> --%>
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
			
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<%-- <c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if> --%>
					<%-- <c:if test="${QX.del == 1 }"> --%>
					<a class="btn btn-small btn-danger" onclick="makeAll();" title="批量删除" ><i class="icon-trash"></i></a>
					<%-- </c:if> --%>
				</td>
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
        						alert(totalPages)
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
        						
        						$("#hostdevicelist01").append(table);
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
        	    	    					'<td class="center">'+item.deviceAddress+'</td>'+
        	    	    					'</tr>');  
        					}	
        				}) 
    				}
    				
    			}
    		});
		})
		
		
		//分页 find（index）
		function find(index){
			alert(index);
			$.ajax({
				url:"findHostByIndex.do",
		    	data: {"index":index }, 
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
							if((currentPage >= 1) &&  (currentPage <= 5) && (totalPages >= 6)){
								if(currentPage ==1){
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
										'</ul>');
								}else{
									$("#hostdevicelist01").append('<ul>'+
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
									alert("totalPages != 2")
									$("#hostdevicelist01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
										'</ul>');
								}else{
									$("#hostdevicelist01").append('<ul>'+
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
									$("#hostdevicelist01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>'+
										'</ul>');
								}else{
									$("#hostdevicelist01").append('<ul>'+
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
							/* alert("coming") */
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
	    	    					'<td class="center">'+item.deviceAddress+'</td>'+
	    	    					'</tr>');   
	    				}
					})								    					
			}, 
			error: function(e){
           	    alert(e)
			}
		});
		}
		
		//检索
		function search(){
			/* top.jzts();
			$("#Form").submit(); */
			var DEVICE_ADDRESS=$("#DEVICE_ADDRESS").val();
			var USER_PHONE=$("#USER_PHONE").val();
			if(DEVICE_ADDRESS !=''){
				$.ajax({
	    			url: "findHostByAddr.do",
	    	    	data: {"deviceAddress":DEVICE_ADDRESS },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				
	    				if(data == ""){
	    					$("#hostDevices").empty();
	    					$("#hostDevices").append('<tr class="main_info">'+
	    	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    	    					'</tr>');					
	    				}else{
	    					$.each(data,function(i,item){//i是key,item是value
	    						$("#hostDevices").empty();
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
	    	    					'<td class="center">'+item.deviceAddress+'</td>'+
	    	    					'</tr>');  
	        				}) 
	    				}
	    				
	    			}
	    		});
			}else if(DEVICE_ADDRESS =="" && USER_PHONE !=""){
				$.ajax({
	    			url: "findHostByTel.do",
	    	    	data: {"userPhone":USER_PHONE },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				
	    				if(data == ""){
	    					$("#hostDevices").empty();
	    					$("#hostDevices").append('<tr class="main_info">'+
	    	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    	    					'</tr>');					
	    				}else{
	    					$.each(data,function(i,item){//i是key,item是value
	    						$("#hostDevices").empty();
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
	    	    					'<td class="center">'+item.deviceAddress+'</td>'+
	    	    					'</tr>');  
	        				}) 
	    				}
	    				
	    			}
	    		});
			}else{
				alert("请输入设备地址码或手机号！");
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
			<%-- bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>host_device/delete.do?ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			}); --%>
			/* alert(typeof(Id)); String */
			/* int id=2; */
			/* alert(typeof(parseInt(Id))); */
			var id=parseInt(Id);
			/* alert(typeof(id)) */
			/* alert(id); */
			$.ajax({
    			url: "delDevices.do",
    	    	data: {"id":id},
    			type: "POST",
    			dataType:"json",
    			success: function(data){
    				alert("删除成功");
    				window.history.back();
    				//跳回原来的页面或移除该行  （未完成）
				}
			})
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>host_device/goEdit.do?ID='+Id;
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
		
		
		//批量操作
		function makeAll(msg){
			$('input:checkbox:checked').each(function (index, item) {
				//逐个取出id
				/* alert($(this).val()); */
				del($(this).val());
				window.history.back();
			});
			<%-- bootbox.confirm(msg, function(result) {
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
								url: '<%=basePath%>host_device/deleteAll.do?tm='+new Date().getTime(),
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
			}); --%>
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>host_device/excel.do';
		}
		</script>
		
	</body>
</html>

