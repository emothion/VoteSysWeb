<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="search container-fluid text-right" style="margin: auto; width: 99%">
	<form class="form-inline" action="${pageContext.request.contextPath }/topicChecker/getTopicInfoList.do" method="post">
		<div class="input-group">
			<label class="checkbox-inline">
				<input type="checkbox" name="topicStatus" id="statusP" ${topicSession.topicStatus == "P" ? "checked='checked'" : "" } value="P"> 待发布
			</label>
			<label class="checkbox-inline">
				<input type="checkbox" name="topicStatus" id="statusU" ${topicSession.topicStatus == "U" ? "checked='checked'" : "" } value="U"> 进行中
			</label>
			<label class="checkbox-inline">
				<input type="checkbox" name="topicStatus" id="statusS" ${topicSession.topicStatus == "S" ? "checked='checked'" : "" } value="S"> 已关闭 &nbsp;
			</label>
		</div>
		<div class="input-group">
			<input type="text" class="form-control" name="topicTitle" value="${topicSession.topicTitle }" placeholder="请输入主题的关键字...">
			<span class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<span class="glyphicon glyphicon-search"></span>&nbsp;查询
				</button>
			</span>
		</div>
	</form>
</div>
<br />
<div class="table-responsive" style="margin: auto; width: 99%">
	<table class="table table-hover table-striped table-condensed text-center">
		<tr>
			<th class="text-center" style="width: 10%">编号</th>
			<th class="text-center">名称</th>
			<th class="text-center">创建时间</th>
			<th class="text-center" style="width: 10%">状态</th>
			<th class="text-center" style="width: 10%">操作</th>
		</tr>
		<c:forEach var="topic" items="${topics }" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${topic.topicTitle}</td>
				<td>${topic.createTime}</td>
				<td>${topic.topicStatus }</td>
				<td>
					<button type="button" class='btn btn-warning btn-xs ${topic.topicStatus=="P" ? "hidden":"" }'><span class="glyphicon glyphicon-export" aria-hidden="true"></span>&nbsp;导出</button>
					<button type="button" class='btn btn-warning btn-xs ${topic.topicStatus=="P" ? "":"hidden" }'><span class="glyphicon glyphicon-send" aria-hidden="true"></span>&nbsp;发布</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="row search container-fluid" style="margin: auto; width: 99%">
	<div class="col-md-offset-4">
		<nav>
			<ul class="pagination">
				<li>
					${pageCode }
				</li>
			</ul>
		</nav>
	</div>
</div>
