package com.study.dubbo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/user")
@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public ModelAndView  login(){
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("username", "张三");
		return modelAndView;
	}
}
