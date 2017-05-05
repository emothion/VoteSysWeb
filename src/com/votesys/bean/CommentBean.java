package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.CommentBean.java
 * @Description 评论数据模型
 */
public class CommentBean {

	private String comID;
	private String comContent;
	private String comStatus;
	private String createTime;
	private int subNum;
	private int upNum;
	
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public String getComStatus() {
		return comStatus;
	}
	public void setComStatus(String comStatus) {
		this.comStatus = comStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getSubNum() {
		return subNum;
	}
	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}
	public int getUpNum() {
		return upNum;
	}
	public void setUpNum(int upNum) {
		this.upNum = upNum;
	}
}
