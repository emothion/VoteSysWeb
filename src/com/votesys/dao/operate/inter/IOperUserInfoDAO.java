package com.votesys.dao.operate.inter;

import com.votesys.bean.UserInfoBean;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperUserInfoDAO.java
 * @Description 用户基础信息数据增删改操作层接口
 */
public interface IOperUserInfoDAO {

	/**
	 * @Function com.votesys.dao.operate.inter.IOperUserInfoDAO::insertUserInfo
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
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperUserInfoDAO::updateUserStatus
	 * @Description 修改用户状态
	 * @param userID
	 * @param userStatus
	 * @return
	 */
	public boolean updateUserStatus(String userID, String userStatus);
}
