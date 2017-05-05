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

import com.votesys.bean.CommentBean;
import com.votesys.bean.PageBean;
import com.votesys.bean.TopicExtInfoBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.VoteInfoBean;
import com.votesys.bean.VoteLaunchBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.qbo.bean.TrineUTCBean;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.service.operate.inter.IOperCommentSV;
import com.votesys.service.operate.inter.IOperRelationSV;
import com.votesys.service.operate.inter.IOperTopicInfoSV;
import com.votesys.service.query.inter.IQueryCommentSV;
import com.votesys.service.query.inter.IQueryConjunctiveSV;
import com.votesys.service.query.inter.IQueryRelationSV;
import com.votesys.service.query.inter.IQueryTopicInfoExtSV;
import com.votesys.service.query.inter.IQueryTopicInfoSV;
import com.votesys.service.query.inter.IQueryUserInfoAndExtSV;
import com.votesys.service.query.inter.IQueryVoteInfoSV;
import com.votesys.tools.PageUtil;
import com.votesys.tools.ResponseUtil;
import com.votesys.tools.StringUtils;

import net.sf.json.JSONObject;

/**
 * @ClassName com.votesys.controller.TopicDetailController.java
 * @Description 主题详细信息控制层
 */
@Controller
@RequestMapping("/topicDetail")
public class TopicDetailController {

