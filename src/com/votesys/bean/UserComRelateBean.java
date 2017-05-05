package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.UserComRelateBean.java
 * @Description 用户与评论关系模型
 */
public class UserComRelateBean {

	private String relateID;
	private String userID;
	private String comID;
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
