<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
  
<%
	String WEBPATH11 = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ WEBPATH11 + "/";
	String userPhone=request.getParameter("userPhone");
	String userName=request.getParameter("userName");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
		<%@ include file="/static/jsp/top.jsp"%> 
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1" name="viewport" />
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
		    
		    #flip,#flipModel,#flipFRD,#flipDevice
			{
				width:80%;
				padding:5px;
				text-align:left;
				background-color:#FFFFCC;
				border:solid 1px #FFFFFF;
			}
			#panel,#panelModel,#panelDevice,#panelFRD
			{
				/* padding:50px; */
				width:80%;
				padding:5px;
				text-align:center;
				background-color:#FFFFFF;
				border:solid 1px #FFFFFF;
				display:none;
			}
			#panelD
			{
				/* padding:50px; */
				width:100%;
				padding:5px;
				text-align:center;
				background-color:#FFFFFF;
				border:solid 1px #FFFFFF;
				display:none;
			}
			#mainShow
			{
				width:95%;
				padding:5px;
				margin-top:5px;
				text-align:center;
			}
			#table_userPhone_below,#table_device_below,#table_model_below,#table_hostdevice_below
			{
				display:none;
			}
			input[type=checkbox], input[type=radio] {
				margin-left:10px;
		   }
	  </style>
	</head>
<body>
<div class="container-fluid" id="main-container">
<!-- for jquery ui dialog -->
<div id="dialog-authAdd" title="授权用户">
  <p class="validateTips">请完善下面的字段信息,<font color="red">*</font>修饰的为必填项</p>
  <form action="" method="post">
	  <fieldset>
	    <label for="userPhone"><font color="red">*</font>手机号</label><input type="text" name="userPhone" id="userPhone0" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入手机号">
	    <p>请选择授权类型账号</p>
	    <div id="radio">
		    <input type="radio" name="role" value="admin" checked style="opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>管理员</font>
		    <input type="radio" name="role" value="normal"  style="opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;"><font>一般用户</font>
  		</div>
		<p>房间权限</p>
		 <div id="roomAuth">
		 </div>
		<p>设备权限</p>
		 <div id="hostDeviceAuth">
		 </div>
		<p>情景权限</p>
		 <div id="boModelAuth">
		 </div>
	  </fieldset>
  </form>
</div>
<div id="dialog-form" title="编辑被授权用户">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	    <label for="nickName">昵称</label><input type="text" name="nickName" id="nickName" class="text ui-widget-content ui-corner-all" placeholder="这里输入用户昵称">
	    <label for="userPhone">手机号</label><input type="text" name="userPhone" id="userPhone" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入手机号">
	    <label for="userSex">性别</label><input type="text" name="userSex" id="userSex" value="" class="text ui-widget-content ui-corner-all">
	    <label for="signature">签名</label><input type="text" name="signature" id="signature" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入签名">
	  </fieldset>
  </form>
</div>
<div id="dialog-edithostDevice" title="编辑设备">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	  	<input type="hidden"  id="hostDeviceId" value="">
	  	<input type="hidden"  id="floorCode" value="">
	    <label for="floorName">楼层</label><input type="text" name="floorName" id="floorName" class="text ui-widget-content ui-corner-all" placeholder="这里输入楼层名称">
	    <label for="roomName">房间</label><input type="text" name="roomName" id="roomName" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入房间名称">
	    <label for="nickName">设备</label><input type="text" name="nickName2" id="nickName2" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入设备名称">
	  </fieldset>
  </form>
</div>


<div id="dialog-add" title="添加主机">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	  	<input type="text" name="deviceCode1" id="deviceCode1" value="" class="text ui-widget-content ui-corner-all" placeholder="请输入主机序列号">
	  	<input type="text" name="nickName1" id="nickName1" value="" class="text ui-widget-content ui-corner-all" placeholder="请输入主机昵称">
	  </fieldset>
  </form>
</div>
<div id="dialog-editDevice" title="编辑主机">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	  	<input type="hidden" id="deviceId" value="">
	    <label for="deviceName">主机名称</label><input type="text" name="deviceName" id="deviceName" class="text ui-widget-content ui-corner-all" placeholder="这里输入主机名称">
	    <label for="deviceCode">主机序列号</label><input type="text" name="deviceCode" id="deviceCode" value="" class="text ui-widget-content ui-corner-all" placeholder="这里输入主机序列号">
	  </fieldset>
  </form>
</div>

<div id="dialog-addRoom" title="添加房间">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	    <label for="floorName">楼层名称</label><input type="text" name="floorName1" id="floorName1" class="text ui-widget-content ui-corner-all" placeholder="输入楼层名称">
	    <label for="roomName">房间名称</label><input type="text" name="roomName1" id="roomName1" value="" class="text ui-widget-content ui-corner-all" placeholder="输入要添加的房间名称">
	  </fieldset>
  </form>
</div>

<div id="dialog-addBoModel" title="添加房情景模式">
  <p class="validateTips"></p>
  <form action="" method="post">
	  <fieldset>
	    <label for="modelName">情景模式</label><input type="text" name="modelName" id="modelName" class="text ui-widget-content ui-corner-all" placeholder="输入模式名称">
	     <p>设 备</p>
		 <div id="hostDeviceList">
		 </div>
	  </fieldset>
  </form>
