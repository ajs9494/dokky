package com.fastcampus.ch2.controller;

import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.ch2.domain.CommentDto;
import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.domain.PageHandler;
import com.fastcampus.ch2.domain.SearchCondition;
import com.fastcampus.ch2.service.FreeBoardService;
import com.fastcampus.ch2.service.FreeCommentService;
import com.fastcampus.ch2.service.UserService;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {
	@Autowired
	FreeBoardService freeBoardService;
	 @Autowired
	 UserService userService;
	@Autowired
	FreeCommentService freeCommentService;
	
	@GetMapping("/list")
	public String getBoardList(Model m, SearchCondition sc, HttpServletRequest request) {
		try {
			m.addAttribute("req", request);
			int totalCnt = freeBoardService.getSearchResultCnt(sc);
			if(totalCnt==0) return "boardList";
			PageHandler ph = new PageHandler(totalCnt, sc);
			
			List<BoardDto> li = freeBoardService.getSearchResult(sc);
			
			m.addAttribute("li", li);
			m.addAttribute("ph", ph);
			
			Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
			m.addAttribute("startOfToday", startOfToday.toEpochMilli());
			Instant startOfThisYear = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).atStartOfDay(ZoneId.systemDefault()).toInstant();
			m.addAttribute("startOfThisYear", startOfThisYear.toEpochMilli());
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("msg", "LIST_ERR");
		}
		return "boardList";
	}
	
	@GetMapping("/read/{bno}")
	public String getBoardRead(@PathVariable Integer bno, SearchCondition sc, HttpServletRequest request, Model m, RedirectAttributes rattr) {
		try {
			// 게시물dto 받아와서 모델에 저장
			BoardDto board = freeBoardService.getBoard(bno);
			m.addAttribute("board", board);
			m.addAttribute("req", request);
			List<CommentDto> comments = freeCommentService.getComments(bno);
			m.addAttribute("comments", comments);
			Integer commentsCount = comments.size();
			m.addAttribute("commentsCount", commentsCount);
			
			// 유저의 닉네임과 게시물의 작성자가 같은지 확인한 결과값을 모델에 저장 (수정,삭제버튼 유무 결정을 위함)
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					String writer = board.getWriter();
					m.addAttribute("isWriter", writerCheck(nickname, writer));
					m.addAttribute("nickname", nickname);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "READ_ERR");
			sc.setKeyword(URLEncoder.encode(sc.getKeyword()));
			return "redirect:/freeBoard/list" + sc.getQueryString();
		}
		 return "boardRead";
	}
	
	@GetMapping("/write")
	public String getBoardWrite(HttpServletRequest request, SearchCondition sc, Model m) {
		m.addAttribute("req", request);
		// 로그인 했는지 확인
		// 로그인 안했으면 로그인화면으로 보냄
		HttpSession session = request.getSession(false);
		if(session == null) return "redirect:/login/login" + sc.getQueryString() + "&toURL=" + request.getServletPath();
		String id = (String) session.getAttribute("id");
		if(id == null) return "redirect:/login/login" + sc.getQueryString() + "&toURL=" + request.getServletPath();
		
		// 로그인 했으면 글쓰기 화면 보여줌
		return "boardWrite";
	}
	
	@PostMapping("/write")
	public String writeBoard(BoardDto board, HttpServletRequest request, RedirectAttributes rattr, SearchCondition sc) {
		try {
			// 유저닉네임 가져와서 게시글의 작성자로 저장
			HttpSession session = request.getSession(false);
			String id = (String) session.getAttribute("id");
			String nickname = userService.getUser(id).getNickname();
			board.setWriter(nickname);
			
			// 게시글을 DB에 저장하는데 실패하면 작성내용을 그대로 유지한채로 쓰기화면으로 되돌아감
			// 뷰네임을 리턴하는게 아니라 리다이렉트하므로 리다이렉트 어트리뷰트에 데이터를 저장
			if(freeBoardService.writeBoard(board) != 1) {
				// 되돌아간 쓰기화면에서 게시글등록 실패메시지를 alert하기 위해 메시지코드 저장
				rattr.addFlashAttribute("msg", "WRT_ERR");
				// 게시글 DTO 저장
				rattr.addFlashAttribute("board", board);
				// 글쓰기 화면에서 취소를 누를 시 글쓰기 이전에 보던 페이지로 되돌아가기 위한 서치컨디션 데이터 저장
				rattr.addFlashAttribute("searchCondition", sc);
				return "redirect:/freeBoard/write";
			}
			// 성공하면 작성한 게시글 읽기 화면으로 리다이렉트
			// 읽기화면에서 게시글등록 성공메시지를 alert하기 위해 메시지코드 저장
			rattr.addFlashAttribute("msg", "WRT_OK");
			// 방금 작성한 글의 bno 얻어오기
			// 글작성자 닉네임으로 게시글검색한 결과가 최신순으로 나열되므로 첫번째 게시글이 방금쓴글임, bno를 가져온다
			sc.setPage(1);
			sc.setOption("W");
			sc.setKeyword(nickname);
			List<BoardDto> boards = freeBoardService.getSearchResult(sc);
			Integer bno = boards.get(0).getBno();
			// 만약에 '글쓴이11'이 작성잔데 '글쓴이111'등등이 동시에 작성할수도 있으니 그런경우에는 리스트로 리다이렉트
			if(!nickname.equals(boards.get(0).getWriter())) return "redirect:/freeBoard/list";
			return "redirect:/freeBoard/read/" + bno;
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "WRT_ERR");
			rattr.addFlashAttribute("board", board);
			rattr.addFlashAttribute("searchCondition", sc);
			return "redirect:/freeBoard/write";
		}
	}
	
	@DeleteMapping("/remove/{bno}")
	public String removeBoard(@PathVariable Integer bno, SearchCondition sc, HttpServletRequest request, Model m, RedirectAttributes rattr) {
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {
				String id = (String)session.getAttribute("id");
				if(id != null) {
					String nickname = userService.getUser(id).getNickname();
					freeBoardService.removeBoard(bno, nickname);
					// 게시글 지울때 해당 게시글에 달린 댓글도 모두 삭제
					freeCommentService.removeComments(bno);
					// 삭제성공메시지 전달
					rattr.addFlashAttribute("msg", "DEL_OK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "DEL_ERR");
		}
		sc.setKeyword(URLEncoder.encode(sc.getKeyword()));
		return "redirect:/freeBoard/list"+sc.getQueryString();
	}
	
	@PostMapping("/update")
	public String getUpdateBoard(SearchCondition sc, Model m, BoardDto board, HttpServletRequest request) {
		m.addAttribute("req", request);
		m.addAttribute("board", board);
		m.addAttribute("searchCondition", sc);
		return "boardUpdate";
	}
	
	@PatchMapping("/update/{bno}")
	public String updateBoard(@PathVariable Integer bno, BoardDto board, RedirectAttributes rattr, SearchCondition sc) {
		try {
			// 게시글을 DB에 저장하는데 실패하면 작성내용을 그대로 유지한채로 수정화면으로 되돌아감
			// 뷰네임을 리턴하는게 아니라 리다이렉트하므로 리다이렉트 어트리뷰트에 데이터를 저장
			if(freeBoardService.modifyBoard(board) != 1) {
				// 되돌아간 수정화면에서 게시글수정 실패메시지를 alert하기 위해 메시지코드 저장
				rattr.addFlashAttribute("msg", "UPD_ERR");
				// 게시글 DTO 저장
				rattr.addFlashAttribute("board", board);
				// 글수정 화면에서 취소를 누를 시 글쓰기 이전에 보던 페이지로 되돌아가기 위한 서치컨디션 데이터 저장
				rattr.addFlashAttribute("searchCondition", sc);
				return "redirect:/freeBoard/update";
			}
			// 성공하면 수정한 게시글 읽기 화면으로 리다이렉트
			// 읽기화면에서 게시글등록 성공메시지를 alert하기 위해 메시지코드 저장
			rattr.addFlashAttribute("msg", "UPD_OK");

			return "redirect:/freeBoard/read/" + bno;
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "UPD_ERR");
			rattr.addFlashAttribute("board", board);
			rattr.addFlashAttribute("searchCondition", sc);
			return "redirect:/freeBoard/update";
		}
	}
	
	private boolean writerCheck(String nickname, String writer) {
		return nickname.equals(writer);
	}
}
