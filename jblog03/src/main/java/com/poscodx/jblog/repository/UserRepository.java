package com.poscodx.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);
		return count == 1;
	}

	public UserVo findByIdAndPassword(String id, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}

}
