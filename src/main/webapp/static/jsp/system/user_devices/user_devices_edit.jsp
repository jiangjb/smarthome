<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#USER_ID").val()==""){
			$("#USER_ID").tips({
				side:3,
	            msg:'请输入USER_ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_ID").focus();
			return false;
		}
		if($("#DEVICE_ID").val()==""){
			$("#DEVICE_ID").tips({
				side:3,
	            msg:'请输入DEVICE_ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEVICE_ID").focus();
			return false;
		}
		if($("#NICK_NAME").val()==""){
			$("#NICK_NAME").tips({
				side:3,
	            msg:'请输入NICK_NAME',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NICK_NAME").focus();
			return false;
		}
		if($("#SCAN_IDENTITY_COLUMN").val()==""){
			$("#SCAN_IDENTITY_COLUMN").tips({
				side:3,
	            msg:'请输入SCAN_IDENTITY_COLUMN',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SCAN_IDENTITY_COLUMN").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="user_devices/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="USER_DEVICES_ID" id="USER_DEVICES_ID" value="${pd.USER_DEVICES_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">USER_ID:</td>
				<td><input type="number" name="USER_ID" id="USER_ID" value="${pd.USER_ID}" maxlength="32" placeholder="这里输入USER_ID" title="USER_ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">DEVICE_ID:</td>
				<td><input type="number" name="DEVICE_ID" id="DEVICE_ID" value="${pd.DEVICE_ID}" maxlength="32" placeholder="这里输入DEVICE_ID" title="DEVICE_ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">NICK_NAME:</td>
				<td><input type="text" name="NICK_NAME" id="NICK_NAME" value="${pd.NICK_NAME}" maxlength="32" placeholder="这里输入NICK_NAME" title="NICK_NAME"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">SCAN_IDENTITY_COLUMN:</td>
				<td><input type="number" name="SCAN_IDENTITY_COLUMN" id="SCAN_IDENTITY_COLUMN" value="${pd.SCAN_IDENTITY_COLUMN}" maxlength="32" placeholder="这里输入SCAN_IDENTITY_COLUMN" title="SCAN_IDENTITY_COLUMN"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>