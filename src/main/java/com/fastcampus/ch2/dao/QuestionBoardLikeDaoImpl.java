package com.fastcampus.ch2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch2.domain.BoardLikeDto;

@Repository
public class QuestionBoardLikeDaoImpl implements QuestionBoardLikeDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.fastcampus.ch2.dao.QuestionBoardLikeMapper.";
	
	@Override
	public int countAll() throws Exception {
		return session.selectOne(namespace + "countAll");
	}
	
	@Override
	public int count(Integer bno, boolean isLiked) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("isLiked", isLiked);
		return session.selectOne(namespace + "count", map);
	}
	
	@Override
	public List<BoardLikeDto> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public BoardLikeDto select(Integer bno, String nickname) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("nickname", nickname);
		return session.selectOne(namespace + "select", map);
	}
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
	}
	
	@Override
	public int delete(Integer bno, String nickname) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("nickname", nickname);
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int insert(BoardLikeDto like) throws Exception {
		return session.insert(namespace + "insert", like);
	}
	
	@Override
	public int update(BoardLikeDto like) throws Exception {
		return session.update(namespace + "update", like);
	}
}
