<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${topicInfo.topicTitle }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>
<script src="${pageContext.request.contextPath}/topic-model/js/TopicDetail.js"></script>
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
			<a id="login" class="navbar-brand" data-toggle="modal" data-target="#LogonModal" href="#">登录</a>
			<a id="logon" class="navbar-brand" data-toggle="modal" data-target="#LoginModal" href="#">注册</a>
			<a id="logout" href="javascript:logout('${pageContext.request.contextPath}')" class="navbar-brand">注销</a>
		</div>
	</div>
</nav>
<!-- 导航栏 结束 -->

<!-- 主体内容 开始 -->
<div class="container" style="width: 75%; margin-bottom: 100px">
	<div class="row">
		<div class="col-md-7 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="page-header">
						<h1>${topic.topicTitle }
							<small>${author.userName }</small> 
							${stopBtn }
						</h1>
					</div>
					<p style="text-indent:2em; padding: 5px">${topic.topicContent }</p>
					<br />
					<c:forEach var="image" items="${Images }">
						<div class="thumbnail">
							<img src="../image/topic/${image.topicImg } " alt="...">
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-md-5 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-body">
					<jsp:include page="/topic-model/Vote.jsp"></jsp:include>
				</div>
			</div>
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title text-center">
							<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-expanded="true" aria-controls="collapseOne">
								回复
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<textarea class="form-control" rows="4" id="comContent" style="resize: none;"></textarea>
							<hr style="margin: 10px 0px 10px 0px;"/>
							<p style="width: 40%; margin: 0px auto 0px auto;"><button type="button" class="btn btn-primary btn-xs btn-block" onclick="submitComment()">提交</button></p>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<c:forEach var="comment" items="${comments }">
						${comment }
					</c:forEach>
					<div id="nextComment"></div>
				</div>
			</div>
			${comNextPage }
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
var url = "${pageContext.request.contextPath}"; 
var page = 2;

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

/**
 * 检查是否登录
 * @param userID
 * @returns
 */
function checkSession() {
	var flag = ${userSession.userID!=null?true:false};
	if (flag) {
		$("#voteForm").submit();
	} else {
		$("#errMsg").text("请先登录后再投票");
		$('#alertModel').modal('show');
		return false;
	}
}
</script>
</body>
</html>