<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String WEBPATH21 = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+WEBPATH21+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="/static/jsp/top.jsp"%>
	<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1;"
	name="viewport" />
<%-- <link rel="stylesheet" href="<%=WEBPATH21 %>/static/js/lc_switch.css"> --%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<!-- <form action="" method="post" name="Form" id="Form"> -->
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off"  type="text" name="DEVICE_CODE" id="DEVICE_CODE" value="" placeholder="这里输入主机序列号" style="width: 150px;"/>
						</span>
						<span class="input-icon">
							<!-- <input autocomplete="off"  type="text" name="DEVICE_CODE" value="" placeholder="这里输入主机序列号" style="width: 150px;"/> -->
							<select name="STATUS" class="form-control checkacc" style="width: 100px;" id="STATUS">
								<c:if test="${pdss == 1}">
									<option value="1">在线</option>
								</c:if>
								<c:if test="${pdss == 0}">
									<option value="0">离线</option>
								</c:if>
								
								<option value="">全部</option>
								<option value="1">在线</option>
								<option value="0">离线</option>	
							</select>
						</span>
					</td>
					
					<td style="vertical-align:top;"><input type="button" style="border:none;" value="搜索"  onclick="search();"  title="检索"></input></td>
						<shiro:hasRole name="admin">
							<td style="vertical-align:top;"><input id="filepath" type="file" class="btn btn-mini btn-light " ></input><button id="nav-search-icon" vallue="导入" class="icon-cloud-upload" style="border:none;" onclick="fromExcel();"></button></td>	
						</shiro:hasRole>
					<h3 style="display:inline;color: green;" >总个数：</h3><h3 id="total" style="display:inline;color: purple;">${pds.countNum} 台&nbsp</h3>
					<h3 style="display:inline;color: maroon;" >▎总在线：</h3><h3 id="online" style="display:inline;color: teal;">${pds.statuNum}</h3>
				</tr>
			</table>
			<!-- 检索  -->
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<!-- <th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th> -->
						<!-- <th class="center">序号</th> -->
						<!-- <th class="center">FACTORY_ID</th>
						<th class="center">TYPE_ID</th> -->
						<th class="center"  style="width: 40%;">主机序列号</th>
						<!-- <th class="center">TYPE_CODE</th>
						<th class="center">DEVICE_NAME</th> -->
						<th class="center" style="width: 20%;">状态</th>
						<th class="center" style="width: 20%;">类型</th>
						<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole>
					</tr>
				</thead>
										
				<tbody id="devicelist">
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<%-- <td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.DEVICE_ID}" /><span class="lbl"></span></label>
								</td> --%>
								<%-- <td class='center' style="width: 30px;">${vs.index+1}</td> --%>
										<%-- <td>${var.FACTORY_ID}</td>
										<td>${var.TYPE_ID}</td> --%>
										<td class="center">
											
											<c:if test="${var.STATUS==0}"><span style="color: black;">${var.DEVICE_CODE}</span></c:if>
											<c:if test="${var.STATUS==1}"><span style="color: blue;">${var.DEVICE_CODE}</span></c:if>
										</td>
										<%-- <td>${var.TYPE_CODE}</td>
										<td>${var.DEVICE_NAME}</td> --%>
										<td class="center">
											<c:if test="${var.STATUS==0}">离线</c:if>
											<c:if test="${var.STATUS==1}">在线</c:if>
										</td>
										
										<td class="center">
											<c:if test="${var.TYPE == ''}">F板</c:if>
											<c:if test="${var.TYPE == 'G'}">G板</c:if>
											<c:if test="${var.TYPE == '8'}">8路控制盒</c:if>
											<c:if test="${var.TYPE == '32'}">32路控制盒</c:if>
										</td>
								<td style="width: 30px;" class="center">
									<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<c:if test="${QX.edit == 1 }">
												<a style="cursor:pointer;" title="编辑" onclick="edit('${var.DEVICE_ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a>
											</c:if>
											<%-- <c:if test="${QX.del == 1 }">
												<a style="cursor:pointer;" title="删除" onclick="del('${var.DEVICE_ID}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a>
											</c:if> --%>
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
			
		<div class="page-header position-relative">
		<table style="width:100%;" >
		    <tr>
		    	<shiro:hasRole name="admin">
		    		<td style="vertical-align:top;">
		    			<a class="btn btn-small btn-success" href="<%=WEBPATH21 %>/static/jsp/system/device/add.jsp">新增</a>
		    		</td>
		    	</shiro:hasRole>
		    	<td style="vertical-align:top;">
		    		<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="devicelist01">
		    	</td>
		    </tr>
		</table>
		</div>
		<!-- </form> -->
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
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH21 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH21 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH21 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH21 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH21 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH21 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH21 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH21 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(function(){
			//总人数，在线人数
			$.ajax({
    			url: "showNum.do",
    	    	data: { },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				/* alert("1"); */
    				$("#total").text(data.total+" 台 ");
    				$("#online").text(data.online+" 台 ");
    			}
    		});
			//
			$.ajax({
    			url: "showHostDevices.do",
    	    	data: { },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				/* alert("2"); */
    				/* alert(data) */
    				if(data == null){
    					$("#devicelist").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');	
    				}else{
        				$.each(data,function(i,item){//i是key,item是value
        					if(item.deviceCode == null){
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
        						
        						$("#devicelist01").append(table);
        					}else{
        						 var col="";
	           					 var status="";
	           				     var type="";
	           				     /* alert(item.deviceCode); */
	   							 if(item.status ==0){
	   								 col='<td class="center"><span style="color: black;">'+item.deviceCode+'</span></td>';
	   							 }else{
	   								 col='<td class="center"><span style="color: blue;">'+item.deviceCode+'</span></td>';
	   							 }
	           				     if(item.status ==0){
	           				    	 status='<td class="center">离线</td>';
	           				     }else{
	           				    	 status='<td class="center">在线</td>';
	           				     }
	           				     
	           				     if(item.type == ""){
	           				    	 type='<td class="center">F板</td>';
	           				     }else if(item.type == "G"){
	           				    	 type='<td class="center">G板</td>';
	           				     }else if(item.type == "8"){
	           				    	 type='<td class="center">8路控制盒</td>';
	           				     }else if(item.type == "32"){
	           				    	 type='<td class="center">32路控制盒</td>';
	           				     }else{
	           				    	 alert("error");
	           				     }
	         					$("#devicelist").append('<tr>'+
	       	    					col+
	       	    					status+
	       	    					type+
	       	    					'<shiro:hasRole name="admin">'+
	       	    					'<td class="center"><a href="<%=WEBPATH21 %>/static/jsp/system/device/edit.jsp?deviceCode='+item.deviceCode+'&type='+item.type+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
	       	    					'</shiro:hasRole>'+
	       	    					'</tr>');  
	        					}
    					})	
    				}
    			}
			})
		});
		//打开上传excel页面
		function fromExcel(){
			/* alert("被点击") */
			var filepath=$("#filepath").val();
			if(filepath ==""){
				alert("请先选择要导入的文件");
			}else{
				$.ajax({
	    			url: "ExcelToHostDevice.do",
	    	    	data: {"filepath":filepath},
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				if(data == "success"){
	    					alert("success");
	    				}
					}
				})
			}
		}
		</script>
		
		
		<script type="text/javascript">
		
		$(top.hangge());
		
		//分页
		function find(index){
			var status;
			var status0=$("#STATUS").val();
			if(status0 ==''){
				status=2;
			}else if(status0 =='0'){
				status=0;
			}else{
				status=1;
			}
			/* alert(status) */
				/* alert(index); */
				$.ajax({
					url:"findDevicesByIndex.do",
			    	data: {"index":index,"status":status }, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){  //list里的实体类都在这里面遍历
						/* alert("success:"+data) */
						//清空标签内 [子标签及内容]，再添加
						$("#devicelist").empty();
						$.each(data,function(i,item){//i是key,item是value
							var currentPage=item.currentPage;
							var totalPages=item.totalPages;
							if(item.deviceId == null){
								//如果是page实体类  处理一
								$("#devicelist01").empty();
								
								//根据 totalPages 和 currentPage 分成 5 种情况
								if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
									var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
								}else{//首页时 上页不能再选择
									var fore='<li><a><font color="#808080">上页</font></a></li>';
								}
								
								//先处理最后一页的分页信息
								if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
									if(currentPage == totalPages && currentPage !=1){
										$("#devicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else{
										$("#devicelist01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												fore+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}	
								}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
									if(currentPage == totalPages){
										$("#devicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										if(currentPage == 1){
											$("#devicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
		        									'<li><a><font color="#808080">上页</font></a></li>'+
		        									'<li><a><font color="#808080">1</font></a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
		        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#devicelist01").append('<ul>'+
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
										$("#devicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#devicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										if(currentPage == 1){
											$("#devicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#devicelist01").append('<ul>'+
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
										$("#devicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												fore+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li><a><font color="#808080">下页</font></a></li>'+
												'<li><a><font color="#808080">尾页</font></a></li>');
									}else if(currentPage == totalPages - 1){
										$("#devicelist01").append('<ul>'+
												'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else if(currentPage == totalPages - 2){
										$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													'<li><a><font color="#808080">上页</font></a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else{
											$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
										$("#devicelist01").append('<ul>'+
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
										$("#devicelist01").append('<ul>'+
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
										$("#devicelist01").append('<ul>'+
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
								var col="";
	           					 var status="";
	           				     var type="";
	           				     /* alert(item.deviceCode); */
	   							 if(item.status ==0){
	   								 col='<td class="center"><span style="color: black;">'+item.deviceCode+'</span></td>';
	   							 }else{
	   								 col='<td class="center"><span style="color: blue;">'+item.deviceCode+'</span></td>';
	   							 }
	           				     if(item.status ==0){
	           				    	 status='<td class="center">离线</td>';
	           				     }else{
	           				    	 status='<td class="center">在线</td>';
	           				     }
	           				     
	           				     if(item.type == ""){
	           				    	 type='<td class="center">F板</td>';
	           				     }else if(item.type == "G"){
	           				    	 type='<td class="center">G板</td>';
	           				     }else if(item.type == "8"){
	           				    	 type='<td class="center">8路控制盒</td>';
	           				     }else if(item.type == "32"){
	           				    	 type='<td class="center">32路控制盒</td>';
	           				     }else{
	           				    	 alert("error");
	           				     }
		           				  $("#devicelist").append('<tr>'+
			       	    					col+
			       	    					status+
			       	    					type+
			       	    					'<shiro:hasRole name="admin">'+
			       	    					'<td class="center"><a href="<%=WEBPATH21 %>/static/jsp/system/device/edit.jsp?deviceCode='+item.deviceCode+'&type='+item.type+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
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
		
		//检索
		function search(){
			/* top.jzts();
			$("#Form").submit(); */
			//先判断条件是什么
			/* alert("ssss"); */
			var DEVICE_CODE=$("#DEVICE_CODE").val();
			//0-离线；1-在线；2-全部
			var STATUS;
			var sel=$("#STATUS").val();
			//$("#STATUS").val() 可以确定选中select标签的value值
			/* alert($("#STATUS").val()) */
			if(sel ==''){
				STATUS=2;
			}else if(sel =='0'){
				STATUS=0;
			}else{
				STATUS=1;
			}
			/* alert(STATUS) */
			if(DEVICE_CODE !=""){
				$.ajax({
	    			url: "findByDevicecode.do",
	    	    	data: {"deviceCode":DEVICE_CODE},
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				/* alert(data) */
	    				if(data == null){
	    					$("#devicelist").empty();
	    					$("#devicelist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');	
	    					
	    				}else{
	    					/* alert("success") */
	    					$("#devicelist").empty();
	    					$("#devicelist01").empty();
	        				$.each(data,function(i,item){//i是key,item是value
	        					 var col="";
	        					 var status="";
	        				     var type="";
	        				     /* alert(item.deviceCode); */
								 if(item.status ==0){
									 col='<td class="center"><span style="color: black;">'+item.deviceCode+'</span></td>';
								 }else{
									 col='<td class="center"><span style="color: blue;">'+item.deviceCode+'</span></td>';
								 }
	        				     if(item.status ==0){
	        				    	 status='<td class="center">离线</td>';
	        				     }else{
	        				    	 status='<td class="center">在线</td>';
	        				     }
	        				     
	        				     if(item.type == ""){
	        				    	 type='<td class="center">F板</td>';
	        				     }else if(item.type == "G"){
	        				    	 type='<td class="center">G板</td>';
	        				     }else if(item.type == "8"){
	        				    	 type='<td class="center">8路控制盒</td>';
	        				     }else if(item.type == "32"){
	        				    	 type='<td class="center">32路控制盒</td>';
	        				     }else{
	        				    	 alert("error");
	        				     }
	        				     $("#devicelist").append('<tr>'+
	 	       	    					col+
	 	       	    					status+
	 	       	    					type+
	 	       	    					'<shiro:hasRole name="admin">'+
	 	       	    					'<td class="center"><a href="<%=WEBPATH21 %>/static/jsp/system/device/edit.jsp?deviceCode='+item.deviceCode+'&type='+item.type+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
	 	       	    					'</shiro:hasRole>'+
	 	       	    					'</tr>');  
	        				})
	        			}
	    			}
				})
			}else if(DEVICE_CODE =="" && (STATUS ==0 || STATUS ==1)){
				/* alert('hhe') */
				$.ajax({
	    			url: "findByStatus.do",
	    	    	data: {"status":STATUS},
	    			type: "POST",
	    			dataType:"json",
	    			async: false,
	    			success: function(data){
	    				/* alert(data) */
	    				if(data == ""){
	    					$("#devicelist").empty();
	    					$("#devicelist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');	
	    				}else{
	    					/* alert("success") */
	    					$("#devicelist").empty();
	        				$.each(data,function(i,item){//i是key,item是value
	        					
	        					var currentPage=item.currentPage;
								var totalPages=item.totalPages;
								if(item.deviceId == null){
									//如果是page实体类  处理一
									$("#devicelist01").empty();
									
									//根据 totalPages 和 currentPage 分成 5 种情况
									if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
										var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
									}else{//首页时 上页不能再选择
										var fore='<li><a><font color="#808080">上页</font></a></li>';
									}
									
									//先处理最后一页的分页信息
									if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
										if(currentPage == totalPages && currentPage !=1){
											$("#devicelist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													fore+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else{
											$("#devicelist01").append('<ul>'+
													'<li><a><font color="#808080">首页</font></a></li>'+
													fore+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}	
									}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
										if(currentPage == totalPages){
											$("#devicelist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else if(currentPage == totalPages - 1){
											if(currentPage == 1){
												$("#devicelist01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
			        									'<li><a><font color="#808080">上页</font></a></li>'+
			        									'<li><a><font color="#808080">1</font></a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
			        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
											}else{
												$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else if(currentPage == totalPages - 1){
											$("#devicelist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else if(currentPage == totalPages - 2){
											if(currentPage == 1){
												$("#devicelist01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
														'<li><a><font color="#808080">上页</font></a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
											}else{
												$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													fore+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li><a><font color="#808080">下页</font></a></li>'+
													'<li><a><font color="#808080">尾页</font></a></li>');
										}else if(currentPage == totalPages - 1){
											$("#devicelist01").append('<ul>'+
													'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
													'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
													'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
										}else if(currentPage == totalPages - 2){
											$("#devicelist01").append('<ul>'+
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
												$("#devicelist01").append('<ul>'+
														'<li><a><font color="#808080">首页</font></a></li>'+
														'<li><a><font color="#808080">上页</font></a></li>'+
														'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
														'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
											}else{
												$("#devicelist01").append('<ul>'+
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
												$("#devicelist01").append('<ul>'+
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
												$("#devicelist01").append('<ul>'+
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
												$("#devicelist01").append('<ul>'+
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
												$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
											$("#devicelist01").append('<ul>'+
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
									var col="";
		           					 var status="";
		           				     var type="";
		           				     /* alert(item.deviceCode); */
		   							 if(item.status ==0){
		   								 col='<td class="center"><span style="color: black;">'+item.deviceCode+'</span></td>';
		   							 }else{
		   								 col='<td class="center"><span style="color: blue;">'+item.deviceCode+'</span></td>';
		   							 }
		           				     if(item.status ==0){
		           				    	 status='<td class="center">离线</td>';
		           				     }else{
		           				    	 status='<td class="center">在线</td>';
		           				     }
		           				     
		           				     if(item.type == ""){
		           				    	 type='<td class="center">F板</td>';
		           				     }else if(item.type == "G"){
		           				    	 type='<td class="center">G板</td>';
		           				     }else if(item.type == "8"){
		           				    	 type='<td class="center">8路控制盒</td>';
		           				     }else if(item.type == "32"){
		           				    	 type='<td class="center">32路控制盒</td>';
		           				     }else{
		           				    	 alert("error");
		           				     }
			           				  $("#devicelist").append('<tr>'+
				       	    					col+
				       	    					status+
				       	    					type+
				       	    					'<shiro:hasRole name="admin">'+
				       	    					'<td class="center"><a href="<%=WEBPATH21 %>/static/jsp/system/device/edit.jsp?deviceCode='+item.deviceCode+'&type='+item.type+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
				       	    					'</shiro:hasRole>'+
				       	    					'</tr>');  
			    				}
	        				
	        				}) 
	
	    				}
	    				
	    			}
	    		});
			}else if(DEVICE_CODE =="" &&   STATUS ==2 ){
				/* alert('hhe')  */
				$.ajax({
	    			url: "showHostDevices.do",
	    	    	data: { },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				/* alert(data) */
	    				if(data == ""){
	    					$("#devicelist").empty();
	    					$("#devicelist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					/* alert("success") */
	    					$("#total").text("2077台 ");
	    					$("#devicelist").empty();
	    					$("#devicelist01").empty();
	        				$.each(data,function(i,item){//i是key,item是value
	        					if(item.deviceCode == null){
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
	        						
	        						$("#devicelist01").append(table);
	        					}else{
	        						 var col="";
		           					 var status="";
		           				     var type="";
		           				     /* alert(item.deviceCode); */
		   							 if(item.status ==0){
		   								 col='<td class="center"><span style="color: black;">'+item.deviceCode+'</span></td>';
		   							 }else{
		   								 col='<td class="center"><span style="color: blue;">'+item.deviceCode+'</span></td>';
		   							 }
		           				     if(item.status ==0){
		           				    	 status='<td class="center">离线</td>';
		           				     }else{
		           				    	 status='<td class="center">在线</td>';
		           				     }
		           				     
		           				     if(item.type == ""){
		           				    	 type='<td class="center">F板</td>';
		           				     }else if(item.type == "G"){
		           				    	 type='<td class="center">G板</td>';
		           				     }else if(item.type == "8"){
		           				    	 type='<td class="center">8路控制盒</td>';
		           				     }else if(item.type == "32"){
		           				    	 type='<td class="center">32路控制盒</td>';
		           				     }else{
		           				    	 alert("error");
		           				     }
		         					$("#devicelist").append('<tr>'+
		       	    					col+
		       	    					status+
		       	    					type+
		       	    					'<shiro:hasRole name="admin">'+
		       	    					'<td class="center"><a href="<%=WEBPATH21 %>/static/jsp/system/device/edit.jsp?deviceCode='+item.deviceCode+'&type='+item.type+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		       	    					'</shiro:hasRole>'+
		       	    					'</tr>');  
		        					}
	        				}) 

	    				}
	    				
	    			}
	    		});
			} 
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
		

		</script>
		
	</body>
</html>

