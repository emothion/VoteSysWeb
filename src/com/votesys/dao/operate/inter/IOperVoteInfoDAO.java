package com.votesys.dao.operate.inter;

import java.util.List;

import com.votesys.bean.VoteLaunchBean;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperVoteInfoSV.java
 * @Description 投票信息增删改操作数据层接口
 */
public interface IOperVoteInfoDAO {

	/**
	 * @Function com.votesys.service.operate.inter.IOperVoteInfoSV::insertVoteInfo
	 * @Description 批量插入投票项目信息
	 * @param params
	 */
	public boolean insertVoteInfo(List<Object[]> params);
	
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
	public boolean insertVoteInfo(VoteLaunchBean voteLaunch); 
}
