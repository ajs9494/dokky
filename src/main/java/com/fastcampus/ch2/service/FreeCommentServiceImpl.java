package com.fastcampus.ch2.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fastcampus.ch2.dao.FreeBoardDao;
import com.fastcampus.ch2.dao.FreeCommentDao;
import com.fastcampus.ch2.domain.CommentDto;

@Service
public class FreeCommentServiceImpl implements FreeCommentService {
	@Autowired
	FreeCommentDao freeCommentDao;
	@Autowired
	FreeBoardDao freeBoardDao;
	@Autowired
	DataSourceTransactionManager tm;
	
	@Override
	public int getCount(Integer bno) throws Exception {
		return freeCommentDao.count(bno);
	}
	
	@Override
	public List<CommentDto> getComments(Integer bno) throws Exception {
		return freeCommentDao.selectAll(bno);
	}
	
	@Override
	public CommentDto getComment(Integer cno) throws Exception {
		return freeCommentDao.select(cno);
	}
	
	@Override
	public int removeComments(Integer bno) throws Exception {
		return freeCommentDao.deleteAll(bno);
	}
	
	@Override
	@Transactional
	public int removeComment(Integer cno, String writer) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			freeBoardDao.decreaseCommentcnt(freeCommentDao.select(cno).getBno());
			int rowCnt = freeCommentDao.delete(cno, writer);
			tm.commit(status);
			return rowCnt;
		} catch (Exception e) {
			tm.rollback(status);
			return 0;
		}
	}
	
	@Override
	@Transactional
	public int writeComment(CommentDto comment) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			freeBoardDao.increaseCommentcnt(comment.getBno());
			int rowCnt = freeCommentDao.insert(comment);
			tm.commit(status);
			return rowCnt;
		} catch (Exception e) {
			tm.rollback(status);
			return 0;
		}
	}
	
	@Override
	public int updateComment(CommentDto comment) throws Exception {
		return freeCommentDao.update(comment);
	}
}
