package com.votesys.service.operate.inter;

import com.votesys.bean.UserInfoBean;

/**
 * @ClassName com.votesys.service.inter.IQueryUserInfoSV.java
 * @Description 用户基础信息操作类服务层接口
 */
public interface IOperUserInfoSV {

	/**
	 * @Function com.votesys.service.operate.inter.IOperUserInfoSV::insertUserInfo
	 * @Description 用户注册（插入基本信息）
	 * @param userInfo
	 */
	public boolean insertUserInfo(UserInfoBean userInfo);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperUserInfoSV::updateUserInfo
	 * @Description 用户部分信息更新
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfo(UserInfoBean userInfo);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperUserInfoSV::updateUserInfoPassword
	 * @Description 重置用户密码
	 * @param userID
	 * @param userKey
	 * @return
	 */
	public boolean updateUserInfoPassword(String userID, String userKey);
}
