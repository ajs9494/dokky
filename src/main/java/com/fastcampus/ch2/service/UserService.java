package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.UserDto;

public interface UserService {

	int getCount() throws Exception;

	List<UserDto> getUsers() throws Exception;

	UserDto getUser(String id) throws Exception;

	int removeUsers() throws Exception;

	int removeUser(String id) throws Exception;

	int register(UserDto userDto) throws Exception;

	int modifyInfo(UserDto userDto) throws Exception;
	
	int modifyPwd(UserDto userDto) throws Exception;

}