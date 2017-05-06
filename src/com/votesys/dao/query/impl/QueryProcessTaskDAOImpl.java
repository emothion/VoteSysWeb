package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryProcessTaskDAO;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperProcessTaskDAO.java
 * @Description 定时任务后台进程数据库查询接口实现类
 */
@Repository("queryProcessTaskDao")
public class QueryProcessTaskDAOImpl implements IQueryProcessTaskDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<String> queryExpTopicOfTopicID(String expTime) {
		List<String> topicIDList = new ArrayList<String>();
		
		jdbcTemplate.query(VoteSysConstant.ProcessTaskSQLTemplate.AUTO_QUERY_EXP_TOPIC, new Object[] {expTime}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				topicIDList.add(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.topicID));
			}
		});
		
		return topicIDList;
	}

}
