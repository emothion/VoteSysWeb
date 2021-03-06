package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.UserTopicRelateBean.java
 * @Description 用户与主题关系数据模型
 */
public class UserTopicRelateBean {

	private String relateID;
	private String userID;
	private String topicID;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
