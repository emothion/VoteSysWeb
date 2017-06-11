package com.votesys.common;

public class VoteSysConstant {

	public static final String ERR0001 = "登陆用户名不能为空";
	public static final String ERR0002 = "登陆使用的电子邮箱为空";
	public static final String ERR0003 = "登陆使用的手机号为空";
	public static final String ERR0004 = "登陆用户密码不能为空";
	
	public static final String Code = "Code";
	public static final String Message = "retMsg";
	public static final String CONST00001 = "retInfo";
	
	/*** 投票界面绘制单选按键*/
	public static final String radioBtn = "<div class='radio'><label><input type='radio' name='radioBtn' value='";
	/*** 投票界面绘制复选按键*/
	public static final String checkBox = "<div class='checkbox'><label><input type='checkbox' name='checkBoxBtn[]' value='";
	/*** 主题发起人可以关闭主题的按键*/
	public static final String stopBtn = "<button type='button' class='btn btn-info' onclick='stopTopic()'><span class='glyphicon glyphicon-ok' aria-hidden='true'></span>&nbsp;Ok</button>";
	/*** 初始化评论区时下一页的按键绘制*/
	public static final String comNextPage = "<button type='button' class='btn btn-default btn-block' onclick='getNextCommentPage(this)'>加载下一页<span class='glyphicon glyphicon-menu-down' aria-hidden='true'></span></button>";
	/*** 初始化评论区时没有更多的评论的提示绘制*/
	public static final String noMoreCom = "<button type='button' class='btn btn-default btn-block' onclick='getNextCommentPage(this)' disabled='disabled'>没有更多了<span class='glyphicon glyphicon-menu-down' aria-hidden='true'></span></button>";
	
	public class SQLTemplate {
		
		/*** 查询用户所有信息*/
		public static final String SQL_QUERY_USER_ALL_INFO = "SELECT * FROM `v_user_all_info` WHERE 1";
		
		/*** 查询用户基础信息系*/
		public static final String SQL_QUERY_USER_INFO = "SELECT * FROM `t_user_info` WHERE 1";
		/*** 查询用户基础信息数量*/
		public static final String SQL_QUERY_USER_INFO_TOTE = "SELECT COUNT(*) FROM `t_user_info` WHERE 1";
		/*** 插入用户基本信息，内容与?有关*/
		public static final String SQL_INSERT_USER_INFO = "INSERT INTO `t_user_info`(?) VALUES (?)";
		/*** 修改用户信息SQL模板*/
		public static final String SQL_UPDATE_USER_INFO = "UPDATE `t_user_info` SET ";
		
		/*** 插入用户详细信息但是只插入用户编号（相当于初始化）*/
		public static final String SQL_INSERT_USER_INFO_DTL = "INSERT INTO `t_user_info_dtl`(`USER_ID`) VALUES (?)";
		/*** 修改用户扩展信息SQL模板*/
		public static final String SQL_UPDATE_USER_INFO_DTL = "UPDATE `t_user_info_dtl` SET ";
		
		/*** 查询主题总数的SQL模板*/
		public static final String SQL_QUERY_TOPIC_INFO_TOTE = "SELECT COUNT(*) FROM `t_topic_info` WHERE 1 ";
		/*** 查询主题信息的SQL模板*/
		public static final String SQL_QUERY_TOPIC_INFO = "SELECT * FROM `t_topic_info` WHERE 1";
		/*** 插入主题信息的SQL模板*/
		public static final String SQL_INSERT_TOPIC_INFO = "INSERT INTO `t_topic_info`(`TOPIC_TITLE`, `TOPIC_CONTENT`, `TOPIC_STATUS`, `CREATE_TIME`, `EFF_TIME`) VALUES (";
		/*** 修改主题信息的SQL模板*/
		public static final String SQL_UPDATE_TOPIC_INFO = "UPDATE `t_topic_info` SET `TOPIC_TITLE`=?,`TOPIC_CONTENT`=?,`TOPIC_STATUS`=?,`EFF_TIME`=? WHERE `TOPIC_ID`=?";
		/***修改主题为发布状态 */
		public static final String SQL_UPDATE_PUBLISH_TOPIC = "UPDATE `t_topic_info` SET `TOPIC_STATUS`='U',`EFF_TIME`=current_timestamp() WHERE `TOPIC_STATUS` = 'P' AND `TOPIC_ID`=?";
		/*** 修改主题信息中失效时间的SQL模板*/
		public static final String SQL_UPDATE_TOPIC_INFO_EXPTIME = "UPDATE `t_topic_info` SET `EXP_TIME`=? WHERE `TOPIC_ID`=?";
		/*** 修改主题信息中的主题状态的SQL模板*/
		public static final String SQL_UPDATE_TOPIC_INFO_STATUS = "UPDATE `t_topic_info` SET `TOPIC_STATUS`=?, `EXP_TIME`=current_timestamp() WHERE `TOPIC_ID`=?";
		
