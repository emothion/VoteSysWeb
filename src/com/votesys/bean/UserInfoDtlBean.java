package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.UserInfoDtlBean.java
 * @Description 用户扩展信息数据模型
 */
public class UserInfoDtlBean {

	private String userExtID;//用户扩展信息编号
	private String userID;//用户编号 
	private String userSexy;//性别 
	private String userBirth;//用户生日 
	private String userProvince;//省 
	private String userCity;//市 
	private String userRegion;//县/区 
	private String userImg;//头像 
	private String userDesc;//签名
	
	public String getUserExtID() {
		return userExtID;
	}
	public void setUserExtID(String userExtID) {
		this.userExtID = userExtID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserSexy() {
		return userSexy;
	}
	public void setUserSexy(String userSexy) {
		this.userSexy = userSexy;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserProvince() {
		return userProvince;
	}
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserRegion() {
		return userRegion;
	}
	public void setUserRegion(String userRegion) {
		this.userRegion = userRegion;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
}
