package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.VoteInfoBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryVoteInfoDAO;
import com.votesys.tools.BeanUtils;

/**
 * @ClassName com.votesys.dao.query.impl.QueryVoteInfoDAOImpl.java
 * @Description 投票项目查询数据层实现类
 */
@Repository("queryVoteInfoDao")
public class QueryVoteInfoDAOImpl implements IQueryVoteInfoDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<VoteInfoBean> queryVoteInfoByRemarks(String topicID) {
		List<VoteInfoBean> voteList = new ArrayList<VoteInfoBean>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_VOTE_INTO);
		sql.append(" AND ").append(BeanOfMapping.VoteInfoBeanMapping.remark).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				VoteInfoBean voteInfo = BeanUtils.setVoteInfoBean(rs);
				voteList.add(voteInfo);
			}
		});
		
		return voteList;
	}

}
