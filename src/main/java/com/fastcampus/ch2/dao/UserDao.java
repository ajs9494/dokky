package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.UserDto;

public interface UserDao {

	int count() throws Exception;

	List<UserDto> selectAll() throws Exception;

	UserDto select(String id) throws Exception;

	int deleteAll() throws Exception;

	int delete(String id) throws Exception;

	int insert(UserDto user) throws Exception;

	int updateInfo(UserDto user) throws Exception;
	
	int updatePwd(UserDto user) throws Exception;

}