package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryUserInfoAndExtDAO;
import com.votesys.qbo.bean.UserAllInfoBean;
import com.votesys.tools.BeanUtils;

/**
 * @ClassName com.votesys.dao.impl.QueryUserInfoDAOImpl.java
 * @Description 用户扩展及完整信息数据查询操作层实现类
 */
@Repository("queryUserInfoAndExtDao")
public class QueryUserInfoAndExtDAOImpl implements IQueryUserInfoAndExtDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserAllInfoBean queryUserAllInfo(String userID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_USER_ALL_INFO);
		sql.append(" AND USER_ID=").append(userID);
		
		List<UserAllInfoBean> list = new ArrayList<UserAllInfoBean>();
		jdbcTemplate.query(sql.toString(), new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserAllInfoBean userAllInfo = BeanUtils.setUserAllInfoBean(rs);
				list.add(userAllInfo);
			}
		});
		
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

}
