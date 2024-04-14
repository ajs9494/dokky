package com.fastcampus.ch2.service;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserServiceImplTest {
	@Autowired
	UserService userService;
	
	@Test
	public void getCountTest() {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		userService.register(user);
		assertTrue(userService.getCount()==1);
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
	}
	
	@Test
	public void getUsers() {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		userService.register(user);
		List<UserDto> list = userService.getUsers();
		assertTrue(list.size()==1);
		
		user = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		userService.register(user);
		list = userService.getUsers();
		assertTrue(list.size()==2);
	}
	
	@Test
	public void getUser() throws Exception {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdate.parse("1994-07-07");
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", date);
		assertTrue(userService.register(user)==1);
		UserDto user2 = userService.getUser(user.getId());
		assertTrue(user.equals(user2));
	}
	
	@Test
	public void removeUsersTest() {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userService.register(user)==1);
		assertTrue(userService.getCount()==1);
		assertTrue(userService.removeUsers()==1);
		assertTrue(userService.getCount()==0);
		
		user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		UserDto user2 = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		assertTrue(userService.register(user)==1);
		assertTrue(userService.register(user2)==1);
		assertTrue(userService.getCount()==2);
		assertTrue(userService.removeUsers()==2);
		assertTrue(userService.getCount()==0);
	}
	
	@Test
	public void removeUserTest() {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		UserDto user2 = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		assertTrue(userService.register(user)==1);
		assertTrue(userService.register(user2)==1);
		assertTrue(userService.removeUser(user.getId())==1);
		assertTrue(userService.getCount()==1);
		UserDto deletedUser = userService.getUser(user.getId());
		assertTrue(deletedUser==null);
		UserDto getUseredUser = userService.getUser(user2.getId());
		assertTrue(getUseredUser!=null);
	}
	
	@Test
	public void registerTest() {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userService.register(user)==1);
		assertTrue(userService.getCount()==1);
		UserDto user2 = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		assertTrue(userService.register(user2)==1);
		assertTrue(userService.getCount()==2);
	}
	
	@Test
	public void modifyTest() {
		userService.removeUsers();
		assertTrue(userService.getCount()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userService.register(user)==1);
		assertTrue(userService.getCount()==1);
		String pwd = "5678";
		UserDto user2 = new UserDto(user.getId(), pwd, "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userService.modify(user2)==1);
		assertTrue(userService.getCount()==1);
		UserDto modifieddUser = userService.getUser(user.getId());
		assertTrue(modifieddUser.getPwd().equals(pwd));
	}
}
