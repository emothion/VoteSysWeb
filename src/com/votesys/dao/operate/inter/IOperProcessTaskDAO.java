package com.votesys.dao.operate.inter;

import java.util.List;

/**
 * @ClassName com.votesys.dao.operate.inter.IOperProcessTaskDAO.java
 * @Description 定时任务后台进程数据库操作接口
 */
public interface IOperProcessTaskDAO {

	/**
	 * @Function com.votesys.dao.operate.inter.IOperProcessTaskDAO::updateTopicStatusToU
	 * @Description 将预发布状态的主题改为发布状态
	 * @return
	 */
	public int updateTopicStatusToU(String effTime);
	
	/**
	 * @Function com.votesys.dao.operate.inter.IOperProcessTaskDAO::updateTopicStatusToU
	 * @Description 将发布状态的主题改为结束状态
	 * @return
	 */
	public int[] updateTopicStatusToS(List<String> topicIDList);
}
