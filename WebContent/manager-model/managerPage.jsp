<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/manager-model/js/managerPage.js"></script>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>
<%
	/* if (session.getAttribute("manager") == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
	} */
%>
</head>
<body>
<!-- 标题栏 开始 -->
<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
     		 <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a>
		</div>
		<div id="navbar" class="navbar-right">
			<a class="navbar-brand" href="javascript:void(0)">『管理員』：${manager.userName }</a>
			<a id="logout" href="javascript:logout('${pageContext.request.contextPath}')" class="navbar-brand">注销</a>
		</div>
	</div>
</nav>
<!-- 标题栏 结束 -->

<!-- 内容 开始 -->
<div class="container" style="width: 75%; margin-bottom: 100px">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div role="navigation" style="margin-bottom: -11px">
						<ul class="nav nav-tabs nav-justified">
							<li role="presentation" id="li1" class='${selected=="U" ? "active" : "" }'><a
								href="${pageContext.request.contextPath}/manager/initManagerPage.do">会员管理</a></li>
							<li role="presentation" id="li2" class='${selected=="T" ? "active" : "" }'><a
								href="${pageContext.request.contextPath}/manager/topicPage.do">话题管理</a></li>
							<li role="presentation" id="li3" class='${selected=="R" ? "active" : "" }'><a
								href="${pageContext.request.contextPath}/manager/comGetNextPage.do?page=1">评论管理</a></li>
						</ul>
					</div>
				</div>
				<div class="panel-body">
					<jsp:include page="${currentPage }"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 内容 结束 -->

<!-- 脚注 开始 -->
<div class="container">
  <div class="row">
    <div class="col-sm-12">
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
  </div>
</div>
<!-- 脚注 结束 -->

<jsp:include page="/CommoPage/AlertModel.jsp"></jsp:include>
<script type="text/javascript">
var url = "/VoteSysWeb";
</script>
</body>
</html>