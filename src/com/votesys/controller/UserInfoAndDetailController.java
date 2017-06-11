package com.votesys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.votesys.bean.UserInfoBean;
import com.votesys.bean.UserInfoDtlBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.service.operate.inter.IOperUserInfoDtlSV;
import com.votesys.service.operate.inter.IOperUserInfoSV;
import com.votesys.service.query.inter.IQueryUserInfoAndExtSV;
import com.votesys.service.query.inter.IQueryUserInfoSV;
import com.votesys.tools.Base64DecodeToImageUtils;
import com.votesys.tools.ResponseUtil;
import com.votesys.tools.StringUtils;

import net.sf.json.JSONObject;

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
	@RequestMapping("/userInfoDetailEdit")//spring的自动注入特性
	public ModelAndView userInfoDetailEdit(UserAllInfoBean userAllInfo, HttpServletRequest request) {
		ModelAndView mAndView = new ModelAndView();
		UserInfoBean userInfo = new UserInfoBean();
		UserInfoDtlBean userInfoDtl = new UserInfoDtlBean();
		UserAllInfoBean getUserAllInfo = new UserAllInfoBean();
		boolean retFlag = false;
		String userID = ((UserAllInfoBean) request.getSession().getAttribute("userSession")).getUserID();
		
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
		}//控制事物一致性
		
		
		getUserAllInfo = queryUserInfoAndExtSV.queryUserAllInfo(userID);//之后都是初始化页面的操作
		if (StringUtils.isNotEmpty(getUserAllInfo.getUserSexy())) {
			getUserAllInfo.setUserSexy("00".equals(getUserAllInfo.getUserSexy()) ? "女":"男");
		} else {
			getUserAllInfo.setUserSexy("");
		}

		userInfo = queryUserInfoSV.queryUserInfoByUserID(userID);
		HttpSession session = request.getSession();
		session.setAttribute("userSession", getUserAllInfo);
		session.setAttribute("userID", userInfo.getUserID());
		
		mAndView.addObject("userAllInfo", getUserAllInfo);
		mAndView.addObject("userInfoPageUnitPath", "UserInfoDtlList.jsp");
		mAndView.setViewName("user-model/UserInfoPage");
		
		if (retFlag) {
			mAndView.addObject("flag", "1");
		} else {
			mAndView.addObject("flag", "0");
		}
		return mAndView;
	}
	
	/**
	 * @Function com.votesys.controller.UserInfoAndDetailController::changeUserImg
	 * @Description 解码base64加密的头像后存数据库
	 * @param imgCode 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/changeUserImg")
	public void changeUserImg(@RequestParam("imgCode") String imgCode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		HttpSession session = request.getSession();
		String[] imgInfo = imgCode.split(",");
		String imgSuffix = imgInfo[0].split("/")[1].split(";")[0];
		String path = request.getServletContext().getRealPath("\\");
		StringBuffer imgPath = new StringBuffer(path);
		String userID = ((UserAllInfoBean) session.getAttribute("userSession")).getUserID();
		String userName = ((UserAllInfoBean) session.getAttribute("userSession")).getUserName();
		imgPath.append("\\image\\user\\").append(userName).append(".").append(imgSuffix);
		
		Base64DecodeToImageUtils.GenerateImage(imgInfo[1], imgPath.toString());
		
		boolean ret = operUserInfoDtlSV.updateUserImg(userID, userName+"."+imgSuffix);
		
		if (ret) {
			ajaxResult.put(VoteSysConstant.Code, "00");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "更换头像出错...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
}
