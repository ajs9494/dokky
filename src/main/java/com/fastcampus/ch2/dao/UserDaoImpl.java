package com.fastcampus.ch2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch2.domain.UserDto;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.fastcampus.ch2.dao.UserMapper.";
	
	@Override
	public int count() {
		return session.selectOne(namespace + "count");
	}
	
	@Override
	public List<UserDto> selectAll() {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public UserDto select(String id) {
		return session.selectOne(namespace + "select", id);
	}
	
	@Override
	public int deleteAll() {
		return session.delete(namespace + "deleteAll");
	}
	
	@Override
	public int delete(String id) {
		return session.delete(namespace + "delete", id);
	}
	
	@Override
	public int insert(UserDto user) {
		return session.insert(namespace + "insert", user);
	}
	
	@Override
	public int update(UserDto user) {
		return session.update(namespace + "update", user);
	}
}
