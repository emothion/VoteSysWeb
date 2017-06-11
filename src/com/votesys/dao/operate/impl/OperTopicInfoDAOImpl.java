package com.votesys.dao.operate.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.votesys.bean.TopicInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperTopicInfoDAO;

/**
 * @ClassName com.votesys.dao.operate.impl.OperTopicInfoDAOImpl.java
 * @Description 主题基础信息增删改操作数据层实现类
 */
@Repository("operTopicInfoDAO")
public class OperTopicInfoDAOImpl implements IOperTopicInfoDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String insertTopicInfo(TopicInfoBean topicInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_INSERT_TOPIC_INFO);
		int ret = 0;
		
		if ("P".equals(topicInfo.getTopicStatus())) {
			sql.append("?,?,?,?,?)");
			ret = jdbcTemplate.update(sql.toString(), new Object[] {
					topicInfo.getTopicTitle(), 
					topicInfo.getTopicContent(), 
					topicInfo.getTopicStatus(), 
					topicInfo.getCreateTime(), 
					topicInfo.getEffTime()});
		} else if ("U".equals(topicInfo.getTopicStatus())) {//自动生效会系统自动生成当前时间
			sql.append("?,?,?,?)");
			ret = jdbcTemplate.update(sql.toString().replaceFirst(", `EFF_TIME`", ""), new Object[] {
					topicInfo.getTopicTitle(), 
					topicInfo.getTopicContent(), 
					topicInfo.getTopicStatus(), 
					topicInfo.getCreateTime()});
		}
		
		if (ret > 0) {
			return topicInfo.getCreateTime();
		}
		return null;
	}

	@Override
	public boolean updateTopicInfo(TopicInfoBean topicInfo) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_UPDATE_TOPIC_INFO);
		
		int ret = jdbcTemplate.update(sql, new Object[] {
				topicInfo.getTopicTitle(),
				topicInfo.getTopicContent(),
				topicInfo.getTopicStatus(),
				topicInfo.getEffTime(),
				topicInfo.getTopicID()
				});
		
		if (ret > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insertTopicExtInfo(List<Object[]> params) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_INSERT_TOPIC_INFO_EXT);
		
		int[] ret = jdbcTemplate.batchUpdate(sql, params);
		
		if (ret.length == params.size()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTopicInfoExpTime(String expTime, String topicID) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_UPDATE_TOPIC_INFO_EXPTIME);
		
		int ret = jdbcTemplate.update(sql, new Object[] {expTime, topicID});
		
		if (ret > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTopicInfoSetStatusS(String topicID, String topicStatus) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_UPDATE_TOPIC_INFO_STATUS);
		
		int ret = jdbcTemplate.update(sql, new Object[] {topicStatus, topicID});

		if (ret > 0) {
			ret = jdbcTemplate.update(VoteSysConstant.SQLTemplate.SQL_UPDATE_VOTE_LAUNCH, new Object[] {topicID});
		}
		
		if (ret > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public int updateTopicStatusToU(String userID) {
		return jdbcTemplate.update(VoteSysConstant.SQLTemplate.SQL_UPDATE_PUBLISH_TOPIC, new Object[] {userID});
	}

}
