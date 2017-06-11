package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryTopicInfoDAO;
import com.votesys.tools.BeanUtils;
import com.votesys.tools.StringUtils;

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

	@Override
	public List<TopicInfoBean> queryTopicInfo(PageBean pageInfo, TopicInfoBean topicInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO);
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		StringBuffer condition = new StringBuffer();
		Object[] params = null;	
		if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
			condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
			if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
				condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus);
				if ("P".equals(topicInfo.getTopicStatus())) {
					condition.append("<>?");
				} else {
					condition.append("=?");
				}
				params = new Object[] {"%"+topicInfo.getTopicTitle()+"%", topicInfo.getTopicStatus(), pageInfo.getStart(), pageInfo.getPageSize()};
			}else {
				condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("<>'P'");
				params = new Object[] {"%"+topicInfo.getTopicTitle()+"%", pageInfo.getStart(), pageInfo.getPageSize()};
			}
		} else {
			if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
				condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus);
				if ("P".equals(topicInfo.getTopicStatus())) {
					condition.append("<>?");
				} else {
					condition.append("=?");
				}
				if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
					condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
					params = new Object[] {topicInfo.getTopicStatus(), "%"+topicInfo.getTopicTitle()+"%", pageInfo.getStart(), pageInfo.getPageSize()};
				}else {
					params = new Object[] {topicInfo.getTopicStatus(), pageInfo.getStart(), pageInfo.getPageSize()};
				}
			}
		}
		if (params == null) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("<>'P'").
			append(" ORDER BY `CREATE_TIME` DESC LIMIT ?,?");
			jdbcTemplate.query(sql.toString(), new Object[] {pageInfo.getStart(), pageInfo.getPageSize()}, new RowCallbackHandler() {
				
				@Override
				public void processRow(ResultSet rs) throws SQLException {//这整个方法为第四步把数据放入模型类。
					TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
					topicList.add(topicInfo);
				}
			});
		} else {
			sql.append(condition).append(" ORDER BY `CREATE_TIME` DESC LIMIT ?,?");
			jdbcTemplate.query(sql.toString(), params, new RowCallbackHandler() {
				
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
					topicList.add(topicInfo);
				}
			});
		}
		
		if (topicList.isEmpty()) {
			return null;
		}
		return topicList;
	}

	@Override
	public int queryTopicInfoTote(TopicInfoBean topicInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO_TOTE);
		StringBuffer condition = new StringBuffer();
		Object[] params = null;	
		int tote = 0;
		if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
			condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
			if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
				condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
				params = new Object[] {"%"+topicInfo.getTopicTitle()+"%", topicInfo.getTopicStatus()};
			}else {
				condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("<>'P'");
				params = new Object[] {"%"+topicInfo.getTopicTitle()+"%"};
			}
		}
		if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
			condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
			if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
				condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
				params = new Object[] {topicInfo.getTopicStatus(), "%"+topicInfo.getTopicTitle()+"%"};
			}else {
				params = new Object[] {topicInfo.getTopicStatus()};
			}
		}
		
		if (params == null) {
			condition.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("<>'P'");
			sql.append(condition);
			tote = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		} else {
			sql.append(condition);
			tote = jdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
		}
		
		return tote;
	}

	@Override
	public List<TopicInfoBean> queryTopicInfoForManager(PageBean pageInfo, TopicInfoBean topicInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO);
		List<String> condition = new ArrayList<String>();
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
			condition.add("%"+topicInfo.getTopicTitle()+"%");
		}
		if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
			condition.add(topicInfo.getTopicStatus());
		}
		if (StringUtils.isNotEmpty(topicInfo.getEffTime())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.createTime).append(">?");
			condition.add(topicInfo.getEffTime());
		}
		if (StringUtils.isNotEmpty(topicInfo.getExpTime())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.createTime).append("<?");
			condition.add(topicInfo.getExpTime());
		}
		Object[] params = new Object[condition.size()+2];
		for (int i = 0; i < condition.size(); i++) {
			params[i] = condition.get(i);
			
		}
		params[condition.size()] = pageInfo.getStart();
		params[condition.size()+1] = pageInfo.getPageSize();
		sql.append(" LIMIT ?,?");
		
		jdbcTemplate.query(sql.toString(), params, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
				topicList.add(topicInfo);
			}
		});
		
		return topicList;
	}

	@Override
	public int queryTopicInfoForManagerTote(TopicInfoBean topicInfo) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_TOPIC_INFO_TOTE);
		List<String> condition = new ArrayList<String>();
		if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
			condition.add("%"+topicInfo.getTopicTitle()+"%");
		}
		if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
			condition.add(topicInfo.getTopicStatus());
		}
		if (StringUtils.isNotEmpty(topicInfo.getEffTime())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.createTime).append(">?");
			condition.add(topicInfo.getEffTime());
		}
		if (StringUtils.isNotEmpty(topicInfo.getExpTime())) {
			sql.append(" AND ").append(BeanOfMapping.TopicInfoBeanMapping.createTime).append("<?");
			condition.add(topicInfo.getExpTime());
		}
		Object[] params = new Object[condition.size()];
		for (int i = 0; i < condition.size(); i++) {
			params[i] = condition.get(i);
			
		}
		
		return jdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
	}

}
