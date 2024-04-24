package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.BoardLikeDto;

public interface QuestionBoardLikeDao {

	int countAll() throws Exception;

	int count(Integer bno, boolean isLiked) throws Exception;

	List<BoardLikeDto> selectAll() throws Exception;

	BoardLikeDto select(Integer bno, String nickname) throws Exception;

	int deleteAll() throws Exception;

	int delete(Integer bno, String nickname) throws Exception;

	int insert(BoardLikeDto like) throws Exception;

	int update(BoardLikeDto like) throws Exception;

}