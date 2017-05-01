package com.votesys.dao.operate.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.votesys.bean.VoteLaunchBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperVoteInfoDAO;

/**
 * @ClassName com.votesys.dao.operate.impl.OperVoteInfoDAOImpl.java
 * @Description 投票项目增删改操作数据层接口
 */
@Repository("operVoteInfoDao")
public class OperVoteInfoDAOImpl implements IOperVoteInfoDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean insertVoteInfo(List<Object[]> params) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_INSERT_VOTE_INFO);
		
		int[] ret = jdbcTemplate.batchUpdate(sql, params);
		
		if (ret.length == params.size()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteVoteInfo(String voteID) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_DELETE_VOTE_INFO);
		
		int ret = jdbcTemplate.update(sql, new Object[] {voteID});
		
		if (ret > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertVoteInfo(VoteLaunchBean voteLaunch) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_INSERT_VOTE_LAUNCH);
		int ret;
		
		if ("A".equals(voteLaunch.getVoteStop())) {
			sql.append("?,?,?,?)");
			ret = jdbcTemplate.update(sql.toString(), new Object[] {
					voteLaunch.getTopicID(), 
					voteLaunch.getVoteStyle(), 
					voteLaunch.getVoteStop(), 
					voteLaunch.getExpDate()});
		} else if ("O".equals(voteLaunch.getVoteStop())) {
			sql.append("?,?,?)");
			ret = jdbcTemplate.update(sql.toString().replaceFirst(", `EXP_DATE`", ""), new Object[] {
					voteLaunch.getTopicID(), 
					voteLaunch.getVoteStyle(), 
					voteLaunch.getVoteStop()});
		} else {
			return false;
		}
		
		if (ret > 0 ) {
			return true;
		} else {
			return false;
		}
	}

}
