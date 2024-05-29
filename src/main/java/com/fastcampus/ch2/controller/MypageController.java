package com.fastcampus.ch2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.ch2.domain.UserDto;
import com.fastcampus.ch2.service.UserService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	UserService userService;

	@GetMapping("/home")
	public String showMypage(HttpSession session, Model m, RedirectAttributes rattr) {
		try {
			// 세션이 유지중인지 확인
			if (session != null) {
				String id = (String) session.getAttribute("id");
				if (id != null) {
					UserDto user = userService.getUser(id);
					m.addAttribute("user", user);
					// 세션이 종료되면 메시지와함께 홈으로
				} else {
					rattr.addFlashAttribute("msg", "SES_END");
					return "redirect:/";
				}
			} else {
				rattr.addFlashAttribute("msg", "SES_END");
				return "redirect:/";
			}
			return "mypage";
		} catch (Exception e) {
			e.printStackTrace();
			return "mypage";
		}
	}

	@PatchMapping("/update")
	public String updateUser(UserDto userDto, HttpSession session, RedirectAttributes rattr, String menu) {
		try {
			// 세션이 유지중인지 확인
			if (session != null) {
				String id = (String) session.getAttribute("id");
				userDto.setId(id);
				if (id != null) {
					// 회원정보 변경
					if ("info".equals(menu) && userDto.getEmail() != null) {
						userService.modifyInfo(userDto);
						// 세션에 저장된 닉네임도 변경된 닉네임으로 다시 저장
						session.setAttribute("nickname", userDto.getNickname());
						rattr.addFlashAttribute("msg", "INFO_OK");
						return "redirect:/mypage/home?menu=info";
					// 비밀번호 변경
					} else if ("password".equals(menu) && userDto.getPwd() != null) {
						userService.modifyPwd(userDto);
						rattr.addFlashAttribute("msg", "PWD_OK");
						return "redirect:/mypage/home?menu=password";
					// menu값에 문제가 있는경우
					} else {
						return "redirect:/mypage/home";
					}
					// 세션이 종료되면 메시지와함께 홈으로
				} else {
					rattr.addFlashAttribute("msg", "SES_END");
					return "redirect:/";
				}
			} else {
				rattr.addFlashAttribute("msg", "SES_END");
				return "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if ("info".equals(menu)) {
				rattr.addFlashAttribute("msg", "INFO_ERR");
				return "redirect:/mypage/home?menu=info";
			} else {
				rattr.addFlashAttribute("msg", "PWD_ERR");
				return "redirect:/mypage/home?menu=password";
			}
		}
	}
	
	@DeleteMapping("/withdrawal")
	public String withdrawalMembership(HttpSession session, RedirectAttributes rattr) {
		try {
			if (session != null) {
				String id = (String) session.getAttribute("id");
				if (id != null) {
					userService.removeUser(id);
					session.invalidate();
					rattr.addFlashAttribute("msg", "RMV_OK");
					return "redirect:/";
				// 세션 종료시
				} else {
					rattr.addFlashAttribute("msg", "SES_END");
					return "redirect:/";
				}
			} else {
				rattr.addFlashAttribute("msg", "SES_END");
				return "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "RMV_ERR");
			return "redirect:/mypage/home?menu=withdrawal";
		}
	}
}
