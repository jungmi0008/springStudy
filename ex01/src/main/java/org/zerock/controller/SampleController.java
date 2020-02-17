package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
//	@InitBinder
//	public void InitBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	@RequestMapping("")
	public void basic() {
		log.info("basic..........");
	}
	
	@RequestMapping(value="/basic", method={RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get..........");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get..........");
	}
	
	@GetMapping("/ex01")
//	http://localhost:9999/sample/ex01?name=AAA&age=10
	public String ex01(SampleDTO dto) {
		log.info(">>>" + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
//	http://localhost:9999/sample/ex02?name=AAA&age=10
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : "+name);
		log.info("age : "+age);
		return "ex02";
	}
	
	@GetMapping("/ex02List")
//	http://localhost:9999/sample/ex02List?ids=111&ids=222&ids=333
//	list의 사용
	public String ex02List(@RequestParam("ids")ArrayList<String>ids) {
		log.info("ids"+ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
//	http://localhost:9999/sample/ex02List?ids=111&ids=222&ids=333
//	배열의 사용
	public String ex02Array(@RequestParam("ids")String[] ids) {
		log.info("array ids : "+Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
//	http://localhost:9999/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
//	List의 사용
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : "+list);
		return "ex02Bean";
	}
	
	@GetMapping("/ex03")
//	http://localhost:9999/sample/ex03?title=test&dueDate=2018-01-01
//	@DateTimeFormat의 사용
	public String ex03(TodoDTO todo) {
		log.info("todo : "+todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
//	@ModelAttribute("page")여기에서 page는 어디에도 선언되지 않는 파라미터이다.
//	@ModelAttribute는 그런 파라미터를 무조건적으로 받아서 화면으로 넘겨준다.
	public String ex04(SampleDTO dto, @ModelAttribute("page")int page) {
		log.info("dto : "+dto);
		log.info("page : "+page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06")
//	http://localhost:9999/sample/ex06
//	@ResponseBody를 사용하여 view로 바로가지 않게 한다.
//	리턴이 vo, dto타입이면 json으로 response된다.
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07..........");
		
		//{"name" : "홍길동"}
		String msg = "{\"name\": \"홍길돌\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
//	exUpload 페이지를 열어준다.
	public void exUpload() {
		log.info("/exUpload..........");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file ->{
			log.info("-------------------------------------");
			log.info("name:"+file.getOriginalFilename());
			log.info("size:"+file.getSize());
		});
	}
}
