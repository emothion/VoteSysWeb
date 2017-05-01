package com.votesys.service.operate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.VoteLaunchBean;
import com.votesys.dao.operate.inter.IOperVoteInfoDAO;
import com.votesys.service.operate.inter.IOperVoteInfoSV;

/**
 * @ClassName com.votesys.service.operate.impl.OperVoteInfoSVImpl.java
 * @Description 投票信息增删改操作服务层实现类
 */
@Service("operVoteInfoService")
public class OperVoteInfoSVImpl implements IOperVoteInfoSV {

	@Resource
	private IOperVoteInfoDAO operVoteInfoDAO;
	
	@Override
	public boolean insertVoteInfo(List<String> voteObjs, String topicID) {
		List<Object[]> params = new ArrayList<Object[]>();
		int num = 1;
		for (String string : voteObjs) {
			params.add(new Object[] {string, num++, topicID});
		}
		return operVoteInfoDAO.insertVoteInfo(params);
	}

	@Override
	public boolean deleteVoteInfo(String voteID) {
		return operVoteInfoDAO.deleteVoteInfo(voteID);
	}

	@Override
	public boolean insertVoteInfo(String voteStop, String voteStyle, String expTime, String topicID) {
		VoteLaunchBean voteLaunch = new VoteLaunchBean();
		voteLaunch.setTopicID(topicID);
		voteLaunch.setVoteStop(voteStop);
		voteLaunch.setVoteStyle(voteStyle);
		voteLaunch.setExpDate(expTime);
		return operVoteInfoDAO.insertVoteInfo(voteLaunch);
	}

}
