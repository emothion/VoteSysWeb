package com.votesys.service.query.inter;

import com.votesys.bean.UserInfoBean;

/**
 * @ClassName com.votesys.service.inter.IQueryUserInfoSV.java
 * @Description 用户基础信息查询类服务层接口
 */
public interface IQueryUserInfoSV {

	/**
	 * @Function com.votesys.service.inter.IQueryUserInfoSV::queryUserInfoByNameAndKey
	 * @Description 通过用户名和密码查询用户
	 * @param userInfo
	 */
	public UserInfoBean queryUserInfoByNameAndKey(UserInfoBean userInfo);
	
	/**
	 * @Function com.votesys.service.inter.IQueryUserInfoSV::queryUserInfoByEmailAndKey
	 * @Description 通过电子邮件和密码查询用户
	 */
	public UserInfoBean queryUserInfoByEmailAndKey(UserInfoBean userInfo);
	
	/**
	 * @Function com.votesys.service.inter.IQueryUserInfoSV::queryUserInfoByPhoneAndKey
	 * @Description 通过电话号码和密码查询用户
	 * @param userInfo
	 */
	public UserInfoBean queryUserInfoByPhoneAndKey(UserInfoBean userInfo);
	
	/**
	 * @Function com.votesys.service.inter.IQueryUserInfoSV::queryUserInfoForUserNameReSeek
	 * @Description 查询用户名是否已经在数据库中存在
	 * @param userInfo
	 * @return
	 */
	public boolean queryUserInfoForUserNameReSeek(UserInfoBean userInfo);
	
	/**
	 * @Function com.votesys.dao.query.inter.IQueryUserInfoDAO::queryUserInfoByuserID
	 * @Description 通过用户编号（主键）查询用户信息
	 * @param userID
	 */
	public UserInfoBean queryUserInfoByUserID(String userID);
}
