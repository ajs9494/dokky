package com.fastcampus.ch2.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.SearchCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FreeBoardDaoImplTest {
	@Autowired
	private FreeBoardDao freeBoardDao;
	
	@Test
	public void countTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		freeBoardDao.insert(board);
		assertTrue(freeBoardDao.count()==1);
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
	}
	
	@Test
	public void selectAllTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		freeBoardDao.insert(board);
		List<BoardDto> list = freeBoardDao.selectAll();
		assertTrue(list.size()==1);
		
		board = new BoardDto("제목2", "글쓴이2", "내용2");
		freeBoardDao.insert(board);
		list = freeBoardDao.selectAll();
		assertTrue(list.size()==2);
	}
	
	@Test
	public void selectTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		board.setBno(bno);
		BoardDto board2 = freeBoardDao.select(bno);
		assertTrue(board.equals(board2));
	}
	
	@Test
	public void deleteAllTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		assertTrue(freeBoardDao.count()==1);
		assertTrue(freeBoardDao.deleteAll()==1);
		assertTrue(freeBoardDao.count()==0);
		
		board = new BoardDto("제목1", "글쓴이1", "내용1");
		BoardDto board2 = new BoardDto("제목2", "글쓴이2", "내용2");
		assertTrue(freeBoardDao.insert(board)==1);
		assertTrue(freeBoardDao.insert(board2)==1);
		assertTrue(freeBoardDao.count()==2);
		assertTrue(freeBoardDao.deleteAll()==2);
		assertTrue(freeBoardDao.count()==0);
	}
	
	@Test
	public void deleteTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		BoardDto board2 = new BoardDto("제목2", "글쓴이2", "내용2");
		assertTrue(freeBoardDao.insert(board)==1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		board.setBno(bno);
		assertTrue(freeBoardDao.insert(board2)==1);
		Integer bno2 = freeBoardDao.selectAll().get(1).getBno();
		board2.setBno(bno2);
		assertTrue(freeBoardDao.delete(board.getBno(),board.getWriter())==1);
		assertTrue(freeBoardDao.count()==1);
		BoardDto deletedBoard = freeBoardDao.select(board.getBno());
		assertTrue(deletedBoard==null);
		BoardDto selectedBoard = freeBoardDao.select(board2.getBno());
		assertTrue(selectedBoard!=null);
	}
	
	@Test
	public void insertTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		assertTrue(freeBoardDao.count()==1);
		BoardDto board2 = new BoardDto("제목2", "글쓴이2", "내용2");
		assertTrue(freeBoardDao.insert(board2)==1);
		assertTrue(freeBoardDao.count()==2);
	}
	
	@Test
	public void updateTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		String title = "변경된제목";
		String contents = "변경된내용";
		BoardDto board2 = new BoardDto(title, "글쓴이1", contents);
		board2.setBno(bno);
		assertTrue(freeBoardDao.update(board2)==1);
		assertTrue(freeBoardDao.count()==1);
		BoardDto updatedBoard = freeBoardDao.select(bno);
		assertTrue(updatedBoard.getTitle().equals(title));
		assertTrue(updatedBoard.getContents().equals(contents));
	}
	
	@Test
	public void increaseViewcntTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		Integer bno = freeBoardDao.selectAll().get(0).getBno();
		assertTrue(freeBoardDao.select(bno).getViewcnt()==0);
		assertTrue(freeBoardDao.increaseViewcnt(bno)==1);
		assertTrue(freeBoardDao.select(bno).getViewcnt()==1);
	}
	
	@Test
	public void searchResultCntTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		BoardDto board2 = new BoardDto("타이틀1", "글쓴이2", "제목내용2");
		assertTrue(freeBoardDao.insert(board2)==1);
		
		SearchCondition sc = new SearchCondition(1, 10, "TC", "글쓴이");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("타이틀");
		assertTrue(freeBoardDao.searchResultCnt(sc)==1);
		sc.setKeyword("내용1");
		assertTrue(freeBoardDao.searchResultCnt(sc)==1);
		sc.setKeyword("제목");
		assertTrue(freeBoardDao.searchResultCnt(sc)==2);
		
		sc.setOption("T");
		sc.setKeyword("내용");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("제목");
		assertTrue(freeBoardDao.searchResultCnt(sc)==1);
		sc.setKeyword("1");
		assertTrue(freeBoardDao.searchResultCnt(sc)==2);
		
		sc.setOption("C");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("내용1");
		assertTrue(freeBoardDao.searchResultCnt(sc)==1);
		sc.setKeyword("내용");
		assertTrue(freeBoardDao.searchResultCnt(sc)==2);
		
		sc.setOption("W");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("내용");
		assertTrue(freeBoardDao.searchResultCnt(sc)==0);
		sc.setKeyword("글쓴이1");
		assertTrue(freeBoardDao.searchResultCnt(sc)==1);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardDao.searchResultCnt(sc)==2);
	}
	
	@Test
	public void searchResultTest() throws Exception {
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardDao.insert(board)==1);
		BoardDto board2 = new BoardDto("타이틀1", "글쓴이2", "제목내용2");
		assertTrue(freeBoardDao.insert(board2)==1);
		
		SearchCondition sc = new SearchCondition(1, 10, "TC", "글쓴이");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("타이틀");
		assertTrue(freeBoardDao.searchResult(sc).size()==1);
		sc.setKeyword("내용1");
		assertTrue(freeBoardDao.searchResult(sc).size()==1);
		sc.setKeyword("제목");
		assertTrue(freeBoardDao.searchResult(sc).size()==2);
		Date reg1 = freeBoardDao.searchResult(sc).get(0).getRegdate();
		Date reg2 = freeBoardDao.searchResult(sc).get(1).getRegdate();
		assertTrue(reg1.after(reg2) || reg1.equals(reg2));
		
		sc.setOption("T");
		sc.setKeyword("내용");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("제목");
		assertTrue(freeBoardDao.searchResult(sc).size()==1);
		sc.setKeyword("1");
		assertTrue(freeBoardDao.searchResult(sc).size()==2);
		
		sc.setOption("C");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("내용1");
		assertTrue(freeBoardDao.searchResult(sc).size()==1);
		sc.setKeyword("내용");
		assertTrue(freeBoardDao.searchResult(sc).size()==2);
		
		sc.setOption("W");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("내용");
		assertTrue(freeBoardDao.searchResult(sc).size()==0);
		sc.setKeyword("글쓴이1");
		assertTrue(freeBoardDao.searchResult(sc).size()==1);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardDao.searchResult(sc).size()==2);
		
		freeBoardDao.deleteAll();
		assertTrue(freeBoardDao.count()==0);
		
		for(int i=0; i<11; i++) {
			freeBoardDao.insert(board);
		}
		assertTrue(freeBoardDao.count()==11);
		SearchCondition sc2 = new SearchCondition(2, 10, "TC", "");
		assertTrue(freeBoardDao.searchResult(sc2).size()==1);
	}
}
