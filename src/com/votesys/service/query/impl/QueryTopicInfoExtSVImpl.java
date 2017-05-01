package com.votesys.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.TopicExtInfoBean;
import com.votesys.dao.query.inter.IQueryTopicInfoExtDAO;
import com.votesys.service.query.inter.IQueryTopicInfoExtSV;

/**
 * @ClassName com.votesys.service.query.impl.QueryTopicInfoExtSVImpl.java
* @Description 查询主题包含的图片服务层实现类
 */
@Service("queryTopicInfoExtService")
public class QueryTopicInfoExtSVImpl implements IQueryTopicInfoExtSV {

	@Resource
	private IQueryTopicInfoExtDAO queryTopicInfoExtDAO;
	
	@Override
	public TopicExtInfoBean queryMainImageByTopicID(String topicID) {
		return queryTopicInfoExtDAO.queryMainImageByTopicID(topicID);
	}

	@Override
	public List<TopicExtInfoBean> queryImageByTopicID(String topicID) {
		return queryTopicInfoExtDAO.queryImageByTopicID(topicID);
	}

}
