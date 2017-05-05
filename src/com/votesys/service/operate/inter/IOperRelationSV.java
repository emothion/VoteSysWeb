package com.votesys.service.operate.inter;

import java.util.List;

/**
 * @ClassName com.votesys.service.operate.inter.IOperRelationSV.java
 * @Description 所有关系表的增删改操作服务层接口
 */
public interface IOperRelationSV {

	/**
	 * @Function com.votesys.service.operate.inter.IOperRelationSV::insertTopicVoteRelate
	 * @Description 批量插入主题与投票项目的关系
	 * @param votes
	 * @param topic
	 */
	public boolean insertTopicVoteRelate(List<String> votes, String topicID);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperRelationSV::deleteTopicVoteRelate
	 * @Description 删除某个主题下的所有投票项目
	 * @param topicID
	 */
	public boolean deleteTopicVoteRelate(String topicID);

	/**
	 * @Function com.votesys.service.operate.inter.IOperRelationSV::insertUserTopicRelate
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
	 * @Function com.votesys.service.operate.inter.IOperRelationSV::insertUserVoteRelate
	 * @Description 插入用户与投票的关系（相当于计一票）
	 * @param userID
	 * @param topicID
	 * @param vote
	 * @return
	 */
	public boolean insertUserVoteRelate(String userID, String topicID, String[] votes);
}
