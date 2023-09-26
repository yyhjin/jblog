package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(UserVo userVo) {
		userRepository.insert(userVo);
		
		// 방금 insert된 데이터의 primary key가 매핑됨 (*user.xml 참고)
		System.out.println(userVo);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
	
}
