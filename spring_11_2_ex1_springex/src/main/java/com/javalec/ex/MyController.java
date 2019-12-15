package com.javalec.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	@RequestMapping("/view")	//	http://localhost:8888/ex/view하면
	public String view() {
		return "view";	//	http://localhost:8888/ex/WEB-INF/views/view.jsp가 실행됨
	}
	
	@RequestMapping("/content/contentView")
	public String content(Model model) {
		//데이터를 전달하는 방법
		model.addAttribute("id","abcde");
		return "/content/contentView";
	}
	
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		//데이터를 전달하는 방법2
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",30);
		mv.setViewName("board/reply");
		
		return mv;
	}
	
/*
 * 클래스 위에 @RequestMapping할 수 있다. 그런 경우는 페이지 컨트롤러가 많은 경우
 
	@Controller
	@RequestMapping("/board")
	public class MyController {	
	
		@RequestMapping("/reply")
		public ModelAndView reply() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("id",30);
			mv.setViewName("board/reply");
			
			return mv;
		}
*/
}

