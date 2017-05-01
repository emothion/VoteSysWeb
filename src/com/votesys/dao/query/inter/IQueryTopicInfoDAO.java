package com.votesys.dao.query.inter;

import java.util.List;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;

/**
 * @ClassName com.votesys.dao.query.inter.IQueryTopicInfoDAO.java
 * @Description 主题基础信息查询数据层接口
 */
public interface IQueryTopicInfoDAO {

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
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryTopicInfoSV::queryTopicInfo
	 * @Description 查询主题（分页）
	 * @param pageInfo
	 * @return
	 */
	public List<TopicInfoBean> queryTopicInfo(PageBean pageInfo, TopicInfoBean topicInfo);
	
	/**
	 * @Function com.votesys.dao.query.inter.IQueryTopicInfoDAO::queryTopicInfoTote
	 * @Description 查询主题总数
	 * @param topicInfo
	 * @return
	 */
	public int queryTopicInfoTote(TopicInfoBean topicInfo);
}
