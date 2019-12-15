package com.javalec.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	@RequestMapping("/view")	//	http://localhost:8888/ex/view�ϸ�
	public String view() {
		return "view";	//	http://localhost:8888/ex/WEB-INF/views/view.jsp�� �����
	}
	
	@RequestMapping("/content/contentView")
	public String content(Model model) {
		//�����͸� �����ϴ� ���
		model.addAttribute("id","abcde");
		return "/content/contentView";
	}
	
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		//�����͸� �����ϴ� ���2
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",30);
		mv.setViewName("board/reply");
		
		return mv;
	}
	
/*
 * Ŭ���� ���� @RequestMapping�� �� �ִ�. �׷� ���� ������ ��Ʈ�ѷ��� ���� ���
 
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

