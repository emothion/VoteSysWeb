package com.votesys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicExtInfoBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.UserInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.service.query.inter.IQueryRelationSV;
import com.votesys.service.query.inter.IQueryTopicInfoExtSV;
import com.votesys.service.query.inter.IQueryTopicInfoSV;
import com.votesys.service.query.inter.IQueryUserInfoSV;
import com.votesys.tools.PageUtil;
import com.votesys.tools.ResponseUtil;
import com.votesys.tools.StringUtils;

import net.sf.json.JSONObject;

/**
 * @ClassName com.votesys.controller.IndexController.java
 * @Description 主页初始化
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private IQueryTopicInfoSV queryTopicInfoSV;
	@Autowired
	private IQueryTopicInfoExtSV queryTopicInfoExtSV;
	@Autowired
	private IQueryUserInfoSV queryUserInfoSV;
	@Autowired
	private IQueryRelationSV queryRelationSV;
	
	@RequestMapping("/getTopic")
	public ModelAndView getTopic(TopicInfoBean topicCondition, HttpServletRequest request){//TopicInfoBean是从form表单匹配name，然后把值赋给Bean中的值，然后再赋给topicCondition
		ModelAndView mAndView=new ModelAndView();//声明返回对象
		HttpSession session = request.getSession();//声明Session对象：Page， Session， Application
		PageBean pageInfo = new PageBean(1, 5);//构造分页数据
		List<String> topicDivList = new ArrayList<String>();//声明一个装页面模块的对象
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();//53,54声明数据库查询结果的对象。声明装查询数据结果的“容器”
		
		if (topicCondition==null) {
			topicList = queryTopicInfoSV.queryTopicInfo(pageInfo, new TopicInfoBean());//new一个主题信息的对象
		} else {
			topicList = queryTopicInfoSV.queryTopicInfo(pageInfo, topicCondition);
		}
		
		String url = request.getContextPath() + "/topicDetail/getTopicDetail.do?topicID=";//构造一个绝对路径，使每一个模块链接到它的详细页。getContextPath是工程名
		
		if (topicList != null && topicList.size() > 0) {
			for (TopicInfoBean topicInfo : topicList) {
				TopicExtInfoBean topicExtInfo = queryTopicInfoExtSV.queryMainImageByTopicID(topicInfo.getTopicID());//取出这个主题的orderimg为1的图片数据
				String userID = queryRelationSV.qryUTRelFindUserIDByTopicID(topicInfo.getTopicID());
				UserInfoBean userInfo = queryUserInfoSV.queryUserInfoByUserID(userID);
				String div = PageUtil.getTopicBlock(topicInfo, userInfo.getUserName(), topicExtInfo.getTopicImg(), url);
				topicDivList.add(div);
			}
		}
		
		session.setAttribute("topicSession", topicCondition);
		mAndView.addObject("divs", topicDivList);
		mAndView.setViewName("LoadIndex");
		return mAndView;
	}
	
	/**
	 * @throws Exception 
	 * @Function com.votesys.controller.IndexController::ajaxGetTopic
	 * @Description ajax获取后续主题
	 */
	@RequestMapping("/ajaxGetTopic")
	public void ajaxGetTopic(@RequestParam("page") String page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();//声明一个JSON对象用于返回数据
		HttpSession session = request.getSession();
		StringBuffer div = new StringBuffer();
		PageBean pageInfo = new PageBean(Integer.parseInt(page), 5);
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		TopicInfoBean topicCondition = (TopicInfoBean) session.getAttribute("topicSession");
		int tote = queryTopicInfoSV.queryTopicInfoTote(topicCondition);//查询记录总数（有无条件）
		if ((Integer.parseInt(page)-1)*pageInfo.getPageSize()<tote) {
			if (topicCondition == null) {
				topicList = queryTopicInfoSV.queryTopicInfo(pageInfo, new TopicInfoBean());
			} else {
				topicList = queryTopicInfoSV.queryTopicInfo(pageInfo, topicCondition);
			}
			
			String url = request.getContextPath() + "/topicDetail/getTopicDetail.do?topicID=";
			
			if (!topicList.isEmpty()) {
				for (TopicInfoBean topicInfo : topicList) {
					TopicExtInfoBean topicExtInfo = queryTopicInfoExtSV.queryMainImageByTopicID(topicInfo.getTopicID());
					String userID = queryRelationSV.qryUTRelFindUserIDByTopicID(topicInfo.getTopicID());
					UserInfoBean userInfo = queryUserInfoSV.queryUserInfoByUserID(userID);
					div.append(PageUtil.getTopicBlock(topicInfo, userInfo.getUserName(), topicExtInfo.getTopicImg(), url));
				}
			}
			
			if (StringUtils.isNotBlank(div.toString())) {
				ajaxResult.put(VoteSysConstant.Code, "00");
				ajaxResult.put("div", div.toString());
			} else {
				ajaxResult.put(VoteSysConstant.Code, "11");
				ajaxResult.put(VoteSysConstant.Message, "加载失败...");
			}
		} else {
			ajaxResult.put(VoteSysConstant.Code, "01");
			ajaxResult.put(VoteSysConstant.Message, "没有更多了...");
		}
		ResponseUtil.write(ajaxResult, response);
	}
}
