package com.votesys.dao.operate.inter;

import com.votesys.bean.UserInfoDtlBean;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperUserInfoDtlDAO.java
 * @Description 用户扩展信息操作数据层接口类
 */
public interface IOperUserInfoDtlDAO {

	/**
	 * @Function com.votesys.dao.operate.inter.IOperUserInfoDtlDAO::insertUserInfoDtl
	 * @Description 初始化注册用户的扩展信息
	 * @param userID
	 * @return
	 */
	public boolean insertUserInfoDtl(String userID);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperUserInfoDtlSV::updateUserInfoDtl
	 * @Description 更新用户扩展信息
	 * @param userInfoDtl
	 */
	public boolean updateUserInfoDtl(UserInfoDtlBean userInfoDtl);
}
