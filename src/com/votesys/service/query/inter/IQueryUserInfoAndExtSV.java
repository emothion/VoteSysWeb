package com.votesys.service.query.inter;

import com.votesys.qbo.bean.UserAllInfoBean;

/**
 * @ClassName com.votesys.service.query.inter.IQueryUserInfoAndExtSV.java
 * @Description 用户扩展以及完整信息查询类服务层接口
 */
public interface IQueryUserInfoAndExtSV {

	/**
	 * @Function com.votesys.service.query.inter.IQueryUserInfoAndExtSV::queryUserAllInfo
	 * @Description 通过用户编号查询用户完整信息
	 * @param userID
	 */
	public UserAllInfoBean queryUserAllInfo(String userID);
}
