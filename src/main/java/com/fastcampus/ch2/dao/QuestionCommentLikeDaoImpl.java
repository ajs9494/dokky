package com.fastcampus.ch2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch2.domain.CommentLikeDto;

@Repository
public class QuestionCommentLikeDaoImpl implements QuestionCommentLikeDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.fastcampus.ch2.dao.QuestionCommentLikeMapper.";
	
	@Override
	public int countAll() throws Exception {
		return session.selectOne(namespace + "countAll");
	}
	
	@Override
	public int count(Integer cno, boolean isLiked) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("isLiked", isLiked);
		return session.selectOne(namespace + "count", map);
	}
	
	@Override
	public List<CommentLikeDto> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public CommentLikeDto select(Integer cno, String nickname) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("nickname", nickname);
		return session.selectOne(namespace + "select", map);
	}
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
	}
	
	@Override
	public int delete(Integer cno, String nickname) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("nickname", nickname);
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int insert(CommentLikeDto like) throws Exception {
		return session.insert(namespace + "insert", like);
	}
	
	@Override
	public int update(CommentLikeDto like) throws Exception {
		return session.update(namespace + "update", like);
	}
}
