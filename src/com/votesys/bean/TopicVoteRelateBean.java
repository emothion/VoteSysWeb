package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.TopicVoteRelateBean.java
 * @Description 主题与投票项目关系数据模型
 */
public class TopicVoteRelateBean {

	private String relateID;
	private String topicID;
	private String voteID;
	private String createTime;
	
	public String getRelateID() {
		return relateID;
	}
	public void setRelateID(String relateID) {
		this.relateID = relateID;
	}
	public String getTopicID() {
		return topicID;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getVoteID() {
		return voteID;
	}
	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
