package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public boolean addPost(PostVo postVo) {
		return postRepository.insertPost(postVo);
	}

	public List<PostVo> getPostsByCategory(Long categoryNo) {
		return postRepository.findPostsByCategory(categoryNo);
	}

	public PostVo getPostByNo(Long postNo) {
		return postRepository.findPostByPostNo(postNo);
	}

	public void removePosts(Long categoryNo) {
		postRepository.deletePostsByCategoryNo(categoryNo);
	}
	
}
