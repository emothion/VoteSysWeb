package com.votesys.service.query.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.CommentBean;
import com.votesys.dao.query.inter.IQueryCommentDAO;
import com.votesys.service.query.inter.IQueryCommentSV;

/**
 * @ClassName com.votesys.service.query.impl.QueryCommentSVImpl.java
 * @Description 评论查询服务层实现类
 */
@Service("queryCommentService")
public class QueryCommentSVImpl implements IQueryCommentSV {

	@Resource
	private IQueryCommentDAO queryCommentDAO;
	
	@Override
	public CommentBean queryUpAndSubNum(String comID) {
		return queryCommentDAO.queryUpAndSubNum(comID);
	}

}
