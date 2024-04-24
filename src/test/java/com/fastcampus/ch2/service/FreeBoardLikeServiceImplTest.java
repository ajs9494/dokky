package com.fastcampus.ch2.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.ch2.dao.FreeBoardDao;
import com.fastcampus.ch2.dao.FreeBoardLikeDao;
import com.fastcampus.ch2.dao.UserDao;
import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.BoardLikeDto;
import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeBoardLikeServiceImplTest {
	@Autowired
	private FreeBoardLikeService freeBoardLikeService;
	@Autowired
	private FreeBoardDao freeBoardDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	public void countAllTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		freeBoardLikeService.addLike(new BoardLikeDto(bno, "글쓴이2", true));
		assertTrue(freeBoardLikeService.getCountAll() == 1);
	}
	
	@Test
	@Transactional
	public void countTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		freeBoardLikeService.addLike(new BoardLikeDto(bno, "글쓴이2", true));
		assertTrue(freeBoardLikeService.getLikeCnt(bno, true) == 1);
		assertTrue(freeBoardLikeService.getLikeCnt(bno, false) == 0);
		
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		freeBoardLikeService.addLike(new BoardLikeDto(bno, "글쓴이", false));
		freeBoardLikeService.addLike(new BoardLikeDto(bno, "글쓴이2", false));
		assertTrue(freeBoardLikeService.getLikeCnt(bno, true) == 0);
		assertTrue(freeBoardLikeService.getLikeCnt(bno, false) == 2);
	}
	
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		BoardLikeDto likeDto = new BoardLikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeService.addLike(likeDto) == 1);
		
		assertTrue(freeBoardLikeService.getLikes().size() == 1);
		
		BoardLikeDto likeDto2 = new BoardLikeDto(bno, "글쓴이2", true);
		assertTrue(freeBoardLikeService.addLike(likeDto2) == 1);
		
		assertTrue(freeBoardLikeService.getLikes().size() == 2);
	}
	
	@Test
	@Transactional
	public void selectTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		BoardLikeDto likeDto = new BoardLikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeService.addLike(likeDto) == 1);
		BoardLikeDto likeDto2 = new BoardLikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeService.addLike(likeDto2) == 1);
		
		BoardLikeDto selectedLikeDto = freeBoardLikeService.getLike(bno, "글쓴이");
		assertTrue(selectedLikeDto.equals(likeDto));
		
		BoardLikeDto selectedLikeDto2 = freeBoardLikeService.getLike(bno, "글쓴이2");
		assertTrue(selectedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void deleteAllTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		BoardLikeDto likeDto = new BoardLikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeService.addLike(likeDto) == 1);
		BoardLikeDto likeDto2 = new BoardLikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeService.addLike(likeDto2) == 1);
		
		assertTrue(freeBoardLikeService.getCountAll() == 2);
		assertTrue(freeBoardLikeService.removeLikes() == 2);
		assertTrue(freeBoardLikeService.getCountAll() == 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		BoardLikeDto likeDto = new BoardLikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeService.addLike(likeDto) == 1);
		BoardLikeDto likeDto2 = new BoardLikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeService.addLike(likeDto2) == 1);
		assertTrue(freeBoardLikeService.getCountAll() == 2);
		
		assertTrue(freeBoardLikeService.removeLike(likeDto) == 1);
		assertTrue(freeBoardLikeService.getCountAll() == 1);
		
		BoardLikeDto deletedLikeDto2 = freeBoardLikeService.getLike(bno, "글쓴이2");
		assertTrue(deletedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void insertTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		BoardLikeDto likeDto = new BoardLikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeService.addLike(likeDto) == 1);
		assertTrue(freeBoardLikeService.getCountAll() == 1);
		BoardLikeDto likeDto2 = new BoardLikeDto(bno, "글쓴이2", false);
		assertTrue(freeBoardLikeService.addLike(likeDto2) == 1);
		assertTrue(freeBoardLikeService.getCountAll() == 2);
		
		BoardLikeDto insertedLikeDto = freeBoardLikeService.getLike(bno, "글쓴이");
		assertTrue(insertedLikeDto.equals(likeDto));
		
		BoardLikeDto insertedLikeDto2 = freeBoardLikeService.getLike(bno, "글쓴이2");
		assertTrue(insertedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		freeBoardLikeService.removeLikes();
		assertTrue(freeBoardLikeService.getCountAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		BoardLikeDto likeDto = new BoardLikeDto(bno, "글쓴이", true);
		assertTrue(freeBoardLikeService.addLike(likeDto) == 1);
		
		BoardLikeDto likeDto2 = new BoardLikeDto(bno, "글쓴이", false);
		assertTrue(freeBoardLikeService.modifyLike(likeDto2) == 1);
		
		BoardLikeDto updatedLikeDto = freeBoardLikeService.getLike(bno, "글쓴이");
		assertTrue(updatedLikeDto.equals(likeDto2));
	}
}
