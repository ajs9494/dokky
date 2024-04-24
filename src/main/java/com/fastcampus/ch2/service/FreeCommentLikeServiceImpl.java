package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fastcampus.ch2.dao.FreeCommentLikeDao;
import com.fastcampus.ch2.dao.FreeCommentLikeDaoImpl;
import com.fastcampus.ch2.domain.CommentLikeDto;

@Service
public class FreeCommentLikeServiceImpl implements FreeCommentLikeService {
	@Autowired
	FreeCommentLikeDao freeCommentLikeDao;
	
	@Override
	public int getCountAll() throws Exception {
		return freeCommentLikeDao.countAll();
	}
	
	@Override
	public int getLikeCnt(Integer cno, boolean isLiked) throws Exception {
		return freeCommentLikeDao.count(cno, isLiked);
	}
	
	@Override
	public List<CommentLikeDto> getLikes() throws Exception {
		return freeCommentLikeDao.selectAll();
	}
	
	@Override
	public CommentLikeDto getLike(Integer cno, String nickname) throws Exception {
		return freeCommentLikeDao.select(cno, nickname);
	}
	
	@Override
	public int removeLikes() throws Exception {
		return freeCommentLikeDao.deleteAll();
	}
	
	@Override
	public int removeLike(CommentLikeDto likeDto) throws Exception {
			return freeCommentLikeDao.delete(likeDto.getCno(), likeDto.getNickname());
	}
	
	@Override
	public int addLike(CommentLikeDto likeDto) throws Exception {
			return freeCommentLikeDao.insert(likeDto);
	}
	
	@Override
	public int modifyLike(CommentLikeDto likeDto) throws Exception {
		return freeCommentLikeDao.update(likeDto);
	}
}
