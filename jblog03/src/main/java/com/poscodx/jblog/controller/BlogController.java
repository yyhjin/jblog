package com.poscodx.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String index(@PathVariable("id") String blogId,
						@PathVariable Optional<Long> categoryNo,
						@PathVariable Optional<Long> postNo) {
		return "blog/main";
	}
	
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String blogId) {  // 기본 컨트롤러 매핑에 blogId 있으므로 얘 꼭 있어야함
		return "BlogController.adminBasic()";
	}
	
	
	
}
