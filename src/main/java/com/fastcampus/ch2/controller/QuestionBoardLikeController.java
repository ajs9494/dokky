package com.fastcampus.ch2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.domain.BoardLikeDto;
import com.fastcampus.ch2.service.QuestionBoardLikeService;
import com.fastcampus.ch2.service.UserService;

@RestController
@RequestMapping("/questionBoardLike")
public class QuestionBoardLikeController {
	@Autowired
	QuestionBoardLikeService questionBoardLikeService;
	@Autowired
	UserService userService;
	
	@GetMapping("/show/{bno}")
	public ResponseEntity<Map<String, Object>> showLikeCnt(@PathVariable Integer bno, HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Integer likeCnt = questionBoardLikeService.getLikeCnt(bno, true);
			Integer dislikeCnt = questionBoardLikeService.getLikeCnt(bno, false);
			map.put("likeCnt", likeCnt);
			map.put("dislikeCnt", dislikeCnt);
			
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					BoardLikeDto likeDto = questionBoardLikeService.getLike(bno, nickname);
					if(likeDto != null) {
						map.put("isLiked", likeDto.getIsLiked());
					}
				}
			}
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add/{bno}")
	public ResponseEntity<Map<String, Object>> addLike(@PathVariable Integer bno, HttpServletRequest request, @RequestBody BoardLikeDto likeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					likeDto.setNickname(nickname);
					likeDto.setBno(bno);
					if(questionBoardLikeService.addLike(likeDto) != 1) return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
					map.put("isLiked", likeDto.getIsLiked());
				}
			}
			Integer likeCnt = questionBoardLikeService.getLikeCnt(bno, true);
			Integer dislikeCnt = questionBoardLikeService.getLikeCnt(bno, false);
			map.put("likeCnt", likeCnt);
			map.put("dislikeCnt", dislikeCnt);
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{bno}")
	public ResponseEntity<Map<String, Object>> removeLike(@PathVariable Integer bno, HttpServletRequest request, @RequestBody BoardLikeDto likeDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					likeDto.setNickname(nickname);
					likeDto.setBno(bno);
					if(questionBoardLikeService.removeLike(likeDto) != 1) return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
				}
			}
			Integer likeCnt = questionBoardLikeService.getLikeCnt(bno, true);
			Integer dislikeCnt = questionBoardLikeService.getLikeCnt(bno, false);
			map.put("likeCnt", likeCnt);
			map.put("dislikeCnt", dislikeCnt);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}
}
