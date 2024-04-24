package com.fastcampus.ch2.controller;

import java.util.ArrayList;
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
import com.fastcampus.ch2.domain.CommentLikeDto;
import com.fastcampus.ch2.service.FreeCommentLikeService;
import com.fastcampus.ch2.service.FreeCommentService;
import com.fastcampus.ch2.service.UserService;

@RestController
@RequestMapping("/freeCommentLike")
public class FreeCommentLikeController {
	@Autowired
	FreeCommentLikeService freeCommentLikeService;
	@Autowired
	FreeCommentService freeCommentService;
	@Autowired
	UserService userService;

	@GetMapping("/show/{bno}")
	public ResponseEntity<List<Map<String, Object>>> showLikeCnt(@PathVariable Integer bno, HttpServletRequest request) {
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			String nickname = "";
			HttpSession session = request.getSession(false);
			if (session != null) {
				String id = (String) session.getAttribute("id");
				if (id != null) {
					nickname = userService.getUser(id).getNickname();
				}
			}
			List<CommentDto> commentDtos = freeCommentService.getComments(bno);
			for (CommentDto commentDto : commentDtos) {
				Integer cno = commentDto.getCno();
				Map<String, Object> map = new HashMap<String, Object>();
				Integer likeCnt = freeCommentLikeService.getLikeCnt(cno, true);
				Integer dislikeCnt = freeCommentLikeService.getLikeCnt(cno, false);
				map.put("likeCnt", likeCnt);
				map.put("dislikeCnt", dislikeCnt);

				CommentLikeDto likeDto = freeCommentLikeService.getLike(cno, nickname);
				if (likeDto != null) {
					map.put("isLiked", likeDto.getIsLiked());
				}
				list.add(map);
			}
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add/{cno}")
	public ResponseEntity<Void> addLike(@RequestBody CommentLikeDto likeDto, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				String id = (String) session.getAttribute("id");
				if (id != null) {
					String nickname = userService.getUser(id).getNickname();
					likeDto.setNickname(nickname);
					if(freeCommentLikeService.addLike(likeDto) != 1) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{cno}")
	public ResponseEntity<Void> removeLike(@RequestBody CommentLikeDto likeDto, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				String id = (String) session.getAttribute("id");
				if (id != null) {
					String nickname = userService.getUser(id).getNickname();
					likeDto.setNickname(nickname);
					if(freeCommentLikeService.removeLike(likeDto) != 1) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
