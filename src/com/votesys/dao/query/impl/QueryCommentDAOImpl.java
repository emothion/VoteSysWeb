package com.votesys.dao.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.votesys.bean.CommentBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.query.inter.IQueryCommentDAO;
import com.votesys.tools.BeanUtils;

/**
 * @ClassName com.votesys.dao.query.impl.QueryCommentDAOImpl.java
 * @Description 评论查询数据层实现类
 */
@Repository("queryCommentDao")
public class QueryCommentDAOImpl implements IQueryCommentDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CommentBean queryUpAndSubNum(String comID) {
		StringBuffer sql = new StringBuffer(VoteSysConstant.SQLTemplate.SQL_QUERY_COMMENT);
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		sql.append(" AND ").append(BeanOfMapping.CommentBeanMapping.comID).append("=?");
		
		jdbcTemplate.query(sql.toString(), new Object[] {comID}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CommentBean comment = BeanUtils.setCommentBean(rs);
				commentList.add(comment);
			}
		});
		
		if (commentList.size() > 0) {
			return commentList.get(0);
		}
		return null;
	}

}
