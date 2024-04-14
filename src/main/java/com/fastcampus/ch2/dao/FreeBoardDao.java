package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.SearchCondition;

public interface FreeBoardDao {

	int count() throws Exception;

	List<BoardDto> selectAll() throws Exception;
	
	List<BoardDto> select10() throws Exception;

	BoardDto select(Integer bno) throws Exception;

	int deleteAll() throws Exception;

	int delete(Integer bno, String writer) throws Exception;

	int insert(BoardDto board) throws Exception;

	int update(BoardDto board) throws Exception;

	int increaseViewcnt(Integer bno) throws Exception;
	
	int increaseCommentcnt(Integer bno) throws Exception;
	
	int decreaseCommentcnt(Integer bno) throws Exception;

	int searchResultCnt(SearchCondition sc) throws Exception;

	List<BoardDto> searchResult(SearchCondition sc) throws Exception;

}