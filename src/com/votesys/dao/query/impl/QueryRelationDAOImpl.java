package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.UserTopicRelateBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryRelationDAO;
import com.votesys.tools.BeanUtils;

@Repository("queryRelationDao")
public class QueryRelationDAOImpl implements IQueryRelationDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<UserTopicRelateBean> qryUTRelByUserID(String userID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_UTR);
		List<UserTopicRelateBean> utRelList = new ArrayList<UserTopicRelateBean>();
		
		sql.append(" AND ").append(BeanOfMapping.UserTopicRelBeanMapping.userID).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {userID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserTopicRelateBean utRel = new UserTopicRelateBean();
				utRel = BeanUtils.setUserTopicRelBean(rs);
				utRelList.add(utRel);
			}
		});
		
		return utRelList;
	}

}
