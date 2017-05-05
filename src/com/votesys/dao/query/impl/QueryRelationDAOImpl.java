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
import com.votesys.bean.UserVoteRelateBean;
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

	@Override
	public String qryUTRelFintUserIDByTopicID(String topicID) {
		UserTopicRelateBean userTopicRelate = new UserTopicRelateBean();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_UTR);
		sql.append(" AND ").append(BeanOfMapping.UserTopicRelBeanMapping.topicID).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				userTopicRelate.setUserID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.userID));
			}
		});
		
		return userTopicRelate.getUserID();
	}

	@Override
	public int qryVoteOpitonGetCount(String voteID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_UVR_NUM);
		sql.append(" AND ").append(BeanOfMapping.UserVoteRelBeanMapping.voteID).append("=?");
		
		int ret = jdbcTemplate.queryForObject(sql.toString(), new Object[] {voteID}, Integer.class);
		
		return ret;
	}

	@Override
	public boolean qryUserExistUserVote(String userID, String topicID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_UVR);
		sql.append(" AND ").append(BeanOfMapping.UserVoteRelBeanMapping.userID).append("=?");
		sql.append(" AND ").append(BeanOfMapping.UserVoteRelBeanMapping.topicID).append("=?");
		List<UserVoteRelateBean> uvrList = new ArrayList<UserVoteRelateBean>();
		
		jdbcTemplate.query(sql.toString(), new Object[] {userID, topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserVoteRelateBean uvRel = BeanUtils.setUserVoteRelateBean(rs);
				uvrList.add(uvRel);
			}
		});
		
		if (uvrList.size() > 0 && uvrList != null) {
			return true;
		}
		return false;
	}

}
