package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.LikeDto;

public interface FreeBoardLikeDao {

	int countAll() throws Exception;

	int count(Integer bno, boolean isLiked) throws Exception;

	List<LikeDto> selectAll() throws Exception;

	LikeDto select(Integer bno, String nickname) throws Exception;

	int deleteAll() throws Exception;

	int delete(Integer bno, String nickname) throws Exception;

	int insert(LikeDto like) throws Exception;

	int update(LikeDto like) throws Exception;

}