package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.dto.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService service;
	
	//게시물 목록
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void getList(Model model) throws Exception {
		List<BoardVO> list = null;
		list = service.list();
		model.addAttribute("list", list);
	}

	//게시물 작성
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void getWrite() throws Exception {
		
	}

	//게시물 작성
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception {
		service.write(vo);
		return "redirect:/board/list";
	}
	
	//게시물 조회
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
	}
	
	//게시물 수정(게시물을 수정하기 위해 화면을 띄울 때 수정할 값을 가져온다.
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
	}
	
	//게시물 수정(수정한 데이터를 보내주기 위해서)
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {
		service.modify(vo);
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	
	//게시물 삭제
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
		service.delete(bno);
		return "redirect:/board/list";
	}
	
	//게시물 목록(페이징 추가)
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {
		
		/* Math.ceil  : 소수값이 존재할 때 값을 올리는 역할을 하는 함수(3.2 => 4)
		 * Math.floor : 소수값이 존재할 때 값을 버리는 역할을 하는 함수(3.2 => 3)
		 * Math.round : 소수값이 따라 올리거나 버리는 역할을 하는 반올림 함수.
		 */
		
		//게시물 총 건수
		int count = service.count();
		
		//한 페이지당 보여줄 게시물 건수
		int postNum = 10;
		
		//하단 페이징 번호( [게시물 총 건수÷한 페이지에 보여줄 건수]의 올림)
		int pageNum = (int)Math.ceil((double)count/postNum);
		
		//출력할 게시물(displayPost)
		//displayPost는 LIMIT의 첫 번째 값 : 데이터의 위치는 0부터 시작한다.
		//num이 17이면 보여줄 내용은 161 ~ 170까지 데이터이다.
		//select * from board LIMIT 17, 10
		//LIMIT 시작점, 갯수 (위의 경우는 16번째부터 10개를 추출한다.
		int displayPost = (num -1) * postNum;
		
		
		//-------------------------------------------------------------------
		// 하단에 표시할 페이지 번호에 관한 부분
		//-------------------------------------------------------------------
		//한 번에 표시할 페이징 번호의 개수
		int pageNum_cnt = 10;
		
		//-------------------------------------------------------------------
		// 마지막 페이지 번호 = 
		// 		((올림)(현재 페이지번호 / 한번에 표시할 페이지 번호의 갯수))
		// 		x 한번에 표시할 페이지 번호의 갯수
		//-------------------------------------------------------------------
		//표시되는 페이지 번호 중 마지막 번호
		//보고자하는 페이지(num)이 17이면
		//17 ÷ pageNum_cnt로 계산하여 올림한 후에 pageNum_cnt로 곱한다.
		// ceil(17 ÷ 10) => 2 x 10 => 20
		int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
		
		//-------------------------------------------------------------------
		// 시작페이지 =
		//	마지막 페이지 번호 - (한 번에 표시할 페이지 번호의 갯수 -1)
		//-------------------------------------------------------------------
		//표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt-1);
		
		//마지막 번호를 재계산한다.
		int endPageNum_tmp = (int)(Math.ceil((double)count /(double)pageNum_cnt));
		//현재화면의 마지막 페이지가 모든 데이터의 마지막 페이지인지?
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;
		
		List<BoardVO> list = null;
		list = service.listPage(displayPost, postNum);
		model.addAttribute("list", 		list);
		model.addAttribute("pageNum", 	pageNum);
		
		//시작 및 끝 번호
		model.addAttribute("startPageNum", 	startPageNum);
		model.addAttribute("endPageNum", 	endPageNum);
		
		//이전 및 다음
		model.addAttribute("prev", 	prev);
		model.addAttribute("next", 	next);
		
		//현재 페이지
		model.addAttribute("select", num);

	}
	
	
	
	
}


