</div>

<!-- for body text -->
	<div id="mainShow">
		<p><font size="5" face="arial" color="#3399FF"><%=userName %>【<%=userPhone %>】</font></p>
	</div>
<!-- 显示用户下的次账户 -->
	<div id="flip" style="margin-top:2px;"><font size="4" face="arial">次账户</font></div><!-- style="float: left;" -->
	<div id="panel">
		<table id="table_userPhone" class="table table-striped table-bordered table-hover" >
			<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox0" /><span class="lbl"></span></label>
						</th>
						<!-- <th class="center">序号</th> -->
						<!-- <th class="center">用户表示</th> -->
						<th class="center">用户名</th>
						<th class="center">被授权账户</th>
						<th class="center">性别</th>
						<th class="center">签名</th>
						<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole>
						<%-- <shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> --%>
					</tr>
			</thead>
			<tbody id="userPhoneList">
				<!-- <td style="text-align:center;">136956985485</td> -->
			</tbody>
		</table>
	</div>
	<div id="table_userPhone_below" class="page-header position-relative">
			<span><button id="create-user" class="btn btn-small btn-success" onclick="addUser();">添加次账户</button></span>
			<span><a class="btn btn-small btn-danger" onclick="ubSelected();" title="批量解绑" ><i class="icon-trash"></i></a></span>
	</div>
<!-- 显示用户下的主机 -->
	<div id="flipDevice" style="margin-top:25px;"><font size="4" face="arial" >主机</font></div>
	<div id="panelDevice">
		<table id="table_device" class="table table-striped table-bordered table-hover" >
			<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox1" /><span class="lbl"></span></label>
						</th>
						<!-- <th class="center">序号</th> -->
						<!-- <th class="center">用户表示</th> -->
						<th class="center">主机昵称</th>
						<th class="center">主机序列号</th>
						<th class="center">状态</th>
						<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> 
					</tr>
			</thead>
			<tbody id="deviceList">
				<!-- <td style="text-align:center;">65dd568dfdwer6g56d56s5</td> -->
			</tbody>
		</table>
	</div>
	<div id="table_device_below" class="page-header position-relative">
			<span><button id="create-device" class="btn btn-small btn-success" onclick="addDevice();">新增</button></span>
			<span><a class="btn btn-small btn-danger"  onclick="unbindSelectedDevices();" title="批量删除" ><i class="icon-trash"></i></a></span>
	</div>
<!-- 显示用户下的情景模式 -->
	<div id="flipModel" style="margin-top:25px;"><font size="4" face="arial">情景模式</font></div>
	<div id="panelModel">
		<table id="table_model" class="table table-striped table-bordered table-hover" >
			<thead>
				<tr>
					<th class="center">
					<label><input type="checkbox" id="zcheckbox2" /><span class="lbl"></span></label>
					</th>
					<th class="center">情景模式</th>
					<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> 
				</tr>
			</thead>
			<tbody id="modelList">
				<!-- <td style="text-align:center;">65dd568dfdwer6g56d56s5</td> -->
			</tbody>
		</table>
	</div>
	<div id="table_model_below" class="page-header position-relative">
			<span><button id="create-modol" class="btn btn-small btn-success" onclick="addboModel();">新增</button></span>
			<span><a class="btn btn-small btn-danger"  onclick="delSelectedModols();" title="批量删除" ><i class="icon-trash"></i></a></span>
	</div>
<!-- 显示用户下房间、设备 -->
	<div id="flipFRD" style="margin-top:25px;"><font size="4" face="arial">设备</font></div>
	<div id="panelFRD">
		<table id="table_floor" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center">
					<label><input type="checkbox" id="zcheckbox3" /><span class="lbl"></span></label>
					</th>
					<th class="center">楼层</th>
					<th class="center">房间</th>
					<th class="center">设备</th>
					<shiro:hasRole name="admin"><th class="center">操作</th></shiro:hasRole> 
				</tr>
			</thead>
			<tbody id="hostDevicesList">
			</tbody>
		</table>
	</div>
	<div id="table_hostdevice_below" class="page-header position-relative">
		<span><button id="create-room" class="btn btn-small btn-success" onclick="addRoom();">新增房间</button></span>
		<span><button id="create-hostDevice" class="btn btn-small btn-danger" onclick="delSelectedDevices();">批量删除设备</button></span>
	</div>

