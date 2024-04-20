package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.LikeDto;

public interface FreeBoardLikeService {

	int getCountAll() throws Exception;

	int getLikeCnt(Integer bno, boolean isLiked) throws Exception;

	List<LikeDto> getLikes() throws Exception;

	LikeDto getLike(Integer bno, String nickname) throws Exception;

	int removeLikes() throws Exception;

	int removeLike(LikeDto likeDto) throws Exception;

	int addLike(LikeDto likeDto) throws Exception;

	int modifyLike(LikeDto likeDto) throws Exception;

}