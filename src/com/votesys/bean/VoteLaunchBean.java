package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.VoteLaunchBean.java
 * @Description 投票方式类型数据模型
 */
public class VoteLaunchBean {

	private String launchID;
	private String topicID;
	private String voteStyle;
	private String voteStop;
	private String expDate;
	private String remarks;
	
	public String getLaunchID() {
		return launchID;
	}
	public void setLaunchID(String launchID) {
		this.launchID = launchID;
	}
	public String getTopicID() {
		return topicID;
	}
	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
	public String getVoteStyle() {
		return voteStyle;
	}
	public void setVoteStyle(String voteStyle) {
		this.voteStyle = voteStyle;
	}
	public String getVoteStop() {
		return voteStop;
	}
	public void setVoteStop(String voteStop) {
		this.voteStop = voteStop;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
