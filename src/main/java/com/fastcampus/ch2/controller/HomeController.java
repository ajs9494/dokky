package com.fastcampus.ch2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.ch2.domain.BoardDto;
import com.fastcampus.ch2.service.FreeBoardService;
import com.fastcampus.ch2.service.QuestionBoardService;

@Controller
public class HomeController {
	@Autowired
	FreeBoardService freeBoardService;
	@Autowired
	QuestionBoardService questionBoardService;
	
	@RequestMapping("/")
	public String showHome(Model m) {
		try {
			List<BoardDto> fli = freeBoardService.getBoards10();
			m.addAttribute("fli", fli);
			List<BoardDto> qli = questionBoardService.getBoards10();
			m.addAttribute("qli", qli);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
}