	@Autowired
	private IQueryTopicInfoSV queryTopicInfoSV;
	@Autowired 
	private IQueryTopicInfoExtSV queryTopicInfoExtSV;
	@Autowired
	private IQueryVoteInfoSV queryVoteInfoSV;
	@Autowired
	private IQueryRelationSV queryRelationSV;
	@Autowired
	private IQueryUserInfoAndExtSV queryUserInfoAndExtSV;
	@Autowired
	private IQueryConjunctiveSV queryConjuncitiveSV;
	@Autowired
	private IQueryCommentSV queryCommentSV;
	@Autowired
	private IOperCommentSV operCommentSV;
	@Autowired
	private IOperRelationSV operRelationSV;
	@Autowired
	private IOperTopicInfoSV operTopicInfoSV;
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::initTopicDetail
	 * @Description 初始化主题详细信息
	 * @param topicID
	 * @param request
	 * @return
	 */
	public ModelAndView initTopicDetail(String topicID, HttpServletRequest request) {
		int voteTote = 0;
		PageBean pageInfo = new PageBean(1, 5);
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		TopicInfoBean topicInfo = new TopicInfoBean();
		VoteLaunchBean voteLaunch = new VoteLaunchBean();
		List<VoteInfoBean> voteList = new ArrayList<VoteInfoBean>();
		List<TrineUTCBean> trineUTCList = new ArrayList<TrineUTCBean>();
		List<TopicExtInfoBean> topicExtList = new ArrayList<TopicExtInfoBean>();
		boolean retCode = false;
		
		topicInfo = queryTopicInfoSV.queryTopicInfoByTopicID(topicID);
		if (topicInfo != null) {
			retCode = true;
		}
		if (retCode) {
			topicExtList = queryTopicInfoExtSV.queryImageByTopicID(topicID);
		}
		if (retCode) {
			voteList = queryVoteInfoSV.queryVoteInfoByRemarks(topicID);
			if (voteList.size() < 0 || voteList == null) {
				retCode = false;
			} else {
				retCode = true;
			}
		}
		if (retCode) {
			voteLaunch = queryVoteInfoSV.queryVoteLaunchByTopicID(topicID);
		}
		//绘制主题页面
		if (voteLaunch != null) {
			for (VoteInfoBean voteInfo : voteList) {
				int num = queryRelationSV.qryVoteOpitonGetCount(voteInfo.getVoteID());
				voteInfo.setRemark(String.valueOf(num));
				voteTote += num;
			}
			for (VoteInfoBean voteInfo : voteList) {
				float num = Float.parseFloat(voteInfo.getRemark());
				float rate = num/voteTote*100;
				voteInfo.setRemark(String.valueOf(rate));
				StringBuffer voteCode = new StringBuffer();
				if ("C".equals(voteLaunch.getVoteStyle())) {
					voteCode.append(VoteSysConstant.checkBox).append(voteInfo.getVoteID()+","+voteInfo.getVoteCode()).append("'>");
				} else {
					voteCode.append(VoteSysConstant.radioBtn).append(voteInfo.getVoteID()+","+voteInfo.getVoteCode()).append("'>");
				}
				voteInfo.setVoteCode(voteCode.toString());
				voteInfo.setVoteOBJ(voteInfo.getVoteOBJ()+"</label></div>");
			}
		}
		//绘制投票界面
		if (retCode) {
			if (session.getAttribute("userSession") != null) {
				String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
				String getUserID = queryRelationSV.qryUTRelFintUserIDByTopicID(topicID);
				UserAllInfoBean userInfo = queryUserInfoAndExtSV.queryUserAllInfo(getUserID);
				mAndView.addObject("author", userInfo);
				if (!getUserID.equals(userID) && !queryRelationSV.qryUserExistUserVote(userID, topicID) && "U".equals(topicInfo.getTopicStatus())) {
					mAndView.addObject("VoteSubmit", PageUtil.setVoteSubmitButton());
				} else if (getUserID.equals(userID) && "U".equals(topicInfo.getTopicStatus())) {
					mAndView.addObject("stopBtn", VoteSysConstant.stopBtn);
				}
			} else {
				String getUserID = queryRelationSV.qryUTRelFintUserIDByTopicID(topicID);
				UserAllInfoBean userInfo = queryUserInfoAndExtSV.queryUserAllInfo(getUserID);
				mAndView.addObject("author", userInfo);
				mAndView.addObject("VoteSubmit", PageUtil.setVoteSubmitButton());
			}
		}
		
		//绘制评论区
		trineUTCList = queryConjuncitiveSV.qryCommentByTopicID(topicID, pageInfo);
		List<String> commentDivList = null;
		if (trineUTCList != null && trineUTCList.size() > 0) {
			commentDivList = PageUtil.setComments(trineUTCList);
			mAndView.addObject("comments", commentDivList);
			if (trineUTCList.size() == pageInfo.getPageSize()) {
				mAndView.addObject("comNextPage", VoteSysConstant.comNextPage);
			} else {
				mAndView.addObject("comNextPage", VoteSysConstant.comNextPage);
			}
		}
		
		session.setAttribute("topicSession", topicInfo);
		if ("S".equals(topicInfo.getTopicStatus())) {
			topicInfo.setTopicTitle("<del>"+topicInfo.getTopicTitle()+"</del>");
		}
		mAndView.addObject("topic", topicInfo);
		mAndView.addObject("Images", topicExtList);
		mAndView.addObject("VoteOptions", voteList); 
		mAndView.setViewName("topic-model/TopicDetail");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::getTopicDetail
	 * @Description 获取主题详细页面
	 * @param topicID
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTopicDetail")
	public ModelAndView getTopicDetail(@RequestParam("topicID") String topicID, HttpServletRequest request) {
		return initTopicDetail(topicID, request);
	}
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::getNextComment
	 * @Description 获取下一页的评论
	 * @param page
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/getNextComment")
	public void getNextComment(@RequestParam("page") String page, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TrineUTCBean> trineUTCList = new ArrayList<TrineUTCBean>();
		HttpSession session = request.getSession();
		PageBean pageInfo = new PageBean(Integer.parseInt(page), 5);
		JSONObject ajaxResult = new JSONObject();
		String topicID = ((TopicInfoBean) session.getAttribute("topicSession")).getTopicID();
		StringBuffer div = new StringBuffer();
		
		trineUTCList = queryConjuncitiveSV.qryCommentByTopicID(topicID, pageInfo);
		List<String> divList = PageUtil.setComments(trineUTCList);
		for (String string : divList) {
			div.append(string);
		}
		
		int tote = queryConjuncitiveSV.qryCommentCountByTopicID(topicID);
		
		if (trineUTCList.size() == 0 || trineUTCList.size() < pageInfo.getPageSize() || tote == (pageInfo.getStart()+trineUTCList.size())) {
			ajaxResult.put(VoteSysConstant.Code, "01");
			ajaxResult.put(VoteSysConstant.Message, div.toString());
		} else if (trineUTCList.size() == pageInfo.getPageSize()) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, div.toString());
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "查询失败...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::comment
	 * @Description 添加评论
	 * @param comment
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/comment")
	public void comment(@RequestParam("comment") String comment, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TrineUTCBean> trineUTCList = new ArrayList<TrineUTCBean>();
		HttpSession session = request.getSession();
		boolean ret = false;
		JSONObject ajaxResult = new JSONObject();
		if (session.getAttribute("userSession") == null) {
			ajaxResult.put(VoteSysConstant.Code, "01");
			ajaxResult.put(VoteSysConstant.Message, "请先登录...");
		} else {
			String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
			String topicID = ((TopicInfoBean) session.getAttribute("topicSession")).getTopicID();
			String comID = operCommentSV.insertComment(comment);
			if (StringUtils.isNotEmpty(comID)) {
				ret = operRelationSV.insertUserComRelate(userID, comID);
				if (ret) {
					ret = operRelationSV.insertTopicComRelate(topicID, comID);
				}
			}
			
			if (ret) {
				trineUTCList = queryConjuncitiveSV.qryCommentByComID(comID);
				String div = PageUtil.setComments(trineUTCList).get(0);
				ajaxResult.put(VoteSysConstant.Code, "00");
				ajaxResult.put(VoteSysConstant.Message, "操作成功");
				ajaxResult.put("comment", div);
			} else {
				ajaxResult.put(VoteSysConstant.Code, "11");
				ajaxResult.put(VoteSysConstant.Message, "保存评论失败，请重试..");
			}
		}
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::vote
	 * @Description 投票
	 * @param radio
	 * @param checkBox
	 * @param request
	 * @return
	 */
	@RequestMapping("/vote")
	public ModelAndView vote(@RequestParam(value="radioBtn", required=false) String radio,
			@RequestParam(value="checkBoxBtn[]", required=false) String[] checkBox, HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		String topicID = ((TopicInfoBean) session.getAttribute("topicSession")).getTopicID();
		boolean ret = false;
		if (StringUtils.isNotBlank(radio)) {
			String[] vote = new String[] {radio};
			ret = operRelationSV.insertUserVoteRelate(userID, topicID, vote);
		}
		if (checkBox != null && checkBox.length > 0) {
			ret = operRelationSV.insertUserVoteRelate(userID, topicID, checkBox);
		}
		
		if (ret) {
			return initTopicDetail(topicID, request);
		}
		mAndView.setViewName("index");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::setOpposerOrEndorse
	 * @Description 踩或赞
	 * @param comID
	 * @param status
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/setOpposerOrEndorse")
	public void setOpposerOrEndorse(@RequestParam("comID") String comID, @RequestParam("status") String status, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentBean comment = new CommentBean();
		JSONObject ajaxResult = new JSONObject();
		boolean ret = false;
		ret = operCommentSV.upDateSubOrUpNum(comID, status);
		
		if (ret) {
			comment = queryCommentSV.queryUpAndSubNum(comID);
		}
		
		if (comment != null) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put("UP", comment.getUpNum());
			ajaxResult.put("SUB", comment.getSubNum());
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "操作失败，请重试...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.TopicDetailController::stopTopic
	 * @Description 结束投票操作
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("stopTopic")
	public void stopTopic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		HttpSession session = request.getSession();
		String topicID = ((TopicInfoBean) session.getAttribute("topicSession")).getTopicID();
		
		boolean ret = operTopicInfoSV.updateTopicInfoSetStatusS(topicID, "S");
		
		if (ret) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "操作成功，稍后会自动跳转...");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "操作失败，请重试...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
}
