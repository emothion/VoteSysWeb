package com.votesys.service.query.inter;

import java.util.List;

import com.votesys.bean.VoteInfoBean;
import com.votesys.bean.VoteLaunchBean;

/**
 * @ClassName com.votesys.service.query.inter.IQueryVoteInfoSV.java
 * @Description 投票项目查询服务层接口
 */
public interface IQueryVoteInfoSV {

	/**
	 * @Function com.votesys.service.query.inter.IQueryVoteInfoSV::queryVoteInfoByRemarks
	 * @Description 通过备注中存的主题编号查询投票项目
	 * @param topicID
	 * @return
	 */
	public List<VoteInfoBean> queryVoteInfoByRemarks(String topicID);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryVoteInfoSV::queryVoteLaunchByTopicID
	 * @Description 通过主题编号查询投票方式的配置
	 * @param topicID
	 * @return
	 */
	public VoteLaunchBean queryVoteLaunchByTopicID(String topicID);
}
