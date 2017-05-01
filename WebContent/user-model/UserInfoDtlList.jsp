<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<dl class="dl-horizontal">
	<dt>用户电邮</dt>
	<dd>${userAllInfo.userEmail }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>用户电话</dt>
	<dd>${userAllInfo.userPhone }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>用户性别</dt>
	<dd>${userAllInfo.userSexy }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>用户生日</dt>
	<dd>${userAllInfo.userBirth }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>所在省会</dt>
	<dd>${userAllInfo.userProvince }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>所在城市</dt>
	<dd>${userAllInfo.userCity }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>所在县区</dt>
	<dd>${userAllInfo.userRegion }</dd>
</dl>
<hr />
<dl class="dl-horizontal">
	<dt>个人签名</dt>
	<dd>${userAllInfo.userDesc }</dd>
</dl>