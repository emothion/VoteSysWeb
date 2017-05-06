/**
 * 标记投票项目的目前个数
 */
var voteNum = 2;

/**
 * 小时下拉框选项设置
 */
var hour = {
		"options":[{"value":""},{"value":"00"},{"value":"01"},{"value":"02"},{"value":"03"},
			{"value":"04"},{"value":"05"},{"value":"06"},{"value":"07"},{"value":"08"},
			{"value":"09"},{"value":"10"},{"value":"11"},{"value":"12"},{"value":"13"},
			{"value":"14"},{"value":"15"},{"value":"16"},{"value":"17"},{"value":"18"},
			{"value":"19"},{"value":"20"},{"value":"21"},{"value":"22"},{"value":"23"}]
}

/**
 * 分钟下拉框选项设置
 */
var minute = {
		"options":[{"value":""},{"value":"00"},{"value":"05"},{"value":"10"},
			{"value":"15"},{"value":"20"},{"value":"25"},{"value":"30"},
			{"value":"35"},{"value":"40"},{"value":"45"},{"value":"50"},{"value":"55"}]
}

$(function() {
	/**
	 * 生日的日期控件初始化
	 */
	$("#userBirth").jeDate({
	    isinitVal:true,
	    festival:true,
	    ishmsVal:false,
	    format:"YYYY-MM-DD",
	    zIndex:3000,
	})
	
	/**
	 * 生效时间的日期控件初始化
	 */
	$.jeDate("#effTime",{
	    format:"YYYY-MM-DD",
	    isTime:true,
	    festival: true,
	})

	/**
	 * 失效时间的日期控件初始化
	 */
	$.jeDate("#expTime",{
	    format:"YYYY-MM-DD",
	    isTime:true,
	    festival: true,
	})

	/**
	 * 左边菜单栏对应的变成蓝色
	 */
	cancelListActive();
	if (flag == 2) {
		$("#userInfoFormPage").addClass("active");
	} else if (flag == 3) {
		$("#publishTopicPage").addClass("active");
		$("#pathNav").removeClass("hidden");
	} else if (flag == 4) {
		$("#topicListPage").addClass("active");
	} else if (flag == 5) {
		$("#resetKeyPage").addClass("active");
	} else if (flag == 0) {
		$("#userInfoFormPage").addClass("active");
		$('#alertModel').modal('show');
	} else {
		$("#userInfoListPage").addClass("active");
	}
	
	/**
	 * 获取到性别值后给对应的单选框选上
	 */
	if (sex == 00) {
		$("input[type=radio][name=userSexy][value=00]").attr("checked",'checked');
	} else if (sex == 11) {
		$("input[type=radio][name=userSexy][value=11]").attr("checked",'checked')
	}
	
	/**
	 * 设置小时下拉框的下拉选项
	 */
	var json = eval(hour.options) 
	for (var i = 0; i < json.length; i++) {
		$("#topicH").append("<option value='"+json[i].value+"'>"+json[i].value+"</option>");
		$("#voteH").append("<option value='"+json[i].value+"'>"+json[i].value+"</option>");
	}
	
	/**
	 * 设置分钟下拉框的下拉选项
	 */
	json = eval(minute.options)
	for (var i = 0; i < json.length; i++) {
		$("#topicM").append("<option value='"+json[i].value+"'>"+json[i].value+"</option>");
		$("#voteM").append("<option value='"+json[i].value+"'>"+json[i].value+"</option>");
	}
});

/**
 * 取消列表所有项目活动状态
 */
function cancelListActive() {
	$("#pathNav").addClass("hidden");
	$("#userInfoListPage").removeClass("active");
	$("#userInfoFormPage").removeClass("active");
	$("#publishTopicPage").removeClass("active");
	$("#topicListPage").removeClass("active");
	$("#resetKeyPage").removeClass("active");
}

/**
 * 在第一步选择延迟生效主题的时候弹出设置自动发布时间的填写框
 * @returns
 */
function openEffTimeForm() {
	var status = $("#topicStatus").val();
	if (status == 'P') {
		$("#setEffTime").removeClass("hidden");
	} else {
		$("#setEffTime").addClass("hidden");
	}
}

