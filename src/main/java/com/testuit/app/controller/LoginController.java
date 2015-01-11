package com.testuit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping("/forgotPassword")
	public ModelAndView forgotPassword() {
		ModelAndView mv = new ModelAndView("forgotPassword");
		return mv;
	}
}
