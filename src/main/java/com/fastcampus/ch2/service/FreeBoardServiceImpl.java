package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.ch2.dao.FreeBoardDao;
import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.SearchCondition;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	@Autowired
	FreeBoardDao freeBoardDao;
	
	@Override
	public int getCount() throws Exception {
		return freeBoardDao.count();
	}
	
	@Override
	public List<BoardDto> getBoards() throws Exception {
		return freeBoardDao.selectAll();
	}
	
	@Override
	public List<BoardDto> getBoards10() throws Exception {
		return freeBoardDao.select10();
	}
	
	@Override
	public BoardDto getBoard(Integer bno) throws Exception {
		freeBoardDao.increaseViewcnt(bno);
		return freeBoardDao.select(bno);
	}
	
	@Override
	public int removeBoards() throws Exception {
		return freeBoardDao.deleteAll();
	}
	
	@Override
	public int removeBoard(Integer bno, String writer) throws Exception {
		return freeBoardDao.delete(bno, writer);
	}
	
	@Override
	public int writeBoard(BoardDto board) throws Exception {
		return freeBoardDao.insert(board);
	}
	
	@Override
	public int modifyBoard(BoardDto board) throws Exception {
		return freeBoardDao.update(board);
	}
	
	@Override
	public int getSearchResultCnt(SearchCondition sc) throws Exception {
		return freeBoardDao.searchResultCnt(sc);
	}
	
	@Override
	public List<BoardDto> getSearchResult(SearchCondition sc) throws Exception {
		return freeBoardDao.searchResult(sc);
	}
	
	@Override
	public int increaseLikecnt(Integer bno) throws Exception {
		return freeBoardDao.increaseLikecnt(bno);
	}
	
	@Override
	public int decreaseLikecnt(Integer bno) throws Exception {
		return freeBoardDao.decreaseLikecnt(bno);
	}
}
