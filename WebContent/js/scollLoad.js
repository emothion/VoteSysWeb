function insertcode(result) {
	var $body = $("#xmlGetTopic");
	$body.append(result.div);
	$("#page_tag_load").hide();
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
			page++;
			$("#page_tag_load").show();
			setTimeout(insertcode, 1000);/* IE 不支持 */
			xmlHttpGetTopic(page);

		}
	});
});

