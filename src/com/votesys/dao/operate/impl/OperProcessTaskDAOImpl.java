package com.votesys.dao.operate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperProcessTaskDAO;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperProcessTaskDAO.java
 * @Description 定时任务后台进程数据库操作接口实现类
 */
@Repository("operProcessTaskDao")
public class OperProcessTaskDAOImpl implements IOperProcessTaskDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int updateTopicStatusToU(String effTime) {
		return jdbcTemplate.update(VoteSysConstant.ProcessTaskSQLTemplate.AUTO_PUBLISH_TOPIC, new Object[] {effTime});
	}

	@Override
	public int[] updateTopicStatusToS(List<String> topicIDList) {
		List<Object[]> params = new ArrayList<Object[]>();
		for (String topicID : topicIDList) {
			Object[] param = new Object[] {topicID};
			params.add(param);
		}
		int[] ret = jdbcTemplate.batchUpdate(VoteSysConstant.ProcessTaskSQLTemplate.AUTO_END_VOTE, params);
		
		if (ret.length > 0) {
			ret = jdbcTemplate.batchUpdate(VoteSysConstant.ProcessTaskSQLTemplate.AUTO_END_TOPIC, params);
		}
		return ret;
	}
	
}
