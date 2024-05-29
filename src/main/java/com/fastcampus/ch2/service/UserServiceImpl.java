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
	public int getCount() throws Exception {
		return userDao.count();
	}
	
	@Override
	public List<UserDto> getUsers() throws Exception {
		return userDao.selectAll();
	}
	
	@Override
	public UserDto getUser(String id) throws Exception {
		return userDao.select(id);
	}
	
	@Override
	public int removeUsers() throws Exception {
		return userDao.deleteAll();
	}
	
	@Override
	public int removeUser(String id) throws Exception {
		return userDao.delete(id);
	}
	
	@Override
	public int register(UserDto userDto) throws Exception {
		return userDao.insert(userDto);
	}
	
	@Override
	public int modifyInfo(UserDto userDto) throws Exception {
		return userDao.updateInfo(userDto);
	}
	
	@Override
	public int modifyPwd(UserDto userDto) throws Exception {
		return userDao.updatePwd(userDto);
	}
}
