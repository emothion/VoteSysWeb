package com.votesys.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.dao.query.inter.IQueryTopicInfoDAO;
import com.votesys.service.query.inter.IQueryTopicInfoSV;

/**
 * @ClassName com.votesys.service.query.impl.QueryTopicInfoSVImpl.java
 * @Description 主题基础信息查询服务层实现类
 */
@Service("queryTopicInfoService")
public class QueryTopicInfoSVImpl implements IQueryTopicInfoSV {

	@Resource
	private IQueryTopicInfoDAO queryTopicInfoDAO;
	
	@Override
	public TopicInfoBean queryTopicInfoByCreateTime(String createTime) {
		return queryTopicInfoDAO.queryTopicInfoByCreateTime(createTime);
	}

	@Override
	public TopicInfoBean queryTopicInfoByTopicID(String topicID) {
		return queryTopicInfoDAO.queryTopicInfoByTopicID(topicID);
	}

	@Override
	public List<TopicInfoBean> queryTopicInfo(PageBean pageInfo, TopicInfoBean topicInfo) {
		return queryTopicInfoDAO.queryTopicInfo(pageInfo, topicInfo);
	}

	@Override
	public int queryTopicInfoTote(TopicInfoBean topicInfo) {
		return queryTopicInfoDAO.queryTopicInfoTote(topicInfo);
	}

	@Override
	public List<TopicInfoBean> queryTopicInfoForManager(PageBean pageInfo, TopicInfoBean topicInfo) {
		return queryTopicInfoDAO.queryTopicInfoForManager(pageInfo, topicInfo);
	}

	@Override
	public int queryTopicInfoForManagerTote(TopicInfoBean topicInfo) {
		return queryTopicInfoDAO.queryTopicInfoForManagerTote(topicInfo);
	}

}
