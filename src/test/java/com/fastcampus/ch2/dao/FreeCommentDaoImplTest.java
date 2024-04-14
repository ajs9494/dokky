package com.fastcampus.ch2.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.domain.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeCommentDaoImplTest {
	@Autowired
	private FreeCommentDao freeCommentDao;
	
	@Test
	public void countTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		assertTrue(freeCommentDao.count(-1)==1);
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
	}
	
	@Test
	public void selectAllTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		freeCommentDao.insert(comment);
		List<CommentDto> list = freeCommentDao.selectAll(-1);
		assertTrue(list.size()==1);
		freeCommentDao.insert(comment);
		list = freeCommentDao.selectAll(-1);
		assertTrue(list.size()==2);
	}
	
	@Test
	public void selectTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		Integer cno = freeCommentDao.selectAll(-1).get(0).getCno();
		CommentDto comment2 = freeCommentDao.select(cno);
		assertTrue(cno==comment2.getCno());
	}
	
	@Test
	public void deleteAllTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		assertTrue(freeCommentDao.count(-1)==1);
		assertTrue(freeCommentDao.deleteAll(-1)==1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		assertTrue(freeCommentDao.insert(comment)==1);
		assertTrue(freeCommentDao.insert(comment)==1);
		assertTrue(freeCommentDao.count(-1)==2);
		assertTrue(freeCommentDao.deleteAll(-1)==2);
		assertTrue(freeCommentDao.count(-1)==0);
	}
	
	@Test
	public void deleteTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		CommentDto comment2 = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		Integer cno = freeCommentDao.selectAll(-1).get(0).getCno();
		comment.setCno(cno);
		Integer cno2 = freeCommentDao.selectAll(-1).get(1).getCno();
		comment2.setCno(cno2);
		assertTrue(freeCommentDao.delete(comment.getCno(),comment.getWriter())==1);
		assertTrue(freeCommentDao.count(-1)==1);
		CommentDto deletedcomment = freeCommentDao.select(comment.getCno());
		assertTrue(deletedcomment==null);
		CommentDto selectedcomment = freeCommentDao.select(comment2.getCno());
		assertTrue(selectedcomment!=null);
	}
	
	@Test
	public void insertTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		assertTrue(freeCommentDao.count(-1)==1);
		assertTrue(freeCommentDao.insert(comment)==1);
		assertTrue(freeCommentDao.count(-1)==2);
	}
	
	@Test
	public void updateTest() throws Exception {
		freeCommentDao.deleteAll(-1);
		assertTrue(freeCommentDao.count(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentDao.insert(comment)==1);
		Integer cno = freeCommentDao.selectAll(-1).get(0).getCno();
		String contents = "변경된 내용";
		comment.setCno(cno);
		comment.setContents(contents);
		assertTrue(freeCommentDao.update(comment)==1);
		CommentDto updatedComment = freeCommentDao.select(cno);
		assertTrue(contents.equals(updatedComment.getContents()));
	}
}
