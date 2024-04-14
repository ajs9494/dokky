package com.fastcampus.ch2.dao;

import java.util.List;

import com.fastcampus.ch2.domain.CommentDto;

public interface QuestionCommentDao {

	int count(Integer bno) throws Exception;

	List<CommentDto> selectAll(Integer bno) throws Exception;

	CommentDto select(Integer cno) throws Exception;

	int deleteAll(Integer bno) throws Exception;

	int delete(Integer cno, String writer) throws Exception;

	int insert(CommentDto comment) throws Exception;

	int update(CommentDto comment) throws Exception;

}