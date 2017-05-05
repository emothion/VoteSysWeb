<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form class="form-horizontal" action="${pageContext.request.contextPath}/userDetail/userInfoDetailEdit.do" method="post" style="margin-right: 40px; margin-left: 40px;" onsubmit="confirmSubmit()">
	<div class="form-group">
		<label for="userEmail" class="col-sm-2 control-label">用户电邮</label>
		<div class="col-sm-8">
			<input type="email" class="form-control" name="userEmail" id="userEmail" value="${userAllInfo.userEmail }" placeholder="请输入电子邮件地址...">
		</div>
		<div class="col-sm-offset-2"></div>
	</div>
	<div class="form-group">
		<label for="userPhone" class="col-sm-2 control-label">用户电话</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" name="userPhone" id="userPhone" value="${userAllInfo.userPhone }" placeholder="请输入电话号码...">
		</div>
		<div class="col-sm-offset-2"></div>
	</div>
	<div class="form-group">
		<label for="userSexy" class="col-sm-2 control-label">用户性别</label>
		<div class="col-sm-8">
			<label class="radio-inline" id="userSexy">
			  <input type="radio" name="userSexy" id="userSexyF" value="00"> 女
			</label>
			<label class="radio-inline">
			  <input type="radio" name="userSexy" id="userSexyM" value="11"> 男
			</label>
		</div>
		<div class="col-sm-offset-2"></div>
	</div>
	<div class="form-group">
		<label for="userBirth" class="col-sm-2 control-label">用户生日</label>
		<div class="col-sm-8">
			<div class="date input-group form_datetime">
				<input type="text" class="form-control" name="userBirth" id="userBirth" value="${userAllInfo.userBirth }">
				<span class="input-group-addon">  
				    <span class="glyphicon glyphicon-calendar"></span>  
				</span> 
			</div> 
		</div>
		<div class="col-sm-offset-2"></div>
	</div>
	<div data-toggle="distpicker" id="distpicker">
		<div class="form-group">
			<label for="userProvince" class="col-sm-2 control-label">所在省会</label>
			<div class="col-sm-8">
				<select class="form-control" name="userProvince" id="userProvince" data-province="${userAllInfo.userProvince }"></select>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="userCity" class="col-sm-2 control-label">所在城市</label>
			<div class="col-sm-8">
				<select class="form-control" name="userCity" id="userCity" data-city="${userAllInfo.userCity }"></select>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="userRegion" class="col-sm-2 control-label">所在县区</label>
			<div class="col-sm-8">
				<select class="form-control" name="userRegion" id="userRegion" data-district="${userAllInfo.userRegion }"></select>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
	</div>
	<div class="form-group">
		<label for="userDesc" class="col-sm-2 control-label">个人签名</label>
		<div class="col-sm-8">
			<textarea class="form-control" rows="4" name="userDesc" id="userDesc" placeholder="请编辑个人签名...">${userAllInfo.userDesc }</textarea>
		</div>
		<div class="col-sm-offset-2"></div>
	</div>
	<div class="form-group text-center" style="padding-left: 20%; padding-right: 20%;">
		<button type="button" class="btn btn-info btn-lg" onclick="openUserImgEdit()" style="width: 48%">编辑头像</button>
		<button type="submit" class="btn btn-info btn-lg" style="width: 48%">提交</button>
	</div>
</form>
