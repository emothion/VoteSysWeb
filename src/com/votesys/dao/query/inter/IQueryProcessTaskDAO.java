package com.votesys.dao.query.inter;

import java.util.List;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperProcessTaskDAO.java
 * @Description 定时任务后台进程数据库查询接口
 */
public interface IQueryProcessTaskDAO {
	
	/**
	 * @Function com.votesys.dao.query.inter.IQueryProcessTaskDAO::queryExpTopicOfTopicID
	 * @Description 查询所有超过失效时间的topicID
	 * @param expTime
	 * @return
	 */
	public List<String> queryExpTopicOfTopicID(String expTime);
}
