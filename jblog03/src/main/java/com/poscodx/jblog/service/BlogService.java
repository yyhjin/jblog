package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public boolean addBlog(UserVo userVo) {
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(userVo.getId());
		blogVo.setTitle(userVo.getName()+"님의 블로그");
		blogVo.setImage("/assets/images/spring-logo.jpg");
		return blogRepository.insertBlog(blogVo);
	}

	public BlogVo getBlog(String blogId) {
		return blogRepository.findByBlogId(blogId);
	}

	public boolean updateBlog(BlogVo blogVo) {
		return blogRepository.updateBlog(blogVo);
	}

}
