package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public boolean addCategory(CategoryVo categoryVo) {
		return categoryRepository.insertCategory(categoryVo);
	}

	public List<CategoryVo> getCategoriesById(String blogId) {
		return categoryRepository.findCategoryByBlogId(blogId);
	}

	public boolean removeCategory(Long categoryNo) {
		return categoryRepository.deleteCategory(categoryNo);
	}
	
}
