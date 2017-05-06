package com.votesys.task;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.votesys.dao.operate.inter.IOperProcessTaskDAO;
import com.votesys.dao.query.inter.IQueryProcessTaskDAO;
import com.votesys.tools.TimeUtils;

@Service("taskJob")
public class TaskJob {

	private static Logger logger = Logger.getLogger(TaskJob.class);
	
	@Resource
	private IOperProcessTaskDAO operProcessTaskDAO;
	@Resource
	private IQueryProcessTaskDAO queryProcessTaskDAO;
	
	public void autoPublishTopic() {
		String nowTime = TimeUtils.formatTimeUtil(TimeUtils.YYYYMMDDHHMMSS, TimeUtils.getSystemTime());
		int ret = operProcessTaskDAO.updateTopicStatusToU(nowTime);
		String retMessage = nowTime + "自动发布任务已经完成：一共发布了"+ret+"条主题";
		logger.debug(retMessage);
	}
	
	public void autoCloseTopic() {
		String nowTime = TimeUtils.formatTimeUtil(TimeUtils.YYYYMMDDHHMMSS, TimeUtils.getSystemTime());
		int[] ret = null;
		List<String> topicIDList = queryProcessTaskDAO.queryExpTopicOfTopicID(nowTime);
		
		if (topicIDList.size() > 0) {
			ret = operProcessTaskDAO.updateTopicStatusToS(topicIDList);
		}
		
		String retMessate = nowTime + "自动结束任务已经完成：一共结束了"+ret.length+"条主题";
		logger.debug(retMessate);
	}
}
