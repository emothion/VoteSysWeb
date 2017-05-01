package com.votesys.service.operate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.bean.TopicInfoBean;
import com.votesys.dao.operate.inter.IOperTopicInfoDAO;
import com.votesys.service.operate.inter.IOperTopicInfoSV;
import com.votesys.tools.TimeUtils;

/**
 * @ClassName com.votesys.service.operate.impl.OperTopicInfoSVImpl.java
 * @Description 主题基础信息增删改操作服务层实现类
 */
@Service("operTopicInfoService")
public class OperTopicInfoSVImpl implements IOperTopicInfoSV {

	@Resource
	private IOperTopicInfoDAO operTopicInfoDAO;
	
	@Override
	public String insertTopicInfo(TopicInfoBean topicInfo) {
		topicInfo.setCreateTime(TimeUtils.getSystemTime().toString());//获取系统当前时间
		return operTopicInfoDAO.insertTopicInfo(topicInfo);
	}

	@Override
	public boolean updateTopicInfo(TopicInfoBean topicInfo) {
		return operTopicInfoDAO.updateTopicInfo(topicInfo);
	}

	@Override
	public boolean insertTopicExtInfo(List<String> imgName, String topicID) {
		List<Object[]> params = new ArrayList<Object[]>();
		int num = 0;
		for (String string : imgName) {
			params.add(new Object[] {topicID, string, ++num});
		}
		return operTopicInfoDAO.insertTopicExtInfo(params);
	}

	@Override
	public boolean updateTopicInfoExpTime(String expTime, String topicID) {
		return operTopicInfoDAO.updateTopicInfoExpTime(expTime, topicID);
	}

}
