package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.UserDto;

public interface UserDao {

	int count();

	List<UserDto> selectAll();

	UserDto select(String id);

	int deleteAll();

	int delete(String id);

	int insert(UserDto user);

	int update(UserDto user);

}