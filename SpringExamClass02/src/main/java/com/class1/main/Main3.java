package com.class1.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.class1.spring.MemberInfoPrinter;
import com.class1.spring.MemberRegisterService;
import com.class1.spring.RegisterRequest;

public class Main3 {

	public static void main(String[] args) {
		
		//이 프로그램을 정상적으로 실행하려면 
		//1. MemberInfoPrinter 클래스의 setPrinter() 메서드에 @Qualifier("sysout")을 추가해야 한다.
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx3.xml");
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



















