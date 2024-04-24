package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.BoardLikeDto;

public interface FreeBoardLikeService {

	int getCountAll() throws Exception;

	int getLikeCnt(Integer bno, boolean isLiked) throws Exception;

	List<BoardLikeDto> getLikes() throws Exception;

	BoardLikeDto getLike(Integer bno, String nickname) throws Exception;

	int removeLikes() throws Exception;

	int removeLike(BoardLikeDto likeDto) throws Exception;

	int addLike(BoardLikeDto likeDto) throws Exception;

	int modifyLike(BoardLikeDto likeDto) throws Exception;

}