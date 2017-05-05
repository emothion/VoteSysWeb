package com.votesys.dao.operate.inter;

import java.util.List;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperRelationDAO.java
 * @Description 关系表增删改操作数据层接口
 */
public interface IOperRelationDAO {

	/**
	 * @Function com.votesys.service.operate.inter.IOperRelationSV::insertTopicVoteRelate
	 * @Description 批量插入主题与投票项目的关系
	 * @param params
	 */
	public boolean insertTopicVoteRelate(List<Object[]> params);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperRelationSV::deleteTopicVoteRelate
	 * @Description 删除某个主题下的所有投票项目
	 * @param topicID
	 */
	public boolean deleteTopicVoteRelate(String topicID);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperRelationDAO::insertUserTopicRelate
	 * @Description 插入用户与主题的关系
	 * @param userID
	 * @param topicID
	 * @param createTime
	 * @return
	 */
	public boolean insertUserTopicRelate(String userID, String topicID, String createTime);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperRelationDAO::insertUserComRelate
	 * @Description 插入用户与评论的关系
	 * @param userID
	 * @param comID
	 * @return
	 */
	public boolean insertUserComRelate(String userID, String comID);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperRelationDAO::insertTopicComRelate
	 * @Description 插入主题与评论的关系
	 * @param topicID
	 * @param comID
	 * @return
	 */
	public boolean insertTopicComRelate(String topicID, String comID);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperRelationDAO::insertUserVoteRelate
	 * @Description 插入用户与投票的关系（相当于计一票）
	 * @param params
	 * @return
	 */
	public boolean insertUserVoteRelate(List<Object[]> params);
}
