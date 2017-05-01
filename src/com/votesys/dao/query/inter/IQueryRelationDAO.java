package com.votesys.dao.query.inter;

import java.util.List;

import com.votesys.bean.UserTopicRelateBean;

/**
 * @ClassName com.votesys.service.query.inter.IQueryRelationSV.java
 * @Description 所有关系表的查询数据层接口
 */
public interface IQueryRelationDAO {

	/**
	 * @Function com.votesys.service.query.inter.IQueryRelationSV::qryThisRelByUserID
	 * @Description 通过用户编号查询用户关联的所有主题
	 * @param userID
	 * @return
	 */
	public List<UserTopicRelateBean> qryUTRelByUserID(String userID);
	
	/**
	 * @Function com.votesys.service.query.inter.IQueryRelationSV::qryUTRelFintUserIDByTopicID
	 * @Description 通过主题编号查找发布人的编号
	 * @param topicID
	 * @return
	 */
	public String qryUTRelFintUserIDByTopicID(String topicID);
}
