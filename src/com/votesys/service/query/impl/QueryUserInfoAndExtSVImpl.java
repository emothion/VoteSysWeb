package com.votesys.service.query.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.dao.query.inter.IQueryUserInfoAndExtDAO;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.service.query.inter.IQueryUserInfoAndExtSV;

/**
 * @ClassName com.votesys.service.query.inter.IQueryUserInfoAndExtSV.java
 * @Description 用户扩展以及完整信息查询类服务层实现类
 */
@Service("queryUserInfoAndExtService")
public class QueryUserInfoAndExtSVImpl implements IQueryUserInfoAndExtSV {

	@Resource
	private IQueryUserInfoAndExtDAO queryUserInfoAndExtDAO;
	
	@Override
	public UserAllInfoBean queryUserAllInfo(String userID) {
		UserAllInfoBean userAllInfo = queryUserInfoAndExtDAO.queryUserAllInfo(userID);
		return userAllInfo; 
	}

}
