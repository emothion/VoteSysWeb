package com.votesys.tools;

import java.util.List;

import com.votesys.bean.PageBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.dao.query.impl.QueryConjunctiveDAOImpl;

public class JUnitTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TopicInfoBean topicInfo = new TopicInfoBean();
		PageBean pageInfo = new PageBean(1, 5);
		topicInfo.setTopicStatus("U");
		QueryConjunctiveDAOImpl qry = new QueryConjunctiveDAOImpl();
		List<TopicInfoBean> topicList = qry.qryTopicInfoWithUserIDByCondition(pageInfo, topicInfo, "0000000001");
	}
}
