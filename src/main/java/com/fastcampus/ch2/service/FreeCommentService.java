package com.fastcampus.ch2.service;

import java.util.List;

import com.fastcampus.ch2.domain.CommentDto;

public interface FreeCommentService {

	int getCount(Integer bno) throws Exception;

	List<CommentDto> getComments(Integer bno) throws Exception;

	CommentDto getComment(Integer cno) throws Exception;

	int removeComments(Integer bno) throws Exception;

	int removeComment(Integer cno, String writer) throws Exception;

	int writeComment(CommentDto comment) throws Exception;

	int updateComment(CommentDto comment) throws Exception;

}