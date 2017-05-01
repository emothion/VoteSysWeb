<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" style="background-color: #DCDCDC; height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详细页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>
<script src="${pageContext.request.contextPath}/user-model/js/distpicker.data.js"></script>
<script src="${pageContext.request.contextPath}/user-model/js/UserInfoPage.js"></script>	
<script src="${pageContext.request.contextPath}/user-model/js/distpicker.js"></script>
<script src="${pageContext.request.contextPath}/user-model/js/fileinput.js"></script>
<script src="${pageContext.request.contextPath}/user-model/js/fileinput_locale_zh.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/user-model/js/main.js"></script>
<script src="${pageContext.request.contextPath}/user-model/js/jquery.jedate.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/user-model/css/jedate.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/user-model/css/fileinput.css" />
<%
	if (session.getAttribute("userSession") == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
	}
%>
</head>
<body style="background-color: #DCDCDC; height: 100%">
<!-- 导航栏 开始 -->
<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a>
		</div>
		<div id="navbar" class="navbar-right">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/userOperate/toUserInfoPage.do">${userSession.userName }</a>
			<a id="logout" href="javascript:logout('${pageContext.request.contextPath}')" class="navbar-brand">注销</a>
		</div>
	</div>
</nav>
<!-- 导航栏 结束 -->

<!-- 主体内容 开始 -->
<div class="container" style="width: 75%; margin-bottom: 55px;">
	<div class="row">
		<div class="col-md-3 col-sm-3">
	  		<div class="panel panel-info">
	  			<div class="panel-heading">
	  				<h4>
	  					<strong>&nbsp;列&nbsp;表</strong>
	  				</h4>
	  			</div>
				<div class="panel-body">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/userOperate/openUserInfoListPage.do" class="list-group-item" id="userInfoListPage">
							详细信息<span class="badge"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
						</a>
						<a href="${pageContext.request.contextPath}/userOperate/openUserInfoFormPage.do" class="list-group-item" id="userInfoFormPage">
							编辑信息<span class="badge"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></span>
						</a>
						<a href="${pageContext.request.contextPath}/userOperate/openPublishTopicPage.do" class="list-group-item" id="publishTopicPage">
							主题发布<span class="badge"><span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span></span>
						</a>
						<a href="${pageContext.request.contextPath}/userOperate/openTopicListPage.do" class="list-group-item" id="topicListPage">
							主题查看<span class="badge"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span></span>
						</a>
						<a href="${pageContext.request.contextPath}/userOperate/openResetKeyPage.do" class="list-group-item" id="resetKeyPage">
							密码重置<span class="badge"><span class="glyphicon glyphicon-retweet" aria-hidden="true"></span></span>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-9 col-sm-9">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row" style="margin-bottom: 15px; padding-left: 15px; padding-right: 15px;">
						<div class="col-md-8 text-center" style="padding-top: 30px"><h1>${userSession.userName}</h1></div>
						<div class="col-md-4 text-right"><img src="../image/user/${userAllInfo.userImg }" alt="Responsive image" class="img-circle" style="width: 140px"></div>
					</div>
					<hr />
					<div class="hidden" style="margin-left: 20px; margin-right: 20px;" id="pathNav">
						<ol class="breadcrumb">
						  <li id="li1" class="active">创建主题</li>
						  <li id="li2">上传图片</li>
						  <li id="li3">定义投票项目</li>
						  <li id="li4">投票设置</li>
						</ol>
					</div>
					<div style="border: 1px solid #DCDCDC; border-radius:5px; padding-top: 20px; margin: 20px;">
						<jsp:include page="${userInfoPageUnitPath }"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 主体内容 结束 -->

<!-- 脚注 开始 -->
<div class="container">
	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<div class="navbar-header">
			</div>
			<div class="col-xs-offset-4">
				<div id="navbar" class="navbar-center">
					<p style="padding-top: 15px; font-size: 0.8em">Copyright © 2016-2017 BonnenuIt 版权所有</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 脚注 结束 -->
<jsp:include page="/CommoPage/AlertModel.jsp"></jsp:include>
</body>

<script type="text/javascript">
var flag = ${flag };
var url = "${pageContext.request.contextPath }";
var sex = "${userAllInfo.userSexy }";
var time = "";
</script>

</html>