package com.votesys.service.query.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
