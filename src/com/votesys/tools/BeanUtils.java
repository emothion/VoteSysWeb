package com.votesys.tools;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.votesys.bean.CommentBean;
import com.votesys.bean.TopicExtInfoBean;
import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.TopicVoteRelateBean;
import com.votesys.bean.UserInfoBean;
import com.votesys.bean.UserTopicRelateBean;
import com.votesys.bean.UserVoteRelateBean;
import com.votesys.bean.VoteInfoBean;
import com.votesys.bean.VoteLaunchBean;
import com.votesys.bean.mapping.BeanOfMapping;
import com.votesys.common.VoteSysConstant;
import com.votesys.qbo.bean.TrineUTCBean;
import com.votesys.qbo.bean.UserAllInfoBean;

/**
 * @ClassName com.votesys.tools.BeanUtils.java
 * @Description 数据模型工具
 */
public class BeanUtils {

	/**
	 * @Function com.votesys.tools.BeanUtils::checkBeanEmpty
	 * @Description 校验除retInfo字段都为空
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static boolean checkBeanEmpty(Object bean) throws IllegalArgumentException, IllegalAccessException {
		if (bean == null) {
			return true;
		}
		boolean ret = false;
		
		@SuppressWarnings("rawtypes")
		Class cls = bean.getClass();
		Field[] fields = cls.getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.get(bean) != null) {
				ret = true;
				if (VoteSysConstant.CONST00001.equals(field.getName())) {
					ret = false;
				}
			}
			field.setAccessible(false);
		}
		return ret;
	}
	
	/**
	 * @Function com.votesys.tools.BeanUtils::convertResultSetToBean
	 * @Description 返回数据转Bean
	 * @param bean
	 * @param rs
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public static Object convertResultSetToBean(Object bean, ResultSet rs) throws IllegalArgumentException, IllegalAccessException, SQLException {
		
		@SuppressWarnings("rawtypes")
		Class cls = bean.getClass();
		Field[] fields = cls.getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = rs.getObject(field.getName());
			if (value != null) {
				field.set(field.getName(), value);
			}
			field.setAccessible(false);
		}
		
		return bean;
	}
	
	/**
	 * @Function com.votesys.tools.BeanUtils::setUserInfoBean
	 * @Description 用户基础信息Bean塞值
	 * @param rs
	 * @throws SQLException
	 */
	public static UserInfoBean setUserInfoBean(ResultSet rs) throws SQLException {
		UserInfoBean user = new UserInfoBean();
		user.setUserID(rs.getLong("USER_ID"));
		user.setUserName(rs.getString("USER_NAME"));
		user.setUserEmail(rs.getString("USER_EMAIL"));
		user.setUserPhone(rs.getString("USER_PHONE"));
		user.setUserKey(rs.getString("USER_KEY"));
		user.setStatus(rs.getString("USER_STATUS"));
		user.setCreateTime(rs.getString("CREATE_TIME"));
		user.setRemark(rs.getString("REMARK"));
		return user;
	}
	
	public static UserAllInfoBean setUserAllInfoBean(ResultSet rs) throws SQLException {
		UserAllInfoBean userAllInfo = new UserAllInfoBean();
		userAllInfo.setUserID(rs.getString("USER_ID"));
		userAllInfo.setUserName(rs.getString("USER_NAME"));
		userAllInfo.setUserEmail(rs.getString("USER_EMAIL"));
		userAllInfo.setUserPhone(rs.getString("USER_PHONE"));
		userAllInfo.setUserProvince(rs.getString("USER_PROVINCE"));
		userAllInfo.setUserCity(rs.getString("USER_CITY"));
		userAllInfo.setUserRegion(rs.getString("USER_REGION"));
		userAllInfo.setUserSexy(rs.getString("USER_SEXY"));
		userAllInfo.setUserBirth(rs.getString("USER_BIRTH"));
		userAllInfo.setUserImg(rs.getString("USER_IMG"));
		userAllInfo.setUserDesc(rs.getString("USER_DESC"));
		return userAllInfo;
	}
	
	public static TopicInfoBean setTopicInfoBean(ResultSet rs) throws SQLException {
		TopicInfoBean topicInfo = new TopicInfoBean();
		topicInfo.setTopicID(rs.getString(BeanOfMapping.TopicInfoBeanMapping.topicID));
		topicInfo.setTopicTitle(rs.getString(BeanOfMapping.TopicInfoBeanMapping.topicTitle));
		topicInfo.setTopicContent(rs.getString(BeanOfMapping.TopicInfoBeanMapping.topicContent));
		topicInfo.setTopicStatus(rs.getString(BeanOfMapping.TopicInfoBeanMapping.topicStatus));
		topicInfo.setCreateTime(rs.getString(BeanOfMapping.TopicInfoBeanMapping.createTime));
		topicInfo.setEffTime(rs.getString(BeanOfMapping.TopicInfoBeanMapping.effTime));
		topicInfo.setExpTime(rs.getString(BeanOfMapping.TopicInfoBeanMapping.expTime));
		topicInfo.setRemark(rs.getString(BeanOfMapping.TopicInfoBeanMapping.remark));
		return topicInfo;
	}
	
