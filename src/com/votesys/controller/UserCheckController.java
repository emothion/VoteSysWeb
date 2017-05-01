package com.votesys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.votesys.bean.UserInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.service.query.inter.IQueryUserInfoSV;
import com.votesys.tools.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * @ClassName com.votesys.controller.UserCheckController.java
 * @Description 用户校验控制层
 */
@Controller
@RequestMapping("/userCheck")
public class UserCheckController {

	@Autowired
	private IQueryUserInfoSV queryUserInfoSV;
	
	/**
	 * @Function com.votesys.controller.UserCheckController::userNameReSeek
	 * @Description 用户名查重
	 * @param userName
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/userNameReSeek")
	public void userNameReSeek(@RequestParam(value = "userName") String userName, HttpServletResponse response) throws Exception {
		UserInfoBean userInfo = new UserInfoBean();
		userInfo.setUserName(userName);
		JSONObject ajaxResult = new JSONObject();
		
		boolean retReq = queryUserInfoSV.queryUserInfoForUserNameReSeek(userInfo);
		
		ajaxResult.put(VoteSysConstant.Code, "00");
		ajaxResult.put(VoteSysConstant.Message, retReq);
		ResponseUtil.write(ajaxResult, response);
	}
}
