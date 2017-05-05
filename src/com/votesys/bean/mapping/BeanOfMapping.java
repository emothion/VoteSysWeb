package com.votesys.bean.mapping;

/**
 * @ClassName com.votesys.bean.mapping.UserInfoBeanMapping.java
 * @Description 用户基础信息属性与数据表头映射关系
 */
public class BeanOfMapping {
	public class UserInfoBeanMapping {
		public static final String userID="USER_ID";
		public static final String userName="USER_NAME";
		public static final String userEmail="USER_EMAIL";
		public static final String userPhone="USER_PHONE";
		public static final String userKey="USER_KEY";
		public static final String status="USER_STATUS";
		public static final String createTime="CREATE_TIME";
		public static final String remark="REMARK";
	}
	
	public class UserInfoDtlBeanMapping {
		public static final String userExtID = "USER_EXT_ID";
		public static final String userID="USER_ID";
		public static final String userSexy = "USER_SEXY";
		public static final String userBirth = "USER_BIRTH";
		public static final String userProvince = "USER_PROVINCE";
		public static final String userCity = "USER_CITY";
		public static final String userRegion = "USER_REGION";
		public static final String userImg = "USER_IMG";
		public static final String userDesc = "USER_DESC";
	}
	
	public class TopicInfoBeanMapping {
		public static final String topicID = "TOPIC_ID";
		public static final String topicTitle = "TOPIC_TITLE";
		public static final String topicContent = "TOPIC_CONTENT";
		public static final String topicStatus = "TOPIC_STATUS";
		public static final String createTime = "CREATE_TIME";
		public static final String effTime = "EFF_TIME";
		public static final String expTime = "EXP_TIME";
		public static final String remark = "REMARK";
	}
	
	public class TopicExtInfoBeanMapping {
		public static final String extInfoID = "EXT_INFO_ID";
		public static final String topicID = "TOPIC_ID";
		public static final String topicImg = "TOPIC_IMG";
		public static final String imgOrder = "IMG_ORDER";
		public static final String extStatus = "EXT_STATUS";
	}
	
	public class VoteInfoBeanMapping {
		public static final String voteID = "VOTE_ID";
		public static final String voteOBJ = "VOTE_OBJ";
		public static final String voteCode = "VOTE_CODE";
		public static final String voteStatus = "VOTE_STATUS";
		public static final String createTime = "CREATE_TIME";
		public static final String remark = "REMARK";
	}
	
	public class VoteLaunchBeanMapping {
		public static final String launchID = "LAUNCH_ID";
		public static final String topicID = "TOPIC_ID";
		public static final String voteStyle = "VOTE_STYLE";
		public static final String voteStop = "VOTE_STOP";
		public static final String expDate = "EXP_DATE";
		public static final String remarks = "REMARKS";
	}
	
	public class TopicVoteRelBeanMapping {
		public static final String relateID = "RELATE_ID";
		public static final String topicID = "TOPIC_ID";
		public static final String voteID = "VOTE_ID";
		public static final String createTime = "CREATE_TIME";
	}
	
	public class UserTopicRelBeanMapping {
		public static final String relateID = "RELATE_ID";
		public static final String userID = "USER_ID";
		public static final String topicID = "TOPIC_ID";
		public static final String createTime = "CREATE_TIME";
	}
	
	public class UserVoteRelBeanMapping {
		public static final String relateID = "RELATE_ID";
		public static final String userID = "USER_ID";
		public static final String topicID = "TOPIC_ID";
		public static final String voteID = "VOTE_ID";
		public static final String createTime = "CREATE_TIME";
	}
	
	public class TrineUTCBeanMapping {
		public static final String topicID = "TOPIC_ID";
		public static final String userID = "USER_ID";
		public static final String userName = "USER_NAME";
		public static final String userImg = "USER_IMG";
		public static final String comID = "COM_ID";
		public static final String comContent = "COM_CONTENT";
		public static final String comStatus = "COM_STATUS";
		public static final String createTime = "CREATE_TIME";
		public static final String subNum = "SUB_NUM";
		public static final String upNum = "UP_NUM";
	}
	
	public class CommentBeanMapping {
		public static final String comID = "COM_ID";
		public static final String comContent = "COM_CONTENT";
		public static final String comStatus = "COM_STATUS";
		public static final String createTime = "CREATE_TIME";
		public static final String subNum = "SUB_NUM";
		public static final String upNum = "UP_NUM";
	}
}
