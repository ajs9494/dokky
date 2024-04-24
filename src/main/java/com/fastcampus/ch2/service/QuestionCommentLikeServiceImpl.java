package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fastcampus.ch2.dao.QuestionCommentLikeDao;
import com.fastcampus.ch2.dao.QuestionCommentLikeDaoImpl;
import com.fastcampus.ch2.domain.CommentLikeDto;

@Service
public class QuestionCommentLikeServiceImpl implements QuestionCommentLikeService {
	@Autowired
	QuestionCommentLikeDao questionCommentLikeDao;
	
	@Override
	public int getCountAll() throws Exception {
		return questionCommentLikeDao.countAll();
	}
	
	@Override
	public int getLikeCnt(Integer cno, boolean isLiked) throws Exception {
		return questionCommentLikeDao.count(cno, isLiked);
	}
	
	@Override
	public List<CommentLikeDto> getLikes() throws Exception {
		return questionCommentLikeDao.selectAll();
	}
	
	@Override
	public CommentLikeDto getLike(Integer cno, String nickname) throws Exception {
		return questionCommentLikeDao.select(cno, nickname);
	}
	
	@Override
	public int removeLikes() throws Exception {
		return questionCommentLikeDao.deleteAll();
	}
	
	@Override
	public int removeLike(CommentLikeDto likeDto) throws Exception {
			return questionCommentLikeDao.delete(likeDto.getCno(), likeDto.getNickname());
	}
	
	@Override
	public int addLike(CommentLikeDto likeDto) throws Exception {
			return questionCommentLikeDao.insert(likeDto);
	}
	
	@Override
	public int modifyLike(CommentLikeDto likeDto) throws Exception {
		return questionCommentLikeDao.update(likeDto);
	}
}
