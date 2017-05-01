package com.votesys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/getTopic")
	public ModelAndView getTopic(HttpServletRequest request){
		ModelAndView mAndView=new ModelAndView();	
		mAndView.setViewName("LoadIndex");
		return mAndView;
	}
}
