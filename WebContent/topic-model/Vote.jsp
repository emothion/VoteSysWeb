<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-progressbar-3.2.0.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-progressbar-demo.css">
<div class="thumbnail">
	<img src="../image/sys/Vote_Title.jpg" alt="投票项目">
	<div class="caption">
		<h3>可选项</h3>
		<form action="${pageContext.request.contextPath}/topicDetail/vote.do" method="post" onsubmit="checkSession('${userSession.userID}')">
			<c:forEach var="voteOption" items="${VoteOptions }" varStatus="status">
				${voteOption.voteCode } ${status.index+1 }.${voteOption.voteOBJ }
				<div class="progress progress-striped active">
					<span class="progressbar-back-text" style="left: 0px; width: 250px; line-width: 250px;"></span>
					<div class="progress-bar progress-bar-info" style="width: ${voteOption.remark}%">${voteOption.remark}%</div>
				</div>
			</c:forEach>
			${VoteSubmit }
		</form>
	</div>
</div>
