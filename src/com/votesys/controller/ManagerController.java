package com.votesys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.votesys.bean.CommentBean;
import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.UserInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.service.operate.inter.IOperCommentSV;
import com.votesys.service.operate.inter.IOperTopicInfoSV;
import com.votesys.service.operate.inter.IOperUserInfoSV;
import com.votesys.service.query.inter.IQueryCommentSV;
import com.votesys.service.query.inter.IQueryTopicInfoSV;
import com.votesys.service.query.inter.IQueryUserInfoSV;
import com.votesys.tools.PageUtil;
import com.votesys.tools.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private IQueryUserInfoSV queryUserInfoSV;
	@Autowired
	private IQueryTopicInfoSV queryTopicInfoSV;
	@Autowired
	private IQueryCommentSV queryCommentSV;
	@Autowired
	private IOperUserInfoSV operUserInfoSV;
	@Autowired
	private IOperTopicInfoSV operTopicInfoSV;
	@Autowired
	private IOperCommentSV operCommentSV;

	public List<UserInfoBean> getUserList(PageBean pageInfo, UserInfoBean userInfo) {
		List<UserInfoBean> list = queryUserInfoSV.queryUserInfo(pageInfo, userInfo);
		
		return list;
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::initManagerPage
	 * @Description 初始化管理员页面
	 * @return
	 */
	@RequestMapping("/initManagerPage")
	public ModelAndView initManagerPage(HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		PageBean pageInfo = new PageBean(1, 3);
		List<UserInfoBean> userList = getUserList(pageInfo, new UserInfoBean());
		
		int Tote = queryUserInfoSV.queryUserInfoTote(new UserInfoBean());
		
		String pageCode = PageUtil.getPagation(request.getContextPath()+"/manager/userGetNextPage.do", Tote, 1, 3);
		mAndView.addObject("selected", "U");
		mAndView.addObject("pageCode",pageCode);
		mAndView.addObject("userList", userList);
		mAndView.addObject("currentPage", "/manager-model/user/Table.jsp");
		mAndView.setViewName("manager-model/managerPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::userGetNextPage
	 * @Description 获取下一页用户信息
	 * @param page
	 * @param userInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/userGetNextPage")
	public ModelAndView userGetNextPage(@RequestParam("page") String page, UserInfoBean userInfo, HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		PageBean pageInfo = new PageBean(Integer.valueOf(page), 3);
		List<UserInfoBean> userList = getUserList(pageInfo, userInfo);
		
		int Tote = queryUserInfoSV.queryUserInfoTote(userInfo);
		
		String pageCode = PageUtil.getPagation(request.getContextPath()+"/manager/userGetNextPage.do", Tote, Integer.valueOf(page), 3);
		mAndView.addObject("selected", "U");
		mAndView.addObject("pageCode",pageCode);
		mAndView.addObject("userList", userList);
		mAndView.addObject("currentPage", "/manager-model/user/Table.jsp");
		mAndView.setViewName("manager-model/managerPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::userStatusChange
	 * @Description 修改用户登录状态
	 * @param userID
	 * @param userStatus
	 * @param httpServletResponse
	 * @throws Exception 
	 */
	@RequestMapping("/userStatusChange")
	public void userStatusChange(@RequestParam("userID") String userID, @RequestParam("userStatus") String userStatus, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		boolean ret = operUserInfoSV.updateUserStatus(userID, userStatus);
		
		if (ret) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "操作成功...");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "操作失败...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::topicGetNextPage
	 * @Description 获取主题下一页
	 * @param page
	 * @param topicInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/topicGetNextPage")
	public ModelAndView topicGetNextPage(@RequestParam("page") String page, TopicInfoBean topicInfo, HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		PageBean pageInfo = new PageBean(Integer.valueOf(page), 3);
		List<TopicInfoBean> topicList = queryTopicInfoSV.queryTopicInfoForManager(pageInfo, topicInfo);
		
		int Tote = queryTopicInfoSV.queryTopicInfoForManagerTote(topicInfo);
		
		String pageCode = PageUtil.getPagation(request.getContextPath()+"/manager/topicGetNextPage.do", Tote, Integer.valueOf(page), 3);
		mAndView.addObject("selected", "T");
		mAndView.addObject("pageCode",pageCode);
		mAndView.addObject("topicList", topicList);
		mAndView.addObject("currentPage", "/manager-model/topic/Table.jsp");
		mAndView.setViewName("manager-model/managerPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::topicStatusChange
	 * @Description 修改主题状态
	 * @param topicID
	 * @param topicStatus
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/topicStatusChange")
	public void topicStatusChange(@RequestParam("topicID") String topicID, @RequestParam("topicStatus") String topicStatus, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		boolean ret = false;
		int flag = 0;
		if ("U".equals(topicStatus)) {
			ret = operTopicInfoSV.updateTopicInfoSetStatusS(topicID, topicStatus);
		} else {
			flag = operTopicInfoSV.updateTopicStatusToU(topicID);
		}
		
		if (ret || flag > 0) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "操作成功...");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "操作失败...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::comGetNextPage
	 * @Description 获取下一页评论信息
	 * @param page
	 * @param comment
	 * @param request
	 * @return
	 */
	@RequestMapping("/comGetNextPage")
	public ModelAndView comGetNextPage(@RequestParam("page") String page, CommentBean comment, HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		PageBean pageInfo = new PageBean(Integer.valueOf(page), 3);
		List<CommentBean> comList = queryCommentSV.queryComment(pageInfo);
		
		int Tote = queryCommentSV.queryCommentTote();
		
		String pageCode = PageUtil.getPagation(request.getContextPath()+"/manager/comGetNextPage.do", Tote, Integer.valueOf(page), 3);
		mAndView.addObject("selected", "R");
		mAndView.addObject("pageCode",pageCode);
		mAndView.addObject("comList", comList);
		mAndView.addObject("currentPage", "/manager-model/comment/Table.jsp");
		mAndView.setViewName("manager-model/managerPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.ManagerController::comStatusChange
	 * @Description 替换评论内容
	 * @param page
	 * @param comment
	 * @param response
	 * @throws Exception 
	 */
	public void comStatusChange(@RequestParam("comID") String comID, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		int ret = operCommentSV.updateCommentContent(comID);
		
		if (ret > 0) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "操作成功...");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "操作失败...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
}
