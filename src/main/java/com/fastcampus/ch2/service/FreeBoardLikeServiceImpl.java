package com.fastcampus.ch2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fastcampus.ch2.dao.FreeBoardLikeDao;
import com.fastcampus.ch2.dao.FreeBoardLikeDaoImpl;
import com.fastcampus.ch2.domain.LikeDto;

@Service
public class FreeBoardLikeServiceImpl implements FreeBoardLikeService {
	@Autowired
	FreeBoardLikeDao freeBoardLikeDao;
	@Autowired
	FreeBoardService freeBoardService;
	@Autowired
	DataSourceTransactionManager tm;
	
	@Override
	public int getCountAll() throws Exception {
		return freeBoardLikeDao.countAll();
	}
	
	@Override
	public int getLikeCnt(Integer bno, boolean isLiked) throws Exception {
		return freeBoardLikeDao.count(bno, isLiked);
	}
	
	@Override
	public List<LikeDto> getLikes() throws Exception {
		return freeBoardLikeDao.selectAll();
	}
	
	@Override
	public LikeDto getLike(Integer bno, String nickname) throws Exception {
		return freeBoardLikeDao.select(bno, nickname);
	}
	
	@Override
	public int removeLikes() throws Exception {
		return freeBoardLikeDao.deleteAll();
	}
	
	@Override
	public int removeLike(LikeDto likeDto) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			if(likeDto.getIsLiked()) {
				freeBoardService.decreaseLikecnt(likeDto.getBno());
				int rowCnt = freeBoardLikeDao.delete(likeDto.getBno(), likeDto.getNickname());
				tm.commit(status);
				return rowCnt;
			} else {
				int rowCnt = freeBoardLikeDao.delete(likeDto.getBno(), likeDto.getNickname());
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
				freeBoardService.increaseLikecnt(likeDto.getBno());
				int rowCnt = freeBoardLikeDao.insert(likeDto);
				tm.commit(status);
				return rowCnt;
			} else {
				int rowCnt = freeBoardLikeDao.insert(likeDto);
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
		return freeBoardLikeDao.update(likeDto);
	}
}
