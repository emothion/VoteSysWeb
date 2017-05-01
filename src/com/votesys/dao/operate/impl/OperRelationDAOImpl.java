package com.votesys.dao.operate.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperRelationDAO;

/**
 * @ClassName com.votesys.dao.operate.impl.OperRelationDAOImpl.java
 * @Description 关系表增删改操作数据层实现类
 */
@Repository("operRelationDao")
public class OperRelationDAOImpl implements IOperRelationDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean insertTopicVoteRelate(List<Object[]> params) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_INSERT_TVR);
		
		int[] ret = jdbcTemplate.batchUpdate(sql, params);
		
		if (ret.length == params.size()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTopicVoteRelate(String topicID) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_DELETE_TVR);
		
		int ret = jdbcTemplate.update(sql, new Object[] {topicID});
		
		if (ret > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertUserTopicRelate(String userID, String topicID) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_INSERT_UTR);
		
		int ret = jdbcTemplate.update(sql, new Object[] {userID, topicID});
		
		if (ret > 0) {
			return true;
		}
		return false;
	}

}
