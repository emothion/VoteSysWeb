package com.votesys.service.operate.inter;

import java.util.List;

import com.votesys.bean.TopicInfoBean;

/**
 * @ClassName com.votesys.service.operate.inter.IOperTopicInfoSV.java
 * @Description 主题基础信息增删改服务层接口
 */
public interface IOperTopicInfoSV {

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
	 */
	public boolean updateTopicInfo(TopicInfoBean topicInfo);
	
	/**
	 * @Function com.votesys.service.operate.inter.IOperTopicInfoSV::insertTopicInfo
	 * @Description 插入主题的图片信息
	 * @param imgName
	 * @param topicID
	 */
	public boolean insertTopicExtInfo(List<String> imgName, String topicID);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperTopicInfoDAO::updateTopicInfoExpTime
	 * @Description 修改主题的失效时间
	 * @param expTime
	 * @param topicID
	 * @return
	 */
	public boolean updateTopicInfoExpTime(String expTime, String topicID);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperTopicInfoDAO::updateTopicInfoSetStatusS
	 * @Description 修改主题状态
	 * @param topicID
	 * @param topicStatus
	 * @return
	 */
	public boolean updateTopicInfoSetStatusS(String topicID, String topicStatus);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperProcessTaskDAO::updateTopicStatusToU
	 * @Description 将预发布状态的主题改为发布状态
	 * @return
	 */
	public int updateTopicStatusToU(String topicID);
}
