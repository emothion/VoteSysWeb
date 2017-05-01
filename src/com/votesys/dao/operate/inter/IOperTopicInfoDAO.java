package com.votesys.dao.operate.inter;

import java.util.List;

import com.votesys.bean.TopicInfoBean;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperTopicInfoDAO.java
 * @Description 主题基础信息增删改操作类数据层接口
 */
public interface IOperTopicInfoDAO {

	/**
	 * @Function com.votesys.dao.operate.inter.IOperTopicInfoDAO::insertTopicInfo
	 * @Description 插入主题基础信息
	 * @param topicInfo
	 * @return String CreateTime 创建时间
	 */
	public String insertTopicInfo(TopicInfoBean topicInfo);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperTopicInfoDAO::updateTopicInfo
	 * @Description 更新主题基础信息
	 * @param topicInfo
	 * @return
	 */
	public boolean updateTopicInfo(TopicInfoBean topicInfo);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperTopicInfoDAO::insertTopicInfo
	 * @Description 插入主题信息信息
	 * @param imgName
	 * @param topicID
	 */
	public boolean insertTopicExtInfo(List<Object[]> params);
}
