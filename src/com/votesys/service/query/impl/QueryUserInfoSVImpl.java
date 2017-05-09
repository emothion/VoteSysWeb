package com.votesys.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.PageBean;
import com.votesys.bean.UserInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryUserInfoDAO;
import com.votesys.service.query.inter.IQueryUserInfoSV;
import com.votesys.tools.StringUtils;

/**
 * @ClassName com.votesys.service.impl.QueryUserInfoSVImpl.java
 * @Description 用户基础信息查询类服务层实现类
 */
@Service("queryUserInfoService")
public class QueryUserInfoSVImpl implements IQueryUserInfoSV {

	@Resource
	private IQueryUserInfoDAO queryUserInfoDAO;
	
	@Override
	public UserInfoBean queryUserInfoByNameAndKey(UserInfoBean userInfo) {
		String uName = userInfo.getUserName();
		String uKey = userInfo.getUserKey();
		UserInfoBean retUserInfo = new UserInfoBean();
		
		if (StringUtils.isBlank(uName)) {
			retUserInfo.setRetInfo(VoteSysConstant.ERR0001);
			return userInfo;
		}
		
		if (StringUtils.isBlank(uKey)) {
			retUserInfo.setRetInfo(VoteSysConstant.ERR0004);
			return userInfo;
		}
		return queryUserInfoDAO.queryUserInfoByNameAndKey(uName, uKey);
	}

	@Override
	public UserInfoBean queryUserInfoByEmailAndKey(UserInfoBean userInfo) {
		String uEmail = userInfo.getUserEmail();
		String uKey = userInfo.getUserKey();
		UserInfoBean retUserInfo = new UserInfoBean();
		
		if (StringUtils.isBlank(uEmail)) {
			retUserInfo.setRetInfo(VoteSysConstant.ERR0002);
			return userInfo;
		}
		
		if (StringUtils.isBlank(uKey)) {
			retUserInfo.setRetInfo(VoteSysConstant.ERR0004);
			return userInfo;
		}
		return queryUserInfoDAO.queryUserInfoByEmailAndKey(uEmail, uKey);
	}

	@Override
	public UserInfoBean queryUserInfoByPhoneAndKey(UserInfoBean userInfo) {
		String uPhone = userInfo.getUserPhone();
		String uKey = userInfo.getUserKey();
		UserInfoBean retUserInfo = new UserInfoBean();
		
		if (StringUtils.isBlank(uPhone)) {
			retUserInfo.setRetInfo(VoteSysConstant.ERR0003);
			return userInfo;
		}
		
		if (StringUtils.isBlank(uKey)) {
			retUserInfo.setRetInfo(VoteSysConstant.ERR0004);
			return userInfo;
		}
		return queryUserInfoDAO.queryUserInfoByPhoneAndKey(uPhone, uKey);
	}

	@Override
	public boolean queryUserInfoForUserNameReSeek(UserInfoBean userInfo) {
		String uName = userInfo.getUserName();
		return queryUserInfoDAO.queryUserInfoForUserNameReSeek(uName);
	}

	@Override
	public UserInfoBean queryUserInfoByUserID(String userID) {
		return queryUserInfoDAO.queryUserInfoByUserID(userID);
	}

	@Override
	public List<UserInfoBean> queryUserInfo(PageBean pageInfo, UserInfoBean userInfo) {
		return  queryUserInfoDAO.queryUserInfo(pageInfo, userInfo);
	}

	@Override
	public int queryUserInfoTote(UserInfoBean userInfo) {
		return queryUserInfoDAO.queryUserInfoTote(userInfo);
	}
}