		/*** 查询主题详细信息的SQL模板*/
		public static final String SQL_QUERY_TOPIC_INFO_EXT = "SELECT * FROM `t_topic_info_dtl` WHERE 1";
		/*** 插入主题详细信息的SQL模板*/
		public static final String SQL_INSERT_TOPIC_INFO_EXT = "INSERT INTO `t_topic_info_dtl`(`TOPIC_ID`, `TOPIC_IMG`,`IMG_ORDER`) VALUES (?,?,?)";
		
		/*** 插入主题与投票项目关系的SQL模板*/
		public static final String SQL_INSERT_TVR = "INSERT INTO `t_topic_vote_relate`(`TOPIC_ID`, `VOTE_ID`) VALUES (?,?)";
		/*** 删除主题与投票项目关系的SQL模板*/
		public static final String SQL_DELETE_TVR = "DELETE FROM `t_topic_vote_relate` WHERE `TOPIC_ID`=?";
		
		/*** 查询投票项目信息的SQL模板*/
		public static final String SQL_QUERY_VOTE_INTO = "SELECT * FROM `t_vote_info` WHERE 1";
		/*** 插入投票项目信息的SQL模板*/
		public static final String SQL_INSERT_VOTE_INFO = "INSERT INTO `t_vote_info`(`VOTE_OBJ`, `VOTE_CODE`, `REMARK`) VALUES (?,?,?)";
		/*** 删除投票项目信息的SQL模板*/
		public static final String SQL_DELETE_VOTE_INFO = "DELETE FROM `t_vote_info` WHERE `VOTE_ID`=?";
		/*** 查询投票项目设置的SQL模板*/
		public static final String SQL_QUERY_VOTE_LAUNCH = "SELECT * FROM `t_vote_launch` WHERE 1";
		/*** 插入投票项目设置的SQL模板*/
		public static final String SQL_INSERT_VOTE_LAUNCH = "INSERT INTO `t_vote_launch`(`TOPIC_ID`, `VOTE_STYLE`, `VOTE_STOP`, `EXP_DATE`) VALUES (";
		/*** 修改投票项目设置的SQL模板*/
		public static final String SQL_UPDATE_VOTE_LAUNCH = "UPDATE `t_vote_launch` SET `EXP_DATE`=current_timestamp() WHERE `TOPIC_ID`=?";
		
		/*** 查询用户参与投票情况（用户与投票项目关系）的SQL模板*/
		public static final String SQL_QUERY_UVR = "SELECT * FROM `t_user_vote_relate` WHERE 1";
		/*** 查询用户参与投票情况（用户与投票项目关系）数量的SQL模板*/
		public static final String SQL_QUERY_UVR_NUM = "SELECT COUNT(*) FROM `t_user_vote_relate` WHERE 1";
		/*** 插入用户参与投票情况（用户与投票项目关系）的SQL模板*/
		public static final String SQL_INSERT_UVR = "INSERT INTO `t_user_vote_relate`(`USER_ID`, `TOPIC_ID`, `VOTE_ID`, `VOTE_CODE`) VALUES (?,?,?,?)";
		
		/*** 查询用户与主题关系的SQL模板*/
		public static final String SQL_QUERY_UTR = "SELECT * FROM `t_user_topic_relate` WHERE 1";
		/*** 插入用户与主题关系的SQL模板*/
		public static final String SQL_INSERT_UTR = "INSERT INTO `t_user_topic_relate`(`USER_ID`, `TOPIC_ID`, `CREATE_TIME`) VALUES (?,?,?)";
		
		/*** 查询评论信息的SQL模板*/
		public static final String SQL_QUERY_COMMENT = "SELECT * FROM `t_comment` WHERE 1";
		/*** 查询评论信息总数的SQL模板*/
		public static final String SQL_QUERY_COMMENT_TOTE = "SELECT COUNT(*) FROM `t_comment` WHERE 1";
		/*** 修改评论内容的SQL模板*/
		public static final String SQL_UPDATE_COMMENT_CONTENT = "UPDATE `t_comment` SET `COM_CONTENT`=? WHERE `COM_ID`=?";
		/*** 踩评论的SQL模板*/
		public static final String SQL_SUB_NUM_INCREAM = "UPDATE `t_comment` SET `SUB_NUM`=`SUB_NUM`+1 WHERE `COM_ID`=?";
		/*** 赞评论的SQL模板*/
		public static final String SQL_UP_NUM_INCREAM = "UPDATE `t_comment` SET `UP_NUM`=`UP_NUM`+1 WHERE `COM_ID`=?";
		
