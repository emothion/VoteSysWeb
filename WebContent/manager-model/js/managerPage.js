/**
 * 禁止用户登录并将禁止按键变成恢复按键
 * @param userID
 */
function forbidLogon(userID) {
	$.post(url + "/manager/userStatusChange.do", {
		userID : userID,
		userStatus : 'S'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			var fb = "#fb"+userID;
			var rc = "#rc"+userID;
			$("#sucMsg").text(result.retMsg+'请刷新页面');
			$('#successModel').modal('show');
			$(fb).addClass('hidden');//隐藏禁止按键
			$(rc).removeClass('hidden');//显示恢复按键
			setTimeout(function(){$('#successModel').modal('hide');},1500);
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 恢复用户登录
 * @param userID
 */
function recoverLogon(userID) {
	$.post(url + "/manager/userStatusChange.do", {
		userID : userID,
		userStatus : 'U'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			var fb = "#fb"+userID;
			var rc = "#rc"+userID;
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
			$(rc).addClass('hidden');//隐藏恢复按键
			$(fb).removeClass('hidden');//显示禁止按键
			setTimeout(function(){$('#successModel').modal('hide');},1500);
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 发布主题
 */
function publishTopic(topicID) {
	$.post(url + "/manager/topicStatusChange.do", {
		topicID : topicID,
		topicStatus : 'U'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			var pt = "#pt"+topicID;
			var et = "#et"+topicID;
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
			$(pt).addClass('hidden');//隐藏发布按键
			$(et).removeClass('hidden');//显示结束按键
			setTimeout(function(){$('#successModel').modal('hide');},1500);
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 结束主题并将结束按键转换为结束两个字
 * @param topicID
 */
function endTopic(topicID) {
	$.post(url + "/manager/topicStatusChange.do", {
		topicID : topicID,
		topicStatus : 'S'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			var et = "#et"+topicID;
			var td = "#td"+topicID;
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
			$(et).addClass('hidden');//隐藏结束按键
			$(td).text("结束");//显示结束文字
			setTimeout(function(){$('#successModel').modal('hide');},1500);
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 封禁评论
 * @param comID
 */
function banComment(comID) {
	$.post(url + "/manager/comStatusChange.do", {
		comID : comID,
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			var bc = "#bc"+comID;
			var td = "#td"+comID;
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
			$(bc).addClass('hidden');//不显示封禁按键
			$(td).text("内容已经被屏蔽...");//替换页面上文本为内容已经屏蔽
			setTimeout(function(){$('#successModel').modal('hide');},1500);
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}