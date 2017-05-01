package com.votesys.dao.query.inter;

import com.votesys.bean.UserInfoBean;

/**
 * @ClassName com.votesys.dao.inter.IQueryUserInfoDAO.java
 * @Description 用户基础信息数据查询操作层接口
 */
public interface IQueryUserInfoDAO {

	/**
	 * @Function com.votesys.dao.inter.IQueryUserInfoDAO::queryUserInfoByNameAndKey
	 * @Description 通过用户名以及用户密码查询是否存在该用户
	 * @param uName 用户名
	 * @param uKey 用户密码
	 */
	public UserInfoBean queryUserInfoByNameAndKey(String uName, String uKey);
	
	/**
	 * @Function com.votesys.dao.inter.IQueryUserInfoDAO::queryUserInfoByEmailAndKey
	 * @Description 通过电子邮件和密码查询是否存在该用户
	 * @param uEmail 用户电子邮件
	 * @param uKey 用户密码
	 */
	public UserInfoBean queryUserInfoByEmailAndKey(String uEmail, String uKey);
	
	/**
	 * @Function com.votesys.dao.inter.IQueryUserInfoDAO::queryUserInfoByPhoneAndKey
	 * @Description 通过电话号码和密码查询是否存在该用户
	 * @param uPhone 用户电话号码
	 * @param uKey 用户密码
	 */
	public UserInfoBean queryUserInfoByPhoneAndKey(String uPhone, String uKey);
	
	/**
	 * @Function com.votesys.dao.inter.IQueryUserInfoDAO::queryUserInfoForUserNameReSeek
	 * @Description 查询用户名是否已经在数据库中存在
	 * @param uName
	 * @return
	 */
	public boolean queryUserInfoForUserNameReSeek(String uName);
	
	/**
	 * @Function com.votesys.dao.query.inter.IQueryUserInfoDAO::queryUserInfoByuserID
	 * @Description 通过用户编号（主键）查询用户信息
	 * @param userID
	 */
	public UserInfoBean queryUserInfoByUserID(String userID);
}
