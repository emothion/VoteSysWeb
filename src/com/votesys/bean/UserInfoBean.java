package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.UserInfoBean.java
 * @Description 用户通用信息数据模型
 */
public class UserInfoBean {
	private String userID;//用户编号
	private String userName;//用户姓名
	private String userEmail;//用户电子邮件
	private String userPhone;//用户电话
	private String userKey;//用户密码
	private String status;//用户状态
	private String createTime;//创建时间
	private String remark;//备注信息
	private String retInfo;//返回信息

	public String getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = String.valueOf(userID);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRetInfo() {
		return retInfo;
	}

	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}

}