	public static TopicExtInfoBean setTopicInfoDtlBean(ResultSet rs) throws SQLException {
		TopicExtInfoBean topicExtInfo = new TopicExtInfoBean();
		topicExtInfo.setExtInfoID(rs.getString(BeanOfMapping.TopicExtInfoBeanMapping.extInfoID));
		topicExtInfo.setTopicID(rs.getString(BeanOfMapping.TopicExtInfoBeanMapping.topicID));
		topicExtInfo.setTopicImg(rs.getString(BeanOfMapping.TopicExtInfoBeanMapping.topicImg));
		topicExtInfo.setImgOrder(rs.getString(BeanOfMapping.TopicExtInfoBeanMapping.imgOrder));
		topicExtInfo.setExtStatus(rs.getString(BeanOfMapping.TopicExtInfoBeanMapping.extStatus));
		return topicExtInfo;
	}
	
	public static VoteInfoBean setVoteInfoBean(ResultSet rs) throws SQLException {
		VoteInfoBean voteInfo = new VoteInfoBean();
		voteInfo.setVoteID(rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteID));
		voteInfo.setVoteOBJ(rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteOBJ));
		voteInfo.setVoteCode(rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteCode));
		voteInfo.setVoteStatus(rs.getString(BeanOfMapping.VoteInfoBeanMapping.voteStatus));
		voteInfo.setCreateTime(rs.getString(BeanOfMapping.VoteInfoBeanMapping.createTime));
		voteInfo.setRemark(rs.getString(BeanOfMapping.VoteInfoBeanMapping.remark));
		return voteInfo;
	}
	
	public static VoteLaunchBean setVoteLaunchBean(ResultSet rs) throws SQLException {
		VoteLaunchBean voteLaunch = new VoteLaunchBean();
		voteLaunch.setLaunchID(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.launchID));
		voteLaunch.setTopicID(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.topicID));
		voteLaunch.setVoteStyle(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.voteStyle));
		voteLaunch.setVoteStop(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.voteStop));
		voteLaunch.setExpDate(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.expDate));
		voteLaunch.setRemarks(rs.getString(BeanOfMapping.VoteLaunchBeanMapping.remarks));
		return voteLaunch;
	}
	
	public static TopicVoteRelateBean setTopicVoteRelBean(ResultSet rs) throws SQLException {
		TopicVoteRelateBean tvRel = new TopicVoteRelateBean();
		tvRel.setRelateID(rs.getString(BeanOfMapping.TopicVoteRelBeanMapping.relateID));
		tvRel.setTopicID(rs.getString(BeanOfMapping.TopicVoteRelBeanMapping.topicID));
		tvRel.setVoteID(rs.getString(BeanOfMapping.TopicVoteRelBeanMapping.voteID));
		tvRel.setCreateTime(rs.getString(BeanOfMapping.TopicVoteRelBeanMapping.createTime));
		return tvRel;
	}
	
	public static UserTopicRelateBean setUserTopicRelBean(ResultSet rs) throws SQLException {
		UserTopicRelateBean utRel = new UserTopicRelateBean();
		utRel.setRelateID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.relateID));
		utRel.setUserID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.userID));
		utRel.setTopicID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.topicID));
		utRel.setCreateTime(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.createTime));
		return utRel;
	}
	
	public static UserVoteRelateBean setUserVoteRelateBean(ResultSet rs) throws SQLException {
		UserVoteRelateBean uvRel = new UserVoteRelateBean();
		uvRel.setRelateID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.relateID));
		uvRel.setUserID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.userID));
		uvRel.setTopicID(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.topicID));
		uvRel.setVoteID(rs.getString(BeanOfMapping.UserVoteRelBeanMapping.voteID));
		uvRel.setCreateTime(rs.getString(BeanOfMapping.UserTopicRelBeanMapping.createTime));
		return uvRel;
	}
	
	public static TrineUTCBean setTrineUTCBean(ResultSet rs) throws SQLException {
		TrineUTCBean trineUTC = new TrineUTCBean();
		trineUTC.setTopicID(rs.getString(BeanOfMapping.TrineUTCBeanMapping.topicID));
		trineUTC.setUserID(rs.getString(BeanOfMapping.TrineUTCBeanMapping.userID));
		trineUTC.setUserName(rs.getString(BeanOfMapping.TrineUTCBeanMapping.userName));
		trineUTC.setUserImg(rs.getString(BeanOfMapping.TrineUTCBeanMapping.userImg));
		trineUTC.setComID(rs.getString(BeanOfMapping.TrineUTCBeanMapping.comID));
		trineUTC.setComContent(rs.getString(BeanOfMapping.TrineUTCBeanMapping.comContent));
		trineUTC.setComStatus(rs.getString(BeanOfMapping.TrineUTCBeanMapping.comStatus));
		trineUTC.setSubNum(rs.getInt(BeanOfMapping.TrineUTCBeanMapping.subNum));
		trineUTC.setUpNum(rs.getInt(BeanOfMapping.TrineUTCBeanMapping.upNum));
		trineUTC.setCreateTime(rs.getString(BeanOfMapping.TrineUTCBeanMapping.createTime));
		return trineUTC;
	}
	
	public static CommentBean setCommentBean(ResultSet rs) throws SQLException {
		CommentBean comment = new CommentBean();
		comment.setComID(rs.getString(BeanOfMapping.CommentBeanMapping.comID));
		comment.setComContent(rs.getString(BeanOfMapping.CommentBeanMapping.comContent));
		comment.setComStatus(rs.getString(BeanOfMapping.CommentBeanMapping.comStatus));
		comment.setSubNum(rs.getInt(BeanOfMapping.CommentBeanMapping.subNum));
		comment.setUpNum(rs.getInt(BeanOfMapping.CommentBeanMapping.upNum));
		comment.setCreateTime(rs.getString(BeanOfMapping.CommentBeanMapping.createTime));
		return comment;
	}
}
