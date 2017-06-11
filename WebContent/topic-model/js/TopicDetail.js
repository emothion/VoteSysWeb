/**
 * 获取接下来的评论
 */
function getNextCommentPage(obj) {
	$.post(url+"/topicDetail/getNextComment.do", {
		page : page
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			++page;
			$("#nextComment").after(result.retMsg);
		} else if (result.Code == '01') {
			$(obj).attr('disabled', 'disabled');
			$(obj).text("没有更多了");
			$("#nextComment").after(result.retMsg);
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 发表评论
 * @returns
 */
function submitComment() {
	var comment = $("#comContent").val();
	if (comment == "") {
		$("#errMsg").text("请先填写评论内容");
		$('#alertModel').modal('show');
		return;
	}
	
	$.post(url+"/topicDetail/comment.do", {
		comment : comment
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
			$("#nextComment").before(result.comment);
			$("#comContent").text("");
		} else if (result.Code == '01') {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 提交踩或赞
 * @param comID
 * @param status
 * @returns
 */
function submitOpposerOrEndorse(comID, status, obj) {
	$.post(url+"/topicDetail/setOpposerOrEndorse.do", {
		comID : comID,
		status : status
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text("操作成功");
			$('#successModel').modal('show');
			if (status=="O") {
				$(obj).html("<span class='glyphicon glyphicon-thumbs-down' aria-hidden='true'></span>(" + result.SUB + ")");
			} else if (status=="E") {
				$(obj).html("<span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span>(" + result.UP + ")");
			}
		} else if (result.Code == '11'){
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 踩
 * @param comID
 * @returns
 */
function oppose(comID, obj) {
	submitOpposerOrEndorse(comID, 'O', obj);
}

/**
 * 赞
 * @param comID
 * @returns
 */
function endorse(comID, obj) {
	submitOpposerOrEndorse(comID, 'E', obj);
}

/**
 * 停止投票
 * @returns
 */
function stopTopic() {
	$.post(url+"/topicDetail/stopTopic.do", {}, 
			function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text("操作成功");
			$('#successModel').modal('show');
			setTimeout(function(){window.location.href=url+'/index.jsp';},3000);
		} else if (result.Code == '11'){
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}
