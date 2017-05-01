package com.votesys.dao.operate.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.votesys.bean.UserInfoBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperUserInfoDAO;
import com.votesys.tools.StringUtils;

/**
 * @ClassName com.votesys.dao.operate.impl.OperUserInfoDAOImpl.java
 * @Description 用户基础信息数据增删改操作层实现类
 */
@Repository("operUserInfoDao")
public class OperUserInfoDAOImpl implements IOperUserInfoDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean insertUserInfo(UserInfoBean userInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_INSERT_USER_INFO);
		StringBuffer titles = new StringBuffer();
		StringBuffer values = new StringBuffer();
		
		if (StringUtils.isNotBlank(userInfo.getUserName())) {
			titles.append(BeanOfMapping.UserInfoBeanMapping.userName).append(", ");
			values.append("'").append(userInfo.getUserName()).append("',");
		}
		if (StringUtils.isNotBlank(userInfo.getUserEmail())) {
			titles.append(BeanOfMapping.UserInfoBeanMapping.userEmail).append(", ");
			values.append("'").append(userInfo.getUserEmail()).append("',");
		}
		if (StringUtils.isNotBlank(userInfo.getUserPhone())) {
			titles.append(BeanOfMapping.UserInfoBeanMapping.userPhone).append(", ");
			values.append("'").append(userInfo.getUserPhone()).append("',");
		}
		if (StringUtils.isNotBlank(userInfo.getUserKey())) {
			titles.append(BeanOfMapping.UserInfoBeanMapping.userKey).append(", ");
			values.append("'").append(userInfo.getUserKey()).append("',");
		}
		if (StringUtils.isNotBlank(userInfo.getStatus())) {
			titles.append(BeanOfMapping.UserInfoBeanMapping.status);
			values.append("'").append(userInfo.getStatus()).append("'");
		}
		
		sql.replace(26, 27, titles.toString());
		sql.replace(sql.length()-2, sql.length()-1, values.toString());
		
		int ret = jdbcTemplate.update(sql.toString());
		if (ret < 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserInfo(UserInfoBean userInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_UPDATE_USER_INFO);
		sql.append(BeanOfMapping.UserInfoBeanMapping.userEmail).
		append("='").append(userInfo.getUserEmail()).append("',");
		sql.append(BeanOfMapping.UserInfoBeanMapping.userPhone).
		append("='").append(userInfo.getUserPhone()).append("' ");
		sql.append("WHERE ").append(BeanOfMapping.UserInfoBeanMapping.userID).
		append("=").append(userInfo.getUserID());
		
		int ret = jdbcTemplate.update(sql.toString());
		
		if (ret < 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserInfoPassword(String userID, String userKey) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_UPDATE_USER_INFO);
		sql.append(BeanOfMapping.UserInfoBeanMapping.userKey).
		append("='").append(userKey).append("' ");
		sql.append("WHERE ").append(BeanOfMapping.UserInfoBeanMapping.userID).
		append("=").append(userID);

		int ret = jdbcTemplate.update(sql.toString());
		
		if (ret < 1) {
			return false;
		}
		return true;
	}

}
