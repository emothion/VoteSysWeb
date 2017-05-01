function insertcode(result) {
	var $body = $("#xmlGetTopic");
	$body.append(result.div);
	$("#page_tag_load").addClass("hidden");
}

$(document).ready(function() {

	$(window).scroll(function() {
		var $body = $("body");
		/*
		 * var $html = ""; $html += "<br/>" + ($(window).height() +
		 * $(window).scrollTop()); $html += "<br/>window.height: " +
		 * $(window).height(); $html += "<br/>body.height: " + $body.height();
		 * $html += "<br/>window.scrollTop: " + $(window).scrollTop();
		 * $("#page_tag_bottom").html($html);
		 */

		/* 判断窗体高度与竖向滚动位移大小相加 是否 超过内容页高度 */
		if (($(window).height() + $(window).scrollTop()) >= $body.height()) {
			if (flag) {
				page++;
				$("#page_tag_load").removeClass("hidden");
				setTimeout("xmlHttpGetTopic(page)",2000)//延迟2秒钟插入html
			}
		}
	});
});

