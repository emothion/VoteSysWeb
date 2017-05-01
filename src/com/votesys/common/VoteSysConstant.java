package com.votesys.common;

public class VoteSysConstant {

	public static final String ERR0001 = "登陆用户名不能为空";
	public static final String ERR0002 = "登陆使用的电子邮箱为空";
	public static final String ERR0003 = "登陆使用的手机号为空";
	public static final String ERR0004 = "登陆用户密码不能为空";
	public static final String Code = "Code";
	public static final String Message = "retMsg";
	public static final String CONST00001 = "retInfo";
	
	public class SQLTemplate {
		public static final String SQL_QUERY_USER_ALL_INFO = "SELECT * FROM `v_user_all_info` WHERE 1";
		
		public static final String SQL_QUERY_USER_INFO = "SELECT * FROM `t_user_info` WHERE 1";
		public static final String SQL_INSERT_USER_INFO = "INSERT INTO `t_user_info`(?) VALUES (?)";
		public static final String SQL_UPDATE_USER_INFO = "UPDATE `t_user_info` SET ";
		
		public static final String SQL_INSERT_USER_INFO_DTL = "INSERT INTO `t_user_info_dtl`(`USER_ID`) VALUES (?)";
		public static final String SQL_UPDATE_USER_INFO_DTL = "UPDATE `t_user_info_dtl` SET ";
		
		public static final String SQL_QUERY_TOPIC_INFO = "SELECT * FROM `t_topic_info` WHERE 1";
		public static final String SQL_INSERT_TOPIC_INFO = "INSERT INTO `t_topic_info`(`TOPIC_TITLE`, `TOPIC_CONTENT`, `TOPIC_STATUS`, `CREATE_TIME`, `EFF_TIME`) VALUES (";
		public static final String SQL_UPDATE_TOPIC_INFO = "UPDATE `t_topic_info` SET `TOPIC_TITLE`=?,`TOPIC_CONTENT`=?,`TOPIC_STATUS`=?,`EFF_TIME`=? WHERE `TOPIC_ID`=?";
		
		public static final String SQL_QUERY_TOPIC_INFO_EXT = "SELECT * FROM `t_topic_info_dtl` WHERE 1";
		public static final String SQL_INSERT_TOPIC_INFO_EXT = "INSERT INTO `t_topic_info_dtl`(`TOPIC_ID`, `TOPIC_IMG`) VALUES (?,?)";
		
		public static final String SQL_INSERT_TVR = "INSERT INTO `t_topic_vote_relate`(`TOPIC_ID`, `VOTE_ID`) VALUES (?,?)";
		public static final String SQL_DELETE_TVR = "DELETE FROM `t_topic_vote_relate` WHERE `TOPIC_ID`=?";
		
		public static final String SQL_QUERY_VOTE_INTO = "SELECT * FROM `t_vote_info` WHERE 1";
		public static final String SQL_INSERT_VOTE_INFO = "INSERT INTO `t_vote_info`(`VOTE_OBJ`, `VOTE_CODE`, `REMARK`) VALUES (?,?,?)";
		public static final String SQL_DELETE_VOTE_INFO = "DELETE FROM `t_vote_info` WHERE `VOTE_ID`=?";
		
		public static final String SQL_INSERT_VOTE_LAUNCH = "INSERT INTO `t_vote_launch`(`TOPIC_ID`, `VOTE_STYLE`, `VOTE_STOP`, `EXP_DATE`) VALUES (";
		
		public static final String SQL_QUERY_UTR = "SELECT * FROM `t_user_topic_relate` WHERE 1";
		public static final String SQL_INSERT_UTR = "INSERT INTO `t_user_topic_relate`(`USER_ID`, `TOPIC_ID`) VALUES (?,?)";
	}
}
