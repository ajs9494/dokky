package com.fastcampus.ch2.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.ch2.dao.QuestionBoardDao;
import com.fastcampus.ch2.dao.QuestionCommentDao;
import com.fastcampus.ch2.dao.UserDao;
import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.domain.CommentLikeDto;
import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class QuestionCommentLikeServiceImplTest {
	@Autowired
	private QuestionCommentLikeService questionCommentLikeService;
	@Autowired
	private QuestionBoardDao questionBoardDao;
	@Autowired
	private QuestionCommentDao questionCommentDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	public void getCountAllTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		
		questionCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(questionCommentLikeService.getCountAll() == 1);
	}
	
	@Test
	@Transactional
	public void countTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		
		questionCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(questionCommentLikeService.getLikeCnt(cno, true) == 1);
		assertTrue(questionCommentLikeService.getLikeCnt(cno, false) == 0);
		
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
		questionCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이", false));
		questionCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이2", false));
		assertTrue(questionCommentLikeService.getLikeCnt(cno, true) == 0);
		assertTrue(questionCommentLikeService.getLikeCnt(cno, false) == 2);
	}
	
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(questionCommentLikeService.addLike(likeDto) == 1);
		
		assertTrue(questionCommentLikeService.getLikes().size() == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", true);
		assertTrue(questionCommentLikeService.addLike(likeDto2) == 1);
		
		assertTrue(questionCommentLikeService.getLikes().size() == 2);
	}
	
	@Test
	@Transactional
	public void selectTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(questionCommentLikeService.addLike(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeService.addLike(likeDto2) == 1);
		
		CommentLikeDto selectedLikeDto = questionCommentLikeService.getLike(cno, "글쓴이");
		assertTrue(selectedLikeDto.equals(likeDto));
		
		CommentLikeDto selectedLikeDto2 = questionCommentLikeService.getLike(cno, "글쓴이2");
		assertTrue(selectedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void removeLikesTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(questionCommentLikeService.addLike(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeService.addLike(likeDto2) == 1);
		
		assertTrue(questionCommentLikeService.getCountAll() == 2);
		assertTrue(questionCommentLikeService.removeLikes() == 2);
		assertTrue(questionCommentLikeService.getCountAll() == 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(questionCommentLikeService.addLike(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeService.addLike(likeDto2) == 1);
		assertTrue(questionCommentLikeService.getCountAll() == 2);
		
		assertTrue(questionCommentLikeService.removeLike(likeDto) == 1);
		assertTrue(questionCommentLikeService.getCountAll() == 1);
		
		CommentLikeDto deletedLikeDto2 = questionCommentLikeService.getLike(cno, "글쓴이2");
		assertTrue(deletedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void insertTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(questionCommentLikeService.addLike(likeDto) == 1);
		assertTrue(questionCommentLikeService.getCountAll() == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(questionCommentLikeService.addLike(likeDto2) == 1);
		assertTrue(questionCommentLikeService.getCountAll() == 2);
		
		CommentLikeDto insertedLikeDto = questionCommentLikeService.getLike(cno, "글쓴이");
		assertTrue(insertedLikeDto.equals(likeDto));
		
		CommentLikeDto insertedLikeDto2 = questionCommentLikeService.getLike(cno, "글쓴이2");
		assertTrue(insertedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		questionCommentLikeService.removeLikes();
		assertTrue(questionCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(questionCommentLikeService.addLike(likeDto) == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이", false);
		assertTrue(questionCommentLikeService.modifyLike(likeDto2) == 1);
		
		CommentLikeDto updatedLikeDto = questionCommentLikeService.getLike(cno, "글쓴이");
		assertTrue(updatedLikeDto.equals(likeDto2));
	}
}
