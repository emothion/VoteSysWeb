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
}
