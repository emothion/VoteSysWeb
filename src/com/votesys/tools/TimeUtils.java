package com.votesys.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @ClassName com.votesys.tools.TimeUtils.java
 * @Description 时间工具类
 */
public class TimeUtils {

	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * @Function com.votesys.tools.TimeUtils::getSystemTime
	 * @Description 返回当前时间
	 * @return
	 */
	public static Timestamp getSystemTime() {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		return time;
	}
	
	/**
	 * @Function com.votesys.tools.TimeUtils::formatTimeUtil
	 * @Description 按照format格式化时间
	 * @param format
	 * @param time
	 * @return
	 */
	public static String formatTimeUtil(String format, Timestamp time) {
		DateFormat formatUtil = new SimpleDateFormat(format);
		return formatUtil.format(time);
	}
}
