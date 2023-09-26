package com.poscodx.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("")
	public String main() {
		return "main/index";
	}
}
