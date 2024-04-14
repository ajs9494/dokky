package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.SearchCondition;

public interface FreeBoardService {

	int getCount() throws Exception;

	List<BoardDto> getBoards() throws Exception;
	
	List<BoardDto> getBoards10() throws Exception;

	BoardDto getBoard(Integer bno) throws Exception;

	int removeBoards() throws Exception;

	int removeBoard(Integer bno, String writer) throws Exception;

	int writeBoard(BoardDto board) throws Exception;

	int modifyBoard(BoardDto board) throws Exception;

	int getSearchResultCnt(SearchCondition sc) throws Exception;

	List<BoardDto> getSearchResult(SearchCondition sc) throws Exception;

}