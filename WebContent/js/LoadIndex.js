function insertcode(result) {
	var $body = $("#xmlGetTopic");
	$body.append(result.div);
	$("#page_tag_load").addClass("hidden");
}

$(document).ready(function() {
	$(window).scroll(function() {
		var $body = $("body");
		/* 判断窗体高度与竖向滚动位移大小相加 是否 超过内容页高度 */
		if (($(window).height() + $(window).scrollTop()) >= $body.height()) {
			if (flag) {
				flag = false;
				page++;
				$("#page_tag_load").removeClass("hidden");
				setTimeout("xmlHttpGetTopic(page)",2000)//延迟2秒钟插入html
			}
		}
	});
});