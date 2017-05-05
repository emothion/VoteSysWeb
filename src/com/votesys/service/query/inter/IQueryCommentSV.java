package com.votesys.service.query.inter;

import com.votesys.bean.CommentBean;

/**
 * @ClassName com.votesys.service.query.inter.IQueryCommentSV.java
 * @Description 评论查询服务层接口
 */
public interface IQueryCommentSV {

	/**
	 * @Function com.votesys.service.query.inter.IQueryCommentSV::queryUpAndSubNum
	 * @Description 查询被踩或被赞的次数
	 * @param comID
	 * @return
	 */
	public CommentBean queryUpAndSubNum(String comID);

}
