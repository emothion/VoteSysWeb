package com.votesys.tools;

import java.sql.Timestamp;

/**
 * @ClassName com.votesys.tools.TimeUtils.java
 * @Description 时间工具类
 */
public class TimeUtils {

	public static Timestamp getSystemTime() {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		return time;
	}
}
