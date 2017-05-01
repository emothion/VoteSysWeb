package com.votesys.dao.query.inter;

import com.votesys.qbo.bean.UserAllInfoBean;

/**
 * @ClassName com.votesys.dao.impl.QueryUserInfoDAOImpl.java
 * @Description 用户扩展及完整信息数据查询操作层接口
 */
public interface IQueryUserInfoAndExtDAO {

	/**
	 * @Function com.votesys.dao.query.inter.IQueryUserInfoAndExtDAO::queryUserAllInfo
	 * @Description 通过用户编号查询用户完整信息
	 * @param userID
	 */
	public UserAllInfoBean queryUserAllInfo(String userID);
}
