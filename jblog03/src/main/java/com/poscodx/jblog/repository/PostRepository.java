package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean insertPost(PostVo postVo) {
		int count = sqlSession.insert("post.insertPost", postVo);
		return count == 1;
	}

	public List<PostVo> findPostsByCategory(Long categoryNo) {
		return sqlSession.selectList("post.findPostsByCategory", categoryNo);
	}

	public PostVo findPostByPostNo(Long postNo) {
		return sqlSession.selectOne("post.findPostByPostNo", postNo);
	}

	public void deletePostsByCategoryNo(Long categoryNo) {
		sqlSession.delete("post.deletePostsByCategoryNo", categoryNo);
	}
}
