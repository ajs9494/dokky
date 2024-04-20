package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.ch2.dao.QuestionBoardDao;
import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.SearchCondition;

@Service
public class QuestionBoardServiceImpl implements QuestionBoardService {
	@Autowired
	QuestionBoardDao questionBoardDao;
	
	@Override
	public int getCount() throws Exception {
		return questionBoardDao.count();
	}
	
	@Override
	public List<BoardDto> getBoards() throws Exception {
		return questionBoardDao.selectAll();
	}
	
	@Override
	public List<BoardDto> getBoards10() throws Exception {
		return questionBoardDao.select10();
	}
	
	@Override
	public BoardDto getBoard(Integer bno) throws Exception {
		questionBoardDao.increaseViewcnt(bno);
		return questionBoardDao.select(bno);
	}
	
	@Override
	public int removeBoards() throws Exception {
		return questionBoardDao.deleteAll();
	}
	
	@Override
	public int removeBoard(Integer bno, String writer) throws Exception {
		return questionBoardDao.delete(bno, writer);
	}
	
	@Override
	public int writeBoard(BoardDto board) throws Exception {
		return questionBoardDao.insert(board);
	}
	
	@Override
	public int modifyBoard(BoardDto board) throws Exception {
		return questionBoardDao.update(board);
	}
	
	@Override
	public int getSearchResultCnt(SearchCondition sc) throws Exception {
		return questionBoardDao.searchResultCnt(sc);
	}
	
	@Override
	public List<BoardDto> getSearchResult(SearchCondition sc) throws Exception {
		return questionBoardDao.searchResult(sc);
	}
	
	@Override
	public int increaseLikecnt(Integer bno) throws Exception {
		return questionBoardDao.increaseLikecnt(bno);
	}
	
	@Override
	public int decreaseLikecnt(Integer bno) throws Exception {
		return questionBoardDao.decreaseLikecnt(bno);
	}
}
