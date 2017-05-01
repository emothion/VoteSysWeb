package com.votesys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.votesys.bean.UserInfoBean;
import com.votesys.bean.UserInfoDtlBean;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.service.operate.inter.IOperUserInfoDtlSV;
import com.votesys.service.operate.inter.IOperUserInfoSV;
import com.votesys.service.query.inter.IQueryUserInfoAndExtSV;
import com.votesys.service.query.inter.IQueryUserInfoSV;

/**
 * @ClassName com.votesys.controller.UserInfoAndDetailController.java
 * @Description 用户详细信息控制层
 */
@Controller
@RequestMapping("/userDetail")
public class UserInfoAndDetailController {

	@Autowired
	private IOperUserInfoSV operUserInfoSV;
	@Autowired
	private IOperUserInfoDtlSV operUserInfoDtlSV;
	@Autowired
	private IQueryUserInfoSV queryUserInfoSV;
	@Autowired
	private IQueryUserInfoAndExtSV queryUserInfoAndExtSV;
	
	/**
	 * @Function com.votesys.controller.UserInfoAndDetailController::userInfoDetailEdit
	 * @Description 用户完整信息编辑
	 * @param userAllInfo
	 */
	@RequestMapping("/userInfoDetailEdit")
	public ModelAndView userInfoDetailEdit(UserAllInfoBean userAllInfo, HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		UserInfoBean userInfo = new UserInfoBean();
		UserInfoDtlBean userInfoDtl = new UserInfoDtlBean();
		UserAllInfoBean getUserAllInfo = new UserAllInfoBean();
		boolean retFlag = false;
		String userID = (String) request.getSession().getAttribute("userID");
		
		userInfo.setUserID(Long.parseLong(userID));
		userInfo.setUserEmail(userAllInfo.getUserEmail());
		userInfo.setUserPhone(userAllInfo.getUserPhone());
		retFlag = operUserInfoSV.updateUserInfo(userInfo);
		
		if (retFlag) {
			userInfoDtl.setUserID(userID);
			userInfoDtl.setUserSexy(userAllInfo.getUserSexy());
			userInfoDtl.setUserBirth(userAllInfo.getUserBirth());
			userInfoDtl.setUserProvince(userAllInfo.getUserProvince());
			userInfoDtl.setUserCity(userAllInfo.getUserCity());
			userInfoDtl.setUserRegion(userAllInfo.getUserRegion());
			userInfoDtl.setUserDesc(userAllInfo.getUserDesc());
			retFlag = operUserInfoDtlSV.updateUserInfoDtl(userInfoDtl);
		}
		
		
		getUserAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);
		getUserAllInfo.setUserSexy("00".equals(userAllInfo.getUserSexy()) ? "女":"男");

		userInfo = queryUserInfoSV.queryUserInfoByUserID(userID);
		request.getSession().invalidate();
		HttpSession session = request.getSession();
		session.setAttribute("userSession", userInfo);
		session.setAttribute("userID", userInfo.getUserID());
		
		mAndView.addObject("userAllInfo", getUserAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "UserInfoDtlForm.jsp");
		mAndView.setViewName("user-model/UserInfoPage");
		
		if (retFlag) {
			mAndView.addObject("flag", "2");
		} else {
			mAndView.addObject("flag", "0");
		}
		return mAndView;
	}
}
