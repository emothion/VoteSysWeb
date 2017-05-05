package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.TopicComRelateBean.java
 * @Description 主题与评论的关系数据模型
 */
public class TopicComRelateBean {

	private String relateID;
	private String topicID;
	private String comID;
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
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
