/**
 * 
 */
function forbidLogon(userID) {
	$.post(url + "/manager/userStatusChange.do", {
		userID : userID,
		userStatus : 'S'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}


function recoverLogon(userID) {
	$.post(url + "/manager/userStatusChange.do", {
		userID : userID,
		userStatus : 'U'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

function publishTopic(topicID) {
	$.post(url + "/manager/topicStatusChange.do", {
		topicID : topicID,
		topicStatus : 'U'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

function endTopic(topicID) {
	$.post(url + "/manager/topicStatusChange.do", {
		topicID : topicID,
		topicStatus : 'U'
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

function banComment(comID) {
	$.post(url + "/manager/comStatusChange.do", {
		comID : comID,
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text(result.retMsg);
			$('#successModel').modal('show');
		} else {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}