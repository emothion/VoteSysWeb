package com.votesys.dao.operate.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.votesys.common.VoteSysConstant;
import com.votesys.dao.operate.inter.IOperCommentDAO;

/**
 * @ClassName com.votesys.dao.operate.impl.OperCommentDAOImpl.java
 * @Description 评论增删改操作数据层实现类
 */
@Repository("operCommentDao")
public class OperCommentDAOImpl implements IOperCommentDAO {

	@Resource
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public String insertComment(String comContent) {
		String sql = new String(VoteSysConstant.SQLTemplate.SQL_INSERT_COMMENT);
		int ret = -1;
		
			
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		ret = jdbcTemplate.update(new PreparedStatementCreator() {  
		    @Override  
		    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		        PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        ps.setString(1, comContent);
		        return ps;  
		    }

		}, keyHolder);  
		if (ret > 0) {
			ret = keyHolder.getKey().intValue();  
		}
		
		return String.valueOf(ret);
	}

	@Override
	public boolean upDateSubOrUpNum(String comID, String status) {
		String sql = null;
		if ("O".equals(status)) {
			sql = VoteSysConstant.SQLTemplate.SQL_SUB_NUM_INCREAM;
		}
		if ("E".equals(status)) {
			sql = VoteSysConstant.SQLTemplate.SQL_UP_NUM_INCREAM;
		}
		
		int ret = jdbcTemplate.update(sql, new Object[] {comID});
		
		if (ret > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int updateCommentContent(String comID) {
		return jdbcTemplate.update(VoteSysConstant.SQLTemplate.SQL_UPDATE_COMMENT_CONTENT, new Object[] {"内容已经被屏蔽...", comID});
	}

}
