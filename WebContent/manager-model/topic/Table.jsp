<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container" style="width: 100%">
	<div class="row search">
		<form action="${pageContext.request.contextPath}/manager/topicGetNextPage.do?page=1" method="post">
			<div class="col-md-7">
			</div>
			<div class="col-md-2 text-right" style="margin-top: 5px">
				<label class="radio-inline">
					<input type="radio" name="topicStatus" value="U" > 发布
				</label>
				<label class="radio-inline">
					<input type="radio" name="topicStatus" value="S"> 结束
				</label>
				<label class="radio-inline">
					<input type="radio" name="topicStatus" value="P"> 预设
				</label>
			</div>
			<div class="col-md-3">
				<div class="input-group">
					<input type="text" class="form-control" name=topicTitle placeholder="请输入查找的关键字...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
					</span>
				</div>
			</div>
		</form>
	</div>
</div>
<hr />
<table class="table table-hover text-center" style="margin-bottom: 0px;">
	<tr>
		<th class="text-center" style="width: 5%">编号</th>
		<th class="text-center" style="width: 10%">题目</th>
		<th class="text-center" style="width: 60%">内容</th>
		<th class="text-center" style="width: 10%">生效</th>
		<th class="text-center" style="width: 10%">失效</th>
		<th class="text-center" style="width: 5%">操作</th>
	</tr>
	<c:forEach var="topic" items="${topicList }" varStatus="status">
		<tr>
			<td>${topic.topicID }</td>
			<td>${topic.topicTitle}</td>
			<td>${topic.topicContent }</td>
			<td>${topic.effTime }</td>
			<td>${topic.expTime }</td>
			<td>
				${topic.topicStatus=="S" ? "结束" : "" }
				<button type="button" class='btn btn-danger btn-xs ${topic.topicStatus=="P" ? "" : "hidden" }' onclick="publishTopic('${topic.topicID }')">
					<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span> 发布
				</button>
				<button type="button" class='btn btn-danger btn-xs ${topic.topicStatus=="U" ? "" : "hidden" }' onclick="endTopic('${topic.topicID }')">
					<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 结束
				</button>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="container text-center" style="width: 100%">
	<nav>
		<ul class="pagination">
			<li>
				${pageCode }
			</li>
		</ul>
	</nav>
</div>
