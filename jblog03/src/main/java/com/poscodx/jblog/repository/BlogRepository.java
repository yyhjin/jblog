package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean insertBlog(BlogVo vo) {
		int count = sqlSession.insert("blog.insertBlog", vo);
		return count == 1;
	}

	public BlogVo findByBlogId(String blogId) {
		return sqlSession.selectOne("blog.findByBlogId", blogId);
	}

	public boolean insertCategory(CategoryVo categoryVo) {
		int count = sqlSession.insert("blog.insertCategory", categoryVo);
		return count == 1;
	}

	public List<CategoryVo> findCategoryByBlogId(String blogId) {
		return sqlSession.selectList("blog.findCategoryByBlogId", blogId);
	}

	public boolean insertPost(PostVo postVo) {
		int count = sqlSession.insert("blog.insertPost", postVo);
		return count == 1;
	}

	public boolean deleteCategory(Long categoryNo) {
		int count = sqlSession.delete("blog.deleteCategory", categoryNo);
		return count == 1;
	}

	public boolean updateBlog(BlogVo blogVo) {
		int count = sqlSession.update("blog.updateBlog", blogVo);
		return count == 1;
	}

	public List<PostVo> findPostsByCategory(Long categoryNo) {
		return sqlSession.selectList("blog.findPostsByCategory", categoryNo);
	}

	public PostVo findPostByPostNo(Long postNo) {
		return sqlSession.selectOne("blog.findPostByPostNo", postNo);
	}
}
