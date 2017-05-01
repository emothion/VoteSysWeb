<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="alertModel" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
				<span class="sr-only">Error:</span>
				糟&nbsp;糕&nbsp;！
			</div>
			<div class="modal-body text-center">
				<div style="margin-bottom: 8%">
	      			<img src="../image/sys/error.png" alt="error">
				</div>
				<p id="errMsg">编辑操作发生错</p>
			</div>
  		</div>
	</div>
</div>

<div id="warningModel" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header alert alert-warning" role="alert">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
				<span class="sr-only">W:</span>
				警&nbsp;告&nbsp;！
			</div>
			<div class="modal-body text-center">
				<div style="margin-bottom: 8%">
	      			<img src="../image/sys/warning.png" alt="warning">
				</div>
				<p id="warnMsg">请编辑好图片后再点击上传，否则无法修改</p>
			</div>
  		</div>
	</div>
</div>

<div id="successModel" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header alert alert-success" role="alert">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
				<span class="sr-only">W:</span>
				警&nbsp;告&nbsp;！
			</div>
			<div class="modal-body text-center">
				<div style="margin-bottom: 8%">
	      			<img src="../image/sys/success.png" alt="success">
				</div>
				<p id="sucMsg">发布成功，3秒后会自动跳转</p>
			</div>
  		</div>
	</div>
</div>