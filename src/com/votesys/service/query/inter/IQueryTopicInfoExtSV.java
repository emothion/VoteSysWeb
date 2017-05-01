package com.votesys.service.query.inter;

import java.util.List;

import com.votesys.bean.TopicExtInfoBean;

/**
 * @ClassName com.votesys.service.query.inter.IQueryTopicInfoExtSV.java
 * @Description 查询主题图片服务层方法
 */
public interface IQueryTopicInfoExtSV {

	/**
	 * @Function com.votesys.service.query.inter.IQueryTopicInfoExtSV::queryMainImageByTopicID
	 * @Description 查询主题封面
	 * @param topicID
	 * @return
	 */
	public TopicExtInfoBean queryMainImageByTopicID(String topicID);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryTopicInfoExtSV::queryImageByTopicID
	 * @Description 查询主题下所有图片
	 * @param topicID
	 * @return
	 */
	public List<TopicExtInfoBean> queryImageByTopicID(String topicID);
}
