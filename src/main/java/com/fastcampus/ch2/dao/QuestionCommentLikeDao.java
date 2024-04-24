package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.CommentLikeDto;

public interface QuestionCommentLikeDao {

	int countAll() throws Exception;

	int count(Integer cno, boolean isLiked) throws Exception;

	List<CommentLikeDto> selectAll() throws Exception;

	CommentLikeDto select(Integer cno, String nickname) throws Exception;

	int deleteAll() throws Exception;

	int delete(Integer cno, String nickname) throws Exception;

	int insert(CommentLikeDto like) throws Exception;

	int update(CommentLikeDto like) throws Exception;

}