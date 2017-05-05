package com.votesys.service.operate.inter;

import com.votesys.bean.UserInfoDtlBean;

/**
 * @ClassName com.votesys.service.operate.inter.IOperUserInfoDtlSV.java
 * @Description 用户扩展信息操作服务类接口
 */
public interface IOperUserInfoDtlSV {

	/**
	 * @Function com.votesys.service.operate.inter.IOperUserInfoDtlSV::insertUserInfoDtl
	 * @Description 初始化用户扩展信息
	 * @param userID
	 */
	public boolean insertUserInfoDtl(String userID);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperUserInfoDtlSV::updateUserInfoDtl
	 * @Description 更新用户扩展信息
	 * @param userInfoDtl
	 */
	public boolean updateUserInfoDtl(UserInfoDtlBean userInfoDtl);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperUserInfoDtlDAO::updateUserImg
	 * @Description 更换头像
	 * @param userID
	 * @param userImg
	 * @return
	 */
	public boolean updateUserImg(String userID, String userImg);
}
