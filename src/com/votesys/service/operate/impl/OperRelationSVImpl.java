package com.votesys.service.operate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.dao.operate.inter.IOperRelationDAO;
import com.votesys.service.operate.inter.IOperRelationSV;

/**
 * @ClassName com.votesys.service.operate.impl.OperRelationSVImpl.java
 * @Description 关系表增删改操作服务层实现类
 */
@Service("operRelationService")
public class OperRelationSVImpl implements IOperRelationSV {

	@Resource
	private IOperRelationDAO operRelationDAO;
	
	@Override
	public boolean insertTopicVoteRelate(List<String> votes, String topicID) {
		List<Object[]> params = new ArrayList<Object[]>();
		for (String string : votes) {
			params.add(new Object[] {topicID, string});
		}
		return operRelationDAO.insertTopicVoteRelate(params);
	}

	@Override
	public boolean deleteTopicVoteRelate(String topicID) {
		return operRelationDAO.deleteTopicVoteRelate(topicID);
	}

	@Override
	public boolean insertUserTopicRelate(String userID, String topicID) {
		return operRelationDAO.insertUserTopicRelate(userID, topicID);
	}

}
