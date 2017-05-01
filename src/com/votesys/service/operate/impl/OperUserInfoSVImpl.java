package com.votesys.service.operate.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.UserInfoBean;
import com.votesys.dao.operate.inter.IOperUserInfoDAO;
import com.votesys.service.operate.inter.IOperUserInfoSV;

/**
 * @ClassName com.votesys.service.impl.QueryUserInfoSVImpl.java
 * @Description 用户基础信息操作类服务层实现类
 */
@Service("operUserInfoService")
public class OperUserInfoSVImpl implements IOperUserInfoSV {

	@Resource
	private IOperUserInfoDAO operUserInfoDAO;
	
	@Override
	public boolean insertUserInfo(UserInfoBean userInfo) {
		userInfo.setStatus("U");
		
		boolean retInfo = operUserInfoDAO.insertUserInfo(userInfo);
		return retInfo;
	}

	@Override
	public boolean updateUserInfo(UserInfoBean userInfo) {
		return operUserInfoDAO.updateUserInfo(userInfo);
	}

	@Override
	public boolean updateUserInfoPassword(String userID, String userKey) {
		return operUserInfoDAO.updateUserInfoPassword(userID, userKey);
	}

}