/**
 * 在第四步选择自动结束主题的时候弹出设置自动结束时间的填写框
 * @returns
 */
function openExpTimeForm() {
	var status = $("#voteStop").val();
	if (status == 'A') {
		$("#setExpTime").removeClass("hidden");
	} else {
		$("#setExpTime").addClass("hidden");
	}
}

/**
 * 增加投票项目的按键在投票项目10个的时候禁用
 * @param obj
 * @returns
 */
function incVote(obj) {
	voteNum++;
	if (voteNum <= 10) {
		$("#step3Form").append("<div class='form-group' id='vote"+voteNum+"'><label for='vote"+
				voteNum+"' class='col-sm-2 control-label'>"+
				voteNum+".</label><div class='col-sm-8'><input type='text' class='form-control' name='vote' id='vote"+
				voteNum+"' placeholder=''></div><div class='col-sm-offset-2'></div></div>");
	} 
	bandBtn();
	if (voteNum == 10) {
		$("#increaseBtn").attr("disabled","disabled");
	}
}

/**
 * 减少投票项目的按键在投票项目只剩两个的时候禁用
 * @param obj
 * @returns
 */
function delVoteObj(obj) {
	obj += voteNum;
	$(obj).remove();
	voteNum--;
	bandBtn();
	if (voteNum == 2) {
		$("#delBtn").attr("disabled","disabled");
	}
}

/**
 * 当投票项目在2-10之间，增加项目和减少项目的按键才能同时生效
 * @returns
 */
function bandBtn() {
	if (voteNum >= 2 && voteNum <=10 ) {
		$("#delBtn").removeAttr("disabled");
		$("#increaseBtn").removeAttr("disabled");
	}
}

/**
 * 跳转到第二步去
 * @returns
 */
