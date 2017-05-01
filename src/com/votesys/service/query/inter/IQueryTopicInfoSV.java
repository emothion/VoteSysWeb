package com.votesys.service.query.inter;

import com.votesys.bean.TopicInfoBean;

/**
 * @ClassName com.votesys.dao.query.inter.IQueryTopicInfoDAO.java
 * @Description 主题基础信息查询服务层接口
 */
public interface IQueryTopicInfoSV {

	/**
	 * @Function com.votesys.dao.query.inter.IQueryTopicInfoDAO::queryTopicInfoByCreateTime
	 * @Description 通过精确到毫秒级的创建时间去查询主题基础信息
	 * @param createTime
	 */
	public TopicInfoBean queryTopicInfoByCreateTime(String createTime);
	
	/**
	 * @Function com.votesys.dao.query.inter.IQueryTopicInfoDAO::queryTopicInfoByTopicID
	 * @Description 通过主题编号查询主题信息
	 * @param topicID
	 * @return
	 */
	public TopicInfoBean queryTopicInfoByTopicID(String topicID);
}
