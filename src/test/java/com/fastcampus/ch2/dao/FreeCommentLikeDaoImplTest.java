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
import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.domain.CommentLikeDto;
import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeCommentLikeDaoImplTest {
	@Autowired
	private FreeCommentLikeDao freeCommentLikeDao;
	@Autowired
	private FreeBoardDao freeBoardDao;
	@Autowired
	private FreeCommentDao FreeCommentDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	public void countAllTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		freeCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(freeCommentLikeDao.countAll() == 1);
	}
	
	@Test
	@Transactional
	public void countTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		freeCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(freeCommentLikeDao.count(cno, true) == 1);
		assertTrue(freeCommentLikeDao.count(cno, false) == 0);
		
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		freeCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이", false));
		freeCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이2", false));
		assertTrue(freeCommentLikeDao.count(cno, true) == 0);
		assertTrue(freeCommentLikeDao.count(cno, false) == 2);
	}
	
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(freeCommentLikeDao.insert(likeDto) == 1);
		
		assertTrue(freeCommentLikeDao.selectAll().size() == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", true);
		assertTrue(freeCommentLikeDao.insert(likeDto2) == 1);
		
		assertTrue(freeCommentLikeDao.selectAll().size() == 2);
	}
	
	@Test
	@Transactional
	public void selectTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(freeCommentLikeDao.insert(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeDao.insert(likeDto2) == 1);
		
		CommentLikeDto selectedLikeDto = freeCommentLikeDao.select(cno, "글쓴이");
		assertTrue(selectedLikeDto.equals(likeDto));
		
		CommentLikeDto selectedLikeDto2 = freeCommentLikeDao.select(cno, "글쓴이2");
		assertTrue(selectedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void deleteAllTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(freeCommentLikeDao.insert(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeDao.insert(likeDto2) == 1);
		
		assertTrue(freeCommentLikeDao.countAll() == 2);
		assertTrue(freeCommentLikeDao.deleteAll() == 2);
		assertTrue(freeCommentLikeDao.countAll() == 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(freeCommentLikeDao.insert(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeDao.insert(likeDto2) == 1);
		assertTrue(freeCommentLikeDao.countAll() == 2);
		
		assertTrue(freeCommentLikeDao.delete(cno, "글쓴이") == 1);
		assertTrue(freeCommentLikeDao.countAll() == 1);
		
		CommentLikeDto deletedLikeDto2 = freeCommentLikeDao.select(cno, "글쓴이2");
		assertTrue(deletedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void insertTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(freeCommentLikeDao.insert(likeDto) == 1);
		assertTrue(freeCommentLikeDao.countAll() == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeDao.insert(likeDto2) == 1);
		assertTrue(freeCommentLikeDao.countAll() == 2);
		
		CommentLikeDto insertedLikeDto = freeCommentLikeDao.select(cno, "글쓴이");
		assertTrue(insertedLikeDto.equals(likeDto));
		
		CommentLikeDto insertedLikeDto2 = freeCommentLikeDao.select(cno, "글쓴이2");
		assertTrue(insertedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		freeCommentLikeDao.deleteAll();
		assertTrue(freeCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		freeBoardDao.deleteAll();
		freeBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(freeBoardDao.count() == 1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		
		FreeCommentDao.deleteAll(bno);
		FreeCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(FreeCommentDao.count(bno) == 1);
		Integer cno = FreeCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(freeCommentLikeDao.insert(likeDto) == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이", false);
		assertTrue(freeCommentLikeDao.update(likeDto2) == 1);
		
		CommentLikeDto updatedLikeDto = freeCommentLikeDao.select(cno, "글쓴이");
		assertTrue(updatedLikeDto.equals(likeDto2));
	}
}
