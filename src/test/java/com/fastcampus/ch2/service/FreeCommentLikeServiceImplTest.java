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
import com.fastcampus.ch2.dao.FreeCommentDao;
import com.fastcampus.ch2.dao.UserDao;
import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.domain.CommentLikeDto;
import com.fastcampus.ch2.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeCommentLikeServiceImplTest {
	@Autowired
	private FreeCommentLikeService freeCommentLikeService;
	@Autowired
	private FreeBoardDao freeBoardDao;
	@Autowired
	private FreeCommentDao FreeCommentDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	public void getCountAllTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		
		freeCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(freeCommentLikeService.getCountAll() == 1);
	}
	
	@Test
	@Transactional
	public void countTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		
		freeCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이2", true));
		assertTrue(freeCommentLikeService.getLikeCnt(cno, true) == 1);
		assertTrue(freeCommentLikeService.getLikeCnt(cno, false) == 0);
		
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
		freeCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이", false));
		freeCommentLikeService.addLike(new CommentLikeDto(cno, "글쓴이2", false));
		assertTrue(freeCommentLikeService.getLikeCnt(cno, true) == 0);
		assertTrue(freeCommentLikeService.getLikeCnt(cno, false) == 2);
	}
	
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(freeCommentLikeService.addLike(likeDto) == 1);
		
		assertTrue(freeCommentLikeService.getLikes().size() == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", true);
		assertTrue(freeCommentLikeService.addLike(likeDto2) == 1);
		
		assertTrue(freeCommentLikeService.getLikes().size() == 2);
	}
	
	@Test
	@Transactional
	public void selectTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(freeCommentLikeService.addLike(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeService.addLike(likeDto2) == 1);
		
		CommentLikeDto selectedLikeDto = freeCommentLikeService.getLike(cno, "글쓴이");
		assertTrue(selectedLikeDto.equals(likeDto));
		
		CommentLikeDto selectedLikeDto2 = freeCommentLikeService.getLike(cno, "글쓴이2");
		assertTrue(selectedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void removeLikesTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(freeCommentLikeService.addLike(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeService.addLike(likeDto2) == 1);
		
		assertTrue(freeCommentLikeService.getCountAll() == 2);
		assertTrue(freeCommentLikeService.removeLikes() == 2);
		assertTrue(freeCommentLikeService.getCountAll() == 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(freeCommentLikeService.addLike(likeDto) == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeService.addLike(likeDto2) == 1);
		assertTrue(freeCommentLikeService.getCountAll() == 2);
		
		assertTrue(freeCommentLikeService.removeLike(likeDto) == 1);
		assertTrue(freeCommentLikeService.getCountAll() == 1);
		
		CommentLikeDto deletedLikeDto2 = freeCommentLikeService.getLike(cno, "글쓴이2");
		assertTrue(deletedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void insertTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(freeCommentLikeService.addLike(likeDto) == 1);
		assertTrue(freeCommentLikeService.getCountAll() == 1);
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이2", false);
		assertTrue(freeCommentLikeService.addLike(likeDto2) == 1);
		assertTrue(freeCommentLikeService.getCountAll() == 2);
		
		CommentLikeDto insertedLikeDto = freeCommentLikeService.getLike(cno, "글쓴이");
		assertTrue(insertedLikeDto.equals(likeDto));
		
		CommentLikeDto insertedLikeDto2 = freeCommentLikeService.getLike(cno, "글쓴이2");
		assertTrue(insertedLikeDto2.equals(likeDto2));
	}
	
	@Test
	@Transactional
	public void updateTest() throws Exception {
		freeCommentLikeService.removeLikes();
		assertTrue(freeCommentLikeService.getCountAll() == 0);
		
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
		assertTrue(freeCommentLikeService.addLike(likeDto) == 1);
		
		CommentLikeDto likeDto2 = new CommentLikeDto(cno, "글쓴이", false);
		assertTrue(freeCommentLikeService.modifyLike(likeDto2) == 1);
		
		CommentLikeDto updatedLikeDto = freeCommentLikeService.getLike(cno, "글쓴이");
		assertTrue(updatedLikeDto.equals(likeDto2));
	}
}
