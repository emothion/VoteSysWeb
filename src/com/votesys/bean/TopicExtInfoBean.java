package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.TopicExtInfoBean.java
 * @Description 主题附加图片信息数据模型
 */
public class TopicExtInfoBean {

	private String extInfoID;
	private String topicID;
	private String topicImg;
	private String extStatus;
	
	public String getExtInfoID() {
		return extInfoID;
	}
	public void setExtInfoID(String extInfoID) {
		this.extInfoID = extInfoID;
	}
	public String getTopicID() {
		return topicID;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getTopicImg() {
		return topicImg;
	}
	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}
	public String getExtStatus() {
		return extStatus;
	}
	public void setExtStatus(String extStatus) {
		this.extStatus = extStatus;
	}
}
