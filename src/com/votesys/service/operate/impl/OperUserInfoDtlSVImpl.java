package com.votesys.service.operate.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.UserInfoDtlBean;
import com.votesys.dao.operate.inter.IOperUserInfoDtlDAO;
import com.votesys.service.operate.inter.IOperUserInfoDtlSV;

/**
 * @ClassName com.votesys.service.operate.impl.OperUserInfoDtlSVImpl.java
 * @Description 用户扩展信息操作服务实现类
 */
@Service("userInfoDtlService")
public class OperUserInfoDtlSVImpl implements IOperUserInfoDtlSV {

	@Resource
	private IOperUserInfoDtlDAO operUserInfoDtlDAO;
	
	@Override
	public boolean insertUserInfoDtl(String userID) {
		return operUserInfoDtlDAO.insertUserInfoDtl(userID);
	}

	@Override
	public boolean updateUserInfoDtl(UserInfoDtlBean userInfoDtl) {
		return operUserInfoDtlDAO.updateUserInfoDtl(userInfoDtl);
	}

}
