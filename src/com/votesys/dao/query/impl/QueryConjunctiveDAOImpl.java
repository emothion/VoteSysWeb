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
import com.votesys.dao.query.inter.IQueryConjunctiveDAO;
import com.votesys.qbo.bean.TrineUTCBean;
import com.votesys.tools.BeanUtils;
import com.votesys.tools.StringUtils;

/**
 * @ClassName com.votesys.dao.query.impl.QueryConjunctiveDAOImpl.java
 * @Description 联合查询数据层实现类
 */
@Repository("queryConjunctiveDao")
public class QueryConjunctiveDAOImpl implements IQueryConjunctiveDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<TopicInfoBean> qryTopicInfoByUserID(PageBean pageinfo, String userID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_USER_FIND_TOPIC);
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		
		jdbcTemplate.query(sql.toString(), new Object[] {userID, pageinfo.getStart(), pageinfo.getPageSize()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
				topicList.add(topicInfo);
			}
		});
		
		if (topicList.isEmpty()) {
			return null;
		}
		return topicList;
	}

	@Override
	public int qryTopicInfoCount(String userID, String topicStatus, String topicTitle) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_USER_FIND_TOPIC_TOTE);

		if (StringUtils.isNotBlank(userID)) {
			sql.append(" AND A.").append(BeanOfMapping.UserTopicRelBeanMapping.userID).append("=?");
			if (StringUtils.isNotBlank(topicTitle)) {
				sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
				if (StringUtils.isNotBlank(topicStatus)) {
					sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
					return jdbcTemplate.queryForObject(sql.toString(), new Object[] {userID,topicTitle,topicStatus}, Integer.class);
				}
				return jdbcTemplate.queryForObject(sql.toString(), new Object[] {userID,topicTitle}, Integer.class);
			} else {
				if (StringUtils.isNotBlank(topicStatus)) {
					sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
					return jdbcTemplate.queryForObject(sql.toString(), new Object[] {userID,topicStatus}, Integer.class);
				}
				return jdbcTemplate.queryForObject(sql.toString(), new Object[] {userID}, Integer.class);
			}
		} 
		if (StringUtils.isNotBlank(topicTitle)) {
			sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
			if (StringUtils.isNotBlank(topicStatus)) {
				sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
				if (StringUtils.isNotBlank(userID)) {
					sql.append(" AND A.").append(BeanOfMapping.UserTopicRelBeanMapping.userID).append("=?");
					return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicTitle,topicStatus,userID}, Integer.class);
				} 
				return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicTitle,topicStatus}, Integer.class);
			} else {
				if (StringUtils.isNotBlank(userID)) {
					sql.append(" AND A.").append(BeanOfMapping.UserTopicRelBeanMapping.userID).append("=?");
					return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicTitle,userID}, Integer.class);
				} 
				return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicTitle}, Integer.class);
			}
		}
		if (StringUtils.isNotBlank(topicStatus)) {
			sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
			if (StringUtils.isNotBlank(userID)) {
				sql.append(" AND A.").append(BeanOfMapping.UserTopicRelBeanMapping.userID).append("=?");
				if (StringUtils.isNotBlank(topicTitle)) {
					sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
					return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicStatus,userID,topicTitle}, Integer.class);
				}
				return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicStatus,userID}, Integer.class);
			} else {
				if (StringUtils.isNotBlank(topicTitle)) {
					sql.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
					return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicStatus,topicTitle}, Integer.class);
				}
				return jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicStatus}, Integer.class);
			}
		}
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

	@Override
	public List<TopicInfoBean> qryTopicInfoWithUserIDByCondition(PageBean pageInfo, TopicInfoBean topicInfo,
			String userID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_USER_FIND_TOPIC);
		StringBuffer condition  = new StringBuffer("A.USER_ID=?");
		List<TopicInfoBean> topicList = new ArrayList<TopicInfoBean>();
		boolean onlyHasUserID = true;
		Object[] params = null;
		
		if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
			condition.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
			if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
				condition.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
				params = new Object[] {userID, topicInfo.getTopicTitle(), topicInfo.getTopicStatus(), pageInfo.getStart(), pageInfo.getPageSize()};
				onlyHasUserID = false;
			}else {
				params = new Object[] {userID, topicInfo.getTopicTitle(), pageInfo.getStart(), pageInfo.getPageSize()};
				onlyHasUserID = false;
			}
		} else if (StringUtils.isNotEmpty(topicInfo.getTopicStatus())) {
			condition.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicStatus).append("=?");
			if (StringUtils.isNotEmpty(topicInfo.getTopicTitle())) {
				condition.append(" AND B.").append(BeanOfMapping.TopicInfoBeanMapping.topicTitle).append(" LIKE ?");
				params = new Object[] {userID, topicInfo.getTopicStatus(), topicInfo.getTopicTitle(), pageInfo.getStart(), pageInfo.getPageSize()};
				onlyHasUserID = false;
			}else {
				params = new Object[] {userID, topicInfo.getTopicStatus(), pageInfo.getStart(), pageInfo.getPageSize()};
				onlyHasUserID = false;
			}
		}
		if (onlyHasUserID) {
			params = new Object[] {userID, pageInfo.getStart(), pageInfo.getPageSize()};
		}
		sql.replace(256, 267, condition.toString());
		
		jdbcTemplate.query(sql.toString(), params, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TopicInfoBean topicInfo = BeanUtils.setTopicInfoBean(rs);
				topicList.add(topicInfo);
			}
		});

		if (topicList.isEmpty()) {
			return new ArrayList<TopicInfoBean>();
		}
		return topicList;
	}

	@Override
	public List<TrineUTCBean> qryCommentByTopicID(String topicID, PageBean pageInfo) {
		List<TrineUTCBean> utcList = new ArrayList<TrineUTCBean>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_TRINE_UTC);
		sql.append(" AND ").append(BeanOfMapping.TrineUTCBeanMapping.topicID).append("=?");
		sql.append(" LIMIT ").append(pageInfo.getStart()).append(",").append(pageInfo.getPageSize());
		
		jdbcTemplate.query(sql.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TrineUTCBean trineUTC = BeanUtils.setTrineUTCBean(rs);
				utcList.add(trineUTC);
			}
		});
		
		if (utcList.size() > 0) {
			return utcList;
		}
		return null;
	}

	@Override
	public List<TrineUTCBean> qryCommentByComID(String comID) {
		List<TrineUTCBean> utcList = new ArrayList<TrineUTCBean>();
		StringBuffer sql = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_TRINE_UTC);
		sql.append(" AND ").append(BeanOfMapping.TrineUTCBeanMapping.comID).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {comID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TrineUTCBean trineUTC = BeanUtils.setTrineUTCBean(rs);
				utcList.add(trineUTC);
			}
		});
		
		if (utcList.size() > 0) {
			return utcList;
		}
		return null;
	}

	@Override
	public int qryCommentCountByTopicID(String topicID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_TRINE_UTC_TOTE);
		sql.append(" AND ").append(BeanOfMapping.TrineUTCBeanMapping.topicID).append("=?");
		
		int ret = jdbcTemplate.queryForObject(sql.toString(), new Object[] {topicID}, Integer.class);
		
		return ret;
	}

	@Override
	public List<String[]> getVoteInfoByTopicID(String topicID) {
		StringBuffer findVoteSQL = new StringBuffer(VoteSysConstant.ConjunctiveQuerySQLTemplate.SQL_CQS_TOPIC_FIND_VOTE);
		StringBuffer VoteNumSQL = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_UVR_NUM);
		StringBuffer condition = new StringBuffer(" AND ").append(BeanOfMapping.TopicVoteRelBeanMapping.topicID).append("=?");
		findVoteSQL.append(condition);
		condition.append(" AND ").append(BeanOfMapping.UserVoteRelBeanMapping.voteID).append("=?");
		VoteNumSQL.append(condition);
		List<String[]> list = new ArrayList<String[]>();
		
		jdbcTemplate.query(findVoteSQL.toString(), new Object[] {topicID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String[] result = new String[] {
						rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteCode),
						rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteOBJ),
						jdbcTemplate.queryForObject(VoteNumSQL.toString(), new String[] {
								topicID,
								rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteID)
								}, String.class)
				};
				list.add(result);
			}
		});
		
		
		if (list.size() > 0) {
			return list;
		}
		return null;
	}
}
