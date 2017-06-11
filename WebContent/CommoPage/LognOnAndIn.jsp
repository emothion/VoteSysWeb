<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 登陆弹出窗 开始 -->
<div class="modal fade" id="LogonModal" tabindex="-1" role="dialog" aria-labelledby="logonModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <form id="logonForm">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="logonModalLabel">
	                     请登陆
	        </h4>
	      </div>
	      <div class="modal-body">
			  <div class="form-group">
			    <label for="userName">用户名</label>
			    <input type="text" class="form-control" id="userName" name="userName" placeholder="Username">
			  </div>
			  <div class="form-group">
			    <label for="userKey">密码</label>
			    <input type="password" class="form-control" id="userKey" name="userKey" placeholder="Password">
			  </div>
			  <div class="alert alert-danger hidden" role="alert" id = "logonAlert">
	            <strong>错误!</strong>
	            <p>用户名或密码错误！</p>
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" onclick="Logon('${pageContext.request.contextPath}')">登录</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<!-- 登陆弹出窗 结束 -->

<!-- 注册弹出窗 开始 -->
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <form id="loginForm">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="loginModalLabel">
	        	注册
	        </h4>
	      </div>
	      <div class="modal-body" id = register>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="text" class="form-control" id="userNameA" name="userNameA" placeholder="请输入昵称..." data-toggle="tooltip" data-placement="right">
			  </div>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="请输入邮箱..." data-toggle="tooltip" data-placement="right">
			  </div>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="请输入电话..." data-toggle="tooltip" data-placement="right">
			  </div>
			  <div class="form-group" style="padding-bottom: 5px">
  				<input type="password" class="form-control" id="userKeyA" name="userKey" placeholder="请设置密码...">
			  </div>
			  <div class="form-group">
  				<input type="password" class="form-control" id="userKeyB" name="userKeyA" placeholder="重复密码...">
			  </div>
			  <div class="alert alert-danger hidden" role="alert" id = "loginAlert">
	            <strong>错误!</strong>
	            <p></p>
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="Login('${pageContext.request.contextPath}')">注册</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<!-- 注册弹出窗 结束 -->
