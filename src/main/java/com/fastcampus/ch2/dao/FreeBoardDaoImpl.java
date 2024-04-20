package com.fastcampus.ch2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.SearchCondition;

@Repository
public class FreeBoardDaoImpl implements FreeBoardDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.fastcampus.ch2.dao.FreeBoardMapper.";
	
	
	@Override
	public int count() throws Exception {
		return session.selectOne(namespace + "count");
	}
	
	
	@Override
	public List<BoardDto> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public List<BoardDto> select10() throws Exception {
		return session.selectList(namespace + "select10");
	}
	
	
	@Override
	public BoardDto select(Integer bno) throws Exception {
		return session.selectOne(namespace + "select", bno);
	}
	
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
	}
	
	
	@Override
	public int delete(Integer bno, String writer) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		return session.delete(namespace + "delete", map);
	}
	
	
	@Override
	public int insert(BoardDto board) throws Exception {
		return session.insert(namespace + "insert", board);
	}
	
	
	@Override
	public int update(BoardDto board) throws Exception {
		return session.update(namespace + "update", board);
	}
	
	@Override
	public int increaseViewcnt(Integer bno) throws Exception {
		return session.update(namespace + "increaseViewcnt", bno);
	}
	
	@Override
	public int increaseCommentcnt(Integer bno) throws Exception {
		return session.update(namespace + "increaseCommentcnt", bno);
	}
	
	@Override
	public int decreaseCommentcnt(Integer bno) throws Exception {
		return session.update(namespace + "decreaseCommentcnt", bno);
	}
	
	@Override
	public int increaseLikecnt(Integer bno) throws Exception {
		return session.update(namespace + "increaseLikecnt", bno);
	}
	
	@Override
	public int decreaseLikecnt(Integer bno) throws Exception {
		return session.update(namespace + "decreaseLikecnt", bno);
	}
	
	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception {
		return session.selectOne(namespace + "searchResultCnt", sc);
	}
	
	@Override
	public List<BoardDto> searchResult(SearchCondition sc) throws Exception {
		return session.selectList(namespace + "searchResult", sc);
	}
}

