package com.fastcampus.ch2.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch2.domain.CommentDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeCommentServiceImplTest {
	@Autowired
	private FreeCommentService freeCommentService;
	
	@Test
	public void getCountTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		assertTrue(freeCommentService.getCount(-1)==1);
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
	}
	
	@Test
	public void getCommentsTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		freeCommentService.writeComment(comment);
		List<CommentDto> list = freeCommentService.getComments(-1);
		assertTrue(list.size()==1);
		freeCommentService.writeComment(comment);
		list = freeCommentService.getComments(-1);
		assertTrue(list.size()==2);
	}
	
	@Test
	public void getCommentTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		Integer cno = freeCommentService.getComments(-1).get(0).getCno();
		CommentDto comment2 = freeCommentService.getComment(cno);
		assertTrue(cno==comment2.getCno());
	}
	
	@Test
	public void removeCommentsTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		assertTrue(freeCommentService.getCount(-1)==1);
		assertTrue(freeCommentService.removeComments(-1)==1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		assertTrue(freeCommentService.writeComment(comment)==1);
		assertTrue(freeCommentService.writeComment(comment)==1);
		assertTrue(freeCommentService.getCount(-1)==2);
		assertTrue(freeCommentService.removeComments(-1)==2);
		assertTrue(freeCommentService.getCount(-1)==0);
	}
	
	@Test
	public void removeCommentTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		CommentDto comment2 = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		Integer cno = freeCommentService.getComments(-1).get(0).getCno();
		comment.setCno(cno);
		Integer cno2 = freeCommentService.getComments(-1).get(1).getCno();
		comment2.setCno(cno2);
		assertTrue(freeCommentService.removeComment(comment.getCno(),comment.getWriter())==1);
		assertTrue(freeCommentService.getCount(-1)==1);
		CommentDto removedcomment = freeCommentService.getComment(comment.getCno());
		assertTrue(removedcomment==null);
		CommentDto remainedcomment = freeCommentService.getComment(comment2.getCno());
		assertTrue(remainedcomment!=null);
	}
	
	@Test
	public void writeCommentTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		assertTrue(freeCommentService.getCount(-1)==1);
		assertTrue(freeCommentService.writeComment(comment)==1);
		assertTrue(freeCommentService.getCount(-1)==2);
	}
	
	@Test
	public void updateCommentTest() throws Exception {
		freeCommentService.removeComments(-1);
		assertTrue(freeCommentService.getCount(-1)==0);
		
		CommentDto comment = new CommentDto();
		assertTrue(freeCommentService.writeComment(comment)==1);
		Integer cno = freeCommentService.getComments(-1).get(0).getCno();
		String contents = "변경된 내용";
		comment.setCno(cno);
		comment.setContents(contents);
		assertTrue(freeCommentService.updateComment(comment)==1);
		CommentDto updatedComment = freeCommentService.getComment(cno);
		assertTrue(contents.equals(updatedComment.getContents()));
	}
}
