package com.votesys.dao.query.inter;

import com.votesys.bean.CommentBean;

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
}
