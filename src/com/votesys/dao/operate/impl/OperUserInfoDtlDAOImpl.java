package com.votesys.dao.operate.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.votesys.bean.UserInfoDtlBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperUserInfoDtlDAO;

/**
 * @ClassName com.votesys.dao.operate.impl.OperUserInfoDAOImpl.java
 * @Description 用户扩展信息数据增删改操作层实现类
 */
@Repository("operUserInfoDtlDao")
public class OperUserInfoDtlDAOImpl implements IOperUserInfoDtlDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean insertUserInfoDtl(String userID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_INSERT_USER_INFO_DTL);
		sql.replace(sql.length()-2, sql.length()-1, userID);
		
		int ret = jdbcTemplate.update(sql.toString());
		
		if (ret < 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserInfoDtl(UserInfoDtlBean userInfoDtl) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_UPDATE_USER_INFO_DTL);
		
		sql.append(BeanOfMapping.UserInfoDtlBeanMapping.userSexy).append("='").
		append(userInfoDtl.getUserSexy()).append("',");
		
		sql.append(BeanOfMapping.UserInfoDtlBeanMapping.userBirth).append("='").
		append(userInfoDtl.getUserBirth()).append("',");
		
		sql.append(BeanOfMapping.UserInfoDtlBeanMapping.userProvince).append("='").
		append(userInfoDtl.getUserProvince()).append("',");
		
		sql.append(BeanOfMapping.UserInfoDtlBeanMapping.userCity).append("='").
		append(userInfoDtl.getUserCity()).append("',");
		
		sql.append(BeanOfMapping.UserInfoDtlBeanMapping.userRegion).append("='").
		append(userInfoDtl.getUserRegion()).append("',");
		
		sql.append(BeanOfMapping.UserInfoDtlBeanMapping.userDesc).append("='").
		append(userInfoDtl.getUserDesc()).append("' ");
		
		sql.append("WHERE ").append(BeanOfMapping.UserInfoDtlBeanMapping.userID).
		append("=").append(userInfoDtl.getUserID());
		
		int ret = jdbcTemplate.update(sql.toString());
		
		if (ret < 1) {
			return false;
		}
		return true;
	}

}
