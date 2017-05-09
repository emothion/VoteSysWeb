package com.votesys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.UserInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.service.operate.inter.IOperUserInfoDtlSV;
import com.votesys.service.operate.inter.IOperUserInfoSV;
import com.votesys.service.query.inter.IQueryConjunctiveSV;
import com.votesys.service.query.inter.IQueryUserInfoAndExtSV;
import com.votesys.service.query.inter.IQueryUserInfoSV;
import com.votesys.tools.PageUtil;
import com.votesys.tools.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName com.votesys.controller.UserOperateController.java
 * @Description 用户登录注册注销等基本信息操作控制层
 */
@Controller
@RequestMapping("/userOperate")
public class UserOperateController {

	@Autowired
	private IOperUserInfoSV operUserInfoSV;
	@Autowired
	private IQueryUserInfoSV queryUserInfoSV;
	@Autowired
	private IQueryConjunctiveSV queryConjunctiveSV;
	@Autowired
	private IOperUserInfoDtlSV operUserInfoDtlSV;
	@Autowired
	private IQueryUserInfoAndExtSV queryUserInfoAndExtSV;

	/**
	 * @Function com.votesys.controller.UserOperateController::userLogon
	 * @Description 用户登录
	 * @param userInfo
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/userLogon")
	public void userLogon(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "userKey") String userKey, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserInfoBean userInfo = new UserInfoBean();
		UserInfoBean resultUser = new UserInfoBean();
		HttpSession session = request.getSession();
		JSONObject ajaxResult = new JSONObject();
		if (userName.contains("@") && userName.contains(".com")) {
			userInfo.setUserEmail(userName);
			userInfo.setUserKey(userKey);
			resultUser = queryUserInfoSV.queryUserInfoByEmailAndKey(userInfo);
		} else if (Pattern.matches("^[0-9]{11}$", userName)) {
			userInfo.setUserPhone(userName);
			userInfo.setUserKey(userKey);
			resultUser = queryUserInfoSV.queryUserInfoByPhoneAndKey(userInfo);
		} else {
			userInfo.setUserName(userName);
			userInfo.setUserKey(userKey);
			resultUser = queryUserInfoSV.queryUserInfoByNameAndKey(userInfo);
		}

		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(resultUser.getUserID());
		
		if (userAllInfo == null) {
			ajaxResult.put(VoteSysConstant.Code, "01");
			ajaxResult.put(VoteSysConstant.Message, "用户名或密码错误！");
		} else if (!"U".equals(resultUser.getStatus())) {
			if ("M".equals(resultUser.getStatus())) {
				ajaxResult.put(VoteSysConstant.Code, "11");
				ajaxResult.put(VoteSysConstant.Message, "管理员账户");
				session.setAttribute("manager", userAllInfo);
				ResponseUtil.write(ajaxResult, response);
			}
			ajaxResult.put(VoteSysConstant.Code, "10");
			ajaxResult.put(VoteSysConstant.Message, resultUser.getRetInfo());
		}  else {
			session.setAttribute("userSession", userAllInfo);
			ajaxResult.put(VoteSysConstant.Code, "00");
		}
		ResponseUtil.write(ajaxResult, response);
	}

	/**
	 * @Function com.votesys.controller.UserOperateController::userLogin
	 * @Description 用户注册
	 * @param userInfo
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/userLogin")
	public void userLogin(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "userEmail") String userEmail, HttpServletResponse response,
			@RequestParam(value = "userPhone") String userPhone, HttpServletRequest request,
			@RequestParam(value = "userKey") String userKey) throws Exception {
		UserInfoBean userInfo = new UserInfoBean();
		JSONObject ajaxResult = new JSONObject();
		userInfo.setUserName(userName);
		userInfo.setUserEmail(userEmail);
		userInfo.setUserPhone(userPhone);
		userInfo.setUserKey(userKey);
		
		boolean retInfo = operUserInfoSV.insertUserInfo(userInfo);
		UserInfoBean resultUser = queryUserInfoSV.queryUserInfoByNameAndKey(userInfo);
		retInfo = operUserInfoDtlSV.insertUserInfoDtl(resultUser.getUserID());
		
		if (retInfo) {
			UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(resultUser.getUserID());
			ajaxResult.put(VoteSysConstant.Code, "00");
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userAllInfo);
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "注册失败，请联系管理员");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}

	/**
	 * @Function com.votesys.controller.UserOperateController::userLogout
	 * @Description 用户登出
	 * @param session
	 */
	@RequestMapping("/userLogout")
	public String userLogout(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::toUserInfoPage
	 * @Description 跳转到用户信息页面并初始化页面数据
	 * @param request
	 */
	@RequestMapping("/toUserInfoPage")
	public ModelAndView toUserInfoPage(HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		userAllInfo.setUserSexy("00".equals(userAllInfo.getUserSexy()) ? "女":"男");
		
		session.removeAttribute("topicSession");
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "UserInfoDtlList.jsp");
		mAndView.addObject("flag", "1");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
	
	/**
	 * @throws Exception 
	 * @Function com.votesys.controller.UserOperateController::openUserInfoListPage
	 * @Description 初始化用户信息列表页面
	 */
	@RequestMapping("/openUserInfoListPage")
	public ModelAndView openUserInfoListPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		userAllInfo.setUserSexy("00".equals(userAllInfo.getUserSexy()) ? "女":"男");
		
		session.removeAttribute("topicSession");
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "UserInfoDtlList.jsp");
		mAndView.addObject("flag", "1");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::openUserInfoFormPage
	 * @Description 打开用户信息编辑页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/openUserInfoFormPage")
	public ModelAndView openUserInfoFormPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		
		session.removeAttribute("topicSession");
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "UserInfoDtlForm.jsp");
		mAndView.addObject("flag", "2");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::openPublishTopicPage
	 * @Description 打开主题发布页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/openPublishTopicPage")
	public ModelAndView openPublishTopicPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		
		session.removeAttribute("topicSession");
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "TopicMakerPage.jsp");
		mAndView.addObject("flag", "3");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::openTopicListPage
	 * @Description 打开经该用户发布的所有主题页面
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/openTopicListPage")
	public ModelAndView openTopicListPage(HttpServletRequest request) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		int tote = 0;
		PageBean pageinfo = new PageBean(1, 5);
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		topicList = queryConjunctiveSV.qryTopicInfoByUserID(pageinfo, userID);
		if (!topicList.isEmpty()) {
			tote = queryConjunctiveSV.qryTopicInfoCount(userID, null, null);
		}
		
		String pageCode = PageUtil.getPagation(request.getContextPath()+"/topicChecker/getTopicInfoList.do", tote, 1, 5);
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		
		session.removeAttribute("topicSession");
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("topics", topicList);
		mAndView.addObject("pageCode", pageCode);
		mAndView.addObject("userInfoPageUnitPath", "TopicChecker.jsp");
		mAndView.addObject("flag", "4");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::openResetKeyPage
	 * @Description 打开重置密码页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/openResetKeyPage")
	public ModelAndView openResetKeyPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserAllInfoBean userAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		
		session.removeAttribute("topicSession");
		mAndView.addObject("userAllInfo", userAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "PasswordResetPage.jsp");
		mAndView.addObject("flag", "5");
		mAndView.setViewName("user-model/UserInfoPage");
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::checkPasswordForResetIt
	 * @Description 为重置密码校验原密码是否正确
	 * @param userKey
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/checkPasswordForResetIt")
	public void checkPasswordForResetIt(@RequestParam("userKey") String userKey, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		HttpSession session = request.getSession();
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserInfoBean userInfo = queryUserInfoSV.queryUserInfoByUserID(userID);
		
		if (userInfo.getUserKey().equals(userKey)) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "true");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "密码错误请重新输入");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.UserOperateController::resetPassword
	 * @Description 重置密码
	 * @param userKey
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/resetPassword")
	public void resetPassword(@RequestParam("userKey") String userKey, @RequestParam("oldKey") String oldKey, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		HttpSession session = request.getSession();
		boolean retCode = false;
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		
		UserInfoBean userInfo = queryUserInfoSV.queryUserInfoByUserID(userID);
		
		if (userInfo.getUserKey().equals(oldKey)) {
			retCode = operUserInfoSV.updateUserInfoPassword(userID, userKey);
		}
		

		if (retCode) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "密码重置成功，稍后请重新登录...");
			ajaxResult.put("nextStep", "/userOperate/userLogout.do");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "原始密码不正确，你个狗逼玩意儿居然改HTML");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
}
