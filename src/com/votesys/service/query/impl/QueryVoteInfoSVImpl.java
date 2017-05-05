package com.votesys.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.VoteInfoBean;
import com.votesys.bean.VoteLaunchBean;
import com.votesys.dao.query.inter.IQueryVoteInfoDAO;
import com.votesys.service.query.inter.IQueryVoteInfoSV;

/**
 * @ClassName com.votesys.service.query.impl.QueryVoteInfoSVImpl.java
 * @Description 投票项目查询服务层实现类
 */
@Service("queryVoteInfoService") 
public class QueryVoteInfoSVImpl implements IQueryVoteInfoSV {

	@Resource
	private IQueryVoteInfoDAO queryVoteInfoDAO;
	
	@Override
	public List<VoteInfoBean> queryVoteInfoByRemarks(String topicID) {
		return queryVoteInfoDAO.queryVoteInfoByRemarks(topicID);
	}

	@Override
	public VoteLaunchBean queryVoteLaunchByTopicID(String topicID) {
		return queryVoteInfoDAO.queryVoteLaunchByTopicID(topicID);
	}

}
