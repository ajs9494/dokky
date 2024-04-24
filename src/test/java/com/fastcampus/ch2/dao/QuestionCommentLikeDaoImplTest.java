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
public class QuestionCommentLikeDaoImplTest {
	@Autowired
	private QuestionCommentLikeDao questionCommentLikeDao;
	@Autowired
	private QuestionBoardDao questionBoardDao;
	@Autowired
	private QuestionCommentDao questionCommentDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	public void countAllTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		questionCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(questionCommentLikeDao.countAll() == 1);
	}
	
	@Test
	@Transactional
	public void countTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		questionCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(questionCommentLikeDao.count(cno, true) == 1);
		assertTrue(questionCommentLikeDao.count(cno, false) == 0);
		
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		questionCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이", false));
		questionCommentLikeDao.insert(new CommentLikeDto(cno, "글쓴이2", false));
		assertTrue(questionCommentLikeDao.count(cno, true) == 0);
		assertTrue(questionCommentLikeDao.count(cno, false) == 2);
	}
	
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(questionCommentLikeDao.insert(likeDto) == 1);
		
		assertTrue(questionCommentLikeDao.selectAll().size() == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", true);
		assertTrue(questionCommentLikeDao.insert(likeDto2) == 1);
		
		assertTrue(questionCommentLikeDao.selectAll().size() == 2);
	}
	
	@Test
	@Transactional
	public void selectTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(questionCommentLikeDao.insert(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeDao.insert(likeDto2) == 1);
		
		CommentLikeDto selectedLikeDto = questionCommentLikeDao.select(cno, "글쓴이");
		assertTrue(selectedLikeDto.equals(likeDto));
		
		CommentLikeDto selectedLikeDto2 = questionCommentLikeDao.select(cno, "글쓴이2");
		assertTrue(selectedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void deleteAllTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(questionCommentLikeDao.insert(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeDao.insert(likeDto2) == 1);
		
		assertTrue(questionCommentLikeDao.countAll() == 2);
		assertTrue(questionCommentLikeDao.deleteAll() == 2);
		assertTrue(questionCommentLikeDao.countAll() == 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(questionCommentLikeDao.insert(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeDao.insert(likeDto2) == 1);
		assertTrue(questionCommentLikeDao.countAll() == 2);
		
		assertTrue(questionCommentLikeDao.delete(cno, "글쓴이") == 1);
		assertTrue(questionCommentLikeDao.countAll() == 1);
		
		CommentLikeDto deletedLikeDto2 = questionCommentLikeDao.select(cno, "글쓴이2");
		assertTrue(deletedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void insertTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(questionCommentLikeDao.insert(likeDto) == 1);
		assertTrue(questionCommentLikeDao.countAll() == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeDao.insert(likeDto2) == 1);
		assertTrue(questionCommentLikeDao.countAll() == 2);
		
		CommentLikeDto insertedLikeDto = questionCommentLikeDao.select(cno, "글쓴이");
		assertTrue(insertedLikeDto.equals(likeDto));
		
		CommentLikeDto insertedLikeDto2 = questionCommentLikeDao.select(cno, "글쓴이2");
		assertTrue(insertedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		questionCommentLikeDao.deleteAll();
		assertTrue(questionCommentLikeDao.countAll() == 0);
		
		userDao.deleteAll();
		assertTrue(userDao.insert(new UserDto("asdf", "1234", "aaa@aaa.com", "안진수", "글쓴이", new Date())) == 1);
		assertTrue(userDao.insert(new UserDto("asdf2", "1234", "aaa@aaa.com", "안진수", "글쓴이2", new Date())) == 1);
		
		questionBoardDao.deleteAll();
		questionBoardDao.insert(new BoardDto("제목", "글쓴이", "내용"));
		assertTrue(questionBoardDao.count() == 1);
		Integer bno = questionBoardDao.selectAll().get(0).getBno();
		
		questionCommentDao.deleteAll(bno);
		questionCommentDao.insert(new CommentDto(bno, "글쓴이", "댓글"));
		assertTrue(questionCommentDao.count(bno) == 1);
		Integer cno = questionCommentDao.selectAll(bno).get(0).getCno();
		
		CommentLikeDto likeDto = new CommentLikeDto(cno, "글쓴이", true);
		assertTrue(questionCommentLikeDao.insert(likeDto) == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이", false);
		assertTrue(questionCommentLikeDao.update(likeDto2) == 1);
		
		CommentLikeDto updatedLikeDto = questionCommentLikeDao.select(cno, "글쓴이");
		assertTrue(updatedLikeDto.equals(likeDto2));
	}
}
