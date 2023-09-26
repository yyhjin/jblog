package com.poscodx.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
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

	public boolean addCategory(CategoryVo categoryVo) {
		return blogRepository.insertCategory(categoryVo);
	}

	public List<CategoryVo> getCategoriesById(String blogId) {
		return blogRepository.findCategoryByBlogId(blogId);
	}

	public boolean addPost(PostVo postVo) {
		return blogRepository.insertPost(postVo);
	}

	public boolean removeCategory(Long categoryNo) {
		return blogRepository.deleteCategory(categoryNo);
	}

	public boolean updateBlog(BlogVo blogVo) {
		return blogRepository.updateBlog(blogVo);
	}

	public List<PostVo> getPostsByCategory(Long categoryNo) {
		return blogRepository.findPostsByCategory(categoryNo);
	}

	public PostVo getPostByNo(Long postNo) {
		return blogRepository.findPostByPostNo(postNo);
	}
	
}
