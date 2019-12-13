package com.example.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.MemberVO;
import com.example.service.MemberService;
/**
 * Handles requests for the application home page.
 */
@Controller	//해당 클래스가 controller라고 표시하는 것
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private MemberService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! My Fist Spring Program......");
		
		List<MemberVO> memberList = service.selectMember();
		
		model.addAttribute("memberList", memberList);
		
		return "home";
		
		
	}
}





