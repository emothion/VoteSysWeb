package com.votesys.dao.query.inter;

import java.util.List;

import com.votesys.bean.CommentBean;
import com.votesys.bean.PageBean;

/**
 * @ClassName com.votesys.dao.query.inter.IQueryCommentDAO.java
 * @Description 评论查询数据层接口
 */
public interface IQueryCommentDAO {

	/**
	 * @Function com.votesys.service.query.inter.IQueryCommentSV::queryUpAndSubNum
	 * @Description 查询被踩或被赞的次数
	 * @param comID
	 * @return
	 */
	public CommentBean queryUpAndSubNum(String comID);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryCommentSV::queryUpAndSubNum
	 * @Description 查询评论
	 * @param pageInfo
	 * @return
	 */
	public List<CommentBean> queryComment(PageBean pageInfo);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryCommentSV::queryUpAndSubNum
	 * @Description 查询数量
	 * @param pageInfo
	 * @return
	 */
	public int queryCommentTote();
}
