package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.VoteInfoBean.java
 * @Description 投票项目数据模型
 */
public class VoteInfoBean {

	private String voteID;//投票编号
	private String voteOBJ;//项目内容
	private String voteCode;//项目编码
	private String voteStatus;//项目状态
	private String createTime;//创建时间
	private String remark;//备注（暂时装主题编号）
	
	public String getVoteID() {
		return voteID;
	}
	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}
	public String getVoteOBJ() {
		return voteOBJ;
	}
	public void setVoteOBJ(String voteOBJ) {
		this.voteOBJ = voteOBJ;
	}
	public String getVoteCode() {
		return voteCode;
	}
	public void setVoteCode(String voteCode) {
		this.voteCode = voteCode;
	}
	public String getVoteStatus() {
		return voteStatus;
	}
	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
