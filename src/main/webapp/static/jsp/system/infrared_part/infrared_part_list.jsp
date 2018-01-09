<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String WEBPATH32 = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+WEBPATH32+"/";
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
							<input autocomplete="off" id="nav-search-input" type="text" name="DEVICE_ADDRESS" value="" placeholder="这里输入红外地址码" />
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
					<!-- <td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i></a></td> -->
					<shiro:hasRole name="admin">
						<td style="vertical-align:top;"><input id="filepath" type="file" class="btn btn-mini btn-light " ></input><button id="nav-search-icon" vallue="导入" class="icon-cloud-upload" style="border:none;" onclick="fromExcel();"></button></td>
					</shiro:hasRole>
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
						<th class="center">红外地址码</th>
						<th class="center">红外验证码</th>
						<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole>
					</tr>
				</thead>
										
				<tbody id="infraredlist">
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.DEVICE_ADDRESS}</td>
										<td>${var.VALIDATION_CODE}</td>
								<td style="width: 30px;" class="center">
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<c:if test="${QX.edit == 1 }">
											<a style="cursor:pointer;" title="编辑" onclick="edit('${var.ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a>
											</c:if>
											<c:if test="${QX.del == 1 }">
											<a style="cursor:pointer;" title="删除" onclick="del('${var.ID}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a>
											</c:if>
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
		<table style="width:100%;">
			<shiro:hasRole name="admin">
			<tr>
				<td style="vertical-align:top;">
					<%-- <c:if test="${QX.add == 1 }"> --%>
					<a class="btn btn-small btn-success" href="<%=WEBPATH32 %>/static/jsp/system/infrared_part/add.jsp">新增</a>
					<%-- </c:if> --%>
					<%-- <c:if test="${QX.del == 1 }"> --%>
					<a class="btn btn-small btn-danger" onclick="makeAll();" title="批量删除" ><i class='icon-trash'></i></a>
					<%-- </c:if> --%>
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
				<td style="vertical-align:top;">
					<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="redlist">
						
					</div>
				</td>
			</tr>
			</shiro:hasRole>
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
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH32 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH32 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH32 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH32 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH32 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH32 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH32 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH32 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		$(function(){
			//显示所有数据
			$.ajax({
    			url: "showDevicesRed.do",
    	    	data: { },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				if(data == ""){
    					$("#infraredlist").empty();
    					$("#infraredlist").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');					
    				}else{
    					
        				$.each(data,function(i,item){//i是key,item是value
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
        						
        						$("#redlist").append(table);
        					}else{
        						$("#infraredlist").append('<tr>'+
            	    					'<td class="center" style="width: 30px;">'+
            	    					'<label>'+
            	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
            	    					'<span class="lbl">'+
            	    					'</span>'+
            	    					'</label>'+
            	    					'</td>'+
            	    					'<td class="center">'+(i+1)+'</td>'+
            	    					'<td class="center">'+item.deviceAddress+'</td>'+
            	    					'<td class="center">'+item.validationCode+'</td>'+
            	    					'<shiro:hasRole name="admin">'+
            	    					'<td style="width: 30px;" class="center">'+
        									'<div class="hidden-phone visible-desktop btn-group">'+
        										'<div class="inline position-relative">'+
        										'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
        										'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
        											'<li><a style="cursor:pointer;" title="编辑" href="<%=WEBPATH32 %>/static/jsp/system/infrared_part/edit.jsp?id='+item.id+'&deviceAddress='+item.deviceAddress+'&validationCode='+item.validationCode+'" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
        											'<li><a style="cursor:pointer;" title="删除" onclick="del('+item.id+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
        										'</ul>'+
        									'</div>'+
        								'</div>'+
        								'</td>'+
        								'</shiro:hasRole>'+
            	    					'</tr>'); 
        					}					  
        				}) 
        				
    				}
    				
    			}
    		});
		})
		//打开上传excel页面
		function fromExcel(){
			var filepath=$("#filepath").val();
			if(filepath ==""){
				alert("请先选择要导入的文件");
			}else{
				$.ajax({
	    			url: "RedtoExcelToRed.do",
	    	    	data: {"filepath":filepath},
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				if(data == "success"){
	    					alert("已导入成功！");
	    				}
					}
				})
			}
		}
		</script>
		
		<script type="text/javascript">
		
		$(top.hangge());
		//分页 find(index)
		function find(index){
			/* alert(index); */
			$.ajax({
				url:"findredByIndex.do",
		    	data: {"index":index }, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){  //list里的实体类都在这里面遍历
					/* alert("success:"+data) */
					//清空标签内 [子标签及内容]，再添加
					$("#infraredlist").empty();
					$.each(data,function(i,item){//i是key,item是value
						var currentPage=item.currentPage;
						var totalPages=item.totalPages;
						if(item.id == null){
							//如果是page实体类  处理一
							$("#redlist").empty();
							if((currentPage >= 1) &&  (currentPage <= 5) && (totalPages >= 6)){
								if(currentPage ==1){
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
										'</ul>');
								}else{
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>'+
										'</ul>');
								}else{
									$("#redlist").append('<ul>'+
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
									$("#redlist").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											'<li><a><font color="#808080">上页</font></a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>'+
										'</ul>');
								}else{
									$("#redlist").append('<ul>'+
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
							$("#infraredlist").append('<tr>'+
									'<td class="center" style="width: 30px;">'+
        	    					'<label>'+
        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
        	    					'<span class="lbl">'+
        	    					'</span>'+
        	    					'</label>'+
        	    					'</td>'+
        	    					'<td class="center">'+(i+1)+'</td>'+
        	    					'<td class="center">'+item.deviceAddress+'</td>'+
        	    					'<td class="center">'+item.validationCode+'</td>'+
        	    					'<shiro:hasRole name="admin">'+
        	    					'<td style="width: 30px;" class="center">'+
    									'<div class="hidden-phone visible-desktop btn-group">'+
    										'<div class="inline position-relative">'+
    										'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
    										'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
    											'<li><a style="cursor:pointer;" title="编辑" href="<%=WEBPATH32 %>/static/jsp/system/infrared_part/edit.jsp?id='+item.id+'&deviceAddress='+item.deviceAddress+'&validationCode='+item.validationCode+'" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
    											'<li><a style="cursor:pointer;" title="删除" onclick="del('+item.id+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
    										'</ul>'+
    									'</div>'+
    								'</div>'+
    								'</td>'+
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
		
		
		//根据红外地址吗 检索
		function search(){//
			/* top.jzts();
			$("#Form").submit(); */
			var deviceAddress=$("#nav-search-input").val();
			/* alert(deviceAddress);  */
			$.ajax({
    			url: "findRedByAddr.do",
    	    	data: {"deviceAddress":deviceAddress },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				/* alert(data) */
    				if(data == ""){
    					$("#infraredlist").empty();
    					$("#infraredlist").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');					
    				}else{
    					/* alert("success")  */
    					$.each(data,function(i,item){//i是key,item是value
    						$("#infraredlist").empty();
     						 $("#infraredlist").append('<tr>'+
     								'<td class="center" style="width: 30px;">'+
        	    					'<label>'+
        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
        	    					'<span class="lbl">'+
        	    					'</span>'+
        	    					'</label>'+
        	    					'</td>'+
        	    					'<td class="center">'+(i+1)+'</td>'+
        	    					'<td class="center">'+item.deviceAddress+'</td>'+
        	    					'<td class="center">'+item.validationCode+'</td>'+
        	    					'<shiro:hasRole name="admin">'+
        	    					'<td style="width: 30px;" class="center">'+
    									'<div class="hidden-phone visible-desktop btn-group">'+
    										'<div class="inline position-relative">'+
    										'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
    										'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
    											'<li><a style="cursor:pointer;" title="编辑" href="<%=WEBPATH32 %>/static/jsp/system/infrared_part/edit.jsp?id='+item.id+'&deviceAddress='+item.deviceAddress+'&validationCode='+item.validationCode+'" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
    											'<li><a style="cursor:pointer;" title="删除" onclick="del('+item.id+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
    										'</ul>'+
    									'</div>'+
    								'</div>'+
    								'</td>'+
    								'</shiro:hasRole>'+
        	    					'</tr>'); 
       					}) 
    				}
				}
			})
		}
		
		
		//删除
		function del(Id){
			$.ajax({
    			url: "delDeviceRed.do",
    	    	data: {"id":Id},
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				alert("删除成功");
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
		</script>
		
	</body>
</html>

