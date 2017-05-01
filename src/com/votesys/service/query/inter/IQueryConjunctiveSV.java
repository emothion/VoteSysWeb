package com.votesys.service.query.inter;

import java.util.List;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;

/**
 * @ClassName com.votesys.service.query.inter.IQueryConjunctiveSV.java
 * @Description 联合查询服务接口
 */
public interface IQueryConjunctiveSV {

	/**
	 * @Function com.votesys.dao.query.inter.IQueryConjunctiveDAO::qryTopicInfoByUserID
	 * @Description 通过userID查询出关联的主题（t_user_topic_relate ,t_topic_info）
	 * @param pageinfo
	 * @param userID
	 * @return
	 */
	public List<TopicInfoBean> qryTopicInfoByUserID(PageBean pageinfo, String userID);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryConjunctiveSV::qryTopicInfoCount
	 * @Description 三种入参下能够查询到的记录的总数（t_user_topic_relate ,t_topic_info）
	 * @param userID
	 * @param topicStatus
	 * @param topicTitle
	 * @return
	 */
	public int qryTopicInfoCount(String userID, String topicStatus, String topicTitle);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryConjunctiveSV::qryTopicInfoWithUserIDByCondition
	 * @Description 通过多个条件查询用户关联的主题（t_user_topic_relate ,t_topic_info）
	 * @param pageInfo
	 * @param topicInfo
	 * @param userID
	 * @return
	 */
	public List<TopicInfoBean> qryTopicInfoWithUserIDByCondition(PageBean pageInfo, TopicInfoBean topicInfo, String userID);
}
