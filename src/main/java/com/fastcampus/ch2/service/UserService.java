package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.UserDto;

public interface UserService {

	int getCount();

	List<UserDto> getUsers();

	UserDto getUser(String id);

	int removeUsers();

	int removeUser(String id);

	int register(UserDto userDto);

	int modify(UserDto userDto);

}