<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div class="container" style="width: 50%; margin: auto;">
	<form class="form-horizontal">
		<input type="hidden" id="checkResult" value="false">
		<div class="alert alert-danger hidden" role="alert" id = "resetAlert">
           <strong>错误！</strong>
           <p>旧密码输入不正确，请重新输入...</p>
         </div>
		<div class="form-group">
			<label for="oldPassword" class="col-sm-3 control-label">旧密码</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="请输入原密码..." onblur="CheckPassword()" data-toggle="tooltip" data-placement="right">
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="newPassword" class="col-sm-3 control-label">新密码</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="请输入新密码...">
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="repeat" class="col-sm-3 control-label">重复新密码</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="repeat" id="repeat" placeholder="请再次输入新密码...">
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group" style="padding-left: 20%; padding-right: 20%;">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4 text-center">
						<button type="button" class="btn btn-info btn-block" disabled="disabled" id="resetBtn" onclick="resertPassword('this')">重置</button>
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
		</div>
	</form>
</div>