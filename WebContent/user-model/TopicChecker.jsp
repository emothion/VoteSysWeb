<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row search container-fluid" style="margin: auto; width: 99%">
	<div class="col-md-offset-8">
		<form action="" method="post">
			<div class="input-group" style="width: 300px">
				<input type="text" class="form-control" name="topicTitle" value="${condition }" placeholder="请输入主题的关键字...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<span class="glyphicon glyphicon-search"></span>&nbsp;查询
					</button>
				</span>
			</div>
		</form>
	</div>
</div>
<br />
<div class="table-responsive" style="margin: auto; width: 99%">
	<table class="table table-hover table-striped table-condensed text-center">
		<tr>
			<th class="text-center" style="width: 10%">编号</th>
			<th class="text-center">名称</th>
			<th class="text-center" style="width: 10%">状态</th>
		</tr>
		<c:forEach var="topic" items="${topics }" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${topic.topicTitle}</td>
				<td>${topic.topicStatus }</td>
			</tr>
		</c:forEach>
	</table>
</div>
<nav style="margin-left: 400px">
	<ul class="pagination">
		<li>
			${pageCode }
		</li>
	</ul>
</nav>
