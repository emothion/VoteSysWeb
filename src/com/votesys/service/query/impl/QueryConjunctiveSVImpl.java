package com.votesys.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.dao.query.inter.IQueryConjunctiveDAO;
import com.votesys.qbo.bean.TrineUTCBean;
import com.votesys.service.query.inter.IQueryConjunctiveSV;

/**
 * @ClassName com.votesys.service.query.inter.IQueryConjunctiveSV.java
 * @Description 联合查询服务实现类
 */
@Service("queryConjunctiveService")
public class QueryConjunctiveSVImpl implements IQueryConjunctiveSV {

	@Resource
	private IQueryConjunctiveDAO queryConjunctiveDAO; 
	
	@Override
	public List<TopicInfoBean> qryTopicInfoByUserID(PageBean pageinfo, String userID) {
		return queryConjunctiveDAO.qryTopicInfoByUserID(pageinfo, userID);
	}

	@Override
	public int qryTopicInfoCount(String userID, String topicStatus, String topicTitle) {
		return queryConjunctiveDAO.qryTopicInfoCount(userID, topicStatus, topicTitle);
	}

	@Override
	public List<TopicInfoBean> qryTopicInfoWithUserIDByCondition(PageBean pageInfo, TopicInfoBean topicInfo,
			String userID) {
		return queryConjunctiveDAO.qryTopicInfoWithUserIDByCondition(pageInfo, topicInfo, userID);
	}

	@Override
	public List<TrineUTCBean> qryCommentByTopicID(String topicID, PageBean pageInfo) {
		return queryConjunctiveDAO.qryCommentByTopicID(topicID, pageInfo);
	}

	@Override
	public List<TrineUTCBean> qryCommentByComID(String comID) {
		return queryConjunctiveDAO.qryCommentByComID(comID);
	}

	@Override
	public int qryCommentCountByTopicID(String topicID) {
		return queryConjunctiveDAO.qryCommentCountByTopicID(topicID);
	}

}
