package com.poscodx.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		if(userService.addUser(userVo)) {
			blogService.addBlog(userVo);
			
			CategoryVo categoryVo = new CategoryVo();
			categoryVo.setBlogId(userVo.getId());
			categoryVo.setName("(미분류)");
			categoryVo.setDescription("(미분류)");
			
			blogService.addCategory(categoryVo);
			return "redirect:/user/joinsuccess";			
		}
		
		return "/user/join";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public String auth(@RequestParam String id, 
						@RequestParam String password,
						HttpSession session) {
		
		UserVo authUser = userService.getUser(id, password);
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
}
