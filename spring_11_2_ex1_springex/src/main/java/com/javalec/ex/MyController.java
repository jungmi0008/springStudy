package com.javalec.ex;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javalec.ex.member.Member;

@Controller
public class MyController {
	@RequestMapping("/view")	//	http://localhost:8888/ex/view하면
	public String view() {
		return "view";	//	http://localhost:8888/ex/WEB-INF/views/view.jsp가 실행됨
	}
	
	//데이터를 전달하는 방법
	@RequestMapping("/content/contentView")
	public String content(Model model) {
		model.addAttribute("id","abcde");
		return "/content/contentView";
	}
	
	//데이터를 전달하는 방법2
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",30);
		mv.setViewName("board/reply");
		
		return mv;
	}
	
	//데이터를 전달하는 방법3
	@RequestMapping("/board/confirmId")
	public String confirmId(HttpServletRequest request, Model model) {
		//http://localhost:8888/ex/board/confirmId?id=abcd&pw=1234
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/confirmId";
	}
	
	//데이터를 전달하는 방법4
	@RequestMapping("/board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		//http://localhost:8888/ex/board/checkId?id=abcd&pw=1234
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/checkId";
	}
	
	//데이터를 전달하는 방법5
//	@RequestMapping("/member/join")
//	public String joinData(@RequestParam("name") String name,@RequestParam("id") String id, @RequestParam("pw") int pw, @RequestParam("email") String email, Model model) {
//		//	http://localhost:8888/ex/member/join?name=홍길동&id=abcd&pw=1234&email=abc@abc.com
//		
//		Member member = new Member();
//		member.addAttribute("name", name);
//		member.addAttribute("id", id);
//		member.addAttribute("pw", pw);
//		member.addAttribute("email", email);
//		
//		model.addAttribute("memberInfo", member);
//	
//		return "/member/join";
//	}
	
	//데이터를 전달하는 방법6(5번을 간단히)
	@RequestMapping("/member/join")
	//	http://localhost:8888/ex/member/join?name=홍길동&id=abcd&pw=1234&email=abc@abc.com
	public String joinData(Member member) {
		return "member/join";
	}
	
	//데이터를 전달하는 방법7
	@RequestMapping("/student/{studentId}")
	//	?studentId=10하는 것 대신에 {studentId}라고 써서 값을 바로 받는다.
	//	http://localhost:8888/ex/student/10
	public String getStudent(@PathVariable String studentId, Model model) {
		model.addAttribute("studentId", studentId);
		return "student/studentView";
	}
	
/*
 * 데이터를 전달하는 방법 8
 * 클래스 위에 @RequestMapping할 수 있다. 그런 경우는 페이지 컨트롤러가 많은 경우 .do같은 거라고 생각하면 된다.
 
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
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/student")
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMetho.GET");
		
		String id = httpServletRequest.getParameter("id");
		System.out.println("id : "+id);
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
}

