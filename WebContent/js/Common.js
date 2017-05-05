/**
 * 
 */
function logout(url) {
	if(confirm('您确定要退出系统吗?')){
		window.location.href=url+"/userOperate/userLogout.do";
	}
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

$(function() {
	/**
	 * 用户名是否重复 校验
	 */
	$("#userNameA").blur(function() {
		var userName = $("#userNameA").val();
		if (userName == "") {
			$("#userNameA").parent().addClass("has-error has-feedback").children().
			attr('data-toggle', 'tooltip').attr('data-placement', 'right').attr('title', '昵称不能为空').
			after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'>" +
			"</span><span id='inputError2Status' class='sr-only'>(error)</span>");
		} else {
			$.post("/VoteSysWeb/userCheck/userNameReSeek.do", {
				userName : $("#userNameA").val()
			}, function(result) {
				var result = eval('(' + result + ')');
				if (result.Code == '00') {
					if (result.retMsg) {
						$("#userNameA").parent().addClass("has-error has-feedback").children().
						attr('data-toggle', 'tooltip').attr('data-placement', 'right').attr('title', '昵称不能重复').
						after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'>" +
						"</span><span id='inputError2Status' class='sr-only'>(error)</span>");
					} else {
						$("#userNameA").removeAttr('title').removeAttr('data-placement').removeAttr('data-toggle').
						parent().removeClass("has-error has-feedback");
						$("#userNameA").siblings().remove()
					}
				}
			});
		}
	});
	
	/**
	 * 电话号码格式校验
	 */
	$("#userPhone").blur(function() {
		var phone = $("#userPhone").val();
		var reg = new RegExp("^[0-9]{11}$");
		if(!reg.test(phone)) {
			$("#userPhone").parent().addClass("has-error has-feedback").children().
			attr('data-toggle', 'tooltip').attr('data-placement', 'right').attr('title', '电话号码为纯数字11位').
			after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'>" +
			"</span><span id='inputError2Status' class='sr-only'>(error)</span>");
		} else {
			$("#userPhone").removeAttr('title').removeAttr('data-placement').removeAttr('data-toggle').
			parent().removeClass("has-error has-feedback");
			$("#userPhone").siblings().remove()
		}
	});
})

/**
 * 登陆
 * @param url
 * @returns
 */
function Logon(url) {
	$.post(url+"/userOperate/userLogon.do", {
		userName : $("#userName").val(),
		userKey : $("#userKey").val()
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			window.location.href=url+"/"; 
		} else if (result.Code == '01') {
			$("#logonAlert").removeClass("hidden").find("p").text(result.retMsg);
		} else if (result.Code == '10') {
			$("#logonAlert").removeClass("hidden").find("p").text(result.retMsg);
		}
	});
}

/**
 * 注册
 * @param url
 * @returns
 */
function Login(url) {
	var userName = $("#userNameA").val();
	var userEmail = $("#userEmail").val();
	var userPhone = $("#userPhone").val();
	var passwordA = $("#userKeyA").val();
	var passwordB = $("#userKeyB").val();
	
	if (userName == "" || userEmail == "" || userPhone == "" || passwordA == "" || passwordB == "") {
		$("#loginAlert").removeClass("hidden").find("p").text("所有项目均为必填项！请编辑后进入下一步");
		return;
	}
	
	if (passwordA == passwordB) {
		$.post(url+"/userOperate/userLogin.do", {
			userName : userName,
			userEmail : userEmail,
			userPhone : userPhone,
			userKey : passwordA
		}, function(result) {
			var result = eval('(' + result + ')');
			if (result.Code == '00') {
				window.location.href=url+"/"; 
			} else if (result.Code == '11') {
				$("#logonAlert").removeClass("hidden").find("p").text(result.retMsg);
			}
		});
	} else {
		$("#loginAlert").removeClass("hidden").find("p").text(result.retMsg);
	}
}

/**
 *首页js 
 */
$('#LoginModal').on('shown.bs.modal', function() {});

function checkLogonForm() {
	var userName=document.getElementById("userName").value;
	var password=document.getElementById("password").value;
	if(userName==null || userName==""){
		document.getElementById("login_err").innerHTML="用户名不能为空";
		return false;
	}
	if(password==null || password==""){
		document.getElementById("login_err").innerHTML="密码不能为空";
		return false;
	}
	return true;
}


