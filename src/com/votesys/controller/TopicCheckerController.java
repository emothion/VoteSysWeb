package com.votesys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.service.query.inter.IQueryConjunctiveSV;
import com.votesys.service.query.inter.IQueryUserInfoAndExtSV;
import com.votesys.tools.PageUtil;
import com.votesys.tools.StringUtils;

@Controller
@RequestMapping("/topicChecker")
public class TopicCheckerController {

	@Autowired
	private IQueryConjunctiveSV queryConjunctiveSV;
	@Autowired
	private IQueryUserInfoAndExtSV queryUserInfoAndExtSV;
	
	@RequestMapping("/getTopicInfoList")
	public ModelAndView getTopicInfoList(@RequestParam(value="page", required=false) String page, 
			TopicInfoBean topicInfo, HttpServletRequest request) {
		int tote = 0;
		PageBean pageInfo = null;
		ModelAndView mAndView = new ModelAndView();
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		String userID = ((UserAllInfoBean) request.getSession().getAttribute("userSession")).getUserID();
		if (topicInfo == null) {
			topicInfo = (TopicInfoBean) request.getSession().getAttribute("topicSession");
		}
		String topicTitle = topicInfo.getTopicTitle();
		if (StringUtils.isNotEmpty(topicTitle)) {
			topicInfo.setTopicTitle("%"+topicTitle+"%");
		}
		
		tote = queryConjunctiveSV.qryTopicInfoCount(userID, topicInfo.getTopicStatus(), topicInfo.getTopicTitle());
		
		if (StringUtils.isEmpty(page)) {
			page = "1";
		}
		pageInfo = new PageBean(Integer.parseInt(page), 5);
		
		if (tote > 0) {
			topicList = queryConjunctiveSV.qryTopicInfoWithUserIDByCondition(pageInfo, topicInfo, userID);
		}
		
		String pageCode = PageUtil.getPagation(request.getContextPath()+"/topicChecker/getTopicInfoList.do", tote, Integer.parseInt(page), 5);
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		
		topicInfo.setTopicTitle(topicTitle);
		request.getSession().setAttribute("topicSession", topicInfo);
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("topics", topicList);
		mAndView.addObject("pageCode", pageCode);
		mAndView.addObject("userInfoPageUnitPath", "TopicChecker.jsp");
		mAndView.addObject("flag", "4");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
}
