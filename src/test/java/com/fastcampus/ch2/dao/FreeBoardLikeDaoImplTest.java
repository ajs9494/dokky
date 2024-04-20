package com.fastcampus.ch2.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.LikeDto;
import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeBoardLikeDaoImplTest {
	@Autowired
	private FreeBoardLikeDao freeBoardLikeDao;
	@Autowired
	private FreeBoardDao freeBoardDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	public void countAllTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		freeBoardLikeDao.insert(new LikeDto(bno, "글쓴이2", true));
		assertTrue(freeBoardLikeDao.countAll() == 1);
	}
	
	@Test
	@Transactional
	public void countTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		freeBoardLikeDao.insert(new LikeDto(bno, "글쓴이2", true));
		assertTrue(freeBoardLikeDao.count(bno, true) == 1);
		assertTrue(freeBoardLikeDao.count(bno, false) == 0);
		
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		freeBoardLikeDao.insert(new LikeDto(bno, "글쓴이", false));
		freeBoardLikeDao.insert(new LikeDto(bno, "글쓴이2", false));
		assertTrue(freeBoardLikeDao.count(bno, true) == 0);
		assertTrue(freeBoardLikeDao.count(bno, false) == 2);
	}
	
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		LikeDto likeDto = new LikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeDao.insert(likeDto) == 1);
		
		assertTrue(freeBoardLikeDao.selectAll().size() == 1);
		
		LikeDto likeDto2 = new LikeDto(bno, "글쓴이2", true);
		assertTrue(freeBoardLikeDao.insert(likeDto2) == 1);
		
		assertTrue(freeBoardLikeDao.selectAll().size() == 2);
	}
	
	@Test
	@Transactional
	public void selectTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		LikeDto likeDto = new LikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeDao.insert(likeDto) == 1);
		LikeDto likeDto2 = new LikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeDao.insert(likeDto2) == 1);
		
		LikeDto selectedLikeDto = freeBoardLikeDao.select(bno, "글쓴이");
		assertTrue(selectedLikeDto.equals(likeDto));
		
		LikeDto selectedLikeDto2 = freeBoardLikeDao.select(bno, "글쓴이2");
		assertTrue(selectedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void deleteAllTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		LikeDto likeDto = new LikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeDao.insert(likeDto) == 1);
		LikeDto likeDto2 = new LikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeDao.insert(likeDto2) == 1);
		
		assertTrue(freeBoardLikeDao.countAll() == 2);
		assertTrue(freeBoardLikeDao.deleteAll() == 2);
		assertTrue(freeBoardLikeDao.countAll() == 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		LikeDto likeDto = new LikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeDao.insert(likeDto) == 1);
		LikeDto likeDto2 = new LikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeDao.insert(likeDto2) == 1);
		assertTrue(freeBoardLikeDao.countAll() == 2);
		
		assertTrue(freeBoardLikeDao.delete(bno, "글쓴이") == 1);
		assertTrue(freeBoardLikeDao.countAll() == 1);
		
		LikeDto deletedLikeDto2 = freeBoardLikeDao.select(bno, "글쓴이2");
		assertTrue(deletedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void insertTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		LikeDto likeDto = new LikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeDao.insert(likeDto) == 1);
		assertTrue(freeBoardLikeDao.countAll() == 1);
		LikeDto likeDto2 = new LikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeDao.insert(likeDto2) == 1);
		assertTrue(freeBoardLikeDao.countAll() == 2);
		
		LikeDto insertedLikeDto = freeBoardLikeDao.select(bno, "글쓴이");
		assertTrue(insertedLikeDto.equals(likeDto));
		
		LikeDto insertedLikeDto2 = freeBoardLikeDao.select(bno, "글쓴이2");
		assertTrue(insertedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		freeBoardLikeDao.deleteAll();
		assertTrue(freeBoardLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		LikeDto likeDto = new LikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeDao.insert(likeDto) == 1);
		
		LikeDto likeDto2 = new LikeDto(bno, "글쓴이", false);
		assertTrue(freeBoardLikeDao.update(likeDto2) == 1);
		
		LikeDto updatedLikeDto = freeBoardLikeDao.select(bno, "글쓴이");
		assertTrue(updatedLikeDto.equals(likeDto2));
	}
}
