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
		if($("#DEVICE_TYPE").val()==""){
			$("#DEVICE_TYPE").tips({
				side:3,
	            msg:'请输入DEVICE_TYPE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEVICE_TYPE").focus();
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
		if($("#DEVICE_ADDRESS").val()==""){
			$("#DEVICE_ADDRESS").tips({
				side:3,
	            msg:'请输入DEVICE_ADDRESS',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEVICE_ADDRESS").focus();
			return false;
		}
		if($("#DEVICE_NUM").val()==""){
			$("#DEVICE_NUM").tips({
				side:3,
	            msg:'请输入DEVICE_NUM',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DEVICE_NUM").focus();
			return false;
		}
		if($("#VALIDATION_CODE").val()==""){
			$("#VALIDATION_CODE").tips({
				side:3,
	            msg:'请输入VALIDATION_CODE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#VALIDATION_CODE").focus();
			return false;
		}
		if($("#ICO").val()==""){
			$("#ICO").tips({
				side:3,
	            msg:'请输入ICO',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ICO").focus();
			return false;
		}
		if($("#ROOM_ID").val()==""){
			$("#ROOM_ID").tips({
				side:3,
	            msg:'请输入ROOM_ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ROOM_ID").focus();
			return false;
		}
		if($("#MNT_DELETE").val()==""){
			$("#MNT_DELETE").tips({
				side:3,
	            msg:'请输入MNT_DELETE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MNT_DELETE").focus();
			return false;
		}
		if($("#INFRARED_VERIFICATION").val()==""){
			$("#INFRARED_VERIFICATION").tips({
				side:3,
	            msg:'请输入INFRARED_VERIFICATION',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INFRARED_VERIFICATION").focus();
			return false;
		}
		if($("#WHETHER_QUERY_STATE_SIGN").val()==""){
			$("#WHETHER_QUERY_STATE_SIGN").tips({
				side:3,
	            msg:'请输入WHETHER_QUERY_STATE_SIGN',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WHETHER_QUERY_STATE_SIGN").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="host_device/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="HOST_DEVICE_ID" id="HOST_DEVICE_ID" value="${pd.HOST_DEVICE_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">DEVICE_ID:</td>
				<td><input type="number" name="DEVICE_ID" id="DEVICE_ID" value="${pd.DEVICE_ID}" maxlength="32" placeholder="这里输入DEVICE_ID" title="DEVICE_ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">USER_ID:</td>
				<td><input type="number" name="USER_ID" id="USER_ID" value="${pd.USER_ID}" maxlength="32" placeholder="这里输入USER_ID" title="USER_ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">DEVICE_TYPE:</td>
				<td><input type="text" name="DEVICE_TYPE" id="DEVICE_TYPE" value="${pd.DEVICE_TYPE}" maxlength="32" placeholder="这里输入DEVICE_TYPE" title="DEVICE_TYPE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">NICK_NAME:</td>
				<td><input type="text" name="NICK_NAME" id="NICK_NAME" value="${pd.NICK_NAME}" maxlength="32" placeholder="这里输入NICK_NAME" title="NICK_NAME"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">DEVICE_ADDRESS:</td>
				<td><input type="text" name="DEVICE_ADDRESS" id="DEVICE_ADDRESS" value="${pd.DEVICE_ADDRESS}" maxlength="32" placeholder="这里输入DEVICE_ADDRESS" title="DEVICE_ADDRESS"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">DEVICE_NUM:</td>
				<td><input type="number" name="DEVICE_NUM" id="DEVICE_NUM" value="${pd.DEVICE_NUM}" maxlength="32" placeholder="这里输入DEVICE_NUM" title="DEVICE_NUM"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">VALIDATION_CODE:</td>
				<td><input type="text" name="VALIDATION_CODE" id="VALIDATION_CODE" value="${pd.VALIDATION_CODE}" maxlength="32" placeholder="这里输入VALIDATION_CODE" title="VALIDATION_CODE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">ICO:</td>
				<td><input type="text" name="ICO" id="ICO" value="${pd.ICO}" maxlength="32" placeholder="这里输入ICO" title="ICO"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">ROOM_ID:</td>
				<td><input type="number" name="ROOM_ID" id="ROOM_ID" value="${pd.ROOM_ID}" maxlength="32" placeholder="这里输入ROOM_ID" title="ROOM_ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">MNT_DELETE:</td>
				<td><input type="text" name="MNT_DELETE" id="MNT_DELETE" value="${pd.MNT_DELETE}" maxlength="32" placeholder="这里输入MNT_DELETE" title="MNT_DELETE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">INFRARED_VERIFICATION:</td>
				<td><input type="text" name="INFRARED_VERIFICATION" id="INFRARED_VERIFICATION" value="${pd.INFRARED_VERIFICATION}" maxlength="32" placeholder="这里输入INFRARED_VERIFICATION" title="INFRARED_VERIFICATION"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">WHETHER_QUERY_STATE_SIGN:</td>
				<td><input type="text" name="WHETHER_QUERY_STATE_SIGN" id="WHETHER_QUERY_STATE_SIGN" value="${pd.WHETHER_QUERY_STATE_SIGN}" maxlength="32" placeholder="这里输入WHETHER_QUERY_STATE_SIGN" title="WHETHER_QUERY_STATE_SIGN"/></td>
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