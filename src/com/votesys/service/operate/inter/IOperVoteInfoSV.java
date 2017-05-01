package com.votesys.service.operate.inter;

import java.util.List;

/**
 * @ClassName com.votesys.service.operate.inter.IOperVoteInfoSV.java
 * @Description 投票项目增删改操作服务层接口
 */
public interface IOperVoteInfoSV {

	/**
	 * @Function com.votesys.service.operate.inter.IOperVoteInfoSV::insertVoteInfo
	 * @Description 批量插入投票项目信息
	 * @param voteObjs
	 * @param topicID
	 */
	public boolean insertVoteInfo(List<String> voteObjs, String topicID);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperVoteInfoSV::deleteVoteInfo
	 * @Description 批量删除投票项目信息
	 * @param voteID
	 */
	public boolean deleteVoteInfo(String voteID);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperVoteInfoSV::insertVoteInfo
	 * @Description 插入投票设置信息
	 * @param voteStop
	 * @param voteStyle
	 * @param expTime
	 * @param topicID
	 */
	public boolean insertVoteInfo(String voteStop, String voteStyle, String expTime, String topicID);
}
