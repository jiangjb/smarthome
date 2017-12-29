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
			if($("#USER_CODE").val()==""){
			$("#USER_CODE").tips({
				side:3,
	            msg:'请输入USER_CODE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_CODE").focus();
			return false;
		}
		if($("#USER_NAME").val()==""){
			$("#USER_NAME").tips({
				side:3,
	            msg:'请输入USER_NAME',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_NAME").focus();
			return false;
		}
		if($("#USER_SEX").val()==""){
			$("#USER_SEX").tips({
				side:3,
	            msg:'请输入USER_SEX',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_SEX").focus();
			return false;
		}
		if($("#USER_PHONE").val()==""){
			$("#USER_PHONE").tips({
				side:3,
	            msg:'请输入USER_PHONE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_PHONE").focus();
			return false;
		}
		if($("#PHONE_TYPE").val()==""){
			$("#PHONE_TYPE").tips({
				side:3,
	            msg:'请输入PHONE_TYPE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PHONE_TYPE").focus();
			return false;
		}
		if($("#VERSION_TYPE").val()==""){
			$("#VERSION_TYPE").tips({
				side:3,
	            msg:'请输入VERSION_TYPE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#VERSION_TYPE").focus();
			return false;
		}
		if($("#SIGNATURE").val()==""){
			$("#SIGNATURE").tips({
				side:3,
	            msg:'请输入SIGNATURE',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SIGNATURE").focus();
			return false;
		}
		if($("#CITY").val()==""){
			$("#CITY").tips({
				side:3,
	            msg:'请输入CITY',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CITY").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="users/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="USERS_ID" id="USERS_ID" value="${pd.USERS_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">USER_CODE:</td>
				<td><input type="text" name="USER_CODE" id="USER_CODE" value="${pd.USER_CODE}" maxlength="32" placeholder="这里输入USER_CODE" title="USER_CODE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">USER_NAME:</td>
				<td><input type="text" name="USER_NAME" id="USER_NAME" value="${pd.USER_NAME}" maxlength="32" placeholder="这里输入USER_NAME" title="USER_NAME"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">USER_SEX:</td>
				<td><input type="text" name="USER_SEX" id="USER_SEX" value="${pd.USER_SEX}" maxlength="32" placeholder="这里输入USER_SEX" title="USER_SEX"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">USER_PHONE:</td>
				<td><input type="text" name="USER_PHONE" id="USER_PHONE" value="${pd.USER_PHONE}" maxlength="32" placeholder="这里输入USER_PHONE" title="USER_PHONE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">PHONE_TYPE:</td>
				<td><input type="number" name="PHONE_TYPE" id="PHONE_TYPE" value="${pd.PHONE_TYPE}" maxlength="32" placeholder="这里输入PHONE_TYPE" title="PHONE_TYPE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">VERSION_TYPE:</td>
				<td><input type="text" name="VERSION_TYPE" id="VERSION_TYPE" value="${pd.VERSION_TYPE}" maxlength="32" placeholder="这里输入VERSION_TYPE" title="VERSION_TYPE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">SIGNATURE:</td>
				<td><input type="text" name="SIGNATURE" id="SIGNATURE" value="${pd.SIGNATURE}" maxlength="32" placeholder="这里输入SIGNATURE" title="SIGNATURE"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">CITY:</td>
				<td><input type="text" name="CITY" id="CITY" value="${pd.CITY}" maxlength="32" placeholder="这里输入CITY" title="CITY"/></td>
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