function gotoStep2() {
	var effTime = "";
	if ($("#effTime").val() != "" && $("#topicH").val() != "" && $("#topicM").val() != "") {
		var effTime = $("#effTime").val()+" "+$("#topicH").val()+":"+$("#topicM").val();
	}
	var opType = $("#opType").val();
	var reqUrl = url+"/topicMaker/stepOneSaveTopicInfo.do";
	if ($("#topicTitle").val() == "" || $("#topicContent").val()  == "" ||  $("#topicStatus").val() == "") {
		$("#errMsg").text("页面内均为必填项！请编辑后进入下一步");
		$('#alertModel').modal('show');
		return;
	} else {
		if ($("#topicStatus").val() == "P" && effTime == "") {
			$("#errMsg").text("延迟发布，必须设置发布时间！");
			$('#alertModel').modal('show');
			return;
		}
	}
	
	getCurrentTime()
	var currentTime = new Date(time);
	var getTime = new Date(effTime);
	
	if (currentTime >= getTime) {
		$("#errMsg").text("设定时间必须大于当前时间");
		$('#alertModel').modal('show');
		return;
	}
	time = effTime;
	
	$.post(reqUrl, {
		topicTitle : $("#topicTitle").val(),
		topicContent : $("#topicContent").val(),
		topicStatus : $("#topicStatus").val(),
		effTime : effTime,
		opType : opType
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#step1").addClass("hidden");
			$("#step2").removeClass("hidden");
			$("#li2").addClass("active");
			$("#li1").removeClass("active");
			$("#topicID").val(result.topicID);
			$("#opType").val("update");
			$("#file-1").fileinput({
				uploadUrl: url+"/topicMaker/stepTwoSaveImg.do?topicID="+$("#topicID").val(), 
				uploadAsync: false,
				language : "zh",
				allowedFileExtensions : ['jpg', 'png','gif'],
				overwriteInitial: false,
				maxFileSize: 1000,
				maxFilesNum: 10,
				enctype: 'multipart/form-data',
				browseClass: "btn btn-info",
				allowedFileTypes: ['image']
			});
			$('#warningModel').modal('show');
		} else if (result.Code == '11') {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 跳转到第三步去
 * @returns
 */
function gotoStep3() {
	$("#step2").addClass("hidden");
	$("#step3").removeClass("hidden");
	$("#li3").addClass("active");
	$("#li2").removeClass("active");
}

/**
 * 主题发布：完成第三步进入第四步
 * @returns
 */
function gotoStep4() {
	var voteOption="";
	var reqUrl = url+"/topicMaker/stepThirdSaveVoteInfo.do";
	var num = 1;
	var amount = 1;
	$("input[name='vote']").each(
		function(){
			if ($(this).val() != "" && num == amount) {
				num++;
			}
			voteOption = voteOption+$(this).val()+"&";
			amount++;
		}
	) 
	
	if (num != amount) {
		$("#errMsg").text("第"+num+"项未填写，请将不需要的输入框去掉");
		$('#alertModel').modal('show');
		return;
	}
	
	$.post(reqUrl, {
		voteOptions : voteOption,
		topicID : $("#topicID").val()
	}, function(result){
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#step3").addClass("hidden");
			$("#step4").removeClass("hidden");
			$("#li4").addClass("active");
			$("#li2").removeClass("active");
			$("#voteIDs").val(result.voteIDS);
		} else if (result.Code == '11') {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 主题发布：完成主题创建
 * @returns
 */
function completeMaker() {
	var expTime = "";
	if ($("#expTime").val() != "" && $("#voteH").val() != "" && $("#voteM").val() != "") {
		var expTime = $("#expTime").val()+" "+$("#voteH").val()+":"+$("#voteM").val();
	}
	
	if ($("#voteStop").val() == "" || $("input[name='voteStyle']:checked").val() == undefined) {
		$("#errMsg").text("页面内均为必填项！请编辑后进入下一步");
		$('#alertModel').modal('show');
		return;
	} else {
		if ($("#voteStop").val() == "A" && expTime == "") {
			$("#errMsg").text("自动结束投票，必须设置结束时间！");
			$('#alertModel').modal('show');
			return;
		}
	}
	
	var getTime = new Date(expTime);
	var effTime = new Date(time);
	if (getTime <= effTime) {
		$("#errMsg").text("结束时间必须大于主题生效时间");
		$('#alertModel').modal('show');
		return;
	}
	
	var reqUrl = url + "/topicMaker/completeMakeTopic.do";
	$.post(reqUrl, {
		expTime : expTime,
		voteStop : $("#voteStop").val(),
		voteStyle : $("input[name='voteStyle']:checked").val(),
		topicID : $("#topicID").val()
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$('#successModel').modal('show');
			setTimeout(function(){window.location.href=url+result.retMsg;},3000)
		} else if (result.Code == '11') {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 主题发布：从第二步返回到第一步去
 * @returns
 */
function backToStep1() {
	$("#step2").addClass("hidden");
	$("#step1").removeClass("hidden");
	$("#li1").addClass("active");
	$("#li2").removeClass("active");
	$("#opType").val("update");
}

/**
 * 主题发布：从第三步返回到第二步去
 * @returns
 */
function backToStep2() {
	$("#step3").addClass("hidden");
	$("#step2").removeClass("hidden");
	$("#li2").addClass("active");
	$("#li3").removeClass("active");
}

/**
 * 主题发布：从第四步返回到第三步去
 * @returns
 */
function backToStep3() {
	var reqUrl = url + "/topicMaker/backToStepThird.do";
	$.post(reqUrl, {
		voteIDs : $("#voteIDs").val(),
		topicID : $("#topicID").val()
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#step4").addClass("hidden");
			$("#step3").removeClass("hidden");
			$("#li3").addClass("active");
			$("#li4").removeClass("active");
		} else if (result.Code == '11') {
			return;
		}
	});
}

/**
 * 获取系统当前时间
 * @returns
 */
function getCurrentTime() {
	var date = new Date();
	time = time + date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes();
}

/**
 * 密码重置时的原始密码校验
 * @returns
 */
function CheckPassword(obj) {
	if ($("#oldPassword").val() == "") {
		$("#oldPassword").parent().addClass("has-error has-feedback").children().
		attr('data-toggle', 'tooltip').attr('data-placement', 'right').attr('title', '原密码不能为空').
		after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'>" +
		"</span><span class='sr-only'>(error)</span>");
	} else {
		reqUrl = url + "/userOperate/checkPasswordForResetIt.do";
		$.post(reqUrl, {
			userKey : $("#oldPassword").val()
		}, function(result) {
			var result = eval('(' + result + ')');
			if (result.Code == '00') {
				$("#checkResult").val(result.retMsg);
				$("#oldPassword").removeAttr('title').removeAttr('data-placement').removeAttr('data-toggle').
				parent().removeClass("has-error has-feedback");
				$("#oldPassword").siblings().remove();
				$("#resetAlert").addClass("hidden");
				$("#resetBtn").removeAttr("disabled");
			} else if (result.Code == '11') {
				$("#oldPassword").parent().addClass("has-error has-feedback").children().
				attr('data-toggle', 'tooltip').attr('data-placement', 'right').attr('title', '原密码错误').
				after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'>" +
				"</span><span class='sr-only'>(error)</span>");
				$("#checkResult").val("false");
				$("#resetBtn").attr("disabled", "disabled");
			}
		});
	}
}

/**
 * 重置密码
 * @returns
 */
function resertPassword() {
	if (!($("#checkResult").val())) {
		$("#resetAlert").removeClass("hidden").find("p").text("旧密码未通过校验，请输入正确的密码！");
		return;
	}
	var newA = $("#newPassword").val();
	var newB = $("#repeat").val();
	
	if ($("#oldPassword").val() == newA) {
		$("#resetAlert").removeClass("hidden").find("p").text("新密码不能与旧密码相同，请重新输入...");
		return;
	}
	
	if (newA != newB) {
		$("#resetAlert").removeClass("hidden").find("p").text("两次输入的密码不一致，请检查！");
		return;
	}
	
	reqUrl = url + "/userOperate/resetPassword.do";
	$.post(reqUrl, {
		userKey : $("#newPassword").val(),
		oldKey : $("#oldPassword").val()
	}, function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$('#sucMsg').text(result.retMsg);
			$('#successModel').modal('show');
			setTimeout(function(){window.location.href=url+result.nextStep;},3000);
		} else if (result.Code == '11') {
			$("#resetAlert").removeClass("hidden").find("p").text(result.retMsg);
		}
	});
}
/**
 * 打开头像编辑弹出窗
 * @returns
 */
function openUserImgEdit() {
	$('#upUserImg').modal('show');
}

/**设置头像上传组件
 * 
 */
$(window).load(function() {
	var options =
	{
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: '../image/user/default-image.png'
	}
	var cropper = $('.imageBox').cropbox(options);
	$('#upload-file').on('change', function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	})
	$('#btnCrop').on('click', function(){
		var img = cropper.getDataURL();
		$('.cropped').html('');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
	})
	$('#btnZoomIn').on('click', function(){
		cropper.zoomIn();
	})
	$('#btnZoomOut').on('click', function(){
		cropper.zoomOut();
	})
});

/**
 * 提交剪切后的头像
 */
function changImage() {
	var imgCode = $(".cropped img").attr('src');
	$.post(url+"/userDetail/changeUserImg.do", {
		imgCode : imgCode
	}, function (result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			setTimeout(function(){window.location.href=url+'/index.jsp';},3000);
		} else if (result.Code == '11') {
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 开启投票
 * @returns
 */
function stopTopic(topicID) {
	$.post(url+"/topicDetail/publishTopic.do", {
		topicID : topicID
	},function(result) {
		var result = eval('(' + result + ')');
		if (result.Code == '00') {
			$("#sucMsg").text("操作成功");
			$('#successModel').modal('show');
			setTimeout(function(){window.location.href=url+'/topicDetail/getTopicDetail.do?topicID='+topicID;},3000);
		} else if (result.Code == '11'){
			$("#errMsg").text(result.retMsg);
			$('#alertModel').modal('show');
		}
	});
}

/**
 * 结束投票并下载投票结果
 * @returns
 */
function stopAndExport(topicID) {
	window.location.href=url+'/topicMaker/stopAndExportTopic.do?topicID='+topicID;
}