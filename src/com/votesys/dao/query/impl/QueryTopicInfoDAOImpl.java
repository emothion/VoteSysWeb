package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryTopicInfoDAO;
import com.votesys.tools.BeanUtils;

/**
 * @ClassName com.votesys.dao.query.impl.QueryTopicInfoDAOImpl.java
 * @Description 主题基础信息查询操作数据层实现类
 */
@Repository("queryTopicInfoDao")
public class QueryTopicInfoDAOImpl implements IQueryTopicInfoDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public TopicInfoBean queryTopicInfoByCreateTime(String createTime) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO);
		sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.createTime).append("=?");
		
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		jdbcTemplate.query(sql.toString(), new Object[] {createTime}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
				topicList.add(topicInfo);
			}
		});
		
		if (topicList.size() > 0) {
			return topicList.get(0);
		}
		return null;
	}

	@Override
	public TopicInfoBean queryTopicInfoByTopicID(String topicID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO);
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		
		sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicID).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
				topicList.add(topicInfo);
			}
		});

		if (topicList.size() > 0) {
			return topicList.get(0);
		}
		return null;
	}

}
