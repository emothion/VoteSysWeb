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
}
