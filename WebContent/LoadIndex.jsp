<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/scollLoad.js"></script>
<script src="${pageContext.request.contextPath}/js/LoadIndex.js"></script>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>
<script type="text/javascript">
$(function() {
  var i = ${userSession.userID!=null?true:false};
  if(i){
    $("#login").hide();
    $("#logon").hide();
    $("#logout").show();
  }else{
    $("#login").show();
    $("#logon").show();
    $("#logout").hide();
  }
});

var page=2;

function xmlHttpGetTopic(page) {
	$.post("${pageContext.request.contextPath}/index/ajaxGetTopic.do", {
		page : page
	}, function(result) {
		var result = eval('(' + result + ')');
		insertcode(result);
	});
}
</script>
</head>

<body style="background-color: #DCDCDC; height: 100%">
<!-- 导航栏 开始 -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a>
    </div>
    <div id="navbar" class="navbar-right">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/userOperate/toUserInfoPage.do">${userSession.userName }</a>
      <a id="login" class="navbar-brand" data-toggle="modal" data-target="#LogonModal" href="#">登陆</a>
      <a id="logon" class="navbar-brand" data-toggle="modal" data-target="#LoginModal" href="#">注册</a>
      <a id="logout" href="javascript:logout('${pageContext.request.contextPath}')" class="navbar-brand">注销</a>
    </div>
  </div>
</nav>
<!-- 导航栏 结束 -->

<!-- 主体内容 开始 -->
<div class="container" style="width:900px; margin-top: 5%">
  <c:forEach var="topicDiv" items="${divStr }">
    ${topicDiv }
  </c:forEach>
  <div id="page_tag_load" style=" display:none; font-size:14px;position:fixed; bottom:0px; background-color:#cccccc;height:50px;">load...</div>
  <div id="xmlGetTopic">
  </div>
</div>
<!-- 主体内容 结束 -->

<!-- 登陆弹出窗 开始 -->
<div class="modal fade" id="LogonModal" tabindex="-1" role="dialog" aria-labelledby="logonModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <form id="logonForm">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="logonModalLabel">
	                     请登陆
	        </h4>
	      </div>
	      <div class="modal-body">
			  <div class="form-group">
			    <label for="userName">用户名</label>
			    <input type="text" class="form-control" id="userName" name="userName" placeholder="Username">
			  </div>
			  <div class="form-group">
			    <label for="userKey">密码</label>
			    <input type="password" class="form-control" id="userKey" name="userKey" placeholder="Password">
			  </div>
			  <div class="alert alert-danger hidden" role="alert" id = "logonAlert">
	            <strong>错误!</strong>
	            <p>用户名或密码错误！</p>
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">稍后登入</button>
	        <button type="button" class="btn btn-primary" onclick="Logon('${pageContext.request.contextPath}')">现在登入</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<!-- 登陆弹出窗 结束 -->

<!-- 注册弹出窗 开始 -->
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <form id="loginForm">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="loginModalLabel">
	        	注册
	        </h4>
	      </div>
	      <div class="modal-body" id = register>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="text" class="form-control" id="userNameA" name="userNameA" placeholder="请输入昵称..." data-toggle="tooltip" data-placement="right">
			  </div>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="请输入邮箱...">
			  </div>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="请输入电话..." data-toggle="tooltip" data-placement="right">
			  </div>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="password" class="form-control" id="userKeyA" name="userKey" placeholder="请设置密码...">
			  </div>
			  <div class="form-group">
  				<input type="password" class="form-control" id="userKeyB" name="userKeyA" placeholder="重复密码...">
			  </div>
			  <div class="alert alert-danger hidden" role="alert" id = "loginAlert">
	            <strong>错误!</strong>
	            <p></p>
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="Login('${pageContext.request.contextPath}')">注册</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<!-- 注册弹出窗 结束 -->

<!-- 脚注 开始 -->
<div class="container">
  <div class="row">
    <div class="col-sm-12">
      <div class="navbar navbar-default navbar-fixed-bottom">
        <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/manager-model/login.jsp">管理界面</a>
          </div>
          <div class="col-xs-offset-4">
            <div id="navbar" class="navbar-center">
              <p style="padding-top: 15px; font-size: 0.8em">Copyright © 2016-2017 BonnenuIt 版权所有</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- 脚注 结束 -->

<jsp:include page="/CommoPage/AlertModel.jsp"></jsp:include>
</body>
</html>
