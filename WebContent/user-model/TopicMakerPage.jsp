<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="step1">
	<form class="form-horizontal">
		<input type="hidden" id="opType" value="insert">
		<div class="form-group">
			<label for="userEmail" class="col-sm-2 control-label">主题</label>
			<div class="col-sm-8">
				<input type="email" class="form-control" name="topicTitle" id="topicTitle" placeholder="必填：请编辑标题...">
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="userDesc" class="col-sm-2 control-label">内容编辑</label>
			<div class="col-sm-8">
				<textarea class="form-control" rows="4" name="topicContent" id="topicContent" placeholder="必填：请编辑主题内容..."></textarea>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="topicStatus" class="col-sm-2 control-label">发布方式</label>
			<div class="col-sm-2">
				<select class="form-control" onchange="openEffTimeForm()" name="topicStatus" id="topicStatus">
					<option value="U">即刻</option>
					<option value="P">延迟</option>
				</select>
			</div>
		</div>
		<div class="form-group hidden" id="setEffTime">
			<label for="effTime" class="col-sm-2 control-label">日期</label>
			<div class="col-sm-2">
				<div class="date form_datetime">
					<input type="text" class="form-control" name="effTime" id="effTime">
				</div> 
			</div>
			<label for="topicH" class="col-sm-1 control-label">时</label>
			<div class="col-sm-2">
				<select class="form-control" name="topicH" id="topicH"></select>
			</div>
			<label for="topicM" class="col-sm-1 control-label">分</label>
			<div class="col-sm-2">
				<select class="form-control" name="topicM" id="topicM"></select>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group" style="padding-left: 20%; padding-right: 20%;">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4"></div>
					<div class="col-md-4 text-left">
						<button type="button" class="btn btn-info btn-block" onclick="gotoStep2()">下一步</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="step2" style="width: 90%;margin-left: 5%" class="hidden">
	<form class="form-horizontal" enctype="multipart/form-data" method="post">
		<div class="form-group">
			<input type="hidden" id="topicID" value="">
		    <input id="file-1" name="upFile" type="file" multiple data-overwrite-initial="false" data-min-file-count="1">
		</div>
		<div class="form-group" style="padding-left: 20%; padding-right: 20%;">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4 text-right">
						<button type="button" class="btn btn-info btn-block" onclick="backToStep1()">上一步</button>
					</div>
					<div class="col-md-4"></div>
					<div class="col-md-4 text-left">
						<button type="button" class="btn btn-info btn-block" onclick="gotoStep3()">下一步</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="step3" class="hidden">
	<form class="form-horizontal" id="step3Form">
		<input type="hidden" id="voteIDs" value="">
		<div class="form-group">
			<label for="vote1" class="col-sm-2 control-label">1.</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" name="vote" id="vote1" placeholder="">
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="vote2" class="col-sm-2 control-label">2.</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" name="vote" id="vote2" placeholder="">
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
	</form>
	<div class="form-group" style="padding-left: 20%; padding-right: 20%;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 text-right">
					<button type="button" class="btn btn-info btn-block" onclick="backToStep2()">上一步</button>
				</div>
				<div class="col-md-4 text-center">
					<button type="button" class="btn btn-danger" id="delBtn" onclick="delVoteObj('#vote')" disabled="disabled">
						<span class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span>
					</button>
					<button class="btn btn-info" onclick="incVote()" id="increaseBtn">
						<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
					</button>
				</div>
				<div class="col-md-4 text-left">
					<button type="button" class="btn btn-info btn-block" onclick="gotoStep4()">下一步</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="step4" class="hidden">
	<form class="form-horizontal" id="step3Form">
		<div class="form-group">
			<label for="voteStop" class="col-sm-2 control-label">结束方式</label>
			<div class="col-sm-4">
				<select class="form-control" onchange="openExpTimeForm()" name="voteStop" id="voteStop">
					<option value="O" selected="selected">手动失效</option>
					<option value="A">自动失效</option>
				</select>
			</div>
			<div class="col-sm-offset-6"></div>
		</div>
		<div class="form-group hidden" id="setExpTime">
			<label for="expTime" class="col-sm-2 control-label">日期</label>
			<div class="col-sm-2">
				<div class="date form_datetime">
					<input type="text" class="form-control" name="expTime" id="expTime">
				</div> 
			</div>
			<label for="voteHours" class="col-sm-1 control-label">时</label>
			<div class="col-sm-2">
				<select class="form-control" name="voteH" id="voteH"></select>
			</div>
			<label for="voteM" class="col-sm-1 control-label">分</label>
			<div class="col-sm-2">
				<select class="form-control" name="voteM" id="voteM"></select>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
		<div class="form-group">
			<label for="userEmail" class="col-sm-2 control-label">投票类型</label>
			<div class="col-sm-8">
				<label class="radio-inline" id="voteStyle">
					<input type="radio" name="voteStyle" id="voteStyleR" value="R"> 单选
				</label>
				<label class="radio-inline">
					<input type="radio" name="voteStyle" id="voteStyleC" value="C"> 复选
				</label>
			</div>
			<div class="col-sm-offset-2"></div>
		</div>
	</form>
	<div class="form-group" style="padding-left: 20%; padding-right: 20%;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4 text-right">
					<button type="button" class="btn btn-info btn-block" onclick="backToStep3()">上一步</button>
				</div>
				<div class="col-md-4 text-center"></div>
				<div class="col-md-4 text-left">
					<button type="button" class="btn btn-info btn-block" onclick="completeMaker()">完成</button>
				</div>
			</div>
		</div>
	</div>
</div>