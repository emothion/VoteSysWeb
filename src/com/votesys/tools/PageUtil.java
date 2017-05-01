package com.votesys.tools;

import com.votesys.bean.TopicInfoBean;

/**
 * 页面工具类
 * 
 * @author Administrator
 *
 */
public class PageUtil {

	/**
	 * @Function com.votesys.tools.PageUtil::getPagation
	 * @Description 获取分页代码
	 * @param targetUrl 目标地址
	 * @param totalNum 总记录数
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @return
	 */
	public static String getPagation(String targetUrl, int totalNum, int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "<font color=red>暂无数据！</font>";
		}
		StringBuffer pageCode = new StringBuffer();
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>首页</a></li>");	
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		} else {
			pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "'>上一页</a></li>");
			pageCode.append("<li><a href='" + targetUrl + "?page=1'>首页</a></li>");	
		}

		for (int i = currentPage - 2; i <= currentPage + 2; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?page=" + i + "'>" + i + "</a></li>");
			}

		}

		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
			pageCode.append("<li class='disabled'><a href='#'>末页</a></li>");
		} else {
			pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "'>下一页</a></li>");
			pageCode.append("<li><a href='" + targetUrl + "?page=" + totalPage + "'>末页</a></li>");
		}
		return pageCode.toString();
	}
	
	/**
	 * @Function com.votesys.tools.PageUtil::getTopicBlock
	 * @Description 在首页绘制一个主题框
	 * @param topicInfo
	 * @param userName
	 * @param mainImg
	 * @return
	 */
	public static String getTopicBlock(TopicInfoBean topicInfo, String userName, String mainImg, String targetUrl) {
		StringBuffer div = new StringBuffer("<div class='panel panel-default'>");
		div.append("<div class='panel-body'>").append("<div class='row'>");
		div.append("<div class='col-sm-6 col-md-4' style='width: 200px;'>");
		div.append("<a href='").append(targetUrl).append("' class='thumbnail' style='margin: 0px;'>");
		div.append("<img src='../image/topic/").append(mainImg).append("' alt='主图'>");
		div.append("</a></div><div class='col-sm-6 col-md-8'>");
		div.append("<div class='page-header' style='margin: 0px 0px 10px 0px;'>");
		div.append("<h1 style='margin: 0px;'>").append(topicInfo.getTopicTitle());
		div.append("<small>").append(userName).append("</small></h1>");
		div.append("</div><p>").append(topicInfo.getTopicContent()).append("</p></div></div></div></div>");
		
		return div.toString();
	}
}
