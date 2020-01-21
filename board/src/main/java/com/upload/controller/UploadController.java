package com.upload.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	//로깅을 위한 코드
	private static final Logger logger
		= LoggerFactory.getLogger(UploadController.class);
	
	//servlet-context.xml에 설정한 리소스 bean을 참조.
	//<beans:bean id="uploadPath" class="java.lang.String">
	//bean의 id가 uploadPath인 태그를 참조한다.
	@Resource(name="uploadPath")
	//String uploadPath="c:/upload"; <= 직접 기술해도 된다.
	String uploadPath; // <= 공통적으로 사용하기 위해서
	
	// 업로드 폼 페이지로 이동
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.GET)
	public String uploadForm() {
		// veiws/upload/uploadForm.jsp
		return "upload/uploadForm";
	}
	
	// 업로드된 내용을 처리
	// MultipartFiel file : 업로드한 파일이 저장됨 (임시경로)
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		logger.info("파일이름:" + file.getOriginalFilename());
		logger.info("파일크기:" + file.getSize());
		logger.info("컨텐트 타입:" + file.getContentType());
		
		String savedName = file.getOriginalFilename();
		//데이터를 전달하기 전에 파일이름 앞에 UUID를 붙인다.
		savedName = uploadFile(savedName, file.getBytes());
		
		mav.setViewName("upload/uploadResult");	//뷰의 이름
		mav.addObject("savedName", savedName); 	//전달할 데이터
		
		return mav; // uploadResult.jsp로 포워딩된다.
	}
	
	// 파일 이름이 중복되지 않도록 처리한다.
	// 기존에 올라간 파일과 이름이 같은 파일을 올리게 되면 이름이 중복되지 않게 하기 위해서
	String uploadFile(String originalName, byte[] fileData) throws Exception {
		// Universal Unique IDentifier, 범용고유식별자 => 코드를 랜덤으로 만들어 낸다.
		// 만들어낸 UUID를 파일 이름 앞에 붙인다.
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		
		// new File(디렉토리, 파일이름);
		File target = new File(uploadPath, savedName);
		
		// 첨부파일을 임시 디렉토리에서 우리가 지정한 디렉토리로 복사한다.
		// FileCopyUtils.copy(바이트배열, 파일객체);
		FileCopyUtils.copy(fileData, target);
		
		return savedName; // 복사한 파일이름을 되돌려 준다.
	}
	
}


























