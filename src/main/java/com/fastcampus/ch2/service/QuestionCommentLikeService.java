package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.CommentLikeDto;

public interface QuestionCommentLikeService {

	int getCountAll() throws Exception;

	int getLikeCnt(Integer cno, boolean isLiked) throws Exception;

	List<CommentLikeDto> getLikes() throws Exception;

	CommentLikeDto getLike(Integer cno, String nickname) throws Exception;

	int removeLikes() throws Exception;

	int removeLike(CommentLikeDto likeDto) throws Exception;

	int addLike(CommentLikeDto likeDto) throws Exception;

	int modifyLike(CommentLikeDto likeDto) throws Exception;

}