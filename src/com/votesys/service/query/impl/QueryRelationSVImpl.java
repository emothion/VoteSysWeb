package com.votesys.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.UserTopicRelateBean;
import com.votesys.dao.query.inter.IQueryRelationDAO;
import com.votesys.service.query.inter.IQueryRelationSV;

/**
 * @ClassName com.votesys.service.query.impl.QueryRelationSVImpl.java
 * @Description 所有关系表查询服务层实现类
 */
@Service("queryRelationService")
public class QueryRelationSVImpl implements IQueryRelationSV {

	@Resource
	private IQueryRelationDAO queryRelationDAO;
	
	@Override
	public List<UserTopicRelateBean> qryUTRelByUserID(String userID) {
		return queryRelationDAO.qryUTRelByUserID(userID);
	}

	@Override
	public String qryUTRelFindUserIDByTopicID(String topicID) {
		return queryRelationDAO.qryUTRelFindUserIDByTopicID(topicID);
	}

	@Override
	public int qryVoteOpitonGetCount(String voteID) {
		return queryRelationDAO.qryVoteOpitonGetCount(voteID);
	}

	@Override
	public boolean qryUserExistUserVote(String userID, String topicID) {
		return queryRelationDAO.qryUserExistUserVote(userID, topicID);
	}

}
