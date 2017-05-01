package com.votesys.tools;

import com.votesys.bean.UserInfoBean;

public class FunctionTest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setRetInfo("123");
		BeanUtils.checkBeanEmpty(userInfoBean);
	}

}