		/*** 插入评论的SQL模板*/
		public static final String SQL_INSERT_COMMENT = "INSERT INTO `t_comment`(`COM_CONTENT`) VALUES (?)";
		/*** 插入用户与评论关系的SQL模板*/
		public static final String SQL_INSERT_UCR = "INSERT INTO `t_user_com_relate`(`USER_ID`, `COM_ID`) VALUES (?,?)";
		/*** 插入主题与评论关系的SQL模板*/
		public static final String SQL_INSERT_TCR = "INSERT INTO `t_topic_com_relate`(`TOPIC_ID`, `COM_ID`) VALUES (?,?)";
		/*** 取出最近一次插入的记录的主键*/
		public static final String SQL_QUERY_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
	}
	
	public class ConjunctiveQuerySQLTemplate {
		
		/*** 通过用户编号查询用户下所有的主题信息(按CREATE_TIME降序排列)*/
		public static final String SQL_CQS_USER_FIND_TOPIC = "SELECT A.`RELATE_ID`,A.`USER_ID`,B.`TOPIC_ID`,B.`TOPIC_TITLE`,B.`TOPIC_CONTENT`,B.`TOPIC_STATUS`,B.`CREATE_TIME`,B.`EFF_TIME`,B.`EXP_TIME`,B.`REMARK` FROM `vote_sys`.`t_user_topic_relate` A,`vote_sys`.`t_topic_info` B WHERE A.`TOPIC_ID` = B.`TOPIC_ID` AND A.USER_ID=? ORDER by B.`CREATE_TIME` DESC LIMIT ?,?";
		
		/*** 获取用户下所有的主题总数*/
		public static final String SQL_CQS_USER_FIND_TOPIC_TOTE = "SELECT COUNT(*) FROM `t_user_topic_relate` A,`t_topic_info` B WHERE A.`TOPIC_ID` = B.`TOPIC_ID`";
		
		/*** 获取评论，评论者名字头像*/
		public static final String SQL_CQS_TRINE_UTC = "SELECT `TOPIC_ID`, `USER_ID`, `USER_NAME`, `USER_IMG`, `COM_ID`, `COM_CONTENT`, `COM_STATUS`, `CREATE_TIME`, `SUB_NUM`, `UP_NUM` FROM `v_user_topic_com_trine` WHERE 1";
		
		/*** 查询某一主题下的评论总数*/
		public static final String SQL_CQS_TRINE_UTC_TOTE = "SELECT COUNT(*) FROM `v_user_topic_com_trine` WHERE 1 ";
		
		/*** 主题下的所有投票信息*/
		public static final String SQL_CQS_TOPIC_FIND_VOTE = "SELECT b.VOTE_ID, b.VOTE_OBJ, b.VOTE_CODE, b.VOTE_STATUS, b.CREATE_TIME, b.REMARK FROM t_topic_vote_relate a, t_vote_info b WHERE a.VOTE_ID = b.VOTE_ID";
	}
	
	public class ProcessTaskSQLTemplate {
		
		/***将所有超过生效时间的主题修改为发布状态  */
		public static final String AUTO_PUBLISH_TOPIC = "UPDATE `t_topic_info` SET `TOPIC_STATUS`='U',`EFF_TIME`=current_timestamp(),`REMARK`='自动发布完成' WHERE `TOPIC_STATUS` = 'P' AND `EFF_TIME` < ?";
		
		/***查询所有超过失效时间的主题编码  */
		public static final String AUTO_QUERY_EXP_TOPIC = "SELECT `LAUNCH_ID`, `TOPIC_ID`, `VOTE_STYLE`, `VOTE_STOP`, `EXP_DATE`, `REMARKS` FROM `t_vote_launch` WHERE `EXP_DATE` < ?";
		
		/***将所有超过失效时间的主题修改为结束状态  */
		public static final String AUTO_END_TOPIC = "UPDATE `t_topic_info` SET `TOPIC_STATUS`='S',`EXP_TIME`=CURRENT_TIMESTAMP(),`REMARK`='自动关闭话题完成' WHERE `TOPIC_ID`=? AND `TOPIC_STATUS`='U'";
		
		/***将所有超过失效时间的投票项目修改为结束状态  */
		public static final String AUTO_END_VOTE = "UPDATE `t_vote_launch` SET `EXP_DATE`=CURRENT_TIMESTAMP(),`REMARKS`='自动结束主题完成' WHERE `TOPIC_ID`=?";
	}
}
