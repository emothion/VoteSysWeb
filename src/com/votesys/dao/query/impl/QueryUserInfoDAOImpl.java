package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.PageBean;
import com.votesys.bean.UserInfoBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryUserInfoDAO;
import com.votesys.tools.BeanUtils;
import com.votesys.tools.StringUtils;

/**
 * @ClassName com.votesys.dao.impl.QueryUserInfoDAOImpl.java
 * @Description 用户基础信息数据查询操作层实现类
 */
@Repository("queryUserInfoDao")
public class QueryUserInfoDAOImpl implements IQueryUserInfoDAO {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserInfoBean queryUserInfoByNameAndKey(String uName, String uKey) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO);
		
		sql.append(" AND USER_NAME='").append(uName).append("'");
		sql.append(" AND USER_KEY='").append(uKey).append("'");
		
		List<UserInfoBean> userList = new ArrayList<UserInfoBean>();
		jdbcTemplate.query(sql.toString(), new Object[] {}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserInfoBean user = BeanUtils.setUserInfoBean(rs);
				userList.add(user);
			}
		});
		
		if (userList.size() == 1) {
			return userList.get(0);
		} else if (userList.size() > 1) {
			UserInfoBean user = new UserInfoBean();
			user.setRetInfo("账号异常,请联系管理员");
			return user;
		} else {
			return null;
		}
	}

	@Override
	public UserInfoBean queryUserInfoByEmailAndKey(String uEmail, String uKey) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO);
		
		sql.append(" AND USER_EMAIL='").append(uEmail).append("'");
		sql.append(" AND USER_KEY='").append(uKey).append("'");
		
		List<UserInfoBean> userList = new ArrayList<UserInfoBean>();
		jdbcTemplate.query(sql.toString(), new Object[] {}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserInfoBean user = BeanUtils.setUserInfoBean(rs);
				userList.add(user);
			}
		});
		
		if (userList.size() == 1) {
			return userList.get(0);
		} else if (userList.size() > 1) {
			UserInfoBean user = new UserInfoBean();
			user.setRetInfo("账号异常,请联系管理员");
			return user;
		} else {
			return null;
		}
	}

	@Override
	public UserInfoBean queryUserInfoByPhoneAndKey(String uPhone, String uKey) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO);
		
		sql.append(" AND USER_PHONE='").append(uPhone).append("'");
		sql.append(" AND USER_KEY='").append(uKey).append("'");
		
		List<UserInfoBean> userList = new ArrayList<UserInfoBean>();
		jdbcTemplate.query(sql.toString(), new Object[] {}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserInfoBean user = BeanUtils.setUserInfoBean(rs);
				userList.add(user);
			}
		});
		
		if (userList.size() == 1) {
			return userList.get(0);
		} else if (userList.size() > 1) {
			UserInfoBean user = new UserInfoBean();
			user.setRetInfo("账号异常,请联系管理员");
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean queryUserInfoForUserNameReSeek(String uName) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO);
		
		sql.append(" AND USER_NAME='").append(uName).append("'");
		
		List<UserInfoBean> userList = new ArrayList<UserInfoBean>();
		jdbcTemplate.query(sql.toString(), new Object[] {}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserInfoBean user = BeanUtils.setUserInfoBean(rs);
				userList.add(user);
			}
		});
		
		if (userList.size() < 1) {
			return false;
		}
		return true;
	}

	public UserInfoBean queryUserInfoByUserID(String userID) {
		List<UserInfoBean> list = new ArrayList<UserInfoBean>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO);
		sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.userID).append("=").append(userID);
		
		jdbcTemplate.query(sql.toString(), new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserInfoBean userInfo = new UserInfoBean();
				userInfo = BeanUtils.setUserInfoBean(rs);
				list.add(userInfo);
			}
		});
		
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UserInfoBean> queryUserInfo(PageBean pageInfo, UserInfoBean userInfo) {
		List<UserInfoBean> list = new ArrayList<UserInfoBean>();
		List<String> condition = new ArrayList<String>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO);
		
		if (StringUtils.isNotEmpty(userInfo.getUserName())) {
			sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.userName).append(" LIKE ?");
			condition.add("%"+userInfo.getUserName()+"%");
		}
		
		if (StringUtils.isNotEmpty(userInfo.getStatus())) {
			sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.status).append("=?");
			condition.add(userInfo.getStatus());
		} else {
			sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.status).append("<>'M'");
		}
		sql.append(" LIMIT ?,? ");
		Object[] params = new Object[condition.size()+2];
		for (int i = 0; i < condition.size(); i++) {
			params[i] = condition.get(i);
		}
		params[condition.size()] = pageInfo.getStart();
		params[condition.size()+1] = pageInfo.getPageSize();
		jdbcTemplate.query(sql.toString(), params, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserInfoBean retInfo = BeanUtils.setUserInfoBean(rs);
				list.add(retInfo);
			}
		});
		return list;
	}

	@Override
	public int queryUserInfoTote(UserInfoBean userInfo) {
		List<String> condition = new ArrayList<String>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_INFO_TOTE);
		
		if (StringUtils.isNotEmpty(userInfo.getUserName())) {
			sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.userName).append(" LIKE ?");
			condition.add("%"+userInfo.getUserName()+"%");
		}
		
		if (StringUtils.isNotEmpty(userInfo.getStatus())) {
			sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.status).append("=?");
			condition.add(userInfo.getStatus());
		} else {
			sql.append(" AND ").append(BeanOfMapping.UserInfoBeanMapping.status).append("<>'M'");
		}
		
		Object[] params = new Object[condition.size()];
		for (int i = 0; i < condition.size(); i++) {
			params[i] = condition.get(i);
		}
		
		int ret = jdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
		
		return ret;
	}
}
