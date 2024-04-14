package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.ch2.dao.UserDao;
import com.fastcampus.ch2.domain.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public int getCount() {
		return userDao.count();
	}
	
	@Override
	public List<UserDto> getUsers() {
		return userDao.selectAll();
	}
	
	@Override
	public UserDto getUser(String id) {
		return userDao.select(id);
	}
	
	@Override
	public int removeUsers() {
		return userDao.deleteAll();
	}
	
	@Override
	public int removeUser(String id) {
		return userDao.delete(id);
	}
	
	@Override
	public int register(UserDto userDto) {
		return userDao.insert(userDto);
	}
	
	@Override
	public int modify(UserDto userDto) {
		return userDao.update(userDto);
	}
}
