package com.fastcampus.ch2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.ch2.domain.UserDto;
import com.fastcampus.ch2.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	UserService userService;
	
	@GetMapping("/add")
	public String registerForm() {
		return "registerForm";
	}
	
	@PostMapping("/add")
	public String registerAdd(UserDto user, BindingResult result, RedirectAttributes rattr) {
		try {
			// 바인딩에러있으면 회원가입화면으로 되돌아감
			if(result.hasErrors()) {
				return "registerForm";
			}
			
			// 아이디 중복 체크, 중복이면 메세지와 함께 회원가입화면으로 되돌아감
			if(!idCheck(user.getId())) {
				String msg = URLEncoder.encode("아이디가 중복입니다. 다시 입력해 주세요!", "UTF-8");
				return "redirect:/register/add?msg="+msg;
			}
			
			// 닉네임 중복 체크, 중복이면 메세지와 함께 회원가입화면으로 되돌아감
			if(!nicknameCheck(user.getNickname())) {
				String msg = URLEncoder.encode("닉네임이 중복입니다. 다시 입력해 주세요!", "UTF-8");
				return "redirect:/register/add?msg="+msg;
			}
			
			// 회원가입 실패시, 에러메시지와 함께 홈화면으로 감
			if(1!=userService.register(user)) {
				rattr.addFlashAttribute("msg", "REG_ERR");
				return "redirect:/";
			}
			
			// 회원가입 성공시, 성공메시지와 함께 홈으로 감
			rattr.addFlashAttribute("msg", "REG_OK");
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "REG_ERR");
			return "redirect:/";
		}
	}
	
	@GetMapping("/check")
	@ResponseBody
	public ResponseEntity<Boolean> registerCheck(String id, String nickname) {
		try {
			// 아이디 또는 닉네임을 중복체크해서 결과를 boolean으로 반환
			if(id != null) return new ResponseEntity<Boolean>(idCheck(id), HttpStatus.OK);
			return new ResponseEntity<Boolean>(nicknameCheck(nickname), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 아이디 중복 검사 메서드
	private boolean idCheck(String id) {
		List<UserDto> userList = userService.getUsers();
		for(UserDto user : userList) {
			if(id.equals(user.getId())) {
				return false;
			}
		}
		return true;
	}
	
	// 닉네임 중복 검사 메서드
	private boolean nicknameCheck(String nickname) {
		List<UserDto> userList = userService.getUsers();
		for(UserDto user : userList) {
			if(nickname.equals(user.getNickname())) {
				return false;
			}
		}
		return true;
	}
		
}
