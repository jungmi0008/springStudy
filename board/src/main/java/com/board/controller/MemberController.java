package com.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.member.dto.MemberDTO;
import com.member.service.MemberService;

@Controller //콘트롤러 빈으로 등록
@RequestMapping("/member/*") //공통적인 url 매핑
public class MemberController {

	//로깅을 위한 변수
	private static final Logger logger =
			LoggerFactory.getLogger(MemberController.class);
	
	//컨트롤러는 서비스를, 서비스는 DAO를 호출한다.
	@Inject
	MemberService memberService;
	
	//회원등록
	@RequestMapping(value="/insertMember.do", method=RequestMethod.GET)
	public ModelAndView insertMember(ModelAndView mav) throws Exception {
		logger.info("insertMember.do GET 실행......");
		System.out.println("Controller.insertMember() GET 호출.....");

		mav.setViewName("member/insertMember");
		return mav;
	}
	
	@RequestMapping(value="/insertMember.do", method=RequestMethod.POST)
	public ModelAndView insertMember(MemberDTO dto, ModelAndView mav) throws Exception {
		logger.info("insertMember.do POST 실행......");
		System.out.println("Controller.insertMember() POST 호출.....");

		
		memberService.insertMember(dto);
		mav.setViewName("member/insertMember");
		return mav;
	}
	
	//로그인 검사
	@RequestMapping("login_check.do")
	public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session) throws Exception {
		//로그인 성공 => 이름이 넘어온다. 실패 => null이 넘어온다.
		
		String name = memberService.loginCheck(dto, session);
		
		ModelAndView mav = new ModelAndView();
		
		logger.info(dto.getId() + " : " + dto.getName());
		
		if(name != null) { //로그인이 성공하면 시작페이지로 이동한다.
			mav.setViewName("main"); //뷰의 이름 : mav.setViewName("main");
		} else { //로그인이 실패하면 login 페이지로 돌아간다.
			mav.setViewName("main"); ////////////////////
			//뷰에 전달할 값
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	//로그 아웃
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) throws Exception {
		memberService.logout(session); //세션초기화
		mav.setViewName("/main"); //이동할 페이지 이름
		mav.addObject("message", "logout"); //메시지 저장
		return mav; //페이지로 이동
	}
	
	
}























