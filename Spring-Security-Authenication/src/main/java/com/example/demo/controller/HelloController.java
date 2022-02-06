package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
   //只要裝上 spring Security 就會被攔截
	@RequestMapping("/")
	public String welcome() {
		 return "welcome"; //.html
	}
	
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "loginpage";
	}
	
	@RequestMapping("/fail")
	@ResponseBody
	public String failpage() {
		return "fail";
	}
	
	@RequestMapping("/adminpage")
	@ResponseBody
	public String adminpage() {
		return "admin";
	}
	
	@RequestMapping("/managerpage")
	@ResponseBody
	public String managerpage() {
		return "manager";
	}
	
	@RequestMapping("/employeepage")
	@ResponseBody
	public String employeepage() {
		return "employee";
	}
}