<div id="page-content" class="clearfix">			
	<div class="row-fluid">
		<div class="page-header position-relative">
			<!-- <span><button id="create-user" class="btn btn-small btn-success">新增</button></span>
			<span><a class="btn btn-small btn-danger"  onclick="delSelected();" title="批量删除" ><i class="icon-trash"></i></a></span> -->
			<table style="width:100%;">
				<tr>
					<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;" id="userlist01">
							
						</div>
					</td>
				</tr>
			</table>
		</div>
		
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
		<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
		<link rel="stylesheet" href="<%=WEBPATH11 %>/static/css/jquery-ui-1.9.2.custom.css" />
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=WEBPATH11 %>/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=WEBPATH11 %>/static/js/jquery.cookie.js"></script>
		<script src="<%=WEBPATH11 %>/static/js/jquery-ui-1.9.2.custom.min.js"></script>
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
			$("#flip").click(function(){
			    $("#panel").slideToggle("slow");
			    $("#table_userPhone_below").slideToggle("slow");
			  });
			$("#flipModel").click(function(){
			    $("#panelModel").slideToggle("slow");
			    $("#table_model_below").slideToggle("slow");
			  });
			$("#flipFRD").click(function(){
			    $("#panelFRD").slideToggle("slow");
			    $("#table_hostdevice_below").slideToggle("slow");
			  });
		  $("#flipD").click(function(){
			    $("#panelD").slideToggle("slow");
			  });
		  $("#flipDevice").click(function(){
			    $("#panelDevice").slideToggle("slow");
			    $("#table_device_below").slideToggle("slow");
			  });
		});	
		$(function(){
			<%-- alert("<%=userPhone%>"); --%>
			var role= '<%= session.getAttribute("role")%>';
			//显示次账户
			$.ajax({
				url:"${pageContext.request.contextPath}/findUsersByPhone.do",
		    	data: {"userPhone":"<%=userPhone %>"},
				type: "POST",
				dataType:"json",
				async: false,	
				/* cache: false, */
				success: function(data){
					/* alert(data) */
					if(data[0].userId==null){
    					$("#userPhoneList").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');
					}else{
						$.each(data,function(i,item){
							if(item.userPhone != null){
								var nickName0=JSON.stringify(item.userName).replace(/\"/g,"'");
								var userPhone0=JSON.stringify(item.userPhone).replace(/\"/g,"'");
								var userSex0=JSON.stringify(item.userSex).replace(/\"/g,"'");
								var signature0=JSON.stringify(item.signature).replace(/\"/g,"'");
								if(role != "user"){
									$("#userPhoneList").append('<tr>'+
											'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.userPhone+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
											'<td class="center" style="width: 30px;">'+item.userName+'</td>'+
					    					'<td class="center" style="width: 30px;">'+item.userPhone+'</td>'+
											'<td class="center" style="width: 30px;">'+item.userSex+'</td>'+
					    					'<td class="center" style="width: 30px;">'+item.signature+'</td>'+
				    						'<td style="width: 30px;" class="center">'+
												'<div class="inline position-relative">'+
													'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
													'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
														'<li><a onclick="editUser('+nickName0+','+userPhone0+','+userSex0+','+signature0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
														'<li><a style="cursor:pointer;" title="解除授权" onclick="unbind('+userPhone0+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
													'</ul>'+
												'</div>'+
    										'</td>'+
					    					'</tr>'); 
								}
							}
						});
					}
				},
				error: function(e){  	 
				}
			});
			//显示主机
			$.ajax({
				url:"${pageContext.request.contextPath}/findHostDevicesByPhone.do",
		    	data: {"userPhone":"<%=userPhone %>"},
				type: "POST",
				dataType:"json",
				async: false,	
				/* cache: false, */
				success: function(data){
					/* alert(data) */
					if(data[0].deviceCode==null){
    					$("#deviceList").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');
					}else{
						$.each(data,function(i,item){
							if(item.deviceCode != null){
								var deviceName="",deviceCode="",hostStatus="";
								if(item.hostStatus == "在线"){
									deviceName='<td class="center" style="width: 30px;color:blue;">'+item.deviceName+'</td>';
									deviceCode='<td class="center" style="width: 30px;color:blue;">'+item.deviceCode+'</td>';
									hostStatus='<td class="center" style="width: 30px;color:blue;">'+item.hostStatus+'</td>';
								}else{
									deviceName='<td class="center" style="width: 30px;">'+item.deviceName+'</td>';
									deviceCode='<td class="center" style="width: 30px;">'+item.deviceCode+'</td>';
									hostStatus='<td class="center" style="width: 30px;">'+item.hostStatus+'</td>';
								}
								var deviceCode0=JSON.stringify(item.deviceCode).replace(/\"/g,"'");
								var nickName0=JSON.stringify(item.deviceName).replace(/\"/g,"'");
								if(role != "user"){
									$("#deviceList").append('<tr>'+
											'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.deviceCode+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					deviceName+
					    					deviceCode+
					    					hostStatus+
			    							'<td style="width: 30px;" class="center">'+
												'<div class="inline position-relative">'+
													'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
														'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
															'<li><a onclick="editUserDevice('+item.deviceId+','+deviceCode0+','+nickName0+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
															'<li><a style="cursor:pointer;" title="解绑主机" onclick="unbindDevice('+deviceCode0+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
														'</ul>'+
												'</div>'+
											'</td>'+
					    					'</tr>');
								}
							}
						});
					}
				},
				error: function(e){  	 
				}
			});
			//显示情景模式
			$.ajax({
				url:"${pageContext.request.contextPath}/findBoModelByPhone.do",
		    	data: {"userPhone":"<%=userPhone %>"},
				type: "POST",
				dataType:"json",
				async: false,	
				/* cache: false, */
				success: function(data){
					/* alert(data) */
					if(data[0].name==null){
    					$("#modelList").append('<tr class="main_info">'+
    					'<td colspan="100" class="center" >没有相关数据</td>'+
    					'</tr>');
					}else{
						$.each(data,function(i,item){
							if(item.name != null){
								if(role != "user"){
									$("#modelList").append('<tr>'+
											'<td class="center" style="width: 30px;">'+
					    					'<label>'+
					    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
					    					'<span class="lbl">'+
					    					'</span>'+
					    					'</label>'+
					    					'</td>'+
					    					'<td class="center" style="width: 30px;">'+item.name+'</td>'+
		    								'<td style="width: 30px;" class="center">'+
												'<div class="inline position-relative">'+
													'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
														'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
															'<li><a onclick="editboModel()" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
															'<li><a style="cursor:pointer;" title="删除" onclick="delModel('+item.id+');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>'+
														'</ul>'+
												'</div>'+
											'</td>'+
					    					'</tr>'); 
								}
							}
						});
					}
				},
				error: function(e){  	 
				}
			});
		});
		//显示设备
		$.ajax({
			url:"${pageContext.request.contextPath}/showRDbyPhone.do",
	    	data: {"userPhone":"<%=userPhone %>"},
			type: "POST",
			dataType:"json",
			async: false,	
			/* cache: false, */
			success: function(data){
				/* alert(data) */
				if(data[0].id==null){
					$("#hostDevicesList").append('<tr class="main_info">'+
					'<td colspan="100" class="center" >没有相关数据</td>'+
					'</tr>');
				}else{
					$.each(data,function(i,item){
						var floorName=JSON.stringify(item.floorName).replace(/\"/g,"'");
						var roomName=JSON.stringify(item.roomName).replace(/\"/g,"'");
						var nickName=JSON.stringify(item.nickName).replace(/\"/g,"'");
						var floorCode=JSON.stringify(item.floorCode).replace(/\"/g,"'");
						var roomCode=JSON.stringify(item.roomCode).replace(/\"/g,"'");
						/* alert("nickName:"+nickName); */
						if(item.nickName!=""){
							if(role != "user"){
								$("#hostDevicesList").append('<tr>'+
										'<td class="center" style="width: 30px;">'+
				    					'<label>'+
				    					'<input type="checkbox" name="ids" value="'+item.id+'" />'+
				    					'<span class="lbl">'+
				    					'</span>'+
				    					'</label>'+
				    					'</td>'+
				    					'<td class="center" style="width: 30px;">'+item.floorName+'</td>'+
				    					'<td class="center" style="width: 30px;">'+item.roomName+'</td>'+
				    					'<td class="center" style="width: 30px;">'+item.nickName+'</td>'+
		    							'<td style="width: 30px;" class="center">'+
											'<div class="inline position-relative">'+
												'<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>'+
													'<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">'+
														'<li><a onclick="editHD('+item.id+','+floorCode+','+floorName+','+roomName+','+nickName+')" style="cursor:pointer;" title="编辑"  class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>'+
														'<li><a onclick="delFloor('+floorCode+')" style="cursor:pointer;" title="删除楼层"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span></a></li>'+
														'<li><a onclick="delRoom('+roomCode+')" style="cursor:pointer;" title="删除房间"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span></a></li>'+
														'<li><a onclick="delHD('+item.id+')" style="cursor:pointer;" title="删除设备"  class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span></a></li>'+
													'</ul>'+
											'</div>'+
										'</td>'+
				    					'</tr>'); 
							}
						}
					});
				}
			},
			error: function(e){  	 
			}
		});
		  $(function() {
			    var name = $( "#nickName" ),
			      userPhone=$("#userPhone"),
			      userSex=$("#userSex"),
			      signature = $( "#signature" ),
			      deviceId=$( "#deviceId" ),
			      deviceName=$( "#deviceName" ),
			      deviceCode=$( "#deviceCode" ),
			      deviceCode1=$( "#deviceCode1" ),
			      nickName1=$( "#nickName1" ),
			      userPhone0=$("#userPhone0"),
			      floorName=$("#floorName"),
			      roomName=$("#roomName"),
			      nickName2=$( "#nickName2" ),
			      floorName1=$("#floorName1"),
			      roomName1=$("#roomName1"),
			      modelName=$("#modelName"),
			      allFields = $( [] ).add( name ).add(userPhone).add( userSex ).add(signature).add(deviceId).add(deviceName).add(deviceCode).add(deviceCode1).add(nickName1).add(userPhone0)
			      	.add(floorName).add(roomName).add(nickName2).add(floorName1).add(roomName1).add(modelName),
			      tips = $( ".validateTips" );
			 
			    function updateTips( t ) {
			      tips
			        .text( t )
			        .addClass( "ui-state-highlight" );
			      setTimeout(function() {
			        tips.removeClass( "ui-state-highlight", 1500 );
			      }, 500 );
			    }
			 
			     function checkLength( o, n, min, max ) {//对输入字段信息进行 长度判断
			      if ( o.val().length > max || o.val().length < min ) {
			        o.addClass( "ui-state-error" );
			        updateTips( "" + n + " 的长度必须在 " +
			          min + " 和 " + max + " 之间。" );
			        return false;
			      } else {
			        return true;
			      }
			    } 
			 
			    function checkRegexp( o, regexp, n ) {//对输入字段信息进行 正则判断
			      if ( !( regexp.test( o.val() ) ) ) {
			        o.addClass( "ui-state-error" );
			        updateTips( n );
			        return false;
			      } else {
			        return true;
			      }
			    } 
			    $( "#dialog-authAdd" ).dialog({
			      autoOpen: false,
			      height: 300,
			      width: 350,
			      modal: true,
			      buttons: {
			        "提交": function() {
			          var bValid = true;
			          allFields.removeClass( "ui-state-error" );
			          bValid = bValid && checkRegexp( userPhone0, /^[1][3,4,5,7,8][0-9]{9}$/, "手机号字段以1为开头；第二位可为3,4,5,7,8,中的任意一位；最后以0-9的9个整数结尾。" );
			 
			          if ( bValid ) {
			            $( "#users tbody" ).append( "<tr>" +
			              "<td>" + userPhone0.val() + "</td>" +
			            "</tr>" );
			            /* alert(name.val()); */
			            var rooms =[]; 
						$('input[name="roomAuth"]:checked').each(function(){ 
							rooms.push($(this).val()); 
						}); 
						/* alert(rooms.length==0 ?'你还没有选择任何内容！':rooms);   */
						var hostDevices =[]; 
						$('input[name="hostDeviceAuth"]:checked').each(function(){ 
							hostDevices.push($(this).val()); 
						}); 
						/* alert(hostDevices.length==0 ?'你还没有选择任何内容！':hostDevices);  */
						var boModels =[]; 
						$('input[name="boModelAuth"]:checked').each(function(){ 
							boModels.push($(this).val()); 
						}); 
						/* alert(boModels.length==0 ?'你还没有选择任何内容！':boModels);  */
						//alert(rooms); 
						//alert(typeof(rooms));//Object
					   var role = $("input[name='role']:checked").val();
					   /* alert(role); */
					   /* alert("role:"+role); */
				       $.ajax({
			            	url:"<%=WEBPATH11 %>/addsubUser.do",
					    	data: { "userPhone":userPhone0.val(),"role":role,"rooms":JSON.stringify(rooms),"hostDevices":JSON.stringify(hostDevices),"boModels":JSON.stringify(boModels)}, 
					    	type: "POST",
							dataType:"json",
							async: true,
							success: function(data){
								if(data == "success"){
									alert("操作成功");
									window.location.reload();
								}else{
									alert("操作失败，请重试！");
								}
							}
			            });
			            
			            $( this ).dialog( "close" );
			          }
			        },
			        Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			      },
			      close: function() {
			        allFields.val( "" ).removeClass( "ui-state-error" );
			      }
			    });
			    $( "#dialog-addBoModel" ).dialog({
				      autoOpen: false,
				      height: 300,
				      width: 350,
				      modal: true,
				      buttons: {
				        "添加": function() {
				          var bValid = true;
				          allFields.removeClass( "ui-state-error" );
				          bValid = bValid && checkLength( modelName, "modelName", 1, 6 );
				          bValid = bValid && checkRegexp( modelName, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "情景模式必须是1-6位。" );
				          if ( bValid ) {
				            $( "#users tbody" ).append( "<tr>" +
				              "<td>" + modelName.val() + "</td>" +
				            "</tr>" );
				            var hostDevices =[]; 
							$('input[name="bhd"]:checked').each(function(){ 
								hostDevices.push($(this).val()); 
							});
							/* alert(hostDevices); */
				            /* alert("deviceId:"+deviceId); */
				            $.ajax({
				            	url:"<%=WEBPATH11 %>/addBoModel.do",
						    	data: {"modelName":modelName.val(),"boHostDevices":JSON.stringify(hostDevices)}, 
						    	type: "POST",
								dataType:"json",
								async: true,
								success: function(data){
									if(data == "success"){
										/* console.log("操作成功"); */
										alert("添加成功");
										window.location.reload();
									}else{
										alert("名称已被占用，请另外选择！");
									}
								}
				            });
				            
				            $( this ).dialog( "close" );
				          }
				        },
				        Cancel: function() {
				          $( this ).dialog( "close" );
				        }
				      },
				      close: function() {
				        allFields.val( "" ).removeClass( "ui-state-error" );
				      }
				    });
			    $( "#dialog-form" ).dialog({
			      autoOpen: false,
			      height: 300,
			      width: 350,
			      modal: true,
			      buttons: {
			        "提交": function() {
			          var bValid = true;
			          allFields.removeClass( "ui-state-error" );
			 
			          bValid = bValid || checkLength( name, "nickName", 1, 16 );
			          bValid = bValid || checkLength( signature, "signature", 6, 80 );//保证这里，和下面的正则判断一起用 || 标识，该字段才能不填，不然为必填项
			 
			          bValid = bValid || checkRegexp( name, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "用户名必须是1-16位。" );// ||标识该字段可为空
			          bValid = bValid && checkRegexp( userPhone, /^[1][3,4,5,7,8][0-9]{9}$/, "手机号字段以1为开头；第二位可为3,4,5,7,8,中的任意一位；最后以0-9的9个整数结尾。" );
			          bValid = bValid || checkRegexp( userSex, /^['男'|'女']$/, "性别只允许： 男，女" );
			          bValid = bValid || checkRegexp( signature, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,255}$/, "签名在1-255位之间" );//保证这里，和上面的长度判断一起用 || 标识，该字段才能不填，不然为必填项
			 
			          if ( bValid ) {
			            $( "#users tbody" ).append( "<tr>" +
			              "<td>" + name.val() + "</td>" +
			              "<td>" + userPhone.val() + "</td>" +
			              "<td>" + userSex.val() + "</td>" +
			              "<td>" + signature.val() + "</td>" +
			            "</tr>" );
			            /* alert(name.val()); */
			            $.ajax({
			            	url:"<%=WEBPATH11 %>/editsubUser.do",
					    	data: {"nickName":name.val(), "userPhone":userPhone.val(),"userSex":userSex.val(),"signature":signature.val()}, 
					    	type: "POST",
							dataType:"json",
							async: true,
							success: function(data){
								if(data == "success"){
									alert("操作成功");
									window.location.reload();
								}else{
									alert("操作失败，请重试！");
								}
							}
			            });
			            
			            $( this ).dialog( "close" );
			          }
			        },
			        Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			      },
			      close: function() {
			        allFields.val( "" ).removeClass( "ui-state-error" );
			      }
			    });
			    $( "#dialog-add" ).dialog({
				      autoOpen: false,
				      height: 300,
				      width: 350,
				      modal: true,
				      buttons: {
				        "添加主机": function() {
				          /* alert(id); */
				          var bValid = true;
				          allFields.removeClass( "ui-state-error" );
				          bValid = bValid && checkLength( nickName1, "nickName1", 1, 16 );
				          bValid = bValid && checkRegexp( deviceCode1, /^([0-9a-zA-Z])+$/, "主机序列号字段只允许： a-z 0-9" );
				          bValid = bValid && checkRegexp( nickName1, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "用户名必须是1-16位。" );
				          if ( bValid ) {
				            $( "#users tbody" ).append( "<tr>" +
				              "<td>" + deviceCode1.val() + "</td>" +
				              "<td>" + nickName1.val() + "</td>" +
				            "</tr>" );
				            $.ajax({
				            	url:"<%=WEBPATH11 %>/addUserDevice.do",
						    	data: {"deviceCode":deviceCode1.val(),"nickName":nickName1.val(),"userPhone":"<%=userPhone %>"}, 
						    	type: "POST",
								dataType:"json",
								async: true,
								success: function(data){
									if(data == "success"){
										/* console.log("操作成功"); */
										alert("添加成功");
										window.location.reload();
									}else if(data == "No Such Device"){
										alert("该主机不存在！");
									}else if(data == "fail"){
										alert("添加失败，主机已被绑定！");
									}else{
										alert("添加出现异常！");
									}
								}
				            });
				            
				            $( this ).dialog( "close" );
				          }
				        },
				        Cancel: function() {
				          $( this ).dialog( "close" );
				        }
				      },
				      close: function() {
				        allFields.val( "" ).removeClass( "ui-state-error" );
				      }
				    });
			      $( "#dialog-editDevice" ).dialog({
				      autoOpen: false,
				      height: 300,
				      width: 350,
				      modal: true,
				      buttons: {
				        "编辑主机": function() {
				          var bValid = true;
				          allFields.removeClass( "ui-state-error" );
				          bValid = bValid && checkLength( deviceName, "deviceName", 1, 16 );
				          bValid = bValid && checkRegexp( deviceName, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "用户名必须是1-16位。" );
				          bValid = bValid && checkRegexp( deviceCode, /^[A-Za-z0-9]+$/, "主机序列号必须是数字，英文字母大小写。" );
				          if ( bValid ) {
				            $( "#users tbody" ).append( "<tr>" +
				              "<td>" + deviceName.val() + "</td>" +
				              "<td>" + deviceCode.val() + "</td>" +
				            "</tr>" );
				            var deviceId=$("#deviceId").val();
				            /* alert("deviceId:"+deviceId); */
				            $.ajax({
				            	url:"<%=WEBPATH11 %>/editUserDevice.do",
						    	data: {"deviceId":deviceId,"deviceCode":deviceCode.val(),"nickName":deviceName.val(),"userPhone":"<%=userPhone %>"}, 
						    	type: "POST",
								dataType:"json",
								async: true,
								success: function(data){
									if(data == "success"){
										/* console.log("操作成功"); */
										alert("编辑成功");
										window.location.reload();
									}else{
										alert("编辑失败，请重试！");
									}
								}
				            });
				            
				            $( this ).dialog( "close" );
				          }
				        },
				        Cancel: function() {
				          $( this ).dialog( "close" );
				        }
				      },
				      close: function() {
				        allFields.val( "" ).removeClass( "ui-state-error" );
				      }
				    });
			      
			      $( "#dialog-edithostDevice" ).dialog({
				      autoOpen: false,
				      height: 300,
				      width: 350,
				      modal: true,
				      buttons: {
				        "提交": function() {
				          var bValid = true;
				          allFields.removeClass( "ui-state-error" );
				          bValid = bValid && checkLength( floorName, "floorName", 1, 16 );
				          bValid = bValid && checkLength( roomName, "roomName", 1, 16 );
				          bValid = bValid && checkLength( nickName2, "nickName2", 1, 16 );
				 
				          bValid = bValid && checkRegexp( floorName, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "楼层名称必须是1-16位。" );// ||标识该字段可为空
				          bValid = bValid && checkRegexp( roomName, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "房间名称必须是1-16位。" );
				          bValid = bValid && checkRegexp( nickName2,  /^[A-Za-z0-9_\u4e00-\u9fa5]{1,16}$/, "设备名称必须是1-16位。");
				 
				          if ( bValid ) {
				            $( "#users tbody" ).append( "<tr>" +
				              "<td>" + floorName.val() + "</td>" +
				              "<td>" + roomName.val() + "</td>" +
				              "<td>" + nickName2.val() + "</td>" +
				            "</tr>" );
				            /* alert(name.val()); */
				            var id=$("#hostDeviceId").val();
				            var floorCode=$("#floorCode").val();
				            $.ajax({
				            	url:"<%=WEBPATH11 %>/edithostDevice.do",
						    	data: {"id":id,"floorCode":floorCode,"floorName":floorName.val(), "roomName":roomName.val(),"nickName":nickName2.val()}, 
						    	type: "POST",
								dataType:"json",
								async: true,
								success: function(data){
									if(data == "success"){
										alert("操作成功");
										window.location.reload();
									}else if(data == "RoomNotExsit"){
										alert("房间不存在，请新填写！");
									}else{
										alert("操作失败，请重试！");
									}
								}
				            });
				            
				            $( this ).dialog( "close" );
				          }
				        },
				        Cancel: function() {
				          $( this ).dialog( "close" );
				        }
				      },
				      close: function() {
				        allFields.val( "" ).removeClass( "ui-state-error" );
				      }
				    });
				    $( "#dialog-addRoom" ).dialog({
					      autoOpen: false,
					      height: 300,
					      width: 350,
					      modal: true,
					      buttons: {
					        "添加房间": function() {
					          var bValid = true;
					          allFields.removeClass( "ui-state-error" );
					          bValid = bValid && checkLength( floorName1, "floorName1", 1, 10 );
					          bValid = bValid && checkLength( roomName1, "roomName1", 1, 10 );
					 
					          bValid = bValid && checkRegexp( floorName1, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,10}$/, "楼层名称必须是1-10位。" );// ||标识该字段可为空
					          bValid = bValid && checkRegexp( roomName1, /^[A-Za-z0-9_\u4e00-\u9fa5]{1,10}$/, "房间名称必须是1-10位。" );
					 
					          if ( bValid ) {
					            $( "#users tbody" ).append( "<tr>" +
					              "<td>" + floorName1.val() + "</td>" +
					              "<td>" + roomName1.val() + "</td>" +
					            "</tr>" );
					            /* alert(name.val()); */
					            $.ajax({
					            	url:"<%=WEBPATH11 %>/addRoom.do",
							    	data: {"userPhone":"<%=userPhone%>","floorName":floorName1.val(),"roomName":roomName1.val()}, 
							    	type: "POST",
									dataType:"json",
									async: true,
									success: function(data){
										if(data == "success"){
											alert("操作成功");
											window.location.reload();
										}else if(data == "NotExsit"){
											alert("楼层名称不存在，请重写！");
											$("$floorName1").val("");
											$("$floorName1").focus();
										}else if(data == "Exsit"){
											alert("该房间已存在，请重写！");
											$("$roomName1").val("");
											$("$roomName1").focus();
										}else{
											alert("操作失败，请重试！");
										}
									}
					            });
					            
					            $( this ).dialog( "close" );
					          }
					        },
					        Cancel: function() {
					          $( this ).dialog( "close" );
					        }
					      },
					      close: function() {
					        allFields.val( "" ).removeClass( "ui-state-error" );
					      }
					    });
			  });
		  function addUser(){
			  //该用户下所有房间
			  $.ajax({
	            	url:"<%=WEBPATH11 %>/getRooms.do",
			    	data: {"userPhone":"<%=userPhone %>"}, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						if(data[0].roomId != null){
							$.each(data,function(i,item){
								$("#roomAuth").append('<input type="checkbox"  name="roomAuth" style="opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;" value="'+item.roomName+'">'+item.roomName);
							});
						}else{
							$("#roomAuth").append('<p>没有房间</p>');
						}
					}
	            });
			  //所有设备
			  $.ajax({
	            	url:"<%=WEBPATH11 %>/getHostDevices.do",
			    	data: {"userPhone":"<%=userPhone %>"}, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						if(data[0].nickName != ""){
							$.each(data,function(i,item){
								if(item.nickName != ""){
									$("#hostDeviceAuth").append(
											'<input type="checkbox"  name="hostDeviceAuth" style="opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;" value="'+item.nickName+'">'+item.nickName);
								}
							});
						}else{
							$("#hostDeviceAuth").append('<p>没有设备</p>');
						}
					}
	            });
			  //所有情景模式
			  $.ajax({
	            	url:"<%=WEBPATH11 %>/findBoModelByPhone.do",
			    	data: {"userPhone":"<%=userPhone %>"}, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						if(data[0].name != ""){
							$.each(data,function(i,item){
								if(item.name !=null){
									$("#boModelAuth").append(
											'<input type="checkbox"  name="boModelAuth" style="opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;" value="'+item.name+'">'+item.name);
								}
							});
						}else{
							$("#boModelAuth").append('<p>没有情景模式</p>');
						}
					}
	            });
			  $( "#dialog-authAdd" ).dialog( "open" );
		  };
		  function editUser(nickName,userPhone,userSex,signature){
			  /* alert("nickName:"+nickName+",userPhone:"+userPhone+",userSex:"+userSex+",signature:"+signature); */
			  $("#nickName").val(nickName);
			  $("#userPhone").val(userPhone);
			  $("#userSex").val(userSex);
			  $("#signature").val(signature);
			  $( "#dialog-form" ).dialog( "open" );
		  };
		  function unbind(userPhone){
			 alert("userPhone:"+userPhone); 
			  $.ajax({
	            	url:"<%=WEBPATH11 %>/unbind.do",
			    	data: {"userPhone":userPhone}, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						if(data == "success"){
							/* console.log("操作成功"); */
							alert("删除成功");
							window.location.reload();
						}else{
							alert("删除失败，请重试！");
						}
					}
	            });
		  };
		  function ubSelected(){
			  $('input:checkbox:checked').each(function (index, item) {
				  unbind($(this).val());//通过input标签value值来传递unbind方法需要的参数
				});
		  };
		  //编辑情景模式
		  function editboModel(userPhone0){
				$("#userPhone0").val(userPhone0);
			};
		//删除情景模式
		function delModel(id){
			 <%-- alert("userPhone:"+<%=userPhone %>);  --%>
			$.ajax({
            	url:"<%=WEBPATH11 %>/delModelBykey.do",
		    	data: {"id":id,"userPhone":"<%=userPhone %>"}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					if(data == "success"){
						/* console.log("操作成功"); */
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败，请重试！");
					}
				}
            });
		};
		//批量删除
		function delSelectedModols(){
			$('input:checkbox:checked').each(function (index, item) {
				delModel($(this).val());//通过input标签value值来传递delModel方法需要的参数
			});
		};
		//添加情景模式
		function addboModel(){
			//所有设备
			  $.ajax({
	            	url:"<%=WEBPATH11 %>/getHostDevices.do",
			    	data: {"userPhone":"<%=userPhone %>"}, 
			    	type: "POST",
					dataType:"json",
					async: true,
					success: function(data){
						if(data[0].nickName != ""){
							$.each(data,function(i,item){
								if(item.nickName != ""){
									$("#hostDeviceList").append(
											'<input type="checkbox"  name="bhd" style="opacity:1;position:relative;top:-4px;right:2px;display: inline-block;width:15px;" value="'+item.nickName+'">'+item.nickName);
								}
							});
						}else{
							$("#hostDeviceAuth").append('<p>没有设备</p>');
						}
					}
	            });
			$( "#dialog-addBoModel" ).dialog( "open" );
		};
		
		//添加主机
		function addDevice(){
			$( "#dialog-add" ).dialog( "open" );
		};
		//编辑主机
		function editUserDevice(deviceId,deviceCode,nickName){
			$("#deviceName").val(nickName);
			$("#deviceCode").val(deviceCode);
			$("#deviceId").val(deviceId);
			$( "#dialog-editDevice" ).dialog( "open" );
		};
		//解除主机绑定
		function unbindDevice(deviceCode){
			/* alert("deviceCode:"+deviceCode); */
			$.ajax({
            	url:"<%=WEBPATH11 %>/unbindDevice.do",
		    	data: {"deviceCode":deviceCode,"userPhone":"<%=userPhone %>"}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					if(data == "success"){
						/* console.log("操作成功"); */
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败，请重试！");
					}
				}
            });
		};
		//批量解除主机绑定
		function unbindSelectedDevices(){
			$('input:checkbox:checked').each(function (index, item) {
				unbindDevice($(this).val());//通过input标签value值来传递unbindDevice方法需要的参数
			});
		};
		//编辑设备
		function editHD(id,floorCode,floorName,roomName,nickName){ 
			/* alert("编辑设备id:"+id+",floorName:"+floorName+",roomName:"+roomName+",nickName:"+nickName); */
			$("#hostDeviceId").val(id);
			$("#floorCode").val(floorCode);
			$("#floorName").val(floorName);
			$("#roomName").val(roomName);
			$("#nickName2").val(nickName);
			$( "#dialog-edithostDevice" ).dialog( "open" );
		};
		//添加房间
		function addRoom(){
			$( "#dialog-addRoom" ).dialog( "open" );
		};
		//删除楼层
		function delFloor(floorCode){
			/* alert("删除楼层floorCode:"+floorCode); */
			$.ajax({
            	url:"<%=WEBPATH11 %>/delFloor.do",
		    	data: {"floorCode":floorCode}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					if(data == "success"){
						/* console.log("操作成功"); */
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败，请重试！");
					}
				}
            });
		};
		//删除房间
		function delRoom(roomCode){
			/* alert("删除房间roomCode:"+roomCode); */
			$.ajax({
            	url:"<%=WEBPATH11 %>/delRoom.do",
		    	data: {"roomCode":roomCode}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					if(data == "success"){
						/* console.log("操作成功"); */
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败，请重试！");
					}
				}
            });
		};
		//删除设备
		function delHD(id){
			/* alert("删除设备id:"+id); */
			$.ajax({
            	url:"<%=WEBPATH11 %>/delDevices.do",
		    	data: {"id":id}, 
		    	type: "POST",
				dataType:"json",
				async: true,
				success: function(data){
					if(data == "success"){
						/* console.log("操作成功"); */
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败，请重试！");
					}
				}
            });
		};
		//批量删除设备
		function delSelectedDevices(){
			$('input:checkbox:checked').each(function (index, item) {
				delHD($(this).val());//通过input标签value值来传递unbindDevice方法需要的参数
			});
		};
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
			window.location.href='<%=basePath%>users/excel.do';
		};
		
		
		</script>
		
	</body>
</html>

