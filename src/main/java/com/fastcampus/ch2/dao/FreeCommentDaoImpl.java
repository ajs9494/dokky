package com.fastcampus.ch2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch2.domain.CommentDto;

@Repository
public class FreeCommentDaoImpl implements FreeCommentDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.fastcampus.ch2.dao.FreeCommentMapper.";
	
	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + "count", bno);
	}
	
	@Override
	public List<CommentDto> selectAll(Integer bno) throws Exception {
		return session.selectList(namespace + "selectAll", bno); 
	}
	
	@Override
	public CommentDto select(Integer cno) throws Exception {
		return session.selectOne(namespace + "select", cno);
	}
	
	@Override
	public int deleteAll(Integer bno) throws Exception {
		return session.delete(namespace + "deleteAll", bno);
	}
	
	@Override
	public int delete(Integer cno, String writer) throws Exception {
		Map map = new HashMap();
		map.put("cno", cno);
		map.put("writer", writer);
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int insert(CommentDto comment) throws Exception {
		return session.insert(namespace + "insert", comment);
	}
	
	@Override
	public int update(CommentDto comment) throws Exception {
		return session.update(namespace + "update", comment);
	}
}
