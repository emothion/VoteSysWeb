<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container" style="width: 100%;height: 20px">
</div>
<hr />
<table class="table table-hover text-center" style="margin-bottom: 0px;">
	<tr>
		<th class="text-center" style="width: 5%">编号</th>
		<th class="text-center" style="width: 80%">内容</th>
		<th class="text-center" style="width: 5%">踩/次</th>
		<th class="text-center" style="width: 5%">赞/次</th>
		<th class="text-center" style="width: 5%">操作</th>
	</tr>
	<c:forEach var="com" items="${comList }" varStatus="status">
		<tr>
			<td>${com.comID }</td>
			<td>${com.comContent}</td>
			<td>${com.subNum }</td>
			<td>${com.upNum }</td>
			<td>
				<button type="button" class='btn btn-danger btn-xs' onclick="banComment('${com.comID }')">
					<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span> 封禁
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
