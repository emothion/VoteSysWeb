package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.TopicExtInfoBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryTopicInfoExtDAO;
import com.votesys.tools.BeanUtils;

/**
 * @ClassName com.votesys.dao.query.impl.QueryTopicInfoExtDAOImpl.java
 * @Description 查询主题包含的图片数据层实现类
 */
@Repository("queryTopicInfoExtDao")
public class QueryTopicInfoExtDAOImpl implements IQueryTopicInfoExtDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public TopicExtInfoBean queryMainImageByTopicID(String topicID) {
		List<TopicExtInfoBean> topicExtList = new ArrayList<TopicExtInfoBean>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO_EXT);
		sql.append(" AND ").append(BeanOfMapping.TopicExtInfoBeanMapping.topicID).append("=?");
		sql.append(" AND ").append(BeanOfMapping.TopicExtInfoBeanMapping.imgOrder).append("=1");
		
		jdbcTemplate.query(sql.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicExtInfoBean topicExtInfo = BeanUtils.setTopicInfoDtlBean(rs);
				topicExtList.add(topicExtInfo);
			}
		});
		
		if (topicExtList.isEmpty()) {
			return null;
		}
		return topicExtList.get(0);
	}

	@Override
	public List<TopicExtInfoBean> queryImageByTopicID(String topicID) {
		List<TopicExtInfoBean> topicExtList = new ArrayList<TopicExtInfoBean>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO_EXT);
		sql.append(" AND ").append(BeanOfMapping.TopicExtInfoBeanMapping.topicID).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicExtInfoBean topicExtInfo = BeanUtils.setTopicInfoDtlBean(rs);
				topicExtList.add(topicExtInfo);
			}
		});
		if (topicExtList.isEmpty()) {
			return new ArrayList<TopicExtInfoBean>();
		}
		return topicExtList;
	}

}
