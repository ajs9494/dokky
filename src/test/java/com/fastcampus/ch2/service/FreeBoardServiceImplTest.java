package com.fastcampus.ch2.service;

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
public class FreeBoardServiceImplTest {
	@Autowired
	FreeBoardService freeBoardService;
	
	@Test
	public void countTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		freeBoardService.writeBoard(board);
		assertTrue(freeBoardService.getCount()==1);
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
	}
	
	@Test
	public void getBoardsTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		freeBoardService.writeBoard(board);
		List<BoardDto> list = freeBoardService.getBoards();
		assertTrue(list.size()==1);
		
		board = new BoardDto("제목2", "글쓴이2", "내용2");
		freeBoardService.writeBoard(board);
		list = freeBoardService.getBoards();
		assertTrue(list.size()==2);
	}
	
	@Test
	public void getBoardTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardService.writeBoard(board)==1);
		Integer bno = freeBoardService.getBoards().get(0).getBno();
		board.setBno(bno);
		assertTrue(freeBoardService.getBoards().get(0).getViewcnt()==0);
		BoardDto board2 = freeBoardService.getBoard(bno);
		assertTrue(board.equals(board2));
		assertTrue(board2.getViewcnt()==1);
	}
	
	@Test
	public void removeBoardsTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardService.writeBoard(board)==1);
		assertTrue(freeBoardService.getCount()==1);
		assertTrue(freeBoardService.removeBoards()==1);
		assertTrue(freeBoardService.getCount()==0);
		
		board = new BoardDto("제목1", "글쓴이1", "내용1");
		BoardDto board2 = new BoardDto("제목2", "글쓴이2", "내용2");
		assertTrue(freeBoardService.writeBoard(board)==1);
		assertTrue(freeBoardService.writeBoard(board2)==1);
		assertTrue(freeBoardService.getCount()==2);
		assertTrue(freeBoardService.removeBoards()==2);
		assertTrue(freeBoardService.getCount()==0);
	}
	
	@Test
	public void removeBoardTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		BoardDto board2 = new BoardDto("제목2", "글쓴이2", "내용2");
		assertTrue(freeBoardService.writeBoard(board)==1);
		Integer bno = freeBoardService.getBoards().get(0).getBno();
		board.setBno(bno);
		assertTrue(freeBoardService.writeBoard(board2)==1);
		Integer bno2 = freeBoardService.getBoards().get(1).getBno();
		board2.setBno(bno2);
		assertTrue(freeBoardService.removeBoard(board.getBno(),board.getWriter())==1);
		assertTrue(freeBoardService.getCount()==1);
		BoardDto removeBoarddBoard = freeBoardService.getBoard(board.getBno());
		assertTrue(removeBoarddBoard==null);
		BoardDto getBoardedBoard = freeBoardService.getBoard(board2.getBno());
		assertTrue(getBoardedBoard!=null);
	}
	
	@Test
	public void writeBoardTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardService.writeBoard(board)==1);
		assertTrue(freeBoardService.getCount()==1);
		BoardDto board2 = new BoardDto("제목2", "글쓴이2", "내용2");
		assertTrue(freeBoardService.writeBoard(board2)==1);
		assertTrue(freeBoardService.getCount()==2);
	}
	
	@Test
	public void modifyBoardTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardService.writeBoard(board)==1);
		Integer bno = freeBoardService.getBoards().get(0).getBno();
		String title = "변경된제목";
		String contents = "변경된내용";
		BoardDto board2 = new BoardDto(title, "글쓴이1", contents);
		board2.setBno(bno);
		assertTrue(freeBoardService.modifyBoard(board2)==1);
		assertTrue(freeBoardService.getCount()==1);
		BoardDto modifyBoarddBoard = freeBoardService.getBoard(bno);
		assertTrue(modifyBoarddBoard.getTitle().equals(title));
		assertTrue(modifyBoarddBoard.getContents().equals(contents));
	}
	
	@Test
	public void getSearchResultCntTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardService.writeBoard(board)==1);
		BoardDto board2 = new BoardDto("타이틀1", "글쓴이2", "제목내용2");
		assertTrue(freeBoardService.writeBoard(board2)==1);
		
		SearchCondition sc = new SearchCondition(1, 10, "TC", "글쓴이");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("타이틀");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==1);
		sc.setKeyword("내용1");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==1);
		sc.setKeyword("제목");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==2);
		
		sc.setOption("T");
		sc.setKeyword("내용");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("제목");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==1);
		sc.setKeyword("1");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==2);
		
		sc.setOption("C");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("내용1");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==1);
		sc.setKeyword("내용");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==2);
		
		sc.setOption("W");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("내용");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==0);
		sc.setKeyword("글쓴이1");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==1);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardService.getSearchResultCnt(sc)==2);
	}
	
	@Test
	public void getSearchResultTest() throws Exception {
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		BoardDto board = new BoardDto("제목1", "글쓴이1", "내용1");
		assertTrue(freeBoardService.writeBoard(board)==1);
		BoardDto board2 = new BoardDto("타이틀1", "글쓴이2", "제목내용2");
		assertTrue(freeBoardService.writeBoard(board2)==1);
		
		SearchCondition sc = new SearchCondition(1, 10, "TC", "글쓴이");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("타이틀");
		assertTrue(freeBoardService.getSearchResult(sc).size()==1);
		sc.setKeyword("내용1");
		assertTrue(freeBoardService.getSearchResult(sc).size()==1);
		sc.setKeyword("제목");
		assertTrue(freeBoardService.getSearchResult(sc).size()==2);
		Date reg1 = freeBoardService.getSearchResult(sc).get(0).getRegdate();
		Date reg2 = freeBoardService.getSearchResult(sc).get(1).getRegdate();
		assertTrue(reg1.after(reg2) || reg1.equals(reg2));
		
		sc.setOption("T");
		sc.setKeyword("내용");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("제목");
		assertTrue(freeBoardService.getSearchResult(sc).size()==1);
		sc.setKeyword("1");
		assertTrue(freeBoardService.getSearchResult(sc).size()==2);
		
		sc.setOption("C");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("내용1");
		assertTrue(freeBoardService.getSearchResult(sc).size()==1);
		sc.setKeyword("내용");
		assertTrue(freeBoardService.getSearchResult(sc).size()==2);
		
		sc.setOption("W");
		sc.setKeyword("타이틀");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("내용");
		assertTrue(freeBoardService.getSearchResult(sc).size()==0);
		sc.setKeyword("글쓴이1");
		assertTrue(freeBoardService.getSearchResult(sc).size()==1);
		sc.setKeyword("글쓴이");
		assertTrue(freeBoardService.getSearchResult(sc).size()==2);
		
		freeBoardService.removeBoards();
		assertTrue(freeBoardService.getCount()==0);
		
		for(int i=0; i<11; i++) {
			freeBoardService.writeBoard(board);
		}
		assertTrue(freeBoardService.getCount()==11);
		SearchCondition sc2 = new SearchCondition(2, 10, "TC", "");
		assertTrue(freeBoardService.getSearchResult(sc2).size()==1);
	}
}
