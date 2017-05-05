package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.UserVoteRelateBean.java
 * @Description 用户与投票关系
 */
public class UserVoteRelateBean {

	private String relateID;
	private String userID;
	private String topicID;
	private String voteID;
	private String createTime;
	
	public String getRelateID() {
		return relateID;
	}
	public void setRelateID(String relateID) {
		this.relateID = relateID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
