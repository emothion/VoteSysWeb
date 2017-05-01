package com.votesys.dao.query.inter;

import java.util.List;

import com.votesys.bean.TopicExtInfoBean;

/**
 * @ClassName com.votesys.dao.query.inter.IQueryTopicInfoExtDAO.java
 * @Description 查询主题包含的图片数据层接口
 */
public interface IQueryTopicInfoExtDAO {

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
