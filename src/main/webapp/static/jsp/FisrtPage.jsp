<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
						
  <div class="row-fluid">
			<table  id="headdd">
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off"  type="text" name="NPC" id="NPC" value="" placeholder="输入昵称、手机号或主机序列号" style="width: 200px;"/>
						</span>
						<span class="input-icon">
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
					<td style="vertical-align:top;"><input type="button" style="border:none;height:30px;" value="搜索"  onclick="search();"  title="检索"></input></td>
				</tr>
			</table>
			
			<table id="table_report1"  style="display:none;" class="table table-striped table-bordered table-hover">
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
					
				</tbody>
			</table>
			
			<div class="control-group" id="tel" style="display:none;position:fixed;left:40%;top:30%;margin-left:width/2;margin-top:height/2;">
					<div class="controls">
						<div class="main_input_box">
							<span>
								<input type="text" name="userPhone" id="userPhone"  value="" placeholder="请输入管理员账号(手机号)"/>
							</span>
							<span>
								<input type="button" style="border:none;height:30px;margin-top:-10px;" value="搜索"  onclick="buyerSearch();"  title="检索"></input>
							</span>
						</div>
					</div>
			</div>
			
			<input type="hidden" id="savedeviceCode" name="savedeviceCode" value="" placeholder="这个隐藏域用于存放deviceCode"> 
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
						<th class="center">操作</th>
						<%-- <shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> --%>
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
		<link rel="stylesheet" href="<%=WEBPATH3 %>/static/css/jquery-ui-1.9.2.custom.css" />
  		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH3 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
  		<script src="<%=WEBPATH3 %>/static/js/jquery-ui-1.9.2.custom.min.js"></script>
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
			var session= '<%= session.getAttribute("role")%>';
			var tel='<%= session.getAttribute("userPhone")%>';
			/* alert("tel:"+tel);  */
			/* alert(tel=="null");  */
			/* alert("session:"+session); */
			/* alert(session); */
			if(session == "buyer"){
				$("#headdd").hide();
				$("#table_report").hide();
				$("#userdeviceslist").hide();
				$("#tel").show();
				/* alert("buyer"); */
				if(tel!="null"){
					$("#tel").hide();
					//直接显示管理员信息
					/* alert("直接显示管理员信息"); */
					$.ajax({
		    			url: "buyerHomePage.do",
		    	    	data: {"userPhone":tel },
		    			type: "POST",
		    			dataType:"json",
		    			async: true,
		    			success: function(data){
		    				$("#tel").hide();
		    				$("#headdd").show();
		    				$("#table_report").show();
		    				$("#userdeviceslist").show();
		    				/* alert(data[0].DEVICE_CODE==null);  */
		    				if(data[0].DEVICE_CODE==null){
		    					$("#userdeviceslist").empty();
		    					$("#userdeviceslist0").append('<tr class="main_info">'+
		    					'<td colspan="100" class="center" >没有相关数据</td>'+
		    					'</tr>');	
		    				}else{
		        				$.each(data,function(i,item){//i是key,item是value
		        					$("#userdeviceslist").empty();
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
		        						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
		        						var col="";
										var creator_date="";
										var updated_date="";
										var status="";
										var userName="";
										var userPhone="";
										var signature="";
										/* json对象 转成 String类型,再转成单引号 */
										var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
										/* alert(a);  */
			           				     /* alert(item.deviceCode); */
			   							 if(item.HOST_STATUS =="离线"){
			   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')"  style="color: black;">'+item.DEVICE_CODE+'</a></td>';
			   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
			   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
			   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
			   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
			   							 }else{
			   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
			   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
			   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
			   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
			   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
			   								 if(item.MNT_CREATOR_DATE !=null){
				           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
				           				     }else{
				           				    	creator_date='<td></td>';
				           				     }
				           				     if(item.MNT_UPDATED_DATE !=null){
				           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
				           				     }else{
				           				    	updated_date='<td></td>';
				           				     }
			   							 }
			           				     
			   							
			         					$("#userdeviceslist0").append('<tr>'+
			         							'<td class="center" style="width: 30px;">'+
		   	    	    					'<label>'+
		   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		   	    	    					'<span class="lbl">'+
		   	    	    					'</span>'+
		   	    	    					'</label>'+
		   	    	    					'</td>'+
		   	    	    						userName+
		   	    	    						userPhone+
			       	    						col+
			       	    						creator_date+
			       	    						updated_date+
			       	    						status+
			       	    						signature+
			         							'<shiro:hasRole name="buyer">'+
				       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
				       	    					'</shiro:hasRole>'+
			       	    					'</tr>'); 
			        					}
		    					})	
		    				}
		    			}
					})
				}else{
					$("#headdd").hide();
					$("#table_report").hide();
					$("#userdeviceslist").hide();
					$("#tel").show();
				}
			}else{
				/* alert("for company"); */
				/* 如果是管理员或一般用户，直接调出所有数据 */
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
	        						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	        						var col="";
									var creator_date="";
									var updated_date="";
									var status="";
									var userName="";
									var userPhone="";
									var signature="";
									/* json对象 转成 String类型,再转成单引号 */
									var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
									/* alert(a);  */
		           				     /* alert(item.deviceCode); */
		   							 if(item.HOST_STATUS =="离线"){
		   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')"  style="color: black;">'+item.DEVICE_CODE+'</a></td>';
		   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
		   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
		   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
		   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
		   							 }else{
		   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
		   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
		   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
		   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
		   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
		   								 if(item.MNT_CREATOR_DATE !=null){
			           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
			           				     }else{
			           				    	creator_date='<td></td>';
			           				     }
			           				     if(item.MNT_UPDATED_DATE !=null){
			           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
			           				     }else{
			           				    	updated_date='<td></td>';
			           				     }
		   							 }
		           				     if(session == "admin"){
		           				    	$("#userdeviceslist0").append('<tr>'+
			         							'<td class="center" style="width: 30px;">'+
		   	    	    					'<label>'+
		   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		   	    	    					'<span class="lbl">'+
		   	    	    					'</span>'+
		   	    	    					'</label>'+
		   	    	    					'</td>'+
		   	    	    						userName+
		   	    	    						userPhone+
			       	    						col+
			       	    						creator_date+
			       	    						updated_date+
			       	    						status+
			       	    						signature+
			         							'<shiro:hasRole name="admin">'+
				       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
				       	    					'</shiro:hasRole>'+
			       	    					'</tr>'); 
		           				     }else{
		           				    	$("#userdeviceslist0").append('<tr>'+
			         							'<td class="center" style="width: 30px;">'+
		   	    	    					'<label>'+
		   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		   	    	    					'<span class="lbl">'+
		   	    	    					'</span>'+
		   	    	    					'</label>'+
		   	    	    					'</td>'+
		   	    	    						userName+
		   	    	    						userPhone+
			       	    						col+
			       	    						creator_date+
			       	    						updated_date+
			       	    						status+
			       	    						signature+
				       	    					'<td class="center"></td>'+
			       	    					'</tr>');  
		           				     }
		        					}
	    					})	
	    				}
	    			}
				})
				
				
			}
			
		});
		
		
		</script>
		<script type="text/javascript">
		
		//分页
		function find(index){
			var role= '<%= session.getAttribute("role")%>'; 
			/* alert(role); */
			/* alert("index:"+index); */
			var NPC=$("#NPC").val();
			/* alert("npc:"+npc); */
			var sel=$("#STATUS").val();
			if(sel ==''){
				STATUS=2;
			}else if(sel =='0'){
				STATUS=0;
			}else{
				STATUS=1;
			}
			if(NPC !=""){
				/* alert("通过NPC+status方法查找");  */
				$.ajax({
	    			url: "findhomePageByNPC.do",
	    	    	data: {"npc":NPC,"status":STATUS,"index":index},
	    			type: "POST",
	    			dataType:"json",
	    			async: false,
	    			success: function(data){
	    				/* alert(data=="") */
	    				if(data==""){
	    					$("#userdeviceslist0").empty();
       						$("#userdeviceslist").empty();
       						$("#userdeviceslist0").append('<tr class="main_info">'+
       		    					'<td colspan="100" class="center" >没有相关数据</td>'+
       		    					'</tr>');
	    				}else{
	    					$("#userdeviceslist0").empty();
	    					$.each(data,function(i,item){//i是key,item是value
	           					if(item.DEVICE_CODE == null){
	           						var currentPage=item.currentPage;
	           						var totalPages=item.totalPages;
	           						
	           						$("#userdeviceslist").empty();
	           						/* alert(totalPages)  */
	           						var table="";
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
	           						
	           						$("#userdeviceslist").append(table);
	           					}else{
	           						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	           						var col="";
	   								var creator_date="";
	   								var updated_date="";
	   								var status="";
	   								var userName="";
	   								var userPhone="";
	   								var signature="";
	   								/* json对象 转成 String类型,再转成单引号 */
	   								var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	   								/* alert(a);  */
	   	           				     /* alert(item.deviceCode); */
	   	   							 if(item.HOST_STATUS =="离线"){
	   	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: black;">'+item.DEVICE_CODE+'</a></td>';
	   	   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
	   	   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
	   	   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
	   	   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
	   	   							 }else{
	   	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
	   	   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
	   	   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
	   	   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
	   	   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
	   	   								 if(item.MNT_CREATOR_DATE !=null){
	   		           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
	   		           				     }else{
	   		           				    	creator_date='<td></td>';
	   		           				     }
	   		           				     if(item.MNT_UPDATED_DATE !=null){
	   		           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
	   		           				     }else{
	   		           				    	updated_date='<td></td>';
	   		           				     }
	   	   							 }
	   	           				     if(role == "admin"){
		   	           				   $("#userdeviceslist0").append('<tr>'+
		   	         							'<td class="center" style="width: 30px;">'+
		      	    	    						'<label>'+
		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		      	    	    							'<span class="lbl">'+
		      	    	    							'</span>'+
		      	    	    						'</label>'+
		      	    	    					'</td>'+
		      	    	    					userName+
		      	    	    					userPhone+
		   	       	    						col+
		   	       	    						creator_date+
		   	       	    						updated_date+
		   	       	    						status+
		   	       	    						signature+
		   	         							'<shiro:hasRole name="admin">'+
		   		       	    					'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		   		       	    					'</shiro:hasRole>'+
		   	       	    					'</tr>'); 
	   	           				     }else if(role == "buyer"){
		   	           				   $("#userdeviceslist0").append('<tr>'+
		   	         							'<td class="center" style="width: 30px;">'+
		      	    	    						'<label>'+
		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		      	    	    							'<span class="lbl">'+
		      	    	    							'</span>'+
		      	    	    						'</label>'+
		      	    	    					'</td>'+
		      	    	    					userName+
		      	    	    					userPhone+
		   	       	    						col+
		   	       	    						creator_date+
		   	       	    						updated_date+
		   	       	    						status+
		   	       	    						signature+
		   	         							'<shiro:hasRole name="buyer">'+
		   		       	    					'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		   		       	    					'</shiro:hasRole>'+
		   	       	    					'</tr>'); 
	   	           				     }else{
		   	           				   $("#userdeviceslist0").append('<tr>'+
		   	         							'<td class="center" style="width: 30px;">'+
		      	    	    						'<label>'+
		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		      	    	    							'<span class="lbl">'+
		      	    	    							'</span>'+
		      	    	    						'</label>'+
		      	    	    					'</td>'+
		      	    	    					userName+
		      	    	    					userPhone+
		   	       	    						col+
		   	       	    						creator_date+
		   	       	    						updated_date+
		   	       	    						status+
		   	       	    						signature+
		   		       	    					'<td class="center"></td>'+
		   	       	    					'</tr>'); 
	   	           				     }
	   	   							
	   	        					}
	       					})
	    				}
	    			}
	    		});
			}else {
				/* alert("通过status方法查找");  */
				/* alert("STATUS:"+STATUS); */
				var judge=$("#table_report").css("display");//none或table
				/* alert("judge:"+judge); */
				if(judge == "table"){
					/* alert("table"); */
					$.ajax({
		    			url: "findhomePageByStatus.do",
		    	    	data: {"status":STATUS,"index":index},
		    			type: "POST",
		    			dataType:"json",
		    			async: false,
		    			success: function(data){
		    				if(data == null){
		    					$("#userdeviceslist0").empty();
		    					$("#userdeviceslist").empty();
		    					$("#userdeviceslist0").append('<tr class="main_info">'+
		    					'<td colspan="100" class="center" >没有相关数据</td>'+
		    					'</tr>');	
		    					
		    				}else{
		    					$("#userdeviceslist0").empty();
	            				$.each(data,function(i,item){//i是key,item是value
	            					if(item.DEVICE_CODE == null){
	            						var currentPage=item.currentPage;
	            						var totalPages=item.totalPages;
	            						
	            						$("#userdeviceslist").empty();
	            						/* alert(totalPages)  */
	            						var table="";
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
	            						
	            						$("#userdeviceslist").append(table);
	            					}else{
	            						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	            						var col="";
	    								var creator_date="";
	    								var updated_date="";
	    								var status="";
	    								var userName="";
	    								var userPhone="";
	    								var signature="";
	    								/* json对象 转成 String类型,再转成单引号 */
	    								var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	    								/* alert(a);  */
	    	           				     /* alert(item.deviceCode); */
	    	   							 if(item.HOST_STATUS =="离线"){
	    	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: black;">'+item.DEVICE_CODE+'</a></td>';
	    	   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
	    	   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
	    	   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
	    	   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
	    	   							 }else{
	    	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
	    	   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
	    	   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
	    	   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
	    	   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
	    	   								 if(item.MNT_CREATOR_DATE !=null){
	    		           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
	    		           				     }else{
	    		           				    	creator_date='<td></td>';
	    		           				     }
	    		           				     if(item.MNT_UPDATED_DATE !=null){
	    		           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
	    		           				     }else{
	    		           				    	updated_date='<td></td>';
	    		           				     }
	    	   							 }
	    	   							if(role == "admin"){
	 		   	           				   $("#userdeviceslist0").append('<tr>'+
	 		   	         							'<td class="center" style="width: 30px;">'+
	 		      	    	    						'<label>'+
	 		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	 		      	    	    							'<span class="lbl">'+
	 		      	    	    							'</span>'+
	 		      	    	    						'</label>'+
	 		      	    	    					'</td>'+
	 		      	    	    					userName+
	 		      	    	    					userPhone+
	 		   	       	    						col+
	 		   	       	    						creator_date+
	 		   	       	    						updated_date+
	 		   	       	    						status+
	 		   	       	    						signature+
	 		   	         							'<shiro:hasRole name="admin">'+
	 		   		       	    					'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
	 		   		       	    					'</shiro:hasRole>'+
	 		   	       	    					'</tr>'); 
	 	   	           				     }else if(role == "buyer"){
	 		   	           				   $("#userdeviceslist0").append('<tr>'+
	 		   	         							'<td class="center" style="width: 30px;">'+
	 		      	    	    						'<label>'+
	 		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	 		      	    	    							'<span class="lbl">'+
	 		      	    	    							'</span>'+
	 		      	    	    						'</label>'+
	 		      	    	    					'</td>'+
	 		      	    	    					userName+
	 		      	    	    					userPhone+
	 		   	       	    						col+
	 		   	       	    						creator_date+
	 		   	       	    						updated_date+
	 		   	       	    						status+
	 		   	       	    						signature+
	 		   	         							'<shiro:hasRole name="buyer">'+
	 		   		       	    					'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
	 		   		       	    					'</shiro:hasRole>'+
	 		   	       	    					'</tr>'); 
	 	   	           				     }else{
	 		   	           				   $("#userdeviceslist0").append('<tr>'+
	 		   	         							'<td class="center" style="width: 30px;">'+
	 		      	    	    						'<label>'+
	 		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	 		      	    	    							'<span class="lbl">'+
	 		      	    	    							'</span>'+
	 		      	    	    						'</label>'+
	 		      	    	    					'</td>'+
	 		      	    	    					userName+
	 		      	    	    					userPhone+
	 		   	       	    						col+
	 		   	       	    						creator_date+
	 		   	       	    						updated_date+
	 		   	       	    						status+
	 		   	       	    						signature+
	 		   		       	    					'<td class="center"></td>'+
	 		   	       	    					'</tr>'); 
	 	   	           				     }
	    	        					}
	        					})	
	        				}
		    			}
		    		});
				}else{//此时原本显示数据的body隐藏起来了
					/* alert("none"); */
					var a=$("#savedeviceCode").val();
					/* alert(a) */
					$.ajax({
						url:"findUDsByDevicecode.do",
				    	data: {"deviceCode":a ,"index":index}, 
				    	type: "POST",
						dataType:"json",
						async: true,
						success: function(data){
		    				$("#userDevicelist").empty();
		    				var j=0;
		    				$.each(data,function(i,item){//i是key,item是value
								if(data == ""){
			    					$("#userDevicelist").append('<tr class="main_info">'+
			    					'<td colspan="100" class="center" >没有相关数据</td>'+
			    					'</tr>');					
			    				}else{
			    					var currentPage=item.currentPage;
									var totalPages=item.totalPages;
									
			    					if(item.id == null){
			    						$("#userdeviceslist").empty();
			    						//如果是page实体类  处理一
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
			    						j++;//用于显示数目
			    						if(role == "buyer"){
			    							$("#userDevicelist").append('<tr>'+
				      								'<td class="center" style="width: 30px;">'+
				        	    					'<label>'+
				        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
				        	    					'<span class="lbl">'+
				        	    					'</span>'+
				        	    					'</label>'+
				        	    					'</td>'+
				        	    					'<td class="center">'+(j)+'</td>'+
				        	    					'<td class="center">'+item.userPhone+'</td>'+
				        	    					'<td class="center">'+item.deviceCode+'</td>'+
				        	    					'<td class="center">'+item.nick_name+'</td>'+
				        	    					'<shiro:hasRole name="buyer">'+
				        	    					'<td style="width: 30px;" class="center">'+
				        	    						'<span onclick="del('+item.id+','+item.DEVICE_ID+','+item.USER_ID+');" style="color: blue;">解绑</span>'+
				        	    					'</td>'+
				        	    					'</shiro:hasRole>'+
				        	    					'</tr>');
		   	   							}else if(session == "admin"){
			   	   							$("#userDevicelist").append('<tr>'+
				      								'<td class="center" style="width: 30px;">'+
				        	    					'<label>'+
				        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
				        	    					'<span class="lbl">'+
				        	    					'</span>'+
				        	    					'</label>'+
				        	    					'</td>'+
				        	    					'<td class="center">'+(j)+'</td>'+
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
				        	    					'<td class="center">'+(j)+'</td>'+
				        	    					'<td class="center">'+item.userPhone+'</td>'+
				        	    					'<td class="center">'+item.deviceCode+'</td>'+
				        	    					'<td class="center">'+item.nick_name+'</td>'+
				        	    					'<td style="width: 30px;" class="center">'+
				        	    					'</td>'+
				        	    					'</tr>');
		   	   							}
			      						 
			    					}
			    				}
		        			}) 
						}
					})
					
				}
				
			}
		}
		
		//deviceCode
		function clickMe(a){
			var role= '<%= session.getAttribute("role")%>'; 
			
			$("#table_report").hide();//隐藏之前页面的字段
			$("#headdd").hide();
			$("#userdeviceslist").empty();
			$("#table_report1").show();//显示解绑页面相关的字段信息
			
			$("#savedeviceCode").val(a);
			$.ajax({
				url:"findUDsByDevicecode.do",
		    	data: {"deviceCode":a ,"index":1}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					var j=0;
    				$.each(data,function(i,item){//i是key,item是value
						if(data == ""){
	    					$("#userDevicelist").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');					
	    				}else{
	    					var currentPage=item.currentPage;
							var totalPages=item.totalPages;
	    					if(item.id == null){
	    						//如果是page实体类  处理一
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
	    						j++;//用于显示数目
	    						if(role == "admin"){
	    							$("#userDevicelist").append('<tr>'+
		      								'<td class="center" style="width: 30px;">'+
		        	    					'<label>'+
		        	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		        	    					'<span class="lbl">'+
		        	    					'</span>'+
		        	    					'</label>'+
		        	    					'</td>'+
		        	    					'<td class="center">'+(j)+'</td>'+
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
		        	    					'<td class="center">'+(j)+'</td>'+
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
		        	    					'<td class="center">'+(j)+'</td>'+
		        	    					'<td class="center">'+item.userPhone+'</td>'+
		        	    					'<td class="center">'+item.deviceCode+'</td>'+
		        	    					'<td class="center">'+item.nick_name+'</td>'+
		        	    					'<td style="width: 30px;" class="center">'+
		        	    					'</td>'+
		        	    					'</tr>');
	    						}
	    					}
	    				}
        			}) 
				}
			})
		}
		
		function buyerSearch(){
			var role= '<%= session.getAttribute("role")%>'; 
			var userPhone=$("#userPhone").val();
			/* alert("userPhone:"+userPhone); */
			$.ajax({
    			url: "buyerHomePage.do",
    	    	data: {"userPhone":userPhone },
    			type: "POST",
    			dataType:"json",
    			async: true,
    			success: function(data){
    				$("#tel").hide();
    				$("#headdd").show();
    				$("#table_report").show();
    				$("#userdeviceslist").show();
    				/* alert(data[0].DEVICE_CODE==null); */
    				$("#userdeviceslist0").empty();
    				if(data[0].DEVICE_CODE==null){
    					$("#userdeviceslist").empty();
    					$("#userdeviceslist0").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');	
    				}else{
        				$.each(data,function(i,item){//i是key,item是value
        					$("#userdeviceslist").empty();
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
        						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
        						var col="";
								var creator_date="";
								var updated_date="";
								var status="";
								var userName="";
								var userPhone="";
								var signature="";
								/* json对象 转成 String类型,再转成单引号 */
								var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
								/* alert(a);  */
	           				     /* alert(item.deviceCode); */
	   							 if(item.HOST_STATUS =="离线"){
	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')"  style="color: black;">'+item.DEVICE_CODE+'</a></td>';
	   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
	   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
	   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
	   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
	   							 }else{
	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
	   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
	   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
	   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
	   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
	   								 if(item.MNT_CREATOR_DATE !=null){
		           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
		           				     }else{
		           				    	creator_date='<td></td>';
		           				     }
		           				     if(item.MNT_UPDATED_DATE !=null){
		           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
		           				     }else{
		           				    	updated_date='<td></td>';
		           				     }
	   							 }
	           				     
	   							if(role == "admin"){
	   								$("#userdeviceslist0").append('<tr>'+
		         							'<td class="center" style="width: 30px;">'+
	   	    	    					'<label>'+
	   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	   	    	    					'<span class="lbl">'+
	   	    	    					'</span>'+
	   	    	    					'</label>'+
	   	    	    					'</td>'+
	   	    	    						userName+
	   	    	    						userPhone+
		       	    						col+
		       	    						creator_date+
		       	    						updated_date+
		       	    						status+
		       	    						signature+
		         							'<shiro:hasRole name="admin">'+
			       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
			       	    					'</shiro:hasRole>'+
		       	    					'</tr>'); 
	   							}else if(role == "buyer"){
	   								$("#userdeviceslist0").append('<tr>'+
		         							'<td class="center" style="width: 30px;">'+
	   	    	    					'<label>'+
	   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	   	    	    					'<span class="lbl">'+
	   	    	    					'</span>'+
	   	    	    					'</label>'+
	   	    	    					'</td>'+
	   	    	    						userName+
	   	    	    						userPhone+
		       	    						col+
		       	    						creator_date+
		       	    						updated_date+
		       	    						status+
		       	    						signature+
		         							'<shiro:hasRole name="buyer">'+
			       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
			       	    					'</shiro:hasRole>'+
		       	    					'</tr>'); 
	   							}else{
	   								$("#userdeviceslist0").append('<tr>'+
		         							'<td class="center" style="width: 30px;">'+
	   	    	    					'<label>'+
	   	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
	   	    	    					'<span class="lbl">'+
	   	    	    					'</span>'+
	   	    	    					'</label>'+
	   	    	    					'</td>'+
	   	    	    						userName+
	   	    	    						userPhone+
		       	    						col+
		       	    						creator_date+
		       	    						updated_date+
		       	    						status+
		       	    						signature+
			       	    					'<td class="center"></td>'+
		       	    					'</tr>'); 
	   							}
	         					
	        					}
    					})	
    				}
    			}
			})
		};
		
		
		//检索
		function search(){
			var role= '<%= session.getAttribute("role")%>'; 
			/* alert("进入方法"); */
			var NPC=$("#NPC").val();
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
			/* alert("NPC:"+NPC);
			alert("STATUS："+STATUS); */
			if(NPC !=""){
				/* alert("通过NPC+status方法查找"); */
				$.ajax({
	    			url: "findhomePageByNPC.do",
	    	    	data: {"npc":NPC,"status":STATUS,"index":1},
	    			type: "POST",
	    			dataType:"json",
	    			async: false,
	    			success: function(data){
	    				/* alert(data=="") */
	    				if(data==""){
	    					$("#userdeviceslist0").empty();
       						$("#userdeviceslist").empty();
       						$("#userdeviceslist0").append('<tr class="main_info">'+
       		    					'<td colspan="100" class="center" >没有相关数据</td>'+
       		    					'</tr>');
	    				}else{
	    					$("#userdeviceslist0").empty();
	    					$.each(data,function(i,item){//i是key,item是value
	           					if(item.DEVICE_CODE == null){
	           						var currentPage=item.currentPage;
	           						var totalPages=item.totalPages;
	           						
	           						$("#userdeviceslist").empty();
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
	           						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	           						var col="";
	   								var creator_date="";
	   								var updated_date="";
	   								var status="";
	   								var userName="";
	   								var userPhone="";
	   								var signature="";
	   								/* json对象 转成 String类型,再转成单引号 */
	   								var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
	   								/* alert(a);  */
	   	           				     /* alert(item.deviceCode); */
	   	   							 if(item.HOST_STATUS =="离线"){
	   	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: black;">'+item.DEVICE_CODE+'</a></td>';
	   	   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
	   	   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
	   	   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
	   	   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
	   	   							 }else{
	   	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
	   	   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
	   	   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
	   	   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
	   	   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
	   	   								 if(item.MNT_CREATOR_DATE !=null){
	   		           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
	   		           				     }else{
	   		           				    	creator_date='<td></td>';
	   		           				     }
	   		           				     if(item.MNT_UPDATED_DATE !=null){
	   		           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
	   		           				     }else{
	   		           				    	updated_date='<td></td>';
	   		           				     }
	   	   							 }
	   	           				     if(role == "admin"){
		   	           				   $("#userdeviceslist0").append('<tr>'+
		   	         							'<td class="center" style="width: 30px;">'+
		      	    	    						'<label>'+
		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		      	    	    							'<span class="lbl">'+
		      	    	    							'</span>'+
		      	    	    						'</label>'+
		      	    	    					'</td>'+
		      	    	    					userName+
		      	    	    					userPhone+
		   	       	    						col+
		   	       	    						creator_date+
		   	       	    						updated_date+
		   	       	    						status+
		   	       	    						signature+
		   	         							'<shiro:hasRole name="admin">'+
		   		       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		   		       	    					'</shiro:hasRole>'+
		   	       	    					'</tr>'); 
	   	           				     }else if(role == "buyer"){
		   	           				   $("#userdeviceslist0").append('<tr>'+
		   	         							'<td class="center" style="width: 30px;">'+
		      	    	    						'<label>'+
		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		      	    	    							'<span class="lbl">'+
		      	    	    							'</span>'+
		      	    	    						'</label>'+
		      	    	    					'</td>'+
		      	    	    					userName+
		      	    	    					userPhone+
		   	       	    						col+
		   	       	    						creator_date+
		   	       	    						updated_date+
		   	       	    						status+
		   	       	    						signature+
		   	         							'<shiro:hasRole name="buyer">'+
		   		       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
		   		       	    					'</shiro:hasRole>'+
		   	       	    					'</tr>'); 
	   	           				     }else{
		   	           				   $("#userdeviceslist0").append('<tr>'+
		   	         							'<td class="center" style="width: 30px;">'+
		      	    	    						'<label>'+
		      	    	    							'<input type="checkbox" name="ids" value="'+item.id+'" />'+
		      	    	    							'<span class="lbl">'+
		      	    	    							'</span>'+
		      	    	    						'</label>'+
		      	    	    					'</td>'+
		      	    	    					userName+
		      	    	    					userPhone+
		   	       	    						col+
		   	       	    						creator_date+
		   	       	    						updated_date+
		   	       	    						status+
		   	       	    						signature+
		   		       	    					'<td class="center"></td>'+
		   	       	    					'</tr>'); 
	   	           				     }
	   	         					
	   	        					}
	       					})
	    				}
	    			}
	    		});
			}else if(NPC == ""){
				/* alert("通过status方法查找"); */
				/* alert("STATUS:"+STATUS); */
				$.ajax({
	    			url: "findhomePageByStatus.do",
	    	    	data: {"status":STATUS,"index":1},
	    			type: "POST",
	    			dataType:"json",
	    			async: false,
	    			success: function(data){
	    				if(data == null){
	    					$("#userdeviceslist0").empty();
	    					$("#userdeviceslist").empty();
	    					$("#userdeviceslist0").append('<tr class="main_info">'+
	    					'<td colspan="100" class="center" >没有相关数据</td>'+
	    					'</tr>');	
	    					
	    				}else{
	    					$("#userdeviceslist0").empty();
            				$.each(data,function(i,item){//i是key,item是value
            					if(item.DEVICE_CODE == null){
            						var currentPage=item.currentPage;
            						var totalPages=item.totalPages;
            						
            						$("#userdeviceslist").empty();
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
            						var deviceCode=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
            						var col="";
    								var creator_date="";
    								var updated_date="";
    								var status="";
    								var userName="";
    								var userPhone="";
    								var signature="";
    								/* json对象 转成 String类型,再转成单引号 */
    								var a=JSON.stringify(item.DEVICE_CODE).replace(/\"/g,"'");
    								/* alert(a);  */
    	           				     /* alert(item.deviceCode); */
    	   							 if(item.HOST_STATUS =="离线"){
    	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: black;">'+item.DEVICE_CODE+'</a></td>';
    	   								 status='<td class="center"><span style="color: black;">'+item.HOST_STATUS+'</span></td>';
    	   								 userName='<td class="center"><span style="color: black;">'+item.USER_NAME+'</span></td>';
    	   								 userPhone='<td class="center"><span style="color: black;">'+item.USER_PHONE+'</span></td>';
    	   								 signature='<td class="center"><span style="color: black;">'+item.SIGNATURE+'</span></td>';
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
    	   							 }else{
    	   								 col='<td class="center"><a onclick="clickMe('+deviceCode+')" style="color: blue;">'+item.DEVICE_CODE+'</a></td>';
    	   								 status='<td class="center"><span style="color: blue;">'+item.HOST_STATUS+'</span></td>';
    	   								 userName='<td class="center"><span style="color: blue;">'+item.USER_NAME+'</span></td>';
    	   								 userPhone='<td class="center"><span style="color: blue;">'+item.USER_PHONE+'</span></td>';
    	   								 signature='<td class="center"><span style="color: blue;">'+item.SIGNATURE+'</span></td>';
    	   								 if(item.MNT_CREATOR_DATE !=null){
    		           				    	creator_date='<td class="center"><span style="color: blue;">'+item.MNT_CREATOR_DATE+'</span></td>';
    		           				     }else{
    		           				    	creator_date='<td></td>';
    		           				     }
    		           				     if(item.MNT_UPDATED_DATE !=null){
    		           				    	updated_date='<td class="center"><span style="color: blue;">'+item.MNT_UPDATED_DATE+'</span></td>';
    		           				     }else{
    		           				    	updated_date='<td></td>';
    		           				     }
    	   							 }
    	         					$("#userdeviceslist0").append('<tr>'+
    	         							'<td class="center" style="width: 30px;">'+
       	    	    					'<label>'+
       	    	    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
       	    	    					'<span class="lbl">'+
       	    	    					'</span>'+
       	    	    					'</label>'+
       	    	    					'</td>'+
       	    	    						userName+
       	    	    						userPhone+
    	       	    						col+
    	       	    						creator_date+
    	       	    						updated_date+
    	       	    						status+
    	       	    						signature+
    	         							'<shiro:hasRole name="admin">'+
    		       	    						'<td class="center"><a href="<%=WEBPATH3 %>/static/jsp/editBZ.jsp?signature='+item.SIGNATURE+'&id='+item.id+'" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></td>'+
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
	</body>
</html>

