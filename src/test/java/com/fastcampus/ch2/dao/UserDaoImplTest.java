package com.fastcampus.ch2.dao;

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
public class UserDaoImplTest {
	@Autowired
	UserDao userDao;
	
	@Test
	public void countTest() {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		userDao.insert(user);
		assertTrue(userDao.count()==1);
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
	}
	
	@Test
	public void selectAllTest() {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		userDao.insert(user);
		List<UserDto> list = userDao.selectAll();
		assertTrue(list.size()==1);
		
		user = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		userDao.insert(user);
		list = userDao.selectAll();
		assertTrue(list.size()==2);
	}
	
	@Test
	public void selectTest() throws Exception {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdate.parse("1994-07-07");
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", date);
		assertTrue(userDao.insert(user)==1);
		UserDto user2 = userDao.select(user.getId());
		assertTrue(user.equals(user2));
	}
	
	@Test
	public void deleteAllTest() {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userDao.insert(user)==1);
		assertTrue(userDao.count()==1);
		assertTrue(userDao.deleteAll()==1);
		assertTrue(userDao.count()==0);
		
		user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		UserDto user2 = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		assertTrue(userDao.insert(user)==1);
		assertTrue(userDao.insert(user2)==1);
		assertTrue(userDao.count()==2);
		assertTrue(userDao.deleteAll()==2);
		assertTrue(userDao.count()==0);
	}
	
	@Test
	public void deleteTest() {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		UserDto user2 = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		assertTrue(userDao.insert(user)==1);
		assertTrue(userDao.insert(user2)==1);
		assertTrue(userDao.delete(user.getId())==1);
		assertTrue(userDao.count()==1);
		UserDto deletedUser = userDao.select(user.getId());
		assertTrue(deletedUser==null);
		UserDto selectedUser = userDao.select(user2.getId());
		assertTrue(selectedUser!=null);
	}
	
	@Test
	public void insertTest() {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userDao.insert(user)==1);
		assertTrue(userDao.count()==1);
		UserDto user2 = new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "닉네임2", new Date());
		assertTrue(userDao.insert(user2)==1);
		assertTrue(userDao.count()==2);
	}
	
	@Test
	public void updateTest() {
		userDao.deleteAll();
		assertTrue(userDao.count()==0);
		
		UserDto user = new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userDao.insert(user)==1);
		assertTrue(userDao.count()==1);
		String pwd = "5678";
		UserDto user2 = new UserDto(user.getId(), pwd, "aaa@aaa.com", "안진수", "닉네임1", new Date());
		assertTrue(userDao.update(user2)==1);
		assertTrue(userDao.count()==1);
		UserDto updatedUser = userDao.select(user.getId());
		assertTrue(updatedUser.getPwd().equals(pwd));
	}
}

