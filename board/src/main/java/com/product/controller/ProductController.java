package com.product.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.product.service.ProductService;

@Controller //현재 클래스를 스프링에서 관리하는 컨트롤로 빈으로 인식시킨다.
@RequestMapping("/product/*") //공통적인 url pattern
public class ProductController {

	// 로깅을 위한 변수
	private static final Logger logger 
		= LoggerFactory.getLogger(ProductController.class);
	
	@Inject // 의존 관계 주입
	// 컨트롤러에서 서비스를 호출할 것이니까 서비스 객체를 스프링에서 만들어 놓았다.
	// 개발자가 객체를 생성하고 의존관계를 설정하는 것이 아니라,
	// 스프링에서 알아서 만들어서 링크시켜준다.
	ProductService productService; // 스프링에서 만든 서비스 객체를 연결시킨다.
	
	@RequestMapping("/list.do") //세부적인 url pattern
	public ModelAndView list(ModelAndView mav) 
	{
		//포워딩할 뷰의 경로
		//mav.setViewName("product/product_list"); // 이동할 페이지
		mav.setViewName("product/product_list"); //이동할 페이지

		//전달할 데이터
		//mav.addObject(변수명, 서비스에서 가져온 목록);
		mav.addObject("list", productService.listProduct());

		//return "redirect:/product/list.do:";
		
		return mav;
	}
	
	// product_list에서 상품명(하이퍼링크)을 누르면 아래와 같은 식으로 주소를 넘긴다.
	// http://localhost/product/detail?product_id=3 방식이 아니라
	// http://localhost/product/detail/3 의 방식으로 주소를 넘긴다.
	// 상품 하나가 고유의 url을 가지게 된다.
	// /3 =>  상품코드가 url에포함되어 있기에, 상품코드가 PathVariable에 저장된다.
	// 상품코드가 {product_id}에 전달된다.
	// {product_id} 변수 앞에 $나 #을 붙으면 안된다.
	@RequestMapping("detail/{product_id}")
	// @PathVariable int product_id => url에 포함된 변수이기 때문에
	//     @PathVariable을 붙여야 한다.
	// ModelAndView => 데이터를 담아서 이동할 수 있는 클래스이다.
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav)
	{
		//포워딩할 뷰의 이름
		mav.setViewName("product/product_detail");

		logger.info("detail Last product_id : " + product_id);

		//뷰에 전달할 데이터
		mav.addObject("dto", productService.detailProduct(product_id));
		
		return mav;
	}
	
}


















