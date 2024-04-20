package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fastcampus.ch2.dao.QuestionBoardLikeDao;
import com.fastcampus.ch2.dao.QuestionBoardLikeDaoImpl;
import com.fastcampus.ch2.domain.LikeDto;

@Service
public class QuestionBoardLikeServiceImpl implements QuestionBoardLikeService {
	@Autowired
	QuestionBoardLikeDao questionBoardLikeDao;
	@Autowired
	QuestionBoardService questionBoardService;
	@Autowired
	DataSourceTransactionManager tm;
	
	@Override
	public int getCountAll() throws Exception {
		return questionBoardLikeDao.countAll();
	}
	
	@Override
	public int getLikeCnt(Integer bno, boolean isLiked) throws Exception {
		return questionBoardLikeDao.count(bno, isLiked);
	}
	
	@Override
	public List<LikeDto> getLikes() throws Exception {
		return questionBoardLikeDao.selectAll();
	}
	
	@Override
	public LikeDto getLike(Integer bno, String nickname) throws Exception {
		return questionBoardLikeDao.select(bno, nickname);
	}
	
	@Override
	public int removeLikes() throws Exception {
		return questionBoardLikeDao.deleteAll();
	}
	
	@Override
	public int removeLike(LikeDto likeDto) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			if(likeDto.getIsLiked()) {
				questionBoardService.decreaseLikecnt(likeDto.getBno());
				int rowCnt = questionBoardLikeDao.delete(likeDto.getBno(), likeDto.getNickname());
				tm.commit(status);
				return rowCnt;
			} else {
				int rowCnt = questionBoardLikeDao.delete(likeDto.getBno(), likeDto.getNickname());
				tm.commit(status);
				return rowCnt;
			}
		} catch (Exception e) {
			tm.rollback(status);
			return 0;
		}
	}
	
	@Override
	public int addLike(LikeDto likeDto) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			if(likeDto.getIsLiked()) {
				questionBoardService.increaseLikecnt(likeDto.getBno());
				int rowCnt = questionBoardLikeDao.insert(likeDto);
				tm.commit(status);
				return rowCnt;
			} else {
				int rowCnt = questionBoardLikeDao.insert(likeDto);
				tm.commit(status);
				return rowCnt;
			}
		} catch (Exception e) {
			tm.rollback(status);
			return 0;
		}
	}
	
	@Override
	public int modifyLike(LikeDto likeDto) throws Exception {
		return questionBoardLikeDao.update(likeDto);
	}
}
