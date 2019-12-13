package com.class1.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.class1.spring.MemberInfoPrinter;
import com.class1.spring.MemberRegisterService;
import com.class1.spring.RegisterRequest;

//----------------------------------------------------------------------------------------
//이프로그램을 실행하면 Exception이 발생합니다.
//잘못된 것이 아니라 일부러 익셉션을 발생시키려고 하는 것입니다.
//----------------------------------------------------------------------------------------
public class Main2 {

	public static void main(String[] args) {
		//----------------------------------------------------------------------------------------
		//이 프로그램을 실행하면 MemberInfoPrinter 클래스의 @Autowired가 적용된 setPrinter() 메서드에
		//MemberPrinter 타입의 2개 Bean 객체 중 어떤 객체를 주입해야 할 지 모르기 때문에
		//Exception이 발생한다.
		//----------------------------------------------------------------------------------------
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx2.xml");
		MemberRegisterService regSvc  = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",  MemberInfoPrinter.class);
		
		RegisterRequest regReq = new RegisterRequest();
		
		regReq.setEmail("honggildong@gilldong.com");
		regReq.setName("홍길동");
		regReq.setPassword("1234");
		regReq.setConfirmPassword("1234");
		regSvc.regist(regReq);

		infoPrinter.printMemberInfo("honggildong@gilldong.com");
	}

}



















