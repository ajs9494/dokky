package com.fastcampus.ch2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.service.QuestionCommentService;
import com.fastcampus.ch2.service.UserService;

@RestController
@RequestMapping("/questionComment")
public class QuestionCommentController {
	@Autowired
	QuestionCommentService questionCommentService;
	@Autowired
	UserService userService;
	
	@PostMapping("/write/{bno}")
	public ResponseEntity<List<CommentDto>> writeComment(@PathVariable Integer bno, @RequestBody CommentDto comment, HttpServletRequest request) {
		try {
			// 유저 닉네임 가져와서 댓글 작성자로 저장
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					comment.setWriter(nickname);
					comment.setBno(bno);
				}
			}
			// 댓글 저장 실패시 에러 상태코드
			if(questionCommentService.writeComment(comment)!=1) return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
			// 댓글 저장 성공시 성공 상태코드와 댓글리스트
			List<CommentDto> list = questionCommentService.getComments(bno);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{cno}")
	public ResponseEntity<List<CommentDto>> removeComment(@PathVariable Integer cno, Integer pcno, String writer, Integer bno, HttpServletRequest request) {
		try {
			// 유저 닉네임 가져와서 댓글 작성자와 같은지 확인
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					// 다르면 에러 상태코드
					if(!nickname.equals(writer)) return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
				}
			}
			// 댓글인경우 해당댓글에 달린 답글도 모두 지운다
			if(cno.equals(pcno)) {
				List<CommentDto> repCheckList = questionCommentService.getComments(bno);
				for (CommentDto c : repCheckList) {
					if(c.getPcno().equals(cno)) {
						if(questionCommentService.removeComment(c.getCno(), c.getWriter())!=1) return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
					}
				}
			// 답글인경우 해당답글만 지운다
			} else {
				// 댓글 삭제 실패시 에러 상태코드
				if(questionCommentService.removeComment(cno, writer)!=1) return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
			}
			// 댓글 삭제 성공시 성공 상태코드와 댓글리스트
			List<CommentDto> list = questionCommentService.getComments(bno);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping("/update/{cno}")
	public ResponseEntity<List<CommentDto>> updateComment(@RequestBody CommentDto comment, HttpServletRequest request) {
		try {
			// 유저 닉네임 가져와서 댓글 작성자와 같은지 확인
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					// 다르면 에러 상태코드
					if(!nickname.equals(comment.getWriter())) return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
				}
			}
			// 댓글 수정 실패시 에러 상태코드
			if(questionCommentService.updateComment(comment)!=1) return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
			// 댓글 수정 성공시 성공 상태코드와 댓글리스트
			List<CommentDto> list = questionCommentService.getComments(comment.getBno());
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
}
