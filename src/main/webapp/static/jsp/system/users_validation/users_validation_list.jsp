<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String WEBPATH12 = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ WEBPATH12 + "/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="/static/jsp/top.jsp"%> 
	</head>
<body>
<div>
	<%-- <shiro:authenticated>
		<p>验证通过</p>
	</shiro:authenticated> --%>
</div>	
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
							<input autocomplete="off" id="nav-search-input" type="text" name="field1" value="" placeholder="这里输入手机号" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<!-- <td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td> -->
					<td style="vertical-align:top;"><input type="button" style="border:none;" value="搜索"  onclick="search();"  title="检索"></input></td>
					<%-- <c:if test="${QX.cha == 1 }"> --%>
					<shiro:hasRole name="admin">
						<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					<%-- </c:if> --%>
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
						<th class="center">手机号</th>
						<th class="center">验证码</th>
						<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole>
					</tr>
				</thead>
										
				<tbody id="validations">
					
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
								<td>${var.USER_PHONE}</td>
								<td>${var.VERIFICATION_CODE}</td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<c:if test="${QX.edit == 1 }">
											<li><a style="cursor:pointer;" title="编辑" onclick="edit('${var.ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											</c:if>
											<c:if test="${QX.del == 1 }">
											<li><a style="cursor:pointer;" title="删除" onclick="del('${var.ID}');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
											</c:if>
										</ul>
										</div>
									</div>
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
				<tr>
					<shiro:hasRole name="admin">
						<td style="vertical-align:top;">
							<a class="btn btn-small btn-success" href="<%=WEBPATH12 %>/static/jsp/system/users_validation/add.jsp">新增</a>
							<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
						</td>
					</shiro:hasRole>	
					<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
					<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="usersvalidations01">	
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
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH12 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH12 %>/static/js/bootstrap.min.js"></script>
		<script src="<%=WEBPATH12 %>/static/js/ace-elements.min.js"></script>
		<script src="<%=WEBPATH12 %>/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="<%=WEBPATH12 %>/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="<%=WEBPATH12 %>/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="<%=WEBPATH12 %>/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="<%=WEBPATH12 %>/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		$(function(){
			$.ajax({
    			url: "showTelValidations.do",
    	    	data: { },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				if(data == null){
    					$("#validations").append('<tr class="main_info">'+
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
        						
        						$("#usersvalidations01").append(table);
        					}else{
        						$("#validations").append('<tr>'+
            	    					'<td class="center" style="width: 30px;">'+
            	    					'<label>'+
            	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
            	    					'<span class="lbl">'+
            	    					'</span>'+
            	    					'</label>'+
            	    					'</td>'+
            	    					'<td class="center">'+(i+1)+'</td>'+
            	    					'<td class="center">'+item.userPhone+'</td>'+
            	    					'<td class="center">'+item.verificationCode+'</td>'+
            	    					'<shiro:hasRole name="admin">'+
            	    					'<td style="width: 30px;" class="center">'+
        									'<div class="hidden-phone visible-desktop btn-group">'+
        										'<div class="inline position-relative">'+
        											'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
        											'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
        												'<li><a style="cursor:pointer;" title="编辑" href="<%=WEBPATH12 %>/static/jsp/system/users_validation/edit.jsp?id='+item.id+'&userPhone='+item.userPhone+'&verificationCode='+item.verificationCode+'" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
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
		
		//分页 find(index)
		function find(index){
			/* alert(index); */
			$.ajax({
				url:"findValidationByIndex.do",
		    	data: {"index":index }, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){  //list里的实体类都在这里面遍历
					/* alert("success:"+data) */
					//清空标签内 [子标签及内容]，再添加
					$("#validations").empty();
					$.each(data,function(i,item){//i是key,item是value
						var currentPage=item.currentPage;
						var totalPages=item.totalPages;
						if(item.id == null){
							//如果是page实体类  处理一
							$("#usersvalidations01").empty();
							
							
							//根据 totalPages 和 currentPage 分成 5 种情况
							if(totalPages > 5  || currentPage != 1){//这里处理 currentPage未最左边一页   时的上一页问题
								var fore='<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>';
							}else{//首页时 上页不能再选择
								var fore='<li><a><font color="#808080">上页</font></a></li>';
							}
							
							//先处理最后一页的分页信息
							if( (totalPages % 5 == 1) && (currentPage == totalPages) ){//第一种情况：总条数     除 5    余1
								if(currentPage == totalPages && currentPage !=1){
									$("#usersvalidations01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else{
									$("#usersvalidations01").append('<ul>'+
											'<li><a><font color="#808080">首页</font></a></li>'+
											fore+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}	
							}else if( (totalPages % 5 == 2) && ((currentPage == totalPages) || (currentPage == totalPages - 1)) ){
								if(currentPage == totalPages){
									$("#usersvalidations01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else if(currentPage == totalPages - 1){
									if(currentPage == 1){
										$("#usersvalidations01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
	        									'<li><a><font color="#808080">上页</font></a></li>'+
	        									'<li><a><font color="#808080">1</font></a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">2</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find(2)">下页</a></li>'+
	        									'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else{
										$("#usersvalidations01").append('<ul>'+
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
									$("#usersvalidations01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else if(currentPage == totalPages - 1){
									$("#usersvalidations01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
								}else if(currentPage == totalPages - 2){
									if(currentPage == 1){
										$("#usersvalidations01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else{
										$("#usersvalidations01").append('<ul>'+
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
									$("#usersvalidations01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											fore+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-3)+')">'+(currentPage-3)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li><a><font color="#808080">下页</font></a></li>'+
											'<li><a><font color="#808080">尾页</font></a></li>');
								}else if(currentPage == totalPages - 1){
									$("#usersvalidations01").append('<ul>'+
											'<li style="cursor:pointer;"><a onclick="find(1)">首页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">上页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-2)+')">'+(currentPage-2)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage-1)+')">'+(currentPage-1)+'</a></li>'+
											'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
											'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
								}else if(currentPage == totalPages - 2){
									$("#usersvalidations01").append('<ul>'+
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
										$("#usersvalidations01").append('<ul>'+
												'<li><a><font color="#808080">首页</font></a></li>'+
												'<li><a><font color="#808080">上页</font></a></li>'+
												'<li><a><font color="#808080">'+currentPage+'</font></a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">'+(currentPage+1)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+2)+')">'+(currentPage+2)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+3)+')">'+(currentPage+3)+'</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+(currentPage+1)+')">下页</a></li>'+
												'<li style="cursor:pointer;"><a onclick="find('+totalPages+')">尾页</a></li>');
									}else{
										$("#usersvalidations01").append('<ul>'+
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
										$("#usersvalidations01").append('<ul>'+
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
										$("#usersvalidations01").append('<ul>'+
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
										$("#usersvalidations01").append('<ul>'+
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
										$("#usersvalidations01").append('<ul>'+
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
									$("#usersvalidations01").append('<ul>'+
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
									$("#usersvalidations01").append('<ul>'+
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
									$("#usersvalidations01").append('<ul>'+
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
							$("#validations").append('<tr>'+
									'<td class="center" style="width: 30px;">'+
        	    					'<label>'+
        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
        	    					'<span class="lbl">'+
        	    					'</span>'+
        	    					'</label>'+
        	    					'</td>'+
        	    					'<td class="center">'+(i+1)+'</td>'+
        	    					'<td class="center">'+item.userPhone+'</td>'+
        	    					'<td class="center">'+item.verificationCode+'</td>'+
        	    					'<shiro:hasRole name="admin">'+
        	    					'<td style="width: 30px;" class="center">'+
    									'<div class="hidden-phone visible-desktop btn-group">'+
    										'<div class="inline position-relative">'+
    											'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
    											'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
    												'<li><a style="cursor:pointer;" title="编辑" href="<%=WEBPATH12 %>/static/jsp/system/users_validation/edit.jsp?id='+item.id+'&userPhone='+item.userPhone+'&verificationCode='+item.verificationCode+'" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
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
		
		//检索
		function search(){
			/* top.jzts();
			$("#Form").submit(); */
		    var userPhone=$("#nav-search-input").val();
			if(userPhone != ""){
				$.ajax({
	    			url: "findValidationByTel.do",
	    	    	data: {"userPhone":userPhone },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				/* alert("success"); */
	    				if(data == ""){
	    					$("#validations").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					/* alert("success") */
	    					$("#validations").empty();
	    					$.each(data,function(i,item){//i是key,item是value
	        					
	     						 $("#validations").append('<tr>'+
	     								'<td class="center" style="width: 30px;">'+
            	    					'<label>'+
            	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
            	    					'<span class="lbl">'+
            	    					'</span>'+
            	    					'</label>'+
            	    					'</td>'+
            	    					'<td class="center">'+(i+1)+'</td>'+
            	    					'<td class="center">'+item.userPhone+'</td>'+
            	    					'<td class="center">'+item.verificationCode+'</td>'+
            	    					'<shiro:hasRole name="admin">'+
            	    					'<td style="width: 30px;" class="center">'+
        									'<div class="hidden-phone visible-desktop btn-group">'+
        										'<div class="inline position-relative">'+
        											'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
        											'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
        												'<li><a style="cursor:pointer;" title="编辑" href="<%=WEBPATH12 %>/static/jsp/system/users_validation/edit.jsp?id='+item.id+'&userPhone='+item.userPhone+'&verificationCode='+item.verificationCode+'" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
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
			}else{
				alert("请先输入手机号！");
			}
			
		}
		
	
		
		//删除
		function del(Id){
			/* alert(Id) */
			$.ajax({
    			url: "delTelValidation.do",
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
		
		//修改
		function edit(Id){//Id暂时不用
			 $.ajax({
	    			url: "modifyHostDevice.do",
	    	    	data: {"deviceCode":deviceCode,"type":type },
	    			type: "POST",
	    			dataType:"json",
	    			async: true,
	    			success: function(data){
	    				alert("修改成功！");
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
		function makeAll(msg){
			$('input:checkbox:checked').each(function (index, item) {
				//逐个取出id
				/* alert($(this).val()); */
				del($(this).val());
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
								url: '<%=basePath%>users_validation/deleteAll.do?tm='+new Date().getTime(),
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
		
		//导出excel   (命名、位置什么的感觉需要改改)
		function toExcel(){
			$.ajax({
    			url: "ValidationtoExcel.do",
    	    	data: { },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				if(data == "success"){
    					alert("已导出成功！");
    				}
				}
			});
		}
		</script>
		
	</body>
</html>

