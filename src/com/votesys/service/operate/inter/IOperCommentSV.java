package com.votesys.service.operate.inter;

/**
 * @ClassName com.votesys.service.operate.inter.IOperCommentSV.java
 * @Description 评论增删改操作服务层接口
 */
public interface IOperCommentSV {

	/**
	 * @Function com.votesys.service.operate.inter.IOperCommentSV::insertComment
	 * @Description 插入评论内容
	 * @param comContent
	 * @return
	 */
	public String insertComment(String comContent);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperCommentDAO::upDateSubOrUpNum
	 * @Description 踩或赞
	 * @param comID
	 * @param status
	 * @return
	 */
	public boolean upDateSubOrUpNum(String comID, String status);
}
