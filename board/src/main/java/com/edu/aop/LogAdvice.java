package com.edu.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//------------------------------------------------------------------------------------
//@Controller, @Service, @Repository에 속하는 것이 아니기에 @Component라고 지정한다.
@Component // 스프링에서 관리하는 빈
@Aspect    // 스프링에서 관리하는 AOP Bean. 공통적인 업무를 지원하는 Bean.
// 로깅을 위한 클래스
public class LogAdvice {
	
	//------------------------------------------------------------------------------------
	// 로깅을 위한 변수
	private static final Logger logger
		= LoggerFactory.getLogger(LogAdvice.class);
	
	//------------------------------------------------------------------------------------
	// 포인트컷 - 실행 시점. 
	// @Before(요청 전), @After(요청 후), @Around(요청 전, 후)
	// Controller, Service, DAO의 모든 Method가 실행 전후에 logPrint method가 호출이 된다.
	// execution(리턴자료형 class.method(매개변수))
			// 포인트 컷 설정 : 이 메서드가 언제, 어느 곳에서 실행이 될 것인가?를 설정.
			// controller, serviceImpl, daoImpl에서 매번 작성해야하는 공통의 코드가 있다면
			// 여기에 모아서 작성을 하면 편리하다는 것이다.
			// controller, service, model은 고유의 업무가 있는데 그것에 집중하도록 하고
			// 매번 해야되는 공통의 코드를 이곳에서 작성하자!!!!!
	@Around(
			"execution(* com.edu.controller..*Controller.*(..))"
					+ " or execution(* com.board.controller..*Controller.*(..))"
					+ " or execution(* com.product.controller..*Controller.*(..))"
					+ " or execution(* com.product.service..*Impl.*(..))"
					+ " or execution(* com.product.dao..*Impl.*(..))"
			)
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable
	{
		long start = System.currentTimeMillis(); // 현재 시간을 구한다.
		
		// joinPoint.proceed()를 중심으로 호출 전, 호출 후로 나뉜다.
		Object result = joinPoint.proceed(); // 핵심업무 실행
		
		// 호출한 클래스 이름
		String type = joinPoint.getSignature().getDeclaringTypeName();
		
		String name = "";
		// 호출한 클래스의 이름이 Controller, Service, DAO에 따라서 표시를 해준다.
		if(type.indexOf("Controller") > -1) {
			name = "Controller \t : ";
		} else if(type.indexOf("Service") > -1) {
			name = "Service \t : ";
		} else if(type.indexOf("DAO") > -1) {
			name = "DAO \t : ";
		}
		
		//호출한 클래스, 메서드 정보
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		
		//method에 전달되는 매개변수들
		logger.info(Arrays.toString(joinPoint.getArgs()));
		
		long end = System.currentTimeMillis();
		
		//업무를 실행하는데 걸린 시간을 계산한다.
		long time = end - start;
		logger.info("*****실행시간 : " + time);
		
		return result;
	}
	

} // End - public class LogAdvice

























