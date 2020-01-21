package com.upload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.service.BoardService;
import com.edu.util.MediaUtils;
import com.edu.util.UploadFileUtils;

@Controller
public class AjaxUploadController {

	// 로깅을 위한 변수
	private static final Logger logger 
		= LoggerFactory.getLogger(AjaxUploadController.class);
	
	@Inject
	BoardService boardService;
	
	// 업로드 디렉토리 servlet-context.xml에 설정되어 있다.
	@Resource(name = "uploadPath")
	String uploadPath;
	
	//------------------------------------------------------------------------------------
	// 파일첨부 페이지로 이동
	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.GET)
	public String uploadAjax() {
		logger.info("uploadAjax Start.......");
		return "upload/uploadAjax";
	}
	
	//------------------------------------------------------------------------------------
	// 드래그를 했을 경우 진행이 되는 메서드
	// 업로드한 파일은 MultipartFile 변수에 저장됨
	// @ResponseBody : jsp로 넘어가는 것이 아니라 데이터 자체를 되돌려 주는 것이다.
	@ResponseBody //json형식으로 리턴한다.
	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.POST,
						produces = "text/plain;charset=utf-8")
	// ResponseEntity<String> : 메시지와 에러코드를 같이 돌려준다.
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		//업로드한 파일 정보와 Http 상태코드를 함께 리턴한다.
		
		// 파일 정보를 로그에 출력한다.
		logger.info("originalName : "	+ file.getOriginalFilename());
		logger.info("fileSize : " 		+ file.getSize());
		logger.info("contentType : " 	+ file.getContentType());
		
		// 업로드한 파일 정보와 Http 상태코드를 함께 리턴.
		// new ResponseEntity<String>(데이터, 상태코드)
		// new ResponseEntity<String>(업로드된 파일이름, 상태코드)
		// file.getBytes() : 파일을 Byte로 풀어서 올린다.
		// HttpStatus.OK : 파일이 올라가면 OK메시지가 떨어진다.
		// 업로드가 잘되었으면 200 코드를 돌려준다.
		
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), 
						file.getBytes()), HttpStatus.OK);
	}

	//------------------------------------------------------------------------------------
	// 이미지 표시 기능
	//------------------------------------------------------------------------------------
	@ResponseBody // view가 아닌 data를 리턴
	@RequestMapping("/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception
	{
		// 서버의 파일을 다운로드하기 위한 스트림
		InputStream in = null; // java.io
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 확장자 검사
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			// 헤데 구성 객체
			HttpHeaders headers = new HttpHeaders();
			
			// InputStream 생성
			in = new FileInputStream(uploadPath + fileName);
			
			if(mType != null) { // 이미지 파일이면
				headers.setContentType(mType);
			} else { // 이미지 파일이 아니면
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				
				// 다운로드용 컨텐트 타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //컨텐트 타입
				
				// 파일이름에 한글이 들어간 경우 스트링.getBytes("언어셑") 
				// 스트링을 바이트배열로 변환
				// new String(바이트배열, "언어셑") => 문자열의 인코딩 변경
				
				// 큰 따옴표 내부에 " \" "
				// 바이트배열을 스트링으로
				// iso-8859-1 : 서유럽언어
				
				/* 아래의 2줄과 동일한 코드
				 * headers.add("Content-Disposition", 
				 * 		"attachment; filename=\""
				 * 		+ new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"");
				 */
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + "\"");
			}
			// 바이트배열, 헤더
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST); // 400에러
		} finally {
			if(in != null)
				in.close(); // 스트림 닫기
		}
		return entity;
	}
	
	//------------------------------------------------------------------------------------
	// 삭제 기능
	//------------------------------------------------------------------------------------
	@ResponseBody // 뷰가 아닌 데이터를 리턴
	@RequestMapping(value="/upload/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName)
	{
		logger.info("fileName: " + fileName);
		
		// 확장자 검사
		// fileName에는 이미지파일의 경우 썸네일 파일 이름이 넘어온다.
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		// \2020\01\01\s_6c2010df-1bff-4240-945c-ea9f503726fd_book01.jpg
		// 이미지 파일이면 원본이미지 삭제
		if(mType != null) {
			String front = fileName.substring(0, 12); // \2020\01\01\
			String end   = fileName.substring(14);
			// File.separatorChar : 유닉스계열(/), 윈도우즈(\)
			
			//썸네일 삭제
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		
		// 원본 파일 삭제(이미지면 썸네일 삭제)
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		// 레코드 삭제
		//boardService.deleteFile(fileName);
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
}



























