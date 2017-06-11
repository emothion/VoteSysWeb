package com.votesys.tools;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	/**
	 * Ajax返回数据的代码封装
	 * @param o
	 * @param response
	 * @throws Exception
	 */
	public static void write(Object o, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
