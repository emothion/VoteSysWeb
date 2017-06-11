<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container" style="width: 100%">
	<div class="row search">
		<form action="${pageContext.request.contextPath}/manager/userGetNextPage.do?page=1" method="post">
			<div class="col-md-7">
			</div>
			<div class="col-md-2 text-right" style="margin-top: 5px">
				<label class="radio-inline">
					<input type="radio" name="status" value="U" > 正常
				</label>
				<label class="radio-inline">
					<input type="radio" name="status" value="S" > 限制
				</label>
			</div>
			<div class="col-md-3">
				<div class="input-group">
					<input type="text" class="form-control" name="userName" value="" placeholder="请输入查找人的关键字...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-search"></span>&nbsp;查询
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
		<th class="text-center" style="width: 10%">编号</th>
		<th class="text-center" style="width: 30%">名称</th>
		<th class="text-center" style="width: 40%">电邮</th>
		<th class="text-center" style="width: 10%">电话</th>
		<th class="text-center" style="width: 10%">操作</th>
	</tr>
	<c:forEach var="user" items="${userList }" varStatus="status">
		<tr>
			<td>${user.userID }</td>
			<td>${user.userName}</td>
			<td>${user.userEmail }</td>
			<td>${user.userPhone }</td>
			<td>
				<button type="button" class='btn btn-danger btn-xs ${user.status=="U" ? "" : "hidden" }' id="fb${user.userID }" onclick="forbidLogon('${user.userID }')">
					<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span> 限制登录
				</button>
				<button type="button" class='btn btn-success btn-xs ${user.status=="S" ? "" : "hidden" }' id="rc${user.userID }" onclick="recoverLogon('${user.userID }')">
					<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 恢复登录
				</button>
			</td>
		</tr>
		<tr>
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
