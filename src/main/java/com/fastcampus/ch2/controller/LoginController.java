package com.fastcampus.ch2.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.ch2.domain.SearchCondition;
import com.fastcampus.ch2.domain.UserDto;
import com.fastcampus.ch2.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, String toURL, HttpServletRequest request, HttpServletResponse response, SearchCondition sc) throws Exception {
		String msg = URLEncoder.encode("아이디 또는 비밀번호가 일치하지 않습니다", "utf-8");
		// 아이디 패스워드 일치하는지 확인, 일치하지 않으면 메시지와 함께 로그인화면으로 돌아감
		if(!loginCheck(id, pwd)) {
			return "redirect:/login/login?msg="+msg;
		}
		
		// 일치하면 세션객체를 얻어와 아이디를 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		// 아이디 저장 체크 시 쿠키 생성, 응답에 저장
		if(rememberId) {
			Cookie cookie = new Cookie("id", id);
			response.addCookie(cookie);
		// 아이디 저장 체크 해제 시 쿠키 삭제, 응답에 저장
		} else {
			Cookie cookie = new Cookie("id", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		// 홈 또는 가려고 했던 페이지로 이동
		if(toURL == null || toURL == "") {
			return "redirect:/";
		} else if(sc.getPage() == null) {
			return "redirect:" + toURL;
		} else {
			sc.setKeyword(URLEncoder.encode(sc.getKeyword()));
			return "redirect:" + toURL + sc.getQueryString();
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rattr) {
		// 세션을 종료한 후 로그아웃 완료 메시지와 함께 홈으로 감
		session.invalidate();
		rattr.addFlashAttribute("msg", "LOUT_OK");
		return "redirect:/";
	}
	
	// 아이디 패스워드 일치하는지 검사하는 메서드
	private boolean loginCheck(String id, String pwd) {
		UserDto user = userService.getUser(id);
		if(user==null) {
			return false;
		}
		if(!user.getPwd().equals(pwd)) {
			return false;
		}
		return true;
	}
}
