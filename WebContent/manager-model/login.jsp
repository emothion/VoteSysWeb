<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" style="background-color: #DCDCDC; height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备管理系统-用户登录</title>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function checkForm(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		if(userName==null || userName==""){
			document.getElementById("login_err").innerHTML="用户名不能为空";
			return false;
		}
		if(password==null || password==""){
			document.getElementById("login_err").innerHTML="密码不能为空";
			return false;
		}
		return true;
	}
</script>
</head>
<body>

<div class="container">
	<div class="row">
			<!-- 栅格次级容器 -->
		<div class="col-md-12">
				<!-- 标题导航栏 -->
			<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation">
					<!-- 标题容器 -->
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand " href="${pageContext.request.contextPath}/index.jsp">新闻事实评论后台数据库管理</a>
					</div>
					<div id="navbar" class="navbar-right">
						<a class="navbar-brand" href="#">注册</a>
					</div>
				</div>
			</nav>
		</div>
	</div>
</div>

<div id="login_center">
	<div id="login_area">
		<div id="login_box">
			<div id="login_form">
				<form id="submitForm"  action="${pageContext.request.contextPath}/manager/login.do"  method="post" onsubmit="return checkForm()">
					<div id="login_tip">
						<span id="login_err" class="sty_txt2">${errorMsg }</span>
					</div>
					<div>
						 用户名： <input type="text"  id="userName" name="managerName" class="username"  value="${manager.managerName }" >
					</div>
					<div>
						密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password"  id="password"  name="password" class="pwd"  value="${manager.password }">
					</div>
					<div id="btn_area">
						<input type="submit" class="login_btn" id="login_sub"  value="登  录">
						<input type="reset" class="login_btn" id="login_ret" value="重 置">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<div class="navbar navbar-default navbar-fixed-bottom">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="${pageContext.request.contextPath}/login.jsp">管理界面</a>
					</div>
					<div class="col-xs-offset-4">
						<div id="navbar" class="navbar-center">
							<p style="padding-top: 15px; font-size: 0.8em">Copyright © 2013-2015 emothion &amp;BonnenuIt &amp; 细水流年 版权所有</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>