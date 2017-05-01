package com.votesys.tools;

/**
 * @ClassName com.emothion.String.mytools.StringUtils.java
 * @Description 字符串通用工具
 */
public class StringUtils {

	/**
	 * @Function com.emothion.String.mytools.StringUtils::isEmpty
	 * @Description 字符串判空(StringUtil.isEmpty("  ") = false;)
	 * @param str
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * @Function com.emothion.String.mytools.StringUtils::isBlank
	 * @Description 字符串判空(StringUtil.isEmpty("  ") = true;)
	 * @param str
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * @Function com.emothion.String.mytools.StringUtils::isNotEmpty
	 * @Description 字符串不为空
	 * @param str
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * @Function com.emothion.String.mytools.StringUtils::isNotBlank
	 * @Description 字符串不为空
	 * @param str
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * @Function com.votesys.tools.StringUtils::upFirstChar
	 * @Description: 字符串首字母大写
	 * @param str
	 */
	public static String upFirstChar(String str) {
		String upStr = str.toUpperCase();
		return upStr.substring(0, 1)+str.substring(1);
	}
	
	/**
	 * @Function com.votesys.tools.StringUtils::getSuffix
	 * @Description 获取文件后缀名
	 * @param fileName
	 */
	public static String getSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
