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

import com.fastcampus.ch2.dao.QuestionBoardDao;
import com.fastcampus.ch2.dao.QuestionCommentDao;
import com.fastcampus.ch2.domain.CommentDto;

@Service
public class QuestionCommentServiceImpl implements QuestionCommentService {
	@Autowired
	QuestionCommentDao questionCommentDao;
	@Autowired
	QuestionBoardDao questionBoardDao;
	@Autowired
	DataSourceTransactionManager tm;
	
	@Override
	public int getCount(Integer bno) throws Exception {
		return questionCommentDao.count(bno);
	}
	
	@Override
	public List<CommentDto> getComments(Integer bno) throws Exception {
		return questionCommentDao.selectAll(bno);
	}
	
	@Override
	public CommentDto getComment(Integer cno) throws Exception {
		return questionCommentDao.select(cno);
	}
	
	@Override
	public int removeComments(Integer bno) throws Exception {
		return questionCommentDao.deleteAll(bno);
	}
	
	@Override
	public int removeComment(Integer cno, String writer) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			questionBoardDao.decreaseCommentcnt(questionCommentDao.select(cno).getBno());
			int rowCnt = questionCommentDao.delete(cno, writer);
			tm.commit(status);
			return rowCnt;
		} catch (Exception e) {
			tm.rollback(status);
			return 0;
		}
	}
	
	@Override
	public int writeComment(CommentDto comment) throws Exception {
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try {
			questionBoardDao.increaseCommentcnt(comment.getBno());
			int rowCnt = questionCommentDao.insert(comment);
			tm.commit(status);
			return rowCnt;
		} catch (Exception e) {
			tm.rollback(status);
			return 0;
		}
	}
	
	@Override
	public int updateComment(CommentDto comment) throws Exception {
		return questionCommentDao.update(comment);
	}
}
