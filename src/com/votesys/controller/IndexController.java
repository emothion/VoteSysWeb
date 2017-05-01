package com.votesys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/getTopic")
	public ModelAndView getTopic(HttpServletRequest request){
		ModelAndView mAndView=new ModelAndView();
		HttpSession session = request.getSession();
		
		session.removeAttribute("topicSession");
		mAndView.setViewName("LoadIndex");
		return mAndView;
	}
}
