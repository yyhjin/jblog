package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean insertCategory(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.insertCategory", categoryVo);
		return count == 1;
	}

	public List<CategoryVo> findCategoryByBlogId(String blogId) {
		return sqlSession.selectList("category.findCategoryByBlogId", blogId);
	}

	public boolean deleteCategory(Long categoryNo) {
		int count = sqlSession.delete("category.deleteCategory", categoryNo);
		return count == 1;
	}

}
