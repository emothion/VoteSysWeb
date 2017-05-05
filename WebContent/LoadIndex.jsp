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
<script src="${pageContext.request.contextPath}/js/LoadIndex.js"></script>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>
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
			<a id="login" class="navbar-brand" data-toggle="modal" data-target="#LogonModal" href="#">登陆</a>
			<a id="logon" class="navbar-brand" data-toggle="modal" data-target="#LoginModal" href="#">注册</a>
			<a id="logout" href="javascript:logout('${pageContext.request.contextPath}')" class="navbar-brand">注销</a>
		</div>
	</div>
</nav>
<!-- 导航栏 结束 -->

<!-- 主体内容 开始 -->
<div class="container" style="width: 75%; margin-bottom: 100px">
	<div class="row">
		<div class="col-md-3 col-sm-4">
	  		<div class="panel panel-info">
	  			<div class="panel-heading">
	  				<strong>&nbsp;操&nbsp;作&nbsp;栏</strong>
	  			</div>
	  			<form  class="form-horizontal" action="${pageContext.request.contextPath}/index/getTopic.do" method="post">
		  			<div class="form-horizontal">
						<div class="panel-body">
							<div class="input-group input-group-sm">
								<span class="input-group-addon" id="sizing-addon3">主题</span>
								<input type="text" class="form-control" name="topicTitle" id="topicTitle" value="${topicSession.topicTitle }" placeholder="主题关键词" aria-describedby="sizing-addon3">
							</div>
							<div class="row form-group text-center">
								<div class="col-sm-6">
									<label class="checkbox-inline">
										<input type="checkbox" name="topicStatus" ${topicSession.topicStatus == "U" ? "checked='checked'":"" } value="U"> 进行中
									</label>
								</div>
								<div class="col-sm-6">
									<label class="checkbox-inline">
										<input type="checkbox" name="topicStatus" ${topicSession.topicStatus == "S" ? "checked='checked'":"" } value="S"> 已结束
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4"></div>
								<div class="col-sm-4">
									<button type="submit" class="btn btn-info btn-sm btn-block">过滤</button>
								</div>
								<div class="col-sm-4"></div>
							</div>
						</div>
					</div>
	  			</form>
			</div>
		</div>
		<div class="col-md-9 col-sm-8">
			<c:forEach var="topicDiv" items="${divs }">
				${topicDiv }
			</c:forEach>
  			<div id="xmlGetTopic"></div>
			<div id="page_tag_load" class="alert alert-success text-center hidden" role="alert"><img src="../image/sys/loading-sm.gif" alt="" style="margin-right: 10px"/>加载中...</div>
			<div id="page_tag_stop" class="panel panel-default text-center hidden"><div class="panel-body">没有更多了</div></div>
		</div>
	</div>
</div>
<!-- 主体内容 结束 -->

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
<jsp:include page="/CommoPage/LognOnAndIn.jsp"></jsp:include>

<jsp:include page="/CommoPage/AlertModel.jsp"></jsp:include>
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

var page=1;
var flag=true;

function xmlHttpGetTopic(page) {
	$.post("${pageContext.request.contextPath}/index/ajaxGetTopic.do", {
		page : page
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			insertcode(result);
			flag = true;
		} else if (result.Code == '01') {
			$("#page_tag_stop").removeClass("hidden");
			$("#page_tag_load").addClass("hidden");
		} else if (result.Code == '11') {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}
</script>
</body>
</html>
